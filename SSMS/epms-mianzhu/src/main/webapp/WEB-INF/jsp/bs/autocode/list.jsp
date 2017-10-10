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
      自动编号规则
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            规则查询
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" action="${root}/bs/autocode/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12">
                <div class="input-group">
                   	<select id="typeCode" name="search_typeCode" class="form-control search-query">
                   		<tags:diccache typeCode="AUTO_CODE_TYPE" defaultValue="${typeCode }" emptyText="全部编号类型" />
                   	</select>
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
            <button class="btn btn-sm btn-danger pull-left" onclick="deleteByIds();"><i
                    class="icon-remove"></i>删除
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
                <th>编号类型</th>
                <th>前区编号</th>
                <th>日期类型</th>
                <th>中区编号</th>
                <th>尾部序列号长度</th>
                <th width="120">操作</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="item">
                <tr>
                	<td ><input type="checkbox" value="${item.typeCode}" name="select-chk"/>
                    </td>
                    <td><tags:xiangxuncache keyName="Dic" typeCode="AUTO_CODE_TYPE" id="${item.typeCode }"/></td>
                    <td>${item.headCode }</td>
	                <td>
	                	${item.dateType }
	                </td>
	                <td>${item.centerCode }</td>
	                <td>${item.orderLength }</td>
                    <td>
                    	<div class="hidden-sm hidden-xs btn-group">
                            <btn:authorBtn menuid="${menuid}" text="修改">
                                <button class="btn btn-xs btn-info" onclick="updateById('${item.typeCode}')">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                            </btn:authorBtn>
                            <btn:authorBtn menuid="${menuid}" text="删除">
                                <button class="btn btn-xs btn-danger" onclick="deleteById('${item.typeCode}')">
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
        window.location.href = "${root}/bs/autocode/list/${menuid}/?isgetsession=1";
    }
    
    function add() {
        window.location.href = "${root}/bs/autocode/add/${menuid}/?page=${page}";
    }
    function updateById(id) {
        window.location.href = "${root}/bs/autocode/update/${menuid}/"+id+"/?page=${page}";
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
            var url = "${root}/bs/autocode/doDelete/";
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