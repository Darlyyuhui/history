<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css"/>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>

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
        农产品基地
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            农产品基地的基本信息
        </small>
    </h1>
</div>
<div style="margin:10px 0">
    <form class="form-search" id="search-form" action="${root}/apb/apbinfo/list/${menuid}/" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="input-group">
                    <input type="text" class="form-control search-query" value="${name}"
                           id="lampb_search_name" name="search_name" placeholder="名称"/  maxlength="20">
                    <span class="input-group-btn">
                           <button type="submit" class="btn btn-purple btn-sm" style="margin-left:1px;">
                               查询
                               <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                           </button>
                             <a href="#modal-table" role="button" class="green btn btn-sm btn-warning"
                                style="margin-left: 1px;"
                                data-toggle="modal" data-backdrop="true"> 高级查询 <i
                                     class="ace-icon fa  fa-arrow-right icon-animated-hand-pointer white"></i>
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
                    <th>基地编号</th>
                    <th>基地名称</th>
                    <th>N（北纬）</th>
                    <th>E（东经）</th>
                    <th>详细地址</th>
                    <th>年产量（万吨）</th>
                    <th>种植面积（亩）</th>
                    <th>主营产品</th>
                    <th>基地描述</th>
                    <th>周围环境</th>
                    <th>土壤类型</th>
                    <th>污染状况</th>
                    
                    <th width="160">操作</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <c:forEach items="${pageList.result}" var="info">
                    <tr>
                        <td style="text-align:center"><input type="checkbox" value="${info.id}"
                                                             name="select-chk"/></td>
                        <td>${info.code }</td>
                        <td>${info.name}</td>
                        <td>${info.latitude}</td>
                        <td>${info.longitude}</td>
                        <td>${info.address}</td>
                        <td>${info.annualOutput}</td>
                        <td>${info.area}</td>
                        <td><tags:listshow keyName="${ID_APBPRODUCTTYPENAME }"  id="${info.id }"></tags:listshow></td>
                        <td>${info.describe}</td>
                        <td>${info.ambient}</td>
                        <td>
                           <tags:xiangxuncache keyName="LANDTYPE_NAME" id="${info.soilType }"/>
                        </td>
                        <td>${info.polluteDesc}</td>
                        
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <btn:authorBtn menuid="${menuid}" text="查看">
                                    <button class="btn btn-xs btn-success"
                                            onclick="showViewById('${info.id}')">
                                        <i class="ace-icon fa fa-eye bigger-120"></i>
                                    </button>
                                </btn:authorBtn>
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
    <div id="modal-table" class="modal fade" tabindex="-1" style="top:80px;display:none">
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
                    <form class="form-horizontal" id="advanForm" role="form" class="form-search"
                          action="${root}/apb/apbinfo/list/${menuid}/" method="post">
                        <!-- #section:elements.form -->
                        <div class="form-group" style="margin-top:10px;">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="van-search-code">
                                编号
                            </label>

                            <div class="col-sm-9">
                                <input type="text" id="van-search-code" name="search_code" value="${code}"   maxlength="20"
                                       placeholder="编号" style="width:300px" class="input-large"/>
                            </div>
                        </div>
                       
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  
                                   for="van-search-name">
                                名称
                            </label>

                            <div class="col-sm-9">
                                <input type="text" id="van-search-name" name="search_name"   maxlength="20"
                                       value="${ name}"
                                       placeholder="名称" style="width:300px" class="input-large"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="van-search-mainProduct">
                                主营产品
                            </label>

                            <div class="col-sm-9">
                               <%--  <input type="text" id="van-search-mainProduct" name="search_mainProduct"
                                       value="${ apbInfo.mainProduct}"
                                       placeholder="名称" style="width:300px" class="input-large"/> --%>
                                       
                                     <select id="search_mainProduct" name="search_mainProduct" style="min-width:120px; width: 300px;" class="input-large" >
                                   <tags:dicothercache typeCode="${APBPRODUCTTYPE }" defaultValue="${ mainProduct}"/>
                                </select>
                            </div>
                        </div>
                        <%-- <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="van-search-simno">
                                所属基地
                            </label>

                            <div class="col-sm-9">
                                <select id="typeCode" name="typeCode" style="min-width:120px; width: 350px;" class="input-large required">
                                   <tags:dicregioncache typeCode="apbinfoTYPE_CODE_NAME"  ></tags:dicregioncache>
                                </select>
                            </div>
                        </div>  --%>
                    </form>
                </div>
                <div class="modal-footer no-margin-top"
                     style="padding-top: 8px; padding-bottom: 8px;">
                    <button class="btn btn-primary btn-xs" type="button" onclick="vanSearch();">
                        <i class="ace-icon fa fa-check bigger-110"></i> 确定
                    </button>
                    <button class="btn btn-xs" type="reset" onclick="resetSearchValue()">
                        <i class="ace-icon fa fa-undo bigger-110"></i> 重置
                    </button>
                </div>
                
            </div>
            
</div>
<script type="text/javascript">

    function vanSearch() {
        $("#advanForm").submit();
    }

    function showMaps() {
        window.location.href = "${root}/apb/apbinfo/showMap/${menuid}/";
    }
 
    function showLocation() {
        window.location.href = "${root}/apb/apbinfo/showLocation/?menuid=${menuid}";
    }

    function add() {
    	window.location.href = "${root}/apb/apbinfo/add/${menuid}/?page=${page}";
    }
    function addBtn() {
    	window.location.href = "${root}/apb/apbinfo/add/${menuid}/?page=${page}";
    }
    function updateById(id) {
        window.location.href = "${root}/apb/apbinfo/update/${menuid}/" + id + "/?page=${page}";
    }

    function showViewById(id) {
        window.location.href = "${root}/apb/apbinfo/showView/${menuid}/" + id + "/";
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
            var url = "${root}/apb/apbinfo/delete/" + ids + "/";
            $.ajax({
                type : 'delete',
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
        window.location.href = "${root}/apb/apbinfo/list/${menuid}/?page=${page}&isgetsession=1";
    }

    $(function () {
        $("#alert-div").hide();
        $("#data-list").width($("#dev-main-div").width() - 275);
    });

</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
