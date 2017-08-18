<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- Ajax调用返回界面 --%>
<c:if test="${fn:length(samplingTypes) == 0 }">
	<span style="color: red;">无可选采样类型</span>
</c:if>
<c:if test="${fn:length(samplingTypes) gt 0 }">
	<c:forEach items="${samplingTypes }" var="item">
		<input type="radio" name="sampleCode" value="${item.code }" ${code eq item.code ? 'checked' : '' } />${item.name }&nbsp;&nbsp;
	</c:forEach>
</c:if>
