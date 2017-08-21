<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<!-- <div class="control-group btn_line" style="text-align:center; margin-bottom:0;">
		<input id="cancel_btn" class="btn btn-info pull-left mar_l10" type="button" value="点击测试对话框" onclick="doTestDialog()" />
	</div> -->
<link id="MainCss" href="${root}/css/transport.css" type="text/css" rel="stylesheet">
<link id="DialogCss" href="${root}/css/newdialog.css" type="text/css" rel="stylesheet">
<script  type="text/javascript">

//切换皮肤方法
var d=window.parent.document.getElementById("skin-sel");
if(d!=null){
	var value= d.options[d.selectedIndex].value;
	if(value=="1"){
		$("#MainCss").attr("href","${root}/css/transport.css");
		$("#DialogCss").attr("href","${root}/css/newdialog.css");
	}else if(value=="2"){
		$("#MainCss").attr("href","${root}/cssGreen/transport.css");
		$("#DialogCss").attr("href","${root}/cssGreen/newdialog.css");
	}else if(value=="3"){
		$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
		$("#DialogCss").attr("href","${root}/cssDarkBlue/newdialog.css");
	}else{
	}
	//d.addEventListener("change",ssss,false);
	//监听主页皮肤切换
	$(d).bind("change", function(){
		var d1=window.parent.document.getElementById("skin-sel");
		var value1= d1.options[d1.selectedIndex].value;
		if(value1=="1"){
			$("#MainCss").attr("href","${root}/css/transport.css");
			$("#DialogCss").attr("href","${root}/css/newdialog.css");
		}else if(value1=="2"){
			$("#MainCss").attr("href","${root}/cssGreen/transport.css");
			$("#DialogCss").attr("href","${root}/cssGreen/newdialog.css");
		}else if(value1=="3"){
			$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
			$("#DialogCss").attr("href","${root}/cssDarkBlue/newdialog.css");
		}else{
		
		}
	});
}
</script>
<div style="min-height:640px;">
  <div class="conten_box">
    <h4 class="xtcs_h4" style="margin:0;">对话框测试页</h4>
	<div class="mar_5">
	  <table class="table tingche-table table-border-bot table-border-rl">
		<tr>
			<td class="device_td_bg2" valign="top">点击对话框测试：</td>
			<td>
				<div class="required">
				</div>
			</td>
		</tr>
	  </table>
	  <table class="table tingche-table table-border-bot table-border-rl">
		<tr>
			<td class="device_td_bg2" valign="top">新对话框测试：</td>
			<td>
				<div class="required">
					<tags:newdevdialog returnDeviceCode="deviceCode" rows="5" maxlength="200" style="width:40%; padding:2px;"/>
				</div>
			</td>
		</tr>
	  </table>
	</div>
  </div>
</div>
<script type="text/javascript">
<!--
	function doTestDialog(){
		window.location.href = "${root}/device/custom/list/${menuid}/";
	}
//-->
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
