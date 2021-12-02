package com.example.projectasoulandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    Button switchToRegisterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TODO switchToUserInformationActivity

        switchToRegisterActivity = findViewById(R.id.btnRegisterPage);
        switchToRegisterActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switchRegisterActivities();
            }
        });
    }

    private void switchRegisterActivities() {
        Intent switchActivityIntent = new Intent(this, RegisterActivity.class);
        startActivity(switchActivityIntent);
    }
}