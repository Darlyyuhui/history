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
      土壤采样点位
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            点位查询
        </small>
    </h1>
</div>
<h3>采样方案：${schemeInfo.name }【${schemeInfo.code }】</h3>
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
        <btn:authorBtn menuid="${menuid}" text="核查采样点">
            <button class="btn btn-sm btn-success pull-left" onclick="checkByIds('1');"><i
                    class="icon-remove"></i>核查通过
            </button>
            <button class="btn btn-sm btn-danger pull-left" onclick="checkByIds('2');"><i
                    class="icon-remove"></i>核查拒绝
            </button>
        </btn:authorBtn>
        <btn:authorBtn menuid="${menuid}" text="发布采样点">
            <button class="btn btn-sm btn-warning pull-left" onclick="releaseByIds();"><i
                    class="icon-remove"></i>发布
            </button>
        </btn:authorBtn>
        <button class="btn btn-sm btn-info pull-left" onclick="goPList();"><i
            class="icon-remove"></i>返回方案列表
        </button>
    </div>
</div>
<h5 style="color: red;">*未核查和点位异常的点位不能成功发布</h5>
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
                <th>点位编号</th>
                <th>经度</th>
                <th>纬度</th>
                <th>采样范围</th>
                <th>区域编号</th>
                <th>是否采样点</th>
                <th>核查状态</th>
                <th>是否发布</th>
                <th>点位状态</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="item">
                <tr>
                	<td ><c:if test="${item.isRelease eq '0' }"><input type="checkbox" value="${item.id}" name="select-chk"/></c:if>
                    </td>
                    <td>${item.code }</td>
                    <td>${item.longitude }</td>
                    <td>${item.latitude }</td>
	                <td>${item.rangeX } x ${item.rangeY }</td>
	                <td>${item.areaCode }</td>
	                <td>${item.isSamplingPoint eq '1' ? '是' : '否' }</td>
	                <td>
	                	<c:if test="${item.checkStatus eq '0' }">未核查</c:if>
	                	<c:if test="${item.checkStatus eq '1' }"><span style="color: blue">已核查</span></c:if>
	                	<c:if test="${item.checkStatus eq '2' }"><span style="color: red">点位异常</span></c:if>
	                </td>
	                <td>
	                	<c:if test="${item.isRelease eq '0' }">未发布</c:if>
	                	<c:if test="${item.isRelease eq '1' }"><span style="color: green">已发布</span></c:if>
	                </td>
	                <td>
	                	<c:if test="${item.isSampling eq '0' }">未采样</c:if>
	                	<c:if test="${item.isSampling eq '1' }"><span style="color: blue">已采样</span></c:if>
	                </td>
                    <td>
                    	<div class="hidden-sm hidden-xs btn-group">
                            <btn:authorBtn menuid="${menuid}" text="修改">
                            	<c:if test="${item.checkStatus eq '0' }">
                                <button class="btn btn-xs btn-info" onclick="updateById('${item.id}')">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                                </c:if>
                            </btn:authorBtn>
                            <btn:authorBtn menuid="${menuid}" text="删除">
                            	<c:if test="${item.isRelease eq '0' }">
                                <button class="btn btn-xs btn-danger" onclick="deleteById('${item.id}')">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                                </c:if>
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
        window.location.href = "${root}/land/sampling/scheme/point/list/${menuid}/${schemeId}/?isgetsession=1&pag=${page}";
    }
    function goPList() {
    	window.location.href = "${root}/land/sampling/scheme/list/${menuid}/?isgetsession=1";
    }
    
    function add() {
        window.location.href = "${root}/land/sampling/scheme/point/add/${menuid}/?page=${page}";
    }
    function updateById(id) {
        window.location.href = "${root}/land/sampling/scheme/point/update/${menuid}/"+id+"/?page=${page}";
    }
    function showViewById(id) {
        window.location.href = "${root}/land/sampling/scheme/point/showView/${menuid}/"+id+"/?page=${page}";
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
            var url = "${root}/land/sampling/scheme/point/doDelete/";
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
    
    function releaseByIds() {
        var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要发布的记录。");
        } else {
        	bootbox.confirm("确定要发布选择的点位吗？",function(a) {
                if (!a) return;
                var url = "${root}/land/sampling/scheme/point/releasePoints/";
                $.ajax({
                    type: 'post',
                    url: url,
                    data : "ids="+ids,
                    success: function (msg) {
                        if (msg.result == "ok") {
                            $("#alert-div").removeClass("alert-danger").addClass("alert-success");
                            showMessage("发布完成（未核查和点位异常的点位不能发布！）");
                            setTimeout("goList()", 1600);
                        } else {
                            showMessage("发布失败!");
                        }
                    }
                });
        	});
        }
    }
    
    function checkByIds(status) {
        var ids = getSelectedValue();
        if (ids.length == 0) {
            showMessage("请选择要核查的记录。");
        } else {
        	bootbox.confirm("确定要核查选择的点位吗？",function(a) {
                if (!a) return;
                var url = "${root}/land/sampling/scheme/point/checkPoints/";
                $.ajax({
                    type: 'post',
                    url: url,
                    data : "ids="+ids+"&status="+status,
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