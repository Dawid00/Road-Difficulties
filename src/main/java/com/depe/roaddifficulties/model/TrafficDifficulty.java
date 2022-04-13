package com.depe.roaddifficulties.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@ToString
@Builder
public class TrafficDifficulty {

    private String difficultyType;
    private String name;
    private Voivodeship voivodeship;
    private Road road;
    private double length;
    private Location location;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String roundAbout;
    private Type type;
    private String effects;
    private String liftingCapacityLimit;
    private String pressureCapacityLimit;
    private double widthLimit;
    private int speedLimit;
    private boolean isAlternatingTraffic;
    private boolean isTrafficLights;
    private boolean isBridgeFailure;
    private boolean isTwoWay;
    private boolean isClosedRoad;
    private Latency latency;
    private String  horizontalExtremeLimit;
    private String verticalExtremeLimit;

}



