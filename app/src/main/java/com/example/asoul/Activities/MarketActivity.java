package com.example.asoul.Activities;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;


public class MarketActivity extends AppCompatActivity {
    Button switchToUserInfo;
    Button switchToUserEdit;
    Button switchToTruckEdit;
    Button switchToExit;
    Button switchToYellow;
    Button switchToBlue;
    Button switchToSushi;
    Button switchToMama;
    Button switchToIdeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);


        switchToUserInfo = findViewById(R.id.Userinfo);
        switchToUserInfo.setOnClickListener(v -> setSwitchToUserInfoActivity());

        switchToUserEdit = findViewById(R.id.UserEdit);
        switchToUserEdit.setOnClickListener(v -> setSwitchToUserEditActivity());

        switchToTruckEdit = findViewById(R.id.EditTruck);
        switchToTruckEdit.setOnClickListener(v -> setSwitchToTruckEdit());

        switchToExit = findViewById(R.id.Exit);
        switchToExit.setOnClickListener(v -> setSwitchToExit());

        switchToBlue = findViewById(R.id.blue);
        switchToBlue.setOnClickListener(v -> setSwitchToBlue());

        switchToYellow = findViewById(R.id.yellow);
        switchToYellow.setOnClickListener(v -> setSwitchToYellow());

        switchToSushi = findViewById(R.id.sushi);
        switchToSushi.setOnClickListener(v -> setSwitchToSushi());

        switchToIdeal = findViewById(R.id.ideal);
        switchToIdeal.setOnClickListener(v -> setSwitchToIdeal());

        switchToMama = findViewById(R.id.mama);
        switchToMama.setOnClickListener(v -> setSwitchToMama());
    }

    private void setSwitchToUserInfoActivity() {
        Intent switchToUserActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchToUserActivityIntent);
    }

    private void setSwitchToExit() {
        Intent switchToExitIntent = new Intent(this, LoginActivity.class);
        startActivity(switchToExitIntent);
    }

    private void setSwitchToUserEditActivity() {
        Intent switchToUserEditActivityIntent = new Intent(this, ChangeUserInfoActivity.class);
        startActivity(switchToUserEditActivityIntent);
    }

    private void setSwitchToTruckEdit() {
        Intent switchToTruckEditActivityIntent = new Intent(this, ChangeTruckInfoActivity.class);
        startActivity(switchToTruckEditActivityIntent);
    }

    private void setSwitchToBlue() {
        Intent switchToBlueActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToBlueActivityIntent);
    }

    private void setSwitchToYellow() {
        Intent switchToYellowActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToYellowActivityIntent);
    }

    private void setSwitchToSushi() {
        Intent switchToSushiActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToSushiActivityIntent);
    }

    private void setSwitchToMama() {
        Intent switchToMamaActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToMamaActivityIntent);
    }

    private void setSwitchToIdeal() {
        Intent switchToIdealActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToIdealActivityIntent);
    }
}