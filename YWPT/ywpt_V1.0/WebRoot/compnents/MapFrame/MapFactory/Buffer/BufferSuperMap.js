MapFactory.Define("MapFactory/Buffer",[
	"MapFactory/BufferAPI*",
	"MapFactory/GeometryUtil",
	"MapFactory/Message*",
	"MapFactory/MapManager"
],function(api,geoUtil,messageClass,MapManager){
	return function(){

		var _url = "",
			_geometries = [],
			_distance = 10,
			_geoUtil = geoUtil(),
			_currentGeometryIndex = 0,
			_bufferedResults = [],
			_successFunc = null,
			_failureFunc = null,
			_sr = MapManager().getSpatialReferenceCode();

		function setUrl(url){
			_url = url;
		}

		function setGeometry(geometryArr){
			_geometries = geometryArr;
		}

		function setDistance(meterArr){
			_distance = meterArr[0];
		}

		function execute(successFunc,failureFunc){
			if(!_url){
				if(failureFunc){
					failureFunc(new messageClass({
						message : "没有设置url"
					}));
				}
			}
			_successFunc = successFunc;
			_failureFunc = failureFunc;
			_iteratorGeometry();
		}

		function _iteratorGeometry(){
			var _bufferedService = new SuperMap.REST.BufferAnalystService(_url);
			var _bufferedDistance = new SuperMap.REST.BufferDistance({value: _distance});
			var _bufferedSetting = new SuperMap.REST.BufferSetting({
                endType: SuperMap.REST.BufferEndType.ROUND,
                leftDistance: _bufferedDistance,
                rightDistance: _bufferedDistance,
                semicircleLineSegment: 10
            });
            var _geo = _geoUtil.convertFromMapFactory(_geometries[_currentGeometryIndex]);
            _geo.SRID = parseInt(_sr);
			var _bufferedParams = new SuperMap.REST.GeometryBufferAnalystParameters({
				sourceGeometry : _geo,
				bufferSetting : _bufferedSetting
			});
			_currentGeometryIndex ++;
			_bufferedService.events.on({
                "processCompleted": function(data){
					_bufferedResults.push(_geoUtil.convertFromObject(data.result.resultGeometry));
					if(_currentGeometryIndex < _geometries.length){
						_iteratorGeometry();
					}else{
						_successFunc(_bufferedResults);
					}
				},
				"processFailed" : function(data){
					_failureFunc(new messageClass({
						message : "缓冲失败"
					}));
				}
            });
			_bufferedService.processAsync(_bufferedParams);
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});