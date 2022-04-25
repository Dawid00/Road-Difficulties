package com.depe.roaddifficulties.traffic_difficulty;

import lombok.Builder;

@Builder
class Road {
    private String name;
    private Double km;

    public String getName() {
        return name;
    }

    public Double getKm() {
        return km;
    }
}


