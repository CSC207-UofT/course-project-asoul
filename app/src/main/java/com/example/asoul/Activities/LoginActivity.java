package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import entities.User;
import exceptions.IncorrectCredentialsException;
import use_case.UserManager;

public class LoginActivity extends AppCompatActivity {

    Button switchToRegisterActivity;
    Button switchToUserInfoActivity;
    EditText username;
    EditText password;
    String key;
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        errorMessage = findViewById(R.id.loginError);
        switchToUserInfoActivity = findViewById(R.id.btnSignIn);
        switchToUserInfoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    key = UserManager.login(username.getText().toString(),
                            password.getText().toString()

                    );
                    switchToUserInfoActivities();
                } catch (IncorrectCredentialsException e) {
                    errorMessage.setText(e.getMessage());
                }
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
