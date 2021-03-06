package entity;

public class Devices {
	private String deviceID;
	private String deviceVersion;
	private String romVersion;
	private String deviceStatus;
	private String bindingStatus;
	private String uid;
	//private Users user;
	public String getDeviceID() {
		return deviceID;
	}
	
	
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getDeviceVersion() {
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}
	public String getRomVersion() {
		return romVersion;
	}
	public void setRomVersion(String romVersion) {
		this.romVersion = romVersion;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getBindingStatus() {
		return bindingStatus;
	}
	public void setBindingStatus(String bindingStatus) {
		this.bindingStatus = bindingStatus;
	}
	
	
public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	@Override
	public String toString() {
		return "Devices [deviceID=" + deviceID + ", deviceVersion="
				+ deviceVersion + ", romVersion=" + romVersion
				+ ", deviceStatus=" + deviceStatus + ", bindingStatus="
				+ bindingStatus + ", uid=" + uid + "]";
	}
	public Devices(String deviceID, String deviceVersion, String romVersion,
			String deviceStatus, String bindingStatus, String uid) {
		super();
		this.deviceID = deviceID;
		this.deviceVersion = deviceVersion;
		this.romVersion = romVersion;
		this.deviceStatus = deviceStatus;
		this.bindingStatus = bindingStatus;
		this.uid = uid;
		//this.user = user;
	}
	
	public Devices()
	{
		
	}
	

}
