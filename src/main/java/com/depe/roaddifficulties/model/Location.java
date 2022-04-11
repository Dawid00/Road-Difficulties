package com.depe.roaddifficulties.model;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Location {
    private double geoLat;
    private double geoLong;
}
