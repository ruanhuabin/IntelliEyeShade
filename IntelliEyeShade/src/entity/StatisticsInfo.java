package entity;

public class StatisticsInfo {
	private int totalUsers;
	private int avgRegisterUsers;
	private int avgLoginUsers;
	private int totalTestTimes;
	private int totalTestMiniutes;
	public int getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}
	public int getAvgRegisterUsers() {
		return avgRegisterUsers;
	}
	public void setAvgRegisterUsers(int avgRegisterUsers) {
		this.avgRegisterUsers = avgRegisterUsers;
	}
	public int getAvgLoginUsers() {
		return avgLoginUsers;
	}
	public void setAvgLoginUsers(int avgLoginUsers) {
		this.avgLoginUsers = avgLoginUsers;
	}
	public int getTotalTestTimes() {
		return totalTestTimes;
	}
	public void setTotalTestTimes(int totalTestTimes) {
		this.totalTestTimes = totalTestTimes;
	}
	public int getTotalTestMiniutes() {
		return totalTestMiniutes;
	}
	public void setTotalTestMiniutes(int totalTestMiniutes) {
		this.totalTestMiniutes = totalTestMiniutes;
	}
	@Override
	public String toString() {
		return "StatisticsInfo [totalUsers=" + totalUsers
				+ ", avgRegisterUsers=" + avgRegisterUsers + ", avgLoginUsers="
				+ avgLoginUsers + ", totalTestTimes=" + totalTestTimes
				+ ", totalTestMiniutes=" + totalTestMiniutes + "]";
	}
	public StatisticsInfo(int totalUsers, int avgRegisterUsers,
			int avgLoginUsers, int totalTestTimes, int totalTestMiniutes) {
		
		this.totalUsers = totalUsers;
		this.avgRegisterUsers = avgRegisterUsers;
		this.avgLoginUsers = avgLoginUsers;
		this.totalTestTimes = totalTestTimes;
		this.totalTestMiniutes = totalTestMiniutes;
	}
	public StatisticsInfo() {
		
	}
	
	
	
	

}