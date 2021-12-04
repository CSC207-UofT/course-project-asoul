package com.example.projectasoulandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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
                Intent intent = null;
                intent = new Intent(ChangeUserInfoActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        Button mbChangeUserInfo = findViewById(R.id.cui_cuib);
        //TODO: change user info

        Button mbBack = findViewById(R.id.cui_back);
        //TODO: back to the front page

    }
}