


/**
	 * 地图部分
	 **/
	dojo.require("esri.map");
	dojo.require("esri.toolbars.edit");
	dojo.require("esri.tasks.geometry");
	dojo.require("esri.layers.graphics");
	dojo.require("esri.tasks.find");
	var map;
	var _edittool = null;
	var _editHandler = null;
	var _currentGeometry = null;
	function init(){
		map = new esri.Map("mapDiv",{
			logo : false,
			slider : true,
			sliderStyle: "large"
		});
		dojo.connect(map,"onLoad",function(){
			/**
			 * 设置导航按钮
			 */
			new itmap.arcgis.navigation({
				mapC : map,
				initExtent : itmap.arcgis.getInitExtent(),
				container : "mapNavigationBox"
			});
			
			map.centerAt(itmap.arcgis.getInitExtent().getCenter());
			
			if(typeof parent.mapOnLoad=="function")parent.mapOnLoad(map);
		});
		
		var tiledMapServiceLayer = new esri.layers.ArcGISTiledMapServiceLayer(baseServiceURL.basemapnew.url);
		map.addLayer(tiledMapServiceLayer);
		// 添加一个bufferGraphicLayer
		bufferGraphicLayer = new esri.layers.GraphicsLayer();
		bufferGraphicLayer.id = "bufferGraphicLayer";
		map.addLayer(bufferGraphicLayer);
	}
	
	var bufferGraphicLayer;
	dojo.addOnLoad(init);

	function editPolygon(){
		_editHandler = dojo.connect(map.graphics,"onClick",function(evt){
			dojo.stopEvent(evt);
			_edit(evt.graphic);
		});
	}
	
	function stopEditPolygon(){
		if(_edittool){
			_edittool.deactivate();
			//_edittool = null;
		}
		dojo.disconnect(_editHandler);
	}
	
	function _edit(graphic){
		var _options = {
			allowAddVertices : true,
			allowDeletevertices : true,
			uniformScaling : true
		};
		var _tool = esri.toolbars.Edit.MOVE | esri.toolbars.Edit.EDIT_VERTICES | esri.toolbars.Edit.SCALE | esri.toolbars.Edit.ROTATE;
		if(!_edittool){
			_edittool = new esri.toolbars.Edit(map);
			dojo.connect(_edittool,"onDeactivate", function(tool,graphic,info) {
	          if(info.isModified){
	        	  bufferGraphicLayer.clear();
	        	  buffer(graphic.geometry);
	          }
	        });
		}
		_edittool.activate(_tool,graphic,_options);
	}

	function drawPoint(){
		var _toolbar = new esri.toolbars.Draw(map);
		_toolbar.activate(esri.toolbars.Draw.POINT);
		dojo.connect(_toolbar,"onDrawEnd",function(geometry){
			var _symbol = pointSymbol();
			pointGraphicLayer.add(new esri.Graphic(geometry,_symbol));
			_toolbar.deactivate();
			buffer(geometry);
		});
	}

	function drawPolyline(){
		var _toolbar = new esri.toolbars.Draw(map);
		_toolbar.activate(esri.toolbars.Draw.POLYLINE);
		dojo.connect(_toolbar,"onDrawEnd",function(geometry){
			var _symbol = polylineSymbol();
			pointGraphicLayer.add(new esri.Graphic(geometry,_symbol));
			_toolbar.deactivate();
			buffer(geometry);
		});
	}

	function drawPolygon(){
		var _toolbar = new esri.toolbars.Draw(map);
		_toolbar.activate(esri.toolbars.Draw.FREEHAND_POLYGON);
		dojo.connect(_toolbar,"onDrawEnd",function(geometry){
			var _symbol = polygonSymbol();
			pointGraphicLayer.add(new esri.Graphic(geometry,_symbol));
			_toolbar.deactivate();
		});
	}
	
	function clearMap() {
		map.graphics.clear();
		pointGraphicLayer.clear();
		bufferGraphicLayer.clear();
		$("#edit_btn").val("停止编辑");
		stopEditPolygon();
	}
	
	function pointSymbol(){
		return new esri.symbol.SimpleMarkerSymbol(
					esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE, 10,
					new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,new dojo.Color([255,0,0]), 1),
					new dojo.Color([0,255,0,0.25])
			   );
	}
	
	function polylineSymbol(){
		return new esri.symbol.SimpleLineSymbol(
					esri.symbol.SimpleLineSymbol.STYLE_SOLID,
					new dojo.Color([255,0,0]),3
		       );
	}
	
	function polygonSymbol(){
		return new esri.symbol.SimpleFillSymbol(
				   esri.symbol.SimpleFillSymbol.STYLE_SOLID,
				   new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,
				   new dojo.Color([255,0,0]), 2),
				   new dojo.Color([255,255,0,0.25])
			   );
	}
	
	function buffer(geometry){
		itmap.arcgis.buffer.bufferSearch(geometry, function(returnGeometry){
			bufferGraphicLayer.add(new esri.Graphic(returnGeometry[0],polygonSymbol()));
		});
	}
	
	function getPolygon(geometry) {
		var result = [];
		for (var i in geometry.rings) {
			var path = geometry.rings[i];
			for(var j in path) {
				var point = path[j];
				result.push(point[0], point[1]);
			}
		}
		return result;
	}

	function addBufferArea(extentid) {
		var query = new esri.tasks.Query();
		var qTask = new esri.tasks.QueryTask(baseServiceURL.gpsextent.url);
		query.outFields = ["*"];
		query.outSpatialReference = map.spatialReference;
		query.returnGeometry = true;
		query.where = "EXTENTID = " + extentid;
		qTask.execute(query,function(featureset){
			bufferGraphicLayer.add(new esri.Graphic(featureset.features[0],polygonSymbol()));
		},function(){
		
		});
	}

	function updateBufferArea() {
		buffer(_currentGeometry);
	}