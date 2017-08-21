/**
 * 视图切换器
 * @author ZLT
 * @since 2014-02-18
 */
itmap.arcgis.viewSwitcher = function(params){
	this._button = "mapPostview,mapNextview";
	this._conf = {
		src : "mapViewSwitcher",
		mapC : null,
		historyMaxSize : 3
	};
	this._postExtentHistory = [];
	this._nextExtentHistory = [];
	this._currentExtent = null;
	this._map = null;
	this._config(params);
	this._init();
	this._bindMapEventListener();
	this._bindButtonEventListener();
	this._recordFlag = false;
}

itmap.arcgis.viewSwitcher.prototype = {

	/**
	 * 初始化
	 */
	_init : function(){
		var _container = $("#"+this._conf.src);
		$(this._button.split(",")).each(function(i,item){
			_container.append("<div id='"+item+"'></div>");
		});
		this._map = this._conf.mapC;
		this._currentExtent = this._map.extent;
	},

	/**
	 * 参数配置
	 */
	_config : function(confObj){
		// 设置参数
		if(typeof confObj != "undefined") {
			for(elem in this._conf) {
				if( typeof confObj[elem] != "undefined"){ //&& confObj[elem] !== "") {
					this._conf[elem] = confObj[elem];
				}
			}
			confObj = null;
		}
	},

	/**
	 * extent记录
	 */
	_extentRecord : function(extent){
		if(this._postExtentHistory.length >= this._conf.historyMaxSize){
			this._postExtentHistory.shift();
		}
		this._postExtentHistory.push(this._currentExtent);
		this._currentExtent = extent;
		
	},

	/**
	 * 前一个视图
	 */
	_postRecord : function(){
		if(!this._postExtentHistory.length){
			return;
		}
		if(this._nextExtentHistory.length >= this._conf.historyMaxSize){
			this._nextExtentHistory.shift();
		}
		this._nextExtentHistory.push(this._currentExtent);
		this._currentExtent = this._postExtentHistory.pop();
		this._setCurrentView(this._currentExtent);
	},

	/**
	 * 后一个视图
	 */
	_nextRecord : function(){
		if(!this._nextExtentHistory.length){
			return;
		}
		if(this._postExtentHistory.length >= this._conf.historyMaxSize){
			this._postExtentHistory.shift();
		}
		this._postExtentHistory.push(this._currentExtent);
		this._currentExtent = this._nextExtentHistory.pop();
		this._setCurrentView(this._currentExtent);
	},

	/**
	 * 设置当前视图
	 */
	_setCurrentView : function(extent){
		this._map.setExtent(extent);
	},

	/**
	 * 绑定监听事件
	 */
	_bindMapEventListener : function(){
		var _instance = this;
		dojo.connect(_instance._map,"onMouseDragStart",function(extent, endPoint){
			_instance._recordFlag = true;
		});

		dojo.connect(_instance._map,"onPanEnd",function(extent, endPoint){
			if(_instance._recordFlag){
				_instance._extentRecord(extent);
				_instance._recordFlag = false;
			}
		});

		dojo.connect(_instance._map,"onZoomEnd",function(extent,zoomFactor,anchor,level){
			_instance._extentRecord(extent);
		});
	},

	/**
	 * 为按钮绑定事件
	 */
	_bindButtonEventListener : function(){
		var _instance = this,
			_backgroundPos = {
				mapPostviewFocus : "-28px 0",
				mapPostviewUnfocus : "0 0",
				mapNextviewFocus : "-42px 0",
				mapNextviewUnfocus : "-14px 0"
			};
		$(this._button.split(",")).each(function(index,elem){
			$("#"+elem).mouseenter(function(){
				$(this).css("backgroundPosition",_backgroundPos[elem+"Focus"]);
			}).mouseleave(function(){
				$(this).css("backgroundPosition",_backgroundPos[elem+"Unfocus"]);
			}).mousedown(function(){
				$(this).css("backgroundPosition",_backgroundPos[elem+"Unfocus"]);
			}).mouseup(function(){
				$(this).css("backgroundPosition",_backgroundPos[elem+"Focus"]);
			}).click(function(){
				_instance._btnAction(elem);
			});
		});
	},

	/**
	 * 按钮动作
	 */
	_btnAction : function(elem){
		switch(elem){
			case "mapPostview" : {
				this._postRecord();
				break;
			}
			case "mapNextview" : {
				this._nextRecord();
				break;
			}
		}
	}
}