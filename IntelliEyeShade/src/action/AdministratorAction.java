package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.AdministratorDAO;
import serviceimpl.AdministratorDAOImpl;


import com.opensymphony.xwork2.ModelDriven;

import entity.Administrator;
import entity.Users;

public class AdministratorAction extends SuperAction implements ModelDriven<Administrator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Administrator user = new Administrator();

	public String login()
	{
		AdministratorDAO udao = new AdministratorDAOImpl();
		
		if(udao.adminLogin(user))
		{
			session.setAttribute("loginUserName", user.getUsername());
			return "admin_login_success";
		}
		else
		{
			
			return "admin_login_failure";
		}
		
		
	}
	
	@SkipValidation
	public String logout()
	{
		
		if(session.getAttribute("loginUserName") != null)
		{
			session.removeAttribute("loginUserName");
		}
		
		return "admin_logout_success";
	}
	
	@Override
	public void validate()
	{
		System.out.println("Start to valid user input");
		if(user.getUsername().trim().equals(""))
		{
			System.out.println("User name is empty");
			this.addFieldError("usernameError", "�û�������Ϊ��");
		}
		else
		{
			System.out.println("User name is not empty");
		}
		
		if(user.getPassword().length() < 6)
		{
			System.out.println("User password is too short");
			addFieldError("passwordError", "���볤�Ȳ���С��6λ");
		}
		else
		{
			System.out.println("User password is valid");
		}
		
	}
	
	
	
	@Override
	public Administrator getModel() {
		// TODO Auto-generated method stub
		
		
		return user;
	}

}