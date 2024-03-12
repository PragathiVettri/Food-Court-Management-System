import java.sql.*;

public class MainDishDao {
	public int insertMainDish(MainDish f1) throws SQLException{
		String query="insert into maindish values('"+f1.getFid()+"','"+f1.getCategory()+"')";  
		Connection con=DbConnection.getConnection();
		Statement st=con.createStatement();
		int row = st.executeUpdate(query);
		return row;
	}

	/*public ResultSet searchMainDish(String fid) throws SQLException{
		String query="select * from MainDish where foodId=?";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, fid);
		ResultSet rs=pst.executeQuery();
		return rs;
	}*/

	/*public int removeMainDish(String fid) throws SQLException{
		String query="delete from MainDish where foodId=?";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, fid);
		int row=pst.executeUpdate();
		return row;
	}*/
}
