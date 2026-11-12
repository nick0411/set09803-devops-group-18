package com.napier.devops;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.City;
import com.napier.devops.model.Language;
import com.napier.devops.model.Population;
import com.napier.devops.reportformat.CapitalCityReport;
import com.napier.devops.reportformat.CityReport;
import com.napier.devops.reportformat.LanguageReport;
import com.napier.devops.reportformat.PopulationReport;
import com.napier.devops.repository.CapitalCityRepository;
import com.napier.devops.repository.CityRepository;
import com.napier.devops.repository.LanguageRepository;
import com.napier.devops.repository.PopulationRepository;

import java.sql.Connection;
import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println("Initializing World Population Reporting System...");

        DatabaseHandler dbHandler = new DatabaseHandler();

        // 1. Handle Database Location from args[0] (Docker ENTRYPOINT passes "db:3306")
        String dbLocation;
        if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
            dbLocation = args[0];
        } else {
            dbLocation = "localhost:3306"; // Default for local IntelliJ testing
        }

        System.out.println("Connecting to database at: " + dbLocation);
        dbHandler.connect(dbLocation);

        Connection con = dbHandler.getConnection();

        if (con != null) {
            // 2. Parse dynamic report parameters from docker-compose command array with safe fallbacks
            // args[0] = db location
            String targetContinent = (args.length > 1 && args[1] != null && !args[1].isEmpty()) ? args[1] : "Asia";
            String targetRegion = (args.length > 2 && args[2] != null && !args[2].isEmpty()) ? args[2] : "Southeast Asia";
            String targetCountry = (args.length > 3 && args[3] != null && !args[3].isEmpty()) ? args[3] : "Myanmar";
            String targetDistrict = (args.length > 4 && args[4] != null && !args[4].isEmpty()) ? args[4] : "England";
            int topN = 10;
            if (args.length > 5 && args[5] != null && !args[5].isEmpty()) {
                try {
                    topN = Integer.parseInt(args[5]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid Top N argument provided, defaulting to 10.");
                }
            }

            System.out.println("Runtime Parameters -> Continent: " + targetContinent +
                    ", Region: " + targetRegion +
                    ", Country: " + targetCountry +
                    ", District: " + targetDistrict +
                    ", Top N: " + topN);

            // --- City Reports (Req 7 - 11) ---
            CityRepository cityRepo = new CityRepository(con);

            System.out.println("\n--- Req 7: All Cities in the World ---");
            List<City> worldCities = cityRepo.getAllCitiesWorld();
            CityReport.print(worldCities);

            System.out.println("\n--- Req 8: All Cities in Continent (" + targetContinent + ") ---");
            List<City> continentCities = cityRepo.getAllCitiesContinent(targetContinent);
            CityReport.print(continentCities);

            System.out.println("\n--- Req 9: All Cities in Region (" + targetRegion + ") ---");
            List<City> regionCities = cityRepo.getAllCitiesRegion(targetRegion);
            CityReport.print(regionCities);

            System.out.println("\n--- Req 10: All Cities in Country (" + targetCountry + ") ---");
            List<City> countryCities = cityRepo.getAllCitiesCountry(targetCountry);
            CityReport.print(countryCities);

            System.out.println("\n--- Req 11: All Cities in District (" + targetDistrict + ") ---");
            List<City> districtCities = cityRepo.getAllCitiesDistrict(targetDistrict);
            CityReport.print(districtCities);

            // --- Capital City Reports (Req 17 - 22) ---
            CapitalCityRepository capitalRepo = new CapitalCityRepository(con);

            System.out.println("\n--- Req 17: All Capital Cities in the World ---");
            List<CapitalCity> worldCapitals = capitalRepo.getAllCapitalsWorld();
            CapitalCityReport.print(worldCapitals);

            System.out.println("\n--- Req 18: All Capital Cities in Continent (" + targetContinent + ") ---");
            List<CapitalCity> continentCapitals = capitalRepo.getAllCapitalsContinent(targetContinent);
            CapitalCityReport.print(continentCapitals);

            System.out.println("\n--- Req 19: All Capital Cities in Region (" + targetRegion + ") ---");
            List<CapitalCity> regionCapitals = capitalRepo.getAllCapitalsRegion(targetRegion);
            CapitalCityReport.print(regionCapitals);

            System.out.println("\n--- Req 20: Top " + topN + " Capital Cities in the World ---");
            List<CapitalCity> topWorldCapitals = capitalRepo.getTopNCapitalsWorld(topN);
            CapitalCityReport.print(topWorldCapitals);

            System.out.println("\n--- Req 21: Top " + topN + " Capital Cities in Continent (" + targetContinent + ") ---");
            List<CapitalCity> topContinentCapitals = capitalRepo.getTopNCapitalsContinent(targetContinent, topN);
            CapitalCityReport.print(topContinentCapitals);

            System.out.println("\n--- Req 22: Top " + topN + " Capital Cities in Region (" + targetRegion + ") ---");
            List<CapitalCity> topRegionCapitals = capitalRepo.getTopNCapitalsRegion(targetRegion, topN);
            CapitalCityReport.print(topRegionCapitals);

            // --- Population Reports (Req 23 - 31) ---
            PopulationRepository populationRepo = new PopulationRepository(con);

            System.out.println("\n--- Req 23: Population Living / Not Living in Cities per Continent ---");
            List<Population> continentPop = populationRepo.getContinentPopulationReport();
            PopulationReport.print(continentPop);

            System.out.println("\n--- Req 24: Population Living / Not Living in Cities per Region ---");
            List<Population> regionPop = populationRepo.getRegionPopulationReport();
            PopulationReport.print(regionPop);

            System.out.println("\n--- Req 25: Population Living / Not Living in Cities per Country ---");
            List<Population> countryPop = populationRepo.getCountryPopulationReport();
            PopulationReport.print(countryPop);

            System.out.println("\n--- Req 26: Total Population of the World ---");
            List<Population> worldTotal = populationRepo.getWorldTotalPopulation();
            PopulationReport.print(worldTotal);

            System.out.println("\n--- Req 27: Total Population of each Continent ---");
            List<Population> continentTotal = populationRepo.getContinentTotalPopulation();
            PopulationReport.print(continentTotal);

            System.out.println("\n--- Req 28: Total Population of each Region ---");
            List<Population> regionTotal = populationRepo.getRegionTotalPopulation();
            PopulationReport.print(regionTotal);

            System.out.println("\n--- Req 29: Total Population of each Country ---");
            List<Population> countryTotal = populationRepo.getCountryTotalPopulation();
            PopulationReport.print(countryTotal);

            System.out.println("\n--- Req 30: Total Population of each District ---");
            List<Population> districtTotal = populationRepo.getDistrictTotalPopulation();
            PopulationReport.print(districtTotal);

            System.out.println("\n--- Req 31: Total Population of each City ---");
            List<Population> cityTotal = populationRepo.getCityTotalPopulation();
            PopulationReport.print(cityTotal);

            // --- Language Reports (Req 32) ---
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