package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Button mbBack = findViewById(R.id.cp_back);
        mbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePasswordActivity.this, ChangeUserInfoActivity.class);
                startActivity(intent);
            }
        });

        Button mbChangePassword = findViewById(R.id.cp_cpb);
        //TODO: Change password
    }
}