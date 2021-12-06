package com.example.asoul.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import use_case.UserManager;

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

        username = (EditText) findViewById(R.id.ETRegisterUsername);
        password = (EditText) findViewById(R.id.ETRegisterPassword);
        nickname = (EditText) findViewById(R.id.ETRegisterNickname);
        phone = (EditText) findViewById(R.id.ETRegisterPhone);
        registerState = (TextView) findViewById(R.id.registerState);
        register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (UserManager.createUser(username.getText().toString(),
                        password.getText().toString(),
                        nickname.getText().toString(),
                        phone.getText().toString())){
                    registerState.setText("Successfully registered new user, you can now proceed to log in!");
                }
                else{
                    registerState.setText("User with the given username has already been registered!");
                }
            }
        });


        switchToLoginActivity = findViewById(R.id.btnLoginPage);
        switchToLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSwitchToLoginActivity();}
        });
    }


    private void setSwitchToLoginActivity(){
        Intent switchActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchActivityIntent);
    }

}
