MapFactory.Define("ItmsMap/Cross/Cross*",[
	"ItmsMap/MapConfig",
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	"MapFactory/Query",
	"MapFactory/Route",
	"MapFactory/Util/Renderer*",
	"MapFactory/Util/Player*",
	"MapFactory/Util/Dialog*",
	"MapFactory/Geometry*",
	"MapFactory/GraphicManager",
	"MapFactory/SymbolUtil",
	"ItmsMap/Util/ActiveMQWarpper*",
	"MapFactory/InfoWindowManager",
	"ItmsMap/Util/ResultBox*",
	"ItmsMap/Util/ResItemO*",
	"ItmsMap/Util/DateFormat*",
	"ItmsMap/Util/Pager*",
	"MapFactory/MapManager",
	"ItmsMap/Util/StatisticTable*",
	"ItmsMap/Util/PositionManager*",
	"ItmsMap/Util/ModuleMessage*",
	"ItmsMap/Util/Tip*",
	"ItmsMap/SymbolConfig*"
], function(mapConfig, mapFun, layerFun, queryFun, routeFun, renderFun, Player, Dialog, geoFun, graphicFun, symbolFun, amq, infoWindow, resultBox, resItem, Format, 
			Pager, MapManager, statisticTable, PositionManager, ModuleMessage, Tip, SymbolConfig){
	var crossDevices = [], preCode="", allDeviceList=[];// 卡口设备缓存{code:crossCode, graphic:graphic}	
	//存储每次生成的graphic
	var graphics = {};
	var cainfocancelcodes = [], cainfocanceltimes = [];// 卡口报警页面取消历史报警的卡口code值
	var cagraphics = [];// 卡口报警对应的graphic
	var _isInit=false,positionManager,firstLoadCross=true,firstLoadAlarmCross=true,crossDivId;
	return function(){
		var tip = Tip();
		var ResultBox = resultBox, ResItem = resItem, InfoWindow = infoWindow, map = MapManager();
		var api = {
			showAll : showAll,// 显示所有的卡口设备
			destroy : destroy,// 销毁
			getCrossDeviceByCode : getCrossDeviceByCode,// 根据卡口设备code，坐标信息，弹出卡口信息框
			getCrossDeviceByCodeDialog : getCrossDeviceByCodeDialog,// 根据卡口设备code，坐标信息，弹出卡口信息框(按照对话框方式弹出，可以拖动)
			autoFresh : autoFresh,// 自动刷新卡口报警信息
			autoFreshDeviceStatus : autoFreshDeviceStatus,// 自动刷新卡口报警信息
			getAlarmCodes : getAlarmCodes,// 获取所有报警的卡口设备code并渲染到地图上
			alarmSearch : alarmSearch,// 卡口报警查询
			getCrossAlarmByDevice : getCrossAlarmByDevice,// 根据卡口报警设备code，坐标信息，弹出卡口报警信息框
			statistic: statistic,//统计卡口数
			crossQuery: crossQuery,
			crossLocation: crossLocation,
			getDeviceGraphic: getDeviceGraphic,// 根据卡口code值获取卡口对应的graphic
			setCrossAlarmCancelCode: setCrossAlarmCancelCode,// 取消或重置报警历史记录的code
			getCrossInfo: getCrossInfo
		};
		
		//对卡口源数据进行转换的模型
		var model = {
			name: "卡口名称",
			code: "设备编码",
			orgNames: "所属部门",
			trademark: "设备品牌",
			ip: "IP地址",
			company_id: "公司信息",
			ftp_id: "ftp服务器",
			road_id: "所在道路",
			buildtime: "建设时间",
			buildnetwork_id: "接入方式",
			buildisp_id: "网络运营商",
			timeout: "超时"
			//license: "设备注册码",
			//devicetypecode: "设备类型"
		};
		
		// 获取卡口图层
		var layer;
		var alarmlayer;
		var query = queryFun();
		var symbol = symbolFun();
		
		layerFun("areaCrossLayer").removeFromMap();
		
		// 初始化方法，只执行一次
		function _init() {
			// 每次获取cross.js对象的时候判断图层是否被移除掉了
			if(!map.isLayerExist("cross")) {
				layer = layerFun("cross");
				alarmlayer = layerFun("crossAlarm");
				layer.addOnClickEvent(function(e) {
					getCrossDeviceByCode(e.graphic);
				});
				alarmlayer.addOnClickEvent(function(obj){
					getCrossAlarmByDevice(obj.graphic, true);
				});
			}
			else {
				layer = layerFun("cross");
				alarmlayer = layerFun("crossAlarm");
			}
			if(_isInit)return;
			_isInit = true;
			positionManager = PositionManager();// 初始化一次高亮定位对象
			layerFun("areaCrossLayer").clear();
			layerFun("areaLabelLayer").clear();
		    positionManager.clear();
		    infoWindow().hide();
			
			// 同步获取所有有坐标的卡口设备
			$.ajax({
				url: "cross/map/all/"+currentModuleId+"/",
				type: "GET",
				async: false,
				success: function(result) {
					allDeviceList = result;
					crossDevices = [];
					var symbol,tempData,point,x,y;
					var symbols = [SymbolConfig.normalCrossSymbol,SymbolConfig.unnormalCrossSymbol,SymbolConfig.blackCrossSymbol];
					for(var i=0,len=result.length; i<len; i++) {
						var tempData = result[i];
						symbol = symbols[tempData["deviceStatFlag"]];
						x = parseFloat(tempData.mapx);
						y = parseFloat(tempData.mapy);
						if (isNaN(x) || isNaN(y))continue;//剔除没有坐标的卡口设备
						point = {type: "point", points: x + "," + y};
						crossDevices.push({code: tempData.code, graphic: {geo: point, symbol: symbol, attributes: tempData}});
					}
				}
			});
		}
		_init();
		
		/***
		 * 统计卡口数据 
		 * */
		function statistic(statNode) {
			crossDivId = statNode;
			//清除图层 防止刷新后图层重叠
			layer.clear();
			positionManager.clear();
			InfoWindow().hide();
			$.ajax({
				url: "cross/map/statistics/"+currentModuleId+"/",
				type: "GET",
				success: function(result) {
					//显示图层
					showGraphics(result["deviceList"],"mapx","mapy",true);					
					//显示统计数据
					showStatistic(result["statusmap"]);
				}
			});
			
			if(firstLoadCross) {
				firstLoadCross = false;
				autoFreshDeviceStatus(true);
			}
		}
		function deviceStatusHandler(message){
			var str;
			if (message.text == undefined) {
				str = message.textContent;
			} else {
				str = message.text;
			}
			var datas=str.split("|")[0].split(",");
			var staticdatas=str.split("|")[1].split(",");
	        var statusmap={};
            statusmap.normal=staticdatas[1];
            statusmap.unnormal=staticdatas[0];
            statusmap.unuse=staticdatas[2];
            statusmap.total=staticdatas[3];
			for(var index in datas){
		      var dataArr=datas[index].split(":");
		      var code=dataArr[0];
		      for(var deviceindex in allDeviceList){
		        var device=allDeviceList[deviceindex];
		        if(device.code==code){
		        	device.deviceStatFlag=dataArr[1];
		        	break;
		        }
	          }
			}
		    layer.clear();
			//positionManager.clear();
			showGraphics(allDeviceList,"mapx","mapy",true);
			showStatistic(statusmap);
		}
		
		/**
		*显示统计数据
		*/
		var _currentCross={};// 缓存当前地图上显示的卡口的code
		function showStatistic(statusmap){
			$("#"+crossDivId).empty();
			statisticTable({src: crossDivId, data: {
				colsTitle: ["", "正常", "故障","未接入", "合计"], 
				rowsTitle: ["卡口设备"], 
				data: [[statusmap.normal,statusmap.unnormal,statusmap.unuse,statusmap.total]],
				colsTitleAttributes: [{type:"", status: 0},{type:"", status: 1},{type:"", status: 2},{type:"", status: -1}],
				rowsTitleAttributes: [{type:"", status: -1}],
				dataAttributes: [[{type:"", status: 0},{type:"", status: 1},{type:"", status: 2},{type:"", status: -1}]]
			}, isSelectAll: true, selectAllClick: selectAll, rowClick: rowClick, colClick: colClick, celClick: celClick
			});
			for(var i=crossDevices.length-1; i>=0; i--) {
				_currentCross[crossDevices[i].code]=true;
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
				for(var i=crossDevices.length-1; i>=0; i--) {
					if(_data.status == -1 || _data.status == crossDevices[i].graphic.attributes.deviceStatFlag) {
						if(!_currentCross[crossDevices[i].code]) {
							_currentCross[crossDevices[i].code]=true;
							graphicFun(crossDevices[i].graphic).addToLayer("cross");
						}
					}
				}
			}
			else {
				layer.clear();
				for(var i=crossDevices.length-1; i>=0; i--) {
					if(_data.status == -1 || _data.status == crossDevices[i].graphic.attributes.deviceStatFlag) {
						_currentCross[crossDevices[i].code]=false;
					}
					else {
						if(_currentCross[crossDevices[i].code]) {
							graphicFun(crossDevices[i].graphic).addToLayer("cross");
						}
					}
				}
			}
		}
		
		function crossLocation(crossName) {
			positionManager.clear();
			var res = [];
			
			var tempData,x,y;
			for(var i=0,len=allDeviceList.length; i<len; i++) {
				var tempData = allDeviceList[i];
				if(tempData.name.search(crossName) < 0) {
					continue;
				}
				res.push(tempData);
				
				x = parseFloat(tempData.mapx);
				y = parseFloat(tempData.mapy);
				if (isNaN(x) || isNaN(y))continue;//剔除没有坐标的卡口设备
				positionManager.addPositionPoint(x, y);
			}
			
			crossList = res;
			crossListSize = crossList.length;
			showCrossPagerResult(1);
		}
		
		/**
		*显示图层
		*@param data 传入的数组数据
		*@param xField x坐标字段名
		*@param yField y坐标字段名
		*@param isStatistic 是否为统计中调用，如果是则更新crossDevices数组
		*/
		function showGraphics(data, xField, yField, isStatistic) {
			if (Object.prototype.toString.call(data) != "[object Array]") return;
			layer.clear();
			var length = data.length,
				i,
				tempData,
				point,
				graphic,
				x,
				y,
				symbol,
				symbols = [SymbolConfig.normalCrossSymbol,SymbolConfig.unnormalCrossSymbol,SymbolConfig.blackCrossSymbol];
			
			// 更新crossDevices数组
			if(isStatistic)crossDevices = [];
			for (i = length - 1; i >= 0; i--) {
				tempData = data[i];
				symbol = symbols[tempData["deviceStatFlag"]];
				x = parseFloat(tempData[xField]);
				y = parseFloat(tempData[yField]);
				if (!x || !y)continue;
				point = {type: "point", points: x + "," + y};
				graphic = graphicFun({geo: point, symbol: symbol, attributes: tempData});
				if(isStatistic)crossDevices.push({code: tempData.code, graphic: {geo: point, symbol: symbol, attributes: tempData}});
				graphic.addToLayer("cross");
			}
		}
		
		/**
		 * 显示所有的卡口设备
		 * */
		function showAll(visiable) {
			layer.clear();
			if(typeof visiable != 'undefined' && visiable == false) {
				InfoWindow().hide();
				positionManager.clear();
				return;
			}
			for (i = crossDevices.length - 1; i >= 0; i--) {
				graphicFun(crossDevices[i].graphic).addToLayer("cross");
			}
		}
		
		/**
		 * 查询卡口数据
		 * */
		function crossQuery(paramObj) {
			layer.clear();
			positionManager.clear();
			InfoWindow().hide();
			ModuleMessage.showMessage("正在查询，请稍后...",ModuleMessage.LOG,99999);
			$.ajax({
				url: "cross/map/query/"+currentModuleId+"/",
				data: paramObj,
				type: "POST",
				success: function(result) {
					ModuleMessage.hideMessage();
					var temp,
						toString = Object.prototype.toString,
						//坐标
						x,y,
						//结果列表数组
						res = [],
						tempGeo,
						tempGraphic,
						symbol,
						symbols = [SymbolConfig.unnormalCrossSymbol, SymbolConfig.normalCrossSymbol],
						url;
					
					if (!result) {
						result = [];
					}
					showGraphics(result, "mapx", "mapy");
					
					crossList = result;
					crossListSize = crossList.length;
					showCrossPagerResult(1);
				},
				fail: function() {
					//res.push(new resItem("查询数据出错！", "", {}));
					resultBox().init("mapResultC").addContent({content: [new resItem("查询数据出错！")], switchtab: false});
					ModuleMessage.showMessage("查询数据出错！", ModuleMessage.ERROR);
				}
			});
		}
		var crossList,crossListSize;
		function showCrossPagerResult(pageNum) {
			var result = [];
			for(var i=(pageNum-1)*19,j=1; i<crossListSize && j<=19; i++,j++) {
				result.push(crossList[i]);
			}
			showCrossResult(result);
			Pager({
				parentSrc : "mapResultC",
				currentPage : pageNum,
				pageSize : 19,
				totalNum : crossListSize,
				pageOffset : 1,
				enableJump : true,
				enableStatistic : true,
				clickEvt : function(e){
					showCrossPagerResult(e.currentPage);
				}
			});
		}
		function showCrossResult(result) {
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
		 * 获取名称对应的graphic
		 * */
		function getGraphic(att) {
			if(!att || !att.code) {
				showTipMsg("此设备在地图上不存在！");
				InfoWindow().hide();
				return;
			}
			var graphic = getDeviceGraphic(att.code);
			if(!graphic) {
				showTipMsg("此设备在地图上不存在！");
				InfoWindow().hide();
				return;
			}
			// 这里定位偏移了
			//if(typeof att.gid != 'undefined')positionManager.highLightPosition(att.gid);
			getCrossDeviceByCode(graphic);
		}
		
		/**
		 * 结果下拉表展示
		 * @param res 数组
		 * */
		function setResultBox(result, name, model, res) {
			for (var i = result.length - 1; i >= 0; i--) {
				res.push(new resItem(result[i][name], getGraphic, result[i], result[i]));
				//res.push(new resItem(result[i][name], getGraphic(result[i]), transfer(result[i], model)));
			}
		}
		
		/**
		 * 按照model对象来转换data
		 * */
		function transfer(data, model) {
			var result = {};
			for (var key in model) {
				if (data[key]) {
					result[model[key]] = data[key];
				}
				if(key == "timeout")result[model[key]] = data[key]+"分钟";
			}
			return result;
		}
		
		// 根据卡口设备code获取卡口相关信息
		function getCrossDeviceByCode(g) {
			var att = g.attributes;
			var info = InfoWindow();
			info.setWidth(480);
			info.setHeight(264);
			info.setAnchor(new geoFun(g.geo));
			info.setTitle("卡口实时信息");
			info.setLoadPage("cross/map/getDeviceInfoByCode", {code: att["code"]}, function() {
				if(preCode)amq.removeListener("cross_current_img_" + preCode);
				preCode = att["code"];
				amq.addListener("cross_current_img_" + att["code"], mqcallback);
			});
			positionManager.addHighLightPosition(g.geo);
			info.showAndCenterInfowindow(3);
		}
		var crossDialogNum = 0;
		var _xhr = MapFactory.XHR,
			_dom = MapFactory.Dom;
		function getCrossDeviceByCodeDialog(g) {
			Dialog({
				title: "卡口实时信息",
				mutiDialog : true,
				mutiDialogSeed : "crossdialog"+crossDialogNum,
				mask: false,
				top: 35,
				right: 10,
				content: '<div id="crossdialog'+crossDialogNum+'" style="width:480px;height:264px;"></div>',
				buttonDisplay: {
					"confirmButton": false,
					"cancelButton": false
				},
				closeCall: function(){
				}
			}).show();
			// 异步加载卡口信息框
			_xhr.Load("crossdialog"+crossDialogNum, "cross/map/getDeviceInfoByCode" , {code: g.attributes["code"]});
			crossDialogNum++;
		}
		
		function destroy() {
			amq.removeListener("cross_current_alarm");
			alarmlayer.clear();
			layer.clear();
		}
		
		function autoFresh(flag) {
			if(flag) {
				amq.addListener("cross_current_alarm", _alarmCodesHandler);
			}
			else {
				amq.removeListener("cross_current_alarm", false);
			}
		}
		function autoFreshDeviceStatus(flag) {
			if(flag) {
				amq.addListener("device_status_log", function(message){
					deviceStatusHandler(message);
				});
			}
			else {
				amq.removeListener("device_status_log", false);
			}
		}
		function getAlarmCodes(visiable) {
			alarmlayer.clear();
			if(typeof visiable != 'undefined' && visiable == false) {
				InfoWindow().hide();
				positionManager.clear();
				return;
			}
			$.ajax({
			  type: "POST",
			  dataType: "json",
			  url: "cross/map/getalarm/allcodes",
			  cache: false,
			  success: function(list){
				showAlarm(list);
			  },
			  error: function(e){
			  	if (typeof console != 'undefined' && console.log) console.log("卡口报警查询codes时候，出错了！"+e);
			  }
			});
			
			if(typeof visiable == 'undefined' && firstLoadAlarmCross) {
				firstLoadAlarmCross = false;
				// 添加卡口报警更新地图报警设备
				autoFresh(true);
			}
		}
		
		// 更新地图上的卡口报警设备
		function showAlarm(list) {
			alarmlayer.clear();
			cagraphics = [];
			for(var i in list) {
				var cageo = getDeviceGraphic(list[i].deviceCode);
				// 添加报警卡口到地图上
				if(cageo)cagraphics.push({graphic:_addCrossAlarmToLayer(cageo), code:list[i].deviceCode, time:list[i].alarmtime});
			}
		}
		
		// 从前台缓存中根据卡口设备code值获取对应的卡口设备对象
		function getDeviceGraphic(code) {
			for(var j in crossDevices) {
				if(code == crossDevices[j].code) {
					// 添加报警卡口到地图上
					return crossDevices[j].graphic;
				}
			}
			return null;
		}
		
		// 卡口报警查询--更新列表-并添加位置标注图标
		var alarmPagenum=1;
		function alarmSearch(plateNum,startTime,endTime,isPagerClick) {
			if(!isPagerClick) {
				alarmPagenum=1;
			}
			ModuleMessage.showMessage("正在查询，请稍后...",ModuleMessage.LOG,99999);
			$.ajax({
			  type: "POST",
			  dataType: "json",
			  url: "cross/map/getalarmlist/20/"+alarmPagenum+"/"+currentModuleId+"/",
			  cache: false,
			  data: {plateNum:plateNum,startTime:startTime,endTime:endTime},
			  success: function(page){
			  	var list = page.result;
			  	if(list && list.length > 0) {
			  		ModuleMessage.hideMessage();
	    			addResultToMap(list);
    				showResult(list);
    				Pager({
						parentSrc : "mapResultC",
						currentPage : alarmPagenum,
						pageSize : 20,
						totalNum : page.totalSize,
						pageOffset : 1,
						enableJump : true,
						enableStatistic : true,
						clickEvt : function(e){
							alarmPagenum = e.currentPage;
							alarmSearch(plateNum,startTime,endTime,true);
						}
					});
    			}
    			else {
	        		ModuleMessage.showMessage("无该车辆卡口报警记录！",ModuleMessage.LOG);
    			}
			  },
			  error: function(e){
			  	ModuleMessage.showMessage("卡口报警查询时候，出错了！",ModuleMessage.ERROR);
			  }
			}); 
		}
		
		function showResult(result) {
			var res = [];
			for (var i=0,len=result.length; i<len; i++) {
				res.push(new ResItem(result[i].deviceName, function(data){
					// 传递报警车辆号码和卡口code，弹出对应的卡口报警信息框
					var g = getDeviceGraphic(data.deviceCode);
					if(g) {
						showAlarmInfowindow(new geoFun(g.geo), data.deviceCode, data.plateNum);
					}
				}, result[i], result[i]));
			}
			ResultBox().init("mapResultC").addContentAsTable({content: res, switchtab: true, relation: {plateNum: "号牌号码", deviceName: "设备名称"},colsWidth:[66]});
		}
		function addResultToMap(list) {
			positionManager.clear();
			InfoWindow().hide();
			for (var i=0,len=list.length; i<len; i++) {
				var g = getDeviceGraphic(list[i].deviceCode);
				if(g) {
					var points = g.geo.points.split(",");
					list[i].gid=positionManager.addPositionPoint(points[0], points[1], {});
				}
			}
		}
		
		// 地图报警卡口点击，弹出信息框
		function getCrossAlarmByDevice(g, showalarmbtn) {
			showAlarmInfowindow(new geoFun(g.geo), g.attributes.code, "", showalarmbtn);
		}
		
		function showAlarmInfowindow(point, code, plateNum, showalarmbtn) {
			var info = InfoWindow();
			info.setAnchor(point);
			info.setWidth(500);
			info.setHeight(300);
			info.setTitle("卡口报警信息");
			info.setLoadPage("cross/map/getalarminfo/"+code+"/500/1/?showalarmbtn="+showalarmbtn, {plateNum: plateNum}, function(){
			});
			positionManager.addHighLightPosition(point);
			info.showAndCenterInfowindow(3);
		}
		
		function _alarmCodesHandler(message) {
			var str, isaddflag=true;
			if (message.text == undefined) {
				str = message.textContent;
			} else {
				str = message.text;
			}
			
			var temp = str.split(";");
			if(!temp || temp.length!=2)return;
			var list = temp[0].split(",");
			var timelist = temp[1].split(",");
			alarmlayer.clear();
			cagraphics = [];// 重置
			for(var i in list) {
				for(var j in crossDevices) {
					isaddflag=true;
					if(list[i] == crossDevices[j].code) {
						// 添加报警卡口到地图上
						for(var k in cainfocancelcodes) {
							// 判断，如果不在取消列表中则添加
							if(list[i] == cainfocancelcodes[k] && cainfocanceltimes[k] == timelist[i]) {
								isaddflag=false;
								break;
							}
						}
						if(isaddflag)cagraphics.push({graphic:_addCrossAlarmToLayer(crossDevices[j].graphic), code:list[i], time:timelist[i]});
						break;
					}
				}
			}
		}
		
		function setCrossAlarmCancelCode(code, iscancel) {
			if(iscancel) {
				// 取消对应的graphic
				for(var k in cagraphics) {
					if(code == cagraphics[k].code && cagraphics[k].graphic) {
						cainfocancelcodes.push(code);
						cainfocanceltimes.push(cagraphics[k].time);
						cagraphics[k].graphic.hide();
						break;
					}
				}
			}
			else {
				for(var k in cainfocancelcodes) {
					if(code == cainfocancelcodes[k]) {
						cainfocancelcodes[k] = "";
						cainfocanceltimes[k] = "";
					}
				}
				// 添加对应的graphic
				for(var j in cagraphics) {
					if(code == cagraphics[j].code && cagraphics[j].graphic)cagraphics[j].graphic.show();
				}
			}
		}
		
		// 添加报警卡口到地图上
		function _addCrossAlarmToLayer(graphic) {
			if(graphic) {
				var gf = graphicFun({geo:graphic.geo, symbol:SymbolConfig.alarmCrossSymbol, attributes:graphic.attributes});
				gf.addToLayer("crossAlarm");
				return gf;
			}
			return null;
		}
		
		//获取卡口的各类别信息
		function getCrossInfo(fn) {
			$.ajax({
				url: "cross/map/getCrossInfo/",
				data: "",
				success: function(e) {
					typeof fn == "function" ? fn(e): "";
				},
				fail: function(e) {
					
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