package com.example.test_realm.acitivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_realm.R;
import com.example.test_realm.adapter.CountryAdapter;
import com.example.test_realm.adapter.PlanetSpinnerAdapter;
import com.example.test_realm.model.Country;
import com.example.test_realm.model.Planet;
import com.example.test_realm.realm.RealmCountry;
import com.example.test_realm.realm.RealmPlanet;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rcCountry;
    private List<Country> countryList;
    private EditText edtCode;
    private EditText edtName;
    private EditText edtPopulation;
    private Button btnOk;
    RealmCountry realmCountry;
    RealmPlanet realmPlanet;
    private String name;
    private int code;
    private long population;
    private Button btnFind;
    private AlertDialog alertDialog;
    private AppCompatSpinner spPlanet;
    private List<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realmCountry = new RealmCountry();
        realmPlanet = new RealmPlanet();
        findId();
        realmCountry.create(this);
        realmPlanet.create(this);
        countryList = realmCountry.showAll();
        setupRecyclerView();

        planetList = realmPlanet.showAll();
        realmPlanet.insert(0, "---Vui Lòng Chọn ---");

        PlanetSpinnerAdapter planetSpinnerAdapter = new PlanetSpinnerAdapter(planetList, this);
        spPlanet.setAdapter(planetSpinnerAdapter);

    }

    private void getData() {
        if (!edtCode.getText().toString().isEmpty() && !edtPopulation.getText().toString().isEmpty() && !edtName.getText().toString().isEmpty()) {
            code = Integer.parseInt(edtCode.getText().toString().trim());
            name = edtName.getText().toString().trim();
            population = Long.parseLong(edtPopulation.getText().toString().trim());

            setupRecyclerView();
            if (realmCountry.checkCode(code)) {
                realmCountry.insert(code, name, population);
                clearForm();
            } else {
                clearForm();
                Toast.makeText(MainActivity.this, "Already have this id", Toast.LENGTH_SHORT).show();
            }
        } else {
            countryList = realmCountry.showAll();
            setupRecyclerView();
            Toast.makeText(this, "Do not blank", Toast.LENGTH_SHORT).show();
        }
    }

    private void findId() {
        rcCountry = findViewById(R.id.rcUser);
        edtCode = findViewById(R.id.edtCode);
        edtName = findViewById(R.id.edtName);
        edtPopulation = findViewById(R.id.edtPopulation);
        btnOk = findViewById(R.id.btnOk);
        spPlanet = findViewById(R.id.spPlanet);
    }

    private void setupRecyclerView() {
        CountryAdapter countryAdapter = new CountryAdapter(countryList, this);
        rcCountry.setAdapter(countryAdapter);
        LinearLayoutManager vertical = new LinearLayoutManager(this);
        rcCountry.setLayoutManager(vertical);
    }

    private void clearForm() {
        edtCode.setText("");
        edtName.setText("");
        edtPopulation.setText("");
    }

    public void addPlanet(View view) {
        openDialog("Add Planet");
    }

    public void addCountry(View view) {
        getData();
    }

    public void equalTo(View view) {
        openDialog("EqualTo");
    }

    public void contains(View view) {
        openDialog("Contains");
    }

    public void like(View view) {
        openDialog("Like");
    }

    public void sort(View view) {
        countryList = realmCountry.sort();
        setupRecyclerView();
    }

    public void between(View view) {
        openDialog("Between");
    }

    public void distinct(View view) {
        // Biết được bao nhiêu tên trùng nhau
        countryList = realmCountry.distinct();
        setupRecyclerView();
    }

    public void greaterThan(View view) {
    }

    public void lessThan(View view) {
    }

    public void openDialog(String checkOpen) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialog = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        builder.setTitle(checkOpen);
        builder.setView(dialog);

        final EditText edtCodeDl;
        final EditText edtNameDl;
        final EditText edtPopulation;
        Button btnOk;

        edtCodeDl = dialog.findViewById(R.id.edtCodeDl);
        edtNameDl = dialog.findViewById(R.id.edtNameDl);
        edtPopulation = dialog.findViewById(R.id.edtPopulationDl);
        btnOk = dialog.findViewById(R.id.btnOkDl);

        if (checkOpen.equalsIgnoreCase("EqualTo")) {
            edtCodeDl.setVisibility(View.GONE);
            btnOk.setText("Ok");
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = edtNameDl.getText().toString();
                    long population = Long.parseLong(edtPopulation.getText().toString().trim());
                    if (!name.isEmpty() && !edtPopulation.getText().toString().isEmpty()) {
                        countryList = realmCountry.equalTo(name, population);
                        if (countryList.size() > 0) {
                            setupRecyclerView();
                            alertDialog.cancel();
                        } else {
                            Toast.makeText(MainActivity.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Do not blank", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else if (checkOpen.equalsIgnoreCase("Contains")) {
            edtCodeDl.setVisibility(View.GONE);
            edtPopulation.setVisibility(View.GONE);
            btnOk.setText("Ok");
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = edtNameDl.getText().toString();
                    if (!name.isEmpty()) {
                        countryList = realmCountry.contains(name);
                        if (countryList.size() > 0) {
                            alertDialog.cancel();
                            setupRecyclerView();
                        } else {
                            Toast.makeText(MainActivity.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Do not blank", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (checkOpen.equalsIgnoreCase("between")) {
            edtCodeDl.setVisibility(View.GONE);
            edtNameDl.setHint("population 1");
            edtPopulation.setHint(" population 2");
            btnOk.setText("Ok");
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long population1 = Long.parseLong(edtNameDl.getText().toString());
                    long population2 = Long.parseLong(edtPopulation.getText().toString());
                    if (!edtName.getText().toString().isEmpty()
                            && !edtPopulation.getText().toString().isEmpty()
                            && population1 <= population2) {
                        countryList = realmCountry.between(population1, population2);
                        if (countryList.size() > 0) {
                            alertDialog.cancel();
                            setupRecyclerView();
                        } else {
                            Toast.makeText(MainActivity.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Do not blank, popualtion1 <= population 2", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (checkOpen.equalsIgnoreCase("like")) {
            edtCodeDl.setVisibility(View.GONE);
            edtNameDl.setHint("population 1");
            edtPopulation.setVisibility(View.GONE);
            btnOk.setText("Ok");
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = edtNameDl.getText().toString().trim();
                    if (!name.isEmpty()) {
                        countryList = realmCountry.like(name);
                        if (countryList.size() > 0) {
                            alertDialog.cancel();
                            setupRecyclerView();
                        } else {
                            Toast.makeText(MainActivity.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Do not blank, popualtion1 <= population 2", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (checkOpen.equalsIgnoreCase("Add Planet")) {
            edtCodeDl.setHint("Id");
            edtCodeDl.setEnabled(true);
            edtPopulation.setVisibility(View.GONE);
            btnOk.setText("Ok");
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = Integer.parseInt(edtCodeDl.getText().toString().trim());
                    String name = edtNameDl.getText().toString().trim();
                    if (!edtNameDl.getText().toString().isEmpty()
                            && !edtCodeDl.getText().toString().isEmpty()) {
                        realmPlanet.insert(id, name);
                        alertDialog.cancel();
                    } else {
                        Toast.makeText(MainActivity.this, "Do not blank", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        builder.create();
        alertDialog = builder.show();
    }


    //    private void writeJson() {
//        try {
//            StringWriter output = new StringWriter();
//
//            Country country = WriteJson.createCountry();
//
//            WriteJson.writeJsonStream(output, country);
//
//        } catch(Exception e)  {
//            e.printStackTrace();
//        }
//    }
}