<%@ page language="java" pageEncoding="UTF-8"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
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
	<h4 class="xtcs_h4" style="margin-top: 0;">添加用户(选择警员)</h4>
		<div class="row-fluid" style="margin-left: 0; padding-left: 0;">
			<div style="margin: 0 10px;overflow-x: scroll;">
				<table class="table table-striped table-bordered table-hover table-style" id="table">
					<thead>
						<tr>
							<th width="16"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
							<th>姓名</th>
							<th>警号</th>
							<th>所属部门</th>
							<th>职位</th>
							<th>类别</th>
							<th>手机号</th>
							<th>电台呼号</th>
							<th>电台频段</th>
							<th>有GPS设备</th>
							<th>GPRS号</th>
							<th>GPS设备序列号</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="rows" value="0" />
						<c:forEach items="${pageList.result}" var="user">
							<c:set var="rows" value="${rows+1}" />
							<tr onclick="rowOnclick(this)">
								<td width="16"><input type="checkbox" value="${user.id}" name="select-chk" /></td>
								<td>${user.name}</td>
								<td>${user.code}</td>
								<td><tags:cache keyName="Department" id="${user.orgId}"></tags:cache></td>
								<td><tags:cache keyName="Dic" id="${user.postCode}" typeCode="policeuserpost"></tags:cache></td>
								<td><tags:cache keyName="Dic" id="${user.types}" typeCode="policetype"></tags:cache></td>
								<td>${user.telPhone}</td>
								<td class="texthidden">${user.callnumber}</td>
								<td class="texthidden">${user.frequency}</td>
								<td class="texthidden">${user.hasGps=='1'?'有':'无'}</td>
								<td>${user.gpsTelnumber}</td>
								<td>${user.gpsSerialId}</td>
							</tr>
						</c:forEach>
						<c:if test="${pageList.result!=null}">
							<c:forEach begin="1" end="${pageList.pageSize-rows}">
								<tr>
									<td colspan="14">&nbsp;</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<tags:pagination page="${pageList}"></tags:pagination>
			</div>
			<div class="clear"></div>
			<div class="btn_line" style="text-align:center; margin-top: 10px;">
				<input id="submit_btn" class="btn btn-info mar_r10" type="button" value="确定" onclick="changeUser();" />
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
			</div>
</div>
<script type="text/javascript">
	function changeUser() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要添加的记录。");
			return;
		}
		if (ids.length >1) {
			showMessage("一次只能选择一条记录。");
		} else {
			window.location.href="${root}/system/policeuser/gouseradd/"+ids+"/${menuid}/${orgId}/";
		}
	}
	$("#alert-div").hide();
</script>