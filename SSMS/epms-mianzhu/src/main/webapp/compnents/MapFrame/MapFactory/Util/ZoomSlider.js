MapFactory.Define("MapFactory/Util/ZoomSlider*",[
    "MapFactory/Util/DragAble*",
    "MapFactory/MapManager"
],function(drag,mapManager){
	return function(conf){
		var _conf = {
			src : "",
			levels : 8,
			levelBarHeight : 20,
			levelStart : -1,
			levelEnd : -1
		};

		MapFactory.Extend(_conf,conf);

		var _container = null,
			_mapManager = mapManager(),
			_zoomBarContainer = null,
			_zoomBarBackground = null,
			_zoomBar = null,
			_zoomHandler = null,
			_zoomBarHeight = null,
			_containerPos = null,
			_levels = _conf.levels,
			_nowIndex = 0,
			_levelStart = _conf.levelStart,
			_levelEnd = _conf.levelEnd,
			_currentLevel = 0;

		function _init(){
			if(!_conf.src){
		   	    return;
			}
			_container = $("#"+_conf.src);
			_currentLevel = _mapManager.getLevel();
			if(_levelStart >= 0 && _levelEnd > 0){
				_currentLevel = _currentLevel < _levelStart ? _levelStart : _currentLevel;
				_levelEnd = _levelEnd > _levels-1 ? _levels-1 : _levelEnd;
				_currentLevel = _currentLevel > _levelEnd ? _levelEnd : _currentLevel;
				_levels = _levelEnd - _levelStart + 1;
				_mapManager.setLevel(_currentLevel);
			}
			_initBoxs();

			_setLevel(_currentLevel);
			_mapManager.setZoomEvent({
				zoomend : function(){
					_currentLevel = _mapManager.getLevel();
					_setLevel(_currentLevel);
				}
			});
			_mapManager.setMouseWheelEvent(function(e){
				if(e.wheelDirection == "in"){
					if(_currentLevel - _levelStart < _levels - 1) {
                        _currentLevel += 1;
                        _mapManager.zoomIn();
					}
				}else if(e.wheelDirection == "out"){
					if(_currentLevel - _levelStart > 0) {
                        _currentLevel -= 1;
                        _mapManager.zoomOut();
					}
				}
			});
		}
		_init();

		function _initBoxs(){
			_zoomBarContainer = $("<div id='zoomBarContainer'></div>");
			_zoomBarBackground = $("<div id='zoomBarBackground'></div>");
			_zoomBar = $("<ul id='zoomBar'></ul>");
			_zoomHandler = $("<div id='zoomHandler'></div>");
			_zoomBarHeight = _levels * _conf.levelBarHeight;
			_containerPos = _container.position();
			_zoomBarContainer.append(_zoomBarBackground);
			_zoomBarContainer.append(_zoomBar);
			_zoomBarContainer.append(_zoomHandler);
			for(var i=0,len=_levels;i<len;i++){
				var _tempLi = $("<li>-</li>");
				_tempLi.height(_conf.levelBarHeight);
				_zoomBar.append(_tempLi);
			}

			_zoomBar.children("li").bind("click",function(){
				_mapManager.setLevel(_levels - $(this).index() + _levelStart - 1);
			});

			_zoomBarBackground.height(_zoomBarHeight);
			_container.append(_zoomBarContainer);

			drag().target("zoomHandler",{
				seed : "zoomSlider",
				onlyY : true,
				minY : _container.position().top,
				maxY : _zoomBarHeight,
				isCapture : true,
				captureArea : "zoomBar",
				captureCallFunc : function(o){
					_zoomHandler.css({
						top : o.position.top - 116
					});
					_nowIndex = o.index;
				},
				mouseUpCallFunc : function(){
					_mapManager.setLevel(_levels - _nowIndex + _levelStart - 1);
				}
			});
		}

		function _setLevel(level){
			_zoomHandler.css({
				top : _zoomBarHeight - ((level + 1 - _levelStart) * _conf.levelBarHeight - 4)
			});
		}
		
		return _zoomBarHeight;
	}
});