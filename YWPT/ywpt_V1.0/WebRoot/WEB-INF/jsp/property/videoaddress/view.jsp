<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
    <h4 class="xtcs_h4" style="margin:0;">播放模板配置-详情</h4><input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="id" value="${videoAddressInfo.id}"/>
		<div class="mar_5">
			<table class="table table-border-rl table-border-bot bukong-table">
			  <tr>
			    <td class="device_td_bg3">模板名称：</td>
			    <td>
			    ${videoAddressInfo.name}
			    </td>
			  </tr>
			  <tr>
			    <td class="device_td_bg3">IP地址：</td>
			    <td>${videoAddressInfo.ip}</td>
			  </tr>
			  <tr>
			    <td class="device_td_bg3">端口号：</td>
			    <td>${videoAddressInfo.port}</td>
			  </tr>
			  <tr>
			    <td class="device_td_bg3">用户名：</td>
			    <td>${videoAddressInfo.username}</td>
			  </tr>
			  <tr>
			    <td class="device_td_bg3">密码：</td>
			    <td>${videoAddressInfo.password}</td>
			  </tr>
			</table>
		</div>
		<div class="btn_line">
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
</div>
<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#ip").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules:{
					password:{
						rangelength:[6,12]
					}
				}
			});
		});
		
	function showList(){
		window.location.href = "${root}/property/videoaddress/list/${menuid}/?page=${page}&isgetsession=1";
	}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>