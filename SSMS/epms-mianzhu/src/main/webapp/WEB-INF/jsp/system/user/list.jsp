<%@ page language="java" pageEncoding="UTF-8" %>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css"/>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck.min.js'/>" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none;">
    <p id="alert-content" align="center"></p>
</div>
<div class="alert alert-block pull-top alert-success" id="alertsuc-div" style="display:none;">
    <p id="alertsuc-content" align="center"></p>
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
<!-- 控制iframe中的页面返回的结果显示开始 -->
<div id="subinfo" class="alert alert-success" style="display: none;">
    <button data-dismiss="alert" class="close">×</button>
    <p id="mesg" align="center"></p>
</div>
<script type="text/javascript">
    function setMesg(s) {
        $("#subinfo").css("display", "");
        document.getElementById("mesg").innerHTML = s;
        setTimeout('$("#subinfo").hide("slow")', 1200);
    }
</script>
<!-- 控制iframe中的页面返回的结果显示结束 -->
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
    function reValues() {
        $("#name").val("");
        $("#account").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="page-header">
    <h1>
        用户管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            管理系统用户及授权
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/system/user/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="input-group">
                    <input type="text" class="form-control search-query" id="name" name="search_name"
                           value="${user.name}" placeholder="用户名称"/>
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
            <button class="btn btn-sm btn-success pull-left" id="addUser" style="margin:1px;">
                <i class="ace-icon fa fa-plus"></i>
                <span class="bigger-110">添加</span>
            </button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="删除">
            <button class="btn btn-sm btn-danger pull-left" id="delUser" style="margin:1px">
                <i class="ace-icon fa fa-trash-o icon-on-left"></i>
                <span class="bigger-110">删除</span>
            </button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="角色授权">
            <button id="grantUser" class="btn  btn-sm btn-warning pull-left" style="margin:1px">
                <i class="ace-icon fa fa-lemon-o icon-on-left"></i>
                <span class="bigger-110">角色授权</span>
            </button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="密码重置">
            <button id="resetPwd" class="btn  btn-sm btn-danger pull-left" style="margin:1px">
                <i class="ace-icon fa fa-cog icon-on-left"></i>
                <span class="bigger-110">密码重置</span>
            </button>
        </btn:authorBtn>
    </div>
</div>
<div class="row" style="margin-top:1px">
    <div class="col-xs-12 ztree_box" style="margin-right:0; margin-left:10px; height:548px;height:565px\0;#height:565px;_height:565px;
			width:185px; overflow:auto;float:left;width:15%">
        <ul id="tree-rec" class="ztree tree_border2 ztree_ul" style="width:180px;"></ul>
    </div>
    <div class="col-xs-12" style="margin: 0;float:left;width:84%">
        <table id="table" class="table table-striped table-bordered table-hover table-style text-center">
            <thead>
            <tr>
                <th class="center" width="50">
                    <label class="position-relative">
                    <input type="checkbox" class="ace" id="selectAll" value="-1"/>
                    <span class="lbl"></span>
                    </label>
                </th>
                <th>姓名</th>
                <th>账号</th>
                <th class="hidden-480">所属机构</th>
                <th class="hidden-480">拥有角色</th>
                <th class="hidden-480">联系方式</th>
                <th class="hidden-480">备注</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="user">
                <tr>
                    <td  class="center" >
                        <c:if test="${isAdmin or (user.createBy eq oper.id)}">
                            <label class="position-relative">
                                <input type="checkbox" class="ace" value="${user.id}"/>
                                <span class="lbl"></span>
                            </label>
                        </c:if>
                    </td>
                    <td>${user.name}</td>
                    <td>${user.account}</td>
                    <td class="hidden-480"><tags:cache keyName="Department" id="${user.deptid}"></tags:cache></td>
                    <td class="hidden-480">${user.roles}</td>
                    <td class="hidden-480">${user.mobile}</td>
                    <td class="hidden-480">${user.memo}</td>
                    <td>
                        <c:if test="${isAdmin or (user.createBy eq oper.id)}">
                        <div class="hidden-sm hidden-xs btn-group">
                            <btn:authorBtn menuid="${menuid}" text="查看">
                                <button class="btn btn-xs btn-success" onclick="showViewById('${user.id}')">
                                    <i class="ace-icon fa fa-eye bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <btn:authorBtn menuid="${menuid}" text="修改">
                                <button class="btn btn-xs btn-info" onclick="updateUserById('${user.id}')">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <c:if test="${user.account ne 'admin'}">
                            <btn:authorBtn menuid="${menuid}" text="删除">
                                <button class="btn btn-xs btn-danger" onclick="delUserById('${user.id}')">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            </c:if>
                            <btn:authorBtn menuid="${menuid}" text="数据授权">
                            <c:if test="${atms['isStartDepartControl']=='1'}">
                                <button class="btn btn-xs btn-warning" onclick="grantDeptUserById('${user.id}')">
                                    <i class="ace-icon fa fa-fire bigger-120"></i>
                                </button>
                                </a>
                            </c:if>
                            </btn:authorBtn>
                        </div>

                        <div class="hidden-md hidden-lg">
                            <div class="inline position-relative">
                                <button class="btn btn-minier btn-primary dropdown-toggle"
                                        data-toggle="dropdown" data-position="auto">
                                    <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                </button>

                                <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                    <li>
                                        <a href="javascript:showViewById('${user.id}')" class="tooltip-info"
                                           data-rel="tooltip"
                                           title="View"> <span class="blue"> <i
                                                class="ace-icon fa fa-search-plus bigger-120"></i>查看</span></a>
                                    </li>
                                    <btn:authorBtn menuid="${menuid}" text="修改">
                                    <li>
                                        <a href="javascript:updateUserById('${user.id}')" class="tooltip-success"
                                           data-rel="tooltip"
                                           title="Edit"> <span class="green"> <i
                                                class="ace-icon fa fa-pencil-square-o bigger-120"></i>修改</span> </a>
                                    </li>
                                        </btn:authorBtn>

                                    <btn:authorBtn menuid="${menuid}" text="删除">
                                    <li>
                                        <a href="javascript:delUserById('${user.id}')" class="tooltip-error"
                                           data-rel="tooltip"
                                           title="Delete"> <span class="red"> <i
                                                class="ace-icon fa fa-trash-o bigger-120"></i>删除</span>
                                        </a>
                                    </li>
                                        </btn:authorBtn>

                                    <btn:authorBtn menuid="${menuid}" text="数据授权">
                                    <li>
                                        <a href="javascript:grantDeptUserById('${user.id}')" class="tooltip-error"
                                           data-rel="tooltip"
                                           title="Delete"> <span class="red"> <i
                                                class="ace-icon fa fa-fire bigger-120"></i>数据授权</span>
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
        <tags:pagination page="${pageList}"></tags:pagination>
    </div>
</div>



<script type="text/javascript">
    <!--
    $("#addUser").click(function () {
        addUser(chooseId);
    });

    $("#delUser").click(function () {
        removeUser();
    });

    $("#grantUser").click(function () {
        grantRoles();
    });


    $("#grantData").click(function () {
        grantData();
    });

    $("#resetPwd").click(function () {
        resetPassword();
    });

    //添加用户
    function addUser(chooseId) {
        if (chooseId.length == 0) {
            showMessage('请选择所属部门');
            return;
        }
        $("#titlebar").css("display", "none");
        goUrl("${root}/forward/system/user/add/?menuid=${menuid}&deptid="+chooseId);

    }

    //修改用户
    function updateUserById(id) {
        var page = '${current}';
        $("#titlebar").css("display", "none");
        goUrl("${root}/system/user/update/" + id + "/${menuid}/?page=" + page);
    }

    function showViewById(id) {
        var page = '${current}';
        $("#titlebar").css("display", "none");
        goUrl("${root}/system/user/showview/" + id + "/${menuid}/?page=" + page);
    }

    //删除用户
    function removeUser() {
        var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要删除的记录。");
        } else {
            delUserById(ids);
        }
    }

    function delUserById(ids) {
        bootbox.confirm("删除后数据将无法恢复，确定要删除吗？",function(a){
            if(!a) return;
            var url = "${root}/system/user/delete/" + ids + "/";
            $.ajax({
                type: 'delete',
                url: url,
                dataType: "json",
                success: function (msg) {
                    if (msg.result == "ok") {
                        showSucMessage("删除成功");
                        setTimeout("document.location.reload()", 1600);
                    } else {
                        showMessage("删除失败");
                    }
                }
            });
        });
    }

    //角色授权
    function grantRoles() {
        var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要授权的记录。");
        } else {
            $("#titlebar").css("display", "none");
            goUrl("${root}/system/user/grantrole/${menuid}/" + ids + "/?page=${current}");
        }
    }

    //数据授权
    function grantData() {
        var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要授权的记录。");
        } else {
            grantDeptUserById(ids);
        }
    }

    function grantDeptUserById(id) {
        $("#titlebar").css("display", "none");
        goUrl("${root}/system/user/grantAuthority/${menuid}/" + id + "/?page=${current}");
    }

    //重置密码
    function resetPassword() {
        var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要重置密码的用户。");
            return;
        }

        if (ids.length > 1) {
            showMessage("密码重置时，只能选择一个用户！");
            return;
        }

        bootbox.confirm("密码重置后，密码将自动修改为该用户的账号。确定要重置吗？",function(a) {
            if (!a){
                return;
            }
            $.getJSON("${root}/system/user/resetPassword/" + ids, function (msg) {
                if (msg.result == "ok") {
                    showMessage("重置成功");
                } else {
                    showMessage("重置失败");
                }
            });
        });
    }

    function treeOnlick(event, treeId, treeNode, clickFlag) {
        chooseId = treeNode.id;
        window.location.href = "${root}/system/user/list/${menuid}/?search_deptid="	+ chooseId;
    }

    function goUrl(url) {
        window.location.href = url;
    }

    var setting = {
        async : {
            enable : true,
            //url : "${root}/system/org/getTreeNode/",
            autoParam : [ "id" ]
        },
        check : {
            enable : false,
            chkStyle : "radio"
        },
        data : {
            simpleData : {
                enable : true
            }
        },
        callback : {
            onClick : treeOnlick
        }
    };

    $.getJSON("${root}/system/org/showTreeNode/${menuid}/",function(zNodes){
        tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
        var node = tree.getNodeByParam("id", 0, null);
        tree.expandNode(node, true, false, true);
        chooseId = '${user.deptid}';
        chooseNode = tree.getNodeByParam("id", chooseId, null);
        tree.selectNode(chooseNode);
    });

    //-->
</script>
</body>