package service;

import entity.DetectDetail;


public interface DetectDetailDAO {
	
	public DetectDetail getDetectDetail(String did);
	public boolean insertDetectDetailInfo(DetectDetail dd);
	public boolean updateDetectDetail(DetectDetail dd);
	public void deleteDetectDetail(String detectID);

}
