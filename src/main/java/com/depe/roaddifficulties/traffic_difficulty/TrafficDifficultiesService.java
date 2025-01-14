package com.depe.roaddifficulties.traffic_difficulty;

import com.depe.roaddifficulties.exceptions.WrongParamException;
import com.depe.roaddifficulties.client.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;

@Service
public class TrafficDifficultiesService {

    private final GddkiaApiClient gddkiaApiClient;

    public TrafficDifficultiesService(GddkiaApiClient gddkiaApiClient) {
        this.gddkiaApiClient = gddkiaApiClient;
    }

    public Results getResults() {
        String responseBody = gddkiaApiClient.getResponse();
        return getResultsFromResponseBody(responseBody);
    }

    private static Results getResultsFromResponseBody(String responseBody){
        return TrafficDifficultyXMLParser.deserializeResultsFromXMLAsString(responseBody);
    }
    public Results getResultsByVoivodeship(String queryVoivodeship) {
        Voivodeship voivodeship = getVoivodeshipFromString(queryVoivodeship);
        String responseBody = gddkiaApiClient.getResponse();
        Results results = getResultsFromResponseBody(responseBody);
        List<TrafficDifficulty> trafficDifficulties = results.getTrafficDifficulties().stream()
                .filter(trafficDifficulty -> trafficDifficulty.getVoivodeship().equals(voivodeship))
                .toList();
        return new Results(results.getDate(), trafficDifficulties);
    }

    private static Voivodeship getVoivodeshipFromString(String voivodeship) {
        if(isVoivodeship(voivodeship)){
            return Voivodeship.valueOf(voivodeship.toUpperCase(Locale.ROOT));
        }
        throw new WrongParamException(voivodeship + " is not a voivodeship");
    }

    private static boolean isVoivodeship(String voivodeship) {
        if(voivodeship != null){
            for (Voivodeship v : Voivodeship.values()) {
                if (v.name().toLowerCase(Locale.ROOT).equals(voivodeship.toLowerCase(Locale.ROOT))) {
                    return true;
                }
            }
        }
        return false;
    }

    public Results getResultsByRoadName(String roadName) {
        String responseBody = gddkiaApiClient.getResponse();
        Results results = getResultsFromResponseBody(responseBody);
        List<TrafficDifficulty> trafficDifficulties = results.getTrafficDifficulties().stream()
                .filter(trafficDifficulty -> trafficDifficulty.getRoad().getName().equals(roadName.toUpperCase(Locale.ROOT)))
                .toList();
        return new Results(results.getDate(), trafficDifficulties);
    }

    public Results getResultsByDistance(Double geoLat, Double geoLong, Double distance) {
        Location location = Location.builder().geoLat(geoLat)
                .geoLong(geoLong)
                .build();
        String responseBody = gddkiaApiClient.getResponse();
        Results results = getResultsFromResponseBody(responseBody);
        List<TrafficDifficulty> trafficDifficulties = results.getTrafficDifficulties().stream()
                .filter(trafficDifficulty -> isTrafficDifficultyInRange(location, trafficDifficulty, distance))
                .toList();
        return new Results(results.getDate(), trafficDifficulties);
    }

    private static Boolean isTrafficDifficultyInRange(Location location, TrafficDifficulty trafficDifficulty, Double maxDistance) {
        return getDistanceInKilometers(location, trafficDifficulty) <= maxDistance;
    }

    private static Double getDistanceInKilometers(Location location, TrafficDifficulty trafficDifficulty) {
        return calculateDistanceInKilometres(location.getGeoLong(), location.getGeoLat(), trafficDifficulty.getLocation().getGeoLong(), trafficDifficulty.getLocation().getGeoLat());
    }

    private static Double calculateDistanceInKilometres(double lon1, double lat1, double lon2, double lat2) {
        //inspired by https://www.movable-type.co.uk/scripts/latlong.html
        double r = 6371e3; // metres
        double latitude1 = lat1 * Math.PI / 180; // latitude and longitude in radians
        double latitude2 = lat2 * Math.PI / 180;
        double deltaLatitude = (lat2 - lat1) * Math.PI / 180;
        double deltaLongitude = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2) +
                Math.cos(latitude1) * Math.cos(latitude2) *
                        Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (r * c) / 1000 ; // r is earth’s radius
    }
}

