import java.sql.*;

public class Admin extends Users{
	
	public Admin() {
		super();
	}
	
	public Admin(String userMailId,String password) {
		super(userMailId,password);
	}
	public boolean logIn(Users u) {
		String query="select * from Admin where userMailId=? and password=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, u.getUserMailId());
			pst.setString(2, u.getPassword());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	public boolean logOut() {
		return false;
	}
}
