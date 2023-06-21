package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextEvent(View v){
        EditText name = (EditText) findViewById(R.id.Name);
        EditText secName = (EditText)findViewById(R.id.secName);
        EditText card = (EditText)findViewById(R.id.email);

        if (!name.getText().toString().matches("") && !secName.getText().toString().matches("") && !card.getText().toString().matches("")) {
            if (!isNumberCorrect(card)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Внимание!")
                        .setMessage("Вы ввели некорректный номер карты")
                        .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
                return;
            }
            Intent i = new Intent(MainActivity.this, ListActivity.class);
            String str = ((EditText) findViewById(R.id.Name)).getText().toString();
            i.putExtra("name", ((EditText) findViewById(R.id.Name)).getText().toString());
            startActivity(i);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Внимание!")
                    .setMessage("Вы не ввели все нужные данные")
                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }
    }
    public static boolean isNumberCorrect(EditText num) {
        String number = num.getText().toString();
        return number.length() == 16 && isNumeric(number) && isValidLuhn(number);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidLuhn(String number) {
        int sum = Character.getNumericValue(number.charAt(number.length() - 1));
        int parity = number.length() % 2;
        for (int i = number.length() - 2; i >= 0; i--) {
            int summand = Character.getNumericValue(number.charAt(i));
            if (i % 2 == parity) {
                int product = summand * 2;
                summand = (product > 9) ? (product - 9) : product;
            }
            sum += summand;
        }
        return (sum % 10) == 0;
    }
}