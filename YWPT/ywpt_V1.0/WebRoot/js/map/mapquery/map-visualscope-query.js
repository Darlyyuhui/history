/**
 * 可视范围查询类
 * @author ZLT
 * @since 2013-10-24
 */
var mapVisualScopeQuery = (function(){
	return function(){

		/**
		 * 对外接口
		 */
		var api = {
			qCross : queryCross, // 查询卡口
			qDetector : queryDetector, // 查询检测器
			qPark : queryPark, // 查询停车诱导屏
			qSignalLamp :querySignalLamp, // 查询信号灯
			qSignBoard : querySignBoard // 查询诱导屏
		};

		/**
		 * 服务链接
		 */
		var _urls = {
			device : baseServiceURL.device.url,
			detector : baseServiceURL.detector.url,
			park : baseServiceURL.park.url,
			signallamp : baseServiceURL.signallamp.url,
			signboard : baseServiceURL.signboard.url
		};

		/**
		 * 地图资源
		 */
		var _map = itmap.arcgis.map;

		/**
		 * 查询类型
		 */
		var _queryType = "";

		/**
		 * 查询
		 */
		function _query(url,extent){
			var _qt = new esri.tasks.QueryTask(url);
			var _queryh = new esri.tasks.Query();
			_queryh.geometry = extent;
			_queryh.outFields = ["*"];
			_queryh.returnGeometry = true;
			_qt.execute(_queryh,resultHandler);
		}

		/**
		 * 查询结果处理
		 */
		function resultHandler(featureset){
			var resultSize = featureset.features.length;
			if(!resultSize){
				return;
			}

			var symbol = "";
			switch(_queryType){
				case "cross" : symbol = itmap.arcgis.symbol.deviceSymbol(); break;
				case "detector" : symbol = itmap.arcgis.symbol.detectorSymbol(); break;
				case "park" : symbol = itmap.arcgis.symbol.parkSymbol(); break;
				case "signallamp" : symbol = itmap.arcgis.symbol.singnalLampSymbol(); break;
				case "signboard" : symbol = itmap.arcgis.symbol.signBoardSymbol(); break;
				default : symbol = itmap.arcgis.symbol.defaultPointSymbol();break;
			}

			while(resultSize--){
				if(resultSize<0){
					break;
				}
				var feature = featureset.features[resultSize];
				var layer = itmap.arcgis.mapGraphicManager("mapvisualscoperesult");
				var graphic = new esri.Graphic(feature.geometry,symbol);
				graphic.attributes = feature.attributes;
				layer.add(graphic);
			}
			delete layer.getLayer().onClick;
			dojo.connect(layer.getLayer(),"onClick",function(e){bindClickEvt(e,{layerName:_queryType})});
		}

		// 绑定点击事件
		function bindClickEvt(evt,data){
			$.post("map/home/getMapGraphicsAttr/",{objID:evt.graphic.attributes["OBJECTID"],layerName:data.layerName},function(data){
				var content = "<table>";
				content += "<tr><td>ID：</td><td>"+evt.graphic.attributes["OBJECTID"]+"</td></tr>";
				for(elem in data){
					content += "<tr><td>"+elem+"：</td><td>"+data[elem]+"</td></tr>";
				}
				content+="</table>";
				mapInfoWindowTool.setContent(content);
			},"json");
			mapInfoWindowTool.setContent("<img src='images/loading.gif' />").show(_map,evt.graphic.geometry);
		}

		/**
		 * 查询卡口
		 */
		function queryCross(extent){
			_query(_urls.device,extent);
			_queryType = "cross";
		}

		/**
		 * 查询检测器
		 */
		function queryDetector(extent){
			_query(_urls.detector,extent);
			_queryType = "detector";
		}

		/**
		 * 查询停车场诱导
		 */
		function queryPark(extent){
			_query(_urls.park,extent);
			_queryType = "park";
		}

		/**
		 * 查询信号灯
		 */
		function querySignalLamp(extent){
			_query(_urls.signallamp,extent);
			_queryType = "signallamp";
		}

		/**
		 * 查询诱导屏
		 */
		function querySignBoard(extent){
			_query(_urls.signboard,extent);
			_queryType = "signboard";
		}
		
		return api;
	}
})();