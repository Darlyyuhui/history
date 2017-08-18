<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<style type="text/css">
.gj-query-input {
	width: 340px;
}
</style>
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
      水样底泥采样污染源
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            污染源查询
        </small>
    </h1>
</div>
<h3>当前采样编号：${waterInfo.code }</h3>
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
        <button class="btn btn-sm btn-info pull-left" onclick="goPList();"><i
            class="icon-remove"></i>返回上级列表
        </button>
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
                <th>污染源类型</th>
                <th>污染源名称</th>
                <th>特征污染物</th>
                <th>污染源规模</th>
                <th>所属乡镇</th>
                <th>状态</th>
                <th>与河距离</th>
                <th>是否有明显排污口</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="item">
                <tr>
                	<td ><input type="checkbox" value="${item.id}" name="select-chk"/>
                    </td>
                    <td><tags:xiangxuncache keyName="Dic" typeCode="SAMPLING_WATER_POLLUTE_TYPE" id="${item.typeCode }" /></td>
	                <td>${item.name }</td>
	                <td>${item.features }</td>
	                <td>${item.features }</td>
	                <td><tags:xiangxuncache keyName="TREGION_FULL_NAME" id="${item.regionId }"/></td>
	                <td><tags:xiangxuncache keyName="Dic" typeCode="SAMPLING_WATER_POLLUTE2" id="${item.status }" /></td>
	                <td>${item.riverDistance }</td>
	                <td>
	                	<c:if test="${item.isOutfall eq '1' }">是</c:if>
	                	<c:if test="${item.isOutfall eq '0' }">否</c:if>
	                </td>
                    <td>
                    	<div class="hidden-sm hidden-xs btn-group">
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
<script type="text/javascript">
    function goList() {
        window.location.href = "${root}/reg/water/pollute/list/${menuid}/${waterInfo.id}/?isgetsession=1";
    }
    function goPList() {
    	window.location.href = "${root}/reg/water/list/${menuid}/?isgetsession=1";
    }
    
    function add() {
        window.location.href = "${root}/reg/water/pollute/add/${menuid}/?page=${page}";
    }
    function updateById(id) {
        window.location.href = "${root}/reg/water/pollute/update/${menuid}/"+id+"/?page=${page}";
    }
    function showViewById(id) {
        window.location.href = "${root}/reg/water/pollute/showView/${menuid}/"+id+"/?page=${page}";
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
            var url = "${root}/reg/water/pollute/doDelete/";
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
    
    $(document).ready(function () {
        $("#alert-div").hide();
    });
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>