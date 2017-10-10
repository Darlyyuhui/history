<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<style type="text/css">
.gj-query-input {
	width: 340px;
}
</style>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
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
     土壤分析数据登记
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            登记列表
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/analysis/land/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12">
                <div class="input-group">
                   	<input type="text" class="form-control search-query" id="code" name="search_code" value="${code }" placeholder="分析编号" />
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
        <btn:authorBtn menuid="${menuid}" text="导出">
            <button class="btn btn-sm btn-purple pull-left" onclick="doExport();"><i
                    class="icon-remove"></i>导出
            </button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="导入">
            <a href="#modal-table1" role="button" class="green btn btn-sm btn-warning" style="margin-left: 1px;"
               data-toggle="modal" data-backdrop="false">导入 <i class="ace-icon fa icon-animated-hand-pointer white"></i>
            </a>
        </btn:authorBtn>
    </div>
</div>
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
                <th>分析编号</th>
                <th>样品名称</th>
                <th>样品时间</th>
                <th>经度</th>
                <th>纬度</th>
                <th>分析单位</th>
                <th>PH值</th>
                <th>镉</th>
                <th>有效态镉</th>
                <th>采样地点</th>
                <th>样品状态</th>
                <th>是否超标</th>
                <th>超标项</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="item">
                <tr>
                	<td >
                	<c:if test="${item.status eq '0' }">
                	<input type="checkbox" value="${item.id}" name="select-chk"/>
                	</c:if>
                    </td>
                    <td>${item.code }</td>
                    <td>${item.name }</td>
                    <td><fmt:formatDate value="${item.samplingTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${item.longitude }</td>
                    <td>${item.latitude }</td>
                    <td>${item.dept }</td>
                    <td>${item.ph }</td>
                    <td>${item.cadmium }</td>
                    <td>${item.availableCadmium }</td>
                    <td><tags:xiangxuncache keyName="TREGION_FULL_NAME" id="${item.regionId }"/></td>
	                <td>${item.sampleStatus }</td>
	                <td>${item.isOver eq '1' ? '是' : '否' }</td>
	                <td>${item.overItem }</td>
                    <td>
                    	<div class="hidden-sm hidden-xs btn-group">
                            <btn:authorBtn menuid="${menuid}" text="查看">
                                <button class="btn btn-xs btn-success" onclick="showViewById('${item.id}')">
                                    <i class="ace-icon fa fa-eye bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <c:if test="${item.status eq '0' }">
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
            <form class="form-horizontal" id="advanForm" role="form" class="form-search" action="${root}/analysis/land/list/${menuid}/" method="post">
            <div class="modal-body no-padding">
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            分析编号
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" class="input-large gj-query-input" id="adv_code" name="search_code" value="${code }" placeholder="分析编号" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            样品名称
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" class="input-large gj-query-input" id="adv_name" name="search_name" value="${name }" placeholder="样品名称" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            分析单位
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" class="input-large gj-query-input" id="adv_dept" name="search_dept" value="${dept }" placeholder="分析单位" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-memo">
                            样品时间
                        </label>
                        <div class="col-sm-9">
                            <input id="startDate" name="search_beginTime" type="text" value="${beginTime}"
							class="input-large" readonly="readonly" style="width: 160px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:dd',alwaysUseStartDate:true,maxDate:document.getElementById('endDate').value==''?'':document.getElementById('endDate').value})" />
							至
							<input id="endDate" name="search_endTime" type="text" value="${endTime}"
							class="input-large" readonly="readonly" style="width: 160px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:dd',alwaysUseStartDate:true,minDate:document.getElementById('startDate').value})"})" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            采样地点
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" id="regionName" name="search_regionName" readonly="readonly" value='<tags:xiangxuncache keyName="TREGION_NAME" id="${regionId }"/>'
								class="input-large gj-query-input" onclick="showRegion('regionId','regionName')"/>
							<input type="hidden" id="regionId" name="search_regionId" value="${regionId }" />
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

<btn:authorBtn menuid="${menuid}" text="导入">
<div id="modal-table1" class="modal fade" tabindex="-1" style="top:80px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    数据导入
                </div>
            </div>
            <div class="modal-body no-padding">
                <form class="form-horizontal" id="importForm" role="form" class="form-search" 
                	action="${root}/analysis/land/doImport/${menuid}/" method="post" enctype="multipart/form-data">
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            导入文件
                        </label>
                        <div class="col-sm-9">
                            <input type="file" id="file" name="file" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-memo">
                            导入模板
                        </label>
                        <div class="col-sm-9">
                            <a href="${root }/downloadfile/土壤分析数据导入模板.xls/?filepath=download/template/excel/analysis_land_import.xls">点击下载导入模板</a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer no-margin-top"
                 style="padding-top: 8px; padding-bottom: 8px;">
                <button class="btn btn-primary btn-xs" type="button" onclick="doImport();">
                    <i class="ace-icon fa fa-check bigger-110"></i> 导入
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
<script type="text/javascript">
	function doImport() {
		$("#importForm").submit();
	}
</script>
</btn:authorBtn>

<btn:authorBtn menuid="${menuid}" text="导出">
	<form id="exportForm" action="${root}/analysis/land/doExport/" method="post">
		<input type="hidden" id="params" name="params" />
	</form>
	<script type="text/javascript">
	function doExport() {
		var parms = $("#advanForm").serialize();
		parms = decodeURIComponent(parms, true);
		$("#params").val(parms);
		bootbox.confirm("确定导出当前查询的结果集吗？",function(a) {
	        if (!a) return;
	        $("#exportForm").submit();
		});
	}
	</script>
</btn:authorBtn>

<c:if test="${not empty impMapId }">
	<form id="exportErrForm" action="${root}/analysis/land/downloadErrTxt/${impMapId }/" method="post">
	</form>
	<script type="text/javascript">
	$(function(){
		$("#exportErrForm").submit();
	});
	</script>
</c:if>

<script type="text/javascript">
    function goList() {
        window.location.href = "${root}/analysis/land/list/${menuid}/?isgetsession=1";
    }
    
    function searchMore() {
    	$("#adv_code").val($("#code").val());
    	$("#advanForm").submit();
    }
    
    function add() {
        window.location.href = "${root}/analysis/land/add/${menuid}/?page=${page}";
    }
    function updateById(id) {
        window.location.href = "${root}/analysis/land/update/${menuid}/"+id+"/?page=${page}";
    }
    function showViewById(id) {
        window.location.href = "${root}/analysis/land/showView/${menuid}/"+id+"/?page=${page}";
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
            var url = "${root}/analysis/land/doDelete/";
            $.ajax({
                type: 'post',
                url: url,
                data : "ids="+ids,
                success: function (msg) {
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
    
    function checkByIds() {
        var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要核查的记录。");
        } else {
        	bootbox.confirm("确定要核查选中的数据吗？",function(a) {
                if (!a) return;
                var url = "${root}/analysis/land/checkRegs/";
                $.ajax({
                    type: 'post',
                    url: url,
                    data : "ids="+ids+"&status=1",
                    success: function (msg) {
                        if (msg.result == "ok") {
                            $("#alert-div").removeClass("alert-danger").addClass("alert-success");
                            showMessage("核查成功");
                            setTimeout("goList()", 1600);
                        } else {
                            showMessage("核查失败!");
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