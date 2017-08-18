MapFactory.Define("MapFactory/HeatMap",function(){
	MapFactory.CreateScriptNode("compnents/MapFrame/MapFactory/ThirdParty/HeatMap/heatmap1.0.js",{},null);
	MapFactory.CreateScriptNode("compnents/MapFrame/MapFactory/ThirdParty/HeatMap/openlayers/heatmap-openlayers.js",{},null);
	return function(confg){			  	    
     var map = MapFactory._MapManagerResource[MapFactory.Engine];
     var heattemplayer = new OpenLayers.Layer.Vector();	
         heattemplayer.id="heattemplayer";
     var heatmaplayer = new OpenLayers.Layer.Heatmap(confg.layername,map,heattemplayer,confg.options,confg.layerparams);
         heatmaplayer.id="heatmaplayer";
         /**
 		 * 添加数据集
 		 * @param datas Object 数据集
 		 *  + max int 最大数
 		 *  + graphics Graphics[]
 		 */
		function addDataSet(datas){						
			if(!datas || !datas.graphics || !datas.graphics.length){
				return;
			}
			var _dataSet = {};
			var _graphics = datas.graphics;
			var _data = [];
			_dataSet.max = datas.max;
			for(var i=0,len= _graphics.length;i<len;i++){
				var _graphic = _graphics[i];
				var _attr = _graphic.attributes;
				var _geo = _graphic.geo;
				var _coor = _geo.points.split(",");
				_data.push({
		            lonlat: new OpenLayers.LonLat(parseFloat(_coor[0]), parseFloat(_coor[1])),
		            count: _attr.count
		        });
			}
			_dataSet.data = _data;			
			heatmaplayer.setDataSet(_dataSet);
		 }
	    function addheatlayer(){
	    	var heattemp_layer = map.getLayer(heattemplayer.id);	    	
	    	var heatmap_layer = map.getLayer(heatmaplayer.id);
	    	  if(heattemp_layer && heatmap_layer){
	    	    deleteheatlayer();
	    	  }
	    	  map.addLayers([heattemplayer, heatmaplayer]); 
	    	  map.zoomToMaxExtent();
	    }
		function deleteheatlayer(){			
			  map.removeLayer(heattemplayer);
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