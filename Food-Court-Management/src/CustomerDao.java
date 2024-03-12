import java.sql.*;

public class CustomerDao implements CustomerOperations{

	public int customerSignUp(Customer c) {
		String query="Insert into customer values(?,?,?,?)";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, c.getUserMailId());
			pst.setString(2, c.getCusName());
			pst.setString(3, c.getPassword());
			pst.setLong(4, c.getPhno());
			int row=pst.executeUpdate();
			return row;
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return 1;
	}
	
	public void viewAvailableRestaurants() {
		String query="Select * from Restaurant";
		try {
		Connection con=DbConnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			System.out.println("------------------------------------");
			System.out.println("Resataurant Id: "+rs.getString(1));
			System.out.println("Restaurant Name: "+rs.getString(2));
			System.out.println("No.of tables: "+rs.getInt(3));
			System.out.println("------------------------------------");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void viewFoodItems(String resId) {
		String query="Select * from fooditem where resId=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, resId);
			ResultSet rs=pst.executeQuery();
			System.out.println("##########################################");
			System.out.println("Dishes in Restaurant id "+resId);
			while(rs.next()) {
				System.out.println("-------------------------------------------");
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				System.out.println("-------------------------------------------");
			}
			System.out.println("##########################################");
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
