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
  <form action="users/Users_verifyUser.action" method="post">
  <input type="text" name="phoneNum"/>
  <input type="submit" value="验证"/> 
  </form>  
  
  
  
  
  <form action="users/Users_uploadUserInfo.action" enctype="multipart/form-data" method="post">
  用户ID：<input type="text" name="UserID" value="3"/>
  用户姓名：<input type="text" name="UserName" value="zhangsan"/>
  用户年龄：<input type="text" name="UserAge" value="15"/>
 用户性别 <input type="text" name="UserGender" value="男"/>
 头像文件 <input type="file" name="UserIconFile"/>
  <input type="submit" value="上传用户信息"/> 
  </form>  
    
 
 
 
  </body>
</html>
