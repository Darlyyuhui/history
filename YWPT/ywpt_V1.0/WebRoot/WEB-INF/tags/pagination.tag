<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="com.xiangxun.atms.framework.base.Page" required="true"%>
<%@ attribute name="cellCount" type="java.lang.Integer" required="false" %>
<%@ attribute name="showPageInfo" type="java.lang.Boolean" required="false" %>
<%@ attribute name="searchSession" type="java.lang.Boolean" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
//分页中显示出来的页码个数
int cells = cellCount==null?10:cellCount;
int begin = Math.max(1, page.getCurrentPageNo() - cells/2);
int end = Math.min(begin + (cells - 1), page.getTotalPageCount());
request.setAttribute("begin", begin);
request.setAttribute("end", end);
request.setAttribute("current",  page.getCurrentPageNo());
request.setAttribute("total",page.getTotalSize());
request.setAttribute("totalPage",page.getTotalPageCount());
request.setAttribute("showPage",showPageInfo==null?true:false);
request.setAttribute("andSearch",searchSession==null?true:false);
%>

<div class="pagination pagination-small" style="margin:0; float:left;">
	<ul>
		 <% if (page.hasPreviousPage()){%>
               	<li><a href="?page=1&sortType=${sortType}&${andSearch?searchParams:'isgetsession=1'}">&lt;&lt;</a></li>
                <li><a href="?page=${current-1}&sortType=${sortType}&${andSearch?searchParams:'isgetsession=1'}">&lt;</a></li>
         <%}else{%>
                <li class="disabled"><a>&lt;&lt;</a></li>
                <li class="disabled"><a>&lt;</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a href="?page=${i}&sortType=${sortType}&${andSearch?searchParams:'isgetsession=1'}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="?page=${i}&sortType=${sortType}&${andSearch?searchParams:'isgetsession=1'}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNextPage()){%>
               	<li><a href="?page=${current+1}&sortType=${sortType}&${andSearch?searchParams:'isgetsession=1'}">&gt;</a></li>
                <li><a href="?page=${page.totalPageCount}&sortType=${sortType}&${andSearch?searchParams:'isgetsession=1'}">&gt;&gt;</a></li>
         <%}else{%>
                <li class="disabled"><a>&gt;</a></li>
                <li class="disabled"><a>&gt;&gt;</a></li>
         <%} %>
	</ul>
</div>
<c:if test="${showPage}">
	<div class="page_info" style="line-height:30px;">当前第<b style="color:blue">${current}</b>页，每页 <%=page.getPageSize()%>条，共<b style="color:blue">${totalPage}</b>页,共<b style="color:blue">${total}</b>条记录</div>
	<div class="clear"></div>
</c:if>

