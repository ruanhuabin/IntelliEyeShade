package entity;

import java.util.Date;

public class TestInfo {
	
	private String tid;
	private String uid;
	private Date testDate;
	private int focusValue;
	private int relaxValue;
	private int pressIndex;
	private int tiredIndex;
	private int improvedIndex;
	private int heartRate;
	private int heartVariate;
	private int timeDuration;
	private String usedPattern;
	private String music;
	
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date date) {
		this.testDate = date;
	}
	public int getFocusValue() {
		return focusValue;
	}
	public void setFocusValue(int focusValue) {
		this.focusValue = focusValue;
	}
	public int getRelaxValue() {
		return relaxValue;
	}
	public void setRelaxValue(int relaxValue) {
		this.relaxValue = relaxValue;
	}
	public int getPressIndex() {
		return pressIndex;
	}
	public void setPressIndex(int pressIndex) {
		this.pressIndex = pressIndex;
	}
	public int getTiredIndex() {
		return tiredIndex;
	}
	public void setTiredIndex(int tiredIndex) {
		this.tiredIndex = tiredIndex;
	}
	public int getImprovedIndex() {
		return improvedIndex;
	}
	public void setImprovedIndex(int improvedIndex) {
		this.improvedIndex = improvedIndex;
	}
	public int getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}
	
	
	public int getHeartVariate() {
		return heartVariate;
	}
	public void setHeartVariate(int heartVariate) {
		this.heartVariate = heartVariate;
	}
	public int getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(int timeDuration) {
		this.timeDuration = timeDuration;
	}
	public String getUsedPattern() {
		return usedPattern;
	}
	public void setUsedPattern(String usedPattern) {
		this.usedPattern = usedPattern;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	@Override
	public String toString() {
		return "TestInfo [tid=" + tid + ", uid=" + uid + ", testDate="
				+ testDate + ", focusValue=" + focusValue + ", relaxValue="
				+ relaxValue + ", pressIndex=" + pressIndex + ", tiredIndex="
				+ tiredIndex + ", improvedIndex=" + improvedIndex
				+ ", heartRate=" + heartRate + ", heartVariate=" + heartVariate
				+ ", timeDuration=" + timeDuration + ", usedPattern="
				+ usedPattern + ", music=" + music + "]";
	}
	public TestInfo() {
		
	}
	public TestInfo(String uid, Date date, int focusValue,
			int relaxValue, int pressIndex, int tiredIndex, int improvedIndex,
			int heartRate, int heartVariate, int timeDuration, String usedPattern, String music) {
		
		
		this.uid = uid;
		this.testDate = date;
		this.focusValue = focusValue;
		this.relaxValue = relaxValue;
		this.pressIndex = pressIndex;
		this.tiredIndex = tiredIndex;
		this.improvedIndex = improvedIndex;
		this.heartRate = heartRate;
		this.heartVariate = heartVariate;
		this.timeDuration = timeDuration;
		this.usedPattern = usedPattern;
		this.music = music;
	}
	
	
	

	
}
