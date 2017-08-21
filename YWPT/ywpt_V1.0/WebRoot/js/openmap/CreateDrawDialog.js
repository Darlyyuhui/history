var feature;
var  popup;
function toggleTag(showContent,selfObj){
	// 操作标签
	var tag = document.getElementById("tagsList").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
	// 操作内容
	for(i=0; j=document.getElementById("tagContent"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
	drawtype=$(selfObj).text();
     if(drawtype=="点注记"){  
    	 $("#imgbox").show();
	    $(".TagRightcon button").unbind("click").bind('click', function() {	    		    	 
	    	togglecontrol("point");		    	
	    	});	    
     }
     
     if(drawtype=="线注记"){ 
    	 $("#imgbox").hide();
    	  $(".TagRightcon button").unbind("click").bind('click', function() {	    	    		
  	    	togglecontrol("line");		    	
  	    	});
         }
     
     if(drawtype=="面注记"){
    	 $("#imgbox").hide();
    	  $(".TagRightcon button").unbind("click").bind('click', function() {	    	
  	    
  	    	togglecontrol("polygon");		    	
  	    	});
         }
     
     
}

function createDrawDialog(){	

	
	$("#Tabcon").css({x:"600",y:"250"}).show(1000);
	
	$("#Tabcon").draggable().draggable();
	
}

function addToMap(event) {	
	 feature=event.feature;

    for(var key in drawControls) {                 
        var drawcontrol = drawControls[key]; 
        drawcontrol.deactivate();
    }  
	var symbol=null;
	// 选择标绘方式	
	switch (drawtype) {
		case "点注记":
			symbol ={externalGraphic:$("#img").attr("src"),graphicHeight:45,graphicWidth:30}; 
			break;
		case "线注记":
			symbol ={stroke:true,strokeColor:lineColor,strokeWidth:$("#SliderSingle").val(),strokeLinecap:"round",strokeDashstyle:$("#linestyle").val(),strokeOpacity:0.3}; 
			break;
		case "面注记":
		    symbol ={fill:true,fillColor:fillColor,strokeColor:strokeColor,strokeWidth:$("#Slider2").val(),strokeOpacity:0.3,fillOpacity:0.4};
			break;   
	}		
    feature.style=symbol;
    marker_Layer.redraw();
    popuInfoWindow();
	
}


// 激活工具条工具结束------------------------------------------------


function popuInfoWindow() {
	    
	    $("#markLineInfoName").val("");
	    $("#markLineInfoBrief").val("");
	    $("#marker").css("display","block");
	    $("#marker").dialog({
			 title:drawtype,
			 width:250,
			 height:240,
			 zIndex:970,
			 buttons:[{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
				        saveText();
					}
				},{
					text:'取消',
					handler:function(){
					  cancel();
					}
				}]
			 
		 		 
	    });	  
}



function saveText(){
	var name= $("#markLineInfoName").val();
	if(name==""){
		return;
		
	}
	$("#marker").dialog('close');		
	imageSrc=$("#img").attr("src");
	var ulNode=$("#draw-list");		
	var nodeName=$("#markLineInfoName").val();
    var nodeDescription=$("#markLineInfoBrief").val();
	var attrObj = {"name":nodeName,"description":nodeDescription};		  
	    feature.attributes=attrObj;	
    var index=marker_Layer.features.length-1;   
	
	var htmlStr="";		
		switch (drawtype) {
		case "点注记":
			      htmlStr="<li><img src='"+imageSrc+"' width='25' height='30' alt='point'/>&nbsp &nbsp" +
			              "<a href='javascript:void(0);' onclick='positionPoint("+index+")'>"+nodeName+"</a>&nbsp &nbsp &nbsp &nbsp"+						
				          "<a href='javascript:void(0);'onclick='deleteRecord(this.parentNode,"+index+")'>删除</a>"+
				          "</li>";	
		                   break;
		case "线注记":			
			     htmlStr="<li><img src='compnents/openmap/img/polyline.png' width='25' height='30' alt='line'/>&nbsp &nbsp " +
			              "<a href='javascript:void(0);' onclick='positionPoint("+index+")'>"+nodeName+"</a>&nbsp &nbsp &nbsp &nbsp"	+				
			              "<a href='javascript:void(0);'onclick='deleteRecord(this.parentNode,"+index+")'>删除</a>"+
			              "</li>";			
			               break;
		case "面注记":			
			     htmlStr="<li><img src='compnents/openmap/img/polygon.png' width='25' height='30'' alt='polygon' />&nbsp &nbsp" +
			             "<a href='javascript:void(0);'onclick='positionPoint("+index+")'>"+nodeName+"</a>&nbsp &nbsp &nbsp &nbsp"+					
		                 "<a href='javascript:void(0);'onclick='deleteRecord(this.parentNode,"+index+")'>删除</a>"+
			             "</li>";			
			              break;		   
	}		
		ulNode.append(htmlStr);	
		
}





function cancel() {	
	$("#marker").dialog('close');
    $("#markLineInfoName").val("");
	$("#markLineInfoBrief").val("");			
		marker_Layer.removeFeatures([feature]);
}


function deleteRecord(liNode,index) {	
	var bl = confirm("确定要删除该记录吗");
	if (bl) {		
        $(liNode).remove();  
        var graphic=marker_Layer.features[index];
        marker_Layer.removeFeatures([graphic]);
	}  		

}
function positionPoint(index){	
	var graphic=marker_Layer.features[index]; 
    popup = new OpenLayers.Popup.FramedCloud("featurePopup",
    		                graphic.geometry.getBounds().getCenterLonLat(),
                             new OpenLayers.Size(100,100),
                             "<h2>"+graphic.attributes.name + "</h2>" +
                             graphic.attributes.description,
                             null, true, null);
    graphic.popup = popup;
    popup.feature = graphic;
    map.addPopup(popup);	
}









