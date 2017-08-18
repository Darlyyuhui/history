<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
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
<!-- 控制iframe中的页面返回的结果显示开始 -->
<div id="subinfo" class="alert alert-success" style="display: none;">
    <button data-dismiss="alert" class="close">×</button>
    <p id="mesg" align="center"></p>
</div>
<div class="page-header">
    <h1>
        村民责任人
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            村民责任人的基本信息
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/bs/owner/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12">
                <div class="input-group">
                   	<input type="text" class="form-control search-query" id="name" name="search_name" value="${name }" placeholder="姓名" />
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-purple btn-sm" style="margin-left:1px;">查询<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                        </button>
                        <button type="button" class="btn btn-info btn-sm" onclick="resetSearchValue()" style="margin-left: 5px;">
                        	重置
                        	<i class="ace-icon fa fa-refresh icon-on-right bigger-110"></i>
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
            <button class="btn btn-sm btn-success pull-left" onclick="add();"><i class="icon-plus"></i>添加</button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="删除">
            <button class="btn btn-sm btn-danger pull-left" onclick="removelampblackDevices();"><i
                    class="icon-remove"></i>删除
            </button>
        </btn:authorBtn>
    </div>
</div>
<div class="row" style="margin-top:1px;">
    <div class="col-xs-12">
            <table id="table" class="table table-striped table-bordered table-hover table-style text-center">
                <thead>
                <tr>
                    <th class="center" width="50">
                        <input type="checkbox" class="ace" id="selectAll" value="-1"/>
                        <span class="lbl"></span>
                    </th>
                    <th>姓名</th>
                    <th>所属乡镇</th>
                    <th>备注</th>
                    <th width="160">操作</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <c:forEach items="${pageList.result}" var="info">
                    <tr>
                        <td style="text-align:center"><input type="checkbox" value="${info.id}"
                                                             name="select-chk"/></td>
                        <td>${info.name }</td>
                        <td><tags:xiangxuncache keyName="TREGION_FULL_NAME" id="${info.regionId }"/></td>
                        <td>${info.remark}</td>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                            	<%-- 
                                <btn:authorBtn menuid="${menuid}" text="查看">
                                    <button class="btn btn-xs btn-success"
                                            onclick="showViewById('${info.id}')">
                                        <i class="ace-icon fa fa-eye bigger-120"></i>
                                    </button>
                                </btn:authorBtn>
                                 --%>
                                <btn:authorBtn menuid="${menuid}" text="修改">
                                    <button class="btn btn-xs btn-info" onclick="updateById('${info.id}')">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </button>
                                </btn:authorBtn>
                                <btn:authorBtn menuid="${menuid}" text="删除">
                                    <button class="btn btn-xs btn-danger" onclick="deleteById('${info.id}')">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </button>
                                </btn:authorBtn>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${pageList.result!=null}">
                    <c:forEach begin="1" end="${15-fn:length(pageList.result)}">
                        <tr>
                            <td colspan="68">&nbsp;</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div class="row">
                <c:if test="${pageList!=null}">
                    <tags:pagination page="${pageList}"></tags:pagination>
                </c:if>
            </div>
        </div>
    </div>
   
<script type="text/javascript">
    function add() {
    	window.location.href = "${root}/bs/owner/add/${menuid}/?page=${page}";
    }
    function updateById(id) {
        window.location.href = "${root}/bs/owner/update/${menuid}/" + id + "/?page=${page}";
    }
    function showViewById(id) {
        window.location.href = "${root}/bs/owner/showView/${menuid}/" + id + "/?page=${page}";
    }

    function removelampblackDevices() {
        var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要删除的记录。");
        } else {
            deleteById(ids);
        }
    }
    function deleteById(ids) {
        bootbox.confirm("删除后数据将无法恢复，确定要删除吗？",function(a) {
            if (!a) return;
            var url = "${root}/bs/owner/delete/" + ids + "/";
            $.ajax({
                type : 'post',
                url : url,
                dataType : "json",
                success : function(msg) {
                    if (msg.result == "ok") {
                        $("#alert-div").removeClass("alert-danger").addClass("alert-success");
                        showMessage("删除成功");
                        setTimeout("goList()", 1600);
                    } else {
                        showMessage("删除失败!");
                    }
                }
            });
        });
    }
    function goList() {
        window.location.href = "${root}/bs/owner/list/${menuid}/?page=${page}&isgetsession=1";
    }

    $(function () {
        $("#alert-div").hide();
        $("#data-list").width($("#dev-main-div").width() - 275);
    });

</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
