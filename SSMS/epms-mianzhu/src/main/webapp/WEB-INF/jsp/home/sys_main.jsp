<%@ page pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${root}/compnents/ace/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${root}/compnents/ace/css/font-awesome.min.css"/>
<!-- page specific plugin styles -->
<!-- text fonts -->
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-fonts.css"/>

<!-- ace styles -->
<link rel="stylesheet" href="${root}/compnents/ace/css/ace.min.css" id="main-ace-style"/>

<!--[if lte IE 9]>
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-part2.min.css"/>
<![endif]-->
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-skins.min.css"/>
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-rtl.min.css"/>
<!--[if lte IE 9]>
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-ie.min.css"/>
<![endif]-->
<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src="${root}/compnents/ace/js/jquery-3.2.1.min.js"></script>
<script src="${root}/compnents/ace/js/ace-extra.min.js"></script>
<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!--[if lte IE 8]>
<script src="${root}/compnents/ace/js/html5shiv.min.js"></script>
<script src="${root}/compnents/ace/js/respond.min.js"></script>
<![endif]-->

<div style="background-color:#fff;"id="wrap">
    <div class="left" style="margin-bottom:0">
        <div class="row" style="margin-top:-8px;">
            <div class="col-sm-12">
                <div class="widget-box transparent">
                    <div class="widget-header widget-header-flat">
                        <h4 class="widget-title lighter">
                            <i class="ace-icon fa fa-user"></i>
                            当前用户
                        </h4>

                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main padding-4">
                            <div class="profile-user-info profile-user-info-striped">
                                <div class="profile-info-row">
                                    <div class="profile-info-name">姓 名：</div>
                                    <div class="profile-info-value">${user.realName}</div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name">拥有角色：</div>
                                    <div class="profile-info-value">${roles}</div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name">部门：</div>
                                    <div class="profile-info-value"><tags:xiangxuncache keyName="Department"
                                                                                id="${user.deptId}" /></div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name">上次登录时间：</div>
                                    <div class="profile-info-value"><fmt:formatDate value="${user.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name">上次登录IP：</div>
                                    <div class="profile-info-value">${user.ipAddress}</div>
                                </div>
                            </div>
                        </div>
                        <!-- /.widget-main -->
                    </div>
                    <!-- /.widget-body -->
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 5px;">
            <div class="col-sm-12">
                <div class="widget-box transparent">
                    <div class="widget-header widget-header-flat">
                        <h4 class="widget-title lighter">
                            <i class="ace-icon fa fa-star orange"></i>
                            最近操作记录
                        </h4>

                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body" style="display: block;">
                        <div class="widget-main no-padding">
                            <table class="table table-striped table-bordered table-hover table-style">
                                <thead class="thin-border-bottom">
                                <tr style="font-size:14px;">
                                    <th>描述</th>
                                    <th>IP地址</th>
                                    <th class="hidden-480">发生时间</th>
                                    <th class="hidden-480">是否成功</th>
                                    <th class="hidden-480">操作员</th>
                                </tr>
                                </thead>

                                <tbody style="font-size:14px;">
                                <c:forEach items="${logPage.result }" var="item">
                                <tr>
                                    <td>${item.des }</td>
                                    <td>${item.ip }</td>
                                    <td><fmt:formatDate value="${item.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>${item.isSuccess eq '1' ? '成功' : '失败' }</td>
                                    <td>${item.operator }</td>
                                </tr>
                                </c:forEach>
                                <c:if test="${logPage.result!=null}">
					                <c:forEach begin="1" end="${6-fn:length(logPage.result)}">
					                    <tr>
					                        <td colspan="68">&nbsp;</td>
					                    </tr>
					                </c:forEach>
					            </c:if>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.widget-main -->
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='${root}/compnents/ace/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${root}/compnents/ace/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="${root}/compnents/ace/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<!--[if lte IE 8]>
<script src="${root}/compnents/ace/js/excanvas.min.js"></script>
<![endif]-->
<!-- ace scripts -->
<script src="${root}/compnents/ace/js/ace-elements.min.js"></script>
<script src="${root}/compnents/ace/js/ace.min.js"></script>
<script type="text/javascript">
    $("#wrap").css("height",$("#wrap").parent().height()+"px")
</script>