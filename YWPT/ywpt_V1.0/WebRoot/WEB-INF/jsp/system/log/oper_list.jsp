<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display: none">
	<p id="alert-content" align="center"></p>
</div>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues() {
		$("#ip").val("");
		$("#operator").val("");
		$("#beginDate").val("");
		$("#endDate").val("");
		$("#operateType").val("");
	}
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding: 6px 0 4px;">
		<form class="form-search" action="${root}/system/log/operlist/${menuid}" method="post"  id="queryForm" style="margin:0; padding:0 6px;">
			<table id="search_table" class="table-pad-td" width="100%">
				<tr>
				  <td>开始日期</td>
				  <td>结束日期</td>
				  <td>IP地址</td>
				  <td>操作员</td>
				  <td>操作类型</td>
				  <td></td>
				</tr>
				<tr>
               	  <td><input name="search_startTime" type="text" value="${log.startTime}" class="Wdate" id="beginDate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\',{d:0})}'})"
                           readonly="readonly" style="width: 80%;" placeholder="开始日期"/></td>
                  <td><input name="search_endtime" type="text" value="${log.endtime}" class="Wdate" id="endDate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDate\',{d:0})}'})"
                           readonly="readonly" style="width: 80%;" placeholder="结束日期"/></td>
				  <td><input style="width:80%;" id="ip" name="search_ip" value="${log.ip}" type="text" placeholder="IP地址" /></td>
				  <td><input style="width:80%;" id="operator" name="search_operator" value="${log.operator}" type="text" placeholder="操作员" /></td>
				  <td>
				  	<select id="operateType" name="search_operateType" style="width:80%;min-width: 80px;">
				  	   <option value="">请选择</option>
				  	   <option value="a" ${log.operateType eq 'a'?'selected':''}>新增</option>
					   <option value="u" ${log.operateType eq 'u'?'selected':''}>修改</option>
					   <option value="d" ${log.operateType eq 'd'?'selected':''}>删除</option>
					   <option value="o" ${log.operateType eq 'o'?'selected':''}>其他</option>
				  	   
				  	</select>
				  </td>
				  <td style="min-width:140px;">
				    <input type="submit" class="btn btn-info mar_l10" value="查询" style="height: 28px;" />
					<input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height: 28px;" />
				  </td>
				</tr>
			</table>
		</form>
	</div>
	<div class="table_box">
		<div class="btn-group-wrap mar_b5">
		  <div class="btn-group pull-right">
			<btn:authorBtn menuid="${menuid}" text="导出"><button class="btn btn-small" onclick="doExport();"><i class="icon-download"></i>导出</button></btn:authorBtn>
			<btn:authorBtn menuid="${menuid}" text="删除"><button class="btn btn-small" onclick="removeLog();"><i class="icon-remove"></i>删除</button></btn:authorBtn>
		  </div>
		  <div class="clear"></div>
		</div>
		<table class="table table-striped table-bordered table-style" id="table">
			<thead>
				<tr>
					<th style="text-align:center;width:20px;"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
					<th>所属模块</th>
					<th>描述信息</th>
					<th>IP地址</th>
					<th>操作时间</th>
					<th>是否成功</th>
					<th>操作类型</th>
					<th>操作员</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pageList.result}" var="log">
					<tr data="${log.id}">
						<td style="text-align:center;"><input type="checkbox" value="${log.id}" name="select-chk" /></td>
						<td>${log.moduleName}</td>
						<td>${log.des}</td>
						<td>${log.ip}</td>
						<td><fmt:formatDate value="${log.operateTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
						<td>${log.isSuccess eq '1'?'成功':'失败'}</td>
						<td>
							<c:if test="${log.operateType == 'a'}">
								新增
							</c:if>
							<c:if test="${log.operateType == 'u'}">
								修改
							</c:if>
							<c:if test="${log.operateType == 'd'}">
								删除
							</c:if>
							<c:if test="${log.operateType == 'o'}">
								其他
							</c:if>
						</td>
						<td>${log.operator}</td>
						<td>
							<a href="javascript:viewById('${log.id}')"> <i class="icon-eye-open"></i>查看</a>
							<a href="javascript:delLogById('${log.id}')"> <i class="icon-remove"></i>删除</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${pageList.result!=null}">
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="9">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<tags:pagination page="${pageList}"></tags:pagination>
	</div>
</div>
<script type="text/javascript">

//导出
function doExport(){
	window.location.href="${root}/system/log/export/doExport/${menuid}/?tag=log&${searchParams}";
}

function viewById(id){
	window.location.href="${root}/system/log/showOperview/"+id+"/${menuid}/";
}
//批量删除记录
function removeLog() {
	var ids = getSelectedValue();
	if (ids.length == 0) {
		showMessage("请选择要删除的记录。");
	} else {
		delLogById(ids);
	}
}
//删除单条记录
function delLogById(ids) {
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
					setTimeout("reloadPage()", 1600);
				} else {
					showMessage("删除失败");
				}
			}
		});
	}
}
//重新加载数据
function reloadPage() {
	window.location.href = "${root}/system/log/operlist/${menuid}/?search_startTime=" + $("#beginDate").val() + "&search_endtime=" + $("#endDate").val() + "&search_ip=" + $("#ip").val() + "&search_operator=" + $("#operator").val() + "&search_operateType=" + $("#operateType").val()

}

</script>

