package com.depe.roaddifficulties.traffic_difficulty;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

class TrafficDifficultyXMLParser {

    static Results deserializeResultsFromXMLAsString(String xml) {
        try {
            xml = xml.replaceAll("\\r\\n|\\r|\\n", " ");
            InputStream targetStream = new ByteArrayInputStream(xml.getBytes());
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(targetStream);
            Element root = doc.getDocumentElement();
            NodeList difficulties = root.getChildNodes();
            List<TrafficDifficulty> trafficDifficulties = new ArrayList<>();
            for (int i = 0; i < difficulties.getLength(); i++) {
                Map<String, String> nodeAndValuesMap = new HashMap<>();
                NodeList difficultiesNodeList = difficulties.item(i).getChildNodes();
                for (int j = 0; j < difficultiesNodeList.getLength(); j++) {
                    Node child = difficultiesNodeList.item(j);
                    if (child.getFirstChild() != null) {
                        if (child.getNodeName().equals("rodzaj")) {
                            nodeAndValuesMap.put("poz", child.getFirstChild().getFirstChild().getNodeValue());
                        } else if (child.getNodeName().equals("czasy_oczekiwania")) {
                            NodeList czasy = child.getFirstChild().getChildNodes();
                            for (int k = 0; k < czasy.getLength(); k++) {
                                nodeAndValuesMap.put(czasy.item(k).getNodeName(), czasy.item(k).getFirstChild().getNodeValue());
                            }
                        } else {
                            nodeAndValuesMap.put(child.getNodeName(), child.getFirstChild().getNodeValue());
                        }
                    }
                }
                trafficDifficulties.add(createTrafficDifficultyFromMap(nodeAndValuesMap));
            }
            return Results.builder()
                    .date(parseStringToLocalDateTime(root.getAttribute("gen")))
                    .trafficDifficulties(trafficDifficulties)
                    .build();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException();
        }
    }

    private static TrafficDifficulty createTrafficDifficultyFromMap(Map<String, String> nodeAndValuesMap) {
        return TrafficDifficulty.
                builder()
                .difficultyType(nodeAndValuesMap.get("typ"))
                .road(
                        Road.builder()
                                .name(nodeAndValuesMap.get("nr_drogi"))
                                .km(parseStringToDouble(nodeAndValuesMap.get("km")))
                                .build()
                )
                .voivodeship(getVoivodeshipFromString(nodeAndValuesMap.get("woj")))
                .length(parseStringToDouble(nodeAndValuesMap.get("dl")))
                .location(Location.builder()
                        .geoLat(parseStringToDouble(nodeAndValuesMap.get("geo_lat")))
                        .geoLong(parseStringToDouble(nodeAndValuesMap.get("geo_long")))
                        .build())
                .name(nodeAndValuesMap.get("nazwa_odcinka"))
                .startDate(parseStringToLocalDateTime(nodeAndValuesMap.get("data_powstania")))
                .finishDate(parseStringToLocalDateTime(nodeAndValuesMap.get("data_likwidacji")))
                .roundAbout(parseString(nodeAndValuesMap.get("objazd")))
                .type(Type.builder().poz(nodeAndValuesMap.get("poz")).build())
                .effects(nodeAndValuesMap.get("skutki"))
                .liftingCapacityLimit(nodeAndValuesMap.get("ogr_nosnosc"))
                .pressureCapacityLimit(nodeAndValuesMap.get("ogr_nacisk"))
                .verticalExtremeLimit(nodeAndValuesMap.get("ogr_skrajna_pionowa"))
                .horizontalExtremeLimit(nodeAndValuesMap.get("ogr_skrajna_pozioma"))
                .widthLimit(parseStringToDouble(nodeAndValuesMap.get("ogr_szerokosc")))
                .speedLimit(parseStringToInteger(nodeAndValuesMap.get("ogr_predkosc")))
                .isAlternatingTraffic(parseStringToBoolean(nodeAndValuesMap.get("ruch_wahadlowy")))
                .isTrafficLights(parseStringToBoolean(nodeAndValuesMap.get("sygnalizacja_swietlna")))
                .isBridgeFailure(parseStringToBoolean(nodeAndValuesMap.get("awaria_mostu")))
                .isTwoWay(parseStringToBoolean(nodeAndValuesMap.get("ruch_2_kierunkowy")))
                .isClosedRoad(parseStringToBoolean(nodeAndValuesMap.get("droga_zamknieta")))
                .latency(Latency.builder()
                        .latencyTime(LatencyTime.builder()
                                .direction(nodeAndValuesMap.get("kierunek"))
                                .timeFrom(nodeAndValuesMap.get("czas_od"))
                                .timeBy(nodeAndValuesMap.get("czas_do"))
                                .fromMondayToFriday(nodeAndValuesMap.get("pn_pt"))
                                .saturday(nodeAndValuesMap.get("so"))
                                .sunday(nodeAndValuesMap.get("ni"))
                                .build()).build())
                .build();
    }

    private static String parseString(String roundAbout) {
        if (roundAbout != null && roundAbout.length() > 0) {
            return roundAbout.substring(0, roundAbout.length() - 1);
        }
        return roundAbout;
    }
    private static Voivodeship getVoivodeshipFromString(String voivodeship) {
        return switch(voivodeship){
            case "śląskie" -> Voivodeship.SLASKIE;
            case "dolnośląskie" -> Voivodeship.DOLNOSLASKIE;
            case "kujawsko-pomorskie" -> Voivodeship.KUJAWSKO_POMORSKIE;
            case "warmińsko-mazurskie" -> Voivodeship.WARMINSKO_MAZURSKIE;
            case "łódzkie" -> Voivodeship.LODZKIE;
            case "małopolskie" -> Voivodeship.MALOPOLSKIE;
            case "świętokrzyskie" -> Voivodeship.SWIETOKRZYSKIE;
            default -> Voivodeship.valueOf(voivodeship.toUpperCase(Locale.ROOT));
        };
    }


    private static LocalDateTime parseStringToLocalDateTime(String date) {
        return LocalDateTime.parse(date.substring(0, 16));
    }

    private static Double parseStringToDouble(String number) {
        return number != null ? Double.parseDouble(number) : 0;
    }

    private static Boolean parseStringToBoolean(String predicate) {
        return predicate != null && predicate.equals("true");
    }

    private static Integer parseStringToInteger(String number) {
        return number != null ? Integer.parseInt(number) : 0;
    }
}
