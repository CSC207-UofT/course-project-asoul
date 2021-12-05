package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

public class OrderActivity extends AppCompatActivity {
    Button switchToOrderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        switchToOrderHistory = findViewById(R.id.btnBack);
        switchToOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToOrderHistory();
            }
        });

    }

    private void switchToOrderHistory() {
        Intent switchActivityIntent = new Intent(this, OrderHistoryActivity.class);
        startActivity(switchActivityIntent);
    }

}