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

        switchToBlue = findViewById(R.id.blue);
        switchToBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToBlue();
            }
        });

        switchToYellow = findViewById(R.id.yellow);
        switchToYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToYellow();
            }
        });

        switchToSushi = findViewById(R.id.sushi);
        switchToSushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToSushi();
            }
        });

        switchToIdeal = findViewById(R.id.ideal);
        switchToIdeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToIdeal();
            }
        });

        switchToMama = findViewById(R.id.mama);
        switchToMama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToMama();
            }
        });
    }

    private void setSwitchToUserInfoActivity(){
        Intent switchToUserActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchToUserActivityIntent);
    }

    private void setSwitchToExit(){
        Intent switchToExitIntent = new Intent(this, LoginActivity.class);
        startActivity(switchToExitIntent);
    }

    private void setSwitchToUserEditActivity(){
        Intent switchToUserEditActivityIntent = new Intent(this, ChangeUserInfoActivity.class);
        startActivity(switchToUserEditActivityIntent);
    }

    private void setSwitchToTruckEdit(){
        Intent switchToTruckEditActivityIntent = new Intent(this, ChangeTruckInfoActivity.class);
        startActivity(switchToTruckEditActivityIntent);
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

    private void setSwitchToMama(){
        Intent switchToMamaActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToMamaActivityIntent);
    }

    private void setSwitchToIdeal(){
        Intent switchToIdealActivityIntent = new Intent(this, FoodTruckActivity.class);
        startActivity(switchToIdealActivityIntent);
    }
}