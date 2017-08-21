/**
 * 选项卡工具
 * @Author ZLT
 * @Date 2013-9-12
 **/
itmap.util.MapTabTool = (function(){
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
		
		/**
		 * 初始化
		 * @param tabSrc
		 * 	+ src tab的容器id
		 *  + style tab的自定义风格，若不定义则按照默认的来
		 *    + active 活动的标签风格
		 *    + deactive 不活动的标签风格
		 * @param contentSrc
		 *  + src 内容部分的id
		 *  + style contentSrc的自定义风格，若不定义则按照默认的来
		 *    + active 活动的标签风格
		 *    + deactive 不活动的标签风格
		 * @param activeIndex 显示的tab索引
		 */
		function init(tabSrc,contentSrc,activeIndex){
			_tabSrc = $("#"+tabSrc.src);
			if(tabSrc.style){
				_tabSrcActiveStyle = tabSrc.style.active;
				_tabSrcDeactiveStyle = tabSrc.style.deactive;
			}
			_contentSrc = $("#"+contentSrc.src);
			if(contentSrc.style){
				_contentActiveSrcStyle = contentSrc.style.active;
				_contentDeactiveSrcStyle = contentSrc.style.deactive;
			}
			_activeIndex = activeIndex;
			_tabSrc.children().bind("click",function(e){
				_toggleContent($(this).index());
			});
			_toggleContent(activeIndex);
			return api;
		}

		/**
		 * 切换标签
		 * @param index 要切换的目标索引
		 * @param func 切换后的回调函数 
		 */
		function switchTab(index,func){
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
			_tabSrc.children().each(function(index,elem){
				if(index==_activeIndex){
					$(this).addClass("mapActiveTab");
					if(_tabSrcActiveStyle){
						$(this).addClass(_tabSrcActiveStyle);
					}
					if(_tabSrcDeactiveStyle){
						$(this).removeClass(_tabSrcDeactiveStyle);
					}
				}else{
					$(this).removeClass("mapActiveTab");
					if(_tabSrcDeactiveStyle){
						$(this).addClass(_tabSrcDeactiveStyle);
					}
					if(_tabSrcActiveStyle){
						$(this).removeClass(_tabSrcActiveStyle);
					}
				}
			});
			_contentSrc.children().each(function(index,elem){
				if(index==_activeIndex){
					if(_contentActiveSrcStyle){
						$(this).addClass(_contentActiveSrcStyle);
					}
					if(_contentDeactiveSrcStyle){
						$(this).removeClass(_contentDeactiveSrcStyle);
					}
					$(this).addClass("mapActiveContent").removeClass("mapDeactiveContent");
				}else{
					if(_contentActiveSrcStyle){
						$(this).removeClass(_contentActiveSrcStyle);
					}
					if(_contentDeactiveSrcStyle){
						$(this).addClass(_contentDeactiveSrcStyle);
					}
					$(this).removeClass("mapActiveContent").addClass("mapDeactiveContent");
				}
			});
		}

		return api;
	}
})();