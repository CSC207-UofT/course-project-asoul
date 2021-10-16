import java.util.ArrayList;

/**
 * Java class representation for Food instance
 */

public class Food implements Comparable<Food> {
    private final String foodName; //Name of the food item, can't be changed once set
    private double price; //Price of the food item
    private final int id; //UNIQUE id of the food item, can't be changed once set
    private final ArrayList<String> label; //A list of labels given to the Food
    private String descriptions; // Description of the food

    /**
     * Construct an instance of a Food
     * @param foodName The name of the food item that will be displayed
     * @param price The price of the food item, in double
     * @param id The UNIQUE id of the food item
     * @param label The category that the food item belongs to
     *              {"Appetizer", "Beverage", "Meal", "Dessert",
     *              "Italian Food", "Fast Food", etc.}
     */

    public Food(String foodName, double price, int id,
                ArrayList<String> label, String descriptions){
        this.foodName = foodName;
        this.price = price;
        this.id = id;
        this.label = label; // can add exceptions here by restricting label type
        this.descriptions = descriptions;
    }

    // Change the price of the Food item and return the previous price
    public double changePrice(double price){
        double temp_price = this.price;
        this.price = price;
        return temp_price;
    }

    // Change the description of the Food item and return the previous description
    public String changeDescription(String description){
        String temp_string = this.descriptions;
        this.descriptions = description;
        return temp_string;
    }

    /**
     * Below are Getter methods for all instance variables
     */

    public String getFoodName(){
        return this.foodName;
    }

    public double getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<String> getLabel() {
        return this.label;
    }

    public String getDescriptions() {
        return this.descriptions;
    }


    /**
     * compare if two food objects are identical, i.e. all their attributes equal to each other
     * @param o
     * @return return 0 if two Food objects are identical, return 1 otherwise
     */
    @Override
    public int compareTo(Food o) {
        if (this.foodName.equals(o.foodName)){
            return 0;
        }
        return 1;
    }
}
