package com.napier.devops.repository;

import com.napier.devops.model.Country;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository {
    private Connection con;

    public CountryRepository(Connection con) {
        this.con = con;
    }

    /**
     * Requirement 1: All the countries in the world organised by largest population to smallest.
     */
    public List<Country> getAllCountriesWorld() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next()) {
                countries.add(extractCountry(rset));
            }
        } catch (Exception e) {
            System.out.println("Error fetching world countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * Requirement 2: All the countries in a continent organised by largest population to smallest.
     */
    public List<Country> getAllCountriesContinent(String continent) {
        List<Country> countries = new ArrayList<>();
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = ? ORDER BY Population DESC"
            );
            pstmt.setString(1, continent);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                countries.add(extractCountry(rset));
            }
        } catch (Exception e) {
            System.out.println("Error fetching continent countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * Requirement 3: All the countries in a region organised by largest population to smallest.
     */
    public List<Country> getAllCountriesRegion(String region) {
        List<Country> countries = new ArrayList<>();
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ? ORDER BY Population DESC"
            );
            pstmt.setString(1, region);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                countries.add(extractCountry(rset));
            }
        } catch (Exception e) {
            System.out.println("Error fetching region countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * Requirement 4: The top N populated countries in the world.
     */
    public List<Country> getTopNCountriesWorld(int n) {
        List<Country> countries = new ArrayList<>();
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT ?"
            );
            pstmt.setInt(1, n);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                countries.add(extractCountry(rset));
            }
        } catch (Exception e) {
            System.out.println("Error fetching top N world countries: " + e.getMessage());
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

    /**
     * Prints the country report list.
     */
    public void printCountryReport(List<Country> countries) {
        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries found.");
            return;
        }
        System.out.println(String.format("| %-5s | %-25s | %-15s | %-25s | %-15s | %-20s |",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        System.out.println("-".repeat(120));
        for (Country country : countries) {
            if (country != null) {
                System.out.println(country.toString());
            }
        }
    }
}