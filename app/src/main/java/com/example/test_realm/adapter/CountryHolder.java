package com.example.test_realm.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_realm.R;

public class CountryHolder extends RecyclerView.ViewHolder {
    public TextView tvCode;
    public TextView tvName;
    public TextView tvPopulation;
    public CountryHolder(@NonNull View itemView) {
        super(itemView);

        tvCode = itemView.findViewById(R.id.tvCode);
        tvName = itemView.findViewById(R.id.tvName);
        tvPopulation = itemView.findViewById(R.id.tvPopulation);

    }
}
