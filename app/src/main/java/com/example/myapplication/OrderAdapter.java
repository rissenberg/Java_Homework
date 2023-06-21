package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Order> orders;

    public OrderAdapter(Context context, int resource, ArrayList<Order> orders){
        super(context, resource, orders);
        this.orders = orders;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        @SuppressLint("ViewHolder") View view = inflater.inflate(this.layout, parent, false);
        TextView firmView = (TextView) view.findViewById(R.id.firm);
        TextView sendView = (TextView) view.findViewById(R.id.send);
        TextView aoView = (TextView) view.findViewById(R.id.a_o);
        TextView akView = (TextView) view.findViewById(R.id.a_k);
        TextView priceView = (TextView) view.findViewById(R.id.price);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        Order order = orders.get(position);

        firmView.setText(order.getCorp().getName());
        sendView.setText(order.getPac().getType().toString());
        aoView.setText(order.getCorp().getAdr());
        akView.setText(order.getDest());
        priceView.setText(Integer.toString(order.getCost()));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

                    if (isChecked) {
                        Order.total += order.getCost();
                    } else {
                        Order.total -= order.getCost();
                    }
                }

        });

        return view;
    }

}
