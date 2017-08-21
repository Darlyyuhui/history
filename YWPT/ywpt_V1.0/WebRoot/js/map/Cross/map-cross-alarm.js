
// 卡口报警
var mapCrossAlarm = (function() {
	return function() {
		var api = {
			getAlarmPageByParam : getAlarmPageByParam,
			setAlarmSearchResult : setAlarmSearchResult,
			updateAlarmLayer : updateAlarmLayer,
			getAlarmCodes : getAlarmCodes,
			alarmSearch : alarmSearch,
			setCacheName : setCacheName,
			getCrossAlarmByDevice : getCrossAlarmByDevice
		};
		
		// 根据卡口设备code和布控车辆 分页查找布控报警集合
		function getAlarmPageByParam(code,blackId,pageNumber) {
			$.ajax({
			  type: "POST",
			  url: "cross/alarm/byparam/"+code+"/"+blackId,
			  cache: false,
			  dataType: "json",
			  data:"pageNumber="+pageNumber,
			  success: function(result){
				// 查询结果为一个page对象
				if(!result)return;
				var list = result.list.result;// 布控报警vo对象的list集合
				var black = result.black;// 布控信息
				
				if(!list || list.length < 1 || !black) {
					$("#blackcolor").text("");
					$("#blacktype").text("");
					$("#cartype").text("");
					$("#alarmtype").text("");
					$("#alarmCarnum").text("");
					$("#alarmTime").text("");
					$("#mapcrossimg").attr("src","images/defaults.jpg");
					mapCross.imgList = [];
					mapCross.imgIndex = 0;
				}
				else {
					$("#blackcolor").text(black.carPlateColorCode);
					$("#blacktype").text(list[0].alarmType);
					$("#cartype").text(black.carPlateTypeCode);
					$("#alarmtype").text(list[0].alarmType);
					$("#alarmCarnum").text(list[0].plateNum);
					$("#alarmTime").text(list[0].trrafficTime);
					$("#mapcrossimg").attr("src",list[0].imgPath);
					mapCross.imgList = list;
					mapCross.imgIndex = 0;
					
					setCacheName("blackcolor", "platecolor", black.carPlateColorCode);
					setCacheName("cartype", "platetype", black.carPlateTypeCode);
				}
			  },
			  error: function(e){
			  }
			});
			return api;
		}
		
		function getCrossAlarmByDevice(g) {
		  	map.infoWindow.resize(530,300);
			map.infoWindow.setTitle("卡口报警信息");
			map.infoWindow.setContent("<div id='crossinfodiv'><img src='images/loading.gif'></div>");
			$("#crossinfodiv").load("cross/alarm/getalarminfobycode/"+g.attributes.code);
			map.infoWindow.show(g.geometry);
			return api;
		}
		
		function setAlarmSearchResult(result, isSwitch) {
			var _relation = {
				plateNum : "报警车辆",
				trrafficTime : "报警时间"
			};
			var _resArr = [];
			for(var i = 0;i < result.length;i++){
				/*var deptname = "";
				$("#gpsorgid").children().each(function (index, item){
					if($(item).val() == result[i].orgId) {
						deptname = $(item).text();
						result[i].orgId = deptname;
					}
				});*/
				_resArr.push(new resItemO(result[i].plateNum,function(obj){
					// 根据code在地图上查询对应设备并显示信息框
					var content = "<div style='width:300px;margin:0;padding:0;border:0px;'>"+
						"<img src='"+obj.imgPath+"' onclick='mapCross.crossImageLarge(this)' style='width: 300px;height: 260px;'></div>";
					
					map.infoWindow.setTitle(""+obj.deviceName+" "+obj.dircName+" "+obj.plateNum);
					map.infoWindow.setContent(content);
					map.infoWindow.resize(320,300);
					map.infoWindow.show(map.extent.getCenter());
					
					itmap.arcgis.query.queryByWherestr("code='"+obj.deviceCode+"'", function(queryResult) {
						if(queryResult.features.length == 0) {
							return;
						}
						map.infoWindow.show(queryResult.features[0].geometry);
						//mapCross.getCrossDeviceByCode(queryResult.features[0]);
					}, null, baseServiceURL.device.url);
				},result[i],result[i]));
			}
			$("#mapOther").css("display","none");
			itmap.util.mapResultboxNew().init("mapResultC").clearBox();
			itmap.util.mapResultboxNew().init("mapResultC").addContentAsTable({content:_resArr,switchtab:isSwitch,relation:_relation});
			return api;
		}
		
		function alarmSearch() {
			$.ajax({
			  type: "POST",
			  dataType: "json",
			  url: "cross/alarm/alllist",
			  cache: false,
			  success: function(list){
			  	debugger;
				if(!list)return;
				var resulthtml="<table class='commonResultTable'>";
				resulthtml += "<tr class=\"commonResultTableTitleRow\"><td>报警车辆</td><td>报警卡口信息</td></tr>";
				
				setAlarmSearchResult(list, false);
				showAlarm(list);
			  },
			  error: function(e){
			  	debugger;
			  }
			}); 
		}
		
		function getAlarmCodes() {
			$.ajax({
			  type: "POST",
			  dataType: "json",
			  url: "cross/alarm/allcodes",
			  cache: false,
			  success: function(list){
			  	debugger;
				showAlarm(list);
			  },
			  error: function(e){
			  	debugger;
			  }
			}); 
		}
		
		function updateAlarmLayer(list) {
			var codes=[];
			for(var i in list) {
				codes.push("'"+list[i]+"'");
			}
			var alarmlayer = itmap.arcgis.mapGraphicManager("crossAlarm").getLayer();
			queryCross(" code in ( "+codes.join(',')+" )", function(queryResult) {
				alarmlayer.clear();
				if(queryResult.features.length == 0) {
					return;
				}
				for(var i=0; i<queryResult.features.length; i++) {
					if(i < 10) {
						var graphic = queryResult.features[i];
						graphic.setSymbol(itmap.arcgis.symbol.crossAlarmSymbol());
						alarmlayer.add(graphic);
					}
				}
			});
		}
		
		function showAlarm(list) {
			map.infoWindow.hide();
			var alarmlayer = itmap.arcgis.mapGraphicManager("crossAlarm").getLayer();
			dojo.connect(alarmlayer, "onClick", function(s){
				// 异步查询，并弹出infotemplate窗口显示信息
				getCrossAlarmByDevice(s.graphic);
			});
			
			alarmlayer.clear();
			var codes=[];
			for(var i in list) {
				codes.push("'"+list[i].deviceCode+"'");
			}
			queryCross(" code in ( "+codes.join(',')+" )", function(queryResult) {
				if(queryResult.features.length == 0) {
					return;
				}
				for(var i=0; i<queryResult.features.length; i++) {
					if(i < 10) {
						var graphic = queryResult.features[i];
						graphic.setSymbol(itmap.arcgis.symbol.crossAlarmSymbol());
						alarmlayer.add(graphic);
					}
				}
			});
		}
		
		function queryCross(wherestr, showQueryResult) {
			var queryTask = new esri.tasks.QueryTask(baseServiceURL.device.url);
			var query = new esri.tasks.Query();
			query.where = wherestr;
			query.outFields = ["*"];
			query.returnGeometry = true;
			
			queryTask.execute(query, showQueryResult);
		}
		
		function setCacheName(divid, type, code) {
			$.ajax({
			  type: "POST",
			  url: "system/diccache/"+type+"/"+code,
			  dataType: "json",
			  cache: false,
			  success: function(obj){
			  	$("#"+divid).html(obj.name);
			  },
			  error: function(err) {
			  }
			});
		}
		
		return api;
	}
})();