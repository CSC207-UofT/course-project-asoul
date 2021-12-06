package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;

import helper.GlobalVariables;
import use_case.OrderManager;
import use_case.UserManager;

public class OrderActivity extends AppCompatActivity {
    GlobalVariables globalVariables = (GlobalVariables) this.getApplication();
    Button switchToOrderHistory;
    Button OrderConfirmBtn;
    String ErrorMessage = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Order Price Display
        String OrderID = "" + GlobalVariables.getID();
        String total_prize = GlobalVariables.getPrice();
        String print_total_prize = "Total Price: " + total_prize + "$";
        TextView txt = (TextView) findViewById(R.id.PriceLabel);
        txt.setText(print_total_prize);

        // Text View for Order Detail
        try {
            String orderDetail = OrderManager.getOrderDetail(OrderID);
            TextView txt2 = (TextView) findViewById(R.id.OrderDetail);
            txt2.setText(orderDetail);
        }catch (Exception e){
            switchToUserInfo();
        }

        // Error Message Text View
        TextView txt3 = (TextView) findViewById(R.id.ErrorMessage);

        OrderConfirmBtn = findViewById(R.id.ConfirmOrder);
        OrderConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OrderManager.changeOrderStatus(GlobalVariables.getUsername(),
                            GlobalVariables.getKey(), OrderID);
                    ErrorMessage = "You have change the Order Status Successfully!";
                    txt3.setText(ErrorMessage);
                }catch (Exception e){
                    ErrorMessage = "Unknown Error occurs to Change Order Status!";
                    txt3.setText(ErrorMessage);
                }
            }
        });


        switchToOrderHistory = findViewById(R.id.BackToUserInfo);
        switchToOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserInfo();
            }
        });

        RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        float rating = simpleRatingBar.getRating();
        double real_rating = (double) rating * 2;
        try {
            OrderManager.rateOrder(GlobalVariables.getUsername()
                    , GlobalVariables.getKey(), real_rating, OrderID);
            ErrorMessage = "Rate Success";
            txt3.setText(ErrorMessage);
        }catch (Exception e){
            ErrorMessage = "Unknown Error occurs to Rating!";
            txt3.setText(ErrorMessage);
        }
    }

    private void switchToUserInfo() {
        Intent switchActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchActivityIntent);
    }

}