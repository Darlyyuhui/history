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
            新增地块信息
        </small>
    </h1>
</div>
<div class="row">
	<form id="inputForm" class="form-horizontal width-100 clearfix"
		action="${root}/land/block/doAdd/${menuid }/" method="post"
		style="margin-bottom: 0; padding: 0;">

		<tags:xiangxuncache keyName="TREGION_LOCATION" id="${item.pointId }" />

		<input type="hidden" id="regionId" name="regionId" />
		<table class="width-100">
			<tr>
				<td>
					<div id="map" style="width: 100%; min-height: 720px !important; position: relative">
						<div id="mapNavigationBox"></div>
						<div id="mapViewSwitcher"></div>
						<div id="mapZoomSlider"></div>
					</div> <!--        地图显示区域 --> <!-- 地图统一样式引入 --> 
					<tags:mapheader rotateImage="true"></tags:mapheader>
				</td>
				<td style="width: 430px; padding-left: 20px; position: relative"
					class="td_h">
					<div class="profile-user-info-striped width-100 pull-right"
						style="border: none; position: absolute; top: 0">
						<div class="profile-info-row">

							<div class="profile-info-name">地块名称</div>
							<div class="profile-info-value">
								<input type="text" id="name" name="name" maxlength="50"
									class="input-large required left-map-input-width" /> <span
									style="color: red">*</span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">地块类型</div>
							<div class="profile-info-value">
								<select id="typeCode" name="typeCode"
									class="required left-map-input-width">
									<tags:diccache typeCode="LAND_BLOCK_TYPE" />
								</select> <span style="color: red">*</span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">所属区域</div>
							<div class="profile-info-value">
								<input type="text" id="regionName" readonly="readonly"
									class="input-large left-map-input-width" />
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">经度</div>
							<div class="profile-info-value">
								<input type="text" id="longitude" name="longitude"
									maxlength="12" class="input-large number left-map-input-width" />
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">纬度</div>
							<div class="profile-info-value">
								<input type="text" id="latitude" name="latitude" maxlength="12"
									class="input-large number left-map-input-width" />
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">土壤类型</div>
							<div class="profile-info-value">
								<input type="text" id="soilTypeName" readonly="readonly"
									class="input-large left-map-input-width" /> <input
									type="hidden" id="soilType" name="soilType" />
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">污染类型</div>
							<div class="profile-info-value">
								<select id="polluteType" name="polluteType"
									class="left-map-input-width">
									<tags:diccache typeCode="LAND_POLLUTE_TYPE" />
								</select>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">面积（亩）</div>
							<div class="profile-info-value">
								<input type="text" id="area" name="area" maxlength="15"
									class="input-large left-map-input-width" /> <input
									type="hidden" id="geoId" name="geoJson" />
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">责任人</div>
							<div class="profile-info-value">
								<select id="ownerId" name="ownerId" class="left-map-input-width">
									<option value="">请选择</option>
									<c:forEach items="${owners }" var="item">
										<option value="${item.id }">${item.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="clearfix form-actions width-100 pull-right"
							style="margin-top: 0; background: none; padding: 0; bottom: 0">

							<div class="col-md-12" style="margin-top: 30px; padding: 0">
								<button id="btdraw" class="btn btn-primary" type="button"
									onclick="draw()">
									<i class="ace-icon fa fa-submit bigger-110"></i>绘制
								</button>
								<button class="btn" type="button" onclick="clearDraw()">
									<i class="ace-icon fa fa-undo bigger-110"></i> 清除
								</button>
								<button class="btn btn-primary" type="button"
									onclick="checkForm()">
									<i class="ace-icon fa fa-submit bigger-110"></i> 保存
								</button>
								<button class="btn" type="reset"
									onclick="window.location.href='${root}/land/block/list/${menuid }/?page=${page }&isgetsession=1'">
									<i class="ace-icon fa fa-undo bigger-110"></i> 返回
								</button>
							</div>
						</div>
					</div>
				</td>

			</tr>


		</table>
	</form>

	<script type="text/javascript">
		/**/
		//给地图动态的添加高度
		$("#map").height(function() {
			var h = $(".td_h").height();
			return h;
		});
		jQuery(function($) {
			var moudle = '${moudelid}';
			if (moudle == '0') {
				$("#sidebar").remove();
			}
		});
		var graphic = null;
		var LayerManager, graphic;
		var measureControls = [];
		var _map;
		// 页面加载成功后，初始化页签和地图
		$(document)
				.ready(
						function() {
							initMap("map");
							// 初始化地图
							function initMap(mapDiv) {
								if (typeof window["mapTag"] == "undefined")
									return;
								mapTag()
										.init(
												mapDiv,
												function(map) {
													_map = map;
													var symbols = map
															.getSymbolConfig();
													map
															.popUpInfowindow(
																	'{"type":"point", "points":"108,34"}',
																	"", "",
																	300, 200);
													map.hideInfowindow();
													MapFactory
															.Require(
																	[ "ItmsMap/UserLayers/CustomLayers/Land*" ],
																	function(
																			Land) {
																		Land()
																				.drawRegions();
																	})

												});
							}

						});
		function regionTreeCB() {
			$.get(
					"${root}/bs/region/getLocation/" + $("#regionId").val()
							+ "/", function(data) {
						if (data) {
							_map.centerAt(data.longitude, data.latitude, 4);
						}

					});
		}

		function draw() {
			$("#btdraw").attr('disabled', true);
			MapFactory
					.Require(
							[ "MapFactory/GeometryUtil",
									"MapFactory/GraphicManager",
									"MapFactory/SymbolConfig*",
									"MapFactory/LayerManager" ],
							function(GeometryUtil, Graphic, SymbolConfig,
									LayerManager) {
								//绘制区域初始化  
								var geoUtil = GeometryUtil();
								var map = MapFactory._MapManagerResource[MapFactory.Engine];
								var sketchSymbolizers = {
									"Polygon" : {
										strokeWidth : 2,
										strokeOpacity : 1,
										strokeColor : "#666666",
										fillColor : "white",
										fillOpacity : 0.3
									}
								};
								var style = new SuperMap.Style();
								style.addRules([ new SuperMap.Rule({
									symbolizer : sketchSymbolizers
								}) ]);
								var styleMap = new SuperMap.StyleMap({
									"default" : style
								});
								measureControls = {
									polygon : new SuperMap.Control.Measure(
											SuperMap.Handler.Polygon,
											{
												persist : true,
												handlerOptions : {
													layerOptions : {
														renderers : SuperMap.Layer.Vector.prototype.renderers,
														styleMap : styleMap
													}
												}
											})
								};
								var control = measureControls["polygon"];
								control.events.on({
									"measure" : _handleMeasurements,
									"measurepartial" : _handleMeasurements
								});
								map.addControl(control);
								measureControls["polygon"].activate();
								//  每次点击后执行
								function _handleMeasurements(event) {
									var units = event.units;
									var order = event.order;
									var measure = event.measure;
									var out = measure.toFixed(3);
									var resultArea;
									if (units == 'm') {
										resultArea = out * 0.0015;
									} else if (units == 'km') {
										resultArea = out * 1500;
									}
									$("#area").val(makeDecimal(resultArea, 4));
									LayerManager("defaultLayer").clear();
									var geometry = geoUtil
											.convertFromObject(event.geometry);
									graphic = Graphic({
										geo : geometry,
										symbol : SymbolConfig.defaultPolygon,
										attributes : {}
									});
									var points = graphic.getCenter().points
											.split(",");
									$("#longitude").val(
											makeDecimal(points[0], 7));
									$("#latitude").val(
											makeDecimal(points[1], 7));
									graphic.addToLayer("defaultLayer");
									$("#geoId").val("");
									$("#geoId").val(
											geometry.toString().replace(
													"polygon", "multipolygon"));
								}
							});

		};
		function clearDraw() {
			$("#btdraw").attr('disabled', false);
			measureControls["polygon"].deactivate();
			MapFactory.Require([ "MapFactory/LayerManager" ], function(
					LayerManager) {
				LayerManager("defaultLayer").clear();
				$("#longitude").val("");
				$("#latitude").val("");
				$("#area").val("");
				graphic = null;
			});
		};
	</script>
<script>
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
    			},
    			area: {
    				decimal4: true
    			}
    		}
        });
        $("#name").focus();
    });
    function checkForm() {
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>