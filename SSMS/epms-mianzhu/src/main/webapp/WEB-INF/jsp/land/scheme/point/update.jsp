<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/custom.validate.js" type="text/javascript"></script>
<script src="${root}/js/business.validate.js" type="text/javascript"></script>
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
        土壤采样方案点位
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增采样点
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/land/sampling/scheme/point/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
        <input type="hidden" name="id" value="${info.id }" />
        <input type="hidden" name="page" value="${page }" />
        <table class="width-100">
            <tr class="td_h">
                <td>
                    <!--        地图显示区域 -->
                    <div id="map" class="col-xs-12"
                         style="width: 100%;min-height: 720px !important;position: relative">
                        <div id="mapNavigationBox"></div>
                        <div id="mapViewSwitcher"></div>
                        <div id="mapZoomSlider"></div>
                    </div>
                    <!--        地图显示区域 -->
                    <!-- 地图统一样式引入 -->
                    <tags:mapheader rotateImage="true"></tags:mapheader>
                </td>
                <td style="width: 430px;padding-left:20px;position: relative">
                    <div style="border:none;position: absolute;top:0">
                    <div class="profile-user-info profile-user-info-striped width-100">
                        <div class="profile-info-row">
                            <div class="profile-info-name">采样方案</div>
                            <div class="profile-info-value">
                                ${schemeInfo.name }【${schemeInfo.code }】
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">采样类型</div>
                            <div class="profile-info-value">
                                <tags:xiangxuncache keyName="Dic" typeCode="SAMPLING_TYPES" id="${schemeInfo.sampleCode }"/>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">采样地块</div>
                            <div class="profile-info-value">
                                <tags:xiangxuncache keyName="LANDBLOCK_ID_CODENAME" id="${schemeInfo.blockId }"/>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">采样选址</div>
                            <div class="profile-info-value">
                                <tags:xiangxuncache keyName="TREGION_FULL_NAME" id="${schemeInfo.regionId }"/>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">点位编号</div>
                            <div class="profile-info-value">
                                ${info.code }
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">区域编号</div>
                            <div class="profile-info-value">
                                <input type="text" id="areaCode" name="areaCode" maxlength="20" value="${info.areaCode }"
                                       style="min-width:120px; width: 200px;" class="input-large required"/>
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

                        <div class="profile-info-row">
                            <div class="profile-info-name">采样范围</div>
                            <div class="profile-info-value">
                                长：
                                <input type="text" id="rangeX" name="rangeX" maxlength="20" value="${info.rangeX }"
                                       style="min-width:80px; width: 200px;" class="input-large number required"/>（米）
                                宽：
                                <input type="text" id="rangeY" name="rangeY" maxlength="20" value="${info.rangeY }"
                                       style="min-width:80px; width: 200px;" class="input-large number required"/>（米）
                                <span style="color: red">*</span>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">是否采样点</div>
                            <div class="profile-info-value">
                                <input type="radio" name="rdo_isSamplingPoint" value="1" />是
                                <input type="radio" name="rdo_isSamplingPoint" value="0" checked />否
                                <input type="hidden" id="isSamplingPoint" name="isSamplingPoint" value="${info.isSamplingPoint }" />
                            </div>
                        </div>
                    </div>
                        <div class="clearfix form-actions width-100 pull-right" style="margin-top:20px;background:none;padding: 0;bottom: 0">
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
                            <button class="btn" type="reset" onclick="window.location.href='${root}/land/sampling/scheme/point/list/${menuid }/${schemeInfo.id }/'">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                            </button>
                        </div>
                    </div>
                        </div>
                </td>
            </tr>
        </table>



    </form>
</div>

<script>
    //给地图动态的添加高度
    $("#map").height(function(){
        var h = $(".td_h").height();
        return h;
    });
	var v;
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
        
        // 页面加载成功后，初始化页签和地图
        initMap("map");

        // 初始化地图
        function initMap(mapDiv) {
            if (typeof window["mapTag"] == "undefined")return;
            mapTag().init(mapDiv, function (map) {
                var symbols = map.getSymbolConfig();
                map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
                map.hideInfowindow();
                var addGeo ='${schemeInfo.geoJson}';
   			    if(addGeo) {
   			    var addJson = eval("(" + addGeo + ")");
   				map.addGeometry(addJson, "landLayer", true);
   				}	
                if("${info.longitude }" && "${info.latitude }"){
                	  map.centerAt("${info.longitude }","${info.latitude }",6);
                }else if(addGeo){
                	getBlockLocation("${info.blockId }");
                }else if("${Info.regionId }"){
                	getRegionLocation("${info.regionId }");
                }
              
                 MapFactory.Require(["MapFactory/GeometryType*","MapFactory/GraphicManager", "MapFactory/Geometry*","ItmsMap/SymbolConfig*","ItmsMap/UserLayers/TRPointConfig*"],function(GeometryType,GraphicManager,Geometry,SymbolConfig,TRPointConfig){
                	 var point=new Geometry({
    					 type : GeometryType.POINT,
    					 points:${info.longitude}+","+${info.latitude}
    						});	
                	 var symbol=MapFactory.Clone(SymbolConfig["cydSymbol"]);
    	        	 symbol.url=symbol.url+TRPointConfig["${schemeInfo.sampleCode}"]+".png"; 	 
    				  var graphic=GraphicManager({geo:point,symbol:symbol,attributes:{}});
    				  graphic.addToLayer("defaultLayer");	
                 });
            });
        }
    });
    
    function draw(){
      	 //绘制区域初始化  
      	 MapFactory.Require(["MapFactory/Draw","MapFactory/GeometryType*","MapFactory/GraphicManager","MapFactory/SymbolConfig*","MapFactory/LayerManager"],
   				                  function(Draw,GeometryType,Graphic,SymbolConfig,LayerManager){ 
   	   		 var draw = Draw();
   	         draw.setGeoType(GeometryType.POINT);
   	         draw.setDrawEndEvent(function(geometry){
   	        	 LayerManager("defaultLayer").clear();
   	              graphic= Graphic({geo:geometry,symbol:SymbolConfig.defaultPoint,attributes:{}});
   	              graphic.addToLayer("defaultLayer");
   	              console.log(geometry.toString());
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
    		radioCheckedVal();
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    function radioCheckedVal() {
    	var ckval = $("input[name='rdo_isSamplingPoint']:checked").val();
    	$("#isSamplingPoint").val(ckval);
    }
    //根据乡镇定位
    function getRegionLocation(regionId) {
    	$.get(
       		"${root}/bs/region/getLocation/"+regionId+"/",
       		function(data) {
       			if(data){
       				_map.centerAt(data.longitude,data.latitude,4);	
       			}
       			
       		}
       	);
    }
    //根据乡镇定位
    function getRegionLocation(regionId) {
    	$.get(
       		"${root}/bs/region/getLocation/"+regionId+"/",
       		function(data) {
       			if(data){
       				_map.centerAt(data.longitude,data.latitude,4);	
       			}
       			
       		}
       	);
    }
    //根据地块定位
    function getBlockLocation(blockId) {
    	$.get(
       		"${root}/land/block/getLocation/"+blockId+"/",
       		function(data) {
       			if(data){
       				_map.centerAt(data.longitude,data.latitude,4);	
       			}
       			
       		}
       	);
    }
    
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>