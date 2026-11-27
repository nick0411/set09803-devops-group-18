package com.napier.devops.integrationTests;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.City;
import com.napier.devops.repository.CityRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityRepositoryIntegrationTest {

    private static Connection con;

    @BeforeAll
    static void setUp() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String dbHost = System.getenv("DB_HOST");
        if (dbHost == null || dbHost.isEmpty()) {
            dbHost = "localhost:3306";
        }

        dbHandler.connect(dbHost);
        con = dbHandler.getConnection();
    }

    @AfterAll
    static void tearDown() {
        if (con != null) {
            try {
                con.close();
                System.out.println("City integration test database connection closed.");
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    // --- All Cities Tests (Req 7 - 11) ---

    @Test
    void testGetAllCitiesWorld() {
        if (con == null) return;
        List<City> cities = new CityRepository(con).getAllCitiesWorld();
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    @Test
    void testGetAllCitiesContinent() {
        if (con == null) return;
        List<City> cities = new CityRepository(con).getAllCitiesContinent("Asia");
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    @Test
    void testGetAllCitiesRegion() {
        if (con == null) return;
        List<City> cities = new CityRepository(con).getAllCitiesRegion("Southeast Asia");
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    @Test
    void testGetAllCitiesCountry() {
        if (con == null) return;
        List<City> cities = new CityRepository(con).getAllCitiesCountry("Myanmar");
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        for (City city : cities) {
            assertEquals("Myanmar", city.getCountry());
        }
    }

    @Test
    void testGetAllCitiesDistrict() {
        if (con == null) return;
        List<City> cities = new CityRepository(con).getAllCitiesDistrict("England");
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    // --- Top N Cities Tests (Req 12 - 16) ---

    @Test
    void testGetTopNCitiesWorld() {
        if (con == null) return;
        int n = 5;
        List<City> cities = new CityRepository(con).getTopNCitiesWorld(n);
        assertNotNull(cities);
        assertEquals(n, cities.size());
    }

    @Test
    void testGetTopNCitiesContinent() {
        if (con == null) return;
        int n = 5;
        List<City> cities = new CityRepository(con).getTopNCitiesContinent("Asia", n);
        assertNotNull(cities);
        assertTrue(cities.size() <= n);
        assertFalse(cities.isEmpty());
    }

    @Test
    void testGetTopNCitiesRegion() {
        if (con == null) return;
        int n = 5;
        List<City> cities = new CityRepository(con).getTopNCitiesRegion("Southeast Asia", n);
        assertNotNull(cities);
        assertTrue(cities.size() <= n);
        assertFalse(cities.isEmpty());
    }

    @Test
    void testGetTopNCitiesCountry() {
        if (con == null) return;
        int n = 3;
        List<City> cities = new CityRepository(con).getTopNCitiesCountry("Myanmar", n);
        assertNotNull(cities);
        assertTrue(cities.size() <= n);
        for (City city : cities) {
            assertEquals("Myanmar", city.getCountry());
        }
    }

    @Test
    void testGetTopNCitiesDistrict() {
        if (con == null) return;
        int n = 2;
        List<City> cities = new CityRepository(con).getTopNCitiesDistrict("England", n);
        assertNotNull(cities);
        assertTrue(cities.size() <= n);
    }
}