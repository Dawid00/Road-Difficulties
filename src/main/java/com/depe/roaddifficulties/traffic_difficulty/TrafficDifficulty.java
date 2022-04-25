package com.depe.roaddifficulties.traffic_difficulty;

import lombok.Builder;
import java.time.LocalDateTime;


@Builder
class TrafficDifficulty {

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

    public String getDifficultyType() {
        return difficultyType;
    }

    public String getName() {
        return name;
    }

    public Voivodeship getVoivodeship() {
        return voivodeship;
    }

    public Road getRoad() {
        return road;
    }

    public double getLength() {
        return length;
    }

    public Location getLocation() {
        return location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public String getRoundAbout() {
        return roundAbout;
    }

    public Type getType() {
        return type;
    }

    public String getEffects() {
        return effects;
    }

    public String getLiftingCapacityLimit() {
        return liftingCapacityLimit;
    }

    public String getPressureCapacityLimit() {
        return pressureCapacityLimit;
    }

    public double getWidthLimit() {
        return widthLimit;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public boolean isAlternatingTraffic() {
        return isAlternatingTraffic;
    }

    public boolean isTrafficLights() {
        return isTrafficLights;
    }

    public boolean isBridgeFailure() {
        return isBridgeFailure;
    }

    public boolean isTwoWay() {
        return isTwoWay;
    }

    public boolean isClosedRoad() {
        return isClosedRoad;
    }

    public Latency getLatency() {
        return latency;
    }

    public String getHorizontalExtremeLimit() {
        return horizontalExtremeLimit;
    }

    public String getVerticalExtremeLimit() {
        return verticalExtremeLimit;
    }
}




