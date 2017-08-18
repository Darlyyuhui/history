<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<style type="text/css">
.gj-query-input {
	width: 340px;

}

</style>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<tags:selTree idElement="soilType" nameElement="soilTypeName" treeType="landtype" />
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
      土壤地块
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            地块查询
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/land/block/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12">
                <div class="input-group">
                   	<input type="text" class="form-control search-query" id="name" name="search_name" value="${name }" placeholder="地块名称" />
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
        <btn:authorBtn menuid="${menuid}" text="上报异常">
            <button class="btn btn-sm btn-info pull-left" onclick="upError();"><i
                    class="icon-remove"></i>上报异常
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
                <th>地块编号</th>
                <th>地块名称</th>
                <th>地块类型</th>
                <th>所属乡镇</th>
                <th>面积（亩）</th>
                <th>土壤类型</th>
                <th>污染类型</th>
                <th>责任人</th>
                <th>状态</th>
                <th>是否存在异常</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="item">
                <tr>
                	<td ><input type="checkbox" value="${item.id}" name="select-chk"/>
                    </td>
                    <td>${item.code }</td>
                    <td>${item.name }</td>
                    <td><tags:xiangxuncache keyName="Dic" typeCode="LAND_BLOCK_TYPE" id="${item.typeCode }"/></td>
                    <td><tags:xiangxuncache keyName="TREGION_FULL_NAME" id="${item.regionId }"/></td>
	                <td>${item.area }</td>
	                <td><tags:xiangxuncache keyName="LANDTYPE_NAME" id="${item.soilType }"/></td>
	                <td><tags:xiangxuncache keyName="Dic" typeCode="LAND_POLLUTE_TYPE" id="${item.polluteType }"/></td>
	                <td><tags:xiangxuncache keyName="T_OWNER" id="${item.ownerId }" /></td>
	                <td>
	                	<c:if test="${item.repairStatus eq '0'}">未修复</c:if>
	                	<c:if test="${item.repairStatus eq '1'}">修复中</c:if>
	                	<c:if test="${item.repairStatus eq '2'}">修复完毕</c:if>
	                </td>
	                <td>
	                	<c:if test="${item.isError eq '0'}">无异常</c:if>
	                	<c:if test="${item.isError gt 0}"><a href="${root }/land/block/error/list/${menuid }/${item.id }/">查看异常</a></c:if>
	                </td>
                    <td>
                    	<div class="hidden-sm hidden-xs btn-group">
                            <btn:authorBtn menuid="${menuid}" text="查看">
                                <button class="btn btn-xs btn-success" onclick="showViewById('${item.id}')">
                                    <i class="ace-icon fa fa-eye bigger-120"></i>
                                </button>
                            </btn:authorBtn>
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
            <form class="form-horizontal" id="advanForm" role="form" class="form-search" action="${root}/land/block/list/${menuid}/" method="post">
            <div class="modal-body no-padding">
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            地块名称
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" class="input-large gj-query-input" id="adv_name" name="search_name" value="${name }" placeholder="地块名称" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            地块类型
                        </label>
                        <div class="col-sm-9">
                        	<select id="adv_typeCode" name="search_typeCode" class="input-large gj-query-input">
                        		<tags:diccache typeCode="LAND_BLOCK_TYPE" defaultValue="${typeCode }" emptyText="全部"/>
                        	</select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            所属乡镇
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" id="regionName" name="search_regionName" readonly="readonly" value='<tags:xiangxuncache keyName="TREGION_NAME" id="${regionId }"/>'
								class="input-large gj-query-input" onclick="showRegion('regionId','regionName')"/>
							<input type="hidden" id="regionId" name="search_regionId" value="${regionId }" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            土壤类型
                        </label>
                        <div class="col-sm-9">
                        	<input type="text" id="soilTypeName" name="search_soilTypeName" readonly="readonly" value='<tags:xiangxuncache keyName="LANDTYPE_NAME" id="${soilType }"/>'
								class="input-large gj-query-input" />
							<input type="hidden" id="soilType" name="search_soilType" value="${soilType }" />
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            污染类型
                        </label>
                        <div class="col-sm-9">
                        	<select id="polluteType" name="search_polluteType" class="input-large gj-query-input">
								<tags:diccache typeCode="LAND_POLLUTE_TYPE" defaultValue="${polluteType }" emptyText="全部"/>
							</select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:10px;">
                        <label class="col-sm-3 control-label no-padding-right"
                               for="van-search-deviceId">
                            是否存在异常
                        </label>
                        <div class="col-sm-9">
                        	<select id="isError" name="search_isError" class="input-large gj-query-input">
								<option value="">全部</option>
								<option value="1" ${isError eq '1' ? 'selected' : '' }>有异常</option>
								<option value="0" ${isError eq '0' ? 'selected' : '' }>无异常</option>
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
        window.location.href = "${root}/land/block/list/${menuid}/?isgetsession=1";
    }
    
    function searchMore() {
    	$("#adv_name").val($("#name").val());
    	$("#advanForm").submit();
    }
    
    function add() {
        window.location.href = "${root}/land/block/add/${menuid}/?page=${page}";
    }
    function updateById(id) {
        window.location.href = "${root}/land/block/update/${menuid}/"+id+"/?page=${page}";
    }
    function showViewById(id) {
        window.location.href = "${root}/land/block/showView/${menuid}/"+id+"/?page=${page}";
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
            var url = "${root}/land/block/doDelete/";
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
    
    function upError() {
    	 var ids = getSelectedValue();
         if (ids.length == 0) {
             showMessage("请选择要上报的记录。");
         } else if (ids.length > 1) {
             showMessage("单次只能对一个地块进行异常上报。");
         } else {
        	 window.location.href = "${root}/land/block/error/add/${menuid}/?blockId="+ids;
         }
    }
    
    $(document).ready(function () {
        $("#alert-div").hide();
    });
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>