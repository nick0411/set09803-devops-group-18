package com.napier.devops.reportformat;

import com.napier.devops.model.Country;
import java.util.List;

public class CountryReport {

    /**
     * Prints a formatted table report for a list of countries.
     *
     * @param countries List of Country objects to display
     */
    public static void print(List<Country> countries) {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries found to display.");
            return;
        }

        String border = "+-------+---------------------------+-----------------+---------------------------+-----------------+----------------------+";

        System.out.println(border);
        System.out.printf("| %-5s | %-25s | %-15s | %-25s | %-15s | %-20s |\n",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        System.out.println(border);

        for (Country country : countries) {
            if (country != null) {
                System.out.println(country.toString());
            }
        }

        System.out.println(border);
        System.out.println("Total Countries Displayed: " + countries.size() + "\n");
    }
}