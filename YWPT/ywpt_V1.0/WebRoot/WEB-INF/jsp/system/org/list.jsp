<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${root}/compnents/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
<script src="${root}/compnents/treeTable/jquery.treeTable.depart.js" type="text/javascript"></script>
<div class="alert alert-block pull-top  alert-error" id="alert-div" style="display: none">
	<p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1800);
	</script>
</c:if>
<div class="row-fluid">
	<div class="conten_box">
		<div class="row-fluid search-area" style="padding: 6px 0 4px;">
			<form class="form-inline" action="${root}/system/org/list/${menuid}/" method="post" style="margin: 0; padding: 0 6px;">
				<table>
					<tr>
						<td class="search_item_td">部门名称</td>
						<td><input class="input-large" style="width: 150px; height: 18px;" id="name" name="search_name" value="${name}" type="text" placeholder="部门名称" /></td>
						<td class="search_item_td">部门编码</td>
						<td><input class="input-large" style="width: 150px; height: 18px;" id="code" name="search_code" value="${orgcode}" type="text" placeholder="部门编码" /></td>

						<td><input type="submit" class="btn btn-info" style="height: 28px; margin-left: 10px;" value="查询" /> <input onclick="retsetValues()" type="button" class="btn mar_l10" value="重置" style="height: 28px;" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="clear"></div>
		<div class="table_box" style="max-height: 600px; overflow: auto">
			<table id="tree-table" class="table table-striped table-bordered  table-condensed table-style" width="100%">
				<thead>
					<tr>
						<th>名称</th>
						<th width="86">编码</th>
						<th>责任人</th>
						<th width="86">联系方式</th>
						<th>备注</th>
						<th width="160">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="depart">
						<tr id="${depart.id}" pId="${depart.parentid}" level="${depart.level}">
							<td>${depart.name}</td>
							<td>${depart.code}</td>
							<td>${depart.principalName}</td>
							<td>${depart.mobile}</td>
							<td class="texthidden"><div style="width:100px;height:22px;margin-top:2px;white-space:nowrap;overflow:hidden;-o-text-overflow:ellipsis;text-overflow:ellipsis;" title="${depart.memo}">${depart.memo}</div></td>
							<td>
							    <btn:authorBtn menuid="${menuid}" text="修改">
									<a href="${root}/system/org/update/${depart.id}/${menuid}/"><i class="icon-edit"></i>修改</a>
								</btn:authorBtn> 
								<c:if test="${depart.parentid ne '0'}">
									<c:if test="${depart.rank ne '0'}">
										<btn:authorBtn menuid="${menuid}" text="删除">
											<a href="javascript:void(0)" onclick="delNode('${depart.id}')"><i class="icon-remove"></i>删除</a>
										</btn:authorBtn>
									</c:if>
								</c:if> 
								<btn:authorBtn menuid="${menuid}" text="添加">
									<a href="${root}/system/org/add/${depart.id}/${menuid}/"><i class="icon-plus"></i>添加子部门</a>
								</btn:authorBtn></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<input type="hidden" id="menuid" value="${menuid}" />
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#tree-table").treeTable({expandLevel :2});
		//隔行换色
		$("#tree-table tbody tr").css("background","fff");
		//$("#tree-table tbody tr:even").css("background","#fff");
	});

	function retsetValues(){
		$("#code").val("");
		$("#name").val("");
	}
	
	function delNode(ids) {
		if (ids == '00') {
			alert("根节点不能删除。");
			return;
		}
		if (confirm("确定要删除吗？")) {
			var url = "${root}/system/org/delete/" + ids + "/";
			$.ajax({
				type : 'delete',
				url : url,
				dataType : "json",
				success : function(msg) {
					if (msg.result == "ok") {
						$("#alert-div").removeClass("alert-error").addClass("alert-success");
						showMessage("删除成功");
						setTimeout("showList()", 1600);
					}else if (msg.result == "hasChild") {
						showMessage("组织机构下存在子部门，不能直接删除！请先删除子部门后再试！");
					}else if (msg.result == "exist") {
						showMessage("组织机构下存在设备："+msg.message+"，不能直接删除！");
					}else if (msg.result == "error") {
						showMessage("该部门已被其他数据所引用："+msg.message+"，不能直接删除！");
					}
				}
			});
		}
	}
	
	function showList(){
		window.location.href = "${root}/system/org/list/${menuid}/";
    }
</script>