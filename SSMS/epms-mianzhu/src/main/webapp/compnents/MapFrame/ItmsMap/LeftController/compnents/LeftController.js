/**
 * 模块工厂
 * @author ZLT
 * @since 2014-6-17
 */
MapFactory.Define("ItmsMap/LeftController/compnents/LeftController*",[
	"ItmsMap/SymbolConfig*",
	"MapFactory/Util/Dialog*",
	"ItmsMap/ModuleConfig*",
	"ItmsMap/LeftController/CompnentsConfig*"
	],function(SymbolConfig,Dialog,ModuleConfig,CompnentsConfig){

	var api = {
			setConf:setConf,
			show: show
	};

	var _containerId = "leftControllerContainer",
	_containerHeadId = "leftControllerContainerHead",
	_containerContentId = "leftControllerContaienrContent";

	_childrenContainerClass="compentContainer";
	_childrenTitleClass="compentTitleContainer";
	_childrenContentClass="compentContentContainer";

	_childrenContainerId="compentContainerId";
	_childrenTitleId="compentTitleContainerId";
	_childrenContentId="compentContentContainerId";
	var _dom = MapFactory.Dom;
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
			isMove : false,
			mutiDialog: true,
			mutiDialogSeed: "leftController",
			mask: false,
			left: _conf.left,
			bottom: _conf.bottom,
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
		//document.getElementById("DialogOuterBox_leftController").style.top = "20px";
		document.getElementById("DialogOuterBox_leftController").style.width = "400px";
		document.getElementById("DialogOuterBox_leftController").style.borderWidth = "0px";
		document.getElementById("DialogOuterBox_leftController").style.backgroundColor = "#FFFFFF";
	}


	function show(){
		if(!_dialog){
			_initContainer();
			initHeadInfo();
			initUnitContainer();
			loadDataChart();
			loadAreaCount();
			
			loadRepaireProjects();
		}
		_dialog.show();
	}

	function initUnitContainer(){
		var index=0;
		var _compentContent = document.getElementById(_containerContentId);

		for (var cc in CompnentsConfig){
			var con=CompnentsConfig[cc];
			var container=_dom.createElem("div");
			_dom.attr(container,"id",_childrenContainerId+index);
			_dom.addClass(container,_childrenContainerClass);


			var titleContainer=_dom.createElem("div");
			_dom.attr(titleContainer,"id",_childrenTitleId+index);
			_dom.addClass(titleContainer,_childrenTitleClass);
			$(titleContainer).css({height:"22px"});
			var titleSpan= _dom.createElem("b");
			_dom.addClass(titleSpan,"compentTitleLabel");
			_dom.html(titleSpan, con["title"]);
			_dom.append(titleContainer,titleSpan);
			
			var colContainer=_dom.createElem("div");
			_dom.addClass(colContainer,"legendControllerButton legendToControllerButton");
			_dom.append(titleContainer,colContainer);
			$(colContainer).click(function(){
				debugger;
				var contentCon= $(this).parent().siblings();
				if($(this).hasClass("legendFromControllerButton")){
					$(contentCon).slideDown();
					$(this).removeClass("legendFromControllerButton").addClass("legendToControllerButton");
				}else{
					$(contentCon).slideUp();
					$(this).removeClass("legendToControllerButton").addClass("legendFromControllerButton");
				}
			});

			var contentContainer=_dom.createElem("div");
			_dom.attr(contentContainer,"id",_childrenContentId+index);
			_dom.addClass(contentContainer,_childrenContentClass);
			$(contentContainer).css({height:con.contentHeight});

			_dom.append(container,titleContainer);
			_dom.append(container,contentContainer );
			_dom.append(_compentContent,container );
			++index;
		}
	}

	function  initHeadInfo(){
		var headDiv = document.createElement("div");
		headDiv.style.height="25px";
		headDiv.style.backgroundColor="#0A3A5C";
		var _containerHead = document.getElementById(_containerHeadId);
		_containerHead.appendChild(headDiv);

		var titlespan=document.createElement("span");
		titlespan.className="compentTitleHead";
		titlespan.innerText="全市总览信息";
		headDiv.appendChild(titlespan);

		var closespan=document.createElement("span");
		closespan.id="CompentCloseBtnId";
		closespan.className="legendControllerButton legendToControllerButton";
		headDiv.appendChild(closespan);
		var isClose = false;
		$(closespan).click(function(){
			if(isClose){
				$("#"+_containerContentId).slideDown();
				$("#CompentCloseBtnId").removeClass("legendFromControllerButton").addClass("legendToControllerButton");
			}else{
				$("#"+_containerContentId).slideUp();
				$("#CompentCloseBtnId").removeClass("legendToControllerButton").addClass("legendFromControllerButton");
			}
			isClose=!isClose;
		});
	}

	function loadDataChart(){
		$.ajax({
			url: path+"/map/getStaticList/",
			type: "POST",
			success: function(result) {
				var chart3 = echarts.init(document.getElementById(_childrenContentId+3), "shine");
				chart3.setOption(result.phOpt);

				var chart4 = echarts.init(document.getElementById(_childrenContentId+2), "shine");
				chart4.setOption(result.cdOpt);

				var chart5 = echarts.init(document.getElementById(_childrenContentId+1), "shine");
				chart5.setOption(result.acdOpt);
			}
		});
	}

	function loadAreaCount(){
		$.ajax({
			url: path+"/map/getAreaCount/",
			type: "POST",
			success: function(result) {
				wrArea=result.wrArea;
				totalArea=result.totalArea;
				repairArea=result.repairArea;
				wrRates=result.wrRates;
				createTable();
			}
		});
	}
	var wrArea=0,totalArea=0,repairArea=0,wrRates=0;
	
	
	function createTable(){
		var table = document.createElement("Table");
		_dom.addClass(table,"staticTable");
		
		var tr1=document.createElement("tr");
		var tr2=document.createElement("tr");
		
		var td1=document.createElement("td");
		_dom.addClass(td1,"staticTableTd");
		td1.innerHTML="<span class='staticTableSpan1'>污染面积</span><span class='staticTableSpan2'>"+wrArea+"亩</span>";
		
		var td2=document.createElement("td");
		_dom.addClass(td2,"staticTableTd");
		td2.innerHTML="<span class='staticTableSpan1'>修复面积</span><span class='staticTableSpan2'>"+repairArea+"亩</span>";
		
		var td3=document.createElement("td");
		_dom.addClass(td3,"staticTableTd");
		td3.innerHTML="<span class='staticTableSpan1'>污染率</span><span class='staticTableSpan2'>"+wrRates+"%</span>";
		
		var td4=document.createElement("td");
		_dom.addClass(td4,"staticTableTd");
		td4.innerHTML="<a class='staticTableSpan1' onclick='zbxqClickHandle()' style='cursor:pointer'>指标详情</a>";

		_dom.append(tr1,td1);
		_dom.append(tr1,td2);
		_dom.append(tr2,td3);
		_dom.append(tr2,td4);
		_dom.append(table,tr1);
		_dom.append(table,tr2);
		
		var tableContent=document.getElementById(_childrenContentId+0)
		_dom.append(tableContent,table);
	}
	
	function loadRepaireProjects(){
		$.ajax({
			url: path+"/map/repaire/list/",
			type: "POST",
			success: function(msg) {
				 $("#"+_childrenContentId+4).html(msg);
			}
		});
	}

	return api;
});