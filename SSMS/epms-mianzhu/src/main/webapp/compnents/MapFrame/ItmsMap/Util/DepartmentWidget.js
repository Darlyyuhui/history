MapFactory.Define("ItmsMap/Util/DepartmentWidget*",function(){
	return function(){

		var api = {
			addDeptTree : addDeptTree // 添加部门树
		};

		var urls = {
			departmentUrl : "map/getDeptAsJson/"
		};

		/**
		 * 添加部门树
		 * @param menuid String 系统权限ID
		 * @param seed String 个性种子，后缀，用于同一页面不同的html调用
		 * @param target String 添加到容器的ID
		 * @param triggerButton String 可选项，触发按钮，如果设置则该按钮触发事件，如果没有设置，则默认target触发
		 */
		function addDeptTree(menuid,seed,target,triggerButton){
			var mapDeptTree=null;
			var deptContainer = $("#"+target);
			var deptHtml = '<div id="mapDepartmentListBox_'+seed+'" class="mapDepartmentListBox">'+
				'<div class="mapDepartmentList">'+
					'<ul id="orgTreeSpace_'+seed+'" class="ztree" style="margin-top: 0px; width:170px;"></ul>'+
				'</div>'+
				'<div style="width:100%;text-align:center;padding:5px;">'+
					'<input id="mapDepartmentListBoxConfirm_'+seed+'"'+
					'type="button" class="btn btn-info"'+
					'style="margin:0;padding:0 10px;height:24px;line-height:12px;"'+
					'value="确定"/>'+
				'</div>'+
			'</div>';
			if(triggerButton){
				$("#"+triggerButton).click(function(e){
					_initTree({
						target : target,
						tidSrc : "mapDeptId_"+seed,
						textArea : target,
						deptVal : "mapDepartmentVal_"+seed,
						deptTids : "mapDeptId_"+seed
					});
				});
			}else{
				deptContainer.click(function(e){
					_initTree({
						target : target,
						tidSrc : "mapDeptId_"+seed,
						textArea : target,
						deptVal : "mapDepartmentVal_"+seed,
						deptTids : "mapDeptId_"+seed
					});
				});
			}

			function _setHTML(){
				var selectedResHtml = '<input id="mapDepartmentVal_'+seed+'" type="hidden" value="" />'+
					'<input id="mapDeptId_'+seed+'" type="hidden" value="" />';
				$("body").append(deptHtml);
				deptContainer.after(selectedResHtml);
				_getDeptAsJson("orgTreeSpace_"+seed);
			}

			_setHTML();

		    /**
			 * 以JSON格式获取部门信息
			 */
			function _getDeptAsJson(src){
				var instance = this;
				$.post(urls.departmentUrl,{menuid:menuid},function(data){
					var dept =data,
				    	departmentNodes = eval("(" + dept + ")"),
				    	orgSetting = {
							check: {
								enable: true,
								chkStyle: "checkbox",
								chkboxType: { "Y": "p", "N": "s" }
							},
							view: {
								dblClickExpand: false
							},
							data: {
								simpleData: {
									enable: true
								}
							},
							callback: {
							}
						};
						orgTree = $.fn.zTree.init($("#"+src),orgSetting,departmentNodes),
						nodes = orgTree.getNodes();
					orgTree.expandNode(nodes[0],true);
					mapDeptTree = orgTree;
				});
			}

			function _initTree(data){
				var position = $("#"+data.target).position();
				var deptTree = mapDeptTree;
				if(!deptTree){
					return;
				}
				deptTree.checkAllNodes(false);
				var selectedOrgtIds = $("#"+data.tidSrc).val();
				var selectedOrgtIdsArr = [];
				if(selectedOrgtIds){
					selectedOrgtIdsArr = selectedOrgtIds.split(" ");
				}

				if(selectedOrgtIdsArr.length){
					for(var i=0,len=selectedOrgtIdsArr.length;i<len;i++){
						var node = deptTree.getNodeByTId(selectedOrgtIdsArr[i]);
						deptTree.checkNode(node,true);
					}
				}
				$("#mapDepartmentListBox_"+seed).css({
					left : position.left,
					top : position.top
				}).show();

				$("#mapDepartmentListBoxConfirm_"+seed).unbind("click").bind("click",function(){
					_confirmTree({
						textArea : data.textArea,
						deptVal : data.deptVal,
						deptTids : data.deptTids
					});
				});
			}

			function _confirmTree(data){
				var deptTree = mapDeptTree;
				if(!deptTree){
					return;
				}
				var nodes = deptTree.getCheckedNodes(true);
				var names = "";
				var codes = "";
				var orgIds = "";
				for(var i=0,len=nodes.length;i<len;i++){
					codes += nodes[i].code;
					names += nodes[i].name;
					orgIds += nodes[i].tId;
					if(i!=(len-1)){
						codes += " ";
						names += ";<br/>";
						orgIds += " ";
					}
				}
				if(names){
					$("#"+data.textArea).html(names);
				}else{
					$("#"+data.textArea).html("所有部门");
				}
				$("#"+data.deptVal).val(codes);
				$("#"+data.deptTids).val(orgIds);
				$("#mapDepartmentListBox_"+seed).hide();
			}
		}

		return api;
	}
});