package com.depe.roaddifficulties.model;

public enum Voivodeship {

    LUBELSKIE("lubelskie"), LODZKIE("łódzkie"), LUBUSKIE("lubuskie"), SLASKIE("śląskie"),
    KUJAWSKO_POMORSKIE("kujawsko-pomorskie"), DOLNOSLASKIE("dolnośląskie"), MALOPOLSKIE("małopolskie"),
    OPOLSKIE("opolskie"), PODKARPACKIE("podkarpackie"), PODLASKIE("podlaskie"), POMORSKIE("pomorskie"),
    ZACHODNIOPOMORSKIE("zachodniopomorskie"), WIELKOPOLSKIE("wielkopolskie"), SWIETOKRZYSKIE("świętokrzyskie"),
    WARMINSKO_MAZURSKIE("warmińsko-mazurskie"), MAZOWIECKIE("mazowieckie");

    final String name;

    Voivodeship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
