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
			var _option = {
				url : _url,
				startPoint : _start.toString(),
				endPoint : _end.toString()
			};
			if(_stops.length){
				_option["stopPoints"] = MapFactory.JSON.Stringify(_stops);
			}else{
				_option["stopPoints"] = "";
			}
			if(_pointBarriers.length || _polylineBarriers.length || _polygonBarriers.length){
				_option["barriers"] = MapFactory.JSON.Stringify(_pointBarriers.concat(_polylineBarriers).concat(_polygonBarriers));
			}else{
				_option["barriers"] = "";
			}
			MapFactory.XHR.Post("openmap/query/route/",_option,function(res){
				successFunc(res);
			});
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});