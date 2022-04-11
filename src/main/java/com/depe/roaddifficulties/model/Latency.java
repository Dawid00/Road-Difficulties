package com.depe.roaddifficulties.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Latency {
    private LatencyTime latencyTime;
}