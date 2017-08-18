	MapFactory.Define("ItmsMap/Util/TreeWidget*", function(){
			return function() {
				var api = {
						init: init,
						cancelSelectedNode: cancelSelectedNode
					};
					
					var toString = Object.prototype.toString,
						//全局调用的树对象
						$zTree = null,
						//外界传回的回调函数
						callback,
						
						//值返回给的元素 和触发此树弹出框的元素相同
						resultCon,
																
						//随机数形成的名字 保证div id不重复
						divId = "";						
					
					/**
					*elem 元素id 表示此元素的点击操作触发树结构弹出
					*@param zSettings 格式{chkStyle: "", idKey:"", pIdKey: "", rootPId: null, callback: ""}
					*@param dataSettings 格式{ajaxSettings: {}, isRealTime: , zNodes: "", isAjax: ""}
					*isAjax 指示是否通过ajax来获取数据还是直接使用zNodes
					*/				
					function init(elem, zSettings, dataSettings, type) {
						resultCon = document.getElementById(elem);
						if(typeof type == 'undefined')type = "radio";
						//默认设置
						var setting = {
							check: {
								enable: true,
								chkStyle: type,
								chkboxType: { "Y": "ps", "N": "ps" },
								//radioType: "level"
								radioType: "all"
							},
							data: {
								simpleData: {
									enable: true,
									idKey: "id",
									pIdKey: "pid",
									rootPId: null
								}
							},
							callback: {
								onCheck: checkCall 
							},
							_top:{
								_top:0
							}
						};
						
						if(dataSettings["_top"] != undefined){
							setting._top._top = dataSettings["_top"];
						}
						
						//键值索引 传入的参数与元素参数位置对应的索引
						var settingKeys = {
							chkStyle: setting.check,
							idKey: setting.data.simpleData,
							pIdKey: setting.data.simpleData,
							rootPId: setting.data.simpleData
						};
						
						if (!dataSettings) {
							dataSettings = zSettings;
							zSettings = {};
						}
											
						for (var key in zSettings) {
							if (zSettings.hasOwnProperty(key) && settingKeys.hasOwnProperty(key)) {
								settingKeys[key][key] = zSettings[key];
							}
						}					
						//设置选中操作后的回调
						callback = zSettings["callback"];
		
						if (toString.call(dataSettings) != "[object Object]") return;
						if (dataSettings.isAjax) {
							var ajaxSettings = dataSettings["ajaxSettings"];
							
							ajaxSettings["success"] =  function(e) {
								createDom(elem, setting, e);
							};
							
							$.ajax(ajaxSettings);
						} else {
							createDom(elem, setting, dataSettings["zNodes"]);
						}
					}
					
					function cancelSelectedNode(){
						if($zTree)$zTree.checkAllNodes(false);
					}
					
					function checkCall(event, treeId, treeNode) {
		
						var strs = [],
							ids = [],
							nodes = [],
							tagNames = {
								"DIV": "innerHTML",
								"INPUT": "value"
							};
		
						if ($zTree) {
							nodes = $zTree.getCheckedNodes(true);
						} 
						//返回nodes用户操作
						if (typeof callback == "function") {
							callback(nodes);
						//如果回调函数不存在
						} else {
							var i = nodes.length - 1;
							for (; i >= 0 && !(node = nodes[i]).isParent; i--) {
								strs.push(node.name);
								ids.push(node.id);
							}
							
							var tagName = resultCon.tagName;
							resultCon.setAttribute(tagNames[tagName], strs.join(";"));
						}					
					}
					
					//生成对应的dom树
					function createDom(elem, zSettings, zNodes) {
						var //装载树的div
							div,
							
							//装载全部树对象最外层的div，便于控制样式
							pCon;
		
						//处理zNodes
						if (typeof zNodes == "string") {
							zNodes = eval(zNodes);
						}
												
						div = document.createElement("div");	
						div.className = "ztree";
						div.id = divId = "zTree_" + (Math.random() + '').replace(".", "");	
						
						$zTree = $.fn.zTree.init($(div), zSettings, zNodes);
		
						pCon = document.createElement("div");
						pCon.style.cssText = "display:none; position: absolute; background: #f0f6e4; border: 1px solid #617775; width:175px;overflow-x:scroll; height:260px; overflow-y:scroll;";				
						pCon.appendChild(div);					
						document.body.appendChild(pCon);
						
						//注册点击事件
						$("#" + elem).click(function() {
							//设置显示树对象的位置
							var a = document.getElementById(elem),
								offset = $(a).offset(),
								cw, ch,
								client,
								location;
							
							location = {left: offset.left, top: offset.top + a.offsetHeight + zSettings["_top"]["_top"]};
							$(pCon).css({left: location.left + "px", top: location.top + "px", display: "block"});
							return false;
						});
						
						$("body").click(function() {
							pCon.style.display = "none";
						});
						
						//取消冒泡
						$(pCon).click(function(event) {
							if (event.stopPropagation) {
								event.stopPropagation();
							} else {
								window.event.cancelBubble = true;
							}
							
							return false;
						});
					}
					return api;
			}	
	});