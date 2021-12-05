package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

public class ChangeUserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);

        // Define the function of changePassword Button
        Button mbChangePassword = findViewById(R.id.cui_cpb);
        mbChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeUserInfoActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        Button mbChangeUserInfo = findViewById(R.id.cui_cuib);
        //TODO: change user info

        Button mbBack = findViewById(R.id.cui_back);
        mbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}