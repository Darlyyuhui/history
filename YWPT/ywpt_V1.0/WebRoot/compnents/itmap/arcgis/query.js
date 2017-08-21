/**
 * 地图查询封装类
 */
itmap.arcgis.query = function(){
	var api = {
			queryByWherestr : queryByWherestr,
			queryByGeometry : queryByGeometry,
			relationIdConfig : relationIdConfig,
			queryByRelation : queryByRelation,
			defaultResult : defaultResult,
			defaultRelationResult : defaultRelationResult
		};
	
	var symbol = itmap.arcgis.symbol;
	var util = itmap.arcgis.util;
	
	// 关系查询id配置
	var relationIdConfig = {
		"Devices" : 12,
		"SignalLamp" : 11,
		"TrafficGuidingPanel" : 13,
		"Park" : 14,
		"FlowPanel" : 16,
		"detector" : 17
	};
	
	// 通过where条件进行查询
	function queryByWherestr(wherestr, showQueryResult, errorResult, layerUrl) {
		if(!layerUrl)return;
		if(!wherestr)wherestr = "1=1";
		if(!showQueryResult)showQueryResult = defaultResult;
		var queryTask = new esri.tasks.QueryTask(layerUrl);
		var query = new esri.tasks.Query();
		query.where = wherestr;
		query.outFields = ["*"];
		query.returnGeometry = true;
		
		queryTask.execute(query, showQueryResult, function(e){
			debugger;
			if(errorResult)errorResult(e);
		});
	}
	// 通过几何要素条件进行查询
	function queryByGeometry(geometry, showQueryResult, errorResult, layerUrl) {
		if(!layerUrl || !geometry)return;
		if(!showQueryResult)showQueryResult = defaultResult;
		var queryTask = new esri.tasks.QueryTask(layerUrl);
		var query = new esri.tasks.Query();
		query.geometry = geometry;
		query.outFields = ["*"];
		query.returnGeometry = true;
		
		queryTask.execute(query, showQueryResult, function(e){
			debugger;
			if(errorResult)errorResult(e);
		});
	}
	/**
	 * 参数
	 * objIds为layerUrl上对应的objectId
	 * relationId为查询的目标图层的关联id
	 */
	// 通过关联关系条件进行查询
	function queryByRelation(objIds, relationId, showQueryResult, errorResult, layerUrl) {
		if(!layerUrl || !objIds)return;
		if(!showQueryResult)showQueryResult = defaultRelationResult;
		var queryTask = new esri.tasks.QueryTask(layerUrl);
		var query = new esri.tasks.RelationshipQuery();
		query.relationshipId = relationId;
		query.objectIds = objIds;
		query.outFields = ["*"];
		query.returnGeometry = true;
		
		queryTask.executeRelationshipQuery(query, showQueryResult, function(e){
			debugger;
			if(errorResult)errorResult(e);
		});
	}
	function defaultResult(queryResult) {
		if(queryResult.features.length == 0) {
			return;
		}
		for(var i=0; i<queryResult.features.length; i++) {
			var graphic = queryResult.features[i];
			if(graphic.geometry.type == "point") {
				graphic.setSymbol(symbol.defaultPointSymbol());
			}
			else if(graphic.geometry.type == "polyline") {
				graphic.setSymbol(symbol.defaultLineSymbol());
			}
			else if(graphic.geometry.type == "polygon") {
				graphic.setSymbol(symbol.defaultPolygonSymbol());
			}
			map.graphics.add(graphic);
		}
	}
	function defaultRelationResult(resultArray) {
		for(var j in resultArray) {
			var queryResult = resultArray[j];
			defaultResult(queryResult);
		}
	}
	
	return api;
}();