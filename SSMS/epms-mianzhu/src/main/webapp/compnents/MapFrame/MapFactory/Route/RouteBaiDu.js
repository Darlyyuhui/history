MapFactory.Define("MapFactory/Route",[
	"MapFactory/RouteAPI*",
	"MapFactory/MapManager",
	"MapFactory/Message*",
	"MapFactory/GeometryType*"
],function(api,mapManager,messageClass,geoType){
	return function(){

		var _url, // 服务链接
			_start, // 起始点
			_stops = [], // 停靠点
			_end, // 终点
			_pointBarriers = [],// 障碍点
			_polylineBarriers = [],// 障碍线
			_polygonBarriers = [],// 障碍面
			_mapManager = mapManager();

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
			
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});