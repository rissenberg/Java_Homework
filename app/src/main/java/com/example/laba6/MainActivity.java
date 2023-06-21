package com.example.laba6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yandex.mapkit.MapKitFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapKitFactory.setApiKey("6cd5643e-1fce-4644-9df5-7c797dcb7ad3");
    }

    public void nextEvent(View v){
        EditText name = (EditText) findViewById(R.id.Name);
        EditText secName = (EditText)findViewById(R.id.secName);
        EditText email = (EditText)findViewById(R.id.email);

        if (!name.getText().toString().matches("") && !secName.getText().toString().matches("") && !email.getText().toString().matches("")) {
            Intent i = new Intent(MainActivity.this, ListActivity.class);
            String str = ((EditText) findViewById(R.id.Name)).getText().toString();
            i.putExtra("name", ((EditText) findViewById(R.id.Name)).getText().toString());
            startActivity(i);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Внимание!")
                    .setMessage("Вы не ввели все нужные данные")
                    .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }
    }
}

// создать сущность гостинница отель - несклолько, при выборе достопр прога будет на карте отрисовывать 3 ближайшие отеля
// постараться узнать как работают