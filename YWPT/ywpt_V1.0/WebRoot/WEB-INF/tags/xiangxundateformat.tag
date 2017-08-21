<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.util.DateUtil"%>
<%@ attribute name="date" type="java.util.Date" required="true"%>
<%
if(null == date || "".equals(date)) {
	out.print("");
}
else {
	String datestr = DateUtil.getHalfHourTime(date);
	if(datestr==null){
		out.print("");
	}else{
	  	out.print(datestr);
	}
}
%>

