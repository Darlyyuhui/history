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

$(document).ready(function(){
	var check=parent.checkAdd();
	if(0==check){
		$.ajax({
			type: "POST",
			url: "../../../../nodes/getNaviZnodes2",
			data: {
				"f_type": 0
			},
			success: function(returnedData){
				zNodes = eval("("+returnedData+")");
				var t = $("#treeDemo");
				$.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			}
		});
	}else if(1==check){
		$.ajax({
			type: "POST",
			url: "../../../../user_type/getNaviZnodesBy",
			data: {
				"ut_key": parent.ut_key,
				"f_type": 0
			},
			success: function(returnedData){
				zNodes = eval("("+returnedData+")");
				var t = $("#treeDemo");
				$.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			}
		});
	}
});

function GetNavigation(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var ut_power=",";
	for(var i=0;i<nodes.length;i++){
		ut_power += nodes[i].id+",";
	}
	return ut_power;
}
