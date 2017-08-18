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
				$("body").append("<div id='mapMouseOverTipBox'></div>");
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
			var right = $("#mapContainer").width() - screenPoint.x - $("#mapMouseOverTipBox").width();
			$("#mapMouseOverTipBox").css({
				right : right-15,
				top : screenPoint.y-10
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
			$("#mapMouseOverTipBox").fadeOut();
		}

		return api;
	}
});