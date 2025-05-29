package com.anatoliydrake.DataCollector.model;

public class Line {
    private final String number;
    private final String name;

    public Line(String number, String name) {
        this.name = name;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return number + " - " + name;
    }
}
