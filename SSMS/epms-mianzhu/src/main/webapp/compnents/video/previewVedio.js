/****************************变量定义**************************************/
var c_maxWindowNum = 25;
var isRegisted = 0;  //预览控件是否已经向cms注册
var m_iSelectWindowsId = 0; // 当前选择的窗口ID
var m_iCameraIsPlay = new Array(); // 监控点是否正在预览

var m_ipreviewType = 0;//当前窗口预览的信息类型 0-视频 1-过车图片

var m_iCameraId = 0; // 当前选择的监控点ID号
var m_iCameraName = "";
var m_iCameraControlUnitId = 0; //当前选择监控点所在的组织id
var m_iCameraRegionId = 0;//当前选择监控点所在的区域id

var m_iLaneInfoId = 0;//当前选择的laneId
var cameraInfoXML;
var m_iPTZSpeed = 4; // 云台速度
var isReOpen = false; //是否正在传递预览参数
var m_szVideoInfo = new Array(); // 视频参数保存到数组里面0亮度1饱和度2对比度3色调

var isCagRegisted = 0;//是否已经注册了cag

for (var i = 0; i < 4; i++) {
	m_szVideoInfo[i] = 1;
}
/*
 * 存放控件中一个预览窗口中的信息
 * controlUnitId  监控点所在控制中心id
 * cameraId       监控点id
 * cameraName     监控点名字
 * regionId       监控点所在区域id
 * isPTZAuto      是否正在云台自转
 * isLight        是否正在灯光
 * isWiper        是否正在雨刷
 * priPtz         云台控制的权限 “0”为没有权限 “1”为有权限
 * priRecord      本地录像的权限
 * priPtzConfig   云台配置的权限
 * priVideoConfig 参数配置的权限
 * --------------------------以下为智能交通扩展--
 * laneId         ....
 * previewType    预览类型  0视频/1图片 -1无
 * laneId
 * laneReqParam
 * node           对应树节点
 * playState      播放成功标志  0--失败（默认），1--成功
 * 
 */
var m_iWindowsInfo = new Array();
var m_presetList = new Array();
for (var j = 0; j < c_maxWindowNum; j++) {
    m_iWindowsInfo[j] =  initWindowObject();
    m_presetList[j] = initPresetObject();
}

function initPresetObject(){
	var obj = {
		currPage: 1,
		selPreset:1,
		presetList: []
	};
	return obj;
}

function resetAllWindow(){
	var playOcx = document.getElementById("RealTimePlayOcx");
	if(!playOcx || !playOcx.object){
		return;
	}
	playOcx.StopAllPreview();
}
function resetWindow(j){
	var playOcx = document.getElementById("RealTimePlayOcx");
	if(!playOcx || !playOcx.object){
		return;
	}
	//初始化树节点样式为非播放状态
	if(m_iWindowsInfo[j].previewType>-1){
	   // if($.isFunction(changeNodeSkin)){
		    if(typeof(m_iWindowsInfo[j].node)!= 'undefined'){
			   changeNodeSkin(m_iWindowsInfo[j].node);
			} 
	      //}
	}
	if(m_iWindowsInfo[j].previewType==0){//视频
		m_iWindowsInfo[j] =  initWindowObject();	
		m_presetList[j] = initPresetObject();
		//playWinIds.push(j);
		playOcx.StopRealPlay(j);
	}
 
	return m_iWindowsInfo[j];
}
function initWindowObject(){
    var obj = new Object();
    obj.cameraId   =  -1;    
    obj.cameraName = "";    
    obj.controlUnitId  = 0;
    obj.regionId  = 0;     
    obj.isPTZAuto  = 0;    
    obj.isLight = 0;     
    obj.isWiper = 0;  
    obj.priPtz = 0;
    obj.priRecord = 0;
    obj.priPtzConfig = 0;
    obj.priVideoConfig = 0;
    obj.previewType = -1;  
    obj.laneId = 0; 
    obj.laneName = "";
    obj.laneReqParam="";
    obj.node = null;
    obj.playState = 0;
    return obj;
}
//设置权限相关参数,并播放预览
function dealCamera(retStr,id,treeNode,winId){
	var xmlDoc = toXML(retStr);
	
	//返回
	if(checkError(xmlDoc)== false)
	{
		return;
	}
	
	var items = xmlDoc.getElementsByTagName("Parament");
	if (items.length < 1) {
		tmcp.warning("获取监控点信息失败，请稍后重试！");
		//cms.ext.msg(Ext.Msg.WARNING, PREVIEW.CAMERA_FAIL);
		return;
	}

	var priObj = new Object();

	var nodes = items[0].getElementsByTagName("CameraName");
	if (nodes.length < 1) {
		tmcp.warning("获取监控点信息不全，缺失CameraName！");
	}else{
		m_iCameraName = nodes[0].text;
	}
	nodes = items[0].getElementsByTagName("ControlUnitID");
	if (nodes.length < 1) {
		tmcp.warning("获取监控点信息不全，缺失ControlUnitID！");
	}else{
		m_iCameraControlUnitId = nodes[0].text;//当前选择监控点所在的组织id
	}
	nodes = items[0].getElementsByTagName("RegionID");
	if (nodes.length < 1) {
		tmcp.warning("获取监控点信息不全，缺失RegionID！");
	}else{
		m_iCameraRegionId = nodes[0].text;//当前选择监控点所在的区域id
	}
	
	var nodes = items[0].getElementsByTagName("PtzControl");
	if (nodes.length < 1) {
		tmcp.warning("获取监控点信息不全，缺失PtzControl！");
	}else{
		priObj.priPtz = nodes[0].text;
	}
	
	var nodes = items[0].getElementsByTagName("RecordVideo");
	if (nodes.length < 1) {
		tmcp.warning("获取监控点信息不全，缺失RecordVideo！");
	}else{
		priObj.priRecord = nodes[0].text;
	}
	
	var nodes = items[0].getElementsByTagName("ptzConfigPrivilege");
	if (nodes.length < 1) {
		tmcp.warning("获取监控点信息不全，缺失ptzConfigPrivilege！");
	}else{
		priObj.priPtzConfig = nodes[0].text;
	}
	
	var nodes = items[0].getElementsByTagName("videoConfigPrivilege");
	if (nodes.length < 1) {
		tmcp.warning("获取监控点信息不全，缺失videoConfigPrivilege！");
	}else{
		priObj.priVideoConfig = nodes[0].text;
	}
	
	m_iCameraId = parseInt(id);
	startPlayCameraX(parseInt(id), items[0], priObj,treeNode,winId);
}
//设置控件相关操作的权限【注：由于控件的实现的特殊性缘故，该接口必须在所有的预览之前调用】
function SetWindowsPtzPrivileges(priObj){
	try{//临时修正【控件由于是全局控制，待修正】
		var ptzPrivilegesXml = "<?xml version=\"1.0\"?>";
		//ptzPrivilegesXml += "<Parament>";
		ptzPrivilegesXml += "<PtzControl>" + (priObj.priPtz !== undefined ? priObj.priPtz : 1) + "</PtzControl>";
		ptzPrivilegesXml += "<SnapPicture>" + (priObj.priRecord !== undefined ? priObj.priRecord : 1) + "</SnapPicture>";
		ptzPrivilegesXml += "<RecordVideo>" + (priObj.priRecord !== undefined ? priObj.priRecord : 1) + "</RecordVideo>";
		ptzPrivilegesXml += "<ThreeDPosition>1</ThreeDPosition>";
		ptzPrivilegesXml += "<DigitalZoom>1</DigitalZoom>";
		//ptzPrivilegesXml += "</Parament>";

		var PlayOCXObj = document.getElementById("RealTimePlayOcx");
		PlayOCXObj.SetPreviewOperationRights(ptzPrivilegesXml);
	}catch(e){
		
	}
}
//开始预览
function startPlayCameraX(iCameraId,xmlEle,priObj,treeNode,winId){
	var playwnd = m_iSelectWindowsId;
	if(typeof(winId) != 'undefined'){
		playwnd = winId;
	}
	
	//var playwnd = (winId)?winId:m_iSelectWindowsId;
	var XmlDoc = new ActiveXObject("MSXML2.DOMDocument.3.0");
	var Instruction = XmlDoc.createProcessingInstruction("xml", "version='1.0' encoding='utf-8'");
	XmlDoc.appendChild(Instruction);
	XmlDoc.appendChild(xmlEle);
	//初始化树节点样式为非播放状态
	var obj = resetWindow(playwnd);
	
	obj.cameraId = m_iCameraId;
	obj.cameraName = m_iCameraName;
	obj.controlUnitId = m_iCameraControlUnitId;
	obj.regionId = m_iCameraRegionId;
	obj.priPtz = priObj.priPtz;
	obj.priRecord = priObj.priRecord;
	obj.priPtzConfig = priObj.priPtzConfig;
	obj.priVideoConfig = priObj.priVideoConfig;
	obj.previewType = 0;
	obj.node = treeNode;
	m_iWindowsInfo[playwnd] = obj;
	var PlayOCXObj = document.getElementById("RealTimePlayOcx");
	isReOpen = true;
	var bReturn = -1;
	SetWindowsPtzPrivileges(priObj);
	//alert(XmlDoc.xml);
	if(winId){
		bReturn = PlayOCXObj.StartTask_Preview_InWnd(XmlDoc.xml,winId);
	}else{
		bReturn = PlayOCXObj.StartTask_Preview(XmlDoc.xml);
	}
    isReOpen = false;
    if(bReturn<0){
    	tmcp.warning("预览失败，返回错误码为："+bReturn);
    	m_iCameraIsPlay[iCameraId] = 0;
		m_iWindowsInfo[playwnd] = initWindowObject();
    }
    else if("undefined"!=typeof(onePresetObj) && onePresetObj){
    	//getPresetByCameraId(m_iCameraId,playwnd);
    }
    PlayOCXObj.PtzSpeed = 4;
}


////获取视频预览参数
//function getTreeDataAjax(type, id, controlUnitId, regionId, cameraNode,winId){
//	//add by fangweihua on 2013-03-27
//	var url = "";
//	var cascade = cameraNode["extra"]["cascade"];
//	if(cascade != 1){
//		cascade = 0;
//	}
//	if (type == "camera") {
//		url = path + "/previewAction!fetchCameraInfoXMLForPreview.action?cameraId=" + encodeURI(id) + "&unitId="
//		        + encodeURI(controlUnitId) + "&regionId=" + encodeURI(regionId)+"&cascade="+encodeURI(cascade);
//	} else if(type == "lane"){
//		return;
//	}else {
//		return;
//	}
//	$.ajax({
//		url:url,
//		async:false,
//		type:"post",
//		success:function(data){
//			var str1 = data;
//			//alert("设备参数:"+str1);
//			var msg = "";
//			if (str1.indexOf("NO_AUTH") != -1) { // 没有权限
//				msg = "没有权限";
//			} else if (str1.indexOf("NO_DATA") != -1) { // 没有数据
//				msg = "没有数据";
//			} else if (str1.indexOf("REQEST_FAILED") != -1) { // 获取信息异常
//				msg = "获取信息异常";
//			} else if (str1.indexOf("NO_STREAMSVR_INFO") != -1) {
//				msg = "没有流媒体服务器";
//			} else if (str1.indexOf("NO_IP_INFO") != -1) {
//				msg = "流媒体服务器异常";
//			} else {
//				//regist(id,str1,cameraNode,winId);  //新控件支持流媒体4.0.去掉注册cms的接口，此方法已经废弃
//				if(id!=null && str1!=null){
//					dealCamera(str1, id,cameraNode,winId);
//				}
//			}
//		}
//	});
//}

function toXML(strxml) {
	try {
		if (window.ActiveXObject) {
			xmlDoc = new ActiveXObject("MSXML2.DOMDocument.3.0");
			xmlDoc.async = false;
			xmlDoc.loadXML(strxml);
		} else if (document.implementation && document.implementation.createDocument) {
			xmlDoc = document.implementation.createDocument('', '', null);
			xmlDoc.loadXML(strxml);
		} else
			xmlDoc = null;
	} catch (e) {
		var oParser = new DOMParser();
		xmlDoc = oParser.parseFromString(strxml, "text/xml");
	}
	return xmlDoc;
}

/***************************视频参数调节*****************************************/
//0亮度1饱和度2对比度3色调
function videoParamChangeFunc(type,value){
	if(value<1) {value = 1;}
	m_szVideoInfo[type] = value;
	SetWebVideo();
}
//设置视频参数
function SetWebVideo() {
	setVideoEffectInner();
	function setVideoEffectInner() {
		var PlayOCX = document.getElementById("RealTimePlayOcx");
		var iRut = PlayOCX.SetVideoEffect(m_szVideoInfo[0], m_szVideoInfo[1], m_szVideoInfo[2], m_szVideoInfo[3]);
	}
}
//视频参数恢复默认值
function RestoreDefaults() {
	if (m_iWindowsInfo[m_iSelectWindowsId].cameraId < 1) {
		return;
	}
	restorDefaulstsInner();
	// varifySetVideoEffectPrivilege(restorDefaulstsInner);
	function restorDefaulstsInner() {
		for (var i = 0; i < 4; i++) {
			m_szVideoInfo[i] = 6;
		}
		
		setSliderValue($("#v_brightness"),m_szVideoInfo[0]);
		setSliderValue($("#v_contrast"),m_szVideoInfo[1]);
		setSliderValue($("#v_saturation"),m_szVideoInfo[2]);
		setSliderValue($("#v_color"),m_szVideoInfo[3]);
		
		var PlayOCX = document.getElementById("RealTimePlayOcx");
		PlayOCX.SetVideoEffect(m_szVideoInfo[0], m_szVideoInfo[1], m_szVideoInfo[2], m_szVideoInfo[3]);
	}
}

/**
 * 判断是否有权限
 * @return
 */
function checkError()
{
	var items = xmlDoc.getElementsByTagName("responseInfo");
	if (items.length < 1) {
		
		return true;
	}
	
	var resultInfoItems = items[0].getElementsByTagName("resultInfo");
	if (resultInfoItems.length < 1) {
		return true;
	}
	
	var errorCodeNode = resultInfoItems[0].getElementsByTagName("errorCode");
	if (errorCodeNode.length < 1) {
		return true;
	}
	
	var errorCodeReasonNode = resultInfoItems[0].getElementsByTagName("errorReason");
	if (errorCodeNode.length < 1) {
		return true;
	}
	
	tmcp.warning(errorCodeReasonNode[0].text);
	return false;
}

/******************************预置点相关********************************************/
//调用预置点
function CallPreset(presetIndex, preName) {
	if (m_iWindowsInfo[m_iSelectWindowsId].cameraId < 1) {
		return;
	}
	// varifyPtzPrivilege(callPresetInner);
	callPresetInner();
	function callPresetInner() {
		var PlayOCX = document.getElementById("RealTimePlayOcx");
		var res = PlayOCX.PTZCtrlGotoPreset(presetIndex);
        if(0 == res){
         // document.all.item("ptzAuto").src = src + "/images/ptz/ptz_auto.png";
          m_iWindowsInfo[m_iSelectWindowsId].isPTZAuto = 0;
        }
	}
}

//预置点事件绑定
function onePresetClick(options){
	var max_perview_num = 128;
	var page_size = 8;
	var prefixId = 'pointer';
	var prefixText= '(空)';
	var page = {
		pageSize: page_size,
		totalPage:max_perview_num / page_size,
		currentPage: 1
	};
	retObj = {};
	retObj.showPage = $.noop;
	options = options || {};
	options.callBackCalPreview = options.callBackCalPreview || function(v){alert(v);};
	
	//绑定预置点上下调节按钮事件

	var presetDiv = $$("#preSetPoint");
	
	var bottomBar = presetDiv.find(".preset-bottom-bar");
	
	/**绑定input中的输入校验**/
	$$('#preset-input').bind('focusout',function(){
		var value = $(this).val();
		if(/\d+/.test(value)){
			if(value < 1){
				$(this).val(1);
			}else if(value > max_perview_num){
				$(this).val(max_perview_num);
			}
		}else{
			$(this).val(1);
		}
	});
}

function presetInitByWin(oldWinId){
	var curPage = m_presetList[m_iSelectWindowsId].currPage;
	var selPreset = m_presetList[m_iSelectWindowsId].selPreset;
	
	var value = $$('#pre-pointer').val();
	if(oldWinId>-1){
		m_presetList[oldWinId].selPreset = value;
	}
	$$('#pre-pointer').val((selPreset==0)?"":selPreset);
}

/**
 * ================开始轨迹记录34、停止轨迹记录35、开始轨迹回放36、停止轨迹回放===============
 *
 */
function locusDeal(cmdId){
	if (m_iWindowsInfo[m_iSelectWindowsId].cameraId < 1) {
		return;
	}
	getUserPriority();
	ptzCtrl(cmdId, userPriority);
}
