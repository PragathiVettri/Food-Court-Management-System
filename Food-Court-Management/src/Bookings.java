import java.text.SimpleDateFormat; 
import java.text.ParseException;
import java.util.*;
import java.sql.SQLException;

public class Bookings {
	private String resId;
	private String cusName;
	private Date bookedDate; 

	Scanner sc=new Scanner(System.in);
	
	public Bookings(String resId) {
		this.resId=resId;
		
		System.out.println("Enter your name:");
		this.cusName=sc.next();
		
		System.out.println("Enter date (dd-mm-yyyy) you wish to dine: ");
		String dateInput = sc.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			bookedDate = dateFormat.parse(dateInput);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	public void setResId(String resId) {
		this.resId=resId;
	}
	public String getResId() {
		return resId;
	}
	
	public void setCusName(String cusName) {
		this.cusName=cusName;
	}
	public String getCusName() {
		return cusName;
	}
	public void setBookedDate(Date bookedDate) {
		this.bookedDate=bookedDate;
	}
	public Date getBookedDate() {
		return bookedDate;
	}
	
	
public boolean isAvailable()  throws SQLException{
		
		RestaurantDao resdao = new RestaurantDao();
		BookingDao bookingdao = new BookingDao();
		int capacity = resdao.restaurantTableCapacity(resId);

		int booked = bookingdao.getBookedCount(resId,bookedDate);
		
		return booked<capacity;
		
	}
}
