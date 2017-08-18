<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region"  isCallback="1"/>
<tags:selTree idElement="soilType" nameElement="soilTypeName" treeType="landtype" />
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
        土壤地块
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改地块信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/land/block/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;">
          <input type="hidden" name="id" value="${info.id }"/>
          <input type="hidden" name="page" value="${page }"/>
          <input type="hidden" id="regionId" name="regionId" value="${info.regionId }" />
        <table class="width-100">
        	<tr>
        		<td>
        			<!--地图显示区域 -->
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
        		<td style="width: 430px;padding-left:20px;position: relative" class="td_h">
        		<div style="border:none;position: absolute;top:0">
        			<div class="profile-user-info profile-user-info-striped">
        
			            <div class="profile-info-row">
			                <div class="profile-info-name">地块编号</div>
			                <div class="profile-info-value">
			                	${info.code }
							</div>
						</div>
						<div class="profile-info-row">	
							<div class="profile-info-name">地块名称</div>
			                <div class="profile-info-value">
			                	<input type="text" id="name" name="name" maxlength="50" value="${info.name }"
									style="min-width:120px; width: 300px;" class="input-large required"/>
								<span style="color: red">*</span>
							</div>
			            </div>
			            
            <div class="profile-info-row">
                <div class="profile-info-name">地块类型</div>
                <div class="profile-info-value">
					<select id="typeCode" name="typeCode" class="required" style="width: 300px;">
						<tags:diccache typeCode="LAND_BLOCK_TYPE" defaultValue="${info.typeCode }"/>
					</select>
					<span style="color: red">*</span>
				</div>
			</div>
			<div class="profile-info-row">	
				<div class="profile-info-name">所属区域</div>
                <div class="profile-info-value">
					<input type="text" id="regionName" readonly="readonly" value='<tags:xiangxuncache keyName="TREGION_NAME" id="${info.regionId }"/>'
						style="min-width:120px; width: 300px;" class="input-large required" onclick="showRegion('regionId','regionName')"/>
					
					<span style="color: red">*</span>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">经度</div>
                <div class="profile-info-value">
					<input type="text" id="longitude" name="longitude" maxlength="12" value="${info.longitude }"
						style="min-width:120px; width: 300px;" class="input-large number"/>
				</div>
			</div>
			<div class="profile-info-row">	
				<div class="profile-info-name">纬度</div>
                <div class="profile-info-value">
					<input type="text" id="latitude" name="latitude" maxlength="12" value="${info.latitude }"
						style="min-width:120px; width: 300px;" class="input-large number"/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">土壤类型</div>
                <div class="profile-info-value">
					<input type="text" id="soilTypeName" readonly="readonly" value='<tags:xiangxuncache keyName="LANDTYPE_NAME" id="${info.soilType }"/>'
						style="min-width:120px; width: 300px;" class="input-large" />
					<input type="hidden" id="soilType" name="soilType" value="${info.soilType }" />
				</div>
			</div>
			<div class="profile-info-row">	
				<div class="profile-info-name">污染类型</div>
                <div class="profile-info-value">
					<select id="polluteType" name="polluteType" style="width: 300px;">
						<tags:diccache typeCode="LAND_POLLUTE_TYPE" defaultValue="${info.polluteType }"/>
					</select>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">面积（亩）</div>
                <div class="profile-info-value">
                    <input type="hidden" id="geoId"  name="geoJson"/>
					<input type="text" id="area" name="area" maxlength="20" value="${info.area }"
						style="min-width:120px; width: 300px;" class="input-large"/>
				</div>
			</div>	
			<div class="profile-info-row">	
				<div class="profile-info-name">责任人</div>
                <div class="profile-info-value">
					<select id="ownerId" name="ownerId" style="min-width:120px; width: 300px;">
						<option value="">请选择</option>
						<c:forEach items="${owners }" var="item">
							<option value="${item.id }" ${info.ownerId eq item.id ? 'selected' : '' }>${item.name }</option>
						</c:forEach>
					</select>
				</div>
            </div>
            
              </div>
              <div class="clearfix form-actions width-100 pull-right" style="margin-top:0;background:none;padding: 0;padding-left:12px;bottom: 0">

                        <div class="col-md-12" style="margin-top:30px;padding:0">
                            <button id="btdraw" class="btn btn-primary" type="button" onclick="draw()">
                                <i class="ace-icon fa fa-submit bigger-110"></i>绘制
                            </button>
                            <button  class="btn" type="reset" onclick="clearDraw()">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 清除
                            </button>
                            <button class="btn btn-primary" type="button" onclick="checkForm()">
                                <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                            </button>
                            <button class="btn" type="reset" onclick="window.location.href='${root}/land/block/list/${menuid }/?page=${page }&isgetsession=1'">
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
$("#map").height(function(){
    var h = $(".td_h").height();
    return h;
});
	var isCheck = false;
	var v;
	var _LayerManager,graphic;
	var measureControls = [];
	var geoUtil,_Graphic,_SymbolConfig;
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
    			},
    			area: {
    				decimal4: true
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
            	_map=map;
                var symbols = map.getSymbolConfig();
                map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
                map.hideInfowindow();
                if("${info.longitude }" && "${info.latitude }"){
                    map.centerAt("${info.longitude }","${info.latitude }",6);
                }
         
                var addGeo ='${info.geoJson}';
    			if(addGeo) {
    			    var addJson = eval("(" + addGeo + ")");
    				map.addGeometry(addJson, "defaultLayer", true);
    				}
    			initControl();
    			 MapFactory.Require(["ItmsMap/UserLayers/CustomLayers/Land*"],function(Land){
    			    Land().drawRegions('${info.id}');
    	            })
            });
        } 
    });
    //所属区域回掉函数
    function regionTreeCB() {
    	$.get(
    		"${root}/bs/region/getLocation/"+$("#regionId").val()+"/",
    		function(data) {
    			_map.centerAt(data.longitude,data.latitude,6);
    		}
    	);
    }
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
   	};
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
   	     	$("#area").val(makeDecimal(resultArea, 4));
   	        _LayerManager("defaultLayer").clear();
	        var geometry=geoUtil.convertFromObject(event.geometry);
	         graphic=_Graphic({geo:geometry,symbol:_SymbolConfig.defaultPolygon,attributes:{}});
	         var points=graphic.getCenter().points.split(",");
	           $("#longitude").val(makeDecimal(points[0], 7));
	           $("#latitude").val(makeDecimal(points[1], 7));
	           graphic.addToLayer("defaultLayer");
	           $("#geoId").val("");
	           $("#geoId").val(geometry.toString().replace("polygon", "multipolygon")); 
     	}
   function clearDraw(){
	   $("#btdraw").attr('disabled',false);
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
    function checkForm() {
    	checkCode();
    	if (v.checkForm() && isCheck) {
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
    			url:"${root}/land/block/checkCode/"+codeObj.val()+"/",
    			data:"tName=T_LAND_BLOCK&cName=CODE",
    			success:function(data) {
    				if (data.result == "ok") {
    					isCheck = true;
    					$("#checkCodeSpan").empty();
    				}else {
    					$("#checkCodeSpan").empty().html(data.message);
    				}
    			}
    		});
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>