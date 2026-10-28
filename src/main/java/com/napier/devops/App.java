package com.napier.devops;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.City;
import com.napier.devops.model.Language;
import com.napier.devops.reportformat.CityReport;
import com.napier.devops.repository.CityRepository;
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
            CityRepository cityRepo = new CityRepository(con);

            // Req 7: All cities in the world (limiting to top 10 for clean sample output, or remove .limit() for all)
            System.out.println("\n--- Req 7: All Cities in the World (Top 10 Sample) ---");
            List<City> worldCities = cityRepo.getAllCitiesWorld();
            CityReport.print(worldCities);

            // Req 8: All cities in a continent (e.g., Asia)
            System.out.println("\n--- Req 8: All Cities in Asia (Top 10 Sample) ---");
            List<City> continentCities = cityRepo.getAllCitiesContinent("Asia");
            CityReport.print(continentCities);

            // Req 9: All cities in a region (e.g., Southeast Asia)
            System.out.println("\n--- Req 9: All Cities in Southeast Asia (Top 10 Sample) ---");
            List<City> regionCities = cityRepo.getAllCitiesRegion("Southeast Asia");
            CityReport.print(regionCities);

            // Req 10: All cities in a country (e.g., Myanmar)
            System.out.println("\n--- Req 10: All Cities in Myanmar ---");
            List<City> countryCities = cityRepo.getAllCitiesCountry("Myanmar");
            CityReport.print(countryCities);

            // Req 11: All cities in a district (e.g., England)
            System.out.println("\n--- Req 11: All Cities in England District ---");
            List<City> districtCities = cityRepo.getAllCitiesDistrict("England");
            CityReport.print(districtCities);

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