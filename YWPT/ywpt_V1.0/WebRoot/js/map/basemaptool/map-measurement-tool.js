var measurementTool = {
	_measurementDijit : null,
	show : function(){

		var _measurement = this._measurementDijit;

		//添加测量工具
		itmap.util.dialog().conf({
			title:'测量',
			content:'<div id="measurementDIV" style="width:240px;"></div>',
			mask:false,
			zindex:999,
			right : 250,
			top : 50,
			closeCall:measurementDestroy,
			buttonDisplay:{
				confirmButton:false,
				cancelButton:false
			}
		}).show();

		if(_measurement!=null){
			_measurement.destroy();
		}
		esri.config.defaults.geometryService = new esri.tasks.GeometryService(baseServiceURL.geometry.url);  
		this._measurementDijit = _measurement = new esri.dijit.Measurement({
			map:itmap.arcgis.map
		},dojo.byId('measurementDIV'));
		_measurement.startup();

		function measurementDestroy(){
			measurementTool._measurementDijit.destroy();
			measurementTool._measurementDijit = null;
		}

	}
}