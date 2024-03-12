import java.util.*;
import java.sql.*;

public class FoodOrder {
	Map<String,Integer> cart=new HashMap<>();
	int tot=0;
	
	public boolean addToCart(String foodId,int qty) {
		
		String query="select price,availableQty from fooditem where foodId=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, foodId);
			ResultSet rs=pst.executeQuery();rs.next();
			if(rs.getInt(2)>=qty) {
			tot+=(qty*rs.getDouble(1));
			cart.put(foodId, qty);
			
			return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public void displayCart() {
		System.out.println("Bill for the items: ");
		for(Map.Entry<String,Integer> map:cart.entrySet()) {
			System.out.println("------------------------------------");
			System.out.println("FoodId:"+map.getKey());
			System.out.println("quantity:"+map.getValue());
			System.out.println("------------------------------------");
		}
		System.out.println("Total:"+tot);
	}
}
