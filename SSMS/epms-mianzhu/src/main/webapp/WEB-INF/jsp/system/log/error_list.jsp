<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
	<p id="alert-content" align="center"></p>
</div>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
		$("#ip").val("");
        $("#operator").val("");
        $("#beginDate").val("");
        $("#endDate").val("");
    }
</script>
<div class="page-header">
    <h1>
      异常日志
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            记录系统运行异常日志
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/system/log/errorlist/${menuid}" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="input-group">
                    <input type="text" class="form-control search-query" id="operator" name="search_operator" value="${log.operator}"  value="${log.operator}" placeholder="操作员名称" />
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-purple btn-sm" style="margin-left:1px;">
                            查询
                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                        </button>
                    </span>
                </div>
            </div>
        </div>
    </form>
</div>
	<div class="clear"></div>
				<table class="table table-striped table-bordered table-style text-center" id="table">
					<thead>
						<tr>
							<th>类名</th>
							<th>方法名</th>
							<th>描述</th>
							<th>IP地址</th>
							<th>发生时间</th>
							<th>操作员</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageList.result}" var="log">
							<tr>
								<td>${log.className}</td>
								<td>${log.methodName}</td>
								<td>${log.des}</td>
								<td>${log.ip}</td>
								<td><fmt:formatDate value="${log.operateTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
								<td>${log.operator}</td>
							</tr>
						</c:forEach>
						<c:if test="${pageList.result!=null}">
							<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
								<tr height="28">

									<td colspan="9">&nbsp;</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<tags:pagination page="${pageList}"></tags:pagination>
			</div>
</div>
<!--/row-->
<script type="text/javascript">
	//-->
	function showView(id) {
		var page = '${current}';
		window.location.href = "${root}/system/log/showview/" + id+ "/${menuid}/?page=" + page;
	}
	function delById(id) {
		bootbox.confirm("删除后数据将无法恢复，确定要删除吗？",function(a) { if (!a) return;
			var url = "${root}/system/log/delete/" + ids + "/";
			$.ajax({
				type : 'delete',
				url : url,
				dataType : "json",
				success : function(msg) {
					if (msg.result == "ok") {
						$("#alert-div").removeClass("alert-danger").addClass("alert-success");
						showMessage("删除成功");
						setTimeout("document.location.reload()", 1600);
					} else {
						showMessage("删除失败");
					}
				}
			});
		}
	}
</script>
