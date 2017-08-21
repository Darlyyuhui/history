<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="conten_box" style="overflow:auto;">
	  <h4 class="xtcs_h4" style="margin:0;">评估信息-详情
		  <font style="font-size: large;font-weight: bolder;font-family: inherit;color: #3ECEF0;margin-left: 20px;">评估得分：</font>
		  <font style="font-size: xx-large;font-weight: bolder;font-family: fantasy;color: ${color};">${workorder.note}</font>
		  <font style="font-size: large;font-weight: bolder;font-family: inherit;color: #3ECEF0;margin-left: 10px;">评估等级：</font>
		  <font style="font-size: xx-large;font-weight: bolder;font-family: inherit;color: ${color};">${level}</font>
	  </h4>
	  <table class="table table-striped table-bordered table-condensed table-style" id="table">
		<thead>
			<tr>
				<th>责任人</th>
				<th>联系电话</th>
				<th>违法规则</th>
				<th>扣除分数</th>
				<th>评估人</th>
				<th>评估时间</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${pageList.result}" var="appraise">
				<tr onclick="rowOnclick(this)" data="${appraise.id}">
					<td>${appraise.contactname}</td>
					<td>${appraise.telephone}</td>
					<td>${appraise.violaterule}</td>
					<td><c:if test="${appraise.deductscore=='0'}"></c:if>
						<c:if test="${appraise.deductscore!='0'}">${appraise.deductscore}</c:if></td>
					<td><tags:xiangxuncache keyName="username_cache" id="${appraise.appraiser}"></tags:xiangxuncache></td>
					<td><fmt:formatDate value="${appraise.appraisetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
			<c:if test="${pageList.result!=null}">
				<c:forEach begin="1" end="${10-fn:length(pageList.result)}">
					<tr>
						<td colspan="15">&nbsp;</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<tags:pagination page="${pageList}"></tags:pagination>
</div>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>