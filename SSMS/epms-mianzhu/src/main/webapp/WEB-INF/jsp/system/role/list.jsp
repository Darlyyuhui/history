<%@ page language="java" pageEncoding="UTF-8" %>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display: none">
    <p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">
            ×
        </button>
        <p align="center">
            ${message}
        </p>
    </div>
    <script>
        setTimeout('$("#message").hide("slow")', 1200);
    </script>
</c:if>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
    function reValues() {
        $("#name").val("");
    }
</script>
<style>

</style>
<div class="page-header">
    <h1>
        角色管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            对角色进行管理
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/system/role/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="input-group">
                    <input type="text" class="form-control search-query" name="search_name" value="${role.name}"
                           placeholder="角色名称"/>
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
        <btn:authorBtn menuid="${menuid}" text="添加">
            <button class="btn btn-sm btn-success pull-left" onclick="addRole()" style="margin:1px;">
                <i class="ace-icon fa fa-plus"></i>
                <span class="bigger-110">添加</span>
            </button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="删除">
            <button class="btn btn-sm btn-danger pull-left" onclick="removeRole()" style="margin:1px">
                <i class="ace-icon fa fa-trash-o icon-on-left"></i>
                <span class="bigger-110">删除</span>
            </button>
        </btn:authorBtn>
    </div>
</div>
<!-- PAGE CONTENT BEGINS -->
<div class="row">
    <div class="col-xs-12">
        <table id="table" class="table table-striped table-bordered table-hover table-style text-center">
            <thead>
            <tr>
                <th class="center" width="50">
                    <label class="position-relative">
                        <input type="checkbox" class="ace" id="selectAll" value="-1"/>
                        <span class="lbl"></span>
                    </label>
                </th>
                <th>角色名称</th>
                <th class="hidden-480">备注</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="role">
                <tr>
                    <td class="center">
                        <c:if test="${isAdmin or (role.createBy eq oper.id)}">
                            <label class="position-relative">
                                <input type="checkbox" class="ace" value="${role.id}"/>
                                <span class="lbl"></span>
                            </label>
                       </c:if>
                    </td>
                    <td>
                        ${role.name}
                    </td>
                    <td  class="hidden-480">
                        <div>
                            ${role.memo}
                        </div>
                    </td>
                    <td>
                        <c:if test="${isAdmin or (role.createBy eq oper.id)}">
                        <div class="hidden-sm hidden-xs btn-group">
                            <btn:authorBtn menuid="${menuid}" text="查看">
                                <button class="btn btn-xs btn-success" onclick="showViewById('${role.id}')">
                                    <i class="ace-icon fa fa-eye bigger-120"></i>
                                </button>
                            </btn:authorBtn>

                            <btn:authorBtn menuid="${menuid}" text="修改">
                                <button class="btn btn-xs btn-info" onclick="updateRoleById('${role.id}')">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <btn:authorBtn menuid="${menuid}" text="删除">
                                <button class="btn btn-xs btn-danger" onclick="delRoleById('${role.id}')">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <btn:authorBtn menuid="${menuid}" text="资源授权">
                                <button class="btn btn-xs btn-purple" onclick="grantRole('${role.id}');">
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

                                <ul
                                        class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                    <li>
                                        <a href="javascript:showViewById('${role.id}')" class="tooltip-info"
                                           data-rel="tooltip"
                                           title="View"> <span class="blue"> <i
                                                class="ace-icon fa fa-search-plus bigger-120"></i>查看</span></a>
                                    </li>
                                    <btn:authorBtn menuid="${menuid}" text="修改">
                                        <li>
                                            <a href="javascript:updateRoleById('${role.id}')" class="tooltip-success"
                                               data-rel="tooltip"
                                               title="Edit"> <span class="green"> <i
                                                    class="ace-icon fa fa-pencil-square-o bigger-120"></i>修改</span> </a>
                                        </li>
                                    </btn:authorBtn>
                                    <btn:authorBtn menuid="${menuid}" text="资源授权">
                                        <li>
                                            <a href="javascript:grantRole('${role.id}');" class="tooltip-error" data-rel="tooltip"
                                               title="Delete"> <span class="red"> <i
                                                    class="ace-icon fa fa-fire bigger-120"></i>资源授权</span>
                                            </a>
                                        </li>
                                    </btn:authorBtn>
                                    <btn:authorBtn menuid="${menuid}" text="删除">
                                        <li>
                                            <a href="javascript:delRoleById('${role.id}')" class="tooltip-error"
                                               data-rel="tooltip"
                                               title="Delete"> <span class="red"> <i
                                                    class="ace-icon fa fa-trash-o bigger-120"></i>删除</span>
                                            </a>
                                        </li>
                                    </btn:authorBtn>
                                </ul>
                            </div>
                        </div>
                        </c:if>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
        <div class="row">
            <div class="col-xs-12">
                <tags:pagination page="${pageList}"></tags:pagination>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        <!--
        function addRole() {
            window.location.href = "${root}/forward/system/role/add/?menuid=${menuid}";
        }

        function updateRoleById(id) {
            var page = '${current}';
            window.location.href = "${root}/system/role/update/" + id + "/${menuid}/?page=" + page;
        }

        function showViewById(id) {
            var page = '${current}';
            window.location.href = "${root}/system/role/showview/" + id + "/${menuid}/?page=" + page;
        }

        function removeRole() {
            var ids = getSelectedValue();
            if (ids.length == 0) {
                showMessage("请选择要删除的记录。");
            } else {
                delRoleById(ids);
            }
        }

        /***
         *资源授权
         */
        function grantRes() {
            var ids = getSelectedValue();
            if (ids.length == 0) {
                showMessage("请选择要授权的记录。");
                return;
            }
            if (ids.length > 1) {
                showMessage("不能同时对多个角色进行授权，请选择一条记录。");
            } else {
                window.location.href = "${root}/system/role/grantres/${menuid}/" + ids + "/?page=${current}";
            }

        }

        function grantRole(ids) {
            window.location.href = "${root}/system/role/grantres/${menuid}/" + ids + "/?page=${current}";
        }


        function delRoleById(ids) {
            bootbox.confirm("删除后数据将无法恢复，确定要删除吗？", function (a) {
                if (!a) {
                    return;
                }
                var url = "${root}/system/role/delete/" + ids + "/";
                $.ajax({
                    type: 'delete',
                    url: url,
                    dataType: "json",
                    success: function (msg) {
                        if (msg.result == "ok") {
                            $("#alert-div").removeClass("alert-danger").addClass("alert-success");
                            showMessage("删除成功");
                            setTimeout("document.location.reload()", 1600);
                        } else {
                            showMessage("该角色信息正在被用户使用，删除失败!");
                        }
                    }
                });
            });
        }

        $(document).ready(function () {
            $("#alert-div").hide();
        });
        //-->
    </script>