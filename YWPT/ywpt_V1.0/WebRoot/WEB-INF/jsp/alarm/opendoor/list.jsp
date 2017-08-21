<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>


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
		<form id="inputForm" class="form-inline" action="${root}/alarm/opendoor/list/${menuid}/" method="post" style="margin:0;padding:0 10px 0 5px;">
			<table width="100%" border="0">
			  <tr>
			    <td class="search_item_td">设备名称</td>
			    <td><input maxlength="30" title="支持 设备名称 模糊查询" style="width:90%;" id="name" name="assetname" value="${assetname}" type="text" placeholder="设备名称" /></td>
			    <td class="search_item_td">IP地址</td>
			    <td><input maxlength="20" title="支持 IP地址 模糊查询" style="width:90%;" id="contactperson" name="ip" value="${ip}" type="text" placeholder="IP地址" /></td>
			    <td class="search_item_td">安装地点</td>
			    <td><input maxlength="13" title="支持 安装地点模糊查询" style="width:90%;" id="contactphone" name="installplace" value="${installplace}" type="text" placeholder="安装地点" /></td>
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info mar_l10" value="查询" style="height:28px;"/>
			      <input onclick="reValues();" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="table_box">
		
		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			<thead>
				<tr>
					 <th>设备名称</th>
					 <th>设备IP</th>
					 <th>安装地址</th>
					 <th>报警时间</th>
					 <th>图片</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pageList.result}" var="ica">
				<tr data="${ica.id}" onclick="rowOnclick(this)" >
				    <td>${ica.assetinfo.assetname}</td>
					<td>${ica.assetinfo.ip}</td>
					<td>${ica.assetinfo.installplace}</td>
					<td><fmt:formatDate value="${ica.eventTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="center">
					
					    <btn:authorBtn menuid="${menuid}" text="查看">
						    <a href="javascript:viewById('${ica.id}')">
						       <i class="icon-eye-open"></i>查看</a>
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
	
	<div style="height:450px;width:850px" class="modal hide fade" id="aa" tabindex="-1" role="dialog" >
	
	 
   
	<div class="modal-body">
	 
	    <table>
	     <tr>
	        <td>摄像头1</td>
	        <td>摄像头2</td>
	     </tr>
	     <tr>
	        <td><div style="height:400px;width:400px"id="img1"></div></td>
	        <td><div style="height:400px;width:400px" id="img2"></div></td>
	     </tr>
      	</table>
      	 	
	</div>
	</div>
	
</div>
<script type="text/javascript">
function viewById(id){

    $("#img1").html("<img src='${root}/alarm/opendoor/view/${menuid}?photoNo=1&id="+id+"'/>");
	$("#img2").html("<img src='${root}/alarm/opendoor/view/${menuid}?photoNo=2&id="+id+"'/>");
	$("#aa" ).modal('show');

}

</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>