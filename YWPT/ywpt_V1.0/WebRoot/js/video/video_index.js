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
// 16屏用于存放当前正在刷新的设备编号
var sixteenScreenDevice = new Array();

var treeNode = {};

// isdeviceFlag值为1是说明是设备,其他说明当前拖动的树节点不是设备
var isdeviceFlag = "";

// 存放上一次拖动树节点是否是设备标识的值,1表示拖动节点为设备
var lastNodeMoveDeviceFlag = "";

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
				isdeviceFlag = "0";// 设置拖动节点标识，0是部门节点不是设备节点
				return true;
			}
			if (treeNodes[0].nodeType == 'device') {// 设备支持拖动
				isdeviceFlag = "1";// 设置拖动节点标识，1是设备节点
				return true;
			}
		}
		// alert(treeNodes[0].isParent+"=="+treeNodes[0].pId);
		// 其他不支持单拖
		if (treeNodes[0].isParent == true && treeNodes[0].pId != null) {
			isdeviceFlag = "0";// 设置拖动节点标识，0是道路节点不是设备节点
			return true;
		} else {
			if (treeNodes[0].nodeType == 'device') {// 设备支持拖动
				isdeviceFlag = "1";// 设置拖动节点标识，1是设备节点
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
		$("#_left_up").unbind('click');
		$("#_right_down").unbind('click');
		// alert(treeNodes[0].children.find("isExistDevice=0"));
		// alert(treeNodes[0].children.isExistDevice.length);
		if (screen_index == 1) {
            //debugger;
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
						if (lastNodeMoveDeviceFlag == '0') {
							// 清除当前屏的所有刷新
							//window.frames['dddddddddddddd'].cleanAllSrceen();// 清除当前刷新页的所有刷新屏幕
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
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
						if (deviceCount >= 1) {
							// 判断当前刷新界面是否有屏在刷新，0是没有
							if (oneScreenDevice.length != 0) {

								// 注销上一次屏幕刷新设备编号
								// window.frames['dddddddddddddd'].frames["oneScreenImg1"]
								// .init(
								// oneScreenDevice[0],
								// treeNodes[0].children[index_device_data1[0]].code);
								// 停止原来的
								window.frames['dddddddddddddd'].stopvideo(
										oneScreenDevice[0], 1);
								// 清除当前屏数所有的选中操作
								window.frames['dddddddddddddd'].cleanSelected();
								// 加载新拖的
								window.frames['dddddddddddddd']
										.xypointMany(
												treeNodes[0].children[index_device_data1[0]].code,
												treeNodes[0].children[index_device_data1[0]].name);
								$("#_right_down").attr('style', 'display:none');
								$("#_left_up").attr('style', 'display:none');
							} else {
								// window.frames['dddddddddddddd'].frames["oneScreenImg1"]
								// .init(
								// null,
								// treeNodes[0].children[index_device_data1[0]].code);
								window.frames['dddddddddddddd']
										.xypointMany(
												treeNodes[0].children[index_device_data1[0]].code,
												treeNodes[0].children[index_device_data1[0]].name);
							}
							one_deviceNO = treeNodes[0].children[index_device_data1[0]].code;
							oneScreenDevice[0] = treeNodes[0].children[index_device_data1[0]].code;
						}

						if (lastNodeMoveDeviceFlag == '1') {
							// 清除全部所有屏
							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
						} 
//                        else {// 上一次操作时点击的视频设备
//							// 清除全部所有屏
//							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
//						}
						lastNodeMoveDeviceFlag = isdeviceFlag;

						// window.frames['dddddddddddddd'].frames["oneScreenImg1"].init(treeNodes[0].children[0].code);
						// $("#_left_up").attr('style','display:inline');
						var pno = 1;
						$("#_left_up").click(function() {
							pno--;
							// var ptotalNum=treeNodes[0].children.length;
							var up_ptotalNum = deviceCount;
							left(pno, screen_index, up_ptotalNum, treeNodes,
									index_device_data1);
						});
						treeNode = treeNodes;
						if (deviceCount > 1) {
							$("#_right_down").attr('style', 'display:inline');
						}

						$("#_right_down").click(function() {
							// aaa('${root}/cross/monitore/oneScreen','1');
							pno++;
							// var down_ptotalNum=treeNodes[0].children.length;
							var down_ptotalNum = deviceCount;
							// //alert(down_ptotalNum);
							right(pno, screen_index, down_ptotalNum, treeNodes,
									index_device_data1);
						});
						// MoveTest.updateType();
					}

				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
					// alert(MoveTest.errorMsg);
				}

			}
			// window.location.href='${root}/cross/monitore/init?screen=1&deviceCode=610122459602';
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=1&deviceCode='+one_deviceNO;
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
						if (lastNodeMoveDeviceFlag == '0') {
							// 清除当前屏的所有刷新
//							window.frames['dddddddddddddd'].cleanAllSrceen();// 清除当前刷新页的所有刷新屏幕
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
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
								// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
								// .unlogout(twoScreenDevice[0]);
								window.frames['dddddddddddddd'].stopvideo(
										twoScreenDevice[0], 1);
							} else if (twoScreenDevice.length == 2) {
								// // 注销上一次屏幕刷新设备编号
								// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
								// .unlogout(twoScreenDevice[0]);
								// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
								// .unlogout(twoScreenDevice[1]);
								window.frames['dddddddddddddd'].stopvideo(
										twoScreenDevice[0], 1);
								window.frames['dddddddddddddd'].stopvideo(
										twoScreenDevice[1], 2);
							} else {
							}
							// 清除当前屏数所有的选中操作
							window.frames['dddddddddddddd'].cleanSelected();
						}
						if (lastNodeMoveDeviceFlag == '1') {
							// 清除全部所有屏
							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
						} 
//                        else {// 上一次操作时点击的视频设备
//							// 清除全部所有屏
//							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
//						}
						lastNodeMoveDeviceFlag = isdeviceFlag;
						// 替换下面代码
						if (deviceCount >= 2) {
							var code = "";
							var name = "";
							for (var i = 0; i < 2; i++) {
								if (i == 1) {
									two_deviceNO += treeNodes[0].children[index_device_data2[i]].code;
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
							window.frames['dddddddddddddd'].xypointMany(code,
									name, e, xpoint);

						} else {
							if (deviceCount == 1) {

								two_deviceNO = treeNodes[0].children[index_device_data2[0]].code;
								var code = treeNodes[0].children[index_device_data2[0]].code;
								var name = treeNodes[0].children[index_device_data2[0]].name;
								twoScreenDevice[0] = treeNodes[0].children[index_device_data2[0]].code
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
							}
						}
						// $("#_left_up").attr('style','display:none');
						var pno = 1;
						$("#_left_up").click(function() {
							pno--;
							// var ptotalNum=treeNodes[0].children.length;
							var up_ptotalNum = deviceCount;
							left(pno, screen_index, up_ptotalNum, treeNodes,
									index_device_data2);
						});
						treeNode = treeNodes;

						if (deviceCount > 2) {
							// $("#_right_down").attr('style','display:inline');
							$("#_right_down").attr('style', 'display:block');
						}
						// $("#_right_down_img").attr('alt','下一页')

						$("#_right_down").click(function() {
							// aaa('${root}/cross/monitore/oneScreen','1');
							pno++;
							// var ptotalNum=treeNodes[0].children.length;
							var down_ptotalNum = deviceCount;
							right(pno, screen_index, down_ptotalNum, treeNodes,
									index_device_data2);

						});
					}
				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
					// alert(MoveTest.errorMsg);
				}
			}
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
		} else if (screen_index == 4) { 
             //debugger;
            // 单拖设备处理
			if (treeId == "dandevices") {
				// window.frames['dddddddddddddd'].frames["fourScreenImg_"+(i+1)+"_iframe"].unlogout(fourScreenDevice[i]);
				// window.frames['dddddddddddddd'].document.getElementById("div1").innerHTML="888888888";
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				//zTree.removeNode(treeNodes[0]);
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
				// odiv1=document.getElementById('div1');
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
				/*
				 * //alert("("+odiv1_x+","+odiv1_y+")--("+odiv1_x1+","+odiv1_y1+")");
				 * //alert("("+odiv2_x+","+odiv2_y+")--("+odiv2_x1+","+odiv2_y1+")");
				 * //alert("("+odiv3_x+","+odiv3_y+")--("+odiv3_x1+","+odiv3_y1+")");
				 * //alert("("+odiv4_x+","+odiv4_y+")--("+odiv4_x1+","+odiv4_y1+")");
				 * //alert(e.pageX+","+e.pageY);
				 */
				// 计算出x轴差值
				var xpoint = $("#pull-left-div").width();
				window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

			} else {
				//debugger;
				// 其他处理
				var four_deviceNO = "";
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
						// alert("last2="+lastNodeMoveDeviceFlag+",isdevic2="+isdeviceFlag)
						if (lastNodeMoveDeviceFlag == '0') {
							// 清除当前屏的所有刷新
							//window.frames['dddddddddddddd'].cleanAllSrceen();// 清除当前刷新页的所有刷新屏幕
							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
						}
						lastNodeMoveDeviceFlag = isdeviceFlag;
						var name = zTree.getSelectedNodes()[0].name;
						// 计算出x轴差值
						var xpoint = $("#pull-left-div").width();
						window.frames['dddddddddddddd'].xypoint(code, name, e,
								xpoint);
						$("#_right_down").attr('style', 'display:none');
						$("#_left_up").attr('style', 'display:none');
						// alert("last3="+lastNodeMoveDeviceFlag+",isdevic3="+isdeviceFlag)
					} else {
						// 存储不是设备下标索引数组
						var index_data4 = new Array();
						// 存储是设备下标索引数组
						var index_device_data4 = new Array();
						/*
						 * var nodeList = $("#"+ddddd+" ul li
						 * a").find("span:first-child"); for(var i=0;i<nodeList.length;i++) {
						 * var id =nodeList[i].id; var style =
						 * $("#"+id).attr('style'); if(style==undefined ||
						 * style=='') { //将下标追加到数组最后 index_data4.push(i); }else{
						 * index_device_data4.push(i) } }
						 */
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
						// zTree.removeNode(treeNodes[0]);
						// alert(treeNodes[0].children.length);

						// 判断当前刷新界面是否有屏在刷新，0是没有
						if (fourScreenDevice.length != 0) {

							$("#_right_down").attr('style', 'display:none');
							$("#_left_up").attr('style', 'display:none');
							for (var i = 0; i < fourScreenDevice.length; i++) {
								// window.frames['dddddddddddddd'].frames["fourScreenImg_"
								// + (i + 1) + "_iframe"]
								// .unlogout(fourScreenDevice[i]);
								window.frames['dddddddddddddd'].stopvideo(
										fourScreenDevice[i], i + 1);
							}
							// 清除当前屏数所有的选中操作
							window.frames['dddddddddddddd'].cleanSelected();
						}
						if (lastNodeMoveDeviceFlag == '1') {
							// 清除全部所有屏
							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
						}
						// else{//上一次操作时点击的视频设备
						// //清除全部所有屏
						// window.frames['dddddddddddddd'].stopAll();//清除当前刷新页的所有刷新屏幕
						// }
						lastNodeMoveDeviceFlag = isdeviceFlag;
						var odiv1 = window.frames['dddddddddddddd'].document
								.getElementById("div1");
						var odiv2 = window.frames['dddddddddddddd'].document
								.getElementById("div2");
						var odiv3 = window.frames['dddddddddddddd'].document
								.getElementById("div3");
						var odiv4 = window.frames['dddddddddddddd'].document
								.getElementById("div4");
						// odiv1=document.getElementById('div1');
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
						// 替换下面代码
						if (deviceCount >= 4) {
							var code = "";
							var name = "";
							for (var i = 0; i < 4; i++) {
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
							window.frames['dddddddddddddd'].xypointMany(code,
									name, e, xpoint);

						} else {
							if (deviceCount == 1) {
								four_deviceNO = treeNodes[0].children[index_device_data4[0]].code;
								fourScreenDevice[0] = treeNodes[0].children[index_device_data4[0]].code;
								var code = treeNodes[0].children[index_device_data4[0]].code;
								var name = treeNodes[0].children[index_device_data4[0]].name;
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
							}
							if (deviceCount == 2) {
								four_deviceNO = treeNodes[0].children[index_device_data4[0]].code
										+ ","
										+ treeNodes[0].children[index_device_data4[1]].code;
								fourScreenDevice[0] = treeNodes[0].children[index_device_data4[0]].code;
								fourScreenDevice[1] = treeNodes[0].children[index_device_data4[1]].code;
								var code = treeNodes[0].children[index_device_data4[0]].code
										+ ","
										+ treeNodes[0].children[index_device_data4[1]].code;;
								var name = treeNodes[0].children[index_device_data4[0]].name
										+ ","
										+ treeNodes[0].children[index_device_data4[1]].name;;
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
							}
							if (deviceCount == 3) {
								var code = ""
								var name = "";
								for (var i = 0; i < index_device_data4.length; i++) {
									if (i == 2) {
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
							}
						}

						var pno = 1;
						$("#_left_up").click(function() {
							pno--;
							// var ptotalNum=treeNodes[0].children.length;
							var up_ptotalNum = deviceCount;
							left(pno, screen_index, up_ptotalNum, treeNodes,
									index_device_data4);
						});
						treeNode = treeNodes;
						if (deviceCount > 4) {
							$("#_right_down").attr('style', 'display:inline');
						}

						$("#_right_down").click(function() {
							// aaa('${root}/cross/monitore/oneScreen','1');
							pno++;
							// var ptotalNum=treeNodes[0].children.length;
							var down_ptotalNum = deviceCount;
							right(pno, screen_index, down_ptotalNum, treeNodes,
									index_device_data4);
						});
						// MoveTest.updateType();
					}
				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
					// alert(MoveTest.errorMsg);
				}
			}
			// alert(four_deviceNO);
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
		} else if (screen_index == 6) { 
            //debugger;
            // 单拖设备处理
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
						if (lastNodeMoveDeviceFlag == '0') {
							// 清除当前屏的所有刷新
							//window.frames['dddddddddddddd'].cleanAllSrceen();// 清除当前刷新页的所有刷新屏幕
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
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
								// window.frames['dddddddddddddd'].frames["sixScreenImg_"
								// + (i + 1) + "_iframe"]
								// .unlogout(sixScreenDevice[i]);
								window.frames['dddddddddddddd'].stopvideo(
										sixScreenDevice[i], i + 1);
							}
							// 清除当前屏数所有的选中操作
							window.frames['dddddddddddddd'].cleanSelected();
						}
						if (lastNodeMoveDeviceFlag == '1') {
							// 清除全部所有屏
							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
						}
						// else{//上一次操作时点击的视频设备
						// //清除全部所有屏
						// window.frames['dddddddddddddd'].stopdrapAll();//清除当前刷新页的所有刷新屏幕
						// }
						lastNodeMoveDeviceFlag = isdeviceFlag;
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
							window.frames['dddddddddddddd'].xypointMany(code,
									name, e, xpoint);
						} else {
							if (deviceCount == 1) {
								six_deviceNO = treeNodes[0].children[index_device_data6[0]].code;
								var code = treeNodes[0].children[index_device_data6[0]].code;
								var name = treeNodes[0].children[index_device_data6[0]].name;
								sixScreenDevice[0] = treeNodes[0].children[index_device_data6[0]].code;
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 4) {
								var code = "";
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

								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 5) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data6.length; i++) {
									if (i == 4) {
										six_deviceNO += treeNodes[0].children[index_device_data6[i]].code;
										name += treeNodes[0].children[index_device_data6[i]].name;
										code += treeNodes[0].children[index_device_data6[i]].code;
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
							}
						}

						// $("#_left_up").attr('style','display:inline');
						var pno = 1;
						$("#_left_up").click(function() {
							pno--;
							// var ptotalNum=treeNodes[0].children.length;
							var up_ptotalNum = deviceCount;
							left(pno, screen_index, up_ptotalNum, treeNodes,
									index_device_data6);
						});
						treeNode = treeNodes;
						if (deviceCount > 6) {
							$("#_right_down").attr('style', 'display:inline');
						}

						$("#_right_down").click(function() {
							// aaa('${root}/cross/monitore/oneScreen','1');
							pno++;
							// var ptotalNum=treeNodes[0].children.length;
							var show_ptotalNum = deviceCount;
							right(pno, screen_index, show_ptotalNum, treeNodes,
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
						if (lastNodeMoveDeviceFlag == '0') {
							// 清除当前屏的所有刷新
							//window.frames['dddddddddddddd'].cleanAllSrceen();// 清除当前刷新页的所有刷新屏幕
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
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
								// window.frames['dddddddddddddd'].frames["nineScreenImg_"
								// + (i + 1) + "_iframe"]
								// .unlogout(nineScreenDevice[i]);
								window.frames['dddddddddddddd'].stopvideo(
										nineScreenDevice[i], i + 1);
							}
							// 清除当前屏数所有的选中操作
							window.frames['dddddddddddddd'].cleanSelected();
						}
						if (lastNodeMoveDeviceFlag == '1') {
							// 清除全部所有屏
							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
						} else {// 上一次操作时点击的视频设备
							// 清除全部所有屏
							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
						}
						lastNodeMoveDeviceFlag = isdeviceFlag;
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
								nineScreenDevice[i] = treeNodes[0].children[index_device_data9[i]].code;

							}
							window.frames['dddddddddddddd'].xypointMany(code,
									name, e, xpoint);
						} else {
							if (deviceCount == 1) {
								nine_deviceNO = treeNodes[0].children[index_device_data9[0]].code;
								var code = treeNodes[0].children[index_device_data9[0]].code;
								var name = treeNodes[0].children[index_device_data9[0]].name;
								nineScreenDevice[0] = treeNodes[0].children[index_device_data9[0]].code;
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
							}
							if (deviceCount == 2) {

								nine_deviceNO = treeNodes[0].children[index_device_data9[0]].code
										+ ","
										+ treeNodes[0].children[index_device_data9[1]].code;
								var code = treeNodes[0].children[index_device_data9[0]].code
										+ ","
										+ treeNodes[0].children[index_device_data9[1]].code;
								var name = treeNodes[0].children[index_device_data9[0]].name
										+ ","
										+ treeNodes[0].children[index_device_data9[1]].name;
								nineScreenDevice[0] = treeNodes[0].children[index_device_data9[0]].code;
								nineScreenDevice[1] = treeNodes[0].children[index_device_data9[1]].code;
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 4) {
								var code = "";
								var name = "";
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 5) {
								var code = "";
								var name = "";
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 6) {
								var code = "";
								var name = "";
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 7) {
								var code = "";
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 8) {
								var code = "";
								var name = "";
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
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
						}
						// $("#_left_up").attr('style','display:inline');
						var pno = 1;
						$("#_left_up").click(function() {
							pno--;
							// var ptotalNum=treeNodes[0].children.length;
							var up_ptotalNum = deviceCount;
							left(pno, screen_index, up_ptotalNum, treeNodes,
									index_device_data9);
						});
						treeNode = treeNodes;
						if (deviceCount > 9) {
							$("#_right_down").attr('style', 'display:inline');
						}
						$("#_right_down").click(function() {
							pno++;
							var down_ptotalNum = deviceCount;
							right(pno, screen_index, down_ptotalNum, treeNodes,
									index_device_data9);
						});
					}
				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
				}
			}
		} else if (screen_index == 16) {
			// 单拖设备处理
			if (treeId == "dandevices") {
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				var code = zTree.getSelectedNodes()[0].code;
				var name = zTree.getSelectedNodes()[0].name;
				// 计算出x轴差值
				var xpoint = $("#pull-left-div").width();
				window.frames['nopage_iframe'].xypoint(code, name, e, xpoint);

			} else {

				var sixteen_deviceNO = "";
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
						if (lastNodeMoveDeviceFlag == '0') {
							// 清除当前屏的所有刷新
							//window.frames['dddddddddddddd'].cleanAllSrceen();// 清除当前刷新页的所有刷新屏幕
                            window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
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
						var index_data16 = new Array();
						// 存储是设备下标索引数组
						var index_device_data16 = new Array();

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
									index_data16.push(i);
								} else {
									index_device_data16.push(i)
								}

							}
						}

						// 确定有几个设备
						var deviceCount = index_device_data16.length;
						// zTree.removeNode(treeNodes[0]);

						// 判断当前刷新界面是否有屏在刷新，0是没有
						if (sixteenScreenDevice.length != 0) {

							$("#_right_down").attr('style', 'display:none');
							$("#_left_up").attr('style', 'display:none');
							for (var i = 0; i < sixteenScreenDevice.length; i++) {
								// window.frames['dddddddddddddd'].frames["nineScreenImg_"
								// + (i + 1) + "_iframe"]
								// .unlogout(sixteenScreenDevice[i]);
								window.frames['dddddddddddddd'].stopvideo(
										sixteenScreenDevice[i], i + 1);
							}
							// 清除当前屏数所有的选中操作
							window.frames['dddddddddddddd'].cleanSelected();
						}
						if (lastNodeMoveDeviceFlag == '1') {
							// 清除全部所有屏
							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
						} else {// 上一次操作时点击的视频设备
							// 清除全部所有屏
							window.frames['dddddddddddddd'].stopAll();// 清除当前刷新页的所有刷新屏幕
						}
						lastNodeMoveDeviceFlag = isdeviceFlag;
						// 替换下面代码
						if (deviceCount >= 16) {
							var code = "";
							var name = "";
							for (var i = 0; i < 16; i++) {

								if (i == 15) {
									nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
									code += treeNodes[0].children[index_device_data16[i]].code;
									name += treeNodes[0].children[index_device_data16[i]].name;
								} else {
									nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
											+ ",";
									code += treeNodes[0].children[index_device_data16[i]].code
											+ ",";
									name += treeNodes[0].children[index_device_data16[i]].name
											+ ",";
								}
								sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;

							}
							window.frames['dddddddddddddd'].xypointMany(code,
									name, e, xpoint);
						} else {
							if (deviceCount == 1) {
								nine_deviceNO = treeNodes[0].children[index_device_data16[0]].code;
								var code = treeNodes[0].children[index_device_data16[0]].code;
								var name = treeNodes[0].children[index_device_data16[0]].name;
								sixteenScreenDevice[0] = treeNodes[0].children[index_device_data16[0]].code;
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
							}
							if (deviceCount == 2) {

								nine_deviceNO = treeNodes[0].children[index_device_data16[0]].code
										+ ","
										+ treeNodes[0].children[index_device_data16[1]].code;
								var code = treeNodes[0].children[index_device_data16[0]].code
										+ ","
										+ treeNodes[0].children[index_device_data16[1]].code;
								var name = treeNodes[0].children[index_device_data16[0]].name
										+ ","
										+ treeNodes[0].children[index_device_data16[1]].name;
								sixteenScreenDevice[0] = treeNodes[0].children[index_device_data16[0]].code;
								sixteenScreenDevice[1] = treeNodes[0].children[index_device_data16[1]].code;
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);
							}
							if (deviceCount == 3) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 2) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 4) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 3) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 5) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 4) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 6) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 5) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 7) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 6) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 8) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 7) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 9) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 8) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 10) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 9) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 11) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 10) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 12) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 11) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 13) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 12) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 14) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 13) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
							if (deviceCount == 15) {
								var code = "";
								var name = "";
								for (var i = 0; i < index_device_data16.length; i++) {
									if (i == 14) {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code;
										code += treeNodes[0].children[index_device_data16[i]].code;
										name += treeNodes[0].children[index_device_data16[i]].name;
									} else {
										nine_deviceNO += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										code += treeNodes[0].children[index_device_data16[i]].code
												+ ",";
										name += treeNodes[0].children[index_device_data16[i]].name
												+ ",";
									}
									sixteenScreenDevice[i] = treeNodes[0].children[index_device_data16[i]].code;
								}
								window.frames['dddddddddddddd'].xypointMany(
										code, name, e, xpoint);

							}
						}
						// $("#_left_up").attr('style','display:inline');
						var pno = 1;
						$("#_left_up").click(function() {
							pno--;
							// var ptotalNum=treeNodes[0].children.length;
							var up_ptotalNum = deviceCount;
							left(pno, screen_index, up_ptotalNum, treeNodes,
									index_device_data16);
						});
						treeNode = treeNodes;
						if (deviceCount > 16) {
							$("#_right_down").attr('style', 'display:inline');
						}
						$("#_right_down").click(function() {
							pno++;
							var down_ptotalNum = deviceCount;
							right(pno, screen_index, down_ptotalNum, treeNodes,
									index_device_data16);
						});
					}
				} else if ($(e.target).parents(".domBtnDiv").length > 0) {
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
		$(".domBtnDiv").bind("mousedown", MoveTest.bindMouseDown);
	},
	bindMouseDown : function(e) {
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
		MoveTest.noSel();
		var doc = $(document), docScrollTop = doc.scrollTop(), docScrollLeft = doc
				.scrollLeft(), tmpTarget = MoveTest.curTmpTarget;
		if (tmpTarget) {
			tmpTarget.css({
						"top" : (e.clientY + docScrollTop + 3) + "px",
						"left" : (e.clientX + docScrollLeft + 3) + "px"
					});
		}
		return false;
	},
	bindMouseUp : function(e) {
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
		// beforeClick : beforeClick,
		beforeClick : beforeClickDevice,
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
		// beforeClick : beforeClick,
		beforeClick : beforeClickDevice,
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
		// beforeClick : beforeClick,
		beforeClick : beforeClickDevice,
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
		beforeClick : beforeClickDevice,
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
	// alert(treeId);
	alert("该节点不允许点击,请拖拽");
	/*
	 * var zTree = $.fn.zTree.getZTreeObj("treebyNormal");
	 * zTree.checkNode(treeNode, !treeNode.checked, null, true); return false;
	 */
}

// 按照道路 设备树 节点点击选择
function beforeClickDevice(treeId, treeNode) {
    //debugger;
	// var zTree = $.fn.zTree.getZTreeObj("treebyNormal");
	// zTree.checkNode(treeNode, !treeNode.checked, null, true); return false;
	// var zTree = $.fn.zTree.getZTreeObj(treeId);
	// var code = zTree.getSelectedNodes()[0].code;
	// var name = zTree.getSelectedNodes()[0].name;
	if ("dandevices" == treeId) {// 设备单拖
		// window.frames['nopage_iframe'].te();
		// alert(window.frames['nopage_iframe'].document.getElementById("imgpath1_code").value);
		iframeid = 'nopage_iframe';
	} else {
		// 按部门 按道路 自定义
		iframeid = 'dddddddddddddd';
	}
	switch (parseInt(screen_index)) {
		case 1 :// 1屏
			var one_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath1_code").value
			if (one_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
						screen_index);
			} else {
				alert("请拖动到播放器内查看！");
			}
			break;
		case 2 :
			var one_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath1_code").value;
			var two_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath2_code").value;
			if (one_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
						screen_index);
			} else if (two_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
						screen_index);
			} else {
				alert("请拖动到播放器内查看！");
			}

			break;
		case 4 :
			var one_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath1_code").value;
			var two_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath2_code").value;
			var three_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath3_code").value;
			var four_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath4_code").value;
			if (one_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
						screen_index);
			} else if (two_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
						screen_index);
			} else if (three_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '3', treeNode,
						screen_index);
			} else if (four_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '4', treeNode,
						screen_index);
			} else {
				alert("请拖动到播放器内查看！");
			}
			break;
		case 6 :
			var one_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath1_code").value;
			var two_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath2_code").value;
			var three_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath3_code").value;
			var four_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath4_code").value;
			var five_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath5_code").value;
			var six_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath6_code").value;
			if (one_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
						screen_index);
			} else if (two_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
						screen_index);
			} else if (three_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '3', treeNode,
						screen_index);
			} else if (four_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '4', treeNode,
						screen_index);
			} else if (five_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '5', treeNode,
						screen_index);
			} else if (six_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '6', treeNode,
						screen_index);
			} else {
				alert("请拖动到播放器内查看！");
			}
			break;
		case 9 :
			var one_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath1_code").value;
			var two_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath2_code").value;
			var three_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath3_code").value;
			var four_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath4_code").value;
			var five_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath5_code").value;
			var six_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath6_code").value;
			var seven_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath7_code").value;
			var eight_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath8_code").value;
			var nine_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath9_code").value;
			if (one_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
						screen_index);
			} else if (two_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
						screen_index);
			} else if (three_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '3', treeNode,
						screen_index);
			} else if (four_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '4', treeNode,
						screen_index);
			} else if (five_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '5', treeNode,
						screen_index);
			} else if (six_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '6', treeNode,
						screen_index);
			} else if (seven_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '7', treeNode,
						screen_index);
			} else if (eight_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '8', treeNode,
						screen_index);
			} else if (nine_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '9', treeNode,
						screen_index);
			} else {
				alert("请拖动到播放器内查看！");
			}
			break;
		case 16 :
			var one_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath1_code").value;
			var two_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath2_code").value;
			var three_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath3_code").value;
			var four_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath4_code").value;
			var five_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath5_code").value;
			var six_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath6_code").value;
			var seven_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath7_code").value;
			var eight_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath8_code").value;
			var nine_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath9_code").value;
			var ten_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath10_code").value;
			var eleven_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath11_code").value;
			var twelve_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath12_code").value;
			var thirteen_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath13_code").value;
			var fourteen_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath14_code").value;
			var fivteen_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath15_code").value;
			var sixteen_imgpath_code_input = window.frames[iframeid].document
					.getElementById("imgpath16_code").value;
			if (one_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '1', treeNode,
						screen_index);
			} else if (two_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '2', treeNode,
						screen_index);
			} else if (three_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '3', treeNode,
						screen_index);
			} else if (four_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '4', treeNode,
						screen_index);
			} else if (five_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '5', treeNode,
						screen_index);
			} else if (six_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '6', treeNode,
						screen_index);
			} else if (seven_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '7', treeNode,
						screen_index);
			} else if (eight_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '8', treeNode,
						screen_index);
			} else if (nine_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '9', treeNode,
						screen_index);
			} else if (ten_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '10', treeNode,
						screen_index);
			} else if (eleven_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '11', treeNode,
						screen_index);
			} else if (twelve_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '12', treeNode,
						screen_index);
			} else if (thirteen_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '13', treeNode,
						screen_index);
			} else if (fourteen_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '14', treeNode,
						screen_index);
			} else if (fivteen_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '15', treeNode,
						screen_index);
			} else if (sixteen_imgpath_code_input == '') {
				setImageCodeNameByIFrameIdInputId(iframeid, '', '16', treeNode,
						screen_index);
			} else {
				alert("请拖动到播放器内查看！");
			}
			break;
	}

}
/**
 * 根据iframeid和inputid和第几屏,设置第几屏对应的视频设备code和name，执行播放视频
 * 
 * @param {}
 *            iframeid 分屏iframeid
 * @param {}
 *            inputid 分屏中设备编号inputid
 * @param {}
 *            pingshu 第几屏，设置第几屏的值
 * @param {}
 *            treeNode 点击设备树的树节点
 * @Param {}
 *            zongpingshu 总屏数
 */
function setImageCodeNameByIFrameIdInputId(iframeid, inputid, pingshu,
		treeNode, zongpingshu) {
            //debugger;
	window.frames[iframeid].document.getElementById("imgpath" + pingshu
			+ "_code").value = treeNode.code;
	window.frames[iframeid].document.getElementById("imgpath" + pingshu
			+ "_name").innerHTML = treeNode.name;
    if(shizheng_poject_type=='internet'){
        //市政项目视频参数配置，加载视频用到，神州鹰视频
    
      videoPlay.load(root, iframeid, treeNode.code, treeNode.name, '2', pingshu,
            zongpingshu,shizheng_poject_type);  
    }else{//普通视频加载
        videoPlay.load(root, iframeid, treeNode.code, treeNode.name, '2', pingshu,
            zongpingshu);
    }
	
}

var videoPlay = {
	/**
	 * CkPlayer播放器 1屏视频加载方法
	 * 
	 * @param {}
	 *            root 系统路径
	 * @param {}
	 *            iframeid iframeid
	 * @param {}
	 *            devicecode 设备编号
	 * @param {}
	 *            devicename 设备名称
	 * @param {}
	 *            command 视频厂家
	 * @param {}
	 *            screen_index 需要加载当前页面的第几屏
	 * @param {}
	 *            pingshu 每个页面的总屏数
	 * @param{} lx 视频播放类型，市政项目神州鹰的是internet类型，其余是默认的
	 */
	load : function(root, iframeid, devicecode, devicename, command,
			screen_index, pingshu, lx) {
		var typelx = '';
		if ("internet" == lx) {
			typelx = 'internet';
		} else {
			typelx = "defalut";
		}
		var deviceIp = "193.169.100.249";// 设备的ip地址
		// var rtspName = "mp4:" + devicecode;// 设备
		var rtspName = devicecode;// 设备
		$.ajax({
			type : "POST",
			url : root + "/vedio/index/sendCommandInfo/" + typelx + "/",
			dataType : "json",
			data : {
				command : command,
				// command : 2,
				deviceIp : deviceIp,
				rtspName : rtspName
			},
			success : function(dd) {
				var serverIp = dd[1];
				rtspName = "mp4:" + devicecode;// 设备
				var videoObject = window.frames[iframeid].document
						.getElementById("videoPlayer" + screen_index);
				// alert( videoObject.clientWidth);
				var width_div = videoObject.clientWidth;
				$(videoObject).remove();
				var videoHeight = videoPlay.height(pingshu);
				var divVideo = window.frames[iframeid].document
						.getElementById("div" + screen_index);

				//ckplayer视频加载时代码,已经不用
                //videoPlay.old_ckplayerLoad(screen_index,videoHeight,serverIp,rtspName)
				if ('ip' == serverIp) {
					$(divVideo).append("<div id='videoPlayer" + screen_index
							+ "' style=\"background:#000;width:100%;height:"
							+ videoHeight + "px\"></div>");
				} else {
                    //现在使用videoPlayer视频播放器播放视频
                    videoPlay.videoplayerLoad_now(divVideo,screen_index,root,serverIp,rtspName,width_div,videoHeight)
                }
			},
			error : function() {
			}
		});

	},
	/**
	 * 根据屏数设置页面视频播放高度
	 * 
	 * @param {}
	 *            screen 屏数
	 */
	height : function(screen) {
		// 获取父页面的高,没有上一页和下一页的父页面高度
		// var paraentHeight = window.parent.document
		// .getElementById("nopage_iframe").clientHeight;
		//debugger;
		var paraentHeight = document.getElementById("nopage_iframe").clientHeight;
		if (paraentHeight == '' || paraentHeight == undefined
				|| paraentHeight == 0) {
			// 获取有上一页下一页的父页面高度
			// paraentHeight = window.parent.document
			// .getElementById("dddddddddddddd").clientHeight;
			paraentHeight = document.getElementById("dddddddddddddd").clientHeight;
		}
		paraentHeight = paraentHeight - 50;
		// alert(paraentHeight);
		var img_4h = paraentHeight / 2 - 26;// 4,6,16屏高度
		var img_9h = paraentHeight / 3 - 26;// 9屏高度
		var height = "";
		// 网页正文部分上，y轴坐标点
		window.screenTop;
		// 网页正文部分左，x轴坐标点
		window.screenLeft;
		// 屏幕高度
		window.screen.height;
		// 屏幕可用工作区高度
		window.screen.availHeight;
		var videoHeight;
		if ($.browser.safari) {
			height = window.screen.availHeight - 185;
			// var videoHeight = height - 110;

		} else if ($.browser.msie) {
			height = window.screen.availHeight - window.screenTop - 30;
			// videoHeight = height - 110;
			videoHeight = height;

		} else if ($.browser.mozilla) {

			if ($.browser.version == "11.0") {// ie11浏览器处理
				height = window.screen.availHeight - window.screenTop - 30;
				// videoHeight = height - 110;
				videoHeight = height;

			} else {
				height = window.screen.availHeight - window.mozInnerScreenY - 5;
				// 左侧设备树自适应高度
				// videoHeight = height - 110;
				videoHeight = height;

			}
		}
		if (screen == 1 || screen == 2) {// 1屏 ,2屏
			return paraentHeight - 26;
		} else if (screen == 4 || screen == 6 || screen == 16) {// 4屏 6屏 16屏
			return img_4h;
		} else if (screen == 9) {// 9屏
			return img_9h;
		} else {// 1屏
			return paraentHeight;
		}
	},
   /**
    * ckplayer视频加载时代码
    * @param {} screen_index 分屏中的第几屏
    * @param {} videoHeight 高度
    * @param {} serverIp 返回的流媒体服务
    * @param {} rtspName 加载视频的设备code
    */
    old_ckplayerLoad:function(screen_index,videoHeight,serverIp,rtspName){
        //ckplayer视频加载时代码
                  $(divVideo)
                      .append("<object width='100%' height="
                              + videoHeight
                              + " id='videoPlayer"
                              + screen_index
                              + "' name='videoPlayer' type='application/x-shockwave-flash' classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'><param name='movie' value='ckplayer6.3_all/ckplayer/ckplayer.swf' />"
                              + "<param name='quality' value='high' />"
                              + "<param name='bgcolor' value='#000000' />"
                              + "<param name='allowfullscreen' value='true' />"
                              + "<param name='flashvars' id='flashvars' value='f=rtmp://"
                              + serverIp
                              + "/live/"
                              + rtspName
                              + "' />"
                              + "<embed width='100%' height='"
                              + videoHeight
                              + "' src='ckplayer6.3_all/ckplayer/ckplayer.swf' id='videoPlayer"
                              + screen_index
                              + "s' quality='high' bgcolor='#000000' name='videoPlayers' allowfullscreen='true'  flashvars='f=rtmp://"
                              + serverIp + "/live/" + rtspName
                              + "' type='application/x-shockwave-flash'>"
                              + "</embed></object>");
               //alert($(divVideo).html());
              // alert("rtmp://" + serverIp+ "/live/" + rtspName);
    },
    /**
     * 现在使用的videoPlayer视频播放器加载
     * @param {} divVideo 页面显示视频的div对象
     * @param {} screen_index 第几屏，
     * @param {} root 系统路径
     * @param {} serverIp 流媒体服务
     * @param {} rtspName 视频设备流code
     * @param {} width_div 视频宽度
     * @param {} videoHeight 视频高度
     */
    videoplayerLoad_now:function(divVideo,screen_index,root,serverIp,rtspName,width_div,videoHeight){
        
                    // videoPlayer视频加载代码--------------
                    if ($.browser.safari) {
                        $(divVideo)
                                .append("<object type='application/x-shockwave-flash' id='videoPlayer"
                                        + screen_index
                                        + "' data='"
                                        + root
                                        + "/swfs/videoplayer/videoPlayer.swf?videourl=rtmp://"
                                        + serverIp
                                        + "/live/"
                                        + rtspName
                                        + "&amp;width="
                                        + width_div
                                        + "&amp;height="
                                        + videoHeight
                                        + "' width='"
                                        + width_div
                                        + "' height='"
                                        + videoHeight
                                        + "' style='visibility: visible;'><param name='allowfullscreen' value='true'></object>");
                    } else if ($.browser.msie) {
                        $(divVideo)
                                .append("<object width='"
                                        + width_div
                                        + "' height='"
                                        + videoHeight
                                        + "' id='videoPlayer"
                                        + screen_index
                                        + "' classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' style='visibility: visible;'><param name='allowfullscreen' value='true'/><param name='movie' value='"
                                        + root
                                        + "/swfs/videoplayer/videoPlayer.swf?videourl=rtmp://"
                                        + serverIp + "/live/" + rtspName
                                        + "&width=" + width_div + "&height="
                                        + videoHeight + "'/></object>");

                    } else if ($.browser.mozilla) {

                        if ($.browser.version == "11.0") {// ie11浏览器处理
                            $(divVideo)
                                    .append("<object width='"
                                            + width_div
                                            + "' height='"
                                            + videoHeight
                                            + "' id='videoPlayer"
                                            + screen_index
                                            + "' classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' style='visibility: visible;'><param name='allowfullscreen' value='true'/><param name='movie' value='"
                                            + root
                                            + "/swfs/videoplayer/videoPlayer.swf?videourl=rtmp://"
                                            + serverIp + "/live/" + rtspName
                                            + "&width=" + width_div
                                            + "&height=" + videoHeight
                                            + "'/></object>");

                        } else {
                            $(divVideo)
                                    .append("<object type='application/x-shockwave-flash' id='videoPlayer"
                                            + screen_index
                                            + "' data='"
                                            + root
                                            + "/swfs/videoplayer/videoPlayer.swf?videourl=rtmp://"
                                            + serverIp
                                            + "/live/"
                                            + rtspName
                                            + "&amp;width="
                                            + width_div
                                            + "&amp;height="
                                            + videoHeight
                                            + "' width='"
                                            + width_div
                                            + "' height='"
                                            + videoHeight
                                            + "' style='visibility: visible;'><param name='allowfullscreen' value='true'></object>");

                        }
                    }
                
    }
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

function aaa(path, index, lx) {
   //debugger;
	screen_index = index;
	// 获取单拖设备列表
	var dandevices = document.getElementById("dandevices");
    if(dandevices==null){//大华视频
        
        if ('internet' == lx) {
            $("#nopage_iframe").attr(
                    "src",
                    root + "/video/internetdevice/dahuamainindex/" + menuid
                            + "/?screen_index=" + screen_index);
        } else {
            alert("ddd");
            $("#nopage_iframe").attr(
                    "src",
                    root + "/vedio/index/mainindex/" + menuid
                            + "/?screen_index=" + screen_index);
        }
        
        
    }else{
    
        if (dandevices.style.display == "none") {
        gotoScreen(screen_index);
        $("#_left_up").attr('style', 'display:none');
        $("#_right_down").attr('style', 'display:none');
        $("#_left_up").unbind('click');
        $("#_right_down").unbind('click');
    } else {
        if ('internet' == lx) {
            $("#nopage_iframe").attr(
                    "src",
                    root + "/video/internetdevice/mainindex/" + menuid
                            + "/?screen_index=" + screen_index);
        } else {

            $("#nopage_iframe").attr(
                    "src",
                    root + "/vedio/index/mainindex/" + menuid
                            + "/?screen_index=" + screen_index);
        }

    }
    }
	
}
/*
 * 上一页函数 pno--页数 psize--每页显示记录数 ptotalNum--分页总数 treeNodes拖拽的节点
 */
function left(pno, psize, ptotalNum, treeNodes, index_device_data) {
	//正在压缩图片
    var rar_now_display = window.frames['dddddddddddddd'].document.getElementById("rar_now").style.display;
    //停止压缩图片
    var rar_tz_display = window.frames['dddddddddddddd'].document.getElementById("rar_tz").style.display;
    //压缩图片
    var compBtn_display = window.frames['dddddddddddddd'].document.getElementById("CompBtn").style.display;
    if(compBtn_display=='none')
    {
        window.frames['dddddddddddddd'].document.getElementById("CompBtn").style.display="block";
        window.frames['dddddddddddddd'].document.getElementById("rar_now").style.display="none";
        window.frames['dddddddddddddd'].document.getElementById("rar_tz").style.display="none";
        window.frames['dddddddddddddd'].document.getElementById("CompBtn").setAttribute("op","yes");
    }
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
				window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
			}
			// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"].init(treeNodes[0].children[startRow].code);
		}
		if (pageSize == 4) {
			var yushu = num % pageSize;
			if (yushu == 1) {
				window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
			}
			if (yushu == 2) {
				window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
			}
			if (yushu == 3) {
				window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
			}

		}
		if (pageSize == 6) {
			var yushu = num % pageSize;
			if (yushu == 1) {
				window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
			}
			if (yushu == 2) {
				window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
			}
			if (yushu == 3) {
				window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
			}
			if (yushu == 4) {
				window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
						.init(treeNodes[0].children[startRow + 2].code);
			}
			if (yushu == 5) {
				window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
						.init(treeNodes[0].children[startRow + 2].code);
				window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
						.init(treeNodes[0].children[startRow + 3].code);
			}

		}
		if (pageSize == 9) {
			var yushu = num % pageSize;
			if (yushu == 1) {
				window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
			}
			if (yushu == 2) {
				window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
			}
			if (yushu == 3) {
				window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
			}
			if (yushu == 4) {
				window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
						.init(treeNodes[0].children[startRow + 2].code);
			}
			if (yushu == 5) {
				window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
						.init(treeNodes[0].children[startRow + 2].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
						.init(treeNodes[0].children[startRow + 3].code);
			}
			if (yushu == 6) {
				window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
						.init(treeNodes[0].children[startRow + 2].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
						.init(treeNodes[0].children[startRow + 3].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
						.init(treeNodes[0].children[startRow + 4].code);
			}
			if (yushu == 7) {
				window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
						.init(treeNodes[0].children[startRow + 2].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
						.init(treeNodes[0].children[startRow + 3].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
						.init(treeNodes[0].children[startRow + 4].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
						.init(treeNodes[0].children[startRow + 5].code);
			}
			if (yushu == 8) {
				window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
						.init(treeNodes[0].children[startRow - 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
						.init(treeNodes[0].children[startRow].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
						.init(treeNodes[0].children[startRow + 1].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
						.init(treeNodes[0].children[startRow + 2].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
						.init(treeNodes[0].children[startRow + 3].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
						.init(treeNodes[0].children[startRow + 4].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
						.init(treeNodes[0].children[startRow + 5].code);
				window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
						.init(treeNodes[0].children[startRow + 6].code);
			}

		}
	} else {
		if (pageSize == 1) {
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.frames['dddddddddddddd'].frames["oneScreenImg1"].init(
			// treeNodes[0].children[startRow].code,
			// treeNodes[0].children[startRow - 1].code);
			window.frames['dddddddddddddd'].stopvideo(
					treeNodes[0].children[startRow].code, 1);

			oneScreenDevice[0] = treeNodes[0].children[startRow - 1].code;
			var code = treeNodes[0].children[startRow - 1].code;
			var name = treeNodes[0].children[startRow - 1].name;
			window.frames['dddddddddddddd'].xypointMany(code, name);
		}
		if (pageSize == 2) {
			// 判断下一页是不是最后一页
			if (pno == (totalPage - 1)) {
				var yushu = num % pageSize;
				if (yushu == 1) {
					// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 2 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									- 1]].code, 1);
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
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
					twoScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					twoScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 0) {
					// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 2 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									- 1]].code, 1);
					window.frames['dddddddddddddd']
							.stopvideo(
									treeNodes[0].children[index_device_data[startRow]].code,
									2);

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
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
					twoScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					twoScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				}

			} else {
				// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 2 - 1]].code,
				// treeNodes[0].children[index_device_data[startRow
				// - 1]].code);
				// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 2]].code,
				// treeNodes[0].children[index_device_data[startRow]].code);
				window.frames['dddddddddddddd']
						.stopvideo(
								treeNodes[0].children[index_device_data[startRow
										- 1]].code, 1);
				window.frames['dddddddddddddd']
						.stopvideo(
								treeNodes[0].children[index_device_data[startRow]].code,
								2);
				var two_deviceNO = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code;
				var code = treeNodes[0].children[index_device_data[startRow - 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code;
				var name = treeNodes[0].children[index_device_data[startRow - 1]].name
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].name;
				twoScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
						- 1]].code;
				twoScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
			}
		}
		if (pageSize == 4) {
			// 判断下一页是不是最后一页
			if (pno == (totalPage - 1)) {
				var yushu = num % pageSize;
				if (yushu == 1) {
					// window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 4 - 1]].code, 1);
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
					fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 2) {
					// window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
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
					fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 3) {
					// window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
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
					fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 0) {
					// window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 4 + 2]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
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
					fourScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					fourScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					fourScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					fourScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				}

			} else {
				// window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 4 - 1]].code,
				// treeNodes[0].children[index_device_data[startRow
				// - 1]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 4]].code,
				// treeNodes[0].children[index_device_data[startRow]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 4 + 1]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 1]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 4 + 2]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 2]].code);
				var four_deviceNO = treeNodes[0].children[index_device_data[startRow
						- 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].code;
				var code = treeNodes[0].children[index_device_data[startRow - 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 1]].code
						+ ","
						+ treeNodes[0].children[index_device_data[startRow + 2]].code;
				var name = treeNodes[0].children[index_device_data[startRow - 1]].name
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
				window.frames['dddddddddddddd'].xypointMany(code, name);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
			}
		}
		if (pageSize == 6) {
			// 判断下一页是不是最后一页
			if (pno == (totalPage - 1)) {
				var yushu = num % pageSize;
				if (yushu == 1) {
					// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 - 1]].code, 1);
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
									+ 4]].name;
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
				} else if (yushu == 2) {
					// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6]].code, 2);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 3) {
					// alert("yushu==3")
					// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 1]].code, 3);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 4) {
//					window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
//							.init(
//									treeNodes[0].children[index_device_data[startRow
//											+ 6 - 1]].code,
//									treeNodes[0].children[index_device_data[startRow
//											- 1]].code);
//					window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
//							.init(
//									treeNodes[0].children[index_device_data[startRow
//											+ 6]].code,
//									treeNodes[0].children[index_device_data[startRow]].code);
//					window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
//							.init(
//									treeNodes[0].children[index_device_data[startRow
//											+ 6 + 1]].code,
//									treeNodes[0].children[index_device_data[startRow
//											+ 1]].code);
//					window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
//							.init(
//									treeNodes[0].children[index_device_data[startRow
//											+ 6 + 2]].code,
//									treeNodes[0].children[index_device_data[startRow
//											+ 2]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 2]].code, 4);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 5) {
					// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 + 2]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 + 3]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 3]].code, 5);
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
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 0) {
					// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 + 2]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 + 3]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 6 + 4]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 6 + 4]].code, 6);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				}

			} else {
				// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 6 - 1]].code,
				// treeNodes[0].children[index_device_data[startRow
				// - 1]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 6]].code,
				// treeNodes[0].children[index_device_data[startRow]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 6 + 1]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 1]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 6 + 2]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 2]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 6 + 3]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 3]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 6 + 4]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 4]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 6
								- 1]].code, 1);
				window.frames['dddddddddddddd']
						.stopvideo(
								treeNodes[0].children[index_device_data[startRow
										+ 6]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 6
								+ 1]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 6
								+ 2]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 6
								+ 3]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 6
								+ 4]].code, 6);
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
				var code = treeNodes[0].children[index_device_data[startRow - 1]].code
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
				var name = treeNodes[0].children[index_device_data[startRow - 1]].name
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
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
		}
		if (pageSize == 9) {
			// 判断下一页是不是最后一页
			if (pno == (totalPage - 1)) {
				var yushu = num % pageSize;
				if (yushu == 1) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 5]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 7]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 - 1]].code, 1);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 2) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 5]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 7]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9]].code, 2);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 3) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 5]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 7]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 1]].code, 3);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 4) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 2]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 5]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 7]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 2]].code, 4);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 5) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 2]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 3]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 5]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 7]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 3]].code, 5);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 6) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 2]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 3]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 4]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 5]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 4]].code, 6);
//					window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
//							.init(
//									null,
//									treeNodes[0].children[index_device_data[startRow
//											+ 7]].code);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 7) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 2]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 3]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 4]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 5]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 5]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 7]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 5]].code, 7);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 8) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 2]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 3]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 4]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 5]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 5]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 6]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
					// .init(
					// null,
					// treeNodes[0].children[index_device_data[startRow
					// + 7]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 6]].code, 8);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 0) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 - 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// - 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9]].code,
					// treeNodes[0].children[index_device_data[startRow]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 1]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 1]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 2]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 2]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 3]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 3]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 4]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 4]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 5]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 5]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 6]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 6]].code);
					// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
					// .init(
					// treeNodes[0].children[index_device_data[startRow
					// + 9 + 7]].code,
					// treeNodes[0].children[index_device_data[startRow
					// + 7]].code);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 6]].code, 8);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 9 + 7]].code, 9);
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
					window.frames['dddddddddddddd'].xypointMany(code, name);
				}

			} else {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 - 1]].code,
				// treeNodes[0].children[index_device_data[startRow
				// - 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9]].code,
				// treeNodes[0].children[index_device_data[startRow]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 1]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 2]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 3]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 4]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 5]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 6]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 7]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 7]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								- 1]].code, 1);
				window.frames['dddddddddddddd']
						.stopvideo(
								treeNodes[0].children[index_device_data[startRow
										+ 9]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 1]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 2]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 3]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 4]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 5]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 6]].code, 8);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 7]].code, 9);
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
				var code = treeNodes[0].children[index_device_data[startRow - 1]].code
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
				var name = treeNodes[0].children[index_device_data[startRow - 1]].name
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
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
		}
		if (pageSize == 16) {

			// 判断下一页是不是最后一页
			if (pno == (totalPage - 1)) {
				var yushu = num % pageSize;
				if (yushu == 1) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 2) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 3) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 4) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 5) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 6) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
							.init(
									null,
									treeNodes[0].children[index_device_data[startRow
											+ 7]].code);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 7) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 8) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 6]].code, 8);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 9) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 6]].code, 8);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 7]].code, 9);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 10) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 6]].code, 8);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 7]].code, 9);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 8]].code, 10);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 11) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 6]].code, 8);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 7]].code, 9);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 8]].code, 10);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 9]].code, 11);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 12) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 6]].code, 8);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 7]].code, 9);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 8]].code, 10);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 9]].code, 11);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 10]].code, 12);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 13) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 6]].code, 8);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 7]].code, 9);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 8]].code, 10);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 9]].code, 11);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 10]].code, 12);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 11]].code, 13);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 14) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 6]].code, 8);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 7]].code, 9);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 8]].code, 10);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 9]].code, 11);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 10]].code, 12);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 11]].code, 13);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 12]].code, 14);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 15) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 6]].code, 8);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 7]].code, 9);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 8]].code, 10);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 9]].code, 11);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 10]].code, 12);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 11]].code, 13);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 12]].code, 14);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 13]].code, 15);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				} else if (yushu == 0) {
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 - 1]].code, 1);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16]].code, 2);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 1]].code, 3);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 2]].code, 4);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 3]].code, 5);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 4]].code, 6);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 5]].code, 7);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 6]].code, 8);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 7]].code, 9);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 8]].code, 10);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 9]].code, 11);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 10]].code, 12);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 11]].code, 13);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 12]].code, 14);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 13]].code, 15);
					window.frames['dddddddddddddd'].stopvideo(
							treeNodes[0].children[index_device_data[startRow
									+ 16 + 14]].code, 16);
					var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;
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
									+ 7]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 8]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].code
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].code;

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
									+ 8]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 7]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 9]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 10]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 11]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 12]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 13]].name
							+ ","
							+ treeNodes[0].children[index_device_data[startRow
									+ 14]].name;
					// 系统启动时所有的设备信息jms主题，这里不需要请求后台
					// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
					sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
							- 1]].code;
					sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
					sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
							+ 1]].code;
					sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
							+ 2]].code;
					sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
							+ 3]].code;
					sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
							+ 4]].code;
					sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
							+ 5]].code;
					sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
							+ 6]].code;
					sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
							+ 7]].code;
					sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
							+ 8]].code;
					sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
							+ 9]].code;
					nineScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
							+ 10]].code;
					sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
							+ 11]].code;
					sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
							+ 12]].code;
					sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
							+ 13]].code;
					sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
							+ 14]].code;
					window.frames['dddddddddddddd'].xypointMany(code, name);
				}

			} else {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 - 1]].code,
				// treeNodes[0].children[index_device_data[startRow
				// - 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9]].code,
				// treeNodes[0].children[index_device_data[startRow]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 1]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 2]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 3]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 4]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 5]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 6]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[startRow
				// + 9 + 7]].code,
				// treeNodes[0].children[index_device_data[startRow
				// + 7]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								- 1]].code, 1);
				window.frames['dddddddddddddd']
						.stopvideo(
								treeNodes[0].children[index_device_data[startRow
										+ 9]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 1]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 2]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 3]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 4]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 5]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 6]].code, 8);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[startRow + 9
								+ 7]].code, 9);
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
				var code = treeNodes[0].children[index_device_data[startRow - 1]].code
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
				var name = treeNodes[0].children[index_device_data[startRow - 1]].name
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
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}

		}
	}
}

/**
 * 下一页函数 pno--页数 psize--每页显示记录数 ptotalNum--分页总数
 */
function right(pno, psize, ptotalNum, treeNodes, index_device_data) {
    
    //正在压缩是否显示，显示的话变为隐藏
    //正在压缩图片
    var rar_now_display = window.frames['dddddddddddddd'].document.getElementById("rar_now").style.display;
    //停止压缩图片
    var rar_tz_display = window.frames['dddddddddddddd'].document.getElementById("rar_tz").style.display;
    //压缩图片
    var compBtn_display = window.frames['dddddddddddddd'].document.getElementById("CompBtn").style.display;
    if(compBtn_display=='none')
    {
    	debugger;
        window.frames['dddddddddddddd'].document.getElementById("CompBtn").style.display="block";
        window.frames['dddddddddddddd'].document.getElementById("rar_now").style.display="none";
        window.frames['dddddddddddddd'].document.getElementById("rar_tz").style.display="none";
        window.frames['dddddddddddddd'].document.getElementById("CompBtn").setAttribute("op","yes");
    }
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
					// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
					// .unlogout(twoScreenDevice[0]);
					window.frames['dddddddddddddd'].stopvideo(
							twoScreenDevice[0], 1);
				} else if (twoScreenDevice.length == 2) {
					// 注销上一次屏幕刷新设备编号
					// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
					// .unlogout(twoScreenDevice[0]);
					// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
					// .unlogout(twoScreenDevice[1]);
					window.frames['dddddddddddddd'].stopvideo(
							twoScreenDevice[0], 1);
					window.frames['dddddddddddddd'].stopvideo(
							twoScreenDevice[1], 2);
				} else {
				}
			}
			var yushu = num % pageSize;
			if (yushu == 1) {
				// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"].init(treeNodes[0].children[startRow-1].code);
				// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 2
								- 1]].code, 1);
				// alert(window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"].$("#imgpath2").attr('src'));
				var two_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].name;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
				// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
				// .$("#imgpath2").attr('src', 'images/moren_bg.gif');
				twoScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}

		}
		if (pageSize == 4) {
			var yushu = num % pageSize;
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (fourScreenDevice.length != 0) {
				for (var i = 0; i < fourScreenDevice.length; i++) {
					// window.frames['dddddddddddddd'].frames["fourScreenImg_"
					// + (i + 1) + "_iframe"]
					// .unlogout(fourScreenDevice[i]);
					window.frames['dddddddddddddd'].stopvideo(
							sixteenScreenDevice[i], i + 1);
				}
			}
			if (yushu == 1) {
				// window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				var four_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].name;
				window.frames['dddddddddddddd'].xypointMany(code, name);
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
				// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
				// .$("#imgpath2").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 4 + 1]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
				// .$("#imgpath3").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 4 + 2]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
				// .$("#imgpath4").attr('src', 'images/moren_bg.gif');

				fourScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
			}
			if (yushu == 2) {
				// window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				var four_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
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

				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
				// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
				// .$("#imgpath3").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 4 + 1]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
				// .$("#imgpath4").attr('src', 'images/moren_bg.gif');
				fourScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				fourScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 3) {
				// window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
				// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
						.$("#imgpath4").attr('src', 'images/moren_bg.gif');
				fourScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				fourScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				fourScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}

		}
		if (pageSize == 6) {
			var yushu = num % pageSize;
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (sixScreenDevice.length != 0) {
				for (var i = 0; i < sixScreenDevice.length; i++) {
					// window.frames['dddddddddddddd'].frames["sixScreenImg_"
					// + (i + 1) + "_iframe"].unlogout(sixScreenDevice[i]);
					window.frames['dddddddddddddd'].stopvideo(
							sixScreenDevice[i], i + 1);

				}
			}
			if (yushu == 1) {

				// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 1]].code, 1);
				var six_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].name;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
				// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 6]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
				// .$("#imgpath2").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 6 + 1]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
				// .$("#imgpath3").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 6 + 2]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
				// .$("#imgpath4").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 6 + 3]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 6 + 4]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// sixScreenDevice[0] =
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 2) {
//				window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
//						.init(
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 6 - 2]].code,
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 2]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
//						.init(
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 6 - 1]].code,
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 1]].code);
                window.frames['dddddddddddddd'].stopvideo(
                        treeNodes[0].children[index_device_data[ptotalNum - 6
                                - 2]].code, 1);
                window.frames['dddddddddddddd'].stopvideo(
                        treeNodes[0].children[index_device_data[ptotalNum - 6
                                - 1]].code, 2);
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
                window.frames['dddddddddddddd'].stopvideo(
                        treeNodes[0].children[index_device_data[ptotalNum - 6
                                ]].code, 3);
                window.frames['dddddddddddddd'].stopvideo(
                        treeNodes[0].children[index_device_data[ptotalNum - 6
                                + 1]].code, 4);
                window.frames['dddddddddddddd'].stopvideo(
                        treeNodes[0].children[index_device_data[ptotalNum - 6
                                + 2]].code, 5);
                window.frames['dddddddddddddd'].stopvideo(
                        treeNodes[0].children[index_device_data[ptotalNum - 6
                                + 3]].code, 6);             
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
//				window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
//						.unlogout(treeNodes[0].children[index_device_data[ptotalNum
//								- 6]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
//						.$("#imgpath3").attr('src', 'images/moren_bg.gif');
//				window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
//						.unlogout(treeNodes[0].children[index_device_data[ptotalNum
//								- 6 + 1]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
//						.$("#imgpath4").attr('src', 'images/moren_bg.gif');
//				window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
//						.unlogout(treeNodes[0].children[index_device_data[ptotalNum
//								- 6 + 2]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
//						.$("#imgpath5").attr('src', 'images/moren_bg.gif');
//				window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
//						.unlogout(treeNodes[0].children[index_device_data[ptotalNum
//								- 6 + 3]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
//						.$("#imgpath6").attr('src', 'images/moren_bg.gif');
				sixScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
                window.frames['dddddddddddddd'].xypointMany(code, name);        
			}
			if (yushu == 3) {
//				window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
//						.init(
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 3 - 6]].code,
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 3]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
//						.init(
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 2 - 6]].code,
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 2]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
//						.init(
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 1 - 6]].code,
//								treeNodes[0].children[index_device_data[ptotalNum
//										- 1]].code);
				 window.frames['dddddddddddddd']
                        .stopvideo(
                                treeNodes[0].children[index_device_data[ptotalNum-3
                                        - 6]].code, 1);
                window.frames['dddddddddddddd']
                        .stopvideo(
                                treeNodes[0].children[index_device_data[ptotalNum
                                        -2- 6]].code, 2);
                window.frames['dddddddddddddd'].stopvideo(
                        treeNodes[0].children[index_device_data[ptotalNum -1- 6
                                ]].code, 3);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
//				window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
//						.unlogout(treeNodes[0].children[index_device_data[ptotalNum
//								- 6]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
//						.$("#imgpath4").attr('src', 'images/moren_bg.gif');
//				window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
//						.unlogout(treeNodes[0].children[index_device_data[ptotalNum
//								- 6 + 1]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
//						.$("#imgpath5").attr('src', 'images/moren_bg.gif');
//				window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
//						.unlogout(treeNodes[0].children[index_device_data[ptotalNum
//								- 6 + 2]].code);
//				window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
//						.$("#imgpath6").attr('src', 'images/moren_bg.gif');
                 window.frames['dddddddddddddd']
                        .stopvideo(
                                treeNodes[0].children[index_device_data[ptotalNum
                                        - 6]].code, 4);
                window.frames['dddddddddddddd']
                        .stopvideo(
                                treeNodes[0].children[index_device_data[ptotalNum
                                        +1- 6]].code, 5);
                window.frames['dddddddddddddd'].stopvideo(
                        treeNodes[0].children[index_device_data[ptotalNum +2- 6
                                ]].code, 6);
				sixScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
                window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 4) {
				// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 4]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 3]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 2]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 1]].code, 4);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
				// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 6]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 6 + 1]].code);
				window.frames['dddddddddddddd']
						.stopvideo(
								treeNodes[0].children[index_device_data[ptotalNum
										- 6]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								+ 1]].code, 6);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				sixScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 5) {
				// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 5]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 5]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 5]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 4]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 3]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 2]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 6
								- 1]].code, 5);
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
				// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 6]].code);
				// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
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
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}

		}
		if (pageSize == 9) {
			var yushu = num % pageSize;
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (nineScreenDevice.length != 0) {
				for (var i = 0; i < nineScreenDevice.length; i++) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_"
					// + (i + 1) + "_iframe"]
					// .unlogout(nineScreenDevice[i]);
					window.frames['dddddddddddddd'].stopvideo(
							nineScreenDevice[i], i + 1);
				}
			}
			if (yushu == 1) {

				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 1]].code, 1);
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].name;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .$("#imgpath2").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .$("#imgpath3").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .$("#imgpath4").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 7]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 2) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 2]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 1]].code, 2);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .$("#imgpath3").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .$("#imgpath4").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 3) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 3]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 2]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 1]].code, 3);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .$("#imgpath4").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 4) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 4]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 3]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 2]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 1]].code, 4);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				nineScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				nineScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				nineScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				nineScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 5) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 5]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 5]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 4]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 3]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 2]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 1]].code, 5);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
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
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 6) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 6]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 5]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 6]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 5]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 4]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 3]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 2]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 1]].code, 6);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
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
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 7) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 7]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 7]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 6]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 5]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 7]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 6]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 5]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 4]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 3]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 2]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 1]].code, 7);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
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
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 8) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 8]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 8]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 7]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 7]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 6]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 5]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 8]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 7]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 6]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 5]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 4]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 3]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 2]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 9
								- 1]].code, 8);

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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
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
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}

		}
		if (pageSize == 16) {

			var yushu = num % pageSize;
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (sixteenScreenDevice.length != 0) {
				for (var i = 0; i < sixteenScreenDevice.length; i++) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_"
					// + (i + 1) + "_iframe"]
					// .unlogout(nineScreenDevice[i]);
					window.frames['dddddddddddddd'].stopvideo(
							sixteenScreenDevice[i], i + 1);
				}
			}
			if (yushu == 1) {

				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 1);
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var code = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				var name = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].name;
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .$("#imgpath2").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .$("#imgpath3").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .$("#imgpath4").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 7]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 2) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 2);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .$("#imgpath3").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .$("#imgpath4").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 3) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 3);
				var sixteen_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .$("#imgpath4").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 4) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 4);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .$("#imgpath5").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 5) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 5]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 5);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .$("#imgpath6").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 6) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 6]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 5]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 6);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
				// .$("#imgpath7").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 7) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 7]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 7]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 6]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 5]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 7]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 7);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
				// .$("#imgpath8").attr('src', 'images/moren_bg.gif');
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9 + 1]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 8) {
				// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 8]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 8]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 7]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 7]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 6]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 6]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 5]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 5]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 4]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 4]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 3]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 3]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 2]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 2]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
				// .init(
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 9 - 1]].code,
				// treeNodes[0].children[index_device_data[ptotalNum
				// - 1]].code);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 8]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 7]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 8);
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 9) {
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 9]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 8]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 7]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 8);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 9);
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 9]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].name
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 9]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 10) {
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 10]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 9]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 8]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 7]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 8);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 9);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 10);
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 10]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].name
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 10]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 9]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 11) {
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 11]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 10]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 9]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 8]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 7]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 8);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 9);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 10);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 11);
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 11]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].name
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 11]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 10]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 9]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 12) {
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 12]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 11]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 10]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 9]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 8]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 7]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 8);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 9);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 10);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 11);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 12);
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 12]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 12]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 12]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].name
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 12]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 11]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 10]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 9]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[11] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 13) {
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 13]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 12]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 11]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 10]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 9]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 8]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 7]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 8);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 9);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 10);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 11);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 12);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 13);
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 13]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 12]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 13]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 12]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 13]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 12]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].name
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 13]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 12]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 11]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 10]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 9]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code;
				sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[11] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 14) {
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 14]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 13]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 12]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 11]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 10]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 9]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 8]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 7]].code, 8);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 9);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 10);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 11);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 12);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 13);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 14);
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 14]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 13]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 12]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 14]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 13]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 12]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 14]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 13]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 12]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].name
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 14]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 13]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 12]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 11]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 10]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 9]].code;
				sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code;
				sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[11] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}
			if (yushu == 15) {
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 15]].code, 1);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 14]].code, 2);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 13]].code, 3);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 12]].code, 4);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 11]].code, 5);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 10]].code, 6);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 9]].code, 7);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 8]].code, 8);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 7]].code, 9);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 6]].code, 10);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 5]].code, 11);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 4]].code, 12);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 3]].code, 13);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 2]].code, 14);
				window.frames['dddddddddddddd'].stopvideo(
						treeNodes[0].children[index_device_data[ptotalNum - 16
								- 1]].code, 15);
				var nine_deviceNO = treeNodes[0].children[index_device_data[ptotalNum
						- 15]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 14]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 13]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 12]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 15]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 14]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 13]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 12]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].code
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].code
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
						- 15]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 14]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 13]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 12]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 11]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum
								- 10]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 9]].name
						+ ","
						+ treeNodes[0].children[index_device_data[ptotalNum - 8]].name
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
				// 系统启动时所有的设备信息jms主题，这里不需要请求后台
				// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .unlogout(treeNodes[0].children[index_device_data[ptotalNum
				// - 9]].code);
				// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
				// .$("#imgpath9").attr('src', 'images/moren_bg.gif');
				sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[ptotalNum
						- 15]].code;
				sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[ptotalNum
						- 14]].code;
				sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[ptotalNum
						- 13]].code;
				sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[ptotalNum
						- 12]].code;
				sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[ptotalNum
						- 11]].code;
				sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[ptotalNum
						- 10]].code;
				sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[ptotalNum
						- 9]].code;
				sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[ptotalNum
						- 8]].code;
				sixteenScreenDevice[8] = treeNodes[0].children[index_device_data[ptotalNum
						- 7]].code;
				sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[ptotalNum
						- 6]].code;
				sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[ptotalNum
						- 5]].code;
				sixteenScreenDevice[11] = treeNodes[0].children[index_device_data[ptotalNum
						- 4]].code;
				sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[ptotalNum
						- 3]].code;
				sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[ptotalNum
						- 2]].code;
				sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[ptotalNum
						- 1]].code;
				window.frames['dddddddddddddd'].xypointMany(code, name);
			}

		}
	} else {
		if (pageSize == 1) {
			// window.frames['dddddddddddddd'].frames["oneScreenImg1"].init(
			// treeNodes[0].children[startRow - 2].code,
			// treeNodes[0].children[startRow - 1].code);
			window.frames['dddddddddddddd'].stopvideo(
					treeNodes[0].children[startRow - 2].code, 1);
			oneScreenDevice[0] = treeNodes[0].children[startRow - 1].code;
			var code = treeNodes[0].children[startRow - 1].code;
			var name = treeNodes[0].children[startRow - 1].name;
			window.frames['dddddddddddddd'].xypointMany(code, name);
		}
		if (pageSize == 2) {
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (twoScreenDevice.length != 0) {
				if (twoScreenDevice.length == 1) {
					// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
					// .unlogout(twoScreenDevice[0]);
					window.frames['dddddddddddddd'].stopvideo(
							twoScreenDevice[0], 1);
				} else if (twoScreenDevice.length == 2) {
					// 注销上一次屏幕刷新设备编号
					// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
					// .unlogout(twoScreenDevice[0]);
					// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
					// .unlogout(twoScreenDevice[1]);
					window.frames['dddddddddddddd'].stopvideo(
							twoScreenDevice[0], 1);
					window.frames['dddddddddddddd'].stopvideo(
							twoScreenDevice[1], 2);
				} else {
				}
			}
			// window.frames['dddddddddddddd'].frames["twoScreenImg_1_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 2 - 1]].code,
			// treeNodes[0].children[index_device_data[startRow
			// - 1]].code);
			// window.frames['dddddddddddddd'].frames["twoScreenImg_2_iframe"]
			// .init(
			// treeNodes[0].children[index_device_data[startRow
			// - 2]].code,
			// treeNodes[0].children[index_device_data[startRow]].code);
			var two_deviceNO = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code;
			var code = treeNodes[0].children[index_device_data[startRow - 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code;
			var name = treeNodes[0].children[index_device_data[startRow - 1]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].name;
			twoScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
					- 1]].code;
			twoScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
			window.frames['dddddddddddddd'].xypointMany(code, name);
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=2&deviceCode='+two_deviceNO;
		}
		if (pageSize == 4) {
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (fourScreenDevice.length != 0) {
				for (var i = 0; i < fourScreenDevice.length; i++) {
					// window.frames['dddddddddddddd'].frames["fourScreenImg_"
					// + (i + 1) + "_iframe"]
					// .unlogout(fourScreenDevice[i]);
					window.frames['dddddddddddddd'].stopvideo(
							fourScreenDevice[i], i + 1);
				}
			}
			// window.frames['dddddddddddddd'].frames["fourScreenImg_1_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 4 - 1]].code,
			// treeNodes[0].children[index_device_data[startRow
			// - 1]].code);
			// window.frames['dddddddddddddd'].frames["fourScreenImg_2_iframe"]
			// .init(
			// treeNodes[0].children[index_device_data[startRow
			// - 4]].code,
			// treeNodes[0].children[index_device_data[startRow]].code);
			// window.frames['dddddddddddddd'].frames["fourScreenImg_3_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 4 + 1]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 1]].code);
			// window.frames['dddddddddddddd'].frames["fourScreenImg_4_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 4 + 2]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 2]].code);
			var four_deviceNO = treeNodes[0].children[index_device_data[startRow
					- 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].code;
			var code = treeNodes[0].children[index_device_data[startRow - 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 1]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 2]].code;
			var name = treeNodes[0].children[index_device_data[startRow - 1]].name
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
			window.frames['dddddddddddddd'].xypointMany(code, name);
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=4&deviceCode='+four_deviceNO;
		}
		if (pageSize == 6) {
			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (sixScreenDevice.length != 0) {
				for (var i = 0; i < sixScreenDevice.length; i++) {
					// window.frames['dddddddddddddd'].frames["sixScreenImg_"
					// + (i + 1) + "_iframe"].unlogout(sixScreenDevice[i]);
					window.frames['dddddddddddddd'].stopvideo(
							sixScreenDevice[i], i + 1);
				}
			}
			// window.frames['dddddddddddddd'].frames["sixScreenImg_1_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 6 - 1]].code,
			// treeNodes[0].children[index_device_data[startRow
			// - 1]].code);
			// window.frames['dddddddddddddd'].frames["sixScreenImg_2_iframe"]
			// .init(
			// treeNodes[0].children[index_device_data[startRow
			// - 6]].code,
			// treeNodes[0].children[index_device_data[startRow]].code);
			// window.frames['dddddddddddddd'].frames["sixScreenImg_3_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 6 + 1]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 1]].code);
			// window.frames['dddddddddddddd'].frames["sixScreenImg_4_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 6 + 2]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 2]].code);
			// window.frames['dddddddddddddd'].frames["sixScreenImg_5_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 6 + 3]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 3]].code);
			// window.frames['dddddddddddddd'].frames["sixScreenImg_6_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 6 + 4]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 4]].code);
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
			var code = treeNodes[0].children[index_device_data[startRow - 1]].code
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
			var name = treeNodes[0].children[index_device_data[startRow - 1]].name
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
			window.frames['dddddddddddddd'].xypointMany(code, name);

			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=6&deviceCode='+six_deviceNO;
		}
		if (pageSize == 9) {

			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (nineScreenDevice.length != 0) {
				for (var i = 0; i < nineScreenDevice.length; i++) {
					// window.frames['dddddddddddddd'].frames["nineScreenImg_"
					// + (i + 1) + "_iframe"]
					// .unlogout(nineScreenDevice[i]);
					window.frames['dddddddddddddd'].stopvideo(
							nineScreenDevice[i], i + 1);
				}
			}

			// window.frames['dddddddddddddd'].frames["nineScreenImg_1_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 9 - 1]].code,
			// treeNodes[0].children[index_device_data[startRow
			// - 1]].code);
			// window.frames['dddddddddddddd'].frames["nineScreenImg_2_iframe"]
			// .init(
			// treeNodes[0].children[index_device_data[startRow
			// - 9]].code,
			// treeNodes[0].children[index_device_data[startRow]].code);
			// window.frames['dddddddddddddd'].frames["nineScreenImg_3_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 9 + 1]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 1]].code);
			// window.frames['dddddddddddddd'].frames["nineScreenImg_4_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 9 + 2]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 2]].code);
			// window.frames['dddddddddddddd'].frames["nineScreenImg_5_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 9 + 3]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 3]].code);
			// window.frames['dddddddddddddd'].frames["nineScreenImg_6_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 9 + 4]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 4]].code);
			// window.frames['dddddddddddddd'].frames["nineScreenImg_7_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 9 + 5]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 6]].code);
			// window.frames['dddddddddddddd'].frames["nineScreenImg_8_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 9 + 6]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 6]].code);
			// window.frames['dddddddddddddd'].frames["nineScreenImg_9_iframe"]
			// .init( treeNodes[0].children[index_device_data[startRow
			// - 9 + 7]].code,
			// treeNodes[0].children[index_device_data[startRow
			// + 7]].code);
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
			var code = treeNodes[0].children[index_device_data[startRow - 1]].code
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
			var name = treeNodes[0].children[index_device_data[startRow - 1]].name
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
			window.frames['dddddddddddddd'].xypointMany(code, name);
			// 系统启动时所有的设备信息jms主题，这里不需要请求后台
			// window.location.href='${root}/cross/monitore/init?screen=9&deviceCode='+nine_deviceNO;
		}
		if (pageSize == 16) {

			// 判断当前刷新界面是否有屏在刷新，0是没有
			if (sixteenScreenDevice.length != 0) {
				for (var i = 0; i < sixteenScreenDevice.length; i++) {
					window.frames['dddddddddddddd'].stopvideo(
							sixteenScreenDevice[i], i + 1);
				}
			}

			var sixteen_deviceNO = treeNodes[0].children[index_device_data[startRow
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
					+ treeNodes[0].children[index_device_data[startRow + 7]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 8]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 9]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 10]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 11]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 12]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 13]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 14]].code;
			var code = treeNodes[0].children[index_device_data[startRow - 1]].code
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
					+ treeNodes[0].children[index_device_data[startRow + 7]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 8]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 9]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 10]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 11]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 12]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 13]].code
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 14]].code;
			var name = treeNodes[0].children[index_device_data[startRow - 1]].name
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
					+ treeNodes[0].children[index_device_data[startRow + 7]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 8]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 9]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 10]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 11]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 12]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 13]].name
					+ ","
					+ treeNodes[0].children[index_device_data[startRow + 14]].name;
			sixteenScreenDevice[0] = treeNodes[0].children[index_device_data[startRow
					- 1]].code;
			sixteenScreenDevice[1] = treeNodes[0].children[index_device_data[startRow]].code;
			sixteenScreenDevice[2] = treeNodes[0].children[index_device_data[startRow
					+ 1]].code;
			sixteenScreenDevice[3] = treeNodes[0].children[index_device_data[startRow
					+ 2]].code;
			sixteenScreenDevice[4] = treeNodes[0].children[index_device_data[startRow
					+ 3]].code;
			sixteenScreenDevice[5] = treeNodes[0].children[index_device_data[startRow
					+ 4]].code;
			sixteenScreenDevice[6] = treeNodes[0].children[index_device_data[startRow
					+ 5]].code;
			sixteenScreenDevice[7] = treeNodes[0].children[index_device_data[startRow
					+ 6]].code;
			nineScreenDevice[8] = treeNodes[0].children[index_device_data[startRow
					+ 7]].code;
			sixteenScreenDevice[9] = treeNodes[0].children[index_device_data[startRow
					+ 8]].code;
			sixteenScreenDevice[10] = treeNodes[0].children[index_device_data[startRow
					+ 9]].code;
			sixteenScreenDevice[11] = treeNodes[0].children[index_device_data[startRow
					+ 10]].code;
			sixteenScreenDevice[12] = treeNodes[0].children[index_device_data[startRow
					+ 11]].code;
			sixteenScreenDevice[13] = treeNodes[0].children[index_device_data[startRow
					+ 12]].code;
			sixteenScreenDevice[14] = treeNodes[0].children[index_device_data[startRow
					+ 13]].code;
			sixteenScreenDevice[15] = treeNodes[0].children[index_device_data[startRow
					+ 14]].code;
			sixteenScreenDevice[16] = treeNodes[0].children[index_device_data[startRow
					+ 15]].code;
			window.frames['dddddddddddddd'].xypointMany(code, name);
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
