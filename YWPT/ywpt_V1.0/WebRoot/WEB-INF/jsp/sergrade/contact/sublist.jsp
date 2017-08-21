<%@ page language="java" pageEncoding="UTF-8"%>
<script src="<c:url value='/js/common.js'/>" type="text/javascript"></script>
<script type="text/javascript">
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
				<th>所属公司</th>
				<th>联系方式</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${pageList.result}" var="contactInfo">
				<tr onclick="rowOnclick(this)" data="${contactInfo.id}">
					<td style="text-align:center;"><input type="checkbox" id="checkbox" value="${contactInfo.id}" name="select-chk" /></td>
					<td>${contactInfo.userName}</td>
					<td>${contactInfo.account}</td>
					<td>${contactInfo.factoryName}</td>
					<td>${contactInfo.mobile}</td>
					<td class="texthidden">${contactInfo.memo}</td>
					<td width="120">
						<btn:authorBtn menuid="${menuid}" text="查看">
							<a href="javascript:viewById('${contactInfo.id}')"> <i class="icon-eye-open"></i>查看</a>
						</btn:authorBtn>
					    <btn:authorBtn menuid="${menuid}" text="修改">
							<a href="javascript:updateContactById('${contactInfo.id}')"><i class="icon-edit"></i>修改</a>
						</btn:authorBtn> 
						<btn:authorBtn menuid="${menuid}" text="删除">
							<a href="javascript:delContactById('${contactInfo.id}')"><i class="icon-remove"></i>删除</a>
						</btn:authorBtn>
						
						<btn:authorBtn menuid="${menuid}" text="责任资产配置">
							<a href="javascript:allotById('${contactInfo.id}')"><i class="icon-cog"></i>责任资产配置</a>
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
<script type="text/javascript">
	function showList(){
		window.location.href = "${root}/sergrade/contact/sublist/${menuid}/";
    }

	//添加运维人员
	function addContact(chooseId) {
		if (chooseId.length == 0) {
			parent.showMessage('请选择所属公司');
			return;
		}
		parent.$("#titlebar").css("display", "none");
		goUrl("${root}/forward/sergrade/contact/add/?menuid=${menuid}&factoryid=" + chooseId);
		
	}

	//修改运维人员
	function updateContactById(id) {
		var page = '${current}';
		parent.$("#titlebar").css("display", "none");
		goUrl("${root}/sergrade/contact/update/" + id + "/${menuid}/?page=" + page);
	}
	
	//责任资产配置
	function allotById(id) {
		var page = '${current}';
		parent.$("#titlebar").css("display", "none");
		goUrl("${root}/sergrade/contact/allot/" + id + "/${menuid}/?page=" + page);
	}
	

	function viewById(id){
		var page = '${current}';
		parent.$("#titlebar").css("display", "none");
		goUrl("${root}/sergrade/contact/showview/" + id + "/${menuid}/?page=" + page);
	}
	
	//删除运维人员
	function removeContact() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			parent.showMessage("请选择要删除的记录。");
		} else {
			delContactById(ids);
		}
	}

	function delContactById(ids) {
		if (confirm("删除后数据将无法恢复，确定要删除吗？")) {
			var url = "${root}/sergrade/contact/delete/" + ids + "/";
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
	
	function goUrl(url){
		parent.parent.$("#content-frame").attr("src",url);
	}
</script>