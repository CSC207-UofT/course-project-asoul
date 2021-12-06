package com.example.asoul.Activities;

import android.text.method.CharacterPickerDialog;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.asoul.R;
import helper.GlobalVariables;

import java.util.ArrayList;

public class FoodTruckActivity extends AppCompatActivity {

    Button switchToMarket;
    Button switchToUserInfo;
    Button food0;
    Button food1;
    Button food2;
    Button food3;
    StringBuilder cartContent;
    ArrayList<String> menuInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        GlobalVariables globalVariables = (GlobalVariables) this.getApplication();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtruck);

        ArrayList<Button> btnList = new ArrayList<>();
        btnList.add(food0);
        btnList.add(food1);
        btnList.add(food2);
        btnList.add(food3);
        food0 = findViewById(R.id.food0);
        food1 = findViewById(R.id.food1);
        food2 = findViewById(R.id.food2);
        food3 = findViewById(R.id.food3);




        switchToMarket = findViewById(R.id.ViewMarket);
        switchToMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToMarket();
            }
        });

        switchToUserInfo = findViewById(R.id.Checkout);
        switchToUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToUserInfo();
            }
        });

        food0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartContent.append(menuInfo.get(0));
            }
        });

        food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartContent.append(menuInfo.get(1));
            }
        });

        food2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartContent.append(menuInfo.get(2));
            }
        });


        food3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartContent.append(menuInfo.get(3));
            }
        });

        for (int i = 0; i < 4; i++){
            btnList.get(i).setText(menuInfo.get(i));
        }
    }

    private void setSwitchToMarket(){
        Intent switchToMarketActivityIntent = new Intent(this, MarketActivity.class);
        startActivity(switchToMarketActivityIntent);
    }

    private void setSwitchToUserInfo(){
        Intent switchToUserInfoActivityIntent = new Intent(this, OrderActivity.class);
        startActivity(switchToUserInfoActivityIntent);
    }
}