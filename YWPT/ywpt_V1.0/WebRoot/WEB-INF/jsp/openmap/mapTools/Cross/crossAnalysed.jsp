<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.input {
	border-left:1px solid #1f6377;
	border-top:1px solid #1f6377;
	border-right:1px solid #b3bfca;
	border-bottom:1px solid #b3bfca;
	height:18px;
	padding:3px;
}
</style>
<script type="text/javascript">
<!--
	if(!mapCrossObj)mapCrossObj = mapCross();
	mapCrossObj.showAll();// 显示所有卡口设备
	
	// 卡口分析图层
	var crossAnalyLayer = map.getLayer("crossAnalyLayer");
	// 卡口关联查询图层
	var connectedLayer = map.getLayer("connectedLayer");
	var firstCross;// 起点
	var firstline;
	var secondline;
	var secondCross;// 终点
	var routeResult;// 网络分析结果
	var arrowFeatures;// 网络分析结果箭头数组
	if(!crossAnalyLayer) {
		crossAnalyLayer = new OpenLayers.Layer.Vector("crossAnalyLayer");
		crossAnalyLayer.id="crossAnalyLayer";
		map.addLayer(crossAnalyLayer);
	}
	if(!connectedLayer) {
		connectedLayer = new OpenLayers.Layer.Vector("connectedLayer");
		connectedLayer.id="connectedLayer";
		map.addLayer(connectedLayer);
	}
	// 标注点样式
	var pointStyle = new OpenLayers.Symbolizer.Point({
		graphicWidth: 34,	
		graphicHeight: 40,	
		graphicXOffset: -10,
		graphicYOffset: -40,
		externalGraphic: "images/map/position.png"
	});
	// 网络分析线的默认样式
	var polylineStyle = new OpenLayers.Symbolizer.Line({
		strokeWidth: 3,
        strokeOpacity: 1,
        strokeColor: "#6666aa"
	});
	var positionSymbol = new OpenLayers.Symbolizer.Point({externalGraphic:"images/map/position.png",graphicWidth:34,graphicHeight:40,graphicXOffset:-10,graphicYOffset:-40});
	var endSymbol = new OpenLayers.Symbolizer.Point({externalGraphic:"images/end.png",graphicWidth:24,graphicHeight:24});
	var startSymbol = new OpenLayers.Symbolizer.Point({externalGraphic:"images/start.png",graphicWidth:24,graphicHeight:24});
	var barriersSymbol = new OpenLayers.Symbolizer.Point({externalGraphic:"images/picone/mainmenu/bukongxitong.png",graphicWidth:24,graphicHeight:24});
	
	var endSymbolXYOffset = new OpenLayers.Symbolizer.Point({externalGraphic:"images/end.png",graphicWidth:24,graphicHeight:24,graphicXOffset:-12,graphicYOffset:-24});
	var startSymbolXYOffset = new OpenLayers.Symbolizer.Point({externalGraphic:"images/start.png",graphicWidth:24,graphicHeight:24,graphicXOffset:-12,graphicYOffset:-24});
	
	var out_options = {'internalProjection' : map.baseLayer.projection,'externalProjection' : map.baseLayer.projection};
	var	writer = new OpenLayers.Format.WKT(out_options);
	var reader = new OpenLayers.Format.WKT();
	
	// 选择卡口设备--标注起始点
	function selCrossPoint(isFirst) {
		addDrawControl('polygon',function callback(geometry){
			var polygon = geometry;
			var deviceArr = map.getLayer("cross").features;
			for(var i=0,len=deviceArr.length; i<len; i++) {
				var point=new OpenLayers.Geometry.Point(deviceArr[i].geometry.x,deviceArr[i].geometry.y);
				if(polygon.containsPoint(point)) {
					var graphic = new OpenLayers.Feature.Vector(point);
					if(isFirst) {
						graphic.style = startSymbolXYOffset;
						if(firstCross)crossAnalyLayer.removeFeatures(firstCross);
						firstCross = graphic;
					}
					else {
						graphic.style = endSymbolXYOffset;
						if(secondCross)crossAnalyLayer.removeFeatures(secondCross);
						secondCross = graphic;
					}
					crossAnalyLayer.addFeatures(graphic);
				}
			}
		});
	}
	// 标注起始点
	function addCrossPoint(isFirst) {
		addDrawControl('point',function callback(geometry){
			var point = geometry;
			var feature = new OpenLayers.Feature.Vector(point,null,startSymbol);
			OpenLayers.Request.GET({
				url :"openmap/snappoint/project?geometryText="+ writer.write(feature),
				type:"json",
				"success" : function(ee) {
				debugger;
					var jsonobj = jQuery.parseJSON(ee.responseText);
					var graphic = writer.read(jsonobj.geometryText);					   
					if(isFirst) {
						graphic.style = startSymbolXYOffset;
						if(firstCross)crossAnalyLayer.removeFeatures(firstCross);
						firstCross = graphic;
						firstline= writer.read(jsonobj.lineText);
					}
					else {
						graphic.style = endSymbolXYOffset;
						if(secondCross)crossAnalyLayer.removeFeatures(secondCross);
						secondCross = graphic;
						secondline= writer.read(jsonobj.lineText);
					}
					crossAnalyLayer.addFeatures([graphic]);
				}
			});
		});
	}
	// 根据选择的道路查询关联的卡口
	function selCrossDevice() {
		addDrawControl('polygon',function callback(geometry){
			var polygon = geometry;
			var deviceList = map.getLayer("cross").features;
			//查询道路
			$.ajax({
				type : "POST",
				url:"openmap/multipleQuery/identify", 
				dataType : "json",
				data:"geometryText="+polygon.toString(),
				success : function(identifyResoultlist) {
					connectedLayer.removeAllFeatures();
					for(var i=0;i<identifyResoultlist.length;i++){
						if(identifyResoultlist[i].type=="road"){
						var road = new OpenLayers.Geometry.fromWKT(identifyResoultlist[i].geometryText);
						for(var j=0;j<deviceList.length;j++){
							//判断卡口和道路的关系
							if(deviceList[j].geometry.intersects(road)&&deviceList[j].geometry.intersects(polygon)){
									var roadGraphic = new OpenLayers.Feature.Vector(road,null,polylineStyle);
									var point = new OpenLayers.Geometry.Point(deviceList[j].geometry.x,deviceList[j].geometry.y);
									var feature = new OpenLayers.Feature.Vector(point,null,positionSymbol);
									connectedLayer.addFeatures([roadGraphic,feature]);
								}
							}
						}
					}
				}
			});
		});
	}
	// 根据选择的卡口查询关联的道路
	function selRoadByCrossDevice() {
		addDrawControl('polygon',function callback(geometry){
			var polygon = geometry;
			var deviceList = map.getLayer("cross").features;
			//查询道路
			$.ajax({
				type : "POST",
				url:"openmap/multipleQuery/identify", 
				dataType : "json",
				data:"geometryText="+polygon.toString(),
				success : function(identifyResoultlist) {
					connectedLayer.removeAllFeatures();
					for(var i=0;i<identifyResoultlist.length;i++){
						if(identifyResoultlist[i].type=="road"){
						var road = new OpenLayers.Geometry.fromWKT(identifyResoultlist[i].geometryText);
						for(var j=0;j<deviceList.length;j++){
							//判断卡口和道路的关系
							if(deviceList[j].geometry.intersects(road)){
									var roadGraphic = new OpenLayers.Feature.Vector(road,null,polylineStyle);
									var point = new OpenLayers.Geometry.Point(deviceList[j].geometry.x,deviceList[j].geometry.y);
									var feature = new OpenLayers.Feature.Vector(point,null,positionSymbol);
									connectedLayer.addFeatures([roadGraphic,feature]);
								}
							}
						}
					}
				}
			});
		});
	}
	
	function crossRoutSearch() {
		$("#msg").html("");
		if(firstCross && secondCross) {
			routeSearch([firstCross,secondCross]);
		}
		else {
			$("#msg").html("请选择卡口！");
		}
	}
	
	var render = itmap.openlayer.renderer();
	// 网络分析features为feature的数组
	function routeSearch(features) {
		var url = baseServiceURL.geoserverNSService.url;
		  
		for(var i=0,len=features.length; i<len; i++) {
			features[i] = writer.write(features[i]);
		}
		var stops=features.join("|");
		$.ajax({
		  type: "POST",
		  url: "openmap/naServer",
		  cache: false,
		   data: {reqUrl:baseServiceURL.geoserverNSService.url,stopPoint:stops,methodname:"getClosedRoute",barriers:"",roadnet:"roadnet"},	
		   success: function(strs){
		  	if(routeResult)crossAnalyLayer.removeFeatures(routeResult);
		  	if(arrowFeatures)crossAnalyLayer.removeFeatures(arrowFeatures);
		  	var route = new OpenLayers.Feature.Vector(OpenLayers.Geometry.fromWKT(strs));	
		  	    route.style = polylineStyle;
		  	  debugger;
		  	mergeLine(route);
		  	crossAnalyLayer.addFeatures([route]);
		  	routeResult = route;		  	
		  	var geometry=route.geometry.components;		  	
			if(!map.getExtent().containsBounds(route.geometry.getBounds())) {
				map.zoomToExtent(route.geometry.getBounds());
			}
			var remotePointList=[];
			
			var points = itmap.openlayer.util.orderRouteResult(route.geometry.components);
			for(var j in points) {
				remotePointList.push([points[j].x, points[j].y]);
			}
			if(remotePointList.length > 1) {
				// 添加箭头
				render.addRouteResultArrow(remotePointList, crossAnalyLayer, function(arrowResult){
					arrowFeatures = arrowResult;
				});
			}
		  },
		  error: function(e) {
		  }
		});
	}
	function caClean() {
		hideInfoWindow();
		
		crossAnalyLayer.removeAllFeatures();
		connectedLayer.removeAllFeatures();
	}
	// 清除地图信息框
	function hideInfoWindow(popup) {
		if(popup) {
			map.removePopup(popup);
		}
		else {
			var pops = map.popups;
			for(var i=0,len=pops.length; i<len; i++) {
				map.removePopup(pops[i]);
			}
		}
	}
	function mergeLine(route){	
	              var routestrs=route.geometry.components;	  	 		
		          var startline=routestrs[0];	 		                 
	              var endline=routestrs[routestrs.length-1];	 	              		   
		          var startpoint=firstCross.geometry;
		          var endpoint=secondCross.geometry;
		          var sflag=startline.getBounds().containsLonLat(new OpenLayers.LonLat(startpoint.x,startpoint.y));
		          var eflag=endline.getBounds().containsLonLat(new OpenLayers.LonLat(endpoint.x,endpoint.y));
		          var verices=itmap.openlayer.util.orderRouteResult(routestrs);
		    if(sflag && eflag){	    
		           route.geometry.components[0]=splitLineStrFromPoint(startline,startpoint,true);
		           route.geometry.components[routestrs.length-1]=splitLineStrFromPoint(endline,endpoint,false);		       
		         }
	             
	       if(sflag && !eflag){
		          route.geometry.components[0]=splitLineStrFromPoint(startline,startpoint,true);		    
		          route.geometry.components.push(getnewLinestr(verices[verices.length-1],false));      
		       }
	
	      if(!sflag && eflag){
	             route.geometry.components.unshift(getnewLinestr(verices[0],true)); 
		         route.geometry.components[routestrs.length-1]=splitLineStrFromPoint(endline,endpoint,false);       
		       }	
	     if(!sflag && !eflag){
	             route.geometry.components.unshift(getnewLinestr(verices[0],true)); 
	       	     route.geometry.components.push(getnewLinestr(verices[verices.length-1],false));   
		       }
	
	
	}
	function splitLineStrFromPoint(linestr,point,flag){
        var	points=itmap.openlayer.util.orderRouteResult([linestr]);
        var arr=[];
	    for(var j=0;j<points.length-1;j++){
	     var prep=points[j];
	     var postp=points[j+1];
	     if((prep.x<=point.x && postp.x>=point.x)||(prep.x>=point.x && postp.x<=point.x)){
	        if(flag)
	        {       arr=points.slice(j+1,points.length);
	                arr.unshift(point);
	        }
	        else{	        
	                arr=points.slice(0,j+1);
	                arr.push(point); 
	        }	       
	        break;
	     }	    
	    }
	  var endlinestr=new OpenLayers.Geometry.LineString(arr);
	return endlinestr;
	}
	
	
	function getnewLinestr(point,flag){	 
	     var arr=[];	
	     var firstallpoints=itmap.openlayer.util.orderRouteResult(firstline.geometry.components);                   
	     var secondallpoints=itmap.openlayer.util.orderRouteResult(secondline.geometry.components);	
	     var startpoint=firstCross.geometry;
		 var endpoint=secondCross.geometry;
		 var startindex=-1;
		 var endindex=-1;
	     if(flag){	
	         if(startpoint.x<point.x){	      
	             firstallpoints=firstallpoints.sort(comparebyup);	      
	            }  
	          if(startpoint.x>point.x){	      
	             firstallpoints=firstallpoints.sort(comparebydown);	      
	            }     
	       for(var j=0;j<firstallpoints.length-1;j++){	       
	         var prep=firstallpoints[j];
	         var postp=firstallpoints[j+1];
	          if((prep.x<=startpoint.x && postp.x>=startpoint.x)|| (prep.x>=startpoint.x && postp.x<=startpoint.x)){	       
	              startindex=j;
	           } 
	          if((prep.x<=point.x && postp.x>=point.x)||(prep.x>=point.x && postp.x<=point.x)){	       
	              endindex=j;
	           }	           	               
	        }	    	  	          
	           arr=firstallpoints.slice(startindex+1,endindex+1);
	           arr.push(point);
	           arr.unshift(startpoint);	       
	     } else{		     
	         if(endpoint.x>point.x){	      
	             secondallpoints=secondallpoints.sort(comparebyup);      
	            }  
	          if(endpoint.x<point.x){	      
	             secondallpoints=secondallpoints.sort(comparebydown);
	      
	            }	          
	         for(var j=0;j<secondallpoints.length-1;j++){	       
	           var prep=secondallpoints[j];
	           var postp=secondallpoints[j+1];
	          if((prep.x<=point.x && postp.x>=point.x)||(prep.x>=point.x && postp.x<=point.x)){	       
	              startindex=j;
	           } 
	          if((prep.x<=endpoint.x && postp.x>=endpoint.x)||(prep.x>=endpoint.x && postp.x<=endpoint.x)){	       
	              endindex=j;
	           }	               
	          }	        
	          arr=secondallpoints.slice(startindex+1,endindex+1);
	          arr.push(endpoint);
	          arr.unshift(point);		 	          	     
	     }		     
        var endlinestr=new OpenLayers.Geometry.LineString(arr);
	return endlinestr;
	}
	
function comparebyup(p1,p2){
  
    if(p1.x<p2.x){
     return -1;} 
      else if(p1.x>p2.x){  
     return 1;
   } else{
    return 0;
   
   }

}
function comparebydown(p1,p2){
  
      if(p1.x<p2.x){
     return 1;} 
   else if(p1.x>p2.x){  
     return -1;
   } else{
    return 0;
   
   }

}	
//-->
</script>
<ul id="caTab" class='zTabTool'>
	<li>关联分析</li>
	<li>行程分析</li>
</ul>
<div id="caTabContent" class='zTabToolContent'>
<div>
  <p style="margin:0px;">
  	根据道路选择卡口设备：
    <img src="images/map/i_draw_rect.png" alt="选择" onclick="selCrossDevice()">
  </p>
  <p style="margin:0px;">
  	根据卡口设备选择道路：
    <img src="images/map/i_draw_rect.png" alt="选择" onclick="selRoadByCrossDevice()">
  </p>
  <div class="btn_line mar_t10" style="padding: 5px 10px 5px 26px">
  	<input type="button" class="btn btn-info mar_l10" value="清除" onclick="caClean()">
  </div>
  </div>
  <div>
  <p>
  	起点：
  	<img src="images/map/i_draw_rect.png" alt="选择" onclick="selCrossPoint(1)">
  	<img src="images/map/i_pin2.png" alt="标注" onclick="addCrossPoint(1)">
  </p>
  <p>
  	终点：
  	<img src="images/map/i_draw_rect.png" alt="选择" onclick="selCrossPoint(0)">
  	<img src="images/map/i_pin2.png" alt="标注" onclick="addCrossPoint(0)">
  </p>
  <div class="btn_line mar_t10" style="padding: 5px 10px 5px 26px">
  	<input type="button" class="btn btn-info mar_l10" value="分析" onclick="crossRoutSearch()">
  	<input type="button" class="btn btn-info mar_l10" value="清除" onclick="caClean()">
  </div>
  <div id="msg" style="color: red;"></div>
</div>
</div>

<script type="text/javascript">
	itmap.util.MapTabTool().init(
		{src:"caTab",style:{
				active : "selectelement",
				deactive : ""
			}},
		{src:"caTabContent"},
		1
	);
</script>