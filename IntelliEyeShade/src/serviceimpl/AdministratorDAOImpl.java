package serviceimpl;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Administrator;

import service.AdministratorDAO;


public class AdministratorDAOImpl implements AdministratorDAO {

	public boolean adminLogin( Administrator u) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Administrator where username=? and password=?";
			
			Query query = session.createQuery(hql);
			
			query.setParameter(0,  u.getUsername());
			query.setParameter(1, u.getPassword());
			
			List list = query.list();
			

			tx.commit();
			
			if(list.size() > 0)
				return true;
			else			
				return false;
			
			
			
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
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

//	@Override
//	public boolean userLogin() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
