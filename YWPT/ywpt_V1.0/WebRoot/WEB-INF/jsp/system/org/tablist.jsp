<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${root}/compnents/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
<script src="${root}/compnents/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none">
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
<div class="row-fluid">
	<div class="conten_box">
		<div class="row-fluid" style="border-radius:5px 5px 0 0; -moz-border-radius:5px 5px 0 0; -webkit-border-radius:5px 5px 0 0; padding:6px 0 4px; background:#d5e4f0; border-bottom:1px solid #bdc5d9;">
			<form class="form-inline" action="${root}/system/org/tablist/${menuid}/" method="post">
				<div class="search_item pull-left mar_l10">
					<span style="clear:both">部门编码：</span><input class="input-large" style="width:150px;height:18px;" name="search_code" value="${department.code}" type="text" placeholder="部门编码" />
				</div>
				<div class="search_item pull-left mar_l10">
					<span style="clear:both">部门名称：</span><input class="input-large" style="width:150px;height:18px;" name="search_name" value="${department.name}" type="text" placeholder="部门名称" /> <input type="submit" class="btn btn-small btn-info" style="height:28px;" value="查询" />
				</div>
			</form>
		</div>
		<div class="clear"></div>
		<div class="table_box">
			<table class="table table-striped table-bordered  table-condensed table-style" id="tree_table" width="100%">
				<thead align="center">
					<tr align="center">
						<th style="text-align:center;">部门名称</th>
						<th  style="text-align:center;" >部门编码</th>
						<th class="center">备注</th>
						<th class="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${departList}" var="org" varStatus="status">
						<tr id="${org.id}" <c:if test="${org.parentid!=null}">pId="${org.parentid}"</c:if>   >
							<td width="20%"  style="text-align:center;">${org.name}</td>
							<td width="20%"  style="text-align:center;">${org.code}</td>
							<td class="texthidden"><div style="text-overflow:ellipsis;overflow:hidden;width:150px">${role.memo}</div></td>
							<td class="center">
								<btn:authorBtn menuid="${menuid}" text="添加">
									<button class="btn btn-small" onclick="addNode('${org.id}')">
										<i class="icon-plus"></i>添加
									</button>
								</btn:authorBtn>
								<btn:authorBtn menuid="${menuid}" text="修改">
									<button class="btn btn-small" onclick="updateNode('${org.id}')">
										<i class="icon-edit"></i>修改
									</button>
								</btn:authorBtn>
								<btn:authorBtn menuid="${menuid}" text="删除">
									<button class="btn btn-small" onclick="delNode('${org.id}')">
										<i class="icon-remove"></i>删除
									</button>
								</btn:authorBtn>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--

	function addNode(id) {
		window.location.href = "${root}/forward/system/org/add/?menuid=${menuid}&parentid=" + id;
	}

	function updateNode(ids) {
		window.location.href = "${root}/system/org/update/" + ids + "/${menuid}/";
	}

	function delNode(ids) {
		if (ids == '00') {
			alert("根节点不能删除。");
			return;
		}
		if (confirm("该部门下的子部门将会同时被删除且无法恢复，确定要删除吗？")) {
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
					}
				}
			});
		}
	}

	$(document).ready(function() {
		$("#alert-div").hide();
		$("#tree_table").treeTable({
			expandLevel :1,
			beforeExpand : function($treeTable, id) {
				 $("tr[pId="+id+"]",$treeTable).each(function(){
				 	$(this).insertAfter("#"+id); 
				 });
				 var tr = $("tr",$treeTable).length;
				 var addHeight = (tr*31)+50;
				 var ifra = parent.$("#content-frame");
				 ifra.height(addHeight);
				 return false;
       		}
			
		});
	});
	
	function showList(){
		window.location.href = "${root}/system/org/list/${menuid}/";
    }
//-->
</script>
