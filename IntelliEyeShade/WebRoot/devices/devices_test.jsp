<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
    <br>
    
  
  
  
  
  <form action="devices/Devices_uploadDeviceInfo.action" method="post">
  设备ID：<input type="text" name="DeviceID" value="3"/>
  用户ID：<input type="text" name="UserID" value="3"/>
 设备版本：<input type="text" name="DeviceVersion" value="zhangsan"/>
  固件版本：<input type="text" name="RomVersion" value="15"/>
 
 
  <input type="submit" value="上传设备信息"/> 
  </form>  
    
 
 
 
  </body>
</html>
