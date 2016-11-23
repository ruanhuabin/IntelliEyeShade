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
public class TestAdministrator {
	@Test
	public void testSchemaExport()
	{
		
		//测试
		Configuration config = new Configuration().configure();
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		
		Session session = sessionFactory.getCurrentSession();
		
		
		
		SchemaExport export = new SchemaExport(config);
		
		//第一个true：生成表结构
		//第二个true：生成sql语句
		export.create(true, true);
		
		
		
		
	}

	
	@Test
	public void testAddAdmin()
	{
		
		Administrator admin = new Administrator(1, "zhansan", "123456");
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(admin);
		
		tx.commit();
		MyHibernateSessionFactory.getSessionFactory().close();
		
	}
}
