package com.depe.roaddifficulties.traffic_difficulty;

import lombok.Builder;

@Builder
class LatencyTime {

    private String direction;
    private String timeFrom;
    private String timeBy;
    private String fromMondayToFriday;
    private String saturday;
    private String sunday;

    public String getDirection() {
        return direction;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public String getTimeBy() {
        return timeBy;
    }

    public String getFromMondayToFriday() {
        return fromMondayToFriday;
    }

    public String getSaturday() {
        return saturday;
    }

    public String getSunday() {
        return sunday;
    }
}
