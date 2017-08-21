<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sessionid = request.getSession().getId();
	request.setAttribute("sessionId", sessionid);
%>
<%
	String niandu = "2013";
	int pageCurrent = 1;
	int currentNumber = 1;
	String impPath = request.getParameter("imgpath");
	String window_skin = request.getParameter("window_skin");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="<%=basePath%>/js/cross/jms/jquery-1.7.2.min.js"></script>
<link id="TipsCss" rel="stylesheet" href="<%=basePath%>/css/tipswindown.css" type="text/css" media="all" />
<script type="text/javascript" src="<%=basePath%>/js/tipswindown/tipswindownFd.js"></script>
<script type="text/javascript">
	var skin='<%=window_skin%>';
	if(skin=="1"){
		$("#TipsCss").attr("href","<%=basePath%>/css/tipswindown/tipswindown.css");
	}else if(skin=="2"){
		$("#TipsCss").attr("href","<%=basePath%>/cssGreen/tipswindown.css");
	}else if(skin=="3"){
		$("#TipsCss").attr("href","<%=basePath%>/cssDarkBlue/tipswindown.css");
	}
</script>
<script>
//alert('<%=window_skin%>');
</script>
<style type="text/css">
.mainFrame {
	width:1020px;
	height:407px;
	padding:0;
}
.picLeft {
	float:left;
	width:508px;
	height:406px;
}
.picRight {
	float:right;
	width:509px;
	height:407px;
	background:#999999 url("../../pic/loading.gif") no-repeat center;
}
#magnifier {
	width:508px;
	height:407px;
	font-size:0;
	/*border:1px solid #000;*/
	margin:0;
	padding:0;
}
#img {
	width:508px;
	height:407px;
	border:0;
}
#Browser {
	border:1px solid #000;
	z-index:100;
	position:absolute;
	background:#fff;
}
#mag {
	border:1px solid #000;
	overflow:hidden;
	z-index:100;
}
</style>
<script type="text/javascript">
  // 如何得到鼠标的 坐标值。 
  </script>
<script type="text/javascript">
function getEventObject(W3CEvent) { //事件标准化函数
 return W3CEvent || window.event;
}
function getPointerPosition(e) { //兼容浏览器的鼠标x,y获得函数
 e = e || getEventObject(e);
 var x = e.pageX || (e.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
 var y = e.pageY || (e.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
 return { 'x':x,'y':y };
}
function setOpacity(elem,level) {//兼容浏览器设置透明值
 if(elem.filters) {
  elem.style.filter = 'alpha(opacity=' + level * 100 + ')';
 } else {
  elem.style.opacity = level;
 }
}
function css(elem,prop) { //css设置函数,可以方便设置css值,并且兼容设置透明值
 for(var i in prop) {
  if(i == 'opacity') {
   setOpacity(elem,prop[i]);
  } else {
   elem.style[i] = prop[i];
  }
 }
 return elem;
}
var magnifier = {
   m : null,
   init:function(magni){
   var m = this.m = magni || {
   cont : null, //装载原始图像的div
   img : null,  //放大的图像
   mag : null, //放大框
   scale : 1500 //比例值,设置的值越大放大越大,但是这里有个问题就是如果不可以整除时,会产生些很小的白边,目前不知道如何解决
   }
   
   css(m.img,{
   'position' : 'absolute',
   'width' : (m.cont.clientWidth * m.scale) + 'px',    //原始图像的宽*比例值
   'height' : (m.cont.clientHeight * m.scale) + 'px'    //原始图像的高*比例值
   })
   
   css(m.mag,{
   'display' : 'none',
   'width' : m.cont.clientWidth + (-1) + 'px', //m.cont为原始图像,与原始图像等宽
   'height' : m.cont.clientHeight + (-2) + 'px',
   'position' : 'absolute',
   'left' : m.cont.offsetLeft + m.cont.offsetWidth + 3 + 'px',//放大框的位置为原始图像的右方远 X(数值) px
   'top' : m.cont.offsetTop +'px'
   })
   
    var borderWid = m.cont.getElementsByTagName('div')[0].offsetWidth - m.cont.getElementsByTagName('div')[0].clientWidth;  //获取border的宽
    css(m.cont.getElementsByTagName('div')[0],{   //m.cont.getElementsByTagName('div')[0]为浏览框
   'display' : 'none',        //开始设置为不可见
   'width' : m.cont.clientWidth / m.scale - borderWid + 'px',   //原始图片的宽/比例值 - border的宽度
   'height' : m.cont.clientHeight / m.scale - borderWid + 'px', //原始图片的高/比例值 - border的宽度
   'opacity' : 0.5//设置透明度
   })
   
   m.img.src = m.cont.getElementsByTagName('img')[0].src;//让原始图像的src值给予放大图像
   m.cont.style.cursor = 'crosshair';
   m.cont.onclick = magnifier.start;    // 点击开始
   m.cont.onmouseover = magnifier.start;
  },   

 start:function(e){
  if(document.all){//只在IE下执行,主要避免IE6的select无法覆盖
   magnifier.createIframe(magnifier.m.img);
  }
  this.onmousemove = magnifier.move;//this指向m.cont
  this.onmouseout = magnifier.end;
 },

 move:function(e){
   var pos = getPointerPosition(e);  //事件标准化
   this.getElementsByTagName('div')[0].style.display = '';
   css(this.getElementsByTagName('div')[0],{
   'top' : Math.min(Math.max(pos.y - this.offsetTop - parseInt(this.getElementsByTagName('div')[0].style.height) / 2,0),this.clientHeight - this.getElementsByTagName('div')[0].offsetHeight) + 'px',
   'left' : Math.min(Math.max(pos.x - this.offsetLeft - parseInt(this.getElementsByTagName('div')[0].style.width) / 2,0),this.clientWidth - this.getElementsByTagName('div')[0].offsetWidth) + 'px'   //left=鼠标x - this.offsetLeft - 浏览框宽/2,Math.max和Math.min让浏览框不会超出图像
   })

   magnifier.m.mag.style.display = '';
   css(magnifier.m.img,{
   'top' : - (parseInt(this.getElementsByTagName('div')[0].style.top) * magnifier.m.scale) + 'px',
   'left' : - (parseInt(this.getElementsByTagName('div')[0].style.left) * magnifier.m.scale) + 'px'
   })
  },

 end:function(e){
   this.getElementsByTagName('div')[0].style.display = 'none';
   magnifier.removeIframe(magnifier.m.img);  //销毁iframe
   magnifier.m.mag.style.display = 'none';
 },

 createIframe:function(elem){
  var layer = document.createElement('iframe');
  layer.tabIndex = '-1';
  layer.src = 'javascript:false;';
  elem.parentNode.appendChild(layer);
  layer.style.width = elem.offsetWidth + 'px';
  layer.style.height = elem.offsetHeight + 'px';
 },

 removeIframe:function(elem){
   var layers = elem.parentNode.getElementsByTagName('iframe');
   while(layers.length >0)
   {
   layers[0].parentNode.removeChild(layers[0]);
  }
 }
}
window.onload = function(){
magnifier.init({
       cont : document.getElementById('magnifier'),
       img : document.getElementById('magnifierImg'),
       mag : document.getElementById('mag'),
       scale : 5
       });
}
function closedWindow(){
	window.close();
}
</SCRIPT>
</head>
<body style="margin:0;padding:0;">
<div class="mainFrame">
  <div class="picLeft">
    <div id="magnifier"> <img src="<%=impPath%>" id="img"/>
      <div id="Browser"> </div>
    </div>
  </div>
  <div class="picRight" onclick="closedWindow()">
    <div id="mag"> <img id="magnifierImg"/> </div>
  </div>
  <script type="text/javascript" language="javascript">
         var   imgObject = document.getElementById("img");
         var   titleObjecto = document.getElementById("titleObject");
         var   emtyString ="" ,args = window.dialogArguments;
         if(args)imgObject.src = args.split(",")[0];
        </script>
</div>
</body>
</html>
