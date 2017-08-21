/**
 * @author ZLT
 * @Date 2013-7-24
 * 需要加载类：
 * 	esri.toolbars.draw
 * 	esri.geometry.Extent
 * 	esri.tasks.identify
 */
var IdentifyTool = (function() {
	return function(conf) {

		// 设置工具可调用的api
		var api = {
			url : setUrl, // 设置mapserver链接
			init : init, // 初始化查询控件
			setMap : setMap, // 设置地图资源
			setFieldRelation : setFieldRelation, // 设置GIS字段与前台需要显示字段的映射
			beginExtent : beginExtent, // 开始框选
			beginClick : beginClick, // 点击选择
			stopTool : stopTool, // 停止框选
			getExtentGeometry : getExtentGeometry, // 获取框选后的Geometry
			identify : identify, // 查询
			release : releaseIdentify, // 释放查询资源
			call : call, // 回调函数
			getIdentifyTextRes : _getIdentifyRes, // 获取结果
			clear : clear, // 清除查询结果
			getGM : getGM // 获取graphic管理器
		}

		// 地图资源，工具条，server服务链接
		var map = null, toolbar = null, identifyTaskUrl;

		// identify资源，参数
		var identify, identifyParams;

		// 点线面符号
		var pointSymbol = null, lineSymbol = null, polySymbol = null;

		// 查询完成后的结果，如属性等，不包含图像属性
		var identifyTextResult;

		// 框选的geometry
		var extentGeometry = null;
		
		// 回调函数
		var callFunc = "";
		
		// graphic管理器
		var mgm = itmap.arcgis.mapGraphicManager("queryResultGraphicLayer");

		// 前台显示字段映射
		var relationField = {
			name : '',
			classify : ''
		};
		
		// 查询结果所在的graphiclayer
		var queryGraphicLayer = null;

		// 初始化工具
		function init() {
			identifyTextResult = '';
			return api;
		}

		// 设置地图
		function setMap(basemap) {
			map = basemap;
			return api;
		}

		// 设置mapserver的url
		function setUrl(url) {
			identifyTaskUrl = url;
			return api;
		}

		// 设置不同字段对标准字段的映射
		function setFieldRelation(rel) {
			if(rel !== '' && typeof rel != 'undefined') {
				for(elem in rel) {
					relationField[elem] = rel[elem];
				}
			}
			return api;
		}

		// 查询
		function identify(e) {
			map.graphics.clear();
			return _Query(e);
		}

		// 释放
		function releaseIdentify() {
			map.graphics.clear();
			identifyTextResult = '';
		}

		// 开始框选
		function beginExtent() {
			if(toolbar == null) {
				_initToolBar();
			}
			toolbar.activate(esri.toolbars.Draw.EXTENT);
			return toolbar;
		}
		
		// 停止工具
		function stopTool(){
			if(toolbar!=null){
				toolbar.deactivate();
			}
		}
		
		// 获取框选几何
		function getExtentGeometry(){
			return extentGeometry;
		}
		
		// 开始框选
		function beginClick() {
			if(toolbar == null) {
				_initToolBar();
			}
			toolbar.activate(esri.toolbars.Draw.POINT);
			return toolbar;
		}
		
		// 回调函数
		function call(fun){
			callFunc = fun;
			return api;
		}

		// 初始化工具
		function _initToolBar() {
			toolbar = new esri.toolbars.Draw(map);
			dojo.connect(toolbar, "onDrawEnd", function(geometry) {
				toolbar.deactivate();
				extentGeometry = geometry;
			});
		}

		// 获取对象属性
		function _getRelationField(obj, attr) {

			var fieldStr = '-';
			if(attr == 'name') {
				var nameArr = relationField.name.split(',');
				for(var i = 0; i < nameArr.length; i++) {
					if( typeof obj[nameArr[i]] != 'undefined' && obj[nameArr[i]] != '') {
						fieldStr = obj[nameArr[i]];
					}
				}
			}
			if(attr == 'classify') {
				var classifyArr = relationField.classify.split(',');
				for(var i = 0; i < classifyArr.length; i++) {
					if( typeof obj[classifyArr[i]] != 'undefined' && obj[classifyArr[i]] != '') {
						fieldStr = obj[classifyArr[i]];
					}
				}
			}
			return fieldStr;
		}

		// 初始化符号设置
		function _initSymbol() {
			// 点符号
			pointSymbol = new esri.symbol.SimpleMarkerSymbol();
			pointSymbol.setStyle(esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE).setColor(new dojo.Color([255, 0, 0]));

			lineSymbol = new esri.symbol.SimpleLineSymbol();
			lineSymbol.setStyle(esri.symbol.SimpleLineSymbol.STYLE_SOLID).setColor(new dojo.Color(255, 0, 0));

			polySymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT, new dojo.Color([255, 0, 0]), 1), new dojo.Color([255, 255, 0, 0.25]));

		}

		// 显示符号
		function _displaySymbol(geometry) {
			switch(geometry.type) {
				case "point" : {
					mgm.add(new esri.Graphic(geometry, pointSymbol));
					break;
				}
				case "polyline" : {
					mgm.add(new esri.Graphic(geometry, lineSymbol));
					break;
				}
				case "polygon" : {
					mgm.add(new esri.Graphic(geometry, polySymbol));
					break;
				}
			}
		}

		// 查询
		function _Query(e) {

			identifyParams = new esri.tasks.IdentifyParameters();
			if(typeof e.mapPoint == 'undefined'){
				identifyParams.geometry = e;
			}else{
				identifyParams.geometry = e.mapPoint;
			}

			identifyParams.mapExtent = map.extent;
			identifyParams.returnGeometry = true;
			identifyParams.layerIds = [2,3,4,5,6,7,8,9,13];
			identifyParams.layerOption = esri.tasks.IdentifyParameters.LAYER_OPTION_ALL;
			identifyParams.tolerance = 1;
			identifyParams.spatialReference = map.spatialReference;

			identify = new esri.tasks.IdentifyTask(identifyTaskUrl);
			identify.execute(identifyParams);
			if(callFunc != ""){
				iOnCompleteHandler = dojo.connect(identify,"onComplete",callFunc);
			}
			return identify;
		}

		// 获取IdentifyRes
		function _getIdentifyRes(identifyResults) {

			_initSymbol();

			for(var i = 0; i < identifyResults.length; i++) {
				_displaySymbol(identifyResults[i].feature.geometry);
			}
			
			var _resArr = [];
			var _attr = null;
			var _name = "";
			var _point = null;

			for(var i = 0; i < identifyResults.length; i++) {
				if(identifyResults[i].feature.geometry.type=="point"){
					_point = identifyResults[i].feature.geometry;
				}else if(identifyResults[i].feature.geometry.type=="polyline" || identifyResults[i].feature.geometry.type=="polygon"){
					_point = identifyResults[i].feature.geometry.getPoint(0,0);
				}
				_attr = {
						layerId:identifyResults[i].layerId,
						layerName:identifyResults[i].layerName,
						shape:identifyResults[i].feature.attributes.Shape
				}
				_name = identifyResults[i].feature.attributes.MC;
				if(typeof _name=="undefined" || _name==""){
					_name = identifyResults[i].feature.attributes.NAME;
				}
				if(typeof _name!="undefined" && _name!="" && _name!="undefined"){
					_resArr.push(new resItemO(_name,"",_attr,{point:_point}));
				}else{
					_resArr.push(new resItemO("无名称","",_attr,{point:_point}));
				}
			}

			return _resArr;
			//setTimeout(function(){map.graphics.clear()},1000); // 点击之后0.6s符号消失

		}

		// 面状查询
		function _polygonQuery(e) {
			toolbar.activate(esri.toolbars.Draw.EXTENT);
		}

		// 清除
		function clear(){
			mgm.clear();
		}

		// 获取grahic管理器
		function getGM(){
			return mgm;
		}

		return api;
	}
})()
