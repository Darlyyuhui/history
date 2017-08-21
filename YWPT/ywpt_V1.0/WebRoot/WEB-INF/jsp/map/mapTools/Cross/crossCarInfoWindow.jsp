<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="compnents/bootstrap/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="css/GisStyle/Dialog.css" type="text/css">
<link rel="stylesheet" href="css/GisStyle/baseMapTools.css" type="text/css">
<link id="CommonCss" rel="stylesheet" href="css/GisStyle/Common.css" type="text/css">
<link rel="stylesheet" href="css/Mapstyle.css" type="text/css">
<script src="compnents/bootstrap/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
	//切换皮肤方法
	var d=window.parent.document.getElementById("skin-sel");
	if(d==null){
		d = parent.parent.document.getElementById("skin-sel");
	}
	if(d!=null){
		var value= d.options[d.selectedIndex].value;
		if(value=="1"){
			$("#CommonCss").attr("href","css/GisStyle/Common.css");
		}else if(value=="2"){
			$("#CommonCss").attr("href","cssGreen/Common.css");
		}else if(value=="3"){
			$("#CommonCss").attr("href","cssDarkBlue/Common.css");
		}else{
		
		}
		//d.addEventListener("change",ssss,false);
		//监听主页皮肤切换
		$(d).bind("change", function(){
			debugger;
			var  d1=window.parent.document.getElementById("skin-sel");
			if(d1==null){
				d1 = parent.parent.document.getElementById("skin-sel");
			}
			var value1= d1.options[d1.selectedIndex].value;
			if(value1=="1"){
				$("#CommonCss").attr("href","css/GisStyle/Common.css");
			}else if(value1=="2"){
				$("#CommonCss").attr("href","cssGreen/Common.css");
			}else if(value1=="3"){
				$("#CommonCss").attr("href","cssDarkBlue/Common.css");
			}else{
			}
		});
	}
	// 切换皮肤方法结束
</script>
</head>
<body>
<div style="width: 620px; height: 320px; padding: 0px;">
	<img onclick="window.parent.tipsdownImgFd('图片特写',this.src)"
		src="<tags:xiangxunftp returnType="ip" deviceCode="${param.deviceCode}"></tags:xiangxunftp>/${param.img1Path}" 
		alt="${param.carPlateNum}"
		style="display: block; width: 320px; height: 320px; float: left;">

	<table class="commonResultTable" style="float: left; width: 285px; margin-left: 5px;">
		<tr class="commonResultTableTitleRow">
			<td colspan="2" style="font-weight: bold;">
				通行车辆基本信息：
			</td>
		</tr>
		<tr class="commonResultTableEvenRow">
			<td>
				号牌号码：
			</td>
			<td>
				${param.carPlateNum}
			</td>
		</tr>
		<tr class="commonResultTableOddRow">
			<td>
				号牌种类：
			</td>
			<td>
				<tags:xiangxuncache keyName="Dic" id="${param.carPlateTypeCode}" typeCode="platetype"></tags:xiangxuncache>
			</td>
		</tr>
		<tr class="commonResultTableEvenRow">
			<td>
				号牌颜色：
			</td>
			<td>
				<tags:xiangxuncache keyName="Dic" id="${param.carPlateColorCode}" typeCode="platecolor"></tags:xiangxuncache>
			</td>
		</tr>
		<tr class="commonResultTableOddRow">
			<td>
				通行时间：
			</td>
			<td>
				${param.carDatetimeStr}
				<fmt:formatDate value="${param.carDatetime}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
		</tr>
		<tr class="commonResultTableEvenRow">
			<td>
				所在道路：
			</td>
			<td>
				<tags:xiangxuncachedeviceroad code="${param.deviceCode}"></tags:xiangxuncachedeviceroad>
			</td>
		</tr>
		<tr class="commonResultTableOddRow">
			<td>
				通行地点：
			</td>
			<td>
				<tags:xiangxuncache keyName="devcode_name" id="${param.deviceCode}" ></tags:xiangxuncache>
			</td>
		</tr>
		<tr class="commonResultTableEvenRow">
			<td>
				行驶方向：
			</td>
			<td>
				<tags:xiangxuncache keyName="Dic" id="${param.directionCode}" typeCode="direction"></tags:xiangxuncache>
			</td>
		</tr>
		<tr class="commonResultTableOddRow">
			<td>
				通行速度：
			</td>
			<td>
				${param.carSpeed}KM/H
			</td>
		</tr>
		<tr class="commonResultTableEvenRow">
			<td>
				行驶车道：
			</td>
			<td id="vio_laneCode">
				${param.laneCode}
			</td>
		</tr>
	</table>
</div>
</body>
</html>