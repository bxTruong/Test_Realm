package com.example.test_realm.realm;

import android.content.Context;

import com.example.test_realm.model.Planet;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmPlanet {

    public io.realm.Realm myRealm;

    public void create(Context context) {
        myRealm.init(context);

        myRealm = Realm.getDefaultInstance();
    }

    public void insert(final int id, final String name) {
        myRealm.beginTransaction();
        final Planet planet = myRealm.createObject(Planet.class);
        planet.setId(id);
        planet.setName(name);
        myRealm.commitTransaction();
    }

    public List<Planet> showAll() {
        RealmResults<Planet> results = myRealm.where(Planet.class).findAll();
        return results;
    }
}
