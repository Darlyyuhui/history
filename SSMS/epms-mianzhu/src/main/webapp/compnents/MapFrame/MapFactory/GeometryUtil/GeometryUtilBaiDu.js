MapFactory.Define("MapFactory/GeometryUtil",[
	"MapFactory/GeometryUtilAPI*",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*"
],function(api,geoClass,geoType){
	return function(){		
		function _convertArrToPoints(points){
			var _points = "";			
			for(var index in points){
				if(index!=0){
					_points += ",";
				}
				_points +=points[index].lng+","+points[index].lat;
			}
			return _points;
		}	
		function convertFromObject(obj){
			if(!obj){
				return null;
			}
			if( obj instanceof BMap.Point){
				return new geoClass({
					type : geoType.POINT,
					points : obj.lng + "," + obj.lat
				});
			}
			if(obj instanceof BMap.Polyline){
				return new geoClass({
					type : geoType.POLYLINE,
					points :_convertArrToPoints(obj.getPath())
				});
			}
			if(obj instanceof BMap.Polygon){
				return new geoClass({
					 type : geoType.POLYGON,
					 points :_convertArrToPoints(obj.getPath())
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
			if(geoType.POINT == geometry.type){
				     var _pointArr = geometry.points.split(",");
				     return new BMap.Point(parseFloat(_pointArr[0]),parseFloat(_pointArr[1]));
			}																				
			if(geoType.POLYLINE == geometry.type){
					var _pointArr = geometry.points.split(",");
					return new BMap.Polyline(_convertArrayType(_pointArr));
				}				
		    if(geoType.POLYGON == geometry.type){
					var _pointArr = geometry.points.split(",");
					return new BMap.Polygon(_convertArrayType(_pointArr));												
			}
			return null;
		}	
        function _convertArrayType(points){
            var tmpArr=[];
		  for(var i=0;i<points.length;i+=2){
			  tmpArr.push(new BMap.Point(parseFloat(points[i]),parseFloat(points[i+1])));
		  }
		   return tmpArr;           	
        }
              
		function union(url,geometries,callback,errback) {
		
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});