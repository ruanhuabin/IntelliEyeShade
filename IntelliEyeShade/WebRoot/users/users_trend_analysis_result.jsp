<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<html>
<body>
<img src="<%=basePath%>/ChartFiles/fd.jpg">This is a image</image>


<a href="<%=basePath%>/ChartFiles/fd.jpg"> lianjie</a>>
</body>
</html>