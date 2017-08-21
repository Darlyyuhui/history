<%@ page language="java" pageEncoding="UTF-8"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="conten_box">
	<form id="inputForm" class="form-inline" action="${root}/vio/hcjx/view" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin-top: 0;">问题录入--详情</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" name="id" value="${factoryInfo.id}" />
		<div class="mar_5">
		  <table class="table table-border-rl table-border-bot bukong-table">
		  	<tr><td colspan="4" style="font-weight: bold;font-size: 14px;">问题信息</td></tr>
			
			<tr>
				<td class="device_td_bg3" width="10%">录入人员：</td>
				<td width="40%"><tags:xiangxuncache keyName="username_cache" id="${questionInfo.operator}" /></td>
				<td class="device_td_bg3" width="10%">录入时间：</td>
				<td width="40%"><fmt:formatDate value="${questionInfo.insertTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			
			<tr>
			 	<td class="device_td_bg3" width="10%">问题标题：</td>
				<td colspan="3">
					${questionInfo.title}
				</td>
			</tr>
			
			<tr>
			 	<td class="device_td_bg3">问题详情：</td>
				<td colspan="3" width="90%">
					${questionInfo.content}
				</td>
			</tr>
			
		 	<tr><td colspan="4" style="font-weight: bold;font-size: 14px;">方案信息</td></tr>
		 	<tr>
		 	 	<td colspan="4">
					<table class="table table-striped table-bordered table-condensed table-style" id="table" style="width:99%;">
				  		<thead>
							<tr>
								<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1"/></th>	
								<th>方案序号</th>
								<th>方案内容</th>
								<th>录入人员</th>
								<th>录入时间</th>
							</tr>
						</thead>
				  		
				  		<c:if test="${schemePageList.result[0]==null}">
				  			<tr>
								<td colspan="5">
									<div style='text-align: center;margin-right: 40px;'>
										<font color='red'>没有查到结果，请重新查询或者改变查询条件。</font>
									</div>
								</td>
							</tr>
							<c:forEach begin="1" end="9">
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
							</c:forEach>
				  		</c:if>
				  		<c:if test="${schemePageList.result[0]!=null}">
							<tbody id="tbody">
							  	<c:forEach items="${schemePageList.result}" var="questionScheme">
								  	<tr onclick="rowOnclick(this)" data="${questionScheme.id}">
								  		<td style="text-align:center;"><input type="checkbox" value="${questionScheme.id}" name="select-chk"/></td>
										<td>${questionScheme.schemeNo}</td>
										<td width="20%"><div style="height:26px;width:220px;overflow:hidden;" title="${questionScheme.content}">${questionScheme.content}</div></td>
										<td><tags:xiangxuncache keyName="username_cache" id="${questionScheme.operator}" /></td>
										<td><fmt:formatDate value="${questionScheme.insertTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>	
								  </c:forEach>
								  <c:if test="${schemePageList.result != null}">
									<c:forEach begin="1" end="${10-fn:length(schemePageList.result)}">
										<tr>
											<td colspan="5">&nbsp;</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</c:if>
						
					</table>
				</td>
			</tr>	
		   
		 </table>
		</div>
		<div class="btn_line">
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script type="text/javascript">
	function showList(){
		window.location.href = "${root}/question/info/list/${menuid}/?page=${page}&isgetsession=1";
	}
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
