package com.napier.devops;

import com.napier.devops.config.DatabaseHandler;
import com.napier.devops.model.City;
import com.napier.devops.reportformat.CityReport;
import com.napier.devops.repository.CityRepository;
import com.napier.devops.repository.PopulationRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println(
                "Initializing World Population Reporting System..."
        );

        DatabaseHandler dbHandler = new DatabaseHandler();

        String dbLocation = args.length > 0
                ? args[0]
                : "localhost:33060";

        dbHandler.connect(dbLocation);

        Connection con = dbHandler.getConnection();

        if (con == null) {
            System.err.println(
                    "Failed to establish database connection after max retries."
            );
            return;
        }

        try {
            CityRepository cityRepo = new CityRepository(con);
            PopulationRepository populationRepo =
                    new PopulationRepository(con);

            // Requirement 7: All cities in the world
            System.out.println(
                    "\n--- Req 7: All Cities in the World ---"
            );
            List<City> worldCities = cityRepo.getAllCitiesWorld();
            CityReport.print(worldCities);

            // Requirement 8: All cities in a continent
            System.out.println(
                    "\n--- Req 8: All Cities in Asia ---"
            );
            List<City> continentCities =
                    cityRepo.getAllCitiesContinent("Asia");
            CityReport.print(continentCities);

            // Requirement 9: All cities in a region
            System.out.println(
                    "\n--- Req 9: All Cities in Southeast Asia ---"
            );
            List<City> regionCities =
                    cityRepo.getAllCitiesRegion("Southeast Asia");
            CityReport.print(regionCities);

            // Requirement 10: All cities in a country
            System.out.println(
                    "\n--- Req 10: All Cities in Myanmar ---"
            );
            List<City> countryCities =
                    cityRepo.getAllCitiesCountry("Myanmar");
            CityReport.print(countryCities);

            // Requirement 11: All cities in a district
            System.out.println(
                    "\n--- Req 11: All Cities in England District ---"
            );
            List<City> districtCities =
                    cityRepo.getAllCitiesDistrict("England");
            CityReport.print(districtCities);

            // Population reports
            System.out.println("\n--- Population Reports ---");

            long worldPopulation =
                    populationRepo.getWorldPopulation();

            long continentPopulation =
                    populationRepo.getContinentPopulation("Asia");

            long regionPopulation =
                    populationRepo.getRegionPopulation("Southeast Asia");

            // This method accepts a country code, not a country name.
            long countryPopulation =
                    populationRepo.getCountryPopulation("MMR");

            // District population requires both country code and district.
            long districtPopulation =
                    populationRepo.getDistrictPopulation("GBR", "England");

            System.out.printf(
                    "%-40s %,d%n",
                    "World population:",
                    worldPopulation
            );

            System.out.printf(
                    "%-40s %,d%n",
                    "Asia population:",
                    continentPopulation
            );

            System.out.printf(
                    "%-40s %,d%n",
                    "Southeast Asia population:",
                    regionPopulation
            );

            System.out.printf(
                    "%-40s %,d%n",
                    "Myanmar population:",
                    countryPopulation
            );

            System.out.printf(
                    "%-40s %,d%n",
                    "England recorded-city population:",
                    districtPopulation
            );

        } catch (SQLException e) {
            System.err.println(
                    "Failed to retrieve population reports: "
                            + e.getMessage()
            );
        } finally {
            dbHandler.disconnect();
        }
    }
}