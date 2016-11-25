package entity;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import service.UsersDAO;
import serviceimpl.UsersDAOImpl;

import db.MyHibernateSessionFactory;
public class TestTestInfo {
	
	@Test
	public void testAddTestInfo()
	{
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		
		for(int i = 15; i < 30; i ++)
		{
			TestInfo testinfo = new TestInfo("" + 15, new Date(), i + 60, i + 60, i + 60, i + 60, i + 60, i + 60, i + 60, i + 60, "1", "1");
			session.save(testinfo);
		}
		
		tx.commit();
		MyHibernateSessionFactory.getSessionFactory().close();
	}
	
	
	
	

}
