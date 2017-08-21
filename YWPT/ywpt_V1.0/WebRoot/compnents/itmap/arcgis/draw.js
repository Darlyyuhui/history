/**
 * 绘制封装类
 */
itmap.arcgis.draw = function(){
	var api = {
			baseMapActivate:baseMapActivate
		};
	
	var symbol = itmap.arcgis.symbol;
	var util = itmap.arcgis.util;
	
	var toolbar = null;// 绘制工具对象
	var handler = null;// 绘制工具对象绑定的绘制完成方法的句柄
	// 获取绘制工具toolbar
	function initToolbar() {
		if(toolbar && handler) {
			toolbar.deactivate();
			dojo.disconnect(handler);
			handler = null;
		}
		else {
			toolbar = null;
			toolbar = new esri.toolbars.Draw(map);
		}
	}
	// 基本的绘制工具激活，返回绘制完成后的geometry，并指定添加到地图上
	function baseMapActivate(type, callback, layer, msgdivid, tooltip) {
		var flag = true;
		if(!util.checkParam(msgdivid) || document.getElementById(msgdivid) == null) {
			flag = false;
		}
		// 如果toolbar已经初始化，则调用它的deactivate方法
		if(toolbar)toolbar.deactivate();
		
		initToolbar();
		handler = dojo.connect(toolbar, "onDrawEnd", function(geometry) {
			toolbar.deactivate();
			if(layer) {
				var g = null;
				if(geometry.type == "point") {
					g = new esri.Graphic(geometry, symbol.defaultPointSymbol());
				}
				else if(geometry.type == "polyline") {
					g = new esri.Graphic(geometry, symbol.defaultLineSymbol());
				}
				else {
					g = new esri.Graphic(geometry, symbol.defaultPolygonSymbol());
				}
				layer.add(g);
			}
			
			if(callback)callback(geometry);
		});
		if(type == "point") {
			// 添加终点
			toolbar.activate(esri.toolbars.Draw.POINT);
			if(flag)$("#"+msgdivid).text("请点击地图，进行绘制...");
		}
		else if(type == "polyline"){
			// 绘制线
			toolbar.activate(esri.toolbars.Draw.POLYLINE);
			if(flag)$("#"+msgdivid).text("请点击地图，进行绘制。双击结束绘制！");
		}
		else if(type == "polygon"){
			// 绘制多边形
			toolbar.activate(esri.toolbars.Draw.POLYGON);
			if(flag)$("#"+msgdivid).text("绘制多边形！");
		}
		else if(type == "handExtent"){
			// 手绘多边形
			toolbar.activate(esri.toolbars.Draw.FREEHAND_POLYGON);
			if(flag)$("#"+msgdivid).text("手绘多边形！");
		}
		else if(type == "circle"){
			// 绘制圆
			toolbar.activate(esri.toolbars.Draw.CIRCLE);
			if(flag)$("#"+msgdivid).text("绘制圆！");
		}
		else {
			// 绘制面
			toolbar.activate(esri.toolbars.Draw.EXTENT);
			if(flag)$("#"+msgdivid).text("请在地图上框选，进行绘制...");
		}
		if(tooltip)$(".tooltip").html(tooltip);
	}
	
	return api;
}();