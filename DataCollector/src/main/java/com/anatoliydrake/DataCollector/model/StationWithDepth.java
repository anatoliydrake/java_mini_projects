package com.anatoliydrake.DataCollector.model;

public class StationWithDepth {
    private String station_name;
    private String depth;

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\tstation_name: \"" + station_name + '\"' +
                "\n\tdepth: \"" + depth + '\"' +
                "\n}";
    }
}
