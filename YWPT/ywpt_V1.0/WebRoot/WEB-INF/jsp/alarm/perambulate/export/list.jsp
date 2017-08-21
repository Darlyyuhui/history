<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div"
	style="display: none">
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
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues() {
		$("#beginDate").val("");
	}
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	
	<div class="mar_t10">
		<form method="post" name="queryForm"
			action="${root}/alarm/perambulateReport/list/${menuid}/"
			id="queryForm" class="form-inline" style="margin: 0; padding: 0 6px;">
			<table>
				<tr>

					<td>统计日期：</td>
					<td><div id="date_div">

							<input class="Wdate required" name="search_beginDate"
								id="beginDate" value="${time}" readonly="readonly" type="text"
								style="width: 180px;"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'%y-%M-%d %H-%m-%s'})" />
						</div></td>
					


					<td valign="bottom"><input type="submit"
						class="btn btn-info pull-left mar_l10" value="查询"
						style="height: 28px;" /> <input onclick="reValues()" type="button"
						class="btn mar_l10" value="重置" style="height: 28px;" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div style="overflow: auto;">
		<table
			class="table table-striped table-bordered table-condensed table-style"
			id="table">
			<thead>
				<tr align="center">
					<th width="30%" align="left">文件名称</th>

					<th width="20%">创建时间</th>
					<th width="10%">文件大小</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<c:if test="${Filelist[0]==null}">
				<tr>
					<td colspan="4">
						<div style='text-align: center; margin-right: 40px;'>
							<font color='red'>没有查到结果。</font>
						</div>
					</td>
				</tr>

			</c:if>
			<tbody id="tbody">
				<c:forEach items="${Filelist}" var="file">
					<tr>
						<td>&nbsp;${file.filename}</td>

						<td>&nbsp;${file.creattime}</td>
						<td>&nbsp;${file.filesize}</td>
						<td><a href="${root}/download/xls/${file.filename}">下载</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</div>
</div>
<script type="text/javascript">
	
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf"%>