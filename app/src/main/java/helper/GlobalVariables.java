package helper;

import android.app.Application;
import entities.Food;
import use_case.FoodTruckManager;
import use_case.OrderManager;
import use_case.UserManager;

public class GlobalVariables extends Application {

    private static String globalKey;
    private static String globalUsername;
    private static String orderID;
    private static Double orderPrice;


    public GlobalVariables() {

    }


    public static String getKey() {
        return globalKey;
    }

    public static void setKey(String key) {
        GlobalVariables.globalKey = key;
    }

    public static String getUsername() {
        return globalUsername;
    }

    public static void setUsername(String username){
         GlobalVariables.globalUsername = username;
    }

    /*
    public static Double getOrderPrice() {return orderPrice; }

    public static void setOrderPrice(Double orderPrice) {
        GlobalVariables.orderPrice = orderPrice;
    }

    public static String getOrderID() {
        return orderID;
    }

    public static void setOrderID(String orderID) {GlobalVariables.orderID = orderID;}*/
}
