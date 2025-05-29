package com.anatoliydrake.DataCollector;

import com.anatoliydrake.DataCollector.model.StationWithDate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public List<StationWithDate> parseStations(String path) {
        List<StationWithDate> stations = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            String firstLine = null;
            for (String line : lines) {
                if (firstLine == null) {
                    firstLine = line;
                    continue;
                }
                String[] tokens = line.split(",");
                LocalDate date = LocalDate.parse(tokens[1], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                StationWithDate station = new StationWithDate(tokens[0], date);
                stations.add(station);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stations;
    }
}
