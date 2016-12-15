package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;

import entity.TestInfo;
import entity.TestInfoPage;
import entity.UserDataPage;
import entity.UserVerifyInfo;
import entity.Users;
import service.TestInfoDAO;
import service.TestInfoPageDAO;
import service.UserDataPageDAO;
import service.UsersDAO;
import serviceimpl.TestInfoDAOImpl;
import serviceimpl.TestInfoPageDAOImpl;
import serviceimpl.UserDataPageDAOImpl;
import serviceimpl.UsersDAOImpl;

public class UsersAction extends SuperAction {

	private static final long serialVersionUID = 1L;
	private int page;
	private String keyword;
	private String keywordSelect;

	private static final Logger logger = Logger.getLogger("MyLogger");

	//For testing purpose
	private String yourFileFileName;
	private String yourFileContentType;
	private File yourFile;

	//Attributes for user upload info
	private String userIconFileFileName;
	private String userIconFileContentType;
	private File userIconFile;

	public String getUserIconFileFileName() {
		return userIconFileFileName;
	}

	public void setUserIconFileFileName(String userIconFileFileName) {
		this.userIconFileFileName = userIconFileFileName;
	}

	public String getUserIconFileContentType() {
		return userIconFileContentType;
	}

	public void setUserIconFileContentType(String userIconFileContentType) {
		this.userIconFileContentType = userIconFileContentType;
	}

	public File getUserIconFile() {
		return userIconFile;
	}

	public void setUserIconFile(File userIconFile) {
		this.userIconFile = userIconFile;
	}

	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getYourFileFileName() {
		return yourFileFileName;
	}

	public void setYourFileFileName(String yourFileFileName) {
		this.yourFileFileName = yourFileFileName;
	}

	public File getYourFile() {
		return yourFile;
	}

	public void setYourFile(File yourFile) {
		this.yourFile = yourFile;
	}

	public String getYourFileContentType() {
		return yourFileContentType;
	}

	public void setYourFileContentType(String yourFileContentType) {
		this.yourFileContentType = yourFileContentType;
	}

	public String getKeywordSelect() {
		return keywordSelect;
	}

	public void setKeywordSelect(String keywordSelect) {
		this.keywordSelect = keywordSelect;
	}

	public int getPage() {

		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String queryByCondition() throws UnsupportedEncodingException {
		UserDataPageDAO userDataDAO = new UserDataPageDAOImpl();
		String keyword = request.getParameter("keyword");
		logger.info("keyword = " + keyword);

		// seach by which column
		String keywordSelect = request.getParameter("keywordSelect");
		UserDataPage pageBean = userDataDAO.getUserPageDataByCondition(5, page,
				keyword, keywordSelect);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("keyword", keyword);
		request.setAttribute("keywordSelect", keywordSelect);

		logger.info("pageBean = " + pageBean);
		logger.info("pageIndex = " + page);

		// 这个属性用来标记当前页面是通过点击用户管理链接得到的
		request.setAttribute("pagetrigger", "users_fromsearchbutton");
		return "users_querybycondition_success";
	}

	public String getTestInfo() {
		String uid = request.getParameter("uid");
		// UsersDAO userDAO = new UsersDAOImpl();
		TestInfoPageDAO testInfoPageDAO = new TestInfoPageDAOImpl();

		TestInfoPage userTestInfo = testInfoPageDAO
				.getTestInfoPageDataByUserID(5, page, uid);

		logger.info("userTestInfo = " + userTestInfo);
		logger.info("uid = " + uid);

		request.setAttribute("usertestinfo", userTestInfo);
		request.setAttribute("curuserid", uid);

		request.setAttribute("pagetrigger", "testinfo_fromTestTimes");

		return "users_gettestinfo_success";
	}

	public String queryByPage() throws Exception {
		logger.info("Start to show user information page by page");
		UserDataPageDAO userDataPageDAO = new UserDataPageDAOImpl();
		// 表示每页显示5条记录，page表示当前网页
		UserDataPage pageBean = userDataPageDAO.getUserPageData(5, page);
		request.setAttribute("pageBean", pageBean);
		logger.info("pageBean = " + pageBean);
		logger.info("pageIndex = " + page);

		// 这个属性用来标记当前页面是通过点击用户管理链接得到的
		request.setAttribute("pagetrigger", "users_fromleftlink");

		return "users_querybypage_success";
	}

	public String filterByPage() {
		logger.info("Start to filter user information page by page");
		String filterType = request.getParameter("userFilterType");
		String startTime = request.getParameter("starttime");
		String endTime = request.getParameter("endtime");

		logger.info("filterType = " + filterType + ", startTime = " + startTime
				+ ", endTime" + endTime);

		String bindCondition = "绑定";
		if (filterType.equals("unbinded")) {
			bindCondition = "未绑定";
		}
		logger.info("bindCondition = " + bindCondition);

		UserDataPageDAO userDataPageDAO = new UserDataPageDAOImpl();
		// 表示每页显示5条记录，page表示当前网页
		// UserDataPage pageBean = userDataPageDAO.getUserPageData(5, page);

		UserDataPage pageBean = null;

		if (filterType.equals("timerange")) {
			pageBean = userDataPageDAO.getUserPageDataByTimeRange(5, page,
					startTime, endTime);
		} else {
			pageBean = userDataPageDAO.getUserPageDataByBindStatus(5, page,
					bindCondition);
		}

		request.setAttribute("pageBean", pageBean);

		request.setAttribute("keyword", keyword);
		request.setAttribute("userFilterType", filterType);
		request.setAttribute("bindCondition", bindCondition);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);

		logger.info("pageBean = " + pageBean);
		logger.info("pageIndex = " + page);

		// 这个属性用来标记当前页面是通过点击用户管理链接得到的
		request.setAttribute("pagetrigger", "users_fromfilterbutton");

		return "users_filterbypage_success";

	}

	public String query() {
		UsersDAO sdao = new UsersDAOImpl();

		List<Users> list = sdao.queryAllUsers();

		logger.info("In query(): list = " + list);

		if (list != null && list.size() > 0) {
			session.setAttribute("users_list", list);
		}

		return "query_success";
	}

	public String delete() {
		UsersDAO sdao = new UsersDAOImpl();
		String uid = request.getParameter("uid");
		String pageNum = request.getParameter("pageNum");

		logger.info("In delete(): pageIndex = " + pageNum);

		setPage(Integer.parseInt(pageNum));

		sdao.deleteUsers(uid);

		return "delete_success";
	}

	public String deleteInSearch() {
		UsersDAO sdao = new UsersDAOImpl();
		String uid = request.getParameter("uid");
		String pageNum = request.getParameter("pageNum");

		// System.out.println("===>pageNum = " + pageNum);
		logger.info("In deleteInSearch(): pageIndex = " + pageNum);

		setPage(Integer.parseInt(pageNum));

		sdao.deleteUsers(uid);

		return "deleteinsearch_success";
	}

	public String add() {
		String address = request.getParameter("address");
		// Date birthday = new Date(request.getParameter("birthday"));
		String gender = request.getParameter("gender");
		String name = request.getParameter("sname");

		String birthday = request.getParameter("birthday");
		Users stu = new Users();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(birthday);
			// stu.setBirthday(date);
			// System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("birthday = " + birthday);

		// stu.setAddress(address);

		stu.setGender(gender);
		// stu.setSname(name);

		UsersDAO sdao = new UsersDAOImpl();
		sdao.addUsers(stu);

		return "add_success";
	}

	public String modify() {
		String sid = request.getParameter("sid");
		UsersDAO sdao = new UsersDAOImpl();
		Users stu = sdao.queryUsersBySid(sid);

		session.setAttribute("modify_Users", stu);
		return "modify_success";
	}

	public String save() {
		String address = request.getParameter("address");
		// Date birthday = new Date(request.getParameter("birthday"));
		String gender = request.getParameter("gender");
		String name = request.getParameter("sname");

		String birthday = request.getParameter("birthday");
		Users stu = new Users();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(birthday);
			// stu.setBirthday(date);
			// System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("birthday = " + birthday);

		// stu.setAddress(address);

		stu.setGender(gender);
		// stu.setSname(name);
		// stu.setSid(request.getParameter("sid"));
		UsersDAO sdao = new UsersDAOImpl();
		// sdao.updateUsers(stu);

		return "save_success";
	}

	public String queryInTestInfoPage() {
		String columnToUsed = "focusValue";
		String columnSelect = request.getParameter("testInfoColumn");
		String keyword = request.getParameter("keyword");
		String uid = request.getParameter("uid");

		String columns[] = { "focusValue", "relaxValue", "pressIndex",
				"tiredIndex", "improvedIndex", "heartRate", "heartVariate",
				"usedPattern", "music", "timeDuration" };

		for (String item : columns) {
			if (columnSelect.equals(item)) {
				columnToUsed = item;
				break;
			}
		}

		logger.info("Column used for search: " + columnToUsed);

		TestInfoPageDAO testInfoPageDAO = new TestInfoPageDAOImpl();

		TestInfoPage pageBean = testInfoPageDAO.queryTestInfoByCondition(5,
				page, uid, keyword, columnToUsed);

		request.setAttribute("usertestinfo", pageBean);
		request.setAttribute("keyword", keyword);
		request.setAttribute("columnSelect", columnSelect);
		request.setAttribute("curuserid", uid);
		request.setAttribute("pagetrigger", "testinfo_fromSearchButton");

		logger.info("page bean = " + pageBean);

		return "users_query_test_info_success";
	}

	public String filterInTestInfoPage() {

		String columnToUsed = "testDate";
		String columnSelect = request.getParameter("testInfoColumn");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String uid = request.getParameter("uid");

		logger.info("columnToUsed = " + columnToUsed + " startTime = "
				+ startTime + " endTime = " + endTime);

		TestInfoPageDAO testInfoPageDAO = new TestInfoPageDAOImpl();

		TestInfoPage pageBean = testInfoPageDAO.filterTestInfoByCondition(5,
				page, uid, startTime, endTime, columnToUsed);

		request.setAttribute("usertestinfo", pageBean);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);

		request.setAttribute("curuserid", uid);
		request.setAttribute("pagetrigger", "testinfo_fromFilterButton");
		logger.info("page bean = " + pageBean);

		return "users_filter_test_info_success";
	}

	public String upload() {

		logger.info("filename = " + this.yourFileFileName);
		logger.info("filetype = " + this.yourFileContentType);

		String path = ServletActionContext.getServletContext().getRealPath(
				uploadDir);

		logger.info("path = " + path);

		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String newFileName;
		long now = new Date().getTime();

		newFileName = yourFileFileName + Long.toString(now);
		logger.info("new File Name = " + newFileName);

		try {
			FileInputStream fis = new FileInputStream(yourFile);
			bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream(new File(dir,
					newFileName));

			bos = new BufferedOutputStream(fos);

			byte[] buf = new byte[4096];

			int len = -1;

			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != bis)
					bis.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {

				if (null != bos)
					bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return "users_upload_success";
	}

	public void saveUploadFile(String newFileName) {

		String path = ServletActionContext.getServletContext().getRealPath(
				uploadDir);

		logger.info("path = " + path);

		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		logger.info("new File Name = " + newFileName);

		try {
			FileInputStream fis = new FileInputStream(this.userIconFile);
			bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream(new File(dir,
					newFileName));

			bos = new BufferedOutputStream(fos);

			byte[] buf = new byte[4096];

			int len = -1;

			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != bis)
					bis.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {

				if (null != bos)
					bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

	public String verifyUser() {

		String phoneNum = request.getParameter("phoneNum");
		if (phoneNum == null)
		{
			phoneNum = "null";
		}

		logger.info("phone num = " + phoneNum);
		UsersDAO userDAO = new UsersDAOImpl();

		UserVerifyInfo uvi = userDAO.getUserVeryfiInfo(phoneNum);

		if(uvi != null)
		{
			logger.info("time stamp = " + uvi.getTimeStamp());
		}

		Users user = null;

		if (uvi != null) {
			user = userDAO.queryUsersBySid(phoneNum);
		}

		logger.info("user info = " + user);

		if (uvi != null) {
			request.setAttribute("UserVerifyTimeStamp", uvi.getTimeStamp());

			if (user != null) {
				request.setAttribute("UserName", user.getUsername());
				request.setAttribute("UserAge", user.getAge());
				request.setAttribute("UserGender", user.getGender());
				request.setAttribute("UserIconURL", user.getUserIconURL());
			} else {
				request.setAttribute("UserVerifyTimeStamp", "null");
				request.setAttribute("UserName", "null");
				request.setAttribute("UserAge", "null");
				request.setAttribute("UserGender", "null");
				request.setAttribute("UserIconURL", "null");
			}
		} else {
			request.setAttribute("UserVerifyTimeStamp", "null");
			request.setAttribute("UserName", "null");
			request.setAttribute("UserAge", "null");
			request.setAttribute("UserGender", "null");
			request.setAttribute("UserIconURL", "null");
		}

		if (uvi == null) {
			return "users_verify_failed";
		}

		return "users_verify_success";
	}

	public String uploadUserInfo() {
		String userID = request.getParameter("UserID");
		String userName = request.getParameter("UserName");
		String userAge = request.getParameter("UserAge");
		String userGender = request.getParameter("UserGender");
		String timeStamp = request.getParameter("TimeStamp");

		logger.info("userID = " + userID);
		logger.info("userName = " + userName);
		logger.info("userAge = " + userAge);
		logger.info("userGender = " + userGender);
		logger.info("timeStamp = " + timeStamp);
		
		if(timeStamp == null)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			timeStamp = dateFormat.format(date); 
		}	

		logger.info("final timeStamp = " + timeStamp);
		int age = Integer.parseInt(userAge);

		logger.info("UserIconFileName = " + this.userIconFileFileName);
		
		if(this.userIconFileFileName == null)
		{
			this.userIconFileFileName = "blank.jpg";
		}
		int pos = this.userIconFileFileName.indexOf('.');

		if (pos == -1)
			pos = this.userIconFileFileName.length();

		String fileSuffix = this.userIconFileFileName.substring(pos);

		String newFileName = userID + fileSuffix;
		
		//用户只有在上传头像文件的情况下才保存文件
		if(this.userIconFileFileName.equals("blank.jpg") == false)
		{
			saveUploadFile(newFileName);
		}
		String userIconURL = "/IntelliEyeShade/users/download.action?f="
				+ newFileName;
		
		if(this.userIconFileFileName.equals("blank.jpg"))
		{
			userIconURL = "/IntelliEyeShade/users/download.action?f=blank.jpg";
		}

		request.setAttribute("UserIconURL", userIconURL);

		logger.info("userIconURL = " + userIconURL);
		Users user = new Users();
		user.setUid(userID);
		user.setAge(age);
		user.setGender(userGender);
		user.setUsername(userName);
		user.setUserIconURL(userIconURL);

		UsersDAO udao = new UsersDAOImpl();
		
		boolean isUserExist = udao.isUserExist(userID);
	
		boolean insertResult = false;
		boolean updateResult = false;
		boolean insertVerifyResult = false;
		if(isUserExist == false)
		{
			insertResult = udao.addUsers(user);
			
			//同时在用户验证表中添加一条验证信息
			UserVerifyInfo uvi = new UserVerifyInfo();
			uvi.setPhoneNum(userID);
			uvi.setTimeStamp(timeStamp);
			insertVerifyResult = udao.addUserVerifyInfo(uvi);
			
		}
		else
		{
			updateResult = udao.updateUsers(user);
		}
		
		if(insertResult == true && updateResult == false)
		{
			request.setAttribute("UserUploadResult", "Success_Insert");			
		}
		else if(insertResult == false && updateResult == true)
		{
			request.setAttribute("UserUploadResult", "Success_Update");
		}
		else
		{
			request.setAttribute("UserUploadResult", "Falied");
		}

		return "users_upload_userinfo_success";
	}

	
	public String bindDevice()
	{
		String uid = request.getParameter("UserID");
		UsersDAO udao = new UsersDAOImpl();
		
		Users u = new Users();
		u.setUid(uid);
		u.setBindingStatus("已绑定");
		
		Users isUserExist = udao.queryUsersBySid(uid);
		
		if(isUserExist == null)
		{
			request.setAttribute("bindDeviceResult", "Failed_User_Not_Exist");
		}
		else
		{
			u.setAge(isUserExist.getAge());
			u.setGender(isUserExist.getGender());
			u.setDeviceID(isUserExist.getDeviceID());
			u.setPhoneNum(isUserExist.getPhoneNum());
			u.setRegisterDate(isUserExist.getRegisterDate());
			u.setTestTimes(isUserExist.getTestTimes());
			u.setUserIconURL(isUserExist.getUserIconURL());
			u.setUsername(isUserExist.getUsername());
			
			boolean updateResult = udao.updateUsers(u);
			
			if(updateResult == true)
			{
				request.setAttribute("bindDeviceResult", "Success");
			}
			else
			{
				request.setAttribute("bindDeviceResult", "Failed");
			}
		}
		
		
		
		
		return "users_bind_device_success";
	}
	
	public String unBindDevice()
	{
		String uid = request.getParameter("UserID");
		UsersDAO udao = new UsersDAOImpl();
		
		Users u = new Users();
		u.setUid(uid);
		u.setBindingStatus("未绑定");
		
		Users isUserExist = udao.queryUsersBySid(uid);
		
		if(isUserExist == null)
		{
			request.setAttribute("bindDeviceResult", "Failed_User_Not_Exist");
		}
		else
		{
			u.setAge(isUserExist.getAge());
			u.setGender(isUserExist.getGender());
			u.setDeviceID(isUserExist.getDeviceID());
			u.setPhoneNum(isUserExist.getPhoneNum());
			u.setRegisterDate(isUserExist.getRegisterDate());
			u.setTestTimes(isUserExist.getTestTimes());
			u.setUserIconURL(isUserExist.getUserIconURL());
			u.setUsername(isUserExist.getUsername());
			
			boolean updateResult = udao.updateUsers(u);
			
			if(updateResult == true)
			{
				request.setAttribute("bindDeviceResult", "Success");
			}
			else
			{
				request.setAttribute("bindDeviceResult", "Failed");
			}
		}
		
		return "users_unbind_device_success";
	}
}
