/**
 * 定位组建
 */
MapFactory
		.Define(
				"ItmsMap/Location/LocationWidget*",
				[ "ItmsMap/MapConfig", "MapFactory/GraphicManager",
						"MapFactory/LayerManager", "MapFactory/Util/Dialog*",
						"MapFactory/Query", "MapFactory/MapManager",
						"MapFactory/InfoWindowManager",
						"ItmsMap/Util/ResItemO*", "ItmsMap/Util/ResultBox*",
						"ItmsMap/Util/Pager*" , "MapFactory/Geometry*",
						"MapFactory/GeometryType*","MapFactory/SymbolConfig*"],
				function(mapConfig, GraphicManager, LayerManager, Dialog,
						Query, MapManager, InfoWindow, ResItemO, resultBox,
						Pager,Geometry,GeometryType,SymbolConfig) {
					var _dialog = null;
					return function(conf) {
						var _container = null, _locationBox = null, _mapManager = MapManager();
						var natureQuery = Query();
						natureQuery.setUrl(mapConfig.layers.naturePolygon.url);
						var _conf = {
							mapDiv : "",
							right : 10,
							top : 35
						};

						var api = {
							/**
							 * 显示
							 */
							show : show,

							/**
							 * 隐藏
							 */
							hide : hide
						};

						MapFactory.Extend(_conf, conf);

						function _initContainer() {
						
							_dialog = Dialog(
									{
										mapDiv : _conf.mapDiv,
										mutiDialog : true,
										mutiDialogSeed : "location",
										title : '坐标定位',
										content : '<form class="bs-example bs-example-form" role="form" id="locationDIV" style="width:298px;padding-left:0px;padding-top:0px;text-align: center;">',
										mask : false,
										zindex : 10000,
										right : _conf.right,
										top : _conf.top,
										confirmButton : "定位",
										cancelButton : '清空',
										closeCall : function() {
											LayerManager("natureLocationQuery")
													.clear();
											LayerManager("naturePolygon")
											.clear();
											
											InfoWindow().hide();
										},
										cancelButtonCall : _locationQueryEvt,
										confirmButtonCall : _locationEvt
									}).show();
							_container = $("#locationDIV");

							_container
									.append('<div id="search_div" class="DialogDivBox"/>');
							var search_div = $("#search_div");
							var search_tab = $('<ul id="search_tab" class="DialogUl"></ul');
							search_div.append(search_tab);
							search_tab
									.append($('<li id="tab1"   class="Dialogli" >坐标定位</li>'));
							search_tab
									.append($('<li id="tab2"   class="Dialogli" >地区查询</li>'));
							search_tab
									.append($('<li id="tab3"   class="Dialogli" >查询结果</li>'));
							_container
									.append('<div id="content_div"  class="DialogDivBox"/>');
							var content_div = $('#content_div');
							var lonlatiDiv = $('<div id="lonlatiDiv"></div>');
							var nameDiv = $('<div id="nameDiv"></div>');
							var resultSetDiv = $('<div id="resultSetDiv" ></div>');

							content_div.append(lonlatiDiv);
							var _locationX = $('<div class="input-group"><span class="input-group-addon">经度</span> <input type="text" class="form-control" id="_lon" placeholder="请输入经度"></div><br>');
							lonlatiDiv.append(_locationX);
							var _locationY = $('<div class="input-group"><span class="input-group-addon">纬度</span> <input type="text" class="form-control" id="_lat" placeholder="请输入纬度"></div><br>');
							lonlatiDiv.append(_locationY);

							content_div.append(nameDiv);
							var inputName = $('<div id="inputName" class="input-group"><span class="input-group-addon">地名</span> <input type="text" class="form-control" id="natureName" placeholder="请输入乡镇名称"></div>');
							nameDiv.append(inputName);
							content_div.append(resultSetDiv);
							$("#resultSetDiv").append($('<span id="resulttip" style="margin-top:10px">无查询结果或未进行查询操作</span>'));
							addtab1();
							// 点击标签事件
							$("#tab1").click(function() {
								addtab1()
							})
							$("#tab2").click(function() {
								$("#lonlatiDiv").hide();
								$("#inputName").show();
								$("#resultSetDiv").hide();
								$("#DialogBoxFooter_location").show();

							})
							$("#tab3").click(function() {
								$("#lonlatiDiv").hide();
								$("#inputName").hide();
								$("#resultSetDiv").show();
								
								$("#DialogBoxFooter_location").hide();

							})

							function addtab1() {
								$("#lonlatiDiv").show();
								$("#inputName").hide();
								$("#resultSetDiv").hide();
								$("#DialogBoxFooter_location").show();
							}
						}

						var pageMax = 5; // 每页最大显示列
						var contentAtt = [];
						var pageNum=1;//当前页码
						function _locationEvt() {
						
							 pageNum=0;
							 contentAtt = [];
							if ($('#lonlatiDiv').is(':visible')) {
								var lon = $("#_lon").val();
								var lat = $("#_lat").val();
								if (isNaN(lon) || isNaN(lat)) {
									alert("请输入正确的经纬度值！");
									return;
								}
								var extent = _mapManager.getMaxExtent();
								var x = parseFloat(lon), y = parseFloat(lat);
								if (extent.minX < x && x < extent.maxX
										&& extent.minY < y && y < extent.maxY) {
									_mapManager.centerAt(x, y);
								} else {
									alert("您输入的坐标值超出地图范围，请重新输入！");
								}
							} else if ($("#inputName").is(':visible')) {
								var name = $('#natureName').val();
								if (!callback)
									return;
								natureQuery
										.setUrl(mapConfig.layers.naturePolygon.url);
								natureQuery.setGeometry(null);
								natureQuery.setCondition({
									"NAME like" : "%" + name + "%"
								});
								natureQuery.execute(function(datas) {
									if (datas && datas.length) {
										callback(datas);
									} else {
										callback("");
									}
								}, function(error) {
									if (typeof console != 'undefined'
											&& console.log)
										console.log("地区数据查询的时候，出错了！" + error);
									callback(null);
								});

								function callback(dataset) {
								
									if (dataset instanceof Array) {
										$("#resulttip").hide();
										if (dataset.length % 5 == '0') {
											pageMax = dataset.length / 5;
										} else {
											pageMax = Math.ceil(dataset.length / 5);
										}
										resultList(dataset);

									} else if (dataset == '') {
                                     //当查询结果为空或者没查询到时
										$("#lonlatiDiv").hide();
										$("#inputName").hide();
										$("#resultSetDiv").show();
										$("#resulttip").show();
										$("#DialogBoxFooter_location").hide();
										resultList(dataset);
									} 

								}
								  var graphicClick=function(_graphic) {
								         var graphic=_graphic.graphic;
								         if(!graphic){
								           graphic=_graphic.getGraphic();
								         };
								      //   graphic.symbol=SymbolConfig.highLight;
								    	  var geo=graphic.geo;
								    	  var attr=graphic.attributes;
								          var points=geo.points.split(",");
									         var x=parseFloat(points[0]);
									         var y=parseFloat(points[1]);
									            _mapManager.centerAndZoom(x,y,5);
								      };

								var resultList = function(result) {
									contentAtt = [];
									if (!result || result.length < 1 ||result == '') {
										resultBox().init("resultSetDiv",false)
												.addContent({
													content : [],
													switchtab : false
												});
									} else {
										  if(pageNum=='0'){pageNum=1};
										for(var i=(pageNum-1)*5,j=1; i<result.length && j<=5; i++,j++) {
											var data = result[i];
											 var point=new Geometry({
												 type : GeometryType.POINT,
												 points:data.attributes.SmX+","+data.attributes.SmY
													});	
											var graphic=GraphicManager({geo:point,symbol:"",attributes:data});
											graphic.addToLayer("naturePolygon");
											contentAtt.push(new ResItemO(
													data.attributes.NAME, (function(r) {
														return function() {
															graphicClick(r);
														}
													})(graphic), data));

										}

										resultBox().init("resultSetDiv",false)
												.addContent({
													content : contentAtt,
													switchtab : true
												});
										$("#lonlatiDiv").hide();
										$("#inputName").hide();
										$("#resultSetDiv").show();
										$("#DialogBoxFooter_location").hide();
										Pager({
											parentSrc : "resultSetDiv",
											currentPage : pageNum,
											pageSize : 5,
											totalNum : result.length,
											pageOffset : 1,
											enableJump : true,
											enableStatistic : true,
											clickEvt : function(e) {
												pageNum = e.currentPage;
												if (pageNum <= pageMax) {
													resultList(result);
												}
											}
										});
										

									}

								}

							}

						}
						LayerManager("natureLocationQuery").addOnClickEvent(
								function(g) {
									var content = g.graphic.attributes.content;
									var info = InfoWindow();
									info.setAnchor(g.graphic.geo);
								//	info.setTitle("保护区检查");
									info.setContent(content);
									info.showAndCenter();
								});

						var pointStyle = {
							url : path + "/images/map/position.png",
							width : 34,
							height : 40,
							xOffset : 8,
							yOffset : 20
						};
						function _locationQueryEvt() {
						
							var lon = $("#_lon").val();
							var lat = $("#_lat").val();
							if (isNaN(lon) || isNaN(lat)) {
								alert("请输入正确的经纬度值！");
								return;
							}
							LayerManager("natureLocationQuery").clear();
							InfoWindow().hide();
							var extent = _mapManager.getMaxExtent();
							var x = parseFloat(lon), y = parseFloat(lat);
							if (extent.minX < x && x < extent.maxX
									&& extent.minY < y && y < extent.maxY) {
								natureQuery.setGeometry({
									"type" : "point",
									"points" : x + "," + y
								});
								natureQuery.setCondition({});
								natureQuery.setSpatialRelationShip("overlap");
								natureQuery
										.execute(
												function(result) {
													var content = "<div style='width:300px;height:100px;'>不在绵阳市范围内";
													if (result.length > 0)
														content = "<div style='width:300px;height:100px;'>所在乡镇名称为：";
													for (var i = 0, len = result.length; i < len; i++) {
														content += "<h4 style='color: #0089ff;'>"
																+ result[i].attributes.NAME
																+ "</h4>";
													}
													content += "</div>";
													var info = InfoWindow();
													info.setAnchor({
														"type" : "point",
														"points" : x + "," + y
													});
												//	info.setTitle("保护区检查");
													info.setContent(content);
													info.showAndCenter();

													var graphic = GraphicManager({
														geo : {
															"type" : "point",
															"points" : x + ","
																	+ y
														},
														symbol : pointStyle,
														attributes : {
															"content" : content
														}
													});
													graphic
															.addToLayer("natureLocationQuery");
													_mapManager.centerAt(x, y);
												},
												function(error) {
													if (typeof console != 'undefined'
															&& console.log)
														console
																.log("坐标点查询乡镇区域出错了！"
																		+ error);
												});
							} else {
								$("#_lon").val("");
								$("#lat").val("");
								$("#natureName").val("");
								 LayerManager("naturePolygon").clear();
							}
						}

						function show() {
							if (!_dialog) {
								_initContainer();
							} else {
								_dialog.show();
							}
						}

						function hide() {
							if (_dialog) {
								_dialog.hide();
							}
						}
						return api;
					}
				});