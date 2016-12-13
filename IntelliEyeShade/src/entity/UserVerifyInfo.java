package entity;

public class UserVerifyInfo {
	private String phoneNum;
	private String timeStamp;
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public UserVerifyInfo(String phoneNum, String timeStamp) {
		super();
		this.phoneNum = phoneNum;
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "UserVerifyInfo [phoneNum=" + phoneNum + ", timeStamp="
				+ timeStamp + "]";
	}
	
	public UserVerifyInfo()
	{
		
	}

}
