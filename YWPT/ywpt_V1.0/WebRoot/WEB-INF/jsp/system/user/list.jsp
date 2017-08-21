<%@ page language="java" pageEncoding="UTF-8"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>

<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
	<p id="alert-content" align="center"></p>
</div>
<div class="alert alert-block pull-top alert-success" id="alertsuc-div" style="display:none;">
	<p id="alertsuc-content" align="center"></p>
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
<!-- 控制iframe中的页面返回的结果显示开始 -->
<div id="subinfo" class="alert alert-success" style="display: none;">
	<button data-dismiss="alert" class="close">×</button>
	<p id="mesg" align="center"></p>
</div>
<script type="text/javascript">
	function setMesg(s){
		$("#subinfo").css("display","");
		document.getElementById("mesg").innerHTML = s;
		setTimeout('$("#subinfo").hide("slow")', 1200);
	}
</script>
<!-- 控制iframe中的页面返回的结果显示结束 -->
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
        $("#name").val("");
        $("#account").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/system/user/sublist/${menuid}/" method="post" target="myIframe" style="margin:0;"> 
			<table>
			  <tr>
			    <td class="td54">姓 名</td>
			    <td><input class="input" style="width:130px;height:18px;" id="name" 
			    name="search_name" value="${user.name}" type="text" placeholder="姓名" /></td>
			    <td class="td54">账 号</td>
			    <td><input class="input" style="width:130px;height:18px;" id="account" 
			    name="search_account" value="${user.account}" type="text" placeholder="账号" /></td>
			    <td><input type="submit" class="btn btn-info mar_l10 mar_r10" value="查询" style="height:28px;*+margin-left:10px;"/>
			    <input onclick="reValues()" type="button" class="btn" value="重置" style="height:28px;"/></td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="table_box">
    	<div id="titlebar" class="btn-group-wrap mar_b5" style="display: block;">
			<div class="btn-group pull-right">
				<btn:authorBtn menuid="${menuid}" text="添加">
					<button id="addUser" class="btn btn-small"><i class="icon-plus"></i>添加</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="删除">
					<button id="delUser" class="btn btn-small"><i class="icon-remove"></i>删除</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="角色授权">
					<button id="grantUser" class="btn btn-small"><i class="icon-flag"></i>角色授权</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="数据授权">
					<c:if test="${atms['isStartDepartControl']=='1'}">
						<button id="grantData" class="btn btn-small"><i class="icon-filter"></i>数据授权</button>
					</c:if>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="密码重置">
					<button id="resetPwd" class="btn btn-small"><i class="icon-wrench"></i>密码重置</button>
				</btn:authorBtn>
		   </div>
		   <div class="clear"></div>
	    </div>
		
		<div class="row-fluid" style="margin:0;">
			<div class="pull-left ztree_box" style="margin-right:0; margin-left:0; height:548px;height:565px\0;#height:565px;_height:565px; 
			width:185px; overflow:scroll; position:fixed; left:6px;">
				<ul id="tree-rec" class="ztree tree_border2 ztree_ul" style="width:240px;"></ul>
			</div>
			<iframe name="myIframe" style="min-height:580px;" frameborder="0" width="100%" align="right" id="myIframe" src="">
			</iframe>
		</div>
   </div>
</div>
<script type="text/javascript">
<!--
	var chooseNode;
	var chooseId;
	
	$("#addUser").click(function(){
		myIframe.addUser(chooseId);
	});
	
	$("#delUser").click(function(){
		myIframe.removeUser();
	});
	
	$("#grantUser").click(function(){
		myIframe.grantRoles();
	});
	
	
	$("#grantData").click(function(){
		myIframe.grantData();
	});
	
	$("#resetPwd").click(function(){
		myIframe.resetPassword();
	});
	
	var firstAsyncSuccessFlag = 0;
	
	function treeOnlick(event, treeId, treeNode, clickFlag) {
		chooseId = treeNode.id;
		myIframe.window.location.href = "${root}/system/user/sublist/${menuid}/?search_deptid="	+ chooseId;
	}
	
	$(document).ready(function(){
		$("#myIframe").attr("src","${root}/system/user/sublist/${menuid}/?page=${pageList.currentPageNo}");
	});

	//$(document).ready(function() {
		var setting = {
			async : {
				enable : true,
				//url : "${root}/system/org/getTreeNode/",
				autoParam : [ "id" ]
			},
			check : {
				enable : false,
				chkStyle : "radio",
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				onClick : treeOnlick
			}
		};
		
		$.getJSON("${root}/system/org/showTreeNode/${menuid}/",function(zNodes){
			tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
			var node = tree.getNodeByParam("id", 0, null);
			tree.expandNode(node, true, false, true);
			chooseId = '${user.deptid}';
			chooseNode = tree.getNodeByParam("id", chooseId, null);
			tree.selectNode(chooseNode);			
		});
		
		$("#alert-div").hide();
	//});
//-->
</script>
