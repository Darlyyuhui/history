<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*"%>
<%@tag import="com.xiangxun.atms.module.maintain.domain.MaintainCompany"%>
<%@tag import="java.util.*"%>
<%@tag import="com.xiangxun.atms.module.maintain.cache.MaintainCompanyCache"%>
<%@ attribute name="defaultValue" type="java.lang.String" required="false"%>
<%@ attribute name="compId" type="java.lang.String" required="false"%>
<%@ attribute name="emptyText" type="java.lang.String" required="false" %>
<%--
emptyText 不传值时默认首选项文本为：请选择；传空值则没有首选项；有值显示为首选项的文本内容
 ！！！！首选项的value均为空字符串！！！！  
 
 compId  	        详情页反显名称用
 defaultValue  下拉列表框默认选中值
  ！！！！compId 和 defaultValue 两个参数一次只能传其中一个值！！！！  
 --%>
<%
Cache cache =(Cache)com.xiangxun.atms.framework.base.ApplicationContextHolder.getBean("ehcacheImpl");
List<MaintainCompany> list = (List<MaintainCompany>)cache.get(MaintainCompanyCache.CACHE_LIST_KEY);
Map<String, String> map = (Map<String, String>)cache.get(MaintainCompanyCache.CACHE_NAME_KEY);

if (compId != null) {
	out.write(map.get(compId)==null?"":map.get(compId));
} else {
	if (emptyText == null) {
		out.write("<option value=\"\">请选择</option>");
	} else if (!"".equals(emptyText)) {
		out.write("<option value=\"\">"+emptyText+"</option>");
	}
	for (MaintainCompany mc : list) {
		if (mc.getId().equals(defaultValue)) {
    		out.write("<option value=\""+mc.getId()+"\" selected>"+mc.getName()+"</option>");
    	} else {
    		out.write("<option value=\""+mc.getId()+"\">"+mc.getName()+"</option>");
    	}
	}
}
%>

