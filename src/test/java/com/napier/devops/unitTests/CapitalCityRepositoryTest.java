package com.napier.devops.unitTests;

import com.napier.devops.model.CapitalCity;
import com.napier.devops.repository.CapitalCityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CapitalCityRepositoryTest {

    private Connection mockConnection;
    private Statement mockStatement;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private CapitalCityRepository capitalRepo;

    @BeforeEach
    void setUp() throws SQLException {
        // Make fake versions of the database parts (runs before every test)
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Req 17 has no blanks in its SQL, so it uses a normal Statement
        // Connection -> Statement -> ResultSet
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Req 18-22 has blanks in their SQL, so they use a PreparedStatement
        // Connection -> PreparedStatement -> ResultSet
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // The real repository, connected to the fake db
        capitalRepo = new CapitalCityRepository(mockConnection);
    }

    // Pretend the database sent back two capitals: Managua, then Belmopan
    private void mockTwoCapitals() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("CapitalName")).thenReturn("Managua", "Belmopan");
        when(mockResultSet.getString("CountryName")).thenReturn("Nicaragua", "Belize");
        when(mockResultSet.getLong("Population")).thenReturn(1064326L, 22956L);
    }

    // Check both capitals came back with the right order and details
    private void assertTwoCapitals(List<CapitalCity> capitals) {
        assertEquals(2, capitals.size());

        assertEquals("Managua", capitals.get(0).getName());
        assertEquals("Nicaragua", capitals.get(0).getCountry());
        assertEquals(1064326L, capitals.get(0).getPopulation());

        assertEquals("Belmopan", capitals.get(1).getName());
        assertEquals("Belize", capitals.get(1).getCountry());
        assertEquals(22956L, capitals.get(1).getPopulation());
    }

    // Req 17: all capitals in the world
    @Test
    void testGetAllCapitalsWorld() throws SQLException {
        // Arrange: fake database has two capitals, req17-22 test has the same arrange
        mockTwoCapitals();
        // Act: ask for every capital in the world
        List<CapitalCity> capitals = capitalRepo.getAllCapitalsWorld();
        // Assert: both capitals came back correctly
        assertTwoCapitals(capitals);
    }

    // Req 18: all capitals in a continent
    @Test
    void testGetAllCapitalsContinent() throws SQLException {
        mockTwoCapitals();
        // Act: ask for the capitals in North America
        List<CapitalCity> capitals = capitalRepo.getAllCapitalsContinent("North America");
        // Assert: both capitals came back, and "North America" was put in the ?
        assertTwoCapitals(capitals);
        verify(mockPreparedStatement).setString(1, "North America");
    }

    // Req 19: all capitals in a region
    @Test
    void testGetAllCapitalsRegion() throws SQLException {
        mockTwoCapitals();
        // Act: ask for the capitals in Central America
        List<CapitalCity> capitals = capitalRepo.getAllCapitalsRegion("Central America");
        // Assert: both capitals came back, and "Central America" was put in the ?
        assertTwoCapitals(capitals);
        verify(mockPreparedStatement).setString(1, "Central America");
    }

    // Req 20: top N capitals in the world
    @Test
    void testGetTopNCapitalsWorld() throws SQLException {
        mockTwoCapitals();
        // Act: ask for the top 2 capitals in the world
        List<CapitalCity> capitals = capitalRepo.getTopNCapitalsWorld(2);
        // Assert: both capitals came back, and 2 was put in the LIMIT ?
        assertTwoCapitals(capitals);
        verify(mockPreparedStatement).setInt(1, 2);
    }

    // Req 21: top N capitals in a continent
    @Test
    void testGetTopNCapitalsContinent() throws SQLException {
        mockTwoCapitals();
        // Act: ask for the top 2 capitals in North America
        List<CapitalCity> capitals = capitalRepo.getTopNCapitalsContinent("North America", 2);
        // Assert: both capitals came back, the continent went in the 1st ?, and 2 went in the 2nd ?
        assertTwoCapitals(capitals);
        verify(mockPreparedStatement).setString(1, "North America");
        verify(mockPreparedStatement).setInt(2, 2);
    }

    // Req 22: top N capitals in a region
    @Test
    void testGetTopNCapitalsRegion() throws SQLException {
        mockTwoCapitals();
        // Act: ask for the top 2 capitals in Central America
        List<CapitalCity> capitals = capitalRepo.getTopNCapitalsRegion("Central America", 2);
        // Assert: both capitals came back, the region went in the 1st ?, and 2 went in the 2nd ?
        assertTwoCapitals(capitals);
        verify(mockPreparedStatement).setString(1, "Central America");
        verify(mockPreparedStatement).setInt(2, 2);
    }

    // When the database finds nothing
    @Test
    void testNoRowsReturnsEmptyList() throws SQLException {
        // Arrange: fake database has no rows at all
        when(mockResultSet.next()).thenReturn(false);
        // Act: ask for every capital in the world
        List<CapitalCity> capitals = capitalRepo.getAllCapitalsWorld();
        // Assert: we get an empty list
        assertTrue(capitals.isEmpty());
    }

    // When the database is offline
    @Test
    void testDatabaseErrorReturnsEmptyList() throws SQLException {
        // Arrange: fake database throws an error as soon as it's used
        when(mockConnection.createStatement()).thenThrow(new SQLException("Database offline"));
        // Act: ask for every capital in the world
        List<CapitalCity> capitals = capitalRepo.getAllCapitalsWorld();
        // Assert: the error is caught and we get an empty list back
        assertTrue(capitals.isEmpty());
    }
}