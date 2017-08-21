<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none">
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
		$("#name").val("");
	    $("#ftpip").val("");
	    $("#ftpport").val("");
	    $("#ftpuser").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/property/deviceftp/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="td40">服务器名称</td>
			    <td><input title="支持 服务器名称 模糊查询" style="width:90%;max-width:144px;" id="name" name="search_name" value="${ftp.name}" maxlength="30" type="text" placeholder="服务器名称" /></td>
			    <td class="td96">服务器IP</td>
			    <td><input title="支持 服务器IP 模糊查询" style="width:90%;max-width:144px;" id="ftpip" name="search_ftpip" value="${ftp.ftpip}" maxlength="15" type="text" placeholder="服务器IP" /></td>
			    <td class="td54">上行端口</td>
			    <td><input style="width:90%;max-width:144px;" id="ftpport" name="search_ftpport" value="${ftp.ftpport}" maxlength="5" type="text" placeholder="上行端口" /></td>
			    <td class="td80">上行账号</td>
			    <td><input class="input" style="width:90%;max-width:144px;" id="ftpuser" name="search_ftpuser" value="${ftp.ftpuser}" maxlength="10" type="text" placeholder="上行账号" /></td>
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info pull-left mar_l10" value="查询" style="height:28px;"/>
			      <input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="table_box">
    	<div class="btn-group-wrap mar_b5">
		  <div class="btn-group pull-right">
			<btn:authorBtn menuid="${menuid}" text="添加">
			<button class="btn btn-small" onclick="addFtp();">
				<i class="icon-plus"></i>添加
			</button>
		    </btn:authorBtn>
		    <btn:authorBtn menuid="${menuid}" text="删除">
			<button class="btn btn-small" onclick="removeFtp();">
				<i class="icon-remove"></i>删除
			</button>
		    </btn:authorBtn>
		  </div>
		  <div class="clear"></div>
	  </div>
	  <div style="overflow: auto;">
		  <table class="table table-striped table-bordered table-condensed table-style" id="table">
				<thead>
					<tr>
						<th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1" /></th>
						<th>服务器名称</th>
						<th>服务器IP</th>
						<th>上行端口</th>
						<th>上行账号</th>
						<th>上行密码</th>
						<th>下行端口</th>
						<th>下行虚拟目录</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="ftpinfo">
						<tr onclick="rowOnclick(this)" data="${ftpinfo.id}">
							<td style="text-align:center;"><input type="checkbox" value="${ftpinfo.id}" name="select-chk" /></td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${ftpinfo.name}">${ftpinfo.name}</div></td>
							<td>${ftpinfo.ftpip}</td>
							<td>${ftpinfo.ftpport}</td>
							<td>${ftpinfo.ftpuser}</td>
							<td>${ftpinfo.ftppassword}</td>
							<td>${ftpinfo.httpport}</td>
							<td>${ftpinfo.dirname}</td>
							<td class="center">
							<btn:authorBtn menuid="${menuid}" text="查看">
								<a href="javascript:viewById('${ftpinfo.id}')"> <i class="icon-eye-open"></i>查看</a>
							</btn:authorBtn>
							<btn:authorBtn menuid="${menuid}" text="修改">
								<a href="javascript:updateFtpById('${ftpinfo.id}')"> <i class="icon-edit"></i>修改</a>
							</btn:authorBtn> 
							<btn:authorBtn menuid="${menuid}" text="删除">
								<a href="javascript:delFtpById('${ftpinfo.id}')"> <i class="icon-remove"></i>删除</a>
							</btn:authorBtn></td>
						</tr>
					</c:forEach>
					<c:if test="${pageList.result!=null}">
						<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
							<tr>
								<td colspan="10">&nbsp;</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<tags:pagination page="${pageList}"></tags:pagination>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/property/deviceftp/list/${menuid}/?page=${current}&isgetsession=1";
	}

	function addFtp() {
		var page='${current}';
		window.location.href = "${root}/forward/property/deviceftp/add/?menuid=${menuid}&page=" + page;
	}

	function updateFtpById(id) {
		var page = '${current}';
		window.location.href = "${root}/property/deviceftp/update/" + id
				+ "/${menuid}/?page=" + page;
	}
	
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/property/deviceftp/view/"+id+"/${menuid}/?page="+page;
	}

	function removeFtp() {
		var ids = getSelectedValue();
		if (ids.length == 0) {
			showMessage("请选择要删除的记录。");
		} else {
			delFtpById(ids);
		}
	}

	function delFtpById(ids) {
		if (confirm("删除后数据将无法恢复，确定要删除吗？")) {
			var url = "${root}/property/deviceftp/delete/" + ids + "/";
			$.ajax({
				type : 'delete',
				url : url,
				dataType : "json",
				success : function(msg) {
					if (msg.result == "ok") {
						$("#alert-div").removeClass("alert-error").addClass("alert-success");
						showMessage("删除成功");
						setTimeout("showList()", 1600);
					} else {
						showMessage("删除失败");
					}
				}
			});
		}
	}

	$(document).ready(function() {
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
	});
//-->
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>