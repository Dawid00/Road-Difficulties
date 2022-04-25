package com.depe.roaddifficulties.traffic_difficulty;

import lombok.Builder;

@Builder
class Latency {
    private LatencyTime latencyTime;

    public Latency() {}

    public Latency(LatencyTime latencyTime) {
        this.latencyTime = latencyTime;
    }

    public LatencyTime getLatencyTime() {
        return latencyTime;
    }
}