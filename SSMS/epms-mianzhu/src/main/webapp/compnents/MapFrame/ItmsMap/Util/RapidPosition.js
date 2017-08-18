/**
 * 绘画控件
 * @author ZLT
 * @since 2014-5-16
 */
MapFactory.Define("ItmsMap/Util/RapidPosition*",[
    "MapFactory/LayerManager",
    "ItmsMap/Util/RightClick*",
    "MapFactory/Util/Dialog*",
    "MapFactory/GraphicManager",
    "ItmsMap/Util/LayerUtil*",
    "MapFactory/Edit",
    "MapFactory/Query",
    "ItmsMap/MapConfig"
],function(LayerManager,RightClick,dialog,GraphicManager,LayerUtil,Edit,queryFun,mapConfig){
	var api = {
		showWidget : showWidget,  
		hide : hide,
		resetDefaultRightClick : resetDefaultRightClick,
		setRightClick : setRightClick,
		ws:ws,
		queryVideo:queryVideo,
		queryCross:queryCross,
		querySignal:querySignal
	};
	
	 
	var videoQuery = queryFun();    //视频设备
	videoQuery.setUrl(mapConfig.layers.videoLayer.url);
	var crossQuery = queryFun();    //卡口图层
	crossQuery.setUrl(mapConfig.layers.cross.url);
	var signalQuery = queryFun();   //信号机
	signalQuery.setUrl(mapConfig.layers.signal.url);
	
	
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
				_edit.setEditMode("move|editvertices");
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
	var _RapidPositionContainer;
	function showWidget(){
		if(_RapidPositionContainer) {
			_RapidPositionContainer.show();
			return;
		}
		_RapidPositionContainer = dialog({
			zindex : 1500,
			mutiDialog : true,
			mutiDialogSeed : "mapSigns",
			mask : false,
			title : "快速定位",
			content : "<div id='rapidpositionContainer' style='width:250px;height:320px;overflow-y:auto;overflow-x:hidden;'></div>",
			buttonDisplay : {
				'confirmButton' : false,
				'cancelButton' : false
			},
			right:10,
			top:35
		});
		_RapidPositionContainer.show();
		MapFactory.XHR.Load("rapidpositionContainer","map/home/rapidposition/");
	}
	
	/**
	 * 隐藏绘画界面
	 */
	function hide(){
		if(_RapidPositionContainer)_RapidPositionContainer.hide();
	}
	
	/**
	 * 获取所有图层人员设备    信号机   卡口图层   视频设备
	 */
	function ws(userindex,root){
		var msg;
		var url = root+"/map/home/getuserinfo11/";
		//alert(url);
		$.ajax({
			type : "post",
			url : url,
			cache: false,
			async: false,
			data : "&name=" + userindex + "&rp=" + Math.random(),
			dataType : "json",
			success : function(list) {
				//list = JSON.parse(list);
				//alert(list);
				msg = list;
			}
		});
		return msg;
	}
	
	/**
	 * 查询视频设备信息
	 */
	
	function queryVideo(name, callback) {
		if(!callback)return;
		// 查询视频设备信息
		videoQuery.setGeometry(null);
		videoQuery.setCondition({"NAME like": "%"+name+"%"});
		videoQuery.execute(function(result) {
			//alert(result);
			if(result && result.length) {
				
				callback(result);
			}
			else {
				callback("");
			}
		}, function(error) {
			if (typeof console != 'undefined' && console.log) console.log("查询视频设备数据的时候，出错了！"+error);
			callback(null);
		});
	}
	
	/**
	 * 查询卡口设备信息
	 */
	
	function queryCross(name, callback) {
		if(!callback)return;
		//查询卡口设备信息
		crossQuery.setGeometry(null);
		crossQuery.setCondition({"NAME like": "%"+name+"%"});
		crossQuery.execute(function(result) {
			//alert(result);
			if(result && result.length) {
				callback(result);
			}
			else {
				callback("");
			}
		}, function(error) {
			if (typeof console != 'undefined' && console.log) console.log("查询卡口数据的时候，出错了！"+error);
			callback(null);
		});
	}
	
	/**
	 * 查询信号机信息
	 */
	
	function querySignal(name, callback) {
		if(!callback)return;
		// 查询点所在的道路信息
		signalQuery.setGeometry(null);
		signalQuery.setCondition({"NAME like": "%"+name+"%"});
		signalQuery.execute(function(result) {
			if(result && result.length) {
				callback(result);
			}
			else {
				callback("");
			}
		}, function(error) {
			if (typeof console != 'undefined' && console.log) console.log("查询信号机数据的时候，出错了！"+error);
			callback(null);
		});
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