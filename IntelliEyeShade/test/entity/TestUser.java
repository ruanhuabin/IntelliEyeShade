package entity;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import action.UsersAction;

import service.UsersDAO;
import serviceimpl.UsersDAOImpl;

import db.MyHibernateSessionFactory;
public class TestUser {
	
	@Test
	public void testAddUser()
	{
		Users user = new Users("1", "ruanhuabin", "��", "123456", 30,  15, "δ��", "device_1", "2016-11-11");
		Users user1 = new Users("2", "hanwenjing", "Ů", "123456", 29,  10, "δ��", "device_2", "2016-11-11");
		Users user2 = new Users("3", "heconghui", "��", "123456", 28,  20, "δ��",  "device_f3", "2016-11-11");
		Users user3 = new Users("4", "liyibin", "��", "123456", 27,  35, "δ��",  "device_4", "2016-11-11");
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		
		for(int i = 15; i < 30; i ++)
		{
			Users userx = new Users("" + i, "ruanhuabin", "��", "123456", 30,  15, "δ��", "device_" + i, "2016-11-11");
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
			Users userx = new Users("" + i, "ruanhuabin", "��", "123456", 30,  15, "δ��", "device_" + i, "2016-11-11");
			session.delete(userx);
		}
		
		tx.commit();
		MyHibernateSessionFactory.getSessionFactory().close();
	}

	@Test
	public void testSearchUserCountByCondition()
	{
		
		
	}
	
	@Test
	public void testSearchUserDataByCondition()
	{
		
		
	}
	
	
	@Test
	public void testIsUserExist()
	{
		String uid="3";
		UsersDAO udao = new UsersDAOImpl();
		
		Users u = udao.getUserByID(uid);
		
		if(u == null)
		{
			System.out.println("user is not exist");
		}
		else
		{
			System.out.println("user is exist");
		}
	}
	
	
	@Test
	public void testTrendAanlysis()
	{
		String userID = "def";
		UsersDAO udao = new UsersDAOImpl();
		//UsersAction.trendAnalysis();
	}
	
	
	@Test
	public void testProp()
	{
		try {
			FileInputStream fis = new FileInputStream("D:\\testdata\\detectdetail.txt");
			Properties prop = new Properties();
			prop.load(fis);
			
			String focusData = prop.getProperty("FocusDegree");
			String relaxData = prop.getProperty("relaxDegree");
			
			System.out.println("focusData = " + focusData);
			System.out.println("relaxData = " + relaxData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
