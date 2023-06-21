package com.example.laba6;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class MapActivity extends Activity {
    private MapView mapview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_info);

        Place place = new Place();

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            place = (Place)extras.getParcelable("data");
        }


        TextView name = (TextView)findViewById(R.id.mainName);
        TextView info = (TextView)findViewById(R.id.mainInfo);
        name.setText(place.getName());
        info.setText(place.getInfo());

        MapKitFactory.initialize(this);
        mapview = (MapView)findViewById(R.id.Map);
        mapview.getMap().move(
                new CameraPosition(place.getPoint(), 17.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        mapview.getMap().getMapObjects().addPlacemark(place.getPoint());

    }
    @Override
    protected void onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
}
