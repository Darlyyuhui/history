MapFactory.Define("MapFactory/MeasureMent",[
    "MapFactory/MeasureMentAPI*",
    "MapFactory/Util/Dialog*",
    "MapFactory/GeometryUtil"
],function(api,Dialog,geoUtil){
	var _dialog = null,
		_container = null,
		_resbox = null,
		measureControls = [],
		_areaBtn,_lineBtn,_pointBtn,
		_buttons = null,
		_geoUtil = geoUtil();
		wkt = new SuperMap.Format.WKT();
		_mousemoveFlag = false;
	return function(){

		function _int(){
			var map = MapFactory._MapManagerResource[MapFactory.Engine];
			var sketchSymbolizers = {
				"Point": {
					pointRadius: 4,
					graphicName: "square",
					fillColor: "white",
					fillOpacity: 1,
					strokeWidth: 1,
					strokeOpacity: 1,
					strokeColor: "#333333"
				},
				"Line": {
					strokeWidth: 3,
					strokeOpacity: 1,
					strokeColor: "#666666",
					strokeDashstyle: "dash"
				},
				"Polygon": {
					strokeWidth: 2,
					strokeOpacity: 1,
					strokeColor: "#666666",
					fillColor: "white",
					fillOpacity: 0.3
				}
			};
			var style = new SuperMap.Style();
			style.addRules([
			   new SuperMap.Rule({symbolizer: sketchSymbolizers})
			]);
            var styleMap = new SuperMap.StyleMap({"default": style});
			measureControls = {
				line: new SuperMap.Control.Measure(
						SuperMap.Handler.Path, {
						persist: true,
						handlerOptions: {
							layerOptions: {
								renderers: SuperMap.Layer.Vector.prototype.renderers,
								styleMap : styleMap
							}
						}
					}
				),
				polygon: new SuperMap.Control.Measure(
						SuperMap.Handler.Polygon, {
						persist: true,
						handlerOptions: {
							layerOptions: {
								renderers: SuperMap.Layer.Vector.prototype.renderers,
								styleMap : styleMap
							}
						}
					}
				)
			};
			var control;
            for(var key in measureControls) {
                control = measureControls[key];
                control.events.on({
                    "measure": _handleMeasurements,
                    "measurepartial": _handleMeasurements
                });
                map.addControl(control);
            }

            map.addControl(
            	new SuperMap.Control.MousePosition({
	                prefix: '',
		            separator: ' ',
		            numDigits: 2,
		            emptyString: ''
            	})
            );

            _areaBtn.toggle(function(){
            	$(this).addClass("buttonClicked");
            	_lineBtn.removeClass("buttonClicked");
            	_pointBtn.removeClass("buttonClicked");
            	_mousemoveFlag = false;
            	measureControls["polygon"].activate();
            	measureControls["line"].deactivate();
            },function(){
            	$(this).removeClass("buttonClicked");
            	measureControls["polygon"].deactivate();
            });

            _lineBtn.toggle(function(){
            	$(this).addClass("buttonClicked");
            	_areaBtn.removeClass("buttonClicked");
            	_pointBtn.removeClass("buttonClicked");
            	_mousemoveFlag = false;
            	measureControls["line"].activate();
            	measureControls["polygon"].deactivate();
            },function(){
            	$(this).removeClass("buttonClicked");
            	measureControls["line"].deactivate();
            });

            map.events.register("mousemove",map,function(e) {
            	if(_mousemoveFlag){
                	var position = this.events.getMousePosition(e);
                	var mapPoint = map.getLonLatFromViewPortPx(position);
                	_resbox.html("结果： "+mapPoint.lon.toFixed(5)+" "+mapPoint.lat.toFixed(5));
            	}
            });

            _pointBtn.toggle(function(){
            	$(this).addClass("buttonClicked");
            	_areaBtn.removeClass("buttonClicked");
            	_lineBtn.removeClass("buttonClicked");
            	measureControls["line"].deactivate();
            	measureControls["polygon"].deactivate();
            	_mousemoveFlag = true;
            },function(){
            	$(this).removeClass("buttonClicked");
            	_mousemoveFlag = false;
            });
		}

		function _initContainer(){
			_dialog = Dialog({
				mask : false,
				mutiDialog : true,
				mutiDialogSeed : "MeasureMent",
				title : "测量",
				content : "<div id='MeasureMentContainer'></div>",
				right : 10,
				top : 35,
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				},
				closeCall : _deactivate
			}).show();
			_container = $("#MeasureMentContainer");
			var _divgap = $("<div id='MeasureMentDiv'></div>");
			_buttons = $("<div id='MeasureMentButtons'></div>");
			_areaBtn = $("<div id='MeasureMentArea'><img src='images/map/Measure_Area16.png'/></div>");
			_lineBtn = $("<div id='MeasureMentLine'><img src='images/map/Measure_Distance16.png'/></div>");
			_pointBtn = $("<div id='MeasureMentPoint'><img src='images/map/Measure_Point16.png'/></div>");
			_resbox = $("<div id='MeasureMentResult'></div>");
			_buttons.append(_pointBtn);
			_buttons.append(_lineBtn);
			_buttons.append(_areaBtn);
			_container.append(_buttons);
			_container.append(_divgap);
			_container.append(_resbox);
		}

		function _handleMeasurements(event) {
			var units = event.units;
			var order = event.order;
			var measure = event.measure;
			var out = "";

            if(order == 1) {
                out += "结果: " + measure.toFixed(3) + " " + units;
            } else {
                out += "结果: " + measure.toFixed(3) + " " + units + "<sup>2</" + "sup>";
            }
			_resbox.html(out);
        }

		function _deactivate(){
			for(var elem in measureControls){
				measureControls[elem].deactivate();
			}
			_buttons.children("div").removeClass("buttonClicked");
		}
		
		function getLength(geo) {
			var prepoints = geo.points;
			var line = _geoUtil.convertFromMapFactory(geo);
			var map = MapFactory._MapManagerResource[MapFactory.Engine];
			var geoLength = line.getLength();
            var geomUnits = map.getUnits();//"degrees"
			var inPerDisplayUnit = SuperMap.INCHES_PER_UNIT["km"];
	        if (inPerDisplayUnit) {
	            var inPerMapUnit = SuperMap.INCHES_PER_UNIT[geomUnits];
	            geoLength *= (inPerMapUnit / inPerDisplayUnit);
	        }
	        if(geoLength > 1) {
		        return geoLength*1000;
	        }
	        else {
	        	geoLength = line.getLength();
	        	var inPerDisplayUnit = SuperMap.INCHES_PER_UNIT["m"];
		        if (inPerDisplayUnit) {
		            var inPerMapUnit = SuperMap.INCHES_PER_UNIT[geomUnits];
		            geoLength *= (inPerMapUnit / inPerDisplayUnit);
		        }
		        return geoLength;
	        }
		}

		function setGeometryServiceUrl(url){
			
		}

		function show(){
			if(!_dialog){
				_initContainer();
				_int();
			}else{
				_dialog.show()
			}
		}

		function hide(){
			if(_dialog){
				_dialog.hide();
			}
		}
		return eval(MapFactory.GenerateAPI(api));
	}
});