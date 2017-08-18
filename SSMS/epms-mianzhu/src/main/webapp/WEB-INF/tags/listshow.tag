<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*,java.util.*" %>
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
            List<String> list = (List<String>)table.column(keyName).get(id);
            if(list== null){
            	out.write("");
            }else{
            	String str="";
            	for(int i=0;i<list.size();i++){
            		if(i<list.size()-1){
            			str+=list.get(i)+",";
            		}else{
            			str+=list.get(i);
            		}
            	}
            	out.write(str);
            }
            
        } else {
            //typeCode 不为空时，从缓存取三维格式的查询（列如：数据字典）
             List<String> list = (List<String>)table.column(typeCode).get(id) ;
             if(list==null){
             	out.write("");
             }else{
             	String str="";
             	for(int i=0;i<list.size();i++){
             		if(i<list.size()-1){
             			str+=list.get(i)+",";
             		}else{
             			str+=list.get(i);
             		}
             	}
             	out.write(str);
             }
        }
    }
%>