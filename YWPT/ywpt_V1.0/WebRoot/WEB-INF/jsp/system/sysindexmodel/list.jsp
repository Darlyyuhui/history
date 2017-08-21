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
	    $("#ftpip").val("");
	    $("#ftpport").val("");
	    $("#ftpuser").val("");
		window.location.href = "${root}/system/sysindexmodel/list/${menuid}/";
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/system/sysindexmodel/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">名称</td>
			    <td><input title="支持 名称 模糊查询" style="max-width:144px;" id="name" name="search_name" value="${sysIndexModel.name}" type="text" placeholder="名称" /></td>
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info pull-left mar_l10" value="查询"/>
			      <input onclick="reValues()" type="button" class="btn mar_l10" value="重置"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="table_box">
    	<div class="btn-group-wrap mar_b5">
		  <div class="btn-group pull-right">
			<btn:authorBtn menuid="${menuid}" text="添加">
			<button class="btn btn-small" onclick="add();">
				<i class="icon-plus"></i>添加
			</button>
		    </btn:authorBtn>
		    <btn:authorBtn menuid="${menuid}" text="删除">
			<button class="btn btn-small" onclick="remove();">
				<i class="icon-remove"></i>删除
			</button>
		    </btn:authorBtn>
		  </div>
		  <div class="clear"></div>
	  </div>
	  <table class="table table-striped table-bordered table-condensed table-style" id="table">
			<thead>
				<tr>
					<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
					<th>组件编号</th>
					<th>组件名称</th>
					<th>组件行高</th>
					<th>组件布局</th>
					<th>是否启用</th>
					<th width="200">操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pageList.result}" var="sysIndexModel">
					<tr onclick="rowOnclick(this)" data="${sysIndexModel.id}">
						<td style="text-align:center;"><input type="checkbox" value="${sysIndexModel.id}" name="select-chk" /></td>
						<td>${sysIndexModel.code}</td>
						<td>${sysIndexModel.name}</td>
						<td>${sysIndexModel.rowcount}</td>
						<td>
						<c:if test="${'left'==sysIndexModel.layout}">左侧列显示</c:if>
						<c:if test="${'center'==sysIndexModel.layout}">中间列显示</c:if>
						<c:if test="${'right'==sysIndexModel.layout}">右侧列显示</c:if>
						</td>
						<td>
						<c:if test="${sysIndexModel.isshow=='0'}">不启用</c:if>
				        <c:if test="${sysIndexModel.isshow=='1'}">启用</c:if>
						</td>
						<td class="center">
						<btn:authorBtn menuid="${menuid}" text="查看">
							<a href="javascript:viewById('${sysIndexModel.id}')"> <i class="icon-eye-open"></i>查看</a>
						</btn:authorBtn>
						<btn:authorBtn menuid="${menuid}" text="修改">
							<a href="javascript:updateById('${sysIndexModel.id}')"> <i class="icon-edit"></i>修改</a>
						</btn:authorBtn> 
						<btn:authorBtn menuid="${menuid}" text="删除">
							<a href="javascript:delById('${sysIndexModel.id}')"> <i class="icon-remove"></i>删除</a>
						</btn:authorBtn></td>
					</tr>
				</c:forEach>
				<c:if test="${pageList.result!=null}">
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="10">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<tags:pagination page="${pageList}"></tags:pagination>
	</div>
</div>
<script type="text/javascript">
	function showList(){
		window.location.href = "${root}/system/sysindexmodel/list/${menuid}/?page=${current}&isgetsession=1";
	}

	function add() {
		var page='${current}';
		window.location.href = "${root}/forward/system/sysindexmodel/add/?menuid=${menuid}&page=" + page;
	}

	function updateById(id) {
		var page = '${current}';
		window.location.href = "${root}/system/sysindexmodel/update/" + id
				+ "/${menuid}/?page=" + page;
	}
	
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/system/sysindexmodel/view/"+id+"/${menuid}/?page="+page;
	}

	function remove() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要删除的记录。");
		} else {
			delById(ids);
		}
	}

	function delById(ids) {
		if (confirm("删除后数据将无法恢复，确定要删除吗？")) {
			var url = "${root}/system/sysindexmodel/delete/" + ids + "/";
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

	$(document).ready(function() {
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
	});
</script>
