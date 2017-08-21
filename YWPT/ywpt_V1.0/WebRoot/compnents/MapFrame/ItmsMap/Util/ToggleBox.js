/**
 * @author ZLT
 * @Date 2013-8-8
 */
MapFactory.Define("ItmsMap/Util/ToggleBox*",function(){
	return function(){

		// 配置参数
		var _conf = {
			srcNode : "", // 连接位置，为块id
			speed : 500, // 动画速度
			ico : false, // 是否需要图标
			uniqueOpen : true
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
						_ico = $("<div class='toggleBoxIcon'></div>");
						_title.prepend(_ico);
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
					if(_conf.uniqueOpen || index == -1){
						if(_conf.ico){
							if(!_ico.hasClass("toggleBoxExpand")){
								_ico.addClass("toggleBoxExpand").removeClass("toggleBoxCollapse");
							}
						}
						$(elm).removeClass("toggleBoxActive");
						_content.slideUp(_conf.speed);
					}
				}
				if(!_isbinded){
					_title.click(function(){
						if($(this).parent().hasClass("toggleBoxActive")){
							if(_conf.ico){
								if(!_ico.hasClass("toggleBoxExpand")){
									_ico.addClass("toggleBoxExpand").removeClass("toggleBoxCollapse");
								}
							}
							$(elm).removeClass("toggleBoxActive");
							_content.slideUp(_conf.speed);
						}else{
							_toggleChildren($(this).parent().index());
						}
					});
				}
			});

			if(!_isbinded){
				_isbinded = true;
			}

			/*if(!_isbinded){
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
			}*/
		}
		return api;
	}
});