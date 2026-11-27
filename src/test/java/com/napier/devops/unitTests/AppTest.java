package com.napier.devops.unitTests;

import com.napier.devops.App;
import com.napier.devops.config.DatabaseHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class AppTest {

    @Test
    public void testMainExecutionWithMockedDatabase() {
        DatabaseHandler mockDbHandler = mock(DatabaseHandler.class);
        Connection mockConnection = mock(Connection.class);

        when(mockDbHandler.getConnection()).thenReturn(mockConnection);

        String[] args = {"localhost:3306", "Asia", "Southeast Asia", "Myanmar", "England", "5"};

        assertDoesNotThrow(() -> {
            App.main(args);
        });
    }

    @Test
    public void testMainWithNoArguments() {
        String[] emptyArgs = {};

        assertDoesNotThrow(() -> {
            App.main(emptyArgs);
        });
    }
}