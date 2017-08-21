/**
 * 通用符号集
 */
itmap.arcgis.symbol = {
	// 默认符号
	defaultPointSymbol : function() {
		return new esri.symbol.SimpleMarkerSymbol(
					esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE, 10,
					new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,new dojo.Color([255,0,0]), 1),
					new dojo.Color([0,255,0,0.25])
			   );
	},
	defaultLineSymbol : function() {
		return new esri.symbol.SimpleLineSymbol(
					esri.symbol.SimpleLineSymbol.STYLE_SOLID,
					new dojo.Color([255,0,0]),3
			   );
	},
	defaultPolygonSymbol : function() {
		return  new esri.symbol.SimpleFillSymbol(
				   esri.symbol.SimpleFillSymbol.STYLE_SOLID,
				   new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,
				   new dojo.Color([255,0,0]), 2),
				   new dojo.Color([255,255,0,0.25])
				);
	},
	polygonSymbolNoFill : function() {
		return  new esri.symbol.SimpleFillSymbol(
				   esri.symbol.SimpleFillSymbol.STYLE_NULL,
				   new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,
				   new dojo.Color([255,0,0]), 3),
				   new dojo.Color([255,255,0,1])
				);
	},
	
	// 默认高亮显示符号
	highlightPointSymbol : function(){
		return new esri.symbol.SimpleMarkerSymbol(
				esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE, 10,
				new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,new dojo.Color([0,255,255]), 1),
				new dojo.Color([0,255,255,0])
		);
	},
	highlightPolylineSymbol : function(){
		return new esri.symbol.SimpleLineSymbol(
					esri.symbol.SimpleLineSymbol.STYLE_SOLID,
					new dojo.Color([0,255,255]),3
			   );
	},
	highlightPolygonSymbol : function(){
		return  new esri.symbol.SimpleFillSymbol(
				   esri.symbol.SimpleFillSymbol.STYLE_SOLID,
				   new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,
				   new dojo.Color([0,255,255]), 2),
				   new dojo.Color([255,255,0,0])
				);
	},

	/**
	 * 高亮显示对应的Graphic
	 * 0 255 255
	 */
	highlightGraphic : function(graphic){
		var _mgm = itmap.arcgis.mapGraphicManager("graphicHighlightLayer");
		var _highlightG = new esri.Graphic(graphic.geometry);
		var _symbol = null;
		var _geometry = graphic.geometry;
		switch(graphic.geometry.type){
			case "point" : {
				_symbol = itmap.arcgis.symbol.highlightPointSymbol();
				if(graphic.symbol instanceof esri.symbol.PictureMarkerSymbol){
					_symbol.size = Math.max(graphic.symbol.width,graphic.symbol.height)+1;
				}else{
					_symbol.size = graphic.symbol.size+1;
				}
				break;
			}
			case "polyline" : {
				_symbol = itmap.arcgis.symbol.highlightPolylineSymbol();
				_symbol.setWidth(graphic.symbol.width+2);
				break;
			}
			case "polygon" : {
				_symbol = itmap.arcgis.symbol.highlightPolygonSymbol();
				_outline = _symbol.outline;
				_outline.setWidth(_outline.width+2);
				_symbol.setOutline(_outline);
				break;
			}
		}
		_highlightG.setSymbol(_symbol);
		_mgm.reorderLayerAfter(graphic.getLayer().id,"graphicHighlightLayer");
		_mgm.clear();
		_mgm.add(_highlightG);
	},
	
	/**
	 * 清除高亮graphic
	 */
	clearHighlight : function(){
		itmap.arcgis.mapGraphicManager("graphicHighlightLayer").clear();
	},
	
	/**
	 * om符号
	 */
	omSymbol : function(type, state){
		return new esri.symbol.PictureMarkerSymbol("images/map/om/"+type+"-"+state+".png", 32, 32);
	},
	
	/**
	 * om符号
	 */
	runcarSymbol : function(dirction){
		return new esri.symbol.PictureMarkerSymbol("images/map/car/c_"+dirction+".png", 32, 32);
	},

	// 信号灯符号
	singnalLampSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/map/singal.png", 15, 15);
	},

	// 标志标牌符号
	signBoardSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/map/signBoard.png", 15, 15);
	},

	// 停车场诱导符号
	parkSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/map/park.png", 15, 15);
	},

	// 流量诱导屏符号
	flowPanelSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/map/flowPanel.png", 15, 15);
	},

	// 开始
	startSymbol : function() {
		var symbol = new esri.symbol.PictureMarkerSymbol("images/start.png",24,24);
		symbol.setOffset(12,12);
		return symbol;
	},

	// 结束
	endSymbol : function() {
		var symbol = new esri.symbol.PictureMarkerSymbol("images/end.png",24,24);
		symbol.setOffset(12,12);
		return symbol;
	},
	
	// 停靠点
	stopSymbol : function() {
		var symbol = new esri.symbol.PictureMarkerSymbol("images/lukoutupian.png",24,24);
		return symbol;
	},
	
	// 人行道开口
	peoCrossSymbol : function() {
		var symbol = new esri.symbol.PictureMarkerSymbol("images/cross.png",24,24);
		return symbol;
	},
	
	// 车辆开口
	carCrossSymbol : function() {
		var symbol = new esri.symbol.PictureMarkerSymbol("images/road.png",16,16);
		return symbol;
	},
	
	// 交通标志
	trafficSignsSymbol : function() {
		var symbol = new esri.symbol.PictureMarkerSymbol("images/bzbp.jpg",24,24);
		return symbol;
	},
	
	// 声响提示装置
	audiDeviceSymbol : function() {
		var symbol = new esri.symbol.PictureMarkerSymbol("images/1p.png",24,24);
		return symbol;
	},
	
	// 车辆检测器
	carDetectorSymbol : function() {
		var symbol = new esri.symbol.PictureMarkerSymbol("images/flow.png",24,24);
		return symbol;
	},

	// 卡口符号
	deviceSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/map/cross-normal.png", 15, 15);
	},
	
	// 卡口样式
	crossMarkerSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/map/cross-normal.png", 24, 24);
	},
	
	// 卡口报警样式
	crossAlarmSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/map/cross-alarm.png", 24, 24);
	},

	// 挖占符号
	occupySymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/occupy/wazhan.gif", 24, 24);
	},
	occupyNormalSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/occupy/wazhan-green.png", 24, 24);
	},
	occupyZXSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/occupy/wazhan-yellow.png", 24, 24);
	},
	
	// 检测器符号
	detectorSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/map/detector.png", 15, 15);
	},
	
	// 加油站符号
	oilStation : function(){
		return new esri.symbol.PictureMarkerSymbol("images/map/oil.png", 15, 15);
	},
	
	// 红色文本符号
	textRedSymbol : function(text, xoffset, yoffset) {
		var symbol = new esri.symbol.TextSymbol(text).setColor(
			    		new dojo.Color([255,0,0])).setFont(
			    	    new esri.symbol.Font("12pt").setWeight(esri.symbol.Font.WEIGHT_BOLD));
		symbol.setOffset(xoffset, yoffset);
		return symbol;
	},
	
	// 文本符号
	textSymbol : function(text, xoffset, yoffset) {
		var symbol = new esri.symbol.TextSymbol(text).setColor(
			    		new dojo.Color([128,0,0])).setFont(
			    	    new esri.symbol.Font("12pt").setWeight(esri.symbol.Font.WEIGHT_BOLD));
		symbol.setOffset(xoffset, yoffset);
		return symbol;
	},
	
	// 标示位置符号
	positionSignSymbol : function() {
		return new esri.symbol.PictureMarkerSymbol("images/map/position1.png",45,45);
	},
	
	// 位置标注点样式带偏移
	positionSymbolOffset : function() {
		var symbol = new esri.symbol.PictureMarkerSymbol("images/map/position.png",30,40);
		symbol.setOffset(6,20);
		return symbol;
	}
	
}