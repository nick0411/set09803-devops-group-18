package com.napier.devops.IntegrationTests;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.Population;
import com.napier.devops.repository.PopulationRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PopulationRepositoryIntegrationTest {

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
                System.out.println("Integration test database connection closed.");
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    @Test
    void testContinentPopulationReportIntegration() {
        if (con == null) {
            System.out.println("Skipping test: No database connection available.");
            return;
        }

        PopulationRepository repo = new PopulationRepository(con);
        List<Population> report = repo.getContinentPopulationReport();

        assertNotNull(report);
        assertFalse(report.isEmpty());

        boolean foundAsia = report.stream().anyMatch(p -> "Asia".equals(p.getName()));
        assertTrue(foundAsia, "Asia should be present in the continent population report");
    }

    @Test
    void testCountryPopulationReportIntegration() {
        if (con == null) {
            System.out.println("Skipping test: No database connection available.");
            return;
        }

        PopulationRepository repo = new PopulationRepository(con);
        List<Population> report = repo.getCountryPopulationReport();

        assertNotNull(report);
        assertFalse(report.isEmpty());

        boolean foundMyanmar = report.stream().anyMatch(p -> "Myanmar".equals(p.getName()));
        assertTrue(foundMyanmar, "Myanmar should be present in the country population report");
    }

    @Test
    void testWorldTotalPopulationIntegration() {
        if (con == null) {
            System.out.println("Skipping test: No database connection available.");
            return;
        }

        PopulationRepository repo = new PopulationRepository(con);
        List<Population> report = repo.getWorldTotalPopulation();

        assertNotNull(report);
        assertEquals(1, report.size());
        assertEquals("World", report.get(0).getName());
        assertTrue(report.get(0).getTotalPopulation() > 0, "World total population should be greater than 0");
    }
}