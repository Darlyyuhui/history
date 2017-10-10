/**
 * @author xiongjie
 * @Date 2014-12-9
 */
MapFactory.Define("ItmsMap/Util/MouseOverTip*",["MapFactory/MapManager"],function(MapManager){
	return function(){
		var map = MapManager();

		/**
		 * 接口
		 */
		var api = {
			setContent : setContent,
			show : show,
			hide : hide
		}

		/**
		 * 初始化
		 */
		function _init(){
			_initTipBox();
		}

		/**
		 * 初始化tipbox
		 */
		function _initTipBox(){
			if($("#mapMouseOverTipBox")[0] == null){
				$("#map").append("<div style='position:absolute;z-index:100000;fontwidget:blod;background:#A9A9A9;padding-left:3px;padding-right:3px; border-radius:5px 5px 5px 5px;' id='mapMouseOverTipBox'></div>");
			}
		}

		/**
		 * 设置位置
		 */
		function _setPosition(geo){
			var points = geo.points.split(",");
			var x = parseFloat(points[0]);
			var y = parseFloat(points[1]);
			var screenPoint = map.toScreen(x, y);
			var left = screenPoint.x - $("#mapMouseOverTipBox").width()/2;
			$("#mapMouseOverTipBox").css({
				left : left,
				top   : screenPoint.y+20
			});
		}

		/**
		 * 设置内容
		 */
		function setContent(content){
			_init();
			$("#mapMouseOverTipBox").html(content);
		}

		/**
		 * 显示事件，单位为毫秒
		 */
		function show(geo, time){
			if(!geo || !geo.points)return;
			_setPosition(geo);
			$("#mapMouseOverTipBox").fadeIn("slow");
			if(typeof time == "number"){
				setTimeout(hide,time);
			}
		}

		/**
		 * 隐藏
		 */
		function hide(){
			$("#mapMouseOverTipBox").fadeOut("slow",stopAllFade);
		}
		function stopAllFade(){
			$("#mapMouseOverTipBox").stop(true);
		}
		return api;
	}
});