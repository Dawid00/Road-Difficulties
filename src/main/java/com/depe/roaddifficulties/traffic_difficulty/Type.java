package com.depe.roaddifficulties.traffic_difficulty;

import lombok.*;


@Builder
class Type {
    private String poz;

    public Type(String poz) {
        this.poz = poz;
    }

    public Type() {
    }

    public String getPoz() {
        return poz;
    }
}
