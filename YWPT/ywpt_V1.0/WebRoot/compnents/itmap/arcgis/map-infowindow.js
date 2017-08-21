/**
 * 地图弹出框，基于Dialog编写
 * @author ZLT
 * @date 2013-10-23
 **/
itmap.arcgis.mapInfoWindow = (function(){
	return function(conf){

		/**
		 * 对外接口
		 */
		var api = {
			show : show,
			hide : hide,
			setTitle : setTitle,
			setContent : setContent
		};
		
		/**
		 * 配置参数 
		 */
		var _conf = {
			mapMarginLeft : 0, // 地图左边距
			mapMarginTop : 0 // 地图上边距
		}

		/**
		 * 初始化Dialog
		 */
		var _d = itmap.util.dialog({
					mutiDialog : true,
					mutiDialogSeed : "mapInfoWindowDialog",
					title : "属性框",
					mask : false,
					isMove : false,
					buttonDisplay : {
						'confirmButton' : false,
						'cancelButton' : false
					},
					closeCall : _closeDialog
				}).hide();
		
		/**
		 * 地图资源
		 */
		var _map = null;
		
		/**
		 * 拖拽事件句柄
		 */
		var _dragStartEvtHandler = null,
			_dragEvtHandler = null,
			_dragEndEvtHandler = null;
		
		/**
		 * 缩放事件的句柄 
		 */
		var _zoomStartEvtHandler = null,
			_zoomEvtHandler = null,
			_zoomEndEvtHandler = null;
		
		/**
		 * 起始点
		 */
		var _dialogAnchor = null;
		
		/**
		 * 对话框开始屏幕坐标的位置 
		 */
		var _startScreenPoint = {x:"",y:""};
		
		/**
		 * 鼠标拖拽开始位置 
		 */
		var _startClickScreenPoint = {x:"",y:""};

		/**
		 * 偏移量
		 */
		var _offset = {x:"",y:""};
		
		/**
		 * 对话框的宽 
		 */
		var _width = 0;
		
		/**
		 * 对话框的高 
		 */
		var _height = 0;
		
		/**
		 * 显示状态 
		 */
		var _showflag = false;
		
		var _dragable = false;

		/**
		 * 初始化配置信息 
		 */
		for(elem in conf){
			if(conf[elem] != "" && typeof conf[elem] != "undefined"){
				_conf[elem] = conf[elem];
			}
		}

		/**
		 * 显示
		 * @param map esri.Map
		 * @param anchor mapPoint
		 */
		function show(map,anchor){
			if(map){
				_map = map;
				_d.hide();
				//_map.centerAt(anchor);
				//setTimeout(function(){
					// 对地图绑定相关事件
					if(_dragStartEvtHandler == null){
						_dragStartEvtHandler = dojo.connect(_map,"onMouseDragStart",_dragStart);
						_dragEndEvtHandler = dojo.connect(_map,"onMouseDragEnd",_dragEnd);
						_zoomStartEvtHandler = dojo.connect(_map,"onZoomStart",_zoomStart),
						_zoomEvtHandler = dojo.connect(_map,"onZoom",_zoom),
						_zoomEndEvtHandler = dojo.connect(_map,"onZoomEnd",_zoomEnd);
						dojo.connect(_map,"onMouseDown",function(e){
							if(e.button != 0){
								_d.hide();
							}
						});
					}
					
					_startScreenPoint = _map.toScreen(anchor);
					
					var _scrollTop = Math.max(document.documentElement.scrollTop,document.body.scrollTop);
					var _scrollLeft = Math.max(document.documentElement.scrollLeft,document.body.scrollLeft);
		
					_startScreenPoint = {
						x : _startScreenPoint.x+_conf.mapMarginLeft+_scrollLeft,
						y : _startScreenPoint.y+_scrollTop+_conf.mapMarginTop
					}
					
					_d.setPosition(_startScreenPoint.x,_startScreenPoint.y);
		
					_dialogAnchor = anchor;
					
					_d.show();
				//},800);
			}else{
				_d.show();
			}
			_showflag = true;
			return api;
		}

		/**
		 * 拖拽起始事件
		 */
		function _dragStart(event){
			_startClickScreenPoint = {
				x : event.clientX,
				y : event.clientY
			};
			_dragable = true;
			_dragEvtHandler = dojo.connect(_map,"onMouseDrag",_drag);
		}

		/**
		 * 拖拽事件
		 */
		function _drag(event){
			if(!_dragable){
				return false;
			}
			_offset = {
				x : event.clientX - _startClickScreenPoint.x,
				y : event.clientY - _startClickScreenPoint.y
			};

			var x = _offset.x+_startScreenPoint.x;
			var y = _startScreenPoint.y+_offset.y

			/**
			 * 如果对话框移动到地图范围以外，则隐藏 
			 */
			if(!_width){
				_width = _d.getWidth();
			}

			if(!_height){
				_height = _d.getHeight();
			}

			if(x<_conf.mapMarginLeft || x > _map.width + _conf.mapMarginLeft - _width - 10 || y < _conf.mapMarginTop || y>_map.height + _conf.mapMarginTop - _height){
				_d.hide(false);
			}else{
				if(_showflag){
					show();
				}
			}
			_d.setPosition(x,y);
		}

		/**
		 * 拖拽完成
		 */
		function _dragEnd(event){
			_dragable = false;
			dojo.disconnect(_dragEvtHandler);
			_startScreenPoint = {
				x : _startScreenPoint.x + _offset.x,
				y : _startScreenPoint.y + _offset.y
			};
			setTimeout(_positionCorrection,300); // 设置时间延迟是因为要使map返回正确的结果
		}

		/**
		 * 缩放开始 
		 */
		function _zoomStart(extent,zoomFactor,anchor,level){
			_d.hide(false);
		}

		/**
		 * 缩放事件 
		 */
		function _zoom(extent,zoomFactor,anchor){}

		/**
		 * 缩放结束 
		 */
		function _zoomEnd(extent,zoomFactor,anchor,level){
			if(_showflag){
				setTimeout(function(){_d.show()},300);
				setTimeout(_positionCorrection,300); // 设置时间延迟是因为要使map返回正确的结果
			}
		}
		
		function _closeDialog(){
			if(_showflag){
				/*dojo.disconnect(_dragStartEvtHandler);
				dojo.disconnect(_dragEvtHandler);
				dojo.disconnect(_dragEndEvtHandler);
				dojo.disconnect(_zoomStartEvtHandler);
				dojo.disconnect(_zoomEvtHandler);
				dojo.disconnect(_zoomEndEvtHandler);
				_dragStartEvtHandler = null;
				_dragEvtHandler = null;
				_dragEndEvtHandler = null;
				_zoomStartEvtHandler = null;
				_zoomEvtHandler = null;
				_zoomEndEvtHandler = null;*/
				_showflag = false;
			}
		}

		/**
		 * 校准点的最后位置 
		 */
		function _positionCorrection(){
			_p = _map.toScreen(_dialogAnchor);
			_startScreenPoint = {
				x : _p.x+_conf.mapMarginLeft,
				y : _p.y+_conf.mapMarginTop
			};
			_d.setPosition(_startScreenPoint.x,_startScreenPoint.y);
		}

		/**
		 * 隐藏
		 */
		function hide(){
			_showflag = false;
			_d.hide();
			return api;
		}

		/**
		 * 设置标题
		 */
		function setTitle(title){
			_d.setDialogTitle(title);
			return api;
		}

		/**
		 * 设置内容
		 */
		function setContent(content){
			_d.setDialogContent(content);
			return api;
		}

		return api;

	}
})();