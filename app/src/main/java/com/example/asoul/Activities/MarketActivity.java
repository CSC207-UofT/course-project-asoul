package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

public class MarketActivity extends AppCompatActivity {
    Button switchToUserInfo;
    Button switchToUserEdit;
    Button switchToTruckEdit;
    Button switchToExit;

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
}