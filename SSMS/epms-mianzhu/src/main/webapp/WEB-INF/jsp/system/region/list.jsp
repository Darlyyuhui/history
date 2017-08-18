<%@ page language="java" pageEncoding="UTF-8" %>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css"/>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.exedit.min.js'/>" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center">${message}</p>
    </div>
    <script>
        setTimeout('$("#message").hide("slow")', 1200);    </script>
</c:if>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
    function resetValues() {
        $('#code').val('');
        $('#name').val('');
        $('#parentCode').val('');
        $('#remark').val('');

    }
</script>
<!-- 重置查询输入框结束 -->
<div class="page-header">
    <h1>
        行政区域管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            管理行政区域的基本信息
        </small>
    </h1>
</div>
<%--<div style="margin:10px 0">
    <form class="form-search" action="${root}/region/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="input-group">
                    <input type="text" class="form-control search-query" id="name" name="search_name" value="${region.name}" placeholder="编码/名称"/>
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
</div>--%>
<div class="space"></div>
<div>
    <div class="ztree_box col-xs-12" style="height: 580px;overflow: auto;">
        <!-- <div class="pull-left ztree_box" style="width:20%;height: 580px;overflow: auto;"> -->
        <ul id="tree-rec" class="ztree" style="margin-top: 5px;"></ul>
    </div>
</div>
<div id="tree-data" style="display:none;">${data}</div>
<script type="text/javascript">
    var zNodes = eval("(" + $('#tree-data').html() + ")");
    var rid = "${rid}";
    var tree;
    var ids;
    $(function () {
        var setting = {
            edit: {
                enable: true,
                showRemoveBtn: false,
                showRenameBtn: false
            },
            check: {
                enable: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
        var node = tree.getNodeByParam("id",rid, null);
        tree.expandNode(node, true, false, false);
    });
</script>
