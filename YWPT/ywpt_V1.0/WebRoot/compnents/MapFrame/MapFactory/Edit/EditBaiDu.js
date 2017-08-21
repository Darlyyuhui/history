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
			
		}

		function setLayerID(layerID){
			
		}

		function activate(){
			

		function deactivate(){
			
		}
		return eval(MapFactory.GenerateAPI(api));
	}}
});