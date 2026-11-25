package com.napier.devops.unitTests;

import com.napier.devops.model.Country;
import com.napier.devops.reportformat.CountryReport;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountryReportTest {

    @Test
    public void testPrintCountryReport() {
        List<Country> countries = List.of(
                new Country("MMR", "Myanmar", "Asia", "Southeast Asia", 45611000L, "Yangon"),
                new Country("THA", "Thailand", "Asia", "Southeast Asia", 61399000L, "Bangkok")
        );

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            CountryReport.print(countries);
        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Code"));
        assertTrue(result.contains("Name"));
        assertTrue(result.contains("Continent"));
        assertTrue(result.contains("Region"));
        assertTrue(result.contains("Population"));
        assertTrue(result.contains("Capital"));
        assertTrue(result.contains("Myanmar"));
        assertTrue(result.contains("Thailand"));
        assertTrue(result.contains("45,611,000"));
        assertTrue(result.contains("61,399,000"));
    }

    @Test
    public void testPrintEmptyCountryReport() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            CountryReport.print(List.of());
        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);
        assertTrue(result.contains("No countries found to display."));
    }
}