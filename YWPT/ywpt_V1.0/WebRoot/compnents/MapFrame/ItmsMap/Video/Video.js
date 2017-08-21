MapFactory.Define("ItmsMap/Video/Video*",[
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	"ItmsMap/Util/ResultBox*",
	"ItmsMap/Util/ResItemO*",
	"MapFactory/Geometry*",
	"MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager",
	"MapFactory/Util/Dialog*",
	"ItmsMap/Util/PositionManager*",
	"ItmsMap/Util/ModuleMessage*",
	"ItmsMap/Util/Tip*",
	"ItmsMap/Util/Pager*",
	"ItmsMap/SymbolConfig*",
	"ItmsMap/Util/DateFormat*",
	"ItmsMap/Util/StatisticTable*"
],function(MapManager,LayerManager,resultBox,resItem,Geometry,GraphicManager,InfoWindow,Dialog,PositionManager,ModuleMessage,Tip,Pager,symbolConfig,dateFormat,StatisticTable){
	var videoDevices = [], _isInit=false, positionManager, allDevice=[];
	return function(){
		var videoLayer;
		var map = MapManager();
		var tip = Tip();
		
		var api = {
			showAll : showAll,// 显示所有的视频设备
			statistic: statistic,//统计
			videoLocation: videoLocation,
			videoQuery: videoQuery,
			getVideoDeviceByCode : getVideoDeviceByCode,// 根据视频设备code，坐标信息，弹出卡口信息框
			getVideoDeviceByCodeDialog : getVideoDeviceByCodeDialog// 根据视频设备code，坐标信息，弹出卡口信息框
		};
		
		// 初始化方法，只执行一次
		function _init() {
			if(!map.isLayerExist("videoLayer")) {
				videoLayer = LayerManager("videoLayer");
				videoLayer.addOnClickEvent(function(_graphic) {
					getVideoDeviceByCode(_graphic.graphic);
				});
			}
			else {
				videoLayer = LayerManager("videoLayer");
			}
			if(_isInit)return;
			_isInit = true;
			positionManager = PositionManager();
			
			// 同步获取所有有坐标的视频设备
			$.ajax({
				url: "map/video/statistics/"+currentModuleId+"/",
				type: "POST",
				async: false,
				success: function(result) {
					allDevice = result.list;
					showGraphics(result.list, true, false);
				}
			});
		}
		_init();
		
		
		/**
		 * 统计
		 **/
		function statistic(statNode) {
			//清除图层 防止刷新后图层重叠
			videoLayer.clear();
			positionManager.clear();
			InfoWindow().hide();
			$.ajax({
				url: "map/video/statistics/"+currentModuleId+"/",
				type: "POST",
				success: function(result) {
					//显示统计数据
					showStatistic(statNode,result.list);
					//显示图层
					showGraphics(result.list,true);					
				}
			});
		}
		
		/**
		 * 定位
		 **/
		function videoLocation(videoName) {
			positionManager.clear();
			InfoWindow().hide();
			var res = [], tempData, x, y;
			for(var i=0,len=allDevice.length; i<len; i++) {
				tempData = allDevice[i];
				if(tempData.name.search(videoName) < 0) {
					continue;
				}
				res.push(tempData);
				x = parseFloat(tempData.mapx);
				y = parseFloat(tempData.mapy);
				if (isNaN(x) || isNaN(y))continue;//剔除没有坐标的卡口设备
				positionManager.addPositionPoint(x, y);
			}
			videoList = res;
			videoListSize = videoList.length;
			showVideoPagerResult(1);
		}
		var videoList,videoListSize;
		function showVideoPagerResult(pageNum) {
			var result = [];
			for(var i=(pageNum-1)*19,j=1; i<videoListSize && j<=19; i++,j++) {
				result.push(videoList[i]);
			}
			showVideoResult(result);
			Pager({
				parentSrc : "mapResultC",
				currentPage : pageNum,
				pageSize : 19,
				totalNum : videoListSize,
				pageOffset : 1,
				enableJump : true,
				enableStatistic : true,
				clickEvt : function(e){
					showVideoPagerResult(e.currentPage);
				}
			});
		}
		function showVideoResult(result) {
			var res = [];
			var tempData, x, y;
			for(var i=0,len=result.length; i<len; i++) {
				tempData = result[i];
				res.push(new resItem(tempData.name, getGraphic, tempData, tempData));
			}
			
			if(!res.length)res.push(new resItem("没有查询到相关数据"));
			resultBox().init("mapResultC").addContent({content:res,switchtab:true});
		}
		
		/**
		 * 查询
		 * */
		function videoQuery(paramObj,isPagerClick) {
			if(!isPagerClick) {
				videoPagenum=1;
				ModuleMessage.showMessage("正在查询，请稍后...",ModuleMessage.LOG,99999);
			}
			videoLayer.clear();
			positionManager.clear();
			InfoWindow().hide();
			$.ajax({
				type : "POST",
	    		url:"map/video/search/"+currentModuleId+"/",
	    		dataType : "json",
	    		data: paramObj,
	    		success: function(result) {
					ModuleMessage.hideMessage();
					if (!result) {
						result = [];
					}
					showGraphics(result);
					
					videoList = result;
					videoListSize = videoList.length;
					showVideoPagerResult(1);
				},
				fail: function() {
					//res.push(new resItem("查询数据出错！", "", {}));
					ModuleMessage.showMessage("查询数据出错！", ModuleMessage.ERROR);
				}
			});
		}
		
		function setResultBox(list, res) {
			for(var i=list.length-1; i>=0; i--) {
				var item = list[i];
				res.push(new resItem(item.name, getGraphic, item, item));
			}
			if(!res.length)res.push(new resItem("没有查询到相关数据"));
		}
		
		/**
		 * 获取名称对应的graphic
		 * */
		function getGraphic(att) {
			InfoWindow().hide();
			if(!att || !att.code) {
				showTipMsg("此设备在地图上不存在！");
				return;
			}
			var graphic = getDeviceGraphic(att.code);
			if(!graphic) {
				showTipMsg("此设备在地图上不存在！");
				return;
			}
			// 这里定位偏移了
			//if(typeof att.gid != 'undefined')positionManager.highLightPosition(att.gid);
			getVideoDeviceByCode(graphic);
		}
		// 从前台缓存中根据视频设备code值获取对应的视频设备对象
		function getDeviceGraphic(code) {
			for(var j in videoDevices) {
				if(code == videoDevices[j].code) {
					return videoDevices[j].graphic;
				}
			}
			return null;
		}
		
		// 根据卡口设备code获取卡口相关信息
		function getVideoDeviceByCode(graphic) {
			var att = graphic.attributes;
			var geo = graphic.geo;
			
			var info = InfoWindow();
			info.setAnchor(geo);
			info.setTitle(att["name"]);
			info.setWidth(500);
			info.setHeight(380);
			//info.setLoadPage("flow/confirm/showvideodialog/?videocode="+att["code"], {}, function() {
			info.setLoadPage("map/video/getVideoInfoByCode?code="+att["code"], {}, function() {
			});
			positionManager.addHighLightPosition(geo);
			info.showAndCenterInfowindow(3);
		}
		var videoDialogNum = 0;
		var _xhr = MapFactory.XHR,
			_dom = MapFactory.Dom;
		function getVideoDeviceByCodeDialog(graphic) {
			graphic = getDeviceGraphic(graphic.attributes.code);
			var att = graphic.attributes;
			Dialog({
				title: att["name"],
				mutiDialog : true,
				mutiDialogSeed : "videodialog"+videoDialogNum,
				mask: false,
				top: 35,
				right: 10,
				content: '<div id="videodialog'+videoDialogNum+'" style="width:500px;height:380px;"></div>',
				buttonDisplay: {
					"confirmButton": false,
					"cancelButton": false
				},
				closeCall: function(){
				}
			}).show();
			// 异步加载卡口信息框
			_xhr.Load("videodialog"+videoDialogNum, "map/video/getVideoInfoByCode?code="+att["code"], {}, function(data) {
			});
			videoDialogNum++;
		}
		
		/**
		*显示图层
		*@param list 传入的数组数据
		*@param isStatistic 是否为统计中调用，如果是则更新videoDevices数组
		*/
		function showGraphics(list, isStatistic, isAddToMap) {
			videoLayer.clear();//清除视频图层，准备重新渲染添加
			positionManager.clear();
			InfoWindow().hide();
			if (Object.prototype.toString.call(list) != "[object Array]") return;
			if(typeof isAddToMap == 'undefined')isAddToMap = true;
			if(isStatistic)videoDevices = [];
			var symbol,tempData,point,x,y;
			for(var i=0,len=list.length; i<len; i++) {
				var tempData = list[i];
				x = parseFloat(tempData.mapx);
				y = parseFloat(tempData.mapy);
				if (isNaN(x) || isNaN(y))continue;//剔除没有坐标的卡口设备
				if(tempData.deviceShape && tempData.deviceShape=="1") {
					symbol = symbolConfig.videoQiujiSymbol;
					tempData.deviceShape = 1;
				}
				else {
					symbol = symbolConfig.videoQiangjiSymbol;
					tempData.deviceShape = 0;
				}
				point = {type: "point", points: x + "," + y};
				if(isStatistic)videoDevices.push({code: tempData.code, graphic: {geo: point, symbol: symbol, attributes: tempData}});
				if(isAddToMap)GraphicManager({geo: point, symbol: symbol, attributes: tempData}).addToLayer("videoLayer");
			}
		}
		
		/**
		 * 显示所有的卡口设备
		 * */
		function showAll(visiable) {
			videoLayer.clear();
			if(typeof visiable != 'undefined' && visiable == false) {
				InfoWindow().hide();
				positionManager.clear();
				return;
			}
			for (var i = videoDevices.length - 1; i >= 0; i--) {
				GraphicManager(videoDevices[i].graphic).addToLayer("videoLayer");
			}
		}
		
		/**
		*显示统计数据
		*/
		var _currentDevice={};// 缓存当前地图上显示的卡口的code
		function showStatistic(statNode,list){
			var qiuji=0,qiangji=0;
			for(var i=0,len=list.length; i<len; i++) {
				if(list[i].deviceShape && list[i].deviceShape=="1") {
					qiuji++;
				}
				else {
					qiangji++;
				}
			}
			$("#"+statNode).empty();
			StatisticTable({src: statNode, data: {
				colsTitle: ["", "枪机", "球机", "合计"], 
				rowsTitle: ["视频监控"], 
				data: [[qiangji, qiuji, qiangji+qiuji]],
				colsTitleAttributes: [{type:"", status: 0}, {type:"", status: 1}, {type:"", status: -1}],
				rowsTitleAttributes: [{type:"", status: -1}],
				dataAttributes: [[{type:"", status: 0}, {type:"", status: 1}, {type:"", status: -1}]]
			}, isSelectAll: true, selectAllClick: selectAll, rowClick: rowClick, colClick: colClick, celClick: celClick});
			for(var i=videoDevices.length-1; i>=0; i--) {
				_currentDevice[videoDevices[i].code]=true;
			}
		}
		
		function selectAll(e) {
			updateLayer({type:"", status: -1}, e.isSelected);
		}
		
		function rowClick(e) {
			updateLayer(e.attributes, e.isSelected);
		};
		
		function colClick(e) {
			updateLayer(e.attributes, e.isSelected);
		}
		
		function celClick(e) {
			updateLayer(e.attributes, e.isSelected);
		}
		
		function updateLayer(_data, isSelected) {
			if(!isSelected) {
				for(var i=videoDevices.length-1; i>=0; i--) {
					if(_data.status == -1 || _data.status == videoDevices[i].graphic.attributes.deviceShape) {
						if(!_currentDevice[videoDevices[i].code]) {
							_currentDevice[videoDevices[i].code]=true;
							GraphicManager(videoDevices[i].graphic).addToLayer("videoLayer");
						}
					}
				}
			}
			else {
				videoLayer.clear();
				for(var i=videoDevices.length-1; i>=0; i--) {
					if(_data.status == -1 || _data.status == videoDevices[i].graphic.attributes.deviceShape) {
						_currentDevice[videoDevices[i].code]=false;
					}
					else {
						if(_currentDevice[videoDevices[i].code]) {
							GraphicManager(videoDevices[i].graphic).addToLayer("videoLayer");
						}
					}
				}
			}
		}
		
		function chooseDialog(videoinfocode,roadname){
			jBox.setDefaults({defaults:{top:65,left:'50px'}});
			jBox.open("iframe:"+basePath+"/flow/confirm/showvideodialog/?videocode="+videoinfocode,'['+roadname+'] 监控视频',660,500,{
		 		loaded: function (h) {
		 			$(h).removeAttr('style');
		 			$(h).attr('style','height:420px; overflow-x: hidden; overflow-y: hidden; position: static; left: -10000px;')
		 			$(".jbox-button-panel").height(30);
		 		}
		 	});
		}
		
		// 地图中心提示消息
		function showTipMsg(msg) {
			tip.setContent(msg);
			tip.show();
			setTimeout(function(){
				tip.setContent("");
				tip.hide();
			}, 1500);
		}
		
		return api;
	}
	
});