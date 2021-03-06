package entity;

import java.util.Arrays;

public class StatisticsInfo {
	private int totalUsers;
	private int avgRegisterUsers;
	private int avgLoginUsers;
	private int totalTestTimes;
	private int totalTestMiniutes;
	
	//单用户平均使用次数
	private int avgUsedTimes;
	//单用户平均使用时长
	private int avgUsedDuration;
	//男性比例
	private float maleRatio;
	//女性比例
	private float femaleRatio;
	//平均男性人数
	private int maleNum;
	//平均女性人数
	private int femaleNum;
	//男性平均时长
	private int maleAvgDuration;
	//女性平均时长
	private int femaleAvgDuration;
	
	//相应年龄段人数
	private int ageGroupNum[];
	//相应年龄段平均使用时长
	private int ageGroupAvgDuration[];
	//相应年龄段的百分比
	private float ageGroupRatio[];
	
	
	
	//相应时间段人数
	private int timeGroupNum[];
	//相应时间段使用时长
	private int timeGroupAvgDuration[];
	//相应时间段人数所占的比例
	private float timeGroupRatio[];	
	
	
	public float[] getAgeGroupRatio() {
		return ageGroupRatio;
	}
	public void setAgeGroupRatio(float[] ageGroupRation) {
		this.ageGroupRatio = ageGroupRation;
	}
	public float[] getTimeGroupRatio() {
		return timeGroupRatio;
	}
	public void setTimeGroupRatio(float[] timeGroupRatio) {
		this.timeGroupRatio = timeGroupRatio;
	}
	public int[] getAgeGroupNum() {
		return ageGroupNum;
	}
	public void setAgeGroupNum(int[] ageGroupNum) {
		this.ageGroupNum = ageGroupNum;
	}
	public int[] getAgeGroupAvgDuration() {
		return ageGroupAvgDuration;
	}
	public void setAgeGroupAvgDuration(int[] ageGroupAvgDuration) {
		this.ageGroupAvgDuration = ageGroupAvgDuration;
	}
	public int[] getTimeGroupNum() {
		return timeGroupNum;
	}
	public void setTimeGroupNum(int[] timeGroupNum) {
		this.timeGroupNum = timeGroupNum;
	}
	public int[] getTimeGroupAvgDuration() {
		return timeGroupAvgDuration;
	}
	public void setTimeGroupAvgDuration(int[] timeGroupAvgDuration) {
		this.timeGroupAvgDuration = timeGroupAvgDuration;
	}
	public int getMaleAvgDuration() {
		return maleAvgDuration;
	}
	public int getAvgUsedTimes() {
		return avgUsedTimes;
	}
	public void setAvgUsedTimes(int avgUsedTimes) {
		this.avgUsedTimes = avgUsedTimes;
	}
	public int getAvgUsedDuration() {
		return avgUsedDuration;
	}
	public void setAvgUsedDuration(int avgUsedDuration) {
		this.avgUsedDuration = avgUsedDuration;
	}
	public float getMaleRatio() {
		return maleRatio;
	}
	public void setMaleRatio(float maleRatio) {
		this.maleRatio = maleRatio;
	}
	public float getFemaleRatio() {
		return femaleRatio;
	}
	public void setFemaleRatio(float femaleRatio) {
		this.femaleRatio = femaleRatio;
	}
	public int getMaleNum() {
		return maleNum;
	}
	public void setMaleNum(int maleNum) {
		this.maleNum = maleNum;
	}
	public int getFemaleNum() {
		return femaleNum;
	}
	public void setFemaleNum(int femaleNum) {
		this.femaleNum = femaleNum;
	}
	public int getMaleDuration() {
		return maleAvgDuration;
	}
	public void setMaleAvgDuration(int maleAvgDuration) {
		this.maleAvgDuration = maleAvgDuration;
	}
	public int getFemaleAvgDuration() {
		return femaleAvgDuration;
	}
	public void setFemaleAvgDuration(int femaleAvgDuration) {
		this.femaleAvgDuration = femaleAvgDuration;
	}
	
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
				+ ", totalTestMiniutes=" + totalTestMiniutes
				+ ", avgUsedTimes=" + avgUsedTimes + ", avgUsedDuration="
				+ avgUsedDuration + ", maleRatio=" + maleRatio
				+ ", femaleRatio=" + femaleRatio + ", maleNum=" + maleNum
				+ ", femaleNum=" + femaleNum + ", maleAvgDuration="
				+ maleAvgDuration + ", femaleAvgDuration=" + femaleAvgDuration
				+ ", ageGroupNum=" + Arrays.toString(ageGroupNum)
				+ ", ageGroupAvgDuration="
				+ Arrays.toString(ageGroupAvgDuration) + ", timeGroupNum="
				+ Arrays.toString(timeGroupNum) + ", timeGroupAvgDuration="
				+ Arrays.toString(timeGroupAvgDuration) + "]";
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
