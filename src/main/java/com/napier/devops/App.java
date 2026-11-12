package com.napier.devops;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.City;
import com.napier.devops.model.Language;
import com.napier.devops.model.Population;
import com.napier.devops.reportformat.CityReport;
import com.napier.devops.reportformat.LanguageReport;
import com.napier.devops.reportformat.PopulationReport;
import com.napier.devops.repository.CityRepository;
import com.napier.devops.repository.LanguageRepository;
import com.napier.devops.repository.PopulationRepository;

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
            dbLocation = "localhost:3306"; // Default for local IntelliJ testing
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

            // Population Reports (Req 23 - 31)
            PopulationRepository populationRepo = new PopulationRepository(con);

            // Req 23: Population living / not living in cities per Continent
            System.out.println("\n--- Req 23: Population Living / Not Living in Cities per Continent ---");
            List<Population> continentPop = populationRepo.getContinentPopulationReport();
            PopulationReport.print(continentPop);

            // Req 24: Population living / not living in cities per Region
            System.out.println("\n--- Req 24: Population Living / Not Living in Cities per Region ---");
            List<Population> regionPop = populationRepo.getRegionPopulationReport();
            PopulationReport.print(regionPop);

            // Req 25: Population living / not living in cities per Country
            System.out.println("\n--- Req 25: Population Living / Not Living in Cities per Country ---");
            List<Population> countryPop = populationRepo.getCountryPopulationReport();
            PopulationReport.print(countryPop);

            // Req 26: Total population of the World
            System.out.println("\n--- Req 26: Total Population of the World ---");
            List<Population> worldTotal = populationRepo.getWorldTotalPopulation();
            PopulationReport.print(worldTotal);

            // Req 27: Total population of each Continent
            System.out.println("\n--- Req 27: Total Population of each Continent ---");
            List<Population> continentTotal = populationRepo.getContinentTotalPopulation();
            PopulationReport.print(continentTotal);

            // Req 28: Total population of each Region
            System.out.println("\n--- Req 28: Total Population of each Region ---");
            List<Population> regionTotal = populationRepo.getRegionTotalPopulation();
            PopulationReport.print(regionTotal);

            // Req 29: Total population of each Country
            System.out.println("\n--- Req 29: Total Population of each Country ---");
            List<Population> countryTotal = populationRepo.getCountryTotalPopulation();
            PopulationReport.print(countryTotal);

            // Req 30: Total population of each District
            System.out.println("\n--- Req 30: Total Population of each District ---");
            List<Population> districtTotal = populationRepo.getDistrictTotalPopulation();
            PopulationReport.print(districtTotal);

            // Req 31: Total population of each City
            System.out.println("\n--- Req 31: Total Population of each City ---");
            List<Population> cityTotal = populationRepo.getCityTotalPopulation();
            PopulationReport.print(cityTotal);

            // Req 32: Global Language Distribution Report
            System.out.println("\n--- Req 32: Global Language Distribution ---");
            LanguageRepository languageRepo = new LanguageRepository(con);
            List<Language> languageReport = languageRepo.getGlobalLanguageReport();
            LanguageReport.print(languageReport);

            // Close connection
            dbHandler.disconnect();
        } else {
            System.err.println("Failed to establish database connection after max retries.");
        }
    }
}