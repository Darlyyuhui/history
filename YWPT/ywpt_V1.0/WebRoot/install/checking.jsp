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
	function ref() {
		location.reload();
	}
	
</script>
<meta content="Comsenz Inc." name="Copyright" />
</head>
<body>
<div class="container">
	<div class="header">
		<h1>ITMS 安装向导</h1>
		<span>ITMS v1.0 简体中文版</span>	<div class="setup step2">
		<h2>检查安装环境</h2>
		<p>数据库结构以及完整性检查</p>
	</div>
	<div class="stepstat">
		<ul>
			<li class="">设置环境</li>
			<li class="current">环境检查</li>
			<li class="unactivated">执行安装</li>
			<li class="unactivated last">完成</li>
		</ul>
		<div class="stepstatbg stepstat1"></div>
	</div>
</div>
<div class="main"><h2 class="title">数据库结构检查</h2>
<table class="tb" style="margin:20px 0 20px 55px;">
<tr>
	<th>检测项目</th>
	<th class="padleft">ITMS 主库</th>
	<th class="padleft">ITMS 卡口</th>
	<th class="padleft">ITMS GPS</th>
</tr>
<tr>
<td>数据库连接</td>
<td class="padleft"><%=request.getAttribute("connstatus") %></td>
<td class="padleft"><%=request.getAttribute("cconnstatus") %></td>
<td class="padleft"><%=request.getAttribute("gconnstatus") %></td>
</tr>

<tr>
<td>表个数</td>
<td class="padleft"><%=request.getAttribute("stablenum") %> / <%=request.getAttribute("tablenum") %></td>
<td class="padleft"><%=request.getAttribute("sctablenum") %> / <%=request.getAttribute("ctablenum") %></td>
<td class="padleft"><%=request.getAttribute("sgtablenum") %> / <%=request.getAttribute("gtablenum") %></td>
</tr>

<tr>
<td>触发器个数</td>
<td class="padleft"><%=request.getAttribute("striggernum") %> / <%=request.getAttribute("triggernum") %></td>
<td class="padleft"><%=request.getAttribute("sctriggernum") %> / <%=request.getAttribute("ctriggernum") %></td>
<td class="padleft"><%=request.getAttribute("sgtriggernum") %> / <%=request.getAttribute("gtriggernum") %></td>
</tr>

<tr>
<td>存储过程个数</td>
<td class="padleft"><%=request.getAttribute("sprocedurenum") %> / <%=request.getAttribute("procedurenum") %></td>
<td class="padleft"><%=request.getAttribute("scprocedurenum") %> / <%=request.getAttribute("cprocedurenum") %></td>
<td class="padleft"><%=request.getAttribute("sgprocedurenum") %> / <%=request.getAttribute("gprocedurenum") %></td>
</tr>

<tr>
<td>PACKAGE个数</td>
<td class="padleft"><%=request.getAttribute("spackagenum") %> / <%=request.getAttribute("packagenum") %></td>
<td class="padleft"><%=request.getAttribute("scpackagenum") %> / <%=request.getAttribute("cpackagenum") %></td>
<td class="padleft"><%=request.getAttribute("sgpackagenum") %> / <%=request.getAttribute("gpackagenum") %></td>
</tr>

<tr>
<td>PACKAGE BODY个数</td>
<td class="padleft"><%=request.getAttribute("spackagelistnum") %> / <%=request.getAttribute("packagelistnum") %></td>
<td class="padleft"><%=request.getAttribute("scpackagelistnum") %> / <%=request.getAttribute("cpackagelistnum") %></td>
<td class="padleft"><%=request.getAttribute("sgpackagelistnum") %> / <%=request.getAttribute("gpackagelistnum") %></td>
</tr>

</table>

<h2 class="title">数据库权限检查</h2>
<table class="tb" style="margin:20px 0 20px 55px;">
<tr>
	<th>检测项目</th>
	<th class="padleft">ITMS 主库</th>
	<th class="padleft">ITMS 卡口</th>
	<th class="padleft">ITMS GPS</th>
</tr>
<tr>
<td>connect</td>
<td class="<%=request.getAttribute("flag_connect")%> pdleft1">需要</td>
<td class="<%=request.getAttribute("cflag_connect")%> pdleft1">需要</td>
<td class="<%=request.getAttribute("gflag_connect")%> pdleft1">需要</td>
</tr>
<tr>
<td>resource</td>
<td class="<%=request.getAttribute("flag_resource")%> pdleft1">需要</td>
<td class="<%=request.getAttribute("cflag_resource")%> pdleft1">需要</td>
<td class="<%=request.getAttribute("gflag_resource")%> pdleft1">需要</td>
</tr>
<tr>
<td>dba</td>
<td class="padleft"></td>
<td class="<%=request.getAttribute("cflag_dba")%> pdleft1">需要</td>
<td class="padleft"></td>
</tr>
<tr>
<td>create table</td>
<td class="padleft"></td>
<td class="<%=request.getAttribute("cflag_createtable")%> pdleft1">需要</td>
<td class="<%=request.getAttribute("gflag_createtable")%> pdleft1">需要</td>
</tr>
<tr>
<td>create session</td>
<td class="padleft"></td>
<td class="<%=request.getAttribute("gflag_createtable")%> pdleft1">需要</td>
<td class="<%=request.getAttribute("gflag_createsession")%> pdleft1">需要</td>
</tr>
</table>

<h2 class="title">目录、文件权限检查</h2>
<table class="tb" style="margin:20px 0 20px 55px;width:90%;">
	<tr>
	<th>目录文件</th>
	<th class="padleft">所需状态</th>
	<th class="padleft">当前状态</th>
</tr>
<tr>
<td>WEB-INF/web.xml</td><td class="w pdleft1">可写</td>
<td class="<%=request.getAttribute("webxmlwrite")%> pdleft1"></td>
</tr>
<tr>
<td>WEB-INF/classes/application.properties</td><td class="w pdleft1">可写</td>
<td class="<%=request.getAttribute("propertiesxmlwrite")%> pdleft1"></td>
</tr>

<tr>
<td>WEB-INF/classes/mongodb.properties</td><td class="w pdleft1">可写</td>
<td class="<%=request.getAttribute("mongodbxmlwrite")%> pdleft1"></td>
</tr>

<tr>
<td>WEB-INF/cache/</td><td class="w pdleft1">可写</td>
<td class="<%=request.getAttribute("cachefilewrite")%> pdleft1"></td>
</tr>

<tr>
<td>./Tomcat6/logs/</td><td class="w pdleft1">可写</td>
<td class="w pdleft1"></td>
</tr>
<tr>
<td>./Tomcat6/work/</td><td class="w pdleft1">可写</td>
<td class="w pdleft1"></td>
</tr>
</table>


<form action="${pageContext.request.contextPath}/install/runing_setup.svl" method="get">
<div class="btnbox marginbot">
<input type="button" onclick="history.back();" value="上一步" />
<input type="submit" value="下一步" <%=request.getAttribute("setupflag")%> />
<input type="button" onclick="ref();" value="刷新" />
</div>
</form>
		<div class="footer">&copy;2001 - 2012 西安翔迅科技有限公司 Inc.</div>
	</div>
</div>
</body>
</html>


