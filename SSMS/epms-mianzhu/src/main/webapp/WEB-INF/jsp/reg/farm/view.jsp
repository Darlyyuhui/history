<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="page-header">
    <h1>
        农产品采样登记
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            查看信息
        </small>
    </h1>
</div>
<div class="row">
    <table class="width-100">
        <tr class="td_h">
            <td style="position: relative">
                <!--        地图显示区域 -->
                <div id="map" class="col-xs-12"
                     style="position:absolute;top:0;height:720px;">
                    <div id="mapNavigationBox"></div>
                    <div id="mapViewSwitcher"></div>
                    <div id="mapZoomSlider"></div>
                </div>
                <!--        地图显示区域 -->
                <!-- 地图统一样式引入 -->
                <tags:mapheader></tags:mapheader>
            </td>
            <td style="width: 430px;padding-left:20px;">
                <div style="border:none;width: 100%;min-height: 720px;">
                    <div class="profile-user-info profile-user-info-striped width-100">

                    <div class="profile-info-row">
                        <div class="profile-info-name">样品编号</div>
                        <div class="profile-info-value">
                            ${info.code }
                        </div></div>
                    <div class="profile-info-row">
                        <div class="profile-info-name">采样任务</div>
                        <div class="profile-info-value">
                            ${mission.name }
                        </div>
                    </div>

                    <div class="profile-info-row">
                        <div class="profile-info-name">采样区域</div>
                        <div class="profile-info-value" id="regionDiv">
                            ${mission.regionName }
                        </div></div>
                    <div class="profile-info-row">
                        <div class="profile-info-name">检测项目</div>
                        <div class="profile-info-value" id="testItemsDiv">
                            ${mission.testItems }
                        </div>
                    </div>

                    <div class="profile-info-row">
                        <div class="profile-info-name">样品名称</div>
                        <div class="profile-info-value">
                            ${info.name }
                        </div></div>
                    <div class="profile-info-row">
                        <div class="profile-info-name">采样部位</div>
                        <div class="profile-info-value">
                            ${info.position }
                        </div>
                    </div>

                    <div class="profile-info-row">
                        <div class="profile-info-name">经度</div>
                        <div class="profile-info-value">
                            ${info.longitude }
                        </div></div>
                    <div class="profile-info-row">
                        <div class="profile-info-name">纬度</div>
                        <div class="profile-info-value">
                            ${info.latitude }
                        </div>
                    </div>

                    <div class="profile-info-row">
                        <div class="profile-info-name">采样人</div>
                        <div class="profile-info-value">
                            ${info.samplingUser }
                        </div></div>
                    <div class="profile-info-row">
                        <div class="profile-info-name">采样时间</div>
                        <div class="profile-info-value">
                            <fmt:formatDate value='${info.samplingTime }' pattern='yyyy-MM-dd HH:mm:ss'/>
                        </div>
                    </div>

                    <div class="profile-info-row">
                        <div class="profile-info-name">收样人</div>
                        <div class="profile-info-value">
                            ${info.receiveUser }
                        </div></div>
                    <div class="profile-info-row">
                        <div class="profile-info-name">送样人</div>
                        <div class="profile-info-value">
                            ${info.sendUser }
                        </div>
                    </div>

                    <div class="profile-info-row">
                        <div class="profile-info-name">审查状态</div>
                        <div class="profile-info-value">
                            <c:if test="${info.checkStatus eq '0'}">未审查</c:if>
                            <c:if test="${info.checkStatus eq '1'}">已审查</c:if>
                        </div></div>
                    <div class="profile-info-row">
                        <div class="profile-info-name">审查人</div>
                        <div class="profile-info-value">
                            <c:if test="${empty info.checkUser }">
                                无
                            </c:if>
                            <c:if test="${not empty info.checkUser }">
                                <tags:xiangxuncache keyName="username_cache" id="${info.checkUser }"></tags:xiangxuncache>
                                （<fmt:formatDate value="${info.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/>）
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="profile-user-info profile-user-info-striped width-100" style="border-top-style: none;">
                    <div class="profile-info-row" >
                        <div class="profile-info-name">现场素材</div>
                        <div class="profile-info-value">
                            <tags:files businessId="${info.id }" />
                            <tags:fileinputs uploadFileTypes=".jpg.jpeg.bmp.png" maxFileSize="20" />
                        </div>
                    </div>
                </div>


                    <div class="clearfix form-actions width-100 pull-right" style="margin-top:10px;bottom: 0">
                        <div class="col-md-12" style="padding:0;">
                        <button class="btn" type="reset" onclick="window.location.href='${root}/reg/farm/list/${menuid }/?page=${page }&isgetsession=1'">
                            <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                        </button>
                    </div>
                </div></div>
            </td>
        </tr>
    </table>

    </form>
</div>
<script type="text/javascript">
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
        var longitide="";
        longitide="${info.longitude}";
         if(longitide!=""){
       	    MapFactory.Require(["MapFactory/GeometryType*","MapFactory/GraphicManager", "MapFactory/Geometry*","ItmsMap/SymbolConfig*","ItmsMap/UserLayers/TRPointConfig*"],function(GeometryType,GraphicManager,Geometry,SymbolConfig,TRPointConfig){
       	    	var point=new Geometry({
  					 type : GeometryType.POINT,
  					 points:"${info.longitude},${info.latitude}"
  						});	
       	     var symbol=MapFactory.Clone(SymbolConfig["cydSymbol"]);
        	 symbol.url=symbol.url+TRPointConfig["NZW"]+".png"; 	 
            var   graphic= GraphicManager({geo:point,symbol:symbol,attributes:{}});
  				  graphic.addToLayer("defaultLayer");	
               }); 
         }
    });
}

</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>