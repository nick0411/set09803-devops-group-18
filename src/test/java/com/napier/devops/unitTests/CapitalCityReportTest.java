package com.napier.devops.unitTests;

import com.napier.devops.model.CapitalCity;
import com.napier.devops.reportformat.CapitalCityReport;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalCityReportTest {

    @Test
    public void testPrintCapitalCityReport() {
        List<CapitalCity> capitals = List.of(
                new CapitalCity("Managua", "Nicaragua", 1064326L),
                new CapitalCity("Belmopan", "Belize", 22956L)
        );

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            CapitalCityReport.print(capitals);
        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Name"));
        assertTrue(result.contains("Country"));
        assertTrue(result.contains("Population"));
        assertTrue(result.contains("Managua"));
        assertTrue(result.contains("Nicaragua"));
        assertTrue(result.contains("1,064,326"));
        assertTrue(result.contains("Belmopan"));
        assertTrue(result.contains("Belize"));
        assertTrue(result.contains("22,956"));
        assertTrue(result.contains("Total Number of Capital Cities: 2"));
    }

    @Test
    public void testPrintEmptyCapitalCityReport() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            CapitalCityReport.print(List.of());
        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);
        assertTrue(result.contains("No capital city data available or database connection failed."));
    }
}