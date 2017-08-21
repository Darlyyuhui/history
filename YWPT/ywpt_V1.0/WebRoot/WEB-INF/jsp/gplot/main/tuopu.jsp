<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <base href="<%=basePath%>">  
    <title>My JSP 'index.jsp' starting page</title>
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
			//创建舞台对象
			var stage = new JTopo.Stage(canvas);
			//创建场景
			var scene = new JTopo.Scene(stage);
			//设置页面背景
			//scene.background = "./images/gplot/heise.jpg";
			scene.background = "./images/gplot/qianlanse.jpg";
			
			
			function node(x, y, img, name){
				var node = new JTopo.Node(name);
				node.setImage("./images/gplot/" + img, true);
				node.setLocation(x, y);
				scene.add(node);
				return node;
			}
			
			function linkNode(nodeA,nodeZ,f){
				var link;
				if(f){
					link = new JTopo.FoldLink(nodeA,nodeZ);
				}else{
					link = new JTopo.Link(nodeA,nodeZ);
				}
				
				//link.direction = "vertical";
				link.strokeColor = JTopo.util.randomColor(); // 线条颜色随机
				scene.add(link);
				return link;
			}
			
			
			//例子
			function hostLink(){
				new JTopo.FlexionalLink(nodeA,nodeZ);
			}
			
			
			var c = node(450, 200, "chulizhongxin.bmp", "数据处理");
			
			var s1 = node(350, 100, "shexiangji.bmp", "全景摄像机");
			var s2 = node(550, 100, "shexiangji.bmp", "高清摄像机");
			
			linkNode(s1,c);
			linkNode(s2,c);
			
			
			var f1 = node(50, 350, "fwq.bmp", "通信服务器");
			var f2 = node(150, 350, "fwq.bmp", "应用服务器");
			var f3 = node(250, 350, "fwq.bmp", "流媒体服务器");
			var f4 = node(350, 350, "web.bmp", "web服务器");
			var f5 = node(550, 350, "fwq.bmp", "数据库服务器");
			var f6 = node(650, 350, "fwq.bmp", "数据库服务器");		
			var f7 = node(750, 350, "fwq.bmp", "无线警务web服务器");
			
			linkNode(f1,c);
			linkNode(f2,c);
			linkNode(f3,c);
			linkNode(f4,c);
			linkNode(f5,c);
			linkNode(f6,c);
			linkNode(f7,c);
			
			var g = node(550, 550, "gongan.bmp", "公安网");
			var sjk1 = node(680, 650, "sjk.bmp", "违法管理系统数据库");
			var sjk2 = node(680, 500, "sjk.bmp", "车驾管理系统数据库");
			
			linkNode(c,g);
			linkNode(g,sjk1);
			linkNode(g,sjk2);
			
			var wx = node(850, 550, "zhuanwang.jpg", "无线专网");
			var wx1 = node(800, 650, "shouji.bmp", "无线警务处理终端");
			var wx2 = node(900, 650, "kehuduan.bmp", "无线警务处理终端");
			
			linkNode(f7,wx);
			linkNode(wx,wx1);
			linkNode(wx,wx2);
			
			
			var pc = node(350, 550, "zhongduan.bmp", "终端");
			var pc1 = node(250, 650, "kehuduan.bmp", "查询处理终端");
			var pc2 = node(400, 650, "kehuduan.bmp", "查询处理终端");
			var pc3 = node(100, 650, "kehuduan.bmp", "查询处理终端");
			
			linkNode(c,pc);
			linkNode(pc,pc1);
			linkNode(pc,pc2);
			linkNode(pc,pc3);	
		});
  </script>
    
    
  </body>
</html>
