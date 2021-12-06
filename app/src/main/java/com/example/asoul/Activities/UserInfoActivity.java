package com.example.asoul.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import exceptions.UnauthorizedAccessException;
import helper.GlobalVariables;
import use_case.UserManager;

import java.io.IOException;

public class UserInfoActivity extends AppCompatActivity {

    GlobalVariables globalVariables = (GlobalVariables) this.getApplication();

    Button switchToMarketActivity;
    Button switchToChangeUserInfoActivity;
    Button switchToChangeTruckInfoActivity;
    Button switchToOrderHistoryActivity;
    String key;
    String username;
    TextView welcomeContent;
    TextView accBalance;
    TextView phoneNum;
    TextView truckName;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        key = GlobalVariables.getKey();
        username = GlobalVariables.getUsername();

        welcomeContent = findViewById(R.id.WelcomeContent);
        try {
            welcomeContent.setText("Welcome, " + UserManager.getNickname(username, key));
        } catch (UnauthorizedAccessException e) {
            //
            e.printStackTrace();
        }
        accBalance = findViewById(R.id.AccountBalance);
        try {
            accBalance.setText("Account Balance: " + UserManager.getBalance(username, key));
        } catch (UnauthorizedAccessException e) {
            e.printStackTrace();
        }
        phoneNum = findViewById(R.id.PhoneNumber);
        try {
            phoneNum.setText("Phone Number: " + UserManager.getPhoneNumber(username, key));
        } catch (UnauthorizedAccessException e) {
            e.printStackTrace();
        }
        truckName = findViewById(R.id.TruckName);
        try {
            accBalance.setText("FoodTruck: " + UserManager.getTruckName(username, key));
        } catch (UnauthorizedAccessException e) {
            e.printStackTrace();
        }


        switchToMarketActivity = findViewById(R.id.btnViewMarket);
        switchToMarketActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToMarketActivity();
            }
        });

        switchToChangeUserInfoActivity = findViewById(R.id.btnEditUserInfo);
        switchToChangeUserInfoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToEditUserInfoActivity();
            }
        });

        switchToChangeTruckInfoActivity = findViewById(R.id.btnEditTruckInfo);
        switchToChangeTruckInfoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToEditTruckInfoActivity();
            }
        });

        switchToOrderHistoryActivity = findViewById(R.id.btnOrderHistory);
        switchToOrderHistoryActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToViewOrderHistoryActivity();
            }
        });

        }
        private void setSwitchToMarketActivity(){
        Intent switchActivityIntent = new Intent(this, MarketActivity.class);
        startActivity(switchActivityIntent);

    }
        private void setSwitchToEditUserInfoActivity(){
        Intent switchActivityIntent = new Intent(this, ChangeUserInfoActivity.class);
        startActivity(switchActivityIntent);

    }
        private void setSwitchToEditTruckInfoActivity(){
        Intent switchActivityIntent = new Intent(this, ChangeTruckInfoActivity.class);
        startActivity(switchActivityIntent);

    }
        private void setSwitchToViewOrderHistoryActivity(){
        Intent switchActivityIntent = new Intent(this, OrderHistoryActivity.class);
        startActivity(switchActivityIntent);

    }





}