package com.napier.devops.unitTests;

import com.napier.devops.model.Language;
import com.napier.devops.reportformat.LanguageReport;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageReportTest {

    @Test
    public void testPrintLanguageReport() {
        List<Language> languages = List.of(
                new Language("English", 1500000000L, 25.50),
                new Language("Chinese", 1200000000L, 20.25)
        );

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true,
                    StandardCharsets.UTF_8));

            LanguageReport.print(languages);

        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Language"));
        assertTrue(result.contains("Total Speakers"));
        assertTrue(result.contains("World %"));
        assertTrue(result.contains("English"));
        assertTrue(result.contains("Chinese"));
        assertTrue(result.contains("1,500,000,000"));
        assertTrue(result.contains("1,200,000,000"));
        assertTrue(result.contains("25.50"));
        assertTrue(result.contains("20.25"));
    }

    @Test
    public void testPrintEmptyLanguageReport() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true,
                    StandardCharsets.UTF_8));

            LanguageReport.print(List.of());

        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);
        assertTrue(result.contains("No language data available"));
    }
}
