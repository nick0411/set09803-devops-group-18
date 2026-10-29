package com.napier.devops.model;

public class population_report {

    private String name;
    private long population;
    private double percentage;

    public Population(String name, long population, double percentage) {
        this.name = name;
        this.population = population;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return String.format("| %-22s | %-20d | %-11.2f%% |",
                name, population, percentage);
    }
}