<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>

<div class="conten_box" style="height: 730px;overflow:hidden;">
	<h4 class="title_intro">设备管理-信息维护</h4>
	<div class="mar_5">
	  <div class="row-fluid" style="margin:0;">
		<div class="pull-left ztree_box" style="margin:0;height:300px; width:180px; overflow:hidden; position:fixed; left:4px;">
		  <h4 class="title_intro">设备信息树</h4>
		  <ul id="tree-road" class="ztree" style="margin-top:5px;min-width:180px;"></ul> 
		</div>
		<iframe name="myIframe"	frameborder="0" width="100%" id="myIframe" src="" style="min-height:690px;margin-left: -2px;" ></iframe>
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
					url:"${root}/property/asset/showTree/",
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
	    
	    myIframe.window.location.href="${root}/property/deviceinfo/list/${menuid}/";
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
    	chooseId = treeNode.id;
    	if(chooseId == '001'){
    		$(".ztree_box").height("300px");
	   	  	myIframe.window.location.href="${root}/property/deviceinfo/list/${menuid}/";
    	}else if(chooseId == '007'){
    		$(".ztree_box").height("300px");
	   	  	myIframe.window.location.href="${root}/property/cabinfo/list/${menuid}/";
    	}else if(chooseId == '002'){
    		$(".ztree_box").height("300px");
	   	  	myIframe.window.location.href="${root}/property/videoinfo/list/${menuid}/";
    	}else {
    		$(".ztree_box").height("685px");
    		if(chooseId == '003'){
		   	  	myIframe.window.location.href="${root}/property/databaseinfo/list/${menuid}/";
    		}else if(chooseId == '004'){
		   	  	myIframe.window.location.href="${root}/property/ftpinfo/list/${menuid}/";
    		}else if(chooseId == '005'){
		   	  	myIframe.window.location.href="${root}/property/serverinfo/list/${menuid}/";
    		}else if(chooseId == '006'){
		   	  	myIframe.window.location.href="${root}/property/projectinfo/list/${menuid}/";
    		}
    	}
    }
    
    
</script> 
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>