<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
<link id="DevCss" href="${root}/css/deviceindex.css" type="text/css" rel="stylesheet">
<script src="${root}/compnents/fusioncharts/js/FusionCharts.js" type="text/javascript" ></script>
<script  type="text/javascript">
//切换皮肤方法
var d=window.parent.document.getElementById("skin-sel");
if(d!=null){
var value= d.options[d.selectedIndex].value;
if(value=="1"){
		$("#DevCss").attr("href","${root}/css/deviceindex.css");
	}else if(value=="2"){
		$("#DevCss").attr("href","${root}/cssGreen/deviceindex.css");
	}else if(value=="3"){
		$("#DevCss").attr("href","${root}/cssDarkBlue/deviceindex.css");
	}else{
	}
	//监听主页皮肤切换
	$(d).bind("change", function(){
		var  d1=window.parent.document.getElementById("skin-sel");
		var value1= d1.options[d1.selectedIndex].value;
		if(value1=="1"){
			$("#DevCss").attr("href","${root}/css/deviceindex.css");
		}else if(value1=="2"){
			$("#DevCss").attr("href","${root}/cssGreen/deviceindex.css");
		}else if(value1=="3"){
			$("#DevCss").attr("href","${root}/cssDarkBlue/deviceindex.css");
		}else{
		
		}
	});
}
</script>
<div class="conten_box" style="height:660px;overflow:hidden;">
	<div class="introduction">
		<div class="row-fluid" style="margin: 0px; display: block; background-position: 50% 100%; background-repeat: no-repeat no-repeat;">
			<h4 class="title_intro">问题管理--本周问题信息列表
				<font>&nbsp;&nbsp;<a href="${root}/question/info/list/160331160036185bb01465507299602b/" style="color: blue; font-weight: lighter;">全部</a></font>
			</h4>
			<div class="mar_5" style="overflow-x:auto;">
		      	<table class="table table-striped table-bordered table-condensed table-style" id="table">
					<thead>
						<tr>
							<th>问题标题</th>
							<th>问题详情</th>
							<th>录入人员</th>
							<th>录入时间</th>
						</tr>
					</thead>
					<c:if test="${pageList.result[0]==null}">
			  			<tr>
							<td colspan="4">
								<div style='text-align: center;margin-right: 40px;'>
									<font color='red'>没有查到结果。</font>
								</div>
							</td>
						</tr>
						<c:forEach begin="1" end="7">
							<tr>
								<td colspan="4">&nbsp;</td>
							</tr>
						</c:forEach>
			  		</c:if>
			  		<c:if test="${pageList.result[0]!=null}">
						<tbody id="tbody">
						  	<c:forEach items="${pageList.result}" var="questionInfo">
							  	<tr onclick="rowOnclick(this)" data="${questionInfo.id}">
									<td width="20%"><div style="height:16px;width:220px;overflow:hidden;" title="${questionInfo.title}">${questionInfo.title}</div></td>
									<td width="20%"><div style="height:16px;width:220px;overflow:hidden;" title="${questionInfo.content}">${questionInfo.content}</div></td>
									<td><tags:xiangxuncache keyName="username_cache" id="${questionInfo.operator}" /></td>
									<td><fmt:formatDate value="${questionInfo.insertTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									
								</tr>	
							  </c:forEach>
							  <c:if test="${pageList.result != null}">
								<c:forEach begin="1" end="${8-fn:length(pageList.result)}">
									<tr>
										<td colspan="4">&nbsp;</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</c:if>
				</table>
			</div>
		</div>
		<div class="row-fluid">
			<div class="pull-left" style="width:65%; margin: 0px;">
				<div class="shebei_info mar_l5 mar_r5" style="min-height:290px;">
					<h3 class="title_intro title-border">
						<span><img src="${root}/images/picone/zhuxing.png"></span>本周-统计频发故障柱状图
					</h3>
					<div class="scrollBox" style="margin: 0; min-height: 240px;">
						<div id="chart1" style="width:100%;margin:0 auto;"></div>
					</div>
				</div>
			</div>
			<div class="pull-right" style="width:35%;">
				<div class="shebei_info mar_l5 mar_r5" style="min-height:290px;">
					<h3 class="title_intro title-border">
						<span><img src="${root}/images/picone/bingtu.png"></span>本周-统计频发故障百分比图
					</h4>
					<div class="scrollBox" style="margin: 0; min-height:240px;">
						<div id="chart2" style="width:100%;margin:0 auto;"></div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>

<script>
	//异步获取FLASH图表数据
	var charStatusflag1 = -1;
	var charStatusflag2 = -1;

    // 页面初始化加载
	$(document).ready(function(){
		initflashchar();
	});
			
	function initflashchar(){
	   	getChart1XmlData();//初始化图表
	   	getChart2XmlData();//初始化图表
	}
	  
	function getChart1XmlData(){
	    var obj1 = $("chart1");
	    if(charStatusflag1 == -1){
	        obj1.innerHTML = '<img src="${root}/images/loading.gif" align="absMiddle"> 实时图表绘制中，请稍候……';
	    }
		$.ajax({
			type : "POST",
			url:"${root}/question/main/column/1603231557175688b12b7b9415fcd10f/", 
			dataType : "json",
			success : function(dd) {
		      if(dd.success){
		       var chart = new FusionCharts("${root}/compnents/fusioncharts/chart/Column3D.swf", "Chart1Id", "100%", "250");
			   if(dd.message == 'nodata'){
				   $("#chart1").html("<center style='margin-top:120px;color:blue'>无数据</center>");
			   }else{
				   chart.setDataXML(dd.message);	
				   chart.render("chart1");
			   }
			   charStatusflag1 = 1;
		      }
			},
			error : function() {
			}
		});
	}


	function getChart2XmlData(){
	    var obj2 = $("chart2");
	    if(charStatusflag2 == -1){
	        obj2.innerHTML = '<img src="${root}/images/loading.gif" align="absMiddle"> 实时图表绘制中，请稍候……';
	    }
	    $.ajax({
			type : "POST",
			url:"${root}/question/main/pie/1603231557175688b12b7b9415fcd10f/", 
			dataType : "json",
			success : function(dd) {
		      if(dd.success){
		       var chart = new FusionCharts("${root}/compnents/fusioncharts/chart/Pie2D.swf", "Chart2Id", "100%", "250");
			   if(dd.message == 'nodata'){
				   $("#chart2").html("<center style='margin-top:120px;color:blue'>无数据</center>");
			   }else{
				   chart.setDataXML(dd.message);
				   chart.render("chart2");
			   }
			   charStatusflag2 = 1;
		      }
			},
			error : function() {
			}
		});
	}
	
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf"%>