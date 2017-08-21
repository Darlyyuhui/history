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
	parent.layer.load(2,{shade:0.3});
	$.ajax({
		type: "POST",
		url: "../../../nodes/getPersonZnodes",
		success: function(returnedData){
			zNodes = eval("("+returnedData+")");
			var t = $("#treeDemo");
			$.fn.zTree.init(t, setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			demoIframe = parent.$("#manageIframe");
			var node = zTree.getNodeByParam("id","s2");
			zTree.selectNode(node);
			zTree.expandNode(node,true,false,true,false);
			setTimeout(function(){
				parent.layer.closeAll('loading');
			},300);
			parent.frames['manageIframe'].document.getElementById('branch_name').focus();
		}
	});
});

function addNode(id,pId,zname){
	treeId ="treeDemo";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	treeNode = zTree.getNodeByParam("id", pId);
	addHoverDom(id,pId,zname,treeId,treeNode);
}
function addHoverDom(id,pId,zname,treeId, treeNode){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getNodesByParam("pId", pId);
	if(nodes.length==0){
		treeNode.open=false;
		updateNode(treeNode);
	}
	if(id.indexOf("b")>-1){
		zTree.addNodes(treeNode, {id:id, pId:pId, name:zname,file:"user/user",open:false,isParent:true,iconOpen:null,iconClose:null,icon:"../../../images/img/branch.png"});
	}else if(id.indexOf("us")>-1){
		zTree.addNodes(treeNode, {id:id, pId:pId, name:zname,file:"key/key",open:false,isParent:true,iconOpen:null,iconClose:null,icon:"../../../images/img/user.png"});
	}else if(id.indexOf("k")>-1){
		zTree.addNodes(treeNode, {id:id, pId:pId, name:zname,file:"",open:false,isParent:false,iconOpen:null,iconClose:null,icon:"../../../images/img/key.png"});
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

document.onkeydown = function(e){
	e = e ? e : window.event;
    var keycode = e.which ? e.which : e.keyCode;
	if(keycode == 13){
		SelectUser();
	}
};

function SelectUser(){
	var name = $("#key_id").val();
	if(""!=name){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node1 = treeObj.getNodeByParam("id", "s2", null);
		treeObj.selectNode(node1);
		var nodes1 = treeObj.getNodesByParam("pId", node1.id, null);
		for(var i=0;i<nodes1.length;i++){
			treeObj.expandNode(nodes1[i], false,true,false);
		}
		/*treeObj.expandAll(false);
		treeObj.expandNode(node1, true,false);*/
		var nodes = treeObj.getNodesByFilter(filter);
		if(0==nodes.length){
			layer.tips('没有符合条件的人员','#key_id',{
				tips:3,time:2000
			});
		}
		for(var j=0;j<nodes.length;j++){
			var node = treeObj.getNodeByParam("id", nodes[j].pId, null);
			if(false==node.open){
				var pnode = treeObj.getNodeByParam("id", node.pId, null);
				treeObj.expandNode(pnode, true,false);
				treeObj.expandNode(node, true,false);
			}
		}
	}else{
		$("#key_id").val("");
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node = treeObj.getNodeByParam("id", "s2", null);
		treeObj.selectNode(node);
		var nodes = treeObj.getNodesByParam("pId", node.id, null);
		for(var i=0;i<nodes.length;i++){
			if(true==nodes[i].open){
				treeObj.expandNode(nodes[i], false,true,false);
			}
		}
	}
}
function filter(node) {
	var name1 = $("#key_id").val();
    return (node.level == 2 && (node.name.split("(")[0].indexOf(name1)>-1||node.num.indexOf(name1)>-1));
}
function SelectKey(){
	var name = $("#key_id").val();
	if(""!=name){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node1 = treeObj.getNodeByParam("id", "s2", null);
		treeObj.selectNode(node1);
		var nodes1 = treeObj.getNodesByParam("pId", node1.id, null);
		for(var i=0;i<nodes1.length;i++){
			treeObj.expandNode(nodes1[i], false,true,false);
		}
		/*treeObj.expandAll(false);
		treeObj.expandNode(node1, true,false);*/
		var nodes = treeObj.getNodesByFilter(filter1);
		if(0==nodes.length){
			layer.tips('没有符合条件的钥匙','#key_id',{
				tips:3,time:2000
			});
		}
		for(var j=0;j<nodes.length;j++){
			var node = treeObj.getNodeByParam("id", nodes[j].pId, null);
			if(false==node.open){
				var pnode = treeObj.getNodeByParam("id", node.pId, null);
				treeObj.expandNode(pnode, true,false);
				treeObj.expandNode(node, true,true);
			}
		}
	}else{
		$("#key_id").val("");
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node = treeObj.getNodeByParam("id", "s2", null);
		treeObj.selectNode(node);
		var nodes = treeObj.getNodesByParam("pId", node.id, null);
		for(var i=0;i<nodes.length;i++){
			if(true==nodes[i].open){
				treeObj.expandNode(nodes[i], false,true,false);
			}
		}
	}
}
function filter1(node) {
	var name1 = $("#key_id").val();
    return (node.level == 3 && node.name.indexOf(name1)>-1);
}