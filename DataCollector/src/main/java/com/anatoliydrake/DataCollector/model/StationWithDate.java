package com.anatoliydrake.DataCollector.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StationWithDate {
    private final String name;
    private final LocalDate date;

    public StationWithDate(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\tstation_name: \"" + name + '\"' +
                "\n\tdate: \"" + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + '\"' +
                "\n}";
    }
}
