/**
 * 动态图层管理器（TOC）
 * @author ZLT
 * @since 2013-11-26
 */
MapFactory.Define("MapFactory/Util/Toc*",[
    "MapFactory/MapManager",
    "MapFactory/LayerManager",
    "MapFactory/Util/DragAble*",
    "MapFactory/Util/Dialog*"
],function(mapManager,layerManager,dragAble,dialogClass){
	return function(){
		var _mapManager = mapManager(),
			_tocPanel = dialogClass({
				mutiDialog : true,
				mutiDialogSeed : "MAPTOC",
				mask : false,
				title : '图层',
				right : 10,
				top : 35,
				buttonDisplay : {
					confirmButton : false,
					cancelButton : false
				}
			}),
			_checkBoxLock = false;
		var _conf = {
			src : "", // 指定TOC容器，传入ID
			layers : {},
			group : [], // 分组设置，不设置则默认分到一组当中,{label:String,layers:[String],isAllowReorder:Boolean},label:分组名称，layers:每组中的图层ID,isAllowReorder:是否允许进行拖拽排序
			excludeLayer : [] // 用来设置不显示的图层，以数组形式存储图层ID
		};

		var _controlerBox = null, // 总容器
			_container = [], // 子容器
			_isGroupFlag = false, // 是否进行分组
			_excludeLayer = [], // 不显示在toc中的Layer
			_layerOrder = []; // 初始化的图层顺序

		/**
		 * 是否在数组中
		 */
		var _inArray = function(item,arr){
			return eval("\/\\b"+item+"\\b\/g").test(arr.toString());
		}

		/**
		 * 判断是否需要分组
		 */
		var _isGroup = function(){
			_conf.group && MapFactory.VariableTypes.isArray(_conf.group) && _conf.group.length ? _isGroupFlag = true : "";
		}

		/**
		 * 初始化管理器容器
		 */
		var _initContainer = function(){
			var _src = _conf.src;
			if(!_src){
				return false;
			}
			var _contentStr = "<div id='"+_src+"' style='width:180px;overflow-x:hidden;'></div>";
			_tocPanel.setDialogContent(_contentStr).show();
			var _layerContainer = null;
			_controlerBox = $("#"+_src);
			_isGroup();
			if(_isGroupFlag){
				for(var i=0;i<_conf.group.length;i++){
					_layerContainer = $("<div class='layerGroupBox'></div>");
					// 加上分组名称，若该分组可以图层排序，则在名称后加*标示
					_layerContainer.append("<div class='layerGroupTitle'><div class='layerGroupCollapse groupUnCollapse'></div><span class='checkbox'><input type='checkbox'/></span><span class='checkboxText'><b><i>"+_conf.group[i].label+(_conf.group[i] && _conf.group[i].isAllowReorder ? "<font color=red> *</font>" : "")+"</i></b></span></div><div style='clear:both'></div>");
					_layerContainer.append("<ul class='mapLayersControler' id='layerGroup_"+i+"'></ul>");
					_layerContainer.hide();
					_container.push(_layerContainer);
					_controlerBox.append(_layerContainer);
				}
				// 为分组加上图层组显示隐藏事件
				$(".layerGroupTitle span").find("input").mouseup(function(){
					var index = $(this).parent().parent().parent().index();
					_showOrHideGroup(index);
				});
				$(".layerGroupCollapse").click(function(){
					if($(this).hasClass("groupUnCollapse")){
						$(this).parent().siblings("ul").slideUp();
						$(this).removeClass("groupUnCollapse").addClass("groupCollapse");
					}else{
						$(this).parent().siblings("ul").slideDown();
						$(this).removeClass("groupCollapse").addClass("groupUnCollapse");
					}
				});
			}else{
				_container.push($("<ul class='mapLayersControler'></ul>"));
				_controlerBox.append(_container[0]);
			}
		}

		/**
		 * 添加图层条目
		 */
		this._addItem = function(id){
			
			if(!_container[0]){
				return false;
			}
			if(_inArray(id,_excludeLayer)){
				return false;
			}

			var _currentLayerMGM = layerManager(id);

			// 设置图层存放位置
			var _idPos = -1;
			if(_isGroupFlag){
				var _defaultGroupIndex = -1;
				for(var i=0;i<_conf.group.length;i++){
					if(_conf.group[i].defaultGroup){
						_defaultGroupIndex = i;
					}
					if(_inArray(id, _conf.group[i].layers)){
						_idPos = i;
						break;
					}
				}
				if(_defaultGroupIndex != -1 && _idPos == -1){
					_idPos = _defaultGroupIndex;
				}
			}

			var _layers = _conf.layers;
			var _layer = _layers[id];
			var _isTop = false;
			var _name = _layers[id] ? (_layers[id].name ? _layers[id].name : id) : id;
			var _shortname = _name.substr(0,14);
			var _checked = _currentLayerMGM.isVisible() ? "checked='checked'" : "";
			var _item = $("<li><span class='checkbox'><input type='checkbox' "+_checked+" value='"+id+"'/></span><span class='checkboxText' alt='"+_name+"'>"+_shortname+"</span></li>");
			var _instance = this;
			var itemContainer = null;

			if(_layer && _layer.isTop){
				_isTop = true;
			}

			// 添加图层可见性改变触发事件
			_currentLayerMGM.addVisibleChangeEvent((function(layerItem){
				return function(visibleFlag){
					if(_checkBoxLock){
						return;
					}
					var _layerItemChecked = false;
					if(visibleFlag){
						_layerItemChecked = true;
					}
					$(layerItem.find("input:checkbox")).attr("checked",_layerItemChecked);
				}
			})(_item));

			// 如果没有存放位置，则存放到第一个容器中,如果有存放位置，则存放到指定的容器中
			_idPos == -1 ? _idPos = 0 : "";

			itemContainer = _container[_idPos].find("ul");

			// 判断该组是否允许拖拽重排序
			if(_conf.group[_idPos] && _conf.group[_idPos].isAllowReorder){
				var _targetCaptureIndex = -1;
				_item.addClass("MapDragAbleTarget_toc");
				var _dragOption = {
					seed : "toc",
					isCapture : true,
					captureArea : itemContainer
				};
				_dragOption["mouseDownCallFunc"] =  function(){
					_layerOrder = _instance._getLayerOrder(_idPos);
				};
				_dragOption["mouseUpCallFunc"] = function(e){
					if(_targetCaptureIndex < 0){
						return;
					}
					var _currentO = $(e.object),
						_currentIndex = _currentO.index(),
						_children = itemContainer.children();
					_children.css("backgroundColor","");
					if(_targetCaptureIndex == _currentIndex){
						return;
					}
					if(_targetCaptureIndex > _currentIndex){
						_currentO.insertAfter(_children.eq(_targetCaptureIndex));
					}else{
						_currentO.insertBefore(_children.eq(_targetCaptureIndex));
					}
					//_instance._reorderLayer(_idPos);
					_instance._layersReorder();
					_targetCaptureIndex = -1;
				};
				_dragOption["captureCallFunc"] = function(e){
					_targetCaptureIndex = e.index;
					itemContainer.children().css("backgroundColor","").eq(_targetCaptureIndex).css("backgroundColor","#B0D8FF");
				};
				dragAble().target(_item[0],_dragOption);
			}

			// 若容器为隐藏状态，则将其显示
			if(_container[_idPos].css("display") == "none"){
				_container[_idPos].show();
			}

			_item.attr("isTop",_isTop);

			// 将新生成的对象存储至指定容器当中
			var _itemContainerChildren = itemContainer.children();
			if(_itemContainerChildren.length){
				var _insertIndex = 0;
				_itemContainerChildren.each(function(i,item){
					if($(this).attr("isTop") == "true"){
						_insertIndex++;
					}
				});
				var _tarItem = _itemContainerChildren.eq(_insertIndex);
				if(_tarItem[0]){
					_item.insertBefore(_tarItem);
				}else{
					_tarItem = _itemContainerChildren.eq(_insertIndex-1);
					_item.insertAfter(_tarItem);
				}
			}else{
				if(_isTop){
					_item.prependTo(itemContainer);
				}else{
					_item.appendTo(itemContainer);
				}
			}

			if(!_layers[id] || (_layers[id].allowOperation != false)){
				$("<span class='moveLayerToTop operationBtn'></span>").appendTo(_item).mousedown(function(){
					var _parent = $(this).parent();
					_parent.prependTo(_parent.parent());
					//_instance._reorderLayer(_idPos);
					_instance._layersReorder();
					$(".operationBtn").hide();
				});

				$("<span class='removeLayer operationBtn'></span>").appendTo(_item).click(function(){
					$(this).parent().remove();
					_currentLayerMGM.removeFromMap();
					if(_isGroupFlag){
						_instance._resetGroupCheckbox(_idPos);
					}
				});
			}

			// 给checkbox添加点击事件
			_item.children("span:eq(0)").children("input").mousedown(function(){
				_checkBoxLock = true;
				$(this).attr("checked") ? _hideLayer(id) : _showLayer(id);
				return false;
			}).mouseup(function(){
				_checkBoxLock = false;
				return false;
			}).click(function(){
				if(_isGroupFlag){
					_instance._resetGroupCheckbox(_idPos);
				}
			});

			_item.mouseenter(function(){
				$(this).children(".operationBtn").show();
			}).mouseleave(function(){
				$(this).children(".operationBtn").hide();
			});

			if(_isGroupFlag){
				this._resetGroupCheckbox(_idPos);
			}

			//_instance._reorderLayer(_idPos);
			_instance._layersReorder();
		}

		/**
		 * 移除图层条目
		 */
		this._removeItem = function(id){
			var item = _controlerBox.find("input[value='"+id+"']").parent("span").parent("li"),
				container = item.parent().parent();

			if(!item.siblings("li").length){
				container.hide();
			}

			this._resetGroupCheckbox(container.index());

			item.remove();
		}

		/**
		 * 隐藏图层
		 */
		var _hideLayer = function(layerid){
			layerManager(layerid).hide();
		}

		/**
		 * 显示图层
		 */
		var _showLayer = function(layerid){
			layerManager(layerid).show();
		}

		/**
		 * 根据情况重置组checkbox的状态
		 */
		this._resetGroupCheckbox = function(index){
			var _groups = $(".layerGroupBox").eq(index);
			_groups.each(function(index,item){
				var _itemCheckboxs = $(item).find("input:checkbox:gt(0)"),
					_groupCheckbox = $(item).find("input:checkbox:eq(0)"),
					_checkFlag = false;
				_itemCheckboxs.each(function(_cbIndex,_checkbox){
					if($(_checkbox).attr("checked")){
						_checkFlag = true;
						return false;
					}
				});
				_groupCheckbox.attr("checked",_checkFlag);
			});
		}

		/**
		 * 显示或隐藏图层组
		 */
		var _showOrHideGroup = function(index){
			var _group = $(".layerGroupBox").eq(index),
				_checkboxs = _group.find("input:checkbox"),
				_hiddenInput = _group.find("input:hidden"),
				_itemCheckboxs = _group.find("input:checkbox:gt(0)"),
				_groupCheckbox = _group.find("input:checkbox:eq(0)"),
				_groupCheck = _groupCheckbox.attr("checked");
			_itemCheckboxs.attr("checked",_groupCheck ? false : true);
			_itemCheckboxs.each(function(i,item){
				var _layerid = $(item).val();
				_groupCheck ? _hideLayer(_layerid) : _showLayer(_layerid);
			});
		}

		/**
		 * 获取图层列表
		 */
		this._getLayerList = function(){
			var _layersArr = _mapManager.getAllLayersID();
			for(var i = 0;i<_layersArr.length;i++) this._addItem(_layersArr[i]);
		}

		/**
		 * 获取当前组的图层顺序
		 */
		this._getLayerOrder = function(groupIndex){
			var _layerIds = [];
			$("#layerGroup_"+groupIndex).find("input").each(function(i,item){
				var id = $(item).val();
				_layerIds.push(id);
			});
			return _layerIds;
		}

		/**
		 * 对当前组的图层重排序
		 */
		this._reorderLayer = function(groupIndex){
			var _instance = this;
			var _newLayerOrder = _instance._getLayerOrder(groupIndex);
			if(_layerOrder.join() != _newLayerOrder.join()){
				var size = _newLayerOrder.length;
				var len = _newLayerOrder.length;
				while(size--){
					var index = (100 - groupIndex)*100+len-size;
					if(!_newLayerOrder[size]){
						continue;
					}
					_mapManager.reorderLayer(_newLayerOrder[size],(100 - groupIndex)*100+len-size);
				}
			}
		}

		this._layersReorder = function(){
			var _newLayerOrder = [];
			var _instance = this;
			_controlerBox.find("input:checkbox").each(function(i,item){
				var _layerid = $(item).val();
				if(_layerid && _layerid!="on"){
					_newLayerOrder.push(_layerid);
				}
			});
			var size = _newLayerOrder.length,
				len = size;
			while(size--){
				_mapManager.reorderLayer(_newLayerOrder[size],len-size);
			}
		}

		/**
		 * 添加图层
		 */
		this.addLayer = function(layerid){
			this._addItem(layerid);
		}

		/**
		 * 移除图层
		 */
		this.removeLayer = function(layerid){
			this._removeItem(layerid);
		}

		/**
		 * 图层管理器显示
		 */
		this.show = function(){
			_tocPanel.show();
		}

		/**
		 * 初始化
		 */
		this.init = function(conf){
			// 给地图添加监听事件
			var _instance = this;
			_mapManager.setLayerAddEvent(function(layerid){
				_instance.addLayer(layerid);
			});
			_mapManager.setLayerRemoveEvent(function(layerid){
				_instance.removeLayer(layerid);
			});
			// 配置参数
			MapFactory.Extend(_conf,conf);
			// 初始化装载容器
			_initContainer();
			// 获取不需要添加的Layer
			_excludeLayer = _excludeLayer.concat(_conf.excludeLayer);
			// 初始化时载入已经加入的图层
			this._getLayerList();
			// 对图层顺序进行初始化
			/*for(var i=0;i<_conf.group.length;i++){
				this._reorderLayer(i);
			}*/
			this._layersReorder();
		}
	}
});