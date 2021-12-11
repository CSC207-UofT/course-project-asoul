package com.example.asoul.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.asoul.R;
import helper.GlobalVariables;

public class OrderActivity extends AppCompatActivity {
    Button switchToOrderHistory;
    Button OrderConfirmBtn;
    String ErrorMessage = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Order Price Display
        //String OrderID = "" + GlobalVariables.getOrderID();
        //Double total_prize = GlobalVariables.getOrderPrice()
        double total_prize = 100.0;
        String print_total_prize = "Total Price: " + total_prize + "$";
        TextView txt = findViewById(R.id.PriceLabel);
        txt.setText(print_total_prize);

        // Text View for Order Detail
        try {
            //String orderDetail = OrderManager.getOrderDetail(OrderID);
            String orderDetail = "This area will display the order detail";
            TextView txt2 = findViewById(R.id.OrderDetail);
            txt2.setText(orderDetail);
        } catch (Exception e) {
            switchToUserInfo();
        }

        // Error Message Text View
        TextView txt3 = findViewById(R.id.ErrorMessage);
        txt3.setText(ErrorMessage);

        OrderConfirmBtn = findViewById(R.id.ConfirmOrder);
        OrderConfirmBtn.setOnClickListener(v -> {
            try {
                ErrorMessage = "You have change the Order Status Successfully!";
                txt3.setText(ErrorMessage);
            } catch (Exception e) {
                ErrorMessage = "Unknown Error occurs to Change Order Status!";
                txt3.setText(ErrorMessage);
            }
        });


        switchToOrderHistory = findViewById(R.id.BackToUserInfo);
        switchToOrderHistory.setOnClickListener(v -> switchToUserInfo());


        RatingBar simpleRatingBar = findViewById(R.id.ratingBar);
        simpleRatingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            float rating = simpleRatingBar.getRating();
            double real_rating = (double) rating * 2;
            try {
                ErrorMessage = "Rate Success, Rate: " + real_rating;
                txt3.setText(ErrorMessage);
            } catch (Exception e) {
                ErrorMessage = "Unknown Error occurs to Rating!";
                txt3.setText(ErrorMessage);
            }
        });


    }
    private void switchToUserInfo() {
        Intent switchActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchActivityIntent);
    }
}