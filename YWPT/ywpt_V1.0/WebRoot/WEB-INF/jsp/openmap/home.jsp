<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/map_header.jspf"%>



<link href='css/opencss/openmapstyle.css' rel='stylesheet'>

<link href='css/transport.css' rel='stylesheet'>
<%-- 引入openLayer相关 --%>
<link rel="stylesheet" type="text/css" href="compnents/openmap/theme/default/style.css"/>
<script type="text/javascript" src="compnents/openmap/lib/OpenLayers.js"></script>
<%-- 引入dojo相关包，为诱导屏的svg绘制最准备  --%>
<script type="text/javascript" src="${atms['map_restUrl']}/arcgis_js_api/library/3.5/jsapi/init.js"></script>	
<script type="text/javascript" src="${atms['map_restUrl']}/arcgis_js_api/library/3.5/jsapi/js/dojo/dojo/dojo.js" ></script> 
<link rel="stylesheet" type="text/css"   href="${atms['map_restUrl']}/arcgis_js_api/library/3.5/jsapi/js/dojo/dijit/themes/tundra/tundra.css"/>
<link rel="stylesheet"  type="text/css"  href="${atms['map_restUrl']}/arcgis_js_api/library/3.5/jsapi/js/dojo/dojo/resources/dojo.css">
<script type="text/javascript" src="compnents/itmap/openlayer/play.js"></script>
<script type="text/javascript" src="compnents/itmap/openlayer/renderer.js"></script>
<script type="text/javascript" src="compnents/itmap/openlayer/util.js"></script>
<script type="text/javascript" src="compnents/itmap/openlayer/navigation.js"></script>
<script type="text/javascript" src="compnents/itmap/openlayer/toc.js"></script>
<script type="text/javascript" src="compnents/itmap/openlayer/viewswitcher.js"></script>
<script type="text/javascript" src="js/openmap/basemaptools/maptool.js"></script>

<script type="text/javascript">
  var map,layer, measureControls,track_layer,drawControl_layer,positionLayer;
  OpenLayers.ProxyHost="openproxy.jsp?url=";
  OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
  OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;
  function bodyOnLoad() {}
  main();
function main() {
	   var format = 'image/png';
      
       var options = {
        controls:[],
        maxExtent: new OpenLayers.Bounds(108.6121489285334,34.18003479100233,109.38484380332164,34.37320850969939),   
          resolutions: [1.886462096650995E-4, 8.803489784371309E-5, 3.772924193301989E-5, 1.8864620966509947E-5, 8.803489784371308E-6, 2.5152827955346597E-6, 1.2576413977673299E-6],
          projection: "EPSG:4326",
          units: 'degrees',
       
        };          
      map = new OpenLayers.Map('map', options);  
      rightClickMenu();
      
      tiled = new OpenLayers.Layer.WMS(
              "西安市底图", baseServiceURL.geoserverWMS.url,
              {
                  LAYERS: baseServiceURL.geoserverBaseLayer.url,
                  STYLES: '',
                  format: format,
                  tiled: true,
                  tilesOrigin : map.maxExtent.left + ',' + map.maxExtent.bottom
              },
              {
                  buffer: 0,
                  displayOutsideMaxExtent: true,
                  isBaseLayer: true,
                  yx : {'EPSG:4326' : true}
              } 
          );
      tiled.id="tiledLayer";
      

      
    
      
      //专门用于绘制的图层
      track_layer=new OpenLayers.Layer.Vector("trackLayer");
      track_layer.id = "trackLayer";
       
      //也是绘制图层，暂时不删，挖占，om，交通规划中引用到了
      drawControl_layer = new OpenLayers.Layer.Vector("drawControl_layer");
      drawControl_layer.id="drawControl_layer";
      	      var positionStyle = new OpenLayers.Style(
	    	        {
	    	        	graphicWidth: 34,	
						graphicHeight: 40,	
						graphicXOffset: -10,
						graphicYOffset: -40,
						externalGraphic: "images/map/position.png"
	    	        },	    	     
	    	        {
	    	            rules: [
							new OpenLayers.Rule({							
							    filter: new OpenLayers.Filter.Comparison({
							        type: OpenLayers.Filter.Comparison.EQUAL_TO,
							        property: "name", // the "foo" feature attribute
							        value: ""
							    }),
	    	                    symbolizer: {
	    	                        externalGraphic: "images/map/position.png"
	    	                    }
	    	                }),
		    	                new OpenLayers.Rule({
	    	                    elseFilter: true,
	    	                    symbolizer: {
	    	                        externalGraphic: "images/map/position.png"
	    	                    }
	    	                })
	    	            ]
	    	        }
	    	    );
      positionLayer=new  OpenLayers.Layer.Vector("positionLayer",{styleMap: new OpenLayers.StyleMap(positionStyle)});
      positionLayer.id="positionLayer";
      positionLayer.setZIndex(20);
      
      map.addLayer(track_layer); 
      map.addLayer(drawControl_layer);
      map.addLayer(positionLayer); 
      map.addLayer(tiled); 
      
      //图层控制器
      //map.addControl(new OpenLayers.Control.LayerSwitcher());
      //地图右下角对鼠标位置的监听
      map.addControl(new OpenLayers.Control.MousePosition());
      //地图测量-----
        var measureSymbolizers = {
                "Point": {
                    pointRadius: 8,
                    graphicName: "circle",
                    fillColor: "red",
                    fillOpacity: 1,
                    strokeWidth: 2,
                    strokeOpacity: 1,
                    strokeColor: "red"
                },
                "Line": {
                    strokeWidth: 3,
                    strokeOpacity: 1,
                    strokeColor: "red",
                    strokeDashstyle: "dash"
                },
                "Polygon": {
                    strokeWidth: 2,
                    strokeOpacity: 1,
                    strokeColor: "red",
                    fillColor: "red",
                    fillOpacity: 0.3
                }
            };
            
            var measurestyle = new OpenLayers.Style();
                measurestyle.addRules([
                new OpenLayers.Rule({symbolizer: measureSymbolizers})
                ]);
            var measurestyleMap = new OpenLayers.StyleMap({"default": measurestyle});
            var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
                renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers; 
                measureControls = {
                line: new OpenLayers.Control.Measure(
                    OpenLayers.Handler.Path, {
                        persist: true,
                        handlerOptions: {
                            layerOptions: {
                                renderers: renderer,
                                styleMap: measurestyleMap
                            }
                        }
                    }
                ),
                polygon: new OpenLayers.Control.Measure(
                    OpenLayers.Handler.Polygon, {
                        persist: true,
                        handlerOptions: {
                            layerOptions: {
                                renderers: renderer,
                                styleMap: measurestyleMap
                            }
                        }
                    }
                )
            }; 
            
            for(var key in measureControls) {
                   var measurecontrol = measureControls[key];
                   measurecontrol.events.on({"measure": handleMeasurements});
                   map.addControl(measurecontrol);
              }    
      
      //地图鹰眼添加
    　　   map.addControl(new OpenLayers.Control.OverviewMap());
      map.addControl(new OpenLayers.Control.PanZoomBar({
          position: new OpenLayers.Pixel(16, 43),
          zoomWorldIcon : false,
          panIcons : false
      }));
      map.addControl(new OpenLayers.Control.Navigation());
      map.addControl(new OpenLayers.Control.Scale($('scale')));
      
      var initBounds = new OpenLayers.Bounds(108.79421457225156,34.185600937445486,109.06190393540747,34.361443105776345);
      map.zoomToExtent(initBounds);
      /**
	   * 地图导航
	   **/
      new itmap.openlayer.navigation({
          mapC : map,
          container : "mapNavigationBox",
          initExtent : initBounds
      });
      
      /**
       * 地图视图切换器
       **/
      new itmap.openlayer.viewSwitcher({
      	mapC : map
      });
      
      /**
       * 地图右上角菜单
       **/
      itmap.util.mapDropDownMenu().init({srcNode:"mapToolBar",data:[
			{label:"图层工具",func:function(){mapTOC()}}
	  ]});
      
      /**
	   * 调用TOC
	   **/
	  function mapTOC(){
			/**
			 * 图层控制承载器
			 **/
			var _mapTOC = itmap.util.dialog({
				mutiDialog : true,
				mutiDialogSeed : "MAPTOC",
				mask : false,
				title : '图层',
				right : 10,
				top : 35,
				content : "<div id='mapTOC' style='width:180px;max-height:400px;overflow:hidden;overflow-y:scroll;overflow-x:hidden;'></div>",
				buttonDisplay : {
					confirmButton : false,
					cancelButton : false
				}
			}).show();
			var _toc = new itmap.openlayer.TOC();
			_toc.init({
				src : "mapTOC",
				layerName : {
					tiledLayer : "底图",
					crossRouteLayer : "卡口历史轨迹",
					gpsmoveLayer : "轨迹回放车辆",
					cross : "卡口",
					crossAlarm : "卡口报警",
					crossAnalyLayer : "卡口分析",
					connectedLayer : "卡口关联查询",
					crossAnalyBarriersLayer : "卡口障碍物",
					positionLayer : "定位图层",
					trackLayer : "绘制图层"
				},
				group : [
					{label:"业务图层",layers:[]},
					{label:"基础图层",layers:["tiledLayer"]}
				],
				excludeLayer : ["result","positionLayer","drawControl_layer","trackLayer"]
			});
	  }
      
 
}
function rightClickMenu(){
	
	var MapRightClickWidgetWork = itmap.util.mapRightClickWidget();
	MapRightClickWidgetWork.init({srcNode:"map"})
						   .add("清屏", clearAllGraphics)
						   .add("取消","");

}
		/**
		 * 清除所有的graphics
		 */
		function clearAllGraphics() {
			var allLayers = map.layers;
			var excludeLayer = ["tiledLayer","result","positionLayer","drawControl_layer","trackLayer"];
			var flag = false;
			for(var i=0,len=allLayers.length; i<len; i++) {
				// 移除初始化以外的图层
				flag = false;
				if(allLayers[i] && allLayers[i].id) {
					for(var j=0,jlen=excludeLayer.length; j<jlen; j++) {
						if(allLayers[i].id == excludeLayer[j]) {
							flag = true;
							break;
						}
					}
					if(!flag) {
						map.removeLayer(allLayers[i]);
						i--;
					}
				}
			}
			// 清除弹出框
			var pops = map.popups;
			for(var i=0,len=pops.length; i<len; i++) {
				map.removePopup(pops[i]);
			}
			itmap.util.mapResultboxNew().init("mapResultC").clearBox();
			$("#mapOther").empty();
			
			// 在点击清屏的时候，清空所有以加载模块的jsp，并重新加载当前jsp页面
			var j;
			for(var i in loadContext) {
				loadContext[i].value[0].innerHTML="";
				if(loadContext[i].url == currentRest) {
					j=i;
				}
			}
			if(currentRest)loadContext[j].value.load(currentRest);
		}

	(function(){
		/**
		 * 左侧菜单缩进
		 **/
		$("#mapCenter").toggle(function(){
			$("#mapLeft").hide();
			$("#mapLeftBox").width(0);
			$("#optionImg").attr("src","images/picone/arr_right.png");
		},function(){
			$("#mapLeftBox").width(240);
			$("#mapLeft").show();
			$("#optionImg").attr("src","images/picone/arr_left.png");
		});

	})()
</script>

<%-- 引入工具类相关--%>
<script type="text/javascript" src="js/map/mapquery/mapchart.js"></script>
<script type="text/javascript" src="compnents/itmap/util/map-chart.js"></script>

<script type="text/javascript" src="js/openmap/util/OLaddFeature.js"></script>
<script type="text/javascript" src="js/openmap/util/OLbuffer.js"></script>
<script type="text/javascript" src="js/openmap/util/OLdeleteFeature.js"></script>
<script type="text/javascript" src="js/openmap/util/OLeditFeature.js"></script>
<script type="text/javascript" src="js/openmap/util/OLidentity.js"></script>
<script type="text/javascript" src="js/openmap/util/OLquery.js"></script>
<script type="text/javascript" src="js/openmap/flowPublish/OLtrafficGuidingScreen.js"></script>

<%-- 引入业务相关 --%>
<script type="text/javascript" src="js/openmap/parkinglot/OLPark.js"></script>
<script type="text/javascript" src="js/openmap/parkinglot/OLTrafficGuidingPanel.js"></script>
<script type="text/javascript" src="js/openmap/flowPublish/OLtrafficGuidingScreen.js"></script>
<script type="text/javascript" src="js/openmap/flowPublish/OLflowPublish.js"></script>
<script type="text/javascript" src="js/openmap/cross/map-cross.js"></script>
<script type="text/javascript" src="js/openmap/cross/map-cross-alarm.js"></script>

<script type="text/javascript" src="js/openmap/gps/mapGPS.js"></script>
<script type="text/javascript" src="js/openmap/occpuy/OLoccpuy.js"></script>
<script type="text/javascript" src="js/openmap/occpuy/OLoccpuySearch.js"></script>
<script type="text/javascript" src="js/openmap/om/OLstationSearch1.js"></script>
<script type="text/javascript" src="js/openmap/om/OLStatic.js"></script>
<script type="text/javascript" src="js/openmap/trafficControl/onWayAccess.js"></script>
<script type="text/javascript" src="js/openmap/trafficControl/trafficConrolArea.js"></script>
<script type="text/javascript" src="js/openmap/netWork/NAServer.js"></script>
<script type="text/javascript" src="js/map/Cross/map-amq.js"></script>
<script type="text/javascript" src="js/openmap/pass/roadquery.js"></script>


<%@ include file="/WEB-INF/jsp/common/map_footer.jspf"%>