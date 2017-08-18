<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*"%>
<%@tag import="java.util.Map"%>
<%@ tag import="com.xiangxun.atms.module.nd.service.NdInfoService" %>
<%@ attribute name="ndCode" type="java.lang.String" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
NdInfoService ndInfo =(NdInfoService)com.xiangxun.atms.framework.base.ApplicationContextHolder.getBean("ndInfoServiceImpl");
String ndInfoName = ndInfo.getNameByKey(ndCode);
if(ndInfoName==null){
	out.write("");
}else{
	out.write(ndInfoName);
}
%>

