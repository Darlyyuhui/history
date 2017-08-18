<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*"%>
<%@tag import="java.util.Map"%>
<%@ attribute name="typeCode" type="java.lang.String" required="true" %>
<%@ attribute name="emptyText" type="java.lang.String" required="false" %>
<%@ attribute name="defaultValue" type="java.lang.String" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
emptyText 不传值时默认首选项文本为：请选择；传空值则没有首选项；有值显示为首选项的文本内容
 ！！！！首选项的value均为空字符串！！！！  
 --%>
<%
Cache cache =(Cache)com.xiangxun.atms.framework.base.ApplicationContextHolder.getBean("ehcacheImpl");
com.google.common.collect.Table table = (com.google.common.collect.Table)cache.get("DicRegion");
if(table==null){
	out.write("");
}else{
    Map map = table.column(typeCode);
    if (emptyText == null) {
		out.write("<option value=\"\">请选择</option>");
	} else if (!"".equals(emptyText)) {
		out.write("<option value=\"\">"+emptyText+"</option>");
	}
    for (Object key : map.keySet()) {
    	if (key.equals(defaultValue)) {
    		out.write("<option value=\""+key+"\" selected>"+map.get(key)+"</option>");
    	} else {
    		out.write("<option value=\""+key+"\">"+map.get(key)+"</option>");
    	}
    	
    }
}
%>
