<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/swfs/videoplayer/swfobject.js" type="text/javascript"></script>

<div id='dhloading' style="text-align:center;font-size:16px;vertical-align:middle;padding-top:120px;">
	视频正在加载，请稍后...
</div>
<div id="videoplayerjs" style="width:10px;height:0px;">
	<object id="DPSDK_OCX" classid="CLSID:D3E383B6-765D-448D-9476-DFD8B499926D" style="width:10px; height:0px; margin:0; padding:0;"></object>
</div>

<div id="yuntaiDiv" class="mapyuntai" style="display:none;border:0;">
  <table width="99%" style="margin:0 auto;">
    <tr>
      <td>
        <div class="yun_step">
	 	   	 云台操作步长：<br>
			<select id="selectPtzDirectionStep" style="width:96px;font-size:12px;">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
			</select>
	 	</div>
	 	<table id="videoDire" width="99" height="99" cellpadding="0" cellspacing="0">
		    <tr>
		      <td onclick="ButtonPtzDirection_onclick(5)"><img src="${root}/images/map/l-up.png" title="左上方向"><img class="optfront"></td>
		      <td onclick="ButtonPtzDirection_onclick(1)"><img src="${root}/images/map/up.png" title="向上"><img src="${root}/images/map/up2.png" class="optfront"></td>
		      <td onclick="ButtonPtzDirection_onclick(7)"><img src="${root}/images/map/r-up.png" title="右上方向"><img src="${root}/images/map/r-up2.png" class="optfront"></td>
		    </tr>
		    <tr>
		      <td onclick="return ButtonPtzDirection_onclick(3)"><img src="${root}/images/map/left.png" title="向左"><img src="${root}/images/map/left2.png" class="optfront"></td>
		      <td onclick="return ButtonPtzStop_onclick()"><img src="${root}/images/map/c-stop.png" title="停止"><img src="${root}/images/map/c-stop2.png" class="optfront"></td>
		      <td onclick="return ButtonPtzDirection_onclick(4)"><img src="${root}/images/map/right.png" title="向右"><img src="${root}/images/map/right2.png" class="optfront"></td>
		    </tr>
		    <tr>
		      <td onclick="return ButtonPtzDirection_onclick(6)"><img src="${root}/images/map/l-down.png" title="左下方向"><img src="${root}/images/map/l-down2.png" class="optfront"></td>
		      <td onclick="return ButtonPtzDirection_onclick(2)"><img src="${root}/images/map/down.png" title="向下"><img src="${root}/images/map/down2.png" class="optfront"></td>
		      <td onclick="return ButtonPtzDirection_onclick(8)"><img src="${root}/images/map/r-down.png" title="右下方向"><img src="${root}/images/map/r-down2.png" class="optfront"></td>
		    </tr>
		</table>
      </td>
      <td>
        <div class="yun_step">
	 		 镜头操作倍速：
			<select id="selectCameraStep" style="width:64px;">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
			</select>
	 	</div>
		<div class="mar_5" style="margin-top:0;">
			<table class="add_redu_table">
			    <tr>
			      <td width="24"><a onclick="javascript:return ButtonPtzCameraOperation_onclick(0);" class="add_op"></a></td>
			      <td><p class="center-p">变倍</p></td>
			      <td width="24"><a onclick="javascript:return ButtonPtzCameraOperation_onclick(3);" class="reduce_op"></a></td>
			    </tr>
			    <tr>
			      <td width="24"><a onclick="javascript:return ButtonPtzCameraOperation_onclick(1);" class="add_op"></a></td>
			      <td><p class="center-p">聚焦</p></td>
			      <td width="24"><a onclick="javascript:return ButtonPtzCameraOperation_onclick(4);" class="reduce_op"></a></td>
			    </tr>
			    <tr>
			      <td width="24"><a onclick="javascript:return ButtonPtzCameraOperation_onclick(2);" class="add_op"></a></td>
			      <td><p class="center-p">光圈</p></td>
			      <td width="24"><a onclick="javascript:return ButtonPtzCameraOperation_onclick(5);" class="reduce_op"></a></td>
			    </tr>
			</table>
		</div>
      </td>
    </tr>
  </table>
</div>
<script>
	$(function(){
		var direction = $("#videoDire tr td");
		direction.mousedown(function(){
			$(this).find(".optfront").show();
		}).mouseup(function(){
			$(this).find(".optfront").hide();
		});
	});
	var _nDirect;
	// 停止
	function ButtonPtzStop_onclick() {
		if(!nDirect)return;
		var obj = document.getElementById("DPSDK_OCX");
		ShowCallRetInfo(obj.DPSDK_PtzDirection(szCameraId, _nDirect, $("#selectPtzDirectionStep").val(), 1), "方向控制");
	}
	// 方向
	function ButtonPtzDirection_onclick(nDirect) {
		_nDirect = nDirect;
		var obj = document.getElementById("DPSDK_OCX");
		ShowCallRetInfo(obj.DPSDK_PtzDirection(szCameraId, _nDirect, $("#selectPtzDirectionStep").val(), 0), "方向控制");
		ShowCallRetInfo(obj.DPSDK_PtzDirection(szCameraId, _nDirect, $("#selectPtzDirectionStep").val(), 1), "方向控制");
	}
	// 镜头
	function ButtonPtzCameraOperation_onclick(nOper) {
		var obj = document.getElementById("DPSDK_OCX");
		ShowCallRetInfo(obj.DPSDK_PtzCameraOperation(szCameraId, nOper, $("#selectCameraStep").val(), 0), "镜头控制");
		ShowCallRetInfo(obj.DPSDK_PtzCameraOperation(szCameraId, nOper, $("#selectCameraStep").val(), 1), "镜头控制");
	}
</script>

<script type="text/javascript">

  var videoCode,videoWidth,videoHeight;
  var szIp = "";
  var nPort = "";
  var szUsername = "";
  var szPassword = "";
  
  var szCameraId = "";
  var nStreamType = "";
  var nMediaType = "";
  var nTransType = "";
  // 页面初始化加载
  $(document).ready(function(){
  	//border:#333 1px solid;width:500px;height:180px;
  	// div总大小500×480  object大小500×300
	szIp = '${param.szIp}';
	nPort = '${param.nPort}';
	szUsername = '${param.szUsername}';
	szPassword = '${param.szPassword}';
	szCameraId = '${param.cameraID}';
	nStreamType = '${param.streamType}';
	nMediaType = '${param.mediaType}';
	nTransType = '${param.transType}';
	
  	videoWidth = '${param.videoWidth}';
  	if(!videoWidth)videoWidth = 500;
  	else videoWidth = Number(videoWidth);
  	videoHeight = '${param.videoHeight}';
  	if(!videoHeight)videoHeight = 300;
  	else videoHeight = Number(videoHeight);
  	
  	//$("#dhloading").css({ width: videoWidth+"px", height: videoHeight+"px" });
  	
  	initvideoplay('${root}', videoCode, 2);
  });
  
  
  function initvideoplay(root, devicecode, command) {
  	  // 显示
  	  _initDH();
  	  _login();
  	  _loadData();
  	  _play();
  	  // 隐藏
  	  $("#dhloading").css("display", "none");
  	  $("#videoplayerjs").css({ width: videoWidth+"px", height: videoHeight+"px" });
  	  $("#DPSDK_OCX").css({ width: videoWidth+"px", height: videoHeight+"px" });
  	  $("#yuntaiDiv").css("display", "inline");
  }
  
var gWndId;
// 初始化
function _initDH() {
	var obj = document.getElementById("DPSDK_OCX");
	
    gWndId = obj.DPSDK_CreateSmartWnd(0, 0, 100, 100);
    obj.DPSDK_SetWndCount(gWndId, 1);//第二个参数为窗口数
	obj.DPSDK_SetSelWnd(gWndId, 0);
}
// 登录
function _login() {
	var obj = document.getElementById("DPSDK_OCX");
    ShowCallRetInfo(obj.DPSDK_Login(szIp, nPort, szUsername, szPassword), "登录");
}
// 加载数据
function _loadData() {
	var obj = document.getElementById("DPSDK_OCX");
	
	ShowCallRetInfo(obj.DPSDK_LoadDGroupInfo(), "加载组织结构");
}
// 播放视频
function _play() {
	var obj = document.getElementById("DPSDK_OCX");
	var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    ShowCallRetInfo(obj.DPSDK_StartRealplayByWndNo(gWndId, nWndNo, szCameraId, nStreamType, nMediaType, nTransType), "播放视频");
}
function ShowCallRetInfo(nRet, strInfo) {
    if(nRet != 0){
        var obj = document.getElementById("DPSDK_OCX");
         try{
         	alert(strInfo + ": ErrorCode = "+obj.DPSDK_GetLastError());
         }catch(e){}
    }
}
</script>

<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
