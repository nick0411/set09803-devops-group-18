package com.napier.devops.integrationTests;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.Language;
import com.napier.devops.repository.LanguageRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LanguageRepositoryIntegrationTest {

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
                System.out.println("Language integration test database connection closed.");
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    @Test
    void testGetGlobalLanguageReportIntegration() {
        if (con == null) {
            System.out.println("Skipping test: No database connection available.");
            return;
        }

        LanguageRepository repo = new LanguageRepository(con);
        List<Language> languages = repo.getGlobalLanguageReport();

        assertNotNull(languages);
        assertFalse(languages.isEmpty(), "Global language report should return records from the database");

        boolean foundEnglish = languages.stream().anyMatch(l -> "English".equals(l.getName()));
        assertTrue(foundEnglish, "English should be present in the global language report");

        boolean foundChinese = languages.stream().anyMatch(l -> "Chinese".equals(l.getName()));
        assertTrue(foundChinese, "Chinese should be present in the global language report");

        for (Language lang : languages) {
            assertTrue(lang.getTotalSpeakers() > 0, "Total speakers for " + lang.getName() + " should be greater than 0");
            assertTrue(lang.getWorldPercentage() > 0.0, "World percentage for " + lang.getName() + " should be greater than 0");
        }
    }
}