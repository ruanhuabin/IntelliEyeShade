package entity;

public class Users {
	
	private String uid;
	private String username;
	private String gender;
	private String phoneNum;
	private int age;
	private int testTimes;
	private String bindingStatus;
	
	
	public Users()
	{
		
		
	}


	public Users(String uid, String username, String gender, String phoneNum,
			int age, int testTimes, String bindingStatus) {
		super();
		this.uid = uid;
		this.username = username;
		this.gender = gender;
		this.phoneNum = phoneNum;
		this.age = age;
		this.testTimes = testTimes;
		this.bindingStatus = bindingStatus;
	}


	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", gender="
				+ gender + ", phoneNum=" + phoneNum + ", age=" + age
				+ ", testTimes=" + testTimes + ", bindingStatus="
				+ bindingStatus + "]";
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public int getTestTimes() {
		return testTimes;
	}


	public void setTestTimes(int testTimes) {
		this.testTimes = testTimes;
	}


	public String getBindingStatus() {
		return bindingStatus;
	}


	public void setBindingStatus(String bindingStatus) {
		this.bindingStatus = bindingStatus;
	}
	
	

	
	

}