package com.napier.devops.repository;

import com.napier.devops.model.City;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {
    private final Connection con;

    public CityRepository(Connection con) {
        this.con = con;
    }

    /**
     * Requirement 7: All the cities in the world ordered by population largest to smallest.
     */
    public List<City> getAllCitiesWorld() {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, ci.Population " +
                "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                "ORDER BY ci.Population DESC";

        try (Statement stmt = con.createStatement();
             ResultSet rset = stmt.executeQuery(sql)) {

            while (rset.next()) {
                cities.add(new City(
                        rset.getString("CityName"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Database query failed: " + e.getMessage());
        }
        return cities;
    }

    /**
     * Requirement 8: All the cities in a continent ordered by population largest to smallest.
     */
    public List<City> getAllCitiesContinent(String continent) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, ci.Population " +
                "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                "WHERE co.Continent = ? " +
                "ORDER BY ci.Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, continent);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(new City(
                            rset.getString("CityName"),
                            rset.getString("CountryName"),
                            rset.getString("District"),
                            rset.getLong("Population")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Database query failed: " + e.getMessage());
        }
        return cities;
    }

    /**
     * Requirement 9: All the cities in a region ordered by population largest to smallest.
     */
    public List<City> getAllCitiesRegion(String region) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, ci.Population " +
                "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                "WHERE co.Region = ? " +
                "ORDER BY ci.Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, region);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(new City(
                            rset.getString("CityName"),
                            rset.getString("CountryName"),
                            rset.getString("District"),
                            rset.getLong("Population")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Database query failed: " + e.getMessage());
        }
        return cities;
    }

    /**
     * Requirement 10: All the cities in a country ordered by population largest to smallest.
     */
    public List<City> getAllCitiesCountry(String countryName) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, ci.Population " +
                "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                "WHERE co.Name = ? " +
                "ORDER BY ci.Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, countryName);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(new City(
                            rset.getString("CityName"),
                            rset.getString("CountryName"),
                            rset.getString("District"),
                            rset.getLong("Population")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Database query failed: " + e.getMessage());
        }
        return cities;
    }

    /**
     * Requirement 11: All the cities in a district ordered by population largest to smallest.
     */
    public List<City> getAllCitiesDistrict(String district) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, ci.Population " +
                "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                "WHERE ci.District = ? " +
                "ORDER BY ci.Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, district);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(new City(
                            rset.getString("CityName"),
                            rset.getString("CountryName"),
                            rset.getString("District"),
                            rset.getLong("Population")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Database query failed: " + e.getMessage());
        }
        return cities;
    }

    // Requirement 12: Top N cities in the world
    public List<City> getTopNCitiesWorld(int n) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT Name, CountryCode, District, Population " +
                "FROM city ORDER BY Population DESC LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, n);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(new City(
                            rset.getString("Name"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getLong("Population")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving top N cities: " + e.getMessage());
        }
        return cities;
    }

    // Requirement 13: Top N cities in a continent
    public List<City> getTopNCitiesContinent(String continent, int n) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(new City(
                            rset.getString("Name"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getLong("Population")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving top N cities by continent: " + e.getMessage());
        }
        return cities;
    }

    // Requirement 14: Top N cities in a region
    public List<City> getTopNCitiesRegion(String region, int n) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, region);
            stmt.setInt(2, n);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(new City(
                            rset.getString("Name"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getLong("Population")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving top N cities by region: " + e.getMessage());
        }
        return cities;
    }

    // Requirement 15: Top N cities in a country
    public List<City> getTopNCitiesCountry(String countryCode, int n) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT Name, CountryCode, District, Population " +
                "FROM city WHERE CountryCode = ? " +
                "ORDER BY Population DESC LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, countryCode);
            stmt.setInt(2, n);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(new City(
                            rset.getString("Name"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getLong("Population")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving top N cities by country: " + e.getMessage());
        }
        return cities;
    }

    // Requirement 16: Top N cities in a district
    public List<City> getTopNCitiesDistrict(String district, int n) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT Name, CountryCode, District, Population " +
                "FROM city WHERE District = ? " +
                "ORDER BY Population DESC LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, district);
            stmt.setInt(2, n);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(new City(
                            rset.getString("Name"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getLong("Population")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving top N cities by district: " + e.getMessage());
        }
        return cities;
    }

}