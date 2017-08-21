/**
 * 资源调度-最近设施分析 
 * @author lhf
 * @data 2013-09-25
 */

itmap.arcgis.closestFacility=(function(){
	return function(naclosesturl, facilitiesGraphics, incidentsGraphics) {
		/**
		 * 参数：na路网服务地址，设施点集合，选择的点集合
		 * 设置的额外参数：可以设置障碍物图层
		 * */
		var api = {
				setBarriers:setBarriers,
				analysis:analysis
			};
		
		var util = itmap.arcgis.util;
		
		//closestFacility分析所需要的参数
		var params;
		var barriers = new esri.tasks.FeatureSet();
		var barriersPolyline = new esri.tasks.FeatureSet();
		var barriersPolygon = new esri.tasks.FeatureSet();
		
		function initParams(){
			var facilities = new esri.tasks.FeatureSet();
			facilities.features = facilitiesGraphics;
			var incidents = new esri.tasks.FeatureSet();
			incidents.features = incidentsGraphics;
			
			params = new esri.tasks.ClosestFacilityParameters();
			params.defaultCutoff= 100000;
			params.returnIncidents=true;
			params.returnRoutes=true;
			//params.returnDirections=true;
			params.returnFacilities=true;
			params.facilities = facilities;// 设置设施要素集
			params.incidents = incidents;// 设置分析点要素集
			params.outSpatialReference = map.spatialReference; 
		}
		
		// 初始化临近设施分析类
		initParams();
		
		function setBarriers(barriers, polylineSet, polygonSet) {
			if(util.checkParam(barriers)) {
				var pointBarriers = new esri.tasks.FeatureSet();
				pointBarriers.features = barriers;
				params.pointBarriers = pointBarriers;
			}
			if(util.checkParam(polylineSet)){
				var polylineBarriers = new esri.tasks.FeatureSet();
				polylineBarriers.features = polylineSet;
				params.polylineBarriers = polylineBarriers;
			}
			if(util.checkParam(polygonSet)){
				var polygonBarriers = new esri.tasks.FeatureSet();
				polygonBarriers.features = polygonSet;
				params.polygonBarriers = polygonBarriers;
			}
			return api;
		}
		
		//执行closestFacility分析
		function analysis(successFun, errorFun){
			if(!util.checkParam(successFun))return;
			if(!util.checkParam(errorFun))errorFun = defaultErrorFun;
			var cfTask = new esri.tasks.ClosestFacilityTask(naclosesturl);
			cfTask.solve(params, successFun, errorFun);
		}
		//分析成功后的回调函数
		function successFun(result){
			if(util.checkParam(callback))_callback();
			if(util.checkParam(graphicLayer)) {
				var directions = result.directions;
				dojo.forEach(result.routes,function(route,index){
					var attr = dojo.map(result.directions[index].features,function(feature){
						return feature.attributes.text;
					});
					
					var infoTemplate = new esri.InfoTemplate("Attributes","<div>"+attr+"</div>");;
					route.setInfoTemplate(infoTemplate);
					route.setAttributes(attr);
					routeGraphicsLayer.add(route);
				});
			}
		}
		//分析失败后的回调函数
		function defaultErrorFun(result){
			if(typeof console != 'undefined' && console.log)console.log(result.message);
		}
		
		return api;
	}
})();
