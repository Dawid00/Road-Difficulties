package com.depe.roaddifficulties.traffic_difficulty;


import lombok.Builder;

@Builder
class Location {
    private double geoLat;
    private double geoLong;

    public double getGeoLat() {
        return geoLat;
    }
    public double getGeoLong() {
        return geoLong;
    }
}
