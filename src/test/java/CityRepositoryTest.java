import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.City;
import com.napier.devops.repository.CityRepository;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CityRepositoryTest {

    @Test
    public void testTopNCitiesWorld() {
        DatabaseHandler db = new DatabaseHandler();
        db.connect("localhost:3306");

        try {
            Connection con = db.getConnection();
            assertNotNull(con, "Database connection failed");

            CityRepository repo = new CityRepository(con);
            List<City> cities = repo.getTopNCitiesWorld(5);

            assertEquals(5, cities.size(),
                    "Expected exactly 5 cities");

            for (int i = 1; i < cities.size(); i++) {
                assertTrue(
                        cities.get(i - 1).getPopulation()
                                >= cities.get(i).getPopulation(),
                        "Cities should be sorted by population"
                );
            }
        } finally {
            db.disconnect();
        }
    }
}
