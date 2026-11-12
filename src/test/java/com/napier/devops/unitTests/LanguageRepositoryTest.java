package com.napier.devops.unitTests;

import com.napier.devops.model.Language;
import com.napier.devops.repository.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LanguageRepositoryTest {

    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;
    private LanguageRepository languageRepository;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        // Hook up the mock chain: Connection -> Statement -> ResultSet
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        languageRepository = new LanguageRepository(mockConnection);
    }

    @Test
    void testGetGlobalLanguageReport_Success() throws SQLException {
        // Arrange: Simulate rows returned from the database for the requested languages
        when(mockResultSet.next()).thenReturn(true, true, false); // 2 rows then stop

        // First row: English
        when(mockResultSet.getString("Language")).thenReturn("English");
        when(mockResultSet.getLong("TotalSpeakers")).thenReturn(1500000000L);
        when(mockResultSet.getDouble("WorldPercentage")).thenReturn(25.50);

        // Act
        List<Language> languages = languageRepository.getGlobalLanguageReport();

        // Assert
        assertNotNull(languages);
        assertEquals(2, languages.size());

        Language firstLang = languages.get(0);
        assertEquals("English", firstLang.getName());
        assertEquals(1500000000L, firstLang.getTotalSpeakers());
        assertEquals(25.50, firstLang.getWorldPercentage());

        // Verify database interactions occurred properly
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
    }

    @Test
    void testGetGlobalLanguageReport_EmptyResult() throws SQLException {
        // Arrange: Simulate empty result set
        when(mockResultSet.next()).thenReturn(false);

        // Act
        List<Language> languages = languageRepository.getGlobalLanguageReport();

        // Assert
        assertNotNull(languages);
        assertTrue(languages.isEmpty());
    }

    @Test
    void testGetGlobalLanguageReport_SQLExceptionHandling() throws SQLException {
        // Arrange: Simulate a database error when creating the statement
        when(mockConnection.createStatement()).thenThrow(new SQLException("Database offline"));

        // Act
        List<Language> languages = languageRepository.getGlobalLanguageReport();

        // Assert: Should safely catch exception and return an empty list rather than crashing
        assertNotNull(languages);
        assertTrue(languages.isEmpty());
    }
}