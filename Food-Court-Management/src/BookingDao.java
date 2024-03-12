import java.util.Date;
import java.sql.*;

public class BookingDao {
	
	public int getBookedCount(String resId, Date date) {
		String query="select count(cusName) from bookings where resId=? and booked_date=?";
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement pst=con.prepareStatement(query);
			java.sql.Date sqldate = new java.sql.Date(date.getTime());
			pst.setString(1, resId);
			pst.setDate(2, sqldate);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public void addBooking(Bookings booking) throws SQLException{
		String query = "Insert into bookings values(?,?,?)";
		Connection con = DbConnection.getConnection();
		java.sql.Date sqldate = new java.sql.Date(booking.getBookedDate().getTime());
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, booking.getCusName());
		pst.setString(2, booking.getResId());
		pst.setDate(3, sqldate);
		
		pst.executeUpdate();
		
	}
}
