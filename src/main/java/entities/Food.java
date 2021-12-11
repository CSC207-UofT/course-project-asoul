package entities;


import java.io.Serializable;


/**
 * Java class representation for Entities.Food instance
 */

public class Food implements Comparable<Food>, Serializable {
    private final String foodName; //Name of the food item, can't be changed once set
    private final double price; //Price of the food item
    private final String descriptions; // Description of the food

    /**
     * Construct an instance of a Entities.Food
     *
     * @param foodName The name of the food item that will be displayed
     * @param price    The price of the food item, in double
     */

    public Food(String foodName, double price, String descriptions) {
        this.foodName = foodName;
        this.price = price;
        this.descriptions = descriptions;
    }


    /**
     * Below are Getter methods for all instance variables
     */

    public String getFoodName() {
        return this.foodName;
    }

    public double getPrice() {
        return this.price;
    }

    public String getDescriptions() {
        return this.descriptions;
    }


    /**
     * compare if two food objects are identical, i.e. all their attributes equal to each other
     *
     * @param o The Entities.Food object which is compared to
     * @return return 0 if two Entities.Food objects are identical, return 1 otherwise
     */
    @Override
    public int compareTo(Food o) {
        if (this.foodName.equals(o.foodName)) {
            return 0;
        }
        return 1;
    }
}
