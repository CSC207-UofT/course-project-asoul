package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

public class ChangeTruckInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_truck_info);

        // Define the function of changeMenu Button
        Button mbChangeMenu = findViewById(R.id.cti_cmb);
        mbChangeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeTruckInfoActivity.this, ChangeMenuActivity.class);
                startActivity(intent);
            }
        });

        Button mbChangeTruckInfo = findViewById(R.id.cti_ctib);
        //TODO: change Truck info

        Button mbBack = findViewById(R.id.cti_back);
        mbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeTruckInfoActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}