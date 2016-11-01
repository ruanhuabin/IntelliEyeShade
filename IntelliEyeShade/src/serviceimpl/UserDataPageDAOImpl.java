package serviceimpl;

import java.util.List;

import entity.UserDataPage;
import entity.Users;
import service.UserDataPageDAO;
import service.UsersDAO;

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
			String condition) 
	{
		
		// TODO Auto-generated method stub
		UserDataPage pageBean = new UserDataPage();
        
       // String hql = "from Users as u where u.username like \"%" + condition + "%\"";
		 String hql = "from Users as u where u.username like :name";
        
        int allRows = userDAO.getAllRowCountByCondition(condition);
        
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

}