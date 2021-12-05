package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

public class LoginActivity extends AppCompatActivity {

    Button switchToRegisterActivity;
    Button switchToUserInfoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        switchToUserInfoActivity = findViewById(R.id.btnSignIn);
        switchToUserInfoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserInfoActivities();
            }
        });

        switchToRegisterActivity = findViewById(R.id.btnRegisterPage);
        switchToRegisterActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switchRegisterActivities();
            }
        });
    }

    private void switchToUserInfoActivities() {
        Intent switchActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchRegisterActivities() {
        Intent switchActivityIntent = new Intent(this, RegisterActivity.class);
        startActivity(switchActivityIntent);
    }
}
