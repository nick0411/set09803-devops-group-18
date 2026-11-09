package com.napier.devops.reportformat;

import com.napier.devops.model.City;
import java.util.List;

public class CityReport {

    /**
     * Print a formatted report of cities.
     */
    public static void printCityReport(List<City> cities) {

        System.out.printf("%-30s %-15s %-25s %15s%n",
                "Name", "Country", "District", "Population");

        System.out.println("-".repeat(90));

        for (City city : cities) {
            System.out.printf("%-30s %-15s %-25s %,15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }
}

