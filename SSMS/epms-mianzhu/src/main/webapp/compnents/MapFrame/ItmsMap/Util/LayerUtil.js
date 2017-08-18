MapFactory.Define("ItmsMap/Util/LayerUtil*",[
    "ItmsMap/Util/RightClick*",
    "MapFactory/LayerManager"
],function(RightClick,LayerManager){
	var api = {
		setRightclickMenu : setRightclickMenu
	};

	var _defaultMenu = [
        {label:"取消",func:function(){}}
    ];

	/**
	 * 设置右击事件
	 * @param menuList Array(menu) 菜单数组
	 *  + menu Object 菜单条目
	 *  ++ label String 菜单选项名
	 *  ++ func Function 选择后的回调函数
	 */
	function setRightclickMenu(layerid,menuList){
		var _menus = [];
		_menus = _menus.concat(menuList).concat(_defaultMenu);
		LayerManager(layerid).addMouseDownEvent(function(evt){
			document.documentElement.oncontextmenu = function(){return false;}
			if(!_menus.length || !(((evt.which) && (evt.which == 3)) || ((evt.button) && (evt.button == 2)))){
				return;
			}
			var _rightClick = RightClick();
			for(var i=0,len=_menus.length;i<len;i++){
				_rightClick.add(_menus[i].label,_menus[i].func,evt.graphic);
			}
			_rightClick.show(evt.clientX,evt.clientY,evt.offsetX,evt.offsetY);
			return false;
		});
	}

	return api;
});