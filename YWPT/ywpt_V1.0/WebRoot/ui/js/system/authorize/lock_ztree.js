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
	var comp_key = $("#comp_key").val();
	if(1==check){
		var alllock_flag = parent.getAllLockFlag();
		parent.layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../../nodes/getLockNodes",
			data: {
				"comp_key": comp_key
			},
			success: function(returnedData){
				zNodes = eval("("+returnedData+")");
				var t = $("#treeDemo");
				$.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var node = zTree.getNodeByParam("id","s1");
				zTree.selectNode(node);
				zTree.expandNode(node,true,false,true,false);
				if("0" == alllock_flag){//全部锁具
					zTree.checkNode(node, true, true);
				}else{//非全部锁具
					zTree.checkNode(node, false, true);
				}
				parent.document.getElementById("authorize_name").focus();
				setTimeout(function(){
					parent.layer.closeAll('loading');
				},300);
			}
		});
	}else{
		parent.layer.load(2,{shade:0.3});
		$('#change').css("display","none");
		i18Frame = parent.parent.parent.window.frames["i18nframe"];
		$.ajax({
			type: "POST",
			url: "../../../authorize/getLockBy",
			data: {
				"record_key": parent.record_key,
				"comp_key":comp_key
			},
			success: function(returnedData){
				zNodes = eval("("+returnedData+")");
				var t = $("#treeDemo");
				$.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var node = zTree.getNodeByParam("id","s1");
				zTree.selectNode(node);
				setTimeout(function(){
					parent.layer.closeAll('loading');
				},300);
			}
		});
	}
});

function Change(val){
	var alllock_flag = parent.getAllLockFlag();
	var comp_key = $("#comp_key").val();
	if(1==val){//切换到锁组授权
		document.getElementById('change').name='0';
		$("#change_text").text(i18Frame.getunit());
		parent.layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../../group/getGroupJson1",
			data: {
				"comp_key": comp_key
			},
			success: function(returnedData){
				zNodes = eval("("+returnedData+")");
				$("#treeDemo").empty();
				var t = $("#treeDemo");
				$.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var node = zTree.getNodeByParam("id","s1");
				zTree.selectNode(node);
				zTree.expandNode(node,true,false,true,false);
				if("0" == alllock_flag){//全部锁具
					zTree.checkNode(node, true, true);
				}else{//非全部锁具
					zTree.checkNode(node, false, true);
				}
				parent.document.getElementById("authorize_name").focus();
				setTimeout(function(){
					parent.layer.closeAll('loading');
				},300);
			}
		});
	}else{//切换为正常授权
		document.getElementById('change').name='1';
		$("#change_text").text(i18Frame.getlockgroup());
		parent.layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../../nodes/getLockNodes",
			data: {
				"comp_key": comp_key
			},
			success: function(returnedData){
				zNodes = eval("("+returnedData+")");
				var t = $("#treeDemo");
				$.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var node = zTree.getNodeByParam("id","s1");
				zTree.selectNode(node);
				zTree.expandNode(node,true,false,true,false);
				if("0" == alllock_flag){//全部锁具
					zTree.checkNode(node, true, true);
				}else{//非全部锁具
					zTree.checkNode(node, false, true);
				}
				parent.document.getElementById("authorize_name").focus();
				setTimeout(function(){
					parent.layer.closeAll('loading');
				},300);
			}
		});
	}
}

function Lock_key(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var lock_value= "";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].isParent){
		}else{
			if(lock_value.indexOf(nodes[i].name+",")==-1){
				lock_value += nodes[i].name+",";
			}
		}
	}
	return lock_value;
}

function checkAllNode(){//勾选节点
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var node = zTree.getNodeByParam("id","s1");
	zTree.checkNode(node, true, true);
}

function cancelCheckAllNode(){//取消节点
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var node = zTree.getNodeByParam("id","s1");
	zTree.checkNode(node, false, true);
}

document.onkeydown = function(e){
	e = e ? e : window.event;
    var keycode = e.which ? e.which : e.keyCode;
	if(keycode == 13){
		Select1();
	}
};

function Select1(){
	var name = $("#lock_id").val();
	var nodec = document.getElementById('change').name;
	if(""!=name){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node1 = treeObj.getNodeByParam("id", "s1", null);
		treeObj.selectNode(node1);
		var nodes1 = treeObj.getNodesByParam("pId", node1.id, null);
		for(var i=0;i<nodes1.length;i++){
			treeObj.expandNode(nodes1[i], false,true,false);
		}
		var nodes;
		if(1==nodec){
			nodes = treeObj.getNodesByFilter(filter2);
		}else{
			nodes = treeObj.getNodesByFilter(filter3);
		}
		if(0==nodes.length){
			layer.tips(i18Frame.getno_cabinet(),'#lock_id',{
				tips:3,time:2000
			});
		}
		for(var i=0;i<nodes.length;i++){
			if(false==nodes[i].open){
				var pnode = treeObj.getNodeByParam("id", nodes[i].pId, null);
				treeObj.expandNode(pnode, true,false);
				treeObj.expandNode(nodes[i], true,true);
			}
		}
	}else{
		$("#lock_id").val("");
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node = treeObj.getNodeByParam("id", "s1", null);
		treeObj.selectNode(node);
		var nodes = treeObj.getNodesByParam("pId", node.id, null);
		for(var i=0;i<nodes.length;i++){
			if(true==nodes[i].open){
				treeObj.expandNode(nodes[i], false,true);
			}
		}
	}
}

function Select(){
	var name = $("#lock_id").val();
	var nodec = document.getElementById('change').name;
	if(""!=name){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node1 = treeObj.getNodeByParam("id", "s1", null);
		treeObj.selectNode(node1);
		var nodes1 = treeObj.getNodesByParam("pId", node1.id, null);
		for(var i=0;i<nodes1.length;i++){
			treeObj.expandNode(nodes1[i], false,true,false);
		}
		var nodes;
		if(1==nodec){
			nodes = treeObj.getNodesByFilter(filter);
		}else{
			nodes = treeObj.getNodesByFilter(filter1);
		}
		if(0==nodes.length){
			layer.tips(i18Frame.getno_condition_lock(),'#lock_id',{
				tips:3,time:2000
			});
		}
		for(var i=0;i<nodes.length;i++){
			var node = treeObj.getNodeByParam("id", nodes[i].pId, null);
			if(false==node.open){
				var pnode = treeObj.getNodeByParam("id", node.pId, null);
				treeObj.expandNode(pnode, true,false);
				treeObj.expandNode(node, true,true);
			}
		}
	}else{
		$("#lock_id").val("");
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var node = treeObj.getNodeByParam("id", "s1", null);
		treeObj.selectNode(node);
		var nodes = treeObj.getNodesByParam("pId", node.id, null);
		for(var i=0;i<nodes.length;i++){
			if(true==nodes[i].open){
				treeObj.expandNode(nodes[i], false,true);
			}
		}
	}
}


function filter(node) {
	var name1 = $("#lock_id").val();
    return (node.level == 2 && node.name.indexOf(name1)>-1);
}
function filter1(node) {
	var name1 = $("#lock_id").val();
    return (node.level == 3 && node.name.indexOf(name1)>-1);
}
function filter2(node) {
	var name1 = $("#lock_id").val();
    return (node.level == 1 && node.name.indexOf(name1)>-1);
}
function filter3(node) {
	var name1 = $("#lock_id").val();
    return (node.level == 2 && node.name.indexOf(name1)>-1);
}