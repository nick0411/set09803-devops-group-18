package com.napier.devops.reportformat;

import com.napier.devops.model.Language;
import java.util.List;

public class LanguageReport {

    public static void print(List<Language> languages) {
        if (languages == null || languages.isEmpty()) {
            System.out.println("No language data available or database connection failed.");
            return;
        }

        String border = "+------------------------+----------------------+--------------+";

        System.out.println(border);
        System.out.printf("| %-22s | %-20s | %-12s |%n", "Language", "Total Speakers", "World %");
        System.out.println(border);

        for (Language lang : languages) {
            System.out.println(lang);
            System.out.println(border);
        }
    }
}