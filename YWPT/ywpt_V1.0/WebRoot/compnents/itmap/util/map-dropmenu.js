/**
 * 下拉菜单工具
 * @author ZLT
 * @Date 2013-10-21
 * 工具html结构：
 * <div>
 *    <span>first level</span>
 *    <ul>
 * 		<li>second level</li>
 * 	  </ul>
 * </div>
 * 传入菜单对象：
 * [
 * 	{label:"label",func:"",style:"",childNode:[]}
 * ]
 * label:菜单标签
 * func:单击菜单时回调函数
 * style:指定菜单风格
 * childNode:指定子节点，其格式与父节点格式相同
 */

itmap.util.mapDropDownMenu = (function(){
	return function(){

		/**
		 * 对外接口
		 */
		var api = {
			init : init
		}

		/**
		 * 工具容器
		 */
		var _c = null;

		/**
		 * 主菜单容器
		 */
		var _span = null;

		/**
		 * 下拉菜单容器
		 */
		var _ul = null;

		/**
		 * 初始化
		 */
		function init(data){

			_c = $("#"+data.srcNode);
			_initMenu(data.data);
			_span = _c.find("span");
			_ul = _c.find("ul");
			_initBind(); // 为主菜单添加事件
		}

		/**
		 * 初始化菜单
		 */
		function _initMenu(menu){
			for(var i = 0,len = menu.length;i<len;i++){
				var _div = $("<div></div>").appendTo(_c); // 生成菜单块
				var _menuI = $("<span>"+menu[i].label+"</span>").appendTo(_div); // 添加菜单标题
				var _ul = $("<ul></ul>").appendTo(_div); // 添加菜单下拉列表
				if(menu[i].style){ // 如果存在指定的风格，则添加指定风格
					_ul.addClass(menu[i].style);
				}else{
					_ul.addClass("mapMenuList");
				}
				if(menu[i].childNode){
					// 添加菜单子项
					for(var j = 0,sublen = menu[i].childNode.length;j<sublen;j++){
						
						var _subLi = $("<li>"+menu[i].childNode[j].label+"</li>");
						_subLi.appendTo(_ul);
						if(menu[i].childNode[j].func){
							//menu[i].childNode[j].func();
							_subLi.click(menu[i].childNode[j].func);
						}
					}
				}else{
					if(menu[i].func){
						_menuI.click(menu[i].func);
					}
				}
			}
		}

		/**
		 * 初始化事件
		 */
		function _initBind(){
			
			_span.bind("mouseenter",function(){
				_ul.hide();
				_span.removeClass("activeSpan");
				$(this).addClass("activeSpan");
				_thisUL = $(this).siblings("ul");
				_thisUL.css("marginLeft",$(this).width() - _thisUL.width() + 10);
				_ul.removeClass("activeUL");
				_thisUL.addClass("activeUL");
				_thisUL.show();
			});
			
			_c.children("div").bind("mouseleave",function(){
				_ul.hide();
				_ul.removeClass("activeUL");
				_span.removeClass("activeSpan");
			});
			
			_ul.children("li").bind("click",function(){
				_ul.hide();
				_ul.removeClass("activeUL");
				_span.removeClass("activeSpan");
			}).bind("mouseover",function(){
				$(this).css("color","#cc0033");
			}).bind("mouseout",function(){
				$(this).css("color","#333");
			});

		}

		return api;
	}
})();