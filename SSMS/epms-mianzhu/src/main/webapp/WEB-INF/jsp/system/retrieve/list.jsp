<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
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
	function reValues(){
        $("#beginDate").val("");
        $("#endDate").val("");
        $("#operator").val("");
        $("#operatorip").val("");
    }
    function exportXls(){
        window.location.href="/atms/system/retrieve/exportXls/?search_startTime=${retrieveInfo.startTime}&search_endTime=${retrieveInfo.endTime}&search_operator=${retrieveInfo.operator}";
    }
    
</script>
<!-- 重置查询输入框结束 -->
<div class="row-fluid">
	<div class="conten_box">
		<div class="row-fluid search-area" style="padding:6px 0 4px;">
		    <form class="form-inline" action="${root}/system/retrieve/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
				<table width="100%">
				  <tr>
				    <td class="search_item_td">开始日期</td>
				    <td width="15%"><input class="input Wdate" style="width:86%; height:18px;" name="search_startTime"
				     onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:document.getElementById('endDate').value})"
				      id="beginDate" value="${retrieveInfo.startTime}" type="text" placeholder="开始日期" /></td>
				    <td class="search_item_td">结束日期</td>
				    <td width="15%"><input class="input Wdate" style="width:86%; height:18px;"
				     name="search_endTime" value="${retrieveInfo.endTime}" id="endDate"
				     onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:'%y-%M-%d %H-%m-%s',minDate:document.getElementById('beginDate').value})"
				     type="text" placeholder="结束日期" /></td>
				    <td class="td54">操作人</td>
				    <td><input class="input-large" style="width:86%; height:18px;" id="operator" name="search_operator"
				     value="${retrieveInfo.operator}" type="text" placeholder="操作人" /></td>
				    <td class="td80">操作机器IP</td>
				    <td><input class="input-large" style="width:86%; *+width:70%; height:18px;" id="operatorip"
				     name="search_operatorip" value="${retrieveInfo.operatorip}" type="text" placeholder="操作人机器ip" /></td>
				    <td width="140">
				      <input type="submit" class="btn btn-info mar_l10 mar_r10" value="查询" style=" height:28px;" />
					  <input onclick="reValues()" type="button" class="btn" value="重置" style="height:28px;"/>
				    </td>
				  </tr>
				</table>				
			</form>
		</div>

		<div class="clear"></div>
		<div class="conten_box">
           <div class="btn-group-wrap mar_b10" style="height:30px;">
				<div class="btn-group pull-right">
				<btn:authorBtn menuid="${menuid}" text="还原">
					<button class="btn btn-sm" onclick="revertData();"><i class="icon-plus"></i>还原</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="导出">
					<button id="exportXls" class="btn btn-sm" onclick="exportXls();"><i class="icon-download"></i>导出</button>
				</btn:authorBtn>
				</div>
		    </div>
			<div class="clear"></div>
			<table class="table table-striped table-bordered table-hover table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
								<th>操作人</th>
								<th>操作机器IP</th>
								<th>删除时间</th>
								<th>删除内容</th>
								<th>来源类</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="retrieveInfo">
						<tr onclick="rowOnclick(this)">
							<td style="text-align:center;"><input type="checkbox" value="${retrieveInfo.id}" name="select-chk" /></td>
							<td>${retrieveInfo.operator}</td>
							<td>${retrieveInfo.operatorip}</td>
							<td><fmt:formatDate value="${retrieveInfo.deltime}" pattern="yyyy-MM-dd HH:mm:SS"/></td>
							<td>
  <a style="height:14px; line-height:14px; font-size:12px; color:#fff;" href="javascript.html#" class="btn btn-danger" rel="popover" title="删除内容" data-content='${retrieveInfo.content}'>详细内容</a>
							</td>
							<td>${retrieveInfo.pojoclass}</td>
						</tr>
					</c:forEach>
					<c:if test="${pageList.result!=null}">
						<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
							<tr>
								<td colspan="6">&nbsp;</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div class="row-fluid">
				<tags:pagination page="${pageList}"></tags:pagination>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	function viewById(id) {
		var page = '${current}';
		window.location.href = "${root}/system/retrieve/view/" + id + "/${menuid}/?page=" + page;
	}
	
	//数据还原
	function revertData(){
	    var ids = getSelectedValue();
		if(ids.length==0){
		   showMessage("请选择要还原的记录。");
		}else{
		   revertDataById(ids);
		}
	}
	
	function revertDataById(ids){
		if(confirm("此操作将会使选中的记录还原，确定要还原吗？")){
   			var url="${root}/system/retrieve/revert/"+ids+"/";
			$.ajax( {
				type : 'delete',
				url : url,
				dataType : "json",
				success:function(msg){
					if(msg.result=="ok"){
		   				 showMessage("还原成功");
		   				 setTimeout("document.location.reload()",1200);
		   			}else{
		   				 showMessage("还原失败");
		   			}
				}
			});
		   }
	}

	$(document).ready(function() {
		$("#role-content").width($('body').width());
		$("#alert-div").hide();
	});
//-->
</script>

