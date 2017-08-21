<%@ page language="java" pageEncoding="UTF-8"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="conten_box">
	<form id="inputForm" class="form-inline" action="${root}/vio/hcjx/view" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin-top: 0;">服务级别--详情</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="id" value="${gradeInfo.id}" />
		<div class="mar_5">
		  <table class="table table-border-rl table-border-bot bukong-table">
		  	<tr><td colspan="4" style="font-weight: bold;font-size: 14px;">服务级别信息</td></tr>
			
			<tr>
				<td class="device_td_bg3">服务级别：</td>
				<td colspan="3">
					<tags:xiangxuncache keyName="Dic" typeCode="gradetype" id="${gradeInfo.code}" ></tags:xiangxuncache>
				</td>		
			</tr>
			
			<tr>
				<td class="device_td_bg3">分值范围：</td>
				<td colspan="3">
					${gradeInfo.minpoint}-${gradeInfo.maxpoint}
				</td>
			</tr>
			<tr>
				<td class="device_td_bg3">星级：</td>
				<td colspan="3">
					<c:if test="${gradeInfo.code == '1'}">
						<img src="${root}/images/star.png" style="width:20px;">
					</c:if>
					<c:if test="${gradeInfo.code == '2'}">
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</c:if>
					<c:if test="${gradeInfo.code == '3'}">
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</c:if>
					<c:if test="${gradeInfo.code == '4'}">
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</c:if>
					<c:if test="${gradeInfo.code == '5'}">
						<img src="${root}/images/star.png" style="width:20px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
						<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
					</c:if>
				</td>
			</tr>
			
			<tr>
			 	<td class="device_td_bg3">备　　注：</td>
				<td colspan="3">
					${gradeInfo.remark}
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
		window.location.href = "${root}/sergrade/grade/info/list/${menuid}/?page=${page}&isgetsession=1";
	}
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
