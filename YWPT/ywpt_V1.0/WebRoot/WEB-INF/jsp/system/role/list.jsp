<%@ page language="java" pageEncoding="UTF-8"%>
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
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
        $("#name").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/system/role/list/${menuid}/" method="post" style="margin:0; padding:0 6px;">
			<table>
			  <tr>
			    <td class="search_item_td">角色名称</td>
			    <td><input style="width:150px;" id="name" name="search_name" value="${role.name}" type="text" placeholder="角色名称" /> </td>
			    <td>
			      <input type="submit" class="btn btn-info mar_l10" value="查询" />
				  <input onclick="reValues()" type="button" class="btn mar_l10" value="重置"/>
				</td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="table_box">
    	<div class="btn-group-wrap mar_b5">
        	<div class="pull-left mar_l5"><small style="color:blue">注意：如果使用IE浏览器，资源授权后请关闭浏览器，重新登陆后方可生效。</small></div>	
			<div class="btn-group pull-right">
				<btn:authorBtn menuid="${menuid}" text="添加">
					<button class="btn btn-small" onclick="addRole();"><i class="icon-plus"></i>添加</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="删除">
					<button class="btn btn-small" onclick="removeRole();"><i class="icon-remove"></i>删除</button>
				</btn:authorBtn>
			</div>
			<div class="clear"></div>
		</div>
		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			<thead>
				<tr>
					<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
					<th>角色名称</th>
					<th>备注</th>
					<th width="200">操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pageList.result}" var="role">
					<tr onclick="rowOnclick(this)" data="${role.id}">
						<td style="text-align:center;"><input type="checkbox" value="${role.id}" name="select-chk" /></td>
						<td>${role.name}</td>
						<td class="texthidden"><div style="text-overflow:ellipsis;-o-text-overflow:ellipsis;overflow:hidden;width:200px">${role.memo}</div></td>
						<td>
							<btn:authorBtn menuid="${menuid}" text="查看">
								<a href="javascript:viewById('${role.id}')"> <i class="icon-eye-open"></i>查看</a>
							</btn:authorBtn>
							<c:if test="${role.rank ne '0'}">
								<btn:authorBtn menuid="${menuid}" text="修改">
									<a href="javascript:updateRoleById('${role.id}')"> <i class="icon-edit"></i>修改</a>
								</btn:authorBtn>
								 <btn:authorBtn menuid="${menuid}" text="删除">
									<a href="javascript:delRoleById('${role.id}')"> <i class="icon-remove"></i>删除</a>
								</btn:authorBtn>
							</c:if>
							<btn:authorBtn menuid="${menuid}" text="资源授权">
								<a href="javascript:grantRole('${role.id}');"><i class="icon-flag"></i>资源授权</a>
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
		<div class="row-fluid">
			<tags:pagination page="${pageList}"></tags:pagination>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	function addRole() {
		window.location.href = "${root}/forward/system/role/add/?menuid=${menuid}";
	}

	function updateRoleById(id) {
		var page = '${current}';
		window.location.href = "${root}/system/role/update/" + id+ "/${menuid}/?page=" + page;
	}

	function viewById(id){
		var page = '${current}';
		window.location.href = "${root}/system/role/showview/" + id+ "/${menuid}/?page=" + page;
	}
	
	function removeRole() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要删除的记录。");
		} else {
			delRoleById(ids);
		}
	}

	/***
	 *资源授权
	 */
	function grantRes() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要授权的记录。");
			return;
		}
		if (ids.length >1) {
			showMessage("不能同时对多个角色进行授权，请选择一条记录。");
		} else {
			window.location.href = "${root}/system/role/grantres/${menuid}/"
					+ ids + "/?page=${current}";
		}

	}

	function grantRole(ids){
		window.location.href = "${root}/system/role/grantres/${menuid}/"+ ids + "/?page=${current}";
	}
	
	
	function delRoleById(ids) {
		if (confirm("删除后数据将无法恢复，确定要删除吗？")) {
			var url = "${root}/system/role/delete/" + ids + "/";
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
						showMessage("该角色信息正在被用户使用，删除失败!");
					}
				}
			});
		}
	}

	$(document).ready(function() {
		$("#alert-div").hide();
	});
	
	function showList(){
		window.location.href = "${root}/system/role/list/${menuid}/";
    }
//-->
</script>
