<%@ page language="java" pageEncoding="UTF-8"%>
<script src="<c:url value='/js/common.js'/>" type="text/javascript"></script>
<script type="text/javascript">
<!--
	function rowOnclick(tr) {
	}

	function selectAllEle(a) {
		var checked = $(a).attr("checked");
		$("#table :checkbox").each(function() {
			if ((checked == "checked") || checked) {
				$(this).attr("checked", checked);
			} else {
				$(this).removeAttr("checked");
			}
		});
	}

	$(document).ready(function() {
		parent.$("#titlebar").css("display", "block");
	});
//-->
</script>
<c:if test="${not empty message}">
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("#submsg").val() != "") {
				parent.setMesg($("#submsg").val());
			}
			$("#submsg").val("");
		});
	</script>
</c:if>
<input id="submsg" type="hidden" name="submsg" value="${message}" />
<div style="margin-right:0; margin-left:190px; min-height:550px; overflow-x:scroll;">
	<table class="table table-striped table-bordered table-condensed table-style" id="table">
		<thead>
			<tr>
				<th width="16"><input type="checkbox" id="selectAll" onclick="selectAllEle(this)" value="-1" /></th>
				<th>姓名</th>
				<th>账号</th>
				<th>所属部门</th>
				<th>拥有角色</th>
				<th>手机号</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${pageList.result}" var="user">
				<tr onclick="rowOnclick(this)" data="${user.id}">
					<td style="text-align:center;"><input type="checkbox" id="checkbox" value="${user.id}" name="select-chk" /></td>
					<td>${user.name}</td>
					<td>${user.account}</td>
					<td><tags:xiangxuncache keyName="Department" id="${user.deptid}"></tags:xiangxuncache></td>
					<td>${user.roles}</td>
					<td>${user.mobile}</td>
					<td class="texthidden">${user.memo}</td>
					<td>
						<btn:authorBtn menuid="${menuid}" text="查看">
							<a href="javascript:viewById('${user.id}')"> <i class="icon-eye-open"></i>查看</a>
						</btn:authorBtn>
					    <btn:authorBtn menuid="${menuid}" text="修改">
							<a href="javascript:updateUserById('${user.id}')"><i class="icon-edit"></i>修改</a>
						</btn:authorBtn> 
						<btn:authorBtn menuid="${menuid}" text="删除">
							<a href="javascript:delUserById('${user.id}')"><i class="icon-remove"></i>删除</a>
						</btn:authorBtn> 
						<%-- <btn:authorBtn menuid="${menuid}" text="数据授权">
							<c:if test="${atms['isStartDepartControl']=='1'}">
								<a href="javascript:grantDeptUserById('${user.id}')"> <i class="icon-filter"></i>数据授权
								</a>
							</c:if>
						</btn:authorBtn> --%>
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
<script type="text/javascript">
	function showList(){
		window.location.href = "${root}/system/user/sublist/${menuid}/";
    }

	//添加用户
	function addUser(chooseId) {
		if (chooseId.length == 0) {
			parent.showMessage('请选择所属部门');
			return;
		}
		parent.$("#titlebar").css("display", "none");
		goUrl("${root}/forward/system/user/add/?menuid=${menuid}&deptid=" + chooseId);
		
	}

	//修改用户
	function updateUserById(id) {
		var page = '${current}';
		parent.$("#titlebar").css("display", "none");
		goUrl("${root}/system/user/update/" + id + "/${menuid}/?page=" + page);
	}

	function viewById(id){
		var page = '${current}';
		parent.$("#titlebar").css("display", "none");
		goUrl("${root}/system/user/showview/" + id + "/${menuid}/?page=" + page);
	}
	
	//删除用户
	function removeUser() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			parent.showMessage("请选择要删除的记录。");
		} else {
			delUserById(ids);
		}
	}

	function delUserById(ids) {
		if (confirm("删除后数据将无法恢复，确定要删除吗？")) {
			var url = "${root}/system/user/delete/" + ids + "/";
			$.ajax({
				type : 'delete',
				url : url,
				dataType : "json",
				success : function(msg) {
					if (msg.result == "ok") {
						parent.showSucMessage("删除成功");
						setTimeout("showList()", 1600);
					} else {
						parent.showMessage("删除失败");
					}
				}
			});
		}
	}

	//角色授权
	function grantRoles() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			parent.showMessage("请选择要授权的记录。");
		} else {
			parent.$("#titlebar").css("display", "none");
			goUrl("${root}/system/user/grantrole/${menuid}/" + ids + "/?page=${current}");
		}
	}

	//数据授权
	function grantData() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			parent.showMessage("请选择要授权的记录。");
		} else {
			grantDeptUserById(ids);
		}
	}

	function grantDeptUserById(id) {
		parent.$("#titlebar").css("display", "none");
		goUrl("${root}/system/user/grantAuthority/${menuid}/" + id + "/?page=${current}");
	}

	//重置密码
	function resetPassword() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			parent.showMessage("请选择要重置密码的用户。");
			return;
		}

		if (ids.length > 1) {
			parent.showMessage("密码重置时，只能选择一个用户！");
			return;
		}

		if (confirm("密码重置后，密码将自动修改为该用户的账号。确定要重置吗？")) {
			$.getJSON("${root}/system/user/resetPassword/" + ids, function(msg) {
				if (msg.result == "ok") {
					parent.showMessage("重置成功");
				} else {
					parent.showMessage("重置失败");
				}
			});
		}
	}
	
	function goUrl(url){
		parent.parent.$("#content-frame").attr("src",url);
	}
</script>