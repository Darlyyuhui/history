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
	    $("#code").val("");
	    $("#serverip").val("");
	    $("#orgNames").val("");
	    $("#orgId").val("");
    }
    
    //miaoxu 导出xls
    function exportXls(){
        var name = $("#name").val();
        var code = $("#code").val();
        var orgNames = $("#orgNames").val();
        var serverip = $("#serverip").val();
        
        window.location.href="${root}/property/serverinfo/exportXls/${menuid}/?search_name="+name+"&search_code="+code+"&search_orgNames="+orgNames+"&search_serverip="+serverip;       
    }
    
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height:690px;overflow:hidden;margin-left:180px;">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/property/serverinfo/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">服务器名称</td>
			    <td><input title="支持 服务器名称 模糊查询" style="width:90%;max-width:144px;" id="name" name="search_name" maxlength="30" value="${server.name}" type="text" placeholder="服务器名称" /></td>
			    <td class="td40">服务器编号</td>
			    <td><input title="支持 服务器编号 模糊查询" style="width:90%;max-width:144px;" id="code" name="search_code" maxlength="18" value="${server.code}" type="text" placeholder="服务器编号" /></td>
			    <td class="td40">服务器IP</td>
			    <td><input title="支持 服务器IP 模糊查询" style="width:90%;max-width:144px;" id="serverip" name="search_serverip" maxlength="15" value="${server.serverip}" type="text" placeholder="服务器IP" /></td>
			    <td class="search_item_td">所属部门</td>
	            <td><input style="width:90%;" type="text" id="orgNames" name="search_orgNames" value="${server.orgNames}" readonly onclick="showMenu('orgNames');"/>
	              	<input type="hidden" id="orgId" name="search_orgId" value="${server.orgId}" /></td>
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
		    <div class="btn-group">
              <button class="btn btn-small" data-toggle="dropdown">更多操作<span class="caret"></span> </button>
              <ul class="dropdown-menu" tyle="width: 40px;right:20px;margin-right:20px;">
                  <li><a href="javascript:modifyById('1');"><i class="icon-cog"></i>维修</a></li>
                  <li><a href="javascript:modifyById('2');"><i class="icon-off"></i>移除</a></li>
                  <li><a href="javascript:modifyById('3');"><i class="icon-remove-circle"></i>报废</a></li>
              </ul>
            </div>
		  </div>
		  <div class="clear"></div>
	  </div>
	  <div class="row-fluid" style="overflow: auto;">
		  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
						<th>服务器名称</th>
						<th>服务器编号</th>
						<th>服务器IP</th>
						<th>服务器型号</th>
						<th>服务器类别</th>
						<th>CPU型号</th>
						<th>内存容量</th>
						<th>硬盘容量</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="server">
						<tr onclick="rowOnclick(this)" data="${server.id}">
							<td style="text-align:center;"><input type="checkbox" value="${server.id}" name="select-chk" /></td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${server.name}">${server.name}</div></td>
							<td>${server.code}</td>
							<td>${server.serverip}</td>
							<td>${server.model}</td>
							<td>${server.type}</td>
							<td>${server.cpuModel}</td>
							<td>${server.ramVolume}</td>
							<td>${server.diskVolume}</td>
							<td><fmt:formatDate value="${server.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td class="center">
								<btn:authorBtn menuid="${menuid}" text="查看">
									<a href="javascript:viewById('${server.id}')"> <i class="icon-th-list"></i>查看</a>
								</btn:authorBtn>
								<btn:authorBtn menuid="${menuid}" text="修改">
									<a href="javascript:updateById('${server.id}')"> <i class="icon-edit"></i>修改</a>
								</btn:authorBtn> 
								<c:if test="${server.hasModified == false}">
									<btn:authorBtn menuid="${menuid}" text="删除">
										<a href="javascript:delById('${server.id}')"> <i class="icon-remove"></i>删除</a>
									</btn:authorBtn>
								</c:if>
								<btn:authorBtn menuid="${menuid}" text="变更">
									<a  href="javascript:updateById('${server.id}','1')">
										<i class="icon-refresh"></i>变更
									</a>
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
<!-- 变更窗口开始 -->
<div class="modal hide fade" id="modifyDiv" style="width: 60%;text-align: center;">
    <button type="button" class="close" data-dismiss="modal">×</button>
    <h4 id="modifyTitle" class="xtcs_h4" style="margin: 0;"></h4>
  	<div class="modal-body">
	    <div class="conten_box">
			<form id="modifyInputForm" class="form-inline" action="${root}/property/serverinfo/doModify" method="post" style="margin:0;">
				<input type="hidden" name="menuid" value="${menuid}" />
				<input type="hidden" id="modifyId" name="modifyId"/>
				<input type="hidden" id="modifyType" name="modifyType"/>
				<div class="mar_5">
				  <table class="table table-border-rl table-border-bot bukong-table">
					<tr>
						<td id="modifyDatetime" class="device_td_bg3"></td>
						<td>
							<input name="modifyDatetimeStr" type="text"
									class="Wdate required" readonly="readonly" style="width:160px;"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:new Date()})"/>
							<font color="red">*</font>
						</td>	
					</tr>
					<tr>
						<td class="device_td_bg3">操作人员：</td>
						<td>
							<input style="width:160px;" type="text" name="modifyOperator" class="required" maxlength="15">
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
					 	<td id="modifyReason" class="device_td_bg3"></td>
						<td colspan="3">
							<textarea rows="3" maxlength="100" style="width:75%;" class="span8 required" name="modifyReason"></textarea>
							<font color="red">*</font>
						</td>
					</tr>
				  </table>
				</div>
				<div class="btn_line">
					<button class="btn btn-info mar_r10" type="submit">保存</button>
					<input id="cancel_btn" class="btn" type="button" value="取消" onclick="showList()" />
				</div>
			</form>
		</div>
  	</div>
</div>
<!-- 变更窗口结束 -->
<script type="text/javascript">

	var typename = "维修";
	function modifyById(type) {
		if(type == '2'){
			typename = "移除";
		}else if(type == '3'){
			typename = "报废";
		}
		var ids = getSelectedValue();
		if(ids.length==0){
			showMessage("请选择要"+typename+"的记录");
		}else{
			showModifyDialog(ids,type);
		}
	}

	function showModifyDialog(infoId,type) {
		$("#modifyTitle").html(typename+"操作窗口");
		$("#modifyDatetime").html(typename+"日期：");
		$("#modifyReason").html(typename+"说明：");
		
		$("#modifyInputForm").validate();
		$('#modifyDiv').modal('show');
		$("#modifyId").val(infoId);
		$("#modifyType").val(type);
	}

	function showList(){
		window.location.href = "${root}/property/serverinfo/list/${menuid}/?page=${current}&isgetsession=1";
	}

	function add() {
		var page='${current}';
		window.location.href = "${root}/forward/property/serverinfo/add/?menuid=${menuid}&page=" + page;
	}

	function updateById(id,ismodify) {
		var page = '${current}';
		window.location.href = "${root}/property/serverinfo/update/" + id
				+ "/${menuid}/?page=" + page+"&ismodify="+ismodify;
	}
	
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/property/serverinfo/view/"+id+"/${menuid}/?page="+page;
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
		if(confirm("删除后无法恢复，并同步删除资产信息、服务商分配信息、服务器历史记录、更改记录及工单信息等，确定删除吗？")){
			var url = "${root}/property/serverinfo/delete/" + ids + "/";
			$.ajax({
				type : 'delete',
				url : url,
				dataType : "json",
				success : function(msg) {
					if (msg.result == "ok") {
						$("#alert-div").removeClass("alert-error").addClass("alert-success");
						showMessage("删除成功");
						setTimeout("showList()", 1600);
					}else if(msg.result=="can't"){
		   				 showMessage("设备已进行变更操作，无法删除！");
		   				 setTimeout("showList()", 1600);
		   			}else {
						showMessage("删除失败");
					}
				}
			});
		}
	}

	$(document).ready(function() {
		var dept ='${departmentjsonArray}';
	    var departmentNodes = eval("(" + dept + ")");
		orgTree = $.fn.zTree.init($("#orgTreeSpace"),orgSetting,departmentNodes);
		orgTree.expandAll(true);
		
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
	});
	
	// 所属部门 组织机构树
	var orgSetting = {
		check: {
			  enable: true,
			  chkStyle: "radio",
			  radioType: "all"
		},
		view: {
			  dblClickExpand: false
		},
		data: {
			  simpleData: {
				 enable: true
			  }
		},
		callback: {
			  onClick: onRadioClick,
			  onCheck: onRadioCheck
		}
	};
		
	var orgTree;
		
   //组织机构树相关函数
	function onRadioClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onRadioCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		var h = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			h += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#orgNames");
		cityObj.attr("value", v);
		
		if (h.length > 0 ) h = h.substring(0, h.length-1);
		var hiddenValue = $("#search_orgId");
		hiddenValue.attr("value", h);
	}
	
	
	function showMenu(selectId) {
		var cityObj = $("#"+selectId);
		var cityOffset = $("#"+selectId).offset();
		if(selectId == 'orgNames'){
		   $("#orgTreeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		}
		$("body").bind("mousedown",onBodyDown);
	}
	function hideMenu() {
		$("#orgTreeContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "deviceTypeNames"|| event.target.id == "orgNames" || event.target.id == "menuContent" ||event.target.id == "orgTreeContent" || $(event.target).parents("#menuContent").length>0|| $(event.target).parents("#orgTreeContent").length>0 )) {
			hideMenu();
		}
	}
</script>
<div id="orgTreeContent" class="menuContent" style="display:none; position: absolute; background: #f0f6e4; border: 1px solid #617775; width:175px;overflow-x:scroll; height:260px; overflow-y:scroll;" >
	<ul id="orgTreeSpace" class="ztree" style="margin-top: 0px; width:260px;"></ul>
</div>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>