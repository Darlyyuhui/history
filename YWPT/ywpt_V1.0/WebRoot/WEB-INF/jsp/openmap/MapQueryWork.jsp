
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<script type="text/javascript">
function clearAll(){
	 if(map.getLayer("searchLayer")){
			map.getLayer("searchLayer").removeAllFeatures();
		 }	
  }

function callback(geometry){
	//清除
	if(map.getLayer("searchLayer")){
		searchLayer = map.getLayer("searchLayer");
		searchLayer.removeAllFeatures();
	} else {
		searchLayer = new OpenLayers.Layer.Vector("searchLayer");
		searchLayer.id = "searchLayer";
		map.addLayer(searchLayer);
	}

	//if (!document.getElementById("MultipleQueryInput").value) {
	//	
	//}
	
  	$.ajax({
		type: "POST",
		url: "openmap/query/identify", 
		dataType: "json",
		data: "geometryText=" + geometry.toString(),
		success : function(queryResoultlist) {	
	  		 var _relation = {},
	  		 	_res = [],
	  		 	queryResoultlistAttr,
	  		 	queryResoultlistGraphics = [],
	  		 	popup,
	  		 	position;

	  		 for(var m = 0; m < queryResoultlist.length; m ++){
				queryResoultlistAttr = {"name": queryResoultlist[m].name};		  					
		   			queryResoultlistGraphics[m] =  new OpenLayers.Feature.Vector(OpenLayers.Geometry.fromWKT(queryResoultlist[m].geometry), queryResoultlistAttr);
		   			searchLayer.addFeatures(queryResoultlistGraphics[m]);		
		  			//右侧数据统计     		
					_res.push(new resItemO(queryResoultlist[m].name, (function(_index){
							return function(){					
			 					if(popup){
				 					map.removePopup(popup);	    	        	  
			 	    	        }

								position = queryResoultlistGraphics[_index].geometry.getBounds().getCenterLonLat();
			 					popup = new OpenLayers.Popup.FramedCloud("", position, null, "<div style='line-height:25px;'>名称:" + queryResoultlist[_index].name + "</div>", null, true);
				 	            popup.autoSize=true;
				 	            queryResoultlistGraphics.popup = popup;	           
				 	            map.addPopup(popup); 
				 	        }
				 	   })(m), queryResoultlist[m].name));     			
	           }	   
	  		   itmap.util.mapResultboxNew().init("mapResultC").addContent({content:_res,switchtab:true,relation:_relation}); 	     
	  	   }
  	   });
}
</script>
</head>
<body>
<div class="gis_div">
 <!--  <input id='identifyClickStyle' type="button" class="btn btn-info mar_r10" value="点选" onclick="addDrawControl('point')">
  <input id='identifyExtentStyle' type="button" class="btn btn-info" value="框选" onclick="addDrawControl('line')"> -->
 
  <input id='identifyClearM' type="button" class="btn mar_l10" value="框选" onclick="addDrawControl('polygon',callback)">
   <input id='MultipleQueryClearButton' type="button" class="btn mar_l10" onclick="clearAll()" value="清除">
</div>
</body>
</html>