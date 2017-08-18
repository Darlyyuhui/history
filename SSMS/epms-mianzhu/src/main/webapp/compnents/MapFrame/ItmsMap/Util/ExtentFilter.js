MapFactory.Define("ItmsMap/Util/ExtentFilter*",["MapFactory/LayerManager",
	"MapFactory/MapManager","MapFactory/GraphicManager","ItmsMap/SymbolConfig*"],
		function(LayerManager,MapManager,GraphicManager,SymbolConfig){
	/**
	 * 
	 */
	var PolluteLevel;
	return function(layerId){
		
		var api = {
				removeOutGraphic:removeOutGraphic,
				isInExtent:isInExtent,
				queryPolluteLevel:queryPolluteLevel
		};
		
		var _layerid = "",
		_mapHandle=MapManager();
		_layerid=layerId;
		
		function queryPolluteLevel(pointList,lastExtent){
			if(PolluteLevel){
				drawNewExtentData(pointList,lastExtent);
			}else{
				MapFactory.XHR.Post(
						path+ "/map/getStandardMonitor/002/",
						function(list) {
							producePolluteLavel(list);
							drawNewExtentData(pointList,lastExtent);
						});
			}
		}
		//移除范围外的图形
		function removeOutGraphic(){
			var features=LayerManager(_layerid).getFeatures();
			var currentExtent=_mapHandle.getCurrentExtent();
			var removeG=[];
			var feature;
			for(var i=0;i<features.length;i++){
				feature=features[i];
				if(!isInExtent(feature.geo,currentExtent)){
					removeG.push(feature);
				}
			}
			for(var j=0;j<removeG.length;j++){
				feature=removeG[j];
				GraphicManager(feature.id).remove();
			}
		}
		//是否在某个范围内
		function isInExtent(point,extent){
			if(!point){
				return false;
			}
			var pointStr=point.points;
			if(!pointStr){
				return false;
			}
			var pointArr=pointStr.split(",");
			if(!(pointArr[0] && pointArr[1])){
				return false;
			}
			var _p = new SuperMap.LonLat(parseFloat(pointArr[0]),parseFloat(pointArr[1]));
			return extentConvertTo(extent).containsLonLat(_p);
		}
		//自定义范围对象转换为原生范围对象
		function extentConvertTo(extent){
			var bounds = new SuperMap.Bounds();
			bounds.extend(new SuperMap.LonLat(extent.minX,extent.minY));
			bounds.extend(new SuperMap.LonLat(extent.maxX,extent.maxY));
			bounds.toBBOX();
			return bounds;
		}
		//加载新出来的范围的数据  除去重复部分  //各种采样点类型对应的数据集合
		function drawNewExtentData(pointList,lastExtent){
			var currentExtent=_mapHandle.getCurrentExtent();
			for(var j=0;j<pointList.length;j++){
				var cydPoints=pointList[j];
				var symbolType=cydPoints.dataType;
				var dataList=cydPoints.dataList;
				var pointTypeName=cydPoints.pointTypeName;
				var pointTypeTag=cydPoints.pointTypeTag;
				for(var i=0;i<dataList.length;i++){
					var cyd=dataList[i];
					var symbol=MapFactory.Clone(SymbolConfig["cydSymbol"]);
					symbol.url=symbol.url+symbolType+getPolluteLavel(cyd.cadmium)+".png";
					cyd.pointTypeName=pointTypeName;
					cyd.pointTypeTag=pointTypeTag;
					if(cyd.longitude){
						var point={type: "point", points: cyd.longitude + "," + cyd.latitude};
						if(isInExtent(point,currentExtent) && (!lastExtent || !isInExtent(point,lastExtent))){
							var graphicPoint={geo: point, symbol:symbol , attributes: cyd};
							GraphicManager(graphicPoint).addToLayer(_layerid);
						}
					}
				}
			}
		}
		function producePolluteLavel(list){
			PolluteLevel=new Object();
			for(var i=0;i<list.length;i++){
				var obj=list[i];
				var min=obj.minVal;
				if(!min){
					min="∞";
				}
				var max=obj.maxVal;
				if(!max){
					max="∞";
				}
				var key=min+"-"+max;
				PolluteLevel[key]=i+1;
			}
		}
		function getPolluteLavel(cadmium){
			if(!cadmium){
				return "1";
			}
			for(var key in PolluteLevel){
				var valueArr=key.split("-");
				if(valueArr[0]=="∞"){
					if(cadmium<=parseFloat(valueArr[1])){
						return PolluteLevel[key];
					}
				}else if(valueArr[1]=="∞"){
					if(cadmium>parseFloat(valueArr[0])){
						return PolluteLevel[key];
					}
				}else{
					if(cadmium>parseFloat(valueArr[0]) && cadmium<=parseFloat(valueArr[1])){
						return PolluteLevel[key];
					}
				}
			}
		}
		return api;
	}

});
