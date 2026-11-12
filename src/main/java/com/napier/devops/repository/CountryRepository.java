package com.napier.devops.repository;

import com.napier.devops.model.Country;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository {
    private final Connection con;

    public CountryRepository(Connection con) {
        this.con = con;
    }

    /**
     * Requirement 1: All the countries in the world organised by largest population to smallest.
     */
    public List<Country> getAllCountriesWorld() {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";

        try (Statement stmt = con.createStatement();
             ResultSet rset = stmt.executeQuery(query)) {
            while (rset.next()) {
                countries.add(extractCountry(rset));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching world countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * Requirement 2: All the countries in a continent organised by largest population to smallest.
     */
    public List<Country> getAllCountriesContinent(String continent) {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = ? ORDER BY Population DESC";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, continent);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    countries.add(extractCountry(rset));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching continent countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * Requirement 3: All the countries in a region organised by largest population to smallest.
     */
    public List<Country> getAllCountriesRegion(String region) {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ? ORDER BY Population DESC";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, region);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    countries.add(extractCountry(rset));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching region countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * Requirement 4: The top N populated countries in the world.
     */
    public List<Country> getTopNCountriesWorld(int n) {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, n);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    countries.add(extractCountry(rset));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching top N world countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * Requirement 5: The top N populated countries in a continent.
     */
    public List<Country> getTopNCountriesContinent(String continent, int n) {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = ? ORDER BY Population DESC LIMIT ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, continent);
            pstmt.setInt(2, n);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    countries.add(extractCountry(rset));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching top N continent countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * Requirement 6: The top N populated countries in a region.
     */
    public List<Country> getTopNCountriesRegion(String region, int n) {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ? ORDER BY Population DESC LIMIT ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, region);
            pstmt.setInt(2, n);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    countries.add(extractCountry(rset));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching top N region countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * Helper method to map a ResultSet row to a Country object.
     */
    private Country extractCountry(ResultSet rset) throws SQLException {
        return new Country(
                rset.getString("Code"),
                rset.getString("Name"),
                rset.getString("Continent"),
                rset.getString("Region"),
                rset.getLong("Population"),
                rset.getString("Capital")
        );
    }
}