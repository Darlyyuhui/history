<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<style type="text/css">
.gj-query-input {
	width: 340px;
}
</style>
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
      数据校验规则
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            规则列表
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/check/info/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12">
                <div class="input-group">
                    <input type="text" class="form-control search-query" id="schemeName" name="search_schemeName" placeholder="样品来源" value="${schemeName }"/>
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
            <button class="btn btn-sm btn-success pull-left" style="margin-left:2px;" onclick="add();"><i class="icon-plus"></i>添加</button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="删除">
            <button class="btn btn-sm btn-danger pull-left" style="margin-left:2px;"  onclick="deleteByIds();"><i class="icon-remove"></i>删除
            </button>
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
                <th>样品来源</th>
                <th>样品种类</th>
                <th>采样单位</th>
                <th>分析单位</th>
                <th>分析样品数</th>
                <th>分析项目</th>
                <th>校验状态</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="item">
                <tr>
                    <td style="text-align:center;"><input type="checkbox" value="${item.id}" name="select-chk"/>
                    </td>
	                <td>${item.schemeName }</td>
	                <td><tags:xiangxuncache keyName="Dic" typeCode="SAMPLING_TYPES" id="${item.sampleType }"/></td>
	                <td>${item.samplingDept }</td>
	                <td>${item.analysisDept }</td>
	                <td>${item.analysisCount }</td>
	                <td>${item.testItems }</td>
	                <td>
	                	<c:if test="${item.status eq '0' }"><span style="color: blue;">未校验</span></c:if>
	                	<c:if test="${item.status eq '1' }"><span style="color: green;">无异常</span></c:if>
	                	<c:if test="${item.status eq '2' }"><span style="color: red; font-weight: bolder;">有异常</span></c:if>
	                </td>
                    <td>
                        <div class="hidden-sm hidden-xs btn-group">
                        	<c:if test="${item.status eq '0' }">
                                <a href="javascript:void(0);" onclick="checkById('${item.id}')">校验</a>
                            </c:if>
                            <c:if test="${item.status ne '0' }">
                                <a href="javascript:void(0);" onclick="showViewById('${item.id}')">查看</a>
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
            <form class="form-horizontal" id="advanForm" role="form" class="form-search" action="${root}/check/info/list/${menuid}/" method="post">
            <div class="modal-body no-padding">
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            样品来源
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" class="input-large gj-query-input" id="adv_schemeName" name="search_schemeName" value="${schemeName }" placeholder="样品来源" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            分析单位
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" class="input-large gj-query-input" id="adv_analysisDept" name="search_analysisDept" value="${analysisDept }" placeholder="分析单位" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            样品种类
                        </label>
                        <div class="col-sm-9">
                        	<select id="adv_sampleType" name="search_sampleType" class="input-large gj-query-input">
                        		<tags:diccache typeCode="SAMPLING_TYPES" defaultValue="${sampleType }" emptyText="全部"/>
                        	</select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            校验状态
                        </label>
                        <div class="col-sm-9">
                        	<select id="status" name="search_status" class="input-large gj-query-input">
								<option value="">全部</option>
								<option value="0" ${status eq '0' ? 'selected' : '' }>未校验</option>
								<option value="1" ${status eq '1' ? 'selected' : '' }>无异常</option>
								<option value="2" ${status eq '2' ? 'selected' : '' }>有异常</option>
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
	function searchMore() {
		$("#adv_schemeName").val($("#schemeName").val());
		$("#advanForm").submit();
	}
	function checkById(id) {
		window.location.href = "${root}/check/result/loadResult/${menuid}/"+id+"/";
	}
	function showViewById(id) {
		window.location.href = "${root}/check/result/list/${menuid}/"+id+"/";
	}
    $(document).ready(function () {
        $("#alert-div").hide();
    });
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>