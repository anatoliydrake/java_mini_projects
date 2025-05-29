package com.anatoliydrake.DataCollector;

import com.anatoliydrake.DataCollector.model.StationWithDepth;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    public List<StationWithDepth> parseStations(String json) throws IOException {
        List<StationWithDepth> stations = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = objectMapper.readTree(json);
        for (JsonNode jsonNode : jsonData) {
            StationWithDepth station = objectMapper.readValue(jsonNode.toString(), StationWithDepth.class);
            stations.add(station);
        }
        return stations;
    }
}
