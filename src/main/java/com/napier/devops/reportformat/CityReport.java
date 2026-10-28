package com.napier.devops.reportformat;

import com.napier.devops.model.City;
import java.util.List;

public class CityReport {

    public static void print(List<City> cities) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No city data available or database connection failed.");
            return;
        }

        // Exact width matching City.java toString() formatting
        String border = "+---------------------------+---------------------------+----------------------+-----------------+";

        System.out.println(border);
        System.out.printf("| %-25s | %-25s | %-20s | %-15s |%n", "Name", "Country", "District", "Population");
        System.out.println(border);

        for (City city : cities) {
            System.out.println(city);
            System.out.println(border);
        }

        // Total count footer row
        System.out.printf("| Total Number of Cities: %-70d |%n", cities.size());
        System.out.println(border);
    }
}