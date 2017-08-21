MapFactory.Define("MapFactory/Edit",[
    "MapFactory/EditAPI*",
    "MapFactory/GraphicManager"
],function(api,graphicManager){
	return function(){
		var _edit,
			_mode=0,_graphic = null,
			_mapHandler = MapFactory._MapManagerResource[MapFactory.Engine],
			_layer;

		function setEditMode(mode){
			if(!MapFactory.VariableTypes.isString(mode)){
				return;
			}
			var _rotateReg = /rotate/ig,
				_moveReg = /move/ig,
				_editVertices = /editvertices/ig,
				_scale = /scale/ig;
			if(mode.match(_editVertices)){
				_mode |= OpenLayers.Control.ModifyFeature.RESHAPE;
			}
			if(mode.match(_rotateReg)){
				_mode |= OpenLayers.Control.ModifyFeature.ROTATE;
			}
			if(mode.match(_moveReg)){
				_mode |= OpenLayers.Control.ModifyFeature.DRAG;
			}
			if(mode.match(_scale)){
				_mode |= OpenLayers.Control.ModifyFeature.RESIZE;
			}
		}

		function setLayerID(layerID){
			_layer = _mapHandler.getLayer(layerID);
		}

		function activate(){
			var _options = {
					mode : _mode,
					createVertices : true
				};
			_edit = new OpenLayers.Control.ModifyFeature(_layer,_options);
			_mapHandler.addControl(_edit);
			_edit.activate();
		}

		function deactivate(){
			if(_edit){
				_edit.deactivate();
			}
		}
		return eval(MapFactory.GenerateAPI(api));
	}
});