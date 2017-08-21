/**
 * 选项卡工具
 * @Author ZLT
 * @Date 2013-9-12
 **/
MapFactory.Define("ItmsMap/Util/Tab*",function(){

	var _tabCache = {};

	return function(){

		/**
		 * 接口
		 */
		var api = {
			init : init, // 初始化工具
			switchTab : switchTab // 转换tab
		}

		/**
		 * 标签盒子
		 */
		var _tabSrc = null;

		/**
		 * 自定义标签盒子样式
		 */
		var _tabSrcActiveStyle = "";

		var _tabSrcDeactiveStyle = "";

		/**
		 * 内容盒子
		 */
		var _contentSrc = null;

		/**
		 * 自定义内容盒子样式
		 */
		var _contentActiveSrcStyle = "";

		var _contentDeactiveSrcStyle = "";
		
		var _currentSeed = "";

		/**
		 * 初始化
		 * @param tabSrc tab的容器id
		 * @param contentSrc 内容部分的id
		 * @param activeIndex 显示的tab索引
		 * @param mouseAction 鼠标行为，可选，默认为click，支持事件：click、hover
		 * @param seed 标识ID
		 */
		function init(tabSrc,contentSrc,activeIndex,mouseAction,seed){
			if(!seed){
				seed = "default";
			}

			_currentSeed = seed;

			if(_tabCache[seed]){
				delete _tabCache[seed];
			}

			if(!document.getElementById(tabSrc)) {
				_tabSrc = $(parent.document.getElementById(tabSrc));
			}
			else{
				_tabSrc = $("#"+tabSrc);
			}
			if(!document.getElementById(contentSrc)) {
				_contentSrc = $(parent.document.getElementById(contentSrc));
			}
			else{
				_contentSrc = $("#"+contentSrc);
			}
			_activeIndex = activeIndex;

			_tabSrcActiveStyle = _tabSrc.attr("activeStyle");
			_tabSrcDeactiveStyle = _tabSrc.attr("deactiveStyle");
			_contentActiveSrcStyle = _contentSrc.attr("activeStyle");
			_tabSrcDeactiveStyle = _contentSrc.attr("deactiveStyle");

			if(!_tabSrcActiveStyle){
				_tabSrcActiveStyle = "mapActiveTab";
			}

			if(!_tabSrcDeactiveStyle){
				_tabSrcDeactiveStyle = "mapDeactiveTab";
			}

			if(!_contentActiveSrcStyle){
				_contentActiveSrcStyle = "mapActiveContent";
			}

			if(!_contentDeactiveSrcStyle){
				_contentDeactiveSrcStyle = "mapDeactiveContent";
			}

			if(!mouseAction){
				mouseAction = "click";
			}

			if(mouseAction == "hover"){
				mouseAction = "mouseenter";
			}else{
				mouseAction = "click";
			}

			_tabCache[seed] = {
				tabSrc : _tabSrc,
				tabSrcActiveStyle : _tabSrcActiveStyle,
				tabSrcDeactiveStyle : _tabSrcDeactiveStyle,
				contentSrc : _contentSrc,
				contentActiveSrcStyle : _contentActiveSrcStyle,
				contentDeactiveSrcStyle : _contentDeactiveSrcStyle,
				activeIndex : _activeIndex
			};

			_tabSrc.children().unbind(mouseAction).bind(mouseAction,function(e){
				_toggleContent($(this).index());
			});

			_toggleContent(activeIndex);
			return api;
		}

		/**
		 * 切换标签
		 * @param index 要切换的目标索引
		 * @param func 切换后的回调函数
		 * @param seed 初始化时传入的唯一标识 
		 */
		function switchTab(index,func,seed){
			if(!seed){
				seed = "default";
			}
			_currentSeed = seed;
			if(_tabCache[seed]){
				var _cache = _tabCache[seed];
				_tabSrc = _cache.tabSrc;
				_contentSrc = _cache.contentSrc;
				_tabSrcActiveStyle = _cache.tabSrcActiveStyle;
				_tabSrcDeactiveStyle = _cache.tabSrcDeactiveStyle;
				_contentActiveSrcStyle = _cache.contentActiveSrcStyle;
				_contentDeactiveSrcStyle = _cache.contentDeactiveSrcStyle;
			}
			if(!_tabSrc || !_contentSrc){
				return;
			}
			_toggleContent(index);
			if(func){
				func();
			}
			return api;
		}

		/**
		 * 标签切换
		 */
		function _toggleContent(_activeIndex){
			if(_currentSeed && _tabCache[_currentSeed]){
				_tabCache[_currentSeed].activeIndex = _activeIndex;
			}
			_tabSrc.children().each(function(index,elem){
				if(index==_activeIndex){
					$(this).addClass(_tabSrcActiveStyle).removeClass(_tabSrcDeactiveStyle);
				}else{
					$(this).removeClass(_tabSrcActiveStyle).addClass(_tabSrcDeactiveStyle);
				}
			});
			_contentSrc.children().each(function(index,elem){
				if(index==_activeIndex){
					$(this).addClass(_contentActiveSrcStyle).removeClass(_contentDeactiveSrcStyle);
				}else{
					$(this).removeClass(_contentActiveSrcStyle).addClass(_contentDeactiveSrcStyle);
				}
			});
		}

		return api;
	}
});