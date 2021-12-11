package com.example.asoul.Activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.asoul.R;
import exceptions.CollidedFoodException;
import exceptions.FoodIdCollisionException;
import helper.GlobalVariables;
import use_case.FoodTruckManager;

public class ChangeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_menu);

        EditText foodNameET = findViewById(R.id.cm_fn);
        EditText descriptionET = findViewById(R.id.cm_description);
        EditText priceET = findViewById(R.id.cm_price);
        EditText idET = findViewById(R.id.cm_id);

        String foodName = foodNameET.getText().toString();
        String description = descriptionET.getText().toString();
        String price = priceET.getText().toString();
        String id = idET.getText().toString();

        String success = "Successfully change the menu!";
        String fail = "Change failed. Please Try again.";

        String username = GlobalVariables.getUsername();

        Button mbAddFood = findViewById(R.id.cm_addb);
        mbAddFood.setOnClickListener(v -> {
            if (foodName.equals("") | description.equals("") | price.equals("") | id.equals("")) {
                Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
            } else {
                try {
                    FoodTruckManager.addFoodToMenu(foodName, price, description, id, username);
                } catch (CollidedFoodException | FoodIdCollisionException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
            }
        });
        // May have some error since lack of try/catch

        Button mbDeleteFood = findViewById(R.id.cm_deleteb);
        mbDeleteFood.setOnClickListener(v -> {
            if (foodName.equals("")) {
                Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
            } else {
                boolean t = FoodTruckManager.removeFoodFromMenu(foodName, username);
                if (!t) {
                    Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button mbBack = findViewById(R.id.cm_back);
        mbBack.setOnClickListener(v -> {
            Intent intent = new Intent(ChangeMenuActivity.this, ChangeTruckInfoActivity.class);
            startActivity(intent);
        });
    }
}