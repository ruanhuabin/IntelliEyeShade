package serviceimpl;

import static util.IntelliEyeShadeLogger.logger;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;

import entity.TestInfo;
import entity.Users;
import service.TestInfoDAO;

public class TestInfoDAOImpl implements TestInfoDAO {

	@Override
	public List<TestInfo> queryByCondition(String condition, int offset, int pageSize) 
			
	{

		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<TestInfo> list = null;
        
        String hql = "from TestInfo as t where t.uid = '" + condition + "'";
        
        logger.info("hql = " + hql);
        
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);            
            
            list = query.list();
            
            
            tx.commit();
            
            logger.info("list = " + list);
            
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
	public List<TestInfo> queryByCondition(String condition, String columnToUsed, int offset, int pageSize) 
			
	{

		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<TestInfo> list = null;
        
        String hql = "from TestInfo as t where t." + columnToUsed + " like:name";
        
        logger.info("hql = " + hql);
        
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);
            query.setString("name", "%" + condition + "%");
            
            list = query.list();
            
            
            tx.commit();
            
            logger.info("list = " + list);
            
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
	public int getAllRowCountByCondition(String columnToUsed, String condition) 
	{
		
					
		logger.info("column used to search: " +  columnToUsed);
		
		//String hql = "from Users as u where u.username like :name";
		String hql = "from TestInfo as t where t." + columnToUsed + " like:name";
		logger.info("hql = " + hql);
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int allRows = 0;
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);            
            query.setString("name", "%" + condition +"%");
            
            List<TestInfo> list =query.list();
            allRows = list.size();
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
        
        return allRows;
		
		
		
		
	}


	@Override
	public int getAllRowCountByCondition(String uid, String columnToUsed,
			String condition) 
	{
		
		logger.info("column used to search: " +  columnToUsed);
		
		//String hql = "from Users as u where u.username like :name";
		String hql = "from TestInfo as t where t.uid = " + "uid and t."+ columnToUsed + " like:name";
		logger.info("hql = " + hql);
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int allRows = 0;
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);            
            query.setString("name", "%" + condition +"%");
            
            List<TestInfo> list =query.list();
            allRows = list.size();
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
        
        return allRows;
		
	}


	@Override
	public List<TestInfo> queryByCondition(String uid, String condition,
			String columnToUsed, int offset, int pageSize) {
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<TestInfo> list = null;
        
        String hql = "from TestInfo as t where t.uid = "  + uid + " and t."+ columnToUsed + " like:name";
        
        logger.info("hql = " + hql);
        
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);
            query.setString("name", "%" + condition + "%");
            
            list = query.list();
            
            
            tx.commit();
            
            logger.info("list = " + list);
            
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
	public int getAllRowCountByCondition(String uid, String columnToUsed,
			String startTime, String endTime) {
		logger.info("column used to search: " +  columnToUsed);
		
		//String hql = "from Users as u where u.username like :name";
		String hql = "from TestInfo as t where t.uid = " + "uid and t."+ columnToUsed + " >= '" + startTime + "' and t." + columnToUsed + " <= '" + endTime + "'";
		logger.info("hql = " + hql);
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int allRows = 0;
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);           
            
            
            List<TestInfo> list =query.list();
            allRows = list.size();
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
        
        return allRows;
	}


	@Override
	public List<TestInfo> queryByCondition(String uid, String startTime,
			String endTime, String columnToUsed, int offset, int pageSize) 
	{
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<TestInfo> list = null;
        
        String hql = "from TestInfo as t where t.uid = "  + uid + " and t."+ columnToUsed + " >= '" + startTime + "' and t." + columnToUsed + " <= '" + endTime + "'";
        
        logger.info("hql = " + hql);
        
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);          
            
            list = query.list();
            
            
            tx.commit();
            
            logger.info("list = " + list);
            
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

}