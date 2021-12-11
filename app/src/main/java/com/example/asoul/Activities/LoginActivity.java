package com.example.asoul.Activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import exceptions.IncorrectCredentialsException;
import helper.GlobalVariables;
import use_case.UserManager;


public class LoginActivity extends AppCompatActivity {

    Button switchToRegisterActivity;
    Button switchToUserInfoActivity;
    EditText username;
    EditText password;
    TextView errorMessage;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        errorMessage = findViewById(R.id.loginError);
        switchToUserInfoActivity = findViewById(R.id.btnSignIn);
        switchToUserInfoActivity.setOnClickListener(v -> {

            try {
                key = UserManager.login(username.getText().toString(),
                        password.getText().toString()

                );
                GlobalVariables.setKey(key);
                GlobalVariables.setUsername(username.getText().toString());
                switchToUserInfoActivities();
            } catch (IncorrectCredentialsException e) {
                errorMessage.setText(e.getMessage());
            }
        });

        switchToRegisterActivity = findViewById(R.id.btnRegisterPage);
        switchToRegisterActivity.setOnClickListener(view -> switchRegisterActivities());
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
