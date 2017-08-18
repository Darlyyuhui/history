<%@ page language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="${root}/compnents/ace/css/chosen.css"/>
<script src="${root}/compnents/ace/js/chosen.jquery.min.js"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none;">
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
    function reValues() {
        $("#dic_type_chzn>a span").html("请选择");
        $("#dic_type").val("");
        $("#code").val("");
        $("#name").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="page-header">
    <h1>
        数据字典
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            系统数据字典统一管理
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/system/dic/list/${groupid}/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="input-group">
                    <%--                    <input type="text" class="form-control search-query" id="name" name="search_name" value="${dic.name}" placeholder="名称" />--%>
                    <select id="dic_type" name="search_type" class="form-control search-query">
                        <option value="">请选择</option>
                        <c:forEach items="${dic_sec}" var="keyValue">
                            <option value="${keyValue.key}"
                                    gencode="${keyValue.gencode}" ${keyValue.key==dic.type.value?'selected':''}>${keyValue.name}</option>
                        </c:forEach>
                    </select>
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-purple btn-sm" style="margin-left:1px;">查询<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                        </button>
                        <%-- 
                        <a href="#modal-table" role="button" class="green btn btn-sm btn-warning" style="margin-left: 1px;"
						data-toggle="modal" data-backdrop="false"> 高级查询 <i class="ace-icon fa  fa-arrow-right icon-animated-hand-pointer white"></i>
						</a> 
						 --%>
                    </span>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="row">
    <div class="col-xs-12">
        <btn:authorBtn menuid="${menuid}" text="添加">
            <button class="btn btn-sm btn-success pull-left" onclick="addDic()" style="margin:1px;">
                <i class="ace-icon fa fa-plus"></i>
                <span class="bigger-110">添加</span>
            </button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="删除">
            <button class="btn btn-sm btn-danger pull-left" onclick="removeDic()" style="margin:1px">
                <i class="ace-icon fa fa-trash-o icon-on-left"></i>
                <span class="bigger-110">删除</span>
            </button>
        </btn:authorBtn>
    </div>
</div>
<div class="row">
    <div class="col-xs-12">
        <table id="table" class="table table-striped table-bordered table-hover table-style">
            <thead>
            <tr>
                <th class="center" width="50">
                    <label class="position-relative">
                        <input type="checkbox" class="ace" id="selectAll" value="-1"/>
                        <span class="lbl"></span>
                    </label>
                </th>
                <th>编码</th>
                <th>名称</th>
                <th class="hidden-480">类型</th>
                <th class="hidden-480">备注</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="dic">
                <tr onclick="rowOnclick(this)">
                    <td class="center">
                        <label class="position-relative">
                            <input type="checkbox" class="ace" value="${dic.id}"/>
                            <span class="lbl"></span>
                        </label>
                    </td>
                    <td>${dic.code}</td>
                    <td>${dic.name}</td>
                    <td class="hidden-480"><c:forEach items="${dic_sec}" var="keyValue">
                        ${keyValue.key==dic.type.value?keyValue.name :''}
                    </c:forEach></td>
                    <td class="texthidden hidden-480"><div style="text-overflow:ellipsis;overflow:hidden;width:350px">${dic.remark}</div></td>
                    <td class="center">
                        <div class="hidden-sm hidden-xs btn-group">
                            <btn:authorBtn menuid="${menuid}" text="查看">
                                <button class="btn btn-xs btn-success" onclick="showViewById('${dic.id}')">
                                    <i class="ace-icon fa fa-eye bigger-120"></i>
                                </button>
                            </btn:authorBtn>

                            <btn:authorBtn menuid="${menuid}" text="修改">
                                <button class="btn btn-xs btn-info" onclick="updateDicById('${dic.id}')">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <btn:authorBtn menuid="${menuid}" text="删除">
                                <button class="btn btn-xs btn-danger" onclick="delDicById('${dic.id}')">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
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
                                    <li>
                                        <a href="javascript:showViewById('${dic.id}')" class="tooltip-info"
                                           data-rel="tooltip"
                                           title="View"> <span class="blue"> <i
                                                class="ace-icon fa fa-search-plus bigger-120"></i>查看</span></a>
                                    </li>
                                    <btn:authorBtn menuid="${menuid}" text="修改">
                                        <li>
                                            <a href="javascript:updateDicById('${dic.id}')" class="tooltip-success"
                                               data-rel="tooltip"
                                               title="Edit"> <span class="green"> <i
                                                    class="ace-icon fa fa-pencil-square-o bigger-120"></i>修改</span> </a>
                                        </li>
                                    </btn:authorBtn>
                                    <btn:authorBtn menuid="${menuid}" text="删除">
                                        <li>
                                            <a href="javascript:delDicById('${dic.id}')" class="tooltip-error"
                                               data-rel="tooltip"
                                               title="Delete"> <span class="red"> <i
                                                    class="ace-icon fa fa-trash-o bigger-120"></i>删除</span>
                                            </a>
                                        </li>
                                    </btn:authorBtn>
                                </ul>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <tags:pagination page="${pageList}"></tags:pagination>
    </div>
</div>
<div id="modal-table" class="modal fade" tabindex="-1" style="top:80px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="white">&times;</span>
					</button>
					高级查询
				</div>
			</div>
			<div class="modal-body no-padding">
				<form class="form-horizontal" id="advanForm" role="form" class="form-search" action="${root}/system/dic/list/${groupid}/${menuid}/" method="post">
					<!-- #section:elements.form -->
					<div class="form-group" style="margin-top:10px;">
						<label class="col-sm-3 control-label no-padding-right"
							for="van-search-deviceId">
							名称
						</label>
						<div class="col-sm-9">
							<input type="text" id="van-search-name" placeholder="名称" name="search_name" class="input-large" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-name">
							编码
						</label>
						<div class="col-sm-9">
							<input type="text" id="form-field-linkMan" name="search_code" placeholder="编码"  class="input-large" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="search_dic_type">
							类型
						</label>
						<div class="col-sm-9">
							<select id="search_dic_type" name="search_type" style="min-width: 210px;" >
		                        <option value="">请选择</option>
		                        <c:forEach items="${dic_sec}" var="keyValue">
		                            <option value="${keyValue.key}"
		                                    gencode="${keyValue.gencode}" ${keyValue.key==dic.type.value?'selected':''}>${keyValue.name}</option>
		                        </c:forEach>
		                    </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-memo">
						备注
						</label>
						<div class="col-sm-9">
							<input type="text" id="form-field-memo" name="search_remark" placeholder="备注"  class="input-large" />
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer no-margin-top"
				style="padding-top: 8px; padding-bottom: 8px;">
				<button class="btn btn-primary btn-xs" type="button" onclick="vanSearch();">
					<i class="ace-icon fa fa-check bigger-110"></i> 确定
				</button>
				<button class="btn btn-xs" type="reset"  onclick="resetSearch()">
					<i class="ace-icon fa fa-undo bigger-110"></i> 重置
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
<script type="text/javascript">
	function vanSearch(){
		$("#advanForm").submit();
	}

	function resetSearch(){
		$("#van-search-name").val("");
		$("#form-field-linkMan").val("");
		$("#form-field-memo").val("");
		$("#search_dic_type").val("");
	}


    function addDic() {
        window.location.href = "${root}/forward/system/dic/add/?groupid=${groupid}&menuid=${menuid}&type="+ $("#dic_type").val();
    }

    function updateDicById(id) {
        var page = '${current}';
        window.location.href = "${root}/system/dic/update/${groupid}/" + id
                + "/${menuid}/?page=" + page;
    }

    function showViewById(id) {
        var page = '${current}';
        window.location.href = "${root}/system/dic/showView/${groupid}/" + id
                + "/${menuid}/?page=" + page;
    }

    function removeDic() {
        var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要删除的记录。");
        } else {
            delDicById(ids);
        }
    }

    function delDicById(ids) {
        bootbox.confirm("删除后数据将无法恢复，确定要删除吗？", function (a) {
            if (a) {
                var url = "${root}/system/dic/delete/" + ids + "/";
                $.ajax({
                    type: 'delete',
                    url: url,
                    dataType: "json",
                    success: function (msg) {
                        if (msg.result == "ok") {
                            $("#alert-div").removeClass("alert-danger").addClass(
                                    "alert-success");
                            showMessage("删除成功");
                            setTimeout("document.location.reload()", 1600);
                        } else {
                            showMessage("删除失败");
                        }
                    }
                });
            }
        });
    }

    function reloadPage() {
        window.location.href = "${root}/system/dic/list/${groupid}/${menuid}/?search_type="
                + $("#dicForm :selected").val()

    }

    $(document).ready(function () {
        $("#role-content").width($('body').width());
        $("#alert-div").hide();
        $('.chosen-select').chosen();
    });
</script>