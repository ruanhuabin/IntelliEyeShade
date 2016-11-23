package serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;

import entity.DeviceDataPage;
import entity.Devices;
import entity.UserDataPage;
import entity.Users;
import service.DeviceDataPageDAO;
import service.DevicesDAO;
import service.UserDataPageDAO;
import service.UsersDAO;

public class DeviceDataPageDAOImpl implements DeviceDataPageDAO {
	
	private DevicesDAO deviceDAO = new DevicesDAOImpl();

	@Override
	public DeviceDataPage getDevicePageData(int pageSize, int page) 
	{
		// TODO Auto-generated method stub
		
		DeviceDataPage pageBean = new DeviceDataPage();
        
        String hql = "from Devices";
        
        int allRows = deviceDAO.getAllRowCount(hql);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        List<Devices> list = deviceDAO.queryByPage(hql, offset, pageSize);
        
        System.out.println("user list = " + list);
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
		return pageBean;
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
	public DeviceDataPage getDevicePageDataByCondition(int pageSize, int page,
			String condition) 
	{
		
		// TODO Auto-generated method stub
		DeviceDataPage pageBean = new DeviceDataPage();
        
       // String hql = "from Users as u where u.username like \"%" + condition + "%\"";
		 String hql = "from Devices as d where d.deviceID like :name";
        
        int allRows = deviceDAO.getAllRowCountByCondition(condition);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        List<Devices> list = deviceDAO.queryByCondition(hql, condition, offset, pageSize);
        
        System.out.println("=======>In getDevicePageDataByCondition():  device list = " + list);
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
		return pageBean;
	}

}
