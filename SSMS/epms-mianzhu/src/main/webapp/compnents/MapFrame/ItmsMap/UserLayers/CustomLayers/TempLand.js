MapFactory.Define("ItmsMap/UserLayers/CustomLayers/TempLand*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"MapFactory/LayerManager","MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager","MapFactory/Geometry*",
	"ItmsMap/SymbolConfig*","MapFactory/MapManager"
	],function(api,LayerManager,GraphicManager,InfoWindowManager,GeometryUtil,SymbolConfig,_map){
	var api={
			drawLand:drawLand,
			clearLand:clearLand
	};
	function drawLand(data){
		//绘制区块
		var pologn=data.geo;
		if(pologn){
			var graphicObj={geo: pologn, symbol: SymbolConfig["landSymbol"], attributes: data};
			var graphic=GraphicManager(graphicObj);
			graphic.addToLayer("tempLandLyrPologn");
			var centerpoint=graphic.getCenter();
			var points=centerpoint.points.split(",");
			var x=parseFloat(points[0]);
			var y=parseFloat(points[1]);
			_map().centerAndZoom(x,y,5);
		}
	}
	function clearLand(){
		LayerManager("tempLandLyrPologn").clear();
	}
	return api;
});
