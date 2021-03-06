package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;

import javax.swing.text.DefaultEditorKit.InsertTabAction;

import org.apache.struts2.ServletActionContext;

import config.Constant;

import entity.DetectDetail;
import entity.FocusDegree;
import entity.TestInfo;
import entity.Users;

import service.DetectDetailDAO;
import service.FocusDegreeDAO;
import service.TestInfoDAO;
import service.UsersDAO;
import serviceimpl.DetectDetailDAOImpl;
import serviceimpl.FocusDegreeDAOImpl;
import serviceimpl.TestInfoDAOImpl;
import serviceimpl.UsersDAOImpl;

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

	private int calAvg(String values)
	{
		int avg = 0;
		int sum = 0;
		String[] strNums = values.split(" ");
		
		//logger.info("strNums len = " + strNums.length + ", strNums = " + strNums + ", values = " + values);
		
		
		for(String num: strNums)
		{
			if(num.equals(""))
			{
				//logger.info("====>num is space");
				continue;
			}
			int intNum = Integer.parseInt(num);
			sum += intNum;
		}
		
		avg = sum / strNums.length;
		return  avg;
	}
	public String uploadDeviceDetectInfo()
	{
		
		//String detectID = request.getParameter("DetectID");
		String detectID = UUID.randomUUID().toString();
		logger.info("tid = " + detectID);
		logger.info("DetectFileFilename = " + this.userDetectFileFileName);
		String userID = request.getParameter("UserID");
		logger.info("userID = " + userID);
		String duration = request.getParameter("TimeDuration");
		logger.info("duration = " + duration);
		
		
		if(userID == null)
		{
			request.setAttribute("DetectFileUploadResult", "Failed_USER_ID_IS_NULL");
			return "detects_upload_detectinfo_failed";
		}
		
		if(duration == null)
		{
			request.setAttribute("DetectFileUploadResult", "Failed_TIME_DURATION_IS_NULL");
			return "detects_upload_detectinfo_failed";
		}
		String newFileName = userID + "_" + detectID + ".dat";
		
		saveUploadFile(newFileName);
		
		
		
		String path = ServletActionContext.getServletContext().getRealPath(
				detectFileUploadDir);
		String fileToRead = path + "/" + newFileName;
		
		logger.info("fileToRead = " + fileToRead);
		
		//从本地文件系统中读取上传的用户检测数据
		Properties prop = new Properties();
		String focusDegrees = null;
		String relaxDegrees = null;
		String heartRates = null;
		String heartRateVariations = null;
		String brainRawData = null;
		String pulseWaveData = null;
		String timeStamp = null;
		InputStream input = null;
		try {
			
			input = new FileInputStream(fileToRead);
			prop.load(input);
			
			focusDegrees = prop.getProperty(Constant.focusDegree);
			relaxDegrees = prop.getProperty(Constant.relaxDegree);
			heartRates = prop.getProperty(Constant.heartRate);
			heartRateVariations = prop.getProperty(Constant.heartRateVariation);
			brainRawData = prop.getProperty(Constant.brainRawData);
			pulseWaveData = prop.getProperty(Constant.pulseWaveData);
			timeStamp = prop.getProperty(Constant.timeStamp);
			logger.info("focusDegrees = " + focusDegrees);
			logger.info("relaxDegrees = " + relaxDegrees);
			logger.info("heartRates = " + heartRates);
			logger.info("heartRateVariations = " + heartRateVariations);
			logger.info("brainRawData = " + brainRawData);
			
			if(focusDegrees == null)
			{
				request.setAttribute("DetectFileUploadResult", "FAILED_FOCUS_DEGREE_IS_NOT_FOUND");
				return "detects_upload_detectinfo_failed";
			}
			
			if(relaxDegrees == null)
			{
				request.setAttribute("DetectFileUploadResult", "FAILED_RELAX_DEGREE_IS_NOT_FOUND");
				return "detects_upload_detectinfo_failed";
			}
			
			if(heartRates == null)
			{
				request.setAttribute("DetectFileUploadResult", "FAILED_HEART_RATE_IS_NOT_FOUND");
				return "detects_upload_detectinfo_failed";
			}
			
			if(heartRateVariations == null)
			{
				request.setAttribute("DetectFileUploadResult", "FAILED_HEART_RATE_VARIATION_IS_NOT_FOUND");
				return "detects_upload_detectinfo_failed";
			}
			
			if(brainRawData == null)
			{
				request.setAttribute("DetectFileUploadResult", "FAILED_BRAIN_RAW_DATA_IS_NOT_FOUND");
				return "detects_upload_detectinfo_failed";
			}
			
			if(pulseWaveData == null)
			{
				request.setAttribute("DetectFileUploadResult", "FAILED_PULSE_WAVE_DATA_IS_NOT_FOUND");
				return "detects_upload_detectinfo_failed";
			}
			
			if(timeStamp == null)
			{
				request.setAttribute("DetectFileUploadResult", "FAILED_TIMESTAMP_IS_NOT_FOUND");
				return "detects_upload_detectinfo_failed";
			}
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
		
		
		//只有在用户存在的前提下才进行相关表的插入或者更新操作
		UsersDAO uDAO = new UsersDAOImpl();
		Users user = uDAO.getUserByID(userID);
		
		if(user == null)
		{
			request.setAttribute("DetectFileUploadResult", "FAILED_USER_IS_NOT_EXIST");		
			return "detects_upload_detectinfo_failed";
		}
		
		
		DetectDetailDAO ddDAO = new DetectDetailDAOImpl();
		
		
		DetectDetail dd = new DetectDetail();
		dd.setDid(detectID);
		dd.setUid(userID);
		dd.setFocusDegrees(focusDegrees);
		dd.setRelaxDegrees(relaxDegrees);
		dd.setHeartRates(heartRates);
		dd.setHeartRateVariations(heartRateVariations);
		dd.setBrainRawData(brainRawData);
		dd.setPulseWaveData(pulseWaveData);
		dd.setTimeStamp(timeStamp);
		
		boolean insertDetectDetailResult = ddDAO.insertDetectDetailInfo(dd);
		if(insertDetectDetailResult == false)
		{
			request.setAttribute("DetectFileUploadResult", "FAILED_INSERT_DETECT_DETAIL");
			return "detects_upload_detectinfo_failed";
		}
		
		
		boolean insertTestInfoResult = updateTestInfoTable(detectID, userID, duration, focusDegrees,
					relaxDegrees, heartRates, heartRateVariations);
		
		
		if(insertTestInfoResult == false)
		{
			request.setAttribute("DetectFileUploadResult", "FAILED_INSERT_TEST_INFO");
			return "detects_upload_detectinfo_failed";
		}
		
		//更新user表中的testTimes字段
		int testTimes = user.getTestTimes();
		testTimes += 1;
		user.setTestTimes(testTimes);
		uDAO.updateUsers(user);
		
		request.setAttribute("DetectFileUploadResult", "SUCCESS");
		return "detects_upload_detectinfo_success";

		
		
		
	}



	private boolean updateTestInfoTable(String detectID, String userID,
			String duration, String focusDegrees, String relaxDegrees,
			String heartRates, String heartRateVariations) 
	{
		//计算这次检测专注度的平均值
		int avgFocusDegree = calAvg(focusDegrees);
		int avgRelaxDegree = calAvg(relaxDegrees);
		int avgHeartRate = calAvg(heartRates);
		int avgHeartRateVariation = calAvg(heartRateVariations);
		
		int timeDuration = Integer.parseInt(duration);
		
		
		Date now = new Date();
		
		String hql = "from TestInfo where uid = '" + userID + "' and tid = '" + detectID + "'";
		TestInfoDAO tiDAO = new TestInfoDAOImpl();
		TestInfo ti = tiDAO.uniqueQueryByHQL(hql);
		if(ti == null)
		{
			TestInfo testInfo = new TestInfo();
			testInfo.setTid(detectID);
			testInfo.setUid(userID);
			testInfo.setFocusValue(avgFocusDegree);
			testInfo.setRelaxValue(avgRelaxDegree);
			testInfo.setHeartRate(avgHeartRate);
			testInfo.setHeartVariate(avgHeartRateVariation);
			testInfo.setTestDate(now);
			testInfo.setTimeDuration(timeDuration);			
			tiDAO.insertTestInfo(testInfo);
		}
		else
		{
			//正常来说这条记录是不应该存在的，因为没上传一次，就要往testinfo表中插入一条数据，这里就直接返回错误的视图吧
			request.setAttribute("DetectFileUploadResult", "Failed_TEST_INFO_EXISTED");
			//return "detects_upload_detectinfo_failed";
			return false;
			
		}
		
		return true;
	}

}
