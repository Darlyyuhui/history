MapFactory.Define("ItmsMap/Util/Navigation*",[
    "MapFactory/MapManager"
],function(mapManager){
	return function(o){
		var _mapManager = mapManager();
		this.button = "panTop,panLeft,panBottom,panRight,fullExtent";
		this._conf = {
			initExtent : {},
			container : "",
			viewSwitch : true
		};

		this._navBox = null;

		MapFactory.Extend(this._conf,o);

		/**
		 * 初始化
		 */
		this._init = function(){
			var _navBox = this._navBox = $("<div id='mapNavigation'></div>").appendTo($("#"+this._conf.container));
			$(this.button.split(",")).each(function(i,elem){
				$("<div id='"+elem+"'></div>").appendTo(_navBox);
			});
			this._bindButton();
		}

		/**
		 * 给按钮绑定事件
		 */
		this._bindButton = function(){
			
			var _backgroundPosition = {
				unfocus : "0 0",
				panTop : "-48px 0px",
				panRight : "-96px 0px",
				panBottom : "-144px 0px",
				panLeft : "-192px 0px",
				fullExtent : "0 0"
			};
			
			var conf = this._conf;
			var _navBox = this._navBox;
			
			var _bindEvents = {
				panTop : (function(){return function(){_mapManager.panUp()}})(),
				panRight : (function(){return function(){_mapManager.panRight()}})(),
				panBottom : (function(){return function(){_mapManager.panDown()}})(),
				panLeft : (function(){return function(){_mapManager.panLeft()}})(),
				fullExtent : (function(){return function(){_mapManager.setExtent(conf.initExtent)}})()
			};
			
			$(this.button.split(",")).each(function(index,elem){
				$("#"+elem).mouseenter(function(){
					_navBox.css("backgroundPosition",_backgroundPosition[elem]);
				}).mouseleave(function(){
					_navBox.css("backgroundPosition",_backgroundPosition["unfocus"]);
				}).mousedown(function(){
					_navBox.css("backgroundPosition",_backgroundPosition["unfocus"]);
					_bindEvents[elem]();
				}).mouseup(function(){
					_navBox.css("backgroundPosition",_backgroundPosition[elem]);
				});
			});
		}

		this._init();
	}
});