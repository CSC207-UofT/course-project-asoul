# Specification
## Introduction
We plan to develop a food truck ordering app in which users can interact with the app to place orders for the food truck.  

The original intention of this project is that we feel the need to introduce a system for the Food Trucks at UofT, as we often find the Food Trucks on St.George rather “chaotic” and “inefficient” when it comes to busy hours around lunch time -- sometimes a bunch of students just crowd around the window of the Food Truck without a line and it’s hard to know who has ordered and who’s waiting. Thus, mimicking the success of online food ordering apps like Uber Eats, who hope our system would introduce a new way of operations for our beloved food truck at UofT, one that’s more efficient, easily used, and organized.

The project will consist of two parts, a front-end and a back-end. The front-end will be where all the information is displayed to the user and where the user can interact with the app and send commands. The back-end is where all information is being processed and stored, including how orders are placed, user account info and more.

To use the app, a user will register and have the option to choose between being a Seller or a Customer. 

A Seller will have to register a corresponding (or more if needed) Food Truck under their account, in which each Food Truck will have its individual description and Menu (of Foods). A Seller will maintain the Food Truck service through various features, such as updating the menu, keeping track of Food Truck’s order history and more.

A Customer can place an Order on the app by choosing Food from a list of Food Truck, but before that, the Customer needs to add balance to the app. The Customer can also choose the pickup time of the Order, and rate the Order once it is completed, which later will be reflected onto the Food Truck’s rating. A Customer is also entitled to their own order history which they can review at any time.

## Details of Key entities
### Customer
Customers need to create an account that includes their account name, an account password, contact number and account balance. Customers are required to add money to their accounts to order food, or withdraw money from their balance if they wish to.
Customers can either order food on their phone and pick up their food now or schedule another pickup time. Customers can also check their order status, such as scheduled(haven’t started) progress, or done. If customers’ order has been started yet, they should be able to cancel it. Customers can check the rate of a food truck and rate a food truck after they ordered from that food truck. Customers can also check how many orders there are in front of theirs. Customers can view their order history as well. Moreover, customers can cancel their accounts.

### Seller
A seller can add food trucks to their account, and modify the information of their existing food trucks, such as changing the name, opening time, as well as updating the menu for their food truck. It is noticeable that ordering food is not allowed within a seller account. A seller should open a new customer account to order food. 

### Food Truck
Each food truck provides information such as the name, the service time, the service location, ratings, and a menu of cuisines it serves. Food trucks should also have an owner and a contact number. Food trucks can only contact customers who have an order placed at their truck. 

### Order
Each order provides all the information that an actual order needs to accomplish its task, such as the order number(id), the seller / customer, the order details (the food and from which food truck), and last but not the least, the price of the order. An order’s key task involves keeping track of the status of itself, and changing it when necessary.
