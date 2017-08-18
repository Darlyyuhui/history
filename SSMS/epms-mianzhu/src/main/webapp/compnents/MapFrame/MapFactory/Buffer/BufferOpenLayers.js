MapFactory.Define("MapFactory/Buffer",[
	"MapFactory/BufferAPI*",
	"MapFactory/GeometryUtil",
	"MapFactory/Message*"
],function(api,geoUtil,messageClass){
	return function(){

		var _url = "",
			_geometries = [],
			_distance = 10,
			_geoUtil = geoUtil();

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
			var _data = {};
			_data["url"] = _url;
			_data["geometries"] = MapFactory.JSON.Stringify(_geometries);
			_data["distance"] = _distance;
			MapFactory.XHR.Post("openmap/query/buffer/",_data,function(geoList){
				successFunc(geoList);
			},function(){
				if(failureFunc){
					failureFunc(new messageClass({
						message : "服务分析错误"
					}));
				}
			});
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});