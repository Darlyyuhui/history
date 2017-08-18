<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*" %>
<%@ attribute name="keyName" type="java.lang.String" required="true" %>
<%@ attribute name="id" type="java.lang.String" required="true" %>
<%@ attribute name="typeCode" type="java.lang.String" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Cache cache = (Cache) com.xiangxun.atms.framework.base.ApplicationContextHolder.getBean("ehcacheImpl");
    com.google.common.collect.Table table = (com.google.common.collect.Table) cache.get(keyName);
    if (table == null) {
        out.write("");
    } else {
        if (typeCode == null) {
            String str = table.column(keyName).get(id) == null ? "" : table.column(keyName).get(id) + "";
            out.write(str);
        } else {
            //typeCode 不为空时，从缓存取三维格式的查询（列如：数据字典）
            String str = table.column(typeCode).get(id) == null ? "" : table.column(typeCode).get(id) + "";
            out.write(str);
        }
    }
%>

