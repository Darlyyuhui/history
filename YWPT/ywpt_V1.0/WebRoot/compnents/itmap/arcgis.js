//导入相关包操作:
dojo.require("dojo.cookie");
dojo.require("dojo.parser");
dojo.require("esri.map");
dojo.require("esri.dijit.Legend");
dojo.require("esri.dijit.Scalebar");
dojo.require("esri.dijit.OverviewMap");
dojo.require("esri.dijit.Measurement");
dojo.require("esri.dijit.Bookmarks");
dojo.require("esri.symbol");
dojo.require("esri.toolbars.draw");
dojo.require("esri.toolbars.edit");
dojo.require("esri.tasks.closestfacility");
dojo.require("esri.tasks.geometry");
dojo.require("esri.tasks.identify");
dojo.require("esri.tasks.query");
dojo.require("esri.tasks.route");
dojo.require("esri.tasks.gp");
dojo.require("esri.tasks.find");
dojo.require("esri.tasks.servicearea");
dojo.require("esri.layers.FeatureLayer");
dojo.require("esri.layers.graphics");
dojo.require("esri.layers.agsdynamic");
dojo.require("esri.layers.graphics");
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.layout.TabContainer");
dojo.require("dijit.TitlePane");

//引入代理
esriConfig.defaults.io.proxyUrl="proxy.jsp";
//esriConfig.defaults.io.alwaysUseProxy=true;

var itmap = itmap || {};

var myMap = null;
var map = null;

// 创建map
itmap.arcgis = {
	mapdiv:"map",
	tileMSUrl:"",
	map: null,
	// 创建GIS地图操作:
	init : function(){
		if(itmap.arcgis.titleMSUrl == "") {
			alert("请设置切片地图服务地址！");
			return;
		}
		// 获取插入的图层
		var myTiledMapServiceLayer = new esri.layers.ArcGISTiledMapServiceLayer(itmap.arcgis.tileMSUrl);
		myTiledMapServiceLayer.id="myTiledMapServiceLayer";
		
	
		myMap = map = itmap.arcgis.map = new esri.Map(itmap.arcgis.mapdiv,{
			logo : false,
			sliderStyle: "large",
			autoResize : true,
			displayGraphicsOnPan : false
		});
	
	
		map.addLayer(myTiledMapServiceLayer);
		var brigade = new esri.layers.FeatureLayer(baseServiceURL.Brigade.url);
		var locus = new esri.layers.FeatureLayer(baseServiceURL.Locus.url);
		brigade.id = "brigade";
		locus.id = "locus";
		map.addLayer(brigade);
		map.addLayer(locus);
		brigade.hide();
		locus.hide();

		dojo.connect(map, "onLoad", function(theMap) {
			/**
			 * 设置比例尺
			 */
			var scalebar = new esri.dijit.Scalebar({
				map : map,
				// 比例尺位置
				attachTo :"bottom-left",			 
				// 比例尺单位
				scalebarUnit : "dual"
			},dojo.byId("mapScaleBarBox"));
			
			/**
			 * 设置导航按钮
			 */
			new itmap.arcgis.navigation({
				mapC : map,
				initExtent : itmap.arcgis.getInitExtent(),
				container : "mapNavigationBox"
			});
			
			new itmap.arcgis.viewSwitcher({
				mapC : map
			});
			
			// 隐藏最大化按钮
			map.infoWindow.show(new esri.geometry.Point(0,0,myMap.spatialReference));
			$(".esriPopup .maximize").hide();
			map.infoWindow.hide();
			
			// 设置地图初始化范围
			map.setExtent(itmap.arcgis.getInitExtent());
		});
		
		//dojo.connect(map,"onDragEnd",function(){console.log(map.extent);});

		/*
		//添加鹰眼
		dojo.connect(esriMap.map, "onLoad", function(theMap) {
			var overviewMapDijit = new esri.dijit.OverviewMap({
			        map: esriMap.map,
			        visible: false,
			        attachTo :"bottom-right",	
			        maximizeButton : true, 
			        color:"#D84E13",
				    acity:.40
			});
			overviewMapDijit.startup();
		});*/
		
		// 获取底图的lod信息
		/*dojo.connect(myTiledMapServiceLayer,"onLoad",function(){
			itmap.arcgis.lods = myTiledMapServiceLayer.tileInfo.lods;
		});*/
	},
	// 地图初始化extent
	getInitExtent : function(){return new esri.geometry.Extent(108.863332,34.185988,109.021804,34.327648,new esri.SpatialReference(4326))}
};

/**
 * 自动加载相关js
 */
(function(){

	/**
	 * 当前加载引导程序文件名
	 * 为之后的文件加载提供参考支持
	 */
	var fileName = "arcgis.js";
	
	/**
	 * 系统JS文件路径
	 */
	var jsFiles = [
	    "arcgis/util.js",
	    "arcgis/symbol.js",
	    "arcgis/query.js",
	    "arcgis/renderer.js",
	    "arcgis/buffer.js",
	    "arcgis/draw.js",
	    "arcgis/add-point.js",
	    "arcgis/closest-facility.js",
	    "arcgis/map-graphic-manager.js",
	    "arcgis/map-infowindow.js",
	    "arcgis/map-snapping-tool.js",
	    "arcgis/route.js",
	    "arcgis/edit.js",
	    "arcgis/play.js",
	    "arcgis/map-toc.js",
	    "arcgis/map-navigation.js",
	    "arcgis/map-graph.js",
	    "arcgis/viewswitcher.js"
	];

	/**
	 * 获取JS文件的存储位置的根目录
	 */
	function _getScriptLocationHost(){
		var scripts = document.getElementsByTagName("script");
		var len = scripts.length;
		var src = "", h = "";
		var reg = new RegExp("(^|.*?\\/)("+fileName+")(\\?|$)");
		for(var i = 0; i < len; i++){
			src = scripts[i].getAttribute("src");
			if(src){
				h = src.match(reg);
				if(h){
					break;
				}
			}
		}
		
		return h[1];
	}

	/**
	 * 读取数组中的JS文件，并配为script tag形式插入到html中
	 */
	var host = _getScriptLocationHost();
	var scriptBox = new Array(jsFiles.length);
	for(var i = 0 , len = jsFiles.length; i< len; i++){
		scriptBox[i] = "<script type='text/javascript' src='"+host+jsFiles[i]+"'></script>"
	}

	if(scriptBox.length){
		document.write(scriptBox.join(""));
	}
})();
