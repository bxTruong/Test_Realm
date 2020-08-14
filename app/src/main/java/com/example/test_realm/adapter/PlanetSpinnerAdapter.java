package com.example.test_realm.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.test_realm.R;
import com.example.test_realm.model.Planet;

import java.util.List;

public class PlanetSpinnerAdapter implements SpinnerAdapter {
    private List<Planet> planetList;
    private Context context;

    public PlanetSpinnerAdapter(List<Planet> planetList, Context context) {
        this.planetList = planetList;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.drop_row, parent, false);
        TextView tvSpinner1 = convertView.findViewById(R.id.tvSpinner1);
        tvSpinner1.setText(planetList.get(position).getName());
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.selectedview_row, parent, false);
        TextView tvSpinner1 = convertView.findViewById(R.id.tvSpinner1);
        tvSpinner1.setText(planetList.get(position).getName());
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    //Chuyển về số hàng spinner hiển thị
    @Override
    public int getCount() {
        return planetList.size();
    }

    @Override
    public Object getItem(int position) {
        return planetList.get(position);
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
