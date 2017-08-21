<%@ page language="java"  pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ITMS 安装向导</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/compnents/install/css/style.css" type="text/css" media="all" />
<script type="text/javascript"> 
	function $(id) {
		return document.getElementById(id);
	}
 
	function showmessage(message) {
		document.getElementById('notice').innerHTML += message + '<br />';
	}
</script>
<meta content="Comsenz Inc." name="Copyright" />
</head>
<body>
<div class="container">
	<div class="header">
		<h1>ITMS 安装向导</h1>
		<span>ITMS V1.0 简体中文版</span>	<div class="setup step4">
		<h2>安装完成</h2>
		<p>平台软件安装完成</p>
	</div>
	<div class="stepstat">
		<ul>
			<li class="">设置环境</li>
			<li class="">环境检查</li>
			<li class="">执行安装</li>
			<li class="current last">完成</li>
		</ul>
		<div class="stepstatbg stepstat1"></div>
	</div>
</div>
<div class="main">


<div class="desc"><b></b></div>

<table class="tb2">

<tr>
<th class="tbopt" align="left">&nbsp;恭喜您已经安装成功！</th>
<td><br />
              请重启TOMCAT服务。只有重启TOMCAT服务之后，安装才能生效。<br />
              平台访问地址“<a href='http://<%=request.getAttribute("hostip") %>:<%=request.getAttribute("hostport") %>/itms/login'><b>http://<%=request.getAttribute("hostip") %>:<%=request.getAttribute("hostport") %>/itms/login</b></a>”<br />
              后台管理员admin，密码admin</td>
</tr>
 
</table>

		<div class="footer">&copy;2001 - 2012 西安翔迅科技有限公司 Inc.</div>
</div>
</div>
</body>
</html>

