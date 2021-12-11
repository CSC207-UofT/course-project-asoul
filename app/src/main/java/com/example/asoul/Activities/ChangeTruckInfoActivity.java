package com.example.asoul.Activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import exceptions.UnauthorizedAccessException;
import helper.GlobalVariables;
import use_case.FoodTruckManager;

public class ChangeTruckInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_truck_info);

        // Define the function of changeMenu Button
        Button mbChangeMenu = findViewById(R.id.cti_cmb);
        mbChangeMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ChangeTruckInfoActivity.this, ChangeMenuActivity.class);
            startActivity(intent);
        });

        Button mbChangeTruckInfo = findViewById(R.id.cti_ctib);
        mbChangeTruckInfo.setOnClickListener(v -> {
            EditText truckNameET = findViewById(R.id.cti_tn);
            EditText startTimeET = findViewById(R.id.cti_st);
            EditText endTimeET = findViewById(R.id.cti_et);
            EditText locationET = findViewById(R.id.cti_location);

            String truckName = truckNameET.getText().toString();
            String startTime = startTimeET.getText().toString();
            String endTime = endTimeET.getText().toString();
            String location = locationET.getText().toString();

            String success = "Successfully change the truck information!";
            String fail = "Change failed. Please Try again.";

            String username = GlobalVariables.getUsername();
            String accessKey = GlobalVariables.getKey();

            if (truckName.equals("") && startTime.equals("") && endTime.equals("") && location.equals("")) {
                Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
            } else {
                if (!truckName.equals("")) {
                    try {
                        FoodTruckManager.setTruckName(truckName, username, accessKey);
                    } catch (UnauthorizedAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (!startTime.equals("")) {
                    try {
                        FoodTruckManager.setStartTime(startTime, username, accessKey);
                    } catch (UnauthorizedAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (!endTime.equals("")) {
                    try {
                        FoodTruckManager.setEndTime(endTime, username, accessKey);
                    } catch (UnauthorizedAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (!location.equals("")) {
                    try {
                        FoodTruckManager.setAddress(location, username, accessKey);
                    } catch (UnauthorizedAccessException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangeTruckInfoActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });

        Button mbBack = findViewById(R.id.cti_back);
        mbBack.setOnClickListener(v -> {
            Intent intent = new Intent(ChangeTruckInfoActivity.this, UserInfoActivity.class);
            startActivity(intent);
        });
    }
}