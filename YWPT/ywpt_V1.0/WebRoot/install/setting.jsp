<%@ page language="java"  pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${pageContext.request.contextPath}/compnents/jquery-validation/1.10.0/validate.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/compnents/bootstrap/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ITMS 安装向导</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/compnents/install/css/style.css" type="text/css" media="all" />
<script type="text/javascript"> 
	function validates() {
		if(document.getElementById('dbhost').value == '' || 
		   document.getElementById('dbname').value == '' ||
		   document.getElementById('cdbhost').value == '' ||
		   document.getElementById('cdbname').value == '' ||
		   document.getElementById('gdbhost').value == '' ||
		   document.getElementById('gdbname').value == '' ||
		   document.getElementById('dbuser').value == '' ||
		   document.getElementById('dbpassword').value == '' || 
		   document.getElementById('postgishost').value == '' || 
		   document.getElementById('postgisname').value == '' || 
		   document.getElementById('postgisport').value == '' || 
		   document.getElementById('dbport').value == ''){
		  showmessage();
		  return false;
		}
		return true;
	}
	
	function showmessage(){
	   document.getElementById('errmessage').innerHTML = '<font color=red>所有设置项不应许为空！</font><br />';
	}
	
</script>
<meta content="Comsenz Inc." name="Copyright" />
</head>
<body>
<div class="container">
	<div class="header">
		<h1>ITMS 安装向导</h1>
		<span>ITMS V1.0 简体中文版</span>	<div class="setup step1">
		<h2>设置运行环境</h2>
		<p>请根据现场环境配置对应的参数</p>
	</div>
	<div class="stepstat">
		<ul>
		    <li class="current">设置环境</li>
			<li class="unactivated">环境检查</li>
			<li class="unactivated">执行安装</li>
			<li class="unactivated last">完成</li>
		</ul>
		<div class="stepstatbg stepstat1"></div>
	</div>
</div>
<div class="main">
<form name="inputForm" method="post" action="${pageContext.request.contextPath}/install/install_setup.svl" onsubmit="return validates();">
<div id="form_items_3" ><br />

<div class="desc"><b>主数据库信息</b></div>

<table class="tb2">
<tr>
<th class="tbopt" align="left">&nbsp;主数据库IP地址:</th>
<td><input type="text" id="dbhost" name="dbhost" value="" size="35" class="txt" /></td>
<td>主数据库服务器IP地址</td>
</tr>
<tr>
<th class="tbopt" align="left">&nbsp;主数据库实例名:</th>
<td><input type="text" id="dbname" name="dbname" value="itms" size="35" class="txt" /></td>
<td></td>
</tr>
</table>


<div class="desc"><b>卡口数据库信息</b></div>
<table class="tb2">
<tr>
<th class="tbopt" align="left">&nbsp;卡口数据库IP地址:</th>
<td><input type="text" id="cdbhost" name="cdbhost" value="" size="35" class="txt" /></td>
<td>卡口数据库服务器IP地址</td>
</tr>
<tr>
<th class="tbopt" align="left">&nbsp;卡口数据库实例名:</th>
<td><input type="text" id="cdbname" name="cdbname" value="cross" size="35" class="txt" /></td>
<td></td>
</tr>
</table>

<div class="desc"><b>GPS数据库信息</b></div>
<table class="tb2">
<tr>
<th class="tbopt" align="left">&nbsp;GPS数据库IP地址:</th>
<td><input type="text" id="gdbhost" name="gdbhost" value="" size="35" class="txt" /></td>
<td>GPS数据库服务器IP地址</td>
</tr>
<tr>
<th class="tbopt" align="left">&nbsp;GPS数据库实例名:</th>
<td><input type="text" id="gdbname" name="gdbname" value="gps" size="35" class="txt" /></td>
<td></td>
</tr>
</table>



<div class="desc"><b>全局数据库配置</b></div>
<table class="tb2">
<tr><th class="tbopt" align="left">&nbsp;数据库用户名:</th>
<td><input type="text" id="dbuser" name="dbuser" value="admin" size="35" class="txt" /></td>
<td></td>
</tr>

<tr><th class="tbopt" align="left">&nbsp;数据库密码:</th>
<td><input type="text" id="dbpassword" name="dbpassword" value="" size="35" class="txt" /></td>
<td></td>
</tr>

<tr><th class="tbopt" align="left">&nbsp;数据库端口:</th>
<td><input type="text" id="dbport" name="dbport" value="1521" size="35" class="txt" /></td>
<td></td>
</tr>
</table>

<div class="desc"><b>开源GIS数据库配置</b></div>
<table class="tb2">
<tr><th class="tbopt" align="left">&nbsp;开源GIS数据库IP地址:</th>
<td><input type="text" id="postgishost" name="postgishost" value="" size="35" class="txt" /></td>
<td></td>
</tr>

<tr><th class="tbopt" align="left">&nbsp;开源GIS数据库名:</th>
<td><input type="text" id="postgisname" name="postgisname" value="" size="35" class="txt" /></td>
<td></td>
</tr>

<tr><th class="tbopt" align="left">&nbsp;开源GIS数据库端口:</th>
<td><input type="text" id="postgisport" name="postgisport" value="1521" size="35" class="txt" /></td>
<td></td>
</tr>
</table>

</div><table class="tb2">
<tr><th class="tbopt" align="left">&nbsp;</th>
<td>
<input type="submit" name="submitname" value="下一步" class="btn" />
<span id="errmessage"></span>
</td>
</tr>
 
</table>
</form>
		<div class="footer">&copy;2001 - 2012 西安翔迅科技有限公司 Inc.</div>
	</div>
</div>
</body>
</html>

