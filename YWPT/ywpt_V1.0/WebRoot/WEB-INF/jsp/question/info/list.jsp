<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
  <p id="alert-content" align="center"></p>
</div>
<div class="alert alert-block pull-top alert-success" id="alertsuc-div" style="display:none;">
  <p id="alertsuc-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p id="mesg" align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<script type="text/javascript">
	function viewById(id) {
		var page = '${curPageNo}';
		window.location.href = "${root}/question/info/view/" + id + "/${menuid}/?page=" + page;
	}	
</script>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function resetFunc(){
		$("#title").val("");
		$("#operator").val("");
		$("#beginDate").val("");
		$("#endDate").val("");
    }

</script>
<!-- 重置查询输入框结束 -->

<script type="text/javascript">

	function add(){
		var page = '${curPageNo}';
		 window.location.href="${root}/question/info/showadd/${menuid}/";
	}
	function updateById(id) {
		var page = '${curPageNo}';
		window.location.href = "${root}/question/info/update/" + id + "/${menuid}/?page=" + page;
	}
	
	function viewById(id) {
		var page = '${curPageNo}';
		window.location.href = "${root}/question/info/view/" + id + "/${menuid}/?page=" + page;
	}
	
	function schemeById(id) {
		var page = '${curPageNo}';
		window.location.href = "${root}/question/info/scheme/" + id + "/${menuid}/?page=" + page;
	}
</script>

<div class="conten_box">
	<div class="row-fluid search-area mar_b5" style="padding: 6px 0 4px;">
		<form method="post" name="queryForm" action="${root}/question/info/list/${menuid}/"  
		id="queryForm" class="form-inline" style="margin:0; padding:0 6px;">
			<table width="100%">
				<tr>
					<td valign="top">
						<div style="margin-top:3px;">
							<table>
								<tr><td style="height:5px; "></td></tr>
								<tr>
									<td style="width:80px; text-align:center;">录入人员：</td>
									<td>
										<input style="width:145px;" type="text" id="operator" value="${operator}" name="search_operator">
									</td>
									
									<td style="width:80px; text-align:center;">问题标题：</td>
									<td>
										<input style="width:145px;" type="text" id="title" value="${title}" name="search_title">
									</td>
										
									<td class="search_item_td" > 录入时间：</td>
								    <td>
									  	<input type="text" name="search_beginDate" class="Wdate required" 
									  		onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:document.getElementById('endDate').value==''?'%y-%M-%d %H-%m-%s':document.getElementById('endDate').value})" 
									  		id="beginDate" value="${beginDate}" readonly="readonly" style="width:145px;" /> 
									</td>
									<td style="text-align: center;">  		
									  	&nbsp;至&nbsp; 
									</td>
									<td>  	
									  	<input type="text" class="Wdate required" id="endDate" name="search_endDate" 
									  	onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:document.getElementById('beginDate').value})" 
									  	value="${endDate}" readonly="readonly" style="width:145px;" />
									</td>
									
									<td style="min-width:140px;">	
										<input class="btn btn-info" type="submit" value="查 询"/>
										<input class="btn mar_l10"  type="button"  onclick="resetFunc();" value="重 置"/>
									</td>
								</tr>
							</table>
							
							<div class="clear"></div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div class="mar_5"">
		<div class="btn-group-wrap mar_b5" align="right">
			<div class="btn-group">
				<btn:authorBtn menuid="${menuid}" text="添加">
					<button class="btn btn-small" onclick="add();"><i class="icon-plus"></i>添加</button>
				</btn:authorBtn>
				
				<btn:authorBtn menuid="${menuid}" text="删除">
					<button class="btn btn-small" onclick="removea();"><i class="icon-remove"></i>删除</button>
			  	</btn:authorBtn>
			</div>
		</div>
		<div class="mar_b5" style="overflow:auto;">
		
			<table class="table table-striped table-bordered table-condensed table-style" id="table">
		  		<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1"/></th>	
						<th>问题标题</th>
						<th>问题详情</th>
						<th>录入人员</th>
						<th>录入时间</th>
			
						<th>操作</th>
					</tr>
				 </thead>
		  		 <c:if test="${pageList.result[0]==null}">
		  			<tr>
						<td colspan="9">
							<div style='text-align: center;margin-right: 40px;'>
								<font color='red'>没有查到结果，请重新查询或者改变查询条件。</font>
							</div>
						</td>
					</tr>
					<c:forEach begin="1" end="14">
						<tr>
							<td colspan="9">&nbsp;</td>
						</tr>
					</c:forEach>
		  		</c:if>
		  		<c:if test="${pageList.result[0]!=null}">
					<tbody id="tbody">
					  	<c:forEach items="${pageList.result}" var="questionInfo">
						  	<tr onclick="rowOnclick(this)" data="${questionInfo.id}">
						  		<td style="text-align:center;"><input type="checkbox" value="${questionInfo.id}" name="select-chk"/></td>
								<td width="20%"><div style="height:16px;width:220px;overflow:hidden;" title="${questionInfo.title}">${questionInfo.title}</div></td>
								<td width="20%"><div style="height:16px;width:220px;overflow:hidden;" title="${questionInfo.content}">${questionInfo.content}</div></td>
								<td><tags:xiangxuncache keyName="username_cache" id="${questionInfo.operator}" /></td>
								<td><fmt:formatDate value="${questionInfo.insertTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="center" style="vertical-align: middle;width:140px;">
								    <btn:authorBtn menuid="${menuid}" text="查看">
										<a  href="javascript:viewById('${questionInfo.id}')">
											<i class="icon-eye-open"></i>查看                                           
										</a>
									</btn:authorBtn>
									<btn:authorBtn menuid="${menuid}" text="修改">
										<a  href="javascript:updateById('${questionInfo.id}')">
											<i class="icon-edit"></i>修改                                           
										</a>
									</btn:authorBtn>
									<btn:authorBtn menuid="${menuid}" text="删除">
										<a  href="javascript:delById('${questionInfo.id}')">
											<i class="icon-remove"></i>删除
										</a>
									</btn:authorBtn>
									<btn:authorBtn menuid="${menuid}" text="方案管理">
										<a  href="javascript:schemeById('${questionInfo.id}')">
											<i class="icon-resize-small"></i>方案管理
										</a>
									</btn:authorBtn>
								</td>
							</tr>	
						  </c:forEach>
						  <c:if test="${pageList.result != null}">
							<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
								<tr>
									<td colspan="6">&nbsp;</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</c:if>
			</table>	
		
			<tags:paginationnew page="${pageList}" pageaction="${root}/question/info/list/${menuid}/" ></tags:paginationnew>
		</div>
	</div>
</div>

<!-- 报废窗口结束 -->
<script type="text/javascript">
	function removea(){
		var ids = getSelectedValue();
		if(ids.length==0){
		   showMessage("请选择要删除的记录。");
		}else{
		   delById(ids);
		}
	}
	
	function delById(ids){
		if(confirm("删除后数据将无法恢复，确定要删除吗？")){
   			var url="${root}/question/info/delete/"+ids+"/";
			$.ajax( {
				type : 'delete',
				url : url,
				dataType : "json",
				success:function(msg){
					if(msg.result=="ok"){
		   				 showSucMessage("删除成功");
		   				 setTimeout("showList()", 1800);
		   			}else{
		   				 showMessage("删除失败");
		   			}
				}
			});
		}
		
	}
	
	function showList(){
		window.location.href = "${root}/question/info/list/${menuid}/";
    }
	
</script>
	
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
	
