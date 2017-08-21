(function(){
	// 图层信息
	var layersInfo = itmap.arcgis.layerInfos;
	var url = ""; // 获取rest访问路径
	var target = null; // checkbox点击对象
	var mapContainer = itmap.arcgis.map; // 获取map资源
	var maxLayerIndex = 1000; // 图层最大层数

	// 初始化
	function init(){
		var layersToc = "";
		for(var i = 0; i < layersInfo.length; i++){
			layersToc += "<li>";
			layersToc += "<span class='checkbox'><input name='layerControlerCheckboxs' type='checkbox' value='"+i+"'";
			if(layersInfo[i].isadd){
				layersToc += "checked = true";
			}
			layersToc += "/></span>";
			layersToc += "<span class='checkboxText'>"+layersInfo[i].alias+"</span>";
			layersToc += "</li>";
		}
		$(".mapLayersControler").html(layersToc);
		
		// checkbox 选择
		$(".mapLayersControler li span input").click(function(e){
			if($(this).attr("checked")){
				$(this).attr({"checked":true});
				addLayer($(this).attr("value"));
			}else{
				$(this).attr({"checked":false});
				removeLayer($(this).attr("value"));
			}
		});
	}
	init();

	// 添加图层
	function addLayer(index){
		url = "map/home/getMapLayersRest/" + layersInfo[index].name;
		if("" == layersInfo[index].url){
			$.get(url,function(data){
				layersInfo[index].url = data.substr(0,data.indexOf(','));
				layersInfo[index].index = data.substr(data.indexOf(',')+1,data.length);
		 		layersInfo[index].isadd = true;
				layersInfo[index].layer = new esri.layers.FeatureLayer(layersInfo[index].url,{
					mode:esri.layers.FeatureLayer.MODE_ONDEMAND,
					outFields:["*"]
				});
				layersInfo[index].isadd = true;
				mapContainer.addLayer(layersInfo[index].layer,layersInfo[index].index);
				delete layersInfo[index].layer.onClick;
				dojo.connect(layersInfo[index].layer,"onClick",function(e){bindClickEvt(e,{layerName:layersInfo[index].name})});
			});
		}else{
			layersInfo[index].isadd = true;
			mapContainer.addLayer(layersInfo[index].layer,layersInfo[index].index);
			delete layersInfo[index].layer.onClick;
			dojo.connect(layersInfo[index].layer,"onClick",function(e){bindClickEvt(e,{layerName:layersInfo[index].name})});
		}
		/**
		 * 对图层进行重新排序，api中的reorderLayer经过测试，发现该函数在排序时，还是以reoder的先后顺序对图层进行排序
		 */
		dojo.connect(mapContainer,"onLayerAdd",function(layer){
			var tempLayer = [];
			var temp = null;
			for(var i=0;i<layersInfo.length;i++){
				if(layersInfo[i].isadd){
					tempLayer.push(layersInfo[i]);
				}
			}
			for(var i=0;i<tempLayer.length-1;i++){
				for(var j=i+1;j<tempLayer.length;j++){
					if(tempLayer[i].index < tempLayer[j].index){
						temp = tempLayer[i];
						tempLayer[i] = tempLayer[j];
						tempLayer[j] = temp;
					}
				}
			}
			for(var i = 0;i<tempLayer.length;i++){
				mapContainer.reorderLayer(tempLayer[i].layer,maxLayerIndex - tempLayer[i].index);
			}
			tempLayer = [];
			temp = null;
		});
	}

	// 移除图层
	function removeLayer(index){
		mapContainer.removeLayer(layersInfo[index].layer);
		layersInfo[index].isadd = false;
	}
	
	// 绑定点击事件
	function bindClickEvt(evt,data){
		
		$.post("map/home/getMapGraphicsAttr/",{objID:evt.graphic.attributes["OBJECTID"],layerName:data.layerName},function(data){
			var content = "<table>";
			content += "<tr><td>ID：</td><td>"+evt.graphic.attributes["OBJECTID"]+"</td></tr>";
			for(elem in data){
				content += "<tr><td>"+elem+"：</td><td>"+data[elem]+"</td></tr>";
			}
			content+="</table>";
			//mapContainer.infoWindow.setContent(content);
			mapInfoWindowTool.setContent(content);
		},"json");
		mapInfoWindowTool.setContent("<img src='images/loading.gif' />").show(mapContainer,evt.mapPoint);
		//mapContainer.infoWindow.setTitle("属性信息");
		//mapContainer.infoWindow.setContent("<img src='images/loading.gif' />");
		//mapContainer.infoWindow.show(evt.screenPoint,mapContainer.getInfoWindowAnchor(evt.screenPoint));
	}
})()