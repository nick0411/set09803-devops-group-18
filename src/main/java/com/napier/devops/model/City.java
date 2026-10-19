package com.napier.devops.model;

public class City {
    private String name;
    private String country;
    private String district;
    private long population;

    public City(String name, String country, String district, long population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    // Getter and Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public long getPopulation() { return population; }
    public void setPopulation(long population) { this.population = population; }

    @Override
    public String toString() {
        return String.format("| %-25s | %-25s | %-20s | %,15d |",
                name, country, district, population);
    }
}