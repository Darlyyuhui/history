<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.input {
	border-left:1px solid #1f6377;
	border-top:1px solid #1f6377;
	border-right:1px solid #b3bfca;
	border-bottom:1px solid #b3bfca;
	height:18px;
	padding:3px;
}
</style>
<!-- 
<ul id="caTab" class='zTabTool'>
	<li>关联分析</li>
	<li>行程分析</li>
</ul>
 -->
<div id="caTabContent" class='zTabToolContent'>
  <!-- 
  <div>
	  <p style="margin:0px;">
	  	根据道路选择卡口设备：
	    <img id="selCrossByRoad" src="images/map/i_draw_rect.png" alt="选择">
	  </p>
	  <p style="margin:0px;">
	  	根据卡口设备选择道路：
	    <img id="selRoadByCross" src="images/map/i_draw_rect.png" alt="选择">
	  </p>
  </div>
 -->
  <div class="mar_5">
    <table classs="map_table">
      <tr>
        <td>起点：</td>
        <td><img style="width:30px;height:30px;" id="selCrossPoint1" src="images/map/i_draw_rect.png" alt="选择"> <img style="width:30px;height:30px;" id="addCrossPoint1" src="images/map/i_pin2.png" alt="标注"></td>
      </tr>
      <tr>
        <td>终点：</td>
        <td><img style="width:30px;height:30px;" id="selCrossPoint2" src="images/map/i_draw_rect.png" alt="选择"> <img style="width:30px;height:30px;" id="addCrossPoint2" src="images/map/i_pin2.png" alt="标注"></td>
      </tr>
      <tr>
        <td>绘制障碍物：</td>
        <td>
        <img style="width:30px;height:30px;" id="ca_barriersPoint" src="images/map/i_draw_point.png" alt="点"> 
        <img style="width:30px;height:30px;" id="ca_barriersPolyline" src="images/map/i_draw_line.png" alt="线">        
        <img style="width:30px;height:30px;" id="ca_barriersPolygon" src="images/map/i_draw_poly.png" alt="面">
        </td>
      </tr>
    </table>
  </div>
  <div class="btn_line mar_t10" style="padding:5px">
      <input id="crossRoutSearchId" type="button" class="btn btn-info mar_l10" value="分析">
      <input id="caClear" type="button" class="btn btn-info mar_l10" value="清除">
  </div>
</div>
<script type="text/javascript">
	MapFactory.Require([
		"ItmsMap/MapConfig",
		"MapFactory/GeometryType*",
		"MapFactory/LayerManager",
		"MapFactory/GraphicManager",
		"MapFactory/Query",
		"MapFactory/Route",
		"MapFactory/Util/Renderer*",
		"ItmsMap/Util/Tab*",
		"MapFactory/Draw",
		"ItmsMap/Util/Tip*",
		"ItmsMap/Cross/Cross*",
		"ItmsMap/Util/ModuleMessage*"
	],function(mapConfig, geoType, layerFun, graphicFun, queryFun, routeFun, renderFun, tab, drawFun, Tip, crossFun, ModuleMessage){
		var firstCross;
		var secondCross;
		var barriers = [];
		var tip = Tip();
		var crossAnalyLayer = layerFun("crossAnalyLayer");// 分析图层
		var crossAnalyBarriersLayer = layerFun("crossAnalyBarriersLayer");// 障碍物图层
		var query = queryFun();
		var draw = drawFun();
		var render = renderFun();
		var route = routeFun();
		route.setUrl(mapConfig.layers.route.url);
		// 初始化选项卡
		//tab().init("caTab", "caTabContent", 1);
		
		crossFun().showAll();// 显示所有的卡口设备
		
		// 添加绘制监听
		// 根据道路选择卡口
		$("#selCrossByRoad").click(function(){
			relationshipsSearch(1);
		});
		// 根据卡口选择道路
		$("#selRoadByCross").click(function(){
			relationshipsSearch(0);
		});
		$("#selCrossPoint1").click(function(){
			selCrossPoint(1);
		});
		$("#addCrossPoint1").click(function(){
			addCrossPoint(1);
		});
		$("#selCrossPoint2").click(function(){
			selCrossPoint(0);
		});
		$("#addCrossPoint2").click(function(){
			addCrossPoint(0);
		});
		// 绘制障碍点
		$("#ca_barriersPoint").click(function(){
			draw.deactivate();
			draw = drawFun();
			draw.setGeoType(geoType.POINT);
			draw.setDrawEndEvent(function(geometry){
				if(geometry) {
					barriers.push(geometry);
					graphicFun({geo:geometry}).addToLayer("crossAnalyBarriersLayer");
				}
				draw.deactivate();
			});
			draw.activate();
		});
		
			// 绘制障碍线
		$("#ca_barriersPolyline").click(function(){
			draw.deactivate();
			draw = drawFun();
			draw.setGeoType(geoType.POLYLINE);
			draw.setDrawEndEvent(function(geometry){
				if(geometry) {
					barriers.push(geometry);
					graphicFun({geo:geometry}).addToLayer("crossAnalyBarriersLayer");
				}
				draw.deactivate();
			});
			draw.activate();
		});
		
			// 绘制障碍面
		$("#ca_barriersPolygon").click(function(){
			draw.deactivate();
			draw = drawFun();
			draw.setGeoType(geoType.POLYGON);
			draw.setDrawEndEvent(function(geometry){
				if(geometry) {
					barriers.push(geometry);
					graphicFun({geo:geometry}).addToLayer("crossAnalyBarriersLayer");
				}
				draw.deactivate();
			});
			draw.activate();
		});
		
		
		
		
		// 分析查询
		$("#crossRoutSearchId").click(function(){
			if(firstCross && secondCross) {
				route.setStart(firstCross.getGraphic().geo);
				route.setEnd(secondCross.getGraphic().geo);
				route.setStops([]);
				route.setBarriers(barriers);
				route.solve(function(result){
					if(result.length > 0) {
						graphicFun({geo:result[0].geo, symbol:{outLineWidth: 6, outLineColor: "#8e8eea", outLineStyle: "Solid"}}).addToLayer("crossAnalyLayer");
						//var points = [];
						//var pointss = result[0].geo.points.split(",");
						//for(var i=0,len=pointss.length; i<len; i+=2) {
						//	points[i/2] = [Number(pointss[i]), Number(pointss[i+1])];
						//}
						//render.addRouteResult(points, "crossAnalyLayer");
						// 添加箭头
						//render.addRouteResultArrow(points, "crossAnalyLayer");
					}
					else {
						ModuleMessage.showMessage("<span style='color: red;'>无法分析出对应的路径！</span>",ModuleMessage.LOG);
					}
				}, function(error){
					ModuleMessage.showMessage("<span style='color: red;'>路径分析时候出错了！</span>",ModuleMessage.LOG);
					if (typeof console != 'undefined' && console.log) console.log("卡口--最短路径分析的时候，出错了！"+error);
				});
			}
			else {
				ModuleMessage.showMessage("<span style='color: red;'>请选择卡口！</span>",ModuleMessage.LOG);
			}
		});
		// 清除
		$("#caClear").click(function(){
			draw.deactivate();
			crossAnalyBarriersLayer.clear();
			crossAnalyLayer.clear();
			firstCross = null;
			secondCross = null;
			barriers = [];
		});
		
		// 添加起始点到地图上
		function addPointToMap(geometry) {
			if(isFirst) {
				if(firstCross)firstCross.remove();
				firstCross = graphicFun({geo:geometry, symbol:{url:"images/start.png", width:24, height:24}});
				firstCross.addToLayer("crossAnalyLayer");
			}
			else {
				if(secondCross)secondCross.remove();
				secondCross = graphicFun({geo:geometry, symbol:{url:"images/end.png", width:24, height:24}});
				secondCross.addToLayer("crossAnalyLayer");
			}
		}
		var isFirst = 0;
		// 选择点
		function selCrossPoint(_isFirst) {
			draw.deactivate();
			draw = drawFun();
			draw.setGeoType(geoType.POLYGON);
			draw.setDrawEndEvent(_polygonDrawEnd);
			draw.activate();
			isFirst = _isFirst;
		}
		// 选择点的回调函数
		function _polygonDrawEnd(geometry) {
			query.setUrl(mapConfig.layers.cross.url);
			query.setGeometry(geometry);
			query.execute(function(result) {
				if(!result || result.length<=0) {
					showTipMsg("该区域没有卡口设备！");
					return;
				}
				addPointToMap(result[0].geo);
			}, function(error) {
				showTipMsg("查询卡口设备出错了！");
				if (typeof console != 'undefined' && console.log) console.log("根据几何体查询卡口数据的时候，出错了！"+error);
			});
			draw.deactivate();
		}
		// 添加点
		function addCrossPoint(_isFirst) {
			draw.deactivate();
			draw = drawFun();
			draw.setGeoType(geoType.POINT);
			draw.setDrawEndEvent(_pointDrawEnd);
			draw.activate();
			isFirst = _isFirst;
		}
		// 添加点的回调函数
		function _pointDrawEnd(geometry) {
			if(geometry)addPointToMap(geometry);
			draw.deactivate();
		}
		
		// 道路卡口关联查询
		function relationshipsSearch() {
			draw.deactivate();
			draw = drawFun();
			draw.setGeoType(geoType.POLYGON);
			draw.setDrawEndEvent(_relationshipsSearchResult);
			draw.activate();
		}
		function _relationshipsSearchResult(geometry) {
			query.setUrl(mapConfig.layers.cross.url);
			query.setGeometry(geometry);
			query.execute(function(result) {
				if(!result || result.length<=0) {
					showTipMsg("该区域没有卡口设备！");
					return;
				}
				var graphic = graphicFun({geo:result[0].geo, symbol:{url:"images/start.png", width:24, height:24}});
				graphic.addToLayer("crossAnalyLayer");
			}, function(error) {
				showTipMsg("查询出错了！");
				if (typeof console != 'undefined' && console.log) console.log("根据几何体查询卡口数据的时候，出错了！"+error);
			});
			draw.deactivate();
		}
		
		// 地图中心提示消息
		function showTipMsg(msg) {
			tip.setContent(msg);
			tip.show();
			setTimeout(function(){
				tip.setContent("");
				tip.hide();
			}, 1500);
		}
	});
</script>
