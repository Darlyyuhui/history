<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table style="width:100%;">
	<tr>
		<td style=''>
			类型：
		</td>
		<td style="">
			
			<select id="keywordSearchType" style='width:100px;margin-top:10px;'>
				<option value="">全部</option>
				<c:if test="${atms.map_road eq 1}">
					<option value="baseRoad">道路</option>
				</c:if>
				<c:if test="${atms.map_education_org eq 1}">
					<option value="educationPOI">教育机构</option>
				</c:if>
				<c:if test="${atms.map_enterprise eq 1}">
					<option value="enterprisePOI">企事业单位</option>
				</c:if>
				<c:if test="${atms.map_government eq 1}">
					<option value="governmentPOI">行政单位</option>
				</c:if>
				<c:if test="${atms.map_medical_place eq 1}">
					<option value="hospitalPOI">医疗卫生</option>
				</c:if>
				<c:if test="${atms.map_oil_station eq 1}">
					<option value="petrolFillPOI">汽车服务</option>
				</c:if>
				<c:if test="${atms.map_org eq 1}">
					<option value="orgPOI">行政区面</option>
				</c:if>
				<c:if test="${atms.map_scenic_spot eq 1}">
					<option value="tourismPOI">旅游景点</option>
				</c:if>
				<c:if test="${atms.map_supermarket eq 1}">
					<option value="marketPOI">超市广场</option>
				</c:if>
				<c:if test="${atms.map_township_gov eq 1}">
					<option value="townsPOI">乡镇点</option>
				</c:if>
				<c:if test="${atms.map_pulbic_places eq 1}">
					<option value="publicPOI">其他</option>
				</c:if>
			</select>
		</td>
	</tr>
</table>
<ul id="mapQueryBoxTab" class="zTabTool" style="height:25px;margin:0;">
  <li style="padding:0px 5px;height:25px;line-height:25px;">关键字</li>
  <li style="padding:0px 5px;height:25px;line-height:25px;">图形</li>
</ul>
<div id="mapQueryBoxContent" style="margin-top:5px;">
	<table>
		<tr>
			<td style="">
				<span style="padding-top:8px;">关键字：</span>
			</td>
			<td>
				<input type="text" id="mapKeywordsSearchBox" class="input" style="width:80px;height:25px;margin-top:0px;"/>
			</td>
			<td>
				<span id='mapKeywordsSearchButton' class="btn btn-info" style="height:20px;line-height:20px;margin-left:5px;">搜索</span>
			</td>
			<td>
				<span class="btn btn-info mapQueryResClearButton" style="height:20px;line-height:20px;margin-left:5px;">清除</span>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
				<span id='extentQuery' class="btn btn-info" style="height:20px;line-height:20px;margin-left:5px;margin-top:0px;">框选</span>
			</td>
			<td>
				<span class="btn btn-info mapQueryResClearButton" style="height:20px;line-height:20px;margin-left:5px;">清除</span>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
	MapFactory.Require([
	    "ItmsMap/MapQuery/QueryWidget*",
	    "ItmsMap/Util/Tab*",
	    "MapFactory/Draw",
	    "MapFactory/GeometryType*"
	],function(QueryWidget,Tab,Draw,geoType){
		Tab().init("mapQueryBoxTab","mapQueryBoxContent",0);
		var queryWidget = new QueryWidget();
		$("#mapKeywordsSearchButton").click(function(){
			if($("#mapKeywordsSearchBox").val() != ""){
				queryWidget.clear();
				queryWidget.setType($("#keywordSearchType").val());
				queryWidget.search($("#mapKeywordsSearchBox").val());
			}
		});		
		$("#mapKeywordsSearchClearButton").click(function(){
			   queryWidget.clear();
		});		
		$("#extentQuery").click(function(){
			var _draw = Draw();
		    _draw.setGeoType(geoType.POLYGON);
		    _draw.setDrawEndEvent(function(polygon){
			    queryWidget.clear();
			    queryWidget.setType($("#keywordSearchType").val());
			    queryWidget.geoSearch(polygon);
			    _draw.deactivate();
			});
			_draw.activate();
		});
		
		$(".mapQueryResClearButton").click(function(){
			queryWidget.clear();
		});
	});
</script>