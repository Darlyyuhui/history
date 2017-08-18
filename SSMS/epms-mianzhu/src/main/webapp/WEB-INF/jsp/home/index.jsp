<%@ page import="java.util.Date" %>
<%@ page import="com.xiangxun.atms.framework.util.DateUtil" %>
<%@ page import="com.xiangxun.atms.framework.constant.FORMAT" %>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%
    java.util.Date date = new java.util.Date();
    Date beginOfMonth = DateUtil.getBeginOfMonth(date, 0);
    Date endOfMonth = DateUtil.getEndOfMonth(date, 0);
    String startDate = DateUtil.formatDate(FORMAT.DATETIME.DEFAULT, beginOfMonth);
    String endDate = DateUtil.formatDate(FORMAT.DATETIME.DEFAULT, endOfMonth);
    request.setAttribute("startDate", startDate);
    request.setAttribute("endDate", endDate);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>土壤安全管理与绿色食品基地展示平台</title>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

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
    <link rel="stylesheet" href="${root}/css/GisStyle/Common.css"/>
    <link rel="stylesheet" href="${root}/compnents/ace/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${root}/compnents/ace/css/ace-rtl.min.css"/>
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${root}/compnents/ace/css/ace-ie.min.css"/>
    <![endif]-->
    <!-- inline styles related to this page -->
    <!-- ace settings handler -->
    <script src="${root}/compnents/ace/js/jquery-1.7.2.min.js"></script>
    <script src="${root}/compnents/ace/js/ace-extra.min.js"></script>
    <!-- inline styles related to this page -->
    <!-- ace settings handler 
    <script src="${root}/compnents/ace/js/jquery-1.7.2.min.js"></script>
    <script src="${root}/compnents/ace/js/ace-extra.min.js"></script>
    -->
    <script src="${root}/compnents/fusioncharts/js/fusioncharts.js" type="text/javascript"></script>
    <script src="${root}/compnents/ECharts/echarts.min.js" type="text/javascript"></script>
	<script src="${root}/compnents/ECharts/theme/shine.js" type="text/javascript"></script>
    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
    <!--[if lte IE 8]>
    <script src="${root}/compnents/ace/js/html5shiv.min.js"></script>
    <script src="${root}/compnents/ace/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="no-skin" style="overflow-x: hidden;">
<tags:top></tags:top>
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container" style="margin-top:76px">
<script type="text/javascript">
    try {
        ace.settings.check('main-container', 'fixed') 
    } catch (e) {
    }
</script>
<tags:menu menus="${menus}"></tags:menu>
<div class="main-content">
<div class="page-content" style="padding:2px 2px;">
<div class="ace-settings-container hide" id="ace-settings-container">
    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
        <i class="ace-icon fa fa-cog bigger-150"></i>
    </div>

    <div class="ace-settings-box clearfix" id="ace-settings-box" style="min-height: 70px;">
        <div class="pull-left width-50">
            <div class="ace-settings-item">
                <div class="pull-left">
                    <select id="skin-colorpicker" class="hide" data-userskin="${skin }">
                        <option data-skin="no-skin" value="#438EB9" selected>
                            #438EB9
                        </option>
                        <option data-skin="skin-1" value="#222A2D">
                            #222A2D
                        </option>
                        <option data-skin="skin-2" value="#C6487E">
                            #C6487E
                        </option>
                        <option data-skin="skin-3" value="#D0D0D0">
                            #D0D0D0
                        </option>
                    </select>
                </div>
                <span>&nbsp; Choose Skin</span>
            </div>
        </div>
        <!-- /.pull-left -->
    </div>
    <!-- /.ace-settings-box -->
</div>
<!-- /.ace-settings-container -->
<div class="page-content-area" id="welcome-page">
<c:if test="${moudelid eq '0'}">
<!-- 地图页签内容 -->
    <div class="row">
        <div id="map" class="col-xs-12" style="position:relative;height:807px;border:0;background-image:url(${root}/images/back.png)"  >
            <div id="mapNavigationBox"></div>
            <div id="mapViewSwitcher"></div>
            <div id="mapZoomSlider"></div>
            <!--
            <div id="maplenged">
                <p id="maplengedtitle"><span>地图图例</span></p>
                <div id="maplengedcontent"></div>
            </div>
            -->
        </div>
    </div>
    <!-- 地图统一样式引入 -->
   <tags:mapheader  mapTools="true"></tags:mapheader>
 <!-- 地图页签内容结束 -->
</div>
</c:if>
<c:if test="${moudelid ne '0'}">
    <iframe height="100%" width="100%" id="welcomeiframe" src="${welcomeurl}"
            style="border:0;margin:0;padding:0" scrolling="no"></iframe>
</c:if>
<div class="footer" style="padding-top:0;margin:0;z-index: 1999999">
    <div class="footer-inner" style="background: #012845">
        <!-- #section:basics/footer -->
        <div class="footer-content" style="z-index:100;position: fixed;border-top:0px double #E5E5E5;bottom: -8px;background: #012845;padding: 0;padding-bottom:6px;left: 0;right: 0">
                        <span class="bigger-120" style="color: #666"> <span class="blue bolder"><a
                                href="http://www.631xiangxun.com">西安翔迅科技有限责任公司 &copy; 2017-2020</a></span> </span>
        </div>
        <!-- /section:basics/footer -->
    </div>
</div>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i
        class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i> </a>
</div>
</div>

</div>
</div>
<textarea id="chart-xml" style="display: none"></textarea>
<!--[if IE]>
<script type="text/javascript">
    window.jQuery    || document.write("<script src='${root}/compnents/ace/js/jquery1x.min.js'>" + "<" + "/script>");
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
<script src="${root}/compnents/ace/js/jquery-ui.custom.min.js"></script>
<script src="${root}/compnents/ace/js/jquery.ui.touch-punch.min.js"></script>
<!-- ace scripts -->
<script src="${root}/compnents/ace/js/ace-elements.min.js"></script>
<script src="${root}/compnents/ace/js/ace.min.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
/**/
jQuery(function ($) {
    var moudle = '${moudelid}';
    if (moudle == '0') {
        $("#sidebar").remove();
    }
});

// 页面加载成功后，初始化页签和地图
$(document).ready(function () {
    $("#welcomeiframe").css("height", $(window).height() - 120 + "px");
    $(".map-content").height($(window).height() - 140);
    $("#allmap").height($(window).height() - 150);
    // 页面加载成功后，初始化页签和地图
    initMap("map");

    // 初始化地图
    function initMap(mapDiv) {
            if (typeof window["mapTag"] == "undefined")return;
        mapTag().init(mapDiv, function (map) {
            var symbols = map.getSymbolConfig();
            map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
            map.hideInfowindow();
        });
    }
});

</script>
<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<form style="display: none" id="farword-form" action="${root}/forward/page/" method="post">
    <input name="farurl" id="far-url">
</form>

<script>

    var pageWidth = window.innerWidth,
            pageHeight = window.innerHeight;
    if (typeof pageWidth != "number"){
        if (document.compatMode == "CSS1Compat"){
            pageWidth = document.documentElement.clientWidth;
            pageHeight = document.documentElement.clientHeight;
        } else {
            pageWidth = document.body.clientWidth;
            pageHeight = document.body.clientHeight;
        }
    };
    $("#map").height( pageHeight-83);
    $(function(){
        function dragDrop(wrap){
            //开关
            var flag = false;
                                                                                                                                                                                                                                                                                                                                                                                                                                        var title = wrap.find(".d_title");
            var close = wrap.find(".t_close");
            title.mousedown(function(i){
                var i = i || window.event;
                    flag = true;//打开开关
                // 获取父类dragbox，需要被拖拽的div
                var dragbox = $(this).parent(wrap);
                //鼠标点击的X和Y坐标
                var a = i.clientX;
                var b = i.clientY;
                //当前距离左顶点的位置
                var left = dragbox.offset().left;
                var top = dragbox.get(0).offsetTop;
                // 最大的限制，边距
                var maxLeft = $("#map").width() - dragbox.width();
                var maxTop = $("#map").height() - dragbox.height();
                //开始拖拽
                $("#map").mousemove(function(e){
                    var e = e || window.event;
                    //如果打开状态
                    if(flag){
                        var x = e.clientX;
                        var y = e.clientY;
                        //计算坐标位置
                        var _left = x - a +left;
                        var _top = y - b + top;

                        if(_left <= 0){_left = 0;}
                        if(_top <= 0){_top = 0;}

                        // 限制溢出的边距
                        if(_left >= maxLeft){_left = maxLeft;}
                        if(_top >= maxTop){_top = maxTop;}

                        $(wrap).css({left:_left,top:_top});
                    }
                }).mouseup(function(){
                    flag = false;// 关闭
                });
            });
            //关闭按钮
            close.click(function(){
                $(this).parent().parent().remove();
            });
        };
        dragDrop($(".dragbox-1"));
        dragDrop($(".dragbox-2"));
        dragDrop($(".dragbox-3"));
    });
    function getUrlPra(parme){
        if(!parme)return;

        if(!window.location.search)return;
        var h = window.location.search.substr(1);
        var obj = {};
        var arr = h.split("&");
        for(var i=0;i<arr.length;i++){
            var str = arr[i];
            var newArr = str.split("=");
            obj[newArr[0]] = newArr[1];
        };
        for(var key in obj){
            if(key == parme)return obj[key];
        };
        return null;
    };
   var url_moudelId = getUrlPra("moudelId");


   url_moudelId == "170620141051155588af2771661c5827" || "1706061722565164bb9a629d464a0b8b"?$("iframe").css("min-height","1000px"):$("iframe").css("min-height","600px");
</script>
</body>
</html>

