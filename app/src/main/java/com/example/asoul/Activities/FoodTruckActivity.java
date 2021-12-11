package com.example.asoul.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.asoul.R;

public class FoodTruckActivity extends AppCompatActivity {

    Button switchBackToMarket;
    Button switchBackToUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtruck);

        switchBackToMarket = findViewById(R.id.ViewMarket);
        switchBackToMarket.setOnClickListener(v -> setSwitchBackToMarket());

        switchBackToUserInfo = findViewById(R.id.Checkout);
        switchBackToUserInfo.setOnClickListener(v -> setSwitchBackToOrderInfo());

    }

    private void setSwitchBackToMarket() {
        Intent switchToBackMarketActivityIntent = new Intent(this, MarketActivity.class);
        startActivity(switchToBackMarketActivityIntent);
    }

    private void setSwitchBackToOrderInfo() {
        Intent switchToBackOrderActivityIntent = new Intent(this, OrderActivity.class);
        startActivity(switchToBackOrderActivityIntent);
    }
}