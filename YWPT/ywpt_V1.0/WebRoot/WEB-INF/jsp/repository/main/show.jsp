<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link id="MainCss" href="${root}/css/transport.css" type="text/css" rel="stylesheet">
<link id="DevCss" href="${root}/css/deviceindex.css" type="text/css" rel="stylesheet">
<script src="${root}/compnents/fusioncharts/js/FusionCharts.js" type="text/javascript"></script>
<script  type="text/javascript">
//切换皮肤方法
var d=window.parent.document.getElementById("skin-sel");
if(d!=null){
	var value= d.options[d.selectedIndex].value;
	if(value=="1"){
		$("#DevCss").attr("href","${root}/css/deviceindex.css");
		$("#MainCss").attr("href","${root}/css/transport.css");
	}else if(value=="2"){
		$("#DevCss").attr("href","${root}/cssGreen/deviceindex.css");
		$("#MainCss").attr("href","${root}/cssGreen/transport.css");
	}else if(value=="3"){
		$("#DevCss").attr("href","${root}/cssDarkBlue/deviceindex.css");
		$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
	}else{
	
	}
	//d.addEventListener("change",ssss,false);
	//监听主页皮肤切换
	$(d).bind("change", function(){
		var  d1=window.parent.document.getElementById("skin-sel");
		var value1= d1.options[d1.selectedIndex].value;
		if(value1=="1"){
			$("#DevCss").attr("href","${root}/css/deviceindex.css");
			$("#MainCss").attr("href","${root}/css/transport.css");
		}else if(value1=="2"){
			$("#DevCss").attr("href","${root}/cssGreen/deviceindex.css");
			$("#MainCss").attr("href","${root}/cssGreen/transport.css");
		}else if(value1=="3"){
			$("#DevCss").attr("href","${root}/cssDarkBlue/deviceindex.css");
			$("#MainCss").attr("href","${root}/cssDarkBlue/transport.css");
		}else{
		
		}
	});
}
</script>
<style type="text/css">
.chart-line object {
	width:100%;
}
</style>
<div class="conten_box" style="height:695px;overflow:hidden;">
	<h4 class="title_intro" style="margin-top: 0;">
		<span><img src="${root}/images/picone/xtcs.png"></span>知识库条目
	</h4>
	<div class="mar_l5 mar_r5" style="margin-bottom: 5px; margin-top: 0;">
		<div class="shebei_info mar_b5">
          <table class="table table-striped table-bordered table-condensed table-style" id="table" style="margin:0;">
			<c:if test="${pageList.result[0]==null}"><td><div style="text-align: center;margin-right: 40px;"><font color="red">没有知识库条目。</font></div></td></c:if>
  		    <c:if test="${pageList.result[0]!=null}">
				<thead>
					<tr>
						<th>序号</th>
						<th>文件名称</th>
						<th>知识分类</th>
						<th>创建人</th>
						<th>创建时间</th>
						<th>知识来源</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${pageList.result}" var="knowledge" varStatus="x">
						<tr data="${knowledge.id}">
							<td width="10px;">
								${x.index+1}
							</td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${knowledge.name}">${knowledge.name}</div></td>
							<td>
								${knowledge.pid}
							</td>
							<td>
								<tags:xiangxuncache keyName="username_cache" id="${knowledge.operator}"></tags:xiangxuncache>
							</td>
							<td>
								<fmt:formatDate value="${knowledge.createtime}" type="both" />
							</td>
							<td>
								<c:if test="${knowledge.type == '0'}">
									<font color="green">人工录入</font>
								</c:if>
								<c:if test="${knowledge.type == '1'}">
									<font color="green">文档上传</font>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${pageList.result!=null}">
						<c:forEach begin="1" end="${5-fn:length(pageList.result)}">
							<tr height="28">
								<td colspan="12">
									&nbsp;
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</c:if>
		</table>
		</div>
	</div>
	<div style="width: 100%;margin-top: -4px;">
       <div class="diagram pull-left" style="width: 40%;">
         <h4 class="title_intro"><span><img src="${root}/images/picone/zhuxing.png"></span>知识库分类统计</h4>
         <div id="diagram" style="min-height:280px;margin-bottom:-10px;">
         <div id="chart-column"></div>
         </div>
       </div>
       <div class="pie-chart pull-right" style="width: 59.9%;">
         <h4 class="title_intro"><span><img src="${root}/images/picone/rzxt.png"></span>知识库审核统计</h4>
         <div id="diagram" style="min-height:310px;margin-bottom:-10px;overflow: auto;">
		  	<table class="table table-striped table-bordered table-condensed table-style" id="table">
		  		<c:if test="${statusList.result[0]==null}"><td><div style="text-align: center;margin-right: 40px;"><font color="red">没有知识库条目。</font></div></td></c:if>
  		    	<c:if test="${statusList.result[0]!=null}">
					<thead>
						<tr>
							<th>序号</th>
							<th>文件名称</th>
							<th>知识分类</th>
							<th>创建人</th>
							<th>创建时间</th>
							<th>审核状态</th>
						</tr>
					</thead>
					<tbody id="tbody1">
						<c:forEach items="${statusList.result}" var="knowledge" varStatus="x">
							<tr data="${knowledge.id}">
								<td>${(x.index+1)+(page-1)*9}</td>
								<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${knowledge.name}">${knowledge.name}</div></td>
								<td>
									${knowledge.pid}
								</td>
								<td>
									<tags:xiangxuncache keyName="username_cache" id="${knowledge.operator}"></tags:xiangxuncache>
								</td>
								<td>
									<fmt:formatDate value="${knowledge.createtime}" type="both" />
								</td>
								<td>
									<c:if test="${knowledge.applyflag==1}"><font color="green"><b>已提交</b></font></c:if>
									<c:if test="${knowledge.applyflag==0}"><font color="red"><b>未提交</b></font></c:if>
									
									<c:if test="${knowledge.applyflag==1}">
									->
									<c:if test="${knowledge.verifyflag==1}"><font color="green"><b>已审核</b></font></c:if>
									<c:if test="${knowledge.verifyflag==0}"><font color="red"><b>未审核</b></font></c:if>
									</c:if>
									
									<c:if test="${knowledge.verifyflag==1}">
									->
									<c:if test="${knowledge.verifyresult==1}"><font color="green"><b>审核通过</b></font></c:if>
									<c:if test="${knowledge.verifyresult==2}"><font color="red"><b>审核驳回</b></font></c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${statusList.result!=null}">
							<c:forEach begin="1" end="${9-fn:length(statusList.result)}">
								<tr height="28">
									<td colspan="12">
										&nbsp;
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</c:if>
			</table>
			<tags:pagination page="${statusList}"></tags:pagination>
         </div>
       </div>
       <div class="clear"></div>
    </div>
</div>
<textarea id="chart-xml" style="display: none">
<chart caption='知识库统计' aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink=''  bgColor='#FBFBFB'  formatNumber='1'  numberSuffix='条'
  rotateYAxisName='0'  baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  
  chartLeftMargin='5'  shownames='1'  showValues='1'  animation='1'  formatNumberScale='0'  showPercentageValues='0'  startingAngle='45'  
  slicingDistance='1'  enableSmartLabels='1'  enableRotation='1'  >
	    <c:forEach items="${knowledgelist}" var="data">
			<set name='${data['TYPES']}' value='${data['COUNTS']}'/>
		</c:forEach>
</chart>
</textarea>

<script>


  // 页面初始化加载
	$(document).ready(function(){
		//隔行换色
		$("#tbody tr:odd").addClass("color1");
		$("#tbody tr:even").addClass("color2");
		
		
		//魏克明add鼠标划进划出tr行产生效果的js	
		$("#tbody tr:odd").mouseover(function(e){
			$(this).removeClass("color1").addClass("tr_over");
	 	});
	 	$("#tbody tr:even").mouseover(function(){
	 		$(this).removeClass("color2").addClass("tr_over");
	 	});
		
	 	$("#tbody tr:odd").mouseout(function(){
	 		if($(this).find(":checkbox").is(":checked")){
	 			$(this).removeClass("color1").addClass("tr_over");
	 		}else{
	 			$(this).removeClass("tr_over").addClass("color1");
	 		}
	 	});
	 	$("#tbody tr:even").mouseout(function(){
	 		if($(this).find(":checkbox").is(":checked")){
	 			$(this).removeClass("color2").addClass("tr_over");
	 		}else{
	 			$(this).removeClass("tr_over").addClass("color2");
	 		}
	 	});
		
		//点击偶数行复选框状态变化
		$("#tbody tr:odd").click(function(e){
			if($(e.target).attr("type") != "checkbox"){
				if($(this).find(":checkbox").is(":checked")){
					$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
					$(this).removeClass("tr_over").addClass("color1");
				}else{
					$(this).find(":checkbox").attr("checked","checked");
					$(this).removeClass("color1").addClass("tr_over");
					
				}
			}
		})
		//点击奇数行复选框状态变化
		$("#tbody tr:even").click(function(e){
			if($(e.target).attr("type") != "checkbox"){
				if($(this).find(":checkbox").is(":checked")){
					$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
					$(this).removeClass("tr_over").addClass("color2");
				}else{
					$(this).find(":checkbox").attr("checked","checked");
					$(this).removeClass("color2").addClass("tr_over");
				}
			}
		})
		
		//隔行换色
		$("#tbody1 tr:odd").addClass("color1");
		$("#tbody1 tr:even").addClass("color2");
		
		
		//魏克明add鼠标划进划出tr行产生效果的js	
		$("#tbody1 tr:odd").mouseover(function(e){
			$(this).removeClass("color1").addClass("tr_over");
	 	});
	 	$("#tbody1 tr:even").mouseover(function(){
	 		$(this).removeClass("color2").addClass("tr_over");
	 	});
		
	 	$("#tbody1 tr:odd").mouseout(function(){
	 		if($(this).find(":checkbox").is(":checked")){
	 			$(this).removeClass("color1").addClass("tr_over");
	 		}else{
	 			$(this).removeClass("tr_over").addClass("color1");
	 		}
	 	});
	 	$("#tbody1 tr:even").mouseout(function(){
	 		if($(this).find(":checkbox").is(":checked")){
	 			$(this).removeClass("color2").addClass("tr_over");
	 		}else{
	 			$(this).removeClass("tr_over").addClass("color2");
	 		}
	 	});
		
		//点击偶数行复选框状态变化
		$("#tbody1 tr:odd").click(function(e){
			if($(e.target).attr("type") != "checkbox"){
				if($(this).find(":checkbox").is(":checked")){
					$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
					$(this).removeClass("tr_over").addClass("color1");
				}else{
					$(this).find(":checkbox").attr("checked","checked");
					$(this).removeClass("color1").addClass("tr_over");
					
				}
			}
		})
		//点击奇数行复选框状态变化
		$("#tbody1 tr:even").click(function(e){
			if($(e.target).attr("type") != "checkbox"){
				if($(this).find(":checkbox").is(":checked")){
					$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
					$(this).removeClass("tr_over").addClass("color2");
				}else{
					$(this).find(":checkbox").attr("checked","checked");
					$(this).removeClass("color2").addClass("tr_over");
				}
			}
		})
		
		getChart1XmlData();
	});
		
  	function getChart1XmlData(){
	    var chart = new FusionCharts("${root}/compnents/fusioncharts/chart/Column3D.swf", "Chart1Id","100%","350");
		var total = '${unfirmSize}';
		if (total == '0') {
			$("#chart-column").html("<center style='margin-top:150px;color:blue'>无数据</center>");
		} else {
			chart.setDataXML($("#chart-xml").val());
			chart.render("chart-column");
		}
	}	
</script>
