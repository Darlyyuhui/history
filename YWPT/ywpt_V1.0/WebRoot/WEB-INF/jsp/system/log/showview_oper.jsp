<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">操作日志--详情</h4>
	<div class="mar_5" >
		<table class="table table-border-rl table-border-bot bukong-table">
			<tr>
				<td class="device_td_bg3">所属模块：</td>
				<td>${log.moduleName}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">IP地址：</td>
				<td>${log.ip}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">操作时间：</td>
				<td><fmt:formatDate value="${log.operateTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
			</tr>
			
			<tr>
				<td class="device_td_bg3">操作员：</td>
				<td>${log.operator}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">操作类型：</td>
				<td>
					<c:if test="${log.operateType == 'a'}">
						新增
					</c:if>
					<c:if test="${log.operateType == 'u'}">
						修改
					</c:if>
					<c:if test="${log.operateType == 'd'}">
						删除
					</c:if>
					<c:if test="${log.operateType == 'o'}">
						其他
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="device_td_bg3">描述信息：</td>
				<td style="padding-right: 5px;">${log.des}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">是否成功：</td>
				<td>${log.isSuccess eq '1'?'成功':'失败'}</td>
			</tr>
			<tr>
				<td class="device_td_bg3">参数：</td>
				<td>${log.args}</td>
			</tr>

		</table>
	</div>
	<div class="btn_line">
		<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
	</div>
</div>
