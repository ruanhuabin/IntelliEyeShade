package entity;

public class DetectDetail {
	
	//���ID
	public String did;
	//�û�ID
	public String uid;
	//רע��
	public String focusDegrees;
	//���ɶ�
	public String relaxDegrees;
	//����
	public String heartRates;
	//���ʱ�����
	public String heartRateVariations;
	//�Ե�ԭʼ����
	public String brainRawData;
	//����������
	public String pulseWaveData;
	//���ɸü�����ݵ�ʱ��
	public String timeStamp;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getPulseWaveData() {
		return pulseWaveData;
	}
	public void setPulseWaveData(String pulseWaveData) {
		this.pulseWaveData = pulseWaveData;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFocusDegrees() {
		return focusDegrees;
	}
	public void setFocusDegrees(String focusDegrees) {
		this.focusDegrees = focusDegrees;
	}
	public String getRelaxDegrees() {
		return relaxDegrees;
	}
	public void setRelaxDegrees(String relaxDegrees) {
		this.relaxDegrees = relaxDegrees;
	}
	public String getHeartRates() {
		return heartRates;
	}
	public void setHeartRates(String heartRates) {
		this.heartRates = heartRates;
	}
	public String getHeartRateVariations() {
		return heartRateVariations;
	}
	public void setHeartRateVariations(String heartRateVariations) {
		this.heartRateVariations = heartRateVariations;
	}
	public String getBrainRawData() {
		return brainRawData;
	}
	public void setBrainRawData(String brainRawData) {
		this.brainRawData = brainRawData;
	}
	@Override
	public String toString() {
		return "[did=" + did +  ", focusDegrees="
				+ focusDegrees + ", relaxDegrees=" + relaxDegrees
				+ ", heartRates=" + heartRates + ", heartRateVariations="
				+ heartRateVariations + ", brainRawData=" + brainRawData
				+ ", pulseWaveData=" + pulseWaveData + ", timeStamp="
				+ timeStamp + "]";
	}
	public DetectDetail() {
		
	}
	public DetectDetail(String did, String uid, String focusDegrees,
			String relaxDegrees, String heartRates, String heartRateVariations,
			String brainRawData, String pulseWaveData, String timeStamp) {
		
		this.did = did;
		this.uid = uid;
		this.focusDegrees = focusDegrees;
		this.relaxDegrees = relaxDegrees;
		this.heartRates = heartRates;
		this.heartRateVariations = heartRateVariations;
		this.brainRawData = brainRawData;
		this.pulseWaveData = pulseWaveData;
		this.timeStamp = timeStamp;
	}
	
	
	
	

}
