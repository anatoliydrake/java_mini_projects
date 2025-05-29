package com.anatoliydrake.DataCollector;

import com.anatoliydrake.DataCollector.model.Line;
import com.anatoliydrake.DataCollector.model.Station;
import com.anatoliydrake.DataCollector.model.StationWithDate;
import com.anatoliydrake.DataCollector.model.StationWithDepth;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalDouble;

public class JSONWriter {
    private final List<Line> lines;
    private final List<Station> stations;
    private final List<StationWithDepth> stationWithDepthList;
    private final List<StationWithDate> stationWithDateList;

    public JSONWriter(List<Line> lines, List<Station> stations,
                      List<StationWithDepth> stationWithDepthList,
                      List<StationWithDate> stationWithDateList) {
        this.lines = lines;
        this.stations = stations;
        this.stationWithDepthList = stationWithDepthList;
        this.stationWithDateList = stationWithDateList;
    }

    public void writeMetroJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        ArrayNode lineArray = mapper.createArrayNode();
        for (Line line : lines) {
            ObjectNode lineNode = mapper.createObjectNode();
            lineNode.put("number", line.getNumber());
            lineNode.put("name", line.getName());
            lineArray.add(lineNode);
        }
        rootNode.set("lines", lineArray);

        ObjectNode lineNode = mapper.createObjectNode();
        ArrayNode stationsArr = mapper.createArrayNode();
        String prevNumber = "1";
        for (Station s : stations) {
            if (!s.getLineNumber().equals(prevNumber)) {
                lineNode.set(prevNumber, stationsArr);
                stationsArr = mapper.createArrayNode();
            }
            stationsArr.add(s.getName());
            prevNumber = s.getLineNumber();
        }
        lineNode.set(prevNumber, stationsArr);
        rootNode.set("stations", lineNode);

        File metro = new File("src/main/resources/metro.json");
        mapper.writeValue(metro, rootNode);
    }

    public void writeStationsJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        ArrayNode lineArray = mapper.createArrayNode();

        for (Station s : stations) {
            ObjectNode node = mapper.createObjectNode();
            String name = s.getName();
            node.put("name", name);
            String line;
            String date;
            String depth;

            for (Line l : lines) {
                if (s.getLineNumber().equals(l.getNumber())) {
                    line = l.getName();
                    node.put("line", line);
                }
            }

            for (StationWithDate station : stationWithDateList) {
                if (s.getName().equals(station.getName())) {
                    date = station.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    node.put("date", date);
                }
            }

            OptionalDouble optionalDepth = stationWithDepthList.stream()
                    .filter(st -> st.getStation_name().equals(s.getName()))
                    .map(StationWithDepth::getDepth)
                    .filter(st -> !st.equals("?"))
                    .map(st -> st.replace(',', '.'))
                    .mapToDouble(Double::parseDouble)
                    .min();
            depth = optionalDepth.isPresent() ? String.valueOf(optionalDepth.getAsDouble()) : "";
            if (!depth.isEmpty()) {
                node.put("depth", depth.replace(".", ","));
            }
            lineArray.add(node);
        }
        rootNode.set("stations", lineArray);
        File metro = new File("src/main/resources/stations.json");
        mapper.writeValue(metro, rootNode);
    }
}
