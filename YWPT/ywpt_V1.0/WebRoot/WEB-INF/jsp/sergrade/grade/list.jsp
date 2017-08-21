<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<link href="${root}/compnents/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="${root}/compnents/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${root}/compnents/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
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
		window.location.href = "${root}/sergrade/grade/info/view/" + id + "/${menuid}/?page=" + page;
	}	
</script>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function resetFunc(){
		$("#code").val("");
		$("#code_chzn>a span").html("请选择");
    }

</script>
<!-- 重置查询输入框结束 -->

<script type="text/javascript">

	function add(){
		var page = '${curPageNo}';
		 window.location.href="${root}/sergrade/grade/info/showadd/${menuid}/";
	}
	function updateById(id) {
		var page = '${curPageNo}';
		window.location.href = "${root}/sergrade/grade/info/update/" + id + "/${menuid}/?page=" + page;
	}
	
	function viewById(id) {
		var page = '${curPageNo}';
		window.location.href = "${root}/sergrade/grade/info/view/" + id + "/${menuid}/?page=" + page;
	}
	
</script>

<div class="conten_box">
	<div class="row-fluid search-area mar_b5" style="padding: 6px 0 4px;">
		<form method="post" name="queryForm" action="${root}/sergrade/grade/info/list/${menuid}/"  
		id="queryForm" class="form-inline" style="margin:0; padding:0 6px;">
			<table width="100%">
				<tr>
					<td valign="top">
						<div style="margin-top:3px;">
							<table>
								<tr><td style="height:5px; "></td></tr>
								<tr>
									<td class="search_item_td">服务级别</td>
									<td><select name="search_code" style="width:180px;" id="code">
											<option value="">请选择</option>
											<c:forEach items="${gradetype}" var="keyValue">
												<option value="${keyValue.code}" ${keyValue.code==code?'selected':''}>${keyValue.name}</option>
											</c:forEach>
										</select>
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
			  	
			  	<btn:authorBtn menuid="${menuid}" text="阀值设置">
					<button class="btn btn-small" onclick="pointset();"><i class="icon-cog"></i>阀值设置</button>
			  	</btn:authorBtn>
			</div>
		</div>
		<div class="mar_b5" style="overflow:auto;">
		
			<table class="table table-striped table-bordered table-condensed table-style" id="table">
		  		<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1"/></th>	
						<th>服务级别</th>
						<th>分值范围</th>
						<th>星级</th>
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
							<td colspan="9">&nbsp;</td>
						</tr>
					</c:forEach>
		  		</c:if>
		  		<c:if test="${pageList.result[0]!=null}">
					<tbody id="tbody">
					  	<c:forEach items="${pageList.result}" var="gradeInfo">
						  	<tr onclick="rowOnclick(this)" data="${gradeInfo.id}">
						  		<td style="text-align:center;"><input type="checkbox" value="${gradeInfo.id}" name="select-chk"/></td>
								<td><tags:xiangxuncache keyName="Dic" typeCode="gradetype" id="${gradeInfo.code}" ></tags:xiangxuncache></td>
								<td>${gradeInfo.minpoint}-${gradeInfo.maxpoint}</td>
							
								<c:if test="${gradeInfo.code == '1'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
									</td>
								</c:if>
								<c:if test="${gradeInfo.code == '2'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
									</td>
								</c:if>
								<c:if test="${gradeInfo.code == '3'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
									</td>
								</c:if>
								<c:if test="${gradeInfo.code == '4'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
									</td>
								</c:if>
								<c:if test="${gradeInfo.code == '5'}">
									<td>
										<img src="${root}/images/star.png" style="width:20px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
										<img src="${root}/images/star.png" style="width:20px;margin-left:2px;">
									</td>
								</c:if>
								
								<td width="20%"><div style="height:16px;width:220px;overflow:hidden;" title="${gradeInfo.remark}">${gradeInfo.remark}</div></td>
								
								<td class="center" style="vertical-align: middle;width:140px;">
								    <btn:authorBtn menuid="${menuid}" text="查看">
										<a  href="javascript:viewById('${gradeInfo.id}')">
											<i class="icon-eye-open"></i>查看                                           
										</a>
									</btn:authorBtn>
									<btn:authorBtn menuid="${menuid}" text="修改">
										<a  href="javascript:updateById('${gradeInfo.id}')">
											<i class="icon-edit"></i>修改                                           
										</a>
									</btn:authorBtn>
									<btn:authorBtn menuid="${menuid}" text="删除">
										<a  href="javascript:delById('${gradeInfo.id}')">
											<i class="icon-remove"></i>删除
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
		
			<tags:paginationnew page="${pageList}" pageaction="${root}/sergrade/grade/info/list/${menuid}/" ></tags:paginationnew>
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
   			var url="${root}/sergrade/grade/info/delete/"+ids+"/";
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
		window.location.href = "${root}/sergrade/grade/info/list/${menuid}/";
    }
	
	
	function pointset(){
        var html='<div style="height:370px;overflow-y:auto;padding:5px;">'
               +'<form id="inputForm" class="form-inline" method="post" enctype="multipart/form-data">'
               +'<table class="table table-bordered table-striped table-style">'
               +'<tr><th>服务级别</th><th>分值范围</th></tr>'
               +'<tbody id="gradetbody"></tbody>'
               +'</table></form>'
               +'</div>';
		       var states={};
		       states.state={
		            content:html,
				    buttons: { '<font onclick="pointsetFun();"">确定</font>':1},
				    buttonsFocus: 1
		       } 
		       $.jBox.open(states,'服务级别信息', 540, '400');
		       $(".jbox-button-panel").height(30); 
		       getGradeInfo();   
   }
	
	function getGradeInfo(){
	    var url="${root}/sergrade/grade/info/showpointset/?menuid=${menuid}";
		$.ajax( {
			type : 'POST',
			url : url,
			dataType : "json",
			data:{pageNumber:0},
			success:function(data){
				if(data.data.dmlistStr!= ''){
					$("#gradetbody").html(data.data.dmlistStr);
				}
			}
		});
	 }
	
	function pointsetFun(){
		debugger;
		var num = 1;
		var checkFlag = true;
		$("input[name='code']").each(function(){
			var min = $("#minpoint"+num).val();
			var max = $("#maxpoint"+num).val();
			var nextmin = $("#minpoint"+(num+1)).val();
			var nextmax = $("#maxpoint"+(num+1)).val();
			if(min == '' || max == ''){
				alert("阀值设置不允许为空");
				checkFlag = false;
			}else if(parseInt(min) >= parseInt(max)){
				alert("最小分值只能小于最大分值");
				checkFlag = false;
			}else if(parseInt(max) > 100){
				alert("最大星级的最大分值不能大于100");
				checkFlag = false;
			}else if(parseInt(max) >= parseInt(nextmin)){
				alert("上一星级的最大分值只能小于下一星级的最小分值");
				checkFlag = false;
			}
			num++;
		});
		if(checkFlag){
			inputForm.action = "${root}/sergrade/grade/info/doPointset/?menuid=${menuid}";
			inputForm.submit();
		}	
	}
	
</script>
	
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
	
