<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
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
	    $("#moduleName").val("");
	    $("#modifyOperator").val("");
        $("#modifyType").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/modify/record/search/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="search_item_td">模块名称</td>
			    <td><input title="支持 模块名称 模糊查询" style="width:60%;" id="moduleName" name="search_moduleName" value="${record.moduleName}" maxlength="20" type="text" placeholder="模板名称" /></td>
			    <td class="search_item_td">操作人员</td>
			    <td><input title="支持 变更人员 模糊查询" style="width:60%;" id="modifyOperator" name="search_modifyOperator" value="${record.modifyOperator}" maxlength="20" type="text" placeholder="变更人员" /></td>
			    <td class="search_item_td">变更类型</td>
			    <td><select id="modifyType" name="search_modifyType" style="width:165px;">
					<option value="">请选择</option>
					<option value="维修" ${'维修'==record.modifyType?'selected':''}>维修</option>
					<option value="移除" ${'移除'==record.modifyType?'selected':''}>移除</option>
					<option value="报废" ${'报废'==record.modifyType?'selected':''}>报废</option>
					<option value="变更" ${'变更'==record.modifyType?'selected':''}>变更</option>
				</select></td>
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
				<btn:authorBtn menuid="${menuid}" text="导出">
					<button class="btn btn-small" onclick="doExport();"><i class="icon-download"></i>导出</button>
				</btn:authorBtn>
			</div>
			<div class="clear"></div>
  	  	 </div>
  	  	 <div style="overflow: auto;">
		  <table class="table table-striped table-bordered table-condensed table-style" id="table">
		  	  <c:if test="${pageList.result[0]==null}"><td><div style="text-align: center;margin-right: 40px;"><font color="red">没有查到结果，请重新查询或者改变查询条件。</font></div></td></c:if>
	  		  <c:if test="${pageList.result[0]!=null}">
			  <thead>
				  <tr>
					 <th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1"/></th>
					 <th>模块名称</th>	
					 <th>变更类别</th>
					 <th>操作人员</th>
					 <th>变更日期</th>
					 <th>变更原因</th>
					 <th>版本号</th>
					 <th>登录名称</th>
					 <th>操作时间</th>
					 <th width="130">操作</th>
				 </tr>
			  </thead>
			  <tbody id="tbody">
				 <c:forEach items="${pageList.result}" var="record">
					 <tr onclick="rowOnclick(this)"  data="${record.id}">
						 <td style="text-align:center;"><input type="checkbox" value="${record.id}" name="select-chk"/></td>
						 <td>${record.moduleName}</td>
						 <td>${record.modifyType}</td>
						 <td>${record.modifyOperator}</td>
						 <td><fmt:formatDate value="${record.modifyDatetime}" pattern="yyyy-MM-dd" /></td>
						 <td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;white-space:nowrap;height: 18px;" title="${record.modifyReason}">${record.modifyReason}</div></td>
						 <td>${record.version}</td>
						 <td><tags:xiangxuncache keyName="username_cache" id="${record.operator}"></tags:xiangxuncache></td>
						 <td><fmt:formatDate value="${record.operatorTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						 <td>
						     <btn:authorBtn menuid="${menuid}" text="查看"><a  href="javascript:viewById('${record.id}')"><i class="icon-th-list"></i>查看</a>
							 </btn:authorBtn>
						 </td>
					 </tr>
				 </c:forEach>
				 <c:if test="${pageList.result!=null}">
					 <c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						 <tr>
							 <td colspan="12">&nbsp;</td>
						 </tr>
					 </c:forEach>
				 </c:if>
				</tbody>
				</c:if>
			</table>
			<tags:pagination page="${pageList}"></tags:pagination>
  	  	 </div>
	</div>
</div>
<script type="text/javascript">
	function showList(){
		window.location.href = "${root}/modify/record/search/${menuid}/?page=${current}&isgetsession=1";
    }
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/modify/record/view/"+id+"/${menuid}/?page="+page;
	}
	$(document).ready(function(){
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
	});
	
	function doExport(){
        window.location.href="${root}/modify/record/doExport/${menuid}/?${searchParams}";
    }
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>