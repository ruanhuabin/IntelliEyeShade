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
        
        function checkfilter(sel)
        {
        	var currentSelect = sel.value;
        	
        	if(currentSelect == "binded" || currentSelect == "unbinded")
        	{
        		document.user_filter.starttime.style.display="none";
        		document.user_filter.endtime.style.display="none";
        		document.getElementById('sl').style.display="none";
        		document.getElementById('el').style.display="none";
        		
        	}
        	else
        	{
        		document.user_filter.starttime.style.display="";
        		document.user_filter.endtime.style.display="";
        		document.getElementById('sl').style.display="";
        		document.getElementById('el').style.display="";
        	}
        	
        	
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
<form action="users/Users_queryByCondition.action">
        <select name="keywordSelect">
                <option value="username" selected>用户名</option>
                <option value="gender">性别</option>                
                <option value="age">年龄</option>
                <option value="phoneNum">手机号</option>
                <option value="testTimes">检测情况</option>
        </select>
        
        <input type=text name="keyword" size=20/>
        
        <input type="submit" value="搜索"/> 
</form>
</div>
<div style="width:800px; display:inline-block;">
<form name = "user_filter" action="users/Users_filter">
<select name="UserFilterType" onchange="return checkfilter(this)">
<option value="timerange" selected>时间段</option>
<option value="binded"> 已绑定</option>
<option value="unbinded">未绑定</option>
</select>
<label id="sl">开始时间：</label><input name="starttime" type="text" id="control_date" size="20"
      maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />

<label id="el">
结束时间：</label><input name="endtime" type="text" id="control_date" size="20"
      maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />
<input type="submit" value="筛选"/> 

</form>
</div>
</p>

<br>

<!-- <div id="mainContainer"> -->
<div id="newcontainer">
<!-- 从session中获取学生集合 -->

<!-- <table class="default" width="100%"> -->
<table width="100%">
	<col width="10%">
	<col width="15%">
	<col width="5%">
	<col width="10%">
	<col width="12%">
	<col width="12%">
	<col width="12%"> 
	<col width="12%">
	<col width="12%">  
	<tr class="title">
	
		<td>ID</td>
		<td>姓名</td>
		<td>性别</td>
		<td>年龄</td>
		<td>手机号</td>
		<td>硬件绑定</td>
		<td>检测情况</td>
		<td>注册时间</td>
		<td>操作</td>
	</tr>
	
	<!-- 遍历开始 -->
	
	 <s:iterator value="#request.pageBean.list" id="usr">
	<tr class="list">
		 <td><s:property value="#usr.uid"/></td>
		
		<td><a href="<%=path%>/usrs/Users_modify.action?uid=<s:property value="#usr.uid"/>"><s:property value="#usr.username"/></a></td>
		<td><s:property value="#usr.gender"/></td>
		<td><s:property value="#usr.age"/></td>
		<td><s:property value="#usr.phoneNum"/></td>
		<td><s:property value="#usr.bindingStatus"/></td>
		<td><s:property value="#usr.testTimes"/></td>
		<td><s:property value="#usr.registerDate"/></td>
		<td><a href="<%=path%>/users/Users_deleteInSearch.action?uid=<s:property value="#usr.uid"/>&pageNum=<s:property value="#request.pageBean.currentPage"/>" onclick="javascript: return confirm('真的要删除吗？');">删除</a></td>
		
	</tr>
	</s:iterator>
	<!-- 遍历结束 -->
</table>
</div>

 <div align="center">
    
                            共<font color="red"><s:property value="#request.pageBean.totalPage"/></font>页&nbsp;&nbsp;
                           共<font color="red"><s:property value="#request.pageBean.allRows"/></font>条记录
        
        <s:if test="#request.pageBean.currentPage == 1">
            <font size=2>首页&nbsp;&nbsp;&nbsp;上一页</font>
        </s:if>
        
        <s:else>
            <a href="Users_queryByCondition.action?page=0&amp;keyword=<s:property value="#request.keyword"/>&amp;keywordSelect=<s:property value="#request.keywordSelect"/>"><font size=2>首页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="Users_queryByCondition.action?page=<s:property value="#request.pageBean.currentPage - 1"/>&amp;keyword=<s:property value="#request.keyword"/>&amp;keywordSelect=<s:property value="#request.keywordSelect"/>"><font size=2>上一页</font></a>
        </s:else>
        
        <s:if test="#request.pageBean.currentPage != #request.pageBean.totalPage">
            <a href="Users_queryByCondition.action?page=<s:property value="#request.pageBean.currentPage + 1"/>&amp;keyword=<s:property value="#request.keyword"/>&amp;keywordSelect=<s:property value="#request.keywordSelect"/>"><font size=2>下一页</font></a>
            &nbsp;&nbsp;&nbsp;
            <a href="Users_queryByCondition.action?page=<s:property value="#request.pageBean.totalPage"/>&amp;keyword=<s:property value="#request.keyword"/>&amp;keywordSelect=<s:property value="#request.keywordSelect"/>"><font size=2>尾页</font></a>
        </s:if>
        
        <s:else>
            <font size=2>下一页&nbsp;&nbsp;&nbsp;尾页</font>
        </s:else>
    
   </div>
    
 <div align="center">
        
        <form action="Users_queryByCondition.action" onsubmit="return validate();">
            <font size="2">跳转至</font>
            <input type="hidden" name="keyword" value="<s:property value="#request.keyword"/>">
            <input type="hidden" name="keywordSelect" value="<s:property value="#request.keywordSelect"/>">
            <input type="text" size="5" name="page" >页
            <input type="submit" value="跳转">
        </form>
        
    </div>

</body>
</html>