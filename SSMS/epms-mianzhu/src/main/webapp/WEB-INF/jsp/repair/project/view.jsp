<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="page-header">
    <h1>
       土壤修复项目档案
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            查看档案
        </small>
    </h1>
</div>
<div class="row">
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${info.id }"   />
		<input type="hidden" id="regionId" name="regionId" value="${info.regionId }" />
		<input type="hidden"  id="blockIds"  name="blockIds" value="${info.blockIds }" >
		
		
		<table class="width-100">
              <tr class="td_h">
                <td style="position: relative">
                    <!--        地图显示区域 -->
                    <div id="map" class="col-xs-12"
                         style="position:absolute;top:0;height:750px;">
                        <div id="mapNavigationBox"></div>
                        <div id="mapViewSwitcher"></div>
                        <div id="mapZoomSlider"></div>
                    </div>
                    <!--        地图显示区域 -->
                    <!-- 地图统一样式引入 -->
                    <tags:mapheader rotateImage="true"></tags:mapheader>
           </div>
                </td>
                <td style="width: 430px;padding-left:20px;">
		
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">项目编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">项目名称</div>
                <div class="profile-info-value">
                	${info.name }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">开始时间</div>
                <div class="profile-info-value">
					<fmt:formatDate value='${info.beginTime }' pattern='yyyy-MM-dd HH:mm:ss'/>
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">结束时间</div>
                <div class="profile-info-value">
					<fmt:formatDate value='${info.endTime }' pattern='yyyy-MM-dd HH:mm:ss'/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">修复范围</div>
                <div class="profile-info-value">
					<tags:xiangxuncache keyName="TREGION_FULL_NAME" id="${info.regionId }"/>
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">修复技术</div>
                <div class="profile-info-value">
					<tags:xiangxuncache keyName="Dic" typeCode="LAND_REPAIR_TECHNOLOGY" id="${item.technology }"/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">修复面积（亩）</div>
                <div class="profile-info-value">
					${info.area }
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">任务及目标</div>
                <div class="profile-info-value">
                	${info.missionTarget }
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">修复效果</div>
                <div class="profile-info-value">
					${info.effect }
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">修复进度</div>
                <div class="profile-info-value">
                	<tags:xiangxuncache keyName="Dic" typeCode="LAND_REPAIR_SCHEDULE" id="${item.schedule }"/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">修复单位</div>
                <div class="profile-info-value">
					${info.dept }
				</div>
			</div>
			<div class="profile-info-row">
				<div class="profile-info-name">验收时间</div>
                <div class="profile-info-value">
					<fmt:formatDate value='${info.acceptionTime }' pattern='yyyy-MM-dd HH:mm:ss'/>
				</div>
            </div>
            
            <div class="profile-info-row" >
                <div class="profile-info-name">修复说明</div>
				<div class="profile-info-value">
					${info.explain }
				</div>
            </div>
            
            <div class="profile-info-row" >
                <div class="profile-info-name">项目附件</div>
				<div class="profile-info-value">
					<tags:files businessId="${info.id }" />
				</div>
            </div>
        </div>
        
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn" type="reset" onclick="window.location.href='${root}/repair/project/list/${menuid }/?page=${page }&isgetsession=1'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
        </td>
        </tr>
        </table>
</div>

<script>
	var landIds=[];
	var _map;
	 var areasSum=0;
    $(document).ready(function () {
        initMap("map");
        // 初始化地图
        function initMap(mapDiv) {
            if (typeof window["mapTag"] == "undefined")return;
            mapTag().init(mapDiv, function (map) {
            	_map=map;
                var symbols = map.getSymbolConfig();
                map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
                map.hideInfowindow();
                var selectedLand=$("#blockIds").val();
		         //重新选择
		         MapFactory.Require(["ItmsMap/UserLayers/CustomLayers/Land*","MapFactory/LayerManager","MapFactory/GraphicManager","MapFactory/SymbolConfig*"],function(Land,LayerManager,GraphicManager,SymbolConfig){
	                	   Land().drawRegions();
	                  	  var landLyrPologn=LayerManager("landLyrPologn");
	                       allResultLand(selectedLand);
	                      
	                })
            });
        }
    });
       function allResultLand(selectedLand){
       	selectedLand=selectedLand.split(",");
       	for(let i=0;i<selectedLand.length;i++){
       		landIds.push(selectedLand[i]);
       	}
       	MapFactory.XHR.Post(path
   				+ "/map/all/landblock/",
   		function(target) {
       		//异步请求不能放在for循环里面
       		MapFactory.Require(["MapFactory/GraphicManager"],function(GraphicManager){
       	    for(let j=0;j<target.length;j++){
       	    	for(let k=0;k<landIds.length;k++){
       	    		if(target[j].id===landIds[k]){
       	    			var addGeo =target[j].geoJson;
       	    			 var geometry = eval("(" + addGeo+ ")");
       	    			 GraphicManager({geo:geometry,symbol:{outLineWidth:1,outLineColor : "#6666aa",outLineStyle : "dashed",backgroundOpacity:0},attributes:{ids:target[j].id}}).highlight("landLyrPologn");
       	    		}
       	    	}
       	    }
       		});
   	  });
       }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>