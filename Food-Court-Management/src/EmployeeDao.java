import java.sql.*;

public class EmployeeDao implements EmployeeOperations{

	public int insertEmployee(Employee e1){

		String query="Insert into employee values('"+e1.getEmpId()+"','"+e1.getName()+"','"+e1.getSalary()+"')";
		try {
			Connection con=DbConnection.getConnection();
			Statement st=con.createStatement();
			int row=st.executeUpdate(query);
			return row;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}

	public ResultSet searchEmployee(String empId){
		String query="Select * from Employee where empId='"+empId+"';";
		try {
			Connection con=DbConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			return rs;
		}
		catch(Exception e){
			System.out.println(e);
		}
		ResultSet rs=null;
		return rs;
	}

	public int removeEmployee(String empId){
		String query="delete from Employee where empId = ?  ";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1,empId);
			int rs=st.executeUpdate();
			return rs;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 1;
	}

	public void showAllEmployees(){
		String query="Select * from Employee";
		try {
			Connection con=DbConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				System.out.println("------------------------------------");
				System.out.println("Employee Id: "+rs.getString(1));
				System.out.println("Employee Name: "+rs.getString(2));
				System.out.println("Employee Salary: "+rs.getDouble(3));
				System.out.println("------------------------------------");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
