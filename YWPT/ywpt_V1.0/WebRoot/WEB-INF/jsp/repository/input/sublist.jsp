<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
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
		$(document).ready(function(){
			if($("#submsg").val() == '目录修改成功' || $("#submsg").val() == '目录新建成功'){
				parent.setMesg($("#submsg").val());
				$("#message").css("display", "none");
			}
			$("#submsg").val("");
		});
		setTimeout('$("#message").hide("slow")', 1200);
		this.parent.$("#uploading_div").modal('hide');
	</script>
</c:if>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
        $("#search_name").val("");
        $("#search_pid").val("");
    }
</script>
<input id="submsg" type="hidden" name="submsg" value="${message}"/>
<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height:640px;overflow:hidden;margin-left:180px;">
	<div class="search-area" style="padding: 6px 0 4px;margin-left:5px;">
		<form class="form-horizontal" id="intelForm" name="intelForm"
			action="${root}/repository/input/sublist/${menuid}/" method="post" 
			style="margin:0; padding:0 6px;">
			<table class="table-condensed">
				<tr>
					<td class="search_item_td">文件名称</td>
					<td>
						<input type="text" title="支持 文件名称 模糊查询" style="width:120px;" name="search_name" id="search_name" value="${knowledge.name }" maxlength="30" placeholder="文件名称">
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
				<btn:authorBtn menuid="${menuid}" text="添加">
					<button class="btn btn-small" onclick="add();"><i class="icon-plus"></i>添加</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="删除">
					<button class="btn btn-small" onclick="removea();"><i class="icon-remove"></i>删除</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="提交审核">
					<button class="btn btn-small" onclick="apply();"><i class="icon-check"></i>提交审核</button>
				</btn:authorBtn>
			</div>
			<div class="clear"></div>
		</div>
		<div style="overflow: auto;">
			<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1"/></th>	
						<th>文件名称</th>
						<th>知识类别</th>
						<th>创建人</th>
						<th>创建时间</th>
						<th>审核状态</th>
						<th>驳回原因</th>
						<th width="130">操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="knowledge">
						<tr data="${knowledge.id}">
							<td style="text-align:center;">
								<c:if test="${knowledge.applyflag==0||knowledge.verifyresult==2}">
									<input type="checkbox" value="${knowledge.id}" name="select-chk" />
								</c:if>
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
								<c:if test="${knowledge.applyflag==1}"><font color="green"><b>已提交</b></font></c:if>
								<c:if test="${knowledge.applyflag==0}"><font color="red"><b>未提交</b></font></c:if>
								
								<c:if test="${knowledge.applyflag==1}">
								->
								<c:if test="${knowledge.verifyflag==1}"><font color="green"><b>已审核</b></font></c:if>
								<c:if test="${knowledge.verifyflag==0}"><font color="red"><b>未审核</b></font></c:if>
								</c:if>
								
								<c:if test="${knowledge.verifyflag==1}">
								->
								<c:if test="${knowledge.verifyresult==1}"><font color="green"><b>审核通过</b></font></c:if>
								<c:if test="${knowledge.verifyresult==2}"><font color="red"><b>审核驳回</b></font></c:if>
								</c:if>
							</td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${knowledge.rebutreason}">${knowledge.rebutreason}</div></td>
							<td>
								<btn:authorBtn menuid="${menuid}" text="查看">
									<a href="javascript:viewById('${knowledge.id}')"> <i class="icon-th-list"></i>查看</a>
								</btn:authorBtn>
								<c:if test="${knowledge.applyflag==0||knowledge.verifyresult==2}">
									<btn:authorBtn menuid="${menuid}" text="修改">
										<a href="javascript:updateById('${knowledge.id}')"><i class="icon-edit"></i>修改 </a>
									</btn:authorBtn>
									<btn:authorBtn menuid="${menuid}" text="删除">
										<a href="javascript:delById('${knowledge.id}')"> <i class="icon-remove"></i>删除 </a>
									</btn:authorBtn>
								</c:if>
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
	function delFolderById(nodes){
		if(nodes[0].id == '00'){
				parent.showMessage("文件根目录不允许删除");
				return;
			}
		if(confirm("删除后数据将无法恢复，确定要删除吗？")){
				var url="${root}/repository/catalog/delete/"+nodes[0].id+"/";
			$.ajax( {
				type : 'delete',
				url : url,
				dataType : "json",
				success:function(msg){
					if(msg.result=="ok"){
		   				 parent.showSucMessage("删除成功");
		   				 setTimeout("parent.showList()", 1800);
		   			}else if(msg.result=="error1"){
		   				parent.showMessage("要删除的节点存在子节点，不能直接删除！");
		   			}else if(msg.result=="error2"){
		   				parent.showMessage("要删除的节点存在文件，不能直接删除！");
		   			}else{
		   				parent.showMessage("删除失败");
		   			}
				}
			});
	   	}
	}

	/**
	 * 显示详情
	 */
	function viewById(id){
	    var page = '${current}';
		var url = "${root}/repository/input/view/"+id+"/${menuid}/?page=" + page;
		window.location.href = url;
	}
	
	//删除
	function removea() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要删除的记录。");
		} else {
			delById(ids);
		}
	}
	
	function updateById(id){
		window.location.href = "${root}/repository/input/update/" + id + "/${menuid}/";
	}
	
	//显示列表信息
	function showList(){
		window.location.href = "${root}/repository/input/sublist/${menuid}/";
	}
	
	//删除数据
	function delById(ids){
		if(confirm("删除后数据将无法恢复，确定要删除吗？")){
   			var url="${root}/repository/input/delete/"+ids+"/";
			$.ajax( {
				type : 'delete',
				url : url,
				dataType : "json",
				success:function(msg){
					if(msg.result=="ok"){
		   				 showSucMessage("删除成功");
		   				 setTimeout("showList()",1800);
		   			}else{
		   				 showMessage("删除失败");
		   			}
				}
			});
		   }
	}
	
	function add(){
		var page = '${current}';
		var chooseId = parent.add();
   		if(chooseId == '00'){
   			showMessage("文件根目录不允许添加");
   			return;
   		}
   		if(chooseId == undefined || chooseId == ''){
   			showMessage("请选择文件目录"); 
   			return;
   		}
		window.location.href = "${root}/forward/repository/input/add/?menuid=${menuid}&page="+page+"&pid="+chooseId;
	}
	
	function apply(){
		var ids = getSelectedValue();
		if(ids.length==0){
		   showMessage("请选择要提交审核的记录");
		}else{
			applyById(ids);
		}
	}
	
	function applyById(id){
		var url = "${root}/repository/verify/doApply/"+id+"/input/?menuid=${menuid}";
		window.location.href = url;
	}
	
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
