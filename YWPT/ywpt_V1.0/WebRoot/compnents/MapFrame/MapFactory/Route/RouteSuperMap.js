MapFactory.Define("MapFactory/Route",[
	"MapFactory/RouteAPI*",
	"MapFactory/MapManager",
	"MapFactory/Message*",
	"MapFactory/GeometryType*",
	"MapFactory/GeometryUtil",
	"MapFactory/Geometry*"
],function(api,mapManager,messageClass,geoType,GeometryUtil,Geometry){
	return function(){

		var _url, // 服务链接
			_start, // 起始点
			_stops = [], // 停靠点
			_end, // 终点
			_pointBarriers = [],// 障碍点
			_polylineBarriers = [],// 障碍线
			_polygonBarriers = [],// 障碍面
			_mapManager = mapManager();
		
		var _geometryUtil = GeometryUtil();

		function setUrl(url){
			_url = url;
		}

		function setStart(geometry){
			_start = geometry;
		}

		function setStops(geometrys){
			_stops = [];
			if(!MapFactory.VariableTypes.isArray(geometrys)){
				return;
			}
			for(var i=0,len = geometrys.length;i<len;i++){
				_stops.push(geometrys[i]);
			}
		}

		function setEnd(geometry){
			_end = geometry;
		}

		function setBarriers(geometrys){
			_pointBarriers = [];
			_polylineBarriers = [];
			_polygonBarriers = [];
			if(!MapFactory.VariableTypes.isArray(geometrys)){
				return;
			}
			for(var i=0,len = geometrys.length;i<len;i++){
				var geometry = geometrys[i];
				switch(geometry.type){
					case geoType.POINT : {
						_pointBarriers.push(geometrys[i]);
						break;
					}
					case geoType.POLYLINE : {
						_polylineBarriers.push(geometrys[i]);
						break;
					}
					case geoType.POLYGON : {
						_polygonBarriers.push(geometrys[i]);
						break;
					}
				}
			}
		}
	
		function solve(successFunc,failureFunc){
			if(!_url){
				if(failureFunc){
					failureFunc(new messageClass({
						message : "服务地址未设置"
					}));
				}
				return;
			}
			if(!(_start && _end)){
				if(failureFunc){
					failureFunc(new messageClass({
						message : "起始点或结束点未设置"
					}));
				}
				return;
			}
			var _findPathService,_findPathparameter,_analystParameter,_resultSetting;
			var _nodeArray = [];
			_nodeArray.push(_geometryUtil.convertFromMapFactory(_start));
			for(var i=0,len=_stops.length;i<len;i++){
				_nodeArray.push(_geometryUtil.convertFromMapFactory(_stops[i]));
			}
			_nodeArray.push(_geometryUtil.convertFromMapFactory(_end));
			_resultSetting = new SuperMap.REST.TransportationAnalystResultSetting({
				returnEdgeFeatures: true,
				returnEdgeGeometry: true,
				returnEdgeIDs: true,
				returnNodeFeatures: true,
				returnNodeGeometry: true,
				returnNodeIDs: true,
				returnPathGuides: true,
				returnRoutes: true
			});
			_analystParameter = new SuperMap.REST.TransportationAnalystParameter({
				resultSetting: _resultSetting,
				weightFieldName: "SmLength"
			});
			_findPathparameter = new SuperMap.REST.FindPathParameters({
                isAnalyzeById: false,
                nodes: _nodeArray,
                hasLeastEdgeCount: false,
                parameter: _analystParameter
            });
			_findPathService = new SuperMap.REST.FindPathService(_url,{
                eventListeners : {
					"processCompleted":function(res){
						var graphics = [];
						var result = res.result.pathList;
						for(var i=0,len=result.length;i<len;i++){
							var path = result[i].route.components;
							for(var j=0,sublen=path.length;j<sublen;j++){
								graphics.push({
									geo : new Geometry({
										type : geoType.POINT,
										points : path[j].x + "," + path[j].y
									}),
									attributes : {
										
									}
								});
							}
						}
						successFunc(graphics);
					},
					"processFailed" : function(failureInfo){
						if(failureFunc){
							failureFunc(new messageClass({
								message : "分析失败"
							}));
						}
					}
				}
            });
			_findPathService.processAsync(_findPathparameter);
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});