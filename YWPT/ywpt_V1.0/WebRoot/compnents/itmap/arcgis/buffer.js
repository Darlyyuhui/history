/**
 * 缓冲区查询封装类
 */
itmap.arcgis.buffer = function(){
	return function(geometryurl) {
		
		var api = {
				bufferSearch:bufferSearch
			};
		var util = itmap.arcgis.util;
		var symbol = itmap.arcgis.symbol;
		
		var pGeometryService=  new esri.tasks.GeometryService(geometryurl);
	  	var params;
		function bufferSearch(geo,callback,distances,layer) {
			if(!util.checkParam(distances))distances = 10;
			if(!params) {
				params = new esri.tasks.BufferParameters();
				params.unit = esri.tasks.GeometryService.UNIT_METER;// 缓冲单位--米
				params.outSpatialReference = map.spatialReference;
				//params.bufferSpatialReference = map.spatialReference;
				params.bufferSpatialReference = new esri.SpatialReference({wkid:32649}); // 转换为平面坐标
			}
			params.distances = [distances]; // 缓冲大小
			params.geometries = [geo];
			pGeometryService.buffer(params, function(geometries) {
				if(geometries.length > 0) {
					map.infoWindow.resize(300,100);
					if(util.checkParam(layer)) {
						var g = new esri.Graphic(geometries[0], symbol.defaultPolygonSymbol);
						layer.add(g);
					}
					
					if(callback)callback(geometries);
				}
			}, function (error) {
				if(typeof console != 'undefined' && console.log)console.log("缓冲查询错误："+error);
			});
		}
		
		return api;
	}
}();