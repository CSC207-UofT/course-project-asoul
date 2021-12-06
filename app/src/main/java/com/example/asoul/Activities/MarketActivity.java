package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.asoul.R;
import exceptions.UnknownFoodTruckException;
import exceptions.UnknownSorterException;
import sorters.Sorter;
import sorters.SorterSimpleFactory;
import use_case.FoodTruckManager;

import java.util.ArrayList;
import java.util.HashMap;

public class MarketActivity extends AppCompatActivity {
    Button switchToUserInfo;
    Button switchToUserEdit;
    Button switchToTruckEdit;
    Button switchToExit;
    Button switchToBlue;
    Button switchToSushi;
    Button switchToBrown;
    Button switchToYellow;
    private HashMap<String, String> info;
    private HashMap<Integer, String> pointer;
    private String sorter;
    ConstraintLayout marketLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        switchToUserInfo = findViewById(R.id.Userinfo);
        switchToUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToUserInfoActivity();
            }
        });

        switchToUserEdit = findViewById(R.id.UserEdit);
        switchToUserEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToUserEditActivity();
            }
        });

        switchToTruckEdit = findViewById(R.id.EditTruck);
        switchToTruckEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToTruckEdit();
            }
        });

        switchToExit = findViewById(R.id.Exit);
        switchToExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToExit();
            }
        });

        switchToSushi = findViewById(R.id.goToSushi);
        switchToSushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToSushi();
            }
        });


        switchToBlue = findViewById(R.id.goToBlue);
        switchToBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToBlue();
            }
        });


        switchToBrown = findViewById(R.id.goToBrown);
        switchToBrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToBrown();
            }
        });


        switchToYellow = findViewById(R.id.goToYellow);
        switchToYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToYellow();
            }
        });

        info = new HashMap<>();
        pointer = new HashMap<>();
        sorter = "";
    }


    private void setSwitchToUserInfoActivity(){
        Intent switchToUserActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchToUserActivityIntent);
    }

    private void setSwitchToUserEditActivity(){
        Intent switchToUserEditActivityIntent = new Intent(this, ChangeUserInfoActivity.class);
        startActivity(switchToUserEditActivityIntent);
    }

    private void setSwitchToTruckEdit(){
        Intent switchToTruckEditActivityIntent = new Intent(this, ChangeTruckInfoActivity.class);
        startActivity(switchToTruckEditActivityIntent);
    }

    private void setSwitchToExit(){
        Intent switchToExitActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchToExitActivityIntent);
    }

    private void setSwitchToBlue(){

        Intent switchToBlueActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToBlueActivityIntent);
    }

    private void setSwitchToYellow(){
        Intent switchToYellowActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToYellowActivityIntent);
    }

    private void setSwitchToSushi(){
        Intent switchToSushiActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToSushiActivityIntent);
    }

    private void setSwitchToBrown(){
        Intent switchToBrownActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToBrownActivityIntent);
    }
}