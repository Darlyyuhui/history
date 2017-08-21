<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="com.xiangxun.atms.framework.base.Page" required="true"%>
<%@ attribute name="pageaction" type="java.lang.String" required="true"%>
<%@ attribute name="cellCount" type="java.lang.Integer" required="false" %>
<%@ attribute name="showPageInfo" type="java.lang.Boolean" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
//分页组件自定义标签 YANTAO ADD  POST提交模式
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
%>

<div class="pagination pagination-small" style="margin:0; float:left;">
	<ul>
		 <% if (page.hasPreviousPage()){%>
               	<li><a onclick="postSearch('page=1&sortType=${sortType}')" href="#">&lt;&lt;</a></li>
                <li><a onclick="postSearch('page=${current-1}&sortType=${sortType}')" href="#">&lt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a onclick="postSearch('page=${i}&sortType=${sortType}')" href="#">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a onclick="postSearch('page=${i}&sortType=${sortType}')" href="#">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNextPage()){%>
               	<li><a onclick="postSearch('page=${current+1}&sortType=${sortType}')" href="#">&gt;</a></li>
                <li><a onclick="postSearch('page=${page.totalPageCount}&sortType=${sortType}')" href="#">&gt;&gt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
         <%} %>
	</ul>
</div>
<c:if test="${showPage}">
	<div class="page_info" style="line-height:30px;">当前第<b style="color:blue">${current}</b>页，每页 <%=page.getPageSize()%>条，共<b style="color:blue">${totalPage}</b>页,共<b style="color:blue">${total}</b>条记录</div>
	<div class="clear"></div>
</c:if>

<form id="tableForm" name="tableForm" class="form-inline" action="${pageaction}" method="post" style="display:none;">
    <span id="searchv"></span>
</form>
<script type="text/javascript">
  function postSearch(p){
  
  var htmlstr = "";
  var parry = p.split('&');
  
  for(var i=0 ; i < parry.length ; i++){
      var dname = parry[i].split("=")[0];
      var dvalue = parry[i].split("=")[1];
      htmlstr += "<input name='"+dname+"' value='"+dvalue+"' />";
  }
  
  
  var paramestr = '&${searchParams}';
  var parry2 = paramestr.split('&');
  for(var i=0 ; i < parry2.length ; i++){
      var dname = parry2[i].split("=")[0];
      var dvalue = parry2[i].split("=")[1];
      htmlstr += "<input name='"+dname+"' value='"+dvalue+"' />";
  }
     

  document.getElementById("searchv").innerHTML=htmlstr;
  var f = document.getElementById("tableForm");
  f.submit();
     
  }
</script>
