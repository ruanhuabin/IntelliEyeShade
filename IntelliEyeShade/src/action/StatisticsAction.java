package action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.AdministratorDAO;
import service.TestInfoDAO;
import service.UsersDAO;
import serviceimpl.AdministratorDAOImpl;
import serviceimpl.TestInfoDAOImpl;
import serviceimpl.UsersDAOImpl;
import static util.IntelliEyeShadeLogger.logger;


import com.opensymphony.xwork2.ModelDriven;

import entity.Administrator;
import entity.StatisticsInfo;
import entity.TestInfo;
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
		
		
		UsersDAOImpl userDAO = new UsersDAOImpl();
		
		String hql = "from Users as u where u.registerDate >= '" + startTime + "' and u.registerDate <= '" + endTime + "'";
		int totalUsers = userDAO.getAllRowCount(hql);
		
		List<Users> users = userDAO.queryByHQL(hql);
		
		StatisticsInfo si = new StatisticsInfo();
		si.setTotalUsers(totalUsers);
		
		int totalTestTimes = 0;
		for(Users user: users)
		{
			totalTestTimes += user.getTestTimes();
		}
		
		si.setTotalTestTimes(totalTestTimes);
		
		
		//获取单用户使用情况信息
		TestInfoDAO tisDAO = new TestInfoDAOImpl();
		int totalUsedDuration = 0;
		
		List<TestInfo> tis = null;
		
		Hashtable<String, List<TestInfo>> uid2TestInfo = new Hashtable<String, List<TestInfo>>();
		for(Users user: users)
		{
			String uid = user.getUid();
			 
			String hql2 = "from TestInfo as ti where ti.uid = '" + uid + "'";
			tis = tisDAO.queryByHQL(hql2);
			//logger.info("tis = " + tis);
			
			uid2TestInfo.put(uid, tis);
			
			for(TestInfo ti: tis)
			{
				totalUsedDuration += ti.getTimeDuration();
			}
			
		}
		int avgUsedDuration = (totalUsers == 0) ? 0: totalUsedDuration / totalUsers;
		int avgTestTimes = (totalTestTimes == 0) ? 0: totalTestTimes / totalUsers;
		si.setAvgUsedDuration(avgUsedDuration);
		si.setAvgUsedTimes(avgTestTimes);
		
		
		//用户性别以及使用时长统计		
		int totalMaleNum = 0;
		int totalFemaleNum = 0;
		float maleRatio = 0.0f;
		float femaleRatio = 0.0f;
		
		int totalMaleDuration = 0;
		int totalFemaleDuration = 0;
		int maleAvgDuration = 0;
		int femaleAvgDuration = 0;
		for(Users user: users)
		{
			String gender = user.getGender();
			logger.info("gender = " + gender);
			
			if(gender.equals("男"))
			{
				totalMaleNum ++;
				
				String uid = user.getUid();				 
//				String hql3 = "from TestInfo as ti where ti.uid = '" + uid + "'";
//				tis = tisDAO.queryByHQL(hql3);
				
				tis = uid2TestInfo.get(uid);
								
				for(TestInfo ti: tis)
				{
					totalMaleDuration += ti.getTimeDuration();
				}
				
				
			}
			else if(gender.equals("女"))
			{
				totalFemaleNum ++;
				String uid = user.getUid();				 
//				String hql3 = "from TestInfo as ti where ti.uid = '" + uid + "'";
//				tis = tisDAO.queryByHQL(hql3);
				tis = uid2TestInfo.get(uid);
				for(TestInfo ti: tis)
				{
					totalFemaleDuration += ti.getTimeDuration();
				}
			}			
		}
		
		maleRatio = (totalUsers == 0) ? 0.0f : (float)totalMaleNum /(float)totalUsers;
		femaleRatio = (totalUsers == 0) ? 0.0f : (float)totalFemaleNum /(float)totalUsers;
		
		logger.info("totalMaleDuration = " + totalMaleDuration);
		logger.info("totalFemaleDuration = " + totalFemaleDuration);
		maleAvgDuration = (totalUsers == 0) ? 0: totalMaleDuration / totalMaleNum;
		femaleAvgDuration = (totalUsers == 0) ? 0: totalFemaleDuration / totalFemaleNum;
		si.setMaleNum(totalMaleNum);
		si.setFemaleNum(totalFemaleNum);
		si.setMaleRatio(maleRatio);
		si.setFemaleRatio(femaleRatio);		
		si.setMaleAvgDuration(maleAvgDuration);
		si.setFemaleAvgDuration(femaleAvgDuration);
		
		
		
		//用户年龄段以及使用时长统计
		
		int ageGroupNum[] = new int[20];
		for(int i = 0; i < ageGroupNum.length;i ++)
		{
			ageGroupNum[i] = 0;
		}
		for(Users user: users)
		{
			int age = user.getAge();
			
			
			//1-5,6-10,11-15,16-20,21-25....
			int groupIndex = (age -  1) / 5;
			ageGroupNum[groupIndex] ++;
		}
		
		int ageTimeDurationGroup[] = new int[20];
		for(int i = 0; i < ageTimeDurationGroup.length; i ++)
		{
			ageTimeDurationGroup[i] = 0;
		}
		for(Users user: users)
		{
			String uid = user.getUid();
			int age = user.getAge();
			
			int groupIndex = (age - 1) / 5;
			
			tis = uid2TestInfo.get(uid);
			
			
			for(TestInfo ti: tis)
			{
				ageTimeDurationGroup[groupIndex] += ti.getTimeDuration();
			}
			
		}
		
		int ageGroupAvgDuration[] = new int[20];
		
		for(int i = 0; i < ageGroupAvgDuration.length; i ++)
		{
			ageGroupAvgDuration[i] = (ageGroupNum[i] == 0) ? 0: ageTimeDurationGroup[i] / ageGroupNum[i];
		}
		
		float ageGroupRatio[] = new float[20];
		
		for(int i = 0; i < ageGroupRatio.length; i ++)
		{
			ageGroupRatio[i] = 0.0f;
		}
		
		for(int i = 0; i < ageGroupRatio.length; i ++)
		{
			ageGroupRatio[i] = (totalUsers == 0) ? 0.0f: (float)ageGroupNum[i] / (float)totalUsers;
		}
		
		si.setAgeGroupNum(ageGroupNum);
		si.setAgeGroupAvgDuration(ageGroupAvgDuration);
		si.setAgeGroupRatio(ageGroupRatio);
		
		request.setAttribute("statisticsinfo", si);
		
		logger.info("statisticsinfo = " + si);	
		
		
		logger.info("ageGroup = " + ageGroupNum);
		for(int i = 0; i < 20; i ++)
		{
			System.out.print(ageGroupNum[i] + " ");
		}
		logger.info("---------------------------------------------------------------");
		
		logger.info("ageTimeDurationGroup = " + ageTimeDurationGroup);
		for(int i = 0; i < 20; i ++)
		{
			System.out.print(ageTimeDurationGroup[i] + " ");
		}
		logger.info("---------------------------------------------------------------");
		
		logger.info("avgAgeTimeGroup = " + ageGroupAvgDuration);
		for(int i = 0; i < 20; i ++)
		{
			System.out.print(ageGroupAvgDuration[i] + " ");
		}
		logger.info("---------------------------------------------------------------");
		
		
		//用户使用时间段及使用时长统计
		int timeGroupNumWithCurrUID[] = new int[4];
		int timeGroupTotalDuration[] = new int[4];
		int timeGroupAvgDuration[] = new int[4];
		float timeGroupRatio[] = new float[4];
		
		for(int i = 0; i < timeGroupNumWithCurrUID.length; i ++)
		{
			timeGroupNumWithCurrUID[i] = 0;
			timeGroupAvgDuration[i] = 0;
			timeGroupTotalDuration[i] = 0;
			timeGroupRatio[i] = 0.0f;
		}
		
		int timeGroupNum[] = new int[4];
		for(int i = 0; i < timeGroupNum.length; i ++)
		{
			timeGroupNum[i] = 0;
		}
		for(Users user:users)
		{
			String uid = user.getUid();
			tis = uid2TestInfo.get(uid);
			
			for(TestInfo ti: tis)
			{
				Date testDate = ti.getTestDate();
				Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
				calendar.setTime(testDate);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				
				//1-6 7-12, 13-18, 19-24
				
				int grpIndex = (hour - 1)/ 6;
				
				System.out.println("hour = " + hour + ", grpIndex = " + grpIndex);
				
				timeGroupNumWithCurrUID[grpIndex] ++;
				timeGroupTotalDuration[grpIndex] += ti.getTimeDuration();
			}
			
			for(int i = 0; i < 4; i ++)
			{
				if(timeGroupNumWithCurrUID[i] != 0)
				{
					timeGroupNum[i] ++;
				}
			}
			
			for(int i = 0; i < 4; i ++)
			{
				timeGroupNumWithCurrUID[i] = 0;
			}
		}
		
		for(int i = 0; i < timeGroupNum.length; i ++)
		{
			timeGroupAvgDuration[i] = (timeGroupNum[i] == 0) ? 0 : timeGroupTotalDuration[i] / timeGroupNum[i];
			timeGroupRatio[i] = (totalUsers == 0) ? 0.0f : (float)timeGroupNum[i] / (float)totalUsers;
		}
		
		si.setTimeGroupAvgDuration(timeGroupAvgDuration);
		si.setTimeGroupNum(timeGroupNum);
		si.setTimeGroupRatio(timeGroupRatio);
		
		
		logger.info("timeGroupNum = " + timeGroupNum);
		for(int i = 0; i < 4; i ++)
		{
			System.out.print(timeGroupNum[i] + " ");
		}
		logger.info("---------------------------------------------------------------");
		
		logger.info("timeGroupTotalDuration = " + timeGroupTotalDuration);
		for(int i = 0; i < 4; i ++)
		{
			System.out.print(timeGroupTotalDuration[i] + " ");
		}
		logger.info("---------------------------------------------------------------");
		
		logger.info("timeGroupAvgDuration = " + timeGroupAvgDuration);
		for(int i = 0; i < 4; i ++)
		{
			System.out.print(timeGroupAvgDuration[i] + " ");
		}
		logger.info("---------------------------------------------------------------");
		
		logger.info("timeGroupRatio = " + timeGroupRatio);
		for(int i = 0; i < 4; i ++)
		{
			System.out.print(timeGroupRatio[i] + " ");
		}
		logger.info("---------------------------------------------------------------");
		
		
		return "statistics_query_success";
	}
	
	
	
}
