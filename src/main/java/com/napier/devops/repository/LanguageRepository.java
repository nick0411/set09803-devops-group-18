package com.napier.devops.repository;

import com.napier.devops.model.Language;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LanguageRepository {
    private final Connection con;

    public LanguageRepository(Connection con) {
        this.con = con;
    }

    /**
     * Requirement 32: Produce a report on specified global language distribution.
     * @return List of Language objects sorted by total speakers descending.
     */
    public List<Language> getGlobalLanguageReport() {
        List<Language> languages = new ArrayList<>();

        String sql = "SELECT " +
                "    cl.Language, " +
                "    ROUND(SUM(c.Population * (cl.Percentage / 100.0))) AS TotalSpeakers, " +
                "    ROUND((SUM(c.Population * (cl.Percentage / 100.0)) / " +
                "          (SELECT SUM(Population) FROM country)) * 100, 2) AS WorldPercentage " +
                "FROM countrylanguage cl " +
                "JOIN country c ON cl.CountryCode = c.Code " +
                "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY cl.Language " +
                "ORDER BY TotalSpeakers DESC;";

        try (Statement stmt = con.createStatement();
             ResultSet rset = stmt.executeQuery(sql)) {

            while (rset.next()) {
                String name = rset.getString("Language");
                long totalSpeakers = rset.getLong("TotalSpeakers");
                double worldPercentage = rset.getDouble("WorldPercentage");

                languages.add(new Language(name, totalSpeakers, worldPercentage));
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed or missing data: " + e.getMessage());
            // Handles Extension 2/3: Return empty list or propagate custom exception
        }

        return languages;
    }
}