MapFactory.Define("ItmsMap/Cross/CrossHistory*",[
	"ItmsMap/MapConfig",
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	"MapFactory/Query",
	"MapFactory/Route",
	"MapFactory/Util/Renderer*",
	"MapFactory/Util/Player*",
	"MapFactory/Geometry*",
	"MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager",
	"ItmsMap/Util/ResultBox*",
	"ItmsMap/Util/ResItemO*",
	"ItmsMap/Util/DateFormat*",
	"ItmsMap/Util/PositionManager*",
	"ItmsMap/Util/Tip*",
	"ItmsMap/Util/ModuleMessage*",
	"ItmsMap/Util/Pager*",
	"ItmsMap/Cross/Cross*"
], function(mapConfig, mapFun, layerFun, queryFun, routeFun, renderFun, Player, geoFun, graphicFun, InfoWindow, ResultBox, ResItem, dateFormat, PositionManager, Tip, ModuleMessage, Pager, Cross){
	return function(){
		var api = {
			play: play,
			pause: pause,
			stop: stop,
			setspeed: setspeed,
			destroy: destroy,
			queryHistoryData: queryHistoryData//卡口历史记录查询
		};
		var cross = Cross();
		
		// 初始化route
		var route = routeFun();
		route.setUrl(mapConfig.layers.route.url);
		var render = renderFun();
		var map = mapFun();
		var tip = Tip();
		var positionManager = PositionManager();
		
		// 初始化添加图层
		layerFun("crossHistoryLayer");
		layerFun("tempPlayerLayer");
		layerFun("areaCrossLayer").removeFromMap();
		
		// 是否进行网络分析
		var isRouteFlag = false;
		
		/**
		 * 查询车辆卡口轨迹
		 */
		function queryHistoryData(plateNum, startTime, endTime, _isRouteFlag) {
			ModuleMessage.showMessage("正在查询，请稍后...",ModuleMessage.LOG,99999);
			isRouteFlag = _isRouteFlag;
			destroy();
			player = null;
			$.ajax({
	    		type : "POST",
	    		url:"cross/map/getcrosshistory",
	    		dataType : "json",
	    		cache: false,
	    		data:"carNum="+plateNum+"&startTime="+startTime+"&endTime="+endTime,
	    		success : function(list) {
	    			ModuleMessage.hideMessage();
	    			if(list && list.length > 1) {
	    				historyResultList = list;
	    				listSize=list.length;
	    				showPagerResult(1);
	    				//showHistoryResult(list);
		    			addResultToMap(list);
	    			}
	    			else {
	    				ResultBox().init("mapResultC").addContent({content: [new ResItem("无该车辆卡口记录！")], switchtab: false});
		        		ModuleMessage.showMessage("无该车辆卡口记录！",ModuleMessage.WARNING);
	    			}
	    		},
	    		error : function() {
    				ResultBox().init("mapResultC").addContent({content: [new ResItem("查询该车辆卡口记录时出错了！")], switchtab: false});
	        		ModuleMessage.showMessage("查询该车辆卡口记录时出错了！",ModuleMessage.ERROR);
	    		}
	    	});
		}
		var historyResultList=[], listSize=0;
		//显示结果
		function showHistoryResult(result){
			// 这里使用分页展示
			if(!result)return;
			
			var res = [], tempCodeList=[];
			for (var i=0,len=result.length; i<len; i++) {
				//result[i].endTime = dateFormat.dateFormartFull(result[i].endTime);
				if(!tempCodeList[result[i].deviceCode]) {
					tempCodeList[result[i].deviceCode] = true;
					var _g = cross.getDeviceGraphic(result[i].deviceCode);
					if(_g)positionManager.addPositionPoint(_g.geo);
				}
				res.push(new ResItem(dateFormat.dateFormartFull(result[i].carDatetime), function(data){
				//res.push(new ResItem(result[i].deviceNames, function(data){
					if(!data.deviceCode) {
						InfoWindow().hide();
						showTipMsg("没有对应的数据！");
						return;
					}
					var _graphic = cross.getDeviceGraphic(data.deviceCode);
					if(!_graphic) {
						InfoWindow().hide();
						showTipMsg("没有对应的坐标！");
						return;
					}
					var params = "?deviceCode="+data.deviceCode+"&img1Path="+data.img1Path+"&carPlateNum="+data.carPlateNum+"&carPlateTypeCode="+data.carPlateTypeCode
								+"&carPlateColorCode="+data.carPlateColorCode+"&carDatetimeStr="+dateFormat.dateFormartFull(data.carDatetime)+"&roadId="+data.roadId
								+"&directionCode="+data.directionCode+"&carSpeed="+data.carSpeed+"&laneCode="+data.laneCode;
								
					// 根据code在地图上查询对应设备并显示信息框
					var info = InfoWindow();
					var content = '<iframe id="result_form" name="result_form" src="forward/map/mapTools/Cross/crossCarInfoWindow/'+params+'"'+
			 		    'width="620" height="320" scrolling="no" style="padding:0; margin:0;" frameborder="0"></iframe>';
					info.setAnchor(_graphic.geo);
					info.setTitle("通行车辆信息");
					info.setWidth(620);
					info.setHeight(320);
					info.setContent(content);
					positionManager.addHighLightPosition(_graphic.geo);
					info.showAndCenterInfowindow(3);
				}, result[i], result[i]));
			}
			ResultBox().init("mapResultC").addContent({content: res, switchtab: false, relation: {startTime:"通行时间", deviceNames: "报警卡口"}});
		}
		function showPagerResult(pageNum) {
			var result = [];
			for(var i=(pageNum-1)*17,j=0; i<listSize && j<=17; i++,j++) {
				result.push(historyResultList[i]);
			}
			showHistoryResult(result);
			Pager({
				parentSrc : "mapResultC",
				currentPage : pageNum,
				pageSize : 17,
				totalNum : listSize,
				pageOffset : 1,
				enableJump : true,
				enableStatistic : true,
				clickEvt : function(e){
					showPagerResult(e.currentPage);
				}
			});
		}
		function addResultToMap(list) {
			var _codes = [];
			var _resArr = [];
			var preDevCode = list[0].deviceCode;// 剔除重复数据
			var crossmap = cross.getDeviceGraphic(preDevCode);
			if(crossmap)_resArr.push(crossmap);
			for(var i=1,len=list.length; i<len; i++) {
				if(preDevCode == list[i].deviceCode)continue;
				preDevCode = list[i].deviceCode;
				crossmap = cross.getDeviceGraphic(preDevCode);
				if(crossmap)_resArr.push(crossmap);
			}
			
		    if(_resArr.length < 2) {
				if(_resArr.length == 1) {
			    	var _point = _resArr[0].geo.points.split(",");
			    	map.centerAt(_point[0], _point[1]);
			    	positionManager.addPositionPoint(_resArr[0].geo);
			    }
        		ModuleMessage.showMessage("对应的卡口设备数为："+_resArr.length+"个，没有轨迹！",ModuleMessage.WARNING);
		    	return;
		    }
		    if(isRouteFlag) {
				var stops=[];
				for(var i=0,len=_resArr.length; i<len; i++){
			   	 	var att = _resArr[i].attributes;
			   	 	stops.push(_resArr[i].geo);
			    }
				routeSearch(stops, [], routeSearchComplate, function(error){
	        		ModuleMessage.showMessage("分析路径时候出错了！",ModuleMessage.ERROR);
				});
		    }
		    else {
		    	// 不进行分析，直接绘制
		    	var movelist = [];
		    	for(var i=0,len=_resArr.length; i<len; i++) {
		    		var points = _resArr[i].geo.points.split(",");
					movelist.push(new Array(parseFloat(points[0]), parseFloat(points[1])));
				}
				map.centerAndZoom(parseFloat(movelist[0][0]), parseFloat(movelist[0][1]), 3);
		    	render.addRouteResult(movelist, "crossHistoryLayer");
				render.addRouteResultArrow(movelist, "crossHistoryLayer");
				player = Player(movelist);
				//player.play();
		    }
		}
		
		// 网络分析features为feature的数组
		function routeSearch(stops, barriers, callback, errorfun) {
			var _start = stops[0];
			var _end = stops[stops.length-1];
			route.setStart(new geoFun({type : "point", points : _start.points}));
			route.setEnd(new geoFun({type : "point", points : _end.points}));
			route.setStops(stops);
			route.setBarriers(barriers);
			route.solve(function(result){
				if(callback)callback(result);
			}, function(error){
				if(errorfun)errorfun(error);
			});
		}
		var player;
		function routeSearchComplate(result) {
			if(result && result.length > 0) {
				if(!result[0].geo.points) {
					ModuleMessage.showMessage("分析后不存在轨迹！",ModuleMessage.WARNING);
					return;
				}
				var pointsList = result[0].geo.points.replace(/\|/g,",").split(",");
				// 构造轨迹回放的坐标点的集合
				var playPointList = [];
				for(var i=0,len=pointsList.length; i<len; i+=2) {
					playPointList.push(new Array(parseFloat(pointsList[i]), parseFloat(pointsList[i+1])));
				}
				map.centerAndZoom(parseFloat(playPointList[0][0]), parseFloat(playPointList[0][1]), 3);
				render.addRouteResult(playPointList, "crossHistoryLayer");
				render.addRouteResultArrow(playPointList, "crossHistoryLayer");
				player = Player(playPointList);
				//player.play();
			}
			else {
				// 分析失败，或没有分析结果
				ModuleMessage.showMessage("分析出错了，或者不存在轨迹！",ModuleMessage.ERROR);
			}
		}
		function play() {
			if(player)player.play();
		}
		function pause() {
			if(player)player.pause();
		}
		function stop() {
			if(player)player.stop();
		}
		function setspeed(type) {
			if(player)player.setspeed(type);
		}
		function destroy() {
			InfoWindow().hide();
			positionManager.clear();
			layerFun("crossHistoryLayer").clear();
			if(player)player.destroy();
			player = null;
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