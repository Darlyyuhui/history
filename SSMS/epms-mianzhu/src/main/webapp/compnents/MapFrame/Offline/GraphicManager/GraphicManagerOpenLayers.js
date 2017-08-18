MapFactory.Define("MapFactory/GraphicManager",[
	"MapFactory/GraphicManagerAPI*",
	"MapFactory/GeometryType*",
	"MapFactory/LayerManager",
	"MapFactory/GeometryUtil",
	"MapFactory/Geometry*",
	"MapFactory/SymbolUtil",
	"MapFactory/SymbolConfig*"
],function(api,gType,layerManager,geoUtil,geoClass,symbolUtil,SymbolDefaultConfig){
var proj4326 = new OpenLayers.Projection("EPSG:4326");
var proj900913 = new OpenLayers.Projection("EPSG:900913");
function transformTo900913(x, y) {
	x -= offsetX;
	y -= offsetY;
	var t = new OpenLayers.LonLat(x, y);
	if(x < 180 && y < 90) {
		t = t.transform(proj4326, proj900913);
	}
	return t;
}
function transformTo4326(x, y) {
	var t = new OpenLayers.LonLat(x, y);
	if(x > 180 && y > 90) {
		t = t.transform(proj900913, proj4326);
		t = new OpenLayers.LonLat(t.lon + offsetX, t.lat + offsetY);
	}
	return t;
}
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
			if(geo instanceof OpenLayers.Geometry.Point){
				geoType = gType.POINT;
			}else if(geo instanceof OpenLayers.Geometry.MultiPoint){
				geoType = gType.MULTIPOINT;
			}else if(geo instanceof OpenLayers.Geometry.LineString){
				geoType = gType.POLYLINE;
			}else if(geo instanceof OpenLayers.Geometry.MultiLineString){
				geoType = gType.MULTIPOLYLINE;
			}else if(geo instanceof OpenLayers.Geometry.Polygon){
				geoType = gType.POLYGON;
			}else if(geo instanceof OpenLayers.Geometry.MultiPolygon){
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
				g = new OpenLayers.Feature.Vector(geo,attr,sym);
				g.id = graphicID;
			}
		}

		function _convertGeostrtoGeo(gStr){
			return _geoUtil.convertFromMapFactory(gStr);
		}

		function getGeometryString(){
			// 返回的结果需要转换为4326在返回，此处geo为投影坐标
			var pointsStr = geo.toString().replace(/[\(|\)|POLYGON|LINESTRING|POINT|MULTIPOLYGON|MULTILINESTRING|MULTIPOINT]/ig,"").replace(/\s/g,",");
			var pointArr = pointsStr.split(","),
				arrLen = pointArr.length,
				tmpArr = [];
			for(var i=0;i<arrLen;i+=2){
				var tp = transformTo4326(parseFloat(pointArr[i]),parseFloat(pointArr[i+1]));
				tmpArr.push(tp.lon);
				tmpArr.push(tp.lat);
			}
			pointsStr = tmpArr.join(",");
			return pointsStr;
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
			centerP = transformTo4326(centerP.lon, centerP.lat);
			return new geoClass({
				type : gType.POINT,
				points : centerP.lon + "," + centerP.lat
			});
		}

		function getExtent(){
			var bounds = geo.getBounds();
			var lb = transformTo4326(bounds.left, bounds.bottom);
			var rt = transformTo4326(bounds.right, bounds.top);
			return {
				minX : lb.lon,
				minY : lb.lat,
				maxX : rt.lon,
				maxY : rt.lat
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
			highlightGraphic = new OpenLayers.Feature.Vector(_geoUtil.convertFromMapFactory(_geoUtil.convertFromObject(geo)),{},_symbolUtil.convertFromMapFactory(geoType,highlightSym));
			_mapEngine.getLayer(highlightLayerId).addFeatures([highlightGraphic]);
			// 定位中心点，如果没有在地图范围内，则定位到地图中心
			var _cp = getCenter();
			var _cp_points = _cp.points.split(",");
			var _p = new OpenLayers.LonLat(parseFloat(_cp_points[0]), parseFloat(_cp_points[1]));
			var _isInExtent = _mapEngine.getExtent().containsLonLat(_p);
			if(!_isInExtent)_mapEngine.setCenter(_p);
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