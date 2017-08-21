var zNodes;
var setting = {
	view: {
		dblClickExpand: true,
		showLine: true
	},
	check: {
		enable: true
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
				zTree.selectNode(treeNode);
				zTree.expandNode(treeNode,true,false,true,false);
				return false;
			} else {
				zTree.selectNode(treeNode);
				return false;
			}
		}
	}
};

var i18Frame = parent.parent.window.frames["i18nframe"];

$(document).ready(function(){
	var check=parent.checkAuthorize();
	var user_id = $("#user_id").val();
	var comp_key = $("#comp_key").val();
	if(1==check){
		$.ajax({
			type: "POST",
			url: "../../../nodes/getUserNodes",
			data: {
				"user_id": user_id,
				"comp_key": comp_key
			},
			success: function(returnedData){
				zNodes = eval("("+returnedData+")");
				var t = $("#treeDemo");
				$.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var node = zTree.getNodeByParam("id","s2");
				zTree.selectNode(node);
				zTree.expandNode(node,true,false,true,false);
				parent.document.getElementById("authorize_name").focus();
			}
		});
	}else{
		$('#change').css("display","none");
		i18Frame = parent.parent.parent.window.frames["i18nframe"];
		$.ajax({
			type: "POST",
			url: "../../../lockauth/getUserBy",
			data: {
				"record_key": parent.record_key,
				"comp_key":comp_key
			},
			success: function(returnedData){
				zNodes = eval("("+returnedData+")");
				var t = $("#treeDemo");
				$.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var node = zTree.getNodeByParam("id","s2");
				zTree.selectNode(node);
			}
		});
	}
});


function Key_key(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var key_value="";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].isParent){
		}else{
			if(key_value.indexOf(nodes[i].num+",")==-1){
				key_value += nodes[i].num+",";
			}
		}
	}
	return key_value;
}

document.onkeydown = function(e){
	e = e ? e : window.event;
    var keycode = e.which ? e.which : e.keyCode;
	if(keycode == 13){
		Select1();
	}
};

function Select1(){
	var name = $("#key_id").val();
	if(""!=name){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node1 = treeObj.getNodeByParam("id", "s2", null);
		treeObj.selectNode(node1);
		var nodes1 = treeObj.getNodesByParam("pId", node1.id, null);
		for(var i=0;i<nodes1.length;i++){
			treeObj.expandNode(nodes1[i], false,true,false);
		}
		var nodes = treeObj.getNodesByFilter(filter1);
		if(0==nodes.length){
			layer.tips(i18Frame.getno_user(),'#key_id',{
				tips:3,time:2000
			});
		}
		for(var j=0;j<nodes.length;j++){
			if(false==nodes[j].open){
				var pnode = treeObj.getNodeByParam("id", nodes[j].pId, null);
				treeObj.expandNode(pnode, true,false);
				treeObj.expandNode(nodes[j], true,true);
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

function Select(){
	var name = $("#key_id").val();
	if(""!=name){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node1 = treeObj.getNodeByParam("id", "s2", null);
		treeObj.selectNode(node1);
		var nodes1 = treeObj.getNodesByParam("pId", node1.id, null);
		for(var i=0;i<nodes1.length;i++){
			treeObj.expandNode(nodes1[i], false,true,false);
		}
		var nodes = treeObj.getNodesByFilter(filter);
		if(0==nodes.length){
			layer.tips(i18Frame.getno_key(),'#key_id',{
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

function filter(node) {
	var name1 = $("#key_id").val();
    return (node.level == 2 && node.name.indexOf(name1)>-1);
}
function filter1(node) {
	var name1 = $("#key_id").val();
    return (node.level == 1 && (node.name.indexOf(name1)>-1||node.num.indexOf(name1)>-1));
}