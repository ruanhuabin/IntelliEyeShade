package service;

import entity.UserDataPage;

public interface UserDataPageDAO {
	public UserDataPage getUserPageData(int pageSize, int page);
	public UserDataPage getUserPageDataByCondition(int pageSize, int page, String condition, String keywordSelect);
	public UserDataPage getUserPageDataByTimeRange(int pageSize, int page,
			String startTime, String endTime);
	public UserDataPage getUserPageDataByBindStatus(int pageSize, int page,
			String bindCondition);

}
