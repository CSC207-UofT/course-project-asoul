package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import exceptions.UnauthorizedAccessException;
import helper.GlobalVariables;
import use_case.UserManager;

public class ChangeUserInfoActivity extends AppCompatActivity {

    GlobalVariables globalVariables = (GlobalVariables) this.getApplication();

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
        mbChangeUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nickNameET = findViewById(R.id.cui_nn);
                EditText phoneNumberET = findViewById(R.id.cui_pn);

                String nickName = nickNameET.getText().toString();
                String phoneNumber = phoneNumberET.getText().toString();

                String success = "Successfully change the user information!";
                String fail = "Changing failed. Please do not keep the frame empty.";

                String username = GlobalVariables.getUsername();
                String accessKey = GlobalVariables.getKey();

                if (nickName.equals("") && phoneNumber.equals("")) {
                    Toast.makeText(getApplicationContext(),fail,Toast.LENGTH_SHORT).show();
                } else if (nickName.equals("")) {
                    try {
                        UserManager.setPhoneNumber(username, accessKey, phoneNumber);
                    } catch (UnauthorizedAccessException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
                    startActivity(intent);
                } else if (phoneNumber.equals("")) {
                    try {
                        UserManager.setNickname(username,accessKey,nickName);
                    } catch (UnauthorizedAccessException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        UserManager.setNickname(username,accessKey,nickName);
                        UserManager.setPhoneNumber(username, accessKey, phoneNumber);
                    } catch (UnauthorizedAccessException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeUserInfoActivity.this, UserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });

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