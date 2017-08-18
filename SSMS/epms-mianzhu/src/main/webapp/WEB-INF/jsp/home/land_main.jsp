<%@ page pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
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
<script src="${root}/compnents/ace/js/jquery-1.7.2.min.js"></script>
<script src="${root}/compnents/ace/js/ace-extra.min.js"></script>
<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!--[if lte IE 8]>
<script src="${root}/compnents/ace/js/html5shiv.min.js"></script>
<script src="${root}/compnents/ace/js/respond.min.js"></script>
<![endif]-->

<div style="background-color:#fff;" id="wrap">
    <div class="left" style="margin-bottom:0">
        <div class="widget-box transparent" style="margin-top:0;">
            <div class="widget-body" style="display: block;">
                <div class="infobox infobox-green">
                    <div class="infobox-icon">
                        <i class="ace-icon fa  fa-lastfm"></i>
                    </div>
                    <div class="infobox-data">
                        <span class="infobox-data-number"><fmt:formatNumber pattern="#" value="${topMap['SUM_AREA']}" /> 亩 </span>
                        <div class="infobox-content">已登记地块</div>
                    </div>
                </div>
                <div class="infobox infobox-red">
                    <div class="infobox-icon">
                        <i class="ace-icon fa fa-lastfm"></i>
                    </div>

                    <div class="infobox-data">
                        <span class="infobox-data-number"><fmt:formatNumber pattern="#" value="${topMap['WR_AREA']}" /> 亩</span>
                        <div class="infobox-content">污染地块</div>
                    </div>
                </div>

                <div class="infobox infobox-success">
                    <div class="infobox-icon">
                        <i class="ace-icon fa fa-lastfm"></i>
                    </div>

                    <div class="infobox-data">
                        <span class="infobox-data-number">
                        <fmt:formatNumber pattern="#.00" value="${topMap['PERC']}" /> %</span>
                        <div class="infobox-content">污染率</div>
                    </div>
                </div>
                
                <div class="infobox infobox-blue">
                    <div class="infobox-icon">
                        <i class="ace-icon fa fa-lastfm"></i>
                    </div>

                    <div class="infobox-data">
                        <span class="infobox-data-number"><fmt:formatNumber pattern="#" value="${topMap['M_START_NUM']}" /> 个</span>
                        <div class="infobox-content">已启动采样</div>
                    </div>
                </div>
                
                <div class="infobox infobox-pink">
                    <div class="infobox-icon">
                        <i class="ace-icon fa fa-lastfm"></i>
                    </div>

                    <div class="infobox-data">
                        <span class="infobox-data-number">
                        <c:if test="${empty topMap['M_END_NUM']}">0</c:if>
                        <c:if test="${not empty topMap['M_END_NUM']}"><fmt:formatNumber pattern="#" value="${topMap['M_END_NUM']}" /></c:if>
                         个</span>
                        <div class="infobox-content">已完成采样</div>
                    </div>
                </div>
                
                <div class="infobox infobox-blue">
                    <div class="infobox-icon">
                        <i class="ace-icon fa fa-lastfm"></i>
                    </div>

                    <div class="infobox-data">
                        <span class="infobox-data-number">
                        <c:if test="${empty topMap['R_START_NUM']}">0</c:if>
                        <c:if test="${not empty topMap['R_START_NUM']}"><fmt:formatNumber pattern="#" value="${topMap['R_START_NUM']}" /></c:if>
                         个</span>
                        <div class="infobox-content">已启动土壤修复项目</div>
                    </div>
                </div>
                
                <div class="infobox infobox-pink">
                    <div class="infobox-icon">
                        <i class="ace-icon fa fa-lastfm"></i>
                    </div>

                    <div class="infobox-data">
                        <span class="infobox-data-number">
                        <c:if test="${empty topMap['R_END_NUM']}">0</c:if>
                        <c:if test="${not empty topMap['R_END_NUM']}"><fmt:formatNumber pattern="#" value="${topMap['R_END_NUM']}" /></c:if>
                         个</span>
                        <div class="infobox-content">已验收土壤修复项目</div>
                    </div>
                </div>
                
            </div><!-- /.widget-body -->
        </div><!-- /.widget-box -->
    </div>
    <hr style="margin:10px 0"/>
    <div class="row" style="margin-top: 5px;">
             <div class="col-sm-6">
                 <div class="widget-box transparent">
                     <div class="widget-header widget-header-flat">
                         <h4 class="widget-title lighter">
                             <i class="ace-icon fa fa-star orange"></i>
                             最近登记地块
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
                                         <i class="ace-icon fa fa-caret-right blue"></i>地块编号
                                     </th>
                                     <th>
                                         <i class="ace-icon fa fa-caret-right blue"></i>面积（亩）
                                     </th>
                                     <th>
                                         <i class="ace-icon fa fa-caret-right blue"></i>责任人
                                     </th>
                                 </tr>
                                 </thead>
                                 <tbody>
                                 <c:forEach items="${newLand }" var="item">
                                  <tr>
                                      <td>
                                      <div style="width:150px;height:22px;margin-top:2px;white-space:nowrap;overflow:hidden;-o-text-overflow:ellipsis;text-overflow:ellipsis;"
                             			title="${item['CODE'] }">${item['CODE'] }</div>
                                      </td>
                                      <td>
                                      	<div style="width:150px;height:22px;margin-top:2px;white-space:nowrap;overflow:hidden;-o-text-overflow:ellipsis;text-overflow:ellipsis;"
                             			title="${item['AREA'] }">${item['AREA'] }</div>
                                      </td>
                                      <td>
                 					  	<div style="width:150px;height:22px;margin-top:2px;white-space:nowrap;overflow:hidden;-o-text-overflow:ellipsis;text-overflow:ellipsis;"
                             			title="${item['OWNER_NAME'] }">${item['OWNER_NAME'] }</div>
                 					  </td>
                                  </tr>
						</c:forEach>
						<c:if test="${newList!=null}">
			                <c:forEach begin="1" end="${3-fn:length(newList)}">
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
             
		<div class="col-sm-6">
                 <div class="widget-box transparent">
                     <div class="widget-header widget-header-flat">
                         <h4 class="widget-title lighter">
                             <i class="ace-icon fa fa-star orange"></i>
                             正在执行采样任务
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
                                         <i class="ace-icon fa fa-caret-right blue"></i>任务名称
                                     </th>
                                     <th>
                                         <i class="ace-icon fa fa-caret-right blue"></i>采集样品
                                     </th>
                                     <th>
                                         <i class="ace-icon fa fa-caret-right blue"></i>采样个数
                                     </th>
                                 </tr>
                                 </thead>
                                 <tbody>
                                 <c:forEach items="${sampling }" var="item">
                                  <tr>
                                      <td>
                                      	<div style="width:100%;height:22px;margin-top:2px;white-space:nowrap;overflow:hidden;-o-text-overflow:ellipsis;text-overflow:ellipsis;"
                             			title="${item['NAME'] }">${item['NAME'] }</div>
                                      </td>
                                      <td><tags:xiangxuncache keyName="Dic" typeCode="SAMPLING_TYPES" id="${item['SAMPLE_CODE'] }"/></td>
                 					  <td>${item['NUM_'] }</td>
                                  </tr>
						</c:forEach>
						<c:if test="${sampling!=null}">
			                <c:forEach begin="1" end="${3-fn:length(sampling)}">
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
    <hr style="margin:10px 0"/>
    <div class="row" style="margin-top:-8px;">
    	<div class="col-sm-6 col-lg-6 col-md-6">
            <div class="widget-box transparent">
                <div class="widget-header widget-header-flat">
                    <h4 class="widget-title lighter">
                        <i class="ace-icon fa fa-pie-chart"></i>
                        	地块类型分析
                    </h4>

                    <div class="widget-toolbar">
                        <a href="#" data-action="collapse">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="widget-body">
                    <div id="product-charts" class="widget-main padding-4" style="width: 100%; height: 260px;">
                    </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->
            </div>
        </div>

        <div class="col-sm-6 col-lg-6 col-md-6">
            <div class="widget-box transparent">
                <div class="widget-header widget-header-flat">
                    <h4 class="widget-title lighter">
                        <i class="ace-icon fa fa-signal"></i>
                        	当前采样情况
                    </h4>

                    <div class="widget-toolbar">
                        <a href="#" data-action="collapse">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="widget-body">
                    <div id="area-charts" class="widget-main padding-4" style="width: 100%; height: 260px;">
                    </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->
            </div>
        </div>
    </div>
    <hr style="margin:10px 0"/>
    <div class="row" style="margin-top: 5px;">
             <div class="col-sm-12">
                 <div class="widget-box transparent">
                     <div class="widget-header widget-header-flat">
                         <h4 class="widget-title lighter">
                             <i class="ace-icon fa fa-star orange"></i>
                             土壤修复项目进度跟踪
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
                                         <i class="ace-icon fa fa-caret-right blue"></i>项目名称
                                     </th>
                                     <th>
                                         <i class="ace-icon fa fa-caret-right blue"></i>修复技术
                                     </th>
                                     <th>
                                         <i class="ace-icon fa fa-caret-right blue"></i>修复面积
                                     </th>
                                     <th>
                                         <i class="ace-icon fa fa-caret-right blue"></i>开始时间
                                     </th>
                                     <th>
                                         <i class="ace-icon fa fa-caret-right blue"></i>当前进度
                                     </th>
                                 </tr>
                                 </thead>
                                 <tbody>
                                 <c:forEach items="${repairList }" var="item">
                                  <tr>
                                      <td>
                                      	<div style="width:200px;height:22px;margin-top:2px;white-space:nowrap;overflow:hidden;-o-text-overflow:ellipsis;text-overflow:ellipsis;"
                             			title="${item['NAME'] }">${item['NAME'] }</div>
                                      </td>
                                      <td><tags:xiangxuncache keyName="Dic" typeCode="LAND_REPAIR_TECHNOLOGY" id="${item['TECHNOLOGY'] }"/></td>
                 					  <td>${item['AREA'] }</td>
                 					  <td><fmt:formatDate value="${item['BEGIN_TIME'] }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                 					  <td><tags:xiangxuncache keyName="Dic" typeCode="LAND_REPAIR_SCHEDULE" id="${item['SCHEDULE'] }"/></td>
                                  </tr>
								</c:forEach>
								<c:if test="${repairList!=null}">
				                <c:forEach begin="1" end="${3-fn:length(repairList)}">
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
<script src="${root}/compnents/ECharts/echarts.min.js" type="text/javascript"></script>
<script src="${root}/compnents/ECharts/theme/shine.js" type="text/javascript"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    $("#wrap").css("min-height",$("#wrap").parent().height()+"px").css("overflow","auto");
</script>
<tags:echart divId="area-charts" option="${lineChart}" theme="shine" />
<tags:echart divId="product-charts" option="${pieChart}" theme="shine" />