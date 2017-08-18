<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="com.xiangxun.atms.framework.base.Page" required="true"%>
<%@ attribute name="cellCount" type="java.lang.Integer" required="false" %>
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
    int curPageSize = page.getResult().size();
%>
<div class="row">
        <div class="col-xs-12">
 <div class="col-sm-6">
        <div class="dataTables_info" id="sample-table-2_info">当前第<b style="color:blue">${current}</b>页，每页显示 <%=page.getPageSize()%>条</div>
    </div>
    <div class="col-sm-6">
        <div class="dataTables_paginate paging_bootstrap pagination-small">
            <ul class="pagination no-margin">
        <% if ((page.getCurrentPageNo()-1>0)){%>
                <li class="paginate_button active"><a href="?search_prevRow=${page.prevRow}&page=${current-1}&${searchParams}">&lt;</a></li>
        <%}else{%>
                <li class="paginate_button disabled"><a href="javascript:void(0)">&lt;</a></li>
        <%}%>

        <% if (page.hasNextPage()){%>
                <li><a href="?page=${current+1}&${searchParams}">&gt;</a></li>
        <%}else if(page.getResult().size()<page.getPageSize()){%>
                <li class="disabled"><a href="javascript:void(0)">&gt;</a></li>
        <%}else{%>
        		<li ><a href="?page=${current+1}&${searchParams}">&gt;</a></li>
        <%} %>
	</ul>
	  </div>
    </div>
</div>
