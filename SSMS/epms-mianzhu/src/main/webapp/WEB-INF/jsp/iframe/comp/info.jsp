<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.mian_white {
	background: white;
	color: #000;
	margin-left: 10px;
    
}
.apb-TabMargin tr td{
  padding:3px 1px;
  width: 200px;
}

.apb-TabMargin{
  height: 100%;
  width:400px;
  padding-bottom: 8px;
  margin-left: 8px;
  padding-right: 15px;
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
			var cydDiv=document.getElementById('compPoluteDiv');
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
			name:"企业名称",
	        longitude:"经度",
    		latitude: "纬度",
    		address:"地址",
    		linkMan: "联系人",
    		linkTel:"联系方式",
    		features:"主要污染物"
  		};
	
	function getPointInfo(countObj){
		var innerHtml="<table style='background:white;color: #000;' class='width-100 text-center'>";
		var flag=0;
		var trhtml="";
		var trtemp="";
		for(var key in dataModel){
			if (countObj.hasOwnProperty(key)){
				var value = countObj[key] ? countObj[key]:"";
				if(flag%2==0){
					trhtml="<tr><td class='apb-td-word-size'>" + dataModel[key] + "</td><td>" + getValueByKey(key,value) + "</td>";
					trtemp="<tr><td class='apb-td-word-size'>" + dataModel[key] + "</td><td colspan='3' style='text-align:left;'>" + getValueByKey(key,value) + "</td>";
				}else{
					trhtml=trhtml+"<td class='apb-td-word-size'>" + dataModel[key] + "</td><td>" + getValueByKey(key,value) + "</td></tr>";
					innerHtml = innerHtml+trhtml;
					trhtml="";
				}
				++flag;
			}
		}
		if(trhtml){
			innerHtml = innerHtml+trtemp;
		}
		
		var files=countObj.filesList;
		var _dom = MapFactory.Dom;
		if(files){
			innerHtml=innerHtml+"<tr><td style='font-weight: bold;'>企业照片</td><td colspan='3'>";
			for(var i=0;i<files.length;i++){
				var file=files[i];
				innerHtml=innerHtml+"<img  style='padding:2px;max-width:100%;' alt='"+file.fileName+"' src='"+path+"/"+file.filePath+"' onclick=showImgInfo('"+file.filePath+"')  onerror=loadImgError(this)></img>";
			}
			innerHtml=innerHtml+"</tr></td>";
		}
		
		//innerHtml=innerHtml+"<tr><td><img src='${root}/"+countObj.files[0].filePath+"'/></td></tr>";
		innerHtml=innerHtml+"</table>";
		return innerHtml;
	}
	function getValueByKey(key,value){
		if(key=='createTime' || key=='updateTime' || key=='samplingTime' || key=='checkTime'){
			return getDataTimeFormat(value);
		}else{
			return valueStringHandle(value);
		}
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
	
</script>
<div id="compPoluteDiv" class="apb-TabMargin">
  
</div>

