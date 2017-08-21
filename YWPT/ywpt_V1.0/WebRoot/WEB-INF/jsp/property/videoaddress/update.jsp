<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
    <h4 class="xtcs_h4" style="margin:0;">播放模板配置-修改</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/property/videoaddress/doUpdate" method="post" style="margin-bottom:0;padding:0;">
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="id" value="${videoAddressInfo.id}"/>
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table">
			  <tr>
			    <td class="device_td_bg3">模板名称：</td>
			    <td><input style="width:50%; min-width:200px;" type="text" id="name" specialcharfilter="true" placeholder="模板名称" name="name" specialcharfilter="true" maxlength="20" value="${videoAddressInfo.name}" class="input-large required" ><strong style="color:red">&nbsp;*</strong></td>
			  </tr>
			  <tr>
			    <td class="device_td_bg3">IP地址：</td>
			    <td><input style="width:50%; min-width:200px;" type="text" id="ip" placeholder="IP地址" maxlength="15" name="ip" value="${videoAddressInfo.ip}" class="input-large required" ip="true"><strong style="color:red">&nbsp;*</strong></td>
			  </tr>
			  <tr>
			    <td class="device_td_bg3">端口号：</td>
			    <td><input style="width:50%; min-width:200px;" type="text" id="port" placeholder="端口号" maxlength="6" name="port" value="${videoAddressInfo.port}" class="input-large required" digits="true" ><strong style="color:red">&nbsp;*</strong></td>
			  </tr>
			  <tr>
			    <td class="device_td_bg3">用户名：</td>
			    <td><input style="width:50%; min-width:200px;" type="text" id="username" placeholder="用户名" specialcharfilter="true" maxlength="10" name="username" specialcharfilter="true" value="${videoAddressInfo.username}" class="input-large" ></td>
			  </tr>
			  <tr>
			    <td class="device_td_bg3">密码：</td>
			    <td><input style="width:50%; min-width:200px;" type="text" id="password" placeholder="密码" password="true" name="password" maxlength="12" value="${videoAddressInfo.password}" class="input-large" ></td>
			  </tr>
			</table>
		</div>
		<div class="btn_line">
		  <input id="submit_btn" class="btn btn-info mar_r10" type="submit" value="修改" />
		  <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
  </form>
</div>
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