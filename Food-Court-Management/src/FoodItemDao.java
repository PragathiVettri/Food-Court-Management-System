import java.sql.*;

public class FoodItemDao implements FoodItemOperations{
	FileReadWriteDemo frwd = new FileReadWriteDemo("D:\\Program Files\\eclipse\\Food-Court-Management\\src\\fileio\\History.txt");

	public int insertFoodItem(MainDish f1,String resId) {
		String query="Insert into fooditem values('"+f1.getFid()+"','"+resId+"','"+f1.getName()+"','"+f1.getPrice()+"','"+f1.getAvailableQuantity()+"')";
		try {
			Connection con=DbConnection.getConnection();
			Statement st=con.createStatement();
			int row=st.executeUpdate(query);
			return row;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 1;
	}

	public int insertFoodItem(Appetiziers f1,String resId) {
		String query="Insert into fooditem values('"+f1.getFid()+"','"+resId+"','"+f1.getName()+"','"+f1.getPrice()+"','"+f1.getAvailableQuantity()+"')";
		try {
			Connection con=DbConnection.getConnection();
			Statement st=con.createStatement();
			int row=st.executeUpdate(query);
			return row;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 1;
	}

	public ResultSet searchFoodItem(String fid) {
		String query="select * from fooditem where foodId=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, fid);
			ResultSet rs=pst.executeQuery();
			return rs;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		ResultSet rs=null;
		return rs;
	}

	public int removeFoodItem(String fid) {
		String query="delete from fooditem where foodId=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, fid);
			int row=pst.executeUpdate();
			return row;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 1;
	}

	public void addFoodItem(String fid,String resId,int qty){
		String query1="select availableQty from fooditem where resId=? and foodId=?";
		String query="update fooditem set availableQty=availableQty+? where resId=? and foodId=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst1=con.prepareStatement(query1);
			pst1.setString(1, resId);
			pst1.setString(2, fid);
			ResultSet rs=pst1.executeQuery();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, qty);
			pst.setString(2, resId);
			pst.setString(3, fid);
			int row=pst.executeUpdate();
			if(row==1) {
				rs.next();
				System.out.println("available quantity: "+ rs.getInt(1));
				System.out.println("Added quantity: "+ qty);
				System.out.println("current quantity: "+(rs.getInt(1)+qty));
				frwd.writeInFile("Amount : " +qty + "  added in "+ fid+ " by "+ resId);
			}
			else {
				System.out.println("cannot be added");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public void sellFoodItem(String fid,String resId,int qty){
		String query1="select availableQty from fooditem where resId=? and foodId=?";
		String query="update fooditem set availableQty=availableQty-? where resId=? and foodId=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst1=con.prepareStatement(query1);
			pst1.setString(1, resId);
			pst1.setString(2, fid);
			ResultSet rs=pst1.executeQuery();
			rs.next();
			if(rs.getInt(1)>=qty) {
				PreparedStatement pst=con.prepareStatement(query);
				pst.setInt(1, qty);
				pst.setString(2, resId);
				pst.setString(3, fid);
				int row=pst.executeUpdate();
				if(row==1) {
					System.out.println("available quantity: "+ rs.getInt(1));
					System.out.println("sold quantity: "+ qty);
					System.out.println("current quantity: "+(rs.getInt(1)-qty));
					frwd.writeInFile("Amount : " +qty + "  sold in "+ fid+ " by "+ resId);

				}
				else {
					System.out.println("cannot be added");
				}
			}
			else {
				System.out.println("No enough stock");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	public void showAllFoodItem(String resId) {
		String query="select fi.foodId,fi.foodName,fi.price,fi.availableQty, md.category AS category, a.size AS size "
				+ "from FoodItem fi"
				+" Left join maindish md ON fi.foodId = md.foodId"
				+" Left Join appetizers a ON fi.foodId = a.foodId"
				+" where fi.resId = ? ";

		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, resId);
			ResultSet rs=pst.executeQuery();
			System.out.println("Foods in Restaurant ID: " +resId);
			while(rs.next()) {
				System.out.println("-------------------------------------------");
				System.out.println("Food ID: " + rs.getString(1));
				System.out.println("Food Name: " + rs.getString(2));
				System.out.println("Price: " + rs.getDouble(3));
				System.out.println("Available Quantity: " +rs.getInt(4));
				if (rs.getString(5) != null) {
					System.out.println("Main Dish Category: " + rs.getString(5));
				} else if (rs.getString(6) != null) {
					System.out.println("Appetizer Size: " + rs.getString(6));
				}
				System.out.println("-------------------------------------------");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
