<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
<div class="page-header">
    <h1>
      采样计划
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            计划查询
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/land/sampling/plan/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12">
                <div class="input-group">
                   	<input type="text" class="form-control search-query" id="name" name="search_name" value="${name }" placeholder="计划名称" />
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-purple btn-sm" style="margin-left:1px;" onclick="searchMore()">查询<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                        </button>
                        <button type="button" class="btn btn-info btn-sm" onclick="resetSearchValue()" style="margin-left: 5px;">
                        	重置
                        	<i class="ace-icon fa fa-refresh icon-on-right bigger-110"></i>
                        </button>
                        <a href="#modal-table" role="button" class="green btn btn-sm btn-warning" style="margin-left: 5px;"
                           data-toggle="modal" data-backdrop="false"> 高级查询 <i class="ace-icon fa  fa-arrow-right icon-animated-hand-pointer white"></i>
                        </a>
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
            <button class="btn btn-sm btn-danger pull-left" onclick="deleteByIds();"><i
                    class="icon-remove"></i>删除
            </button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="计划完成">
            <button class="btn btn-sm btn-info pull-left" onclick="finishByIds();"><i
                    class="icon-remove"></i>计划完成
            </button>
        </btn:authorBtn>
    </div>
</div>
<h5 style="color: red;">*已有采样方案的计划不能被删除</h5>
<div class="row" style="margin-top:1px;">
    <div class="col-xs-12">
        <table id="table" class="table table-striped table-bordered table-hover table-style" style="text-align: center">
            <thead>
            <tr>
            	<th class="center" width="50">
                    <label class="position-relative">
                        <input type="checkbox" class="ace" id="selectAll" value="-1"/>
                        <span class="lbl"></span>
                    </label>
                </th>
                <th>计划编号</th>
                <th>计划名称</th>
                <th>采样种类</th>
                <th>实施机构</th>
                <th>开始日期</th>
                <th>是否完成</th>
                <th>完成日期</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="item">
                <tr>
                	<td >
                	<c:if test="${item.isFinish eq '0' }">
                	<input type="checkbox" value="${item.id}" name="select-chk"/>
                	</c:if>
                    </td>
                    <td>${item.code }</td>
                    <td>${item.name }</td>
                    <td>${item.sampleCodes }</td>
	                <td>${item.dept }</td>
	                <td><fmt:formatDate value="${item.startTime }" pattern="yyyy-MM-dd"/></td>
	                <td>
	                	<c:if test="${item.isFinish eq '0'}">未完成</c:if>
	                	<c:if test="${item.isFinish eq '1'}">已完成</c:if>
	                </td>
	                <td><fmt:formatDate value="${item.finishTime }" pattern="yyyy-MM-dd"/></td>
                    <td>
                    	<div class="hidden-sm hidden-xs btn-group">
                            <btn:authorBtn menuid="${menuid}" text="查看">
                                <button class="btn btn-xs btn-success" onclick="showViewById('${item.id}')">
                                    <i class="ace-icon fa fa-eye bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <c:if test="${item.isFinish eq '0' }">
                            <btn:authorBtn menuid="${menuid}" text="修改">
                                <button class="btn btn-xs btn-info" onclick="updateById('${item.id}')">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <btn:authorBtn menuid="${menuid}" text="删除">
                                <button class="btn btn-xs btn-danger" onclick="deleteById('${item.id}')">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            </c:if>
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
            <form class="form-horizontal" id="advanForm" role="form" class="form-search" action="${root}/land/sampling/plan/list/${menuid}/" method="post">
            <div class="modal-body no-padding">
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            计划名称
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" class="input-large" id="adv_name" name="search_name" value="${name }" placeholder="计划名称" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            计划编号
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" class="input-large" id="adv_code" name="search_code" value="${code }" placeholder="计划编号" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            采样种类
                        </label>
                        <div class="col-sm-9">
                        	<select id="adv_sampleCodes" name="search_sampleCodes" class="input-large">
                        		<option value="">全部</option>
                        		<c:forEach items="${samplingTypes }" var="item">
                        			<option value="${item.code }" ${item.code eq sampleCodes ? 'selected' : '' }>${item.name }</option>
                        		</c:forEach>
                        	</select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                           是否完成
                        </label>
                        <div class="col-sm-9">
                        	<select id="adv_isFinish" name="search_isFinish" class="input-large">
                        		<option value="">全部</option>
                        		<option value="0" ${isFinish eq '0' ? 'selected' : '' }>未完成</option>
                        		<option value="1" ${isFinish eq '1' ? 'selected' : '' }>已完成</option>
                        	</select>
                        </div>
                    </div>
            </div>
            <div class="modal-footer no-margin-top"
                 style="padding-top: 8px; padding-bottom: 8px;">
                <button class="btn btn-primary btn-xs" type="submit">
                    <i class="ace-icon fa fa-check bigger-110"></i> 查询
                </button>
                <button class="btn btn-xs" type="button" onclick="resetSearchValue()">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 重置
                </button>
            </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
<script type="text/javascript">
    function goList() {
        window.location.href = "${root}/land/sampling/plan/list/${menuid}/?isgetsession=1";
    }
    
    function searchMore() {
    	$("#adv_name").val($("#name").val());
    	$("#advanForm").submit();
    }
    
    function add() {
        window.location.href = "${root}/land/sampling/plan/add/${menuid}/?page=${page}";
    }
    function updateById(id) {
        window.location.href = "${root}/land/sampling/plan/update/${menuid}/"+id+"/?page=${page}";
    }
    function showViewById(id) {
        window.location.href = "${root}/land/sampling/plan/showView/${menuid}/"+id+"/?page=${page}";
    }
    function deleteByIds() {
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
            var url = "${root}/land/sampling/plan/doDelete/";
            $.ajax({
                type: 'post',
                url: url,
                data : "ids="+ids,
                success: function (msg) {
                    if (msg.result == "ok") {
                        $("#alert-div").removeClass("alert-danger").addClass("alert-success");
                        showMessage("删除完成（已有采样方案的计划不能被删除）");
                        setTimeout("goList()", 1600);
                    } else {
                        showMessage("删除失败!");
                    }
                }
            });
    	});
    }
    
    function finishByIds() {
    	var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要完成的记录。");
        } else {
        	bootbox.confirm("确定要将选择的记录完成吗？",function(a) {
                if (!a) return;
                var url = "${root}/land/sampling/plan/doFinish/";
                $.ajax({
                    type: 'post',
                    url: url,
                    data : "ids="+ids,
                    success: function (msg) {
                        if (msg.result == "ok") {
                            $("#alert-div").removeClass("alert-danger").addClass("alert-success");
                            showMessage("操作成功");
                            setTimeout("goList()", 1600);
                        } else {
                            showMessage("操作失败!");
                        }
                    }
                });
        	});
        }
    }
    
    $(document).ready(function () {
        $("#alert-div").hide();
    });
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>