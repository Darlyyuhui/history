MapFactory.Define("MapFactory/GraphicManager",[
	"MapFactory/GraphicManagerAPI*",
	"MapFactory/GeometryType*",
	"esri/geometry/Geometry*",
	"esri/graphic*",
	"MapFactory/LayerManager",
	"MapFactory/GeometryUtil",
	"MapFactory/Geometry*",
	"MapFactory/SymbolUtil",
	"MapFactory/SymbolConfig*"
],function(api,gType,geometry,graphic,layerManager,geoUtil,geoClass,symUtil,SymbolDefaultConfig){
	return function(id,conf){
		var _conf = {
			geo : "",
			symbol : "",
			attributes : ""
		},
		geo = null,sym = null,attr = {},g = null,
		graphicID = "",geoType = "";
		
		var _mapEngine = MapFactory._MapManagerResource[MapFactory.Engine];
		var _geoUtil = geoUtil();
		var _symbolUtil = symUtil();

		if(MapFactory.VariableTypes.isString(id)){
			graphicID = id;
			g = MapFactory._MapGraphicResource[graphicID];
			geo = g.geometry;
			sym = g.symbol;
			attr = g.attributes;
			if(geo.type == "point"){
				geoType = gType.POINT;
			}else if(geo.type == "multipoint"){
				geoType = gType.MULTIPOINT;
			}else if(geo.type == "polyline"){
				geoType = gType.POLYLINE;
			}else if(geo.type == "polygon"){
				geoType = gType.POLYGON;
			}
		}else{
			conf = id;
			graphicID = MapFactory.GenerateID();
		}

		MapFactory.Extend(_conf,conf);

		if(!g){
			if(_conf.geo){
				if(MapFactory.VariableTypes.isString(_conf.geo)){
					var _geom = MapFactory.JSON.Parse(_conf.geo);
					geo = _convertGeostrtoGeo(_geom);
					geoType = _geom.type;
				}
				if(MapFactory.VariableTypes.isObject(_conf.geo)){
					geo = _convertGeostrtoGeo(_conf.geo);
					geoType = _conf.geo.type;
				}
			}

			if(geo){
				sym = symUtil().convertFromMapFactory(geoType,_conf.symbol);
				if(_conf.attributes){
					attr = _conf.attributes;
				}
				g = new graphic(geo,sym,attr);
				g.id = graphicID;
			}
		}

		function _convertGeostrtoGeo(gStr){
			if(MapFactory.VariableTypes.isString(gStr)){
				geoType = MapFactory.JSON.Parse(gStr).type;
			}else{
				geoType = gStr.type;
			}
			return geoUtil().convertFromMapFactory(gStr);
		}

		function getGeometryString(){
			if(geo.x && geo.y){
				return geo.x+","+geo.y;
			}
			if(geo.rings){
				return geo.rings.toString();
			}
			if(geo.paths){
				return geo.paths.toString();
			}
			return "";
		}

		function getGeometryType(){
			return geo.type;
		}

		function getAttributes(){
			return g.attributes;
		}

		function addToLayer(layerid){
			/*if(MapFactory.VariableTypes.isObject(MapFactory._MapGraphicResource[layerid])){
				MapFactory._MapGraphicResource[layerid][graphicID] = g;
			}else{
				MapFactory._MapGraphicResource[layerid] = {}
				MapFactory._MapGraphicResource[layerid][graphicID] = g;
			}*/
			MapFactory._MapGraphicResource[graphicID] = g;
			if(!MapFactory.VariableTypes.isArray(MapFactory._MapLayerGraphicResource[layerid])){
				MapFactory._MapLayerGraphicResource[layerid] = [];
			}
			MapFactory._MapLayerGraphicResource[layerid].push(graphicID);
			layerManager(layerid);
			MapFactory._MapManagerResource[MapFactory.Engine].getLayer(layerid).add(g);
		}

		function remove(){
			var layer = g.getLayer(),
				gIndex,arrayUtil = MapFactory.Array;
			if(!layer){
				return;
			}
			for(var elem in MapFactory._MapLayerGraphicResource){
				gIndex = arrayUtil.inArray(graphicID,MapFactory._MapLayerGraphicResource[elem]);
				if(gIndex){
					delete MapFactory._MapLayerGraphicResource[elem][gIndex];
					delete MapFactory._MapGraphicResource[graphicID];
					break;
				}
			}
			layer.remove(g);
		}

		function getCenter(){
			if(gType.POINT==geo.type){
				return new geoClass({
					type : gType.POINT,
					points : geo.x + "," + geo.y
				});
			}else{
				var centerP = geo.getExtent().getCenter();
				return new geoClass({
					type : gType.POINT,
					points : centerP.x + "," + centerP.y
				});
			}
		}

		function getExtent(){
			if(geo.type=="point"){
				return null;
			}
			var extent = geo.getExtent();
			return {
				minX : extent.xmin,
				minY : extent.ymin,
				maxX : extent.xmax,
				maxY : extent.ymax
			};
		}

		function getGraphic(){
			var graphic = MapFactory._MapGraphicResource[graphicID];
			if(graphic){
				return {
					id : graphicID,
					geo : geoUtil().convertFromObject(graphic.geometry),
					attributes : graphic.attributes
				}
			}else{
				return null;
			}
		}

		function getLayerID(){
			return g.getLayer().id;
		}

		function show(){
			g.show();
		}

		function hide(){
			g.hide();
		}

		function highlight(){
			var highlightLayerId = "graphicHighlightLayer";
			var highlightSym = _conf.symbol;
			var highlightGraphic = null;

			if(geoType == gType.POINT || geoType == gType.MULTIPOINT){
				if(!highlightSym){
					highlightSym = MapFactory.Clone(SymbolDefaultConfig.defaultPoint);
				}
				if(highlightSym.url){
					delete highlightSym.url;
					highlightSym.backgroundOpacity = 0.1;
					highlightSym.backgroundColor = "#fff";
					highlightSym.symbolStyle = "square";
					highlightSym.outLineWidth = 2;
					highlightSym.outLineStyle = "";
					highlightSym.outLineColor = "";
					if(highlightSym.width && highlightSym.height){
						highlightSym.width = highlightSym.height = Math.max(highlightSym.width,highlightSym.height) * 1.2;
					}else{
						highlightSym.size = highlightSym.size * 6;
					}
				}else{
					if(highlightSym.width && highlightSym.height){
						highlightSym.width = highlightSym.height = Math.max(highlightSym.width,highlightSym.height) + 1;
					}else{
						highlightSym.size = highlightSym.size + 1;
					}
				}
			}
			if(geoType == gType.POLYLINE){
				if(!highlightSym){
					highlightSym = MapFactory.Clone(SymbolDefaultConfig.defaultPolyline);
				}
				highlightSym.outLineWidth = highlightSym.outLineWidth + 2;
			}
			if(geoType == gType.POLYGON){
				if(!highlightSym){
					highlightSym = MapFactory.Clone(SymbolDefaultConfig.defaultPolygon);
				}
				highlightSym.outLineWidth = highlightSym.outLineWidth + 2;
			}

			MapFactory.Extend(highlightSym,SymbolDefaultConfig.highLight);
			layerManager(highlightLayerId);
			highlightGraphic = new graphic(_geoUtil.convertFromMapFactory(_geoUtil.convertFromObject(geo)),_symbolUtil.convertFromMapFactory(geoType,highlightSym),{});
			_mapEngine.getLayer(highlightLayerId).add(highlightGraphic);
		}

		function clearAllHighlight(){
			var highlightLayerId = "graphicHighlightLayer";
			layerManager(highlightLayerId).clear();
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});