/**
 * 设备查询
 * @Author ZLT
 * @Date 2013-8-28
 */

var mapRelatedQuery = (function(){
	return function(){
		
		// 图层资源
		var baseRoadFeatureLayer = new esri.layers.FeatureLayer(baseServiceURL.roadline.url,{
			mode : esri.layers.FeatureLayer.MODE_ONDEMAND
		});

		// 关系查询id配置
		var relationIdConfig = {
			"Devices" : 10,
			"SignalLamp" : 10,
			"TrafficGuidingPanel" : 11,
			"Park" : 12,
			"FlowPanel" : 14,
			"detector" : 17
		};

		var relationTargetIco = {
			"Devices" : "images/map/device.png",
			"SignalLamp" : "images/map/signalLamp.png",
			"TrafficGuidingPanel" : "images/map/GuidingPanel.png",
			"Park" : "images/map/park.png",
			"FlowPanel" : "images/map/flowPanel.png",
			"detector" : "images/map/detector.png"
		};
		
		var api = {
			queryTargetFeature : queryTargetFeature,
			getScreenPoint : getScreenPoint,
			relationIdConfig : relationIdConfig
		};

		// 点击对象点
		var targetPoint = null;

		var infoTemplate;
		
		// 设备代码
		var deviceCode = "";
		
		// 结果处理事件
		var deviceResultHandler = "";
		
		// 地图资源
		var _map = itmap.arcgis.map;
		
		/**
		 * 设备graphiclayer
		 */
		var deviceGraphicLayer = new esri.layers.GraphicsLayer();

		var defalutPointSymbol = new esri.symbol.SimpleMarkerSymbol();
		defalutPointSymbol.setStyle(esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE).setColor(new dojo.Color([255, 0, 0]));

		/**
		 * 获取屏幕坐标并转换
		 */
		function getScreenPoint(e){
			var e = e || window.event;
			var scrollLeft = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
			var scrollTop = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
			var pointX = scrollLeft+e.clientX - $("#mapLeftBox").width() - 10;
			var pointY = scrollTop+e.clientY - $("#mapToolBar").height();
			targetPoint = _map.toMap(new esri.geometry.Point(pointX,pointY));
			//myMap.addLayer(baseRoadFeatureLayer);
		}

		/**
		 * 查询目标feature
		 */
		function queryTargetFeature(relationID,name,resultFunc){

	  		/*var pointSymbol = new esri.symbol.SimpleMarkerSymbol();
			pointSymbol.setStyle(esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE).setColor(new dojo.Color([255, 0, 0]));
			var graphic = new esri.Graphic(clickPoint,pointSymbol);
	  		map.graphics.add(graphic);*/

			// 获取鼠标点击容差
			var mapLevel = _map.getLevel();
			var tolCoefficient = mapLevel<4?5:mapLevel>5?mapLevel>6?120:60:15;
			var tol = _map.getLayer('myTiledMapServiceLayer').tileInfo.lods[mapLevel].resolution * tolCoefficient;

			var queryExtent = new esri.geometry.Extent(targetPoint.x-tol,targetPoint.y-tol,targetPoint.x+tol,targetPoint.y+tol,targetPoint.spatialReference);
			var pointQuery = new esri.tasks.Query();
			pointQuery.geometry = queryExtent;
			pointQuery.outFields = ["*"];
			pointQuery.returnGeometry = true;

			baseRoadFeatureLayer.queryFeatures(pointQuery,function(res){
				if(res.features.length){
					var queryRoute = new esri.tasks.Query();
					queryRoute.where = "road_id = "+res.features[0].attributes.OBJECTID_1;
					queryRoute.returnGeometry = true;
					baseRoadFeatureLayer.queryFeatures(queryRoute,function(routeRes){
						var lineSymbol = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,new dojo.Color([255,0,0]),3);
						_map.graphics.clear();
						deviceGraphicLayer.clear();
						delete deviceGraphicLayer.onClick;
						_map.graphics.add(new esri.Graphic(routeRes.features[0].geometry,lineSymbol));
						queryRelationFeature(res.features[0].attributes.OBJECTID_1,relationID,name);
						deviceResultHandler = resultFunc;
					});
				}else{
					// 未找到
					itmap.util.mapTipTool().setContent("没有找到道路").show(2000);
				}
			});
		}

		/**
		 * 查询关联feature
		 */
		function queryRelationFeature(ObjId,relationID,name){
			var relationQuery = new esri.tasks.RelationshipQuery();
			relationQuery.objectIds = [ObjId];
			relationQuery.relationshipId = relationID;
			relationQuery.returnGeometry = true;
			relationQuery.outFields = ["*"];

			baseRoadFeatureLayer.queryRelatedFeatures(relationQuery,function(res){
				if(!isNullObj(res)){
					queryResultHandler(res,name);
				}else{
					itmap.util.mapTipTool().setContent("没有查询到关联的设备").show(2000);
				}
			});
		}

		/**
		 * 查询结果处理
		 */
		function queryResultHandler(res,name){
			//console.log(res);
			var deviceGraphics = [];
			for(elem in res){
				var features = res[elem].features;
				var arrSize = features.length;
				var feature = null;
				while(arrSize--){
					feature = features[arrSize];
					if(feature.geometry.type == "point"){
						var graphic = new esri.Graphic(feature.geometry);
						if(relationTargetIco[name] != ""){
							var ps = new esri.symbol.PictureMarkerSymbol(relationTargetIco[name],15,15);
							graphic.setSymbol(ps);
						}else{
							graphic.setSymbol(defalutPointSymbol);
						}
						
						graphic.attributes = features[arrSize].attributes;
						
						deviceGraphics.push(graphic);
						deviceGraphicLayer.add(graphic);
						//myMap.graphics.add(graphic);
					}
				}
				
			}
			

			dojo.connect(deviceGraphicLayer,"onClick",function(events){
				// 结果处理回调函数
				if(deviceResultHandler != "" && typeof deviceResultHandler != "undefined"){
					deviceResultHandler(events.graphic);
				}
			});
			_map.addLayer(deviceGraphicLayer);
			
			
		}


		/**
		 * 判断是否为空对象
		 */
		function isNullObj(o){
			for(var i in o){
				if(o.hasOwnProperty(i)){
					return false;
				}
			}
			return true;
		}
		return api;
	}
})();
