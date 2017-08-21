<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
    <h4 class="xtcs_h4" style="margin:0;">播放模板配置-添加</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/property/videoaddress/doAdd" method="post" style="margin:0;">
		<input type="hidden" name="menuid" value="${param.menuid}"/>
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table">
				<tr>
					<td class="device_td_bg3">模板名称：</td>
					<td><input style="width:50%; min-width:200px;" type="text" specialcharfilter="true" id="name" maxlength="20" placeholder="模板名称" name="name" class="required" ><strong style="color:red">&nbsp;*</strong></td>
				</tr>
				<tr>
					<td class="device_td_bg3">IP地址：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="ip" placeholder="IP地址" maxlength="15" name="ip" class="required" ip="true"><strong style="color:red">&nbsp;*</strong></td>
				</tr>
				<tr>
					<td class="device_td_bg3">端口号：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="port" maxlength="6" placeholder="端口号" name="port" class="required" digits="true" ><strong style="color:red">&nbsp;*</strong></td>
				</tr>
				<tr>
					<td class="device_td_bg3">用户名：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="username" maxlength="10" specialcharfilter="true" placeholder="用户名" name="username"  specialcharfilter="true"></td>
				</tr>
				<tr>
					<td class="device_td_bg3">密码：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="password" password="true" maxlength="12" placeholder="密码" name="password"  ></td>
				</tr>
			</table>
	    </div>
		<div class="btn_line">
			<input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="保存" />
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
  </form>
  <script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#ip").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules:{
					password:{
						rangelength:[5,12]
					}
				}
			});
		});
 </script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>