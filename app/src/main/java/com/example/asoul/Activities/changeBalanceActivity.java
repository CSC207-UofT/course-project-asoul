package com.example.asoul.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import exceptions.IncorrectArgumentException;
import exceptions.InsufficientBalanceException;
import exceptions.UnauthorizedAccessException;
import helper.GlobalVariables;
import use_case.UserManager;

public class changeBalanceActivity extends AppCompatActivity {

    EditText fundAmount;
    TextView fundAccessError;
    Button addFund;
    Button withdrawFund;
    Button fromFundBackToUserInfo;
    String key;
    String username;




    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_balance);

        fundAmount = findViewById(R.id.ETAmount);
        fundAccessError  = findViewById(R.id.fundAccessError);
        addFund = findViewById(R.id.btnAddFundPage);
        withdrawFund = findViewById(R.id.btnWithdrawFundPage);
        fromFundBackToUserInfo = findViewById(R.id.btnFromFundToUserInfo);
        key = GlobalVariables.getKey();
        username = GlobalVariables.getUsername();

        addFund.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    UserManager.addMoney(username, key, Double.parseDouble(fundAmount.getText().toString()));
                    fundAccessError.setText("Add fund successful!");
                } catch (IncorrectArgumentException e) {
                    fundAccessError.setText("The argument entered is incorrect!");
                } catch (UnauthorizedAccessException e) {
                    fundAccessError.setText("Unauthorized access to the content!");
                }
            }
        });

        withdrawFund.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    UserManager.withdrawMoney(username, key, Double.parseDouble(fundAmount.getText().toString()));
                    fundAccessError.setText("Withdraw fund successful!");
                } catch (IncorrectArgumentException e) {
                    fundAccessError.setText("The argument entered is incorrect!");
                } catch (UnauthorizedAccessException e) {
                    fundAccessError.setText("Unauthorized access to the content!");
                } catch (InsufficientBalanceException e) {
                    fundAccessError.setText("Account balance is insufficient!");
                }
            }
        });

        fromFundBackToUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToUserInfoActivities();
            }
        });
    }

    private void switchToUserInfoActivities() {
        Intent switchActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchActivityIntent);
    }
}
