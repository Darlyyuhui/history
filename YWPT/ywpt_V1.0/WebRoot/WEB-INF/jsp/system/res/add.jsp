<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
  <h4 class="xtcs_h4" style="margin:0;">系统参数</h4>
  <form id="inputForm" class="form-horizontal" action="${root}/system/res/doAddModule/${param.menuid}/" method="post" style="margin:0;">
		<div class="mar_5" >
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">名称：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="res-name" placeholder="名称" specialcharfilter="true" name="name" maxlength="10" class="input-large required"><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="device_td_bg3">图标：</td>
					<td><input style="width:50%; min-width:200px;" type="text"  placeholder="图标" name="icon" class="input-large"></td>
				</tr>
				<tr>
					<td class="device_td_bg3">排序：</td>
					<td><input style="width:50%; min-width:200px;" type="text" placeholder="排序" maxlength="10" name="sortOrder" digits="true" class="input-large required"><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="device_td_bg3">访问地址：</td>
					<td><textarea rows="6" class="span8" style="min-width:200px;" maxlength="100" name="content"></textarea><span></span></td>
				</tr>
			</table>
		</div>
		<div class="btn_line">
			  <input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存" />
			  <input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
  </form>
</div>
<script>
		function showList(){
			window.location.href="${root}/system/res/sublist/${menuid}/";
		}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#res-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
