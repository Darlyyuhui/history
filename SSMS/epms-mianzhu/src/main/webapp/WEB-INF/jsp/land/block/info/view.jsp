<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center">${message}</p>
    </div>
    <script>
        setTimeout('$("#message").hide("slow")', 1200);
    </script>
</c:if>
<div class="page-header">
    <h1>
        土壤地块
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            查看地块信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/land/block/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
        <input type="hidden" name="id" value="${info.id }"/>
       	<table class="width-100">
       		<tr>
       			<td>
       				<div id="map" class="col-xs-12" style="width: 100%;min-height: 720px !important;position: relative">
					<div id="mapNavigationBox"></div>
					<div id="mapViewSwitcher"></div>
					<div id="mapZoomSlider"></div>
				</div>
	    <!-- 地图统一样式引入 -->
	    		<tags:mapheader rotateImage="true"></tags:mapheader>
       			</td>
       			<td style="width: 430px;padding-left:20px;padding-top:0;position: relative" class="td_h">
       				<div class="profile-user-info-striped width-100 pull-right" style="border:none;position: absolute;top:0">
        
            <div class="profile-info-row">
                <div class="profile-info-name">地块编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
			</div>
			<div class="profile-info-row">	
				<div class="profile-info-name">地块名称</div>
                <div class="profile-info-value">
                	${info.name }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">地块类型</div>
                <div class="profile-info-value">
					<tags:xiangxuncache keyName="Dic" typeCode="LAND_BLOCK_TYPE" id="${info.typeCode }"/>
				</div>
			</div>
			<div class="profile-info-row">	
				<div class="profile-info-name">所属区域</div>
                <div class="profile-info-value">
					<tags:xiangxuncache keyName="TREGION_FULL_NAME" id="${info.regionId }"/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">经度</div>
                <div class="profile-info-value">
					${info.longitude }
				</div>
			</div>
			<div class="profile-info-row">	
				<div class="profile-info-name">纬度</div>
                <div class="profile-info-value">
					${info.latitude }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">土壤类型</div>
                <div class="profile-info-value">
					<tags:xiangxuncache keyName="LANDTYPE_NAME" id="${info.soilType }"/>
				</div>
			</div>
		    <div class="profile-info-row">	
				<div class="profile-info-name">污染类型</div>
                <div class="profile-info-value">
					<tags:xiangxuncache keyName="Dic" typeCode="LAND_POLLUTE_TYPE" id="${info.polluteType }"/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">面积（亩）</div>
                <div class="profile-info-value">
					${info.area }
				</div>
			</div>
			<div class="profile-info-row">	
				<div class="profile-info-name">责任人</div>
                <div class="profile-info-value">
					<tags:xiangxuncache keyName="T_OWNER" id="${info.ownerId }" />
				</div>
            </div>
            <div class="clearfix form-actions">
                <button class="btn" type="reset" onclick="window.location.href='${root}/land/block/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
       			</td>
       		</tr>
       	</table>
        
            
       
        
        
    </form>
</div>
<script type="text/javascript">
$("#map").height(function(){
    var h = $(".td_h").height();
    return h;
});
	var LayerManager,graphic;
	//页面加载成功后，初始化页签和地图
	$(document).ready(function () {
	 // 页面加载成功后，初始化页签和地图
	 initMap("map");
	
	 // 初始化地图
	 function initMap(mapDiv) {
	     if (typeof window["mapTag"] == "undefined")return;
	     mapTag().init(mapDiv, function (map) {
	         var symbols = map.getSymbolConfig();
	         map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
	         map.hideInfowindow();
	         map.centerAt("${info.longitude }","${info.latitude }",6);
             var addGeo ='${info.geoJson}';
			 if(addGeo) {
			    var addJson = eval("(" + addGeo + ")");
				map.addGeometry(addJson, "defaultLayer", true);
				}	
		     }); 
	     }});
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>