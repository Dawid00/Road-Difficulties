package com.depe.roaddifficulties.traffic_difficulty;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
class Results {
    private LocalDateTime date;
    private List<TrafficDifficulty> trafficDifficulties;

    Results() {
    }

    Results(LocalDateTime date, List<TrafficDifficulty> trafficDifficulties) {
        this.date = date;
        this.trafficDifficulties = trafficDifficulties;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<TrafficDifficulty> getTrafficDifficulties() {
        return trafficDifficulties;
    }
}
