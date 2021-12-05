package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

public class UserInfoActivity extends AppCompatActivity {

    Button switchToMarketActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        String nickName = "WilliamQD";
        String username = "Wilsb";
        String welcomeContent = "Hello! UserName: " + username + " (" + nickName + ")";
        TextView txt = (TextView) findViewById(R.id.WelcomeContent);
        txt.setText(welcomeContent);

        int balance = 100;
        String accountBalance = "Your account Balance is: " + balance;
        TextView txt2 = (TextView) findViewById(R.id.AccountBalance);
        txt2.setText(accountBalance);

        String phoneNum = "6047718078";
        String phoneNumberDisplay = "Your Linked Phone Number is: " + phoneNum;
        TextView txt3 = (TextView) findViewById(R.id.PhoneNumber);
        txt3.setText(phoneNumberDisplay);

        String truckName = "BlueTruck";
        String truckStatus = "Activated";
        String truckNameDisplay = "Your Linked Truck Name is: " + truckName + " (" + truckStatus + ")";
        TextView txt4 = (TextView) findViewById(R.id.TruckName);
        txt4.setText(truckNameDisplay);

        switchToMarketActivity = findViewById(R.id.btnViewMarket);
        switchToMarketActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToMarketActivity();
            }
        });

        }
        private void setSwitchToMarketActivity(){
        Intent switchActivityIntent = new Intent(this, MarketActivity.class);
        startActivity(switchActivityIntent);

    }




}