import java.sql.*;

public interface EmployeeOperations{

	int insertEmployee(Employee e);

	int removeEmployee(String empId);

	ResultSet searchEmployee(String empId);

	void showAllEmployees();

}


