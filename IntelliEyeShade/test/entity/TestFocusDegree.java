package entity;

import org.junit.Test;

import service.FocusDegreeDAO;
import serviceimpl.FocusDegreeDAOImpl;

public class TestFocusDegree {
	
	@Test
	public void testInsertFocusDegree()
	{
		String tid = "4321";
		String fds = "12 34 56 78 90";
		
		FocusDegree fd = new FocusDegree();
		
		fd.setTid(tid);
		fd.setFocusValues(fds);
		
		FocusDegreeDAO fdao = new FocusDegreeDAOImpl();
		
		fdao.insertFocusDegreeInfo(fd);
		
	}

}
