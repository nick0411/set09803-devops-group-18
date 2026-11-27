package com.napier.devops.integrationTests;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.CapitalCity;
import com.napier.devops.repository.CapitalCityRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CapitalCityRepositoryIntegrationTest {

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
                System.out.println("Capital City integration test database connection closed.");
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    // --- Requirement 17: All Capital Cities in the World ---
    @Test
    void testGetAllCapitalsWorldIntegration() {
        if (con == null) {
            System.out.println("Skipping test: No database connection available.");
            return;
        }

        CapitalCityRepository repo = new CapitalCityRepository(con);
        List<CapitalCity> capitals = repo.getAllCapitalsWorld();

        assertNotNull(capitals);
        assertFalse(capitals.isEmpty(), "World capital cities report should return rows from the database");
    }

    // --- Requirement 18: All Capital Cities in a Continent ---
    @Test
    void testGetAllCapitalsContinentIntegration() {
        if (con == null) return;

        CapitalCityRepository repo = new CapitalCityRepository(con);
        List<CapitalCity> capitals = repo.getAllCapitalsContinent("Asia");

        assertNotNull(capitals);
        assertFalse(capitals.isEmpty(), "Asia capital cities report should return rows");
    }

    // --- Requirement 19: All Capital Cities in a Region ---
    @Test
    void testGetAllCapitalsRegionIntegration() {
        if (con == null) return;

        CapitalCityRepository repo = new CapitalCityRepository(con);
        List<CapitalCity> capitals = repo.getAllCapitalsRegion("Southeast Asia");

        assertNotNull(capitals);
        assertFalse(capitals.isEmpty(), "Southeast Asia capital cities report should return rows");
    }

    // --- Requirement 20: Top N Capital Cities in the World ---
    @Test
    void testGetTopNCapitalsWorldIntegration() {
        if (con == null) return;

        CapitalCityRepository repo = new CapitalCityRepository(con);
        int n = 5;
        List<CapitalCity> capitals = repo.getTopNCapitalsWorld(n);

        assertNotNull(capitals);
        assertEquals(n, capitals.size(), "Should return exactly top N capital cities in the world");
    }

    // --- Requirement 21: Top N Capital Cities in a Continent ---
    @Test
    void testGetTopNCapitalsContinentIntegration() {
        if (con == null) return;

        CapitalCityRepository repo = new CapitalCityRepository(con);
        int n = 3;
        List<CapitalCity> capitals = repo.getTopNCapitalsContinent("Asia", n);

        assertNotNull(capitals);
        assertTrue(capitals.size() <= n, "Should return at most N capital cities for the continent");
        assertFalse(capitals.isEmpty());
    }

    // --- Requirement 22: Top N Capital Cities in a Region ---
    @Test
    void testGetTopNCapitalsRegionIntegration() {
        if (con == null) return;

        CapitalCityRepository repo = new CapitalCityRepository(con);
        int n = 3;
        List<CapitalCity> capitals = repo.getTopNCapitalsRegion("Southeast Asia", n);

        assertNotNull(capitals);
        assertTrue(capitals.size() <= n, "Should return at most N capital cities for the region");
        assertFalse(capitals.isEmpty());
    }
}