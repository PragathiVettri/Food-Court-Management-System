
public abstract class Users {
	private String userMailId;
	private String password;
	
	public Users() {}
	public Users(String userMailId,String password) {
		this.userMailId=userMailId;
		this.password=password;
	}
	
	public void setUserMailId(String userMailId) {
		this.userMailId=userMailId;
	}
	
	public String getUserMailId() {
		return userMailId;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getPassword() {
		return password;
	}
	//abstract boolean signUp();
	abstract boolean logIn(Users u);
	abstract boolean logOut();
}
