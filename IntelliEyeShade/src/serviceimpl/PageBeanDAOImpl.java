package serviceimpl;

import java.util.List;

import entity.PageBean;
import entity.Users;
import service.PageBeanDAO;
import service.UsersDAO;

public class PageBeanDAOImpl implements PageBeanDAO {
	
	private UsersDAO personDAO = new UsersDAOImpl();

	@Override
	public PageBean getPageBean(int pageSize, int page) {
		// TODO Auto-generated method stub
		
		PageBean pageBean = new PageBean();
        
        String hql = "from Users";
        
        int allRows = personDAO.getAllRowCount(hql);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        List<Users> list = personDAO.queryByPage(hql, offset, pageSize);
        
        System.out.println("user list = " + list);
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
		return pageBean;
	}

}