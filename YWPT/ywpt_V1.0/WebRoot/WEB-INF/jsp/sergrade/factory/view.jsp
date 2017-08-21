<%@ page language="java" pageEncoding="UTF-8"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="conten_box">
	<form id="inputForm" class="form-inline" action="${root}/vio/hcjx/view" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin-top: 0;">运维服务商--详情</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="id" value="${factoryInfo.id}" />
		<div class="mar_5">
		  <table class="table table-border-rl table-border-bot bukong-table">
		  	<tr><td colspan="4" style="font-weight: bold;font-size: 14px;">运维服务商信息</td></tr>
			
			<tr>
				<td class="device_td_bg3">公司名称：</td>
				<td>${factoryInfo.name}</td>
				<td class="device_td_bg3">联系人：</td>
				<td>${factoryInfo.linkman}</td>		
			</tr>
			
			<tr>
				<td class="device_td_bg3">联系电话：</td>
				<td>${factoryInfo.telphone}</td>
				
				<td class="device_td_bg3">服务商级别：</td>
				<c:if test="${factoryInfo.level == '1'}">
					<td>
						<img src="${root}/images/star.png" style="width:20px;">
					</td>
				</c:if>
				<c:if test="${factoryInfo.level == '2'}">
					<td>
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</td>
				</c:if>
				<c:if test="${factoryInfo.level == '3'}">
					<td>
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</td>
				</c:if>
				<c:if test="${factoryInfo.level == '4'}">
					<td>
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</td>
				</c:if>
				<c:if test="${factoryInfo.level == '5'}">
					<td>
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</td>
				</c:if>
				
				<c:if test="${factoryInfo.level == ''}">
					<td>&nbsp;</td>
				</c:if>
				
			</tr>
			<tr>
			 	<td class="device_td_bg3">备　　注：</td>
				<td colspan="3">
					${factoryInfo.remark}
				</td>
			</tr>
		   
		 </table>
		</div>
		<div class="btn_line">
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script type="text/javascript">
	function showList(){
		window.location.href = "${root}/sergrade/factory/info/list/${menuid}/?page=${page}&isgetsession=1";
	}
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
