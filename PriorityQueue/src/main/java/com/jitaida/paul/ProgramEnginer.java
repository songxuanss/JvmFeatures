package com.jitaida.paul;

public class ProgramEnginer implements Comparable{
    private String name;
    private int height;
    private int weight;
    private double skillRating;

    public ProgramEnginer(String name, int height, int weight, double skillRating) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.skillRating = skillRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(double skillRating) {
        this.skillRating = skillRating;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
