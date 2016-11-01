package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import entity.UserDataPage;
import entity.Users;
import service.UserDataPageDAO;
import service.UsersDAO;
import serviceimpl.UserDataPageDAOImpl;
import serviceimpl.UsersDAOImpl;

public class UsersAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	    
    private int page;
    private String keyword;
    
    private static final Logger logger = LogManager.getLogger(UsersAction.class.getName());
    
    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }
        
    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String queryByCondition()
    {
    	UserDataPageDAO userDataDAO = new UserDataPageDAOImpl();
    	String keyword = request.getParameter("keyword");
    	System.out.println("keyword = " + keyword);
    	UserDataPage pageBean = userDataDAO.getUserPageDataByCondition(5, page, keyword);
    	request.setAttribute("pageBean", pageBean);
    	request.setAttribute("keyword", keyword);
    	        
        logger.info("In queryByCondition(): pageBean = " + pageBean);
        logger.info("In queryByCondition(): pageIndex = " + page);
    	
    	return "querybycondition_success";
    }
    
    public String queryByPage() throws Exception
    {
    	UserDataPageDAO userDataPageDAO = new UserDataPageDAOImpl();
        //表示每页显示5条记录，page表示当前网页
        UserDataPage pageBean = userDataPageDAO.getUserPageData(5, page);	        
        request.setAttribute("pageBean", pageBean);
        
        System.out.println("pageBean = " + pageBean);
        
        System.out.println("====>page = " + page);
        
        logger.info("In queryByPage(): pageBean = " + pageBean);
        logger.info("In queryByPage(): pageIndex = " + page);
        
        return "querybypage_success";
    }
	
	
	public String query()
	{
		UsersDAO sdao = new UsersDAOImpl();
		
		List<Users> list = sdao.queryAllUsers();
		
		logger.info("In query(): list = " + list);

		
		if(list != null && list.size() > 0)
		{
			session.setAttribute("users_list", list);
		}
		
		return "query_success";
	}

	
	public String delete()
	{
		UsersDAO sdao = new UsersDAOImpl();
		String uid = request.getParameter("uid");
		String pageNum = request.getParameter("pageNum");
		
		
		logger.info("In delete(): pageIndex = " + pageNum);
		
		setPage(Integer.parseInt(pageNum));
		
		sdao.deleteUsers(uid);
		
		return "delete_success";
	}
	
	public String deleteInSearch()
	{
		UsersDAO sdao = new UsersDAOImpl();
		String uid = request.getParameter("uid");
		String pageNum = request.getParameter("pageNum");
		
//		System.out.println("===>pageNum = " + pageNum);
		logger.info("In deleteInSearch(): pageIndex = " + pageNum);
		
		setPage(Integer.parseInt(pageNum));
		
		sdao.deleteUsers(uid);
		
		return "deleteinsearch_success";
	}
	
	public String add()
	{
		String address = request.getParameter("address");
		//Date birthday = new Date(request.getParameter("birthday"));
		String gender = request.getParameter("gender");
		String name = request.getParameter("sname");
		
		String birthday = request.getParameter("birthday");
		Users stu = new Users();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(birthday);
			//stu.setBirthday(date);
			//System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("birthday = " + birthday);
		
		
	//	stu.setAddress(address);
		
		stu.setGender(gender);
		//stu.setSname(name);
		
		UsersDAO sdao = new UsersDAOImpl();
		sdao.addUsers(stu);
		
		
		return "add_success";
	}
	
	public String modify()
	{
		String sid = request.getParameter("sid");
		UsersDAO sdao = new UsersDAOImpl();
		Users stu = sdao.queryUsersBySid(sid);
		
		session.setAttribute("modify_Users", stu);
		return "modify_success";
	}
	
	public String save()
	{
		String address = request.getParameter("address");
		//Date birthday = new Date(request.getParameter("birthday"));
		String gender = request.getParameter("gender");
		String name = request.getParameter("sname");
		
		String birthday = request.getParameter("birthday");
		Users stu = new Users();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(birthday);
			//stu.setBirthday(date);
			//System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("birthday = " + birthday);
		
		
		//stu.setAddress(address);
		
		stu.setGender(gender);
		//stu.setSname(name);
		//stu.setSid(request.getParameter("sid"));
		UsersDAO sdao = new UsersDAOImpl();
		//sdao.updateUsers(stu);
		
		return "save_success";
		
		
	}
}
