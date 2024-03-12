import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customer extends Users{

	private String cusName;
	private long phno;

	public Customer(String userMailId,String cusName,String password,long phno){
		super(userMailId,password);
		this.cusName=cusName;
		this.phno=phno;
	}
	
	public Customer(String userMailId,String password){
		super(userMailId,password);
	}
	
	public void setCusName(String cusName) {
		this.cusName=cusName;
	}

	public String getCusName() {
		return cusName;
	}

	public void setPhno(long phno) {
		this.phno=phno;
	}

	public long getPhno() {
		return phno;
	}

	public boolean logIn(Users u) {
		String query="select userMailId from Customer where userMailId=? and password=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, u.getUserMailId());
			pst.setString(2, u.getPassword());
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}

	public boolean signUp(Customer c) {
		String query="Insert into customer values(?,?,?,?)";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, c.getUserMailId());
			pst.setString(2, c.getCusName());
			pst.setString(3, c.getPassword());
			pst.setLong(4, c.getPhno());
			int row=pst.executeUpdate();
			if(row!=0)
				return true;
			else 
				return false;
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return false;
	}
	
	public boolean logOut() {
		return false;
	}
}
