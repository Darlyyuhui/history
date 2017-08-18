<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="page-header">
    <h1>
        基地产品信息
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改基地产品信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/apb/apbinfo/doUpdate/${menuid }/" method="post"
    enctype="multipart/form-data">
    <input type="hidden" name="menuid" value="${menuid}"/>
        <input type="hidden" name="page" value="${page}"/>
        <input type="hidden" name="id" value="${info.id}"/>
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
        		<td style="width: 450px;padding-left:20px;position: relative">
                    <div style="border:none;position: absolute;top:0;">
                        <div class="profile-user-info profile-user-info-striped width-100">
             <div class="profile-info-row">
                <div class="profile-info-name">编号</div>
                <div class="profile-info-value">
                	<input type="text" id="code" name="code" maxlength="20"   value="${info.code}"
						style="min-width:120px; width: 250px;" class="input-large required"/>
					<span style="color: red">*</span>
					<span id="checkCodeSpan" style="color: red"></span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">名称</div>
                <div class="profile-info-value">
                	<input type="text" id="name" name="name" maxlength="12"   value="${info.name}"
						style="min-width:120px; width: 250px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">N（北纬）</div>
                <div class="profile-info-value">
                  <input type="hidden" id="latitude" name="latitude" maxlength="12"    value="${info.latitude}"
						style="min-width:120px; width: 250px;" class="input-large "/>
						    <input type="text" id="latitude2" name="latitude2" maxlength="20"   
						style="min-width:120px; width: 250px;" class="input-large "/>
                
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">E（东经）</div>
                <div class="profile-info-value">
                	<input type="hidden" id="longitude" name="longitude" maxlength="12"   value="${info.longitude}"
						style="min-width:120px; width: 250px;" class="input-large"/>
						<input type="text" id="longitude2" name="longitude2" maxlength="20"   
						style="min-width:120px; width: 250px;" class="input-large"/>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">详细地址</div>
                <div class="profile-info-value">
                	<input type="text" id="address" name="address" maxlength="50"   value="${info.address}"
						style="min-width:120px; width: 250px;" class="input-large"/>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">年产量（万吨）</div>
                <div class="profile-info-value">
                	<input type="text" id="annualOutput" name="annualOutput" maxlength="6" min="0" max="180000"
                	 value="${info.annualOutput}"
						style="min-width:120px; width: 250px;" class="input-large number required"/>
					<span style="color: red">*</span>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">种植面积（亩）</div>
                <div class="profile-info-value">
                	<input type="text" id="area" name="area" maxlength="12"  min="0"  value="${info.area}"
						style="min-width:120px; width: 250px;" class="input-large number required"/>
					<input type="hidden" id="geoId" name="geoJson" />
					<span style="color: red">*</span>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">主营产品</div>
                <div class="profile-info-value">
                <c:forEach items="${productTypeList}" var="infoAll">
                      <input type="checkbox" name="mainProduct"  value="${infoAll.code }"   
                      <c:forEach items="${productTypeNameList}" var="info"><c:if test="${info== infoAll.name}">
                      checked    </c:if> </c:forEach>  onclick="mainChecked()">
                     ${infoAll.name }
                 </c:forEach>
                 <br><p  style="color:red;display:none" id="checkedMainProduct" >主营产品种类不大于5个</p>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">基地描述</div>
                <div class="profile-info-value">
                	<input type="text" id="describe" name="describe" maxlength="20"  value="${info.describe}"
						style="min-width:120px; width: 250px;" class="input-large"/>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">周围环境</div>
                <div class="profile-info-value">
                	<input type="text" id="ambient" name="ambient" maxlength="20"   value="${info.ambient}"
						style="min-width:120px; width: 250px;" class="input-large"/>
				</div>
            </div>
             <div class="profile-info-row">
                <div class="profile-info-name">土壤类型</div>
                <div class="profile-info-value">
                 <select id="soilType" name="soilType" style="min-width:120px; width: 250px;" class="input-large" >
                   <tags:dicothercache typeCode="${SOILE_TYPE }"   defaultValue="${ info.soilType}"/>
                 </select>
				</div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">污染状况</div>
                <div class="profile-info-value">
                	<input type="text" id="polluteDesc" name="polluteDesc" maxlength="20"   value="${info.polluteDesc}"
						style="min-width:120px; width: 250px;" class="input-large"/>
				</div>
            </div>
        </div>
        	
        <div class="clearfix form-actions width-100 pull-right" style="margin-top:10px;bottom: 0">
                            <div class="col-md-12" style="padding:0;">
            <button id="btdraw"  class="btn btn-primary" type="button" onclick="draw()">
                    <i class="ace-icon fa fa-submit bigger-110"></i>绘制
                </button>
                <button class="btn" type="button" onclick="clearDraw()">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 清除
                </button>
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 修改
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/apb/apbinfo/list/${menuid }/?page=${page}'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div></div></div>
        		</td>
        	</tr>
        </table>
       
       
    </form>
</div>
<script>
var _LayerManager,graphic;
var measureControls = [];
var geoUtil,_Graphic,_SymbolConfig;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        $("#inputForm").validate();
        // 页面加载成功后，初始化页签和地图
        initMap("map");
        // 初始化地图
        if($("#longitude").val()!=""){
			 var longitude=formatDegree($("#longitude").val()); 
			 $("#longitude2").val(longitude);
			 var latitude=formatDegree($("#latitude").val()); 
			 $("#latitude2").val(latitude); 
		 }
        function initMap(mapDiv) {
            if (typeof window["mapTag"] == "undefined")return;
            mapTag().init(mapDiv, function (map) {
                var symbols = map.getSymbolConfig();
                map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
                map.hideInfowindow();
                if("${info.longitude}" && "${info.latitude }"){
                	  map.centerAt("${info.longitude}","${info.latitude }",6);
                 }
                var addGeo ='${info.geoJson}';
    			if(addGeo) {
    				 $("#geoId").val(addGeo);
    			    var addJson = eval("(" + addGeo + ")");
    				map.addGeometry(addJson, "defaultLayer", true);
    				}
    			 initControl();
    			 
    			 //绘制所有已画地块
    			  MapFactory.Require(["ItmsMap/UserLayers/CustomLayers/Land*"],function(Land){
            	Land().drawRegions('${info.id}');
            })
            });
        } 
    });
    function initControl(){
        MapFactory.Require(["MapFactory/GeometryUtil","MapFactory/GraphicManager","MapFactory/SymbolConfig*","MapFactory/LayerManager"],function(GeometryUtil,Graphic,SymbolConfig,LayerManager){
       	geoUtil=GeometryUtil();
       	_LayerManager=LayerManager;
       	_Graphic=Graphic;
       	_SymbolConfig=SymbolConfig;
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
       	});   
    	
    }  
    
    function draw(){
    	 //绘制区域初始化
      	 $("#btdraw").attr('disabled',true);
        measureControls["polygon"].activate();
    }
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
    	     _LayerManager("defaultLayer").clear();
    	     var geometry=geoUtil.convertFromObject(event.geometry);
              graphic=_Graphic({geo:geometry,symbol:_SymbolConfig.defaultPolygon,attributes:{}});
              graphic.addToLayer("defaultLayer");
              var points=graphic.getCenter().points.split(",");
              $("#longitude").val(points[0]);
              $("#latitude").val(points[1]);
             var longitude2=formatDegree(points[0]); 
   		     $("#longitude2").val(longitude2);
   		     var latitude2=formatDegree(points[1]); 
   		      $("#latitude2").val(latitude2);
              $("#geoId").val(geometry.toString().replace("polygon", "multipolygon"));
    	}
      function clearDraw(){
    	  $("#btdraw").attr('disabled',false);
   	      $("#longitude2").val("");
          $("#latitude2").val("");
          $("#longitude").val("");
          $("#latitude").val("");
          $("#area").val("");
          $("#geoId").val("");
          measureControls["polygon"].deactivate();
      	   MapFactory.Require(["MapFactory/LayerManager"],function(LayerManager){
      		 LayerManager("defaultLayer").clear();
      	      graphic=null;
      	 });
      };
      function formatDegree(value) {  
          ///<summary>将度转换成为度分秒</summary>  
          value = Math.abs(value);  
          var v1 = Math.floor(value);//度  
          var v2 = Math.floor((value - v1) * 60);//分  
          var v3 = Math.round((value - v1) * 3600 % 60);//秒  
          return v1 + '°' + v2 + '\'' + v3 + '"';  
      }; 
      function  mainChecked(){
        	
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
      };
      function checkForm() {
      	
      	var v = $("#inputForm").validate();
      	if (v.checkForm() &&mainChecked()) {
      		$("#inputForm").submit();
      	}else{
      		v.showErrors();
      	}
      }
   
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>