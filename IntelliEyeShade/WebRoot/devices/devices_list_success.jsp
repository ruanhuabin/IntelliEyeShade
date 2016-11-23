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

<br><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div align="left">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<H2>硬件详情</H2><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID: <s:property value="#request.deviceinfo.deviceID"/><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;硬件版本：<s:property value="#request.deviceinfo.deviceVersion"/><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;固件版本：<s:property value="#request.deviceinfo.romVersion"/><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态：<s:property value="#request.deviceinfo.deviceStatus"/><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户绑定：<s:property value="#request.deviceinfo.bindingStatus"/><br>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户信息：<s:property value="#request.userinfo.username"/> &nbsp;&nbsp;&nbsp;
       <s:property value="#request.userinfo.gender"/>&nbsp;&nbsp;&nbsp;
       <s:property value="#request.userinfo.age"/>&nbsp;&nbsp;&nbsp;
       <s:property value="#request.userinfo.phoneNum"/>&nbsp;&nbsp;&nbsp;
       
       <br>
</div>



</body>
</html>