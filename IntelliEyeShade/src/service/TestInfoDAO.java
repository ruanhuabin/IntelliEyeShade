package service;

import java.util.List;

import entity.TestInfo;



public interface TestInfoDAO {

	List<TestInfo> queryByCondition(String condition, int offset,int pageSize);

	int getAllRowCountByCondition(String columnToUsed, String condition);

}
