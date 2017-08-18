/**
 * 图例工具
 * @author ZLT
 * @since 2014-6-17
 */
MapFactory.Define("ItmsMap/Compents/LeftController*",[
    "ItmsMap/SymbolConfig*",
    "MapFactory/Util/Dialog*",
    "ItmsMap/ModuleConfig*"
],function(SymbolConfig,Dialog,ModuleConfig){

	var api = {
			setConf:setConf,
			show: show
	   };

	var _containerId = "leftControllerContainer",
		_containerHeadId = "leftControllerContainerHead",
		_containerContentId = "leftControllerContaienrContent";

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
			mutiDialogSeed: "leftController",
			mask: false,
            top: _conf.top,
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
         document.getElementById("DialogBoxTitle_leftController").style.display = "none";
         document.getElementById("DialogOuterBox_leftController").style.top = "20px";
         document.getElementById("DialogOuterBox_leftController").style.width = "240px";
         document.getElementById("DialogOuterBox_leftController").style.borderWidth = "0px";
         document.getElementById("DialogOuterBox_leftController").style.backgroundColor = "#0A3A5C";
         
         
         //document.getElementById("DialogOuterBox_mapLegend").style.width = "296px";
	}
	
	
	function show(){
		if(!_dialog){
			_initContainer();
			
			var content="<div style='font-weight:blod;font-size:16px;color:#9ABC32;'>全市污染普查面积:<span style='font-size:18px;color:#CB6FD7;'>118649亩</span></div>" +
			"<div  style='font-weight:blod;font-size:16px;color:#9ABC32;'>修复面积:<span style='font-size:18px;color:#CB6FD7;'>2040亩<span></div>"+
					"<div  style='font-weight:blod;font-size:16px;color:#9ABC32;'>污染率:<span style='font-size:18px;color:#CB6FD7;'>86%</span></div>";
			var div=document.createElement("div");
			div.style.margin="8px";
			
			div.innerHTML=content;
			
			var _containerContent = document.getElementById(_containerContentId);
			_containerContent.appendChild(div);
		}
		
		_dialog.show();
	}
	
	
	return api;
});