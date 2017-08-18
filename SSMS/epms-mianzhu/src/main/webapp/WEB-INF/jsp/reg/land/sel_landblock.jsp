<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${fn:length(lbList) == 0 }">
	<span style="color: red;">无可用地块信息</span>
</c:if>
<c:if test="${fn:length(lbList) != 0 }">
	<c:forEach items="${lbList }" var="item">
		<input type="checkbox" name="lb_ckb" value="${item.id }" ${checkedMap[item.id] ? 'checked' : '' } ${isEdit eq '1' ? '' : 'disabled=\"disabled\"' } />${item.name }
	</c:forEach>
</c:if>