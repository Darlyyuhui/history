/**
 * 地图要素捕捉类
 * @author ZLT
 * @date 2013-10-12
 * 用例：
 * <input type="checkbox" id="snappingPower"/>捕捉
			
	var mst = mapSnappingTool().init({
		map:myMap,
		func:getPoint,
		layers:layerArr
	});
	
	$("#snappingPower").click(function(){
		if($(this).attr("checked")){
			mst.active();
		}else{
			mst.deactive();
		}
	});
	
	function getPoint(mapPoint){
		console.log(mapPoint);
	}
 * 
 * 
 **/
itmap.arcgis.mapSnappingTool = (function(){
	return function(){

		/**
		 * 返回api
		 */
		var api = {
			init : init, // 初始化工具
			active : active, // 激活工具
			deactive : deactive // 关闭工具
		};

		/**
		 * 需要查找的图层数组
		 */
		var _layersArr = [];

		/**
		 * 地图容器
		 */
		var _mapC = null;

		/**
		 * 地图鼠标移动事件句柄
		 */
		var _mapMouseMoveHandler = null;

		/**
		 * 结果处理回调函数
		 */
		var _resultCallFunc = null;

		/**
		 * 捕捉点图层
		 */
		var snappingPointGraphicLayer = itmap.arcgis.mapGraphicManager("snappingPointGraphicLayer");

		/**
		 * 初始化
		 * @param array layerArray 初始化时传入图层数组
		 */
		function init(mapContainer,func,layerArray){
			_mapC = mapContainer;
			if(_isArray(layerArray)){
				_layerAnalysis(layerArray)
			}
			_setResultFunc(func);
			return api;
		}

		/**
		 * 激活工具
		 */
		function active(){

			// 设置snapping参数
			var snappingParams = {
				map : _mapC,
				alwaysSnap : true,
				tolerance : 20,
				snapPointSymbol : mapCommonSymbol.defaultPointSymbol
			}

			// 如果有指定图层，则设置指定图层，如果没有，则设置为默认的图层
			if(_layersArr.length){
				snappingParams.layerInfos = _layersArr;
			}else{
				snappingParams.layerInfos = [{layer:new esri.layers.FeatureLayer(baseServiceURL.roadline.url,{
					mode : esri.layers.FeatureLayer.MODE_ONDEMAND
				})}];
			}

			// 启动map对象中的snapping功能
			_mapC.enableSnapping(snappingParams);

			// 添加鼠标移动事件，用来监听捕捉事件
			_mapMouseMoveHandler = dojo.connect(_mapC,"onMouseMove",function(e){
				var deferred = _mapC.snappingManager.getSnappingPoint(e.screenPoint);
				snappingPointGraphicLayer.clear();
				deferred.then(function(value){
					if(value !== undefined){
						// 向地图里边添加捕捉到的点
						snappingPointGraphicLayer.add(new esri.Graphic(value,mapCommonSymbol.defaultPointSymbol));
						// 如果有回调函数，则调用回调函数
						if(_resultCallFunc){
							_resultCallFunc(value);
						}
					}
				},function(error){
					//console.log('failure');
				});
			});
		}

		/**
		 * 关闭工具
		 */
		function deactive(){
			_mapC.disableSnapping();
			dojo.disconnect(_mapMouseMoveHandler);
		}

		/**
		 * 图层分析
		 */
		function _layerAnalysis(layerIDs){
			var i = 0;
			var layer = null;
			for(;i<layerIDs.length;i++){
				layer = _mapC.getLayer(layerIDs[i]);
				if(layer){
					_layersArr.push({layer:layer});
				}
			}
		}

		/**
		 * 设置结果回调函数
		 */
		function _setResultFunc(func){
			if(typeof func == "function"){
				_resultCallFunc = func;
			}
			return api;
		}

		/**
		 * 判断是否为数组
		 */
		function _isArray(obj){
			return Object.prototype.toString.call(obj).match(/Array/ig) ? true : false;
		}

		return api;
	}
})();