package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends Activity {

    Button clearBtn;
    Button okBtn;
    TextView moneyCounter;
    int money = 0;

    ListView list;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        clearBtn = findViewById(R.id.clearBtn);
        okBtn = findViewById(R.id.okBtn);
        moneyCounter = findViewById(R.id.MoneyText);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView username = findViewById(R.id.textView);
            username.setText(extras.getString("name"));
        }

        list = findViewById(R.id.listView);
        OrderAdapter orderAdapter = new OrderAdapter(this, R.layout.list_item,
                Order.getSends());
        list.setAdapter(orderAdapter);
    }


    public void okEvent(View view) {
        money += Order.total;
        TextView text = findViewById(R.id.MoneyText);
        String newline = "Выручка: " + money + " руб";
        text.setText(newline);

        CheckBox checkBox;
        for (int i = 0; i < list.getChildCount(); i++) {
            checkBox = (CheckBox) list.getChildAt(i).findViewById(R.id.checkBox);
            if (checkBox.isChecked()) {
                checkBox.setChecked(false);
                checkBox.setVisibility(View.GONE);
            }
        }
    }

    public void clearEvent(View view) {
        CheckBox checkBox;
        for (int i = 0; i < list.getChildCount(); i++) {
            checkBox = (CheckBox) list.getChildAt(i).findViewById(R.id.checkBox);
            checkBox.setChecked(false);
        }
        Order.total = 0;
    }

    public void withdrawEvent(View view) {
        ListActivity context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (money < 2000) {
            builder.setTitle("ВНИМАНИЕ! ДЕНЬГИ!!!")
                    .setMessage("Вы не можете вывести сумму, меньше чем 2000 руб.\nНазад к работе!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
        } else {
            builder.setTitle("ВНИМАНИЕ! ДЕНЬГИ!!!")
                    .setMessage("Вы можете вывести заработанные деньги с комиссией 10%. (" + money * 0.9 + ")\nПродолжить?")
                    .setPositiveButton("я солгласен!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            TextView text = findViewById(R.id.MoneyText);
                            String newline = "Выручка: 0 руб";
                            text.setText(newline);
                            androidx.appcompat.app.AlertDialog.Builder builder2 = new androidx.appcompat.app.AlertDialog.Builder(context);
                            builder2.setTitle("Успешно")
                                    .setMessage("Вы вывели " + money * 0.9 + " руб")
                                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            builder2.show();
                            money = 0;
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("нет, спасибо", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
        }
        builder.show();
    }
}


// номер счета - логин, номер карты. проверить валидацию в интернете
// можно вывести с комиссией 10%, и есть минимальная сумма, с которой можно вывести
// +лист адаптер