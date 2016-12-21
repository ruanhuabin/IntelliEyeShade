package serviceimpl;

import static util.IntelliEyeShadeLogger.logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.DetectDetail;
import entity.FocusDegree;
import service.DetectDetailDAO;

public class DetectDetailDAOImpl implements DetectDetailDAO {

	@Override
	public DetectDetail getDetectDetail(String did) {
		Transaction tx = null;
		DetectDetail dd = null;
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			dd = (DetectDetail) session.get(DetectDetail.class, did);
			tx.commit();
			return dd;
			
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return dd;
			
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
	public boolean insertDetectDetailInfo(DetectDetail dd) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(dd);
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
	public boolean updateDetectDetail(DetectDetail dd) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(dd);
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
	public void deleteDetectDetail(String did) {
		Transaction tx = null;
		
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("delete from DetectDetail where tid = '" + did + "'");
			query.executeUpdate();
			tx.commit();
			
			logger.info("Delete DetectDetail Item Success, tid = " + did);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			logger.info("Delete DetectDetail Item Failed, tid = " + did);
			
			
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
