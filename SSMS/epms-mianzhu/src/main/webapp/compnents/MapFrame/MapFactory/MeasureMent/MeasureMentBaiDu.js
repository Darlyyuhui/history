MapFactory.Define("MapFactory/MeasureMent",[
    "MapFactory/MeasureMentAPI*",
    "MapFactory/Util/Dialog*",
    "MapFactory/GeometryType*"
],function(api,Dialog,GeometryType){
	var _dialog = null,
		_container = null,
		_resbox = null,
		_areaBtn,_lineBtn,_pointBtn,
		_buttons = null,
		_mousemoveFlag = false;
	return function(){
		var label=null,overlay=null;
		var map = MapFactory._MapManagerResource[MapFactory.Engine];	
		function _int(){	
			var sketchSymbolizers = {
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
			var _drawingManager=new BMapLib.DrawingManager(map,{					
			    isOpen: false, //是否开启绘制模式
		        enableDrawingTool:false, //是否显示工具栏
		        drawingToolOptions: {
		            anchor: BMAP_ANCHOR_BOTTOM_RIGHT, //位置
		            offset: new BMap.Size(5, 5), //偏离值
		        },
		        markerOptions:"",
		        polylineOptions:sketchSymbolizers.Line, 
		        polygonOptions: sketchSymbolizers.Polygon
		}); 	
			_drawingManager.enableCalculate();
			_drawingManager.addEventListener('overlaycomplete', function(event){				
				    var keys=MapFactory.OverLayerMap.keys;
	                for(var index in keys){               	
	                	map.removeOverlay(MapFactory.OverLayerMap.get(keys[index]));                	
	                }
	                MapFactory.OverLayerMap.clear();
	                if(label){
	                	 map.removeOverlay(label);
	                }
	                if(overlay){
	                	 map.removeOverlay(overlay);	                	
	                }
	                overlay=event.overlay;
	                label=event.label;
				   map.addOverlay(overlay);	
				   map.addOverlay(label);
			  });
            _areaBtn.toggle(function(){
            	$(this).addClass("buttonClicked");
            	 _lineBtn.removeClass("buttonClicked");
            	 _pointBtn.removeClass("buttonClicked");
            	 _mousemoveFlag = false;
            	 _drawingManager.close();
                 _drawingManager.setDrawingMode(GeometryType.POLYGON);
     	         _drawingManager.open();
            
            },function(){
            	$(this).removeClass("buttonClicked");
            	 _drawingManager.close();
            });

            _lineBtn.toggle(function(){
            	$(this).addClass("buttonClicked");
            	_areaBtn.removeClass("buttonClicked");
            	_pointBtn.removeClass("buttonClicked");
            	_mousemoveFlag = false; 
            	_drawingManager.close();
            	_drawingManager.setDrawingMode(GeometryType.POLYLINE);           	
       	        _drawingManager.open();
            },function(){
            	$(this).removeClass("buttonClicked");
            	 _drawingManager.close();
            });

            map.addEventListener("mousemove",function(e){
            	if(_mousemoveFlag){        
                	var mapPoint = e.point;
                	_resbox.html("结果： "+mapPoint.lng.toFixed(5)+" "+mapPoint.lat.toFixed(5));
            	}
            });
            _pointBtn.toggle(function(){
            	$(this).addClass("buttonClicked");
            	_areaBtn.removeClass("buttonClicked");
            	_lineBtn.removeClass("buttonClicked");
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
			  if(label){
             	 map.removeOverlay(label);
             }
              if(overlay){
             	 map.removeOverlay(overlay);	                	
              }
			_buttons.children("div").removeClass("buttonClicked");
		}
		
		function getLength(geo) {
		
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