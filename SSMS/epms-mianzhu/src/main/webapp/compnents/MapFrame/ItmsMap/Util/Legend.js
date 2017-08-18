/**
 * 图例工具
 * @author ZLT
 * @since 2014-6-17
 */
MapFactory.Define("ItmsMap/Util/Legend*",[
    "ItmsMap/SymbolConfig*",
    "MapFactory/Util/Dialog*",
    "ItmsMap/ModuleConfig*"
],function(SymbolConfig,Dialog,ModuleConfig){

	var api = {
        setConf: setConf,
        addModule: addModule,
		setIncoInfo:setIncoInfo
	   };

	var _legendConfig = {
		"reserve" : {
			items: ["waterSymbol","natureSymbol"],
			name: "保护区"
		},
        "diggings" : {
            items: ["ndSymbol"],
            name: "矿区"
        },
        "enter" : {
            items: ["enterStartSymbol","enterStopSymbol","enterPauseSymbol"],
            name: "企业"
        },
        "lamp" : {
            items: ["lampOnlineSymbol","lampOfflineSymbol","lampUnconnectedSymbol"],
            name: "油烟"
        },
        "nd" : {
            items: ["ndOnlineSymbol","ndOfflineSymbol","ndUnconnectedSymbol"],
            name: "声尘"
        },
        "video" : {
            items: ["videoOnlineSymbol","videoOfflineSymbol","videoUnconnectedSymbol"],
            name: "视频"
        }
	};

	/**
	 * 根据当前用户及数据库跟新挖占专题图图标---胡红勋添加 2014-9-23
	 */
	
	function setIncoInfo(iconinfos){		
		var items=[]//
		for(var i in iconinfos){
			var iconinfo=iconinfos[i];			
			items.push(iconinfo.id);			
		}
		_legendConfig.wazhan.items=items;		
		
	 }
	
	
	/**
	 * 存储已经打开的模块的ID
	 */
	var _moduleIds = [];

	/**
	 * 存储已经打开的模块的ID
	 */
	var _moduleNames = [];

	var _containerId = "legendContainer",
		_containerHeadId = "legendContainerHead",
		_containerContentId = "legendContaienrContent";

	var _dialog = null;

	/**
	 * 当前可见的模块图例索引
	 */
	var _currentIndex = -1;

	var _modulesNum = 0;

    var _conf = {
        mapDiv: "",
        left: 8,
        bottom: 10
    };

    function setConf(conf){
        MapFactory.Extend(_conf,conf);
    }

	function _initContainer(){
		_dialog = Dialog({
            mapDiv: _conf.mapDiv,
			title: "图例",
            isMove : true,
			mutiDialog: true,
			mutiDialogSeed: "mapLegend",
			mask: false,
            bottom: _conf.bottom,
			left: _conf.left,
			content: "<div id='"+_containerId+"' style='width:250px;height:170px;'>" +
						"<div id='"+_containerHeadId+"'></div>" +
						"<div id='"+_containerContentId+"'></div>" +
					 "</div>",
			buttonDisplay: {
				"confirmButton": false,
				"cancelButton": false
			}
		}).show();
         document.getElementById("DialogBoxTitle_mapLegend").style.display = "none";
	}

	function _initHead(){
		if(_currentIndex<0){
			return;
		}
		var _containerHead = document.getElementById(_containerHeadId),
			_preBtn = document.createElement("div"),
			_nextBtn = document.createElement("div"),
			_moduleTitle = document.createElement("div");
		_preBtn.id = "LegendPreBtn";
		_preBtn.className = "LegendOperationBtn";
		_nextBtn.id = "LegendNextBtn";
		_nextBtn.className = "LegendOperationBtn";
		_moduleTitle.id = "LegendModuleTitle";
		_containerHead.appendChild(_preBtn);
		_containerHead.appendChild(_moduleTitle);
		_containerHead.appendChild(_nextBtn);
		_moduleTitle.innerHTML = _moduleNames[_currentIndex];
		_preBtn.onclick = function(){
			if(_currentIndex <= 0){
				return;
			}
			_currentIndex=_currentIndex-1;
			_moduleVisible(_currentIndex);
			_changeTitle();
		}
		_nextBtn.onclick = function(){
			if(_currentIndex >= _modulesNum - 1){
				return;
			}
			_currentIndex=_currentIndex+1;
			_moduleVisible(_currentIndex);
			_changeTitle();
		}
	}

	
	//模块图例切换-------
	function _moduleVisible(index){
		var _containerContent = document.getElementById(_containerContentId),
			_children = _containerContent.childNodes;
		if(index < 0 || index >= _modulesNum){
			return;
		}
		for(var i=0;i<_modulesNum;i=i+1){
			_children[i].style.display = (i==index) ? "block" : "none";
		}
	}

	
	//图例标题名称修改-----------
	function _changeTitle(){
		var ModuleTitle=document.getElementById("LegendModuleTitle");
	     	ModuleTitle.innerHTML = _moduleNames[_currentIndex]+"("+(_currentIndex+1)+"/"+_modulesNum+")";
	}

	
	//寻找item元素在数组中的索引或者下标-----
	function _inArray(item,arr){
		if(!arr){
			return -1;
		}
		var _size = arr.length;
		for(var i=0;i<_size;i=i+1){
			if(arr[i] == item){
				return i;
			}
		}
		return -1;
	}

	
    //module为对象，具有id,模块名称、关联的内容地址，以及子模块名称-------
	function addModule(module){
		_currentIndex = _inArray(module.id,_moduleIds);
		if(_currentIndex>=0){
			_dialog.show();
			_changeTitle();
			_moduleVisible(_currentIndex);
			return;
		}else{
			_currentIndex = _moduleIds.length;
		}
		if(!_dialog){
			_initContainer();
		}else{
			_dialog.show();
		}
		var _container = document.getElementById(_containerId),
			_containerHead = document.getElementById(_containerHeadId),
			_containerContent = document.getElementById(_containerContentId),
			_moduleContainer = document.createElement("div"),
			_moduleName = ModuleConfig[module.id],
			_moduleLegend = _legendConfig[_moduleName];
		if(!_moduleLegend){
			_dialog.hide();
			return;
		}
		_moduleIds.push(module.id);
		_moduleNames.push(module.name);
		if(!_containerHead.childNodes.length){
			_initHead();
		}
		_moduleContainer.className = "LegendModule";
		var _items = _moduleLegend.items,
			_itemNums = _items.length;
		// 大于五行时加滚动条
		if(_itemNums > 8){
			_moduleContainer.style.overflowY = "auto";
			_moduleContainer.style.height = "150px";
		}
		for(var i=0,len=_itemNums;i<len;i=i+1){
			var _item = SymbolConfig[_items[i]];
			if(!_item){
				continue;
			}
			if(_item.url){
				_moduleContainer.appendChild(_generateImgItem(_item));
			}else if(_item.outLineColor || _item.backgroundColor){
				_moduleContainer.appendChild(_generateColorItem(_item));
			}else{
				continue;
			}
			_containerContent.appendChild(_moduleContainer);
		}
		_modulesNum = _containerContent.childNodes.length;
		_changeTitle();
		_moduleVisible(_currentIndex);
	}

	
	//生成图例图片-------
	function _generateImgItem(obj){
		var _img = document.createElement("img"),
			_label = document.createElement("span"),
			_div = document.createElement("div");
		_div.className = "LegendItem";
		_img.src = obj.url;
		_label.innerHTML = obj.name;
		_div.appendChild(_img);
		_div.appendChild(_label);
		return _div;
	}

	//图例为颜色区分------------
	function _generateColorItem(obj){
		var _ico = document.createElement("div"),
			_label = document.createElement("span"),
			_div = document.createElement("div");
		_div.className = "LegendItem";
		_ico.className = "LegendItemIco";
		_ico.style.background = obj.outLineColor || _item.backgroundColor;
		_label.innerHTML = obj.name;
		_div.appendChild(_ico);
		_div.appendChild(_label);
		return _div;
	}
	return api;
});