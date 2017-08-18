/**
 * 图例工具
 * @author ZLT
 * @since 2014-6-17
 */
MapFactory.Define("ItmsMap/Compents/SimpleLegend*",[
    "ItmsMap/SymbolConfig*",
    "MapFactory/Util/Dialog*",
    "ItmsMap/ModuleConfig*"
],function(SymbolConfig,Dialog,ModuleConfig){

	var api = {
			setConf:setConf,
			show: show
	   };

	var _containerId = "legendContainer",
		_containerHeadId = "legendContainerHead",
		_containerContentId = "legendContaienrContent";

	var _dialog = null;

	/**
	 * 当前可见的模块图例索引
	 */
	var _currentIndex = -1;

    var _conf = {
        mapDiv: "",
        left: 8,
        bottom: 10
    };

    function setConf(conf){
        MapFactory.Extend(_conf,conf);
    }

	function _initContainer(){
		_dialog = Dialog({
            mapDiv: _conf.mapDiv,
			title: "图例",
            isMove : true,
			mutiDialog: true,
			mutiDialogSeed: "mapLegend",
			mask: false,
            bottom: _conf.bottom,
			left: _conf.left,
			content: "<div id='"+_containerId+"'>" +
						"<div id='"+_containerHeadId+"'></div>" +
						"<div id='"+_containerContentId+"'></div>" +
					 "</div>",
			buttonDisplay: {
				"confirmButton": false,
				"cancelButton": false
			}
		}).show();
         document.getElementById("DialogBoxTitle_mapLegend").style.display = "none";
         document.getElementById("DialogOuterBox_mapLegend").style.width = "289px";
         document.getElementById("DialogOuterBox_mapLegend").style.borderWidth = "0px";
         document.getElementById("DialogOuterBox_mapLegend").style.backgroundColor = "rgba(0,0,0,0)";
	}
	
	
	function show(){
		if(!_dialog){
			_initContainer();
			
			var headDiv = document.createElement("div");
			headDiv.style.height="25px";
			headDiv.style.backgroundColor="rgba(0,0,0,0.2)";
			var _containerHead = document.getElementById(_containerHeadId);
			_containerHead.appendChild(headDiv);
			
			var titlespan=document.createElement("span");
			titlespan.className="legendControllerWold";
			titlespan.innerText="图  例";
			headDiv.appendChild(titlespan);
			
			var closespan=document.createElement("span");
			closespan.id="legendCloseBtnId";
			closespan.className="legendControllerButton legendToControllerButton";
			headDiv.appendChild(closespan);
			var isClose = false;
			$(closespan).click(function(){
				if(isClose){
					$("#"+_containerContentId).slideDown();
					$("#legendCloseBtnId").removeClass("legendFromControllerButton").addClass("legendToControllerButton");
				}else{
					$("#"+_containerContentId).slideUp();
					$("#legendCloseBtnId").removeClass("legendToControllerButton").addClass("legendFromControllerButton");
				}
				isClose=!isClose;
            });
			//添加内容
			var imgEle = document.createElement("img");
			imgEle.setAttribute("src",path+"/images/renderIcon/legend.png");
			var _containerContent = document.getElementById(_containerContentId);
			_containerContent.appendChild(imgEle);
		}
		_dialog.show();
	}
	return api;
});