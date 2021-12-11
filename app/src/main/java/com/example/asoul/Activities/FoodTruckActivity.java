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

    Button switchBackToMarket;
    Button switchBackToUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtruck);

        switchBackToMarket = findViewById(R.id.ViewMarket);
        switchBackToMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchBackToMarket();
            }
        });

        switchBackToUserInfo = findViewById(R.id.Checkout);
        switchBackToUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchBackToOrderInfo();
            }
        });

    }

    private void setSwitchBackToMarket(){
        Intent switchToBackMarketActivityIntent = new Intent(this, MarketActivity.class);
        startActivity(switchToBackMarketActivityIntent);
    }

    private void setSwitchBackToOrderInfo(){
        Intent switchToBackOrderActivityIntent = new Intent(this, OrderActivity.class);
        startActivity(switchToBackOrderActivityIntent);
    }
}