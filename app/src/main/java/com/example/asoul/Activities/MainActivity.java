package com.example.asoul.Activities;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import use_case.UserManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    Button switchToLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            UserManager.saveUserDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switchToLoginActivity = findViewById(R.id.btnLoginPage);
        switchToLoginActivity.setOnClickListener(view -> switchActivities());
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchActivityIntent);
    }
}