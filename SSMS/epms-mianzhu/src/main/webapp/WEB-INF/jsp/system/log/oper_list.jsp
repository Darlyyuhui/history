<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display: none">
	<p id="alert-content" align="center"></p>
</div>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues() {
		$("#ip").val("");
		$("#operator").val("");
		$("#beginDate").val("");
		$("#endDate").val("");
	}
</script>
<div class="page-header">
    <h1>
            操作日志
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            记录系统用户全部的操作日志
        </small>
    </h1>
</div>

<div style="margin:10px 0">
    <form class="form-search" action="${root}/system/log/operlist/${menuid}" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="input-group">
                    <input type="text" class="form-control search-query" id="operator" name="search_operator" value="${log.operator}" placeholder="操作员名称" />
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
<!-- 
					  <input class="Wdate" style="width:90%;" name="search_startTime" 
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:document.getElementById('endDate').value})" 
						id="beginDate" value="${log.startTime}" type="text" placeholder="开始日期" />					  
					  <input class="Wdate" style="width:90%;" name="search_endtime" value="${log.endtime}" id="endDate"  type="text" 
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:'%y-%M-%d %H-%m-%s',minDate:document.getElementById('beginDate').value})"placeholder="结束日期" />
					  <input style="width:90%;" id="ip" name="search_ip" value="${log.ip}" type="text" placeholder="IP地址" />
					  <input style="width:80%;" id="operator" name="search_operator" value="${log.operator}" type="text" placeholder="操作员" />
					    <input type="submit" class="btn btn-info mar_l10" value="查询" style="height: 28px;" />
						<input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height: 28px;" />
						 -->
				<table class="table table-striped table-bordered table-style text-center" id="table">
					<thead>
						<tr>
							<th>所属模块</th>
							<th>描述</th>
							<th>IP地址</th>
							<th>发生时间</th>
							<th>是否成功</th>
							<th>操作员</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageList.result}" var="log">
							<tr>
								<td>${log.moduleName}</td>
								<td>${log.des}</td>
								<td>${log.ip}</td>
								<td><fmt:formatDate value="${log.operateTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
								<td>${log.isSuccess eq '1'?'成功':'失败'}</td>
								<td>${log.operator}</td>
							</tr>
						</c:forEach>
						<c:if test="${pageList.result!=null}">
							<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
								<tr>
									<td colspan="9">&nbsp;</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<tags:pagination page="${pageList}"></tags:pagination>
	</div>
</div>
<script type="text/javascript">
function showViewById(id){
	window.location.href="${root}/system/log/showOperview/"+id+"/${menuid}/";
}
</script>

