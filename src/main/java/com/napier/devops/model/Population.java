package com.napier.devops.model;

public class Population {
    private String name;
    private long totalPopulation;
    private long cityPopulation;
    private double cityPercentage;
    private long nonCityPopulation;
    private double nonCityPercentage;

    public Population(String name, long totalPopulation, long cityPopulation, double cityPercentage, long nonCityPopulation, double nonCityPercentage) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.cityPopulation = cityPopulation;
        this.cityPercentage = cityPercentage;
        this.nonCityPopulation = nonCityPopulation;
        this.nonCityPercentage = nonCityPercentage;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getTotalPopulation() { return totalPopulation; }
    public void setTotalPopulation(long totalPopulation) { this.totalPopulation = totalPopulation; }

    public long getCityPopulation() { return cityPopulation; }
    public void setCityPopulation(long cityPopulation) { this.cityPopulation = cityPopulation; }

    public double getCityPercentage() { return cityPercentage; }
    public void setCityPercentage(double cityPercentage) { this.cityPercentage = cityPercentage; }

    public long getNonCityPopulation() { return nonCityPopulation; }
    public void setNonCityPopulation(long nonCityPopulation) { this.nonCityPopulation = nonCityPopulation; }

    public double getNonCityPercentage() { return nonCityPercentage; }
    public void setNonCityPercentage(double nonCityPercentage) { this.nonCityPercentage = nonCityPercentage; }

    @Override
    public String toString() {
        return String.format("| %-25s | %,15d | %,15d (%6.2f%%) | %,15d (%6.2f%%) |",
                name, totalPopulation, cityPopulation, cityPercentage, nonCityPopulation, nonCityPercentage);
    }
}