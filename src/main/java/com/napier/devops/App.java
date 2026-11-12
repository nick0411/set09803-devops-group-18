package com.napier.devops;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.City;
import com.napier.devops.model.Country;
import com.napier.devops.model.Language;
import com.napier.devops.model.Population;
import com.napier.devops.reportformat.CapitalCityReport;
import com.napier.devops.reportformat.CityReport;
import com.napier.devops.reportformat.CountryReport;
import com.napier.devops.reportformat.LanguageReport;
import com.napier.devops.repository.CityRepository;
import com.napier.devops.repository.CountryRepository;
import com.napier.devops.repository.LanguageRepository;

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
            CountryRepository countryRepo = new CountryRepository(con);

            // Country Reports (Your feature implementation)
            System.out.println("\n--- Country Report: All Countries in the World ---");
            List<Country> worldCountries = countryRepo.getAllCountriesWorld();
            CountryReport.print(worldCountries);

            // Req 7: All cities in the world (limiting to top 10 for clean sample output, or remove .limit() for all)
            System.out.println("\n--- Req 7: All Cities in the World ---");
            List<City> worldCities = cityRepo.getAllCitiesWorld();
            CityReport.print(worldCities);

            // Req 8: All cities in a continent (e.g., Asia)
            System.out.println("\n--- Req 8: All Cities in a Specific Continent ---");
            List<City> continentCities = cityRepo.getAllCitiesContinent("Asia");
            CityReport.print(continentCities);

            // Req 9: All cities in a region (e.g., Southeast Asia)
            System.out.println("\n--- Req 9: All Cities in a Specific Region ---");
            List<City> regionCities = cityRepo.getAllCitiesRegion("Southeast Asia");
            CityReport.print(regionCities);

            // Req 10: All cities in a country (e.g., Myanmar)
            System.out.println("\n--- Req 10: All Cities in a Specific Country ---");
            List<City> countryCities = cityRepo.getAllCitiesCountry("Myanmar");
            CityReport.print(countryCities);

            // Req 11: All cities in a district (e.g., England)
            System.out.println("\n--- Req 11: All Cities in a Specific District ---");
            List<City> districtCities = cityRepo.getAllCitiesDistrict("England");
            CityReport.print(districtCities);

            // Capital City Reports (Req 17 - 22)
            CapitalCityRepository capitalRepo = new CapitalCityRepository(con);

            // Req 17: All capital cities in the world
            System.out.println("\n--- Req 17: All Capital Cities in the World ---");
            List<CapitalCity> worldCapitals = capitalRepo.getAllCapitalsWorld();
            CapitalCityReport.print(worldCapitals);

            // Req 18: All capital cities in a continent (e.g., Asia)
            System.out.println("\n--- Req 18: All Capital Cities in a Continent ---");
            List<CapitalCity> continentCapitals = capitalRepo.getAllCapitalsContinent("Asia");
            CapitalCityReport.print(continentCapitals);

            // Req 19: All capital cities in a region (e.g., Southeast Asia)
            System.out.println("\n--- Req 19: All Capital Cities in a Region ---");
            List<CapitalCity> regionCapitals = capitalRepo.getAllCapitalsRegion("Southeast Asia");
            CapitalCityReport.print(regionCapitals);

            // Req 20: Top N populated capital cities in the world (e.g., Top 10)
            System.out.println("\n--- Req 20: Top n Capital Cities in the World ---");
            List<CapitalCity> topWorldCapitals = capitalRepo.getTopNCapitalsWorld(10);
            CapitalCityReport.print(topWorldCapitals);

            // Req 21: Top N populated capital cities in a continent (e.g., Top 5 in Asia)
            System.out.println("\n--- Req 21: Top n Capital Cities in a Continent ---");
            List<CapitalCity> topContinentCapitals = capitalRepo.getTopNCapitalsContinent("Asia", 5);
            CapitalCityReport.print(topContinentCapitals);

            // Req 22: Top N populated capital cities in a region (e.g., Top 5 in Southeast Asia)
            System.out.println("\n--- Req 22: Top n Capital Cities in a region ---");
            List<CapitalCity> topRegionCapitals = capitalRepo.getTopNCapitalsRegion("Southeast Asia", 5);
            CapitalCityReport.print(topRegionCapitals);

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