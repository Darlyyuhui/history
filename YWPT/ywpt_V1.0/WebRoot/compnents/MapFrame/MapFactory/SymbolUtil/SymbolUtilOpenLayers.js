MapFactory.Define("MapFactory/SymbolUtil",[
    "MapFactory/SymbolUtilAPI*",
    "MapFactory/GeometryType*",
	"MapFactory/SymbolConfig*"
],function(api,geoType,symbolConf){
	return function(){

		function convertFromMapFactory(gType,symbolObj){
			if(geoType.POINT == gType || geoType.MULTIPOINT == gType){
				if(symbolObj){
					return _convertPointSymbol(symbolObj);
				}else{
					return _convertPointSymbol(symbolConf["defaultPoint"]);
				}
			}
			if(geoType.POLYLINE == gType || geoType.MULTIPOLYLINE == gType){
				if(symbolObj){
					return _convertPolylineSymbol(symbolObj);
				}else{
					return _convertPolylineSymbol(symbolConf["defaultPolyline"]);
				}
			}
			if(geoType.POLYGON == gType || geoType.MULTIPOLYGON == gType){
				if(symbolObj){
					return _convertPolygonSymbol(symbolObj);
				}else{
					return _convertPolygonSymbol(symbolConf["defaultPolygon"]);
				}
			}
			return null;
		}

		function _convertPointSymbol(symbolObj){
			var pointSym = null,
				xOffset = symbolObj.xOffset || 0,
				yOffset = symbolObj.yOffset || 0,
				size,width,height;

			if(symbolObj.width && symbolObj.height){
				width = symbolObj.width;
				height = symbolObj.height;
				size = Math.max(width,height);
			}else{
				width = height = size = symbolObj.size || 10;
			}

			// pic marker
			if(symbolObj.url){
				pointSym = new OpenLayers.Symbolizer.Point();
				pointSym.externalGraphic = symbolObj.url;
				pointSym.graphicWidth = width;
				pointSym.graphicHeight = height;
				pointSym.graphicXOffset = xOffset - width / 2;
				pointSym.graphicYOffset = -(yOffset + height /2);
				return pointSym;
			}

			// text marker
			if(symbolObj.text){
				var fontfam = symbolObj.textFontFamily || "黑体",
					textstyle = symbolObj.textStyle || "",
					textweight = symbolObj.textWeight || "",
					textcolor = symbolObj.textColor || "";
				pointSym = new OpenLayers.Symbolizer.Text();
				pointSym.label = symbolObj.text;
				pointSym.fontFamily = fontfam;
				pointSym.fontSize = 14;
				pointSym.fontColor=textcolor;
				if("bold"==textweight){
					pointSym.fontWeight = "bold";
				}else{
					pointSym.fontWeight = "normal";
				}
				if("italic"==textstyle){
					pointSym.textstyle = "italic";
				}else if("oblique"==textstyle){
					pointSym.textstyle = "oblique";
				}else{
					pointSym.textstyle = "normal";
				}
				pointSym.labelXOffset = symbolObj.xOffset;
				pointSym.labelYOffset = symbolObj.yOffset;
				return pointSym;
			}

			// normal marker
			var symbolStyle = symbolObj.symbolStyle || "round",
				backgroundOpacity = symbolObj.backgroundOpacity || 1,
				backgroundColor = symbolObj.backgroundColor || "#ff0000",
				strokeColor = symbolObj.outLineColor || "#000000",
				strokeOpacity = symbolObj.outlineOpacity || 1,
				strokeWidth = symbolObj.outLineWidth || 1,
				strokeDashstyle = symbolObj.outlineStyle || "solid";

			pointSym = new OpenLayers.Symbolizer.Point();
			pointSym.strokeColor = strokeColor;
			pointSym.strokeOpacity = strokeOpacity;
			pointSym.strokeWidth = strokeWidth;
			if("dashed"==strokeDashstyle){
				pointSym.strokeDashstyle = "dash";
			}else if("dot"==strokeDashstyle){
				pointSym.strokeDashstyle = "dot";
			}else{
				pointSym.strokeDashstyle = "solid";
			}
			pointSym.fillColor = backgroundColor;
			pointSym.fillOpacity = backgroundOpacity;
			pointSym.pointRadius = size;
			pointSym.graphicXOffset = xOffset - width / 2;
			pointSym.graphicYOffset = -(yOffset + height /2);
			if("square"==symbolStyle){
				pointSym.strokeLinecap = "square";
			}else{
				pointSym.strokeLinecap = "round";
			}
			return pointSym;
		}

		function _convertPolylineSymbol(symbolObj){
			var lineSym = new OpenLayers.Symbolizer.Line();
			lineSym.strokeColor = symbolObj.outLineColor || "#000000";
			lineSym.strokeOpacity = symbolObj.outlineOpacity || 1;
			lineSym.strokeWidth = symbolObj.outLineWidth || 1;
			if("dashed"==symbolObj.outlineStyle){
				lineSym.strokeDashstyle = "dash";
			}else if("dot"==symbolObj.outlineStyle){
				lineSym.strokeDashstyle = "dot";
			}else{
				lineSym.strokeDashstyle = "solid";
			}
			return lineSym;
		}

		function _convertPolygonSymbol(symbolObj){
			var polygonSym = new OpenLayers.Symbolizer.Polygon();
			if(typeof symbolObj.outLineColor == "undefined"){
				polygonSym.strokeColor = "#000000";
			}else{
				polygonSym.strokeColor = symbolObj.outLineColor;
			}

			if(typeof symbolObj.outlineOpacity == "undefined"){
				polygonSym.strokeOpacity = 1;
			}else{
				polygonSym.strokeOpacity = symbolObj.outlineOpacity;
			}

			if(typeof symbolObj.outLineWidth == "undefined"){
				polygonSym.strokeWidth = 1;
			}else{
				polygonSym.strokeWidth = symbolObj.outLineWidth;
			}

			if("dashed"==symbolObj.outlineStyle){
				polygonSym.strokeDashstyle = "dash";
			}else if("dot"==symbolObj.outlineStyle){
				polygonSym.strokeDashstyle = "dot";
			}else{
				polygonSym.strokeDashstyle = "solid";
			}
			polygonSym.fillColor = symbolObj.backgroundColor || "#FFFF00";
			if(typeof symbolObj.backgroundOpacity == "undefined"){
				polygonSym.fillOpacity = 1;
			}else{
				polygonSym.fillOpacity = symbolObj.backgroundOpacity;
			}
			
			return polygonSym;
		}

		function convertFromObject(gType,symbolObject){
			if(!symbolObject){
				return symbolObject;
			}
			if(gType==geoType.POINT || gType==geoType.MULTIPOINT){
				return _convertPointToMapFactory(symbolObject);
			}else if(gType==geoType.POLYLINE || gType==geoType.MULTIPOLYLINE){
				return _convertPolylineToMapFactory(symbolObject);
			}else if(gType==geoType.POLYGON || gType==geoType.MULTIPOLYGON){
				return _convertPolygonToMapFactory(symbolObject);
			}
			return null;
		}

		function _convertPointToMapFactory(symbolObject){
			var _newSymbol = {};
			if(symbolObject instanceof OpenLayers.Symbolizer.Point){
				_newSymbol["xOffset"] = symbolObject.graphicXOffset + symbolObject.graphicWidth / 2;
				_newSymbol["yOffset"] = -(symbolObject.graphicHeight /2 + symbolObject.graphicYOffset);
				_newSymbol["angle"] = symbolObject.rotation;
				if(symbolObject.externalGraphic){
					_newSymbol["url"] = symbolObject.externalGraphic;
					_newSymbol["width"] = symbolObject.graphicWidth;
					_newSymbol["height"] = symbolObject.graphicHeight;
				}else{
					if("square" == symbolObject.strokeLinecap){
						_newSymbol["symbolStyle"] = "square";
					}else{
						_newSymbol["symbolStyle"] = "circle";
					}
					_newSymbol["size"] = symbolObject.pointRadius;
					_newSymbol["outLineWidth"] = symbolObject.strokeWidth;
					if("dash"==symbolObject.strokeDashstyle){
						_newSymbol["outlineStyle"] = "dashed";
					}else if("dot"==symbolObject.strokeDashstyle){
						_newSymbol["outlineStyle"] = "dot";
					}else{
						_newSymbol["outlineStyle"] = "solid";
					}
					_newSymbol["outLineColor"] = symbolObject.strokeColor;
					_newSymbol["backgroundColor"] = symbolObject.fillColor;
					_newSymbol["backgroundOpacity"] = symbolObject.fillOpacity;
				}
				return _newSymbol;
			}else if(symbolObject instanceof OpenLayers.Symbolizer.Text){
				_newSymbol["text"] = symbolObject.label;
				_newSymbol["textFontFamily"] = symbolObject.fontFamily;
				_newSymbol["size"] = symbolObject.fontSize;
				_newSymbol["textColor"] = symbolObject.fontColor;
				if("italic" == symbolObject.fontStyle){
					_newSymbol["textStyle"] = "italic";
				}else if("oblique" == symbolObject.fontStyle){
					_newSymbol["textStyle"] = "oblique";
				}else{
					_newSymbol["textStyle"] = "normal";
				}
				if("bold" == symbolObject.fontWeight){
					_newSymbol["textWeight"] = "bold";
				}else{
					_newSymbol["textWeight"] = "normal";
				}
				_newSymbol["xOffset"] = symbolObject.labelXOffset;
				_newSymbol["yOffset"] = symbolObject.labelYOffset;
				return _newSymbol;
			}
			return null;
		}

		function _convertPolylineToMapFactory(symbolObject){
			var _newSymbol = {};
			_newSymbol["outLineColor"] = symbolObject.strokeColor;
			_newSymbol["outLineWidth"] = symbolObject.strokeWidth;
			_newSymbol["outlineOpacity"] = symbolObject.strokeOpacity;
			if("dash" == symbolObject.strokeDashstyle){
				_newSymbol["outlineStyle"] = "dashed";
			}else if("dot" == symbolObject.strokeDashstyle){
				_newSymbol["outlineStyle"] = "dot";
			}else{
				_newSymbol["outlineStyle"] = "solid";
			}
			return _newSymbol;
		}

		
		function _convertPolygonToMapFactory(symbolObject){			
			var _newSymbol = {};
			_newSymbol["outLineColor"] = symbolObject.strokeColor;
			_newSymbol["outLineWidth"] = symbolObject.strokeWidth;
			if("dash"==symbolObject.strokeDashstyle){
				_newSymbol["outlineStyle"] = "dashed";
			}else if("dot"==symbolObject.strokeDashstyle){
				_newSymbol["outlineStyle"] = "dot";
			}else{
				_newSymbol["outlineStyle"] = "solid";
			}
			_newSymbol["outlineOpacity"] = symbolObject.strokeOpacity;
			_newSymbol["backgroundColor"] = symbolObject.fillColor;
			_newSymbol["backgroundOpacity"] = symbolObject.fillOpacity;
			return _newSymbol;
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});