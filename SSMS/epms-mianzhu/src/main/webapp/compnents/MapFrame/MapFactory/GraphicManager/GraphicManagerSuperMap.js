MapFactory.Define("MapFactory/GraphicManager",[
	"MapFactory/GraphicManagerAPI*",
	"MapFactory/GeometryType*",
	"MapFactory/LayerManager",
	"MapFactory/GeometryUtil",
	"MapFactory/Geometry*",
	"MapFactory/SymbolUtil",
	"MapFactory/SymbolConfig*"
],function(api,gType,layerManager,geoUtil,geoClass,symbolUtil,SymbolDefaultConfig){
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
		var _symbolUtil = symbolUtil();
		if(MapFactory.VariableTypes.isString(id)){
			graphicID = id;
			g = MapFactory._MapGraphicResource[graphicID];
			geo = g.geometry;
			attr = g.attributes;
			sym = g.style;
			if(geo instanceof SuperMap.Geometry.Point){
				geoType = gType.POINT;
			}else if(geo instanceof SuperMap.Geometry.MultiPoint){
				geoType = gType.MULTIPOINT;
			}else if(geo instanceof SuperMap.Geometry.LineString){
				geoType = gType.POLYLINE;
			}else if(geo instanceof SuperMap.Geometry.MultiLineString){
				geoType = gType.MULTIPOLYLINE;
			}else if(geo instanceof SuperMap.Geometry.Polygon){
				geoType = gType.POLYGON;
			}else if(geo instanceof SuperMap.Geometry.MultiPolygon){
				geoType = gType.MULTIPOLYGON;
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
				sym = symbolUtil().convertFromMapFactory(geoType,_conf.symbol);
				if(_conf.attributes){
					attr = _conf.attributes;
				}
				g = new SuperMap.Feature.Vector(geo,attr,sym);
				g.id = graphicID;
			}
		}

		function _convertGeostrtoGeo(gStr){
			return _geoUtil.convertFromMapFactory(gStr);
		}

		function getGeometryString(){
			return geo.toString().replace(/[\(|\)|POLYGON|LINESTRING|POINT|MULTIPOLYGON|MULTILINESTRING|MULTIPOINT]/ig,"").replace(/\s/g,",");
		}

		function getGeometryType(){
			return geoType;
		}

		function getAttributes(){
			
			return g.attributes;
			
		}

		function addToLayer(layerid){
			MapFactory._MapGraphicResource[graphicID] = g;
			if(!MapFactory.VariableTypes.isArray(MapFactory._MapLayerGraphicResource[layerid])){
				MapFactory._MapLayerGraphicResource[layerid] = [];
			}
			MapFactory._MapLayerGraphicResource[layerid].push(graphicID);
			layerManager(layerid);
			var test = _mapEngine.getLayer(layerid);
			_mapEngine.getLayer(layerid).addFeatures([g]);
		}

		function remove(){
			var layer = g.layer,
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
			layer.removeFeatures([g]);
		}

		function getCenter(){
			var centerP = geo.getBounds().getCenterLonLat();
			return new geoClass({
				type : gType.POINT,
				points : centerP.lon + "," + centerP.lat
			});
		}

		function getExtent(){
			var bounds = geo.getBounds();
			return {
				minX : bounds.left,
				minY : bounds.bottom,
				maxX : bounds.right,
				maxY : bounds.top
			}
		}

		function getGraphic(){
			var graphic = MapFactory._MapGraphicResource[graphicID];
			if(graphic){
				return {
					id : graphicID,
					geo : _geoUtil.convertFromObject(graphic.geometry),
					attributes : graphic.attributes
				}
			}else{
				return null;
			}
			console.log(graphic);
		}

		function getLayerID(){
			return g.layer.id;
		}

		function show(){
			var layer = g.layer;
			if(layer){
				g.style.display = "";
				layer.redraw();
			}
		}

		function hide(){
			var layer = g.layer;
			if(layer){
				g.style.display = "none";
				layer.redraw();
			}
		}

		function highlight(layerId){
			var highlightLayerId;
			if(layerId){
				highlightLayerId=layerId;	
			}else{
				highlightLayerId = "graphicHighlightLayer";
			}
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
						highlightSym.width = highlightSym.height = Math.max(highlightSym.width,highlightSym.height);
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
			if(geoType == gType.POLYLINE || geoType == gType.MULTIPOLYLINE){
				if(!highlightSym){
					highlightSym = MapFactory.Clone(SymbolDefaultConfig.defaultPolyline);
				}
				highlightSym.outLineWidth = highlightSym.outLineWidth + 2;
			}
			if(geoType == gType.POLYGON || geoType == gType.MULTIPOLYGON){
				if(!highlightSym){
					highlightSym = MapFactory.Clone(SymbolDefaultConfig.defaultPolygon);
				}
				highlightSym.outLineWidth = highlightSym.outLineWidth + 2;
			}
			
			MapFactory.Extend(highlightSym,SymbolDefaultConfig.highLight);
			layerManager(highlightLayerId);
			highlightGraphic = new SuperMap.Feature.Vector(_geoUtil.convertFromMapFactory(_geoUtil.convertFromObject(geo)),{ids:_conf.attributes.ids},_symbolUtil.convertFromMapFactory(geoType,highlightSym));
		
			var _gid = MapFactory.GenerateID();
			highlightGraphic.id = _gid;
			MapFactory._MapGraphicResource[_gid] = highlightGraphic;
			MapFactory._MapLayerGraphicResource[highlightLayerId].push(_gid);
			_mapEngine.getLayer(highlightLayerId).addFeatures([highlightGraphic]);
		}

		function clearAllHighlight(){
			var highlightLayerId = "graphicHighlightLayer";
			var highlightLayer = _mapEngine.getLayer(highlightLayerId);
			if(highlightLayer){
				highlightLayer.removeAllFeatures();
			}
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});