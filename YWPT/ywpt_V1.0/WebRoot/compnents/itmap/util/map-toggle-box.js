/**
 * @author ZLT
 * @Date 2013-8-8
 */

itmap.util.mapToggleBox = (function($){
	return function(){

		// 配置参数
		var _conf = {
			srcNode : "", // 连接位置，为块id
			speed : 500, // 动画速度
			ico : false // 是否需要图标
		}

		// 对象接口
		var api = {
			init : init, // 初始化
			open : open  //打开指定索引盒子
		}

		// 父节点
		var _rootContainer = null;

		// 标题栏高度
		var _titleHeight = 30;

		// 标题栏边框高度
		var _titleBorderHeight = 1;
		
		var _isbinded = false;

		function init(conf){
			// 获取配置属性
			if(typeof conf != "undefined"){
				$.extend(_conf,conf);
			}
			
			if(typeof _conf.srcNode == "string"){
				_rootContainer = $("#"+_conf.srcNode);
			}else if(typeof _conf.srcNode == "object"){
				_rootContainer = _conf.srcNode;
			}
			
			_rootContainer.addClass('mapToggleBox');

			// 查找是否有默认的open，如果没有，则默认打开第一个块
			/*if(_rootContainer.children('.mapToolOpen').length){
				_toggleChildren(_rootContainer.children('.mapToolOpen').index());
			}else{
				_toggleChildren(0);
			}*/
			//console.log(conf.srcNode);
			return api;
		}
		
		// 打开指定盒子
		function open(index){
			_toggleChildren(index);
			return api;
		}

		/**
		 * 遍历子节点
		 */
		function _toggleChildren(index){

			var children = _rootContainer.children();

			// class为hold的所有块内容高度总和
			var holdElemHeight = 0;

			// 如果没有子节点则返回
			if(!children.length){
				return;
			}
			
			children.each(function(i,elm){
				var _title = $($(elm).children()[0]);
				var _content = $($(elm).children()[1]);
				if(_conf.ico){
					var _ico = _title.children(".toggleBoxIcon");
					if(!_ico[0]){
						_ico = $("<div class='toggleBoxIcon'></div>").appendTo(_title);
					}
				}
				if(i==index){
					if(_conf.ico){
						if(!_ico.hasClass("toggleBoxCollapse")){
							_ico.addClass("toggleBoxCollapse").removeClass("toggleBoxExpand");
						}
					}
					$(elm).addClass("toggleBoxActive");
					_content.slideDown(_conf.speed);
				}else{
					if(_conf.ico){
						if(!_ico.hasClass("toggleBoxExpand")){
							_ico.addClass("toggleBoxExpand").removeClass("toggleBoxCollapse");
						}
					}
					$(elm).removeClass("toggleBoxActive");
					_content.slideUp(_conf.speed);
				}
			});
			
			if(!_isbinded){
				children.each(function(i,elm){
					$($(elm).children()[0]).click(function(){
						if($(this).parent().hasClass("toggleBoxActive")){
							_toggleChildren(-1);
						}else{
							_toggleChildren($(this).parent().index());
						}
					});
				});
				_isbinded = true;
			}
			
			
			
/*
			// 计算class为hold的块高度总和
			$.each(_rootContainer.children('.mapToolHold'),function(i,elem){
				var holdTarget = $(elem).children('.mapToggleBoxContent')[0];
				holdTarget.style.height = holdTarget.clientHeight + "px";
				holdElemHeight += holdTarget.clientHeight;
			});

			console.log(children);

			targetHeight = _rootContainer.height() - _titleHeight * children.length - _titleBorderHeight * children.length - holdElemHeight;
			console.log("theight:"+targetHeight);

			// 折叠样式修改
			_rootContainer.children('.mapToolOpen').removeClass('mapToolOpen').addClass('mapToolClose');
			$(children[index]).removeClass('mapToolClose').addClass('mapToolOpen');

			// 折叠动画
			_rootContainer.children('.mapToolOpen .mapToggleBoxContent').animate({
				height:targetHeight
			},_conf.speed);

			_rootContainer.children('.mapToolClose .mapToggleBoxContent').animate({
				height:0
			},_conf.speed);

			// 绑定标题点击事件
			_rootContainer.children('.mapToolClose .mapToggleBoxTitle').unbind().bind('click',function(e){
				_toggleChildren($(this).parent('.mapToggleInnerBox').index());
			});
			_rootContainer.children('.mapToolOpen .mapToggleBoxTitle').unbind('click');
*/
		}
		return api;
	}
})($);