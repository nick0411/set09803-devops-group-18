package com.napier.devops.unitTests;

import com.napier.devops.model.Population;
import com.napier.devops.reportformat.PopulationReport;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationReportTest {

    @Test
    public void testPrintPopulationReport() {
        List<Population> populations = List.of(
                new Population("Asia", 3705025700L, 697604103L, 18.83, 3007421597L, 81.17),
                new Population("Myanmar", 45611000L, 6203000L, 13.60, 39408000L, 86.40)
        );

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true,
                    StandardCharsets.UTF_8));

            PopulationReport.print(populations);

        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Name"));
        assertTrue(result.contains("Total Population"));
        assertTrue(result.contains("City Population"));
        assertTrue(result.contains("Non-City Population"));
        assertTrue(result.contains("Asia"));
        assertTrue(result.contains("Myanmar"));
        assertTrue(result.contains("3,705,025,700"));
        assertTrue(result.contains("697,604,103 (18.83%)"));
        assertTrue(result.contains("3,007,421,597 (81.17%)"));
    }

    @Test
    public void testPrintEmptyPopulationReport() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true,
                    StandardCharsets.UTF_8));

            PopulationReport.print(List.of());

        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);
        assertTrue(result.contains("No population data available or database connection failed."));
    }
}
