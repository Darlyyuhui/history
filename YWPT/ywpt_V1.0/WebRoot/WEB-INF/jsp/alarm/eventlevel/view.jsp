<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">事件级别-详情</h4>
	<input type="hidden" name="menuid" value="${menuid}"/>
	<input type="hidden" name="page" value="${page}"/>
	<div class="mar_5">
	  <table class="table tingche-table table-border-rl table-border-bot">
		<tr>
			<td class="device_td_bg2" style="width: 10%">级别名称：</td>
			<td>
				${eventlevel.name}
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">告警方式：</td>
			<td>
				<ul class="ul_checkbox">
					<c:forEach items="${alarmtypeList}" var="type" varStatus="r">
								<li><input type="checkbox" name="relationType" disabled="disabled" value="${type.code}" ${type.code==types[r.index] ? "checked" : ""}/>${type.name}</li>
							</c:forEach>
				</ul>
			</td> 
		</tr>
		<tr>
					<td class="device_td_bg3">健康度权重系数：</td>
					<td>
						<input style="width:26%;" type="text" id="healthcoefficient" name="healthcoefficient" class="required" maxlength="30" class="required" value="${eventlevel.healthcoefficient}">
						<font color="red">*</font>
					</td> 
				</tr>
				<tr>
					<td class="td40">级别名称</td>
				    <td>
				    	<select style="width:26%;" id="grade" name="grade" class="required" placeholder="事件级别">
							<option value="">请选择</option>
							<c:forEach items="${eventLeverGradeType}" var="level">
							    <option value="${level.code}" ${level.code==eventlevel.grade?'selected':''}>${level.name}</option>
							</c:forEach>
			         	 </select>
				    </td>
				</tr>
		<tr>
			<td class="device_td_bg2">告警颜色：</td>
			<td>
				<font style="background-color: ${eventlevel.color};color: white;">${eventlevel.color}</font>
			</td> 
		</tr>
		<tr>
			<td class="device_td_bg2">备注：</td>
			<td>
				${eventlevel.note}
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
		window.location.href = "${root}/alarm/eventlevel/list/${menuid}/?page=${page}&isgetsession=1";
	}
//-->
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>