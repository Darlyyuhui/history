<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script type="text/javascript" src="${root}/js/cross/jms/amq_jquery_adapter.js"></script>
<script type="text/javascript" src="${root}/js/cross/jms/amq.js"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none">
	<p id="alert-content" align="center"></p>
</div>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
		$("#ip").val("");
        $("#operator").val("");
        $("#beginDate").val("");
        $("#endDate").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="row-fluid">
	<div class="conten_box">
		<div class="row-fluid search-area" style="padding:6px 0 4px;">
		   <form class="form-inline" action="${root}/system/log/errorlist/${menuid}" method="post" style="margin:0; padding:0 10px 0 5px;">
		      <table id="search_table" width="100%">
				<tr>
				<td width="64" align="center">开始日期</td>
                <td><input name="search_startTime" type="text" value="${log.startTime}" class="Wdate" id="beginDate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\',{d:0})}'})"
                           readonly="readonly" style="width: 80%;" placeholder="开始日期"/></td>
                <td width="64" align="center">结束日期</td>
                <td><input name="search_endtime" type="text" value="${log.endtime}" class="Wdate" id="endDate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDate\',{d:0})}'})"
                           readonly="readonly" style="width: 80%;" placeholder="结束日期"/></td>
					<td width="50" align="center">IP地址</td>
					<td><input class="input-large" style="width:80%; height:18px;" id="ip" name="search_ip" value="${log.ip}" type="text" placeholder="IP地址" /></td>
					<td width="56" align="center">操作员</td>
					<td><input class="input-large" style="width:80%; height:18px;" id="operator" name="search_operator" value="${log.operator}" type="text" placeholder="操作员" /></td>
					<td style="width:140px;">
					<input type="submit" class="btn btn-info mar_l10" value="查询" style=" height:28px;*+margin-left:10px;" />
					<input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height:28px;"/></td>
				</tr>
			  </table>
		   </form>
	</div>
	<div class="clear"></div>
	 <div class="row-fluid">
            <div class="table_box">
				<table class="table table-striped table-bordered table-style" id="table">
					<thead>
						<tr>
							<th>类名</th>
							<th>方法名</th>
							<th>描述</th>
							<th>IP地址</th>
							<th>发生时间</th>
							<th>操作员</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${pageList.result}" var="log">
							<tr data="${log.id}">
								<td>${log.className}</td>
								<td>${log.methodName}</td>
								<td>${log.des}</td>
								<td>${log.ip}</td>
								<td><fmt:formatDate value="${log.operateTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
								<td>${log.operator}</td>
								<td><a href="javascript:viewById('${log.id}')"><i class="icon-eye-open"></i>查看</a></td>
							</tr>
						</c:forEach>
						<c:if test="${pageList.result!=null}">
							<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
								<tr height="28">
									<td colspan="9">&nbsp;</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<tags:pagination page="${pageList}"></tags:pagination>
			</div>
		</div>
	</div>
</div>
<!--/row-->
<script type="text/javascript">
	//-->
	function viewById(id) {
		var page = '${current}';
		window.location.href = "${root}/system/log/showview/" + id+ "/${menuid}/?page=" + page;
	}
	function delById(id) {
		if (confirm("删除后数据将无法恢复，确定要删除吗？")) {
			var url = "${root}/system/log/delete/" + ids + "/";
			$.ajax({
				type : 'delete',
				url : url,
				dataType : "json",
				success : function(msg) {
					if (msg.result == "ok") {
						$("#alert-div").removeClass("alert-error").addClass("alert-success");
						showMessage("删除成功");
						setTimeout("showList()", 1600);
					} else {
						showMessage("删除失败");
					}
				}
			});
		}
	}
	
	function showList(){
		window.location.href = "${root}/system/log/errorlist/${menuid}/";
    }
</script>
