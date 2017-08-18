<%@ page language="java" pageEncoding="UTF-8"%>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display: none;">
	<p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">
			×
		</button>
		<p align="center">
			${message}
		</p>
	</div>
	<script>
        setTimeout('$("#message").hide("slow")', 1200);
    </script>
</c:if>

<div class="page-header">
	<h1>
		系统参数
		<small> <i class="ace-icon fa fa-angle-double-right"></i> 对系统参数进行设置 </small>
	</h1>
</div>
<style type="text/css">
.ul_lable {
	wihte-space:nowrap;
	width:70px;
}
</style>
<div class="row">
	<div class="col-xs-12">
		<form id="inputForm" class="form-horizontal" action="${root}/system/params/doUpdateSysParams" method="post" style="margin: 0; padding: 0">
			<input type="hidden" name="menuid" value="${menuid}" />
			<div class="row-fluid">
				<ul class="nav nav-tabs" style="padding-left:10px;margin:0;">
					<li class="active"><a data-toggle="tab">系统参数</a></li>
				</ul>
				<div class="tab-content" style="margin: 0; padding: 0;">
					<!-- 系统参数页签 start -->
					<div class="tab-pane mar_5 active" id="tab1">
						<table class="table table-border-bot bukong-table table-border-rl table-padd10-lr" style="border-top:0">
							<tr>
								<td class="set-label">区域编码：</td>
								<td colspan="3"><input type="text" class="text required" maxlength="6" minlength="6" id="area-code" name="areacode" digits=true value="${areacode}" style="width: 30%;" /></td>
							</tr>
							<tr>
								<td class="set-label">权限设置：</td>
								<td class="inputImportent" colspan="3">
									<ul class="ul_checkbox">
									  <li><input type="radio" id="chk-isCross" name="isStartDepartControl" value="1" ${isStartDepartControl== '1'?'checked':''} onclick="isStartDepart(this)">启用</li>
									  <li><input type="radio" name="isStartDepartControl" value="0" ${isStartDepartControl== '0'?'checked':''} onclick="isStart(this)">不启用【不启用时登陆人能看到所有部门的信息】</li>
									  <div class="clear"></div>
									</ul>
									<ul class="ul_checkbox">
									  <li><input type="checkbox" id="isBlankDeviceShowAll" name="isBlankDeviceShowAll" value="1" ${isBlankDeviceShowAll=='1'?'checked':''}>当用户所在的部门没有设备时，不进行权限过滤</li>
									  <div class="clear"></div>
									</ul>
								</td>
							</tr>
						</table>
					</div>
					<!-- 系统参数页签end -->
				</div>
			</div>
			 <div class="clearfix form-actions">
	            <div class="col-md-offset-1 col-md-9">
	                <input id="submit_btn" class="btn btn-primary" type="submit" value="保存"/>
	            </div>
	        </div>
		</form>
		<div class="clear"></div>
	</div>
</div>
<script>
	function isStartDepart(a) {
		if ($(a).attr("checked") == 'checked') {
			$("#user-depart-options").show();
		}

	}
	
	function isStart(a) {
		if ($(a).attr("checked") == 'checked') {
			$("#user-depart-options").hide();
		}

	}

	$(document).ready(function() {
		//聚焦第一个输入框
		$("#user-name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
</script>
