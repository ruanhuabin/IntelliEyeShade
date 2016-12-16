package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;

import entity.FocusDegree;

import service.FocusDegreeDAO;
import serviceimpl.FocusDegreeDAOImpl;

public class UserDetectAction extends SuperAction {
	
	//Attributes for Detect info upload
	private String userDetectFileFileName;
	private String userDetectFileContentType;
	private File userDetectFile;

	
	private String detectFileUploadDir;
	
	private static final Logger logger = Logger.getLogger("MyLogger");

	
	
	
	public String getDetectFileUploadDir() {
		return detectFileUploadDir;
	}



	public void setDetectFileUploadDir(String DetectFileUploadDir) {
		this.detectFileUploadDir = DetectFileUploadDir;
	}



	public String getUserDetectFileFileName() {
		return userDetectFileFileName;
	}



	public void setUserDetectFileFileName(String userDetectFileFileName) {
		this.userDetectFileFileName = userDetectFileFileName;
	}



	public String getUserDetectFileContentType() {
		return userDetectFileContentType;
	}



	public void setUserDetectFileContentType(String userDetectFileContentType) {
		this.userDetectFileContentType = userDetectFileContentType;
	}



	public File getUserDetectFile() {
		return userDetectFile;
	}



	public void setUserDetectFile(File userDetectFile) {
		this.userDetectFile = userDetectFile;
	}

	public void saveUploadFile(String newFileName) {

		String path = ServletActionContext.getServletContext().getRealPath(
				detectFileUploadDir);

		logger.info("path = " + path);

		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		logger.info("new File Name = " + newFileName);

		try {
			FileInputStream fis = new FileInputStream(this.userDetectFile);
			bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream(new File(dir,
					newFileName));

			bos = new BufferedOutputStream(fos);

			byte[] buf = new byte[4096];

			int len = -1;

			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != bis)
					bis.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {

				if (null != bos)
					bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

	public String uploadDeviceDetectInfo()
	{
		
		String detectID = request.getParameter("DetectID");
		logger.info("tid = " + detectID);
		logger.info("DetectFileFilename = " + this.userDetectFileFileName);
		
		
		if(detectID == null)
		{
			request.setAttribute("DetectFileUploadResult", "Failed_TID_IS_NULL");
			return "detects_upload_detectinfo_failed";
		}
		String newFileName = detectID;
		
		saveUploadFile(newFileName);
		
		String path = ServletActionContext.getServletContext().getRealPath(
				detectFileUploadDir);
		String fileToRead = path + "/" + newFileName;
		
		logger.info("fileToRead = " + fileToRead);
		
		FocusDegreeDAO fdao = new FocusDegreeDAOImpl();
		
		FocusDegree fd = fdao.getFocusDegreeInfo(detectID);
		
		
		if(fd == null)
		{
			FocusDegree focusDegree = new FocusDegree();
			focusDegree.setTid(detectID);
			focusDegree.setFocusValues("123 456 789");
			
			fdao.insertFocusDegreeInfo(focusDegree);			
			request.setAttribute("DetectFileUploadResult", "Success_Insert");
			
		}
		else
		{
			FocusDegree focusDegree = new FocusDegree();
			focusDegree.setTid(detectID);
			focusDegree.setFocusValues("abc def hig");
			
			fdao.updateFocusDegreeInfo(focusDegree);			
			request.setAttribute("DetectFileUploadResult", "Success_Update");
		}
		
		
		
		
		
		return "detects_upload_detectinfo_success";
		
		
		
		
	}

}
