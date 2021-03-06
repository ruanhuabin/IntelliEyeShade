package serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.TestInfo;
import entity.UserVerifyInfo;
import entity.Users;
import static util.IntelliEyeShadeLogger.logger;


import service.UsersDAO;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public List<Users> queryAllUsers() {
		// TODO Auto-generated method stub
		
		Transaction tx = null;
		List<Users> list = null;
		String hql = "";
		
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users";
			
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
			
			System.out.println("list = " + list);
			
			return list;
			
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return list;
			
		}
		finally
		{
			if(tx != null)
			{
				
				tx = null;
			}
		}
		
		
	}

	@Override
	public Users getUserByID(String sid) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;
		Users s = null;
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			s = (Users) session.get(Users.class, sid);
			tx.commit();
			
			
			
			return s;
			
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return s;
			
		}
		finally
		{
			if(tx != null)
			{
				
				tx = null;
			}
		}
		
	}
	@Override
	public boolean isUserExist(String uid)
	{
		Transaction tx = null;
		Users s = null;
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			s = (Users) session.get(Users.class, uid);
			tx.commit();
			
			if(s != null)
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
			
		}
		finally
		{
			if(tx != null)
			{
				
				tx = null;
			}
		}
		
		
	}

	@Override
	public boolean addUsers(Users s) {
		// TODO Auto-generated method stub
		
		//s.setUid(getStuID());
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.save(s);
			
			tx.commit();
			
			return true;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
			
		}
		
		finally
		{
			if(tx != null)
			{
				tx = null;
			}
			
			
		}
		
		
		
		
	}

	@Override
	public boolean updateUsers(Users s) {
		// TODO Auto-generated method stub\
		
		Transaction tx = null;		
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.update(s);
			
			tx.commit();
			
			return true;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
			
		}
		
		finally
		{
			if(tx != null)
			{
				tx = null;
			}
		}
	
		
	}

	@Override
	public boolean deleteUsers(String sid) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;
		
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Users s = (Users)session.get(Users.class, sid);
			session.delete(s);
			
			tx.commit();
			
			return true;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
			
		}
		
		finally
		{
			if(tx != null)
			{
				tx = null;
			}
		}
	
	}
	
	public String getStuID()
	{
		
		Transaction tx = null;
		String hql = "";
		String sid = null;
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			//From 后面的名字 是hbm.xml中table属性所指定的名字
			hql = "select max(sid) from Users";
			//hql = "from Users";
			
			Query query = session.createQuery(hql);
			
			sid = (String)query.uniqueResult();
			//sid = ((Users)query.list().get(2)).getSid();
			
			if(sid == null || "".equals(sid))
			{
				
				sid = "S0000001";
			}
			else
			{
				String temp = sid.substring(1);
				int i = Integer.parseInt(temp);
				i ++;
				
				temp = String.valueOf(i);
				
				int len = temp.length();
				
				for(int j = 0; j < 6 - len; j ++)
				{
					temp = "0" + temp;
				}
				
				sid = "S" + temp;
			}
			
			tx.commit();
			return sid;
			
			
			
			
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			tx.commit();
			return null;
		}
		finally{
			
			
		}
	}

	@Override
	public List<Users> queryByPage(String hql, int offset, int pageSize) {
		// TODO Auto-generated method stub
		
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Users> list = null;
        
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);
            
            list = query.list();
            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
        }
        finally
        {
        	if(tx != null)
            {
                tx = null;
            }
        }
        
        
        return list;
	}
	
	
	public List<Users> queryByCondition(String hql, String condition, int offset, int pageSize) {
		// TODO Auto-generated method stub
		
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Users> list = null;
        
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);
            query.setString("name", "%" + condition +"%");
            
            list = query.list();
            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
        }
        finally
        {
        	if(tx != null)
            {
                tx = null;
            }
        }
        
        
        return list;
	}

	@Override
	public int getAllRowCount(String hql) {
		// TODO Auto-generated method stub
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int allRows = 0;
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);
            
            allRows = query.list().size();
            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
        }
        finally
        {
            if(tx != null)
            {
            	tx = null;
            }
        }
        
        return allRows;
		
	}

	@Override
	public int getAllRowCountByCondition(String columnName, String condition) {
		// TODO Auto-generated method stub
		
		String columnToUsed = "username";
		
		if(columnName.equals("gender"))
		{
			columnToUsed = "gender";
		}
		else if(columnName.equals("age"))
		{
			columnToUsed = "age";
		}
		else if(columnName.equals("phoneNum"))
		{
			columnToUsed = "phoneNum";
		}
		else if(columnName.equals("testTimes"))
		{
			columnToUsed = "testTimes";
		}
			
		logger.info("column used to search: " +  columnToUsed);
		
		//String hql = "from Users as u where u.username like :name";
		String hql = "from Users as u where u." + columnToUsed + " like :name";
		logger.info("hql = " + hql);
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int allRows = 0;
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);
            query.setString("name", "%" + condition +"%");
            
            List<Users> list =query.list();
            allRows = list.size();
            System.out.println("In Search by condition: list = " + list);
            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
        }
        finally
        {
            if(tx != null)
            {
            	tx = null;
            }
        }
        
        return allRows;
		
	}

	@Override
	public List<TestInfo> getUserTestInfo(String uid) {
		// TODO Auto-generated method stub
		
		
		String hql = "from TestInfo as t where t.uid = '" + uid + "'";
		logger.info("hql = " + hql);
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        List<TestInfo> list = null;
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);
            
            
            list =query.list();   
            
            logger.info("list = " + list);
            
            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
        }
        finally
        {
            if(tx != null)
            {
            	tx = null;
            }
        }
		return list;
	}

	@Override
	public List<Users> queryByHQL(String hql) {
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int allRows = 0;
        List<Users> users = null;
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);
            
            users = query.list();
            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
        }
        finally
        {
            if(tx != null)
            {
            	tx = null;
            }
        }
        
        return users;
	}

	@Override
	public UserVerifyInfo getUserVeryfiInfo(String phoneNum) {
		
		Transaction tx = null;		
		UserVerifyInfo uvi = null;
		
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
		    uvi = (UserVerifyInfo) session.get(UserVerifyInfo.class, phoneNum);
			tx.commit();
			return uvi;
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return uvi;
			
		}
		finally
		{
			if(tx != null)
			{
				tx = null;
			}
		}
		
	
	}

	@Override
	public boolean addUserVerifyInfo(UserVerifyInfo uvi) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();			
			session.save(uvi);
			tx.commit();
			return true;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
			
		}
		
		finally
		{
			if(tx != null)
			{
				tx = null;
				
			}
			
			
		}
		
	}

	@Override
	public boolean updateUserVerifyInfo(UserVerifyInfo uvi) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(uvi);
			tx.commit();
			return true;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
			
		}
		
		finally
		{
			if(tx != null)
			{
				tx = null;
			}
			
			
		}
	}

}
