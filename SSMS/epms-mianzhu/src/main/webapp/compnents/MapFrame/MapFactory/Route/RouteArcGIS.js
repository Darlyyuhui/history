MapFactory.Define("MapFactory/Route",[
	"MapFactory/RouteAPI*",
	"MapFactory/MapManager",
	"esri/tasks/RouteParameters*",
	"esri/tasks/RouteTask*",
	"esri/tasks/FeatureSet*",
	"MapFactory/GeometryUtil",
	"MapFactory/Message*",
	"esri/graphic*",
	"esri/SpatialReference*",
	"MapFactory/GeometryType*"
],function(api,mapManager,routeParams,routeTask,featureSet,geoUtil,messageClass,graphicClass,srClass,geoType){
	return function(){

		var _url, // 服务链接
			_start, // 起始点
			_stops = [], // 停靠点
			_end, // 终点
			_pointBarriers = [],// 障碍点
			_polylineBarriers = [],// 障碍线
			_polygonBarriers = [],// 障碍面
			_geoUtil = geoUtil(),
			_mapManager = mapManager();

		function setUrl(url){
			_url = url;
		}

		function setStart(geometry){
			_start = new graphicClass(_geoUtil.convertFromMapFactory(geometry));
		}

		function setStops(geometrys){
			_stops = [];
			for(var i=0,len = geometrys.length;i<len;i++){
				_stops.push(new graphicClass(_geoUtil.convertFromMapFactory(geometrys[i])));
			}
		}

		function setEnd(geometry){
			_end = new graphicClass(_geoUtil.convertFromMapFactory(geometry));
		}

		function setBarriers(geometrys){
			_pointBarriers = [];
			_polylineBarriers = [];
			_polygonBarriers = [];
			for(var i=0,len = geometrys.length;i<len;i++){
				var geometry = geometrys[i];
				switch(geometry.type){
					case geoType.POINT : {
						_pointBarriers.push(new graphicClass(_geoUtil.convertFromMapFactory(geometrys[i])));
						break;
					}
					case geoType.POLYLINE : {
						_polylineBarriers.push(new graphicClass(_geoUtil.convertFromMapFactory(geometrys[i])));
						break;
					}
					case geoType.POLYGON : {
						_polygonBarriers.push(new graphicClass(_geoUtil.convertFromMapFactory(geometrys[i])));
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

			var _rt = new routeTask(_url),
				_rtParams = new routeParams(),
				_stopFeatureSet = new featureSet(),
				_pointBarriersFeatureSet = new featureSet(),
				_polylineBarriersFeatureSet = new featureSet(),
				_polygonBarriersFeatureSet = new featureSet();

			_stopFeatureSet.features = [_start].concat(_stops).concat([_end]);
			_pointBarriersFeatureSet.features = _pointBarriers;
			_polylineBarriersFeatureSet.features = _polylineBarriers;
			_polygonBarriersFeatureSet.features = _polygonBarriers;

			_rtParams.stops = _stopFeatureSet;
			_rtParams.outSpatialReference = new srClass(_mapManager.getSpatialReferenceCode());
			_rtParams.barriers = _pointBarriersFeatureSet;
			_rtParams.polylineBarriers = _polylineBarriersFeatureSet;
			_rtParams.polygonBarriers = _polygonBarriersFeatureSet;
			_rtParams.outputGeometryPrecisionUnits = "esriMeters";
			_rtParams.returnRoutes = true;
			_rt.solve(_rtParams,function(routeResult){
				var _routeSet = routeResult.routeResults,
					_routeRes = [];
				for(var i=0,len=_routeSet.length;i<len;i++){
					_routeRes.push({
						geo : _geoUtil.convertFromObject(_routeSet[i].route.geometry),
						attributes : _routeSet[i].route.attributes
					});
				}
				successFunc(_routeRes);
			},function(error){
				if(failureFunc){
					failureFunc(new messageClass({
						message : "路径查询错误"
					}));
				}
			});
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});