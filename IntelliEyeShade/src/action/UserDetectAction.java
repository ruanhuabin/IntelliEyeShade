package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;

import entity.DetectDetail;
import entity.FocusDegree;

import service.DetectDetailDAO;
import service.FocusDegreeDAO;
import serviceimpl.DetectDetailDAOImpl;
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
		String userID = request.getParameter("UserID");
		logger.info("userID = " + userID);
		
		
		if(detectID == null)
		{
			request.setAttribute("DetectFileUploadResult", "Failed_DETECT_ID_IS_NULL");
			return "detects_upload_detectinfo_failed";
		}
		
		if(userID == null)
		{
			request.setAttribute("DetectFileUploadResult", "Failed_USER_ID_IS_NULL");
			return "detects_upload_detectinfo_failed";
		}
		String newFileName = userID + "_" + detectID + ".dat";
		
		saveUploadFile(newFileName);
		
		
		
		String path = ServletActionContext.getServletContext().getRealPath(
				detectFileUploadDir);
		String fileToRead = path + "/" + newFileName;
		
		logger.info("fileToRead = " + fileToRead);
		
		//�ӱ����ļ�ϵͳ�ж�ȡ�ϴ����û��������
		Properties prop = new Properties();
		String focusDegrees = null;
		String relaxDegrees = null;
		String heartRates = null;
		String heartRateVariations = null;
		String brainRawData = null;
		InputStream input = null;
		try {
			
			input = new FileInputStream(fileToRead);
			prop.load(input);
			
			focusDegrees = prop.getProperty("FocusDegree");
			relaxDegrees = prop.getProperty("RelaxDegree");
			heartRates = prop.getProperty("HeartRate");
			heartRateVariations = prop.getProperty("HeartRateVariation");
			brainRawData = prop.getProperty("BrainRawData");
			logger.info("focusDegrees = " + focusDegrees);
			logger.info("relaxDegrees = " + relaxDegrees);
			logger.info("heartRates = " + heartRates);
			logger.info("heartRateVariations = " + heartRateVariations);
			logger.info("brainRawData = " + brainRawData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
		
		
		DetectDetailDAO fdao = new DetectDetailDAOImpl();
		
		DetectDetail detectDetail = fdao.getDetectDetail(detectID);
		
		
		if(detectDetail == null)
		{
			DetectDetail dd = new DetectDetail();
			dd.setDid(detectID);
			dd.setUid(userID);
			dd.setFocusDegrees(focusDegrees);
			dd.setRelaxDegrees(relaxDegrees);
			dd.setHeartRates(heartRates);
			dd.setHeartRateVariations(heartRateVariations);
			dd.setBrainRawData(brainRawData);
			
			fdao.insertDetectDetailInfo(dd);			
			request.setAttribute("DetectFileUploadResult", "Success_Insert");
			
		}
		else
		{
			DetectDetail dd = new DetectDetail();
			dd.setDid(detectDetail.getDid());
			dd.setUid(detectDetail.getUid());			
			dd.setFocusDegrees(focusDegrees);
			dd.setRelaxDegrees(relaxDegrees);
			dd.setHeartRates(heartRates);
			dd.setHeartRateVariations(heartRateVariations);
			dd.setBrainRawData(brainRawData);			
			fdao.updateDetectDetail(dd);			
			request.setAttribute("DetectFileUploadResult", "Success_Update");
		}
		return "detects_upload_detectinfo_success";
		
		
		
		
	}

}