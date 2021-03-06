package service;

import java.util.List;

import entity.TestInfo;
import entity.UserVerifyInfo;
import entity.Users;

public interface UsersDAO {

	public List<Users> queryAllUsers();
	public List<Users> queryByHQL(String hql);
	
	public Users getUserByID(String sid);
	
	public boolean addUsers(Users s);
	
	public boolean updateUsers(Users s);
	
	public boolean deleteUsers(String sid);
	
	public List<Users> queryByPage(String hql, int offset, int pageSize);
	    
	public int getAllRowCount(String hql);
	public int getAllRowCountByCondition(String column, String condition);

	public List<Users> queryByCondition(String hql, String condition,
			int offset, int pageSize);
	
	public List<TestInfo> getUserTestInfo(String uid);
	public UserVerifyInfo getUserVeryfiInfo(String phoneNum);
	boolean isUserExist(String uid);
	
	boolean addUserVerifyInfo(UserVerifyInfo uvi);
	public boolean updateUserVerifyInfo(UserVerifyInfo uvi);
}
