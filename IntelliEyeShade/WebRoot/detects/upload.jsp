<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  
  <form action="detects/UserDetect_uploadDeviceDetectInfo.action" enctype="multipart/form-data" method="post">
  <input type="text" name="DetectID" value="abc">
  <input type="text" name="UserID" value="def">  
  <input type="file" name="userDetectFile"/>
  <input type="submit" value="上传"/> 
  </form>  
    
    
  </body>
</html>