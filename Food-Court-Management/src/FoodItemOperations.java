import java.sql.*;

public interface FoodItemOperations{

	int insertFoodItem(MainDish f1,String resId);
	
	int removeFoodItem(String fid);
	
	ResultSet searchFoodItem(String fid);
	
	void addFoodItem(String fid,String resId,int qty);
	
	void sellFoodItem(String fid,String resId,int qty);
	
	void showAllFoodItem(String resId);
	
}





