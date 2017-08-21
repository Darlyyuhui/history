MapFactory.Define("ItmsMap/Util/ResultBox*",[
    "ItmsMap/Util/ToggleBox*",
    "ItmsMap/Util/Tab*",
    "ItmsMap/Util/MapChart*"
],function(toggleBox,tab,MapChart){
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
		};

		/**
		 * 总容器
		 */
		var _c = null;

		/**
		 * 总容器id 
		 */
		var _srcNode = "";

		/**
		 * 统计结果对象
		 */
		var _statisticRes = {};

		/**
		 * 统计图id
		 */
		var _statisticID = "mapOther";

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
				tab().switchTab(1,null,"menuListTab");
			}
		}

		/**
		 * 添加内容为列表块形式
		 * @param data
		 * + content resItemO[] 传入要解析的内容
		 * + switchtab Boolean 是否要跳转到结果列表
		 * + relation Object 字段映射
		 * + isAddChart Boolean 是否添加统计图，默认为false
		 * + targetField String 要统计的字段
		 */
		function addContent(data){
			if(data.switchtab || typeof data.switchtab == "undefined"){
				tab().switchTab(1,null,"menuListTab");
			}
			var content = data.content;
			if(MapFactory.VariableTypes.isArray(content)){
				$("#"+_statisticID).empty();
				_c.empty();
				var _cSize = content.length;
				var _innerB = null;
				if(!_cSize){
					addHTML("<div style='width:100%;line-height:30px;text-align:center;'>没有数据</div>",true);
					return;
				}
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
					_itemTitle.click(function(){
						$(".mapResItemTitle").removeClass("selectedTitle");
						$(this).addClass("selectedTitle");
					});

					_mapToggleBoxContent = $("<div id='mapToggleBoxContent"+i+"' style='display:none;'></div>")

					_innerB
					.appendTo(_c)
					.append(_itemTitle)
					.append(_mapToggleBoxContent);

					_attributeList = $("<ul class='mapResItemAttr'></ul>");

					if(data.relation && typeof data.relation == "object"){
						for(var elem in data.relation){
							if(typeof content[i].attribute != "undefined" && typeof content[i].attribute[elem] != "undefined"){
								_attributeList.append("<li><b>"+data.relation[elem]+"</b>:"+content[i].attribute[elem]+"</li>");
							}
						}
					}else{
						for(var elem in content[i].attribute){
							_attributeList.append("<li><b>"+elem+"</b>:"+content[i].attribute[elem]+"</li>");
						}
					}
					if(data.isAddChart && data.targetField){
						_statistic(content[i].attribute[data.targetField]);
					}
					_mapToggleBoxContent.append(_attributeList);
				}
				if(data.isAddChart && data.targetField){
					_setChart();
				}
			}else{
				addHTML("<div style='width:100%;line-height:30px;text-align:center;'>没有数据</div>",true);
				return;
			}
			//_bindToggleBox();
			_autoLimitHeight();
		}

		/**
		 * 添加内容为表格形式
		 * @param data
		 * + content resItemO[] 传入要解析的内容
		 * + switchtab Boolean 是否要跳转到结果列表
		 * + relation Object 字段映射
		 * + colsWidth Array 每一列宽度
		 */
		function addContentAsTable(data){
			$("#"+_statisticID).empty();
			_c.empty();
			if(data.switchtab || typeof data.switchtab == "undefined"){
				tab().switchTab(1,null,"menuListTab");
			}
			if(!data.content || !data.content.length){
				return;
			}
			var content = data.content;
			var resultTable = $("<table class='commonResultTable commonActiveTable'></table>");
			var resultTableHead = $("<tr class='commonResultTableTitleRow'></tr>");
			var isRelExist = false;
			var colsWidth = [];

			if(data.colsWidth && data.colsWidth.length){
				colsWidth = data.colsWidth;
			}

			if(!content.length){
				addHTML("<div style='width:100%;line-height:30px;text-align:center;'>没有数据</div>",true);
				return;
			}

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
				var contentTr,rows,col=0;
				/*if(i%2){
					contentTr = $("<tr class='commonResultTableEvenRow'></tr>");
				}else{
					contentTr = $("<tr class='commonResultTableOddRow'></tr>");
				}*/
				contentTr = $("<tr></tr>");
				rows = isRelExist ? data.relation : content[i].attribute;
				for(var elem in rows){
					var style = "";
					if(col < colsWidth.length){
						style += "width:"+colsWidth[col]+"px";
					}
					contentTr.append("<td style='"+style+"'>"+content[i].attribute[elem]+"</td>");
					col++;
				}
				if(content[i].func){
					contentTr[0].onclick = (function(_index){
						return function(){
							content[_index].func(content[_index].attribute);
						}
					})(i);
				}
				resultTable.append(contentTr);
				contentTr.mouseover(function(){
					$(this).addClass("rowHover");
				}).mouseout(function(){
					$(this).removeClass("rowHover");
				}).click(function(){
					$.each($(this).siblings(),function(i,item){
						$(item).children("td").removeClass("tdSelected");
					});
					$(this).children("td").addClass("tdSelected");
				});
				contentTr = null;
			}
			_c.append(resultTable);
			_autoLimitHeight();
		}

		function _statistic(fieldValue){
			if(!fieldValue){
				return;
			}
			if(_statisticRes[fieldValue]){
				_statisticRes[fieldValue].total++;
			}else{
				_statisticRes[fieldValue] = {};
				_statisticRes[fieldValue].total = 1;
			}
		}

		function _setChart(){
			var _res = [];
			for(var elem in _statisticRes){
				_res.push({
					name : elem,
					total : _statisticRes[elem].total
				});
			}
			MapChart().init({
				src : _statisticID,
				data : _res
			});
		}

		function _autoLimitHeight(){
			setTimeout(function(){
				// 若内容超过一定高度，则给外层盒子添加滚动条
				var parentHeight = _getParentStyle(_c).height;
				var siblingsHeight = 0;
				var _cTargetHeight = 0;
				$.each(_c.siblings(),function(i,item){
					siblingsHeight += $(item).outerHeight();
				});
				_cTargetHeight = parentHeight - siblingsHeight - 30;
				var _actualHeight = 0; 
				$.each(_c.children(),function(i,item){
					_actualHeight += $(item).outerHeight();
				});

				// 当对象为隐藏状态时，获取不到真实offsetHeight，所以要考虑到内
				if(_actualHeight+30 > _cTargetHeight || !_c[0].offsetHeight){
					_c.css({
						"width":238,
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
			},1000);
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
			toggleBox().init({srcNode:_srcNode}).open(-1);
		}

		function clearBox(){
			_c.empty();
			$("#"+_statisticID).empty();
			return api;
		}

		return api;
	}
});