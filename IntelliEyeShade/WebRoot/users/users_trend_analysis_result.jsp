<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<html>
<body>
<img src="<%=basePath%>/ChartFiles/fd.jpg"/>
<img src="<%=basePath%>/ChartFiles/rd.jpg"/>
<img src="<%=basePath%>/ChartFiles/hr.jpg"/>
<img src="<%=basePath%>/ChartFiles/hrv.jpg"/>

</body>
</html>