package com.example.test_realm.realm;

import android.content.Context;

import com.example.test_realm.model.Country;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmCountry {

    public io.realm.Realm myRealm;

    public void create(Context context) {
        myRealm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("country.realm")
                .build();
        Realm.setDefaultConfiguration(config);
        myRealm = Realm.getDefaultInstance();
    }

    public void insert(final int code, final String name, final long population) {
        myRealm.beginTransaction();
        final Country country = myRealm.createObject(Country.class);
        country.setCode(code);
        country.setName(name);
        country.setPopulation(population);
        myRealm.commitTransaction();
    }

    public void update(int code, String name, long population) {
        myRealm.beginTransaction();
        RealmResults<Country> result = myRealm.where(Country.class)
                .equalTo("code", code)
                .findAll();
        for (Country country : result) {
            country.setName(name);
            country.setPopulation(population);
        }
        myRealm.commitTransaction();

    }

    public void delete(int code) {
        final RealmResults<Country> result = myRealm.where(Country.class)
                .equalTo("code", code)
                .findAll();

        myRealm.executeTransaction(new io.realm.Realm.Transaction() {
            @Override
            public void execute(io.realm.Realm realm) {
                result.deleteFirstFromRealm();
            }
        });
    }

    public List<Country> showAll() {
        RealmResults<Country> results = myRealm.where(Country.class).findAll();
        return results;
    }

    public boolean checkCode(int code) {
        final RealmResults<Country> result = myRealm.where(Country.class)
                .equalTo("code", code)
                .findAll();
        if (!result.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<Country> equalTo(String name, long population) {
        RealmResults<Country> results = myRealm.where(Country.class)
                .equalTo("name", name)
                .and()
                .equalTo("population", population)
                .findAll();
        return results;
    }

    public List<Country> contains(String name) {
        RealmResults<Country> results = myRealm.where(Country.class)
                .contains("name", name)
                .findAll();
        return results;
    }

    public List<Country> like(String name) {
        RealmResults<Country> results = myRealm.where(Country.class)
                .like("name", "*" + name + "*")
                .findAll();
        return results;
    }

    public List<Country> sort() {
        // ASC (thấp -> cao)
        RealmResults<Country> result = myRealm.where(Country.class).sort("population").findAll();
        //result = result.sort("age", Sort.DESCENDING); (DES) (cao -> thấp)
        return result;
    }

    public List<Country> between(long population1, long population2) {
        RealmResults<Country> result = myRealm.where(Country.class)
                .between("population", population1, population2)
                .findAll();
        return result;
    }

    public List<Country> distinct() {
        RealmResults<Country> result = myRealm.where(Country.class).distinct("name").findAll();
        return result;
    }

//    protected void readJson() {
//        myRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                try {
//                    InputStream is = new FileInputStream("D:/FPTPOLYTECHNIC/AndoridProject/Test Realm/app/src/main/res/raw/country.json");
//                    realm.createAllFromJson(Country.class, is);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }

}
