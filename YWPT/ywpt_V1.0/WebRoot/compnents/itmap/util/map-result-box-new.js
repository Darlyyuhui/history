/**
 * 结果集盒子 辅助对象
 * 结果条目
 * @param name 结果条目名称，也可以叫label
 * @param func 结果条目点击后的回调函数，若没有，则传空字符串 ""
 * @param attribute 结果条目属性
 * @param data 需要传出的额外数据
 */
var resItemO = function(name,func,attribute,data){
   this.name = name;
   this.func = func;
   this.attribute = attribute;
   this.data = data;
}

/**
 * 结果集盒子
 * @author ZLT
 */
itmap.util.mapResultboxNew = (function(){
	return function(){

		/**
		 * 对外接口
		 */
		var api = {
			init : init, // 初始化
			addContent : addContent, // 添加内容
			addContentAsTable : addContentAsTable, // 将内容添加为表格形式
			addHTML : addHTML, // 添加HTML
			clearBox : clearBox // 清空结果集
		}

		/**
		 * 总容器
		 */
		var _c = null;

		/**
		 * 总容器id 
		 */
		var _srcNode = "";

		/**
		 * 初始化
		 */
		function init(srcNode){
			_c = $("#"+srcNode);
			_srcNode = srcNode;
			return api;
		}

		/**
		 * 添加html页面
		 */
		function addHTML(html,switchtab){
			_c.empty().html(html);
			if(switchtab || typeof switchtab == "undefined"){
				tabSwitch.switchTab(1);
			}
		}

		/**
		 * 添加内容为列表块形式
		 * @param data
		 * + content resItemO[] 传入要解析的内容
		 * + switchtab Boolean 是否要跳转到结果列表
		 * + relation Object 字段映射
		 */
		function addContent(data){
			if(data.switchtab || typeof data.switchtab == "undefined"){
				tabSwitch.switchTab(1);
			}
			var content = data.content;
			if(itmap.util.variableTypes.isArray(content)){
				_c.empty();
				var _cSize = content.length;
				var _innerB = null;
				for(var i = 0,len = content.length;i<len;i++){
					_innerB = $("<div class='mapResItemBox'></div>");
					if(i!=0){
						_innerB.addClass("mapToolClose");
					}
					var _itemTitle = $("<div class='mapResItemTitle'>· "+content[i].name+"</div>");
					if(content[i].func){
						_itemTitle.click((function(_index){
							return function(){
								content[_index].func(content[_index].data);
							}
						})(i));
					}

					_mapToggleBoxContent = $("<div id='mapToggleBoxContent"+i+"'></div>")

					_innerB
					.appendTo(_c)
					.append(_itemTitle)
					.append(_mapToggleBoxContent);

					_attributeList = $("<ul class='mapResItemAttr'></ul>");

					if(data.relation && typeof data.relation == "object"){
						for(var elem in data.relation){
							if(typeof content[i].attribute != "undefined"){
								_attributeList.append("<li><b>"+data.relation[elem]+"</b>:"+content[i].attribute[elem]+"</li>");
							}
						}
					}else{
						for(var elem in content[i].attribute){
							_attributeList.append("<li><b>"+elem+"</b>:"+content[i].attribute[elem]+"</li>");
						}
					}
					_mapToggleBoxContent.append(_attributeList);
				}
				
			}
			_bindToggleBox();
			_autoLimitHeight();
		}

		/**
		 * 添加内容为表格形式
		 * @param data
		 * + content resItemO[] 传入要解析的内容
		 * + switchtab Boolean 是否要跳转到结果列表
		 * + relation Object 字段映射
		 */
		function addContentAsTable(data){
			if(data.switchtab || typeof data.switchtab == "undefined"){
				tabSwitch.switchTab(1);
			}
			if(!data.content || !data.content.length){
				return;
			}
			var content = data.content;
			var resultTable = $("<table class='commonResultTable'></table>");
			var resultTableHead = $("<tr class='commonResultTableTitleRow'></tr>");
			var isRelExist = false;

			// 添加表头
			if(data.relation){
				// 若有relation则用relation
				for(var elem in data.relation){
					resultTableHead.append("<td>"+data.relation[elem]+"</td>");
				}
				isRelExist = true;
			}else{
				// 若没有relation则使用属性原字段
				for(var elem in content[0].attribute){
					resultTableHead.append("<td>"+elem+"</td>");
				}
			}
			resultTable.append(resultTableHead);
			// 添加表内容
			for(var i=0,len=content.length;i<len;i++){
				var contentTr,rows;
				if(i%2){
					contentTr = $("<tr class='commonResultTableEvenRow'></tr>");
				}else{
					contentTr = $("<tr class='commonResultTableOddRow'></tr>");
				}
				rows = isRelExist ? data.relation : content[i].attribute;
				for(var elem in rows){
					contentTr.append("<td>"+content[i].attribute[elem]+"</td>");
				}
				if(content[i].func){
					contentTr[0].onclick = (function(_index){
						return function(){
							content[_index].func(content[_index].attribute);
						}
					})(i);
				}
				resultTable.append(contentTr);
				contentTr = null;
			}
			
			_c.append(resultTable);
			_autoLimitHeight();
		}

		function _autoLimitHeight(){
			// 若内容超过一定高度，则给外层盒子添加滚动条
			var parentHeight = _getParentStyle(_c).height;
			var siblingsHeight = 0;
			var _cTargetHeight = 0;
			setTimeout(function(){
				$.each(_c.siblings(),function(i,item){
					siblingsHeight += $(item).outerHeight();
				});
				_cTargetHeight = parentHeight - siblingsHeight;
				// 当对象为隐藏状态时，获取不到真实offsetHeight，所以要考虑到内
				if(_c[0].offsetHeight > _cTargetHeight || !_c[0].offsetHeight){
					_c.css({
						"width":228,
						"height":_cTargetHeight,
						"overflowY":"scroll"
					});
				}else{
					_c.css({
						"width":"",
						"height":"",
						"overflowY":"hidden",
						"overflow":"hidden"
					});
				}
			},2000);
		}

		/**
		 * 获取对象父元素高度
		 * @param Jquery Object
		 * @return Object{width:"",height""}
		 */
		function _getParentStyle(obj){
			var parent,
				offsetWidth=0,offsetHeight=0;
			parent = obj.parent();
			while(parent){
				offsetWidth = parent.width();
				offsetHeight = parent.height();
				if(offsetWidth && offsetHeight){
					break;
				}else{
					parent = parent.parent();
				}
			}
			return {
				width : offsetWidth,
				height : offsetHeight
			};
		}

		function _bindToggleBox(){
			itmap.util.mapToggleBox().init({srcNode:_srcNode}).open(-1);
		}

		function clearBox(){
			_c.empty();
			return api;
		}

		return api;
	}
})()