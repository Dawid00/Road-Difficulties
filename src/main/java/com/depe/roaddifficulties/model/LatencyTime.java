package com.depe.roaddifficulties.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class LatencyTime {
    private String direction;
    private String timeFrom;
    private String timeBy;
    private String fromMondayToFriday;
    private String saturday;
    private String sunday;
}
