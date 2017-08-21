<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display: none">
	<p id="alert-content" align="center"></p>
</div>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues() {
		$("#operatorId").val("");
		$("#beginDate").val("");
		$("#endDate").val("");
		$("#type").val("");
		$("#operatorName").val("");
	}
</script>
<!-- 重置查询输入框结束 -->
<div class="row-fluid">
	<div class="conten_box">
		<div class="row-fluid search-area" style="padding: 6px 0 4px;">
			<form class="form-inline" action="${root}/system/log/loginlist/${menuid}" method="post" style="margin: 0; padding: 0 6px 0 10px;">
				<table class="table-pad-td" width="100%">
					<tr>
					  <td>开始日期</td>
					  <td>结束日期</td>
					  <td>操作账号</td>
					  <td>操作员姓名</td>
					  <!-- <td>类型</td> -->
					  <td></td>
					</tr>
					<tr>
	                  <td><input name="search_startTime" type="text" value="${log.startTime}" class="Wdate" id="beginDate"
	                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\',{d:0})}'})"
	                           readonly="readonly" style="width: 80%;" placeholder="开始日期"/></td>
	                  <td><input name="search_endtime" type="text" value="${log.endtime}" class="Wdate" id="endDate"
	                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDate\',{d:0})}'})"
	                           readonly="readonly" style="width: 80%;" placeholder="结束日期"/></td>
					  <td><input style="width: 80%;" id="operatorId" name="search_operatorId" value="${log.operatorId}" type="text" placeholder="操作账号" /></td>
					  <td><input style="width: 80%;" id="operatorName" name="search_operatorName" value="${log.operatorName}" type="text" placeholder="操作员名称" /></td>
					  <%-- <td>
					    <select id="type" name="search_type" style=" width: 80%;min-width:80px;" class="required">
						  <option value="">请选择</option>
						  <option value="1" ${log.type == '1'?'selected':''}>登录</option>
						  <option value="2" ${log.type == '2'?'selected':''}>退出</option>
						</select>
					  </td> --%>
					  <td style="min-width:140px;">
					    <input type="submit" class="btn btn-info mar_l10 mar_r10" value="查询" style="height: 28px;" />
						<input onclick="reValues()" type="button" class="btn" value="重置" style="height: 28px;" />
					  </td>
					</tr>
				</table>
			</form>
		</div>
		<div class="table_box">
			<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th>操作账号</th>
						<th>操作员姓名</th>
						<th>IP地址</th>
						<th>类型</th>
						<th>发生时间</th>
						<th>内容</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="log">
						<tr data="${log.id}">
							<td>${log.operatorId}</td>
							<td>${log.operatorName}</td>
							<td>${log.ipAddress}</td>
							<td>${log.type==1?"登录":"退出"}</td>
							<td><fmt:formatDate value="${log.operationTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
							<td class="texthidden">${log.content}</td>
							<td><btn:authorBtn menuid="${menuid}" text="查看">
									<a href="javascript:viewById('${log.id}')"> <i class="icon-eye-open"></i>查看</a>
								</btn:authorBtn></td>
						</tr>
					</c:forEach>
					<c:if test="${pageList.result!=null}">
						<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
							<tr>
								<td colspan="8">&nbsp;</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<tags:pagination page="${pageList}"></tags:pagination>
		</div>
	</div>
	<!--/row-->
</div>
<script type="text/javascript">

function viewById(id){
	window.location.href="${root}/system/log/showloginview/"+id+"/${menuid}";
}
</script>
