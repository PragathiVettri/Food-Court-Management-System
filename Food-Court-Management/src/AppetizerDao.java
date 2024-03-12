import java.sql.*;

public class AppetizerDao{

	public int insertAppetizer(Appetiziers f1) throws SQLException{
		String query="insert into appetizers values(?,?)";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, f1.getFid());
		pst.setString(2, f1.getSize());
		int row=pst.executeUpdate();
		return row;
	}

	/*public ResultSet searchAppetizer(String fid) throws SQLException{
		String query="select * from Appetizers where foodId=?";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, fid);
		ResultSet rs=pst.executeQuery();
		return rs;
	}*/

	/*public int removeAppetizer(String fid) throws SQLException{
		String query="delete from Appetizers where foodId=?";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, fid);
		int row=pst.executeUpdate();
		return row;
	}*/

	/*public void showAllAppetizer(String fid) throws SQLException{
		String query="Select * from  Appetizers where resId=?";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, fid);
		ResultSet rs=pst.executeQuery();

	}*/
}
