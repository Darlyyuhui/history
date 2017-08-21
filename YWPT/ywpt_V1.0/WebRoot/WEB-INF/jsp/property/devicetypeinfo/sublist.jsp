<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<c:if test="${not empty message}">
	<script type="text/javascript">
		$(document).ready(function(){
			if($("#submsg").val() != ""){
				parent.setMesg($("#submsg").val());
			}
			$("#submsg").val("");
		});
	</script>
</c:if>
<input id="submsg" type="hidden" name="submsg" value="${message}"/>
