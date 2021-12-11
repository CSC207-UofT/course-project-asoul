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

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtruck);



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
                setSwitchToOrderInfo();
            }
        });

    }

    private void setSwitchToMarket(){
        Intent switchToBackMarketActivityIntent = new Intent(this, MarketActivity.class);
        startActivity(switchToBackMarketActivityIntent);
    }

    private void setSwitchToOrderInfo(){
        Intent switchToBackOrderActivityIntent = new Intent(this, OrderActivity.class);
        startActivity(switchToBackOrderActivityIntent);
    }
}