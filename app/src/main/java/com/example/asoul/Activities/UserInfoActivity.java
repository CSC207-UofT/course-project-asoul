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
    TextView accessError;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        key = GlobalVariables.getKey();
        username = GlobalVariables.getUsername();

        welcomeContent = findViewById(R.id.WelcomeContent);
        accBalance = findViewById(R.id.AccountBalance);
        phoneNum = findViewById(R.id.PhoneNumber);
        truckName = findViewById(R.id.TruckName);
        accessError = findViewById(R.id.accessError);

        try {
            welcomeContent.setText("Welcome, " + UserManager.getNickname(username, key));
            accBalance.setText("Account Balance is " + UserManager.getBalance(username, key));
            phoneNum.setText("Phone Number: " + UserManager.getPhoneNumber(username, key));
            truckName.setText("FoodTruck: " + UserManager.getTruckName(username, key));
        } catch (UnauthorizedAccessException e) {
            //
            accessError.setText("Unauthorized access to the content!");
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
        Intent switchActivityIntent = new Intent(this, OrderActivity.class);
        startActivity(switchActivityIntent);

    }





}