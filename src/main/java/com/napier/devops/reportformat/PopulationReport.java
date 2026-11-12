package com.napier.devops.reportformat;

import com.napier.devops.model.Population;
import java.util.List;

public class PopulationReport {

    public static void print(List<Population> populations) {
        if (populations == null || populations.isEmpty()) {
            System.out.println("No population data available or database connection failed.");
            return;
        }

        String border = "+---------------------------+-----------------+----------------------------+----------------------------+";

        System.out.println(border);
        System.out.printf("| %-25s | %-15s | %-26s | %-26s |%n",
                "Name", "Total Pop", "City Pop & %", "Non-City Pop & %");
        System.out.println(border);

        for (Population pop : populations) {
            System.out.println(pop);
            System.out.println(border);
        }
    }
}