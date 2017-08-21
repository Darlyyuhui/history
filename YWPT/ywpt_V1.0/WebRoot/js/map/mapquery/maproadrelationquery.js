/**
 * 道路关联查询
 * @author ZLT
 * @since 2014-3-6
 **/
var roadRelationQuery = (function(){
	return function(){

		/**
		 * 返回API
		 */
		var api = {
			queryRoadByScreenPoint : queryRoadByScreenPoint,
			queryCross : queryCross
		}

		/**
		 * 基础地图容器
		 */
		var mapC = itmap.arcgis.map;

		/**
		 * 服务链接
		 */
		var urls = {
			roadURL : baseServiceURL.roadline.url, // 道路服务链接
			crossUrl : "cross/search/getDeviceByRoadNames/" // 卡口服务链接
		}

		/**
		 * 转换点
		 */
		function _convertPoint(screenPoint){
			var mapPoint = mapC.toMap(screenPoint);
			return mapPoint;
		}

		/**
		 * 将点转换为Extent
		 */
		function _pointToExtent(mapPoint){
			var mapLevel = mapC.getLevel(),
				tolCoefficient = mapLevel<4?5:mapLevel>5?mapLevel>6?120:60:15,
				tol = mapC.getLayer('myTiledMapServiceLayer').tileInfo.lods[mapLevel].resolution * tolCoefficient,
				queryExtent = new esri.geometry.Extent(mapPoint.x-tol,mapPoint.y-tol,mapPoint.x+tol,mapPoint.y+tol,mapC.spatialReference);
			return queryExtent;
		}

		/**
		 * 查询路
		 */
		function _queryRoad(geometry,funcCall){
			var queryTask = new esri.tasks.QueryTask(urls.roadURL);
			var queryParams = new esri.tasks.Query();
			queryParams.geometry = geometry;
			queryParams.geometryPrecision = 0;
			queryParams.returnGeometry = true;
			queryParams.outFields = ["*"];
			queryTask.execute(queryParams,function(features){
				if(features.features && features.features.length){
					if(funcCall){
						funcCall(features.features);
					}
				}
			});
		}

		/**
		 * 按路查询
		 */
		function queryRoadByScreenPoint(currentPos,funcCall){
			var mapPoint = _convertPoint(new esri.geometry.Point(currentPos.left,currentPos.top)),
				extent = _pointToExtent(mapPoint);
			//mapC.graphics.clear();
			//mapC.graphics.add(new esri.Graphic(extent,itmap.arcgis.symbol.defaultPolygonSymbol()));
			_queryRoad(extent,funcCall);
		}

		/**
		 * 查询卡口
		 * @param currentPos
		 * @return
		 */
		function queryCross(currentPos){
			queryRoadByScreenPoint(currentPos,function(features){
				var roadNames = '';
				var name = '';
				var crossMgm = itmap.arcgis.mapGraphicManager("cross");
				crossMgm.clear();
				for(var i=0,len=features.length;i<len;i++){
					name = features[i].attributes.DLMC;
					if(name){
						if(i>0 && i<len){
							roadNames += ',';
						}
						roadNames += name;
					}
				}

				$.post(urls.crossUrl,{roadNames:roadNames},function(deviceList){
					if(deviceList && deviceList.length){
						var hasDevicePos = false;
						for(var j=0,listSize=deviceList.length;j<listSize;j++){
							var device = deviceList[j];
							if(device.mapx && device.mapy){
								var	mapPoint = new esri.geometry.Point(device.mapx,device.mapy,mapC.spatialReference);
								var	graphic = new esri.Graphic(mapPoint,itmap.arcgis.symbol.crossMarkerSymbol(),device);
								crossMgm.add(graphic);
								hasDevicePos = true;
							}
						}
						if(!hasDevicePos){
							itmap.util.mapTipTool().setContent("该条道路上的卡口没有位置信息").show(2000);
						}else{
							dojo.connect(crossMgm.getLayer(),"onClick",function(e){
								mapCross().getCrossDeviceByCode(e.graphic);
							});
						}
					}else{
						itmap.util.mapTipTool().setContent("该条道路上没有卡口信息").show(2000);
					}
				});
			});
		}

		return api;
	}
})()