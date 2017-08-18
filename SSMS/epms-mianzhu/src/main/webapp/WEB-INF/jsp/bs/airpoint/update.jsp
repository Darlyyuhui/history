<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" isCallback="1"/>
<script src="${root}/js/business.validate.js" type="text/javascript"></script>
<div class="page-header">
    <h1>
        大气采样点位管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改点位
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/bs/airpoint/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${info.id }" />
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
                    <!--        地图显示区域 -->
                    <!-- 地图统一样式引入 -->
                    <tags:mapheader></tags:mapheader>
                </td>
                <td style="width: 390px;padding-left:20px;position: relative">
                    <div style="border:none;position: absolute;top:0">
                        <div class="profile-user-info profile-user-info-striped width-100">

                        <div class="profile-info-row">
                            <div class="profile-info-name">点位编号</div>
                            <div class="profile-info-value">
                                ${info.code }
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">点位名称</div>
                            <div class="profile-info-value">
                                <input type="text" id="name" name="name" maxlength="50" value="${info.name }"
                                       style="min-width:120px; width: 200px;" class="input-large required"/>
                                <span style="color: red">*</span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">所属区域</div>
                            <div class="profile-info-value">
                                <input type="text" id="regionName" readonly="readonly" value='<tags:xiangxuncache keyName="TREGION_NAME" id="${info.regionId }"/>'
                                       style="min-width:120px; width: 200px;" class="input-large required" />
                                <input type="hidden" id="regionId" name="regionId" value="${info.regionId }" />
                                <span style="color: red">*</span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">经度</div>
                            <div class="profile-info-value">
                                <input type="text" id="longitude" name="longitude" maxlength="12" value="${info.longitude }"
                                       style="min-width:120px; width: 200px;" class="input-large number required"/>
                                <span style="color: red">*</span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">纬度</div>
                            <div class="profile-info-value">
                                <input type="text" id="latitude" name="latitude" maxlength="12" value="${info.latitude }"
                                       style="min-width:120px; width: 200px;" class="input-large number required"/>
                                <span style="color: red">*</span>
                            </div>
                        </div>
                    </div>



                        <div class="clearfix form-actions width-100 pull-right" style="margin-top:10px;background:none;padding: 0;bottom: 0">
                            <div class="col-md-12" style="padding:0;">
                            <button class="btn btn-primary" type="button" onclick="draw()">
                                <i class="ace-icon fa fa-submit bigger-110"></i>选点
                            </button>
                            <button class="btn" type="button" onclick="clearDraw()">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 清除
                            </button>
                            <button class="btn btn-primary" type="button" onclick="checkForm()">
                                <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                            </button>
                            <button class="btn" type="reset" onclick="window.location.href='${root}/bs/airpoint/list/${menuid }/?page=${page }&isgetsession=1'">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                            </button>
                        </div>
                    </div> </div>
                </td>
            </tr>
        </table>

    </form>
</div>

<script>
	var v;
	var _map;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate({
        	rules: {
        		longitude: {
        			isCoordinate: true
    			},
    			latitude: {
        			isCoordinate: true
    			}
    		}
        });
        $("#name").focus();
        //地图模块
        $("#welcomeiframe").css("height", $(window).height() - 120 + "px");
        $(".map-content").height($(window).height() - 140);
        $("#allmap").height($(window).height() - 150);
        // 页面加载成功后，初始化页签和地图
        initMap("map");

        // 初始化地图
        function initMap(mapDiv) {
            if (typeof window["mapTag"] == "undefined")return;
            mapTag().init(mapDiv, function (map) {
            	_map = map;
                var symbols = map.getSymbolConfig();
                map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
                map.hideInfowindow();
                if("${info.longitude }" && "${info.latitude }"){
                	 map.centerAt("${info.longitude }","${info.latitude }",6);
                }
                 MapFactory.Require(["MapFactory/GeometryType*","MapFactory/GraphicManager", "MapFactory/Geometry*","ItmsMap/SymbolConfig*","ItmsMap/UserLayers/TRPointConfig*"],function(GeometryType,GraphicManager,Geometry,SymbolConfig,TRPointConfig){
                	 var point=new Geometry({
    					 type : GeometryType.POINT,
    					 points:${info.longitude}+","+${info.latitude}
    						});	
                	 var symbol=MapFactory.Clone(SymbolConfig["cydSymbol"]);
   	        	    symbol.url=symbol.url+TRPointConfig["DQ"]+".png"; 	 
   	               var    graphic= GraphicManager({geo:point,symbol:symbol,attributes:{}});
    				  graphic.addToLayer("defaultLayer");	
                 });
            });
        }
    });
    
    function draw(){
      	 //绘制区域初始化  
      	 MapFactory.Require(["MapFactory/Draw","MapFactory/GeometryType*","MapFactory/GraphicManager","ItmsMap/SymbolConfig*","MapFactory/LayerManager","ItmsMap/UserLayers/TRPointConfig*"],
   				                  function(Draw,GeometryType,Graphic,SymbolConfig,LayerManager,TRPointConfig){ 
   	   		 var draw = Draw();
   	         draw.setGeoType(GeometryType.POINT);
   	         draw.setDrawEndEvent(function(geometry){
   	        	 LayerManager("defaultLayer").clear();
   	        	 var symbol=MapFactory.Clone(SymbolConfig["cydSymbol"]);
	        	  symbol.url=symbol.url+TRPointConfig["DQ"]+".png"; 	 
	              graphic= Graphic({geo:geometry,symbol:symbol,attributes:{}});
   	              graphic.addToLayer("defaultLayer");
   	              var points=geometry.points.split(",");
   	              $("#longitude").val(points[0]);
   	              $("#latitude").val(points[1]);
   	             draw.deactivate();                                                         
   	         });
   	         draw.activate();
      	 });
      };
      function clearDraw(){
      	 MapFactory.Require(["MapFactory/LayerManager"],function(LayerManager){
      		 LayerManager("defaultLayer").clear();
      		 $("#longitude").val("");
	         $("#latitude").val("");
      	      graphic=null;
      	 });
      };
    function checkForm() {
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    function regionTreeCB() {
		$.get(
			"${root}/bs/region/getLocation/"+$("#regionId").val()+"/",
			function(data) {
				if(data){
					if(data){
						_map.centerAt(data.longitude,data.latitude,6);
					}
				
				}
				
			}
		);
	}
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>