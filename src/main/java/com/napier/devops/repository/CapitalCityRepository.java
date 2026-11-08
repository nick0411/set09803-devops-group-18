package com.napier.devops.repository;

import com.napier.devops.model.CapitalCity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CapitalCityRepository {
    private final Connection con;

    public CapitalCityRepository(Connection con) {this.con = con;}

    /**
     * Requirement 17: All capital cities in the world, largest population to smallest.
     */
    public List<CapitalCity> getAllCapitalsWorld() {
        List<CapitalCity> capitals = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalName, country.Name AS CountryName, city.Population " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "ORDER BY city.Population DESC";

        try (Statement myQuery = con.createStatement();
             ResultSet results = myQuery.executeQuery(sql)) {

            while (results.next()) {
                capitals.add(new CapitalCity(
                        results.getString("CapitalName"),
                        results.getString("CountryName"),
                        results.getLong("Population")));
            }
        } catch (SQLException e) {
            System.err.println("Failed to get capital cities: " + e.getMessage());
        }

        return capitals;
    }
    /**
     * Requirement 18: All capital cities in a continent, largest population to smallest.
     */
    public List<CapitalCity> getAllCapitalsContinent(String continent) {
        List<CapitalCity> capitals = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalName, country.Name AS CountryName, city.Population " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";

        try (PreparedStatement myQuery = con.prepareStatement(sql)) {
            myQuery.setString(1, continent);

            try (ResultSet results = myQuery.executeQuery()) {
                while (results.next()) {
                    capitals.add(new CapitalCity(
                            results.getString("CapitalName"),
                            results.getString("CountryName"),
                            results.getLong("Population")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to get capital cities: " + e.getMessage());
        }

        return capitals;
    }
    /**
     * Requirement 19: All capital cities in a region, largest to smallest.
     */
    public List<CapitalCity> getAllCapitalsRegion(String region) {
        List<CapitalCity> capitals = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalName, country.Name AS CountryName, city.Population " +
                "FROM country " +
                "JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";

        try (PreparedStatement myQuery = con.prepareStatement(sql)) {
            myQuery.setString(1, region);

            try (ResultSet results = myQuery.executeQuery()) {
                while (results.next()) {
                    capitals.add(new CapitalCity(
                            results.getString("CapitalName"),
                            results.getString("CountryName"),
                            results.getLong("Population")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to get capital cities: " + e.getMessage());
        }

        return capitals;
    }
}