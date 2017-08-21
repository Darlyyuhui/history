/**
 * 开源地图动态图层管理器（TOC）
 * @author ZLT
 * @since 2014-02-17
 **/

itmap.openlayer.TOC = function(){
	this._mapC = map;
	this._conf = {
		src : "", // 指定TOC容器，传入ID
		layerName : {}, // 需要映射的图层名称，若没有映射，则按id显示
		group : [], // 分组设置，不设置则默认分到一组当中,{label:String,layers:[String],isAllowReorder:Boolean},label:分组名称，layers:每组中的图层ID,isAllowReorder:是否允许进行拖拽排序
		excludeLayer : [] // 用来设置不显示的图层，以数组形式存储图层ID
	}
	this._controlerBox = null; // 总容器
	this._container = []; // 子容器
	this._isGroupFlag = false; // 是否进行分组
	this._excludeLayer = []; // 不显示在toc中的Layer
	this._layerOrder = []; // 初始化的图层顺序
}

itmap.openlayer.TOC.prototype = {

	/**
	 * 初始化
	 */
	init : function(conf){
		// 给地图添加监听事件
		var _instance = this; 
		map.events.register("addlayer",this._mapC,function(event){
			_instance.addLayer(event.layer);
		});

		map.events.register("removelayer",this._mapC,function(event){
			_instance.removeLayer(event.layer);
		});
		// 配置参数
		this._config(conf);
		// 初始化装载容器
		this._initContainer();
		// 获取不需要添加的Layer
		this._excludeLayer = this._excludeLayer.concat(this._conf.excludeLayer);
		// 初始化时载入已经加入的图层
		this._getLayerList();
	},

	/**
	 * 参数配置
	 */
	_config : function(confObj){
		// 设置参数
		if( typeof confObj != "undefined") {
			for(elem in this._conf) {
				if( typeof confObj[elem] != "undefined"){ //&& confObj[elem] !== "") {
					this._conf[elem] = confObj[elem];
				}
			}
			confObj = null;
		}
	},

	/**
	 * 初始化管理器容器
	 */
	_initContainer : function(){
		if(!this._conf.src){
			return false;
		}
		var _layerContainer = null;
		this._controlerBox =  $("#"+this._conf.src);
		this._isGroup();
		if(this._isGroupFlag){
			for(var i=0;i<this._conf.group.length;i++){
				_layerContainer = $("<div></div>");
				// 加上分组名称，若该分组可以图层排序，则在名称后加*标示
				_layerContainer.append("<i>"+this._conf.group[i].label+(this._conf.group[i] && this._conf.group[i].isAllowReorder ? "<font color=red> *</font>" : "")+"</i>");
				_layerContainer.append("<ul class='mapLayersControler' id='layerGroup_"+i+"'></ul>");
				_layerContainer.hide();
				this._container.push(_layerContainer);
				this._controlerBox.append(_layerContainer);
			}
		}else{
			this._container.push($("<div><ul class='mapLayersControler'></ul></div>"));
			this._controlerBox.append(this._container[0]);
		}
	},

	/**
	 * 判断是否需要分组
	 */
	_isGroup : function(){
		this._conf.group && itmap.util.variableTypes.isArray(this._conf.group) && this._conf.group.length ? this._isGroupFlag = true : "";
	},

	/**
	 * 是否在数组中
	 */
	_inArray : function(item,arr){
		return eval("\/\\b"+item+"\\b\/g").test(arr.toString());
	},

	/**
	 * 添加图层条目
	 */
	_addItem : function(id){

		if(!this._container[0]){
			return false;
		}
		if(this._inArray(id,this._excludeLayer)){
			return false;
		}
		// 设置图层存放位置
		var _idPos = -1;
		if(this._isGroupFlag){
			for(var i=0;i<this._conf.group.length;i++){
				if(this._inArray(id, this._conf.group[i].layers)){
					_idPos = i;
					break;
				}
			}
		}

		var _name = this._conf.layerName[id] ? this._conf.layerName[id] : id;
		_name = _name.substr(0,14);
		var _layer = this._mapC.getLayer(id);
		var _checked = _layer.visibility ? "checked='checked'" : "";
		var _item = $("<li><span class='checkbox'><input type='checkbox' "+_checked+" value='"+id+"'/></span><span class='checkboxText'>"+_name+"</span></li>");
		var _instance = this;
		var itemContainer = null;

		// 如果没有存放位置，则存放到第一个容器中,如果有存放位置，则存放到指定的容器中
		_idPos == -1 ? _idPos = 0 : "";

		itemContainer = this._container[_idPos].find("ul");

		// 判断该组是否允许拖拽重排序
		if(this._conf.group[_idPos] && this._conf.group[_idPos].isAllowReorder){
			_item.addClass("MapDragAbleTarget_toc");
			itmap.util.mapDragAble().target(_item[0],{
				seed : "toc",
				isCapture : true,
				captureArea : itemContainer,
				mouseDownCallFunc : function(){_instance._layerOrder = _instance._getLayerOrder(_idPos);},
				mouseUpCallFunc : function(){_instance._reorderLayer(_idPos);}
			});
		}

		// 若容器为隐藏状态，则将其显示
		if(this._container[_idPos].css("display") == "none"){
			this._container[_idPos].show();
		}

		// 将新生成的对象存储至指定容器当中
		if(itemContainer.children().length){
			_item.insertBefore(itemContainer.children().eq(0));
		}else{
			_item.appendTo(itemContainer);
		}

		// 给checkbox添加点击事件
		_item.children("span:eq(0)").children("input").mousedown(function(){
			$(this).attr("checked") ? _layer.display(false) : _layer.display(true);
			return false;
		});
	},

	/**
	 * 移除图层条目
	 */
	_removeItem : function(id){
		var item = this._controlerBox.find("input[value='"+id+"']").parent("span").parent("li");
		if(!item.siblings("li").length){
			item.parent().parent().hide();
		}
		item.remove();
	},

	/**
	 * 获取图层列表
	 */
	_getLayerList : function(){
		var _addedLayers = this._mapC.layers;
		for(var i=0,len=_addedLayers.length;i<len;i++) this._addItem(_addedLayers[i].id);
	},

	/**
	 * 获取当前组的图层顺序
	 */
	_getLayerOrder : function(groupIndex){
		var _layerIds = [];
		$("#layerGroup_"+groupIndex).find("input").each(function(i,item){
			_layerIds.push($(item).val());
		});
		return _layerIds;
	},

	/**
	 * 对当前组的图层重排序
	 */
	_reorderLayer : function(groupIndex){
		var _instance = this;
		var _newLayerOrder = _instance._getLayerOrder(groupIndex);
		if(_instance._layerOrder.join() != _newLayerOrder.join()){
			var size = _newLayerOrder.length;
			var len = _newLayerOrder.length;
			while(size--){
				this._mapC.setLayerIndex(this._mapC.getLayer(_newLayerOrder[size]),groupIndex*100+len-size);
			}
		}
	},

	/**
	 * 添加图层
	 */
	addLayer : function(layer){
		if(!(layer instanceof OpenLayers.Layer)){
			return false;
		}
		this._addItem(layer.id);
	},

	/**
	 * 移除图层
	 */
	removeLayer : function(layer){
		if(!(layer instanceof OpenLayers.Layer)){
			return false;
		}
		this._removeItem(layer.id);
	}
}