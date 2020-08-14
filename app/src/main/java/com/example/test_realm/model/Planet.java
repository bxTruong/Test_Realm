package com.example.test_realm.model;

import io.realm.RealmObject;

public class Planet extends RealmObject {

    private int id;
    private String name;
    private Country country;

    public Planet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
