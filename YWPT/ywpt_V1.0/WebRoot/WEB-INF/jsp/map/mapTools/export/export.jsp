<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>地图导出</title>
		<link rel="stylesheet" href="css/GisStyle/Dialog.css" type="text/css" media="all"/>
		<link id="CommonCss" rel="stylesheet" href="css/Common.css" type="text/css" media="all"/>
		<link id="CommonCss" rel="stylesheet" href="css/transport.css" type="text/css" media="all"/>
		<link id="CommonCss" rel="stylesheet" href="compnents/bootstrap/css/bootstrap.css" type="text/css" media="all"/>
		<script type="text/javascript">
		    // 把字符串数组转换成数字数组
			function strListToNumberList(list) {
				var result = [];
				for(var i=0,len=list.length; i<len; i++) {
					result[i] = Number(list[i]);
				}
				return result;
			}
			
			var path = "<%=path%>",
				basePath = "<%=basePath%>",
				menuid = "${menuid}",
				arcgisbaseurl = "${atms.arcgis_rest_url}",
				supermapbaseurl = "${atms.supermap_base_url}",
				geoserverbaseurl = "${atms.geoserver_base_url}",
				map_center_zoom = "${atms.map_center_zoom}",
				map_max_extent = "${atms.map_max_extent}",
				geoserver_resolutions = "${atms.geoserver_resolutions}",
				supermap_resolutions = "${atms.supermap_resolutions}",
				arcgis_resolutions = "${atms.arcgis_resolutions}",
				MapFactoryBaseUrl = "<%=basePath%>";
			var centerAndZoom,
				maxExtent,
				geoserverResolutions,
				supermapResolutions,
				arcgisResolutions;
			if(map_center_zoom) {
				centerAndZoom = strListToNumberList(map_center_zoom.split(","));
			}
			if(map_max_extent) {
				var temp = strListToNumberList(map_max_extent.split(","));
				maxExtent = {minX: temp[0], minY: temp[1], maxX: temp[2], maxY: temp[3]};
			}
			if(geoserver_resolutions) {
				geoserverResolutions = strListToNumberList(geoserver_resolutions.split(","));
			}
			if(supermap_resolutions) {
				//supermapResolutions = strListToNumberList(supermap_resolutions.split(","));
			}
			if(arcgis_resolutions) {
				arcgisResolutions = strListToNumberList(arcgis_resolutions.split(","));
			}
		</script>
		<script src="compnents/MapFrame/MapFactory.js" type="text/javascript"></script>
		<script src="compnents/bootstrap/js/jquery-1.7.2.min.js" type="text/javascript"></script>
		<style type="text/css">
			#tempMask{
				position : absolute;
				left : 0;
				top : 0;
				width : 100%;
				height : 100%;
				background:white;
				z-index:999995;
				-moz-opacity: 0.8;  /* firefox*/
				opacity: .80;  /* chrome*/
				filter: alpha(opacity=80); /* ie*/
			}
			#mapWaiting{
				position : absolute;
				z-index:999998;
				width:220px;
				height:19px;
				margin:0 auto;
				margin-top:260px;
				margin-left:340px;
				background:url("images/picone/ajax-loaders/ajax-loader-7.gif");
			}
			#exportToolBars{
				height:100px;
			}
			.map-table {
				margin-top:5px;
			}
			.map-table tr td {
				border:1px solid #ddd;
			}
			#rightClose{
				position:absolute;
				top:20px;
				right:275px;
				z-index:10000;
			}
			#divContaienrContent div {
				float:left;
				margin:3px 5px;
			}
			#divContaienrContent div img {
				width:20px;
				height:20px;
			}
		</style>
	</head>
	<body style="overflow:hidden;">
		<div id="mapWaiting"></div>
		<div id="tempMask" style="display:block;"></div>
		<table width="100%" height="590" class="map-table">
		  <tr>
		    <td class="device_td_bg4" style="padding:0;">
		    	<input id="rightClose" type="button" class="btn" value="打印>>">
		    	<div class="mar_5">
				  <div id="map" class="claro" style="background:#fff;width:100%;">
				  <!--<div id="tempLegendDiv" style="width:260px;min-height:160px;background:#fff;border:1px solid #ddd;position:absolute;right:5px;bottom:5px;z-index:10000;"></div>
				  --></div>
		    	</div>
		    </td>
		    <td id="opt_td" width="260" valign="top" class="device_td_bg4" style="padding:0;">
		      <div class="mar_5" style="background:#fff;height:580px;">
		        <h4 class="h4_title" style="border-radius:0;"><b>▲</b>地图标注</h4>
		        <div class="mar_5 nextDiv">
		          <p class="mar_t5">
		                                 选择图层：
		            <select style="width:120px;padding:4px;" id="layerselecter">			
			        </select>
		          </p>
		          <p class="mar_t5">
		                               图层字段：
		            <select style="width:120px;padding:4px;" id="attselecter">			         
			        </select>
		          </p>
		           <input type="button" value="标注" class="btn" id="labelbutton"/>
		        </div>		        
		        <h4 class="h4_title" style="border-radius:0;"><b>▲</b>地图设置</h4>
		        <div class="mar_5 nextDiv">	        
		          <p class="mar_t5">地图大小：<input type="button" value="放大" class="btn" id="zoomin"/>
		           <input type="button" value="缩小" class="btn" id="zoomout"/>
		          </p>		          
		          <table class="mar_t10">
		            <tr>
		              <td style="border:0;">方向控制：</td>
		              <td style="border:0;">
			            <div id="mapNavigation-export">
						  <div id="panTop" title="向上"></div>
						  <div id="panLeft" title="向左"></div>
						  <div id="panBottom" title="向下"></div>
						  <div id="panRight" title="向右"></div>
					    </div>
		              </td>
		            </tr>
		          </table>		        
		        </div>		         		        		     
		      </div>
		    </td>
		  </tr>
		</table>

		<div style="clear:both;"></div>				
		<script type="text/javascript">
			var _engine ="BaiDu"
			if(0==parent.window.tempLoadTimes){
				parent.window.tempLoadTimes++;
				window.location.reload();
			}
			(function(){
				document.body.onload = function(){
					setTimeout(function(){
						var mapContaienr = MapFactory.Dom.getById("map");
						MapFactory.Dom.css(mapContaienr,"width","100%");
						MapFactory.Dom.css(mapContaienr,"height","580");
						MapFactory.Init({
							engine : _engine,
							callback : function(){
								MapFactory.Require([
									"ItmsMap/MapConfig",
									"MapFactory/MapManager",
									"MapFactory/LayerManager",
									"MapFactory/GraphicManager",
									"MapFactory/InfoWindowManager",
									"ItmsMap/SymbolConfig*",
									 "MapFactory/Util/Dialog*"							
								],function(mapConfig,mapManager,LayerManager,GraphicManager,InfoWindow,SymbolConfig,Dialog){                                      
									var _mapConfig = mapConfig,
										_userLayer,
										_layers = _mapConfig.layers;
								    var _containerId = "divContainer",
		                                _containerHeadId = "divContainerHead",
		                                _containerContentId = "divContaienrContent";	
									var _dialog=null;	
									var _graphics={};	
									var _dom = MapFactory.Dom;
				     $(".h4_title").toggle(
					      function(){
						       $(this).children("b").html("▼");
						       $(this).next(".nextDiv").hide();
					      },
					       function(){
						      $(this).next(".nextDiv").show();
						      $(this).children("b").html("▲");
					     }
				      );				
				    $("#rightClose").toggle(
					     function(){
						      $("#opt_td").hide();
						      $(this).attr("value","<<展开").css("right","15px");
						      setTimeout(function(){						
						        window.print();						
						       },1000);								    	
						       _dialog.setPosition(null,null,10,10);
					},
					  function(){
					    $("divContainer").css("right","275px");
						$("#opt_td").show();
						$(this).attr("value","打印>>").css("right","275px");
					       _dialog.setPosition(null,null,275,10);
					}
				);
									/**
									 * 初始化地图
									 */
									var mapMGM = mapManager({
										src : "map",
										mapConfig : _mapConfig,
										loaded : function(){
										   //设置地图范围------------
											   //mapMGM.setExtent(currentExtent);
											   // mapMGM.disablePan();
											//将地图现有要素显示在新地图上--------
												$("#layerselecter").empty();
				                            	$("#layerselecter").append("<option value=''>请选择</option>");
				                                $("#attselecter").empty();				                                		                            	
				                            	$("#layerselecter").change(function(){
				                            	               $("#attselecter").empty();
                                                                  var _layerid=  $("#layerselecter").val();
                                                                  var attributes=  _getLayerAttributeById(_layerid);
                                                                  for(var key in attributes){
                                                                  $("#attselecter").append("<option value=\""+key+"\">"+key+"</option>");
                                                                   }                                                                 
                                                            });
                                                $("#labelbutton").click(function(){
                                                     var graphics=[];                                                    
                                                     var color="#FF0000";                                                
                                                     var _layerid=$("#layerselecter").val();
                                                       if(_graphics[_layerid]){
                                                           for(var i in _graphics[_layerid] ){
                                                                _graphics[_layerid][i].remove();                                                                                                                               
                                                           }
                                                         } 
                                                     var  features=currentFeatures[_layerid];
                                                     var  labelname=$("#attselecter").val();
                                                     for(var index in features){                                                    
                                                        var graphicmanager=GraphicManager({geo:features[index].geo,symbol:{textFontFamily:"黑体",textStyle:"normal",textWeight:"bold",textColor:color,text:features[index].attributes[labelname],size:10,xOffset:0,yOffset:15},attributes:features[index].attributes});
 			                                                graphicmanager.addToLayer(_layerid);
 			                                                graphics.push(graphicmanager);                                                     
                                                     }
                                                      _graphics[_layerid]=graphics;                                                                                               
                                                  });  
											for(var layerid in currentFeatures){
											    var layer=LayerManager(layerid);
											    var features=currentFeatures[layerid];
											    for(var index in features){
											       	GraphicManager(features[index]).addToLayer(layerid);
											    }	
											   var layername= _getLayerNameById(layerid);
											   if(layername==""){continue;}
											   $("#layerselecter").append("<option value=\""+layerid+"\">"+layername+"</option>");											    										
											}											
											//隐藏等待图标-----------
											_dom.hide(_dom.getById("tempMask"));
											_dom.hide(_dom.getById("mapWaiting"));
											// 解决打印时底图不显示问题
											if("OpenLayers" == _engine){
												$(".olLayerGrid").css({
													width : mapWidth,
													height : mapHeight
												});
											}
											//alert(legendimages+"");			
											//设置图例内容------------															         								           
										}
									});																		
									       $("#zoomin").click(function(){
									         mapMGM.zoomIn();
									       });
								           $("#zoomout").click(function(){
								              mapMGM.zoomOut();
								           });	
								           $("#panTop").click(function(){
									         mapMGM.panUp();
									       });
								           $("#panLeft").click(function(){
								              mapMGM.panLeft();
								           });
								             $("#panBottom").click(function(){
									         mapMGM.panDown();
									       });
								           $("#panRight").click(function(){
								              mapMGM.panRight();
								           });								           								          								           															
										function _initContainer(){
		                                    _dialog = Dialog({
			                                            title: "图例",
			                                            mutiDialog: true,
			                                            mutiDialogSeed: "mapLegend",
			                                            mask: false,
			                                            bottom:15,
			                                            right:275,
			                                            isMove:false,
			                                            content: "<div id='"+_containerId+"' style='width:320px;height:80px;'>" +						    
						                                          "<div id='"+_containerContentId+"' style='width:100%;height:100%;'></div>" +
					                                              "</div>",
			                                            buttonDisplay: {
				                                                  "confirmButton": false,
				                                                  "cancelButton": false
			                                                           }
		                                              }).show();
	                                                 }
									
									_initContainer();																		
									function createLegend(){										 	                             
			                              var  _containerContent = document.getElementById(_containerContentId),
			                                   _moduleContainer = document.createElement("div");
			                             var  _itemNums = legendimages.length;	
		                                 if(_itemNums > 8){
			                                    _moduleContainer.style.overflowY = "auto";
			                                    _moduleContainer.style.height = "80px";
		                                    }
		                              for(var i=0,len=_itemNums;i<len;i=i+1){
		                	                 var _item = legendimages[i];
			                                 if(!_item){
				                                 continue;
			                                        }
			                                if(_item.url)
			                                            {_moduleContainer.appendChild(_generateImgItem(_item));}
			                                     else if(_item.outLineColor || _item.backgroundColor){
				                                         _moduleContainer.appendChild(_generateColorItem(_item));
			                                        }else{
				                                           continue;
			                                             }
			                                      _containerContent.appendChild(_moduleContainer);
		                                 }
	
	                                          }																			
									     
									    
									     
	                            //生成图例图片-------
	                         function _generateImgItem(obj){
	                            	var _img = document.createElement("img"),
			                            _label = document.createElement("span"),
			                            _div = document.createElement("div");
			                            _div.style.margin="3px 5px";
			                            _div.style.width="130px";
	                           	        _img.src = obj.url;
		                                _label.innerHTML = obj.name;
		                                _div.appendChild(_img);
		                                _div.appendChild(_label);
		                             return _div;
	                             }

	                      //图例为颜色区分------------
	                    function _generateColorItem(obj){
		                      var _ico = document.createElement("div"),
			                      _label = document.createElement("span"),
			                      _div = document.createElement("div");
			                      _div.style.margin="3px 5px";
			                      _div.style.width="130px";
		                          _ico.style.background = obj.outLineColor;
		                          _ico.style.width ="20px";
		                          _ico.style.height="20px";
		                          _label.innerHTML = obj.name;
		                          _div.appendChild(_ico);
		                          _div.appendChild(_label);
		                         return _div;
	                         }	
	                         
	                         
	                    function _getLayerNameById(id){	  
	                          var name="";                  
	                          for(var attr in _layers){
	                           var layerObj=_layers[attr];
	                            if(layerObj.id==id){	                            
	                              name=layerObj.name;
	                              break;	                            	                            
	                            }
	                          
	                          }
	                           return name;
	                         }   
	                         	
	                   function _getLayerAttributeById(id){	  	                              						            
								           var features=currentFeatures[id];											 
										   return features[0].attributes;								   																						    																					
	                         }    		                         		                         		                         																	
								 createLegend();																																					
								})
							}
						});
					},1000);
				}
			})();
		</script>
	</body>
</html>
