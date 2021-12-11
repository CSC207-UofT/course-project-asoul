package com.example.asoul.Activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import exceptions.IncorrectOldPasswordException;
import exceptions.UnauthorizedAccessException;
import helper.GlobalVariables;
import use_case.UserManager;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Button mbBack = findViewById(R.id.cp_back);
        mbBack.setOnClickListener(v -> {
            Intent intent = new Intent(ChangePasswordActivity.this, ChangeUserInfoActivity.class);
            startActivity(intent);
        });

        Button mbChangePassword = findViewById(R.id.cp_cpb);
        mbChangePassword.setOnClickListener(v -> {
            EditText oldPasswordET = findViewById(R.id.cp_op);
            EditText newPasswordET = findViewById(R.id.cp_np);
            EditText confirmPasswrodET = findViewById(R.id.cp_confirm);

            String oldPassword = oldPasswordET.getText().toString();
            String newPassword = newPasswordET.getText().toString();
            String confirmPassword = confirmPasswrodET.getText().toString();

            String success = "Successfully change the password!";
            String fail = "The password is invalid or incorrect. Please Try again.";

            String username = GlobalVariables.getUsername();
            String accessKey = GlobalVariables.getKey();

            if (newPassword.equals("")) {
                Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
            } else if (!confirmPassword.equals(newPassword)) {
                Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
            } else {
                try {
                    String old = UserManager.getPassword(username, accessKey);
                    if (oldPassword.equals(old)) {
                        try {
                            UserManager.setPassword(username, accessKey, newPassword, oldPassword);
                        } catch (IncorrectOldPasswordException | UnauthorizedAccessException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePasswordActivity.this, ChangeUserInfoActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
                    }
                } catch (IncorrectOldPasswordException | UnauthorizedAccessException e) {
                    e.printStackTrace();
                }

                // 这里不应该抛exception，应该简单抛个toast

            }
        });
    }
}