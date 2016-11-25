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
		Users userInfo = userDAO.queryUsersBySid(uid);

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
		 * //�������������ǵ�ǰҳ����ͨ������û��������ӵõ��� request.setAttribute("pagetrigger",
		 * "users_fromsearchbutton"); return "users_querybycondition_success";
		 */

		return "devices_querybycondition_success";
	}

	public String queryByPage() throws Exception {
		logger.info("Start to show device information page by page");
		DeviceDataPageDAO deviceDataPageDAO = new DeviceDataPageDAOImpl();
		// ��ʾÿҳ��ʾ5����¼��page��ʾ��ǰ��ҳ
		DeviceDataPage pageBean = deviceDataPageDAO.getDevicePageData(5, page);
		request.setAttribute("devicepagebean", pageBean);
		logger.info("pageBean = " + pageBean);
		logger.info("pageIndex = " + page);
		request.setAttribute("pagetrigger", "devices_fromleftlink");

		/*
		 * logger.info("Start to show user information page by page");
		 * UserDataPageDAO userDataPageDAO = new UserDataPageDAOImpl();
		 * //��ʾÿҳ��ʾ5����¼��page��ʾ��ǰ��ҳ UserDataPage pageBean =
		 * userDataPageDAO.getUserPageData(5, page);
		 * request.setAttribute("pageBean", pageBean); logger.info("pageBean = "
		 * + pageBean); logger.info("pageIndex = " + page);
		 * 
		 * //�������������ǵ�ǰҳ����ͨ������û��������ӵõ��� request.setAttribute("pagetrigger",
		 * "users_fromleftlink");
		 */

		return "device_querybypage_success";
	}
	
	public String filterByPage()
    {
    	logger.info("Start to filter device information page by page");
    	String filterType = request.getParameter("deviceFilterType");
    	
    	
    	logger.info("filterType = " + filterType );
    	
    	String bindCondition = "�Ѱ�";
    	String statusCondition = "����";
    	if(filterType.equals("unbinded"))
    	{
    		bindCondition = "δ��";
    	}
    	
    	if(filterType.equals("unormalStatus"))
    	{
    		statusCondition = "�쳣";
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
        
      //�������������ǵ�ǰҳ����ͨ������û��������ӵõ���
        request.setAttribute("pagetrigger", "users_fromfilterbutton");
        
        return "devices_filterbypage_success";
    	
    }
	

}