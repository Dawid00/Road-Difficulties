package com.depe.roaddifficulties.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Road {
    private String name;
    private Double km;
}
