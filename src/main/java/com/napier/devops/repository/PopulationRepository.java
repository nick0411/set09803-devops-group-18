package com.napier.devops.repository;

import com.napier.devops.model.Population;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PopulationRepository {
    private final Connection con;

    public PopulationRepository(Connection con) {
        this.con = con;
    }

    /**
     * Requirement 23: Population living / not living in cities per Continent (with %).
     */
    public List<Population> getContinentPopulationReport() {
        String sql = "SELECT " +
                "    c.Continent AS Name, " +
                "    SUM(c.Population) AS TotalPopulation, " +
                "    COALESCE(SUM(ci.CityPopulation), 0) AS CityPopulation, " +
                "    ROUND((COALESCE(SUM(ci.CityPopulation), 0) / SUM(c.Population)) * 100, 2) AS CityPercentage, " +
                "    (SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) AS NonCityPopulation, " +
                "    ROUND(((SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) / SUM(c.Population)) * 100, 2) AS NonCityPercentage " +
                "FROM country c " +
                "LEFT JOIN (" +
                "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                "    FROM city " +
                "    GROUP BY CountryCode" +
                ") ci ON c.Code = ci.CountryCode " +
                "GROUP BY c.Continent " +
                "ORDER BY TotalPopulation DESC;";

        return executePopulationQuery(sql, "Failed to retrieve continent population report");
    }

    /**
     * Requirement 24: Population living / not living in cities per Region (with %).
     */
    public List<Population> getRegionPopulationReport() {
        String sql = "SELECT " +
                "    c.Region AS Name, " +
                "    SUM(c.Population) AS TotalPopulation, " +
                "    COALESCE(SUM(ci.CityPopulation), 0) AS CityPopulation, " +
                "    ROUND((COALESCE(SUM(ci.CityPopulation), 0) / SUM(c.Population)) * 100, 2) AS CityPercentage, " +
                "    (SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) AS NonCityPopulation, " +
                "    ROUND(((SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) / SUM(c.Population)) * 100, 2) AS NonCityPercentage " +
                "FROM country c " +
                "LEFT JOIN (" +
                "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                "    FROM city " +
                "    GROUP BY CountryCode" +
                ") ci ON c.Code = ci.CountryCode " +
                "GROUP BY c.Region " +
                "ORDER BY TotalPopulation DESC;";

        return executePopulationQuery(sql, "Failed to retrieve region population report");
    }

    /**
     * Requirement 25: Population living / not living in cities per Country (with %).
     */
    public List<Population> getCountryPopulationReport() {
        String sql = "SELECT " +
                "    c.Name AS Name, " +
                "    c.Population AS TotalPopulation, " +
                "    COALESCE(ci.CityPopulation, 0) AS CityPopulation, " +
                "    ROUND((COALESCE(ci.CityPopulation, 0) / c.Population) * 100, 2) AS CityPercentage, " +
                "    (c.Population - COALESCE(ci.CityPopulation, 0)) AS NonCityPopulation, " +
                "    ROUND(((c.Population - COALESCE(ci.CityPopulation, 0)) / c.Population) * 100, 2) AS NonCityPercentage " +
                "FROM country c " +
                "LEFT JOIN (" +
                "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                "    FROM city " +
                "    GROUP BY CountryCode" +
                ") ci ON c.Code = ci.CountryCode " +
                "ORDER BY TotalPopulation DESC;";

        return executePopulationQuery(sql, "Failed to retrieve country population report");
    }

    /**
     * Requirement 26: Total population of the World.
     */
    public List<Population> getWorldTotalPopulation() {
        String sql = "SELECT " +
                "    'World' AS Name, " +
                "    SUM(c.Population) AS TotalPopulation, " +
                "    COALESCE(SUM(ci.CityPopulation), 0) AS CityPopulation, " +
                "    ROUND((COALESCE(SUM(ci.CityPopulation), 0) / SUM(c.Population)) * 100, 2) AS CityPercentage, " +
                "    (SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) AS NonCityPopulation, " +
                "    ROUND(((SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) / SUM(c.Population)) * 100, 2) AS NonCityPercentage " +
                "FROM country c " +
                "LEFT JOIN (" +
                "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                "    FROM city " +
                "    GROUP BY CountryCode" +
                ") ci ON c.Code = ci.CountryCode;";
        return executePopulationQuery(sql, "Failed to retrieve World total population");
    }

    /**
     * Requirement 27: Total population of each Continent.
     */
    public List<Population> getContinentTotalPopulation() {
        String sql = "SELECT " +
                "    c.Continent AS Name, " +
                "    SUM(c.Population) AS TotalPopulation, " +
                "    COALESCE(SUM(ci.CityPopulation), 0) AS CityPopulation, " +
                "    ROUND((COALESCE(SUM(ci.CityPopulation), 0) / SUM(c.Population)) * 100, 2) AS CityPercentage, " +
                "    (SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) AS NonCityPopulation, " +
                "    ROUND(((SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) / SUM(c.Population)) * 100, 2) AS NonCityPercentage " +
                "FROM country c " +
                "LEFT JOIN (" +
                "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                "    FROM city " +
                "    GROUP BY CountryCode" +
                ") ci ON c.Code = ci.CountryCode " +
                "GROUP BY c.Continent " +
                "ORDER BY TotalPopulation DESC;";
        return executePopulationQuery(sql, "Failed to retrieve Continent total populations");
    }

    /**
     * Requirement 28: Total population of each Region.
     */
    public List<Population> getRegionTotalPopulation() {
        String sql = "SELECT " +
                "    c.Region AS Name, " +
                "    SUM(c.Population) AS TotalPopulation, " +
                "    COALESCE(SUM(ci.CityPopulation), 0) AS CityPopulation, " +
                "    ROUND((COALESCE(SUM(ci.CityPopulation), 0) / SUM(c.Population)) * 100, 2) AS CityPercentage, " +
                "    (SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) AS NonCityPopulation, " +
                "    ROUND(((SUM(c.Population) - COALESCE(SUM(ci.CityPopulation), 0)) / SUM(c.Population)) * 100, 2) AS NonCityPercentage " +
                "FROM country c " +
                "LEFT JOIN (" +
                "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                "    FROM city " +
                "    GROUP BY CountryCode" +
                ") ci ON c.Code = ci.CountryCode " +
                "GROUP BY c.Region " +
                "ORDER BY TotalPopulation DESC;";
        return executePopulationQuery(sql, "Failed to retrieve Region total populations");
    }

    /**
     * Requirement 29: Total population of each Country.
     */
    public List<Population> getCountryTotalPopulation() {
        String sql = "SELECT " +
                "    c.Name AS Name, " +
                "    c.Population AS TotalPopulation, " +
                "    COALESCE(ci.CityPopulation, 0) AS CityPopulation, " +
                "    ROUND((COALESCE(ci.CityPopulation, 0) / c.Population) * 100, 2) AS CityPercentage, " +
                "    (c.Population - COALESCE(ci.CityPopulation, 0)) AS NonCityPopulation, " +
                "    ROUND(((c.Population - COALESCE(ci.CityPopulation, 0)) / c.Population) * 100, 2) AS NonCityPercentage " +
                "FROM country c " +
                "LEFT JOIN (" +
                "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                "    FROM city " +
                "    GROUP BY CountryCode" +
                ") ci ON c.Code = ci.CountryCode " +
                "ORDER BY TotalPopulation DESC;";
        return executePopulationQuery(sql, "Failed to retrieve Country total populations");
    }

    /**
     * Requirement 30: Total population of each District.
     */
    public List<Population> getDistrictTotalPopulation() {
        String sql = "SELECT " +
                "    District AS Name, " +
                "    SUM(Population) AS TotalPopulation, " +
                "    SUM(Population) AS CityPopulation, " +
                "    100.00 AS CityPercentage, " +
                "    0 AS NonCityPopulation, " +
                "    0.00 AS NonCityPercentage " +
                "FROM city " +
                "GROUP BY District " +
                "ORDER BY TotalPopulation DESC;";
        return executePopulationQuery(sql, "Failed to retrieve District total populations");
    }

    /**
     * Requirement 31: Total population of each City.
     */
    public List<Population> getCityTotalPopulation() {
        String sql = "SELECT " +
                "    Name AS Name, " +
                "    Population AS TotalPopulation, " +
                "    Population AS CityPopulation, " +
                "    100.00 AS CityPercentage, " +
                "    0 AS NonCityPopulation, " +
                "    0.00 AS NonCityPercentage " +
                "FROM city " +
                "ORDER BY Population DESC;";
        return executePopulationQuery(sql, "Failed to retrieve City total populations");
    }

    /**
     * Helper method to map ResultSet records to Population objects.
     */
    private Population mapResultSetToPopulation(ResultSet rset) throws SQLException {
        String name = rset.getString("Name");
        long totalPopulation = rset.getLong("TotalPopulation");
        long cityPopulation = rset.getLong("CityPopulation");
        double cityPercentage = rset.getDouble("CityPercentage");
        long nonCityPopulation = rset.getLong("NonCityPopulation");
        double nonCityPercentage = rset.getDouble("NonCityPercentage");

        return new Population(name, totalPopulation, cityPopulation, cityPercentage, nonCityPopulation, nonCityPercentage);
    }

    /**
     * Helper template method to execute queries safely with standard error handling.
     */
    private List<Population> executePopulationQuery(String sql, String errorMessage) {
        List<Population> populations = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rset = stmt.executeQuery(sql)) {

            while (rset.next()) {
                populations.add(mapResultSetToPopulation(rset));
            }
        } catch (SQLException e) {
            System.err.println(errorMessage + ": " + e.getMessage());
        }

        return populations;
    }
}