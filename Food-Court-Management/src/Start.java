import java.util.*;
import java.sql.*;

public class Start{
	public static void main(String args[]) throws SQLException{

		Scanner sc = new Scanner(System.in);
		FileReadWriteDemo frwd = new FileReadWriteDemo("D:\\Program Files\\eclipse\\Food-Court-Management\\src\\fileio\\History.txt");
		System.out.println("-------------------------------------------");
		System.out.println("WelCome to Food Court");
		System.out.println("-------------------------------------------\n");

		boolean repeat=true;

		while(repeat) {

			System.out.println("1. Customer Services ");
			System.out.println("2. Admin Services");
			System.out.println("3. Exit");
			System.out.println("-------------------------------------------\n");

			System.out.println("Enter your choice:");
			int ch=sc.nextInt();

			switch(ch) {

			case 1:
				System.out.println("-------------------------------------------");
				System.out.println("you have choosed customer services.\n");

				System.out.println("1. SignUp if you don't have an account.");
				System.out.println("2. logIn if you already have an account");
				System.out.println("3. Go back");
				System.out.println("-------------------------------------------");
				int ch1=sc.nextInt();
				boolean sign=false;

				if(ch1==1) {
					System.out.println("Enter mailId:");
					String userMailId=sc.next();
					System.out.println("Enter customer Name:");
					String cusName=sc.next();
					System.out.println("Enter Password:");
					String password=sc.next();
					System.out.println("Enter phone number:");
					long phno=sc.nextLong();
					Customer c=new Customer(userMailId,cusName,password,phno);
					if(c.logIn(c)==false) {
						sign=c.signUp(c);
						if(sign) {
							System.out.println("Signup successful");
							System.out.println("-------------------------------------------");
						}
					}
					else {
						System.out.println("MailId already registered. Please login");
					}
				}
				if((ch1==2 )|| (sign && ch1==1)) {
					System.out.println("-------------------------------------------");
					System.out.println("Login for Customer services");
					System.out.println("-------------------------------------------");
					System.out.println("Enter mailId:");
					String userMailId=sc.next();
					//System.out.println("Enter customer Name:");
					//String cusName=sc.next();
					System.out.println("Enter Password:");
					String password=sc.next();
					//System.out.println("Enter phone number:");
					//long phno=sc.nextLong();
					Customer c=new Customer(userMailId,password);
					if(c.logIn(c)) {
						System.out.println("Login successful");
						System.out.println("-------------------------------------------");
						boolean logout1=true;
						while(logout1) {
							System.out.println("Select your option ");
							System.out.println("\t1. view Restaurants");
							System.out.println("\t2. view foodItems");
							System.out.println("\t3. Book table(View available restaurants to book table.)");
							System.out.println("\t4. Order food");
							System.out.println("\t5. logOut");
							System.out.println("-------------------------------------------");
							System.out.print("Eneter you choice: ");
							int choice0 = sc.nextInt();
							CustomerDao cdao=new CustomerDao();
							RestaurantDao resdao=new RestaurantDao();
							switch(choice0) {
							case 1:
								System.out.println("-------------------------------------------");
								System.out.println("you have chose to view Restaurants");
								System.out.println("-------------------------------------------");
								cdao.viewAvailableRestaurants();
								break;

							case 2:
								System.out.println("-------------------------------------------");
								System.out.println("you have chose to view food items");
								System.out.println("-------------------------------------------");
								System.out.println("Enter resId to view foodItems:");
								String resId=sc.next();
								cdao.viewFoodItems(resId);
								break;

							case 3:
								System.out.println("------------------------------------");
								System.out.println("you have chose to Book table");
								System.out.println("-------------------------------------------");
								System.out.println("Enter restaurant id in which you wish to dine:");
								String rId=sc.next();
								ResultSet result=resdao.searchRestaurant(rId);
								System.out.println("Restaurant found.");
								System.out.println("-------------------------------------------");
								Bookings booking=new Bookings(rId);
								if(result.next()) {
									if(booking.isAvailable()) {
										System.out.println("Booked Successfully...");
										BookingDao bookingdao = new BookingDao();
										bookingdao.addBooking(booking);
										System.out.println("-------------------------------------------");
									}
									else {
										System.out.println("Tables are not available currently. Try another restaurant or date.");
									}
								}
								else {
									System.out.println("Invalid restaurant id.");
									System.out.println("-------------------------------------------");
								}
								break;
							case 4:
								FoodOrder ord=new FoodOrder();
								System.out.println("-------------------------------------------");
								System.out.println("You have choosed to order food.");
								FoodItemDao fdao=new FoodItemDao();
								boolean place=true;
								while(place) {
									System.out.println("1. Add food to cart");
									System.out.println("2. Place order");
									System.out.println("3. GoBack");
									System.out.println("Enter your choice:");
									int ch0ice=sc.nextInt();
									if(ch0ice==1) {
										System.out.println("-------------------------------------------");
										System.out.println("Enter restaurant id to view menu:");
										String rId1=sc.next();
										ResultSet result1=resdao.searchRestaurant(rId1);
										if(result1.next()) {
											System.out.println("Restaurant found");
											System.out.println("-------------------------------------------");
											fdao.showAllFoodItem(rId1);
											System.out.println("Enter foodId :");
											String foodId=sc.next();
											System.out.println("Enter quantity:");
											int quant=sc.nextInt();
											ResultSet rs=fdao.searchFoodItem(foodId);
											if(rs.next()) {
												if(ord.addToCart(foodId,quant)) {
													fdao.sellFoodItem(foodId,rId1,quant);
													System.out.println("Added to cart successfully.");
													System.out.println("-------------------------------------------");
												}
												else {
													System.out.println("Sorry for the inconvenience. Insufficient quantity. ");
												}
											}
											else {
												System.out.println("Food Id is not valid. try again.");
												System.out.println("-------------------------------------------");
											}
										}
										else {
											System.out.println("Invalid restaurant id. try again");
											System.out.println("-------------------------------------------");
										}
									}
									else if(ch0ice==2) {
										System.out.println("-------------------------------------------");
										System.out.println("You havae choosed to place order.");
										ord.displayCart();
										System.out.println("Order placed successfully");
										System.out.println("-------------------------------------------");
									}
									else if(ch0ice==3) {
										place=false;
										System.out.println("-------------------------------------------");
									}
									else {
										System.out.println("Invalid choice.");
										System.out.println("-------------------------------------------");
									}
								}
								System.out.println("-------------------------------------------");
								break; 

							case 5:
								System.out.println("-------------------------------------------");
								System.out.println("Logged out successfully....");
								logout1=c.logOut();
								System.out.println("-------------------------------------------");
								break;

							default:
								System.out.println("Invalid choice. try again.");
							}
						}
					}
					else {
						System.out.println("login Failed");
					}
				}
				if(ch1==3) {
					System.out.println("-------------------------------------------");
					System.out.println("Going back to the main menu....");
					System.out.println("-------------------------------------------");

				}
				break;
			case 2:
				System.out.println("-------------------------------------------");
				System.out.println("Login for admin services");
				System.out.println("Enter mailId:");
				String userMailId=sc.next();
				System.out.println("Enter Password:");
				String password=sc.next();
				Admin a=new Admin(userMailId,password);
				if(a.logIn(a)) {
					System.out.println("Login successful");
					System.out.println("-------------------------------------------");
					boolean logout=true;
					while(logout) {
						System.out.println("Select your option ");
						System.out.println("\t1. Employee Management");
						System.out.println("\t2. Restaurant Management");
						System.out.println("\t3. Restaurant FoodItem Management");
						System.out.println("\t4. FoodItem Quantity Add-Sell");
						System.out.println("\t5. logOut");
						System.out.println("-------------------------------------------");
						System.out.print("Eneter you choice: ");
						int choice = sc.nextInt();
						switch(choice){

						case 1: 
							System.out.println("\n\n\n-------------------------------------------");
							System.out.println("You have chose Employee Management");
							System.out.println("-------------------------------------------");
							System.out.println("Select your option ");
							System.out.println("\t1. Insert New Employee");
							System.out.println("\t2. Remove Existing Employee");
							System.out.println("\t3. Show All Employees");
							System.out.println("\t4. Search a Employee");
							System.out.println("\t5. Go Back");
							System.out.println("-------------------------------------------");
							System.out.print("Eneter you choice: ");
							int choice1 = sc.nextInt();
							EmployeeDao empdao=new EmployeeDao();

							switch(choice1){
							case 1: 


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Insert New Employee");
								System.out.println("-------------------------------------------");

								System.out.print("Employee  ID : ");
								String empId1 = sc.next();
								System.out.print("Employee Name : ");
								String name1 = sc.next();
								System.out.print("Employee Salary : ");
								double salary1 = sc.nextDouble();

								Employee e1 = new Employee(empId1, name1, salary1);

								if(empdao.insertEmployee(e1)==1){
									System.out.println("Employee Inserted Successfully.");
									System.out.print("Name "+ name1+ ",  Id "+ empId1);
								}
								else{System.out.println("Employee can not be created now. Try again later");}


								System.out.println("\n\n#######################################################\n\n");

								break;

							case 2 :


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Remove Employee");
								System.out.println("-------------------------------------------");

								System.out.print("Employee  ID: ");
								String empId2 = sc.next();

								ResultSet rs= empdao.searchEmployee(empId2);

								System.out.println("Are You sure you want to delete this Employee? ");
								System.out.print("Press 1 to delete Employee  ");

								int delete = sc.nextInt();

								if(delete==1){

									if(rs.next()){
										if(empdao.removeEmployee(empId2)==1){
											System.out.println("Employee ID " +empId2 +" Removed Successfully.");
										}
										else{System.out.println("Employee can not be removed now. Try again later");}
									}
									else{System.out.println("No Employee found.");}
								}
								else{System.out.println("Account is not deleted. Thanks for coming back");}


								System.out.println("\n\n#######################################################\n\n");

								break;
							case 3: 



								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Show All Employee");
								System.out.println("-------------------------------------------");

								empdao.showAllEmployees();


								System.out.println("\n\n#######################################################\n\n");

								break;

							case 4 :


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Search Employee");
								System.out.println("-------------------------------------------");

								System.out.print("Employee  ID: ");
								String empId4 = sc.next();

								ResultSet rs1 = empdao.searchEmployee(empId4);

								if(rs1.next()){
									System.out.println("\n\n---------------------------------------------------\n");
									System.out.println("Employee has been founded.");
									System.out.println("Employee ID : "+rs1.getString(1));
									System.out.println("Employee Name : "+rs1.getString(2));
									System.out.println("Employee Salary : "+rs1.getDouble(3));
									System.out.println("\n---------------------------------------------------\n");
								}
								else{System.out.println("No Employee found.");}


								System.out.println("\n\n#######################################################\n\n");

								break;

							case 5: 

								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("Going Back to the main menu...............");
								System.out.println("-------------------------------------------");


								System.out.println("\n\n#######################################################\n\n");

								break;

							default :


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("Invalid option . Try again...............");
								System.out.println("-------------------------------------------");


								System.out.println("\n\n#######################################################\n\n");


								break;
							}

							break;

						case 2 :

							System.out.println("\n\n-------------------------------------------");
							System.out.println("You have chose Restaurant Management");
							System.out.println("-------------------------------------------");

							System.out.println("Select your option ");
							System.out.println("\t1. Insert New Restaurant");
							System.out.println("\t2. Remove Existing Restaurant");
							System.out.println("\t3. Show All Restaurants");
							System.out.println("\t4. Search a Restaurant");
							System.out.println("\t5. Go Back");
							System.out.println("-------------------------------------------");
							System.out.print("Eneter you choice: ");
							int choice2 = sc.nextInt();
							RestaurantDao resdao=new RestaurantDao();


							switch(choice2){
							case 1: 


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Insert New Restaurant");
								System.out.println("-------------------------------------------");

								System.out.print("Restaurant  ID: ");
								String rid1 = sc.next();
								System.out.print("Restaurant Name: ");
								String name1 = sc.next();
								System.out.print("No.Of Tables in Restaurant : ");
								int noOfTables = sc.nextInt();


								Restaurant r1 = new Restaurant(rid1, name1,noOfTables);

								if(resdao.insertRestaurant(r1)==1){
									System.out.println("Restaurant Inserted Successfully.");
									System.out.println("Name "+ name1+ ",  Id  "+rid1);
								}
								else{System.out.println("Restaurant can not be created now. Try again later");}

								System.out.println("\n\n#######################################################\n\n");

								break;

							case 2 :


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Remove Restaurant");
								System.out.println("-------------------------------------------");

								System.out.print("Restaurant  ID: ");
								String rid2 = sc.next();

								ResultSet rs = resdao.searchRestaurant(rid2);

								System.out.println("Are You sure you want to delete this Restaurant? ");
								System.out.print("Press 1 to delete Restaurant ");

								int delete = sc.nextInt();

								if(delete==1){

									if(rs.next()){
										if(resdao.removeRestaurant(rid2)==1){
											System.out.print("Restaurant ID " + rid2+ " Removed Successfully.");
										}
										else{System.out.println("Restaurant can not be removed now. Try again later");}
									}
									else{System.out.println("No Restaurant found.");}
								}
								else{System.out.println("Account is not deleted. Thanks for come back");}
								System.out.println("\n\n#######################################################\n\n");

								break;
							case 3: 


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Show All Restaurant");
								System.out.println("-------------------------------------------");

								resdao.showAllRestaurants();

								System.out.println("\n\n#######################################################\n\n");

								break;

							case 4 :

								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Search Restaurant");
								System.out.println("-------------------------------------------");

								System.out.print("Restaurant  ID: ");
								String rid4 = sc.next();

								ResultSet rs1 = resdao.searchRestaurant(rid4);

								if(rs1.next()){
									System.out.println("Restaurant has been founded.");
									System.out.println("---------------------------------------------");
									System.out.println("Restaurant ID : "+rs1.getString(1));
									System.out.println("Restaurant Name : "+rs1.getString(2));
									System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
									//.showAllFoodItems();
									System.out.println("---------------------------------------------");
								}
								else{System.out.println("No Restaurant found.");}
								System.out.println("\n\n#######################################################\n\n");

								break;

							case 5: 

								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("Going Back to the main menu...............");
								System.out.println("-------------------------------------------");
								System.out.println("\n\n#######################################################\n\n");

								break;

							default :


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("Invalid option . Try again...............");
								System.out.println("-------------------------------------------");
								System.out.println("\n\n#######################################################\n\n");


								break;
							}

							break;

						case 3: 

							System.out.println("\n\n-------------------------------------------");
							System.out.println("You have chose Restaurant FoodItem Management");
							System.out.println("-------------------------------------------");

							System.out.println("Select your option ");
							System.out.println("\t1. Insert New Food Item");
							System.out.println("\t2. Remove Existing Food Item");
							System.out.println("\t3. Show All Food Item");
							System.out.println("\t4. Search a Food Item");
							System.out.println("\t5. Go Back");
							System.out.println("-------------------------------------------");
							System.out.print("Enter you choice: ");
							int choice3 = sc.nextInt();
							RestaurantDao resdao1=new RestaurantDao();
							FoodItemDao fdao=new FoodItemDao();
							AppetizerDao adao=new AppetizerDao();
							MainDishDao mdao=new MainDishDao(); 

							switch(choice3){

							case 1: 

								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have choose to Insert New Food Items");
								System.out.println("-------------------------------------------\n");

								System.out.print("Restaurant ID: ");
								String rid3 = sc.next();


								if(resdao1.searchRestaurant(rid3) != null){

									System.out.println("Which types of food do you want to insert ?");
									System.out.println("\t1. Main Dish");
									System.out.println("\t2. Appitizers");
									System.out.println("\t3. Go Back");

									System.out.print("Enter Food Type: ");
									int choice31 = sc.nextInt();

									switch(choice31){
									case 1: 
										System.out.println("\n\n#######################################################\n\n");
										System.out.print("Food Id : ");
										String fid31 = sc.next();

										System.out.print("Food Name: ");
										String name31 = sc.next();

										System.out.print("Available Quantity : ");
										int availableQuantity31 = sc.nextInt();

										System.out.print("Food Price : ");
										double price31 = sc.nextDouble();

										System.out.print("Food Category : ");
										String category31 = sc.next();

										MainDish m31 = new MainDish(fid31,name31,availableQuantity31 ,price31, category31 );

										if(fdao.insertFoodItem(m31,rid3)==1 && mdao.insertMainDish(m31)==1)
										{
											System.out.println("Food Id Number "+ fid31 +" inserted for "+ rid3);
										}
										else
										{
											System.out.println("Food Item Can Not be inserted");
										}

										System.out.println("\n\n#######################################################\n\n");


										break;

									case 2:
										System.out.println("\n\n#######################################################\n\n");
										System.out.print("Food Id : ");
										String fid32 = sc.next();

										System.out.print("Food Name: ");
										String name32 = sc.next();

										System.out.print("Available Quantity : ");
										int availableQuantity32 = sc.nextInt();

										System.out.print("Food Price : ");
										double price32 = sc.nextDouble();

										System.out.print("Food Size : ");
										String size32 = sc.next();

										Appetiziers m32 = new Appetiziers(fid32,name32, availableQuantity32 ,price32, size32  );

										if(fdao.insertFoodItem(m32,rid3)==1 && adao.insertAppetizer(m32)==1)
										{
											System.out.println("Food Id Number "+ fid32 +" inserted for "+ rid3);
										}
										else
										{
											System.out.println("Food Item Can Not be inserted");
										}

										System.out.println("\n\n#######################################################\n\n");


										break;

									case 3: 

										System.out.println("\n\n#######################################################\n\n");
										System.out.println("-------------------------------------------");
										System.out.println("Going Back to the main menu...............");
										System.out.println("-------------------------------------------");
										System.out.println("\n\n#######################################################\n\n");


										break;

									default: 


										System.out.println("\n\n#######################################################\n\n");
										System.out.println("-------------------------------------------");
										System.out.println("Invalid option . Try again...............");
										System.out.println("-------------------------------------------");
										System.out.println("\n\n#######################################################\n\n");


										break;
									}
								}

								else{System.out.println("Food Id does not match. Try again.");}


								break;

							case 2 :


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have choose to Remove Food Items");
								System.out.println("-------------------------------------------\n\n");

								System.out.print("Restaurant ID: ");
								String rid322 = sc.next();

								if(resdao1.searchRestaurant(rid322) != null){
									//Restaurant r322 = f.searchRestaurant(rid322);
									System.out.print("Food Item  ID: ");
									String fid322 = sc.next();

									//FoodItem f322 = fdao.searchFoodItem(fid322);
									ResultSet rs=fdao.searchFoodItem(fid322);
									//ResultSet rs1=mdao.searchMainDish(fid322);
									//ResultSet rs2=adao.searchAppetizer(fid322);
									/*int flag=0;
										if(rs1.next()) {
											flag=1;
										} else {
											flag=0;
										}*/

									if(rs.next()){
										System.out.println("Are You sure you want to delete this Food Item? ");
										System.out.print("Press 1 to delete Food Item ");

										int delete = sc.nextInt();

										if(delete==1){
											if(fdao.removeFoodItem(fid322)==1 /*&& ((flag==1)?(mdao.removeMainDish(fid322)):(adao.removeAppetizer(fid322)))==1*/){
												System.out.print("Food Item Removed Successfully.");
											}
											else{System.out.println("Food Item can not be removed now. Try again later");}
										}

										else{System.out.println("Account is not deleted. Thanks for come back");}
									}
									else{System.out.println("No Food Item found.");}
								}
								else{System.out.println("No Restaurant found.");}


								System.out.println("\n\n#######################################################\n\n");

								break;

							case 3: 


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Show All Food Item");
								System.out.println("-------------------------------------------\n\n");


								System.out.print("Restaurant  ID: ");
								String rid332 = sc.next();

								//Restaurant r332 = f.searchRestaurant(rid332);
								ResultSet rs=resdao1.searchRestaurant(rid332);

								if(rs.next()){
									System.out.println("Restaurant has been founded.");
									fdao.showAllFoodItem(rid332);
									//adao.showAllAppetizer();
								}

								else{System.out.println("No Restaurant found.");}
								System.out.println("\n\n#######################################################\n\n");

								break;

							case 4 : 


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Search Food Item");
								System.out.println("-------------------------------------------\n\n");

								System.out.print("Restaurant ID: ");
								String rid44 = sc.next();
								ResultSet rs1 = resdao1.searchRestaurant(rid44);
								if(rs1.next()){
									//Restaurant r44 = f.searchRestaurant(rid44);
									System.out.print("Food Item  ID: ");
									String fid44 = sc.next();

									ResultSet rs2 = fdao.searchFoodItem(fid44);

									if(rs2.next()){
										System.out.println("FoodItem has been founded.");
										System.out.println("\n-------------------------------------------\n");
										//fdao.showAllFoodItem(rid44);
										System.out.println("-------------------------------------------");
										System.out.println("Food ID: " + rs2.getString(1));
										System.out.println("Food Name: " + rs2.getString(3));
										System.out.println("Price: " + rs2.getDouble(4));
										System.out.println("Available Quantity: " +rs2.getInt(5));
										System.out.println("\n-------------------------------------------\n");
									}
									else{System.out.println("No FoodItem found.");}
								}

								else{System.out.println("No Restaurant found.");}

								System.out.println("\n\n#######################################################\n\n");

								break;
							case 5: 

								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("Going Back to the main menu...............");
								System.out.println("-------------------------------------------");
								System.out.println("\n\n#######################################################\n\n");

								break;

							default :


								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("Invalid option . Try again...............");
								System.out.println("-------------------------------------------");
								System.out.println("\n\n#######################################################\n\n");

								break;
							}



							break;

						case 4 :
							System.out.println("-------------------------------------------");
							System.out.println("You have chose FoodItem Quantity Add-Sell");
							System.out.println("-------------------------------------------");

							System.out.println("Select your option ");
							System.out.println("\t1. Add  Food Item");
							System.out.println("\t2. Sell  Food Item");
							System.out.println("\t3. Show Add Sell History");
							System.out.println("\t4. Go Back");
							System.out.println("-------------------------------------------");
							System.out.print("Eneter you choice: ");
							int choice4 = sc.nextInt();
							RestaurantDao resdao2=new RestaurantDao();
							FoodItemDao fdao1=new FoodItemDao();
							switch(choice4){
							case 1: 
								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Add Food Item");
								System.out.println("-------------------------------------------");

								System.out.print("Enter Restaurant ID: ");

								String rid411 = sc.next();
								ResultSet rs=resdao2.searchRestaurant(rid411);

								if(rs.next()){

									System.out.print("Enter Food Item ID: ");

									String fid411 = sc.next();
									ResultSet rs1=fdao1.searchFoodItem(fid411);
									if(rs1.next()){

										System.out.print("Added Food Items Quantity : ");
										int amount1 = sc.nextInt();
										fdao1.addFoodItem(fid411, rid411, amount1);
									}
									else{System.out.println("Invalid Food Item Id Number");}

								}
								else{System.out.println("Restaurant ID MISMATCH");}
								System.out.println("\n\n#######################################################\n\n");


								break;

							case 2 : 
								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Sell Food Item");
								System.out.println("-------------------------------------------");

								System.out.print("Enter Restaurant ID: ");

								String rid412 = sc.next();

								ResultSet rs1=resdao2.searchRestaurant(rid412);
								if(rs1.next()){

									System.out.print("Enter Food Item ID: ");

									String fid412 = sc.next();
									ResultSet rs2=fdao1.searchFoodItem(fid412);
									if(rs2.next()){

										System.out.print("Selled Food Item Quantity : ");
										int amount2 = sc.nextInt();
										fdao1.sellFoodItem(fid412,rid412,amount2);
									}
									else{System.out.println("Invalid Food Item Id Number");}

								}
								else{System.out.println("Restaurant ID  does not MISMATCH");}

								break;
							case 3: 
								System.out.println("\n\n#######################################################\n\n"); 
								System.out.println("-------------------------------------------");
								System.out.println("You have chose to Show ADD SELL History");
								System.out.println("-------------------------------------------");

								frwd.readFromFile();
								System.out.println("\n\n#######################################################\n\n");

								break;

							case 4 :
								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("Going Back to the main menu...............");
								System.out.println("-------------------------------------------");
								System.out.println("\n\n#######################################################\n\n");

								break;

							default :

								System.out.println("\n\n#######################################################\n\n");
								System.out.println("-------------------------------------------");
								System.out.println("Invalid option . Try again...............");
								System.out.println("-------------------------------------------");
								System.out.println("\n\n#######################################################\n\n");


								break;
							}			

							break;
						case 5: 

							System.out.println("\n\n#######################################################\n\n");
							logout=a.logOut();
							System.out.println("-------------------------------------------");
							System.out.println("Logged Out successfully...............");
							System.out.println("-------------------------------------------");
							System.out.println("\n\n#######################################################\n\n");

							break;

						default :


							System.out.println("\n\n#######################################################\n\n");
							System.out.println("-------------------------------------------");
							System.out.println("Invalid option . Try again...............");
							System.out.println("-------------------------------------------");
							System.out.println("\n\n#######################################################\n\n");


							break;

						}

					}
				}
				else {
					System.out.println("Invalid logIn. Try Again...");
					System.out.println("------------------------------------------");
				}
				break;

			case 3:
				repeat=false;
				System.out.println("-------------------------------------------");
				System.out.println("Thanks for being with us...............");
				System.out.println("-------------------------------------------");
				break;

			default:
				System.out.println("\n\n#######################################################\n\n");
				System.out.println("-------------------------------------------");
				System.out.println("Invalid option . Try again...............");
				System.out.println("-------------------------------------------");
				System.out.println("\n\n#######################################################\n\n");

				break;
			}
		}
		sc.close();
	}
}
