package com.depe.roaddifficulties.service;

import com.depe.roaddifficulties.exceptions.WrongParamException;
import com.depe.roaddifficulties.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;

@Service
public class RoadDifficultiesService {

    private final GddkiaApiClient gddkiaApiClient;

    public RoadDifficultiesService(GddkiaApiClient gddkiaApiClient) {
        this.gddkiaApiClient = gddkiaApiClient;
    }

    public Results getResults() {
        return gddkiaApiClient.getResults();
    }

    public Results getResultsByVoivodeship(String queryVoivodeship) {
        Voivodeship voivodeship = getVoivodeshipFromString(queryVoivodeship);
        Results results = gddkiaApiClient.getResults();
        List<TrafficDifficulty> trafficDifficulties = results.getTrafficDifficulties().stream()
                .filter(trafficDifficulty -> trafficDifficulty.getVoivodeship().equals(voivodeship.getName()))
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

    public Results getResultsByRoad(String road) {
        Results results = gddkiaApiClient.getResults();
        List<TrafficDifficulty> trafficDifficulties = results.getTrafficDifficulties().stream()
                .filter(trafficDifficulty -> trafficDifficulty.getRoad().equals(road.toUpperCase(Locale.ROOT)))
                .toList();
        return new Results(results.getDate(), trafficDifficulties);
    }

    public Results getResultsByDistance(Double geoLat, Double geoLong, Double distance) {
        Location location = Location.builder().geoLat(geoLat)
                .geoLong(geoLong)
                .build();
        Results results = gddkiaApiClient.getResults();
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
        return (r * c) ; // r is earth’s radius
    }
}

