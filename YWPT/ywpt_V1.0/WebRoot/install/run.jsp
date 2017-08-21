<%@ page language="java"  pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ITMS 安装向导</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/compnents/install/css/style.css" type="text/css" media="all" />
<script type="text/javascript"> 
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
		<span>ITMS V1.0 简体中文版</span>	<div class="setup step3">
		<h2>执行安装</h2>
		<p>执行安装脚本</p>
	</div>
	<div class="stepstat">
		<ul>
			<li class="">设置环境</li>
			<li class="">环境检查</li>
			<li class="current">执行安装</li>
			<li class="unactivated last">完成</li>
		</ul>
		<div class="stepstatbg stepstat1"></div>
	</div>
</div>
<div class="main">
<form method="post" action="${pageContext.request.contextPath}/install/runing_setup.svl">

<div id="form_items_3" ><br /><div class="desc"><b>填写LICENSE</b></div>

<table class="tb2">
<tr>
<td>
<textarea rows="5" cols="55"></textarea>
</td>
<td>请输入购买的LICENSE验证码</td>
</tr>
</table>


<div class="desc"><b>填写管理员信息</b></div>

<table class="tb2">
<tr>
<th class="tbopt" align="left">&nbsp;管理员账号:</th>
<td><input type="text" name="username" value="admin" disabled size="35" class="txt" /></td>
<td></td>
</tr>
 
<tr>
<th class="tbopt" align="left">&nbsp;管理员密码:</th>
<td><input type="password" name="password" value="admin" disabled size="35" class="txt" /></td>
<td>管理员密码不能为空</td>
</tr>
 
<tr>
<th class="tbopt" align="left">&nbsp;重复密码:</th>
<td><input type="password" name="password2" value="admin" disabled size="35" class="txt" /></td>
<td></td>
</tr>
 
</table>
</div>

<div class="btnbox marginbot">
<input type="button" onclick="history.back();" value="上一步" />
<input type="submit" value="下一步" />
</div>

</form>
		<div class="footer">&copy;2001 - 2012 西安翔迅科技有限公司 Inc.</div>
	</div>
</div>
</body>
</html>

