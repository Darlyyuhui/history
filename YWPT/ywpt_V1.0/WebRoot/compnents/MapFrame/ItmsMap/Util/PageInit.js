MapFactory.Define("ItmsMap/Util/PageInit*",[
    "ItmsMap/Util/ToggleBox*",
    "ItmsMap/Util/Tab*",
    "MapFactory/MapManager"
],function(togglebox,Tab,mapManager){

	/**
	 * 初始化选项卡
	 */
	Tab().init("menuList","mapTabContent",0,"click","menuListTab");

	$(".mapToolMenuBox:eq(0)").css("borderTopWidth","1px");

	/**
	 * 隐藏地图读取进度条
	 */
	$("#mapWaiting").hide();

	/**
	 * 左侧菜单自适应
	 */
	$(".mapToolParentMenuTitle").click(function(){
		setTimeout(resizeParent,1000);
	});

	$(".mapToolSubMenuTitle").click(function(){
		setTimeout(resizeParent,1000);
	});

	function resizeParent(){
		var leftHeight = document.getElementById("mapLeft").clientHeight;
		if(leftHeight < 720){
			leftHeight = 720;
		}
		window.parent.document.getElementById("content-frame").style.height = leftHeight + 5 + "px";
	}

	/**
	 * 计算地图容器大小
	 */
	function resizeMapContainer(){
		$("#mapContainer").width(document.body.clientWidth-$("#mapCenter")[0].clientWidth-$("#mapLeft")[0].clientWidth-10);
	}
	resizeMapContainer();
	window.onresize = resizeMapContainer;

	/**
	 * 左侧菜单缩进
	 */
	$("#mapCenter").toggle(function(){
		$("#mapLeft").hide();
		$("#optionImg").attr("src","images/picone/arr_right.png");
		if($.browser.msie){
			$("#mapContainer").width($("#mapContainer").outerWidth()+240);
		}else{
			$("#mapContainer").width($("#mapContainer").width()+242);
		}
		$("#map_root").width($("#map_root").width()+242);
		mapManager().resize();
	},function(){
		$("#mapLeft").show();
		$("#optionImg").attr("src","images/picone/arr_left.png");
		if($.browser.msie){
			$("#mapContainer").width($("#mapContainer").outerWidth()-240);
		}else{
			$("#mapContainer").width($("#mapContainer").width()-242);
		}
		$("#map_root").width($("#map_root").width()-242);
		mapManager().resize();
	});

});