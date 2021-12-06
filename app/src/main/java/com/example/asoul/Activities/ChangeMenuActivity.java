package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

public class ChangeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_menu);

        Button mbAddFood = findViewById(R.id.cm_addb);
        //TODO: Add Food to Menu

        Button mbDeleteFood = findViewById(R.id.cm_deleteb);
        //TODO: Delete Food from Menu

        Button mbBack = findViewById(R.id.cm_back);
        mbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeMenuActivity.this, ChangeTruckInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}