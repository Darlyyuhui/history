var mapLegendTool = (function(){
	return function(){
		function showlegendTool(){
			var _legendDialog = new itmap.util.dialog();
			legendDialog.conf({
				title:'图例',
				content:'<div id="legendDIV"  style="width: 240px !important;"></div>',
				mask:false,
				zindex:100,
				closeCall : legendDestroy ,
				buttonDisplay : {
					confirmButton : false,
					cacelButton : false
				}
			});
			function legendDestroy(){
				legendDijit.destroy();
				_legendDialog=null;
			}
			_legendDialog.show();
			initlegend();
		}
				
		function initlegend(){	
			//动态图例添加 ,注意：目前动态图例支持 ArcGISDynamicMapServiceLayer, ArcGISTiledMapServiceLayer, FeatureLayer 和KMLLayer，但是如果图层服务为
			//ArcGISDynamicMapServiceLayer or ArcGISTiledMapServiceLayer 的话必须是arcgis 10.0SP1及以上的版本
			var waterbodies = new esri.layers.FeatureLayer("http://193.169.100.111:8399/arcgis/rest/services/xianMapDMS_NEW/FeatureServer/0") ;		 
			myMap.addLayers([waterbodies]);
			//add the legend
			dojo.connect(itmap.arcgis.map, 'onLayersAddResult', function (results) {
				var layerInfo = dojo.map(results, function (layer, index) {
					return {layer:layer.layer, title:layer.layer.name};
				});
				if (layerInfo.length > 0) {
					var legendDijit = new esri.dijit.Legend({
						map:itmap.arcgis.map,
						arrangment: esri.dijit.Legend.ALIGN_RIGHT, 
						autoUpdate:true ,	 
						layerInfos:layerInfo
					}, "legendDIV");
					legendDijit.startup();
				}
			});
		}
		return {showlegend:showlegendTool};
	}
})();
