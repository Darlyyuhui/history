var zNodes;
var setting = {
	view: {
		dblClickExpand: true,
		showLine: true,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			if (treeNode.isParent) {
				demoIframe.attr("src",treeNode.file + ".jsp?id="+treeNode.id);
				zTree.selectNode(treeNode);
				zTree.expandNode(treeNode,true,false,true,false);
				return false;
			} else {
				/*demoIframe.attr("src",treeNode.file + ".jsp?id="+treeNode.pId);*/
				return false;
			}
		}
	}
};

$(document).ready(function(){
	$.ajax({
		type: "POST",
		url: "../../../nodes/getNaviZnodes",
		data: {
			"f_type": 0
		},
		success: function(returnedData){
			zNodes = eval("("+returnedData+")");
			var t = $("#treeDemo");
			$.fn.zTree.init(t, setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			demoIframe = parent.$("#manageIframe");
			var node = zTree.getNodeByParam("id","n1");
			zTree.selectNode(node);
			zTree.expandNode(node,true,false,true,false);
			parent.frames['manageIframe'].document.getElementById('f_name').focus();
		}
	});
});

function addNode(id,pId,zname,zfile){
	treeId ="treeDemo";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	treeNode = zTree.getNodeByParam("id", pId);
	addHoverDom(id,pId,zname,zfile,treeId,treeNode);
}
function addHoverDom(id,pId,zname,zfile,treeId, treeNode){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getNodesByParam("pId", pId);
	if(nodes.length==0){
		treeNode.open=false;
		updateNode(treeNode);
	}
	if(zfile.indexOf("second")>-1){
		zTree.addNodes(treeNode, {id:id, pId:pId, name:zname,file:zfile,open:false,isParent:true,iconOpen:null,iconClose:null,icon:null});
	}else if(zfile.indexOf("third")>-1){
		zTree.addNodes(treeNode, {id:id, pId:pId, name:zname,file:zfile,open:false,isParent:true,iconOpen:null,iconClose:null,icon:null});
	}else{
		zTree.addNodes(treeNode, {id:id, pId:pId, name:zname,file:zfile,open:false,isParent:false,iconOpen:null,iconClose:null,icon:null});
	}
}

function delNode(id){
	treeId = "treeDemo";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	treeNode = zTree.getNodeByParam("id", id);
	removeHoverDom(treeId, treeNode);
}
function delNode1(id,pId){
	treeId = "treeDemo";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	treeNode = zTree.getNodeByParam("id", id);
	treeNodes = zTree.getNodesByParam("id", id);
	removeHoverDom(treeId, treeNode);
	pNode = zTree.getNodeByParam("id", pId);
	zTree.addNodes(pNode, treeNodes);
}
function removeHoverDom(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var node = treeNode.getParentNode();
	zTree.removeNode(treeNode);
	var nodes = zTree.getNodesByParam("pId", node.id);
	if(nodes.length==0){
		node.isParent=true;
		node.open=true;
		updateNode(node);
	}
};

function updNode(id,name){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	treeNode = zTree.getNodeByParam("id", id);
	treeNode.name=name;
	updateNode(treeNode);
}

function updateNode(treeNode){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.updateNode(treeNode);
}