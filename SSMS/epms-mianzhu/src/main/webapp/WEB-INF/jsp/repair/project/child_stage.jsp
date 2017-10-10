<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="sileder_login">
	<c:forEach items="${childState }" var="item" varStatus="vs">
    <li ${vs.index == 0 ? 'class=\"hot\"' : '' } id="${item.id }">${item.name }</li>
	</c:forEach>
</ul>