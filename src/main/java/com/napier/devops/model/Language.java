package com.napier.devops.model;

/**
 * Represents language speaker metrics for global distribution reporting.
 */
public class Language {
    private String name;
    private long totalSpeakers;
    private double worldPercentage;

    public Language(String name, long totalSpeakers, double worldPercentage) {
        this.name = name;
        this.totalSpeakers = totalSpeakers;
        this.worldPercentage = worldPercentage;
    }

    // Getters
    public String getName() {
        return name;
    }
    public long getTotalSpeakers() {
        return totalSpeakers;
    }
    public double getWorldPercentage() {
        return worldPercentage;
    }

    @Override
    public String toString() {
        return String.format("| %-22s | %,20d | %10.2f%%  |", name, totalSpeakers, worldPercentage);
    }
}