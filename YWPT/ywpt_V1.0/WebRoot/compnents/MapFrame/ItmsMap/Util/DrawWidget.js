/**
 * 绘画控件
 * @author ZLT
 * @since 2014-5-16
 */
MapFactory.Define("ItmsMap/Util/DrawWidget*",[
    "MapFactory/LayerManager",
    "ItmsMap/Util/RightClick*",
    "MapFactory/Util/Dialog*",
    "MapFactory/GraphicManager",
    "ItmsMap/Util/LayerUtil*",
    "MapFactory/Edit"
],function(LayerManager,RightClick,dialog,GraphicManager,LayerUtil,Edit){
	var api = {
		showWidget : showWidget,
		hide : hide,
		resetDefaultRightClick : resetDefaultRightClick,
		setRightClick : setRightClick
	};

	var	_menus = [],
		_drawLayerId = "mapSigns",
		_edit = null;

	var _defaultMenu = [
		{label:"编辑",func:function(evt){
			if(_edit){
				_edit.deactivate();
				_edit = null;
				_menus[evt.index].label = "编辑";
			}else{
				_edit = Edit();
				_edit.setEditMode("rotate|scale");
				_edit.setLayerID(_drawLayerId);
				_edit.activate();
				_menus[evt.index].label = "停止";
			}
			LayerUtil.setRightclickMenu(_drawLayerId,_menus);
		}},
        {label:"删除",func:function(evt){
        	GraphicManager(evt.object.id).remove();
        }}
    ];

	/**
	 * 显示绘画界面
	 */
	var _signContainer;
	function showWidget(){
		if(_signContainer) {
			_signContainer.show();
			return;
		}
		_signContainer = dialog({
			zindex : 1500,
			mutiDialog : true,
			mutiDialogSeed : "mapSigns",
			mask : false,
			title : "标绘工具",
			content : "<div id='mapSignsContainer' style='width:230px;height:350px;'></div>",
			buttonDisplay : {
				'confirmButton' : false,
				'cancelButton' : false
			},
			right:10,
			top:35
		});
		_signContainer.show();
		MapFactory.XHR.Load("mapSignsContainer","map/home/mapsigns/");
	}
	
	/**
	 * 隐藏绘画界面
	 */
	function hide(){
		if(_signContainer)_signContainer.hide();
	}

	/**
	 * 重置到默认右击事件
	 */
	function resetDefaultRightClick(){
		_menus = [];
		setRightClick([]);
	}

	/**
	 * 设置右击事件
	 * @param menuList Array(menu) 菜单数组
	 *  + menu Object 菜单条目
	 *  ++ label String 菜单选项名
	 *  ++ func Function 选择后的回调函数
	 */
	function setRightClick(menuList){
		if(menuList && menuList.length){
			_menus = _menus.concat(menuList.concat(_defaultMenu));
		}else{
			_menus = _defaultMenu;
		}
		LayerUtil.setRightclickMenu(_drawLayerId,_menus);
	}

	return api;
});