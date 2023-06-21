package com.example.laba6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.yandex.mapkit.geometry.Point;

import java.util.ArrayList;


public class ListActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            ((TextView)findViewById(R.id.hellolay2)).setText("Привет, " + extras.getString("name") +"!");
        }
        
        ListView list = (ListView) findViewById(R.id.listView);
        PlaceAdapter sitesAdapter = new PlaceAdapter(this, R.layout.list_item, Place.getPlaces());
        list.setAdapter(sitesAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Place selected = (Place) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListActivity.this, MapActivity.class);
                intent.putExtra("data", selected);
                startActivity(intent);
            }
        };
        list.setOnItemClickListener(itemListener);


    }
}
