package serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Devices;
import entity.Users;

import service.DevicesDAO;
import static util.IntelliEyeShadeLogger.logger;

public class DevicesDAOImpl implements DevicesDAO {

	@Override
	public Devices getDeviceInfo(String deviceID) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;		
		String hql = "";
		Devices s = null;
		
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			s = (Devices) session.get(Devices.class, deviceID);
			
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
	public int getAllRowCountByCondition(String columnToUsed, String condition) {
		// TODO Auto-generated method stub
		
		String hql = "from Devices as d where d." + columnToUsed + " like :name";
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int allRows = 0;
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);
            query.setString("name", "%" + condition +"%");
            
            List<Devices> list =query.list();
            allRows = list.size();
            logger.info("In Search by condition: list = " + list);
            
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
	public List<Devices> queryByCondition(String hql, String condition, int offset, int pageSize) {
		// TODO Auto-generated method stub
		
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Devices> list = null;
        
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
	public List<Devices> queryByPage(String hql, int offset, int pageSize) {
		// TODO Auto-generated method stub
		
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Devices> list = null;
        
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
	public boolean addDevice(Devices newDevice)
	{
		
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.save(newDevice);
			
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
	public boolean updateDevice(Devices device) {
		
		Transaction tx = null;		
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.update(device);
			
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
