package com.example.laba6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceAdapter extends ArrayAdapter<Place> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Place> places;


    public PlaceAdapter(Context context, int resource, ArrayList<Place> sites){
        super(context, resource, sites);
        this.places = sites;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        @SuppressLint("ViewHolder") View view = inflater.inflate(this.layout, parent, false);
        TextView name = (TextView) view.findViewById(R.id.name);

        Place site = places.get(position);
        name.setText(site.getName());

        return view;
    }

}
