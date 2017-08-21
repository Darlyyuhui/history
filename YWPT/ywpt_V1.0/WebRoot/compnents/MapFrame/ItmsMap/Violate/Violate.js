MapFactory.Define("ItmsMap/Violate/Violate*",[
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	"MapFactory/Geometry*",
	"MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager",
	"ItmsMap/SymbolConfig*",
	"ItmsMap/Util/ResultBox*",
	"ItmsMap/Util/ResItemO*",
	"ItmsMap/Util/Pager*",
	"ItmsMap/Util/PositionManager*",
	"ItmsMap/Util/DateFormat*",
	"ItmsMap/Util/StatisticTable*",
	"ItmsMap/Util/Tip*",
	"ItmsMap/Util/ModuleMessage*"
],function(MapManager,LayerManager,Geometry,GraphicManager,InfoWindow,SymbolConfig,ResultBox,ResItem,Pager,PositionManager,dateFormat,statisticTable,Tip,ModuleMessage){
	var vioDevices = [], allDeviceList=[];// 违法设备缓存
	var _isInit = false,positionManager;
	return function(){
		var api = {
			showAll: showAll,
			statistics: statistics,
			location: location,
			search: search
		}
		var map = MapManager();
		var tip = Tip();
		var vioLayer, graphics=[];
		
		// 初始化方法，只执行一次
		function _init() {
			if(!map.isLayerExist("vioLayer")) {
				vioLayer=LayerManager("vioLayer");
				vioLayer.addOnClickEvent(function(e) {
					getViolateByCode(e.graphic.attributes.code, e.graphic.geo);
				});
			}
			else {
				vioLayer=LayerManager("vioLayer");
			}
			if(_isInit)return;
			_isInit = true;
			positionManager = PositionManager();
			
			// 同步获取所有有坐标的卡口设备
			$.ajax({
				url: "map/vio/statistics/"+currentModuleId+"/",
				type: "GET",
				async: false,
				success: function(result) {
					result = result.list;
					allDeviceList = result;
					vioDevices = [];
					var symbol,tempData,point,x,y;
					var symbols = [SymbolConfig.vioSymbol,SymbolConfig.vioErrorSymbol,SymbolConfig.vioBlackSymbol];
					for(var i=0,len=result.length; i<len; i++) {
						tempData = result[i];
						symbol = symbols[tempData["deviceStatFlag"]];
						x = parseFloat(tempData.mapx);
						y = parseFloat(tempData.mapy);
						if (isNaN(x) || isNaN(y))continue;//剔除没有坐标的卡口设备
						point = {type: "point", points: x + "," + y};
						vioDevices.push({code: tempData.code, graphic: {geo: point, symbol: symbol, attributes: tempData}});
					}
				}
			});
		}
		_init();
		
		function showAll(visiable) {
			vioLayer.clear();
			if(typeof visiable != 'undefined' && visiable == false) {
				InfoWindow().hide();
				positionManager.clear();
				return;
			}
			for (var i = vioDevices.length - 1; i >= 0; i--) {
				GraphicManager(vioDevices[i].graphic).addToLayer("vioLayer");
			}
		}
		
		function getViolateByCode(code, point) {
			var info = InfoWindow();
			info.setAnchor(point);
			info.setTitle("违法设备信息框");
			info.setWidth(400);
			info.setHeight(260);
			info.setLoadPage("map/vio/vioInfoWindow?code="+code, null, function() {
			});
			positionManager.addHighLightPosition(point);
			info.showAndCenterInfowindow(3);
		}
		
		var _currentVio={};// 缓存当前地图上显示的违法设备的code
		function statistics(statNode) {
			$("#"+statNode).empty();
			vioLayer.clear();
			InfoWindow().hide();
			positionManager.clear();
			$.post("map/vio/statistics/"+currentModuleId+"/", function(result){
				// 查询结果
				var list = result.list;
				var statistics = result.statistics;
				
				vioDevices = [];
				var symbol,tempData,point,x,y;
				var symbols = [SymbolConfig.vioSymbol,SymbolConfig.vioErrorSymbol,SymbolConfig.vioBlackSymbol];
				for(var i=0,len=list.length; i<len; i++) {
					tempData = list[i];
					symbol = symbols[tempData["deviceStatFlag"]];
					x = parseFloat(tempData.mapx);
					y = parseFloat(tempData.mapy);
					if (isNaN(x) || isNaN(y))continue;//剔除没有坐标的卡口设备
					point = {type: "point", points: x + "," + y};
					vioDevices.push({code: tempData.code, graphic: {geo: point, symbol: symbol, attributes: tempData}});
					_currentVio[tempData.code]=true;
					GraphicManager({geo: point, symbol: symbol, attributes: tempData}).addToLayer("vioLayer");
				}
				
				statisticTable({src: statNode, data: {
					colsTitle: ["", "正常", "故障","未接入", "合计"], 
					rowsTitle: ["违法设备"], 
					data: [[statistics.normal, statistics.error, statistics.black, statistics.all]],
					colsTitleAttributes: [{type:"", status: 0},{type:"", status: 1},{type:"", status: 2},{type:"", status: -1}],
					rowsTitleAttributes: [{type:"", status: -1}],
					dataAttributes: [[{type:"", status: 0},{type:"", status: 1},{type:"", status: 2},{type:"", status: -1}]]
				}, isSelectAll: true, selectAllClick: selectAll, rowClick: rowClick, colClick: colClick, celClick: celClick
				});
			});
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
				for(var i=vioDevices.length-1; i>=0; i--) {
					if(_data.status == -1 || _data.status == vioDevices[i].graphic.attributes.deviceStatFlag) {
						if(!_currentVio[vioDevices[i].code]) {
							_currentVio[vioDevices[i].code]=true;
							GraphicManager(vioDevices[i].graphic).addToLayer("vioLayer");
						}
					}
				}
			}
			else {
				vioLayer.clear();
				for(var i=vioDevices.length-1; i>=0; i--) {
					if(_data.status == -1 || _data.status == vioDevices[i].graphic.attributes.deviceStatFlag) {
						_currentVio[vioDevices[i].code]=false;
					}
					else {
						if(_currentVio[vioDevices[i].code]) {
							GraphicManager(vioDevices[i].graphic).addToLayer("vioLayer");
						}
					}
				}
			}
		}
		
		// 定位查询
		function location(name) {
			positionManager.clear();
			InfoWindow().hide();
			if(!name)name="";
			var res = [];
			
			var tempData, x, y;
			for(var i=0,len=allDeviceList.length; i<len; i++) {
				tempData = allDeviceList[i];
				if(tempData.name.indexOf(name) != -1) {
					res.push(tempData);
					
					x = parseFloat(tempData.mapx);
					y = parseFloat(tempData.mapy);
					if (isNaN(x) || isNaN(y))continue;//剔除没有坐标的卡口设备
					positionManager.addPositionPoint(x, y);
				}
			}
			violateList = res;
			violateListSize = violateList.length;
			showViolatePagerResult(1);
		}
		var violateList,violateListSize;
		function showViolatePagerResult(pageNum) {
			var result = [];
			for(var i=(pageNum-1)*19,j=1; i<violateListSize && j<=19; i++,j++) {
				result.push(violateList[i]);
			}
			showViolateResult(result);
			Pager({
				parentSrc : "mapResultC",
				currentPage : pageNum,
				pageSize : 19,
				totalNum : violateListSize,
				pageOffset : 1,
				enableJump : true,
				enableStatistic : true,
				clickEvt : function(e){
					showViolatePagerResult(e.currentPage);
				}
			});
		}
		function showViolateResult(result) {
			var res = [];
			var tempData, x, y;
			for(var i=0,len=result.length; i<len; i++) {
				tempData = result[i];
				res.push(new ResItem(tempData.name, showVioInfoByResultBoxData, tempData, tempData));
			}
			
			if(!res.length)res.push(new ResItem("没有查询到相关数据"));
			ResultBox().init("mapResultC").addContent({content:res,switchtab:true});
		}
		
		// 定位列表单击处理方法
		function showVioInfoByResultBoxData(data) {
			InfoWindow().hide();
			if(!data.mapx && !data.mapy) {
				showTipMsg("没有坐标信息！");
				return;
			}
			getViolateByCode(data.code, {type: "point", points: data.mapx+","+data.mapy});
		}
		
		var vioPagenum = 1;
		function search(carnum, starttime, endtime, isPagerClick) {
			if(!isPagerClick) {
				vioPagenum=1;
				ModuleMessage.showMessage("正在查询，请稍后...",ModuleMessage.LOG,99999);
			}
			if(!carnum)carnum="";
			if(!starttime)starttime="";
			if(!endtime)endtime="";
   			positionManager.clear();
   			InfoWindow().hide();
			$.ajax({
	    		type : "POST",
	    		url:"map/vio/search/"+vioPagenum+"/20/"+currentModuleId+"/",
	    		dataType : "json",
	    		cache: false,
	    		data:"carNumber="+carnum+"&startTime="+starttime+"&endTime="+endtime,
	    		success : function(page) {
	    			ModuleMessage.hideMessage();
	    			// 查询结果
					var result = page.result;
					var relation = {carNum: "号牌号码", roadName: "违法地点", carDateTimeStr: "违法时间", vioTypeName: "违法类型", carSpeed: "车辆速度", stdSpeed: "标准限速", speedRate: "超速百分比"};
	    			var res = [];
					for (var i=0,len=result.length; i < len; i++) {
						//result[i].carSpeed += "km/h";
						//result[i].stdSpeed += "km/h";
						//result[i].speedRate = result[i].speedRate.toFixed(2)+"%";
						//result[i].carDateTimeStr = dateFormat.dateFormartFull(Number(result[i].carDatetime));
						res.push(new ResItem(result[i].carNum+"<br />"+result[i].roadName, showVioCarInfoByResultBoxData, result[i], result[i]));
						//positionManager.addPositionPoint(result[i].mapx, result[i].mapy, result[i]);
					}
					
					//ResultBox().init("mapResultC").addContent({content:res,switchtab:true,relation:relation});
					ResultBox().init("mapResultC").addContentAsTable({content: res, switchtab: true, relation: {carNum: "号牌号码", roadName: "违法地点"},colsWidth:[80]});
					
					Pager({
						parentSrc : "mapResultC",
						currentPage : vioPagenum,
						pageSize : 20,
						totalNum : page.totalSize,
						pageOffset : 1,
						enableJump : true,
						enableStatistic : true,
						clickEvt : function(e){
							vioPagenum = e.currentPage;
							search(carnum, starttime, endtime, true);
						}
					});
	    		},
	    		error : function() {
	    			ModuleMessage.showMessage("违法数据查询时出错了！",ModuleMessage.ERROR);
	    		}
	    	});
		}
		
		function showVioCarInfoByResultBoxData(data) {
			InfoWindow().hide();
			if(!data.mapx && !data.mapy) {
				showTipMsg("没有坐标信息！");
				return;
			}
			
			var info = InfoWindow();
			var point = {type: "point", points: data.mapx+","+data.mapy};
			info.setAnchor(new Geometry(point));
			//info.setTitle(data.carNum+"  "+data.roadName+"  "+dateFormat.dateFormartFull(Number(data.carDatetime)));
			info.setTitle(data.carNum+"  "+data.roadName);
			info.setWidth(630);
			info.setHeight(300);
			info.setLoadPage("map/vio/vioCarInfoWindow?id="+data.id, null, function() {
			});
			positionManager.addHighLightPosition(point);
			info.showAndCenterInfowindow(3);
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