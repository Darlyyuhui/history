/**
 * 关键字搜索类
 * @author ZLT
 * @Date 2013-8-19
 * 需要加载的类库：
 * "esri.tasks.find"
 **/
var mapFindTool = function(){
	this.mgm = itmap.arcgis.mapGraphicManager("keywordsFindResultGraphicLayer");
	this._map = itmap.arcgis.map;
}

/**
 * 设置搜索参数
 */
mapFindTool.prototype.setParameter = function(keywords){
	var findParam = new esri.tasks.FindParameters();
	findParam.layerIds = [2,3,4,5,6,7,8,9,13];
	findParam.returnGeometry = true;
	findParam.searchText = keywords;
	return findParam;
}

/**
 * 执行搜索
 */
mapFindTool.prototype.search = function(keywords){
	var instance = this;
	var findR = new esri.tasks.FindTask(baseServiceURL.basemapnew.url);
	var thisMap = this._map;
	findR.execute(this.setParameter(keywords),searchSuccess,searchFailed);
	
	/**
	 * 搜索成功回调
	 */
	function searchSuccess(result){
		var _relation = {
			layerid : "图层ID",
			layername : "图层名"
		}
		var _resArr = [];
		for(var i = 0;i < result.length;i++){
			_resArr.push(new resItemO(result[i].value,(function(_index){
				return function(){
					var _p = null;
					if(result[_index].feature.geometry.type == "point"){
						_p = result[_index].feature.geometry;
					}else{
						_p = result[_index].feature.geometry.getPoint(0,0);
					}
					thisMap.centerAt(_p);
					
					itmap.arcgis.mapGraphicManager("mapPositionSign").clear();
					itmap.arcgis.mapGraphicManager("mapPositionSign").add(new esri.Graphic(_p,itmap.arcgis.symbol.positionSymbolOffset()));
				}
			})(i),{layerid:result[i].layerId,layername:result[i].layerName}));
			instance.displayRes(result[i].feature.geometry);
		}
		//return _resArr;
		itmap.util.mapResultboxNew().init("mapResultC").addContent({content:_resArr,switchtab:true,relation:_relation});
		
		var data = [];
		var ids = [];
		for(var _index = 0,len=result.length;_index<len;_index++){
			var pos = inArray(result[_index].layerId,ids);
			if(pos==-1){
				ids.push(result[_index].layerId);
				data.push({name:result[_index].layerName,total:1});
			}else{
				data[pos].total++;
			}
			
		}
		
		function inArray(item,arr){
			for(var arrindex=0,len=arr.length;arrindex<len;arrindex++){
				if(item==arr[arrindex]){
					return arrindex;
				}
			}
			return -1;
		}
		
		itmap.util.chart().init({src:"mapOther",data:data});
	}
	/**
	 * 搜索失败回调
	 */
	function searchFailed(){
		
	}
}

/**
 * 显示结果
 */
mapFindTool.prototype.displayRes = function(geometry){
	switch(geometry.type) {
		case "point" : {
			this.mgm.add(new esri.Graphic(geometry, this.pointSymbol()));
			break;
		}
		case "polyline" : {
			this.mgm.add(new esri.Graphic(geometry, this.lineSymbol()));
			break;
		}
		case "polygon" : {
			this.mgm.add(new esri.Graphic(geometry, this.polySymbol()));
			break;
		}
	}
}

/**
 * 点状符号
 */
mapFindTool.prototype.pointSymbol = function(){
	var pointSymbol = new esri.symbol.SimpleMarkerSymbol();
	pointSymbol.setStyle(esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE).setColor(new dojo.Color([255, 0, 0]));
	return pointSymbol;
}

/**
 * 线状符号
 */
mapFindTool.prototype.lineSymbol = function(){
	var lineSymbol = new esri.symbol.SimpleLineSymbol();
	lineSymbol.setStyle(esri.symbol.SimpleLineSymbol.STYLE_SOLID).setColor(new dojo.Color(255, 0, 0));
	return lineSymbol;
}

/**
 * 面状符号
 */
mapFindTool.prototype.polySymbol = function(){
	return new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT, new dojo.Color([255, 0, 0]), 1), new dojo.Color([255, 255, 0, 0.25]));
}

/**
 * 清除
 */
mapFindTool.prototype.clear = function(){
	this.mgm.clear();
}
