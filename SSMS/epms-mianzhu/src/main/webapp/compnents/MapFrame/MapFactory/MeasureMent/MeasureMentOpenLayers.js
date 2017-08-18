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
		wkt = new OpenLayers.Format.WKT();
		_mousemoveFlag = false;
	return function(conf){

        var _conf = {
            mapDiv: "",
            right: 10,//默认显示位置
            top: 35//默认显示位置
        };

        MapFactory.Extend(_conf,conf);

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
			var style = new OpenLayers.Style();
			style.addRules([
			   new OpenLayers.Rule({symbolizer: sketchSymbolizers})
			]);
            var styleMap = new OpenLayers.StyleMap({"default": style});
			measureControls = {
				line: new OpenLayers.Control.Measure(
					OpenLayers.Handler.Path, {
						persist: true,
						handlerOptions: {
							layerOptions: {
								renderers: OpenLayers.Layer.Vector.prototype.renderers,
								styleMap : styleMap
							}
						}
					}
				),
				polygon: new OpenLayers.Control.Measure(
					OpenLayers.Handler.Polygon, {
						persist: true,
						handlerOptions: {
							layerOptions: {
								renderers: OpenLayers.Layer.Vector.prototype.renderers,
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
            	new OpenLayers.Control.MousePosition({
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
                mapDiv: _conf.mapDiv,
				mask : false,
				mutiDialog : true,
				mutiDialogSeed : "MeasureMent",
				title : "测量",
				content : "<div id='MeasureMentContainer'></div>",
				right : _conf.right,
				top : _conf.top,
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				},
				closeCall : _deactivate
			}).show();
			_container = $("#MeasureMentContainer");
			var _divgap = $("<div id='MeasureMentDiv'></div>");
			_buttons = $("<div id='MeasureMentButtons'></div>");
			_areaBtn = $("<div id='MeasureMentArea'><img src='"+path+"/images/map/Measure_Area16.png'/></div>");
			_lineBtn = $("<div id='MeasureMentLine'><img src='"+path+"/images/map/Measure_Distance16.png'/></div>");
			_pointBtn = $("<div id='MeasureMentPoint'><img src='"+path+"/images/map/Measure_Point16.png'/></div>");
			_resbox = $("<div id='MeasureMentResult'></div>");
			_buttons.append(_pointBtn);
			_buttons.append(_lineBtn);
			_buttons.append(_areaBtn);
			_container.append(_buttons);
			_container.append(_divgap);
			_container.append(_resbox);
		}

		function _handleMeasurements(event) {
			var _geo = event.geometry;
			var units = event.units;
			var order = event.order;
			var measure = event.measure;
			var out = "";

            if(order == 1) {
            	var dd = _getLength(_geoUtil.convertFromObject(_geo));
            	if(dd < 1)dd*=1000;
                out += "结果: " + dd.toFixed(3) + " " + units;
            } else {
            	var dd = _getAreas(_geoUtil.convertFromObject(_geo));
            	dd = Math.abs(dd);
            	if(dd < 1)dd*=1000;
                out += "结果: " + dd.toFixed(3) + " " + units + "<sup>2</" + "sup>";
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
			var dd = _getLength(_geoUtil.convertFromObject(geo));
			return dd*1000;// 转换为m单位
		}
		
		////////////////////计算距离/////////////////////
		function _getLength(geo) {
			var _58 = geo.points.split("|");
			var _59 = 'esriKilometers';
			var _5a=Math.PI/180;
			var _5b=[];
			var _5d=0;
			for(var j=0,len=_58.length; j<len; j++) {
				var _5e = _58[j].split(",");
				var _5f=0;var i,_60,_61,_62,_63,_64;
				for(i=3;i<_5e.length;i+=2){
					_60=_5e[i-3]*_5a;
					_61=_5e[i-1]*_5a;
					_62=_5e[i-2]*_5a;
					_63=_5e[i]*_5a;
					_64=_25(_62,_60,_63,_61);
					_5f+=_64.geodesicDistance/1609.344;
				}
				_5d+=_5f;
			}
			_5d*=_9[_59];
			return _5d;
		}
		var _9={"esriMiles":1,"esriKilometers":1.609344,"esriFeet":5280,"esriMeters":1609.34,"esriYards":1760,"esriNauticalMiles":0.869,"esriCentimeters":160934,"esriDecimeters":16093.4,"esriInches":63360,"esriMillimeters":1609340,"esriAcres":1,"esriAres":40.4685642,"esriSquareKilometers":0.00404685642,"esriSquareMiles":0.0015625,"esriSquareFeet":43560,"esriSquareMeters":4046.85642,"esriHectares":0.404685642,"esriSquareYards":4840,"esriSquareInches":6272640,"esriSquareMillimeters":4046856420,"esriSquareCentimeters":40468564.2,"esriSquareDecimeters":404685.642};
		function _25(_26,_27,_28,_29){
			if(_26 == _28 && _27 == _29)return {"azimuth":0,"geodesicDistance":0};
			var a=6378137,b=6356752.31424518,f=1/298.257223563;
			var L=(_29-_27);
			var U1=Math.atan((1-f)*Math.tan(_26));
			var U2=Math.atan((1-f)*Math.tan(_28));
			var _2a=Math.sin(U1),_2b=Math.cos(U1);
			var _2c=Math.sin(U2),_2d=Math.cos(U2);
			var _2e=L,_2f,_30=1000;
			var _31,_32,_33,_34,_35;
			do{
				var _36=Math.sin(_2e),_37=Math.cos(_2e);
				_32=Math.sqrt((_2d*_36)*(_2d*_36)+(_2b*_2c-_2a*_2d*_37)*(_2b*_2c-_2a*_2d*_37));
				if(_32===0){return 0;}
				_34=_2a*_2c+_2b*_2d*_37;
				_35=Math.atan2(_32,_34);
				var _38=_2b*_2d*_36/_32;
				_31=1-_38*_38;
				_33=_34-2*_2a*_2c/_31;
				if(isNaN(_33)){
					_33=0;
				}
				var C=f/16*_31*(4+f*(4-3*_31));
				_2f=_2e;
				_2e=L+(1-C)*f*_38*(_35+C*_32*(_33+C*_34*(-1+2*_33*_33)));
			}while(Math.abs(_2e-_2f)>1e-12&&--_30>0);
			if(_30===0){
				var _39=6371009;
				var _3a=Math.acos(Math.sin(_26)*Math.sin(_28)+Math.cos(_26)*Math.cos(_28)*Math.cos(_29-_27))*_39;
				var _3b=_29-_27;var y=Math.sin(_3b)*Math.cos(_28);
				var x=Math.cos(_26)*Math.sin(_28)-Math.sin(_26)*Math.cos(_28)*Math.cos(_3b);
				var _3c=Math.atan2(y,x);
				return {"azimuth":_3c,"geodesicDistance":_3a};
			}
			var uSq=_31*(a*a-b*b)/(b*b);
			var A=1+uSq/16384*(4096+uSq*(-768+uSq*(320-175*uSq)));
			var B=uSq/1024*(256+uSq*(-128+uSq*(74-47*uSq)));
			var _3d=B*_32*(_33+B/4*(_34*(-1+2*_33*_33)-B/6*_33*(-3+4*_32*_32)*(-3+4*_33*_33)));
			var s=b*A*(_35-_3d);
			var _3e=Math.atan2(_2d*Math.sin(_2e),_2b*_2c-_2a*_2d*Math.cos(_2e));
			var _3f=Math.atan2(_2b*Math.sin(_2e),_2b*_2c*Math.cos(_2e)-_2a*_2d);
			var _40={azimuth:_3e,geodesicDistance:s,reverseAzimuth:_3f};
			return _40;
		}
		
		///////////////////////////计算面积////////////////////////
		var _6 = function(x, y){
			this.x = x;
			this.y = y;
		}
		function _getAreas(geo){
			var _68 = geo.points.split("|");
			var _67 = 'esriSquareKilometers';
			var _6b=[];
			var _6c,_6d;
			for(var j=0,jl=_68.length; j<jl; j++) {
				var _6f=0, _71=0;
				var _6e = _68[j].split(",");
				_6e = _41(_6e,10000);
				for(var i=3,len=_6e.length-1;i<len;i+=2){
					_6c=_a(new _6(_6e[i-1],_6e[i]));
					_6d=_a(new _6(_6e[i-3],_6e[i-2]));
					_71+=_6d.x*_6c.y-_6c.x*_6d.y;
				}
				_71/=4046.87;
				_6f+=_71;
				
				_6f*=_9[_67];
				_6b.push(_6f/(-2));
			}
			if(isNaN(_6b[0]))return 0;
			return _6b[0];
		}
		function _a(pt){
			var _b=Math.PI/180;
			var a=6378137;
			var _c=0.006694379990197414,e=0.0818191908429643;
			var _d=Math.sin(pt.y*_b);
			var q=(1-_c)*((_d/(1-_c*(_d*_d))-(1/(2*e))*Math.log((1-e*_d)/(1+e*_d))));
			var x=a*pt.x*_b;
			var y=a*q*0.5;
			var _e=new _6(x,y);
			return _e;
		}
		function _41(_42,_43){
			var _44=Math.PI/180;
			var _45=6371008.771515059;
			if(_43<_45/10000){_43=_45/10000;}
			var _48=[],_49;
			_48.push(_49=[]);
			_49.push(parseFloat(_42[0]),parseFloat(_42[1]));// 添加第一个点
			var _4b,_4c,_4d,_4e,i,j;
			_4b=_42[0]*_44;
			_4c=_42[1]*_44;
			
			for(var k=0,kl=_42.length; k<kl; k+=2) {
				_4d=_42[k+2]*_44;
				_4e=_42[k+3]*_44;
				var _4f=_25(_4c,_4b,_4e,_4d);
				var _50=_4f.azimuth;
				var _51=_4f.geodesicDistance;
				var _52=_51/_43;
				if(_52>1){
					for(j=1;j<=_52-1;j++){
						var _53=j*_43;
						var pt=_f(_4c,_4b,_50,_53);
						_49.push(pt.x,pt.y);
					}
					var _54=(_51+Math.floor(_52-1)*_43)/2;
					var _55=_f(_4c,_4b,_50,_54);
					_49.push(_55.x,_55.y);
				}
				var _56=_f(_4c,_4b,_50,_51);
				_49.push(_56.x,_56.y);
				_4b=_56.x*_44;
				_4c=_56.y*_44;
			}
			return _49;
		}
		function _f(_10,_11,_12,s){
			var a=6378137,b=6356752.31424518,f=1/298.257223563;
			var _13=Math.sin(_12);
			var _14=Math.cos(_12);
			var _15=(1-f)*Math.tan(_10);
			var _16=1/Math.sqrt((1+_15*_15)),_17=_15*_16;
			var _18=Math.atan2(_15,_14);
			var _19=_16*_13;
			var _1a=1-_19*_19;
			var uSq=_1a*(a*a-b*b)/(b*b);
			var A=1+uSq/16384*(4096+uSq*(-768+uSq*(320-175*uSq)));
			var B=uSq/1024*(256+uSq*(-128+uSq*(74-47*uSq)));
			var _1b=s/(b*A),_1c=2*Math.PI;
			var _1d,_1e,_1f;
			while(Math.abs(_1b-_1c)>1e-12){
				_1f=Math.cos(2*_18+_1b);
				_1d=Math.sin(_1b);
				_1e=Math.cos(_1b);
				var _20=B*_1d*(_1f+B/4*(_1e*(-1+2*_1f*_1f)-B/6*_1f*(-3+4*_1d*_1d)*(-3+4*_1f*_1f)));
				_1c=_1b;
				_1b=s/(b*A)+_20;
			}
			var tmp=_17*_1d-_16*_1e*_14;
			var _21=Math.atan2(_17*_1e+_16*_1d*_14,(1-f)*Math.sqrt(_19*_19+tmp*tmp));
			var _22=Math.atan2(_1d*_13,_16*_1e-_17*_1d*_14);
			var C=f/16*_1a*(4+f*(4-3*_1a));
			var L=_22-(1-C)*f*_19*(_1b+C*_1d*(_1f+C*_1e*(-1+2*_1f*_1f)));
			var _23=_21/(Math.PI/180);
			var _24=(_11+L)/(Math.PI/180);
			var pt=new _6(_24,_23);
			return pt;
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