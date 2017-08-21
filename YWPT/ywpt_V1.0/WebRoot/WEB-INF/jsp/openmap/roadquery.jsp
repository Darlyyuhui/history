
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String sessionId=request.getSession().getId(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
	</head>
	<body>
		请输入道路名称:
		<input id="searchText" type="text" placeholder="请输入道路名称"/>
		<input id="query" type="button" value="查询道路" class="btn btn-info"/>
		<input id="clear" type="button" value="清除" class="btn btn-info"/>	
		
		<script type="text/javascript">
			var basePath = "<%=basePath%>",
				baseServiceURL = ${mapLayersInfo};
						
			$("#query").click(function(){
				roadQuery.query($("#searchText").attr("value"), false);
			});
			$("#clear").click(function() {
				document.getElementById("searchText").value = "";
			});
		</script>     
	</body>
</html>