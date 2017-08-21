<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.util.DateUtil"%>
<%@ attribute name="date" type="java.lang.String" required="true"%>
<%
String day = DateUtil.getWeek(date);
if(day==null){
	out.print("");
}else{
  	out.print(day);
}
%>

