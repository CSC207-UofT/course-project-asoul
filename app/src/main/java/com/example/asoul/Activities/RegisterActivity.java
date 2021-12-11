package com.example.asoul.Activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import use_case.UserManager;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    Button switchToLoginActivity;
    Button register;
    EditText username;
    EditText password;
    EditText nickname;
    EditText phone;
    TextView registerState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.ETRegisterUsername);
        password = findViewById(R.id.ETRegisterPassword);
        nickname = findViewById(R.id.ETRegisterNickname);
        phone = findViewById(R.id.ETRegisterPhone);
        registerState = findViewById(R.id.registerState);
        register = findViewById(R.id.btnRegister);
        register.setOnClickListener(view -> {
            if (UserManager.createUser(username.getText().toString(),
                    password.getText().toString(),
                    nickname.getText().toString(),
                    phone.getText().toString())) {
                try {
                    UserManager.saveUserDataBase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String success = "Successfully registered.\n Proceeding to log in...";
                Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
                // registerState.setText("Successfully registered new user, you can now proceed to log in!");
                setSwitchToLoginActivity();
            } else {
                String fail = "Username has already been registered!";
                // registerState.setText("User with the given username has already been registered!");
                Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
            }
        });


        switchToLoginActivity = findViewById(R.id.btnLoginPage);
        switchToLoginActivity.setOnClickListener(view -> setSwitchToLoginActivity());
    }


    private void setSwitchToLoginActivity() {
        Intent switchActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchActivityIntent);
    }

}
