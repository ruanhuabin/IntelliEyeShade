package serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Users;


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
	public Users queryUsersBySid(String sid) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;
		List<Users> list = null;
		String hql = "";
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
	public boolean addUsers(Users s) {
		// TODO Auto-generated method stub
		
		//s.setUid(getStuID());
		Transaction tx = null;
		
		String hql = "";
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
		
		String hql = "";
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
		
		String hql = "";
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
			//From ��������� ��hbm.xml��table������ָ��������
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

}