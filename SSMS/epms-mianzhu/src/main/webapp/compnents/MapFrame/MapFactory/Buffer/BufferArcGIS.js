MapFactory.Define("MapFactory/Buffer",[	"MapFactory/BufferAPI*",	"MapFactory/MapManager",	"esri/tasks/BufferParameters*",	"esri/tasks/GeometryService*",	"MapFactory/GeometryUtil",	"esri/SpatialReference*",	"MapFactory/Message*",	"MapFactory/GeometryType*"],function(api,mapManager,bufferParams,geoService,geoUtil,srClass,messageClass,geoType){	return function(){		var _url = "",			_geometries = [],			_points = [],			_polylines = [],			_polygons = [],			_distance = 10,			_geoUtil = geoUtil(),			_type = "",			_mapManager = mapManager(),			_bufferRes = [];		function setUrl(url){			_url = url;		}		function setGeometry(geometryArr){			for(var i=0,len=geometryArr.length;i<len;i++){				switch(geometryArr[i].type){					case geoType.POINT : {						_points.push(_geoUtil.convertFromMapFactory(geometryArr[i]));						break;					}					case geoType.POLYLINE : {						_polylines.push(_geoUtil.convertFromMapFactory(geometryArr[i]));						break;					}					case geoType.POLYGON : {						_polygons.push(_geoUtil.convertFromMapFactory(geometryArr[i]));						break;					}				}			}		}		function setDistance(meterArr){			_distance = meterArr;		}		function execute(successFunc,failureFunc){			if(!_url){				if(failureFunc){					failureFunc(new messageClass({						message : "没有设置url"					}));				}			}			var _bParams = new bufferParams(),				_geoService = new geoService(_url);			_bParams.bufferSpatialReference = new srClass(_mapManager.getProjectSpatialReferenceCode());			_bParams.distances = _distance;			if(_points.length){				_geometries = _points;				_points = [];			}else if(_polylines.length){				_geometries = _polylines;				_polylines = [];			}else if(_polygons.length){				_geometries = _polygons;				_polygons = [];			}			_bParams.geometries = _geometries;			_bParams.outSpatialReference = new srClass(_mapManager.getSpatialReferenceCode());			_bParams.unit = esri.tasks.GeometryService.UNIT_METER;			_geoService.buffer(_bParams,function(geometriesRes){				for(var i=0,len=geometriesRes.length;i<len;i++){					_bufferRes.push(_geoUtil.convertFromObject(geometriesRes[i]));				}				if(_points.length || _polylines.length || _polygons.length){					execute(successFunc,failureFunc)				}else{					successFunc(_bufferRes);				}			},function(error){				if(failureFunc){					failureFunc(new messageClass({						message : "缓冲错误"					}));				}			});		}		return eval(MapFactory.GenerateAPI(api));	}});