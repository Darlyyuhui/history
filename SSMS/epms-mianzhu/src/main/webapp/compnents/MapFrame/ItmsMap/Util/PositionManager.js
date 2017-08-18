MapFactory.Define("ItmsMap/Util/PositionManager*",[
	"MapFactory/LayerManager",
	"MapFactory/Geometry*",
	"MapFactory/GraphicManager",
	"ItmsMap/SymbolConfig*"
], function(layerFun, geoFun, graphicFun, symbolConfig){
	var _gid=0, graphics=[], preIndex, hightLightPoint;
	return function() {
		var api = {
			addPositionPoint : addPositionPoint,// 添加标注点到地图上
			addHighLightPosition : addHighLightPosition,// 添加标注点到地图上高亮图标
			highLightPosition : highLightPosition,// 添加标注点到地图上
			clear : clear// 清除posistion图层
		};
		
		var positionLayer,positionHighLightLayer;
		function _init() {
			positionLayer = layerFun("positionLayer");
			positionHighLightLayer = layerFun("positionHighLightLayer");
			positionLayer.clear();
			positionHighLightLayer.clear();
			graphics = [];
			preIndex = 0;
			hightLightPoint = null;
		}
		_init();
		
		function addPositionPoint(mapx, mapy, att) {
			if(!mapx && !mapy)return "";
			if(!att)att={};
			att.gid = ++_gid;
			if(typeof mapx == "object") {
				var graphic = graphicFun({geo: mapx, symbol: symbolConfig.positionStyle, attributes: att});
				graphic.addToLayer("positionLayer");
				graphics[_gid] = graphic;
				return _gid;
			}
			var point = new geoFun({type : "point", points : mapx+","+mapy});
			var graphic = graphicFun({geo: point, symbol: symbolConfig.positionStyle, attributes: att});
			graphic.addToLayer("positionLayer");
			graphics[_gid] = graphic;
			return _gid;
		}
		
		function highLightPosition(id) {
			if(!id)return;
			var currentPosition = graphics[id];
			layerFun("positionHighLightLayer").clear();
			if(currentPosition) {
				hightLightPoint = graphicFun({geo: currentPosition.getGraphic().geo, symbol: symbolConfig.positionHighLightStyle});
				hightLightPoint.addToLayer("positionHighLightLayer");
			}
		}
		
		function addHighLightPosition(mapx, mapy, isAutoClear) {
			if(!mapx && !mapy)return;
			if(typeof mapx == "object") {
				if(typeof mapy == 'undefined' || mapy == true)layerFun("positionHighLightLayer").clear();
				graphicFun({geo: mapx, symbol: symbolConfig.positionHighLightStyle}).addToLayer("positionHighLightLayer");
				return;
			}
			if(typeof isAutoClear == 'undefined' || isAutoClear == true)layerFun("positionHighLightLayer").clear();
			var point = new geoFun({type : "point", points : mapx+","+mapy});
			graphicFun({geo: point, symbol: symbolConfig.positionHighLightStyle}).addToLayer("positionHighLightLayer");
		}
		
		function clear() {
			layerFun("positionLayer").clear();
			layerFun("positionHighLightLayer").clear();
			preIndex = 0;
		}
		
		return api;
	}
});