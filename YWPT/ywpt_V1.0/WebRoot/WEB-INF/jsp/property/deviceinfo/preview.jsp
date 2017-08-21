<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="<c:url value='/js/jquery.form.js'/>" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-success" id="alert-div" style="display:none;">
	<p id="alert-content" align="center"></p>
</div>
<div class="row-fluid " id="user-content" >
	<div style="margin:10px;">
		<div class="row-fluid">
			<form id="inputForm" class="form-horizontal" action="${root}/property/deviceinfo/doImport/${menuid}/" method="post" style="margin-bottom:0;padding:0;">
				<table class="table table-striped table-bordered table-condensed table-style" id="table">
					<thead>
						<tr>
							<th>序号</th>
							<th>设备编号</th>
							<th>设备名称</th>
							<th>设备IP</th>
							<th>所在道路</th>
							<th>管理部门</th>
							<th>建设厂商</th>
							<th>服务厂商</th>
							<th>设备功能</th>
							<th>资产状态</th>
							<th>保修时间</th>
							<th>采购时间</th>
							<th>安装时间</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="rows" value="0" />
						<c:forEach items="${pageList.result}" var="obj" varStatus="c">
							<c:set var="rows" value="${rows+1}" />
							<tr onclick="rowOnclick(this)">
								<td>${c.index+1 }</td>
								<td>${obj.device_code}</td>
								<td>${obj.device_name}</td>
								<td>${obj.device_ip}</td>
								<td>${obj.device_road}</td>
								<td>${obj.device_depatment}</td>
								<td>${obj.device_business}</td>
								<td>${obj.device_fuwucs}</td>
								<td>${obj.device_gongneng}</td>
								<td>${obj.asset_status}</td>
								<td>${obj.asset_baoxiutime}</td>
								<td>${obj.asset_caigoutime}</td>
								<td>${obj.asset_anzhuangtime}</td>
							</tr>
						</c:forEach>
							<c:if test="${rows<13 }">
								<c:forEach begin="1" end="${13-rows}">
									<tr>
										<td colspan="15">&nbsp;</td>
									</tr>
								</c:forEach>
							</c:if>
					</tbody>
				</table>
				<div class="mar_t5">
				  <tags:paginationPost page="${pageList}"></tags:paginationPost>
				</div>
			</form>
			<div class="clear"></div>
		</div>
		<!--/row-->
	</div>
</div>
<script>
	function save() {
		var options = {
			      dataType: 'json',
			      success: function(data) {
			         	if(data.result=='ok'){
			         		showMessage("导入成功");
							setTimeout("reload()", 1200);
		      			}else if(data.result=='error'){
		      				showMessage("导入失败  !!" + data.resultmessage);
							setTimeout("reload()", 1200);
		      			}
			      }
		 };
		$("#inputForm").ajaxSubmit(options);
	}

	function reload() {
		parent.location.href = "${root}/property/deviceinfo/list/${menuid}/";
	}
	
	parent.$("#btn_div").show();
</script>
