<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${root}/js/business.validate.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
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
       水样底泥采样登记
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/reg/water/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${info.id }" />
        <table class="width-100">
            <tr class="th_d">
                <td style="position: relative">
                    <!--        地图显示区域 -->
                    <div id="map" class="col-xs-12"
                         style="position:absolute;top: 0;width: 100%">
                        <div id="mapNavigationBox"></div>
                        <div id="mapViewSwitcher"></div>
                        <div id="mapZoomSlider"></div>
                    </div>
                    <!--        地图显示区域 -->
                    <!-- 地图统一样式引入 -->
                    <tags:mapheader></tags:mapheader>
                </td>
                <td style="width: 430px;padding-left:20px;position: relative" >
                    <div style="border:none;position: relative;top:0;">
                        <div class="profile-user-info profile-user-info-striped width-100">
                        <div class="profile-info-row">
                            <div class="profile-info-name">编号</div>
                            <div class="profile-info-value">
                                ${info.code }
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">采样任务</div>
                            <div class="profile-info-value">
                                <select id="missionId" name="missionId" onchange="regionTreeCB(this.value)" style="min-width:120px; width: 180px;" class="required">
                                    <option value="">请选择</option>
                                    <c:forEach items="${missions }" var="m">
                                        <option value="${m.id }" ${info.missionId eq m.id ? 'selected' : '' }>${m.name }</option>
                                    </c:forEach>
                                </select>
                                <span style="color: red">*</span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">采样区域</div>
                            <div class="profile-info-value" id="regionDiv">
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">检测项目</div>
                            <div class="profile-info-value" id="testItemsDiv">
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">经度</div>
                            <div class="profile-info-value">
                                <input type="text" id="longitude" name="longitude" maxlength="12" value="${info.longitude }"
                                       style="min-width:120px; width: 180px;" class="input-large number"/>
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">纬度</div>
                            <div class="profile-info-value">
                                <input type="text" id="latitude" name="latitude" maxlength="12" value="${info.latitude }"
                                       style="min-width:120px; width: 180px;" class="input-large number"/>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">类型</div>
                            <div class="profile-info-value">
                                <select id="typeCode" name="typeCode" style="min-width:120px; width: 180px;" class="required">
                                    <tags:diccache typeCode="SAMPLING_WATER_TYPE1" defaultValue="${info.typeCode }"/>
                                </select>
                                <span style="color: red">*</span>
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">河流名称</div>
                            <div class="profile-info-value">
                                <input type="text" id="riversName" name="riversName" maxlength="50" value="${info.riversName }"
                                       style="min-width:120px; width: 180px;" class="input-large" />
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">样品类型</div>
                            <div class="profile-info-value">
                                <select id="samplingType" name="samplingType" style="min-width:120px; width: 180px;" class="required">
                                    <tags:diccache typeCode="SAMPLING_WATER_TYPE2" defaultValue="${info.samplingType }"/>
                                </select>
                                <span style="color: red">*</span>
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">污染源状态</div>
                            <div class="profile-info-value">
                                <select id="polluteSs" name="polluteSs" style="min-width:120px; width: 180px;" class="required">
                                    <tags:diccache typeCode="SAMPLING_WATER_POLLUTE1" defaultValue="${info.polluteSs }"/>
                                </select>
                                <span style="color: red">*</span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">采样人</div>
                            <div class="profile-info-value">
                                <input type="text" id="samplingUser" name="samplingUser" maxlength="20" value="${info.samplingUser }"
                                       style="min-width:120px; width: 180px;" class="input-large"/>
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">采样时间</div>
                            <div class="profile-info-value">
                                <input id="samplingTime" name="samplingTime" type="text" value="<fmt:formatDate value='${info.samplingTime }' pattern='yyyy-MM-dd HH:mm:ss'/>"
                                       class="input-large required" readonly="readonly" style="width: 180px;"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
                                <span style="color: red">*</span>
                            </div>
                        </div>


                        <div class="profile-info-row" >
                            <div class="profile-info-name">现场素材</div>
                            <div class="profile-info-value">
                                <tags:files businessId="${info.id }" isDel="1" />
                                <tags:fileinputs uploadFileTypes=".jpg.jpeg.bmp.png" maxFileSize="20" />
                            </div>
                        </div>
                        </div>
                        <div class="clearfix form-actions width-100 pull-right" style="margin-top:10px;bottom: 0">
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
                            <button class="btn" type="reset" onclick="window.location.href='${root}/reg/water/list/${menuid }/?page=${page }&isgetsession=1'">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                            </button>
                        </div>
                    </div></div>
                </td>
            </tr>
        </table>

    </form>
</div>

<script>
    $("#map").height(function(){
        var h = $(".th_d").height();
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
        
        regionTreeCB("${info.missionId}");
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
                var symbols = map.getSymbolConfig();
                map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
                map.hideInfowindow();
                if("${info.longitude }" && "${info.latitude }"){
                	  map.centerAt("${info.longitude }","${info.latitude }",6);
                }
                longitide="${info.longitude}";
                 if(longitide!=""){
               	    MapFactory.Require(["MapFactory/GeometryType*","MapFactory/GraphicManager", "MapFactory/Geometry*","ItmsMap/SymbolConfig*","ItmsMap/UserLayers/TRPointConfig*"],function(GeometryType,GraphicManager,Geometry,SymbolConfig,TRPointConfig){
               	    	var point=new Geometry({
          					 type : GeometryType.POINT,
          					 points:"${info.longitude},${info.latitude}"
          						});	
               	           var symbol=MapFactory.Clone(SymbolConfig["cydSymbol"]);
   	        	           symbol.url=symbol.url+TRPointConfig["DN"]+".png"; 	 
          				  var graphic=GraphicManager({geo:point,symbol:symbol,attributes:{}});
          				  graphic.addToLayer("defaultLayer");	
                       }); 
                 }
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
     
     function regionTreeCB(missionId) {
     	if (missionId == "") {
     		$("#regionDiv").html("");
 			$("#testItemsDiv").html("");
     	} else {
     		$.post(
       			"${root}/land/mission/getMissionById/"+missionId+"/",
       			function(data) {
       				$("#regionDiv").html(data.regionName);
       				$("#testItemsDiv").html(data.testItems);
       			}
       		);
     	}
     }
     
     
    function checkForm() {
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>