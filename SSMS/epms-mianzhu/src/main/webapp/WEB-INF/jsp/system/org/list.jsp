<%@ page language="java" pageEncoding="UTF-8" %>
<link href="${root}/compnents/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css"/>
<script src="${root}/compnents/treeTable/jquery.treeTable.depart.js" type="text/javascript"></script>
<div class="alert alert-block pull-top  alert-danger" id="alert-div" style="display: none">
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

<div class="page-header">
    <h1>
        组织机构
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            组织机构进行管理
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/system/org/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="input-group">
                    <input type="text" class="form-control search-query" id="name" name="search_name" value="${name}"
                           placeholder="机构名称"/>
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
        <table id="tree-table" class="table table-striped table-bordered  table-condensed table-style text-center" width="100%">
            <thead>
            <tr>
                <th>名称</th>
                <th class="hidden-480">编码</th>
                <th class="hidden-480">责任人</th>
                <th class="hidden-480">联系方式</th>
                <th class="hidden-480">备注</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="depart">
                <tr id="${depart.id}" pId="${depart.parentid}" level="${depart.levels}">
                    <td>${depart.name}</td>
                    <td class="hidden-480">${depart.code}</td>
                    <td class="hidden-480">${depart.principalname}</td>
                    <td class="hidden-480">${depart.mobile}</td>
                    <td class="hidden-480">
                        <div style="width:100px;height:22px;margin-top:2px;white-space:nowrap;overflow:hidden;-o-text-overflow:ellipsis;text-overflow:ellipsis;"
                             title="${depart.memo}">${depart.memo}</div>
                    </td>
                    <td>

                        <div class="hidden-sm hidden-xs btn-group">
                            <btn:authorBtn menuid="${menuid}" text="修改">
                                <button class="btn btn-xs btn-info" onclick="update('${depart.id}')" title="修改">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <c:if test="${depart.id ne '00'}">
	                            <btn:authorBtn menuid="${menuid}" text="删除">
	                                <button class="btn btn-xs btn-danger" onclick="delNode('${depart.id}')" title="删除">
	                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
	                                </button>
	                            </btn:authorBtn>
							</c:if>
                            <btn:authorBtn menuid="${menuid}" text="添加">
                                <button class="btn btn-xs btn-purple" onclick="addChild('${depart.id}')" title="添加子机构">
                                    <i class="ace-icon fa fa-fire bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                        </div>

                        <div class="hidden-md hidden-lg">
                            <div class="inline position-relative">
                                <button class="btn btn-minier btn-primary dropdown-toggle"
                                        data-toggle="dropdown" data-position="auto">
                                    <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                </button>

                                <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                    <btn:authorBtn menuid="${menuid}" text="修改">
                                        <li>
                                            <a href="javascript:update('${depart.id}')" class="tooltip-success"
                                               data-rel="tooltip"
                                               title="Edit"> <span class="green"> <i
                                                    class="ace-icon fa fa-pencil-square-o bigger-120"></i>修改</span> </a>
                                        </li>
                                    </btn:authorBtn>
                                    <btn:authorBtn menuid="${menuid}" text="添加">
                                        <li>
                                            <a href="javascript:addChild('${depart.id}');" class="tooltip-error" data-rel="tooltip"
                                               title="Delete"> <span class="green"> <i
                                                    class="ace-icon fa fa-fire bigger-120"></i>添加子机构</span>
                                            </a>
                                        </li>
                                    </btn:authorBtn>
                                      <c:if test="${depart.id ne '00'}">
	                                    <btn:authorBtn menuid="${menuid}" text="删除">
	                                        <li>
	                                            <a href="javascript:delNode('${depart.id}')" class="tooltip-error"
	                                               data-rel="tooltip"
	                                               title="Delete"> <span class="red"> <i
	                                                    class="ace-icon fa fa-trash-o bigger-120"></i>删除</span>
	                                            </a>
	                                        </li>
	                                    </btn:authorBtn>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <input type="hidden" id="menuid" value="${menuid}"/>
</div>
<script type="text/javascript">

    $(document).ready(function () {
        $("#tree-table").treeTable({expandLevel: 2});
        //隔行换色
        $("#tree-table tbody tr").css("background", "fff");
    });

    $("#addCorp").click(function () {
        window.location.href = "${root}/system/org/addCorpor/${menuid}/";
    });

    function retsetValues() {
        $("#code").val("");
        $("#name").val("");
    }

    function update(ids){
        window.location.href="${root}/system/org/update/"+ids+"/${menuid}/";
    }

    function addChild(ids){
        window.location.href="${root}/system/org/add/"+ids+"/${menuid}/";
    }

    function delNode(ids) {

        if (ids == '00') {
            alert("根节点不能删除。");
            return;
        }
        bootbox.confirm("该机构下的子机构将会同时被删除且无法恢复，确定要删除吗？", function (a) {
            if (!a) return;
            var url = "${root}/system/org/delete/" + ids + "/";
            $.ajax({
                type: 'delete',
                url: url,
                dataType: "json",
                success: function (msg) {
                    if (msg.result == "ok") {
                        $("#alert-div").removeClass("alert-danger").addClass("alert-success");
                        showMessage("删除成功");
                        setTimeout("document.location.reload()", 1600);
                    }
                }
            });
        });
    }
</script>