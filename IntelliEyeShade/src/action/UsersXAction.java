package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;




import entity.TestInfo;
import entity.TestInfoPage;
import entity.UserDataPage;
import entity.Users;
import service.TestInfoDAO;
import service.TestInfoPageDAO;
import service.UserDataPageDAO;
import service.UsersDAO;
import serviceimpl.TestInfoDAOImpl;
import serviceimpl.TestInfoPageDAOImpl;
import serviceimpl.UserDataPageDAOImpl;
import serviceimpl.UsersDAOImpl;

public class UsersXAction extends SuperAction {

	private static final long serialVersionUID = 1L;	    
    private static final Logger logger = Logger.getLogger("MyLogger");  
    //Attributes for user upload info
    
    private String yourFileFileName;    
    private String yourFileContentType;
    private File yourFile;
    
    private String uploadDir;

    public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getYourFileFileName() {
		return yourFileFileName;
	}

	public void setYourFileFileName(String yourFileFileName) {
		this.yourFileFileName = yourFileFileName;
	}

	
	public File getYourFile() {
		return yourFile;
	}

	public void setYourFile(File yourFile) {
		this.yourFile = yourFile;
	}

	public String getYourFileContentType() {
		return yourFileContentType;
	}

	public void setYourFileContentType(String yourFileContentType) {
		this.yourFileContentType = yourFileContentType;
	}

		
	
	public String upload()
	{
		
		logger.info("filename = " + this.yourFileFileName);
		logger.info("filetype = " + this.yourFileContentType);
		
		String path = ServletActionContext.getServletContext().getRealPath(uploadDir);
		
		logger.info("path = " + path);
		
		File dir = new File(path);
		if(!dir.exists())
			dir.mkdir();
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		String newFileName;
		long now = new Date().getTime();
		
		newFileName = yourFileFileName + Long.toString(now);
		logger.info("new File Name = " + newFileName);
		
		try {
			FileInputStream fis = new FileInputStream(yourFile);
			bis = new BufferedInputStream(fis);
			
			FileOutputStream fos = new FileOutputStream(new File(dir, newFileName));
			
			bos = new BufferedOutputStream(fos);
			
			byte[] buf = new byte[4096];
			
			int len = -1;
			
			while((len = bis.read(buf)) != -1)
			{
				bos.write(buf, 0, len);
			}
			
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				
			
				if(null != bis)
					bis.close();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				
				if(null != bos)
					bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
				
		
		return SUCCESS;
	}
}
