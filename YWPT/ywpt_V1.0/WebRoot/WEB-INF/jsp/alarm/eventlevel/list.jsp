<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
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
		$("#typename").val("");
	    $("#typelike").val("");
	    $("#grade").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/alarm/eventlevel/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">级别名称</td>
			    <td><input title="支持 级别名称 模糊查询" style="width:90%;max-width:165px;" id="name" name="search_name" value="${eventlevel.name}" maxlength="30" type="text" placeholder="级别名称" /></td>
			    <td class="td40">告警方式</td>
				<td>
				  <input class="input-text" style="width:165px;" type="text" id="typename" name="search_typeName" value="${eventlevel.typeName}" readonly onclick="showMenu();"/>
	            		<input type="hidden" id="typelike" name="search_relationType" value="${eventlevel.relationType}"/>
		        </td>
		        <td class="td40">级别等级</td>
			    <td>
			    	<select style="width:60%;min-width: 150px;" id="grade" name="search_grade" placeholder="事件级别">
						<option value="">请选择</option>
						<c:forEach items="${eventLeverGradeType}" var="level">
						    <option value="${level.code}" ${level.code==eventlevel.grade?'selected':''}>${level.name}</option>
						</c:forEach>
		         	 </select>
			    </td>
			   
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info pull-left mar_l10" value="查询" style="height:28px;"/>
			      <input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
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
			<button class="btn btn-small" onclick="removea();">
				<i class="icon-remove"></i>删除
			</button>
		    </btn:authorBtn>
		  </div>
		  <div class="clear"></div>
	  </div>
	  <div style="overflow: auto;">
		  <table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
						<th>级别名称</th>
						<th>告警方式</th>
						<th>健康度权重系数</th>
						<th>级别等级</th>
						<th>告警颜色</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="eventtype">
						<tr onclick="rowOnclick(this)" data="${eventtype.id}">
							<td style="text-align:center;"><input type="checkbox" value="${eventtype.id}" name="select-chk" /></td>
							<td>${eventtype.name}</td>
							<td>${eventtype.relationType}</td>
							<td>${eventtype.healthcoefficient}</td>
							<td>
								<c:forEach items="${eventLeverGradeType}" var="eventLever" >
									<c:if test="${eventLever.code == eventtype.grade}">
										${eventLever.name}
									</c:if>
								</c:forEach>
							</td>
							<td><font style="background-color: ${eventtype.color};color: white;">${eventtype.color}</font></td>
							<td><div style="width:260px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title="${eventtype.note}">${eventtype.note}</div></td>
							<td class="center">
							<btn:authorBtn menuid="${menuid}" text="查看">
								<a href="javascript:viewById('${eventtype.id}')"> <i class="icon-eye-open"></i>查看</a>
							</btn:authorBtn>
							<btn:authorBtn menuid="${menuid}" text="修改">
								<a href="javascript:updateFtpById('${eventtype.id}')"> <i class="icon-edit"></i>修改</a>
							</btn:authorBtn> 
							<btn:authorBtn menuid="${menuid}" text="删除">
								<a href="javascript:delFtpById('${eventtype.id}')"> <i class="icon-remove"></i>删除</a>
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
</div>
<div id="typeTreeContent" class="menuContent" style="display:none; position: absolute; background: #f0f6e4; border: 1px solid #617775; width:173px;overflow:hidden; height:180px;" >
	<ul id="typeTreeSpace" class="ztree" style="margin-top: 0px; width:180px;"></ul>
</div>
<script type="text/javascript">

	var typeTree;
	// 所属部门 组织机构树
	var typeSetting = {
		check: {
			  enable: true,
			  chkStyle: "checkbox",
			  radioType: "all",
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
	//组织机构树相关函数
	function onRadioClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("typeTreeSpace");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}	
	function onRadioCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("typeTreeSpace"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		var h = "";
		var length = ${fn:length(alarmtypeList)};
		for (var i=0, l=nodes.length; i<l; i++) {
			if(nodes[i].id != '00'){
				v += nodes[i].name + ",";
				h += nodes[i].id + ",";
			}
		}
		if (h.length > 0 ) h = h.substring(0, h.length-1);
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var ah = h.split(',');
		if(ah.length == length){
			$("#typename").attr("value", "全部");
			$("#typelike").attr("value", "");
		}else{
			$("#typename").attr("value", v);
			$("#typelike").attr("value", h);
		}
	}
	
	function showMenu() {
		var cityObj = $("#typename");
		var cityOffset = cityObj.offset();
		$("#typeTreeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown",onBodyDown);
	}
	function hideMenu() {
		$("#typeTreeContent").hide();
		$("body").unbind("mousedown", onBodyDown);
	}
	
	function onBodyDown(event) {
		debugger;
		if (!(event.target.id == event.target.id == "menuContent" ||event.target.id == "typeTreeContent" || $(event.target).parents("#typeTreeContent").length>0 )) {
			hideMenu();
		}
	}
	
	function showList(){
		window.location.href = "${root}/alarm/eventlevel/list/${menuid}/?page=${current}&isgetsession=1";
	}

	function add() {
		var page='${current}';
		window.location.href = "${root}/forward/alarm/eventlevel/add/?menuid=${menuid}&page=" + page;
	}

	function updateFtpById(id) {
		var page = '${current}';
		window.location.href = "${root}/alarm/eventlevel/update/" + id
				+ "/${menuid}/?page=" + page;
	}
	
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/alarm/eventlevel/view/"+id+"/${menuid}/?page="+page;
	}

	function removea() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要删除的记录。");
		} else {
			delFtpById(ids);
		}
	}

	function delFtpById(ids) {
		if (confirm("删除后数据将无法恢复，确定要删除吗？")) {
			var url = "${root}/alarm/eventlevel/delete/" + ids + "/";
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
		
		var type ='${typeJsonArray}';
	    var typeNodes = eval("(" + type + ")");
		typeTree = $.fn.zTree.init($("#typeTreeSpace"),typeSetting,typeNodes);
		typeTree.expandAll(true);
	});
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>