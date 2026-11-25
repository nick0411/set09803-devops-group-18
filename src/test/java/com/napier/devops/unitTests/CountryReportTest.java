package com.napier.devops.unitTests;

import com.napier.devops.model.Country;
import com.napier.devops.reportformat.CountryReport;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CountryReportTest {

    @Test
    void printHandlesEmptyList() {
        List<Country> emptyList = Collections.emptyList();
        assertDoesNotThrow(() -> CountryReport.print(emptyList));
    }

    @Test
    void printHandlesNullList() {
        assertDoesNotThrow(() -> CountryReport.print(null));
    }
}