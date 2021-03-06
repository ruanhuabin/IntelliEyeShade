package service;

import java.util.List;

import entity.Devices;
import entity.Users;

public interface DevicesDAO {
	
	public Devices getDeviceInfo(String deviceID);

	int getAllRowCount(String hql);

	int getAllRowCountByCondition(String columnToUsed, String keyword);

	List<Devices> queryByCondition(String hql, String condition, int offset,
			int pageSize);

	List<Devices> queryByPage(String hql, int offset, int pageSize);

	public boolean addDevice(Devices newDevice);

	public boolean updateDevice(Devices device);
	

//	public List<Users> queryAllUsers();
//	
//	public Users queryUsersBySid(String sid);
//	
//	public boolean addUsers(Users s);
//	
//	public boolean updateUsers(Users s);
//	
//	public boolean deleteUsers(String sid);
//	
//	public List<Users> queryByPage(String hql, int offset, int pageSize);
//	    
//	public int getAllRowCount(String hql);
//	public int getAllRowCountByCondition(String condition);
//
//	public List<Users> queryByCondition(String hql, String condition,
//			int offset, int pageSize);
}
