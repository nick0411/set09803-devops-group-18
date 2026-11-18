
import com.napier.devops.model.City;
import com.napier.devops.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CityRepositoryTest {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rset;
    private CityRepository repo;

    @BeforeEach
    public void setUp() throws Exception {
        con = mock(Connection.class);
        stmt = mock(PreparedStatement.class);
        rset = mock(ResultSet.class);

        when(con.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rset);

        repo = new CityRepository(con);
    }

    private void mockTwoCities() throws Exception {
        when(rset.next()).thenReturn(true, true, false);

        when(rset.getString("Name"))
                .thenReturn("Yangon", "Mandalay");
        when(rset.getString("CountryCode"))
                .thenReturn("MMR", "MMR");
        when(rset.getString("District"))
                .thenReturn("Yangon", "Mandalay");
        when(rset.getLong("Population"))
                .thenReturn(3361700L, 885300L);
    }

    private void assertCityResults(List<City> cities) {
        assertEquals(2, cities.size());
        assertEquals("Yangon", cities.get(0).getName());
        assertEquals("Mandalay", cities.get(1).getName());
        assertEquals("MMR", cities.get(0).getCountry());
        assertTrue(cities.get(0).getPopulation()
                >= cities.get(1).getPopulation());
    }

    @Test
    public void testTopNCitiesWorld() throws Exception {
        mockTwoCities();

        List<City> cities = repo.getTopNCitiesWorld(5);

        assertCityResults(cities);
        verify(stmt).setInt(1, 5);
        verify(con).prepareStatement(
                contains("ORDER BY Population DESC"));
    }

    @Test
    public void testTopNCitiesContinent() throws Exception {
        mockTwoCities();

        List<City> cities =
                repo.getTopNCitiesContinent("Asia", 5);

        assertCityResults(cities);
        verify(stmt).setString(1, "Asia");
        verify(stmt).setInt(2, 5);
        verify(con).prepareStatement(
                contains("country.Continent = ?"));
    }

    @Test
    public void testTopNCitiesRegion() throws Exception {
        mockTwoCities();

        List<City> cities =
                repo.getTopNCitiesRegion("Southeast Asia", 5);

        assertCityResults(cities);
        verify(stmt).setString(1, "Southeast Asia");
        verify(stmt).setInt(2, 5);
        verify(con).prepareStatement(
                contains("country.Region = ?"));
    }

    @Test
    public void testTopNCitiesCountry() throws Exception {
        mockTwoCities();

        List<City> cities =
                repo.getTopNCitiesCountry("MMR", 5);

        assertCityResults(cities);
        verify(stmt).setString(1, "MMR");
        verify(stmt).setInt(2, 5);
        verify(con).prepareStatement(
                contains("WHERE CountryCode = ?"));
    }

    @Test
    public void testTopNCitiesDistrict() throws Exception {
        mockTwoCities();

        List<City> cities =
                repo.getTopNCitiesDistrict("Yangon", 5);

        assertCityResults(cities);
        verify(stmt).setString(1, "Yangon");
        verify(stmt).setInt(2, 5);
        verify(con).prepareStatement(
                contains("WHERE District = ?"));
    }

    @Test
    public void testEmptyResults() throws Exception {
        when(rset.next()).thenReturn(false);

        List<City> cities = repo.getTopNCitiesWorld(5);

        assertNotNull(cities);
        assertTrue(cities.isEmpty());
        verify(stmt).setInt(1, 5);
    }
}
