package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entity.PageBean;
import entity.Users;
import service.PageBeanDAO;
import service.UsersDAO;
import serviceimpl.PageBeanDAOImpl;
import serviceimpl.UsersDAOImpl;

public class UsersAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private PageBeanDAO personService = new PageBeanDAOImpl();
	    
	    private int page;
	    
	    public int getPage()
	    {
	        return page;
	    }

	    public void setPage(int page)
	    {
	        this.page = page;
	    }

	    
	    public String queryByPage() throws Exception
	    {
	        //��ʾÿҳ��ʾ5����¼��page��ʾ��ǰ��ҳ
	        PageBean pageBean = personService.getPageBean(5, page);	        
	        request.setAttribute("pageBean", pageBean);
	        
	        System.out.println("pageBean = " + pageBean);
	        
	        System.out.println("====>page = " + page);
	        
	        return "querybypage_success";
	    }
	
	
	public String query()
	{
		UsersDAO sdao = new UsersDAOImpl();
		
		List<Users> list = sdao.queryAllUsers();
		
//		if(list != null && list.size() > 0)
//		{
//			session.setAttribute("sers_list", list);
//		
//			
//		}
		
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
		
		System.out.println("===>pageNum = " + pageNum);
		
		setPage(Integer.parseInt(pageNum));
		
		sdao.deleteUsers(uid);
		
		return "delete_success";
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