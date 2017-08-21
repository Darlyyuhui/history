/**
 * 编辑
 */
itmap.arcgis.edit = function(){
		var api = {
				addPoint:addPoint,
				addPolyline:addPolyline,
				addPolygon:addPolygon,
				updatePoint:updatePoint,
				updatePolyline:updatePolyline,
				updatePolygon:updatePolygon,
				delGeometry:delGeometry
			};
		
		var typeutil = itmap.util.variableTypes;
		
		/**
		 * 添加点
		 * 参数 context图层服务的位置eg:atms/cross
		 * 参数 index所在context中的位置eg:0
		 * 参数 geometry几何信息，这里为点对象
		 * 参数 attribute要素属性信息
		 * 参数 callback编辑完成后，返回的函数，一般为状态
		 * */
		function addPoint(context, index, geometry, attribute, callback) {
			var obj = getAttributes(attribute);
			$.ajax({
			  type: "POST",
			  url: "arcgis/edit/addpoint/"+index,
			  data: "point="+getPoint(geometry)+"&attkey="+obj.attkey+"&attvalue="+obj.attvalue+"&contextName="+context,
			  cache: false,
			  success: function(html){
				  if(typeutil.isFunc(callback))callback(html);
			  },
			  error: function(e) {
				  if(typeutil.isFunc(callback))callback(e);
			  }
			});
		}
		function addPolyline(context, index, geometry, attribute, callback) {
			var obj = getAttributes(attribute);
			$.ajax({
			  type: "POST",
			  url: path+"/arcgis/edit/addpolyline/"+index,
			  data: "geometry="+getPolyline(geometry)+"&attkey="+obj.attkey+"&attvalue="+obj.attvalue+"&contextName="+context,
			  cache: false,
			  success: function(html){
				  if(typeutil.isFunc(callback))callback(html);
			  },
			  error: function(e) {
				  if(typeutil.isFunc(callback))callback(e);
			  }
			});
		}
		function addPolygon(context, index, geometry, attribute, callback) {
			var obj = getAttributes(attribute);
			$.ajax({
			  type: "POST",
			  url: "arcgis/edit/addpolygon/"+index,
			  data: "geometry="+getPolygon(geometry)+"&attkey="+obj.attkey+"&attvalue="+obj.attvalue+"&contextName="+context,
			  cache: false,
			  success: function(html){
				  if(typeutil.isFunc(callback))callback(html);
			  },
			  error: function(e) {
				  if(typeutil.isFunc(callback))callback(e);
			  }
			});
		}
		function updatePoint(context, index, objectid, geometry, attribute, callback) {
			var obj = getAttributes(attribute);
			$.ajax({
			  type: "POST",
			  url: "arcgis/edit/updatepoint/"+index,
			  data: "point="+getPoint(geometry)+"&attkey="+obj.attkey+"&attvalue="+obj.attvalue+"&objectId="+objectid+"&contextName="+context,
			  cache: false,
			  success: function(html){
				  if(typeutil.isFunc(callback))callback(html);
			  },
			  error: function(e) {
				  if(typeutil.isFunc(callback))callback(e);
			  }
			});
		}
		function updatePolyline(context, index, objectid, geometry, attribute, callback) {
			var obj = getAttributes(attribute);
			$.ajax({
			  type: "POST",
			  url: "arcgis/edit/updatepolyline/"+index,
			  data: "geometry="+getPolyline(geometry)+"&attkey="+obj.attkey+"&attvalue="+obj.attvalue+"&objectId="+objectid+"&contextName="+context,
			  cache: false,
			  success: function(html){
				  if(typeutil.isFunc(callback))callback(html);
			  },
			  error: function(e) {
				  if(typeutil.isFunc(callback))callback(e);
			  }
			});
		}
		function updatePolygon(context, index, objectid, geometry, attribute, callback) {
			var obj = getAttributes(attribute);
			$.ajax({
			  type: "POST",
			  url: "arcgis/edit/updatepolygon/"+index,
			  data: "geometry="+getPolygon(geometry)+"&attkey="+obj.attkey+"&attvalue="+obj.attvalue+"&objectId="+objectid+"&contextName="+context,
			  cache: false,
			  success: function(html){
				  if(typeutil.isFunc(callback))callback(html);
			  },
			  error: function(e) {
				  if(typeutil.isFunc(callback))callback(e);
			  }
			});
		}
		function delGeometry(context, index, objectid, callback) {
			$.ajax({
			  type: "POST",
			  url: "arcgis/edit/delgeometry/"+index,
			  data: "objectId="+objectid+"&contextName="+context,
			  cache: false,
			  success: function(html){
				  if(typeutil.isFunc(callback))callback(html);
			  },
			  error: function(e) {
				  if(typeutil.isFunc(callback))callback(e);
			  }
			});
		}
		
		// 构造点
		function getPoint(geometry) {
			return [geometry.x, geometry.y];
		}
		// 构造线
		function getPolyline(geometry) {
			var result = [];
			for(var i in geometry.paths) {
				var path = geometry.paths[i];
				for(var j in path) {
					var point = path[j];
					result.push(point[0]+","+point[1]);
				}
			}
			return result;
		}
		// 构造面
		function getPolygon(geometry) {
			var result = [];
			for (var i in geometry.rings) {
				var path = geometry.rings[i];
				for(var j in path) {
					var point = path[j];
					result.push(point[0], point[1]);
				}
			}
			return result;
		}
		// 构造属性
		function getAttributes(attributes) {
			if(!typeutil.isObject(attributes))return {attkey:[], attvalue:[]};
			var attkey=[];
			var attvalue=[];
			for(var key in attributes) {
				attkey.push(key);
				attvalue.push(attributes[key]);
			}
			return {attkey:attkey, attvalue:attvalue};
		}
		return api;
}();