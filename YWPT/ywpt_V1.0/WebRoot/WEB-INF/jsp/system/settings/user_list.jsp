<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${root }/compnents/My97DatePicker/WdatePicker.js"></script>

<link href="${root}/compnents/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="${root}/compnents/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${root}/compnents/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${root }/js/xiangxun_user.js"></script>

<div class="alert alert-block pull-top alert-error" id="alert-div" style="display: none;">
	<p id="alert-content" align="center"></p>
</div>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin-top: 0;">用户参数</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/params/doUpdateUserParams" method="post" style="margin: 0; padding: 0">
		<input type="hidden" name="menuid" value="${menuid}" />
		<ul class="nav nav-tabs" style="padding-left:10px;margin:0;">
			<li class="active"><a href="#tab9" data-toggle="tab">主题配置</a></li> 
		</ul>
		<div class="tab-content" style="margin: 0; padding: 0;">
			<!-- 用户主题配置 start -->
			<div class="tab-pane mar_5 active" id="tab9">
				<table class="table table-border-bot table-border-rl table-padd10-lr bukong-table">
					<tr>
						<td class="device_td_bg2">界面主题</td>
						<td>
							<select id="theme-sel" class="skin-sel" style="width:175px;padding:4px;" onchange="themeChange()" >
						        <option id="fir" value="1">经典主题：清新灵动</option>
						        <option id="sec" value="2">经典主题：梦幻蓝光</option>
					      	</select>
					    </td>
					</tr>
				</table>
			</div>
		</div>
		<div class="btn_line">
			<button class="btn btn-info" type="submit">确 定</button>
		</div>
	</form>
</div>
<script>
	function isStartDepart(a) {
		if ($(a).attr("checked") == 'checked') {
			$("#user-depart-options").show();
		}
	}
	
	function isStart(a) {
		if ($(a).attr("checked") == 'checked') {
			$("#user-depart-options").hide()
		}
	}

	function initRadio() {
		var val = $("#watermark_canvasPosition").val();
		if (val == "") {
			val = "0";
		}
		$("input[name='watermark_canvasPosition_radio']").each(function(){
			if (this.value == val) {
				this.checked = true;
				return false;
			}
		});
	}
	
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#code1").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
		var theme = '${themeType}';
		$('#theme-sel').val(theme);
		if(theme == ''){
			$('#theme-sel').val("1");
		}
		initRadio();
	});
	
	//切换主题方法
	function themeChange(){
		var theme=$("#theme-sel option:selected").val();
		jQuery.ajax({
			type : "GET",  
			async: false,
			dataType : "json",
			url: "${root}/system/theme/change_theme/" + theme + "/",
			success : function(data) {
				if(data != null){
					if(data.message == 'ok'){
						/**if(confirm("界面主题已改变，请重新登录！")){
							window.location.href="${root}/j_spring_security_logout";
						}*/
						alert("界面主题已改变，请刷新页面或重新登录！");
					}
				}
			}
		});
	}
	
</script>
