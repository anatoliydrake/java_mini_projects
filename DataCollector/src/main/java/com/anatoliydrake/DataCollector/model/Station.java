package com.anatoliydrake.DataCollector.model;

public class Station {
    private final String lineNumber;
    private final String name;

    public Station(String lineNumber, String name) {
        this.lineNumber = lineNumber;
        this.name = name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return lineNumber + " - " + name;
    }
}
