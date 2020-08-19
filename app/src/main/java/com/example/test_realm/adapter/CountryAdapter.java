package com.example.test_realm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_realm.OnItemClick;
import com.example.test_realm.model.Country;
import com.example.test_realm.R;
import com.example.test_realm.realm.RealmCountry;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryHolder> {

    private List<Country> countryList;
    private Context context;
    private RealmCountry realmCountry;
    private AlertDialog alertDialog;
    private OnItemClick callBack;

    public CountryAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country, parent, false);
        CountryHolder countryHolder = new CountryHolder(view);
        return countryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, final int position) {
        final Country country = countryList.get(position);
        realmCountry = new RealmCountry();
        realmCountry.create(context);
        holder.tvCode.setText(country.getCode() + "");
        holder.tvName.setText(country.getName());
        holder.tvPopulation.setText(country.getPopulation() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(country);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                realmCountry.delete(country.getCode());
                notifyDataSetChanged();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void openDialog(Country country) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialog = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        builder.setTitle("Update");
        builder.setView(dialog);

        final EditText edtCodeDl;
        final EditText edtName;
        final EditText edtPopulation;
        Button btnOk;

        edtCodeDl = dialog.findViewById(R.id.edtCodeDl);
        edtName = dialog.findViewById(R.id.edtNameDl);
        edtPopulation = dialog.findViewById(R.id.edtPopulationDl);
        btnOk = dialog.findViewById(R.id.btnOkDl);

        edtCodeDl.setText(country.getCode() + "");
        edtName.setText(country.getName());
        edtPopulation.setText(country.getPopulation() + "");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int code = Integer.parseInt(edtCodeDl.getText().toString().trim());
                String name = edtName.getText().toString();
                long population = Long.parseLong(edtPopulation.getText().toString().trim());
                realmCountry.update(code, name, population);
                alertDialog.cancel();
                notifyDataSetChanged();
            }
        });
        builder.create();
        alertDialog = builder.show();
    }
}

