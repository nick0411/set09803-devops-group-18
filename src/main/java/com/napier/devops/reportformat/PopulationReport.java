package com.napier.devops.reportformat;

import com.napier.devops.model.Population;
import java.util.List;

public class PopulationReport {

    public static void print(List<Population> populations) {
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data available or database connection failed.");
            return;
        }

        // Exact border matching column widths
        String border = "+---------------------------+-----------------+----------------------------+----------------------------+";

        System.out.println(border);
        System.out.printf("| %-25s | %-15s | %-26s | %-25s |%n",
                "Name", "Total Population", "City Population", "Non-City Population");
        System.out.println(border);

        for (Population pop : populations) {
            // Format city and non-city population strings with numbers and percentages
            String cityCol = String.format("%,d (%.2f%%)", pop.getCityPopulation(), pop.getCityPercentage());
            String nonCityCol = String.format("%,d (%.2f%%)", pop.getNonCityPopulation(), pop.getNonCityPercentage());

            System.out.printf("| %-25s | %,15d | %-26s | %-26s |%n",
                    pop.getName(),
                    pop.getTotalPopulation(),
                    cityCol,
                    nonCityCol);
            System.out.println(border);
        }

        // Integrated total footer row matching the table width
        System.out.printf("| Total Number of Records: %-76d |%n", populations.size());
        System.out.println(border);
    }
}