package serviceimpl;

import java.util.List;

import entity.UserDataPage;
import entity.Users;
import service.UserDataPageDAO;
import service.UsersDAO;
import static util.IntelliEyeShadeLogger.logger;

public class UserDataPageDAOImpl implements UserDataPageDAO {
	
	private UsersDAO userDAO = new UsersDAOImpl();

	@Override
	public UserDataPage getUserPageData(int pageSize, int page) 
	{
		// TODO Auto-generated method stub
		
		UserDataPage pageBean = new UserDataPage();
        
        String hql = "from Users";
        
        int allRows = userDAO.getAllRowCount(hql);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        List<Users> list = userDAO.queryByPage(hql, offset, pageSize);
        
        System.out.println("user list = " + list);
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public UserDataPage getUserPageDataByCondition(int pageSize, int page,
			String condition, String keywordSelect) 
	{
		
		// TODO Auto-generated method stub
		UserDataPage pageBean = new UserDataPage();
        
       
		String columnToUsed = "username";
		
		if(keywordSelect.equals("gender"))
		{
			columnToUsed = "gender";
		}
		else if(keywordSelect.equals("age"))
		{
			columnToUsed = "age";
		}
		else if(keywordSelect.equals("phoneNum"))
		{
			columnToUsed = "phoneNum";
		}
		else if(keywordSelect.equals("testTimes"))
		{
			columnToUsed = "testTimes";
		}
			
		logger.info("column used to search: " +  columnToUsed);
		
		//String hql = "from Users as u where u.username like :name";
		String hql = "from Users as u where u." + columnToUsed + " like :name";
		logger.info("hql = " + hql);
        
        int allRows = userDAO.getAllRowCountByCondition(columnToUsed, condition);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        List<Users> list = userDAO.queryByCondition(hql, condition, offset, pageSize);
        
        System.out.println("=======>user list = " + list);
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public UserDataPage getUserPageDataByTimeRange(int pageSize, int page,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		
		UserDataPage pageBean = new UserDataPage();
        
        String hql = "from Users as u where u.registerDate >= '" + startTime + "' and u.registerDate <= '" + endTime + "'";
        
        logger.info("hql = " + hql);
        
        int allRows = userDAO.getAllRowCount(hql);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        List<Users> list = userDAO.queryByPage(hql, offset, pageSize);
        
        logger.info("list = " + list);
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public UserDataPage getUserPageDataByBindStatus(int pageSize, int page, String bindCondition)
	{
		UserDataPage pageBean = new UserDataPage();
        
        String hql = "from Users as u where u.bindingStatus = '" + bindCondition + "'";
        
        logger.info("hql = " + hql);
        
        int allRows = userDAO.getAllRowCount(hql);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        List<Users> list = userDAO.queryByPage(hql, offset, pageSize);
        
        logger.info("list = " + list);
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
		return pageBean;
	}

}
