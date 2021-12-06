package com.example.asoul.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asoul.R;

public class OrderHistoryActivity extends AppCompatActivity {
    Button switchToUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        switchToUserInfo = findViewById(R.id.btnBackToUserInfo);
        switchToUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToUserInfoActivity();
            }
        });
    }

    private void setSwitchToUserInfoActivity(){
        Intent switchToUserInfoActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchToUserInfoActivityIntent);
    }
}