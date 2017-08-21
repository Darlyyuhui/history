<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<div style="width:480px;margin:0px;padding:0px;border:0px;">
  <table class="commonResultTable" style="width:245px;float:left;">
    <tr class="commonResultTableTitleRow">
      <td style="text-align:left;" colspan="2" style="font-weight:bold;">卡口信息：</td>
    </tr>
    <tr class="commonResultTableEvenRow">
      <td style="text-align:left;" width="75">设备名称：</td>
      <td style="text-align:left;" style="width:160px;"><span id='devicename'>${deviceInfo.name }</span></td>
    </tr>
    <tr class="commonResultTableOddRow">
      <td style="text-align:left;">设备编码：</td>
      <td style="text-align:left;">${deviceInfo.code }</td>
    </tr>
    <tr class="commonResultTableEvenRow">
      <td style="text-align:left;">设备地点：</td>
      <td style="text-align:left;"><span id='deviceaddress'>${deviceInfo.roadName }</span></td>
    </tr>
    <tr class="commonResultTableOddRow">
      <td style="text-align:left;">建设厂家：</td>
      <td style="text-align:left;">
      	<span id='crosscomname'>${deviceInfo.companyId }</span>
	  </td>
    </tr>
    <tr class="commonResultTableEvenRow">
      <td style="text-align:left;">建设时间：</td>
      <td style="text-align:left;">
      	<span id='builddate'>${deviceInfo.buildtimeStr }</span>
	  </td>
    </tr>
    <tr class="commonResultTableOddRow">
      <td style="text-align:left;">管理部门：</td>
      <td style="text-align:left;"><span id='managerdept'>${deviceInfo.orgNames}</span></td>
    </tr>
    <tr class="commonResultTableEvenRow">
      <td style="text-align:left;">设备功能：</td>
      <td style="text-align:left;" width="120"><span id="crosstype_info" style="position: relative; display: block"></span></td>
    </tr>
    <tr class="commonResultTableOddRow">
      <td style="text-align:left;">IP地址：</td>
      <td style="text-align:left;"><span id='netaddress'>${deviceInfo.ip}</span></td>
    </tr>
    <tr class="commonResultTableEvenRow">
      <td style="text-align:left;">超时设置：</td>
      <td style="text-align:left;">${deviceInfo.timeout }分钟</td>
    </tr>
  </table>
  <table class="commonResultTable" style="width:220px;float:left;margin-left: 5px;">
    <tr class="commonResultTableTitleRow">
      <td style="text-align:left;" colspan="2" style="font-weight:bold;">实时数据：</td>
    </tr>
    <tr class="commonResultTableEvenRow">
      <td style="text-align:left;" width="75">号牌号码：</td>
      <td style="text-align:left;"><span id='curcrosscarnum'>暂无数据</span></td>
    </tr>
    <tr class="commonResultTableOddRow">
      <td style="text-align:left;">通行时间：</td>
      <td style="text-align:left;"><span id='curcrosspasstime'>暂无数据</span></td>
    </tr>
    <tr class="commonResultTableEvenRow">
      <td style="text-align:left;" rowspan="8" colspan="2"><img id='curcrossimg' src='images/defaults.jpg' 
        onclick='window.parent.tipsdownImgFd("图片特写", this.src);' style='text-align:center; width: 220px;height:200px;'/>
      </td>
    </tr>
  </table>
  </div>
<script type="text/javascript">
	var df;
	MapFactory.Require([
		"ItmsMap/Util/DateFormat*"
	],function(_df){
		df = _df;
	});
	
	function mqcallback(message) {
		var str;
		if (message.text == undefined) {
			str = message.textContent;
		} else {
			str = message.text;
		}
		var json = $.parseJSON(str);
		$("#curcrossimg").attr("src",json.img);
		$("#curcrosscarnum").text(json.carnum);
		if(df)$("#curcrosspasstime").text(df.dateFormartFull(Number(json.time)));
	}
	
	// 获取建设厂家
	$(function () {
		$.ajax({
		  type: "POST",
		  dataType: "json",
		  url: "device/devicecompany/getbyid/${deviceInfo.companyId }",
		  success: function(obj){
		  	if(obj)$("#crosscomname").html(obj.name);
		  },
		  error: function(e) {
		  }
		 });
	});
	// 获取设备功能
	$(function () {
		$.ajax({
		  type: "POST",
		  dataType: "json",
		  url: "device/devicetypeinfo/gettype/${deviceInfo.id }",
		  success: function(obj){
		  	if(!obj)return;
		  	var detailsContainer = $("#crosstype_info"),
		  		detailsPrompt = '<a class="btn btn-info" id="showDetails" style="height:14px;line-height:14px;font-size:12px;">详细内容</a>',
		  		str = "",
		  		detailsDiv = '<div id="detailsDiv_cross" style="display:none; position:absolute; left:0px; bottom:25px;width:200px;padding:5px;background:#fff; z-index: 8888; border: 1px solid #ccc;"></div>';
		  	
		  	for(var i in obj) {
		  		str += obj[i].name+",";
		  	}

		  	detailsContainer.html(detailsPrompt + detailsDiv);
		  	$("#detailsDiv_cross").html(str);
		  	detailsContainer.mouseenter(function() {
		  		$("#detailsDiv_cross").show();
		  	}).mouseleave(function() {
		  		$("#detailsDiv_cross").hide();
		  	});
		  	//detailsPrompt = '<a id="showDetails" style="height:14px;line-height:14px;font-size:12px;" rel="popover" data-content="' + str + '"' + '>详细内容</a>';
		  	
		  	//detailsContainer.html(detailsPrompt);

		  	//$("#detailsDiv_cross").html(str);
		  	
		  }
		 });
	});
</script>
<!--
<link rel="stylesheet" type="text/css" src="compnents/bootstrap/css/bootstrap.css">
<script type="text/javascript" src="compnents/bootstrap/js/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="compnents/bootstrap/js/bootstrap-popover.js"></script>
-->

