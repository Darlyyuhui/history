<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
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
        农产品基地
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增农产品基地
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/apb/apbinfo/doAdd/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
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
                    <tags:mapheader rotateImage="true"></tags:mapheader>
                </td>
                <td style="width: 460px;padding-left:20px;position: relative">
                    <div style="border:none;position: absolute;top:0">
                        <div class="profile-user-info profile-user-info-striped width-100">
                        <div class="profile-info-row">
                            <div class="profile-info-name">编号</div>
                            <div class="profile-info-value">
                                <input type="text" id="code" name="code" maxlength="20"
                                       style="min-width:120px; width: 280px;" class="input-large required"/>
                                <span style="color: red">*</span>
                                <span id="checkCodeSpan" style="color: red"></span>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">名称</div>
                            <div class="profile-info-value">
                                <input type="text" id="name" name="name" maxlength="20"
                                       style="min-width:120px; width: 280px;" class="input-large required"/>
                                <span style="color: red">*</span>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">N（北纬）</div>
                            <div class="profile-info-value">
                                <input type="text" id="latitude" name="latitude" maxlength="12"
                                       style="min-width:120px; width: 280px;display:none;" class="input-large "/>
                                <input type="text" id="latitude2" name="latitude2" maxlength="20"
                                       style="min-width:120px; width: 280px;"class="input-large "/>

                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">E（东经）</div>
                            <div class="profile-info-value">
                                <input type="text" id="longitude" name="longitude" maxlength="12"
                                       style="min-width:120px; width: 280px;display:none;" class="input-large"/>
                                <input type="text" id="longitude2" name="longitude2" maxlength="20"
                                       style="min-width:120px; width: 280px;" class="input-large"/>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">详细地址</div>
                            <div class="profile-info-value">
                                <input type="text" id="address" name="address" maxlength="50"
                                       style="min-width:120px; width: 280px;" class="input-large"/>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">年产量（万吨）</div>
                            <div class="profile-info-value">
                                <input type="text" id="annualOutput" name="annualOutput" maxlength="6" min="0" max="180000"
                                       style="min-width:120px; width: 280px;" class="input-large number required"/>
                                <span style="color: red">*</span>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">种植面积（亩）</div>
                            <div class="profile-info-value">
                                <input type="text" id="area" name="area" maxlength="12"  min="0"  
                                       style="min-width:120px; width: 280px;" class="input-large number required"/>
                                <input type="hidden" id="geoId" name="geoJson" />
                                <span style="color: red">*</span>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">主营产品</div>
                            <div class="profile-info-value">
                                <c:forEach items="${productTypeList}" var="infoAll">
                                    <input type="checkbox" name="mainProduct"  value="${infoAll.code}"  onclick="mainChecked()"> ${infoAll.name }
                                </c:forEach>
                                <br><p  style="color:red;display:none" id="checkedMainProduct" >主营产品种类不大于5个</p>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">基地描述</div>
                            <div class="profile-info-value">
                                <input type="text" id="describe" name="describe" maxlength="20"
                                       style="min-width:120px; width: 280px;" class="input-large"/>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">周围环境</div>
                            <div class="profile-info-value">
                                <input type="text" id="ambient" name="ambient" maxlength="20"
                                       style="min-width:120px; width: 280px;" class="input-large"/>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">土壤类型</div>
                            <div class="profile-info-value">
                                <select id="soilType" name="soilType" style="min-width:120px; width: 280px;" class="input-large" >
                                   <tags:dicothercache typeCode="${SOILE_TYPE }"/>
                                </select>

                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">污染状况</div>
                            <div class="profile-info-value">
                                <input type="text" id="polluteDesc" name="polluteDesc" maxlength="20"
                                       style="min-width:120px; width: 280px;" class="input-large"/>
                            </div>
                        </div>

                    </div>

                        <div class="clearfix form-actions width-100 pull-right" style="margin-top:10px;background:none;padding: 0;bottom: 0">
                            <div class="col-md-12" style="padding:0;">
                            <button id="btdraw" class="btn btn-primary" type="button" onclick="draw()">
                                <i class="ace-icon fa fa-submit bigger-110"></i>绘制
                            </button>
                            <button class="btn" type="reset" onclick="clearDraw()">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 清除
                            </button>
                            <button class="btn btn-primary" type="button" onclick="checkForm()">
                                <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                            </button>
                            <button class="btn btn-primary" type="button" onclick="continueSubmit()">
                                <i class="ace-icon fa fa-submit bigger-110"></i> 保存并继续
                            </button>
                            <button class="btn" type="reset" onclick="window.location.href='${root}/apb/apbinfo/list/${menuid }/'">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                            </button>
                        </div>
                    </div>
                       </div>
                </td>
            </tr>
        </table>
		<input type="hidden" id="isContinue" name="isContinue" value="0"/>

    </form>
</div>
<script>
	var isSub = false;
	
	var graphic=null;
	var LayerManager,graphic;
	var measureControls = [];
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        $("#inputForm").validate({
        	rules: {
        		longitude: {
        			isCoordinate: true
    			},
    			latitude: {
        			isCoordinate: true
    			}
    		}
        });
        $("#typeCode").focus();
        initMap("map");
        // 初始化地图
        function initMap(mapDiv) {
            if (typeof window["mapTag"] == "undefined")return;
            mapTag().init(mapDiv, function (map) {
                var symbols = map.getSymbolConfig();
                map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
                map.hideInfowindow();
                //绘制所有地块
                MapFactory.Require(["ItmsMap/UserLayers/CustomLayers/Land*"],function(Land){
                	Land().drawRegions();
                })
            });
        }
    });
    
    function draw(){
   	 //绘制区域初始化 
   	 $("#btdraw").attr('disabled',true);
   	MapFactory.Require(["MapFactory/GeometryUtil","MapFactory/GraphicManager","MapFactory/SymbolConfig*","MapFactory/LayerManager"],function(GeometryUtil,Graphic,SymbolConfig,LayerManager){
   		var geoUtil=GeometryUtil();
   		var map = MapFactory._MapManagerResource[MapFactory.Engine];
   	 var sketchSymbolizers = {
   			 "Polygon": {
   					strokeWidth: 2,
   					strokeOpacity: 1,
   					strokeColor: "#666666",
   					fillColor: "white",
   					fillOpacity: 0.3
   				}
   			};
   	 var style = new SuperMap.Style();
   		style.addRules([
   		   new SuperMap.Rule({symbolizer: sketchSymbolizers})
   		]);
        var styleMap = new SuperMap.StyleMap({"default": style});
   		measureControls = {
   				polygon: new SuperMap.Control.Measure(
   						SuperMap.Handler.Polygon, {
   						persist: true,
   						handlerOptions: {
   							layerOptions: {
   								renderers: SuperMap.Layer.Vector.prototype.renderers,
   								styleMap : styleMap
   							}
   						}
   					}
   				)
   			};
   		var control = measureControls["polygon"];
               control.events.on({
                   "measure": _handleMeasurements,
                   "measurepartial": _handleMeasurements
               });
               map.addControl(control);
               measureControls["polygon"].activate();
   	//  每次点击后执行
   	function _handleMeasurements(event) {
   		var units = event.units;
   		var order = event.order;
   		var measure = event.measure;
   		var  out =  measure.toFixed(3)  ;
   	        var resultArea;
   	        if(units=='m'){
   	        	resultArea=out*0.0015;
   	        }else if(units=='km'){
   	        	resultArea=out*1500;
   	        }
   	        $("#area").val(resultArea);
    		LayerManager("defaultLayer").clear();
    		var geometry=geoUtil.convertFromObject(event.geometry);
              graphic=Graphic({geo:geometry,symbol:SymbolConfig.defaultPolygon,attributes:{}});
              var points=graphic.getCenter().points.split(",");
              $("#longitude").val(points[0]);
              $("#latitude").val(points[1]);
              var longitude2=formatDegree(points[0]); 
     		 $("#longitude2").val(longitude2);
     		 var latitude2=formatDegree(points[1]); 
     		 $("#latitude2").val(latitude2);
              graphic.addToLayer("defaultLayer");
              $("#geoId").val("");
              $("#geoId").val(geometry.toString().replace("polygon", "multipolygon"));
          };
       });
   };
   function clearDraw(){
	   $("#btdraw").attr('disabled',false);
	   measureControls["polygon"].deactivate();
   	    MapFactory.Require(["MapFactory/LayerManager"],function(LayerManager){
   		 LayerManager("defaultLayer").clear();
   		 $("#longitude").val("");
         $("#latitude").val("");
         $("#area").val("");
   	      graphic=null;
   	 });
   };
   function mainChecked(){
    	
    	 var checboxs = document.getElementsByName("mainProduct");
    	 var a = 0;
    	 
    	 for (var index = 0; index < checboxs.length; index++) {
    	    if (checboxs[index].checked) {
    	      a = a + 1;
    	     
    	    }
    	  }
    	  
        if(a>5){
        	$("#checkedMainProduct").show();
        	return false;
        }else{
        	$("#checkedMainProduct").hide("slow");
        	
        } 
        return true;
    }
    function continueSubmit() {
    	$("#isContinue").val("1");
    	checkForm();
    }
    function checkForm() {
    	checkCode();
    	var v = $("#inputForm").validate();
    	if (v.checkForm() && isSub&&mainChecked()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
    function checkCode() {
    	var codeObj = $("#code");
    	if (codeObj.val() != "") {
    		$.ajax({
    			async:false,
    			type:"post",
    			url:"${root}/apb/apbinfo/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_APB_INFO&cName=CODE",
    			success:function(data) {
    				if (data.result == "ok") {
    					isSub = true;
    					$("#checkCodeSpan").empty();
    			
    				}else {
    					$("#checkCodeSpan").empty().html(data.message);
    				}
    			}
    		});
    	}
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