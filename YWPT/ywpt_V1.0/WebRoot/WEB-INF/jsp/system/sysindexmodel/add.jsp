<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%
	String pages = request.getParameter("page");
 %>
<div class="conten_box">
	<form id="inputForm" class="form-inline" action="${root}/system/sysindexmodel/doAdd" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin:0;">首页组件--信息添加</h4>
		<input type="hidden" name="menuid" value="${param.menuid}"/>
		<div class="mar_5">
		  <table class="table tingche-table table-border-rl table-border-bot">
			<tr>
				<td class="device_td_bg3">组件名称：</td>
				<td>
					<input style="width:160px;" type="text" id="name" name="name" class="required" maxlength="30" class="required">
					<font color="red">*</font>
				</td> 
				<td class="device_td_bg3">组件编号：</td>
				<td>
					<input style="width:160px;" type="text" id="code" name="code" maxlength="4" class="required" >
                    <font style="color: red;"> *</font>
				</td>			
			</tr>
			<tr>
				<td class="device_td_bg3">组件行数：</td>
				<td>
					<input style="width:160px;" type="text" id="rowcount" name="rowcount" class="required" maxlength="2" digits="true">
					<font color="red">*</font>
				</td> 
				<td class="device_td_bg3">组件布局：</td>
				<td>
					<select style="width:168px;padding:4px;overflow:hidden;" id="layout" name="layout" >
			            <option value="">请选择</option>
			            <option value="left">左侧列显示</option>
			            <option value="center">中间列显示</option>
			            <option value="right">右侧列显示</option>
				    </select>
				</td>			
			</tr>
			<tr>
				<td class="device_td_bg3">HTML源码：</td>
				<td colspan="3">
					<textarea name="htmlsrc" rows="3" cols="60"></textarea>
				</td> 
			</tr>
			<tr>
				<td class="device_td_bg3">是否启用：</td>
				<td>
					<ul class="ul_dev">
			            <li><input type="radio" name="isshow" id="isshow" value="1">启用</li>
			            <li><input type="radio" name="isshow" id="isshow" value="0" checked>不启用</li>
			        </ul>
				</td> 
				<td class="device_td_bg3"></td>
				<td>
					
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
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/system/sysindexmodel/list/${menuid}/?isgetsession=1&page=" + <%=pages%>;
	}
	$(document).ready(function() {
			//聚焦第一个输入框
			$("#ftpip").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					"ftppassword":{
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
