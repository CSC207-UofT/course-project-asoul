# Scenario Walk-Through
Paul runs the main method in the CommandlineInterface, and it then creates 4 scenes which are LoginScene, UserInformationScene, MarketScene and FoodTruckScene which add themselves to Scene.allScenes upon creation. The active scene is set to be LoginScene initially. Paul is then prompted to enter login information to login into the system. However, he does not have an account just yet. So he types in “register” command to sign up a new account and "help" command for help information. While registering, he enters “Entities.Seller” as the user type as well as all of the necessary information for this new account, then confirms it to create his new account. This class will call the createUser method in UserManger, then call Seller constructor. After that, he returns to the login menu and enters information to login, which will call the login method in SellerManger. After he logins, as he moves onto the next scene called UserInformationScene, he sees his account information listed on the console, where all this information is provided by user manager classes. The information is getting from the getUserByAccountName in UserManager class. Paul sees that he doesn’t have any funds in his wallet, so he types in command (“add_fund” + a blank space + integer number) to add some funds to his wallet. He is now satisfied and used “view market” command to move onto the scene called MarketScene to see all food trucks available to him, which calls the getFoodTruck in FoodTruckManager. He sees his food truck on the list and he selected it to see what it actually contains. The program then moves onto FoodTruckScene to display detailed information for Paul. The information of the truck is from the getFoodTruckDetail method in FoodTruckManager. From the food menu, he can see names, descriptions and prices of these food. After he finishes browsing through them, he decides to call it a day. So he uses “back”, “view user info” and “sign out” commands consecutively to sign out of the system, then types in “exit” to fully exit the program.
