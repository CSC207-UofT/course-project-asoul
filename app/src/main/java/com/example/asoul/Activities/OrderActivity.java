package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

public class OrderActivity extends AppCompatActivity {
    Button switchToOrderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        String total_prize = "100";
        String print_total_prize = "Total Price: " + total_prize + "$";
        TextView txt = (TextView) findViewById(R.id.PriceLabel);
        txt.setText(print_total_prize);

        switchToOrderHistory = findViewById(R.id.BackToHistory);
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