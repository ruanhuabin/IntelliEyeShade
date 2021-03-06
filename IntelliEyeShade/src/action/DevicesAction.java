package action;

import entity.DeviceDataPage;
import entity.Devices;
import entity.UserDataPage;
import entity.Users;
import service.DeviceDataPageDAO;
import service.DevicesDAO;
import service.UserDataPageDAO;
import service.UsersDAO;
import serviceimpl.DeviceDataPageDAOImpl;
import serviceimpl.DevicesDAOImpl;
import serviceimpl.UserDataPageDAOImpl;
import serviceimpl.UsersDAOImpl;

import static util.IntelliEyeShadeLogger.logger;

public class DevicesAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int page;
	private String keyword;

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

	public String listDeviceDetails() {

		String deviceID = request.getParameter("deviceID");
		String uid = request.getParameter("uid");
		System.out.println("In listDeviceDetails(), deviceID = " + deviceID
				+ ", uid = " + uid);

		DevicesDAO deviceDAO = new DevicesDAOImpl();
		Devices deviceInfo = deviceDAO.getDeviceInfo(deviceID);

		UsersDAO userDAO = new UsersDAOImpl();
		Users userInfo = userDAO.getUserByID(uid);

		request.setAttribute("deviceinfo", deviceInfo);
		request.setAttribute("userinfo", userInfo);

		System.out
				.println("In listDeviceDetails(), deviceInfo = " + deviceInfo);
		System.out.println("In listDeviceDetails(), userInfo = " + userInfo);

		return "device_list_details_success";

	}

	public String queryByCondition() {
		DeviceDataPageDAO deviceDataDAO = new DeviceDataPageDAOImpl();
		String keyword = request.getParameter("keyword");
		String keywordFilter = request.getParameter("keywordSelect");
		System.out.println("keyword = " + keyword);
		System.out.println("keywordFilter = " + keywordFilter);
		DeviceDataPage pageBean = deviceDataDAO.getDevicePageDataByCondition(5,
				page, keyword, keywordFilter);
		request.setAttribute("devicepagebean", pageBean);
		request.setAttribute("keyword", keyword);
		request.setAttribute("keywordSelect", keywordFilter);

		request.setAttribute("pagetrigger", "devices_fromsearchbutton");

		/**
		 * UserDataPageDAO userDataDAO = new UserDataPageDAOImpl(); String
		 * keyword = request.getParameter("keyword"); logger.info("keyword = " +
		 * keyword);
		 * 
		 * 
		 * //seach by which column String keywordSelect =
		 * request.getParameter("keywordSelect"); UserDataPage pageBean =
		 * userDataDAO.getUserPageDataByCondition(5, page, keyword,
		 * keywordSelect); request.setAttribute("pageBean", pageBean);
		 * request.setAttribute("keyword", keyword);
		 * request.setAttribute("keywordSelect", keywordSelect);
		 * 
		 * logger.info("pageBean = " + pageBean); logger.info("pageIndex = " +
		 * page);
		 * 
		 * //这个属性用来标记当前页面是通过点击用户管理链接得到的 request.setAttribute("pagetrigger",
		 * "users_fromsearchbutton"); return "users_querybycondition_success";
		 */

		return "devices_querybycondition_success";
	}

	public String queryByPage() throws Exception {
		logger.info("Start to show device information page by page");
		DeviceDataPageDAO deviceDataPageDAO = new DeviceDataPageDAOImpl();
		// 表示每页显示5条记录，page表示当前网页
		DeviceDataPage pageBean = deviceDataPageDAO.getDevicePageData(5, page);
		request.setAttribute("devicepagebean", pageBean);
		logger.info("pageBean = " + pageBean);
		logger.info("pageIndex = " + page);
		request.setAttribute("pagetrigger", "devices_fromleftlink");

		/*
		 * logger.info("Start to show user information page by page");
		 * UserDataPageDAO userDataPageDAO = new UserDataPageDAOImpl();
		 * //表示每页显示5条记录，page表示当前网页 UserDataPage pageBean =
		 * userDataPageDAO.getUserPageData(5, page);
		 * request.setAttribute("pageBean", pageBean); logger.info("pageBean = "
		 * + pageBean); logger.info("pageIndex = " + page);
		 * 
		 * //这个属性用来标记当前页面是通过点击用户管理链接得到的 request.setAttribute("pagetrigger",
		 * "users_fromleftlink");
		 */

		return "device_querybypage_success";
	}
	
	public String filterByPage()
    {
    	logger.info("Start to filter device information page by page");
    	String filterType = request.getParameter("deviceFilterType");
    	
    	
    	logger.info("filterType = " + filterType );
    	
    	String bindCondition = "已绑定";
    	String statusCondition = "正常";
    	if(filterType.equals("unbinded"))
    	{
    		bindCondition = "未绑定";
    	}
    	
    	if(filterType.equals("unormalStatus"))
    	{
    		statusCondition = "异常";
    	}
    	logger.info("bindCondition = " + bindCondition);
    	logger.info("statusCondition = " + statusCondition);
    	
    	DeviceDataPageDAO deviceDataPageDAO = new DeviceDataPageDAOImpl();
    	DeviceDataPage pageBean = null;
    	
    	
    	
    	if(filterType.equals("normalStatus") || filterType.equals("unormalStatus"))
    	{
    		//pageBean = userDataPageDAO.getUserPageDataByTimeRange(5, page, startTime, endTime);
    		pageBean = deviceDataPageDAO.getDevicePageDataByDeviceStatus(5, page, statusCondition);
    	}
    	else
    	{
    		pageBean = deviceDataPageDAO.getDevicePageDataByBindStatus(5, page, bindCondition);
    	}
    	
        request.setAttribute("devicepagebean", pageBean);   
        
        logger.info("devicepagebean = " + pageBean);
        
        
    	request.setAttribute("deviceFilterType", filterType);
    	request.setAttribute("bindCondition", bindCondition);
    	request.setAttribute("bindCondition", bindCondition);
    	
    
    	
    	
        logger.info("pageBean = " + pageBean);
        logger.info("pageIndex = " + page);
        
      //这个属性用来标记当前页面是通过点击用户管理链接得到的
        request.setAttribute("pagetrigger", "users_fromfilterbutton");
        
        return "devices_filterbypage_success";
    	
    }
	
	
	
	public String uploadDeviceInfo()
	{
		String deviceID = request.getParameter("DeviceID");
		String userID = request.getParameter("UserID");
		String deviceVersion = request.getParameter("DeviceVersion");
		String romVersion = request.getParameter("RomVersion");
		
		logger.info("deviceID = " +deviceID);
		logger.info("userID = " + userID);
		logger.info("deviceVersion = " + deviceVersion);
		logger.info("romVersion = " + romVersion);
		
		UsersDAO udao = new UsersDAOImpl();
		Users user = udao.getUserByID(userID);
		
		if(user == null)
		{
			request.setAttribute("DeviceUploadResult", "Failed_User_Not_Exist");
		}
		else
		{
			String bindStatus = user.getBindingStatus();
			
			if(bindStatus == null || bindStatus.equals("未绑定"))
			{
				request.setAttribute("DeviceUploadResult", "Failed_Device_Not_Bind");
			}
			else
			{
				
				DevicesDAO deviceDAO = new DevicesDAOImpl();
				Devices device = deviceDAO.getDeviceInfo(deviceID);
				
				if(device == null)
				{
					Devices newDevice = new Devices();
					newDevice.setDeviceID(deviceID);
					newDevice.setDeviceVersion(deviceVersion);
					newDevice.setRomVersion(romVersion);
					newDevice.setUid(userID);
					newDevice.setBindingStatus(user.getBindingStatus());
					newDevice.setDeviceStatus("正常");
					deviceDAO.addDevice(newDevice);
					request.setAttribute("DeviceUploadResult", "Success_Insert");
					
				}
				else
				{
					device.setUid(userID);
					device.setDeviceVersion(deviceVersion);
					device.setRomVersion(romVersion);
					deviceDAO.updateDevice(device);
					request.setAttribute("DeviceUploadResult", "Success_Update");
				}
				
			}
		}
		return "devices_upload_deviceinfo_success";
	}
	
	
	

}
