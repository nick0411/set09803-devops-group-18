package com.napier.devops.integrationTests;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.Country;
import com.napier.devops.repository.CountryRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryRepositoryIntegrationTest {

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
                System.out.println("Country integration test database connection closed.");
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    // --- Requirement 1: All Countries in the World ---
    @Test
    void testGetAllCountriesWorld() {
        if (con == null) {
            System.out.println("Skipping test: No database connection available.");
            return;
        }

        CountryRepository repo = new CountryRepository(con);
        List<Country> countries = repo.getAllCountriesWorld();

        assertNotNull(countries);
        assertFalse(countries.isEmpty(), "World countries report should return rows from the database");
    }

    // --- Requirement 2: All Countries in a Continent ---
    @Test
    void testGetAllCountriesContinent() {
        if (con == null) return;

        CountryRepository repo = new CountryRepository(con);
        List<Country> countries = repo.getAllCountriesContinent("Asia");

        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        for (Country country : countries) {
            assertEquals("Asia", country.getContinent());
        }
    }

    // --- Requirement 3: All Countries in a Region ---
    @Test
    void testGetAllCountriesRegion() {
        if (con == null) return;

        CountryRepository repo = new CountryRepository(con);
        List<Country> countries = repo.getAllCountriesRegion("Southeast Asia");

        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        for (Country country : countries) {
            assertEquals("Southeast Asia", country.getRegion());
        }
    }

    // --- Requirement 4: Top N Countries in the World ---
    @Test
    void testGetTopNCountriesWorld() {
        if (con == null) return;

        CountryRepository repo = new CountryRepository(con);
        int n = 5;
        List<Country> countries = repo.getTopNCountriesWorld(n);

        assertNotNull(countries);
        assertEquals(n, countries.size(), "Should return exactly top N countries");
    }

    // --- Requirement 5: Top N Countries in a Continent ---
    @Test
    void testGetTopNCountriesContinent() {
        if (con == null) return;

        CountryRepository repo = new CountryRepository(con);
        int n = 3;
        List<Country> countries = repo.getTopNCountriesContinent("Asia", n);

        assertNotNull(countries);
        assertTrue(countries.size() <= n, "Should return at most N countries");
        for (Country country : countries) {
            assertEquals("Asia", country.getContinent());
        }
    }

    // --- Requirement 6: Top N Countries in a Region ---
    @Test
    void testGetTopNCountriesRegion() {
        if (con == null) return;

        CountryRepository repo = new CountryRepository(con);
        int n = 3;
        List<Country> countries = repo.getTopNCountriesRegion("Southeast Asia", n);

        assertNotNull(countries);
        assertTrue(countries.size() <= n, "Should return at most N countries");
        for (Country country : countries) {
            assertEquals("Southeast Asia", country.getRegion());
        }
    }
}