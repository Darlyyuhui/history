MapFactory.Define("MapFactory/SymbolUtil",[
    "MapFactory/SymbolUtilAPI*",
    "MapFactory/SymbolConfig*",
    "MapFactory/GeometryType*",
    "esri/symbols/PictureMarkerSymbol*",
	"esri/symbols/TextSymbol*",
	"esri/symbols/Font*",
	"esri/symbols/SimpleMarkerSymbol*",
	"esri/symbols/SimpleLineSymbol*",
	"esri/symbols/SimpleFillSymbol*"
],function(api,symbolConf,geoType,picMarkSym,textSymbol,font,simpleMarkerSym,simpleLineSym,simpleFillSym){
	return function(){
		function convertFromMapFactory(gType,symbolObj){
			if(gType == geoType.POINT){
				if(symbolObj){
					return _convertPointSymbol(symbolObj);
				}else{
					return _convertPointSymbol(symbolConf["defaultPoint"]);
				}
			}
			if(gType == geoType.MULTIPOLYLINE || gType == geoType.POLYLINE){
				if(symbolObj){
					return _convertPolylineSymbol(symbolObj);
				}else{
					return _convertPolylineSymbol(symbolConf["defaultPolyline"]);
				}
			}
			if(gType == geoType.MULTIPOLYGON || gType == geoType.POLYGON){
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
				pointSym = new picMarkSym(symbolObj.url,width,height);
				pointSym.setOffset(xOffset,yOffset);
				return pointSym;
			}

			// text marker
			if(symbolObj.text){
				var fontfam = symbolObj.textFontFamily || "黑体",
					textstyle = symbolObj.textStyle,
					textweight = symbolObj.textWeight,
					textcolor = symbolObj.textColor;

				if("italic"==textstyle){
					textstyle = font.STYLE_ITALIC;
				}else if("oblique"==textstyle){
					textstyle = font.STYLE_OBLIQUE;
				}else{
					textstyle = font.STYLE_NORMAL;
				}

				if("bold"==textweight){
					textweight = font.WEIGHT_BOLD;
				}else{
					textweight = font.WEIGHT_NORMAL;
				}

				if(textcolor){
					var tempColor = dojo.colorFromHex(textcolor).toRgb();
					textcolor = new dojo.Color(tempColor);
				}else{
					textcolor  = new dojo.Color([0,0,0]);
				}
				pointSym = new textSymbol(
					symbolObj.text,
					new font(size,textstyle,font.VARIANT_NORMAL,textweight,fontfam),
					textcolor
				);
				pointSym.setOffset(xOffset, yOffset);
				return pointSym;
			}

			// normal marker
			var symbolStyle = symbolObj.symbolStyle,
				backgroundOpacity = symbolObj.backgroundOpacity || 1,
				backgroundColor = symbolObj.backgroundColor;

			if(backgroundColor){
				var tempColor = dojo.colorFromHex(backgroundColor).toRgb();
				tempColor = tempColor.concat([backgroundOpacity]);
				backgroundColor = new dojo.Color(tempColor);
			}else{
				backgroundColor = new dojo.Color([255,0,0]);
			}

			if("square"==symbolStyle){
				symbolStyle = simpleMarkerSym.STYLE_SQUARE;
			}else{
				symbolStyle = simpleMarkerSym.STYLE_CIRCLE;
			}
			pointSym = new simpleMarkerSym(symbolStyle,size,_convertPolylineSymbol(symbolObj),backgroundColor);

			return pointSym;
		}

		function _convertPolylineSymbol(symbolObj){
			var outlineStyle = symbolObj.outlineStyle,
				outLineWidth,
				outlineOpacity,
				outLineColor = symbolObj.outLineColor,
				outlineSym = new simpleLineSym();

			if(typeof symbolObj.outLineWidth == "undefined"){
				outLineWidth = 1;
			}else{
				outLineWidth = symbolObj.outLineWidth;
			}

			if(typeof symbolObj.outlineOpacity == "undefined"){
				outlineOpacity = 1;
			}else{
				outlineOpacity = symbolObj.outlineOpacity;
			}

			if(outLineColor){
				var tempColor = dojo.colorFromHex(outLineColor).toRgb();
				tempColor = tempColor.concat([outlineOpacity]);
				outLineColor = new dojo.Color(tempColor);
			}else{
				outLineColor = new dojo.Color([255,255,0]);
			}

			if("dashed"==outlineStyle){
				outlineStyle = simpleLineSym.STYLE_DASH;
			}else if("dot"==outlineStyle){
				outlineStyle = simpleLineSym.STYLE_DOT;
			}else{
				outlineStyle = simpleLineSym.STYLE_SOLID;
			}
			outlineSym.setColor(outLineColor);
			outlineSym.setStyle(outlineStyle);
			outlineSym.setWidth(outLineWidth);
			return outlineSym;
		}

		function _convertPolygonSymbol(symbolObj){
			var backgroundStyle = symbolObj.backgroundStyle,
				outline = _convertPolylineSymbol(symbolObj),
				backgroundOpacity,
				backgroundColor = symbolObj.backgroundColor;

			if(typeof symbolObj.backgroundOpacity == "undefined"){
				backgroundOpacity = 1;
			}else{
				backgroundOpacity = symbolObj.backgroundOpacity;
			}

			if(backgroundColor){
				var tempColor = dojo.colorFromHex(backgroundColor).toRgb();
				tempColor = tempColor.concat([backgroundOpacity]);
				backgroundColor = new dojo.Color(tempColor);
			}else{
				backgroundColor = new dojo.Color[255,255,0];
			}
			switch(backgroundStyle){
				case "diagonal" : {
					backgroundStyle = simpleFillSym.STYLE_BACKWARD_DIAGONAL;
					break;
				}
				case "cross" : {
					backgroundStyle = simpleFillSym.STYLE_CROSS;
					break;
				}
				case "diagonalcross" : {
					backgroundStyle = simpleFillSym.STYLE_DIAGONAL_CROSS;
					break;
				}
				case "forwarddiagonal" : {
					backgroundStyle = simpleFillSym.STYLE_FORWARD_DIAGONAL;
					break;
				}
				case "horizontal" : {
					backgroundStyle = simpleFillSym.STYLE_HORIZONTAL;
					break;
				}
				case "null" : {
					backgroundStyle = simpleFillSym.STYLE_NULL;
					break;
				}
				case "vertical" : {
					backgroundStyle = simpleFillSym.STYLE_VERTICAL;
					break;
				}
				default : {
					backgroundStyle = simpleFillSym.STYLE_SOLID;
					break;
				}
			}
			return new simpleFillSym(backgroundStyle,outline,backgroundColor);
		}

		function convertFromObject(gType,symbolObject){
			if(geoType.POINT || geoType.MULTIPOINT){
				return _convertPointToMapFactory(symbolObject);
			}else if(geoType.POLYLINE || geoType.MULTIPOLYLINE){
				return _convertPolylineToMapFactory(symbolObject);
			}else if(geoType.POLYGON || geoType.MULTIPOLYGON){
				return _convertPolygonToMapFactory(symbolObject);
			}
			return null;
		}

		function _convertPointToMapFactory(symbolObject){
			var _newSymbol = {};
			_newSymbol["xOffset"] = symbolObject.xoffset;
			_newSymbol["yOffset"] = symbolObject.yoffset;
			_newSymbol["angle"] = symbolObject.angle;
			if("simplemarkersymbol" == symbolObject.type){
				if(simpleMarkerSym.STYLE_SQUARE == symbolObject.style){
					_newSymbol["symbolStyle"] = "square";
				}else{
					_newSymbol["symbolStyle"] = "circle";
				}
				_newSymbol["size"] = symbolObject.size;
				_newSymbol["outLineWidth"] = symbolObject.outline.width;
				if(simpleLineSym.STYLE_DASH==symbolObject.outline.style){
					_newSymbol["outlineStyle"] = "dashed";
				}else if(simpleLineSym.STYLE_DOT==symbolObject.outline.style){
					_newSymbol["outlineStyle"] = "dot";
				}else{
					_newSymbol["outlineStyle"] = "solid";
				}
				_newSymbol["outLineColor"] = symbolObject.outline.color.toHex();
				var _background = symbolObject.color.toRgba();
				_newSymbol["backgroundColor"] = symbolObject.color.toHex();
				_newSymbol["backgroundOpacity"] = _background[3];
				return _newSymbol;
			}else if("picturemarkersymbol" == symbolObject.type){
				_newSymbol["url"] = symbolObject.url;
				_newSymbol["width"] = symbolObject.width;
				_newSymbol["height"] = symbolObject.height;
				return _newSymbol;
			}else if("textsymbol" == symbolObject.type){
				_newSymbol["text"] = symbolObject.text;
				var _font = symbolObject.font;
				_newSymbol["textFontFamily"] = _font.family;
				_newSymbol["textColor"] = symbolObject.color.toHex();
				if(font.STYLE_ITALIC == symbolObject.font.style){
					_newSymbol["textStyle"] = "italic";
				}else if(font.STYLE_OBLIQUE == symbolObject.font.style){
					_newSymbol["textStyle"] = "oblique";
				}else{
					_newSymbol["textStyle"] = "normal";
				}
				if(font.WEIGHT_BOLD == _font.weight){
					_newSymbol["textWeight"] = "bold";
				}else{
					_newSymbol["textWeight"] = "normal";
				}
				return _newSymbol;
			}
			return null;
		}

		function _convertPolylineToMapFactory(symbolObject){
			var _newSymbol = {};
			var _color = symbolObject.color.toRgba();
			_newSymbol["outLineColor"] = symbolObject.color.toHex();
			_newSymbol["outLineWidth"] = symbolObject.width;
			_newSymbol["outlineOpacity"] = _color[3];
			if(simpleLineSym.STYLE_DASH == symbolObject.style){
				_newSymbol["outlineStyle"] = "dashed";
			}else if(simpleLineSym.STYLE_DOT == symbolObject.style){
				_newSymbol["outlineStyle"] = "dot";
			}else{
				_newSymbol["outlineStyle"] = "solid";
			}
			return _newSymbol;
		}

		function _convertPolygonToMapFactory(symbolObject){
			var _newSymbol = {},
				_backgroundColor = symbolObject.color.toRgba();
			if(simpleFillSym.STYLE_BACKWARD_DIAGONAL == symbolObject.style){
				_newSymbol["backgroundStyle"] = "diagonal";
			}else if(simpleFillSym.STYLE_CROSS == symbolObject.style){
				_newSymbol["backgroundStyle"] = "cross";
			}else if(simpleFillSym.STYLE_DIAGONAL_CROSS == symbolObject.style){
				_newSymbol["backgroundStyle"] = "diagonalcross";
			}else if(simpleFillSym.STYLE_FORWARD_DIAGONAL == symbolObject.style){
				_newSymbol["backgroundStyle"] = "forwarddiagonal";
			}else if(simpleFillSym.STYLE_HORIZONTAL == symbolObject.style){
				_newSymbol["backgroundStyle"] = "horizontal";
			}else if(simpleFillSym.STYLE_NULL == symbolObject.style){
				_newSymbol["backgroundStyle"] = "null";
			}else if(simpleFillSym.STYLE_VERTICAL == symbolObject.style){
				_newSymbol["backgroundStyle"] = "vertical";
			}else{
				_newSymbol["backgroundStyle"] = "solid";
			}

			_newSymbol["backgroundOpacity"] = _backgroundColor[3];
			_newSymbol["backgroundColor"] = symbolObject.color.toHex();

			var _outLine = symbolObject.outline;
			_newSymbol["outLineWidth"] = _outLine.width;
			if(simpleLineSym.STYLE_DASH==_outLine.style){
				_newSymbol["outlineStyle"] = "dashed";
			}else if(simpleLineSym.STYLE_DOT==_outLine.style){
				_newSymbol["outlineStyle"] = "dot";
			}else{
				_newSymbol["outlineStyle"] = "solid";
			}
			_newSymbol["outLineColor"] = _outLine.color.toHex();

			return _newSymbol;
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});