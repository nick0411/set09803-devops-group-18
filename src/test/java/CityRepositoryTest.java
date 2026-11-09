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

    @Test
    public void testTopNCitiesContinent() {
        DatabaseHandler db = new DatabaseHandler();
        db.connect("localhost:3306");

        try {
            Connection con = db.getConnection();
            assertNotNull(con, "Database connection failed");

            CityRepository repo = new CityRepository(con);
            List<City> cities = repo.getTopNCitiesContinent("Asia", 5);

            assertEquals(5, cities.size(),
                    "Expected exactly 5 cities in Asia");

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


    @Test
    public void testTopNCitiesRegion() {
        DatabaseHandler db = new DatabaseHandler();
        db.connect("localhost:3306");

        try {
            Connection con = db.getConnection();
            assertNotNull(con, "Database connection failed");

            CityRepository repo = new CityRepository(con);
            List<City> cities =
                    repo.getTopNCitiesRegion("Southeast Asia", 5);

            assertEquals(5, cities.size(),
                    "Expected exactly 5 cities in Southeast Asia");

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

    @Test
    public void testTopNCitiesCountry() {
        DatabaseHandler db = new DatabaseHandler();
        db.connect("localhost:3306");

        try {
            Connection con = db.getConnection();
            assertNotNull(con, "Database connection failed");

            CityRepository repo = new CityRepository(con);
            List<City> cities = repo.getTopNCitiesCountry("MMR", 5);

            assertEquals(5, cities.size(),
                    "Expected exactly 5 cities in Myanmar");

            for (int i = 0; i < cities.size(); i++) {
                assertEquals("MMR", cities.get(i).getCountry(),
                        "Every city should belong to Myanmar");

                if (i > 0) {
                    assertTrue(
                            cities.get(i - 1).getPopulation()
                                    >= cities.get(i).getPopulation(),
                            "Cities should be sorted by population"
                    );
                }
            }
        } finally {
            db.disconnect();
        }
    }

    @Test
    public void testTopNCitiesDistrict() {
        DatabaseHandler db = new DatabaseHandler();
        db.connect("localhost:3306");

        try {
            Connection con = db.getConnection();
            assertNotNull(con, "Database connection failed");

            CityRepository repo = new CityRepository(con);
            List<City> cities =
                    repo.getTopNCitiesDistrict("California", 5);

            assertEquals(5, cities.size(),
                    "Expected exactly 5 cities in California");

            for (int i = 0; i < cities.size(); i++) {
                assertEquals("California", cities.get(i).getDistrict(),
                        "Every city should belong to California");

                if (i > 0) {
                    assertTrue(
                            cities.get(i - 1).getPopulation()
                                    >= cities.get(i).getPopulation(),
                            "Cities should be sorted by population"
                    );
                }
            }
        } finally {
            db.disconnect();
        }
    }


}
