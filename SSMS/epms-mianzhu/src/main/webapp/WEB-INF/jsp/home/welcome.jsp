<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${root}/compnents/ace/css/bootstrap.min.css" />
<link rel="stylesheet" href="${root}/compnents/ace/css/font-awesome.min.css" />
<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-fonts.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${root}/compnents/ace/css/ace.min.css" id="main-ace-style" />

<!--[if lte IE 9]>
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-part2.min.css"/>
<![endif]-->
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-skins.min.css" />
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-rtl.min.css" />
<!--[if lte IE 9]>
<link rel="stylesheet" href="${root}/compnents/ace/css/ace-ie.min.css"/>
<![endif]-->
<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src="${root}/compnents/ace/js/jquery-3.2.1.min.js"></script>
<script src="${root}/compnents/ace/js/ace-extra.min.js"></script>
<script src="${root}/compnents/ace/js/flot/jquery.flot.min.js"></script>
<script src="${root}/compnents/ace/js/flot/jquery.flot.pie.min.js"></script>
<script src="${root}/compnents/ace/js/flot/jquery.flot.resize.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!--[if lte IE 8]>
<script src="${root}/compnents/ace/js/html5shiv.min.js"></script>
<script src="${root}/compnents/ace/js/respond.min.js"></script>
<![endif]-->
<div style="background-color:#fff;">
    <div class="row">
        <div class="col-xs-12">
        <div class="alert alert-block alert-success" style="margin-bottom: 5px;">
            <i class="ace-icon fa fa-check green"></i> 欢迎使用
            <strong class="green">智慧环保综合管控平台
                <small>(v1.0.0)</small>
            </strong>, 稳定高效，功能丰富和易于使用的管理平台.
        </div>

        <div class="row">
            <div class="col-sm-4 infobox-container">
                <div class="widget-box transparent">
                    <div class="widget-header widget-header-flat">
                        <h4 class="widget-title lighter pull-left">
                            <i class="ace-icon fa fa-bolt orange"></i>
                            污染源在线监控
                        </h4>
                        <h5 class="pull-right">总数：500</h5>
                    </div>

                    <div class="widget-body" style="display: block;">
                        <div class="infobox infobox-green">
                            <div class="infobox-icon">
                                <i class="ace-icon fa fa-bolt"></i>
                            </div>

                            <div class="infobox-data">
                                <span class="infobox-data-number">300</span>

                                <div class="infobox-content">正常监测点数</div>
                            </div>

                        </div>
                        <div class="infobox infobox-red">
                            <div class="infobox-icon">
                                <i class="ace-icon fa fa-bolt"></i>
                            </div>

                            <div class="infobox-data">
                                <span class="infobox-data-number">100</span>

                                <div class="infobox-content">异常监测点数</div>
                            </div>
                        </div>
                        <div class="infobox infobox-pink">
                            <div class="infobox-icon">
                                <i class="ace-icon fa fa-bolt"></i>
                            </div>

                            <div class="infobox-data">
                                <span class="infobox-data-number">100</span>

                                <div class="infobox-content">未接入点数</div>
                            </div>
                            <%--<div class="stat stat-important">10%</div>--%>
                        </div>
                    </div>
                    <!-- /.widget-body -->
                </div>
                <!-- /.widget-box -->
            </div>
            <!-- /.col -->
            <div class="col-sm-4 infobox-container">
                <div class="widget-box transparent">
                    <div class="widget-header widget-header-flat">
                        <h4 class="widget-title lighter pull-left">
                            <i class="ace-icon fa fa-codepen orange"></i>
                            污染源工况监测
                        </h4>

                        <h5 class="pull-right">总数：100</h5>
                    </div>

                    <div class="widget-body" style="display: block;">
                        <div class="infobox infobox-green">
                            <div class="infobox-icon">
                                <i class="ace-icon fa fa-codepen"></i>
                            </div>

                            <div class="infobox-data">
                                <span class="infobox-data-number">50</span>

                                <div class="infobox-content">正常监测点数</div>
                            </div>

                        </div>
                        <div class="infobox infobox-red">
                            <div class="infobox-icon">
                                <i class="ace-icon fa fa-codepen"></i>
                            </div>

                            <div class="infobox-data">
                                <span class="infobox-data-number">20</span>

                                <div class="infobox-content">异常监测点数</div>
                            </div>
                        </div>
                        <div class="infobox infobox-pink">
                            <div class="infobox-icon">
                                <i class="ace-icon fa fa-codepen"></i>
                            </div>

                            <div class="infobox-data">
                                <span class="infobox-data-number">30</span>

                                <div class="infobox-content">未接入点数</div>
                            </div>
                        </div>
                    </div>
                    <!-- /.widget-body -->
                </div>
                <!-- /.widget-box -->
            </div>
            <!-- /.col -->
            <div class="col-sm-4 infobox-container">
                <div class="widget-box transparent">
                    <div class="widget-header widget-header-flat">
                        <h4 class="widget-title lighter pull-left">
                            <i class="ace-icon fa fa-video-camera orange"></i>
                            视频监控
                        </h4>

                        <h5 class="pull-right">总数：150</h5>
                    </div>

                    <div class="widget-body" style="display: block;">
                        <div class="infobox infobox-green">
                            <div class="infobox-icon">
                                <i class="ace-icon fa fa-video-camera"></i>
                            </div>

                            <div class="infobox-data">
                                <span class="infobox-data-number">120</span>

                                <div class="infobox-content">正常视频路数</div>
                            </div>
                        </div>
                        <div class="infobox infobox-red">
                            <div class="infobox-icon">
                                <i class="ace-icon fa fa-video-camera"></i>
                            </div>

                            <div class="infobox-data">
                                <span class="infobox-data-number">30</span>

                                <div class="infobox-content">异常视频路数</div>
                            </div>
                        </div>
                    </div>
                    <!-- /.widget-body -->
                </div>
                <!-- /.widget-box -->
            </div>
            <!-- /.col -->
        </div>
        <!-- 第一行结束 -->
        <div class="row" style="margin-top: 5px;">
            <div class="col-sm-4">
                <div class="widget-box transparent">
                    <div class="widget-header widget-header-flat">
                        <h4 class="widget-title lighter">
                            <i class="ace-icon fa fa-signal"></i>
                            声尘监控
                        </h4>

                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main padding-4">
                            <div id="sales-charts">
                            </div>
                        </div>
                        <!-- /.widget-main -->
                    </div>
                    <!-- /.widget-body -->
                </div>
            </div>
            <div class="col-sm-4">
                <div class="widget-box" style="padding-top:6px;">
                    <div class="widget-header widget-header-flat widget-header-small">
                        <h5 class="widget-title">
                            <i class="ace-icon fa fa-signal"></i>
                            油烟监控
                        </h5>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <!-- #section:plugins/charts.flotchart -->
                            <div id="piechart-placeholder"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="widget-box" style="padding-top:6px;">
                    <div class="widget-header widget-header-flat widget-header-small">
                        <h5 class="widget-title">
                            <i class="ace-icon fa fa-signal"></i>
                            油烟监控2
                        </h5>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <!-- #section:plugins/charts.flotchart -->
                            <div id="chart2"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
            <div class="row" style="margin-top: 5px;">
                <div class="col-sm-6">
                    <div class="widget-box transparent">
                        <div class="widget-header widget-header-flat">
                            <h4 class="widget-title lighter">
                                <i class="ace-icon fa fa-star orange"></i>
                                工单状态
                            </h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body" style="display: block;">
                            <div class="widget-main no-padding">
                                <table class="table table-bordered table-striped">
                                    <thead class="thin-border-bottom">
                                    <tr>
                                        <th>
                                            <i class="ace-icon fa fa-caret-right blue"></i>序号
                                        </th>
                                        <th>
                                            <i class="ace-icon fa fa-caret-right blue"></i>工单名称
                                        </th>
                                        <th class="hidden-480">
                                            <i class="ace-icon fa fa-caret-right blue"></i>状态
                                        </th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>XX工单</td>
                                        <td class="hidden-480">
                                            <span class="label label-info arrowed-right arrowed-in">进行中</span>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>2</td>
                                        <td>
                                            <b class="green">XX工单2</b>
                                        </td>
                                        <td class="hidden-480">
                                            <span class="label label-success arrowed-in arrowed-in-right">完成</span>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>3</td>
                                        <td>
                                            <small>
                                                <s class="red">XX历史工单</s>
                                            </small>
                                            <b class="green">XX变更工单</b>
                                        </td>
                                        <td class="hidden-480">
                                            <span class="label label-danger arrowed">紧急工单</span>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>4</td>
                                        <td>XX工单3</td>
                                        <td class="hidden-480">
                                                                                <span class="label arrowed">
                                                                                    <s>超期</s>
                                                                                </span>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>5</td>
                                        <td>XX工单4</td>
                                        <td class="hidden-480">
                                            <span class="label label-warning arrowed arrowed-right">日常</span>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.widget-main -->
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="widget-box transparent">
                        <div class="widget-header widget-header-flat">
                            <h4 class="widget-title lighter">
                                <i class="ace-icon fa fa-star orange"></i>
                                违规记录
                            </h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body" style="display: block;">
                            <div class="widget-main no-padding">
                                <table class="table table-bordered table-striped">
                                    <thead class="thin-border-bottom">
                                    <tr>
                                        <th>
                                            <i class="ace-icon fa fa-caret-right blue"></i>序号
                                        </th>

                                        <th>
                                            <i class="ace-icon fa fa-caret-right blue"></i>厂家
                                        </th>

                                        <th class="hidden-480">
                                            <i class="ace-icon fa fa-caret-right blue"></i>违规情况
                                        </th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>某某公司</td>
                                        <td class="hidden-480">
                                            <span class="label label-info arrowed-right arrowed-in">违规排放污水</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>某某企业</td>
                                        <td class="hidden-480">
                                            <span class="label label-success arrowed-in arrowed-in-right">未安装净化器</span>
                                        </td>
                                    </tr>
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
<script src="${root}/compnents/ace/js/jquery-ui.custom.min.js"></script>
<script src="${root}/compnents/ace/js/jquery.ui.touch-punch.min.js"></script>
<!-- ace scripts -->
<script src="${root}/compnents/ace/js/ace-elements.min.js"></script>
<script src="${root}/compnents/ace/js/ace.min.js"></script>
<script type="text/javascript">
    // 创建图标--曲线图
    var d1 = [];
    for (var i = 0; i < Math.PI * 2; i += 0.5) {
        d1.push([i, Math.sin(i)]);
    }

    var d2 = [];
    for (var i = 0; i < Math.PI * 2; i += 0.5) {
        d2.push([i, Math.cos(i)]);
    }

    var d3 = [];
    for (var i = 0; i < Math.PI * 2; i += 0.2) {
        d3.push([i, Math.tan(i)]);
    }


    var sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
    $.plot("#sales-charts", [
        { label: "日均噪声", data: d1 },
        { label: "日均粉尘", data: d2 },
        { label: "其他污染", data: d3 }
    ], {
        hoverable: true,
        shadowSize: 0,
        series: {
            lines: { show: true },
            points: { show: true }
        },
        xaxis: {
            tickLength: 0
        },
        yaxis: {
            ticks: 10,
            min: -2,
            max: 2,
            tickDecimals: 3
        },
        grid: {
            backgroundColor: { colors: [ "#fff", "#fff" ] },
            borderWidth: 1,
            borderColor:'#555'
        }
    });

    // 创建图标--柱状图
    var chart2 = $('#chart2').css({'width':'90%' , 'min-height':'180px'});
    $.plot(chart2, [
        {
            label: "Bar",
            data: [[1, 13],[2, 11], [3, 7]],
            color: "#DC5625"
        },
        {
            label: "Bar2",
            data: [[1, 6],[2, 9], [3, 17]],
            color: "#007ACC"
        }
    ], {
        series: {
            bars: {
                show: true,
                barWidth: 0.5,
                align: "center",
                horizontal: false
            }
        },
        xaxis: {
            ticks: [[1, "columnA"],[2, "columnB"],[3, "columnC"]]
        }

    });

    // 创建图标--饼状图
    var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'180px'});
    var data = [
        { label: "social networks",  data: 38.7, color: "#68BC31"},
        { label: "search engines",  data: 24.5, color: "#2091CF"},
        { label: "ad campaigns",  data: 8.2, color: "#AF4E96"},
        { label: "direct traffic",  data: 18.6, color: "#DA5430"},
        { label: "other",  data: 10, color: "#FEE074"}
    ]
    function drawPieChart(placeholder, data, position) {
        $.plot(placeholder, data, {
            series: {
                pie: {
                    show: true,
                    tilt:0.8,
                    highlight: {
                        opacity: 0.25
                    },
                    stroke: {
                        color: '#fff',
                        width: 2
                    },
                    startAngle: 2
                }
            },
            legend: {
                show: true,
                position: position || "ne",
                labelBoxBorderColor: null,
                margin:[-30,15]
            }
            ,
            grid: {
                hoverable: true,
                clickable: true
            }
        })
    }
    drawPieChart(placeholder, data);
</script>