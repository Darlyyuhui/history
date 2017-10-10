MapFactory.Define("MapFactory/GeometryUtil",[
	"MapFactory/GeometryUtilAPI*",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*"
],function(api,geoClass,geoType){
	return function(){
 
		function convertFromObject(geometry){
			if(!geometry){
				return null;
			}
			var geoStr = geometry.toString(),
				pointsStr = geoStr;

			// 超图返回多面数据时，同样适用polygon，在这里转换为MultiPolygon
			if(geoStr.match(/\)\s*,\s*\(/ig)){
				geoStr = "MULTI" + geoStr;
				pointsStr = geoStr;
			}

			if(geoStr.match(/MULTI/)){
				pointsStr = pointsStr.replace(/\)\s*,\s*\(/ig,"|");
				if(geoStr.match(/POINT/)){
					pointsStr = pointsStr.replace("/,/","|");
				}
			}

			pointsStr = pointsStr.replace(/\(|\)|MULTI|MULTIPOLYGON|MULTIPOLYLINE|MULTIPOINT|POLYGON|LINESTRING|POINT/ig,"")
								 .replace(/\s+/g,",");

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
				tmpArr.push(new SuperMap.Geometry.Point(parseFloat(pointArr[i]),parseFloat(pointArr[i+1])));
			}
			return tmpArr;
		}

		function _convertToPoint(points){
			var pointArr = points.split(",");
			return new SuperMap.Geometry.Point(parseFloat(pointArr[0]),parseFloat(pointArr[1]));
		}

		function _convertToPolyline(points){
			return new SuperMap.Geometry.LineString(_convertPointStrToArray(points));
		}

		function _convertToPolygon(points){
			return new SuperMap.Geometry.Polygon([new SuperMap.Geometry.LinearRing(_convertPointStrToArray(points))]);
		}

		function _convertToMultiPoint(points){
			var pointArr = points.split("|"),
				geoArr = [];
			for(var i=0,len=pointArr.length;i<len;i++){
				geoArr.push(_convertToPoint(pointArr[i]));
			}
			return new SuperMap.Geometry.MultiPoint(geoArr);
		}

		function _convertToMultiPolyline(points){
			var pointArr = points.split("|"),
				geoArr = [];
			for(var i=0,len=pointArr.length;i<len;i++){
				geoArr.push(_convertToPolyline(pointArr[i]));
			}
			return new SuperMap.Geometry.MultiLineString(geoArr);
		}

		function _convertToMultiPolygon(points){
			var pointArr = points.split("|"),
				geoArr = [];
			for(var i=0,len=pointArr.length;i<len;i++){
				geoArr.push(_convertToPolygon(pointArr[i]));
			}
			return new SuperMap.Geometry.MultiPolygon(geoArr);
		}

		function union(geometries,callback,errback) {
			
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});