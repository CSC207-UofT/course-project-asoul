package com.example.asoul.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.asoul.R;

public class OrderHistoryActivity extends AppCompatActivity {
    Button switchToUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        switchToUserInfo = findViewById(R.id.btnBackToUserInfo);
        switchToUserInfo.setOnClickListener(v -> setSwitchToUserInfoActivity());
    }

    private void setSwitchToUserInfoActivity() {
        Intent switchToUserInfoActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchToUserInfoActivityIntent);
    }
}