<%@tag pageEncoding="UTF-8" %>
<%@ attribute name="page" type="com.xiangxun.atms.framework.base.Page" required="true" %>
<%@ attribute name="cellCount" type="java.lang.Integer" required="false" %>
<%@ attribute name="showPageInfo" type="java.lang.Boolean" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //分页中显示出来的页码个数
    int cells = cellCount == null ? 7 : cellCount;
    int begin = Math.max(1, page.getCurrentPageNo() - cells / 2);
    int end = Math.min(begin + (cells - 1), page.getTotalPageCount());
    request.setAttribute("begin", begin);
    request.setAttribute("end", end);
    request.setAttribute("current", page.getCurrentPageNo());
    request.setAttribute("total", page.getTotalSize());
    request.setAttribute("totalPage", page.getTotalPageCount());
    request.setAttribute("showPage", showPageInfo == null ? true : false);

    int endRowNum = page.getStart() + page.getPageSize();
    if (page.getStart() > 0) {
        endRowNum -= 1;
    }
    request.setAttribute("endRowNum", endRowNum);
%>
<div class="row">
    <div class="col-xs-12">
        <div class="col-sm-6">
            <div class="dataTables_info" id="sample-table-2_info">显示第&nbsp;${page.start == 0 ? 1 : page.start}&nbsp;-&nbsp;${endRowNum}&nbsp;条记录,共<b
                    style="color:blue">${total}</b>条
            </div>
        </div>
        <div class="col-sm-6">
            <div class="dataTables_paginate paging_bootstrap pagination-small">
                <ul class="pagination no-margin">
                    <% if (page.hasPreviousPage()) {%>
                    <li class="paginate_button "><a href="?page=1&sortType=${sortType}&${searchParams}">&lt;&lt;</a>
                    </li>
                    <li class="paginate_button "><a href="?page=${current-1}&sortType=${sortType}&${searchParams}">
                        &lt;</a>
                    </li>
                    <%} else {%>
                    <li class="disabled"><a href="#">&lt;&lt;</a></li>
                    <li class="disabled"><a href="#">&lt;</a></li>
                    <%} %>
                    <c:forEach var="i" begin="${begin}" end="${end}">
                        <c:choose>
                            <c:when test="${i == current}">
                                <li class="paginate_button active"><a
                                        href="?page=${i}&sortType=${sortType}&${searchParams}">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="paginate_button"><a
                                        href="?page=${i}&sortType=${sortType}&${searchParams}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <% if (page.hasNextPage()) {%>
                    <li class="paginate_button next"><a href="?page=${current+1}&sortType=${sortType}&${searchParams}">
                        &gt;</a>
                    </li>
                    <li class="paginate_button next"><a
                            href="?page=${page.totalPageCount}&sortType=${sortType}&${searchParams}">&gt;&gt;</a></li>
                    <%} else {%>
                    <li class="disabled"><a href="#">&gt;</a></li>
                    <li class="disabled"><a href="#">&gt;&gt;</a></li>
                    <%} %>
                </ul>
            </div>
        </div>
    </div>
</div>