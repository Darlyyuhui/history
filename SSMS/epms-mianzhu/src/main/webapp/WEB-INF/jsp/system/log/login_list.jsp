<%@ page language="java" pageEncoding="UTF-8" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display: none">
    <p id="alert-content" align="center"></p>
</div>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
    function reValues() {
        $("#operatorId").val("");
        $("#beginDate").val("");
        $("#endDate").val("");
    }
</script>

<div class="page-header">
    <h1>
        登陆日志
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            查看登陆日志
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <!-- 重置查询输入框结束 -->
    <form class="form-search" action="${root}/system/log/loginlist/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="input-group">
                    <input type="text" class="form-control search-query" name="search_operatorName"
                           value="${log.operatorName}" placeholder="操作员姓名"/>
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
<div class="row">
    <div class="col-xs-12">
<table class="table table-striped table-bordered table-hover" id="table">
    <thead>
    <tr>
        <th>账号</th>
        <th>操作员姓名</th>
        <th>IP地址</th>
        <th>类型</th>
        <th>发生时间</th>
        <th>内容</th>
    </tr>
    </thead>
    <tbody id="tbody">
    <c:forEach items="${pageList.result}" var="log">
        <tr>
            <td>${log.operatorId}</td>
            <td>${log.operatorName}</td>
            <td>${log.ipAddress}</td>
            <td>${log.type==1?"登录":'退出'}</td>
            <td><fmt:formatDate value="${log.operationTime}" pattern="yyyy-MM-dd HH:mm:SS"/></td>
            <td class="texthidden">${log.content}</td>
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
<tags:pagination page="${pageList}"></tags:pagination>
</div>
</div>
<script type="text/javascript">
    function showViewById(id) {
        window.location.href = "${root}/system/log/showloginview/" + id + "/${menuid}";
    }
</script>