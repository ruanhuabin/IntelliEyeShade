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

	public String listDeviceDetails()
	{
		
		String deviceID = request.getParameter("deviceID");
		String uid = request.getParameter("uid");
		System.out.println("In listDeviceDetails(), deviceID = " + deviceID + ", uid = " + uid);
		
		DevicesDAO deviceDAO = new DevicesDAOImpl();
		Devices deviceInfo = deviceDAO.getDeviceInfo(deviceID);
		
		UsersDAO userDAO = new UsersDAOImpl();
		Users userInfo = userDAO.queryUsersBySid(uid);
		
		request.setAttribute("deviceinfo", deviceInfo);
		request.setAttribute("userinfo", userInfo);
		
		System.out.println("In listDeviceDetails(), deviceInfo = " + deviceInfo);
		System.out.println("In listDeviceDetails(), userInfo = " + userInfo);
		
		return "device_list_details_success";
		
				
	}
	
	
	public String queryByCondition()
    {
    	DeviceDataPageDAO deviceDataDAO = new DeviceDataPageDAOImpl();
    	String keyword = request.getParameter("keyword");
    	String keywordFilter = request.getParameter("keywordSelect");
    	System.out.println("keyword = " + keyword);
    	System.out.println("keywordFilter = " + keywordFilter);
    	DeviceDataPage pageBean = deviceDataDAO.getDevicePageDataByCondition(5, page, keyword);
    	request.setAttribute("devicepagebean", pageBean);
    	request.setAttribute("keyword", keyword);
    	        
        
    	
    	return "device_querybycondition_success";
    }
	
	
	
	
	
	public String queryByPage() throws Exception
    {
    	DeviceDataPageDAO userDataPageDAO = new DeviceDataPageDAOImpl();
        //表示每页显示5条记录，page表示当前网页
        DeviceDataPage pageBean = userDataPageDAO.getDevicePageData(5, page);	        
        request.setAttribute("devicepagebean", pageBean);
        
        System.out.println("pageBean = " + pageBean);
        
        System.out.println("====>page = " + page);
        
       
        
        return "device_querybypage_success";
    }

}
