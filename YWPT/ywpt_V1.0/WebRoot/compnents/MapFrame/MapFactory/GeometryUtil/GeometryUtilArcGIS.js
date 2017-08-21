MapFactory.Define("MapFactory/GeometryUtil",[
	"MapFactory/GeometryUtilAPI*",
	"MapFactory/MapManager",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*",
	"esri/geometry/Point*",
	"esri/geometry/Polyline*",
	"esri/geometry/Polygon*",
	"esri/geometry/Multipoint*",
	"esri/tasks/GeometryService*",
	"esri/SpatialReference*",
	"MapFactory/Message*"
],function(api,mapManager,geoClass,geoType,PointClass,PolylineClass,PolygonClass,MultipointClass,GeometryService,srClass,messageClass){
	return function(){

		var _mapManager = mapManager();

		function _convertArrToPoints(pointsArr){
			var _points = "",
				_objPoints = pointsArr;
			for(var i=0,len=_objPoints.length;i<len;i++){
				if(i!=0){
					_points += "|";
				}
				_points += _objPoints[i].toString();
			}
			return _points;
		}

		function convertFromObject(obj){
			if(!obj){
				return null;
			}
			if("point" == obj.type){
				return new geoClass({
					type : geoType.POINT,
					points : obj.x + "," + obj.y
				});
			}
			if("multipoint" == obj.type){
				return new geoClass({
					type : geoType.MULTIPOINT,
					points : _convertArrToPoints(obj.points)
				});
			}
			if("polyline" == obj.type){
				var _paths = obj.paths,
					_pathsLen = _paths.length;
				if(_pathsLen == 1){
					return new geoClass({
						type : geoType.POLYLINE,
						points : _paths.toString()
					});
				}else if(_pathsLen > 1){
					return new geoClass({
						type : geoType.MULTIPOLYLINE,
						points : _convertArrToPoints(_paths)
					});
				}
			}
			if("polygon" == obj.type){
				var _rings = obj.rings,
					_ringsLen = _rings.length;
				if(_ringsLen == 1){
					return new geoClass({
						type : geoType.POLYGON,
						points : _rings.toString()
					});
				}else if(_ringsLen > 1){
					return new geoClass({
						type : geoType.MULTIPOLYGON,
						points : _convertArrToPoints(_rings)
					});
				}
			}
			return null;
		}

		function convertFromMapFactory(geometry){
			if(!geometry){
				return null;
			}
			if(MapFactory.VariableTypes.isString(geometry)){
				geometry = MapFactory.JSON.Parse(geometry);
			}
			var _multiReg = /\|/g,
				_points = geometry.points,
				_isMulti = _points.match(_multiReg);
			
			if(geoType.POINT == geometry.type){
				var _pointArr = geometry.points.split(",");
				return new PointClass(parseFloat(_pointArr[0]),parseFloat(_pointArr[1]),new srClass(_mapManager.getSpatialReferenceCode()));
			}else{
				var _pointsArr = _points.split("\|"),
					_pointsLen = _pointsArr.length;
				if(geoType.MULTIPOINT == geometry.type){
					var MultiP = new MultipointClass(new srClass(_mapManager.getSpatialReferenceCode()));
					for(var i=0;i<_pointsLen;i++){
						var _point = _pointsArr[i].split(",");
						MultiP.addPoint({"x":parseFloat(_point[0]),"y":parseFloat(_point[1])});
					}
					return MultiP;
				}
				if(geoType.MULTIPOLYLINE == geometry.type || geoType.POLYLINE == geometry.type){
					var _polyline = new PolylineClass(new srClass(_mapManager.getSpatialReferenceCode()));
					for(var i=0;i<_pointsLen;i++){
						var _tempPointsArr = _pointsArr[i].split(","),
							_tempPoints = [];
						var _prex, _prey, _x, _y;
						for(var j=0,_tempPointsArrLen=_tempPointsArr.length;j<_tempPointsArrLen;j+=2){
							// 剔除线上的重复点---xiongjie添加
							_x = parseFloat(_tempPointsArr[j]);
							_y = parseFloat(_tempPointsArr[j+1]);
							if(typeof _prey == 'undefined' || !(_prex == _x && _prey == _y)) {
								_tempPoints.push([_x, _y]);
							}
							_prex = _x;
							_prey = _y;
							//_tempPoints.push([parseFloat(_tempPointsArr[j]),parseFloat(_tempPointsArr[j+1])]);
						}
						_polyline.addPath(_tempPoints);
					}
					return _polyline;
				}
				if(geoType.MULTIPOLYGON == geometry.type || geoType.POLYGON == geometry.type){
					var polygon = new PolygonClass(new srClass(_mapManager.getSpatialReferenceCode()));
					for(var i=0;i<_pointsLen;i++){
						var _tempPointsArr = _pointsArr[i].split(","),
							_tempPoints = [];
						var _prex, _prey, _x, _y;
						for(var j=0,_tempPointsArrLen=_tempPointsArr.length;j<_tempPointsArrLen;j+=2){
							// 剔除面上的重复点---xiongjie添加
							_x = parseFloat(_tempPointsArr[j]);
							_y = parseFloat(_tempPointsArr[j+1]);
							if(typeof _prey == 'undefined' || !(_prex == _x && _prey == _y)) {
								_tempPoints.push([_x, _y]);
							}
							_prex = _x;
							_prey = _y;
							//_tempPoints.push([parseFloat(_tempPointsArr[j]),parseFloat(_tempPointsArr[j+1])]);
						}
						polygon.addRing(_tempPoints);
					}
					return polygon;
				}
			}
			return null;
		}

		function union(url,geometries,callback,errback) {
			var geoService = new GeometryService(url);
			var mapGeos = [];
			for(var i=0,len=geometries.length; i<len; i++) {
				var mapGeo = convertFromMapFactory(geometries[i]);
				if(mapGeo)mapGeos.push(mapGeo);
			}
			geoService.union(mapGeos,function(geometry){
				callback(convertFromObject(geometry));
			},function(){
				if(errback){
					errback(new messageClass({
						message : "几何体合并错误"
					}));
				}
			});
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});