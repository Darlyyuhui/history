/**
 * 基础地图类
 * @author ZLT
 * @since 2014-3-25
 */
MapFactory.Define("MapFactory/MapManager",[
	"MapFactory/MapManagerAPI*",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*"
],function(api,Geometry,GeometryType,infowidow){
	var _conf = {
			src : "mapDiv",
			mapConfig : {},
			loaded : ""
		},
		_eventHandlers = {
			"addLayer" : {},
			"removeLayer" : {},
			"dragStart" : {},
			"dragMove" : {},
			"dragEnd" : {},
			"zoomStart" : {},
			"zooming" : {},
			"zoomEnd" : {},
			"mouseMove" : {},
			"mouseDown" : {},
			"mouseUp" : {},
			"mouseClick" : {},
			"mouseWheel" : {},
			"mouseDbClick" : {},
			"extentChange" : {}
		};
	return function(conf){
		var _panFactor = 50,
		    _defferTime = 500,_mapHandler;	
		var _variableTypes = MapFactory.VariableTypes,_events = MapFactory.Events;
		    MapFactory.Extend(_conf,conf);	
		    _mapHandler = MapFactory._MapManagerResource[MapFactory.Engine];
		if(!_mapHandler){
			MapFactory._MapManagerResource[MapFactory.Engine] = _mapHandler =new BMap.Map(_conf.src,{
		        mapType: _getDefaultMapType()
		    });			
			if(_conf.loaded){
				if (typeof _conf.loaded =='function') {		       				
					_mapHandler.addEventListener("load", function(e){   
						setTimeout(_conf.loaded,500);
							});										
		        }				
			}						     			     			     			     			    			    			  			 			  			  			     
			_mapHandler.addControl(new BMap.NavigationControl({
				anchor: BMAP_ANCHOR_TOP_LEFT,
				type: BMAP_NAVIGATION_CONTROL_LARGE
			}));
			    _mapHandler.centerAndZoom(getInitPoint(),12);  			    
			    _mapHandler.enableScrollWheelZoom();//启用滚轮放大缩小，默认禁用
			    _mapHandler.enableInertialDragging();//启用地图惯性拖拽，默认禁用
			    _mapHandler.enableContinuousZoom();//启用连续放大---------
			    _mapHandler.enableDoubleClickZoom();//启用双击放大-----				    			    
			 // 延迟设置地图事件
				setTimeout(function(){
					_mouseDownEvt();
					_mouseUpEvt();
					_layerAddEvt();
					_layerRemoveEvt();
					_dragEvt();
					_zoomEvt();
					_mouseMoveEvt();
					_mouseClickEvt();
					_dbClickEvt();
					_extentChangeEvt();
					_mouseWheelEvt();					
				},200);
				
		}		
		//默认地图类型，加载离线瓦片-----------
		function _getDefaultMapType(){
		    var tileLayer = new BMap.TileLayer();
		    tileLayer.getTilesUrl = function(tileCoord, zoom) {
		        var x = tileCoord.x;
		        var y = tileCoord.y;
		        var url ="http://localhost:8080/itms/baidumap/"+zoom+ "/" + y +"/"+x+ ".jpg"; //根据当前坐标，选取合适的瓦片图
		        return url;
		    }
		    var myType = new BMap.MapType('MyMap', tileLayer, {
		        minZoom: getMinZoom(),
		        maxZoom: getMaxZoom()
		    });
		    return myType;
		};
								
		//画线颜色
		function getPolyLineColor(){
		    return "#333";
		}
		//画线颜色
		function getPolyLineWidth() {
		    return 4;
		}
		//是否创建鹰眼图
		function isCreateHawkeye() {
		    return true;
		}
		//默认是否打开鹰眼图
		function isOpenHawkeye() {
		    return true;
		}
		//是否添加比例尺
		function isCreateScale() {
		    return true;
		}
		//拉框放大提示文本
		function getRectangleZoomText() {
		    return "拖拽鼠标进行操作";
		}
		//是否启用键盘操作
		function isEnableKeyboard() {
		    return true;
		}
		//是否添加缩放控件
		function isAddNavigation() {
		    return true;
		}
		//是否支持鼠标滚动缩放
		function isEnableScrollWheelZoom() {
		    return true;
		}
		//是否支持双击放大
		function isEnableDoubleClickZoom() {
		    return true;
		}
		//是否限制拖动区域根据搜索的行政区
		function isAreaRestriction() {
		    return false;
		}
		function getInitPoint() {
		    var baiduX = 108.94245 - 108.953567;
			var baiduY = 34.26102 - 34.265685;
			var x=108.94236-baiduX;
			var y=34.26100-baiduY;
		    return new BMap.Point(x,y);
		}
		function getInitZoom() {
		    return 9;
		}
		function getMaxZoom() {
		    return 16;
		}
		function getMinZoom() {
		    return 9;
		}		
		//设置地图样式--------
		function getMapStyle() {
		    var mapStyle = {
		        style: "light" //设置地图风格为高端黑
		    }
		    return mapStyle;
		}
		//新加的函数--------end 
		

		/*设置地图中心点以及级别*/
		function centerAndZoom(x,y,level,callback){			
			_mapHandler.centerAndZoom(new BMap.Point(x, y),level);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}
		/*设置地图中心点*/
		function centerAt(x,y,callback){
			_mapHandler.setCenter(new BMap.Point(x, y));
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}
		
		/*给当前坐标点偏差值设置中心点*/
		function centerAtWithOffset(x,y,offX,offY,callback){
			var _screenP = toScreen(x,y);
			_screenP.x = _screenP.x + offX;
			_screenP.y = _screenP.y + offY;
			var _mapP = toMap(_screenP.x,_screenP.y);
			centerAt(_mapP.x,_mapP.y,callback);
		}
          //地图导航平移方法-------开始-----
		function panLeft(callback){	
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
			_mapHandler.panBy(- Math.round( _mapHandler.getSize().width/ 3), 0);
		}
		function panRight(callback){		
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
			_mapHandler.panBy(Math.round(_mapHandler.getSize().width / 3), 0);
		}
		function panUp(callback){				
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
			_mapHandler.panBy(0, -Math.round(_mapHandler.getSize().height / 3));		
		}

		function panDown(callback){	
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
			_mapHandler.panBy(0, Math.round(_mapHandler.getSize().height / 3));
		}
		//地图导航平移方法-------结束-----
		
		/* 设置当前地图级别-----*/
		function setLevel(level,callback){
			_mapHandler.setZoom(level);
			if(_variableTypes.isFunc(callback)){
				setTimeout(callback,_defferTime);
			}
		}
		/* 获取当前地图级别-----*/
		function getLevel(){
			return _mapHandler.getZoom();
		}
      /* 获取地图级别数暂时无法实现-----*/
		function getNumsOfLevel(){
		   return 5;
		}
        /* 设置地图范围-----*/
		function setExtent(extent){
			var extents=[];
			    extents.push(new BMap.Point(extent.minX,extent.minY));
			    extents.push(new BMap.Point(extent.maxX,extent.maxY));
			_mapHandler.setViewport(extents);
		}
         /* 设置当前地图范围-----*/
		function getCurrentExtent(){
			var extent = _mapHandler.getBounds();
			return  {
				minX : extent.getSouthWest().lng,
				minY : extent.getSouthWest().lat,
				maxX : extent.getNorthEast().lng,
				maxY : extent.getNorthEast().lat
			};
		}
		/* 判断经纬度坐标是否在当前地图范围内-----*/
		function isInCurrentExtent(x,y){
			var _p = new BMap.Point(parseFloat(x),parseFloat(y));
			return _mapHandler.getBounds().containsPoint(_p);
		}
		/* 将像素坐标转换成地图坐标-----*/
		function toMap(x,y){
			var p = _mapHandler.pixelToPoint(new BMap.Pixel(x,y));
			return {
				x : p.lng,
				y : p.lat
			}
		}
		
		/* 将地图坐标转换成像素坐标*/
		function toScreen(x,y){
			var p = _mapHandler.pointToPixel(new BMap.Point(x,y));
			return {
				x : p.x,
				y : p.y
			}
		}
      //获取所有图层的id
		function getAllLayersID(){
			var layeridArr = [];
			for(var layerid in MapFactory._layermanager){				
				layeridArr.push(layerid);
			}
			return layeridArr;
		}	
		
		function setLayerAddEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			_eventHandlers["addLayer"][_evtId] = func;
			return _evtId;
		}
		function _layerAddEvt(){

		}

		function setLayerRemoveEvent(func){

		}

		function _layerRemoveEvt(){
		
		}
            /*图层排序方法，暂时无法实现*/
		function reorderLayer(layerId,layerIndex){
			
		}         		
		/*给地图添加鼠标拖拽事件监听*/
		function setMouseDragEvent(obj){
			var _dragStartId,_dragMoveId,_dragEndId;
			if(_variableTypes.isFunc(obj.dragstart)){
				_dragStartId = MapFactory.GenerateID();
				_eventHandlers["dragStart"][_dragStartId] = obj.dragstart;
			}
			if(_variableTypes.isFunc(obj.dragmove)){
				_dragMoveId = MapFactory.GenerateID();
				_eventHandlers["dragMove"][_dragMoveId] = obj.dragmove;
			}
			if(_variableTypes.isFunc(obj.dragend)){
				_dragEndId = MapFactory.GenerateID();
				_eventHandlers["dragEnd"][_dragEndId] = obj.dragend;
			}
			return {
				_dragstart : _dragStartId,
				_dragmove : _dragMoveId,
				_dragend : _dragEndId
			};
		}		
		/*给地图添加拖拽事件*/
		function _dragEvt(){						
			_mapHandler.addEventListener("dragstart",function(e){			
				for(var elem in _eventHandlers["dragStart"]){
					_eventHandlers["dragStart"][elem]({
						mapPoint : new Geometry({
							type : GeometryType.POINT,
							points : e.point.lng + "," + e.point.lat
						})
					});
				}				
			});						
			_mapHandler.addEventListener("dragging",function(e){			
				for(var elem in _eventHandlers["dragMove"]){
					_eventHandlers["dragMove"][elem]({
						mapPoint : new Geometry({
							type : GeometryType.POINT,
							points : e.point.lng + "," + e.point.lat
						})
					});
				}				   
			});									
			_mapHandler.addEventListener("dragend",function(e){
				for(var elem in _eventHandlers["dragEnd"]){
					_eventHandlers["dragEnd"][elem]({
						mapPoint : new Geometry({
							type : GeometryType.POINT,
							points : e.point.lng + "," + e.point.lat
						})
					});
				}				 											
			});												
		}
		/* 从地图删除鼠标监听----*/
		function removeMouseDragEvent(){
			if(!_variableTypes.isEmptyObject(_eventHandlers["dragStart"])){
				_eventHandlers["dragStart"] = {};
			}
			if(!_variableTypes.isEmptyObject(_eventHandlers["dragMove"])){
				_eventHandlers["dragMove"] = {};
			}
			if(!_variableTypes.isEmptyObject(_eventHandlers["dragEnd"])){
				_eventHandlers["dragEnd"] = {};
			}
		}		
          /*为地图设置缩放事件监听*/
		function setZoomEvent(obj){
			var _zoomStartId,_zoomingId,_zoomEndId;
			if(_variableTypes.isFunc(obj.zoomstart)){
				_zoomStartId = MapFactory.GenerateID();
				_eventHandlers["zoomStart"][_zoomStartId] = obj.zoomstart;
			}
			if(_variableTypes.isFunc(obj.zooming)){
				_zoomId = MapFactory.GenerateID();
				_eventHandlers["zooming"][_zoomingId] = obj.zooming;
			}
			if(_variableTypes.isFunc(obj.zoomend)){
				_zoomEndId = MapFactory.GenerateID();
				_eventHandlers["zoomEnd"][_zoomEndId] = obj.zoomend;
			}
			return {
				zoomStart : _zoomStartId,
				zooming : _zoomingId,
				zoomEnd : _zoomEndId
			};
		}		
        /*为地图添加缩放事件----*/
		function _zoomEvt(){
			_mapHandler.addEventListener("zoomstart",function(e){				
				for(var elem in _eventHandlers["zoomStart"]){
					_eventHandlers["zoomStart"][elem](getCurrentExtent());
				}
			});
			
			_mapHandler.addEventListener("zoomend",function(e){
				for(var elem in _eventHandlers["zoomEnd"]){
					_eventHandlers["zoomEnd"][elem](getCurrentExtent());
				}
			});
		}		
		/*设置地图鼠标移动事件*/
		function setMouseMoveEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseMoveId = MapFactory.GenerateID();
			_eventHandlers["mouseMove"][_mouseMoveId] = func;
			return _mouseMoveId;
		}       
		/*设置地图鼠标移动事件*/
		function _mouseMoveEvt(){
			_mapHandler.addEventListener("moveend",function(event){
				var _screenX = event.screenX,
					_screenY = event.screenY,
					_mapPoint = toMap(_screenX,_screenY);
				var _screenP = new Geometry({
					type : GeometryType.POINT,
					points : _screenX + "," + _screenY
				});
				var _mapP =  new Geometry({
					type : GeometryType.POINT,
					points : _mapPoint.lng+ "," + _mapPoint.lat
				});
				for(var elem in _eventHandlers["mouseMove"]){
					_eventHandlers["mouseMove"][elem]({
						screenPoint : _screenP,
						mapPoint : _mapP
					});
				}
			});
		}
		/*移除地图鼠标移动事件*/
		function removeMouseMoveEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["mouseMove"])){
				return;
			}
			_eventHandlers["mouseMove"] = {};
		}						
		/*设置地图鼠标按下事件*/
		function setMouseDownEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseDownId = MapFactory.GenerateID();
			_eventHandlers["mouseDown"][_mouseDownId] = func;
			return _mouseDownId;
		}
		/*设置地图鼠标按下事件*/
		function _mouseDownEvt(){
			_mapHandler.addEventListener("mousedown",function(event){
				var _screenX = event.screenX,
					_screenY = event.screenY,
					_mapPoint = toMap(_screenX,_screenY);
				var _screenP = new Geometry({
					type : GeometryType.POINT,
					points : _screenX + "," + _screenY
				});
				var _mapP =  new Geometry({
					type : GeometryType.POINT,
					points : _mapPoint.lng + "," + _mapPoint.lat
				});
				for(var elem in _eventHandlers["mouseDown"]){
					_eventHandlers["mouseDown"][elem]({
						which : event.which,
						button : event.button,
						screenPoint : _screenP,
						mapPoint : _mapP
					});
				}
			},true);
		}
		/*删除地图鼠标按下事件*/
		function removeMouseDownEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["mouseDown"])){
				return;
			}
			_eventHandlers["mouseDown"] = {};
		}
		/*设置地图鼠标回弹事件*/
		function setMouseUpEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseUpId = MapFactory.GenerateID();
			_eventHandlers["mouseUp"][_mouseUpId] = func;
			return _mouseUpId;
		}
		/*设置地图鼠标回弹事件*/
		function _mouseUpEvt(){
			_mapHandler.addEventListener("mouseup",function(event){
				var _screenX = event.screenX,
					_screenY = event.screenY,
					_mapPoint = toMap(_screenX,_screenY);
				var _screenP = new Geometry({
					type : GeometryType.POINT,
					points : _screenX + "," + _screenY
				});
				var _mapP =  new Geometry({
					type : GeometryType.POINT,
					points : _mapPoint.lng + "," + _mapPoint.lat
				});
				for(var elem in _eventHandlers["mouseUp"]){
					_eventHandlers["mouseUp"][elem]({
						which : event.which,
						button : event.button,
						screenPoint : _screenP,
						mapPoint : _mapP
					});
				}
			});
		}
		/*删除地图鼠标回弹事件*/
		function removeMouseUpEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["mouseUp"])){
				return;
			}
			_eventHandlers["mouseUp"] = {};
		}
       /*设置地图鼠标单击事件*/
		function setMouseClickEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseClickId = MapFactory.GenerateID();
			_eventHandlers["mouseClick"][_mouseClickId] = func;
			return _mouseClickId;
		}
		 /*设置地图鼠标单击事件*/
		function _mouseClickEvt(){
			_mapHandler.addEventListener("click",function(event){
				   var _mapPoint=event.point;
				   var pixel=event.pixel;
				for(var elem in _eventHandlers["mouseClick"]){
					_eventHandlers["mouseClick"][elem]({
						screenPoint : new Geometry({
							type : GeometryType.POINT,
							points : pixel.x + "," + pixel.y
						}),
						mapPoint : new Geometry({
							type : GeometryType.POINT,
							points : _mapPoint.lng + "," + _mapPoint.lat
						})
					});
				}
			});
		}
		 /*删除地图鼠标单击事件*/
		function removeMouseClickEvent(){
			if(!_variableTypes.isEmptyObject(_eventHandlers["mouseClick"])){
				_eventHandlers["mouseClick"] = {};
			}
		}
		 /*设置地图鼠标双击事件*/
		function setMouseDoubleClickEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseDbClickId = MapFactory.GenerateID();
			_eventHandlers["mouseDbClick"][_mouseDbClickId] = func;
			return _mouseDbClickId;
		}
		function _dbClickEvt(){
			_mapHandler.addEventListener("dblclick",function(event){
				   var _mapPoint=event.point;
				   var pixel=event.pixel;
				for(var elem in _eventHandlers["mouseDbClick"]){
					_eventHandlers["mouseDbClick"][elem]({
						screenPoint : new Geometry({
							type : GeometryType.POINT,
							points : pixel.x + "," + pixel.y
						}),
						mapPoint : new Geometry({
							type : GeometryType.POINT,
							points : _mapPoint.lng + "," + _mapPoint.lat
						})
					});
				}
			});									
		 }
		function removeMouseDoubleClickEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["mouseDbClick"])){
				return;
			}
			_eventHandlers["mouseDbClick"] = {};
		}
		 /*设置地图范围改变事件*/
		function setExtentChangeEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _extentChangeId = MapFactory.GenerateID();
			_eventHandlers["extentChange"][_extentChangeId] = func;
			return _extentChangeId;
		}
		 /*设置地图范围改变事件*/
		function _extentChangeEvt(){
			_mapHandler.addEventListener("moveend",function(event){
				for(var elem in _eventHandlers["extentChange"]){
					_eventHandlers["extentChange"][elem](getCurrentExtent());
				}
			});
		}
		 /*地图范围改变事件*/
		function removeExtentChangeEvent(){
			if(_variableTypes.isEmptyObject(_eventHandlers["extentChange"])){
				return;
			}
			_eventHandlers["extentChange"] = {};
		}
		function setMouseWheelEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _mouseWheelId = MapFactory.GenerateID();
			_eventHandlers["mouseWheel"][_mouseWheelId] = func;
			return _mouseWheelId;
		}
		function _mouseWheelEvt(){
			_events.onMouseWheel(document.getElementById(_conf.src),function(e){
				var _directionFlag = e.wheelDelta > 0 ? "in" : "out";
				for(var elem in _eventHandlers["mouseWheel"]){
					_eventHandlers["mouseWheel"][elem]({
						wheelDirection : _directionFlag
					});
				}
				return false;
			});
		}		
		/*获取地图空间坐标系*/
		function getSpatialReferenceCode(){
			


		}
		/*获取地图投影坐标系*/
		function getProjectSpatialReferenceCode(){


		}		
      /* 获取地图初始化范围*/
		function getInitExtent(){
			
		}
		/*重新设置地图大小*/
		function resize(){
		
		}
		
         /*获取地图初始化配置设置*/
		function getMapConfig(){
			
		}       		
		/*暂时无法实现*/
		function disablePan(){
		
		}
        /*暂时无法实现*/
		function enablePan(){
			
		}
		  /*判断地图图层是否存在*/
		function isLayerExist(layerid){
			return MapFactory._layermanager[layerid]==null?false:true
		}
		  /*放大地图到一级别*/
		function zoomIn(){
			_mapHandler.zoomIn();
		}
		  /*放小地图到一级别*/
		function zoomOut(){
			_mapHandler.zoomOut();
		}
         /*获取地图大小*/
		function getMapSize(){
			return {
				width : _mapHandler.getSize().width,
				height : _mapHandler.getSize().height
			};
		}
		return eval(MapFactory.GenerateAPI(api));
	}
});