<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
  <p id="alert-content" align="center"></p>
</div>
<div class="alert alert-block pull-top alert-success" id="alertsuc-div" style="display:none;">
  <p id="alertsuc-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p id="alert-suc" align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
        $("#search_name").val("");
        $("#search_pid").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box" id="user-content">
	<div class="search-area" style="padding: 6px 0 4px;">
		<form class="form-horizontal" id="intelForm" name="intelForm"
			action="${root}/repository/search/list/${menuid}/" method="post" 
			style="margin:0; padding:0 6px;">
			<table class="table-condensed">
				<tr>
					<td class="search_item_td">文件名称</td>
					<td>
						<input type="text" style="width:120px;" title="支持 文件名称 模糊查询" name="search_name" id="search_name" value="${knowledge.name }" maxlength="30" placeholder="文件名称">
					</td>
					<td class="search_item_td">知识类别</td>
					<td>
						<select style="width:125px;" id="search_pid" name="search_pid">
							<option value="">请选择</option>
							<c:forEach items="${catalogList}" var="catalog">
								<option value="${catalog.id}" ${catalog.id==knowledge.pid?'selected':''}>${catalog.name}</option>
							</c:forEach>
						</select>
					</td>
					<td style="min-width:140px;">
						<input type="submit" class="btn btn-info mar_l10" value="查询"/>
						<input onclick="reValues()" type="button" class="btn mar_l10" value="重置"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="table_box">
		<div id="titlebar" class="btn-group-wrap mar_b5">
			<div class="btn-group pull-right">
				<btn:authorBtn menuid="${menuid}" text="导出">
					<button class="btn btn-small" onclick="doExport()"><i class="icon-download"></i>导出</button>
				</btn:authorBtn>
			</div>
			<div class="clear"></div>
		</div>
		<div style="overflow: auto;">
			<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
						<th>文件名称</th>
						<th>知识类别</th>
						<th>创建人</th>
						<th>创建时间</th>
						<th width="130">操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="knowledge">
						<tr data="${knowledge.id}">
							<td>
								<input type="checkbox" value="${knowledge.id}" name="select-chk" />
							</td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${knowledge.name}">${knowledge.name}</div></td>
							<td>
								${knowledge.knowledgetype}
							</td>
							<td>
								<tags:xiangxuncache keyName="username_cache" id="${knowledge.operator}"></tags:xiangxuncache>
							</td>
							<td>
								<fmt:formatDate value="${knowledge.createtime}" type="both" />
							</td>
							<td>
								<btn:authorBtn menuid="${menuid}" text="查看">
									<a href="javascript:viewById('${knowledge.id}')"> <i class="icon-th-list"></i>查看</a>
								</btn:authorBtn>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${pageList.result!=null}">
						<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
							<tr height="28">
								<td colspan="12">
									&nbsp;
								</td>
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
/**
 * 显示详情
 */
function viewById(id){
    var page = '${current}';
	var url = "${root}/repository/search/view/"+id+"/${menuid}/?page=" + page;
	window.location.href = url;
}

//导出
function doExport(){
	window.location.href="${root}/repository/search/doExport/${menuid}/?${searchParams}";
}
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
