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
	
	 if(startDate!="" && endDate!="" && t1 >=t2)  
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
<br>
<label id="usageInfo">使用统计：</label>
<br>
<br>
<label  id="totalTimes">总次数： <s:property value="#request.statisticsinfo.totalTestTimes"/></label>
<br>
<label id="totalMiniutes">总时长：<s:property value="#request.statisticsinfo.totalTestMiniutes"/></label>
<br>
</div>

</s:else>


</body>
</html>
