<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
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
	    $("#dialect").val("");
	    $("#ip").val("");
    }
    
        //miaoxu 导出xls
    function exportXls(){
        var name = $("#name").val();
        var dialect = $("#dialect").val();
        var ip = $("#ip").val();
        
        window.location.href="${root}/property/databaseinfo/exportXls/${menuid}/?search_name="+name+"&search_dialect="+dialect+"&search_ip="+ip;       
    }
    
    
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height:690px;overflow:hidden;margin-left:180px;">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/property/databaseinfo/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">数据库名称</td>
			    <td><input title="支持 数据库名称 模糊查询" style="width:90%;max-width:144px;" id="name" name="search_name" maxlength="30" value="${database.name}" type="text" placeholder="数据库名称" /></td>
			    <td class="td40">数据库类型</td>
			    <td><input title="支持 数据库类型 模糊查询" style="width:90%;max-width:144px;" id="dialect" name="search_dialect" maxlength="15" value="${database.dialect}" type="text" placeholder="数据库类型" /></td>
			    <td class="td40">IP地址</td>
			    <td><input title="支持 IP地址 模糊查询" style="width:90%;max-width:144px;" id="ip" name="search_ip" maxlength="15" value="${database.ip}" type="text" placeholder="IP地址" /></td>
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info pull-left mar_l10" value="查询" style="height:28px;"/>
			      <input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="mar_5">
    	<div class="btn-group-wrap mar_b5">
		  <div class="btn-group pull-right">
		  
		  <btn:authorBtn menuid="${menuid}" text="导出">
            <button id="exportXls" class="btn btn-small" onclick="exportXls();"><i class="icon-download"></i>导出</button>
          </btn:authorBtn>
		  
			<btn:authorBtn menuid="${menuid}" text="添加">
			<button class="btn btn-small" onclick="add();">
				<i class="icon-plus"></i>添加
			</button>
		    </btn:authorBtn>
		    <btn:authorBtn menuid="${menuid}" text="删除">
			<button class="btn btn-small" onclick="removea();">
				<i class="icon-remove"></i>删除
			</button>
		    </btn:authorBtn>
		  </div>
		  <div class="clear"></div>
	  </div>
	  <div class="row-fluid" style="overflow: auto;">
		  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
						<th>数据库名称</th>
						<th>数据库类型</th>
						<th>IP地址</th>
						<th>端口号</th>
						<th>用户名</th>
						<th>密码</th>
						<th>数据库实例</th>
						<th>运维服务商</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="database">
						<tr onclick="rowOnclick(this)" data="${database.id}">
							<td style="text-align:center;"><input type="checkbox" value="${database.id}" name="select-chk" /></td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${database.name}">${database.name}</div></td>
							<td>${database.dialect}</td>
							<td>${database.ip}</td>
							<td>${database.port}</td>
							<td>${database.username}</td>
							<td>${database.password}</td>
							<td>${database.sid}</td>
							<td>${database.factoryName}</td>
							<td class="center">
								<btn:authorBtn menuid="${menuid}" text="查看">
									<a href="javascript:viewById('${database.id}')"> <i class="icon-th-list"></i>查看</a>
								</btn:authorBtn>
								<btn:authorBtn menuid="${menuid}" text="修改">
									<a href="javascript:updateById('${database.id}')"> <i class="icon-edit"></i>修改</a>
								</btn:authorBtn> 
								<btn:authorBtn menuid="${menuid}" text="删除">
									<a href="javascript:delById('${database.id}')"> <i class="icon-remove"></i>删除</a>
								</btn:authorBtn>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${pageList.result!=null}">
						<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
							<tr>
								<td colspan="15">&nbsp;</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<tags:pagination page="${pageList}"></tags:pagination>
		</div>
	</div>
</div>
<script type="text/javascript">

	function showList(){
		window.location.href = "${root}/property/databaseinfo/list/${menuid}/?page=${current}&isgetsession=1";
	}

	function add() {
		var page='${current}';
		window.location.href = "${root}/forward/property/databaseinfo/add/?menuid=${menuid}&page=" + page;
	}

	function updateById(id,ismodify) {
		var page = '${current}';
		window.location.href = "${root}/property/databaseinfo/update/" + id
				+ "/${menuid}/?page=" + page;
	}
	
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/property/databaseinfo/view/"+id+"/${menuid}/?page="+page;
	}

	function removea() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要删除的记录。");
		} else {
			delById(ids);
		}
	}

	function delById(ids) {
		if(confirm("删除后无法恢复，并同步删除资产信息、服务商分配信息及工单信息等，确定删除吗？")){
			var url = "${root}/property/databaseinfo/delete/" + ids + "/";
			$.ajax({
				type : 'delete',
				url : url,
				dataType : "json",
				success : function(msg) {
					if (msg.result == "ok") {
						$("#alert-div").removeClass("alert-error").addClass("alert-success");
						showMessage("删除成功");
						setTimeout("showList()", 1600);
					}else {
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
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>