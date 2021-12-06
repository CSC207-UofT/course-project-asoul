package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import use_case.UserManager;

public class UserInfoActivity extends AppCompatActivity {

    Button switchToMarketActivity;
    Button switchToChangeUserInfoActivity;
    Button switchToChangeTruckInfoActivity;
    Button switchToOrderHistoryActivity;
    String Key;
    String username;
    TextView welcomeContent;
    TextView accBalance;
    TextView phoneNum;
    TextView truckName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);


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