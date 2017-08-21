//Author:Mario 
//For IT irreg detail page to show media 

var showMediaForDetail={
  listMedia:null ,
  switchUrl:function(url , carInfo , obj){                                           //switchUr , switch mainFrame media address
      this.mainMediaFrameObject = document.getElementById("mainMediaFrame");
      this.mainMediaFrameObject.src = url ;
      this.mainMediaFrameObject.onclick=function(){showMediaForDetail.showLargeImg(url,carInfo);};
      for(var j=0 ;j<showMediaForDetail.listMedia.length ; j++){
        showMediaForDetail.unselectStyle(showMediaForDetail.listMedia[j]);
      }
        showMediaForDetail.selectedStyle(obj);                           
  },

   startShowMedia:function(){                                                 //startShowMedia , this function to start at onload page
   this.mainMedia = document.getElementById("showMedia");
   this.listMedia = document.getElementsByName("media");
   showMediaForDetail.listMedia = this.listMedia;
   if(this.listMedia.length != 0){
    for(var i=0 ; i<this.listMedia.length ; i++){
   this.listMedia[i].onclick = function(){showMediaForDetail.switchUrl(this.src,this.id , this);};
   }
   if(this.listMedia[0].id.substring(0,3)=="img"){
    showMediaForDetail.selectedStyle(this.listMedia[0]);
    this.mainMedia.appendChild(showMediaForDetail.createImgTag(this.listMedia[0].src ,this.listMedia[0].id))  ;
   }
   else if(this.listMedia[0].id == "MediaPlayer1"){
    showMediaForDetail.selectedStyle(this.listMedia[0]);
    this.mainMedia.innerHTML = showMediaForDetail.createObjectTag(this.listMedia[0].src);
    }
   }
   else{
     this.mainMedia.appendChild(showMediaForDetail.createImgTag(baseUrl+"/pic/crossErrorPic.png" ,"wushuju"));
    }
  },
  
   createImgTag:function(url , carInfoArray){                                        //createImgTag , create a new Img Tag
   this.ImgTag = document.createElement("img");
   this.ImgTag.id="mainMediaFrame";
   this.ImgTag.className="imgStyleWidth";
   this.ImgTag.src = url ;
   this.ImgTag.onclick = function(){showMediaForDetail.showLargeImg(url,carInfoArray);};
   return this.ImgTag ;
  },
  
   createObjectTag:function(url){                                                            //createObjectTag , create a new Object Tag
   this.ObjectTag =  '<object id="MediaPlayer1" name="media"  width="100" height="80" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,4,7,1112" align="baseline" border="0" type="application/x-oleobject">';
   this.ObjectTag +='<param id="URL" name="URL" value="'+url+'">';
   this.ObjectTag +='<param name="autoStart" value="TRUE">';
   this.ObjectTag +='<param name="fullScreen" value="false">';
   this.ObjectTag +='</object>' ;
   return this.ObjectTag ;
   },

    showLargeImg:function(imageAddress,carInfo){     
		  if(carInfo!='wushuju'){
			  this.roadName = carInfo.substring(carInfo.indexOf("roadName=")+9,carInfo.indexOf("&carNumber"));
				this.carNumber = carInfo.substring(carInfo.indexOf("carNumber=")+10,carInfo.indexOf("&vioTime"));
				this.vioTime = carInfo.substring(carInfo.indexOf("&vioTime=")+9,carInfo.indexOf("&laneCode"));
				this.laneCode = carInfo.substring(carInfo.indexOf("&laneCode=")+10,carInfo.indexOf("&flag"));
				this.carInformation = this.roadName.concat(",").concat(this.carNumber).concat(",").concat(this.vioTime).concat(",").concat(this.laneCode);
			 	window.showModalDialog(baseUrl+'/jsp/crossroad/large.html',imageAddress+','+this.carInformation,'dialogWidth=1024px;dialogHeight=510px;center=yes;middle=yes ;help=no;status=no;scroll=no');
		  }
   },
   
    selectedStyle:function(imgObj){
    imgObj.style.width = "98";
    imgObj.style.height = "78";
    imgObj.style.border = "2";
    imgObj.style.borderColor = "#990000";
    imgObj.style.borderStyle= "solid";
   },
  
    unselectStyle:function(imgObj){
    imgObj.style.width = "100";
    imgObj.style.height = "80";
    imgObj.style.border = "0";
    imgObj.style.borderColor = "#ffffff";
    imgObj.style.borderStyle= "solid";
   }
   
}