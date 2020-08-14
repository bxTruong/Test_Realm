package com.example.test_realm;

import android.util.JsonWriter;

import com.example.test_realm.model.Country;

import java.io.IOException;
import java.io.Writer;

public class WriteJson {

    public static void writeJsonStream(Writer output, Country country ) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(output);

        jsonWriter.beginObject();

        jsonWriter.name("id").value(country.getCode());
        jsonWriter.name("name").value(country.getName());
        jsonWriter.name("population").value(country.getPopulation());

        jsonWriter.endObject();

        jsonWriter.endObject();
    }


}
