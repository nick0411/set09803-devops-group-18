package com.napier.devops.unitTests;

import com.napier.devops.App;
import com.napier.devops.config.DatabaseHandler;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AppTest {

    @Test
    public void testMainExecutionWithMockedDatabase() throws Exception {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);

        Statement mockStatement = mock(Statement.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        try (MockedConstruction<DatabaseHandler> mocked = Mockito.mockConstruction(DatabaseHandler.class,
                (mock, context) -> {
                    when(mock.getConnection()).thenReturn(mockConnection);
                })) {

            String[] args = {"localhost:3306", "Asia", "Southeast Asia", "Myanmar", "England", "5"};

            assertDoesNotThrow(() -> {
                App.main(args);
            });
        }
    }

    @Test
    public void testMainWithNoArguments() {
        try (MockedConstruction<DatabaseHandler> mocked = Mockito.mockConstruction(DatabaseHandler.class,
                (mock, context) -> {
                    when(mock.getConnection()).thenReturn(null); // Simulate failed/null connection fallback
                })) {

            String[] emptyArgs = {};

            assertDoesNotThrow(() -> {
                App.main(emptyArgs);
            });
        }
    }
}