package serviceimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.FocusDegree;
import entity.Users;
import service.FocusDegreeDAO;

public class FocusDegreeDAOImpl implements FocusDegreeDAO {

	@Override
	public FocusDegree getFocusDegreeInfo(String tid) {
		
		Transaction tx = null;
		FocusDegree fd = null;
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			fd = (FocusDegree) session.get(FocusDegree.class, tid);
			tx.commit();
			return fd;
			
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return fd;
			
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
	public boolean insertFocusDegreeInfo(FocusDegree fd) {

		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(fd);
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
	public boolean updateFocusDegreeInfo(FocusDegree focusDegree) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(focusDegree);
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
