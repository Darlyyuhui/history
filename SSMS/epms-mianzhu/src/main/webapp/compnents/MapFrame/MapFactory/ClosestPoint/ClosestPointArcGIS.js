MapFactory.Define("MapFactory/ClosestPoint",[
    "MapFactory/ClosestPointAPI*",
    "MapFactory/GeometryType*",
    "MapFactory/GraphicManager",
    "MapFactory/GeometryUtil",
    "esri/graphic*",
    "esri/layers/FeatureLayer*",
    "esri/layers/GraphicsLayer*",
    "esri/SnappingManager*",
    "MapFactory/MapManager",
    "MapFactory/Geometry*"
],function(api,geoType,graphicManager,geoUtil,graphic,featureLayer,graphicLayer,snappingManager,mapManager,geoClass){
	return function(conf){

		var _mapHandler = MapFactory._MapManagerResource[MapFactory.Engine],
			_snapLayers = [],
			_snappingManager = null,
			_snappingGraphicLayer = new graphicLayer(),
			_mapManager = mapManager(),
			_isFeatureLayerLoaded = true,
			_geoUtil = geoUtil();

		/**
		 * 配置参数
		 * @param conf Object 配置信息
		 * 	 + urls String[] 查找最近点的图层url字符串数组
		 *   + ids String[] 查找最近点的图层id字符串数组
		 *   + geometry MapFactory/Geometry[] 查找最近点的几何体数组
		 */
		
		function setLayerInfos(_urls){
			if(_urls && _urls.length){
				_isFeatureLayerLoaded = false;
				for(var i=0,len=_urls.length;i<len;i++){
					if(_urls[i]){
						var flayer = new featureLayer(_urls[i]);
						// 添加图层加载完成事件，如果有一个图层加载完成，则认为所有的图层都加载完成
						dojo.connect(flayer,"onLoad",function(event){
							_isFeatureLayerLoaded = true;
						});
						_snapLayers.push({layer: flayer});
					}
				}
				//_snappingManager.setLayerInfos(_snapLayers);
			}
		}

		function setLayerIds(_ids){
			if(_ids && _ids.length){
				for(var i=0,len=_ids.length;i<len;i++){
					if(_ids[i]){
						var _layer = _mapHandler.getLayer(_ids[i]);
						if(_layer){
							_snapLayers.push({layer:_layer});
						}
					}
				}
				//_snappingManager.setLayerInfos(_snapLayers);
			}
		}

		function setGeometry(_geos){
			if(_geos && _geos.length){
				for(var i=0,len=_geos.length;i<len;i++){
					var _geo = _geos[i],g;
					if(_geo){
						if(MapFactory.VariableTypes.isString(_geo)){
							_geo = MapFactory.JSON.Parse(_geo);
						}
						//由于这里没有添加样式，所以不显示，并且没有设置图层id，在图层管理中屏蔽了
						g = new graphic(_geoUtil.convertFromMapFactory(_geo));
						_snappingGraphicLayer.add(g);
					}
				}
				
				_snapLayers.push({layer: _snappingGraphicLayer});
				//_snappingManager.setLayerInfos(_snapLayers);
			}
		}

		function destroy(){
			if(_snappingManager){
				_snappingManager.destroy();
				_snappingManager = null;
			}
			_mapHandler.removeLayer(_snappingGraphicLayer);
		}

		function _snappingInit(){
			if(!conf)return;
			setLayerIds(conf.ids);
			setLayerInfos(conf.urls);
			setGeometry(conf.geos);
			var tolerance = Number(conf.tolerance) || 15;

			var _options = {
				map : _mapHandler,
				alwaysSnap : true,
				tolerance : tolerance,
				layerInfos : _snapLayers
			};

			_snappingManager = new snappingManager(_options);
		}
		// 初始化的时候创建对象
		_snappingInit();

		function getClosestPoint(point,func){
			if(!_isFeatureLayerLoaded) {
				setTimeout(function(){
					getClosestPoint(point,func);
				}, 100);
				return;
			}
			var points = point.points.split(",");
			var _screenP = _mapManager.toScreen(parseFloat(points[0]), parseFloat(points[1]));

			_screenP = new geoClass({
				type : geoType.POINT,
				points : _screenP.x+","+_screenP.y
			});

			_screenP = _geoUtil.convertFromMapFactory(_screenP);
			_screenP.spatialReference = null;

			if(!_snappingManager) {
				func(point);
				return;
			}
			_snappingManager.getSnappingPoint(_screenP).then(function(value){
				if(!value){
					if(func){
						func("");
					}
					return;
				}
				var _mapP = _geoUtil.convertFromObject(value);
				if(func){
					func(_mapP);
				}
			}, function(error){
				if (typeof console != 'undefined' && console.log) console.log(error);
			});
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});