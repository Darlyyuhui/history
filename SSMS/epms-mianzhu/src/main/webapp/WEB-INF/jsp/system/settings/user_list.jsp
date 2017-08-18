<%@ page language="java" pageEncoding="UTF-8"%>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display: none;">
	<p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin-top: 0;">用户参数</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/system/params/doUpdateUserParams" method="post" style="margin: 0; padding: 0">
		<input type="hidden" name="menuid" value="${menuid}" />
			<div class="btn_line" style="text-align:center; margin: 0;">
				<button class="btn btn-info" type="submit">确 定</button>
			</div>
	</form>
</div>
<script>
	function isStartDepart(a) {
		if ($(a).attr("checked") == 'checked') {
			$("#user-depart-options").show();
		}

	}
	
	function isStart(a) {
		if ($(a).attr("checked") == 'checked') {
			$("#user-depart-options").hide()
		}

	}

	$(document).ready(function() {
		//聚焦第一个输入框
		$("#user-name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
</script>
