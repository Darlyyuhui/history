MapFactory.Define("MapFactory/Edit",[
    "MapFactory/EditAPI*",
    "esri/toolbars/edit*",
    "MapFactory/LayerManager"
],function(api,editTool,layerManager){
	var _edit;
	return function(){
		var _mapHandler = MapFactory._MapManagerResource[MapFactory.Engine],
			_mode=0,_graphic = null,
			_layerMGM;
			
		var _clickHandler = null;

		function setEditMode(mode){
			if(!MapFactory.VariableTypes.isString(mode)){
				return;
			}
			var _rotateReg = /rotate/ig,
				_moveReg = /move/ig,
				_editVertices = /editvertices/ig,
				_scale = /scale/ig;
			if(mode.match(_rotateReg)){
				_mode |= editTool.ROTATE;
			}
			if(mode.match(_moveReg)){
				_mode |= editTool.MOVE;
			}
			if(mode.match(_editVertices)){
				_mode |= editTool.EDIT_VERTICES;
			}
			if(mode.match(_scale)){
				_mode |= editTool.SCALE;
			}
		}

		function setLayerID(layerID){
			_layerMGM = layerManager(layerID);
			
		}

		function activate(){
			_clickHandler = _layerMGM.addOnClickEvent(function(o){
				_graphic = MapFactory._MapGraphicResource[o.graphic.id];
				if(_edit){
					_edit.deactivate();
				}
				_edit = new editTool(_mapHandler);
				_edit.activate(_mode,_graphic);
			});
		}

		function deactivate(){
			if(_edit){
				_layerMGM.removeEvent(_clickHandler);
				_edit.deactivate();
			}
		}
		return eval(MapFactory.GenerateAPI(api));
	}
});