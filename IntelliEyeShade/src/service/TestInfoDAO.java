package service;

import java.util.List;

import entity.TestInfo;



public interface TestInfoDAO {

	List<TestInfo> queryByCondition(String condition, int offset,int pageSize);

	int getAllRowCountByCondition(String columnToUsed, String condition);

	List<TestInfo> queryByCondition(String condition, String columnToUsed,
			int offset, int pageSize);

	int getAllRowCountByCondition(String uid, String columnToUsed,
			String condition);

	List<TestInfo> queryByCondition(String uid, String condition,
			String columnToUsed, int offset, int pageSize);

	int getAllRowCountByCondition(String uid, String columnToUsed,
			String startTime, String endTime);

	List<TestInfo> queryByCondition(String uid, String startTime,
			String endTime, String columnToUsed, int offset, int pageSize);
	
	List<TestInfo> queryByHQL(String hql);

}
