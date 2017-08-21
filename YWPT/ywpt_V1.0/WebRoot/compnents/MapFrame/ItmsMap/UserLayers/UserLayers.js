MapFactory.Define("ItmsMap/UserLayers/UserLayers*",[
	"ItmsMap/ModuleConfig*",
	"ItmsMap/Util/ModuleManager*",
	"ItmsMap/Util/Legend*",
	"ItmsMap/UserLayers/CustomLayersConfig*",
	"ItmsMap/MapConfig",
	"MapFactory/Util/Dialog*",
	"MapFactory/MapManager",
	"MapFactory/LayerManager"
],function(ModuleConfig,ModuleManager,Legend,CustomLayersConfig,MapConfig,Dialog,MapManager,LayerManager){
	return function(){

		var api = {
			init : init,
			showMoudle : showMoudle,
			show : show
		};

		var index = 0;
		var i = 0;
		var moduleIds = ModuleManager.getModuleIds();
		var baseLayerPath = "ItmsMap/UserLayers/CustomLayers/";
		var layersData = {};
		var mapManager = MapManager();

		var layers = MapConfig.layers;

		var _dom = MapFactory.Dom;

		var _panel = Dialog({
			mutiDialog : true,
			mutiDialogSeed : "CustomLayers",
			mask : false,
			title : '用户图层',
			right : 10,
			top : 35,
			buttonDisplay : {
				confirmButton : false,
				cancelButton : false
			}
		});
		var contentid = "userCustomLayers";

		var inputboxs = [];

		function init(){
			var contentStr = "<div id='"+contentid+"' style='width:180px;max-height:400px;_height:expression(this.scrollHeight>400?\"400px\":\"auto\");overflow-y:auto;overflow-x:hidden;'></div>";
			_panel.setDialogContent(contentStr).show();

			for(var i=0,len=moduleIds.length;i<len;i++){
				addModuleLayers(ModuleConfig[moduleIds[i]], moduleIds[i]);
			}
			addModuleLayers("basemap", "");// 添加地图

			//mapManager.setLayerAddEvent(function(layerid){
				//resetCheckBox(layerid,true);
			//});

			//mapManager.setLayerRemoveEvent(function(layerid){
				//resetCheckBox(layerid,false);
			//});
		}
		
		function show() {
			_panel.show();
		}

		function resetCheckBox(layerid,isOpen){
			var contentbox = _dom.getById(contentid);
			inputboxs = [];
			iteratorbox(contentbox);
			for(var i=0,len=inputboxs.length;i<len;i++){
				var inputbox = inputboxs[i];
				var classname = _dom.attr(inputbox,"className");
				var layername = _dom.attr(inputbox,"layerName");
				if(layername == layerid){
					var _parentLayer = $(inputbox).parent().parent().parent().siblings("div").find("input");
					if(isOpen){
						inputbox.checked = true;
						//_dom.attr(inputbox,"checked","checked");
						layersData[classname][layername]["isOpen"] = true;
						if(_parentLayer && _parentLayer.length)_parentLayer[0].checked = true;
					}else{
						inputbox.checked = false;
						//_dom.removeAttr(inputbox,"checked");
						layersData[classname][layername]["isOpen"] = false;
						if(_parentLayer && _parentLayer.length)_parentLayer[0].checked = false;
					}
				}
			}
		}

		function iteratorbox(elem){
			var children = _dom.children(elem);
			for(var i=0,len=children.length;i<len;i++){
				if(_dom.children(children[i]).length){
					iteratorbox(children[i]);
				}else{
					if(_dom.attr(children[i],"type") == "checkbox" && _dom.attr(children[i], "className") != "BaseMap"){
						inputboxs.push(children[i]);
					}
				}
			}
		}

		function addModuleLayers(id, moduleId){
			if(!CustomLayersConfig[id]){
				return;
			}
			var moduleObj = CustomLayersConfig[id];
			moduleObj.moduleid = moduleId;
			addGroup(moduleObj);
		}

		function addGroup(data){
			MapFactory.Require([baseLayerPath+data["class"]+"*"],function(target){
				var flag = false;
				var contentBox = _dom.getById(contentid);
				var layerGroupBox = _dom.createElem("div");
				var layerGroupTitle = _dom.createElem("div");
				var layerGroupContainer = _dom.createElem("ul");
				var layerTitleCollapse = _dom.createElem("div");
				var layerTitlecheckBoxC = _dom.createElem("span");
				var layerTitlecheckBox = _dom.createElem("input");
				var layerTitlecheckBoxText = _dom.createElem("span");
				var layerTitleB = _dom.createElem("b");
				var clearDiv = _dom.createElem("div");
				var className = data["class"];
				
				_dom.addClass(clearDiv,"clear");
				_dom.addClass(layerGroupBox,"userLayerGroupBox");
				_dom.addClass(layerGroupTitle,"userLayerGroupTitle");
				_dom.addClass(layerGroupContainer,"userLayersControler");
				_dom.addClass(layerTitleCollapse,"userLayerGroupCollapse userGroupUnCollapse");
				_dom.addClass(layerTitlecheckBoxC,"checkbox");
				_dom.addClass(layerTitlecheckBoxText,"checkboxText");
				_dom.attr(layerTitleCollapse,"id","userLayerTitleCollapse_"+i);
				_dom.attr(layerGroupContainer,"id","userLayersControler_"+i);
				_dom.attr(layerTitlecheckBox,"className",className);
				_dom.attr(layerTitlecheckBox,"type","checkbox");
				//_dom.attr(layerTitlecheckBox,"value",data["moduleid"]+","+data["label"]);
				_dom.attr(layerTitlecheckBox,"moduleId",data["moduleid"]);
				_dom.attr(layerTitlecheckBox,"moduleName",data["label"]);
				
				_dom.html(layerTitleB,data["label"]);
				_dom.append(layerTitlecheckBoxText,layerTitleB);
				_dom.append(layerTitlecheckBoxC,layerTitlecheckBox);
				_dom.append(layerGroupTitle,layerTitleCollapse);
				_dom.append(layerGroupTitle,layerTitlecheckBoxC);
				_dom.append(layerGroupTitle,layerTitlecheckBoxText);
				_dom.append(layerGroupBox,layerGroupTitle);
				_dom.append(layerGroupBox,clearDiv);
				_dom.append(layerGroupBox,layerGroupContainer);
				_dom.append(contentBox,layerGroupBox);
				
				if(data["isOpen"]){
					_dom.attr(layerTitlecheckBox,"checked","checked");
				}
				
				// 全选按钮控制事件
				layerTitlecheckBox.onclick = function(){
					var _className = _dom.attr(this,"className");
					var _module = _dom.attr(this,"value");
					var _moduleId = _dom.attr(this,"moduleId");
					var _moduleName = _dom.attr(this,"moduleName");
					var _isChecked = this.checked;
					//var _moduleId = _module.split(",")[0];
					//var _moduleName = _module.split(",")[1];
					var _subLayers = $(this).parent().parent().siblings("ul").find("input");
					for(var j=_subLayers.length-1; j>=0; j--) {
						_subLayers[j].checked = this.checked;
						var _layerName = _dom.attr(_subLayers[j],"layerName");
						layersData[_className][_layerName]["isOpen"] = this.checked;
						if(this.checked) {
							if(mapManager.isLayerExist(_layerName))LayerManager(_layerName).show();
						}
						else {
							if(mapManager.isLayerExist(_layerName))LayerManager(_layerName).hide();
						}
					}
					MapFactory.Require([baseLayerPath+_className+"*"],function(target){
						if(_moduleId && _isChecked) {
							if(typeof window["currentModuleId"] != "undefined")currentModuleId = _moduleId;
							Legend.addModule({id: _moduleId, name: _moduleName});//打开图例
						}
						target.showLayers(layersData[_className]);
					});
				}
				
				$("#userLayerTitleCollapse_"+i).click(function(){
					if($(this).hasClass("userGroupUnCollapse")){
						//$("#userLayersControler_3").slideUp();
						$(this).parent().siblings("ul").slideUp();
						$(this).removeClass("userGroupUnCollapse").addClass("userGroupCollapse");
					}else{
						//$("#userLayersControler_3").slideDown();
						$(this).parent().siblings("ul").slideDown();
						$(this).removeClass("userGroupCollapse").addClass("userGroupUnCollapse");
					}
				});
				i++;
				
				layersData[data["class"]] = {};

				var customLayers = data.layers;
				for(var elem in customLayers){
					if(layers[elem]){
						customLayers[elem]["name"] = layers[elem]["name"];
						customLayers[elem]["id"] = layers[elem]["id"];
						customLayers[elem]["moduleid"] = data["moduleid"];
						customLayers[elem]["label"] = data["label"];
					}
					addItem(customLayers[elem],layerGroupContainer,className);
					if(customLayers[elem]["isOpen"]){
						flag = true;
					}
				}
				
				// 判断是否有默认打开的图层，如果有则调用对应的方法
				if(flag) {
					if(data["moduleid"]) {
						if(typeof window["currentModuleId"] != "undefined")currentModuleId = data["moduleid"];
						Legend.addModule({id: data["moduleid"], name: data["label"]});//打开图例
					}
					target.showLayers(layersData[data["class"]]);
				}
			});
		}

		function addItem(item,groupBox,className){
			var layerContainer = _dom.createElem("li");
			var checkBoxC = _dom.createElem("span");
			var checkBox = _dom.createElem("input");
			var checkBoxText = _dom.createElem("span");
			_dom.attr(checkBox,"type","checkbox");
			_dom.attr(checkBox,"className",className);
			_dom.attr(checkBox,"layerName",item["id"]);
			_dom.attr(checkBox,"moduleId",item["moduleid"]);
			_dom.attr(checkBox,"moduleName",item["label"]);
			_dom.html(checkBoxText,item["name"]);
			
			//_dom.addClass(layerContainer,"MapDragAbleTarget_toc");
			_dom.addClass(checkBoxC,"checkbox");
			_dom.addClass(checkBoxText,"checkboxText");
			
			_dom.append(checkBoxC,checkBox);
			_dom.append(layerContainer,checkBoxC);
			_dom.append(layerContainer,checkBoxText);
			_dom.append(groupBox,layerContainer);

			layersData[className][item["id"]] = {};

			if(item["isOpen"]){
				_dom.attr(checkBox,"checked","checked");
				layersData[className][item["id"]]["isOpen"] = true;
			}else{
				layersData[className][item["id"]]["isOpen"] = false;
			}

			checkBox.onclick = function(){
				var _className = _dom.attr(this,"className");
				var _layerName = _dom.attr(this,"layerName");
				var _moduleId = _dom.attr(this,"moduleId");
				var _moduleName = _dom.attr(this,"moduleName");
				var _isChecked = this.checked;
				layersData[_className][_layerName]["isOpen"] = this.checked ? false : true;
				if(this.checked){
					layersData[_className][_layerName]["isOpen"] = true;
					if(mapManager.isLayerExist(_layerName))LayerManager(_layerName).show();
				}else{
					layersData[_className][_layerName]["isOpen"] = false;
					if(mapManager.isLayerExist(_layerName))LayerManager(_layerName).hide();
				}
				
				var _subLayers = layersData[_className];
				var flag = true;
				for(var _name in _subLayers) {
					if(_subLayers[_name]["isOpen"] != this.checked) {
						flag = false;
					}
				}
				
				if(flag) {
					var _parentLayer = $(this).parent().parent().parent().siblings("div").find("input");
					if(_parentLayer && _parentLayer.length)_parentLayer[0].checked = this.checked;
				}

				MapFactory.Require([baseLayerPath+className+"*"],function(target){
					if(_moduleId && _isChecked) {
						if(typeof window["currentModuleId"] != "undefined")currentModuleId = _moduleId;
						Legend.addModule({id: _moduleId, name: _moduleName});//打开图例
					}
					target.showLayers(layersData[_className]);
				});
			}
		}
		
		function showMoudle(moduleId, isOpen) {
			var moduleName = ModuleConfig[moduleId];
			if(!moduleName)return;
			var _className = CustomLayersConfig[moduleName]["class"];
			var contentbox = _dom.getById(contentid);
			inputboxs = [];
			iteratorbox(contentbox);
			for(var i=0,len=inputboxs.length;i<len;i++){
				var inputbox = inputboxs[i];
				var classname = _dom.attr(inputbox,"className");
				var layername = _dom.attr(inputbox,"layerName");
				if(classname == _className){
					if(isOpen){
						inputbox.checked = true;
						if(layername)layersData[classname][layername]["isOpen"] = true;
					}else{
						inputbox.checked = false;
						if(layername)layersData[classname][layername]["isOpen"] = false;
					}
				}else {
					inputbox.checked = false;
					if(layername)layersData[classname][layername]["isOpen"] = false;
				}
			}
		}

		/*function showUserLayers(){
			if(index>=moduleIds.length){
				return;
			}
			var id = moduleIds[index];
			index++;
			if(ModuleConfig[id] && layersKeyValue[ModuleConfig[id]]){
				MapFactory.Require([baseLayerPath+layersKeyValue[ModuleConfig[id]]+"*"],function(targetLayers){
					targetLayers.showLayers();
					showUserLayers();
				});
			}else{
				showUserLayers();
			}
		}*/

		return api;
	}
});