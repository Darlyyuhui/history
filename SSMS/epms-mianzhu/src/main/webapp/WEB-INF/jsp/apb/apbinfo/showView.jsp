<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

	<div class="page-header">
		<h1>
			基地产品信息<small> <i
				class="ace-icon fa fa-angle-double-right"></i> 查看基地产品信息
			</small>
		</h1>
	</div>
	<div class="row">
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${standardIndustry.id }" />
         <table class="width-100">
            <tr class="td_h">
                <td>
                    <!--        地图显示区域 -->
                    <div id="map" class="col-xs-12"
                         style="position:relative;height:720px;">
			<div id="mapNavigationBox"></div>
			<div id="mapViewSwitcher"></div>
			<div id="mapZoomSlider"></div>
		</div>
	    <!-- 地图统一样式引入 -->
	    <tags:mapheader rotateImage="true"></tags:mapheader>
        		</td>
        		<td style="width: 430px;padding-left:20px;position: relative">
                    <div style="border:none;position: absolute;top:0;width:90%">
                        <div class="profile-user-info profile-user-info-striped width-100">
	   <div class="profile-info-row">
                <div class="profile-info-name">编号</div>
                <div class="profile-info-value">
                	${info.code }
				</div>
        </div>
        <div class="profile-info-row">
                <div class="profile-info-name">名称</div>
                <div class="profile-info-value">
                	${info.name }
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">N（北纬）</div>
                <div class="profile-info-value" >
                  <div id="latitude">${info.latitude }</div> 
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">E（东经）</div>
                <div class="profile-info-value" >
                	<div id="longitude">${info.longitude }</div>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">详细地址</div>
                <div class="profile-info-value">
                	${info.address  }
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">年产量（万吨）</div>
                <div class="profile-info-value">
                	${info.annualOutput }
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">种植面积（亩）</div>
                <div class="profile-info-value">
                	${info.area  }
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">主营产品</div>
                <div class="profile-info-value">
                	<tags:listshow keyName="${ID_APBPRODUCTTYPENAME }"  id="${info.id }"></tags:listshow>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">基地描述</div>
                <div class="profile-info-value">
                	${info.describe }
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">周围环境</div>
                <div class="profile-info-value">
                	${info.ambient }
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">土壤类型</div>
                <div class="profile-info-value">
                	<tags:cache keyName="${SOILE_TYPE }" id="${info.soilType }"></tags:cache>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">污染状况</div>
                <div class="profile-info-value">
                	${info.polluteDesc }
				</div>
            </div>
           </div>	
            <div class="clearfix form-actions width-100 pull-right" style="margin-top:10px;bottom: 0">
                            <div class="col-md-12" style="padding:0;">
			<button class="btn" type="reset" onclick="showList()">
				<i class="ace-icon fa fa-undo bigger-110"></i> 返回
			</button>
		</div>
	</div></div>
        		</td>
        	</tr>
        </table>
	
           
        

		 
	
</div>
<script>
	function showList() {
		window.location.href = "${root}/apb/apbinfo/list/${menuid}/?page=${page}";
	}
	var LayerManager,graphic;
	//页面加载成功后，初始化页签和地图
	$(document).ready(function () {
		 initMap("map");
		 if($("#longitude").html()!=""){
			 var longitude=formatDegree($("#longitude").html()); 
			 $("#longitude").html(longitude);
			 var latitude=formatDegree($("#latitude").html()); 
			 $("#latitude").html(latitude); 
		 }
		
    });
	 // 初始化地图
	 function initMap(mapDiv) {
	     if (typeof window["mapTag"] == "undefined")return;
	     mapTag().init(mapDiv, function (map) {
	         var symbols = map.getSymbolConfig();
	         map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
	         map.hideInfowindow();
	        if($("#longitude").html() && $("#latitude").html()){
	        	 map.centerAt($("#longitude").html(),$("#latitude").html(),6);
	        }
            var addGeo ='${info.geoJson}';
			if(addGeo) {
			    var addJson = eval("(" + addGeo + ")");
				map.addGeometry(addJson, "defaultLayer", true);
				}	
		     }); 
	     }
     function formatDegree(value) {  
         ///<summary>将度转换成为度分秒</summary>  
         value = Math.abs(value);  
         var v1 = Math.floor(value);//度  
         var v2 = Math.floor((value - v1) * 60);//分  
         var v3 = Math.round((value - v1) * 3600 % 60);//秒  
         return v1 + '°' + v2 + '\'' + v3 + '"';  
     };  
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>