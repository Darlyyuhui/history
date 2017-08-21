/**
 * @author ZLT
 * @Date 2013-9-2
 */
MapFactory.Define("ItmsMap/Util/Tip*",function(){
	return function(){

		/**
		 * 接口
		 */
		var api = {
			setContent : setContent,
			show : show,
			hide : hide
		}

		/**
		 * 初始化
		 */
		function _init(){
			_initTipBox();
		}

		/**
		 * 初始化tipbox
		 */
		function _initTipBox(){
			if($("#mapTipBox")[0] == null){
				$("body").append("<div id='mapTipBox'></div>");
			}
		}

		/**
		 * 设置位置
		 */
		function _setPosition(){
			var left = ($("body").width() - $("#mapTipBox").width()+ $("#left").width())/2;
			$("#mapTipBox").css({
				left : left,
				top : 100 + $("body").scrollTop()
			});
		}

		/**
		 * 设置内容
		 */
		function setContent(content){
			_init();
			$("#mapTipBox").html(content);
			return api;
		}

		/**
		 * 显示事件，单位为毫秒
		 */
		function show(time){
			_setPosition();
			$("#mapTipBox").fadeIn("slow");
			if(typeof time != "undefined" && time != ""){
				setTimeout(hide,time);
			}
		}

		/**
		 * 隐藏
		 */
		function hide(){
			$("#mapTipBox").fadeOut();
		}

		return api;
	}
});