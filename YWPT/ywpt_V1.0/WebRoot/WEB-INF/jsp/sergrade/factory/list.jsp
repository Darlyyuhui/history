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
		window.location.href = "${root}/sergrade/factory/info/view/" + id + "/${menuid}/?page=" + page;
	}	
</script>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function resetFunc(){
		$("#name").val("");
		$("#linkman").val("");
		$("#telphone").val("");
    }

</script>
<!-- 重置查询输入框结束 -->

<script type="text/javascript">

	function add(){
		var page = '${curPageNo}';
		 window.location.href="${root}/sergrade/factory/info/showadd/${menuid}/";
	}
	function updateById(id) {
		var page = '${curPageNo}';
		window.location.href = "${root}/sergrade/factory/info/update/" + id + "/${menuid}/?page=" + page;
	}
	
	function viewById(id) {
		var page = '${curPageNo}';
		window.location.href = "${root}/sergrade/factory/info/view/" + id + "/${menuid}/?page=" + page;
	}
</script>

<div class="conten_box">
	<div class="row-fluid search-area mar_b5" style="padding: 6px 0 4px;">
		<form method="post" name="queryForm" action="${root}/sergrade/factory/info/list/${menuid}/"  
		id="queryForm" class="form-inline" style="margin:0; padding:0 6px;">
			<table width="100%">
				<tr>
					<td valign="top">
						<div style="margin-top:3px;">
							<table>
								<tr><td style="height:5px; "></td></tr>
								<tr>
									<td style="width:80px; text-align:center;">公司名称：</td>
									<td>
										<input style="width:145px;" type="text" id="name" value="${name}" name="search_name">
									</td>
										
									<td style="width:80px; text-align:center;">联系人：</td>
									<td>
										<input style="width:145px;" type="text" id="linkman" value="${linkman}" name="search_linkman">
									</td>
									
									<td style="width:80px; text-align:center;">联系电话：</td>
									<td>
										<input style="width:145px;" type="text" id="telphone" value="${telphone}" name="search_telphone">
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
						<th>公司名称</th>
						<th>联系人</th>
						<th>联系电话</th>
						<th width="150">星级</th>
						<th>备注</th>
			
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
							<td colspan="10">&nbsp;</td>
						</tr>
					</c:forEach>
		  		</c:if>
		  		<c:if test="${pageList.result[0]!=null}">
					<tbody id="tbody">
					  	<c:forEach items="${pageList.result}" var="factoryInfo">
						  	<tr onclick="rowOnclick(this)" data="${factoryInfo.id}">
						  		<td style="text-align:center;"><input type="checkbox" value="${factoryInfo.id}" name="select-chk"/></td>
								<td>${factoryInfo.name}</td>
								<td>${factoryInfo.linkman}</td>
								<td>${factoryInfo.telphone}</td>
								<c:if test="${factoryInfo.level == '1'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
									</td>
								</c:if>
								<c:if test="${factoryInfo.level == '2'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
									</td>
								</c:if>
								<c:if test="${factoryInfo.level == '3'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
									</td>
								</c:if>
								<c:if test="${factoryInfo.level == '4'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
									</td>
								</c:if>
								<c:if test="${factoryInfo.level == '5'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
									</td>
								</c:if>
								
								<c:if test="${factoryInfo.level == ''}">
									<td>&nbsp;</td>
								</c:if>
								
								<td width="20%"><div style="height:16px;width:220px;overflow:hidden;" title="${factoryInfo.remark}">${factoryInfo.remark}</div></td>
								
								<td class="center" style="vertical-align: middle;width:140px;">
								    <btn:authorBtn menuid="${menuid}" text="查看">
										<a  href="javascript:viewById('${factoryInfo.id}')">
											<i class="icon-eye-open"></i>查看                                           
										</a>
									</btn:authorBtn>
									<btn:authorBtn menuid="${menuid}" text="修改">
										<a  href="javascript:updateById('${factoryInfo.id}')">
											<i class="icon-edit"></i>修改                                           
										</a>
									</btn:authorBtn>
									<btn:authorBtn menuid="${menuid}" text="删除">
										<a  href="javascript:delById('${factoryInfo.id}')">
											<i class="icon-remove"></i>删除
										</a>
									</btn:authorBtn>
									
								</td>
							</tr>	
						  </c:forEach>
						  <c:if test="${pageList.result != null}">
							<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
								<tr>
									<td colspan="10">&nbsp;</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</c:if>
			</table>	
		
			<tags:paginationnew page="${pageList}" pageaction="${root}/sergrade/factory/info/list/${menuid}/" ></tags:paginationnew>
		</div>
	</div>
</div>

<!-- 报废窗口结束 -->
<script type="text/javascript">
	function removea(){
		debugger;
		var ids = getSelectedValue();
		if(ids.length==0){
		   showMessage("请选择要删除的记录。");
		}else{
		   delById(ids);
		}
	}
	
	function delById(ids){
		if(confirm("删除后数据将无法恢复，确定要删除吗？")){
   			var url="${root}/sergrade/factory/info/delete/"+ids+"/";
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
		window.location.href = "${root}/sergrade/factory/info/list/${menuid}/";
    }
	
</script>
	
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
	
