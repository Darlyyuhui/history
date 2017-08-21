MapFactory.Define("ItmsMap/Util/ModuleManager2*",[
	"ItmsMap/Util/ModuleMessage*",
	"ItmsMap/Util/Tab*",
	"ItmsMap/Util/ToggleBox*",
	"ItmsMap/Util/ModuleMessage*"
],function(ModuleMessage,Tab,ToggleBox,ModuleMessage){
	var api = {
		init : init,
		addModule : addModule
	};

	var _conf = {
		src : "",
		target : "",
		modules : []
	};

	var _container = null,
		_modulesInfo = {},
		_target = null,
		_modulesContainer = null,
		_loadWaiting = $("<div class='loadWaiting'></div>"),
		_openedModules = {},
		_moduleMessage = $("<div class='mapModuleMessage'></div>"),
		_isAutoRefresh = false,
		_hasModuleAdded = false,
		_autoRefreshHandler = null,
		_isMultiModule = false;

	/**
	 * 初始化菜单
	 * @param conf Object 配置对象
	 */
	function init(conf){
		MapFactory.Extend(_conf,conf);
		_container = $("#"+_conf.src);
		_target = $("#"+_conf.target);
		var _modules = _conf.modules,
			_modulesLen = _modules.length;
		for(var i=0;i<_modulesLen;i++){
			addModule(_modules[i]);
		}
		_initModuleBox();
	}

	/**
	 * 添加模块
	 * @param module Object 模块对象
	 *   + name String 模块名
	 *   + url String 模块链接
	 *   + children Object 子模块对象
	 */
	function addModule(module){
		if(!module.name){
			return;
		}
		for(var i=0,len=module.children.length;i<len;i++){
			var _childModule = module.children[i];
			_modulesInfo[_childModule.id] = _childModule;
			module.children[i] = _childModule.id;
		}
		_modulesInfo[module.id] = module;

		var _menuBox = $("<div class='mapToolMenuBox'></div>"),
			_title = $("<div class='mapToolParentMenuTitle'><div class='mapToolParentMenuTitleContent'><img src='images/picone/icon1.gif' />&nbsp&nbsp&nbsp&nbsp"+module.name+"</div></div>");

		_title.append("<input type='hidden' value='"+module.id+"'/>");

		_menuBox.append(_title);
		_container.append(_menuBox);
		_title.click(function(){
			_hasModuleAdded = true;
			var _moduleId = $($(this).find("input")[0]).val();
			if(!_openedModules[_moduleId]){
				if(!_isMultiModule){
					_modulesContainer.children(".mapModuleBox").hide();
				}
				addModuleContent(_moduleId);
				_openedModules[module.id] = {};
				_openedModules[module.id]["opened"] = true;
			}else{
				Tab().switchTab(1,null,"menuListTab");
				_modulesContainer.children(".mapModuleBox").each(function(i,item){
					var _theModule = $(item);
					if(_theModule.attr("id") == _moduleId){
						_theModule.show()
					}else{
						if(!_isMultiModule){
							_theModule.hide();
						}
					}
				});
			}
			_isMultiModule = false;
		});
	}

	function _initModuleBox(){
		var _moduleToolBar = $("<div class='mapModuleTools'></div>"),
			_modulesBox = $("<div id='mapModulesContent'></div>"),
			_refreshBtn = $("<div id='mapModulesRefresh' class='mapModuleToolIcon mapModuleToolIconNormal'></div>'"),
			_autoRefreshBtn = $("<div id='mapModulesAutoRefresh' class='mapModuleToolIcon'></div>"),
			_addModuleBtn = $("<div id='mapAddModule' class='mapModuleToolIcon mapModuleToolIconNormal'></div>"),
			_messageBox = _moduleMessage.clone();
		_modulesContainer = _modulesBox;
		_moduleToolBar.append(_refreshBtn).append(_autoRefreshBtn).append(_addModuleBtn);
		_target.empty().append(_moduleToolBar).append(_messageBox).append(_modulesBox);
		var _iconPosition = {
			mapAddModule : {
				mouseenter : "-13px 0",
				mouseout : "0 0",
				click : "0 0"
			},
			mapModulesAutoRefresh : {
				auto : "-26px 0",
				handle : "-39px 0"
			},
			mapModulesRefresh : {
				mouseenter : "-65px 0",
				mouseout : "-52px 0",
				click : "-52px 0"
			}
		};
		$(".mapModuleToolIconNormal").mouseenter(function(){
			var _tarIco = $(this),
				_icoId = _tarIco.attr("id");
			_tarIco.css("backgroundPosition",_iconPosition[_icoId].mouseenter);
		}).mouseleave(function(){
			var _tarIco = $(this),
				_icoId = _tarIco.attr("id");
			_tarIco.css("backgroundPosition",_iconPosition[_icoId].mouseout);
		}).mousedown(function(){
			var _tarIco = $(this),
				_icoId = _tarIco.attr("id");
			_tarIco.css("backgroundPosition",_iconPosition[_icoId].mouseout);
		}).mouseup(function(){
			var _tarIco = $(this),
				_icoId = _tarIco.attr("id");
			_tarIco.css("backgroundPosition",_iconPosition[_icoId].mouseenter);
		});
		_refreshBtn.click(function(){
			if(!_hasModuleAdded){
				ModuleMessage.showMessage("还没有加载模块！",ModuleMessage.WARNING);
				return;
			}
			_refresh();
		});
		_autoRefreshBtn.click(function(){
			if(!_hasModuleAdded){
				ModuleMessage.showMessage("还没有加载模块！",ModuleMessage.WARNING);
				return;
			}
			if(_isAutoRefresh){
				ModuleMessage.showMessage("自动刷新关闭！",ModuleMessage.SUCCESS);
				_isAutoRefresh = false;
				$(this).css("backgroundPosition",_iconPosition["mapModulesAutoRefresh"].handle);
				_closeAutoRefresh();
			}else{
				ModuleMessage.showMessage("自动刷新开启，每5秒刷新一次",ModuleMessage.SUCCESS);
				_isAutoRefresh = true;
				$(this).css("backgroundPosition",_iconPosition["mapModulesAutoRefresh"].auto);
				_autoRefresh();
			}
		});
		_addModuleBtn.click(function(){
			ModuleMessage.showMessage("跳到导航栏选择要添加的模块",ModuleMessage.LOG);
			setTimeout(function(){Tab().switchTab(0,null,"menuListTab")},2000);
			_isMultiModule = true;
		});
	}

	function _refresh(){
		ModuleMessage.showMessage("正在刷新...",ModuleMessage.LOG);
		$(".mapModuleBox:visible").each(function(i,item){
			var _tarModuleID = $(item).attr("id");
			if(_modulesInfo[_tarModuleID].children && _modulesInfo[_tarModuleID].children.length){
				var _openedTars = $(item).children().find(".mapToolSubMenuContent:visible");
				for(var i=0,len=_openedTars.length;i<len;i++){
					var _openedTarID = $($(_openedTars[i]).siblings(".mapToolSubMenuTitle").find("input")[0]).val();
					if(_modulesInfo[_openedTarID] && _modulesInfo[_openedTarID].url){
						$(_openedTars[i]).load(_modulesInfo[_openedTarID].url,function(){
							//$(this).prepend(_moduleMessage.clone());
							ModuleMessage.showMessage("加载完成!",ModuleMessage.SUCCESS);
						});
					}
				}
			}else{
				if(_modulesInfo[_tarModuleID].url){
					$(item).load(_modulesInfo[_tarModuleID].url,function(){
						//$(item).prepend(_moduleMessage.clone());
						ModuleMessage.showMessage("加载完成!",ModuleMessage.SUCCESS);
					});
				}
			}
		});
	}

	function _autoRefresh(){
		_autoRefreshHandler = setInterval(_refresh,5000);
	}

	function _closeAutoRefresh(){
		clearInterval(_autoRefreshHandler);
	}

	function addModuleContent(id){
		var module = _modulesInfo[id],
			_moduleBox = $("<div class='mapModuleBox' id='"+id+"'></div>");
		_moduleBox.append(_loadWaiting);
		if(module.url){
			if(!_openedModules[module.id]){
				_openedModules[module.id] = {};
				_openedModules[module.id]["opened"] = true;
				_moduleBox.load(module.url,function(){
					//_moduleBox.prepend(_moduleMessage.clone());
				});
				_moduleBox.addClass("mapToolMenuContent");
			}
		}else{
			_loadChildrenModule(_moduleBox,module,0);
		}
		_modulesContainer.append(_moduleBox);
		Tab().switchTab(1,null,"menuListTab");
	}

	function _loadChildrenModule(src,module,childIndex){
		var children = module.children;
		if(children.length <= childIndex){
			ToggleBox().init({srcNode:src,ico:false}).open(0);
			return;
		}
		var _subModule = _modulesInfo[children[childIndex]],
			_subModuleBox = $("<div></div>"),
			_subModuleTitle = $("<div class='mapToolSubMenuTitle'>"+_subModule.name+"</div>"),
			_subModuleContent = $("<div class='mapToolSubMenuContent'></div>");
		_subModuleTitle.append("<input type='hidden' value='"+_subModule.id+"'/>");
		_subModuleBox.append(_subModuleTitle);
		_subModuleBox.append(_subModuleContent);
		if(0==childIndex && _subModule.url){
			_subModuleContent.load(_subModule.url,function(){
				_loadWaiting.remove();
				_openedModules[_subModule.id] = {};
				_openedModules[_subModule.id]["opened"] = true;
				//_subModuleContent.prepend(_moduleMessage.clone());
			}).show();
		}
		//_subModuleContent.prepend(_moduleMessage.clone());
		_subModuleTitle.click(function(){
			var _moduleid = $($(this).find("input")[0]).val(),
				_currentModule = _modulesInfo[_moduleid];
			if(_currentModule.url && !_openedModules[_moduleid]){
				_subModuleContent.load(_currentModule.url,function(){
					//_subModuleContent.prepend(_moduleMessage.clone());
				}).show();
				_openedModules[_currentModule.id] = {};
				_openedModules[_currentModule.id]["opened"] = true;
			}
		});

		src.append(_subModuleBox);
		_loadChildrenModule(src,module,++childIndex);
	}

	return api;
});