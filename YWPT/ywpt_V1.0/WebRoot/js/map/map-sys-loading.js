/**
 * 系统JS加载程序
 * @author ZLT
 * @date 2013-10-15
 */
(function(){

	/**
	 * 当前加载引导程序文件名
	 * 为之后的文件加载提供参考支持
	 */
	var fileName = "map-sys-loading.js";

	/**
	 * 系统JS文件路径
	 */
	var jsFiles = [
	    "basemaptool/map-bookmark-tool.js",
	    "basemaptool/map-measurement-tool.js",
	    "mapquery/mapdevicequery.js",
	    "mapquery/mapchart.js",
	    "mapquery/map-visualscope-query.js",
	    "flowPublish/flowPublish.js",
	    "flowPublish/hotMap.js",
	    "parkinglot/park.js",
	    "parkinglot/trafficGuidingPanel.js",
	    "Cross/mapCross.js",
	    "Gps/crossBorderManager.js",
	    "Gps/mapGPS.js",
	    "flowPublish/trafficGuidingScreen.js",
	    "preplan/map-preplan-list.js",
	    "preplan/map-preplan-add.js"
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