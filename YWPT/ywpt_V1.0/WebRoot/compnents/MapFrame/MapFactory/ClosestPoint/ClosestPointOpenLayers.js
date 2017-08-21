MapFactory.Define("MapFactory/ClosestPoint",[
    "MapFactory/ClosestPointAPI*",
    "MapFactory/GeometryUtil",
    "MapFactory/LayerManager"
],function(api,geoUtil,layerManager){
	return function(conf){

		var _mapHandler = MapFactory._MapManagerResource[MapFactory.Engine],
			_layerNames = [],
			_layers = [],
			_geoData = [],
			_geoUtil = geoUtil();

		/**
		 * 配置参数
		 * @param conf Object 配置信息
		 * 	 + urls String[] 查找最近点的图层url字符串数组
		 *   + ids String[] 查找最近点的图层id字符串数组
		 *   + geometry MapFactory/Geometry[] 查找最近点的几何体数组
		 */

		function _init(){
			setLayerNames(conf.urls);
			setLayerIds(conf.ids);
			setGeometry(conf.geos);
		}
		_init();

		function setLayerNames(urls){
			if(conf.urls && conf.urls.length) {
				for(var i=0,len=urls.length; i<len; i++) {
					var url = urls[i];
					if(!url)continue;
					url =  url.substr(0, url.length-1);
					var _index = url.lastIndexOf("/");
					var layername = url.substr(_index+1, url.length);
					_layerNames.push(layername);
				}
			}
		}

		function setLayerIds(_ids){
			if(_ids && _ids.length){
				for(var i=0,len=_ids.length;i<len;i++){
					if(_ids[i]){
						var feats = layerManager(_ids[i]).getFeatures();
						_geoData.push(window.JSON2.stringify(feats));
					}
				}
			}
		}

		function setGeometry(_geos){
			if(_geos && _geos.length){
				for(var i=0,len=_geos.length;i<len;i++){
					if(_geos[i]){
						_geoData.push(window.JSON2.stringify(_geos[i]));
					}
				}
			}
		}

		function destroy(){
		}

		function getClosestPoint(point,func){
			$.ajax({
				url: "openmap/query/closedpointfromgeos/",
				type: "POST",
				//async: false,
				data: {point: window.JSON2.stringify(point), geos:_geoData.join(";"), layernames: _layerNames.join(",")},
				success: function(result) {
					if(result)func(result);
					else func("");
				},
				error: function(){
					func("");
				}
			});
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});