package service;

import entity.FocusDegree;

public interface FocusDegreeDAO {
	
	public FocusDegree getFocusDegreeInfo(String tid);
	public boolean insertFocusDegreeInfo(FocusDegree fd);
	public boolean updateFocusDegreeInfo(FocusDegree focusDegree);

}
