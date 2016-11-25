package service;

import java.util.List;

import entity.DeviceDataPage;
import entity.Devices;



public interface DeviceDataPageDAO {
	public DeviceDataPage getDevicePageData(int pageSize, int page);
	public DeviceDataPage getDevicePageDataByCondition(int pageSize, int page, String condition, String keywordFilter);
	List<Devices> queryByPage(String hql, int offset, int pageSize);
	public DeviceDataPage getDevicePageDataByDeviceStatus(int pageSize, int page,
			String statusCondition);
	public DeviceDataPage getDevicePageDataByBindStatus(int pageSize, int page,
			String bindCondition);

}
