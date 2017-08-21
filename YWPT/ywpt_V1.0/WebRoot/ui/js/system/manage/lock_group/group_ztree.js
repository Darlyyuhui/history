var zNodes;
var comp_key;
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
			if (treeNode.level==0||treeNode.level==1) {
				demoIframe.attr("src",treeNode.file + ".jsp?id="+treeNode.id);
				zTree.selectNode(treeNode);
				zTree.expandNode(treeNode,true,false,true,false);
				return false;
			} else {
				/*demoIframe.attr("src",treeNode.file + ".jsp?id="+treeNode.pId);*/
				zTree.expandNode(treeNode,true,false,true,false);
				return false;
			}
		}
	}
};

$(document).ready(function(){
	parent.layer.load(2,{shade:0.3});
	comp_key = $("#comp_key").val();
	$.ajax({
		type: "POST",
		url: "../../../../group/getGroupJson",
		data: {
			"comp_key": comp_key
		},
		success: function(returnedData){
			zNodes = eval("("+returnedData+")");
			var t = $("#treeDemo");
			$.fn.zTree.init(t, setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			demoIframe = parent.$("#manageIframe");
			var node = zTree.getNodeByParam("id","s1");
			zTree.selectNode(node);
			zTree.expandNode(node,true,false,true,false);
			setTimeout(function(){
				parent.layer.closeAll('loading');
			},300);
			parent.frames['manageIframe'].document.getElementById('group_name').focus();
		}
	});
});

function addNode(id,pId,zname){
	treeId ="treeDemo";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	treeNode = zTree.getNodeByParam("id", pId);
	addHoverDom(id,pId,zname,treeId,treeNode);
}

function addNewSmallTool(group_key,g_num_help,g_name_help,add_num){
	treeId ="treeDemo";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	treeNode = zTree.getNodeByParam("id", "g"+group_key);
	var streeNode = null;
	var scount = 0;
	var lock_value = g_num_help.split(";");
	var lock_nvalue = g_name_help.split(";");
	var chk_value=[];
	for(var i=0;i<lock_value.length-1;i++){
		addHoverDom(group_key+"u"+lock_value[i],"g"+group_key,lock_nvalue[i]+"(0)",treeId,treeNode);
		chk_value.push(lock_value[i]);
	}
	$.ajax({
		type: "POST",
		url: "../../../../lock/getLockByUnits",
		data: {
			"chk_value[]":chk_value
		},
		success: function(returnedData){
			var jar0 = eval("("+returnedData+")");
			for(var j=0;j<jar0.length;j++){
				streeNode = zTree.getNodeByParam("id", group_key+"u"+jar0[j].unit_key);
				scount = parseInt(streeNode.name.split("(")[1].split(")")[0]);
				scount += 1;
				updNode(group_key+"u"+jar0[j].unit_key,streeNode.name.split("(")[0]+"("+scount+")");
				addHoverDom(group_key+"l"+jar0[j].lock_key,group_key+"u"+jar0[j].unit_key,jar0[j].lock_id,treeId,treeNode);
			}
		}
	});
	var count = parseInt(treeNode.name.split("(")[1].split(")")[0]);
	count += add_num;
	updNode("g"+group_key,treeNode.name.split("(")[0]+"("+count+")");
}

function delSmallTool(group_key,s_unit_key){
	treeId ="treeDemo";
	var unit_key_array = s_unit_key.split(";");
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	treeNode = zTree.getNodeByParam("id", "g"+group_key);
	var count = parseInt(treeNode.name.split("(")[1].split(")")[0]);
	count = count - (unit_key_array.length-1);
	updNode("g"+group_key,treeNode.name.split("(")[0]+"("+count+")");
	for( var i = 0; i < (unit_key_array.length-1); i++){
		delNode(group_key+"u"+unit_key_array[i]);
	}
}

function addHoverDom(id,pId,zname,treeId, treeNode){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getNodesByParam("pId", pId);
	if(nodes.length==0){
		treeNode.open=false;
		updateNode(treeNode);
	}
	if(id.indexOf("g")>-1){
		zTree.addNodes(treeNode, {id:id, pId:pId, num:"", name:zname,file:"group_unit",open:false,isParent:true,iconOpen:null,iconClose:null,icon:null});
	}else if(id.indexOf("u")>-1){
		zTree.addNodes(treeNode, {id:id, pId:pId, num:"", name:zname,file:"",open:false,isParent:true,iconOpen:null,iconClose:null,icon:"../../../../images/img/unit.png"});
	}else if(id.indexOf("l")>-1){
		zTree.addNodes(treeNode, {id:id, pId:pId, num:"", name:zname,file:"",open:false,isParent:false,iconOpen:null,iconClose:null,icon:"../../../../images/img/lock.png"},true);
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

function updNode1(id,name){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	treeNode = zTree.getNodeByParam("id", id);
	var count = treeNode.name.split("(")[1];
	treeNode.name=name+"("+count;
	updateNode(treeNode);
}

function updateNode(treeNode){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.updateNode(treeNode);
}