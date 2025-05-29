package com.anatoliydrake.DataCollector;

import com.anatoliydrake.DataCollector.model.Line;
import com.anatoliydrake.DataCollector.model.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLParser {

    private final List<Line> lines;
    private final List<Station> stations;
    private final String LINK = "https://skillbox-java.github.io";

    public HTMLParser() {
        this.lines = new ArrayList<>();
        this.stations = new ArrayList<>();
    }

    public Document getDocumentFromHTML() throws IOException {
        return Jsoup.connect(LINK).get();
    }

    public void parseLines() throws IOException {
        Document doc = getDocumentFromHTML();
        Elements lines = doc.select("span[data-line]");
        String number;
        String name;
        for (Element l : lines) {
            number = l.attr("data-line");
            name = l.childNode(0).toString();
            this.lines.add(new Line(number, name));
        }
    }

    public void parseStations() throws IOException {
        Document doc = getDocumentFromHTML();
        Elements lines = doc.select("div[data-line]");
        String number;
        String name;
        for (Element l : lines) {
            Elements stations = l.select("[class=\"name\"]");
            number = l.attr("data-line");
            for (Element s : stations) {
                name = s.childNode(0).toString();
                this.stations.add(new Station(number, name));
            }
        }
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Station> getStations() {
        return stations;
    }
}
