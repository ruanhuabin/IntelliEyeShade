<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<html>
<body>
<br>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.TestTime"/>&nbsp;&nbsp;&nbsp;&nbsp;本次检测(休息)时间&nbsp;&nbsp;<s:property value="#request.TimeDuration"/> &nbsp;&nbsp;分钟
<br>
<br>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;模式：&nbsp;&nbsp;<s:property value="#request.UsedPattern"/>&nbsp;&nbsp;&nbsp;&nbsp;所选音乐：&nbsp;&nbsp;<s:property value="#request.UsedMusic"/> &nbsp;&nbsp;
<br>
<br>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;专注度均值：<s:property value="#request.AvgFD"/>
<br>
<br>
<img src="<%=basePath%>/ChartFiles/detail_fd.jpg"/>
<div>
&nbsp;&nbsp;&nbsp;&nbsp;放松度均值：<s:property value="#request.AvgRD"/>
</div>
<br>
<br>
<img src="<%=basePath%>/ChartFiles/detail_rd.jpg"/>
<div>
&nbsp;&nbsp;&nbsp;&nbsp;心率均值：<s:property value="#request.AvgHR"/>
</div>
<br>
<br>
<img src="<%=basePath%>/ChartFiles/detail_hr.jpg"/>

<div>
&nbsp;&nbsp;&nbsp;&nbsp;心率变异性均值：<s:property value="#request.AvgHRV"/>
</div>
<br>
<br>
<img src="<%=basePath%>/ChartFiles/detail_hrv.jpg"/>

</body>
</html>