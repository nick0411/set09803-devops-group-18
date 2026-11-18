
package com.napier.devops.unitTests;

import com.napier.devops.model.Population;
import com.napier.devops.repository.PopulationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PopulationRepositoryTest {

    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    private PopulationRepository populationRepository;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        populationRepository = new PopulationRepository(mockConnection);
    }

    // Common method for successful population reports
    private void testSuccess(Supplier<List<Population>> method, String name,
                             long total, long city, double cityPercent,
                             long nonCity, double nonCityPercent)
            throws SQLException {

        // Arrange
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("Name")).thenReturn(name);
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(total);
        when(mockResultSet.getLong("CityPopulation")).thenReturn(city);
        when(mockResultSet.getDouble("CityPercentage")).thenReturn(cityPercent);
        when(mockResultSet.getLong("NonCityPopulation")).thenReturn(nonCity);
        when(mockResultSet.getDouble("NonCityPercentage")).thenReturn(nonCityPercent);

        // Act
        List<Population> populations = method.get();

        // Assert
        assertNotNull(populations);
        assertEquals(1, populations.size());

        Population pop = populations.get(0);
        assertEquals(name, pop.getName());
        assertEquals(total, pop.getTotalPopulation());
        assertEquals(city, pop.getCityPopulation());
        assertEquals(cityPercent, pop.getCityPercentage(), 0.01);
        assertEquals(nonCity, pop.getNonCityPopulation());
        assertEquals(nonCityPercent, pop.getNonCityPercentage(), 0.01);

        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
    }


    // Req 23: Continent population breakdown
    @Test
    void testReq23_ContinentPopulation() throws SQLException {
        testSuccess(populationRepository::getContinentPopulationReport,
                "Asia", 3705025700L, 697604103L, 18.83,
                3007421597L, 81.17);
    }

    // Req 24: Region population breakdown
    @Test
    void testReq24_RegionPopulation() throws SQLException {
        testSuccess(populationRepository::getRegionPopulationReport,
                "Eastern Asia", 1507328000L, 317476534L, 21.06,
                1189851466L, 78.94);
    }

    // Req 25: Country population breakdown
    @Test
    void testReq25_CountryPopulation() throws SQLException {
        testSuccess(populationRepository::getCountryPopulationReport,
                "Myanmar", 45611000L, 6203000L, 13.60,
                39408000L, 86.40);
    }

    // Req 26: World total population
    @Test
    void testReq26_WorldPopulation() throws SQLException {
        testSuccess(populationRepository::getWorldTotalPopulation,
                "World", 6078749450L, 1429559884L, 23.52,
                4649189566L, 76.48);
    }

    // Req 27: Continent total population
    @Test
    void testReq27_ContinentTotal() throws SQLException {
        testSuccess(populationRepository::getContinentTotalPopulation,
                "Europe", 730074600L, 241942813L, 33.14,
                488131787L, 66.86);
    }

    // Req 28: Region total population
    @Test
    void testReq28_RegionTotal() throws SQLException {
        testSuccess(populationRepository::getRegionTotalPopulation,
                "Western Europe", 183247600L, 45683298L, 24.93,
                137564302L, 75.07);
    }

    // Req 29: Country total population
    @Test
    void testReq29_CountryTotal() throws SQLException {
        testSuccess(populationRepository::getCountryTotalPopulation,
                "Thailand", 61399000L, 7953161L, 12.95,
                53445839L, 87.05);
    }

    // Req 30: District total population
    @Test
    void testReq30_DistrictTotal() throws SQLException {
        testSuccess(populationRepository::getDistrictTotalPopulation,
                "Mandalay", 1118600L, 1118600L, 100.0,
                0L, 0.0);
    }

    // Req 31: City total population
    @Test
    void testReq31_CityTotal() throws SQLException {
        testSuccess(populationRepository::getCityTotalPopulation,
                "Mandalay", 885300L, 885300L, 100.0,
                0L, 0.0);
    }

    // Empty result test
    @Test
    void testPopulationRepository_EmptyResult() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        List<Population> populations =
                populationRepository.getContinentPopulationReport();

        assertNotNull(populations);
        assertTrue(populations.isEmpty());
    }

    // SQL exception handling test
    @Test
    void testPopulationRepository_SQLExceptionHandling() throws SQLException {
        when(mockConnection.createStatement())
                .thenThrow(new SQLException("Database offline"));

        List<Population> populations =
                populationRepository.getContinentPopulationReport();

        assertNotNull(populations);
        assertTrue(populations.isEmpty());
    }
}
