MapFactory.Define("ItmsMap/Util/ViewSwitch*",[
    "MapFactory/MapManager"
],function(mapManager){
	return function(params){
		var _mapManager = mapManager();
		this._button = "mapPostview,mapNextview";
		this._conf = {
			src : "mapViewSwitcher",
			historyMaxSize : 3
		};
		this._postExtentHistory = [];
		this._nextExtentHistory = [];
		this._currentExtent = null;
		this._recordFlag = false;

		/**
		 * 初始化
		 */
		this._init = function(){
			var _container = $("#"+this._conf.src);
			$(this._button.split(",")).each(function(i,item){
				_container.append("<div id='"+item+"'></div>");
			});
			this._currentExtent = _mapManager.getCurrentExtent();
		}

		/**
		 * 为按钮绑定事件
		 */
		this._bindButtonEventListener = function(){
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
		}

		/**
		 * 按钮动作
		 */
		this._btnAction = function(elem){
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

		/**
		 * extent记录
		 */
		this._extentRecord = function(extent){
			if(this._postExtentHistory.length >= this._conf.historyMaxSize){
				this._postExtentHistory.shift();
			}
			this._postExtentHistory.push(this._currentExtent);
			this._currentExtent = extent;
			
		}
		
		/**
		 * 设置当前视图
		 */
		this._setCurrentView = function(extent){
			_mapManager.setExtent(extent);
		}

		/**
		 * 前一个视图
		 */
		this._postRecord = function(){
			if(!this._postExtentHistory.length){
				return;
			}
			if(this._nextExtentHistory.length >= this._conf.historyMaxSize){
				this._nextExtentHistory.shift();
			}
			this._nextExtentHistory.push(this._currentExtent);
			this._currentExtent = this._postExtentHistory.pop();
			this._setCurrentView(this._currentExtent);
		}

		/**
		 * 后一个视图
		 */
		this._nextRecord = function(){
			if(!this._nextExtentHistory.length){
				return;
			}
			if(this._postExtentHistory.length >= this._conf.historyMaxSize){
				this._postExtentHistory.shift();
			}
			this._postExtentHistory.push(this._currentExtent);
			this._currentExtent = this._nextExtentHistory.pop();
			this._setCurrentView(this._currentExtent);
		}

		/**
		 * 绑定监听事件
		 */
		this._bindMapEventListener = function(){
			var _instance = this;

			_mapManager.setMouseDragEvent({
				dragstart : function(point){
					_instance._recordFlag = true;
				},
				dragend : function(point){
					if(_instance._recordFlag){
						_instance._extentRecord(_mapManager.getCurrentExtent());
						_instance._recordFlag = false;
					}
				}
			});
			_mapManager.setZoomEvent({
				zoomend : function(extent){
					_instance._extentRecord(extent);
				}
			});
		}

		MapFactory.Extend(this._conf,params);
		this._init();
		this._bindMapEventListener();
		this._bindButtonEventListener();
	}
});