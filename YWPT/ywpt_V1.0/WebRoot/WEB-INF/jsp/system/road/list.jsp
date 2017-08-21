<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>

<div class="alert alert-block pull-top  alert-error" id="alert-div" style="display: none;">
	  <p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button><p align="center">${message}</p></div>
	<script>
		 setTimeout('$("#message").hide("slow")',1200);
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
		$("#subinfo").removeClass("alert-error").addClass("alert-success");
		if(s.indexOf("二级道路下不允许再添加道路信息") >= 0){
			$("#subinfo").removeClass("alert-success").addClass("alert-error");
		}
		setTimeout('$("#subinfo").hide("slow")', 1200);
		setTimeout("showList()",1800);
	}
	
	function showList(){
		window.location.href = "${root}/system/road/list/${menuid}/";
    }
</script>
<!-- 控制iframe中的页面返回的结果显示结束 -->
<div class="conten_box">
	<h4 class="title_intro">道路管理--信息维护</h4>
	<div class="mar_5">
	  <div class="btn-group-wrap mar_b5">
		<div class="btn-group pull-left">
			<btn:authorBtn menuid="${menuid}" text="添加">
				<button class="btn btn-small" onclick="addNode()"><i class="icon-plus"></i>添加</button>
			</btn:authorBtn>
			<btn:authorBtn menuid="${menuid}" text="修改">
				<button class="btn btn-small" onclick="updateNode()"><i class="icon-edit"></i>修改</button>
			</btn:authorBtn>
			<btn:authorBtn menuid="${menuid}" text="删除">
				<button class="btn btn-small" onclick="delNode()"><i class="icon-remove"></i>删除</button>
			</btn:authorBtn>
		</div>
		<div class="clear"></div>
	  </div>
	  <div class="row-fluid" style="margin:0;">
		<div class="pull-left ztree_box" style="margin:0;height:535px; width:185px; overflow:scroll; position:fixed; left:6px;">
		  <ul id="tree-road" class="ztree" style="margin-top:5px;min-width:380px"></ul> 
		</div>
		<iframe name="myIframe"	frameborder="0" width="100%" id="myIframe" src="" style="padding:0;margin:0;min-height:540px;" ></iframe>
		<div class="clear"></div>
	  </div>
	</div>
</div>
<input type="hidden" id="menuid" value="${menuid}"/>
<script type="text/javascript">
	
	$("#myIframe").load(function(){
		var myIframeH = $(this).contents().height();
		$(this).height(myIframeH);
	});

	var tree;
	var ids;
	var firstAsyncSuccessFlag =0;
  	$(document).ready(function(){
 	var setting = {
	    async: {
				enable: true,
				url:"${root}/system/road/showTree/",
				autoParam:["id"]
				},
		check: {
				enable: false,
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback : {
			onClick : treeOnlick,
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
	};
    tree= $.fn.zTree.init($("#tree-road"), setting);
    $("#alert-div").hide();
 });
    
    
    function zTreeOnAsyncSuccess(event, treeId, msg) {  
	 	if (firstAsyncSuccessFlag == 0) {  
	           try {  
	                  //调用默认展开第一个结点  
	                  var nodes = tree.getNodes();  
	                  tree.expandNode(nodes[0], true);  
	               
	                  var childNodes = tree.transformToArray(nodes[0]);  
	                  tree.expandNode(childNodes[1], true);  
	                  var childNodes1 = tree.transformToArray(childNodes[1]);  
	                  tree.checkNode(childNodes1[1], true, true);  
	                  firstAsyncSuccessFlag = 1;  
	            } catch (err) {  
	               
	            }  
	     }
	} 
 
 function treeOnlick(event, treeId, treeNode, clickFlag){
 	ids = treeNode.id;
 	myIframe.window.location.href="${root}/system/road/view/"+ids+"/${menuid}/";
 }
 
 function addNode(treeId, treeNode){
	var nodes = tree.getSelectedNodes();
	/**if (nodes[0].id == '00') {
		showMessage("道路根节点下不允许添加道路信息");
		return;
	}*/
	if (nodes.length == 0 ||nodes.length >1) {
		showMessage("请先选择一个父节点");
		return;
	} 
	$("#myIframe").attr("src","${root}/system/road/showadd/${menuid}/"+nodes[0].id+"/");
 }
 
 function updateNode(){
    if(ids == undefined){
      showMessage("请先选择要修改的节点");
	  return;
    }else{
    	if (ids == '00') {
			showMessage("道路根节点不允许修改");
			return;
		}
    }
 	$("#myIframe").attr("src","${root}/system/road/update/"+ids+"/${menuid}/");
 }
 
 function delNode(){
    if(ids == undefined){
      showMessage("请先选择要删除的节点");
	  return;
    }
    var nodes = tree.getSelectedNodes();
    if(nodes[0].id=='00'){
    	showMessage("道路根节点不允许删除");
    	return;
    }else{
	    if(nodes[0].children != undefined){
	      if(nodes[0].children.length > 0){
	         alert("要删除的节点存在子节点，无法能直接删除！");
	         return;
	      }
	    }
    }
 	if(confirm("删除后数据将无法恢复，确定要删除吗？")){
 	var url="${root}/system/road/delete/"+ids+"/";
	$.ajax( {
		type : 'delete',
		url : url,
		dataType : "json",
		success:function(msg){
			if(msg.result=="ok"){
				 $("#alert-div").removeClass("alert-error").addClass("alert-success");
   				 showMessage("删除成功");
   				 setTimeout("showList()",1600);
   			}
   			if(msg.result=="error"){
   				 showMessage(msg.message);
   				 setTimeout("showList()",1200);
   			}
		}
	});
   }
 }
</script> 
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>