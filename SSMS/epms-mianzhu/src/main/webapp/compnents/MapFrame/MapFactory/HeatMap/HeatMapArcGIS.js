MapFactory.Define("MapFactory/HeatMap",["esri/graphic*","MapFactory/GeometryUtil"],
function(Graphic,GeometryUtil){
	MapFactory.CreateScriptNode("compnents/MapFrame/MapFactory/ThirdParty/HeatMap/heatmap1.0.js",{},null);
	MapFactory.CreateScriptNode("compnents/MapFrame/MapFactory/ThirdParty/HeatMap/arcgis/heatmap-arcgis.js",{},null);
	return function(config){
	    var map = MapFactory._MapManagerResource[MapFactory.Engine];

		var  heatmaplayer = new HeatmapLayer({
               config: {
                  "useLocalMaximum": true,
                  "radius": 90,
                  "gradient": {
                      0.45: "rgb(000,000,255)",
                      0.55: "rgb(000,255,255)",
                      0.65: "rgb(000,255,000)",
                      0.95: "rgb(255,255,000)",
                      1.00: "rgb(255,000,000)"
                  }
              }, 
              "map": map,
              "domNodeId": "heatLayer",
              "opacity": 0.85
          }); 
		 heatmaplayer.id="heatmaplayer";
		 /**
			 * 添加数据集
			 * graphic 数组
			 */
		function addDataSet(datas){
			if(!datas || !datas.graphics|| datas.graphics.length>0){
				return;
			}
	
			var _graphics = datas.graphics;	
            var features=[]
			for(var i=0,len= _graphics.length;i<len;i++){
				var _graphic = _graphics[i];
				var _attr = _graphic.attributes;
				var _geo = _graphic.geo;
				var geometry=GeometryUtil().convertFromMapFactory(_geo);			
				features.push(new Graphic(geometry,null,_attr));
			}
			
			heatmaplayer.setData(features);
		 }
	    function addheatlayer(){	    	
	    	var heatmap_layer = map.getLayer(heatmaplayer.id);
	    	  if(heatmap_layer){
	    		 map.removeLayer(heatmap_layer);
	    	  }
	    	  map.addLayer(heatmaplayer); 	    	 
	    }
		function deleteheatlayer(){			
              map.removeLayer(heatmaplayer);
		 }					
		 var api={
				 addDataSet:addDataSet,
				 addheatlayer:addheatlayer,
				 deleteheatlayer:deleteheatlayer
		 }
		return api;
		
		
		
			
	}
});