/**
 * @author ZLT
 * @Date 2013-9-10
 * 方法：
 * init 初始化函数，当参数为true时，会移动原来的对象，当参数为false时则不会移动原来的对象，默认为true
 * target 指定对象id，将指定对象进行移动，参数为对象id
 * mouseDownCall 鼠标按下的回调函数，参数为回调函数
 * mouseUpCall 鼠标抬起时的回调函数，参数为回调函数
 */
itmap.util.mapDragAble = (function() {
	return function() {

		/**
		 * 对象接口
		 */
		var api = {
			init : init,
			target : target
		}

		/**
		 * 需要初始化的对象
		 */
		var _o = null;
		
		/**
		 * 对象的序数
		 */
		var _oIndex = -1;
		
		/**
		 * 克隆对象
		 */
		var _oClone = null;

		/**
		 * 开始拖拽的点
		 */
		var _startPoint = {};

		/**
		 * 拖拽完成鼠标所在的点
		 */
		var _endPoint = {};
		
		/**
		 * 鼠标在拖拽对象内的偏移量
		 */
		var _offsetX = 0,_offsetY = 0;

		/**
		 * 是否允许拖动
		 */
		var _dragEable = false;
		
		/**
		 * 捕捉区域
		 */
		var _captureArea = null;
		
		/**
		 * 捕捉时离元素最近的index
		 */
		var _nearestIndex = -1;
		
		/**
		 * 配置信息
		 */
		var _conf = {
			seed : "", // 初始化时分别不同实例的种子
			isMoveOriginal : true, // 是否移动原来的对象
			isCapture : false, // 拖拽时是否进行捕捉
			captureArea : "", // 若为空字符串，则不进行捕捉，若设置为目的区域的ID或者用Jquery转化过的对象，则拖拽时对当前对象进行捕捉
			mouseDownCallFunc : "", // 鼠标按下时触发的回调函数
			mouseUpCallFunc : "" // 鼠标抬起时触发的回调函数
		}

		/**
		 * 初始化接口
		 */
		function init(conf){
			_init();
			_config(conf);
			$(".MapDragAbleTarget"+_conf.seed).bind("mousedown",_cloneTarget);
		}

		/**
		 * 初始化函数
		 */
		function _init() {
			_startPoint = {};
			_endPoint = {};
			_oClone = null;
		}

		// 配置对话框
		function _config(confObj){
			// 设置参数
			if( typeof confObj != "undefined") {
				for(elem in _conf) {
					if( typeof confObj[elem] != "undefined"){ //&& confObj[elem] !== "") {
						_conf[elem] = confObj[elem];
					}
				}
				confObj = null;
			}
			return api;
		}

		// 允许拖动并初始化
		function _dragEnable(_event){

			var e = _event || window.event;
			var p = _getTargetPosition(_oClone[0]);
			var scrollLeft = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
			var scrollTop = Math.max(document.body.scrollTop,document.documentElement.scrollTop);

			_startPoint = {
				x : p.left ? p.left : 0,
				y : p.top ? p.top : 0
			};

			if(_oClone[0].style.right != ''){
				_oClone[0].style.right = '';
				p.left = Math.max(document.body.clientWidth,document.documentElement.clientWidth) - _oClone[0].clientWidth - p.right;
				DialogOuterBox.style.left = p.left + 'px';
			}

			if(_oClone[0].style.bottom != ''){
				_oClone[0].style.bottom = '';
				p.top = Math.max(document.body.clientHeight,document.documentElement.clientHeight) - _oClone[0].clientHeight - p.bottom;
				_oClone[0].style.left = p.top + 'px';
			}

			_offsetX = scrollLeft + e.clientX - p.left;
			_offsetY = scrollTop + e.clientY - p.top;
			_dragEable = true;

			// 设置移动时的鼠标样式
			_oClone[0].style.cursor = "move";

			// 触发鼠标按下时的回调函数
			if("" != _conf.mouseDownCallFunc){
				_conf.mouseDownCallFunc(_startPoint);
			}

			document.body.onmousemove = _move;
			document.body.onmouseup = _dragEnd;

			if(_conf.isCapture){
				_initCapture();
			}

			
		}

		/**
		 * 获取元素位置
		 */
		function _getTargetPosition(o) {
			if( typeof o != "object" || o == null) {
				return 0;
			}

			var left = 0, right = 0, top = 0, bottom = 0;
			left = o.style.left;
			top = o.style.top;
			right = o.style.right;
			bottom = o.style.bottom;
			if(left != '') {
				left = left.substr(0, left.indexOf('p'));
			}
			if(top != '') {
				top = top.substr(0, top.indexOf('p'));
			}
			if(right != '') {
				right = right.substr(0, right.indexOf('p'));
			}
			if(bottom != '') {
				bottom = bottom.substr(0, bottom.indexOf('p'));
			}
			return {
				left : left,
				right : right,
				top : top,
				bottom : bottom
			};
		}

		/**
		 * 拖动
		 */
		function _move(_event) {
			if(_dragEable) {
				var e = _event || window.event;
				var scrollLeft = Math.max(document.body.scrollLeft, document.documentElement.scrollLeft);
				var scrollTop = Math.max(document.body.scrollTop, document.documentElement.scrollTop);

				// 计算偏移量
				_offsetMove = {
					offsetX : scrollLeft + e.clientX - _offsetX - parseFloat(_oClone.css("left")),
					offsetY : scrollTop + e.clientY - _offsetY - parseFloat(_oClone.css("top"))
				}

				// 计算并设置所拖拽对象的当前位置
				_oClone.css({
					"left" : scrollLeft + e.clientX - _offsetX,
					"top" : scrollTop + e.clientY - _offsetY
				});
			}
			if(_conf.isCapture && _dragEable){
				_capture(_event);
			}
		}

		/**
		 * 拖动结束
		 */
		function _dragEnd() {
			if(_dragEnable){
				document.body.onmousemove = null;
				document.body.onmouseup = null;
				var p = _getTargetPosition(_oClone[0]);
				_endPoint = {
					x : parseInt(p.left) + _oClone[0].clientWidth/2,
					y : parseInt(p.top) + _oClone[0].clientHeight/2,
					attributes : _oClone[0].attributes
				};
				if(_conf.isMoveOriginal && !_conf.isCapture){
					_o.css({
						position : "absolute",
						left : parseInt(p.left),
						top : parseInt(p.top),
						zIndex : 20000
					});
				}
				
				if(_conf.isCapture){
					_setCapture();
				}
	
				_oClone.remove();
	
				// 触发鼠标抬起时的函数
				if("" != _conf.mouseUpCallFunc){
					_conf.mouseUpCallFunc(_endPoint);
				}
	
				_dragEable = false;
			}
		}

		/**
		 * 复制对象
		 */
		function _cloneTarget(e){
			_init();
			_o = $(this);
			_oIndex = $(this).index();
			_oClone = _o.clone();
			_oClone.fadeTo(500,0.5);
			_oClone.css({
				"position" : "absolute",
				"left" : parseInt(e.clientX),
				"top" : parseInt(e.clientY),
				"zIndex" : 20000
			});
			$("body").append(_oClone);
			_oClone.css({
				"left" : parseInt(e.clientX - _oClone[0].clientWidth/2),
				"top" : parseInt(e.clientY - _oClone[0].clientHeight/2)
			});
			_dragEnable(e);
			//_oClone[0].onmouseup = _dragEnd;
			return false;
		}

		/**
		 * 初始化需要拖拽的对象
		 */
		function target(target,conf) {

			if(typeof target == "object"){
				if(typeof target.target == "object"){
					target = target.target;
				}
			}

			if(target == "") {
				return null;
			}

			if(typeof target == "object"){
				_o = $(target);
			}else if(typeof target == "string"){
				_o = $("#" + target);
			}else{
				return null;
			}

			if(_o[0] == null){
				return null;
			}
			
			_config(conf);

			_init();

			_o.mousedown(_cloneTarget);

			return api;
		}

		/**
		 * 初始化捕捉
		 */
		function _initCapture(){
			if(!_conf.captureArea || !_dragEable || _captureArea){
				return;
			}

			if(typeof _conf.captureArea == "object"){
				_captureArea = _conf.captureArea;
			}else{
				_captureArea = $("#"+_conf.captureArea);
			}

		}

		/**
		 * 捕捉
		 */
		function _capture(_event){
			var _mousePos = _getMousePos(_event);
			var _children = _captureArea.children();
			var scrollTop = _captureArea.parent().scrollTop();
			var scrollLeft = _captureArea.parent().scrollLeft();
			var _objPos = null;
			var i = 0,len = _children.length;
			for(i=0;i<len;i++){
				_objPos = _getPos(_children[i]);
				if(_mousePos.x > _objPos.left - scrollLeft &&
				   _mousePos.x < _objPos.left + _children[i].offsetWidth - scrollLeft &&
				   _mousePos.y > _objPos.top - scrollTop &&
				   _mousePos.y < _objPos.top + _children[i].offsetHeight - scrollTop &&
				   _oIndex != i
				){
					_nearestIndex = i;
					$(_children[i]).css({
						"backgroundColor" : "#B0D8FF"
					});
				}else{
					$(_children[i]).css({
						"backgroundColor" : ""
					});
				}
			}
		}

		/**
		 * 将元素设置到捕捉后的位置
		 */
		function _setCapture(){
			if(_nearestIndex != -1){
				if(_nearestIndex > _oIndex){
					_o.insertAfter(_captureArea.children().css("backgroundColor","").eq(_nearestIndex));
				}else{
					_o.insertBefore(_captureArea.children().css("backgroundColor","").eq(_nearestIndex));
				}
				_nearestIndex = -1;
			}
		}

		/**
		 * 获取对象位置
		 */
		function _getPos(obj){
			var iTop = obj.offsetTop;
			var iLeft = obj.offsetLeft;
			while(obj.offsetParent){
				iTop += obj.offsetParent.offsetTop;
				iLeft += obj.offsetParent.offsetLeft;
				obj = obj.offsetParent;
			}
			return {top:iTop,left:iLeft};
		}

		/**
		 * 获取当前鼠标位置
		 */
		function _getMousePos(_event){
		
			var e = _event || window.event;
			return {
				x : e.clientX,
				y : e.clientY
			}
		}

		return api;
	}
})();