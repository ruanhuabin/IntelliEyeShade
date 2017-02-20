package action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;

import service.DetectDetailDAO;
import serviceimpl.DetectDetailDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

import entity.DetectDetail;

public class DownloadAction extends SuperAction {
	
	private String inputPath;
	private String uploadDir;
	private InputStream targetFile;
	private String outputFilename;
	private String downloadDir;
	
	private static final Logger logger = Logger.getLogger("MyLogger");
	private static final String detectDetailFilename = "detect_detail.txt";
	
	
	
	public String getDownloadDir() {
		return downloadDir;
	}

	public void setDownloadDir(String downloadDir) {
		this.downloadDir = downloadDir;
	}

	public String getOutputFilename() {
		return outputFilename;
	}

	public void setOutputFilename(String outputFilename) {
		this.outputFilename = outputFilename;
	}

	public void setTargetFile(InputStream targetFile) {
		this.targetFile = targetFile;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}




	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
	
	public InputStream getTargetFile() throws Exception
	{
		String fileName = request.getParameter("f");		
		this.setOutputFilename(fileName);
		logger.info("fileName = " + fileName);		
		String uploadDir = ServletActionContext.getServletContext().getRealPath(this.uploadDir);		
		String filePath = uploadDir + "/" + fileName;		
		logger.info("filePath = " + filePath);
		File file = new File(filePath);
		return new FileInputStream(file);
		
	}
	
	private void  writeDetectDetailItem()
	{
		DetectDetailDAO ddDAO = new DetectDetailDAOImpl();
		List<DetectDetail> detectDetails = ddDAO.getAllDetectDetail();
		
		logger.info("All Detect Detail = " + detectDetails);
		String downloadDir = ServletActionContext.getServletContext().getRealPath(this.downloadDir);
		logger.info("Download Dir = " + downloadDir);
		BufferedWriter bw = null;
		
		try
		{
			File dir = new File(downloadDir);
			
			if(!dir.exists())
			{
				dir.mkdir();
			}
			
			File f = new File(dir, this.detectDetailFilename);
			if(f.exists())
			{
				f.delete();
				f.createNewFile();
			}
			
			FileWriter fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			
			for(DetectDetail dd: detectDetails)
			{
				bw.write(dd + "\n");				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			try {
				if(bw != null)
				{
					bw.close();
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		
	}
	
	
	public InputStream getDetectDetailTarget() throws Exception
	{
		
		writeDetectDetailItem();
		
		//String fileName = request.getParameter("f");		
		this.setOutputFilename(this.detectDetailFilename);
		//logger.info("fileName = " + fileName);		
		String downloadDir = ServletActionContext.getServletContext().getRealPath(this.downloadDir);		
		String filePath = downloadDir + "/" + this.detectDetailFilename;		
		logger.info("path for detect detail = " + filePath);
		File file = new File(filePath);
		return new FileInputStream(file);
		
	}
	


}
