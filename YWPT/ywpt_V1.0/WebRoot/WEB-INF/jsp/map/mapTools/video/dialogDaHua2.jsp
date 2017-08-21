<%@ page language="java" pageEncoding="UTF-8"%>

<div id="videoplayerjs" style="width:500px;height:300px;">
	<object id="DPSDK_OCX_MAIN" classid="CLSID:D3E383B6-765D-448D-9476-DFD8B499926D" style="width:500px; height:300px; margin:0; padding:0;"></object>
</div>
<input id="dahuaCurrentCameraID" type="hidden" value="">
<div id="yuntaiDiv" class="mapyuntai" style="border:0;">
  <table width="99%" style="margin:0 auto;">
    <tr>
      <td width='114'>
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
		      <td onclick="ButtonPtzDirection_onclick(5)"><img src="images/map/l-up.png" title="左上方向"><img src="images/map/l-up2.png" class="optfront"></td>
		      <td onclick="ButtonPtzDirection_onclick(1)"><img src="images/map/up.png" title="向上"><img src="images/map/up2.png" class="optfront"></td>
		      <td onclick="ButtonPtzDirection_onclick(7)"><img src="images/map/r-up.png" title="右上方向"><img src="images/map/r-up2.png" class="optfront"></td>
		    </tr>
		    <tr>
		      <td onclick="return ButtonPtzDirection_onclick(3)"><img src="images/map/left.png" title="向左"><img src="images/map/left2.png" class="optfront"></td>
		      <td onclick="return ButtonPtzStop_onclick()"><img src="images/map/c-stop.png" title="停止"><img src="images/map/c-stop2.png" class="optfront"></td>
		      <td onclick="return ButtonPtzDirection_onclick(4)"><img src="images/map/right.png" title="向右"><img src="images/map/right2.png" class="optfront"></td>
		    </tr>
		    <tr>
		      <td onclick="return ButtonPtzDirection_onclick(6)"><img src="images/map/l-down.png" title="左下方向"><img src="images/map/l-down2.png" class="optfront"></td>
		      <td onclick="return ButtonPtzDirection_onclick(2)"><img src="images/map/down.png" title="向下"><img src="images/map/down2.png" class="optfront"></td>
		      <td onclick="return ButtonPtzDirection_onclick(8)"><img src="images/map/r-down.png" title="右下方向"><img src="images/map/r-down2.png" class="optfront"></td>
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
		var obj = document.getElementById("DPSDK_OCX_MAIN");
		var szCameraId = $("#dahuaCurrentCameraID").val();
		ShowCallRetInfo(obj.DPSDK_PtzDirection(szCameraId, _nDirect, $("#selectPtzDirectionStep").val(), 1), "方向控制");
	}
	// 方向
	function ButtonPtzDirection_onclick(nDirect) {
		_nDirect = nDirect;
		var obj = document.getElementById("DPSDK_OCX_MAIN");
		var szCameraId = $("#dahuaCurrentCameraID").val();
		ShowCallRetInfo(obj.DPSDK_PtzDirection(szCameraId, _nDirect, $("#selectPtzDirectionStep").val(), 0), "方向控制");
		ShowCallRetInfo(obj.DPSDK_PtzDirection(szCameraId, _nDirect, $("#selectPtzDirectionStep").val(), 1), "方向控制");
	}
	// 镜头
	function ButtonPtzCameraOperation_onclick(nOper) {
		var szCameraId = $("#dahuaCurrentCameraID").val();
		var obj = document.getElementById("DPSDK_OCX_MAIN");
		ShowCallRetInfo(obj.DPSDK_PtzCameraOperation(szCameraId, nOper, $("#selectCameraStep").val(), 0), "镜头控制");
		ShowCallRetInfo(obj.DPSDK_PtzCameraOperation(szCameraId, nOper, $("#selectCameraStep").val(), 1), "镜头控制");
	}
	function ShowCallRetInfo(nRet, strInfo) {
	    if(nRet != 0){
	        var obj = document.getElementById("DPSDK_OCX_MAIN");
	         try{
	         	alert(strInfo + ": ErrorCode = "+obj.DPSDK_GetLastError());
	         }catch(e){}
	    }
	}
</script>
