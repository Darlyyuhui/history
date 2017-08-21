/**
 * Grpahic管理类
 * @author ZLT
 * @date 2013-10-10
 * @param graphicLayerID String graphicLayer图层id
 **/
itmap.arcgis.mapGraphicManager = (function(){
	return function(graphicLayerID){

		/**
		 * 工具api
		 */
		var api = {
			clear : clear, // 清除graphicLayer上的元素
			add : add, // 向指定graphicLayer添加graphic
			getLayer : getLayer, // 获取当前graphicLayer
			remove : remove, // 移除指定的graphic
			listGraphicLayer : listGraphicLayer, // 获取graphicLayer列表
			clearAll : clearAll, // 清除地图上所有的graphic
			reorderLayer : reorderLayer, // 对指定的两个图层交换他们的位置
			reorderLayerAfter : reorderLayerAfter, // 将指定图层排到另外一个指定图层下边
			addRightClickToGraphic : addRightClickToGraphic // 向地图上的指定的graphic图层添加右键单击事件
		}

		/**
		 * 地图资源
		 */
		var map = itmap.arcgis.map;

		/**
		 * graphicLayer对象
		 */
		var _targetGraphicLayer = null;
		
		/**
		 * 地图graphic点击事件句柄
		 */
		var _mapGraphicsClickEvt = null;
		
		/**
		 * 图层id
		 */
		var _layerId = "";

		/**
		 * 初始化
		 */
		function _init(){
			if(typeof graphicLayerID == "string" && graphicLayerID != ""){
				_initGraphicLayer(graphicLayerID);
			}
		}
		_init();

		/**
		 * 设置图层
		 * @param id String 图层id
		 * @return layer Layer 返回相应的graphiclayer
		 */
		function _initGraphicLayer(id){
			_targetGraphicLayer = map.getLayer(id);
			if(!_targetGraphicLayer){
				_targetGraphicLayer = new esri.layers.GraphicsLayer();
				_targetGraphicLayer.id = id;
				_layerId = id;
				map.addLayer(_targetGraphicLayer);
			}
			_targetGraphicLayer.show();
		}

		/**
		 * 获取graphiclayer图层
		 */
		function getLayer(){
			return _targetGraphicLayer;
		}

		/**
		 * 添加graphic
		 */
		function add(graphic){
			if(!_targetGraphicLayer){
				_initGraphicLayer(_layerId);
			}
			_targetGraphicLayer.add(graphic);
		}

		/**
		 * 清除对应的graphic
		 */
		function clear(){
			if(_targetGraphicLayer){
				_targetGraphicLayer.clear();
			}
		}

		/**
		 * 清除地图上不同图层所有的graphic
		 * @param o 传入参数对象
		 * + excludeLayer Array[String] 传入不清除的图层ID
		 */
		function clearAll(o){
			var graphicLayerList = listGraphicLayer(),
				otherLayers = listDynamicAndTilesLayer(),
				allList = graphicLayerList.concat(otherLayers),
				length = allList.length,
				i;
			for(i=0;i<length;i++){
				if(allList[i].layer){
					if(o.excludeLayer && o.excludeLayer.length && !_inArray(allList[i].name,o.excludeLayer)){
						map.removeLayer(allList[i].layer);
					}
				}
			}
			map.graphics.clear();
			_targetGraphicLayer = null;
		}

		/**
		 * 移除graphic
		 */
		function remove(graphic){
			if(_targetGraphicLayer){
				_targetGraphicLayer.remove(graphic);
			}
		}

		/**
		 * 获取现有的graphiclayer列表
		 */
		function listGraphicLayer(){
			var graphicLayerList = [];
			var list = map.graphicsLayerIds;
			var listSize = list.length;
			var sysGraphiLayerReg = /graphicsLayer\d*/;
			var i = 0;
			for(;i<listSize;i++){
				if(!list[i].match(sysGraphiLayerReg)){ // 不将系统自己添加的graphiclayer计算在内，用正则将其过滤掉
					graphicLayerList.push({"name":list[i],"layer":map.getLayer(list[i])});
				}
			}
			graphicLayerList.push({"name":"HotMapLayer","layer":map.getLayer("HotMapLayer")});
			graphicLayerList.push({"name":"dxdDynamicLayer","layer":map.getLayer("dxdDynamicLayer")});
			graphicLayerList.push({"name":"gzDynamicLayer","layer":map.getLayer("gzDynamicLayer")});
			return graphicLayerList;
		}

		/**
		 * 获取现有的动态和切片图层
		 */
		function listDynamicAndTilesLayer(){
			var layerList = [],
				list = map.layerIds,
				i,len;
			for(i=0,len=map.layerIds.length;i<len;i++){
				layerList.push({"name":list[i],"layer":map.getLayer(list[i])});
			}
			return layerList;
		}

		/**
		 * 对graphicLayer进行重新排序
		 */
		function reorderLayer(firstLayer,secondLayer){
			if(typeof firstLayer == "string" && typeof secondLayer == "string"){
				if(map.getLayer(firstLayer) && map.getLayer(secondLayer)){
					var fir = dojo.byId(firstLayer+"_layer");
					var sec = dojo.byId(secondLayer+"_layer");
					var temp1 = "<div id='mapTempLayer1'></div>";
					var temp2 = "<div id='mapTempLayer2'></div>";
					dojo.place(temp1,fir,"replace");
					dojo.place(temp2,sec,"replace");
					dojo.place(fir,dojo.byId("mapTempLayer2"),"replace");
					dojo.place(sec,dojo.byId("mapTempLayer1"),"replace");
				}
			}
		}
		
		/**
		 * 将第一个Layer放置到第二个Layer以下
		 */
		function reorderLayerAfter(firstLayer,secondLayer){
			if(typeof firstLayer == "string" && typeof secondLayer == "string"){
				if(map.getLayer(firstLayer) && map.getLayer(secondLayer)){
					var fir = dojo.byId(firstLayer+"_layer");
					var sec = dojo.byId(secondLayer+"_layer");
					dojo.place(fir,sec,"after");
				}
			}
		}
		
		/**
		 * 为graphic增加右键单击事件
		 * @param menuObj[] 传入菜单对象
		 * + label 对应标题
		 * + func 标题对应要调用的函数
		 */
		function addRightClickToGraphic(menuObj){
			if(itmap.util.variableTypes.isArray(menuObj)){
				//delete _targetGraphicLayer.onMouseDown;
				_mapGraphicsClickEvt = dojo.connect(_targetGraphicLayer,"onMouseDown",function(event){
					document.documentElement.oncontextmenu = function(){return false;}
					if(event.button == 2){
						var mapRightClick = itmap.util.mapRightClickWidget();
						for(var i = 0, len = menuObj.length;i < len ;i++){
							mapRightClick.add(menuObj[i].label,function(data){
								if(menuObj[data.index].func){
									menuObj[data.index].func(event.graphic);
								}
							});
						}
						mapRightClick.show(event);
					}
					return false;
				});
			}
		}
		
		/**
		 * 是否在数组中
		 * @param item 传入要检查元素
		 * @param arr 传入要检查数组
		 */
		function _inArray(item,arr){
			return eval("\/\\b"+item+"\\b\/g").test(arr.toString());
		}

		return api;
	}
})();