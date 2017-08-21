/**
 * 总加载类
 */
var itmap = {
	packages : [
		"util",
		"arcgis",
		"openlayer",
		"pgis"
	],
	createPkg : function(pkgs) {
		var levels = pkgs.split(".");
		var obj = itmap;
		for(var i=1;i <levels.length; i++) {
			if(typeof obj[levels[i]] == "undefined") {
				obj[levels[i]] = new Object();
			}
			obj = obj[levels[i]];
		}
	}
};

/**
 * 自动加载相关js
 */
(function(){
	/**
	 * 当前加载引导程序文件名
	 * 为之后的文件加载提供参考支持
	 */
	var fileName = "itmap.js";
	
	/**
	 * 初始化包
	 */
	for(var i=0,len=itmap.packages.length;i<len;i++){
		itmap[itmap.packages[i]] = new Object();
	}
	
	/**
	 * 系统JS文件路径
	 */
	var jsFiles = [
	    "util/date-format.js",
	    "util/map-dialog.js",
	    "util/map-dragable-tool.js",
	    "util/map-dropmenu.js",
	    "util/map-result-box-new.js",
	    "util/map-rightclick-tool.js",
	    "util/map-tab-tool.js",
	    "util/map-tip-box.js",
	    "util/map-toggle-box.js",
	    "util/map-variable-type.js",
	    "util/map-chart.js"
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