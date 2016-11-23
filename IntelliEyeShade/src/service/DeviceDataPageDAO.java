package service;

import java.util.List;

import entity.DeviceDataPage;
import entity.Devices;



public interface DeviceDataPageDAO {
	public DeviceDataPage getDevicePageData(int pageSize, int page);
	public DeviceDataPage getDevicePageDataByCondition(int pageSize, int page, String condition);
	List<Devices> queryByPage(String hql, int offset, int pageSize);

}
