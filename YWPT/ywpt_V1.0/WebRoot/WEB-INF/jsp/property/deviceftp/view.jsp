<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">文件服务器-详情</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<div class="mar_5">
	  <table class="table tingche-table table-border-rl table-border-bot">
		<tr>
			<td class="device_td_bg2">服务器名称：</td>
			<td>
				${ftpInfo.name}
			</td> 
			<td class="device_td_bg2">服务器IP：</td>
			<td>
				${ftpInfo.ftpip}
			</td>			
		</tr>
		<tr>
			<td class="device_td_bg2">上行端口：</td>
			<td>
				${ftpInfo.ftpport}
			</td> 
			<td class="device_td_bg2">下行端口：</td>
			<td>
				${ftpInfo.httpport}
			</td>			
		</tr>
		<tr>
			<td class="device_td_bg2">上行账号：</td>
			<td>
				${ftpInfo.ftpuser}
			</td> 
			<td class="device_td_bg2">下行虚拟目录：</td>
			<td>
				${ftpInfo.dirname}
			</td>			
		</tr>
		<tr>
			<td class="device_td_bg2">上行密码：</td>
			<td>
				${ftpInfo.ftppassword}
			</td>
			<td class="device_td_bg2"></td>
			<td>
				
			</td>
		</tr>
	  </table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
	</div>
</div>
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/property/deviceftp/list/${menuid}/?page=${page}&isgetsession=1";
	}
	$(document).ready(function() {
			//聚焦第一个输入框
			$("#ftpip").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					ftppassword:{
						rangelength:[6,12]
					}
				}
			});
		});
//-->
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>