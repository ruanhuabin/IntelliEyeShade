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

import db.MyHibernateSessionFactory;
public class TestUser {
	
	@Test
	public void testAddUser()
	{
		Users user = new Users("1", "ruanhuabin", "��", "123456", 30,  15, "δ��");
		Users user1 = new Users("2", "hanwenjing", "Ů", "123456", 29,  10, "δ��");
		Users user2 = new Users("3", "heconghui", "��", "123456", 28,  20, "δ��");
		Users user3 = new Users("4", "liyibin", "��", "123456", 27,  35, "δ��");
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		
		for(int i = 15; i < 30; i ++)
		{
			Users userx = new Users("" + i, "ruanhuabin", "��", "123456", 30,  15, "δ��" );
			session.save(userx);
		}
		
		tx.commit();
		MyHibernateSessionFactory.getSessionFactory().close();
	}
	
	@Test
	public void testDeleteUser()
	{
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		
		for(int i = 5; i < 100; i ++)
		{
			Users userx = new Users("" + i, "ruanhuabin", "��", "123456", 30,  15, "δ��" );
			session.delete(userx);
		}
		
		tx.commit();
		MyHibernateSessionFactory.getSessionFactory().close();
	}

}