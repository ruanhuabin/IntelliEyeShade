package serviceimpl;

import java.util.List;

import entity.TestInfo;
import entity.TestInfoPage;
import entity.UserDataPage;
import entity.Users;
import service.TestInfoDAO;
import service.TestInfoPageDAO;
import service.UserDataPageDAO;
import service.UsersDAO;
import static util.IntelliEyeShadeLogger.logger;

public class TestInfoPageDAOImpl implements TestInfoPageDAO {
	
	private TestInfoDAO userDAO = new TestInfoDAOImpl();

	

	@Override
	public TestInfoPage getTestInfoPageDataByUserID(int pageSize, int page, String condition) 
	{		
		// TODO Auto-generated method stub
		TestInfoPage pageBean = new TestInfoPage();       
		String columnToUsed = "uid";			
		logger.info("column used to search: " +  columnToUsed);
		
		//String hql = "from Users as u where u.username like :name";
		String hql = "from Users as u where u.uid = '" + condition + "'";
		logger.info("hql = " + hql);
        
        int allRows = userDAO.getAllRowCountByCondition(columnToUsed, condition);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        List<TestInfo> list = userDAO.queryByCondition(condition, offset, pageSize);
        
        logger.info("=======>TestInfo list = " + list);
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
		return pageBean;
	}
	
	
	@Override
	public TestInfoPage queryTestInfoByCondition(int pageSize, int page, String uid, String condition, String columnToUsed) 
	{		
		// TODO Auto-generated method stub
		TestInfoPage pageBean = new TestInfoPage();       
		TestInfoDAO testInfoDAO = new TestInfoDAOImpl();
        
        int allRows = testInfoDAO.getAllRowCountByCondition(uid, columnToUsed, condition);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        logger.info("Row num: " + allRows);
        
        
        
        List<TestInfo> list = testInfoDAO.queryByCondition(uid, condition, columnToUsed,  offset, pageSize);
        
        
        
        logger.info("=======>TestInfo list = " + list);
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
		return pageBean;
	}


	@Override
	public TestInfoPage filterTestInfoByCondition(int pageSize, int page, String uid,
			String startTime, String endTime, String columnToUsed) 
	{
		
		// TODO Auto-generated method stub
				TestInfoPage pageBean = new TestInfoPage();       
				TestInfoDAO testInfoDAO = new TestInfoDAOImpl();
		        
		        int allRows = testInfoDAO.getAllRowCountByCondition(uid, columnToUsed, startTime, endTime);
		        
		        int totalPage = pageBean.getTotalPages(pageSize, allRows);
		        
		        int currentPage = pageBean.getCurPage(page);
		        
		        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		        
		        logger.info("Row num: " + allRows);
		        
		        
		        
		        List<TestInfo> list = testInfoDAO.queryByCondition(uid, startTime, endTime, columnToUsed,  offset, pageSize);
		        
		        
		        
		        logger.info("=======>TestInfo list = " + list);
		        
		        pageBean.setList(list);
		        pageBean.setAllRows(allRows);
		        pageBean.setCurrentPage(currentPage);
		        pageBean.setTotalPage(totalPage);
				return pageBean;
		
		
	}

	

}
