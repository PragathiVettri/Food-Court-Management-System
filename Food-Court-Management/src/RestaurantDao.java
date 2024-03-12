import java.sql.*;

public class RestaurantDao{
	public int insertRestaurant(Restaurant r1) throws SQLException{

		String query="Insert into restaurant values(?,?,?)";
		Connection con=DbConnection.getConnection();
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1, r1.getRid());
		st.setString(2, r1.getName());
		st.setInt(3, r1.getNoOfTables());
		int row=st.executeUpdate();
		return row;
	}

	public ResultSet searchRestaurant(String rid) throws SQLException{
		String query="Select * from Restaurant where resId='"+rid+"';";
		Connection con=DbConnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		return rs;
	}

	public int removeRestaurant(String rid) throws SQLException{
		String query="delete from Restaurant where resId = ?  ";
		Connection con=DbConnection.getConnection();
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1,rid);
		int rs=st.executeUpdate();
		return rs;
	}

	public void showAllRestaurants() throws SQLException{
		String query="Select * from Restaurant";
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
	
	public int restaurantTableCapacity(String resId) {
		String query="select noOfTables from restaurant where resId=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, resId);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
