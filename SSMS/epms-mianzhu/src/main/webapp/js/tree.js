/****
 * 树节点封装类
 * @param {} url 请求url
 * @param {} render 待渲染的html
 * @param {} checked 是否支持checkbox
 * @param {} onclick 单击function
 */
function Tree(url, render, checked, onclick,onAsyncSuccess,type) {
	var ztree;
	var sel = [];
    if(type == 'check'){
    	type ='checkbox'
    }else{
    	type ='radio'
    }
	this.zTreeOnCheck = function(event, treeId, treeNode) {
		sel.push("'" + treeNode.id + "'");
		return true;
	};
	
	var settings = {
		async : {
			enable : true,
			url : url,
			autoParam : ["id", "name"]
		},

		check : {
			enable : checked,
			chkStyle:type
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : "0"
			}
		},
		view : {
			expandSpeed : ""
		},
		callback : {
			onClick : onclick,
			onAsyncSuccess:onAsyncSuccess
		}
	};

	this.getCheckedNodes = function() {
		var rel = [];
		$.each(this.ztree.getCheckedNodes(), function(i, n) {
					rel.push(n.id);
				});
		return rel;
	};

	this.showTree = function() {
		this.ztree = $.fn.zTree.init($("#" + render), settings, null);
		var zTree = $.fn.zTree.getZTreeObj(render);
		zTree.setting.check.chkboxType = {
			"Y" : "s",
			"N" : "ps"
		};
		$('#' + render).show();
	};

	this.getTree = function() {
		return this.ztree;
	}
}