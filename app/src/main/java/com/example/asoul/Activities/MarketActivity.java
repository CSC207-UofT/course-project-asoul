package com.example.asoul.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.asoul.R;
import exceptions.UnknownFoodTruckException;
import exceptions.UnknownSorterException;
import sorters.Sorter;
import sorters.SorterSimpleFactory;
import use_case.FoodTruckManager;

import java.util.ArrayList;
import java.util.HashMap;

public class MarketActivity extends AppCompatActivity {
    Button switchToUserInfo;
    Button switchToUserEdit;
    Button switchToTruckEdit;
    Button switchToExit;
    Button switchToBlue;
    Button switchToSushi;
    Button switchToBrown;
    Button switchToYellow;
    private HashMap<String, String> info;
    private HashMap<Integer, String> pointer;
    private String sorter;
    ConstraintLayout marketLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        switchToUserInfo = findViewById(R.id.Userinfo);
        switchToUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToUserInfoActivity();
            }
        });

        switchToUserEdit = findViewById(R.id.UserEdit);
        switchToUserEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToUserEditActivity();
            }
        });

        switchToTruckEdit = findViewById(R.id.EditTruck);
        switchToTruckEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToTruckEdit();
            }
        });

        switchToExit = findViewById(R.id.Exit);
        switchToExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToExit();
            }
        });

        switchToSushi = findViewById(R.id.goToSushi);
        switchToSushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToSushi();
            }
        });


        switchToBlue = findViewById(R.id.goToBlue);
        switchToBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToBlue();
            }
        });


        switchToBrown = findViewById(R.id.goToBrown);
        switchToBrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToBrown();
            }
        });


        switchToYellow = findViewById(R.id.goToYellow);
        switchToYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSwitchToYellow();
            }
        });

        info = new HashMap<>();
        pointer = new HashMap<>();
        sorter = "";
    }


    private void setSwitchToUserInfoActivity(){
        Intent switchToUserActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchToUserActivityIntent);
    }

    private void setSwitchToUserEditActivity(){
        Intent switchToUserEditActivityIntent = new Intent(this, ChangeUserInfoActivity.class);
        startActivity(switchToUserEditActivityIntent);
    }

    private void setSwitchToTruckEdit(){
        Intent switchToTruckEditActivityIntent = new Intent(this, ChangeTruckInfoActivity.class);
        startActivity(switchToTruckEditActivityIntent);
    }

    private void setSwitchToExit(){
        Intent switchToExitActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchToExitActivityIntent);
    }

    private void setSwitchToBlue(){

        Intent switchToBlueActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchToBlueActivityIntent);
    }

    private void setSwitchToYellow(){
        Intent switchToYellowActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchToYellowActivityIntent);
    }

    private void setSwitchToSushi(){
        Intent switchToSushiActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchToSushiActivityIntent);
    }

    private void setSwitchToBrown(){
        Intent switchToBrownActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(switchToBrownActivityIntent);
    }

    private void updateInfo(){
        info = FoodTruckManager.getActiveFoodTruckDescription();
        assignPointer();
    }

    private void assignPointer(){
        int counter = 1;
        pointer.clear();
        try {
            ArrayList<String> sorted = sortTruckInfo();
            for (String name : sorted) {
                pointer.put(counter, name);
                counter++;
            }
        }catch (UnknownSorterException e){ // When Sorter is unknown, assign pointers by the order of the elements in the map
            for (String name : info.keySet()) {
                pointer.put(counter, name);
                counter++;
            }
        }
    }


    private ArrayList<String> sortTruckInfo()throws UnknownSorterException{
        Sorter s = SorterSimpleFactory.constructSorter(sorter);
        HashMap<String, String> items = new HashMap<>();
        if(sorter.equals("rating")){
            for(String key: info.keySet()){
                items.put(key, Double.toString(FoodTruckManager.getRating(key)));
            }
        }else if(sorter.equals("name_length")){
            for(String key: info.keySet()){
                items.put(key, FoodTruckManager.getTruckName(key));
            }
        }
        return s.sort(items);
    }


    public void constructOutput(){
        updateInfo();
        String upperObjectID = "textview2";
        for (int i = 1; i <= info.size(); i++) {
            String key = pointer.get(i);
            String content = info.get(key);
            Button newBtn = new Button(MarketActivity.this);
            marketLayout = (ConstraintLayout) findViewById(R.id.marketLayout);
            newBtn.setLayoutParams(new ConstraintLayout.LayoutParams(150, 150));
            newBtn.setId(i);
            newBtn.setText(content);
            newBtn.setHeight(170);
            newBtn.setWidth(400);

            marketLayout.addView(newBtn);
        }
    }
}