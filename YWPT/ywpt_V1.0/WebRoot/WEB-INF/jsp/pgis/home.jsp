<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'home.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta http-equiv="Content-Type" content="text/html; charset=GB2312" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="css/pgis/print.css" type="text/css"/>
<link rel="stylesheet" href="css/pgis/style.css" type="text/css"/>
<script type="text/javascript" src="compnents/pgis/EzMapAPI.js"></script>
<style type="text/css">
		v\:* {
		        BEHAVIOR: url(#default#VML)
		      }
		 .main{
		   
		   background-color:#CCCCCC;
		   
		 }
		 .toolbar{
		   width:100%;
		   height:30px;
		   background:url(images/pgis/images/menubj.jpg) repeat-x;
		 }
		.map{
		   background-color:#D7E0EA;
		   float:left;
		   
		}
		.mainright{
		   background-color:#A5C7FE;
		   float:right;
		   
		} 
		
		 .icon  {
		  
		  font-size:12px;
		  float:left;
		  margin:2px 6px;
		  
		}
		.icon a img {
		  
		  margin-top:5px;
		  margin:1px 4px;
		  border:none;
		  
		}
		.icon a {
		  color:#ffffff;
		  text-decoration:none;
		  
		  }
		</style>
		
<title>PGIS页面</title>
</head>
  
<body   onLoad="onLoad()" topmargin="0" leftmargin="0">

<div id="zhuyao" class="main">
  <div class="toolbar">
    <div class="icon" ><a href="#" onclick="_MapApp.zoomIn();" ><img   src="images/pgis/icon/zoomout.gif" />放大</a></div>
    <div class="icon" ><a href="#" onclick="_MapApp.zoomOut();" ><img  src="images/pgis/icon/zoomin.gif" />缩小</a></div>
	<div class="icon" ><a href="#" onclick="_MapApp.changeDragMode('pan');" ><img  src="images/pgis/icon/drag.gif" />移动</a></div>
	<div class="icon" ><a href="#" onclick="_MapApp.fullExtent();" ><img  src="images/pgis/icon/fullextent.gif" />全图</a></div>
	<div class="icon" ><a href="#"  onclick="_MapApp.changeDragMode('measure')"><img  src="images/pgis/icon/measure.gif" />测距离</a></div>
	<div class="icon" ><a href="#" onclick="_MapApp.measureArea(callback);"><img  src="images/pgis/icon/area.gif" />测面积</a></div>
	<div class="icon" ><a href="#" onclick="addIcon();"><img  src="images/pgis/icon/drawpoint.gif" />绘制点</a></div>
	<div class="icon" ><a href="#" onclick="_MapApp.changeDragMode('drawPolyline',null,null,callback);"><img  src="images/pgis/icon/drawpolyline.gif" />绘制线</a></div>
	<div class="icon" ><a href="#" onclick="_MapApp.changeDragMode('drawCircle',null,null,callback);"><img  src="images/pgis/icon/drawcircle.gif" />绘制圆</a></div>
	<div class="icon" ><a href="#" onclick="_MapApp.changeDragMode('drawPolygon',null,null,callback);"><img  src="images/pgis/icon/drawpolygon.gif" />绘制多边形</a></div>
	<div class="icon" ><a href="#" onclick="_MapApp.clearOverlays();"><img  src="images/pgis/icon/clear.gif" />清除</a></div>
	<div class="icon" ><a href="#" onclick="_MapApp.showOverView();" ><img  src="images/pgis/icon/voerview.gif" />鹰眼</a></div>
	<div class="icon" ><a href="#" onclick="_MapApp.print('css/print.css');" ><img  src="images/pgis/icon/print.gif" />打印</a></div>
  </div>
  <div class="mainview">
    <div id="mymap" class="map"></div>
    
	<div class="mainright" id="gongju">
	  
	</div>
  </div>
</div>
<script type="text/javascript">
   document.getElementById("zhuyao").style.height=screen.height-180;
   document.getElementById("zhuyao").style.width=screen.width-4;
   document.getElementById("mymap").style.height=screen.height-210;
   document.getElementById("mymap").style.width=screen.width-4-200;
   document.getElementById("gongju").style.height=screen.height-210;
   document.getElementById("gongju").style.width=195;
     //定义地图对象
	  var _MapApp;	
	   function onLoad(){
        // 构造地图类
        _MapApp = new EzMap(document.getElementById("mymap"));
        // 显示地图左侧比例尺控制控件
        _MapApp.showMapControl();
        // 设置地图对中中心
        _MapApp.centerAndZoom(new Point(111.309, 30.733), 8);
       
        // 添加鹰眼
        var uOverview=new OverView();// 构造一个鹰眼对象
        uOverview.width=200;// 设置鹰眼视窗的宽度
        uOverview.height=200;// 设置鹰眼视窗的高度
        uOverview.minLevel=7;// 设置鹰眼视窗中最小显示地图级别
        uOverview.maxLevel=20;// 设置鹰眼视窗中最大显示地图级别
		_MapApp.getCurrentMapScale();
        _MapApp.addOverView(uOverview);// 添加鹰眼对象
		          
      }
	  
	  //调用回调函数获取绘制的要素的坐标信息
	  function callback(str){
	//alert("调用回调函数！"+dataInputx.toString()+":"+dataInputy.toString());
	//alert("调用回调函数！"+dataInputx.value);
	alert("坐标信息:"+str);
	//getMapApp().pan();
          }
      //添加点要素函数
	  var iIndex=0;
	  function addIcon(iPos){
	  //定义marker图标
	    var pIcon = new Icon();
	    pIcon.image="images/pgis/icon/policecar.gif";
	 
	    pIcon.topOffset=0;
	    pIcon.leftOffset=0;
		var strMsg="点击事件响应输出窗口";
	   if(typeof iPos =="undefined" || iPos==null)iPos=7;
	     var marker = new Marker(_MapApp.getCenterLatLng(),pIcon,new Title("陕A001"+iIndex,12,iPos,"宋体",null,null,"red","2"));
		 marker.addListener("click",function(){marker.openInfoWindowHtml(strMsg);});// 添加点击事件的响应
		  _MapApp.addOverlay(marker);
	    marker.enableEdit()
	    return marker;
	  }
	  //定义线要素
	  
	   var pLine = new Polyline("115.4033,39.96989,116.43162,39.90055","#ff0000", 3,1);// 构造一个多义线对象
	    
	  //客户端的点查询
	    function IdentifyByPoint(){
		
		   var _subFields="casetype:类型;caseadd:发案地点;caseconten:案件描述";
           var _table="dcxsaj2_pt";
           var _imgURL="images/pnt/A.gif";
           var pQuery=new QueryObject();
           pQuery.queryType=2;
           pQuery.tableName=_table;
         pQuery.dispField="casetype";
         pQuery.coords="116.39409,39.9209,116.4356,39.9458";
         pQuery.addSubFields(_subFields);
         pQuery.imgURL=_imgURL;
 
         _MapApp.query(pQuery,drawFeatureObject); // drawFeatureObject为回调函数	
		
		}
</script>
</body>
</html>
