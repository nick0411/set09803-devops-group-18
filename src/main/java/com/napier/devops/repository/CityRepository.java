package com.napier.devops.repository;

import com.napier.devops.model.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {
    private final Connection con;
    public CityRepository(Connection con){
        this.con = con;
    }

    /**
     * Requirement 12:
     * Get the top N populated cities in the world.
     *
     * @param n Number of cities requested
     * @return List of cities
     */
    public List<City> getTopNCitiesWorld(int n) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT Name, CountryCode, District, Population " +
                "FROM city " +
                "ORDER BY Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, n);

            ResultSet rset = stmt.executeQuery();

            while (rset.next()) {
                String name = rset.getString("Name");
                String country = rset.getString("CountryCode");
                String district = rset.getString("District");
                long population = rset.getLong("Population");

                cities.add(new City(name, country, district, population));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving top N cities: " + e.getMessage());
        }


        return cities;

    }

    /**
     * Requirement 13:
     * Get the top N populated cities in a continent.
     */
    public List<City> getTopNCitiesContinent(String continent, int n) {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT city.Name, city.CountryCode, " +
                "city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, continent);
            stmt.setInt(2, n);

            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    String name = rset.getString("Name");
                    String country = rset.getString("CountryCode");
                    String district = rset.getString("District");
                    long population = rset.getLong("Population");

                    cities.add(new City(name, country, district, population));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving top N cities by continent: "
                    + e.getMessage());
        }

        return cities;
    }

    /**
     * Requirement 14:
     * Get the top N populated cities in a region.
     */
    public List<City> getTopNCitiesRegion(String region, int n) {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT city.Name, city.CountryCode, " +
                "city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, region);
            stmt.setInt(2, n);

            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    String name = rset.getString("Name");
                    String country = rset.getString("CountryCode");
                    String district = rset.getString("District");
                    long population = rset.getLong("Population");

                    cities.add(new City(name, country, district, population));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving top N cities by region: "
                    + e.getMessage());
        }

        return cities;
    }


}
