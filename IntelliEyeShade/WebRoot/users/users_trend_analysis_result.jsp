<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<html>
<body>
<div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户共检测了&nbsp;<s:property value="#request.UserTestTimes"/>&nbsp;次
</div>
<img src="<%=basePath%>/ChartFiles/trend_fd.jpg"/>
<img src="<%=basePath%>/ChartFiles/trend_rd.jpg"/>
<img src="<%=basePath%>/ChartFiles/trend_hr.jpg"/>
<img src="<%=basePath%>/ChartFiles/trend_hrv.jpg"/>

</body>
</html>