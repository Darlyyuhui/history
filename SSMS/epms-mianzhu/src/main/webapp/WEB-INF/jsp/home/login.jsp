<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="btn" uri="/WEB-INF/author-btn.tld" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>土壤安全管理与绿色食品基地展示平台</title>

    <meta name="description" content="User login page"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome-->
    <link rel="stylesheet"
          href="${root}/compnents/ace/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="${root}/compnents/ace/css/font-awesome.min.css"/>

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${root}/compnents/ace/css/ace-fonts.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${root}/compnents/ace/css/ace.min.css"
          id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${root}/compnents/ace/css/ace-part2.min.css"/>
    <![endif]-->
    <link rel="stylesheet"
          href="${root}/compnents/ace/css/ace-skins.min.css"/>
    <link rel="stylesheet"
          href="${root}/compnents/ace/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${root}/compnents/ace/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${root}/compnents/ace/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${root}/compnents/ace/js/html5shiv.min.js"></script>
    <script src="${root}/compnents/ace/js/respond.min.js"></script>
    <![endif]-->
    <!--[if !IE]> -->
    <script type="text/javascript">
        window.jQuery || document.write("<script src='${root}/compnents/ace/js/jquery.min.js'>" + "<" + "/script>");
    </script>
    <!-- <![endif]-->

    <!--[if IE]>
    <script type="text/javascript">
        window.jQuery || document.write("<script src='${root}/compnents/ace/js/jquery1x.min.js'>" + "<" + "/script>");
    </script>
    <![endif]-->
    <script type="text/javascript">
        if ('ontouchstart' in document.documentElement) document.write("<script src='${root}/compnents/ace/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
    </script>
    <script src="${root}/compnents/ace/js/bootstrap.min.js"></script>
</head>
<style>

</style>
<body class="login-layout" style="background: #3a8fcd url(${root}/images/background.jpg) no-repeat center;height:100%;">
<div class="main-container">
    <div class="main-content" style="margin-top:12%;">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1" style="margin-top:-40px;">
                <div class="centesr" style="width: 800px;margin:40px auto">
                    <h1 style="font-family: '微软雅黑'">
                        <img src="${root}/compnents/ace/images/logo_top.png" alt=""/>
                    </h1>
                </div>
                <div class="login-container" style="width:500px;">

                    <div class="space-6"></div>
                    <div class="position-relative" style="border: 1px solid rgba(255,255,255,.4);border-radius: 5px;">
                        <div id="login-box"
                             class="login-box visible widget-box no-border" style="background: transparent;">
                            <div class="widget-body" style="background: transparent">
                                <div class="widget-main" style="background:url(${root}/images/admin-loginform-bg.png) no-repeat center;">
                                    <div class="space-6"></div>

                                    <form action="j_spring_security_check" method="post">
                                        <fieldset>
                                            <label class="block clearfix infobox-black" style="border: 1px solid #b0bcc8;">
														<span class="block input-icon input-icon-right"> <input
                                                                type="text" class="form-control" placeholder="用户名"
                                                                autofocus name="j_username" id="username" value="admin"
                                                                class="required white" maxlength="30" autocomplete="off" style="outline:none; border:none;background:#faffbd ;color: #000;height: 45px;"/>
                                                                </span>
                                            </label>

                                            <label class="block clearfix" style="border: 1px solid #b0bcc8;">
														<span class="block input-icon input-icon-right"> <input
                                                                type="password" class="form-control" placeholder="密码"
                                                                name="j_password" id="password" value="admin"
                                                                class="required" maxlength="20" autocomplete="off" style="outline:none; border:none;background:#faffbd ;color: #000;height: 45px; "/>
                                                               </span>
                                            </label>

                                            <div class="space"></div>
<%-- 
                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace" name="remember-me" style="background: #000;"/>
                                                    <span class="lbl" style="color: #bdc4cc"> 记住我</span>
                                                </label>

                                                <div type="submit"
                                                    class="width-35 pull-right text-right">
                                                    <a href="#"><span class="bigger-110 text-right" style="color: #bdc4cc">忘记密码？</span></a>
                                                </div>
                                            </div>
 --%>                                            
                                            <div
                                                    style="height: 25px; ${ param .error?'': 'display:none'}">
                                                <div style="font-weight: bold; color: #EA5200;">
                                                    ${param.error?'用户名密码不正确':''}
                                                </div>
                                            </div>
                                            <div class="toolbar clearfix" style="border-color: #005689;background:#005689;margin-top:15px;filter: Alpha(opacity=90); -moz-opacity:0.9; opacity:0.9;">
                                                <button type="submit" class="width-100 btn btn-primary" style="border-color: #005689;background:#005689 !important;font-size: 20px;padding: 0 14px;">

                                                    登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>


                                    <div class="space-6"></div>
                                </div>
                                <!-- /.widget-main -->

                                <%--<div class="toolbar clearfix" style="filter: Alpha(opacity=90); -moz-opacity:0.9; opacity:0.9;">--%>
                                    <%--<div class="foot_con" style="text-align: right;color:#fff">--%>
                                        <%--<a href="http://www.631xiangxun.com" style="color:#fff;padding:10px 0;" >西安翔迅科技</a>--%>
                                        <%--<span>©</span>2014-2016--%>
                                        <%--<div--%>
                                                <%--style="float: right; margin-right: 10px; display: none;">--%>
                                            <%--技术支持：029-88151288--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                            </div>
                            <!-- /.widget-body -->
                        </div>
                        <!-- /.login-box -->

                        <div id="forgot-box" class="forgot-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger">
                                        <i class="ace-icon fa fa-key"></i> 重置密码
                                    </h4>

                                    <div class="space-6"></div>
                                    <p>
                                        请输入获取信息的邮件地址
                                    </p>

                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right"> <input
                                                                type="email" class="form-control" placeholder="Email"/>
															<i class="ace-icon fa fa-envelope"></i> </span>
                                            </label>

                                            <div class="clearfix">
                                                <button type="button"
                                                        class="width-35 pull-right btn btn-sm btn-danger">
                                                    <i class="ace-icon fa fa-lightbulb-o"></i>
                                                    <span class="bigger-110">发送</span>
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                                <!-- /.widget-main -->

                                <div class="toolbar center">
                                    <a href="#" data-target="#login-box"
                                       class="back-to-login-link"> 返回登陆 <i
                                            class="ace-icon fa fa-arrow-right"></i> </a>
                                </div>
                            </div>
                            <!-- /.widget-body -->
                        </div>
                        <!-- /.forgot-box -->

                    </div>
                    <!-- /.position-relative -->

                    <%--<div class="navbar-fixed-top align-right">--%>
                        <%--<br/>--%>
                        <%--&nbsp;--%>
                        <%--<a id="btn-login-dark" href="#">黑</a> &nbsp;--%>
                        <%--<span class="blue">/</span> &nbsp;--%>
                        <%--<a id="btn-login-blur" href="#">蓝</a> &nbsp;--%>
                        <%--<span class="blue">/</span> &nbsp;--%>
                        <%--<a id="btn-login-light" href="#">灰</a> &nbsp; &nbsp; &nbsp;--%>
                    <%--</div>--%>
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->
<!-- inline scripts related to this page -->
<script type="text/javascript">

    jQuery(function ($) {
        $("#username").focus();
        $(document).on('click', '.toolbar a[data-target]', function (e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });
    });

    //you don't need this, just used for changing background
    jQuery(function ($) {
        $('#btn-login-dark').on('click', function (e) {
            $('body').attr('class', 'login-layout');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'blue');
            e.preventDefault();
        });
        $('#btn-login-light').on('click', function (e) {
            $('body').attr('class', 'login-layout light-login');
            $('#id-text2').attr('class', 'grey');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
        });
        $('#btn-login-blur').on('click', function (e) {
            $('body').attr('class', 'login-layout blur-login');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'light-blue');

            e.preventDefault();
        });

    });//
</script>
</body>
</html>