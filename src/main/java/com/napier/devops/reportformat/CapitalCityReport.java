package com.napier.devops.reportformat;

import com.napier.devops.model.CapitalCity;
import java.util.List;

public class CapitalCityReport {

    public static void print(List<CapitalCity> capitals) {
        if (capitals == null || capitals.isEmpty()) {
            System.out.println("No capital city data available or database connection failed.");
            return;
        }

        // Exact width matching CapitalCity.java toString() formatting
        String border = "+---------------------------+---------------------------+-----------------+";

        System.out.println(border);
        System.out.printf("| %-25s | %-25s | %-15s |%n", "Name", "Country", "Population");
        System.out.println(border);

        for (CapitalCity capital : capitals) {
            System.out.println(capital);
            System.out.println(border);
        }

        // Total count footer row
        System.out.printf("| Total Number of Capital Cities: %-39d |%n", capitals.size());
        System.out.println(border);
    }
}