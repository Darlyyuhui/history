<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none">
	<p id="alert-content" align="center"></p>
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
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
	    $("#name").val("");
		$("#contactperson").val("");
		$("#contactphone").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">
		<form id="inputForm" class="form-inline" action="${root}/property/devicecompany/list/${menuid}/" method="post" style="margin:0;padding:0 10px 0 5px;">
			<table width="100%" border="0">
			  <tr>
			    <td class="search_item_td">设备厂商</td>
			    <td><input maxlength="30" title="支持 设备厂商 模糊查询" style="width:90%;" id="name" name="search_name" value="${deviceCompanyInfo.name}" type="text" placeholder="设备厂商" /></td>
			    <td class="search_item_td">联系人</td>
			    <td><input maxlength="20" title="支持 联系人 模糊查询" style="width:90%;" id="contactperson" name="search_contactperson" value="${deviceCompanyInfo.contactperson}" type="text" placeholder="联系人" /></td>
			    <td class="search_item_td">联系电话</td>
			    <td><input maxlength="13" title="支持 联系电话 模糊查询" style="width:90%;" id="contactphone" name="search_contactphone" value="${deviceCompanyInfo.contactphone}" type="text" placeholder="联系电话" /></td>
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info mar_l10" value="查询" style="height:28px;"/>
			      <input onclick="reValues();" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="table_box">
		<div class="btn-group-wrap mar_b5">
			<div class="btn-group pull-right">
			  <btn:authorBtn menuid="${menuid}" text="添加">
				<button class="btn btn-small" onclick="add();"><i class="icon-plus"></i>添加</button>
			  </btn:authorBtn>
			  <btn:authorBtn menuid="${menuid}" text="删除">
				<button class="btn btn-small" onclick="removea();"><i class="icon-remove"></i>删除</button>
			  </btn:authorBtn>
			</div>
			<div class="clear"></div>
		</div>
		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			<thead>
				<tr>
					 <th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1"/></th>	
					 <th>设备厂商</th>
					 <th>接入KEY</th>
					 <th>联系人</th>
					 <th>联系电话</th>
					 <th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pageList.result}" var="companyInfo">
				<tr data="${companyInfo.id}">
					<td style="text-align:center;"><input type="checkbox" value="${companyInfo.id}" name="select-chk"/></td>
					<td>${companyInfo.name}</td>
					<td>${companyInfo.datekey}</td>
					<td>${companyInfo.contactperson}</td>
					<td>${companyInfo.contactphone}</td>
					<td class="center">
					    <btn:authorBtn menuid="${menuid}" text="查看">
						    <a href="javascript:viewById('${companyInfo.id}')"> <i class="icon-eye-open"></i>查看</a>
					    </btn:authorBtn>
					    <btn:authorBtn menuid="${menuid}" text="修改">
							<a  href="javascript:updateById('${companyInfo.id}')">
								<i class="icon-edit"></i>修改                                           
							</a>
						</btn:authorBtn>
						<btn:authorBtn menuid="${menuid}" text="删除">
							<a  href="javascript:delById('${companyInfo.id}')">
								<i class="icon-remove"></i>删除
							</a>
						</btn:authorBtn>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${pageList.result!=null}">
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
					<tr>
						<td colspan="8">&nbsp;</td>
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
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/property/devicecompany/list/${menuid}/?page=${current}&isgetsession=1";
	}
	
	function add(){
	    window.location.href="${root}/forward/property/devicecompany/add/?menuid=${menuid}";
	}
	
	function updateById(id){
		var page='${current}';
		window.location.href="${root}/property/devicecompany/update/"+id+"/${menuid}/?page="+page;
	}
	
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/property/devicecompany/view/"+id+"/${menuid}/?page="+page;
	}
	
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
   			var url="${root}/property/devicecompany/delete/"+ids+"/";
			$.ajax( {
				type : 'delete',
				url : url,
				dataType : "json",
				success:function(msg){
					if(msg.result=="ok"){
		   				 $("#alert-div").removeClass("alert-error").addClass("alert-success");
						showMessage("删除成功");
						setTimeout("showList()", 1600);
		   			}else{
		   				 showMessage("删除失败");
		   			}
				}
			});
		   }
	}
	
 $(document).ready(function(){
    $("#device-content").width($('body').width())
    $("#alert-div").hide();
    //为inputForm注册validate函数
	$("#inputForm").validate();
 });
	
//-->
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>