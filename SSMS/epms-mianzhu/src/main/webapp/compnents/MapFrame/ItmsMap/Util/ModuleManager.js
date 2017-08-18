MapFactory.Define("ItmsMap/Util/ModuleManager*",[
	"ItmsMap/Util/ToggleBox*",
	"ItmsMap/Util/ModuleMessage*",
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	"ItmsMap/MapConfig"
],function(ToggleBox,ModuleMessage,MapManager,LayerManager,MapConfig){

	var api = {
		init : init,
		addModule : addModule,
		addTool : addTool,
		resetTool : resetTool,
		cleanModuleExcludeId : cleanModuleExcludeId,
		refreshModule : refreshModule,
		getModuleIds : getModuleIds
	};

	var _conf = {
		src : "",
		modules : [],
		parentModuleOpenEvt : ""
	};

	var _container = null;
	
	var _intervalHandler = {};
	
	var _parentModuleOpenEvt = null,
		_subModuleOpenEvt = null;

	var moduleIds = [];
	
	var _baseLayerId = MapConfig.layers.baseMap.id;

	/**
	 * 初始化菜单
	 * @param conf Object 配置对象
	 */
	function init(conf){
		MapFactory.Extend(_conf,conf);
		_container = $("#"+_conf.src);
		var _modules = _conf.modules,
			_modulesLen = _modules.length;
		_parentModuleOpenEvt = conf.parentModuleOpenEvt;
		_subModuleOpenEvt = conf.subModuleOpenEvt;
		for(var i=0;i<_modulesLen;i++){
			addModule(_modules[i]);
			moduleIds.push(_modules[i].id);
		}
		ToggleBox().init({srcNode:"mapMenuList",ico:true,uniqueOpen:true}).open(-1);
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
		var _menuBox = $("<div class='mapToolMenuBox'></div>"),
			_title = $("<div class='mapToolParentMenuTitle'><div class='mapToolParentMenuTitleContent'><img src='images/picone/icon1.gif' />&nbsp&nbsp&nbsp&nbsp"+module.name+"</div></div>"),
			_content = $("<div class='mapToolMenuContent' style='display:none;'></div>");
		_title.attr("restid",module.id);
		_menuBox.append(_title);
		_menuBox.append(_content);
		_container.append(_menuBox);

		if(module.url){
			_title.attr("rest",module.url);
			_title.attr("restid",module.id);
			_title.click(function(){
				_removeLayers();
				//_content.load(module.url);
				if(_parentModuleOpenEvt){
					_parentModuleOpenEvt(module);
				}
				_loadModule(_content,module.url);
			});
			return;
		}else{
			_title.click(function(){
				_removeLayers();
				var _targetContent = $($(this).siblings(".mapToolMenuContent").find(".mapToolSubMenuContent")[0]),
					_targetSubContent = $(_targetContent).find(".mapModuleContentArea"),
					_rest = _targetContent.siblings(".mapToolSubMenuTitle").attr("rest");
				var _subContentHtml = "";
				if(_targetSubContent && _targetSubContent.length){
					_subContentHtml = $(_targetSubContent[0]).html();
				}
				if(_rest){// && !_subContentHtml){
					_loadModule(_targetContent,_rest);
				}
				if(_parentModuleOpenEvt){
					_parentModuleOpenEvt(module);
				}
			});
		}

		var _children = module.children,
			_childrenLen = _children.length;
		for(var i=0;i<_childrenLen;i++){
			_addChildModule(_content,_children[i]);
		}
		ToggleBox().init({srcNode:_content}).open(0);
	}

	function _removeLayers(){
		var _layerids = MapManager().getAllLayersID();
		for(var i=0,len=_layerids.length;i<len;i++){
			if(_baseLayerId == _layerids[i]){
				continue;
			}
			LayerManager(_layerids[i]).removeFromMap();
		}
	}

	function _addChildModule(src,module){
		var _menuBox = $("<div></div>"),
			_title = $("<div class='mapToolSubMenuTitle'></div>"),
			_content = $("<div class='mapToolSubMenuContent'></div>");
		_title.html("<span></span>"+module.name);
		_title.attr("rest",module.url);
		_title.attr("restid",module.id);
		if(_subModuleOpenEvt){
			_title.click(function(){
				_subModuleOpenEvt(module);
			});
		}
		_menuBox.append(_title);
		_menuBox.append(_content);
		src.append(_menuBox);
		if(module.url){
			_title.click(function(){
				var _rest = $(this).attr("rest");
				if(!$("<div class='mapModuleContentArea'></div>").html() && _rest){
					//_content.load(module.url);
					_loadModule(_content,_rest);
				}
			});
		}
	}

	function _loadModule(src,url,func){
		var isShowModuleTools = false;
		var index = url.indexOf("?")
		var paramstr = url.substr(index+1);
		var params = paramstr.split("&");
		for(var p=0,pl=params.length; p<pl; p++) {
			var param = params[p].split("=");
			if(param.length == 2 && param[0]=="refresh" && param[1]=="true") {
				isShowModuleTools = true;
			}
		}
		var _moduleContentArea = null;
		//ModuleMessage.showMessage("正在读取...",ModuleMessage.LOG);
		if(!src.find(".mapModuleTools")[0]){
			var _moduleTools = $("<div class='mapModuleTools'></div>"),
				_infoContent = $("<div class='mapModuleMessage'></div>"),
				_refreshBtn = $("<div class='mapModuleToolIconNormal mapModuleToolIcon mapModulesRefresh' btnid='mapModulesRefresh' title='刷新'></div>'"),
				_autoRefreshBtn = $("<div class='mapModuleToolIcon mapModulesAutoRefresh' btnid='mapModulesAutoRefresh' title='是否开启自动刷新'><input type='hidden' value='false' /></div>");
			_refreshBtn.click(function(){
				ModuleMessage.showMessage("正在刷新...",ModuleMessage.LOG);
				_loadModule(src,url,function(){
					ModuleMessage.showMessage("加载完成!",ModuleMessage.SUCCESS);
				});
			});
			if(isShowModuleTools) {
				_moduleTools.append(_refreshBtn).append(_autoRefreshBtn);
				src.append(_moduleTools);
			}
			if(!src.find(".mapModuleMessage")[0]){
				src.append(_infoContent);
			}
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
					_icoId = _tarIco.attr("btnid");
				_tarIco.css("backgroundPosition",_iconPosition[_icoId].mouseenter);
			}).mouseleave(function(){
				var _tarIco = $(this),
					_icoId = _tarIco.attr("btnid");
				_tarIco.css("backgroundPosition",_iconPosition[_icoId].mouseout);
			}).mousedown(function(){
				var _tarIco = $(this),
					_icoId = _tarIco.attr("btnid");
				_tarIco.css("backgroundPosition",_iconPosition[_icoId].mouseout);
			}).mouseup(function(){
				var _tarIco = $(this),
					_icoId = _tarIco.attr("btnid");
				_tarIco.css("backgroundPosition",_iconPosition[_icoId].mouseenter);
			});
			_autoRefreshBtn.click(function(){
				var _isAuto = $($(this).find("input")[0]);
				var _id = $(this).parent().parent().siblings().attr("restid");
				if(_isAuto.val()=="false"){
					_isAuto.val("true");
					$(this).css("backgroundPosition",_iconPosition["mapModulesAutoRefresh"].auto);
					ModuleMessage.showMessage("自动刷新开启，每5秒刷新一次",ModuleMessage.SUCCESS);
					_intervalHandler[_id] = {};
					_intervalHandler[_id].src = src;
					_intervalHandler[_id].url = url;
					_intervalHandler[_id].interval = setInterval(function(){
						_loadModule(_intervalHandler[_id].src,_intervalHandler[_id].url,function(){
							
						});
					},5000);
				}else{
					_isAuto.val("false");
					$(this).css("backgroundPosition",_iconPosition["mapModulesAutoRefresh"].handle);
					ModuleMessage.showMessage("自动刷新关闭！",ModuleMessage.SUCCESS);
					clearInterval(_intervalHandler[_id].interval);
					delete _intervalHandler[_id];
				}
			});
		}
		_moduleContentArea = src.find(".mapModuleContentArea")[0];
		if(!_moduleContentArea){
			_moduleContentArea = $("<div class='mapModuleContentArea'></div>");
			src.append(_moduleContentArea);
		}else{
			_moduleContentArea = $(_moduleContentArea);
		}
		_moduleContentArea.load(url,function(){
			//ModuleMessage.showMessage("加载完成！",ModuleMessage.SUCCESS);
			if(func){
				func();
			}
		});
	}

	/**
	 * 添加工具
	 * @param moduleid String 模块id
	 * @param ico Object
	 *  + classNormal String 图标未点击样式
	 *  + classClicked String 图标已点击样式
	 *  + initStatus String 初始化图标状态，默认为classNormal
	 *  + label String 标签
	 * @func func Function 点击回调函数
	 *  + event 
	 *   ++ status String clicked/normal图标当前状态
	 */
	function addTool(moduleid,ico,func){
		var _t = $(".mapToolParentMenuTitle[restid="+moduleid+"]");
		if(_t.length <= 0){
			_t = $(".mapToolSubMenuTitle[restid="+moduleid+"]");
		}
		if(_t.length <= 0){
			return;
		}
		var _tool = $("<div class='mapModuleToolIcon "+(ico["initStatus"] ? ico["initStatus"] : ico["classClicked"])+"' title='"+(ico["label"] ? ico["label"] : "")+"'></div>");
		_t.siblings(".mapToolSubMenuContent").children(".mapModuleTools").append(_tool);
		_tool.click(function(){
			if(_tool.hasClass(ico["classNormal"])){
				_tool.removeClass(ico["classNormal"]).addClass(ico["classClicked"]);
				func({
					status : "clicked"
				});
			}else{
				_tool.removeClass(ico["classClicked"]).addClass(ico["classNormal"]);
				func({
					status : "normal"
				});
			}
		});
	}

	/**
	 * 重置工具条
	 * @param moduleid String 模块ID
	 */
	function resetTool(moduleid){
		var _t = $(".mapToolParentMenuTitle[restid="+moduleid+"]");
		if(_t.length <= 0){
			_t = $(".mapToolSubMenuTitle[restid="+moduleid+"]");
		}
		if(_t.length <= 0){
			return;
		}
		_t.siblings(".mapToolSubMenuContent").children(".mapModuleTools").children().each(function(i,item){
			var id = $(item).attr("btnid");
			if(!id || (id != "mapModulesRefresh" && id != "mapModulesAutoRefresh")){
				$(this).remove();
			}
		});
	}

	/**
	 * 刷新模块
	 * @param moduleid String 模块ID
	 */
	function refreshModule(moduleid){
		var _t = $(".mapToolParentMenuTitle[restid="+moduleid+"]");
		if(_t.length <= 0){
			_t = $(".mapToolSubMenuTitle[restid="+moduleid+"]");
		}
		if(_t.length <= 0){
			return;
		}
		var rest = _t.attr("rest");
		if(rest){
			_t.siblings(".mapToolSubMenuContent").children(".mapModuleContentArea").empty().load(rest);
		}else{
			var div = _t.siblings(".mapToolMenuContent").children("div:first");
			rest = div.children(".mapToolSubMenuTitle").attr("rest");
			div.children(".mapToolSubMenuContent").children(".mapModuleContentArea").empty().load(rest);
		}
	}

	/**
	 * 清除模块内容
	 * @param moduleid String 不清除的模块ID
	 */
	function cleanModuleExcludeId(emid) {
		for(var i=0,len=moduleIds.length; i<len; i++) {
			var moduleid = moduleIds[i];
			if(emid == moduleid)continue;// 不清除当前模块
			var _t = $(".mapToolParentMenuTitle[restid="+moduleid+"]");
			if(_t.length <= 0){
				_t = $(".mapToolSubMenuTitle[restid="+moduleid+"]");
			}
			if(_t.length <= 0){
				continue;
			}
			var rest = _t.attr("rest");
			if(rest){
				_t.siblings(".mapToolSubMenuContent").children(".mapModuleContentArea").empty();
			}else{
				var div = _t.siblings(".mapToolMenuContent").children("div:first");
				rest = div.children(".mapToolSubMenuTitle").attr("rest");
				div.children(".mapToolSubMenuContent").children(".mapModuleContentArea").empty();
			}
		}
	}

	/**
	 * 返回用户所拥有的模块id
	 * @return moduleIds String[] 模块ID
	 */
	function getModuleIds(){
		return moduleIds;
	}

	return api;
});