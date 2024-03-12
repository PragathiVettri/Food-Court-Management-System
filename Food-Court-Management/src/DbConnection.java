import java.sql.DriverManager;
import java.sql.*;

public class DbConnection {
	private static final String url="jdbc:mysql://localhost:3306/foodcourtmanagement";
	private static final String userName="Pragathi";
	private static final String password="PragathI@916";

	public static Connection getConnection()throws SQLException{
		return DriverManager.getConnection(url,userName,password);
	}

}
