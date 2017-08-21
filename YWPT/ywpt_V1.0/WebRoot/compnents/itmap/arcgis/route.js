/*
 * 路网分析辅助类 
 */

itmap.arcgis.route = function(){
	return function(routeurl){
		var api = {
				setBarriersLayer : setBarriersLayer,
				setBarriers : setBarriers,
				routeSolve : routeSolve
			};
		var util = itmap.arcgis.util;
		
		var msg = "";// divid提示信息
		
		var preIndex=0;// 上一次错误点的下标，为数组下标，所以应该在index的基础上+1
		var remotePointList=[];//远程对象查询结果封装为mapPoint的集合点
		var pNum=0;//remotePointList的length属性
		var resultPoint=new Array();//分析结果中的点对象(实际为{x:109,y:34.2})的集合
		var isRouteSearchFault = false;// 是否为错误查询
		var _callback;
		var _barriers;
		var _barriersPolyline;
		var _barriersPolygon;
		
		/**
		 * obj：对象[109,34.2]集合
		 * callback：返回函数，路网分析成功后调用的监听函数，参数为：点对象[109,34.2]的集合
		 * */
		function routeSolve(array,callback,msgdiv) {
			if(!util.checkParam(array)) {
				if(!util.checkParam(callback))callback(resultPoint);
				return;
			}
			if(util.checkParam(msgdiv) && "" != msgdiv)msg = msgdiv;
			remotePointList = array;
			pNum = remotePointList.length;
			_callback = callback;
			routeSearch(0);
		}
		/**
		 * 通过障碍点图层，设置网络分析时的障碍线和面的featureSet
		 * */
		function setBarriersLayer(blayer) {
			var gs = blayer.graphics;
			if(!_barriers)_barriers = new esri.tasks.FeatureSet();
			if(!_barriersPolyline)_barriersPolyline = new esri.tasks.FeatureSet();
			if(!_barriersPolygon)_barriersPolygon = new esri.tasks.FeatureSet();
			for(var i in gs) {
				switch(gs[i].geometry.type) {
					case "point" : 
						_barriers.features.push(gs[i]);
					break;
					case "polyline" : 
						_barriersPolyline.features.push(gs[i]);
					break;
					case "polygon" : 
						_barriersPolygon.features.push(gs[i]);
					break;
				}
			}
			return api;
		}
		/**
		 * 设置网络分析时的障碍线和面的featureSet
		 * */
		function setBarriers(barriers, polylineSet, polygonSet) {
			if(util.checkParam(barriers))_barriers = barriers;
			if(util.checkParam(polylineSet))_barriersPolyline = polylineSet;
			if(util.checkParam(polygonSet))_barriersPolygon = polygonSet;
			return api;
		}
		/**
		 * route查询，这个查询有可能会出现，查询错误。不可到达点
		 * @param index 错误点号，比如错误信息：1-2,7-8点查询出错，则index=1
		 * */
		function routeSearch(index) {
			var list = [];
			if(index == 0) {
				list = remotePointList;
			}
			else {
				// 错误查询，首先给list赋值
				var pointindex = preIndex+index;
				for(var i=0;pointindex<pNum;pointindex++,i++) {
					list[i] = remotePointList[pointindex];
				}
				// 到最后一个点了，直接添加到resultPoint并返回，完成分析
				if(list.length < 2) {
					if(list.length == 1) {
						var p3array = [];
						p3array[0] = Number(remotePointList[pointindex][0]);
						p3array[1] = Number(remotePointList[pointindex][1]);
						resultPoint.push(p3array);
					}
					_callback(resultPoint);
					if(msg)$("#"+msg).html("网络分析查询成功！");
					return;
				}
				
				preIndex += index;
			}
			
			if(msg)$("#"+msg).html("网络分析查询中...");
			
			var route = new esri.tasks.RouteTask(routeurl);
			var params = new esri.tasks.RouteParameters();
			var pointset = new esri.tasks.FeatureSet();
			for(var i in list) {
				pointset.features.push(new esri.Graphic(new esri.geometry.Point(list[i],map.spatialReference)));
			}
			params.stops = pointset;
			params.directionsLengthUnits = esri.Units.MILES;
			params.outSpatialReference = map.spatialReference;
			if(_barriers)params.barriers = _barriers;
			if(_barriersPolyline)params.polylineBarriers = _barriersPolyline;
			if(_barriersPolygon)params.polygonBarriers = _barriersPolygon;
			
			route.solve(params,routecallback,routeerrorback);
		}
		
		//完成网络分析进入的方法
		function routecallback(solveResult) {
			var routeResult = solveResult.routeResults[0];
			// 分析结果的线，为一个graphic  但样式symbol=null
			var lastRoute = routeResult.route;
			
			var routesymbol = new esri.symbol.SimpleLineSymbol(
					esri.symbol.SimpleLineSymbol.STYLE_SOLID,new dojo.Color([0,0,255,0.5]),3);
			// 拿到查询结果的线Polyline
			var routeLines = lastRoute.geometry;
			// 网络分析线上的点的集合
			var routePoints = [];
			
			if(!routeLines) return;
			var linePath = routeLines.paths;
			var linePathLen = linePath.length;
			var pointnum = 0;
			var points = new Array();
			for(var k=0; k<linePathLen; k++) {
				var lineCP = linePath[k];
				if(lineCP) {
					var lineCPLen = lineCP.length;
					pointnum += lineCPLen;
					for(var m=0; m<lineCPLen; m++) {
						var rmp = lineCP[m];
						if(rmp) {
							routePoints.push(new Array(rmp[0],rmp[1]));
							points.push(new Array(rmp[0],rmp[1]));
							if(isRouteSearchFault) {
								// 查询结果为错误查询结过集
								resultPoint.push(new Array(rmp[0],rmp[1]));//添加网络分析点的集合到结果集
							}
						}
					}
				}
			}
			
			// 当错误查询全部排查完毕时，重新设置lastRoute的graphic绘制到地图上
			if(isRouteSearchFault) {
				points = resultPoint;
			}
			
			_callback(points);
			if(msg)$("#"+msg).html("网络分析查询成功！");
		}
		
		function routeerrorback(error) {
			if(!error || !error.message)return;
		
			// 设置为错误查询模式
			isRouteSearchFault = true;
			if(error.message == "Error solving route") {
				faultNum++;
				if(msg)$("#"+msg).html("再次路由查询出错，遇到不可到达点！"+faultNum+"\n错误点信息："+error.details[0]);
				var routeArray = error.details[0].match(/\d+/g);
				if(routeArray.length < 2) {
					alert("第一次，网络分析查询失败！");
					return;
				}
				var routeMan = Number(routeArray[0]);
				var routeMin = Number(routeArray[0]);
				for(var i=0; i<routeArray.length-1; i++) {
					if(Number(routeArray[i]) < Number(routeArray[i+1])) {
						routeMan = Math.max(Number(routeArray[i+1]), routeMan);
						routeMin = Math.min(Number(routeArray[i]), routeMin);
					}
				}
				
				// 需要用到递归
				routeFaultSearch(routeMin);
			}
			else alert("第一次，网络分析查询失败！"+error.message);
		};
		var fs = 0;
		var faultNum = 0;
		/**
		 * route查询遇到错误的时候，查询前半部分，这个查询不会出错！
		 * */
		function routeFaultSearch(faultStartIndex) {
			fs++;
			if(msg)$("#"+msg).html("第"+fs+"次错误查询");
			var rt = new esri.tasks.RouteTask(routeurl);
			var rp = new esri.tasks.RouteParameters();
			var sstemp = new esri.tasks.FeatureSet();
			for(var i=0; i<faultStartIndex; i++) {
				sstemp.features.push(new esri.Graphic(new esri.geometry.Point(remotePointList[i+preIndex],map.spatialReference)));
			}
			// 如果为第一点，也就是网路查询的时候只有一个点，则直接添加到points中
			if(sstemp.features.length <= 1) {
				// 如果错误点前面没有点了，也就是，分析的时候只有一个点，则添加错误点到resultPoint集合
				if(faultStartIndex == 1) {
					var p2array = new Array();
					p2array[0] = Number(remotePointList[preIndex][0]);
					p2array[1] = Number(remotePointList[preIndex][1]);
					resultPoint.push(p2array);
				}
				routeSearch(faultStartIndex);
				return;
			}
			rp.stops = sstemp;
			rp.outSpatialReference = map.spatialReference;
			rp.directionsLengthUnits = esri.Units.MILES;
			if(_barriers)rp.barriers = _barriers;
			if(_barriersPolyline)rp.polylineBarriers = _barriersPolyline;
			if(_barriersPolygon)rp.polygonBarriers = _barriersPolygon;
			rt.solve(rp,function(solveResult){
				if(msg)$("#"+msg).html("第"+fs+"次错误查询，前半部分成功，准备进行后半段查询...");
				var routeResult = solveResult.routeResults[0];
				// 分析结果的线，为一个graphic  但样式symbol=null
				var lastRoute = routeResult.route;
				
				// 拿到查询结果的线Polyline
				var routeLines = lastRoute.geometry;
				// 网络分析线上的点的集合
				
				if(!routeLines) {
					// 调用路由查询，错误点后的分析
					routeSearch(faultStartIndex);
					return;
				}
				var linePath = routeLines.paths;
				var linePathLen = linePath.length;
				for(var k=0; k<linePathLen; k++) {
					var lineCP = linePath[k];
					if(lineCP) {
						var lineCPLen = lineCP.length;
						for(var m=0; m<lineCPLen; m++) {
							var rmp = lineCP[m];
							if(rmp) {
								if(isRouteSearchFault) {
									// 查询结果为错误查询结果
									resultPoint.push(new Array(rmp[0],rmp[1]));//添加网络分析点的集合到结果集
								}
							}
						}
					}
				}
		
				if(msg)$("#"+msg).html("网络分析错误点前部分查询成功！");
				
				// 调用路由查询，错误点后的分析
				routeSearch(faultStartIndex);
			},function(error){
				if(!error || !error.message)return;
			});
		}

	return api;
	}
}();