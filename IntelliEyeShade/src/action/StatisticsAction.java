package action;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.AdministratorDAO;
import service.UsersDAO;
import serviceimpl.AdministratorDAOImpl;
import serviceimpl.UsersDAOImpl;
import static util.IntelliEyeShadeLogger.logger;


import com.opensymphony.xwork2.ModelDriven;

import entity.Administrator;
import entity.StatisticsInfo;
import entity.Users;

public class StatisticsAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String query()
	{
		String startTime = request.getParameter("starttime");
		String endTime = request.getParameter("endtime");
		
		logger.info("startTime = " + startTime);
		logger.info("endTime = " + endTime);
		
		
		UsersDAO userDAO = new UsersDAOImpl();
		
		int totalUsers = userDAO.getAllRowCount("from Users");
		
		List<Users> list = userDAO.queryAllUsers();
		
		StatisticsInfo si = new StatisticsInfo();
		si.setTotalUsers(totalUsers);
		
		int totalTestTimes = 0;
		for(Users user: list)
		{
			totalTestTimes += user.getTestTimes();
		}
		
		si.setTotalTestTimes(totalTestTimes);
		
		request.setAttribute("statisticsinfo", si);
		
		logger.info("statisticsinfo = " + si);
		
		return "statistics_query_success";
	}
	
	
	
}
