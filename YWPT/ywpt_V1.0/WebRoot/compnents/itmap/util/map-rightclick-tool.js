/**
 * 右键点击菜单插件
 * @Author ZLT
 * @Date 2013-8-26
 */
itmap.util.mapRightClickWidget = (function(){
	return function(){

		/**
		 * 工具接口
		 */
		var api = {
			init : init, // 初始化右键点击菜单
			add : add, // 添加菜单项
			hide : hide, // 隐藏菜单
			show : _show // 显示菜单
		}

		/**
		 * 初始化对象
		 */
		var o = null;

		/**
		 * 对话框信息
		 */
		var menuContext = [];

		/**
		 * 菜单显示事件回调函数
		 */
		var menuContextEvt = null;
		
		/**
		 * 目前单击位置
		 */
		var currentPosition = null;
		
		/**
		 * 所对应容器距浏览器左上角的左边和上边的距离
		 */
		var marginLeft = 0;
		var marginTop = 0;
		
		/**
		 * 单击时相对容器内部的位置
		 */
		var relativePosition = null;

		/**
		 * 初始化
		 */
		function init(conf){
			if(!conf.srcNode){
				o = document.body || document.documentElement;
			}
			if(o == null){
				o = document.getElementById(conf.srcNode);
			}
			if(conf.onRightClick){
				_call(conf.onRightClick);
			}
			if(conf.marginLeft){
				marginLeft = conf.marginLeft;
			}
			if(conf.marginTop){
				marginTop = conf.marginTop;
			}
			o.oncontextmenu = _show;
			return api;
		}

		/**
		 * 点击后回调函数
		 */
		function _call(func){
			menuContextEvt = func;
			return api;
		}

		/**
		 * 添加
		 */
		function add(text,func){
			menuContext.push([text,func]);
			return api;
		}

		/**
		 * 显示工具
		 */
		function _show(e){
			if(menuContextEvt!=null){
				menuContextEvt(e);
			}
			if($("#mapRightClickContextMenu")[0]==null){
				_initMenu(e);
				var pos = _getPosition(e);
				_setPosition(pos.left,pos.top);
				$("#mapRightClickContextMenu").show();
			}else{
				_initMenuContent();
				var pos = _getPosition(e);
				_setPosition(pos.left,pos.top);
				$("#mapRightClickContextMenu").show();
			}
			return false;
		}

		/**
		 * 初始化菜单信息
		 */
		function _initMenu(e){

			if($("#mapRightClickContextMenu")[0]==null){
				$("body").append("<ul id='mapRightClickContextMenu'></ul>");
			}
			_initMenuContent();
		}

		/**
		 * 获取点击位置
		 */
		function _getPosition(e){
			// 位置判断
			var e = e || window.event;
			var scrollLeft = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
			var scrollTop = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
			currentPosition = {
				left : scrollLeft+e.clientX,
				top : scrollTop+e.clientY
			};
			relativePosition = {
				left : currentPosition.left - marginLeft,
				top : currentPosition.top - marginTop
			};
			return currentPosition;
		}

		/**
		 * 设置菜单位置
		 */
		function _setPosition(left,top){
			$("#mapRightClickContextMenu").css({
				left : left,
				top : top
			});
		}

		/**
		 * 初始化菜单内容
		 */
		function _initMenuContent(){
			$("#mapRightClickContextMenu").empty();
			for(var i=0;i<menuContext.length;i++){
				$("#mapRightClickContextMenu").append("<li>"+menuContext[i][0]+"</li>");
				$("#mapRightClickContextMenu li:eq("+i+")").bind("click",function(){
					if(menuContext[$(this).index()][1]!=""){
						// 回调函数默认给传参数data，包含index和label
						menuContext[$(this).index()][1]({
							index : $(this).index(),
							label : menuContext[$(this).index()][0],
							position : currentPosition,
							relativePosition : relativePosition
						});
					}
					hide();
				});
			}
			$("body").click(function(){
				hide();
			});
		}

		/**
		 * 隐藏工具
		 */
		function hide(){
			$("#mapRightClickContextMenu").hide();
		}

		return api;
	}
})($);