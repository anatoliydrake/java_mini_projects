package com.anatoliydrake.DataCollector;

import com.anatoliydrake.DataCollector.model.Line;
import com.anatoliydrake.DataCollector.model.Station;
import com.anatoliydrake.DataCollector.model.StationWithDate;
import com.anatoliydrake.DataCollector.model.StationWithDepth;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HTMLParser htmlParser = new HTMLParser();
        try {
            htmlParser.parseLines();
            htmlParser.parseStations();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Line> lines = htmlParser.getLines();
        List<Station> stations = htmlParser.getStations();

        FilesScanner filesScanner = new FilesScanner();
        List<File> files = filesScanner.getFiles("data");

        List<StationWithDepth> stationWithDepthList;
        try {
            String jsonFile = Files.readString(Paths.get("data/2/4/depths-1.json"));
            JSONParser jp = new JSONParser();
            stationWithDepthList = jp.parseStations(jsonFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CSVParser csvParser = new CSVParser();
        List<StationWithDate> stationWithDateList = csvParser.parseStations("data/4/6/dates-1.csv");

        JSONWriter jsonWriter = new JSONWriter(lines, stations, stationWithDepthList, stationWithDateList);
        try {
            jsonWriter.writeMetroJSON();
            jsonWriter.writeStationsJSON();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
