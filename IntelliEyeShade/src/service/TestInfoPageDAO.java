package service;

import entity.TestInfoPage;
import entity.UserDataPage;

public interface TestInfoPageDAO {	
	public TestInfoPage getTestInfoPageDataByUserID(int pageSize, int page, String condition);

	

	TestInfoPage queryTestInfoByCondition(int pageSize, int page,
			String uid, String condition, String columnToUsed);



	public TestInfoPage filterTestInfoByCondition(int i, int page, String uid,
			String startTime, String endTime, String columnToUsed);
	

}
