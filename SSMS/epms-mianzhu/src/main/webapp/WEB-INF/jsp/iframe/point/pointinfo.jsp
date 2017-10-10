<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.mian_white {
	background: white;
	color: #000;
	margin-left: 10px;
    
}
.mian_white tr td{
  padding:3px 1px;
}

.apb-TabMargin{
  height: 100%;
  padding-bottom: 8px;
  margin-left: 8px;
}
.apb-td-word-size{
  font-weight: bold;
  width: 30%;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		MapFactory.Require([ "ItmsMap/UserLayers/DataController*"], 
				function(DataController){

			var countObj=DataController().getcydPoint();
			var innerhtml=getPointInfo(countObj);
			var cydDiv=document.getElementById('cydpointDiv');
			cydDiv.innerHTML=innerhtml;
		});
	  
	});
	 function showImgInfo(url){
	    	var imgUrl=path+"/"+url;
	    	 MapFactory.Require(["ItmsMap/UserLayers/ShowImg*"],function(ShowImg){
	    		var showImg=ShowImg();
	    		showImg.init();
	    	
	    		if(isHasImg(imgUrl)){
	    			showImg.show(imgUrl);
	    		}else{
	    			showImg.show(path+"/images/files/error.png");
	    		}
	         });
	    	
		}
	    function isHasImg(pathImg){
	        var ImgObj=new Image();
	        ImgObj.src= pathImg;
	         if(ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0))
	         {
	           return true;
	         } else {
	           return false;
	        }
	    }
	    function loadImgError(img){
	    	img.src=path+"/images/files/error.png";
	    }
	var dataModel={
	    // 地下水监测点   地表灌溉水监测点  底泥监测点
		//	typeCode:"类型",
		//	samplingType:"样品类型",
		//	riversName:"河流名称",
		//	mudLongitude:"底泥经度",
		//	mudLatitude:"底泥纬度",
		//	polluteSs:"污染源状态",
		//	regionId:"采样地点",
        //	testItem:"待测项目",
	    //	receiveUser:"收样人",
	    //	sendUser:"送样人",
		//	checkStatus:"审查状态",
		//	checkUser:"审查人",
		//	checkTime:"审查时间",
		//	createId:"创建人",
		//	updateId:"修改人",
		//	updateTime:"修改时间",
		//	status:"状态",
		//	createTime: "创建时间",
	        longitude:"经度",
    	 	latitude: "纬度",
			samplingTime:"采样时间",
			samplingSource:"采样来源",
			samplingUser:"采样人",
    		name:"样品名称",
    		depth:"采样深度",
    		pointId:"采样地点",
    		containerVolume:"容器体积",
    		collectVolume:"收集量",
    	//	soilType:"土壤类型",
    		
    		typeCode:"类型",
    		position:"采样部位",
    		shopName:"店名",
    		shopkeeper:"店主",
    		tel:"联系方式",
    		dealManure:"经营肥料",
    		ph:"ph",
    		cadmium:"镉",
    		availableCadmium:"有效态镉"
  		};
	
	function getPointInfo(countObj){
		var innerHtml="<table style='background:white;color: #000;' class='width-100 text-center' id='cydPointInfoDiv'>";
		for(var key in dataModel){
			if (countObj.hasOwnProperty(key)){
				var value = countObj[key] ? countObj[key]:"";
				if(key=='createTime' || key=='updateTime' || key=='samplingTime' || key=='checkTime'){
					value=getDataTimeFormat(value);
					innerHtml = innerHtml + "<tr style='height: 28px;'><td class='apb-td-word-size'>" + dataModel[key] + "</td><td>" + value + "</td></tr>";
				}else if(key=='checkStatus'){
					value=getCheckStatus(value);
					innerHtml = innerHtml + "<tr style='height: 28px;'><td class='apb-td-word-size'>" + dataModel[key] + "</td><td>" + value + "</td></tr>";
				}else if(key=='pointId'){
					innerHtml = innerHtml + "<tr style='height: 28px;'><td class='apb-td-word-size'>" + dataModel[key] + "</td><td><tags:xiangxuncache keyName='AIRPOINT_ID_NAME' id='"+value+"'/></td></tr>";
				}else if(key=='samplingSource'){
					innerHtml = innerHtml + "<tr style='height: 28px;'><td class='apb-td-word-size'>" + dataModel[key] + "</td><td>" + cySource(value) + "</td></tr>";
				}
				else{
					value=valueStringHandle(value);
					innerHtml = innerHtml + "<tr style='height: 28px;'><td class='apb-td-word-size'>" + dataModel[key] + "</td><td>" + value + "</td></tr>";
				}
				
			}
		}
		
		var files=countObj.files;
		var _dom = MapFactory.Dom;
		if(files){
			innerHtml=innerHtml+"<tr><td style='font-weight: bold;'>现场素材</td><td>";
			for(var i=0;i<files.length;i++){
				var file=files[i];
				innerHtml=innerHtml+"<img  style='margin:2px;width:33px;height:56px;' src='"+path+"/"+file.filePath+"' onclick=showImgInfo('"+file.filePath+"')  onerror=loadImgError(this)></img>";
			}
			innerHtml=innerHtml+"</tr></td>";
		}
		
		//innerHtml=innerHtml+"<tr><td><img src='${root}/"+countObj.files[0].filePath+"'/></td></tr>";
		innerHtml=innerHtml+"</table>";
		return innerHtml;
	}
	function valueStringHandle(value){
		return value ? value : "";
	}
	function cySource(source){
    	if(source==="1"){
    		return "原始录入";
    	}else if(source==="2"){
    		return "移动APP";
    	}else{
    		return "批量导入";
    	}
	}
	function getDataTimeFormat(time){
		if(!time){
			return "";
		}
		var unixTimestamp = new Date( time );
		var formatTime=format(unixTimestamp,"yyyy-MM-dd hh:mm:ss");
		return formatTime;
	}
	function format(date, fmt) {
		var first;
		var o = {
			"M+": date.getMonth() + 1, //月
			"d+": date.getDate(),//日
			"h+": date.getHours(),//小时
			"m+": date.getMinutes(),//分钟
			"s+": date.getSeconds(),//秒
			"q+": Math.floor((date.getMonth() + 3)/3),
			"S": date.getMilliseconds()
		};
		
		//年可以不区分大小写
		if (/(y+)/ig.test(fmt)) {
			first = RegExp.$1;
			//以年份替换格式符
			fmt = fmt.replace(first, (date.getFullYear() + "").substr(4 - first.length));
			
			for (var key in o) {
				if (new RegExp("(" + key + ")").test(fmt))
				{
					first = RegExp.$1;
					fmt = fmt.replace(first, ("0" + o[key]).substr(("" + o[key]).length - 1));
				}
			}
		}
		
		return fmt;
	}
</script>
<div id="cydpointDiv" class="apb-TabMargin">
  
</div>

