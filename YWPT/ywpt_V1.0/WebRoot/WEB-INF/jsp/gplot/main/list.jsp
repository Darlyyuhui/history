<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

   <!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">${root}
		<form id="inputForm" class="form-inline" action="${root}/gplot/main/view/${menuid}/" method="post" style="margin:0;padding:0 10px 0 5px;">
			<table width="100%" border="0">
			  <tr>
			    <td class="search_item_td">ftp</td>
			    <td><input maxlength="30" style="width:90%;" id="type" name="type" value="ftp" type="radio" /></td>
			    <td class="search_item_td">project</td>
			    <td><input maxlength="20" style="width:90%;" id="type" name="type" value="project" type="radio" /></td>
			    <td class="search_item_td">database</td>
			    <td><input maxlength="13" style="width:90%;" id="type" name="type" value="database" type="radio" /></td>
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info mar_l10" value="查询" style="height:28px;"/>
			      <input onclick="reValues();" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
