<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2016, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Struts 2的文件下载</title>
</head>
<body>
	<h1>Struts 2的文件下载</h1>
	<ul>
	<li>
	下载疯狂Java联盟的Logo：
		<a href="<%=path%>/users/download.action?f=7.py">下载图形文件</a> 
	</li>
	<li>
	下载疯狂Java联盟的Logo的压缩文件：
		<a href="download2.action">下载压缩文件</a>
	</li>
	</ul>
</body>
</html>