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
         document.getElementById("DialogBoxTitle_leftController").style.display = "none";
         document.getElementById("DialogOuterBox_leftController").style.top = "20px";
         document.getElementById("DialogOuterBox_leftController").style.width = "350px";
         document.getElementById("DialogOuterBox_leftController").style.borderWidth = "0px";
         document.getElementById("DialogOuterBox_leftController").style.backgroundColor = "#0A3A5C";
	}
	
	
	function show(){
		var wrArea=0,totalArea=0,repairArea=0,wrRates=0;
		
		$.ajax({
			url: path+"/map/getAreaCount/",
			type: "GET",
			success: function(result) {
				wrArea=result.wrArea;
				totalArea=result.totalArea;
				repairArea=result.repairArea;
				wrRates=result.wrRates;
				
				if(!_dialog){
					_initContainer();
					
					var content="<div style='font-weight:blod;font-size:16px;color:#9ABC32;'>污染面积:<span style='font-size:18px;color:#CB6FD7;'>"+wrArea+"</span></div>" +
					        "<div  style='font-weight:blod;font-size:16px;color:#9ABC32;'>总面积:<span style='font-size:18px;color:#CB6FD7;'>"+totalArea+"<span></div>"+
							"<div  style='font-weight:blod;font-size:16px;color:#9ABC32;'>修复面积:<span style='font-size:18px;color:#CB6FD7;'>"+repairArea+"</span></div>"+
							"<div  style='font-weight:blod;font-size:16px;color:#9ABC32;'>污染率:<span style='font-size:18px;color:#CB6FD7;'>"+wrRates+"%</span></div>";
					var div=document.createElement("div");
					
					div.style.margin="8px";
					div.innerHTML=content;
					var _containerContent = document.getElementById(_containerContentId);
					_containerContent.appendChild(div);
				}
				_dialog.show();
			}
		});
		
	}
	
	function initUnitContainer(){
		
	}
	return api;
});