package com.napier.devops.integrationTests;

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

    // --- Req 23: Continent population breakdown ---
    @Test
    void testContinentPopulationReportIntegration() {
        if (con == null) return;
        List<Population> report = new PopulationRepository(con).getContinentPopulationReport();
        assertNotNull(report);
        assertFalse(report.isEmpty());
        boolean foundAsia = report.stream().anyMatch(p -> "Asia".equals(p.getName()));
        assertTrue(foundAsia);
    }

    // --- Req 24: Region population breakdown ---
    @Test
    void testRegionPopulationReportIntegration() {
        if (con == null) return;
        List<Population> report = new PopulationRepository(con).getRegionPopulationReport();
        assertNotNull(report);
        assertFalse(report.isEmpty());
    }

    // --- Req 25: Country population breakdown ---
    @Test
    void testCountryPopulationReportIntegration() {
        if (con == null) return;
        List<Population> report = new PopulationRepository(con).getCountryPopulationReport();
        assertNotNull(report);
        assertFalse(report.isEmpty());
        boolean foundMyanmar = report.stream().anyMatch(p -> "Myanmar".equals(p.getName()));
        assertTrue(foundMyanmar);
    }

    // --- Req 26: World total population ---
    @Test
    void testWorldTotalPopulationIntegration() {
        if (con == null) return;
        List<Population> report = new PopulationRepository(con).getWorldTotalPopulation();
        assertNotNull(report);
        assertEquals(1, report.size());
        assertEquals("World", report.get(0).getName());
        assertTrue(report.get(0).getTotalPopulation() > 0);
    }

    // --- Req 27: Continent total population ---
    @Test
    void testContinentTotalPopulationIntegration() {
        if (con == null) return;
        List<Population> report = new PopulationRepository(con).getContinentTotalPopulation();
        assertNotNull(report);
        assertFalse(report.isEmpty());
    }

    // --- Req 28: Region total population ---
    @Test
    void testRegionTotalPopulationIntegration() {
        if (con == null) return;
        List<Population> report = new PopulationRepository(con).getRegionTotalPopulation();
        assertNotNull(report);
        assertFalse(report.isEmpty());
    }

    // --- Req 29: Country total population ---
    @Test
    void testCountryTotalPopulationIntegration() {
        if (con == null) return;
        List<Population> report = new PopulationRepository(con).getCountryTotalPopulation();
        assertNotNull(report);
        assertFalse(report.isEmpty());
    }

    // --- Req 30: District total population ---
    @Test
    void testDistrictTotalPopulationIntegration() {
        if (con == null) return;
        List<Population> report = new PopulationRepository(con).getDistrictTotalPopulation();
        assertNotNull(report);
        assertFalse(report.isEmpty());
    }

    // --- Req 31: City total population ---
    @Test
    void testCityTotalPopulationIntegration() {
        if (con == null) return;
        List<Population> report = new PopulationRepository(con).getCityTotalPopulation();
        assertNotNull(report);
        assertFalse(report.isEmpty());
    }
}