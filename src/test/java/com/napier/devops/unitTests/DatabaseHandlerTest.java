package com.napier.devops.unitTests;

import com.napier.devops.config.DatabaseHandler;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DatabaseHandlerTest {

    @Test
    void testGetConnectionInitiallyNull() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        assertNull(dbHandler.getConnection(), "Connection should be null before connecting.");
    }

    @Test
    void testDisconnectWhenConnectionIsNull() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        assertDoesNotThrow(dbHandler::disconnect);
    }

    @Test
    void testConnectFailureAndRetryHandling() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        assertDoesNotThrow(() -> {
            dbHandler.connect("invalid-host-port:9999");
        });

        assertNull(dbHandler.getConnection(), "Connection should remain null after failed retries.");
    }

    @Test
    void testConnectSuccessWithMockedDriverManager() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Connection mockConnection = mock(Connection.class);

        try (MockedStatic<DriverManager> mockedDriverManager = Mockito.mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), eq("root"), eq("rootpassword")))
                    .thenReturn(mockConnection);

            dbHandler.connect("localhost:3306");

            assertNotNull(dbHandler.getConnection(), "Connection should be successfully established.");
            assertEquals(mockConnection, dbHandler.getConnection());

            dbHandler.disconnect();
            verify(mockConnection, times(1)).close();
        }
    }
}