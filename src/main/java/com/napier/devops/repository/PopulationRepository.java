package com.napier.devops.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulationRepository {
    private final Connection con;

    public PopulationRepository(Connection con) {
        this.con = con;
    }

    /**
     * Get the total population of the world.
     */
    public long getWorldPopulation() throws SQLException {
        String sql =
                "SELECT COALESCE(SUM(Population), 0) AS TotalPopulation " +
                        "FROM country";

        return queryPopulation(sql);
    }

    /**
     * Get the total population of a continent.
     */
    public long getContinentPopulation(String continent) throws SQLException {
        String sql =
                "SELECT COALESCE(SUM(Population), 0) AS TotalPopulation " +
                        "FROM country " +
                        "WHERE Continent = ?";

        return queryPopulation(sql, continent);
    }

    /**
     * Get the total population of a region.
     */
    public long getRegionPopulation(String region) throws SQLException {
        String sql =
                "SELECT COALESCE(SUM(Population), 0) AS TotalPopulation " +
                        "FROM country " +
                        "WHERE Region = ?";

        return queryPopulation(sql, region);
    }

    /**
     * Get the total population of a country using its country code.
     */
    public long getCountryPopulation(String countryCode) throws SQLException {
        String sql =
                "SELECT COALESCE(SUM(Population), 0) AS TotalPopulation " +
                        "FROM country " +
                        "WHERE Code = ?";

        return queryPopulation(sql, countryCode);
    }

    /**
     * Get the total population of a district within a country.
     */
    public long getDistrictPopulation(
            String countryCode,
            String district
    ) throws SQLException {
        String sql =
                "SELECT COALESCE(SUM(Population), 0) AS TotalPopulation " +
                        "FROM city " +
                        "WHERE CountryCode = ? AND District = ?";

        return queryPopulation(sql, countryCode, district);
    }

    /**
     * Get the population of a city using its unique ID.
     */
    public long getCityPopulation(int cityId) throws SQLException {
        String sql =
                "SELECT COALESCE(SUM(Population), 0) AS TotalPopulation " +
                        "FROM city " +
                        "WHERE ID = ?";

        return queryPopulation(sql, cityId);
    }

    /**
     * Execute a population query with optional parameters.
     */
    private long queryPopulation(
            String sql,
            Object... parameters
    ) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }

            try (ResultSet rset = stmt.executeQuery()) {
                if (rset.next()) {
                    return rset.getLong("TotalPopulation");
                }

                return 0L;
            }
        }
    }
}