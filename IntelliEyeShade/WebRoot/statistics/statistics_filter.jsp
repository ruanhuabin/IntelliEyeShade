<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/default.css" />
<head>
<script type="text/javascript">
function check() 
{
	//alert("check body");
	
	var startDate = document.filter.starttime.value;
	var endDate = document.filter.endtime.value;
	
	var t1 = new Date(startDate.replace(/\-/g, "\/"));
	var t2 = new Date(endDate.replace(/\-/g, "\/"));
	
	if(startDate == "" || endDate == "")
	{
		alert("请选择开始时间或者结束时间");
		return false;
		
	}
	
	 if(startDate!="" && endDate!="" && t1 >t2)  
	 {  
		  alert("开始时间不能大于结束时间！");  
		  return false;  
	 }
	 
	 document.getElementById("userTotalInfo").style.display="";
	 document.getElementById("totalUsers").style.display="";
	 document.getElementById("avgRegisterUsers").style.display="";
	 document.getElementById("avgLoginUsers").style.display="";
	 document.getElementById("usageInfo").style.display="";
	 document.getElementById("totalTimes").style.display="";
	 document.getElementById("totalMiniutes").style.display="";
	
	
	
	return true;
}


function getSelectFilter(sel)
{
	
	
	var typeSelect = sel.value;
	
	//alert(typeSelect);
	
	if(typeSelect == "deviceID")
	{
		
		//alert("You select: " + typeSelect);
		document.filter.starttime.style.display = "none";
	}
	
	if(typeSelect == "bindingStatus")
	{
		document.filter.starttime.style.display = "";
		document.filter.keyword.style.display="";
	}
	
	
	
	
	return true;
}
</script>

</head>



<body>


<script type="text/javascript" src="../js/Calendar3.js"></script>
<br>
<br>
<div style="width:40px; display:inline-block;">
</div>
<div style="width:600px; display:inline-block;">
<form name="filter" action="<%=path%>/statistics/Statistics_query.action" method="post">
开始时间：<input name="starttime" type="text" id="control_date" size="20"
      maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />
      
      <input name="keyword" size="20" type="text" style="display:none" value="abc"/>

结束时间：<input name="endtime" type="text" id="control_date" size="20"
      maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />

<input type="submit" value="筛选" onclick="return check()"/>
</form>
</div>

<br>
<br>
<div style="width:40px; display:inline-block;">
</div>


<s:if test="#request.statisticsinfo == null">
<div style="width:600px; display:inline-block;">
<label style="display:none" id="userTotalInfo">用户统计：</label>
<br><br>

<label style="display:none" id="totalUsers">总人数：<s:property value="#request.statisticsinfo.totalUsers"/></label>
<br>
<label style="display:none" id="avgRegisterUsers">日人均注册人数：<s:property value="#request.statisticsinfo.avgRegisterUsers"/></label>
<br>
<label style="display:none" id="avgLoginUsers">日人均登录人数：<s:property value="#request.statisticsinfo.avgLoginUsers"/></label>
<br>
<label style="display:none" id="usageInfo">使用统计：</label>
<br>
<br>
<label style="display:none" id="totalTimes">总次数： <s:property value="#request.statisticsinfo.totalTestTimes"/></label>
<br>
<label style="display:none" id="totalMiniutes">总时长：<s:property value="#request.statisticsinfo.totalTestMiniutes"/></label>
<br>
</div>

</s:if>
<s:else>

<div style="width:600px; display:inline-block;">
<label id="userTotalInfo">用户统计：</label>
<br><br>

<label id="totalUsers">总人数：<s:property value="#request.statisticsinfo.totalUsers"/></label>
<br>
<label id="avgRegisterUsers">日人均注册人数：<s:property value="#request.statisticsinfo.avgRegisterUsers"/></label>
<br>
<label  id="avgLoginUsers">日人均登录人数：<s:property value="#request.statisticsinfo.avgLoginUsers"/></label>
<br>
<%--<br>--%>
<%--<label id="usageInfo">使用统计：</label>--%>
<%--<br>--%>
<%--<br>--%>
<%--<label  id="totalTimes">总次数： <s:property value="#request.statisticsinfo.totalTestTimes"/></label>--%>
<%--<br>--%>
<%--<label id="totalMiniutes">总时长：<s:property value="#request.statisticsinfo.totalTestMiniutes"/></label>--%>
<%--<br>--%>

<br>
<label id="usageInfo">使用统计:</label>
<br>
<br>
<label  id="totalTimes">单用户使用次数： <s:property value="#request.statisticsinfo.avgUsedTimes"/></label>
<br>
<label id="totalMiniutes">单用户平均时长：<s:property value="#request.statisticsinfo.avgUsedDuration"/></label>
<br>

<div style="width:500px; display:inline-block;">
<br>
<label id="usageInfo">用户性别以及使用时长:</label>
<br>
<br>
<label  id="totalTimes">男性比例： <s:property value="#request.statisticsinfo.maleRatio"/></label>
<br>
<label  id="totalTimes">男性人数： <s:property value="#request.statisticsinfo.maleNum"/></label>
<br>
<label id="totalMiniutes">男性平均时长：<s:property value="#request.statisticsinfo.maleAvgDuration"/></label>
<br>

<label  id="totalTimes">女性比例： <s:property value="#request.statisticsinfo.femaleRatio"/></label>
<br>
<label  id="totalTimes">女性人数： <s:property value="#request.statisticsinfo.femaleNum"/></label>
<br>
<label id="totalMiniutes">女性平均时长：<s:property value="#request.statisticsinfo.femaleAvgDuration"/></label>
<br>
</div>

<div style="width:500px; display:inline-block;">
<img src="<%=basePath%>/ChartFiles/stat_gender.jpg"/>
</div>
<br>
<label id="usageInfo">用户年龄段以及使用时长:</label>
<br>


<table  width="100%">
	<tr>
		<td>年龄段</td>
		<td>人数</td>
		<td>百分比</td>
		<td>平均时长</td>
	</tr>
	
	<tr>
		<td>16-20</td>
		<td><s:property value="#request.statisticsinfo.ageGroupNum[3]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupRatio[3]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupAvgDuration[3]"/></td>
	</tr>	
	<tr>
		<td>21-25</td>
		<td><s:property value="#request.statisticsinfo.ageGroupNum[4]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupRatio[4]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupAvgDuration[4]"/></td>
	</tr>
	<tr>
		<td>26-30</td>
		<td><s:property value="#request.statisticsinfo.ageGroupNum[5]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupRatio[5]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupAvgDuration[5]"/></td>
	</tr>
	<tr>
		<td>31-35</td>
		<td><s:property value="#request.statisticsinfo.ageGroupNum[6]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupRatio[6]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupAvgDuration[6]"/></td>
	</tr>
	<tr>
		<td>36-40</td>
		<td><s:property value="#request.statisticsinfo.ageGroupNum[7]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupRatio[7]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupAvgDuration[7]"/></td>
	</tr>
	<tr>
		<td>41-45</td>
		<td><s:property value="#request.statisticsinfo.ageGroupNum[8]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupRatio[8]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupAvgDuration[8]"/></td>
	</tr>
	<tr>
		<td>46-50</td>
		<td><s:property value="#request.statisticsinfo.ageGroupNum[9]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupRatio[9]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupAvgDuration[9]"/></td>
	</tr>
	
	<tr>
		<td>51-55</td>
		<td><s:property value="#request.statisticsinfo.ageGroupNum[10]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupRatio[10]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupAvgDuration[10]"/></td>
	</tr>
	<tr>
		<td>56-60</td>
		<td><s:property value="#request.statisticsinfo.ageGroupNum[11]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupRatio[11]"/></td>
		<td><s:property value="#request.statisticsinfo.ageGroupAvgDuration[11]"/></td>
	</tr>
	
</table>

<img src="<%=basePath%>/ChartFiles/stat_age.jpg"/>

<br>
<label id="usageInfo">用户使用时间段以及使用时长:</label>
<br>


<table  width="100%">
	<tr>
		<td>时间段</td>
		<td>人数</td>
		<td>百分比</td>
		<td>平均时长</td>
	</tr>
	
	<tr>
		<td>1-6</td>
		<td><s:property value="#request.statisticsinfo.timeGroupNum[0]"/></td>
		<td><s:property value="#request.statisticsinfo.timeGroupRatio[0]"/></td>
		<td><s:property value="#request.statisticsinfo.timeGroupAvgDuration[0]"/></td>
	</tr>	
	<tr>
		<td>7-12</td>
		<td><s:property value="#request.statisticsinfo.timeGroupNum[1]"/></td>
		<td><s:property value="#request.statisticsinfo.timeGroupRatio[1]"/></td>
		<td><s:property value="#request.statisticsinfo.timeGroupAvgDuration[1]"/></td>
	</tr>
	<tr>
		<td>13-18</td>
		<td><s:property value="#request.statisticsinfo.timeGroupNum[2]"/></td>
		<td><s:property value="#request.statisticsinfo.timeGroupRatio[2]"/></td>
		<td><s:property value="#request.statisticsinfo.timeGroupAvgDuration[2]"/></td>
	</tr>
	<tr>
		<td>19-24</td>
		<td><s:property value="#request.statisticsinfo.timeGroupNum[3]"/></td>
		<td><s:property value="#request.statisticsinfo.timeGroupRatio[3]"/></td>
		<td><s:property value="#request.statisticsinfo.timeGroupAvgDuration[3]"/></td>
	</tr>
	
</table>
<img src="<%=basePath%>/ChartFiles/stat_time.jpg"/>
</div>

</s:else>


</body>
</html>
