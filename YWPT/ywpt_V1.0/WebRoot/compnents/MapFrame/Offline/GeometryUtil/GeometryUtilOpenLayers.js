MapFactory.Define("MapFactory/GeometryUtil",[
	"MapFactory/GeometryUtilAPI*",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*",
	"MapFactory/Message*"
],function(api,geoClass,geoType,messageClass){
var proj4326 = new OpenLayers.Projection("EPSG:4326");
var proj900913 = new OpenLayers.Projection("EPSG:900913");
function transformTo900913(x, y) {
	x -= offsetX;
	y -= offsetY;
	var t = new OpenLayers.LonLat(x, y);
	if(x < 180 && y < 90) {
		t = t.transform(proj4326, proj900913);
	}
	return t;
}
function transformTo4326(x, y) {
	var t = new OpenLayers.LonLat(x, y);
	if(x > 180 && y > 90) {
		t = t.transform(proj900913, proj4326);
		t = new OpenLayers.LonLat(t.lon + offsetX, t.lat + offsetY);
	}
	return t;
}
	return function(){

		function convertFromObject(geometry){
			if(!geometry){
				return null;
			}
			var geoStr = geometry.toString(),
				pointsStr = geoStr;

			if(geoStr.match(/MULTI/)){
				pointsStr = pointsStr.replace(/\)\s*,\s*\(/ig,"|");
				if(geoStr.match(/POINT/)){
					pointsStr = pointsStr.replace("/,/","|");
				}
			}

			pointsStr = pointsStr.replace(/\(|\)|MULTI|MULTIPOLYGON|MULTIPOLYLINE|MULTIPOINT|POLYGON|LINESTRING|POINT/ig,"")
								 .replace(/\s+/g,",");

			// 将传递进来的geo转换为4326坐标
			var pointArr = pointsStr.split(","),
				arrLen = pointArr.length,
				tmpArr = [];
			for(var i=0;i<arrLen;i+=2){
				var tp = transformTo4326(parseFloat(pointArr[i]),parseFloat(pointArr[i+1]));
				tmpArr.push(tp.lon);
				tmpArr.push(tp.lat);
			}
			pointsStr = tmpArr.join(",");

			if(geoStr.match(/MULTIPOINT/)){
				return new geoClass({
					type : geoType.MULTIPOINT,
					points : pointsStr
				});
			}

			if(geoStr.match(/MULTILINESTRING/)){
				return new geoClass({
					type : geoType.MULTIPOLYLINE,
					points : pointsStr
				});
			}

			if(geoStr.match(/MULTIPOLYGON/)){
				return new geoClass({
					type : geoType.MULTIPOLYGON,
					points : pointsStr
				});
			}

			if(geoStr.match(/POINT/)){
				return new geoClass({
					type : geoType.POINT,
					points : pointsStr
				});
			}

			if(geoStr.match(/LINESTRING/)){
				return new geoClass({
					type : geoType.POLYLINE,
					points : pointsStr
				});
			}

			if(geoStr.match(/POLYGON/)){
				return new geoClass({
					type : geoType.POLYGON,
					points : pointsStr
				});
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

			switch(geometry.type){
				case geoType.POINT : {
					return _convertToPoint(geometry.points);
					break;
				}
				case geoType.POLYLINE : {
					return _convertToPolyline(geometry.points);
					break;
				}
				case geoType.POLYGON : {
					return _convertToPolygon(geometry.points);
					break;
				}
				case geoType.MULTIPOINT : {
					return _convertToMultiPoint(geometry.points);
					break;
				}
				case geoType.MULTIPOLYLINE : {
					return _convertToMultiPolyline(geometry.points);
					break;
				}
				case geoType.MULTIPOLYGON : {
					return _convertToMultiPolygon(geometry.points);
					break;
				}
			}

			return null;
		}

		function _convertPointStrToArray(points){
			var pointArr = points.split(","),
				arrLen = pointArr.length,
				tmpArr = [];
			for(var i=0;i<arrLen;i+=2){
				//转换为900913坐标
				var tp = transformTo900913(parseFloat(pointArr[i]),parseFloat(pointArr[i+1]));
				tmpArr.push(new OpenLayers.Geometry.Point(tp.lon, tp.lat));
			}
			return tmpArr;
		}

		function _convertToPoint(points){
			var pointArr = points.split(",");
			//转换为900913坐标
			var tp = transformTo900913(parseFloat(pointArr[0]),parseFloat(pointArr[1]));
			return new OpenLayers.Geometry.Point(tp.lon, tp.lat);
		}

		function _convertToPolyline(points){
			return new OpenLayers.Geometry.LineString(_convertPointStrToArray(points));
		}

		function _convertToPolygon(points){
			return new OpenLayers.Geometry.Polygon([new OpenLayers.Geometry.LinearRing(_convertPointStrToArray(points))]);
		}

		function _convertToMultiPoint(points){
			var pointArr = points.split("|"),
				geoArr = [];
			for(var i=0,len=pointArr.length;i<len;i++){
				geoArr.push(_convertToPoint(pointArr[i]));
			}
			return new OpenLayers.Geometry.MultiPoint(geoArr);
		}

		function _convertToMultiPolyline(points){
			var pointArr = points.split("|"),
				geoArr = [];
			for(var i=0,len=pointArr.length;i<len;i++){
				geoArr.push(_convertToPolyline(pointArr[i]));
			}
			return new OpenLayers.Geometry.MultiLineString(geoArr);
		}

		function _convertToMultiPolygon(points){
			var pointArr = points.split("|"),
				geoArr = [];
			for(var i=0,len=pointArr.length;i<len;i++){
				geoArr.push(_convertToPolygon(pointArr[i]));
			}
			return new OpenLayers.Geometry.MultiPolygon(geoArr);
		}

		function union(url,geometries,callback,errback) {
			MapFactory.XHR.Post(url,{geos: MapFactory.JSON.Stringify(geometries)},function(geometry){
				callback(geometry);
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