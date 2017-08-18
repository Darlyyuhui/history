MapFactory.Define("MapFactory/Draw",[
    "MapFactory/DrawAPI*",
    "MapFactory/LayerManager",
    "MapFactory/GeometryType*",
    "MapFactory/GeometryUtil"
],function(api,layerManager,geoType,GeometryUtil){

	return function(){
		var _callback,_mapHandler = MapFactory._MapManagerResource[MapFactory.Engine],_type,_pointsym,_linesym,_polygonsym;
	    var  _drawingManager = null; 		
	    var _geoUtil=GeometryUtil();
		function setGeoType(geometryType){
			  _type = geometryType;
		}

		function setFillSymbol(fillSymbol){
			_polygonsym=fillSymbol;
		}

		function setLineSymbol(lineSymbol){
			_linesym=lineSymbol;
		}

		function setMarkerSymbol(markerSymbol){
			_pointsym=markerSymbol;
		}

		function setDrawEndEvent(func){
			_callback = func;
		}

		function openSnapping(snapConf){
	
		
		}

		function closeSnapping(){
			
		}

		function activate(){						
				_drawingManager=new BMapLib.DrawingManager(_mapHandler,{					
					    isOpen: false, //是否开启绘制模式
				        enableDrawingTool:false, //是否显示工具栏
				        drawingToolOptions: {
				            anchor: BMAP_ANCHOR_BOTTOM_RIGHT, //位置
				            offset: new BMap.Size(5, 5), //偏离值
				        },
				        markerOptions: _pointsym?_pointsym:"",
				        polylineOptions: _linesym?_linesym:"", 
				        polygonOptions: _polygonsym?_polygonsym:""
				}); 							
			_drawingManager.setDrawingMode(_type);
			_drawingManager.open();
			_drawingManager.addEventListener('overlaycomplete', function(event){			
				  var keys=MapFactory.OverLayerMap.keys;
	                for(var index in keys){               	
	                	_mapHandler.removeOverlay(MapFactory.OverLayerMap.get(keys[index]));                	
	                }
	                MapFactory.OverLayerMap.clear();
				_callback(_geoUtil.convertFromObject(event.overlay));
				  
			  });			
		}		
		function _snappingInit(){
			
		}
		function deactivate(){
			_drawingManager.close();
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});