MapFactory.Define("MapFactory/Query",[
	"MapFactory/QueryAPI*",
	"MapFactory/Message*",
	"MapFactory/GeometryUtil",
	"MapFactory/Route",
	"MapFactory/MapManager",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*"
],function(api,messageClass,GeometryUtil,Route,MapManager,Geometry,GeometryType){
	return function(url){

		var _url = "",
			_geometry,
			_geometryPrecision = 1,
			_relationship = "intersect",
			_where;

		var _geometryUtil = GeometryUtil();

		function setUrl(url){
			_url = url;
		}

		function setGeometry(geometry){
			_geometry = geometry;
		}

		function setGeometryPrecision(precision){
			_geometryPrecision = precision ? precision : _geometryPrecision;
		}

		function setSpatialRelationShip(relationship){
			_relationship = relationship ? relationship : _relationship;
		}

		function setCondition(obj){
			var _condition = "",
			_conReg = /[=|>|<]/g,
			_conReg2 = /\s+IN\b/g,
			_numReg = /\b\d+\b/g,
			_conReg3 = /like/g,
			i = 0;
			for(var elem in obj){
				i++;
				var _match = elem.match(_conReg),
					_match2 = elem.match(_conReg2),
					_numMatch = obj[elem].match(_numReg),
					_match3 = elem.match(_conReg3),
					_quoteStr = "'";
				if(i > 1){
					_condition += " And ";
				}
				if(!_match){
					if(!_match2){
						if(_match3){
							_condition += elem.replace(_conReg3,"") + " like " + _quoteStr + "%" + obj[elem] + "%" + _quoteStr;
						}else{
							_condition += elem + " = " + _quoteStr + obj[elem] + _quoteStr;
						}
						
					}else{
						var _fields = elem.replace(_conReg2,""),
							_values = obj[elem].split(","),
							_valueFlag = 0;
						for(var _vIndex=0,_vLen=_values.length;_vIndex<_vLen;_vIndex++){
							_valueFlag++;
							if(_valueFlag > 1){
								_condition += " OR ";
							}
							_condition += _fields + " = " + _quoteStr + _values[_vIndex] + _quoteStr;
						}
					}
				}else{
					_condition += elem.replace(_conReg,"") + _match.toString() + obj[elem];
				}
			}
			_where = _condition;
		}

		function execute(successFunc,failureFunc){
			if(!_url){
				if(failureFunc){
					failureFunc(new messageClass({
						message : "服务地址未设置"
					}));
				}
				return;
			}
			if(_relationship == "closedpoint") {
				var _route = Route();
				var _currentExtent = MapManager().getCurrentExtent();
				var _centerPX = _currentExtent.minX + (_currentExtent.maxX - _currentExtent.minX)/2;
				var _centerPY = _currentExtent.minY + (_currentExtent.maxY - _currentExtent.minY)/2;
				var _centerP = new Geometry({
					type : GeometryType.POINT,
					points : _centerPX + "," + _centerPY
				});
				_route.setUrl(_url);
				_route.setStart(_geometry);
				_route.setEnd(_centerP);
				_route.setStops([]);
				_route.setBarriers([]);
				_route.solve(function(data){
					if(data[0]){
						successFunc(data[0].geo);
					}else{
						successFunc(null);
					}
				},function(){
					failureFunc(new messageClass({
						message : "最邻近点查找失败"
					}));
				});
				return;
			}

			var _queryParam,_queryByGeometryParam,_queryBySQLParam,_queryService;
			var _targetUrl,_targetLayerName;
			var _paramOption = {};
			var _listeners = {};
			var _splitIndex = _url.lastIndexOf("/");
			_targetUrl = _url.substr(0,_splitIndex);
			_targetLayerName = _url.substr(_splitIndex+1,_url.length);

			_paramOption["name"] = _targetLayerName;

			_listeners["processCompleted"] = function(e){
				var i, j, feature,
					result = e.result;
				var resArr = [];
				if (result && result.recordsets) {
					for (i=0; i<result.recordsets.length; i++) {
						if (result.recordsets[i].features) {
							for (j=0; j<result.recordsets[i].features.length; j++) {
								feature = result.recordsets[i].features[j];
								resArr.push({
									geo : _geometryUtil.convertFromObject(feature.geometry),
									attributes : feature.attributes
								});
							}
						}
					}
				}
				successFunc(resArr);
			};

			_listeners["processFailed"] = function(failureInfo){
				console.log(failureInfo);
				failureFunc(new messageClass({
					message : "查询失败"
				}));
			};

			if(!_where && !_geometry){
				_where = "1=1";
			}

			if(_where){
				_paramOption["attributeFilter"] = _where;
			}

			_queryParam = new SuperMap.REST.FilterParameter(_paramOption);

			if(_where){
				_queryBySQLParam = new SuperMap.REST.QueryBySQLParameters({
                    queryParams: [_queryParam]
                });
				_queryService = new SuperMap.REST.QueryBySQLService(_targetUrl,{eventListeners:_listeners});
				_queryService.processAsync(_queryBySQLParam);
				return;
			}

			if(_relationship == "intersect"){
				_relationship = SuperMap.REST.SpatialQueryMode.INTERSECT;
			}else if(_relationship == "overlap"){
				_relationship = SuperMap.REST.SpatialQueryMode.OVERLAP
			}else if(_relationship == "contains"){
				_relationship = SuperMap.REST.SpatialQueryMode.CONTAIN
			}else{
				_relationship = SuperMap.REST.SpatialQueryMode.INTERSECT;
			}

			if(_geometry){
				_queryByGeometryParam = new SuperMap.REST.QueryByGeometryParameters({
	                queryParams: [_queryParam],
	                geometry: _geometryUtil.convertFromMapFactory(_geometry),
	                spatialQueryMode: _relationship
	            });
				_queryService = new SuperMap.REST.QueryByGeometryService(_targetUrl,{eventListeners:_listeners});
				_queryService.processAsync(_queryByGeometryParam);
			}
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});