/**
 * @author ZLT
 * Date : 2013-7-15
 * 需要加载类库有：
 *  "esri.toolbars.draw"
 * 	"esri.geometry.Extent"
 *  "esri.tasks.gp"
 */

var ThemeMapWidget = (function() {
	return function(conf) {
		/**
		 * 配置参数信息
		 * 参数说明：
		 * @param basemap 底图，esri.Map对象
		 */
		var _conf = {
			basemap : '',
			extentEndCall : ''
		}

		// 设置参数
		if( typeof conf != "undefined") {
			for(elem in _conf) {
				if( typeof conf[elem] != "undefined" && conf[elem] != "") {
					_conf[elem] = conf[elem];
				}
			}
		}

		// 专题地图的相关参数
		var params = {
			MapTitle : '',
			MapExtent : '',
			MapPoint : '',
			MapPolyline : '',
			MapPolygon : '',
			MapTemplateIndex : ''
		}

		// GP服务的url地址
		var gpUrl = "";//baseServiceURL.layoutmapgp.url;

		// GP服务资源
		var geoProcessorTool;

		// GP处理状态
		var _GPJobStatus = '';

		// 结果URL
		var resultUrl = '';

		var STATUS_SUBMIT = 0, // 提交
		STATUS_FAILED = 1, // 失败
		STATUS_EXECUTING = 2, // 正在执行
		STATUS_SUCCEEDED = 3; // 成功
		
		// 地图1:150000的extent,定义最小缩放
		var _MIN_EXTENT_XMIN = 107.568593,
		_MIN_EXTENT_YMIN = 33.806987,
		_MIN_EXTENT_XMAX = 109.484629,
		_MIN_EXTENT_YMAX = 34.910009;

		// 对外部提供的api
		var api = {
			init : _init, // 初始化
			beginSeletionBox : _extentBegin, // 开始框选
			setMapParameters : _setMapParameters, // 设置地图相关参数
			exportMap : _generateThemeMap, // 生成专题地图
			getJobStatus : _getStatus, // 获取工作状态
			getResultUrl : _getResultUrl, // 获取结果url
			STATUS_SUBMIT : STATUS_SUBMIT, // 提交
			STATUS_FAILED : STATUS_FAILED, // 失败
			STATUS_EXECUTING : STATUS_EXECUTING, // 正在执行
			STATUS_SUCCEEDED : STATUS_SUCCEEDED // 成功
		}

		// 主题图输出控件初始化
		function _init() {
			isDrawExtent = false;
			_initToolbar();
			return api;
		}

		// 初始化工具条
		function _initToolbar() {
			toolbar = new esri.toolbars.Draw(_conf.basemap);
			dojo.connect(toolbar, "onDrawEnd", _addToMap);
		}

		// 在地图上显示框选区域，并缩放
		function _addToMap(geometry) {
			toolbar.deactivate();
			_conf.basemap.showZoomSlider();
			if(geometry.type == "extent") {
				var symbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT, new dojo.Color([255, 0, 0]), 2), new dojo.Color([255, 255, 0, 0.25]));
			} else {
				return;
			}
			//var graphic = new esri.Graphic(geometry, symbol);
			//map.graphics.add(graphic);
			_conf.basemap.setExtent(new esri.geometry.Extent(geometry.xmin, geometry.ymin, geometry.xmax, geometry.ymax, _conf.basemap.spatialReference), true);
			if(_conf.extentEndCall != '') {
				_conf.extentEndCall();
			}
		}

		// 开始使用工具绘图
		function _extentBegin() {
			toolbar.activate(esri.toolbars.Draw.EXTENT);
			_conf.basemap.hideZoomSlider();
		}

		/**
		 * 开始设置地图相关参数
		 * @param o
		 *  + title String 图名
		 *  + extent extent 范围
		 *  + mapPoint featureset 点集
		 *  + mapPolyline featureset 线集
		 *  + mapPolygon featureset 面集
		 *  + mapTemplateIndex String 索引
		 */
		function _setMapParameters(o) {
			params.MapTitle = o.title;
			//console.log(o.extent);
			// 判断是否范围超出最小底线
			if(_MIN_EXTENT_XMIN > o.extent.xmin || _MIN_EXTENT_YMIN > o.extent.ymin){
				o.extent.xmin = _MIN_EXTENT_XMIN;
				o.extent.ymin = _MIN_EXTENT_YMIN;
				//console.log("xmin changed");
			}
			if(_MIN_EXTENT_XMAX < o.extent.xmax || _MIN_EXTENT_YMAX < o.extent.ymax){
				o.extent.xmax = _MIN_EXTENT_XMAX;
				o.extent.ymax = _MIN_EXTENT_YMAX;
				//console.log("xmax changed");
			}
			params.MapPoint = o.mapPoint;
			params.MapPolyline = o.mapPolyline;
			params.MapPolygon = o.mapPolygon;
			params.MapExtent = o.extent.xmin + ' ' + o.extent.ymin + ' ' + o.extent.xmax + ' ' + o.extent.ymax;
			params.MapTemplateIndex = o.templateIndex;
			
			// 当传入的矢量数据为空时，为保证提交参数正确，所以虚构参数
			if(!params.MapPolyline.features.length){
				var polylineJson = {
					"paths" : [[[0,0],[1,1]]],
					"spatialReference":{"wkid":4326}
				}
				params.MapPolyline.features[0] = new esri.Graphic(new esri.geometry.Polyline(polylineJson));
				params.MapPolyline.geometryType = "esriGeometryPolyline";
			}
			
			if(!params.MapPolygon.features.length){
				var polygonJson = {
					"rings" : [[[0,0],[1,1],[2,2]]],
					"spatialReference":{"wkid":4326}
				}
				params.MapPolygon.features[0] = new esri.Graphic(new esri.geometry.Polygon(polygonJson));
				params.MapPolygon.geometryType = "esriGeometryPolygon";
			}
			
			if(!params.MapPoint.features.length){
				params.MapPoint.features[0] = new esri.Graphic(new esri.geometry.Point(0,0,new esri.SpatialReference(4326)));
				params.MapPoint.geometryType = "esriGeometryPoint";
			}
			return api;
		}

		// 生成专题地图
		function _generateThemeMap() {
			_GPJobStatus = STATUS_SUBMIT;
			geoProcessorTool = new esri.tasks.Geoprocessor(gpUrl);
			geoProcessorTool.submitJob(params, _getResult, _onStatus);
		}

		// GP服务执行成功后回调函数
		function _getResult(jobInfo) {
			if(_getStatus() == STATUS_SUCCEEDED) {
				//console.log(jobInfo);
				var imgParam = new esri.layers.ImageParameters();
				imgParam.format = 'gif';
				geoProcessorTool.getResultData(jobInfo.jobId, 'MapOutput', function(result) {
					// server只返回tip格式的，在这里将扩展名修改为gif
					resultUrl = result.value.url;
					var urlArr = resultUrl.split(".");
					resultUrl = urlArr[0]+".gif";
				});
			}

		}

		// 处理状态函数
		function _onStatus(statusInfo) {
			//console.log(statusInfo);
			with(statusInfo) {
				if(jobStatus == 'esriJobSubmitted') {
					_GPJobStatus = STATUS_SUBMIT;
				} else if(jobStatus == 'esriJobExecuting') {
					_GPJobStatus = STATUS_EXECUTING;
				} else if(jobStatus == 'esriJobSucceeded') {
					_GPJobStatus = STATUS_SUCCEEDED;
				} else {
					_GPJobStatus = STATUS_FAILED;
				}
			}
		}

		// 获取当前工作状态
		function _getStatus() {
			return _GPJobStatus;
		}

		// 获取结果
		function _getResultUrl() {
			return resultUrl;
		}

		return api;
	}
})();
