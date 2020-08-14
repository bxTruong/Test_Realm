package com.example.test_realm.model;


import io.realm.RealmList;
import io.realm.RealmObject;

public class Country extends RealmObject {
    //    @PrimaryKey
    private int code;

    private String name;
    private long population;

    private RealmList<Planet> planetRealmList = new RealmList<>();

    public Country() {
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public RealmList<Planet> getPlanetRealmList() {
        return planetRealmList;
    }

    public void setPlanetRealmList(RealmList<Planet> planetRealmList) {
        this.planetRealmList = planetRealmList;
    }
}
