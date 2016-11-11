package service;

import java.util.List;

import entity.Users;

public interface UsersDAO {

	public List<Users> queryAllUsers();
	
	public Users queryUsersBySid(String sid);
	
	public boolean addUsers(Users s);
	
	public boolean updateUsers(Users s);
	
	public boolean deleteUsers(String sid);
	
	public List<Users> queryByPage(String hql, int offset, int pageSize);
	    
	public int getAllRowCount(String hql);
}