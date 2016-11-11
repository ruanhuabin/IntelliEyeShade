package entity;

public class Administrator {
	private int mid;
	private String username;
	private String password;
	
	public Administrator()
	{}
	
	
	
	public Administrator(int mid, String username, String password) {
		super();
		this.mid = mid;
		this.username = username;
		this.password = password;
	}



	@Override
	public String toString() {
		return "Administrator [mid=" + mid + ", username=" + username
				+ ", password=" + password + "]";
	}



	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
