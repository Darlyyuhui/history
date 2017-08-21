<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link rel="stylesheet" href="${root}/compnents/colorpicker/css/jquery.bigcolorpicker.css">
<script src="${root}/compnents/colorpicker/js/jquery.bigcolorpicker.min.js"></script>
<%
	String pages = request.getParameter("page");
 %>
<div style="height: 650px;overflow: auto;">
	<div class="conten_box">
		<form id="inputForm" class="form-inline" action="${root}/alarm/eventlevel/doAdd" method="post" style="margin:0;">
			<h4 class="xtcs_h4" style="margin:0;">事件级别-添加</h4>
			<input type="hidden" name="menuid" value="${menuid}"/>
			<div class="mar_5">
			  <table class="table tingche-table table-border-rl table-border-bot" width="100%">
				<tr>
					<td class="device_td_bg3" style="width: 10%">级别名称：</td>
					<td>
						<input style="width:26%;" type="text" id="name" name="name" class="required" maxlength="30" class="required">
						<font color="red">*</font>
					</td> 
				</tr>
				<tr>
					<td class="device_td_bg3">告警方式：</td>
					<td>
						<ul class="ul_checkbox">
							<c:forEach items="${alarmtypeList}" var="type">
								<li><input type="checkbox" name="relationType" value="${type.code}"/>${type.name}</li>
							</c:forEach>
						</ul>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">健康度权重系数：</td>
					<td>
						<input style="width:26%;" type="text" id="healthcoefficient" name="healthcoefficient" class="required" maxlength="4">
						<font color="red">*系数范围（0.10---1.00）</font>
					</td> 
				</tr>
				<tr>
					<td class="td40">级别等级</td>
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
					<td class="device_td_bg3">告警颜色：</td>
					<td>
						<input style="width: 175px; background: #FFFFFF;" readonly="readonly" type="text" id="alarmcolor" class="valid" value="告警颜色">
						<input style="width: 85px;" type="text" name="color" readonly="readonly" class="required" value="">
						<font style="color: red">&nbsp;*</font>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">备注：</td>
					<td>
						<textarea rows="3" style="width:60%;" id="note" name="note" maxlength="100" ></textarea>
					</td>	
				</tr>
			  </table>
			</div>
			<div class="btn_line">
				<button class="btn btn-info mar_r10" type="submit">保存</button>
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	
	function showList(){
		window.location.href = "${root}/alarm/eventlevel/list/${menuid}/?isgetsession=1&page=" + <%=pages%>;
	}
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"name":{
					remote:{
						url:"${root}/alarm/eventlevel/nameExist",
						type:"post",
						data:{
							name:function(){
								return $("#name").val();
							}
						}
					}
				},
				"healthcoefficient":{
					range:[0.10,1.00]
				}
			}
		});
		
		$("#inputForm").submit(function(){
			var checkflag = false;
			$(".ul_checkbox li>input").each(function(){
				var checked = $(this).attr("checked");
				if(checked=="checked"){
					checkflag = true;
				}
			});
			if(!checkflag){
				alert("请至少选择一种告警方式");
				return false;
			}
		});
	});
	
	window.onload=function (){
		$("#alarmcolor").bigColorpicker(function(el,color){
			$(el).css("background-color",color);
			$(el).next().attr("value",color);
		});
	}
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
