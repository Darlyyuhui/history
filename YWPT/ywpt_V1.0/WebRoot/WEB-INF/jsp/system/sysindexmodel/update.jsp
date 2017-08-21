<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box">
	<form id="inputForm" class="form-inline" action="${root}/system/sysindexmodel/doUpdate" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin: 0;">首页组件--信息修改</h4>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="id" value="${sysIndexModel.id}"/>
		<div class="mar_5">
		  <table class="table tingche-table table-border-rl table-border-bot">
			<tr>
				<td class="device_td_bg3">组件名称：</td>
				<td>
					<input style="width:160px;" type="text" id="name" name="name" value="${sysIndexModel.name}" class="required" maxlength="30" class="required">
					<font color="red">*</font>
				</td> 
				<td class="device_td_bg3">组件编号：</td>
				<td>
					<input style="width:160px;" type="text" id="code" name="code" value="${sysIndexModel.code}" maxlength="4" class="required" >
                    <font style="color: red;">*</font>
				</td>			
			</tr>
			<tr>
				<td class="device_td_bg3">组件行数：</td>
				<td>
					<input style="width:160px;" type="text" id="rowcount" name="rowcount" value="${sysIndexModel.rowcount}" class="required" maxlength="2" digits="true" >
					<font color="red">*</font>
				</td> 
				<td class="device_td_bg3">组件布局：</td>
				<td>
					<select style="width:168px;padding:4px;overflow:hidden;" id="layout" name="layout" >
			            <option value="">请选择</option>
			            <option value="left" ${sysIndexModel.layout=='left'?'selected':''}>左侧列显示</option>
			            <option value="center" ${sysIndexModel.layout=='center'?'selected':''}>中间列显示</option>
			            <option value="right" ${sysIndexModel.layout=='right'?'selected':''}>右侧列显示</option>
				    </select>
                    <font style="color: red;">*</font>
				</td>			
			</tr>
			<tr>
				<td class="device_td_bg3">HTML源码：</td>
				<td colspan="3">
					<textarea name="htmlsrc" rows="10" cols="60">${sysIndexModel.htmlsrc}</textarea>
				</td> 
			</tr>
			<tr>
				<td class="device_td_bg3">是否启用：</td>
				<td>
					<ul class="ul_dev">
			            <li><input type="radio" name="isshow" id="isshow" value="1" ${sysIndexModel.isshow=='1'?'checked':''}>启用</li>
			            <li><input type="radio" name="isshow" id="isshow" value="0" ${sysIndexModel.isshow=='0'?'checked':''}>不启用</li>
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
		window.location.href = "${root}/system/sysindexmodel/list/${menuid}/?page=${page}&isgetsession=1";
	}
	$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
					
				}
			});
	});
//-->
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>