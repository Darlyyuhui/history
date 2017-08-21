<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

     var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
	 var popup,deviceLayer,bzbp_point,dlwz_point,jtgz_polygon,lljcq_point;
    renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
    if(map.getLayer("deviceLayer")){
		 map.removeLayer(map.getLayer("deviceLayer"));
     }	
    var style = new OpenLayers.Style({
 	    graphicWidth: 32,
         graphicHeight: 32,
         externalGraphic: "images/map/device.png"
 	   });
    
   var layerListeners = {
 		    featureclick: function(feature) {
 	        feature=feature.feature;	 
 	        if(popup){
 	        	  map.removePopup(popup);	    	        	  
 	          }
             popup = new OpenLayers.Popup.FramedCloud("", 
              feature.geometry.getBounds().getCenterLonLat(),
              null,	                                   
              feature.attributes,null, true);
             popup.autoSize=true;
             feature.popup = popup;	           
             map.addPopup(popup);  
             },
 		    nofeatureclick: function(feature) {}
 		};
    
     //根据参数定义用于编辑的卡口图层	        
    deviceLayer = new OpenLayers.Layer.Vector("deviceLayer", {
  	    protocol: new OpenLayers.Protocol.WFS({
  	        url: "http://193.169.100.111:8080/geoserver/wfs",
  	        featureType: "kk_point",
  	        featureNS: "http://www.openplans.org/xiangxun",
  	        geometryName: "geom"	
  	    }),
  	  renderers: renderer,
  	 styleMap: new OpenLayers.StyleMap(style),
  	 eventListeners: layerListeners,
  	   strategies: [new OpenLayers.Strategy.BBOX()]
    	});  	
   deviceLayer.name="deviceLayer"; 
   deviceLayer.id="deviceLayer";



   //根据参数定义用于编辑的标示标牌图层	
       var style_bzbp_point = new OpenLayers.Style({
 	    graphicWidth: 32,
         graphicHeight: 32,
         externalGraphic: "images/map/bzbp.jpg"
 	   });           
       bzbp_point = new OpenLayers.Layer.Vector("bzbp_point", {
 	    protocol: new OpenLayers.Protocol.WFS({
 	        url: "http://193.169.100.111:8080/geoserver/wfs",
 	        featureType: "bzbp_point",
 	        featureNS: "http://www.openplans.org/xiangxun",
 	        geometryName: "geom"	
 	    }),
 	  renderers: renderer,
 	 styleMap: new OpenLayers.StyleMap(style_bzbp_point),
 	 eventListeners: layerListeners,
 	   strategies: [new OpenLayers.Strategy.BBOX()]
   	});  	
       bzbp_point.name="bzbp_point"; 
       bzbp_point.id="bzbp_point";


     //根据参数定义用于编辑的流量检测器图层	
       var style_lljcq_point = new OpenLayers.Style({
 	    graphicWidth: 32,
         graphicHeight: 32,
         externalGraphic: "images/flow.png"
 	   });           
       lljcq_point = new OpenLayers.Layer.Vector("lljcq_point", {
 	    protocol: new OpenLayers.Protocol.WFS({
 	        url: "http://193.169.100.111:8080/geoserver/wfs",
 	        featureType: "lljcq_point",
 	        featureNS: "http://www.openplans.org/xiangxun",
 	        geometryName: "geom"	
 	    }),
 	  renderers: renderer,
 	 styleMap: new OpenLayers.StyleMap(style_lljcq_point),
 	 eventListeners: layerListeners,
 	   strategies: [new OpenLayers.Strategy.BBOX()]
   	});  	
       lljcq_point.name="lljcq_point"; 
       lljcq_point.id="lljcq_point";

       
       //根据参数定义用于编辑的道路挖占图层	        
       dlwz_point = new OpenLayers.Layer.Vector("dlwz_point", {
 	    protocol: new OpenLayers.Protocol.WFS({
 	        url: "http://193.169.100.111:8080/geoserver/wfs",
 	        featureType: "bzbp_point",
 	        featureNS: "http://www.openplans.org/xiangxun",
 	        geometryName: "geom"	
 	    }),
 	  renderers: renderer,
 	 eventListeners: layerListeners,
 	   strategies: [new OpenLayers.Strategy.BBOX()]
   	});  	
       dlwz_point.name="dlwz_point"; 
       dlwz_point.id="dlwz_point";
       //根据参数定义用于编辑的交通管制图层	        
       jtgz_polygon = new OpenLayers.Layer.Vector("jtgz_polygon", {
 	    protocol: new OpenLayers.Protocol.WFS({
 	        url: "http://193.169.100.111:8080/geoserver/wfs",
 	        featureType: "jtgz_polygon",
 	        featureNS: "http://www.openplans.org/xiangxun",
 	        geometryName: "geom"	
 	    }),
 	  renderers: renderer,
 	 eventListeners: layerListeners,
 	   strategies: [new OpenLayers.Strategy.BBOX()]
   	});  	
       jtgz_polygon.name="jtgz_polygon"; 
       jtgz_polygon.id="jtgz_polygon";
       
  map.addLayers([deviceLayer,bzbp_point,dlwz_point,jtgz_polygon,lljcq_point]);
  
  
  function checkdeviceLayer(){
 	 if(document.getElementById("selectdeviceLayer").checked){
		    map.getLayer("deviceLayer").display(true);   	    	
         }
        else {
        	map.getLayer("deviceLayer").display(false);         
        }
  }
  function checkbzbp_pointLayer(){
	 	 if(document.getElementById("selectbzbp_pointLayer").checked){
			    map.getLayer("bzbp_point").display(true);   	    	
	         }
	        else {
	        	map.getLayer("bzbp_point").display(false);         
	        }
	  }
  function checkdlwz_pointLayer(){
	 	 if(document.getElementById("selectdlwz_pointLayer").checked){
			    map.getLayer("dlwz_point").display(true);   	    	
	         }
	        else {
	        	map.getLayer("dlwz_point").display(false);         
	        }
	  }

  function checklljcq_pointLayer(){
	 	 if(document.getElementById("selectlljcq_pointLayer").checked){
			    map.getLayer("lljcq_point").display(true);   	    	
	         }
	        else {
	        	map.getLayer("lljcq_point").display(false);         
	        }
	  }
</script>

</head>
<body >
<ul class="gis_ul2 mar_t10">
  <li><input id="selectdeviceLayer" checked="checked" type="checkbox" onclick="checkdeviceLayer()">选择卡口图层</li>
  <li><input id="selectbzbp_pointLayer" checked="checked" type="checkbox" onclick="checkbzbp_pointLayer()">标示标牌图层</li>
  <li><input id="selectdlwz_pointLayer" checked="checked" type="checkbox" onclick="checkdlwz_pointLayer()">道路挖占图层</li>
  <li><input id="selectjtgz_polygonLayer" checked="checked" type="checkbox" onclick="checkjtgz_polygonLayer()">交通管制图层</li>
  <li><input id="selectlljcq_pointLayer" checked="checked" type="checkbox" onclick="checklljcq_pointLayer()">流量检测器图层</li>
  <div class="clear"></div>
</ul>
</body>
</html>