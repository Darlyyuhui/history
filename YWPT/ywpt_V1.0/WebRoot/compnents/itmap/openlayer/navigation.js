/**
 * 开源地图导航按钮
 * @author ZLT
 * @since 2014-02-17
 **/

itmap.openlayer.navigation = function(o){
	this.button = "panTop,panLeft,panBottom,panRight,fullExtent";
	this._conf = {
		mapC : null,
		initExtent : {},
		container : ""
	}
	this._config(o);
	this._navBox = null;
	this._init();
}

itmap.openlayer.navigation.prototype = {

	/**
	 * 初始化
	 */
	_init : function(){
		var _navBox = this._navBox = $("<div id='mapNavigation'></div>").appendTo($("#"+this._conf.container));
		$(this.button.split(",")).each(function(i,elem){
			$("<div id='"+elem+"'></div>").appendTo(_navBox);
		});
		this._bindButton();
	},

	/**
	 * 参数配置
	 */
	_config : function(confObj){
		// 设置参数
		if( typeof confObj != "undefined") {
			for(elem in this._conf) {
				if( typeof confObj[elem] != "undefined"){ //&& confObj[elem] !== "") {
					this._conf[elem] = confObj[elem];
				}
			}
			confObj = null;
		}
	},

	/**
	 * 给按钮绑定事件
	 */
	_bindButton : function(){
		
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
		var _slideFactor = 50;
		
		var _bindEvents = {
			panTop : (function(){return function(){conf.mapC.pan(0,-_slideFactor)}})(),
			panRight : (function(){return function(){conf.mapC.pan(_slideFactor,0)}})(),
			panBottom : (function(){return function(){conf.mapC.pan(0,_slideFactor)}})(),
			panLeft : (function(){return function(){conf.mapC.pan(-_slideFactor,0)}})(),
			fullExtent : (function(){return function(){conf.mapC.zoomToExtent(conf.initExtent,false)}})()
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
}