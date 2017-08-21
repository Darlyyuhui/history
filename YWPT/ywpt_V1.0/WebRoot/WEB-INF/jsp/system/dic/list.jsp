<%@ page language="java" pageEncoding="UTF-8"%>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
	<p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
        $("#dic_type_chzn>a span").html("请选择");
        $("#dic_type").val("");
        $("#code").val("");
        $("#name").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/system/dic/list/${groupid}/${menuid}/" method="post" id="dicForm" style="margin:0;padding:0 6px;">
			<table>
			  <tr>
			    <td class="td40">编码</td>
			    <td><input style="width:150px;" id="code" name="search_code" value="${dic.code}" maxlength="11" type="text" placeholder="编码" /></td>
			    <td class="td40">名称</td>
			    <td><input style="width:150px;" id="name" name="search_name" value="${dic.name}" maxlength="20" type="text" placeholder="名称" /></td>
			    <td class="td40">类型</td>
			    <td>
			      <select id="dic_type" name="search_type" style="width:150px;">
					<option value="">请选择</option>
					<c:forEach items="${dic_sec}" var="keyValue">
						<option value="${keyValue.key}" gencode="${keyValue.gencode}" ${keyValue.key==dic.type.value?'selected':''}>${keyValue.name}</option>
					</c:forEach>
				  </select>
				</td>
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info mar_r10 mar_l10" value="查询"/>
			      <input onclick="reValues()" type="button" class="btn" value="重置"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="table_box">
		<div class="btn-group-wrap mar_b5">
		  <div class="btn-group pull-right">
			<btn:authorBtn menuid="${menuid}" text="添加"><button class="btn btn-small" onclick="addDic();"><i class="icon-plus"></i>添加</button></btn:authorBtn>
			<btn:authorBtn menuid="${menuid}" text="删除"><button class="btn btn-small" onclick="removeDic();"><i class="icon-remove"></i>删除</button></btn:authorBtn>
		  </div>
		  <div class="clear"></div>
		</div>
		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			<thead>
				<tr>
					<th style="text-align:center;width:20px;"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
					<th width="8%">编码</th>
					<th width="12%">名称</th>
					<th width="12%">类型</th>
					<th width="53%">备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pageList.result}" var="dic">
					<tr data="${dic.id}">
						<td style="text-align:center;"><input type="checkbox" value="${dic.id}"/></td>
						<td>${dic.code}</td>
						<td>${dic.name}</td>
						<td><c:forEach items="${dic_sec}" var="keyValue">
									${keyValue.key==dic.type.value?keyValue.name :''}
							</c:forEach>
						</td>
						<td class="texthidden" title="${dic.remark}"><div style="text-overflow:ellipsis;overflow:hidden;width:350px;height:20px;whtie-space:nowrap;">${dic.remark}</div></td>
					    <td>
					    	<btn:authorBtn menuid="${menuid}" text="查看">
								<a href="javascript:viewById('${dic.id}')"> <i class="icon-eye-open"></i>查看</a>
							</btn:authorBtn> 
					    	<btn:authorBtn menuid="${menuid}" text="修改">
								<a href="javascript:updateDicById('${dic.id}')"> <i class="icon-edit"></i>修改</a>
							</btn:authorBtn> 
							<btn:authorBtn menuid="${menuid}" text="删除">
								<a href="javascript:delDicById('${dic.id}')"> <i class="icon-remove"></i>删除</a>
							</btn:authorBtn>
						</td>
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
<script type="text/javascript">
	function addDic() {
		window.location.href = "${root}/forward/system/dic/add/?groupid=${groupid}&menuid=${menuid}&type=" + $("#dicForm :selected").val();
	}

	function updateDicById(id) {
		var page = '${current}';
		window.location.href = "${root}/system/dic/update/${groupid}/" + id + "/${menuid}/?page=" + page;
	}
	
	function viewById(id){
		var page = '${current}';
		window.location.href = "${root}/system/dic/showView/${groupid}/" + id + "/${menuid}/?page=" + page;
	}

	function removeDic() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要删除的记录。");
		} else {
			delDicById(ids);
		}
	}

	function delDicById(ids) {
		if (confirm("删除后数据将无法恢复，确定要删除吗？")) {
			var url = "${root}/system/dic/delete/" + ids + "/";
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

	function reloadPage() {
		window.location.href = "${root}/system/dic/list/${groupid}/${menuid}/?search_type=" + $("#dicForm :selected").val()

	}
</script>