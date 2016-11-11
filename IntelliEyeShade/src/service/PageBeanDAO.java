package service;

import entity.PageBean;

public interface PageBeanDAO {
	public PageBean getPageBean(int pageSize, int page);

}
