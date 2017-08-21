MapFactory.Define("MapFactory/MeasureMent",[
    "MapFactory/MeasureMentAPI*",
    "MapFactory/Util/Dialog*",
    "esri/tasks/GeometryService*",
    "esri/dijit/Measurement*",
    "esri/geometry/geodesicUtils*",
    "esri/units*",
    "MapFactory/GeometryUtil"
],function(api,Dialog,GeometryService,MeasureMent,geodesicUtils,Units,geoUtil){
	var _measurement, _geometryService;
	var _geoUtil = geoUtil();
	return function(){
		var _url,_d;

		function getLength(geo) {
			var lengths = geodesicUtils.geodesicLengths([_geoUtil.convertFromMapFactory(geo)], Units.METERS);
			return lengths[0];
		}

		function setGeometryServiceUrl(url){
			_url = url;
		}

		function show(){
			_init();
		}

		function hide(){
			_d.hide();
		}
		
		function _initContainer() {
			_d = Dialog({
				mutiDialog : true,
				mutiDialogSeed : "measurement",
				title:'测量',
				mask:false,
				zindex:999,
				right : 10,
				top : 35,
				closeCall : _measurementDestroy,
				buttonDisplay:{
					confirmButton:false,
					cancelButton:false
				}
			});
		}

		function _init(){
			if(!_url){
				return;
			}
			_initContainer();
			_d.setDialogContent('<div id="measurementDIV" style="width:240px;"></div>');
			_d.show();
			if(_measurement!=null){
				_measurement.destroy();
			}
			esri.config.defaults.geometryService = new GeometryService(_url);
			_measurement = new MeasureMent({
				map:MapFactory._MapManagerResource[MapFactory.Engine]
			},dojo.byId('measurementDIV'));
			_measurement.startup();
			var _measurementWidget = document.getElementById("measurementDIV"),
				_children = _measurementWidget.getElementsByTagName("span"),
				_pointToolMeasure = null,
				_lineToolMeasure = null,
				_polygonToolMeasure = null;
			for(var i=0,len=_children.length;i<len;i++){
				var widgetid = _children[i].getAttribute("widgetid");
				if(!widgetid){
					continue;
				}
				if(!_polygonToolMeasure){
					_polygonToolMeasure = _children[i];
					continue;
				}
				if(!_lineToolMeasure){
					_lineToolMeasure = _children[i];
					continue;
				}
				if(!_pointToolMeasure){
					_pointToolMeasure = _children[i];
					continue;
				}
			}
			_measurementWidget.insertBefore(_pointToolMeasure,_polygonToolMeasure);
			_measurementWidget.insertBefore(_lineToolMeasure,_polygonToolMeasure);
		}

		function _measurementDestroy(){
			_measurement.destroy();
			_measurement = null;
		}
		return eval(MapFactory.GenerateAPI(api));
	}
});