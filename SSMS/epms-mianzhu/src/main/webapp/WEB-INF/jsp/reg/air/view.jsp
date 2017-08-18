<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="page-header">
    <h1>
        大气沉降采样登记
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            查看信息
        </small>
    </h1>
</div>
<div class="row">
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
                    <!-- 地图统一样式引入 -->
                    <tags:mapheader></tags:mapheader>
                </td>
            <td style="width: 430px;padding-left:20px;position: relative">
                <div style="border:none;position: absolute;top:0;width: 90%">
                    <div class="profile-user-info profile-user-info-striped width-100">

                        <div class="profile-info-row">
                            <div class="profile-info-name">样品编号</div>
                            <div class="profile-info-value" width="200">
                                ${info.code }
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">采样任务</div>
                            <div class="profile-info-value" width="200">
                                ${mission.name }
                            </div>

                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">采样地点</div>
                            <div class="profile-info-value" width="200">
                                <tags:xiangxuncache keyName="AIRPOINT_ID_NAME" id="${info.pointId }" />
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">采样时间</div>
                            <div class="profile-info-value" width="200">
                                <fmt:formatDate value='${info.samplingTime }' pattern='yyyy-MM-dd HH:mm:ss'/>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">容器体积</div>
                            <div class="profile-info-value" width="200">
                                ${info.containerVolume }
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">收集量</div>
                            <div class="profile-info-value" width="200">
                                ${info.collectVolume }
                            </div>

                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">采样人</div>
                            <div class="profile-info-value" width="200">
                                ${info.samplingUser }
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name"></div>
                            <div class="profile-info-value" width="200">
                            </div>

                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name">审查状态</div>
                            <div class="profile-info-value" width="200">
                                <c:if test="${info.checkStatus eq '0'}">未审查</c:if>
                                <c:if test="${info.checkStatus eq '1'}">已审查</c:if>
                            </div></div>
                        <div class="profile-info-row">
                            <div class="profile-info-name">审查人</div>
                            <div class="profile-info-value" width="200">
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
                                <input type="hidden" id="location" value='<tags:xiangxuncache keyName="AIRPOINT_ID_LOCATION" id="${info.pointId }" />' />
                            </div>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-2 col-md-10">
                            <button class="btn" type="reset" onclick="window.location.href='${root}/reg/air/list/${menuid }/?page=${page }&isgetsession=1'">
                                <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                            </button>
                        </div>
                    </div> </div>
                </td>
            </tr>
        </table>

</div>
<script type="text/javascript">
// 页面加载成功后，初始化页签和地图
$(document).ready(function(){
	initMap("map");
// 初始化地图
function initMap(mapDiv) {
    if (typeof window["mapTag"] == "undefined")return;
    mapTag().init(mapDiv, function (map) {
        var symbols = map.getSymbolConfig();
        map.popUpInfowindow('{"type":"point", "points":"108,34"}', "", "", 300, 200);
        map.hideInfowindow();
       var points=$("#location").val().split(",");
         if(points.length>0){
       	    MapFactory.Require(["MapFactory/GeometryType*","MapFactory/GraphicManager", "MapFactory/Geometry*"],function(GeometryType,GraphicManager,Geometry){
       	    	var point=new Geometry({
  					 type : GeometryType.POINT,
  					 points:points[0]+","+points[1]
  						});	
  				  var graphic=GraphicManager({geo:point,symbol:"",attributes:{}});
  				  graphic.addToLayer("defaultLayer");	
               }); 
         }
    });
}
});


</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>