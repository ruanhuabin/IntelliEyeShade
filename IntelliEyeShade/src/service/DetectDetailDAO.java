package service;

import java.util.List;

import entity.DetectDetail;


public interface DetectDetailDAO {
	
	public DetectDetail getDetectDetail(String did);
	public boolean insertDetectDetailInfo(DetectDetail dd);
	public boolean updateDetectDetail(DetectDetail dd);
	public void deleteDetectDetail(String detectID);
	public List<DetectDetail> getAllDetectDetail();

}
