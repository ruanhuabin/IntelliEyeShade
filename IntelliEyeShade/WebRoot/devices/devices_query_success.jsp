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
<style type="text/css">
* {
    background: none repeat scroll 0 0 transparent;
    border: 0 none;
    margin: 0;
    padding: 0;
    vertical-align: baseline;
	font-family:微软雅黑;
	overflow:hidden;
}
#navi{
	width:100%;
	position:relative;
	word-wrap:break-word;
	border-bottom:1px solid #065FB9;
	margin:0;
	padding:0;
	height:40px;
	line-height:40px;
	vertical-align:middle;
    background-image: -moz-linear-gradient(top,#EBEBEB, #BFBFBF);
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),color-stop(1, 
#BFBFBF));
}
#naviDiv{
	font-size:14px;
	color:#333;
	padding-left:10px;
}
#tips{
	margin-top:10px;
	width:100%;
	height:40px;
}
#buttonGroup{
	padding-left:10px;
	float:left;
	height:35px;
}
.button{
	float:left;
	margin-right:10px;
	padding-left:10px;
	padding-right:10px;
	font-size:14px;
	width:70px;
	height:30px;
	line-height:30px;
	vertical-align:middle;
	text-align:center;
	cursor:pointer;
    border-color: #77D1F6;
    border-width: 1px;
    border-style: solid;
    border-radius: 6px 6px;
    -moz-box-shadow: 2px 2px 4px #282828;
    -webkit-box-shadow: 2px 2px 4px #282828;
    background-image: -moz-linear-gradient(top,#EBEBEB, #BFBFBF);
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),color-stop(1, #BFBFBF));
}
#mainContainer{
	padding-left:10px;
	padding-right:10px;
	text-align:center;
	width:98%;
	font-size:12px;
}
</style>

<head>
<style>
    input{
       border:2px solid gray;
    }
    select{
       border:2px solid gray;
    }
    
</style>

 <script type="text/javascript">
    
        function validate()
        {
            var page = document.getElementsByName("page")[0].value;
                
            if(page > <s:property value="#request.pageBean.totalPage"/>)
            {
                alert("你输入的页数大于最大页数，页面将跳转到首页！");
                
                window.document.location.href = "Users_queryByPage";
                
                return false;
            }
            
            return true;
        }
    
    </script>
</head>
<body>


<%-- <div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;学生管理<span>&nbsp;
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;<a href="<%=path%>/students/Student_query.action">学生列表</a><span>&nbsp;
	</div>
</div> --%>
<%-- <br><div id="tips">
	<div id="buttonGroup">
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a href="<%=path%>/students/Students_add.jsp">添加学生</a>
		</div>
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a>查找学生</a>
		</div>
	</div>
</div> --%>

<br>
<p>
<div style="width:20px; display:inline-block;">
</div>
<div style="width:600px; display:inline-block;">
<form action="devices/Devices_queryByCondition.action">
        <select name="keywordSelect">
                <option value="deviceID" selected>硬件ID</option>
                <option value="romVersion">固件版本</option>
                <option value="deviceVersion">硬件版本</option>                
        </select>
        
        <input type=text name="keyword" size=20/>
        <input type="submit" value="搜索"/> 
</form>

</div>
<div style="width:800px; display:inline-block;">
<form name = "device_filter" action="devices/Devices_filterByPage">
<select name="deviceFilterType" onchange="return checkfilter(this)">
<option value="normalStatus" selected>状态正常</option>
<option value="unormalStatus">状态异常</option>
<option value="binded">已绑定</option>
<option value="unbinded">未绑定</option>
</select>
<input type="submit" value="筛选"/> 
</form>
</div>
</p>

<br>

<div id="mainContainer1">
<!-- 从session中获取学生集合 -->

<!-- <table class="default" width="100%"> -->
<table  width="100%">
	<col width="20%">
	<col width="20%">
	<col width="20%">
	<col width="20%">
	<col width="20%">
	<tr class="title">
	
		<td>ID</td>
		<td>硬件版本</td>
		<td>固件版本</td>
		<td>状态</td>
		<td>用户绑定</td>
		
	</tr>
	
	<!-- 遍历开始 -->
	
	 <s:iterator value="#request.devicepagebean.list" id="device">
	<tr class="list">
		 <td><s:property value="#device.deviceID"/></td>		
		<td><s:property value="#device.deviceVersion"/></td>
		<td><s:property value="#device.romVersion"/></td>
		<td><s:property value="#device.deviceStatus"/></td>		
		<td><a href="<%=path%>/devices/Devices_listDeviceDetails.action?uid=<s:property value="#device.uid"/>&deviceID=<s:property value="#device.deviceID"/>" ><s:property value="#device.bindingStatus"/></a></td>
		
	</tr>
	</s:iterator>
	<!-- 遍历结束 -->
</table>
</div>

<s:if test="#request.pagetrigger == \"devices_fromleftlink\"">
 <div align="center">
    
                            共<font color="red"><s:property value="#request.devicepagebean.totalPage"/></font>页&nbsp;&nbsp;
                           共<font color="red"><s:property value="#request.devicepagebean.allRows"/></font>条记录
        
        <s:if test="#request.devicepagebean.currentPage == 1">
            <font size=2>首页&nbsp;&nbsp;&nbsp;上一页</font>
        </s:if>
        
        <s:else>
            <a href="Devices_queryByPage.action?page=0"><font size=2>首页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="Devices_queryByPage.action?page=<s:property value="#request.devicepagebean.currentPage - 1"/>"><font size=2>上一页</font></a>
        </s:else>
        
        <s:if test="#request.devicepagebean.currentPage != #request.devicepagebean.totalPage">
            <a href="Devices_queryByPage.action?page=<s:property value="#request.devicepagebean.currentPage + 1"/>"><font size=2>下一页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="Devices_queryByPage.action?page=<s:property value="#request.devicepagebean.totalPage"/>"><font size=2>尾页</font></a>
        </s:if>
        
        <s:else>
            <font size=2>下一页&nbsp;&nbsp;&nbsp;尾页</font>
        </s:else>
    
   </div>
    
 <div align="center">
        
        <form action="Devices_queryByPage.action" onsubmit="return validate();">
            <font size="2">跳转至</font>
            <input type="text" size="5" name="page" >页
            <input type="submit" value="跳转">
        </form>
        
</div>
</s:if>


<s:if test="#request.pagetrigger == \"devices_fromsearchbutton\"">

<div align="center">
    
                            共<font color="red"><s:property value="#request.devicepagebean.totalPage"/></font>页&nbsp;&nbsp;
                           共<font color="red"><s:property value="#request.devicepagebean.allRows"/></font>条记录
        
        <s:if test="#request.devicepagebean.currentPage == 1">
            <font size=2>首页&nbsp;&nbsp;&nbsp;上一页</font>
        </s:if>
        
        <s:else>
            <a href="Devices_queryByCondition.action?page=0&amp;keyword=<s:property value="#request.keyword"/>&amp;keywordSelect=<s:property value="#request.keywordSelect"/>"><font size=2>首页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="Devices_queryByCondition.action?page=<s:property value="#request.devicepagebean.currentPage - 1"/>&amp;keyword=<s:property value="#request.keyword"/>&amp;keywordSelect=<s:property value="#request.keywordSelect"/>"><font size=2>上一页</font></a>
        </s:else>
        
        <s:if test="#request.devicepagebean.currentPage != #request.devicepagebean.totalPage">
            <a href="Devices_queryByCondition.action?page=<s:property value="#request.devicepagebean.currentPage + 1"/>&amp;keyword=<s:property value="#request.keyword"/>&amp;keywordSelect=<s:property value="#request.keywordSelect"/>"><font size=2>下一页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="Devices_queryByCondition.action?page=<s:property value="#request.devicepagebean.totalPage"/>&amp;keyword=<s:property value="#request.keyword"/>&amp;keywordSelect=<s:property value="#request.keywordSelect"/>"><font size=2>尾页</font></a>
        </s:if>
        
        <s:else>
            <font size=2>下一页&nbsp;&nbsp;&nbsp;尾页</font>
        </s:else>
    
   </div>
    
 <div align="center">
        
        <form action="Devices_queryByCondition.action" onsubmit="return validate();">
            <font size="2">跳转至</font>
            <input type="hidden" name="keyword" value="<s:property value="#request.keyword"/>">
            <input type="hidden" name="keywordSelect" value="<s:property value="#request.keywordSelect"/>">
            <input type="text" size="5" name="page" >页
            <input type="submit" value="跳转">
        </form>
        
    </div>

</s:if>

<s:if test="#request.pagetrigger == \"users_fromfilterbutton\"">

<div align="center">
    
                            共<font color="red"><s:property value="#request.devicepagebean.totalPage"/></font>页&nbsp;&nbsp;
                           共<font color="red"><s:property value="#request.devicepagebean.allRows"/></font>条记录
        
        <s:if test="#request.devicepagebean.currentPage == 1">
            <font size=2>首页&nbsp;&nbsp;&nbsp;上一页</font>
        </s:if>
        
        <s:else>
            <a href="Devices_filterByPage.action?page=0&amp;deviceFilterType=<s:property value="#request.deviceFilterType"/>"><font size=2>首页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="Devices_filterByPage.action?page=<s:property value="#request.devicepagebean.currentPage - 1"/>&amp;deviceFilterType=<s:property value="#request.deviceFilterType"/>"><font size=2>上一页</font></a>
        </s:else>
        
        <s:if test="#request.devicepagebean.currentPage != #request.devicepagebean.totalPage">
            <a href="Devices_filterByPage.action?page=<s:property value="#request.devicepagebean.currentPage + 1"/>&amp;deviceFilterType=<s:property value="#request.deviceFilterType"/>"><font size=2>下一页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="Devices_filterByPage.action?page=<s:property value="#request.devicepagebean.totalPage"/>&amp;deviceFilterType=<s:property value="#request.deviceFilterType"/>"><font size=2>尾页</font></a>
        </s:if>
        
        <s:else>
            <font size=2>下一页&nbsp;&nbsp;&nbsp;尾页</font>
        </s:else>
    
   </div>
    
 <div align="center">
        
        <form action="Devices_filterByPage.action" onsubmit="return validate();">
            <font size="2">跳转至</font>            
            <input type="hidden" name="deviceFilterType" value="<s:property value="#request.deviceFilterType"/>">
            <input type="text" size="5" name="page" >页
            <input type="submit" value="跳转">
        </form>
        
    </div>




</s:if>

</body>
</html>