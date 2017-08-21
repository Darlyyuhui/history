// 右边加载几屏
var screen_index = 1;
// 每页显示几屏，默认1屏，当前页显示的个数
var js_pageCurrent = 1;
// 分页默认第一页,当前第几页
var js_pageNumber = 1;
// 总页数
var js_pageNum = 0;

// 一屏用于存放当前正在刷新的设备编号
var oneScreenDevice = new Array();
// 二屏用于存放当前正在刷新的设备编号
var twoScreenDevice = new Array();
// 四屏用于存放当前正在刷新的设备编号
var fourScreenDevice = new Array();
// 六屏用于存放当前正在刷新的设备编号
var sixScreenDevice = new Array();
// 九屏用于存放当前正在刷新的设备编号
var nineScreenDevice = new Array();

var treeNode = {};

//isdeviceFlag值为1是说明是设备,其他说明当前拖动的树节点不是设备
var isdeviceFlag = "";

//存放上一次拖动树节点是否是设备标识的值,1表示拖动节点为设备
var lastNodeMoveDeviceFlag ="";

/**
 * 分页函数 pno--页数 psize--每页显示记录数 分页部分是从真实数据行开始，因而存在加减某个常数，以确定真正的记录数
 * 纯js分页实质是数据行全部加载，通过是否显示属性完成分页功能
 */
function ddd() {
	window.frames['dddddddddddddd'].frames["oneScreenImg1"]
			.$("#oneScreenImg1_input").val('dddddwwewe');
}

/**
 * 获取拖拽节点，下面可以显示的设备编号，只支持拖拽节点下的设备节点，其他概不支持 nodeId拖拽树节点id,存储
 */
function nodeListData(nodeId, index_data1, index_device_data1) {

	// 原来获取拖拽节点下的叶子节点
	// var nodeList = $("#"+ddddd+" ul li a").find("span:first-child");
	// 获取拖拽节点下的孩子节点
	var liNodeList = $("#" + ddddd + ">ul>li");
	for (var i = 0; i < liNodeList.length; i++) {
		// alert(liNodeList[i].innerHTML);
		// 判断孩子节点是否是叶子节点
		if (liNodeList[i].lastChild.nodeName == "A") {
			var id = liNodeList[i].lastChild.firstChild.id;
			var style = $("#" + id).attr(style);
			if (style == undefined || style == '') {
				// 将下标追加到数组最后
				index_data1.push(i);
			} else {
				index_device_data1.push(i)
			}

		}
		// alert($($("#"+ddddd+">ul>li")[i]).attr(id));
	}
}

var MoveTest = {
	errorMsg : "放错了...请选择正确的类别！",
	curTarget : null,
	curTmpTarget : null,
	noSel : function() {
		try {
			window.getSelection
					? window.getSelection().removeAllRanges()
					: document.selection.empty();
		} catch (e) {
		}
	},
	dragTree2Dom : function(treeId, treeNodes) {
        //debugger;
		// alert(treeNodes[0].id+"--"+treeNodes[0].pId);
		// return !treeNodes[0].isParent;
		/*
		 * if(treeNodes[0].isParent==true) { return true; }else{ return false; }
		 */
		// if(treeNodes[0].isParent ==true
		// &&treeNodes[0].pId!="00"&&treeNodes[0].id!="0"){
		// 如果是单拖设备树，支持单拖
		if (treeId == 'dandevices') {
			return true;
		}
		// 按部门分组支持树节点第一级拖动
		// treebyNormal
		if (treeId == 'treebyNormal') {
			if (treeNodes[0].isParent == true && treeNodes[0].pId == null) {
				isdeviceFlag = "0";//设置拖动节点标识，0是部门节点不是设备节点
                return true;
			}
			if (treeNodes[0].nodeType == 'device') {// 设备支持拖动
                isdeviceFlag = "1";//设置拖动节点标识，1是设备节点
				return true;
			}
		}
		// alert(treeNodes[0].isParent+"=="+treeNodes[0].pId);
		// 其他不支持单拖
		if (treeNodes[0].isParent == true && treeNodes[0].pId != null) {
			isdeviceFlag = "0";//设置拖动节点标识，0是道路节点不是设备节点
            return true;
		} else {
			if (treeNodes[0].nodeType == 'device') {// 设备支持拖动
                isdeviceFlag = "1";//设置拖动节点标识，1是设备节点
				return true;
			} else {
				return false;
			}

		}
	},
	prevTree : function(treeId, treeNodes, targetNode) {
		return !targetNode.isParent
				&& targetNode.parentTId == treeNodes[0].parentTId;
	},
	nextTree : function(treeId, treeNodes, targetNode) {
		return !targetNode.isParent
				&& targetNode.parentTId == treeNodes[0].parentTId;
	},
	innerTree : function(treeId, treeNodes, targetNode) {
		return targetNode != null && targetNode.isParent
				&& targetNode.tId == treeNodes[0].parentTId;
	},
	dropTree2Dom : function(e, treeId, treeNodes, targetNode, moveType) {
		//debugger;
        $("#_left_up").unbind('click');
		$("#_right_down").unbind('click');
		// alert(treeNodes[0].children.find("isExistDevice=0"));
		// alert(treeNodes[0].children.isExistDevice.length);
		if (screen_index == 1) {
			// 单拖设备处理
			if (treeId == "dandevices") {
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				var code = zTree.getSelectedNodes()[0].code;
				var name = zTree.getSelectedNodes()[0].name;
				// 计算出x轴差值
				var xpoint = $("#pull-left-div").width();
				window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

			} else {
				var one_deviceNo = "";
				// alert(treeNodes[0].getParentNode().id);
				// var domId = "dom_" + treeNodes[0].getParentNode().id;
				var domId = "dom_2";
				// if (moveType == null && (domId == e.target.id ||
				// $(e.target).parents("#" + domId).length > 0)) {
				if (true) {
					var zTree = $.fn.zTree.getZTreeObj(treeId);
					var ddddd = zTree.getSelectedNodes()[0].tId;
					// 节点属性是否是设备
					var nodeTypeFlag = zTree.getSelectedNodes()[0].nodeType;
					if (nodeTypeFlag == 'device') {
						var zTree = $.fn.zTree.getZTreeObj(treeId);
						var code = zTree.getSelectedNodes()[0].code;
                        if(lastNodeMoveDeviceFlag=='0')
                        {   
                            //清除当前屏的所有刷新
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
						var name = zTree.getSelectedNodes()[0].name;
						// 计算出x轴差值
						var xpoint = $("#pull-left-div").width();
						window.frames['dddddddddddddd'].xypoint(code, name, e,
								xpoint);
						$("#_right_down").attr('style', 'display:none');
						$("#_left_up").attr('style', 'display:none');

					} else {
						// 存储不是设备下标索引数组
						var index_data1 = new Array();
						// 存储是设备下标索引数组
						var index_device_data1 = new Array();

						// 原来获取拖拽节点下的叶子节点
						// var nodeList = $("#"+ddddd+" ul li
						// a").find("span:first-child");
						// 获取拖拽节点下的孩子节点
						var liNodeList = $("#" + ddddd + ">ul>li");
						for (var i = 0; i < liNodeList.length; i++) {
							// alert(liNodeList[i].innerHTML);
							// 判断孩子节点是否是叶子节点
							if (liNodeList[i].lastChild.nodeName == "A") {
								var id = liNodeList[i].lastChild.firstChild.id;
								var style = $("#" + id).attr("style");
								if (style == undefined || style == '') {
									// 将下标追加到数组最后
									index_data1.push(i);
								} else {
									index_device_data1.push(i)
								}

							}
						}
						// 确定有几个设备
						var deviceCount = index_device_data1.length;
                        // 判断当前刷新界面是否有屏在刷新，0是没有
                        if (oneScreenDevice.length != 0) {
                            window.frames['dddddddddddddd'].unlogout(oneScreenDevice[0],'',1);
                        }
                        if(lastNodeMoveDeviceFlag=='1'){
                            //清除全部所有屏
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                         }
                        lastNodeMoveDeviceFlag =isdeviceFlag;
						if (deviceCount >= 1) {
                             
							// 判断当前刷新界面是否有屏在刷新，0是没有
							if (oneScreenDevice.length != 0) {
								// 注销上一次屏幕刷新设备编号
								// window.frames['dddddddddddddd'].frames["oneScreenImg1"]
								// .init(
								// oneScreenDevice[0],
								// treeNodes[0].children[index_device_data1[0]].code,
								// treeNodes[0].children[index_device_data1[0]].name);
                                 //alert("last="+lastNodeMoveDeviceFlag+",isdevic="+isdeviceFlag);
		                       
								// 计算出x轴差值
								var xpoint = $("#pull-left-div").width();
								var code = treeNodes[0].children[index_device_data1[0]].code;
								var name = treeNodes[0].children[index_device_data1[0]].name;
								window.frames['dddddddddddddd'].xypointMany(code,
										name, e, xpoint);
								$("#_right_down").attr('style', 'display:none');
								$("#_left_up").attr('style', 'display:none');
							} else {
								// alert(treeNodes[0].children[index_device_data1[0]].name);
								// window.frames['dddddddddddddd'].frames["oneScreenImg1"]
								// .init(
								// null,
								// treeNodes[0].children[index_device_data1[0]].code,
								// treeNodes[0].children[index_device_data1[0]].name);
								// 计算出x轴差值
								var xpoint = $("#pull-left-div").width();
								var code = treeNodes[0].children[index_device_data1[0]].code;
								var name = treeNodes[0].children[index_device_data1[0]].name;
								window.frames['dddddddddddddd'].xypoint(code,
										name, e, xpoint);
							}
							one_deviceNO = treeNodes[0].children[index_device_data1[0]].code;
							oneScreenDevice[0] = treeNodes[0].children[index_device_data1[0]].code;
						}

						// window.frames['dddddddddddddd'].frames["oneScreenImg1"].init(treeNodes[0].children[0].code);
						// $("#_left_up").attr('style','display:inline');
						var pno = 1;
						$("#_left_up").click(function() {
							pno--;
							// var ptotalNum=treeNodes[0].children.length;
							var up_ptotalNum = deviceCount;
							left(e,pno, screen_index, up_ptotalNum, treeNodes,
									index_device_data1);
						});
						treeNode = treeNodes;
						if (deviceCount > 1) {
							$("#_right_down").attr('style', 'display:inline');
						}

						$("#_right_down").click(function() {
							pno++;
							// var down_ptotalNum=treeNodes[0].children.length;
							var down_ptotalNum = deviceCount;
							// alert(down_ptotalNum);
							right(e,pno, screen_index, down_ptotalNum, treeNodes,
									index_device_data1);
						});
						// MoveTest.updateType();
					}

				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
					// alert(MoveTest.errorMsg);
				}

			}
		} else if (screen_index == 2) {
			// 单拖设备处理
			if (treeId == "dandevices") {
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				var code = zTree.getSelectedNodes()[0].code;
				var name = zTree.getSelectedNodes()[0].name;
				// 计算出x轴差值
				var xpoint = $("#pull-left-div").width();
				window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

			} else {
				$('#_right_down').unbind('click', function() {
						});
				var two_deviceNO = "";
				var domId = "dom_2";
				if (true) {
					var zTree = $.fn.zTree.getZTreeObj(treeId);
					var ddddd = zTree.getSelectedNodes()[0].tId;
                    
                    // 节点属性是否是设备
                    var nodeTypeFlag = zTree.getSelectedNodes()[0].nodeType;
                    if (nodeTypeFlag == 'device') {
                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        var code = zTree.getSelectedNodes()[0].code;
                        if(lastNodeMoveDeviceFlag=='0')
                        {   
                            //清除当前屏的所有刷新
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
                        var name = zTree.getSelectedNodes()[0].name;
                        // 计算出x轴差值
                        var xpoint = $("#pull-left-div").width();
                        window.frames['dddddddddddddd'].xypoint(code, name, e,
                                xpoint);
                        $("#_right_down").attr('style', 'display:none');
                        $("#_left_up").attr('style', 'display:none');

                    }
                    else
                    {
	                            // 存储不是设备下标索引数组
	                    var index_data2 = new Array();
	                    // 存储是设备下标索引数组
	                    var index_device_data2 = new Array();
	                    // 获取拖拽节点下的孩子节点
	                    var liNodeList = $("#" + ddddd + ">ul>li");
	                    for (var i = 0; i < liNodeList.length; i++) {
	                        // alert(liNodeList[i].innerHTML);
	                        // 判断孩子节点是否是叶子节点
	                        if (liNodeList[i].lastChild.nodeName == "A") {
	                            var id = liNodeList[i].lastChild.firstChild.id;
	                            var style = $("#" + id).attr("style");
	                            if (style == undefined || style == '') {
	                                // 将下标追加到数组最后
	                                index_data2.push(i);
	                            } else {
	                                index_device_data2.push(i)
	                            }
	                        }
	                    }
	
	                    // 确定有几个设备
	                    var deviceCount = index_device_data2.length;
	
	                    // 判断当前刷新界面是否有屏在刷新，0是没有
	                    if (twoScreenDevice.length != 0) {
	
	                        $("#_right_down").attr('style', 'display:none');
	                        $("#_left_up").attr('style', 'display:none');
	                        if (twoScreenDevice.length == 1) {
                                 window.frames['dddddddddddddd'].unlogout(twoScreenDevice[0],'',1);
	                        } else if (twoScreenDevice.length == 2) {
	                            // 注销上一次屏幕刷新设备编号
                                window.frames['dddddddddddddd'].unlogout(twoScreenDevice[0],'',1);
                                window.frames['dddddddddddddd'].unlogout(twoScreenDevice[1],'',2);
	                        } else {
	                        }
	                    }
                        if(lastNodeMoveDeviceFlag=='1'){
                            //清除全部所有屏
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag =isdeviceFlag;
                        
	                    // 替换下面代码
	                    if (deviceCount >= 2) {
	                        var code = "";
                            var name = "";
	                        for (var i = 0; i < 2; i++) {
	                            if (i == 1) {
	                                two_deviceNO += treeNodes[0].children[index_device_data2[i]].code;
	                                 // 计算出x轴差值
		                            code += treeNodes[0].children[index_device_data2[i]].code;
                                    name += treeNodes[0].children[index_device_data2[i]].name;
		                              
	                            } else {
	                                two_deviceNO += treeNodes[0].children[index_device_data2[i]].code
	                                        + ",";
	                                code += treeNodes[0].children[index_device_data2[i]].code
	                                        + ",";
	                                name += treeNodes[0].children[index_device_data2[i]].name
	                                        + ",";
	                            }
	                            twoScreenDevice[i] = treeNodes[0].children[index_device_data2[i]].code;
                                
	                        }
	                        var xpoint = $("#pull-left-div").width();
                            window.frames['dddddddddddddd'].xypointMany(code, name, e,
                                        xpoint);    
	                    } else {
	                        if (deviceCount == 1) {
	                            two_deviceNO = treeNodes[0].children[index_device_data2[0]].code;
	                            var code = treeNodes[0].children[index_device_data2[0]].code;
	                            var name = treeNodes[0].children[index_device_data2[0]].name;
	                            twoScreenDevice[0] = treeNodes[0].children[index_device_data2[0]].code
                                window.frames['dddddddddddddd'].xypointMany(code, name, e,
                                            xpoint);
	                        }
	                    }
	                    var pno = 1;
	                    $("#_left_up").click(function() {
	                        pno--;
	                        var up_ptotalNum = deviceCount;
	                        left(e,pno, screen_index, up_ptotalNum, treeNodes,
	                                index_device_data2);
	                    });
	                    treeNode = treeNodes;
	
	                    if (deviceCount > 2) {
	                        $("#_right_down").attr('style', 'display:block');
	                    }
	
	                    $("#_right_down").click(function() {
	                        pno++;
	                        var down_ptotalNum = deviceCount;
	                        right(e,pno, screen_index, down_ptotalNum, treeNodes,
	                                index_device_data2);
	
	                    });
	                        
                    }
                    
				
				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
					// alert(MoveTest.errorMsg);
				}
			}
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
		} else if (screen_index == 4) { // 单拖设备处理
			if (treeId == "dandevices") {
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				var code = zTree.getSelectedNodes()[0].code;
				var name = zTree.getSelectedNodes()[0].name;
				var odiv1 = window.frames['nopage_iframe'].document
						.getElementById("div1")
				var odiv2 = window.frames['nopage_iframe'].document
						.getElementById("div2")
				var odiv3 = window.frames['nopage_iframe'].document
						.getElementById("div3")
				var odiv4 = window.frames['nopage_iframe'].document
						.getElementById("div4")
				// 左上角x\y坐标
				odiv1_x = odiv1.getBoundingClientRect().left;
				odiv1_y = odiv1.getBoundingClientRect().top;
				// 右下角x,y坐标
				odiv1_x1 = odiv1.getBoundingClientRect().right;
				odiv1_y1 = odiv1.getBoundingClientRect().bottom;
				// 左上角x\y坐标
				odiv2_x = odiv2.getBoundingClientRect().left;
				odiv2_y = odiv2.getBoundingClientRect().top;
				// 右下角x,y坐标
				odiv2_x1 = odiv2.getBoundingClientRect().right;
				odiv2_y1 = odiv2.getBoundingClientRect().bottom;
				// 左上角x\y坐标
				odiv3_x = odiv3.getBoundingClientRect().left;
				odiv3_y = odiv3.getBoundingClientRect().top;
				// 右下角x,y坐标
				odiv3_x1 = odiv3.getBoundingClientRect().right;
				odiv3_y1 = odiv3.getBoundingClientRect().bottom;
				// 左上角x\y坐标
				odiv4_x = odiv4.getBoundingClientRect().left;
				odiv4_y = odiv4.getBoundingClientRect().top;
				// 右下角x,y坐标
				odiv4_x1 = odiv4.getBoundingClientRect().right;
				odiv4_y1 = odiv4.getBoundingClientRect().bottom;
				// 计算出x轴差值
				var xpoint = $("#pull-left-div").width();
				window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

			} else {
				// 其他处理
				var four_deviceNO = "";
				var domId = "dom_2";
				if (true) {
					var zTree = $.fn.zTree.getZTreeObj(treeId);
					// zTree.removeNode(treeNodes[0]);
					var ddddd = zTree.getSelectedNodes()[0].tId;
                    // 节点属性是否是设备
                    var nodeTypeFlag = zTree.getSelectedNodes()[0].nodeType;
                    if (nodeTypeFlag == 'device') {
                        
                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        var code = zTree.getSelectedNodes()[0].code;
                        if(lastNodeMoveDeviceFlag=='0')
                        {   
                            //清除当前屏的所有刷新
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
                        var name = zTree.getSelectedNodes()[0].name;
                        // 计算出x轴差值
                        var xpoint = $("#pull-left-div").width();
                        window.frames['dddddddddddddd'].xypoint(code, name, e,
                                xpoint);
                        $("#_right_down").attr('style', 'display:none');
                        $("#_left_up").attr('style', 'display:none');
                        //alert("last3="+lastNodeMoveDeviceFlag+",isdevic3="+isdeviceFlag)
                    }
                    else
                    {
                        // 存储不是设备下标索引数组
	                    var index_data4 = new Array();
	                    // 存储是设备下标索引数组
	                    var index_device_data4 = new Array();
	                    // 获取拖拽节点下的孩子节点
	                    var liNodeList = $("#" + ddddd + ">ul>li");
	                    for (var i = 0; i < liNodeList.length; i++) {
	                        // alert(liNodeList[i].innerHTML);
	                        // 判断孩子节点是否是叶子节点
	                        if (liNodeList[i].lastChild.nodeName == "A") {
	                            var id = liNodeList[i].lastChild.firstChild.id;
	                            var style = $("#" + id).attr("style");
	                            if (style == undefined || style == '') {
	                                // 将下标追加到数组最后
	                                index_data4.push(i);
	                            } else {
	                                index_device_data4.push(i)
	                            }
	
	                        }
	                    }
	
	                    // 确定有几个设备
	                    var deviceCount = index_device_data4.length;
	
	                    // 判断当前刷新界面是否有屏在刷新，0是没有
	                    if (fourScreenDevice.length != 0) {
	                        $("#_right_down").attr('style', 'display:none');
	                        $("#_left_up").attr('style', 'display:none');
	                        for (var i = 0; i < fourScreenDevice.length; i++) {
                                window.frames['dddddddddddddd'].unlogout(fourScreenDevice[i],'',i+1);
	                        }
	                    }
	                    if(lastNodeMoveDeviceFlag=='1'){
                            //清除全部所有屏
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag =isdeviceFlag;
	                    // 替换下面代码
	                    if (deviceCount >= 4) {
                            var code = "";
                            var name = "";
	                        for (var i = 0; i < 4; i++) {
//	
	                            if (i == 3) {
	                                four_deviceNO += treeNodes[0].children[index_device_data4[i]].code;
	                                code += treeNodes[0].children[index_device_data4[i]].code;
	                                name += treeNodes[0].children[index_device_data4[i]].name;
	                            } else {
	                                four_deviceNO += treeNodes[0].children[index_device_data4[i]].code
	                                        + ",";
	                                code += treeNodes[0].children[index_device_data4[i]].code
	                                        + ",";
	                                name += treeNodes[0].children[index_device_data4[i]].name
	                                        + ",";
	                            }
	                            fourScreenDevice[i] = treeNodes[0].children[index_device_data4[i]].code;
	                        }
	                        var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
	                    } else {
	                        if (deviceCount == 1) {
                                
	                            four_deviceNO = treeNodes[0].children[index_device_data4[0]].code;
	                            fourScreenDevice[0] = treeNodes[0].children[index_device_data4[0]].code;
                                var code = treeNodes[0].children[index_device_data4[0]].code;
                                var name = treeNodes[0].children[index_device_data4[0]].name;
                                var xpoint = $("#pull-left-div").width();        
                                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
	                        }
	                        if (deviceCount == 2) {
	                            four_deviceNO = treeNodes[0].children[index_device_data4[0]].code
	                                    + ","
	                                    + treeNodes[0].children[index_device_data4[1]].code;
	                            var code = treeNodes[0].children[index_device_data4[0]].code
	                                    + ","
	                                    + treeNodes[0].children[index_device_data4[1]].code;
	                            var name = treeNodes[0].children[index_device_data4[0]].name
	                                    + ","
	                                    + treeNodes[0].children[index_device_data4[1]].name;
	                            fourScreenDevice[0] = treeNodes[0].children[index_device_data4[0]].code;
	                            fourScreenDevice[1] = treeNodes[0].children[index_device_data4[1]].code;
                                var xpoint = $("#pull-left-div").width();        
                                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
	                        }
	                        if (deviceCount == 3) {
                                var code = "";
                                var name = "";
	                            for (var i = 0; i < index_device_data4.length; i++) {
	                                
	                                if (i == 2) {
	                                    four_deviceNO += treeNodes[0].children[index_device_data4[i]].code;
	                                    code += treeNodes[0].children[index_device_data4[i]].code;
	                                    name += treeNodes[0].children[index_device_data4[i]].name;
	                                }else{
                                        four_deviceNO += treeNodes[0].children[index_device_data4[i]].code
                                            + ",";
	                                    code += treeNodes[0].children[index_device_data4[i]].code
	                                            + ",";
	                                    name += treeNodes[0].children[index_device_data4[i]].name
	                                            + ",";
                                    }
	                                fourScreenDevice[i] = treeNodes[0].children[index_device_data4[i]].code;
	                            }
                                var xpoint = $("#pull-left-div").width();        
                                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
	                        }
	                    }
	
	                    var pno = 1;
	                    $("#_left_up").click(function() {
	                        pno--;
	                        var up_ptotalNum = deviceCount;
	                        left(e,pno, screen_index, up_ptotalNum, treeNodes,
	                                index_device_data4);
	                    });
	                    treeNode = treeNodes;
	                    if (deviceCount > 4) {
	                        $("#_right_down").attr('style', 'display:inline');
	                    }
	
	                    $("#_right_down").click(function() {
	
	                        pno++;
	                        var down_ptotalNum = deviceCount;
	                        right(e,pno, screen_index, down_ptotalNum, treeNodes,
	                                index_device_data4);
	                    });
                    }
					
				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
					// alert(MoveTest.errorMsg);
				}
			}
			// alert(four_deviceNO);
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
		} else if (screen_index == 6) { // 单拖设备处理
			if (treeId == "dandevices") {
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				var code = zTree.getSelectedNodes()[0].code;
				var name = zTree.getSelectedNodes()[0].name;
				// 计算出x轴差值
				var xpoint = $("#pull-left-div").width();
				window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

			} else {
				var six_deviceNO = '';
				// alert(treeNodes[0].getParentNode().id);
				// var domId = "dom_" + treeNodes[0].getParentNode().id;
				var domId = "dom_2";
				// if (moveType == null && (domId == e.target.id ||
				// $(e.target).parents("#" + domId).length > 0)) {
				if (true) {
					var zTree = $.fn.zTree.getZTreeObj(treeId);
					// zTree.removeNode(treeNodes[0]);
					var ddddd = zTree.getSelectedNodes()[0].tId;
                     // 节点属性是否是设备
                    var nodeTypeFlag = zTree.getSelectedNodes()[0].nodeType;
                    if (nodeTypeFlag == 'device') {
                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        var code = zTree.getSelectedNodes()[0].code;
                        if(lastNodeMoveDeviceFlag=='0')
                        {   
                            //清除当前屏的所有刷新
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
                        var name = zTree.getSelectedNodes()[0].name;
                        // 计算出x轴差值
                        var xpoint = $("#pull-left-div").width();
                        window.frames['dddddddddddddd'].xypoint(code, name, e,
                                xpoint);
                        $("#_right_down").attr('style', 'display:none');
                        $("#_left_up").attr('style', 'display:none');

                    }
                    else
	                {
	                    // 存储不是设备下标索引数组
	                    var index_data6 = new Array();
	                    // 存储是设备下标索引数组
	                    var index_device_data6 = new Array();
	                    // 获取拖拽节点下的孩子节点
	                    var liNodeList = $("#" + ddddd + ">ul>li");
	                    for (var i = 0; i < liNodeList.length; i++) {
	                        // alert(liNodeList[i].innerHTML);
	                        // 判断孩子节点是否是叶子节点
	                        if (liNodeList[i].lastChild.nodeName == "A") {
	                            var id = liNodeList[i].lastChild.firstChild.id;
	                            var style = $("#" + id).attr("style");
	                            if (style == undefined || style == '') {
	                                // 将下标追加到数组最后
	                                index_data6.push(i);
	                            } else {
	                                index_device_data6.push(i)
	                            }
	
	                        }
	                    }
	                    // 确定有几个设备
	                    var deviceCount = index_device_data6.length;
	                    // zTree.removeNode(treeNodes[0]);
	
	                    // 判断当前刷新界面是否有屏在刷新，0是没有
	                    if (sixScreenDevice.length != 0) {
	                        $("#_right_down").attr('style', 'display:none');
	                        $("#_left_up").attr('style', 'display:none');
	                        for (var i = 0; i < sixScreenDevice.length; i++) {
	                            window.frames['dddddddddddddd'].unlogout(sixScreenDevice[i],'',i+1);
	                        }
	                    }
                        if(lastNodeMoveDeviceFlag=='1'){
                            //清除全部所有屏
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag =isdeviceFlag;   
	                    // 替换下面代码
	                    if (deviceCount >= 6) {
                            var code = "";
                            var name = "";
	                        for (var i = 0; i < 6; i++) {
	                            if (i == 5) {
	                                six_deviceNO += treeNodes[0].children[index_device_data6[i]].code;
	                                code += treeNodes[0].children[index_device_data6[i]].code;
	                                name += treeNodes[0].children[index_device_data6[i]].name;
	                            } else {
	                                six_deviceNO += treeNodes[0].children[index_device_data6[i]].code
	                                        + ",";
	                                code += treeNodes[0].children[index_device_data6[i]].code
	                                        + ",";
	                                name += treeNodes[0].children[index_device_data6[i]].name
	                                        + ",";
	                            }
	                            sixScreenDevice[i] = treeNodes[0].children[index_device_data6[i]].code;
	                        }
                            var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
	                    } else {
	                        if (deviceCount == 1) {
	                            six_deviceNO = treeNodes[0].children[index_device_data6[0]].code;
	                            var code = treeNodes[0].children[index_device_data6[0]].code;
	                            var name = treeNodes[0].children[index_device_data6[0]].name;
	                            sixScreenDevice[0] = treeNodes[0].children[index_device_data6[0]].code;
                                var xpoint = $("#pull-left-div").width();        
                                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
	                        }
	                        if (deviceCount == 2) {
	                            six_deviceNO = treeNodes[0].children[index_device_data6[0]].code
	                                    + ","
	                                    + treeNodes[0].children[index_device_data6[1]].code;
	                            var code = treeNodes[0].children[index_device_data6[0]].code
	                                    + ","
	                                    + treeNodes[0].children[index_device_data6[1]].code;
	                            var name = treeNodes[0].children[index_device_data6[0]].name
	                                    + ","
	                                    + treeNodes[0].children[index_device_data6[1]].name;
	                            sixScreenDevice[0] = treeNodes[0].children[index_device_data6[0]].code;
	                            sixScreenDevice[1] = treeNodes[0].children[index_device_data6[0]].code;
                                var xpoint = $("#pull-left-div").width();        
                                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
	                        }
	                        if (deviceCount == 3) {
                                var code = "";
                                var name = "";
	                            for (var i = 0; i < index_device_data6.length; i++) {
	                                if (i == 2) {
	                                    six_deviceNO += treeNodes[0].children[index_device_data6[i]].code;
	                                    code += treeNodes[0].children[index_device_data6[i]].code;
	                                    name += treeNodes[0].children[index_device_data6[i]].name;
	                                } else {
	                                    six_deviceNO += treeNodes[0].children[index_device_data6[i]].code
	                                            + ",";
	                                    code += treeNodes[0].children[index_device_data6[i]].code
	                                            + ",";
	                                    name += treeNodes[0].children[index_device_data6[i]].name
	                                            + ",";
	                                }
	                                sixScreenDevice[i] = treeNodes[0].children[index_device_data6[i]].code;
	                            }
	                            var xpoint = $("#pull-left-div").width();        
                                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
	                        }
	                        if (deviceCount == 4) {
                                var code = ""
                                var name = "";
	                            for (var i = 0; i < index_device_data6.length; i++) {
	                                if (i == 3) {
	                                    six_deviceNO += treeNodes[0].children[index_device_data6[i]].code;
	                                    code += treeNodes[0].children[index_device_data6[i]].code;
	                                    name += treeNodes[0].children[index_device_data6[i]].name;
	                                } else {
	                                    six_deviceNO += treeNodes[0].children[index_device_data6[i]].code
	                                            + ",";
	                                    code += treeNodes[0].children[index_device_data6[i]].code
	                                            + ",";
	                                    name += treeNodes[0].children[index_device_data6[i]].name
	                                            + ",";
	                                }
	                                sixScreenDevice[i] = treeNodes[0].children[index_device_data6[i]].code;
	                            }
                                var xpoint = $("#pull-left-div").width();        
                                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
	
	                        }
	                        if (deviceCount == 5) {
                                var code = "";
                                var name = "";
	                            for (var i = 0; i < index_device_data6.length; i++) {
	                                if (i == 4) {
	                                    six_deviceNO += treeNodes[0].children[index_device_data6[i]].code;
	                                    code += treeNodes[0].children[index_device_data6[i]].code;
	                                    name += treeNodes[0].children[index_device_data6[i]].name;
	                                } else {
	                                    six_deviceNO += treeNodes[0].children[index_device_data6[i]].code
	                                            + ",";
	                                    code += treeNodes[0].children[index_device_data6[i]].code
	                                            + ",";
	                                    name += treeNodes[0].children[index_device_data6[i]].name
	                                            + ",";
	                                }
	                                sixScreenDevice[i] = treeNodes[0].children[index_device_data6[i]].code;
	                            }
                                var xpoint = $("#pull-left-div").width();        
                                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
	
	                        }
	                    }
	
	                    var pno = 1;
	                    $("#_left_up").click(function() {
	                        pno--;
	                        var up_ptotalNum = deviceCount;
	                        left(e,pno, screen_index, up_ptotalNum, treeNodes,
	                                index_device_data6);
	                    });
	                    treeNode = treeNodes;
	                    if (deviceCount > 6) {
	                        $("#_right_down").attr('style', 'display:inline');
	                    }
	
	                    $("#_right_down").click(function() {
	                        pno++;
	                        var show_ptotalNum = deviceCount;
	                        right(e,pno, screen_index, show_ptotalNum, treeNodes,
	                                index_device_data6);
	                    });
	                    // MoveTest.updateType();
	                 }
				
				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
					// alert(MoveTest.errorMsg);
				}
			}
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
		} else if (screen_index == 9) {
			// 单拖设备处理
			if (treeId == "dandevices") {
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				var code = zTree.getSelectedNodes()[0].code;
				var name = zTree.getSelectedNodes()[0].name;
				// 计算出x轴差值
				var xpoint = $("#pull-left-div").width();
				window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

			} else {
				var nine_deviceNO = "";
				// alert(treeNodes[0].getParentNode().id);
				// var domId = "dom_" + treeNodes[0].getParentNode().id;
				var domId = "dom_2";
				// if (moveType == null && (domId == e.target.id ||
				// $(e.target).parents("#" + domId).length > 0)) {
				if (true) {
					var zTree = $.fn.zTree.getZTreeObj(treeId);
					var ddddd = zTree.getSelectedNodes()[0].tId;
                     // 节点属性是否是设备
                    var nodeTypeFlag = zTree.getSelectedNodes()[0].nodeType;
                    if (nodeTypeFlag == 'device') {
                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        var code = zTree.getSelectedNodes()[0].code;
                        if(lastNodeMoveDeviceFlag=='0')
                        {   
                            //清除当前屏的所有刷新
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag = isdeviceFlag;
                        var name = zTree.getSelectedNodes()[0].name;
                        // 计算出x轴差值
                        var xpoint = $("#pull-left-div").width();
                        window.frames['dddddddddddddd'].xypoint(code, name, e,
                                xpoint);
                        $("#_right_down").attr('style', 'display:none');
                        $("#_left_up").attr('style', 'display:none');

                    }
                    else
                    {
                        // 存储不是设备下标索引数组
                    var index_data9 = new Array();
                    // 存储是设备下标索引数组
                    var index_device_data9 = new Array();

                    // 获取拖拽节点下的孩子节点
                    var liNodeList = $("#" + ddddd + ">ul>li");
                    for (var i = 0; i < liNodeList.length; i++) {
                        // alert(liNodeList[i].innerHTML);
                        // 判断孩子节点是否是叶子节点
                        if (liNodeList[i].lastChild.nodeName == "A") {
                            var id = liNodeList[i].lastChild.firstChild.id;
                            var style = $("#" + id).attr("style");
                            if (style == undefined || style == '') {
                                // 将下标追加到数组最后
                                index_data9.push(i);
                            } else {
                                index_device_data9.push(i)
                            }

                        }
                    }

                    // 确定有几个设备
                    var deviceCount = index_device_data9.length;
                    // zTree.removeNode(treeNodes[0]);

                    // 判断当前刷新界面是否有屏在刷新，0是没有
                    if (nineScreenDevice.length != 0) {

                        $("#_right_down").attr('style', 'display:none');
                        $("#_left_up").attr('style', 'display:none');
                        for (var i = 0; i < nineScreenDevice.length; i++) {
                            window.frames['dddddddddddddd'].unlogout(nineScreenDevice[i],'',i+1);
                        }
                    }
                        if(lastNodeMoveDeviceFlag=='1'){
                            //清除全部所有屏
                            window.frames['dddddddddddddd'].cleanAllSrceen();//清除当前刷新页的所有刷新屏幕
                        }
                        lastNodeMoveDeviceFlag =isdeviceFlag;
                    // 替换下面代码
                    if (deviceCount >= 9) {
                        var code = "";
                        var name = "";
                        for (var i = 0; i < 9; i++) {

                            if (i == 8) {
                                nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code;
                                code += treeNodes[0].children[index_device_data9[i]].code;
                                name += treeNodes[0].children[index_device_data9[i]].name;
                            } else {
                                nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code
                                        + ",";
                                code += treeNodes[0].children[index_device_data9[i]].code
                                        + ",";
                                name += treeNodes[0].children[index_device_data9[i]].name
                                        + ",";
                            }

                        }
                        for (var i = 0; i < 9; i++) {
                            nineScreenDevice[i] = treeNodes[0].children[index_device_data9[i]].code;
                        }
                        var xpoint = $("#pull-left-div").width();        
                        window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
                    } else {
                        if (deviceCount == 1) {
                            nine_deviceNO = treeNodes[0].children[index_device_data9[0]].code;
                            var code = treeNodes[0].children[index_device_data9[0]].code;
                            var name = treeNodes[0].children[index_device_data9[0]].name;
                            nineScreenDevice[0] = treeNodes[0].children[index_device_data9[0]].code;
                            var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
                        }
                        if (deviceCount == 2) {
                            nine_deviceNO = treeNodes[0].children[index_device_data9[0]].code
                                    + ","
                                    + treeNodes[0].children[index_device_data9[1]].code;
                            code = treeNodes[0].children[index_device_data9[0]].code
                                    + ","
                                    + treeNodes[0].children[index_device_data9[1]].code;
                            name = treeNodes[0].children[index_device_data9[0]].name
                                    + ","
                                    + treeNodes[0].children[index_device_data9[1]].name;
                            nineScreenDevice[0] = treeNodes[0].children[index_device_data9[0]].code;
                            nineScreenDevice[1] = treeNodes[0].children[index_device_data9[1]].code;
                            var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
                        }
                        if (deviceCount == 3) {
                            var code = "";
                            var name = "";
                            for (var i = 0; i < index_device_data9.length; i++) {
                                if (i == 2) {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code;
                                    code += treeNodes[0].children[index_device_data9[i]].code;
                                    name += treeNodes[0].children[index_device_data9[i]].name;
                                } else {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    code += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    name += treeNodes[0].children[index_device_data9[i]].name
                                            + ",";
                                }
                                nineScreenDevice[i] = treeNodes[0].children[index_device_data9[i]].code;
                            }
                            var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
                        }
                        if (deviceCount == 4) {
                            var code ="";
                            var name ="";
                            for (var i = 0; i < index_device_data9.length; i++) {
                                if (i == 3) {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code;
                                    code += treeNodes[0].children[index_device_data9[i]].code;
                                    name += treeNodes[0].children[index_device_data9[i]].name;
                                } else {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    code += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    name += treeNodes[0].children[index_device_data9[i]].name
                                            + ",";
                                }
                                nineScreenDevice[i] = treeNodes[0].children[index_device_data9[i]].code;
                            }
                            var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   

                        }
                        if (deviceCount == 5) {
                            var code ="";
                            var name ="";
                            for (var i = 0; i < index_device_data9.length; i++) {
                                if (i == 4) {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code;
                                    code += treeNodes[0].children[index_device_data9[i]].code;
                                    name += treeNodes[0].children[index_device_data9[i]].name;
                                } else {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    code += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    name += treeNodes[0].children[index_device_data9[i]].name
                                            + ",";
                                }
                                nineScreenDevice[i] = treeNodes[0].children[index_device_data9[i]].code;
                            }
                            var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   

                        }
                        if (deviceCount == 6) {
                            var code ="";
                            var name ="";
                            for (var i = 0; i < index_device_data9.length; i++) {
                                if (i == 5) {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code;
                                    code += treeNodes[0].children[index_device_data9[i]].code;
                                    name += treeNodes[0].children[index_device_data9[i]].name;
                                } else {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    code += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    name += treeNodes[0].children[index_device_data9[i]].name
                                            + ",";
                                }
                                nineScreenDevice[i] = treeNodes[0].children[index_device_data9[i]].code;
                            }
                            var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
                        }
                        if (deviceCount == 7) {
                            var code ="";
                            var name = "";
                            for (var i = 0; i < index_device_data9.length; i++) {
                                if (i == 6) {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code;
                                    code += treeNodes[0].children[index_device_data9[i]].code;
                                    name += treeNodes[0].children[index_device_data9[i]].name;
                                } else {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    code += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    name += treeNodes[0].children[index_device_data9[i]].name
                                            + ",";
                                }
                                nineScreenDevice[i] = treeNodes[0].children[index_device_data9[i]].code;
                            }
                            var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   
                        }
                        if (deviceCount == 8) {
                            var code ="";
                            var name ="";
                            for (var i = 0; i < index_device_data9.length; i++) {
                                if (i == 7) {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code;
                                    code += treeNodes[0].children[index_device_data9[i]].code;
                                    name += treeNodes[0].children[index_device_data9[i]].name;
                                } else {
                                    nine_deviceNO += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    code += treeNodes[0].children[index_device_data9[i]].code
                                            + ",";
                                    name += treeNodes[0].children[index_device_data9[i]].name
                                            + ",";
                                }
                                nineScreenDevice[i] = treeNodes[0].children[index_device_data9[i]].code;
                            }
                            var xpoint = $("#pull-left-div").width();        
                            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);   

                        }
                    }
                    // $("#_left_up").attr('style','display:inline');
                    var pno = 1;
                    $("#_left_up").click(function() {
                        pno--;
                        // var ptotalNum=treeNodes[0].children.length;
                        var up_ptotalNum = deviceCount;
                        left(e,pno, screen_index, up_ptotalNum, treeNodes,
                                index_device_data9);
                    });
                    treeNode = treeNodes;
                    if (deviceCount > 9) {
                        $("#_right_down").attr('style', 'display:inline');
                    }
                    $("#_right_down").click(function() {
                        pno++;
                        // var ptotalNum=treeNodes[0].children.length;
                        var down_ptotalNum = deviceCount;
                        right(e,pno, screen_index, down_ptotalNum, treeNodes,
                                index_device_data9);
                    });
                    // MoveTest.updateType();
                    }
					
				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
					// alert(MoveTest.errorMsg);
				}
			}
			// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
		}

	},
	dom2Tree : function(e, treeId, treeNode) {
		var target = MoveTest.curTarget, tmpTarget = MoveTest.curTmpTarget;
		if (!target)
			return;
		var zTree = $.fn.zTree.getZTreeObj(treeId), parentNode;
		if (treeNode != null && treeNode.isParent
				&& "dom_" + treeNode.id == target.parent().attr("id")) {
			parentNode = treeNode;
		} else if (treeNode != null
				&& !treeNode.isParent
				&& "dom_" + treeNode.getParentNode().id == target.parent()
						.attr("id")) {
			parentNode = treeNode.getParentNode();
		}

		if (tmpTarget)
			tmpTarget.remove();
		if (!!parentNode) {
			var nodes = zTree.addNodes(parentNode, {
						id : target.attr("domId"),
						name : target.text()
					});
			zTree.selectNode(nodes[0]);
		} else {
			target.removeClass("domBtn_Disabled");
			target.addClass("domBtn");
			// alert(MoveTest.errorMsg);
		}
		MoveTest.updateType();
		MoveTest.curTarget = null;
		MoveTest.curTmpTarget = null;
	},
	updateType : function() {
		// var zTree = $.fn.zTree.getZTreeObj("treebyNormal"),
		nodes = zTree.getNodes();
		for (var i = 0, l = nodes.length; i < l; i++) {
			var num = nodes[i].children ? nodes[i].children.length : 0;
			nodes[i].name = nodes[i].name.replace(/ \(.*\)/gi, "") + " (" + num
					+ ")";
			zTree.updateNode(nodes[i]);
		}
	},
	bindDom : function() {
		// alert("bindDom");
		$(".domBtnDiv").bind("mousedown", MoveTest.bindMouseDown);
	},
	bindMouseDown : function(e) {
		// alert("bindMouseDown");
		var target = e.target;
		if (target != null && target.className == "domBtn") {
			var doc = $(document), target = $(target), docScrollTop = doc
					.scrollTop(), docScrollLeft = doc.scrollLeft();
			target.addClass("domBtn_Disabled");
			target.removeClass("domBtn");
			curDom = $("<span class='dom_tmp domBtn'>" + target.text()
					+ "</span>");
			curDom.appendTo("body");

			curDom.css({
						"top" : (e.clientY + docScrollTop + 3) + "px",
						"left" : (e.clientX + docScrollLeft + 3) + "px"
					});
			MoveTest.curTarget = target;
			MoveTest.curTmpTarget = curDom;

			doc.bind("mousemove", MoveTest.bindMouseMove);
			doc.bind("mouseup", MoveTest.bindMouseUp);
			doc.bind("selectstart", MoveTest.docSelect);
		}
		if (e.preventDefault) {
			e.preventDefault();
		}
	},
	bindMouseMove : function(e) {
		// alert("bindMouseMove");
		MoveTest.noSel();
		var doc = $(document), docScrollTop = doc.scrollTop(),
		// alert(docScrollTop);
		docScrollLeft = doc.scrollLeft(),
		// alert(docScrollLeft);
		tmpTarget = MoveTest.curTmpTarget;
		if (tmpTarget) {
			tmpTarget.css({
						"top" : (e.clientY + docScrollTop + 3) + "px",
						"left" : (e.clientX + docScrollLeft + 3) + "px"
					});
		}
		return false;
	},
	bindMouseUp : function(e) {
		// alert("bindMouseUp");
		var doc = $(document);
		doc.unbind("mousemove", MoveTest.bindMouseMove);
		doc.unbind("mouseup", MoveTest.bindMouseUp);
		doc.unbind("selectstart", MoveTest.docSelect);

		var target = MoveTest.curTarget, tmpTarget = MoveTest.curTmpTarget;
		if (tmpTarget)
			tmpTarget.remove();

		if ($(e.target).parents("#" + treeId).length == 0) {
			if (target) {
				target.removeClass("domBtn_Disabled");
				target.addClass("domBtn");
			}
			MoveTest.curTarget = null;
			MoveTest.curTmpTarget = null;
		}
	},
	bindSelect : function() {
		return false;
	}
};

function getAbsoluteHeight(ob) {
	return ob.offsetHeight
}
function getAbsoluteWidth(ob) {
	return ob.offsetWidth
}
function getAbsoluteLeft(ob) {
	var mendingLeft = ob.offsetLeft;
	while (ob != null && ob.offsetParent != null
			&& ob.offsetParent.tagName != "BODY") {
		mendingLeft += ob.offsetParent.offsetLeft;
		ob = ob.offsetParent;
	}
	return mendingLeft;
}
function getAbsoluteTop(ob) {
	var mendingTop = ob.offsetTop;
	while (ob != null && ob.offsetParent != null
			&& ob.offsetParent.tagName != "BODY") {
		mendingTop += ob.offsetParent.offsetTop;
		ob = ob.offsetParent;
	}
	return mendingTop;
}

var deviceTreeByRoad, deviceTreebyNormal, deviceTreeByGroup;

// 按照部门 设备树 设置
var byNormalSetting = {
	check : {
		enable : false
	},
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onCheck : onCheck,
		beforeDrag : MoveTest.dragTree2Dom,
		onDrop : MoveTest.dropTree2Dom,
		onMouseUp : MoveTest.dom2Tree
	},
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false,
		drag : {
			// prev: MoveTest.prevTree,
			// next: MoveTest.nextTree,
			// inner: MoveTest.innerTree
			prev : function() {
				return false;
			},
			next : function() {
				return false;
			},
			inner : function() {
				return false;
			}

		}
	}
};

// 按照道路 设备树 设置
var byRoadSetting = {
	check : {
		enable : false
	},
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onCheck : onCheck,
		beforeDrag : MoveTest.dragTree2Dom,
		onDrop : MoveTest.dropTree2Dom,
		onMouseUp : MoveTest.dom2Tree
	},
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false,
		drag : {
			// prev: MoveTest.prevTree,
			// next: MoveTest.nextTree,
			// inner: MoveTest.innerTree
			prev : function() {
				return false;
			},
			next : function() {
				return false;
			},
			inner : function() {
				return false;
			}

		}
	}
};

// 按自定义分组 设备树 设置
var byGroupSetting = {
	check : {
		enable : false
	},
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onCheck : onCheck,
		beforeDrag : MoveTest.dragTree2Dom,
		onDrop : MoveTest.dropTree2Dom,
		onMouseUp : MoveTest.dom2Tree
	},
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false,
		drag : {
			// prev: MoveTest.prevTree,
			// next: MoveTest.nextTree,
			// inner: MoveTest.innerTree
			prev : function() {
				return false;
			},
			next : function() {
				return false;
			},
			inner : function() {
				return false;
			}

		}
	}
};
// 单拖设备树
dandevicesByGroupSetting = {
	check : {
		enable : false
	},
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onCheck : onCheck,
		beforeDrag : MoveTest.dragTree2Dom,
		onDrop : MoveTest.dropTree2Dom,
		onMouseUp : MoveTest.dom2Tree
	},
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false,
		drag : {
			// prev: MoveTest.prevTree,
			// next: MoveTest.nextTree,
			// inner: MoveTest.innerTree
			prev : function() {
				return false;
			},
			next : function() {
				return false;
			},
			inner : function() {
				return false;
			}

		}
	}
};
// 按照道路 设备树 节点点击选择
function beforeClick(treeId, treeNode) {
	alert("该节点不允许点击,请拖拽");
}

// 按照道路 设备树
function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree-org"), nodes = zTree
			.getCheckedNodes(true), v = "";
	var h = "";
	for (var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
		h += nodes[i].id + ",";
	}
}

function ajaxJMS(url) {
	// var url='${root}/cross/monitore/startJsp';
	$.ajax({
				type : 'get',
				url : url,
				dataType : "json",
				success : function(msg) {
					if (msg.result == "ok") {
						// showMessage("删除成功");
						// setTimeout("document.location.reload()",1200);
					} else {
						// showMessage("删除失败");
					}
				}
			});
}

function sc() {
	var e = document.getElementById("accordion2")
	e.scrollTop = e.scrollHeight;
}

function aaa(index) {
	screen_index = index;
	// 获取单拖设备列表
	var dandevices = document.getElementById("dandevices");
	if (dandevices.style.display == "none") {
		gotoScreen(screen_index);
		$("#_left_up").attr('style', 'display:none');
		$("#_right_down").attr('style', 'display:none');
		$("#_left_up").unbind('click');
		$("#_right_down").unbind('click');
	} else {
		$("#nopage_iframe").attr("src",root + "/cross/monitore/mainindex/" + meunid+ "/?screen_index=" + screen_index);
	}
}
/**
 * 上一页函数 pno--页数 psize--每页显示记录数 ptotalNum--分页总数 treeNodes拖拽的节点
 */
function left(e,pno, psize, ptotalNum, treeNodes, index_device_data) {
	var num = ptotalNum;
	var totalPage = 0;// 总页数
	var pageSize = psize;// 每页显示行数
	totalPage = parseInt((num - 1) / pageSize) + 1;
	if (pno >= totalPage) {
		$("#_right_down").attr('style', 'display:none');
	} else {
		$("#_right_down").attr('style', 'display:block');
	}
	if (pno <= 1) {
		$("#_left_up").attr('style', 'display:none');
	}
	var currentPage = pno - 1;// 当前页数
	var startRow = (pno - 1) * pageSize + 1;// 开始显示的行
	var endRow = pno * pageSize;// 结束显示的行
	endRow = (endRow > num) ? num : endRow;
	if (endRow > num) {
		if (pageSize == 2) {
			var yushu = num % pageSize;
			if (yushu == 1) {
			    var code= treeNodes[0].children[startRow - 1].code;
                var name =treeNodes[0].children[startRow - 1].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
            }
		}
		if (pageSize == 4) {
			var yushu = num % pageSize;
			if (yushu == 1) {
			    var code= treeNodes[0].children[startRow - 1].code;
                var name =treeNodes[0].children[startRow - 1].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
            }
			if (yushu == 2) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code;
                var name = treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
			}
			if (yushu == 3) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code;
                +","+treeNodes[0].children[startRow + 1].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name;
                +","+treeNodes[0].children[startRow + 1].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
			}

		}
		if (pageSize == 6) {
			var yushu = num % pageSize;
			if (yushu == 1) {
                var code= treeNodes[0].children[startRow - 1].code;
                var name= treeNodes[0].children[startRow - 1].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
			}
			if (yushu == 2) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
			}
			if (yushu == 3) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code+","+treeNodes[0].children[startRow + 1].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name+","+treeNodes[0].children[startRow + 1].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
			}
			if (yushu == 4) {
			    var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code+","+treeNodes[0].children[startRow + 1].code+","+treeNodes[0].children[startRow + 2].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name+","+treeNodes[0].children[startRow + 1].name+","+treeNodes[0].children[startRow + 2].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
            }
			if (yushu == 5) {
			    var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code+","+treeNodes[0].children[startRow + 1].code+","+treeNodes[0].children[startRow + 2].code+","+treeNodes[0].children[startRow + 3].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name+","+treeNodes[0].children[startRow + 1].name+","+treeNodes[0].children[startRow + 2].name+","+treeNodes[0].children[startRow + 3].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
            }

		}
		if (pageSize == 9) {
			var yushu = num % pageSize;
			if (yushu == 1) {
			    var code= treeNodes[0].children[startRow - 1].code;
                var name= treeNodes[0].children[startRow - 1].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);  
            }
			if (yushu == 2) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
			}
			if (yushu == 3) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code+","+treeNodes[0].children[startRow + 1].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name+","+treeNodes[0].children[startRow + 1].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
			}
			if (yushu == 4) {
			    var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code+","+treeNodes[0].children[startRow + 1].code+","+treeNodes[0].children[startRow + 2].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name+","+treeNodes[0].children[startRow + 1].name+","+treeNodes[0].children[startRow + 2].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
            }
			if (yushu == 5) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code+","+treeNodes[0].children[startRow + 1].code+","+treeNodes[0].children[startRow + 2].code+","+treeNodes[0].children[startRow + 3].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name+","+treeNodes[0].children[startRow + 1].name+","+treeNodes[0].children[startRow + 2].name+","+treeNodes[0].children[startRow + 3].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
			}
			if (yushu == 6) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code+","+treeNodes[0].children[startRow + 1].code+","+treeNodes[0].children[startRow + 2].code+","+treeNodes[0].children[startRow + 3].code+","+treeNodes[0].children[startRow + 4].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name+","+treeNodes[0].children[startRow + 1].name+","+treeNodes[0].children[startRow + 2].name+","+treeNodes[0].children[startRow + 3].name+","+treeNodes[0].children[startRow + 4].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
			}
			if (yushu == 7) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code+","+treeNodes[0].children[startRow + 1].code+","+treeNodes[0].children[startRow + 2].code+","+treeNodes[0].children[startRow + 3].code+","+treeNodes[0].children[startRow + 4].code+","+treeNodes[0].children[startRow + 5].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name+","+treeNodes[0].children[startRow + 1].name+","+treeNodes[0].children[startRow + 2].name+","+treeNodes[0].children[startRow + 3].name+","+treeNodes[0].children[startRow + 4].name+","+treeNodes[0].children[startRow + 5].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
			}
			if (yushu == 8) {
                var code= treeNodes[0].children[startRow - 1].code+","+treeNodes[0].children[startRow].code+","+treeNodes[0].children[startRow + 1].code+","+treeNodes[0].children[startRow + 2].code+","+treeNodes[0].children[startRow + 3].code+","+treeNodes[0].children[startRow + 4].code+","+treeNodes[0].children[startRow + 5].code+","+treeNodes[0].children[startRow + 6].code;
                var name= treeNodes[0].children[startRow - 1].name+","+treeNodes[0].children[startRow].name+","+treeNodes[0].children[startRow + 1].name+","+treeNodes[0].children[startRow + 2].name+","+treeNodes[0].children[startRow + 3].name+","+treeNodes[0].children[startRow + 4].name+","+treeNodes[0].children[startRow + 5].name+","+treeNodes[0].children[startRow + 6].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint); 
			}

		}
	} else {
		if (pageSize == 1) {
           
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			var code =treeNodes[0].children[startRow - 1].code;
			var name =treeNodes[0].children[startRow - 1].name;
            var xpoint = $("#pull-left-div").width();        
            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
            oneScreenDevice[0] = treeNodes[0].children[startRow - 1].code;
		}
		if (pageSize == 2) {
			// 判断下一页是不是最后一页
			if (pno == (totalPage - 1)) {
				var yushu = num % pageSize;
				if (yushu == 1) {
					var two_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    // 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
					twoScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					twoScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
				} else if (yushu == 0) {
					var two_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    //系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
					twoScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					twoScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
				}

			} else {
				var two_deviceNO = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code;
				var code = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code;
				var name = treeNodes[0].children[index_device_data[startRow
						- 1]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].name;
				var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                twoScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
						- 1]].code;
				twoScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
			}
		}
		if (pageSize == 4) {
			// 判断下一页是不是最后一页
			if (pno == (totalPage - 1)) {
				var yushu = num % pageSize;
				if (yushu == 1) {
					var four_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
				} else if (yushu == 2) {
					var four_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
				} else if (yushu == 3) {
					var four_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
				} else if (yushu == 0) {
                    
					var four_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
				}

			} else {
				var four_deviceNO = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].code;
				var code = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].code;
				var name = treeNodes[0].children[index_device_data[startRow
						- 1]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].name;
				var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
						- 1]].code;
				fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
				fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
						+ 1]].code;
				fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
						+ 2]].code;

				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
			}
		}
		if (pageSize == 6) {
			// 判断下一页是不是最后一页
			if (pno == (totalPage - 1)) {
				var yushu = num % pageSize;
				if (yushu == 1) {
					var six_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name;
					
                    var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    sixScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
				} else if (yushu == 2) {
					var six_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name;
					// alert("six_deviceNO=="+six_deviceNO);
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
					
                    var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    sixScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
				} else if (yushu == 3) {
					var six_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
					
                    var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    sixScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
				} else if (yushu == 4) {
					var six_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
					
                    var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    sixScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
				} else if (yushu == 5) {
					var six_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
					
                    var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    sixScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
				} else if (yushu == 0) {
					var six_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
					
                    var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    sixScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
				}

			} else {
				var six_deviceNO = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 4]].code;
				var code = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 4]].code;
				var name = treeNodes[0].children[index_device_data[startRow
						- 1]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 4]].name;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
				
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                sixScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
						- 1]].code;
				sixScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
				sixScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
						+ 1]].code;
				sixScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
						+ 2]].code;
				sixScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
						+ 3]].code;
				sixScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
						+ 4]].code;
			}
		}
		if (pageSize == 9) {
			// 判断下一页是不是最后一页
			if (pno == (totalPage - 1)) {
				var yushu = num % pageSize;
				if (yushu == 1) {
					var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
				} else if (yushu == 2) {
					var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
				} else if (yushu == 3) {
					var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					
                    var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
				} else if (yushu == 4) {
					var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					
                    var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
				} else if (yushu == 5) {
					var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
				} else if (yushu == 6) {
					var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
				} else if (yushu == 7) {
					var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
				} else if (yushu == 8) {
					var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
				} else if (yushu == 0) {
					var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var code = treeNodes[0].children[index_device_data[startRow
							- 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].code;
					var name = treeNodes[0].children[index_device_data[startRow
							- 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 1]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 2]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 3]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 4]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 5]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 6]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					
                    var xpoint = $("#pull-left-div").width();        
                    window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                    nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
				}

			} else {
				var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 6]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 7]].code;
				var code = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 6]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 7]].code;
				var name = treeNodes[0].children[index_device_data[startRow
						- 1]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 4]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 5]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 6]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 7]].name;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
						- 1]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
				nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
						+ 1]].code;
				nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
						+ 2]].code;
				nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
						+ 3]].code;
				nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
						+ 4]].code;
				nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
						+ 5]].code;
				nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
						+ 6]].code;
				nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
						+ 7]].code;
			}
		}
	}
}
/**
 * 下一页函数 pno--页数 psize--每页显示记录数 ptotalNum--分页总数
 */
function right(e,pno, psize, ptotalNum, treeNodes, index_device_data) {
    var num = ptotalNum;
	var pageSize = psize;// 每页显示行数
	var totalPage = 0;// 总页数
	totalPage = parseInt((num - 1) / pageSize) + 1;
	if (pno >= totalPage) {
		$("#_right_down").attr('style', 'display:none');
		$("#_left_up").attr('style', 'display:inline');
		// return ;
	} else {
		$("#_left_up").attr('style', 'display:inline');
	}

	var currentPage = pno;// 当前页数
	var startRow = (currentPage - 1) * pageSize + 1;// 开始显示的行
	var endRow = currentPage * pageSize;// 结束显示的行
	endRow = (endRow > num) ? endRow : num;
	if (endRow > num) {
		if (pageSize == 2) {

			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (twoScreenDevice.length != 0) {
				if (twoScreenDevice.length == 1) {
					window.frames['dddddddddddddd'].unlogout(twoScreenDevice[0],'',1);
				} else if (twoScreenDevice.length == 2) {
					// 注销上一次屏幕刷新设备编号
                    window.frames['dddddddddddddd'].unlogout(twoScreenDevice[0],'',1);
                    window.frames['dddddddddddddd'].unlogout(twoScreenDevice[1],'',2);
				} else {
				}
			}
			var yushu = num % pageSize;
			if (yushu == 1) {
				// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"].init(treeNodes[0].children[startRow-1].code);
                var code = treeNodes[0].children[index_device_data[ptotalNum
                                      - 1]].code;
                var name = treeNodes[0].children[index_device_data[ptotalNum
                                      - 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
				// alert(window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"].$("#imgpath2").attr('src'));
				var two_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
                window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
                                - 2]].code,'',1);
                window.frames['dddddddddddddd'].$("#imgpath2").attr('src', root+'/images/moren_bg.gif');
				twoScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}

		}
		if (pageSize == 4) {
			var yushu = num % pageSize;
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (fourScreenDevice.length != 0) {
				for (var i = 0; i < fourScreenDevice.length; i++) {
					window.frames['dddddddddddddd'].unlogout(fourScreenDevice[i],'',i+1);
				}
			}
			if (yushu == 1) {
                var code = treeNodes[0].children[index_device_data[ptotalNum
                                        - 1]].code;
                var name = treeNodes[0].children[index_device_data[ptotalNum
                                        - 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);                        
				var four_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 4]].code,'',2);
				window.frames['dddddddddddddd'].$("#imgpath2").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 4 + 1]].code,'',3);
				window.frames['dddddddddddddd'].$("#imgpath3").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 4 + 2]].code,'',4);
				window.frames['dddddddddddddd'].$("#imgpath4").attr('src', root+'/images/moren_bg.gif');
                
				fourScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 2) {
				var code = treeNodes[0].children[index_device_data[ptotalNum
                                        - 2]].code+","+treeNodes[0].children[index_device_data[ptotalNum
                                        - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
                                        - 2]].name+","+treeNodes[0].children[index_device_data[ptotalNum
                                        - 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                var four_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 4]].code,'',3);
				window.frames['dddddddddddddd'].$("#imgpath3").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 4 + 1]].code,'',4);
				window.frames['dddddddddddddd'].$("#imgpath4").attr('src', root+'/images/moren_bg.gif');
				fourScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				fourScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 3) {
				var four_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
				var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                // 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 4]].code,'',4);
				window.frames['dddddddddddddd'].$("#imgpath4").attr('src', root+'/images/moren_bg.gif');
				fourScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				fourScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				fourScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}

		}
		if (pageSize == 6) {
			var yushu = num % pageSize;
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (sixScreenDevice.length != 0) {
				for (var i = 0; i < sixScreenDevice.length; i++) {
					window.frames['dddddddddddddd'].unlogout(sixScreenDevice[i],'',i+1);
				}
			}
			if (yushu == 1) {

				var six_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6]].code,'',2);
				window.frames['dddddddddddddd'].$("#imgpath2").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 1]].code,'',3);
				window.frames['dddddddddddddd'].$("#imgpath3").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 2]].code,'',4);
				window.frames['dddddddddddddd'].$("#imgpath4").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 3]].code,'',5);
				window.frames['dddddddddddddd'].$("#imgpath5").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 4]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src', root+'/images/moren_bg.gif');
				sixScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 2) {
				var six_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
				var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                // 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6]].code,'',3);
				window.frames['dddddddddddddd'].$("#imgpath3").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 1]].code,'',4);
				window.frames['dddddddddddddd'].$("#imgpath4").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 2]].code,'',5);
				window.frames['dddddddddddddd'].$("#imgpath5").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 3]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src', root+'/images/moren_bg.gif');
				sixScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 3) {
				var six_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);                        
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6]].code,'',4);
				window.frames['dddddddddddddd'].$("#imgpath4").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 1]].code,'',5);
				window.frames['dddddddddddddd'].$("#imgpath5").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 2]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src', root+'/images/moren_bg.gif');
				sixScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 4) {
				var six_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
                 var xpoint = $("#pull-left-div").width();        
                 window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6]].code,'',5);
				window.frames['dddddddddddddd'].$("#imgpath5").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6 + 1]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src', root+'/images/moren_bg.gif');
				sixScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 5) {
				var six_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
//				window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
//						.unlogout(treeNodes[0].children[index_device_data[ptotalNum
//								- 6]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
//						.$("#imgpath6").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 6]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src', root+'/images/moren_bg.gif');
				sixScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}

		}
		if (pageSize == 9) {
			var yushu = num % pageSize;
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (nineScreenDevice.length != 0) {
				for (var i = 0; i < nineScreenDevice.length; i++) {
					window.frames['dddddddddddddd'].unlogout(nineScreenDevice[i],'',i+1);
				}
			}
			if (yushu == 1) {

				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].name;
				var xpoint = $("#pull-left-div").width();        
				window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9]].code,'',2);
				window.frames['dddddddddddddd'].$("#imgpath2").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 1]].code,'',3);
				window.frames['dddddddddddddd'].$("#imgpath3").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 2]].code,'',4);
				window.frames['dddddddddddddd'].$("#imgpath4").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 3]].code,'',5);
				window.frames['dddddddddddddd'].$("#imgpath5").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 4]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 5]].code,'',7);
				window.frames['dddddddddddddd'].$("#imgpath7").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 6]].code,'',8);
				window.frames['dddddddddddddd'].$("#imgpath8").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 7]].code,'',9);
				window.frames['dddddddddddddd'].$("#imgpath9").attr('src', root+'/images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 2) {
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
				var xpoint = $("#pull-left-div").width();        
				window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
                // 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9]].code,'',3);
				window.frames['dddddddddddddd'].$("#imgpath3").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 1]].code,'',4);
				window.frames['dddddddddddddd'].$("#imgpath4").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 2]].code,'',5);
				window.frames['dddddddddddddd'].$("#imgpath5").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 3]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 4]].code,'',7);
				window.frames['dddddddddddddd'].$("#imgpath7").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 5]].code,'',8);
				window.frames['dddddddddddddd'].$("#imgpath8").attr('src', root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 6]].code,'',9);
				window.frames['dddddddddddddd'].$("#imgpath9").attr('src', root+'/images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 3) {
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
                 var xpoint = $("#pull-left-div").width();        
                 window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9]].code,'',4);
				window.frames['dddddddddddddd'].$("#imgpath4").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 1]].code,'',5);
				window.frames['dddddddddddddd'].$("#imgpath5").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 2]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 3]].code,'',7);
				window.frames['dddddddddddddd'].$("#imgpath7").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 4]].code,'',8);
				window.frames['dddddddddddddd'].$("#imgpath8").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 5]].code,'',9);
				window.frames['dddddddddddddd'].$("#imgpath9").attr('src',  root+'/images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 4) {
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9]].code,'',5);
				window.frames['dddddddddddddd'].$("#imgpath5").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 1]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 2]].code,'',7);
				window.frames['dddddddddddddd'].$("#imgpath7").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 3]].code,'',8);
				window.frames['dddddddddddddd'].$("#imgpath8").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 4]].code,'',9);
				window.frames['dddddddddddddd'].$("#imgpath9").attr('src',  root+'/images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				nineScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 5) {
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9]].code,'',6);
				window.frames['dddddddddddddd'].$("#imgpath6").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 1]].code,'',7);
				window.frames['dddddddddddddd'].$("#imgpath7").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 2]].code,'',8);
				window.frames['dddddddddddddd'].$("#imgpath8").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 3]].code,'',9);
				window.frames['dddddddddddddd'].$("#imgpath9").attr('src',  root+'/images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				nineScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				nineScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 6) {
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 5]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9]].code,'',7);
				window.frames['dddddddddddddd'].$("#imgpath7").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 1]].code,'',8);
				window.frames['dddddddddddddd'].$("#imgpath8").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 2]].code,'',9);
				window.frames['dddddddddddddd'].$("#imgpath9").attr('src',  root+'/images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				nineScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				nineScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				nineScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 7) {
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 6]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 6]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 6]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 5]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9]].code,'',8);
				window.frames['dddddddddddddd'].$("#imgpath8").attr('src',  root+'/images/moren_bg.gif');
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9 + 1]].code,'',9);
				window.frames['dddddddddddddd'].$("#imgpath9").attr('src',  root+'/images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				nineScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				nineScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				nineScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				nineScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 8) {
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 7]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 6]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 7]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 6]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 5]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 7]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 6]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 5]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 4]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 3]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 2]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 1]].name;
                var xpoint = $("#pull-left-div").width();        
                window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);        
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				window.frames['dddddddddddddd'].unlogout(treeNodes[0].children[index_device_data[ptotalNum
								- 9]].code,'',9);
				window.frames['dddddddddddddd'].$("#imgpath9").attr('src',  root+'/images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				nineScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				nineScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				nineScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				nineScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				nineScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[7] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}

		}
	} else {
		if (pageSize == 1) {
            // 计算出x轴差值
            var xpoint = $("#pull-left-div").width();
            var code = treeNodes[0].children[startRow - 1].code;
            var name = treeNodes[0].children[startRow - 1].name;
            window.frames['dddddddddddddd'].xypoint(code,
                    name, e, xpoint);        
			oneScreenDevice[0] = treeNodes[0].children[startRow - 1].code;
			// window.frames['dddddddddddddd'].frames["oneScreenImg1"].init(treeNodes[0].children[startRow-1].code);
		}
		if (pageSize == 2) {
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (twoScreenDevice.length != 0) {
				if (twoScreenDevice.length == 1) {
                    window.frames['dddddddddddddd'].unlogout(twoScreenDevice[0],'',1);
				} else if (twoScreenDevice.length == 2) {
					// 注销上一次屏幕刷新设备编号
                    window.frames['dddddddddddddd'].unlogout(twoScreenDevice[0],'',1);        
                    window.frames['dddddddddddddd'].unlogout(twoScreenDevice[1],'',2);        
				} else {
				}
			}
			var two_deviceNO = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code;
			var code = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code;
			var name = treeNodes[0].children[index_device_data[startRow
					- 1]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].name;
           
			twoScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
					- 1]].code;
			twoScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
            var xpoint = $("#pull-left-div").width();        
            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
		}
		if (pageSize == 4) {
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (fourScreenDevice.length != 0) {
				for (var i = 0; i < fourScreenDevice.length; i++) {
					window.frames['dddddddddddddd'].unlogout(fourScreenDevice[i],'',i+1);
				}
			}
			var four_deviceNO = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].code;
			var code = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].code;
			var name = treeNodes[0].children[index_device_data[startRow
					- 1]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].name;
			fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
					- 1]].code;
			fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
			fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
					+ 1]].code;
			fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
					+ 2]].code;
            var xpoint = $("#pull-left-div").width();        
            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
		}
		if (pageSize == 6) {
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (sixScreenDevice.length != 0) {
				for (var i = 0; i < sixScreenDevice.length; i++) {
//					window.frames['dddddddddddddd'].frames["sixScreenImg_"
//							+ (i + 1) + "_iframe"].unlogout(sixScreenDevice[i]);
					window.frames['dddddddddddddd'].unlogout(sixScreenDevice[i],'',i+1);
				}
			}
			var six_deviceNO = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 3]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 4]].code;
			var code = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 3]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 4]].code;
			var name = treeNodes[0].children[index_device_data[startRow
					- 1]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 3]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 4]].name;
			sixScreenDevice[0] = treeNodes[0].children[index_device_data[startRow]].code;
			sixScreenDevice[1] = treeNodes[0].children[index_device_data[startRow
					+ 1]].code;
			sixScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
					+ 2]].code;
			sixScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
					+ 3]].code;
			sixScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
					+ 4]].code;
			sixScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
					+ 5]].code;
            var xpoint = $("#pull-left-div").width();        
            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
		}
		if (pageSize == 9) {

			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (nineScreenDevice.length != 0) {
				for (var i = 0; i < nineScreenDevice.length; i++) {
					window.frames['dddddddddddddd'].unlogout(nineScreenDevice[i],'',i+1);
				}
			}

			var nine_deviceNO = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 3]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 4]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 5]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 6]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 7]].code;
			var code = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 3]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 4]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 5]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 6]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 7]].code;
			var name = treeNodes[0].children[index_device_data[startRow
					- 1]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 3]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 4]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 5]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 6]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 7]].name;
			nineScreenDevice[0] = treeNodes[0].children[index_device_data[startRow]].code;
			nineScreenDevice[1] = treeNodes[0].children[index_device_data[startRow
					+ 1]].code;
			nineScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
					+ 2]].code;
			nineScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
					+ 3]].code;
			nineScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
					+ 4]].code;
			nineScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
					+ 5]].code;
			nineScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
					+ 6]].code;
			nineScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
					+ 7]].code;
			nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
					+ 8]].code;
			var xpoint = $("#pull-left-div").width();        
            window.frames['dddddddddddddd'].xypointMany(code,name,e,xpoint);
            // 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
		}
	}
}
/**
 * 分页函数 pno--页数 psize--每页显示记录数 ptotalNum--分页总数
 * 分页部分是从真实数据行开始，因而存在加减某个常数，以确定真正的记录数 纯js分页实质是数据行全部加载，通过是否显示属性完成分页功能
 */
function goPage(pno, psize, ptotalNum) {
	var itable = document.getElementById("idData");
	// var num = itable.rows.length;//表格行数
	num = ptotalNum;
	var totalPage = 0;// 总页数
	var pageSize = psize;// 每页显示行数
	if ((num - 1) / pageSize > parseInt((num - 1) / pageSize)) {
		totalPage = parseInt((num - 1) / pageSize) + 1;
	} else {
		totalPage = parseInt((num - 1) / pageSize);
	}
	var currentPage = pno;// 当前页数
	var startRow = (currentPage - 1) * pageSize + 1;// 开始显示的行
	var endRow = currentPage * pageSize + 1;// 结束显示的行
	endRow = (endRow > num) ? num : endRow;

}

// 新修改方法
function gotoScreen(index) {
	screen_index = index;
	$("#_left_up").attr('style', 'display:none');
	$("#_right_down").attr('style', 'display:none');
	$("#_left_up").unbind('click');
	$("#_right_down").unbind('click');
	$("#dddddddddddddd").attr(
			"src",
			root + "/cross/monitore/mainindex/" + menuid + "/?screen_index="
					+ screen_index);
}
