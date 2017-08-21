<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <base href="<%=basePath%>">  
    <title>设备拓扑图</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="http://www.jtopo.com/css/base.css">
	<link rel="stylesheet" type="text/css" href="http://www.jtopo.com/css/jquery.snippet.min.css">
	
  </head>
  
  <body>
    项目设备拓扑图 <br>
    
    <canvas id="canvas" width="999" height="999" id="canvas" style="background-color: rgb(238, 238, 238); border: 1px solid rgb(68, 68, 68); cursor: default;"></canvas>
        
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jtopo-0.4.8-min.js"></script>
	<script type="text/javascript" src="http://www.jtopo.com/demo/js/toolbar.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var canvas = document.getElementById("canvas");
			var stage = new JTopo.Stage(canvas);
			var scene = new JTopo.Scene();
			stage.add(scene);
			scene.background = "./images/gplot/qianlanse.jpg";
			
			var cloudNode = new JTopo.Node("root");
			cloudNode.setSize(50,23);
			cloudNode.setLocation(332,290);
			cloudNode.layout = {type:"circle",radius:150};
			
			scene.add(cloudNode);
			
			for(var i=1; i<4; i++){
				var node = new JTopo.CircleNode("vvvvvv" + i + "层" );
				node.fillStyle = "200,255,0";
				node.radius = 20;
				node.setLocation(scene.width * Math.random(),scene.height * Math.random());
				node.layout = {type:"circle",radius: 70};
				
				scene.add(node);
				var link = new JTopo.Link(cloudNode,node);
				scene.add(link);
				
				for(var j=0; j<6; j++){
					var vmNode = new JTopo.CircleNode("xxxxxx" + j + "层");
					vmNode.radius = 30;
					vmNode.fillStyle = "250,255,0";
					vmNode.setLocation(scene.width * Math.random(), scene.height * Math.random());
					scene.add(vmNode);
					scene.add(new JTopo.Link(node,vmNode));
				}
			}
			
			JTopo.layout.layoutNode(scene,cloudNode,true);
			scene.addEventListener("mouseup",function(e){
				if(e.target && e.target.layout){
					JTopo.layout.layoutNode(scene,e.target,true);
				}
			});
		
		});
		
	</script>
    
    
  </body>
</html>