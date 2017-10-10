MapFactory
.Define(
		"ItmsMap/UserLayers/UserLayers*",
		[ "ItmsMap/ModuleConfig*", "ItmsMap/Util/ModuleManager*",
			"ItmsMap/Util/Legend*",
			"ItmsMap/UserLayers/CustomLayersConfig*",
			"ItmsMap/MapConfig", "MapFactory/Util/Dialog*",
			"MapFactory/MapManager", "MapFactory/LayerManager" ,"ItmsMap/UserLayers/DataController*"
			,"ItmsMap/UserLayers/CustomLayers/SamplingPoint*","ItmsMap/SymbolConfig*","MapFactory/GraphicManager"],
			function(ModuleConfig, ModuleManager, Legend,
					CustomLayersConfig, MapConfig, Dialog, MapManager,
					LayerManager,DataController,SamplingPoint,SymbolConfig,GraphicManager) {
			return function(conf) {
				var api = {
						init : init,
						showMoudle : showMoudle,
						show : show,
						loadDataByTimeChange:loadDataByTimeChange
				};

				var index = 0;
				var i = 0;
				var moduleIds = ModuleManager.getModuleIds();
				var baseLayerPath = "ItmsMap/UserLayers/CustomLayers/";
				var layersData = {};
				var mapManager = MapManager();
				
				var layers = MapConfig.layers;
				var dataController=DataController();
				var _dom = MapFactory.Dom;
				var selectedcydMenuItem=[];
				var _conf = {
						moduleIds : "",
						mapDiv : "",
						right : 10,
						top : 35
				};
				MapFactory.Extend(_conf, conf);
				moduleIds = _conf.moduleIds;
				var dataList;
				var _panel = Dialog({
					mapDiv : _conf.mapDiv,
					mutiDialog : true,
					mutiDialogSeed : "CustomLayers",
					mask : false,
					title : '用户图层',
					right : _conf.right,
					top : _conf.top,
					buttonDisplay : {
						confirmButton : false,
						cancelButton : false
					}
				});
				var contentid = "userCustomLayers";
				var itemLyrWidget = {};
				var inputboxs = [];
				
				var regionArr=[];
				var pointArr=[];
				
				function init() {
					var contentStr = "<div id='"
						+ contentid
						+ "' style='width:200px;max-height:400px;_height:expression(this.scrollHeight>400?\"400px\":\"auto\");overflow-y:auto;overflow-x:hidden;'></div>";
					_panel.setDialogContent(contentStr).show();
					
					initUserLayers();
					var spaceTime = DataController().gettimeSpace();
					//请求统计信息
					MapFactory.XHR.Post(
							path+ "/map/pollute/region/",spaceTime,
							function(list) {
								dataController.setCountData(list);
								//请求菜单信息
								MapFactory.XHR.Post(path
										+ "/map/menus/" + menuid + "/",
										function(target) {
									dataController.setMenuData(target);
									
									pointArr=target[2].children;
									
									regionArr=target[0].children;
									
									dataList = target;
									generateItem();
									
								});

							});
					
					// addModuleLayers("basemap", "");// 添加地图

					// mapManager.setLayerAddEvent(function(layerid){
					// resetCheckBox(layerid,true);
					// });

					// mapManager.setLayerRemoveEvent(function(layerid){
					// resetCheckBox(layerid,false);
					// });
				}
				//重新绘制所有区域点位
				function loadDataByTimeChange(){
					//各个县区按照统计值进行渲染  其次各个区县分许值展示
			        var spaceTime = DataController().gettimeSpace();
				    MapFactory.XHR.Post(path + "/map/pollute/region/",
									spaceTime, function(list) {
				    	//只有普查分析用到该数据  位置信息可能也用到
						dataController.setCountData(list);
						
						SamplingPoint().drawRegionByTimeChange();
					});
					
				}
				
				
				function initUserLayers(){
					LayerManager("ysfxLyr");
					LayerManager("xzqyLyrPologn");
					LayerManager("hightLightLyr");
					LayerManager("landLyrPologn");
					LayerManager("tempLandLyrPoint");
					LayerManager("landLyrPoint");
					LayerManager("xzqyLyrPoint");
					LayerManager("pccydLayerPoint");
					LayerManager("lcpjdLyrPoint");
					LayerManager("ydjkLayerPoint");
					LayerManager("wrcpLyr");
				}

				/**
				 * 菜单生成
				 */
				function generateItem() {
					for (var i = 0, len = moduleIds.length; i < len; i++) {
						addModuleLayers(ModuleConfig[moduleIds[i]],
								moduleIds[i]);
					}
					for (var k = 0, len = moduleIds.length; k < len; k++) {
						var id = ModuleConfig[moduleIds[k]];
						if (CustomLayersConfig[id]
						&& CustomLayersConfig[id]["isOpen"] == true) {
							loadLyrItem(CustomLayersConfig[id]);
						}
					}
				}
				//将点图层提在最顶层
				function reorderUserLayer(){
//					if(mapManager.getLevel()==6){
//						return;
//					}
//					for ( var elem in layers) {
//						var layerid=layers[elem].id;
//						var layeridLength = layerid.length;
//						var str = "Point";
//						var layeridLast = layerid.lastIndexOf(str);
//						if(mapManager.isLayerExist(layerid) && layeridLength-str.length === layeridLast){
//							mapManager.reorderLayer(layerid,mapManager.getAllLayersID().length-1);
//						}
//					}
				}

				function show() {
					_panel.show();
				}

				function resetCheckBox(layerid, isOpen) {
					var contentbox = _dom.getById(contentid);
					inputboxs = [];
					iteratorbox(contentbox);
					for (var i = 0, len = inputboxs.length; i < len; i++) {
						var inputbox = inputboxs[i];
						var classname = _dom
						.attr(inputbox, "className");
						var layername = _dom
						.attr(inputbox, "layerName");
						if (layername == layerid) {
							var _parentLayer = $(inputbox).parent()
							.parent().parent().siblings("div")
							.find("input");
							if (isOpen) {
								inputbox.checked = true;
								// _dom.attr(inputbox,"checked","checked");
								layersData[classname][layername]["isOpen"] = true;
								if (_parentLayer && _parentLayer.length)
									_parentLayer[0].checked = true;
							} else {
								inputbox.checked = false;
								// _dom.removeAttr(inputbox,"checked");
								layersData[classname][layername]["isOpen"] = false;
								if (_parentLayer && _parentLayer.length)
									_parentLayer[0].checked = false;
							}
						}
					}
				}

				function iteratorbox(elem) {
					var children = _dom.children(elem);
					for (var i = 0, len = children.length; i < len; i++) {
						if (_dom.children(children[i]).length) {
							iteratorbox(children[i]);
						} else {
							if (_dom.attr(children[i], "type") == "checkbox"
								&& _dom.attr(children[i],
								"className") != "BaseMap") {
								inputboxs.push(children[i]);
							}
						}
					}
				}

				function addModuleLayers(id, moduleId) {
					if (!CustomLayersConfig[id]) {
						return;
					}
					var moduleObj = CustomLayersConfig[id];
					var menuItem = getMenuItemById(id);
					//根据菜单生成的菜单
					if (menuItem) {
						moduleObj.itemListData = menuItem.children;
						moduleObj.moduleid = moduleId;
						moduleObj.label = menuItem.name;
					}else{
						moduleObj.itemListData = moduleObj.layers;
						moduleObj.moduleid = moduleId;
					}
					addGroup(moduleObj);
				}

				function getMenuItemById(id) {
					for (var m = 0; m < dataList.length; m++) {
						var menuItem = dataList[m];
						if (menuItem["url"] == id) {
							return menuItem;
						}
					}
					return null;
				}

				function addGroup(data) {

					var contentBox = _dom.getById(contentid);
					var layerGroupBox = _dom.createElem("div");
					var layerGroupTitle = _dom.createElem("div");
					var layerGroupContainer = _dom.createElem("ul");
					var layerTitleCollapse = _dom.createElem("div");
					var layerTitlecheckBoxC = _dom.createElem("span");
					var layerTitlecheckBox = _dom.createElem("input");
					var layerTitlecheckBoxText = _dom
					.createElem("span");
					var layerTitleB = _dom.createElem("b");
					var clearDiv = _dom.createElem("div");
					var className = data["class"];
					
					if(!data.isShowCol){
						$(layerTitleCollapse).css({visibility:"hidden"});
					}
					if(!data.isShowAC){
						$(layerTitlecheckBoxC).css({display:"none"});
						$(layerTitlecheckBoxC).css({visibility:"hidden"});
					}
					
					_dom.addClass(clearDiv, "clear");
					_dom.addClass(layerGroupBox, "userLayerGroupBox");
					_dom.addClass(layerGroupTitle,
					"userLayerGroupTitle");
					_dom.addClass(layerGroupContainer,
					"userLayersControler");
					_dom
					.addClass(layerTitleCollapse,
					"userLayerGroupCollapse userGroupUnCollapse");
					_dom.addClass(layerTitlecheckBoxC, "checkbox");
					_dom.addClass(layerTitlecheckBoxText,
					"checkboxText");

					_dom.attr(layerTitleCollapse, "id",
							"userLayerTitleCollapse_" + i);
					_dom.attr(layerGroupContainer, "id",
							"userLayersControler_" + i);
					_dom.attr(layerTitlecheckBox, "className",
							className);
					_dom.attr(layerTitlecheckBox, "type", "checkbox");
					// _dom.attr(layerTitlecheckBox,"value",data["moduleid"]+","+data["label"]);
					_dom.attr(layerTitlecheckBox, "moduleId",
							data["moduleid"]);
					_dom.attr(layerTitlecheckBox, "moduleName",
							data["label"]);
					_dom.attr(layerTitlecheckBox, "itemDataList",
							MapFactory.JSON
							.Stringify(data["itemListData"]));

					_dom.html(layerTitleB, data["label"]);
					_dom.append(layerTitlecheckBoxText, layerTitleB);
					_dom
					.append(layerTitlecheckBoxC,
							layerTitlecheckBox);
					_dom.append(layerGroupTitle, layerTitleCollapse);
					_dom.append(layerGroupTitle, layerTitlecheckBoxC);
					_dom
					.append(layerGroupTitle,
							layerTitlecheckBoxText);
					_dom.append(layerGroupBox, layerGroupTitle);
					_dom.append(layerGroupBox, clearDiv);
					_dom.append(layerGroupBox, layerGroupContainer);
					_dom.append(contentBox, layerGroupBox);

					if (data["isOpen"]) {
						_dom.attr(layerTitlecheckBox, "checked",
						"checked");
					}

					if (data["isOpen"]) {
						$("#userLayerTitleCollapse_" + i).parent()
						.siblings("ul").slideDown();
						$("#userLayerTitleCollapse_" + i).removeClass(
						"userGroupCollapse").addClass(
						"userGroupUnCollapse");
					} else {
						$("#userLayerTitleCollapse_" + i).parent()
						.siblings("ul").slideUp();
						$("#userLayerTitleCollapse_" + i).removeClass(
						"userGroupUnCollapse").addClass(
						"userGroupCollapse");
					}

					// 全选按钮控制事件
					layerTitlecheckBox.onclick = function() {
						var _className = _dom.attr(this, "className");
						var _module = _dom.attr(this, "value");
						var _moduleId = _dom.attr(this, "moduleId");
						var _moduleName = _dom.attr(this, "moduleName");
						var itemListData = _dom.attr(this,
						"itemDataList");
						itemListData = MapFactory.JSON
						.Parse(itemListData);
						var _isChecked = this.checked;
						// var _moduleId = _module.split(",")[0];
						// var _moduleName = _module.split(",")[1];
						var _subLayers = $(this).parent().parent()
						.siblings("ul").find("input");
						for (var j = _subLayers.length - 1; j >= 0; j--) {
							_subLayers[j].checked = this.checked;
							var _layerName = _dom.attr(_subLayers[j],
							"layerName");
							layersData[_className][_layerName]["isOpen"] = this.checked;
							// if (this.checked) {
							// if (mapManager.isLayerExist(_layerName))
							// LayerManager(_layerName).show();
							// } else {
							// if (mapManager.isLayerExist(_layerName))
							// LayerManager(_layerName).hide();
							// }
						}
						if(_isChecked){
							if(_className=="SamplingPoint"){
								pointArr=itemListData;
							}
							if(_className=="Regions"){
								regionArr=itemListData;
							}
						}else{
							if(_className=="SamplingPoint"){
								pointArr=[];
							}
							if(_className=="Regions"){
								regionArr=[];
							}
						}
						
						
						MapFactory.Require(
								[ baseLayerPath + _className+ "*" ],
									function(target) {
									// LayerManager(_layerName).clear();
									itemLyrWidget = target();
									itemLyrWidget.clearAllGraphic(itemListData);
									if (_isChecked) {
										reorderUserLayer();
										if(_className=="SamplingPoint"){
											itemLyrWidget.drawRegions(pointArr,regionArr);
										}else{
											itemLyrWidget.drawRegions(itemListData,[]);
											if(_className=="Regions"){
												loadCydInfo(_className);
											}
										}
										
									}else{
										if(_className=="SamplingPoint"){
											itemLyrWidget.drawRegions([],[]);
										}
										if(_className=="Regions"){
											clearCydInfo("Regions");
										}
									}
								});
					}

					$("#userLayerTitleCollapse_" + i)
					.click(
							function() {
								if ($(this).hasClass(
								"userGroupUnCollapse")) {
									// $("#userLayersControler_3").slideUp();
									$(this).parent().siblings(
									"ul").slideUp();
									$(this)
									.removeClass(
									"userGroupUnCollapse")
									.addClass(
									"userGroupCollapse");
								} else {
									// $("#userLayersControler_3").slideDown();
									$(this).parent().siblings(
									"ul").slideDown();
									$(this)
									.removeClass(
									"userGroupCollapse")
									.addClass(
									"userGroupUnCollapse");
								}
							});
					i++;

					layersData[data["class"]] = {};

					var customLayers = data.layers;
					if(!data.isChildren){
						return;
					}
					for ( var elem in customLayers) {
						if (layers[elem]) {
							customLayers[elem]["name"] = layers[elem]["name"];
							customLayers[elem]["id"] = layers[elem]["id"];
							customLayers[elem]["moduleid"] = data["moduleid"];
							customLayers[elem]["label"] = data["label"];
						}
						var itemListData = data.itemListData;
						//根据数据生成菜单
						if (MapFactory.VariableTypes.isArray(itemListData)) {
							for (var m = 0; m < itemListData.length; m++) {
								var itemData = itemListData[m];
								addItem(customLayers[elem],
										layerGroupContainer, className,
										itemData);
							}
						}else{
							//根据服务生成菜单
							addItem(customLayers[elem],layerGroupContainer, className,customLayers[elem]);
							
						}
						
						if (customLayers[elem]["isOpen"]) {
							flag = true;
						}
					}

				}
				function loadCydInfo(className){
					if(className=="SamplingPoint" || className=="Regions"){
						MapFactory.Require([ baseLayerPath +"SamplingPoint*" ], 
								function(target){
							target().drawRegions(pointArr,regionArr);
						});
					}
				}
				function clearCydInfo(className){
					if(className=="SamplingPoint" || className=="Regions"){
						MapFactory.Require([ baseLayerPath +"SamplingPoint*" ], 
								function(target){
							target().drawRegions([],[]);
						});
					}
				}
				//
				function loadLyrItem(data) {
					MapFactory.Require([ baseLayerPath + data["class"]
					+ "*" ], function(target) {
						// 绘制区域多边形
						itemLyrWidget = target();
						itemLyrWidget.clearAllGraphic(data.itemListData);
						if(data["class"]=="SamplingPoint"){
							itemLyrWidget.drawRegions(pointArr,regionArr)
						}else{
							itemLyrWidget.drawRegions(data.itemListData,[]);
						}
						
						reorderUserLayer();
					});
				}
				function addItem(item, groupBox, className, itemData) {

					var itemObj = itemData;
					var layerContainer = _dom.createElem("li");
					var checkBoxC = _dom.createElem("span");
					var checkBox = _dom.createElem("input");
					var checkBoxText = _dom.createElem("span");

					_dom.attr(checkBox, "type", "checkbox");
					_dom.attr(checkBox, "className", className);
					_dom.attr(checkBox, "layerName", item["id"]);
					_dom.attr(checkBox, "moduleId", item["moduleid"]);
					_dom.attr(checkBox, "moduleName", item["label"]);
					_dom.attr(checkBox, "itemData", MapFactory.JSON
							.Stringify(itemData));
					_dom.html(checkBoxText, itemObj["name"]);

					// _dom.addClass(layerContainer,"MapDragAbleTarget_toc");
					_dom.addClass(checkBoxC, "checkbox");
					_dom.addClass(checkBoxText, "checkboxText");

					_dom.append(checkBoxC, checkBox);
					_dom.append(layerContainer, checkBoxC);
					_dom.append(layerContainer, checkBoxText);
					// _dom.append(layerContainer, contentDiv);
					_dom.append(groupBox, layerContainer);

					layersData[className][item["id"]] = {};

					if (item["isOpen"]) {
						_dom.attr(checkBox, "checked", "checked");
						layersData[className][item["id"]]["isOpen"] = true;
					} else {
						layersData[className][item["id"]]["isOpen"] = false;
					}

					checkBox.onclick = function() {
						var _className = _dom.attr(this, "className");
						var _layerName = _dom.attr(this, "layerName");
						var _moduleId = _dom.attr(this, "moduleId");
						var _moduleName = _dom.attr(this, "moduleName");
						var itemData = _dom.attr(this, "itemData");
						itemData = MapFactory.JSON.Parse(itemData);
						var _isChecked = this.checked;
						layersData[_className][_layerName]["isOpen"] = this.checked ? false
								: true;
						if (this.checked) {
							layersData[_className][_layerName]["isOpen"] = true;
							// if (mapManager.isLayerExist(_layerName))
							// LayerManager(_layerName).show();
						} else {
							layersData[_className][_layerName]["isOpen"] = false;
							// if (mapManager.isLayerExist(_layerName))
							// LayerManager(_layerName).hide();
						}

						var _subLayers = layersData[_className];
						var flag = false;
						
						var selectedMenuItem=[];

						var allChildrenMenu=$(this).parent().parent().parent().find("input");
						for ( var m=0;m<allChildrenMenu.length;m++) {
							var childernMenu=allChildrenMenu[m];
							if(childernMenu && childernMenu.checked){
								flag=true;
								var childrenObj= _dom.attr(childernMenu, "itemData");
								selectedMenuItem.push(MapFactory.JSON.Parse(childrenObj));
							}
							if(className=="ZTAnalysis"){
								childernMenu.checked=!_isChecked ? _isChecked : (this==childernMenu);
							}
						}
						var _parentLayer = $(this).parent().parent().parent().siblings("div").find("input");
						if (_parentLayer && _parentLayer.length)
							_parentLayer[0].checked = flag;
						
						if(className=="SamplingPoint"){
							pointArr=selectedMenuItem;
						}
						if(className=="Regions"){
							regionArr=selectedMenuItem;
						}
						MapFactory
						.Require(
								[ baseLayerPath + className
									+ "*" ],
									function(target) {
									itemLyrWidget = target();
									if(className=="SamplingPoint"){
										itemLyrWidget.drawRegions(pointArr,regionArr);
										reorderUserLayer();
									}else{
										if(className=="Regions"){
											loadCydInfo("Regions");
										}
										if (_isChecked) {
											itemLyrWidget.drawOnlyRegions(itemData,true);
											reorderUserLayer();
										} else {
											itemLyrWidget.removeGraphic(itemData);
										}
									}
									
								});
					}
				}

				function showMoudle(moduleId, isOpen) {
					var moduleName = ModuleConfig[moduleId];
					if (!moduleName)
						return;
					var _className = CustomLayersConfig[moduleName]["class"];
					var contentbox = _dom.getById(contentid);
					inputboxs = [];
					iteratorbox(contentbox);
					for (var i = 0, len = inputboxs.length; i < len; i++) {
						var inputbox = inputboxs[i];
						var classname = _dom
						.attr(inputbox, "className");
						var layername = _dom
						.attr(inputbox, "layerName");
						if (classname == _className) {
							if (isOpen) {
								inputbox.checked = true;
								if (layername)
									layersData[classname][layername]["isOpen"] = true;
							} else {
								inputbox.checked = false;
								if (layername)
									layersData[classname][layername]["isOpen"] = false;
							}
						} else {
							inputbox.checked = false;
							if (layername)
								layersData[classname][layername]["isOpen"] = false;
						}
					}
				}

				/*
				 * function showUserLayers(){
				 * if(index>=moduleIds.length){ return; } var id =
				 * moduleIds[index]; index++; if(ModuleConfig[id] &&
				 * layersKeyValue[ModuleConfig[id]]){
				 * MapFactory.Require([baseLayerPath+layersKeyValue[ModuleConfig[id]]+"*"],function(targetLayers){
				 * targetLayers.showLayers(); showUserLayers(); });
				 * }else{ showUserLayers(); } }
				 */

				return api;
			}
		});