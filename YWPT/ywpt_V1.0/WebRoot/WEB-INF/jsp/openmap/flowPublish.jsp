<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'flowPublish.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">



  <script type="text/javascript">
              var flowPublishtemp =OLflowPublish();         
			   flowPublishtemp.searchRoadStatus();		
			   flowPublishtemp.showroadStatus();

				   function checkflowLayer(){
		        	 if(document.getElementById("selectflowLayer").checked){
		       		    map.getLayer("RoadLever1GraphicsLayer").display(true);   
		       	    	map.getLayer("RoadLever2GraphicsLayer").display(true);
		       		    map.getLayer("RoadLever3GraphicsLayer").display(true);         	
		                }
		               else {
		               	map.getLayer("RoadLever1GraphicsLayer").display(false); 
		            	map.getLayer("RoadLever2GraphicsLayer").display(false); 
		            	map.getLayer("RoadLever3GraphicsLayer").display(false);    	        
		               }
		         }

				   var xianpt= new OpenLayers.Geometry.Point(108.942,34.261);	
					
					 
				   function checkLayerLever(element){
			        	 var value=element.value;
			        if(value=="level1"){	
			        	document.getElementById("level2").checked=false;
			        	document.getElementById("level3").checked=false;
			 			
			        	  map.getLayer("RoadLever1GraphicsLayer").display(true);   
			     	    	map.getLayer("RoadLever2GraphicsLayer").display(false);
			     		    map.getLayer("RoadLever3GraphicsLayer").display(false);
			     		   map.zoomToScale(150000,xianpt);
							
			 			   		
			 		}else if(value=="level2"){
			 			document.getElementById("level1").checked=false;
			        	document.getElementById("level3").checked=false;
			        	 map.getLayer("RoadLever1GraphicsLayer").display(true);   
			    	    	map.getLayer("RoadLever2GraphicsLayer").display(true);
			    		    map.getLayer("RoadLever3GraphicsLayer").display(false);
			    		    map.zoomToScale(75000,xianpt);
			 			  	
			 		}else if(value=="level3"){
			 			document.getElementById("level1").checked=false;
			        	document.getElementById("level2").checked=false;
			        	  map.getLayer("RoadLever1GraphicsLayer").display(true);   
			     	    	map.getLayer("RoadLever2GraphicsLayer").display(true);
			     		    map.getLayer("RoadLever3GraphicsLayer").display(true);         	
			     		   map.zoomToScale(35000,xianpt);
			 			   
			        	
			            }
							
					}		 
					        
	    
  </script>
<style type="text/css"> 

#tab {
	border:1px solid #ccc;
	border-radius:0 3px 3px 0;
	-moz-border-radius:0 3px 3px 0;
	-webkit-border-radius:0 3px 3px 0;
	margin:5px 2px 5px 0;
}
.Tableft {
	float:left;
	width:60px;
}
.Tableft h3 {
	 width:60px;
	 height:20px;
	 font-family:"微软雅黑";
	 line-height:20px;
	 font-size:12px;
	 cursor:pointer;
	 font-weight:bold;
	 text-align:center;
	 color:#00007F;
	 background:#f8f8f8;
	 border:1px solid #ccc;
	 border-left:0;
	 margin:1px 0;
}
.result_tab {
	width:150px;

	padding:2px;
	float:right;
}
.result_tab div {
	display:none;
	font-size:12px;
}
.result_tab .block {
	display:block;
}

.Tableft .up {
	background:#eee;
	border:1px solid #aaa;
	border-left:0;
}

.result_tab table tr td {
	border:1px solid #ccc;
	padding:1px;
	font-size:12px;
	text-align:center;
	word-break:break-all;
	word-wrap:break-word;
	height:20px;
	line-height:20px;
}
.result_tab table tr th {
	border:1px solid #ccc;
	padding:0 1px;
	font-size:10px;
	font-family:Microsoft YaHei;
	text-align:center;
	background:#f8f8f8;
	height:20px;
	line-height:20px;
}
.result_tab i {
	height:20px;
	line-height:20px;
	margin:1px 0;
	display:block;
}
.clear {
	clear:both;
}
</style>  
  
</head>
<body >
<ul class="gis_ul2 mar_t10">
  <li><input id="selectflowLayer" checked="checked" type="checkbox" onclick="checkflowLayer()">选择路况图层</li>
</ul>
<div id="tab" >
  <div class="Tableft">
  	<h3 id="dlwz">道路挖占</h3>
	<h3 id="jtgz">交通管制</h3> 
  </div>
  <div class="result_tab" id="Result">
	
  </div>
  <div class="clear"></div>
</div>

  <div>
  <ul>
     <li>
      <input id="level1"  value="level1" checked="checked" type="checkbox" onclick="checkLayerLever(this)">宏观路网
     
   
  </li>
  <li>
   <input id="level2"  value="level2"  type="checkbox" onclick="checkLayerLever(this)">中观路网
  </li>
   <li>
      <input id="level3"   value="level3"  type="checkbox" onclick="checkLayerLever(this)">微观路网
  </li>
   </ul>
  </div>
</body>
</html>
