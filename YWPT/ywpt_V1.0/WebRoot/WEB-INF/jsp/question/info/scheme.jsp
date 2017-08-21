<%@ page language="java" pageEncoding="UTF-8"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
	function checkForm(){
		var checkFlag = true;
		
		var i = parseInt($("#num").val());
		$("textarea[name='workContent']").each(function() {
			if (this.value == "") {
				i++;
			} else if(this.value.length > 500){
				alert("填写方案内容长度不能超过500字，请重新填写后再添加新方案项。");
				checkFlag = false; 
			} 
		});
		if (i > parseInt($("#num").val())) {
			alert("请填写方案内容。");
			checkFlag = false;
		}

		return checkFlag;
	}
	function addRow() {
		debugger;
		var lastnum = $("input[name='workNo']").last().val();
		var num = parseInt(lastnum.substring(2,lastnum.length));
		if($("textarea[name='workContent']").last().val() == "") {
			alert("请填写方案内容后再添加新方案项。");
			return;
		}else if($("textarea[name='workContent']").last().val().length > 500){
			alert("填写方案内容长度不能超过500字，请重新填写后再添加新方案项。");
			return;
		}
		num++;
		var html = "<tr id='workContent"+num+"'>";
		html += "<td><input type='hidden' name='schemeId' value='"+num+"'/>";
		html += "<input type='text' name='workNo' value='方案"+num+"' maxlength='15' readonly='readonly'/></td>";
		html += "<td><textarea rows='4' maxlength='500' style='width:70%;' class='span8' name='workContent' ></textarea><font color='red' style='margin-left: 5px;'>*</font></td>";
		html += "<td><a href=\"javascript:removeRow('workContent"+num+"')\"><i class='icon-remove'></i>删除</a></td></tr>";
		$("#table1").append(html);
		resetIframeHeight($("#workContent"+num).height());
		
	}
	function removeRow(rowId) {
		if (confirm("确定要删除该行吗？")) {
			$("#table1").find("tr[id='"+rowId+"']").remove();
		}
		resetIframeHeight($("#workContent"+num).height());
	}
	$(function(){
		$("#table1").append(html);
		resetIframeHeight($("#table1").height());
	});
</script>

<div class="conten_box" style="overflow:hidden;">
	<form id="inputForm" class="form-inline" action="${root}/question/info/doScheme" method="post" style="margin:0;">
		<h4 class="xtcs_h4" style="margin:0;">问题录入--方案管理</h4>
		<input type="hidden" name="menuid" value="${menuid}" />
		<input type="hidden" name="page" value="${page}" />
		<input type="hidden" id="num" value="${num}" />
		<input type="hidden" name="infoId" value="${questionInfo.id}" />
		<div class="mar_5">
		  <table class="table table-border-rl table-border-bot bukong-table">
		  	<tr><td colspan="4" style="font-weight: bold;font-size: 14px;">问题信息</td></tr>
			<tr>
				<td class="device_td_bg3" width="10%">录入人员：</td>
				<td width="40%">
					<tags:xiangxuncache keyName="username_cache" id="${questionInfo.operator}" />
				</td>
				
				<td class="device_td_bg3" width="10%">录入时间：</td>
				<td width="40%">
					<fmt:formatDate value="${questionInfo.insertTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>		
				
			</tr>
			
			<tr>
				<td class="device_td_bg3" width="10%">问题标题：</td>
				<td colspan="3" width="90%">
					${questionInfo.title}
				</td>
				
			</tr>
			
			<tr>
			 	<td class="device_td_bg3" width="10%">问题详情：</td>
				<td colspan="3" width="90%">
					${questionInfo.content}
				</td>
			</tr>
			
		  </table>
		</div>
		
		<div class="conten_box mar_5" style="margin-bottom: 15px;">
		    <h4 class="xtcs_h4">方案内容 <a class="btn btn-small h4_btn" onclick="addRow()"><i class="icon-plus"></i>添加方案</a></h4>
		    <div class="mar_5">
		  	  <table class="table table-striped table-bordered table-condensed table-style" id="table1">
		        <thead>
					<tr>
						<th width="200">序号</th>
						<th>方案内容</th>
						<th width="50">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${schemePageList.result[0]==null}">
						<tr>
							<td><input type="text" name="workNo" value="方案1" maxlength="16" readonly="readonly"/></td>
							<td>
								<textarea rows="4" maxlength="500" style="width:70%;" 
								class="span8" name="workContent" ></textarea>
								<font color="red" style="margin-left: 5px;">*</font>
							</td>
							<td>&nbsp;</td>
						</tr>
					</c:if>
					<c:if test="${schemePageList.result[0]!=null}">
						<c:forEach items="${schemePageList.result}" var="questionScheme" varStatus="k">
							<tr id="workContent${k.index+1}">
								<td>
									<input type="hidden" name="schemeId" value="${questionScheme.id}"/>
									<input type="text" name="workNo" value="${questionScheme.schemeNo}" maxlength="16" readonly="readonly"/>
								</td>
								<td>
									<textarea rows="4" maxlength="500" style="width:70%;" 
									class="span8" name="workContent" >${questionScheme.content}</textarea>
									<font color="red" style="margin-left: 5px;">*</font>
								</td>
								<td><a href="javascript:removeRow('workContent${k.index+1}')"><i class='icon-remove'></i>删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
					
				</tbody>
		      </table>
		    </div>
		</div>
		
		<div class="btn_line">
			<button class="btn btn-info mar_r10" onclick="javascript:return checkForm();" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="javascript:history.go(-1);" />
		</div>
	</form>
</div>
<script type="text/javascript">
	function showList(){
		window.location.href = "${root}/question/info/list/${menuid}/";
	}
	$(document).ready(function() {
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});	
</script>

<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
