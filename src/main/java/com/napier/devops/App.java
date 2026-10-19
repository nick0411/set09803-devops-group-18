package com.napier.devops;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.Language;
import com.napier.devops.repository.LanguageRepository;
import com.napier.devops.reportformat.LanguageReport; // Import the new report package

import java.sql.Connection;
import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println("Initializing World Population Reporting System...");

        DatabaseHandler dbHandler = new DatabaseHandler();

        String dbLocation;

        if (args.length > 0) {
            dbLocation = args[0]; // Used inside Docker
        } else {
            dbLocation = "localhost:33060"; // Default for local IntelliJ testing
        }
        dbHandler.connect(dbLocation);

        Connection con = dbHandler.getConnection();

        if (con != null) {
            // Execute Requirement 32: Global Language Distribution Report
            LanguageRepository languageRepo = new LanguageRepository(con);
            List<Language> languageReport = languageRepo.getGlobalLanguageReport();

            // Print output using the report package
            LanguageReport.print(languageReport);

            // Close connection
            dbHandler.disconnect();
        } else {
            System.err.println("Failed to establish database connection after max retries.");
        }
    }
}