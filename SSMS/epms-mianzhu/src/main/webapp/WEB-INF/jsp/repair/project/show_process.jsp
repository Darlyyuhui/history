<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="btn" uri="/WEB-INF/author-btn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
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
    <script src="${root}/compnents/ECharts/echarts.min.js" type="text/javascript"></script>
    <script src="${root}/compnents/ECharts/theme/shine.js" type="text/javascript"></script>
    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
    <!--[if lte IE 8]>
    <script src="${root}/compnents/ace/js/html5shiv.min.js"></script>
    <script src="${root}/compnents/ace/js/respond.min.js"></script>
    <![endif]-->

</head>
<body class="no-skin">
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${root}/compnents/ace/js/jquery-3.2.1.min.js'>"+"<"+"/script>");
</script>
<!--<![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${root}/compnents/ace/js/jquery-3.2.1.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    var root = "${root}";
</script>
<script src="${root}/compnents/ace/js/ace-elements.min.js"></script>
<script src="${root}/compnents/ace/js/ace.min.js"></script>
<script src="${root}/compnents/ace/js/jquery.validate.min.js"></script>
<script src="${root}/compnents/ace/js/messages_bs_zh.js"></script>
<script src="${root}/js/jquery.placeholder.min.js"></script>
<script src="${root}/js/common.js"></script>
<script src="${root}/compnents/ace/js/bootbox.min.js"></script>
<script src="${root}/compnents/MapFrame/MapFactory/ThirdParty/JSON/json2.js"></script>
<!-- #section:basics/navbar.layout -->
<tags:top></tags:top>
<div style="clear: both"></div>
<style>
     li{list-style: none}
     ul{margin: 0}
    .K_wrap{width: 100%;margin:76px auto 0;min-height: 849px;background: #fff;}
    .K_login{width:100%;border-bottom:1px solid #cfd8dd }
    .login_list{height: 45px; background: #dfe8ef;width:100%;padding-left:50px;}
    .login_list li{line-height: 45px;width: 140px; text-align: center;color:#031829;font-size:18px;cursor: pointer;
        border-top-left-radius:10px;
        border-top-right-radius:10px;}
    .login_list li.on{background: #204e70; color:#fff}
    .k_contanier{position: relative;height:823px;overflow-y: auto}
    .k_silder{width: 200px;height: 100%;background: #dfe8ef;padding: 30px 0 0 50px;}
    .k_silder li{text-align: center;line-height: 50px;cursor: pointer;font-size: 16px;
        border-top-left-radius:2px;
        border-bottom-left-radius:2px;}
    .k_silder li.hot{background:#fff;color:#204e70}
    .k_content{position: absolute;left:200px;right: 0;height: 100%;overflow-y:auto;top:0}
    .callback{position:fixed;right:10px; bottom:30px;width:90px;height:30px;text-align: center;
        line-height: 30px;color:#fff;background:#255f9e ;font-size:16px;border-radius: 40px;cursor: pointer;
        box-shadow: 0 0 5px 5px #5982ae;
    }
</style>
<div class="K_wrap">
    <div class="K_login">
        <ul class="clearfix login_list">
        	<c:forEach items="${topList }" var="item" varStatus="vs">
            <li class="pull-left ${vs.index == 0 ? 'on' : '' }" id="${item.id }">${item.name }</li>
            </c:forEach>
        </ul>
    </div>
    <div class="k_contanier width-100">
        <div id="childDiv" class="k_silder">
            <ul class="sileder_login">
            	<c:forEach items="${firstTopChild }" var="item" varStatus="vs">
                <li ${vs.index == 0 ? 'class=\"hot\"' : '' } id="${item.id }">${item.name }</li>
                </c:forEach>
            </ul>
        </div>
        <div class="k_content">
			<div id="contentDiv" style="width: 98%;"></div>
        </div>
    </div>
</div>
<div class="callback" onclick="goBack()">返<span style="padding:0 5px;"></span>回</div>
<script type="text/javascript">
$(function() {
	
	$(".login_list li").click(function(){
        var old_time = 0;
        if(new Date() - old_time>500){
            old_time = new Date();
            $(this).addClass("on").siblings().removeClass("on");
            changeChild($(this).attr("id"));
            $(".k_contanier").css("left","100%").animate({left: '0'}, 500)
        }
    });
	buildChildChange();
	
	getProcessDetail($(".k_silder li.hot").attr("id"));
});

function buildChildChange() {
	$(".k_silder li").click(function(){
        var old_time = 0;
        if(new Date() - old_time>500) {
            old_time = new Date();
            $(this).addClass("hot").siblings().removeClass("hot");
            getProcessDetail($(this).attr("id"));
            $(".k_content").css("top", "100%").animate({"top": '0'}, 500);
        }
    });
}

function changeChild(id) {
	$.post(
		"${root}/repair/project/changeChild/"+id+"/",
		function(data) {
			$("#childDiv").html(data);
			buildChildChange();
			getProcessDetail($(".k_silder li.hot").attr("id"));
		}
	);
}

function getProcessDetail(stageId) {
	$(".ad-preloads").remove();
	$.post(
		"${root}/repair/project/processDetail/${proId}/"+stageId+"/",
		function(data) {
			$("#contentDiv").empty().html(data);
		}
	);
}

function goBack() {
	var url = "${root}/home/admin/";
	if ("${backType}" == "list") {
		url = "${root}/repair/project/list/${menuid}/?isgetsession=1"
	}
	window.location.href = url;
}
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>