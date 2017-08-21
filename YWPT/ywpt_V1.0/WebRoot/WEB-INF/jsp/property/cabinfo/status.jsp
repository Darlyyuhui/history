<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none">
	<p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p id="mesg" align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
	    $("#name").val("");
		$("#contactperson").val("");
		$("#contactphone").val("");
    }
</script>

<script language="JavaScript">
function myrefresh(){
   window.location.reload();
}
setTimeout('myrefresh()',3000); //指定3秒刷新一次
</script> 

<!-- 重置查询输入框结束 -->
<div class="conten_box">

	<div class="table_box">
		<div class="btn-group-wrap mar_b5">
			
			<div class="clear"></div>
		</div>
		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			<thead>
				<tr>
					 	
					 <th>机柜名称</th>
					 <th>IP</th>
					 <th>下发状态</th>
					 
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${icaList}" var="icab">
				<tr data="${icab.id}">
					
					<td>${icab.cabinfo.name}</td>
					<td>${icab.ip}</td>
					<td>${icab.message}</td>
					
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
		
	</div>
</div>
<script type="text/javascript">

</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>