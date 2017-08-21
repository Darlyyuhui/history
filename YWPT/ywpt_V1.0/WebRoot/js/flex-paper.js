/***
 * 用于展示flexPaper阅读器
 * @param name
 */
function showPaper(name,root) {
	var swfVersionStr = "10.0.0";
	var xiSwfUrlStr = root+"/compnents/flexpaper/swf/playerProductInstall.swf";
	var flashvars = {
		SwfFile : escape(root+"/"+name),
		Scale : 0.6,
		ZoomTransition : "easeOut",
		ZoomTime : 0.5,
		ZoomInterval : 0.1,
		FitPageOnLoad : false,
		FitWidthOnLoad : true,
		PrintEnabled : true,
		FullScreenAsMaxWindow : false,
		ProgressiveLoading : true,
		PrintToolsVisible : true,
		ViewModeToolsVisible : true,
		ZoomToolsVisible : true,
		FullScreenVisible : true,
		NavToolsVisible : true,
		CursorToolsVisible : false,
		SearchToolsVisible : false,
		localeChain : "zh_CN"
	};

	var params = {};
	params.quality = "high";
	params.bgcolor = "#ffffff";
	params.allowscriptaccess = "sameDomain";
	params.allowfullscreen = "true";
	var attributes = {};
	attributes.id = "FlexPaperViewer";
	attributes.name = "FlexPaperViewer";
	swfobject.embedSWF(root+"/compnents/flexpaper/swf/flexpaper.swf","flashContent", "90%", "600", swfVersionStr, xiSwfUrlStr,flashvars, params, attributes);
	swfobject.createCSS("#flashContent", "display:block;text-align:center;");
}