package com.napier.devops.unitTests;

import com.napier.devops.model.City;
import com.napier.devops.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CityRepositoryTest {

    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rset;
    private CityRepository repo;

    @BeforeEach
    public void setUp() throws Exception {
        con = mock(Connection.class);
        pstmt = mock(PreparedStatement.class);
        stmt = mock(Statement.class);
        rset = mock(ResultSet.class);

        // Mock for standard Statement (getAllCitiesWorld)
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rset);

        // Mock for PreparedStatement (filtered & top-N queries)
        when(con.prepareStatement(anyString())).thenReturn(pstmt);
        when(pstmt.executeQuery()).thenReturn(rset);

        repo = new CityRepository(con);
    }

    private void mockTwoCities() throws Exception {
        when(rset.next()).thenReturn(true, true, false);

        when(rset.getString("CityName"))
                .thenReturn("Yangon", "Mandalay");
        when(rset.getString("CountryName"))
                .thenReturn("Myanmar", "Myanmar");
        when(rset.getString("District"))
                .thenReturn("Yangon", "Mandalay");
        when(rset.getLong("Population"))
                .thenReturn(3361700L, 885300L);
    }

    private void assertCityResults(List<City> cities) {
        assertEquals(2, cities.size());
        assertEquals("Yangon", cities.get(0).getName());
        assertEquals("Mandalay", cities.get(1).getName());
        assertEquals("Myanmar", cities.get(0).getCountry());
        assertTrue(cities.get(0).getPopulation()
                >= cities.get(1).getPopulation());
    }

    @Test
    public void testGetAllCitiesWorld() throws Exception {
        mockTwoCities();

        List<City> cities = repo.getAllCitiesWorld();

        assertCityResults(cities);
        verify(con).createStatement();
        verify(stmt).executeQuery(contains("ORDER BY ci.Population DESC"));
    }

    @Test
    public void testGetAllCitiesContinent() throws Exception {
        mockTwoCities();

        List<City> cities = repo.getAllCitiesContinent("Asia");

        assertCityResults(cities);
        verify(pstmt).setString(1, "Asia");
        verify(pstmt).executeQuery();
    }

    @Test
    public void testGetAllCitiesRegion() throws Exception {
        mockTwoCities();

        List<City> cities = repo.getAllCitiesRegion("Southeast Asia");

        assertCityResults(cities);
        verify(pstmt).setString(1, "Southeast Asia");
        verify(pstmt).executeQuery();
    }

    @Test
    public void testGetAllCitiesCountry() throws Exception {
        mockTwoCities();

        List<City> cities = repo.getAllCitiesCountry("Myanmar");

        assertCityResults(cities);
        verify(pstmt).setString(1, "Myanmar");
        verify(pstmt).executeQuery();
    }

    @Test
    public void testGetAllCitiesDistrict() throws Exception {
        mockTwoCities();

        List<City> cities = repo.getAllCitiesDistrict("Yangon");

        assertCityResults(cities);
        verify(pstmt).setString(1, "Yangon");
        verify(pstmt).executeQuery();
    }

    @Test
    public void testTopNCitiesWorld() throws Exception {
        mockTwoCities();

        List<City> cities = repo.getTopNCitiesWorld(5);

        assertCityResults(cities);
        verify(pstmt).setInt(1, 5);
        verify(pstmt).executeQuery();
    }

    @Test
    public void testTopNCitiesContinent() throws Exception {
        mockTwoCities();

        List<City> cities =
                repo.getTopNCitiesContinent("Asia", 5);

        assertCityResults(cities);
        verify(pstmt).setString(1, "Asia");
        verify(pstmt).setInt(2, 5);
        verify(pstmt).executeQuery();
    }

    @Test
    public void testTopNCitiesRegion() throws Exception {
        mockTwoCities();

        List<City> cities =
                repo.getTopNCitiesRegion("Southeast Asia", 5);

        assertCityResults(cities);
        verify(pstmt).setString(1, "Southeast Asia");
        verify(pstmt).setInt(2, 5);
        verify(pstmt).executeQuery();
    }

    @Test
    public void testTopNCitiesCountry() throws Exception {
        mockTwoCities();

        List<City> cities =
                repo.getTopNCitiesCountry("Myanmar", 5);

        assertCityResults(cities);
        verify(pstmt).setString(1, "Myanmar");
        verify(pstmt).setInt(2, 5);
        verify(pstmt).executeQuery();
    }

    @Test
    public void testTopNCitiesDistrict() throws Exception {
        mockTwoCities();

        List<City> cities =
                repo.getTopNCitiesDistrict("Yangon", 5);

        assertCityResults(cities);
        verify(pstmt).setString(1, "Yangon");
        verify(pstmt).setInt(2, 5);
        verify(pstmt).executeQuery();
    }

    @Test
    public void testEmptyResults() throws Exception {
        when(rset.next()).thenReturn(false);

        List<City> cities = repo.getAllCitiesWorld();

        assertNotNull(cities);
        assertTrue(cities.isEmpty());
        verify(con).createStatement();
    }
}