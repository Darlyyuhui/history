/*
 * @LHF
 * @data 2013-09-04
 * 加载行政区域的动态切片地图服务
 * 
 * */
(function(map){
	
	//配置信息
	var conf={			
			//图层配置信息
			layerInfos:[{name:"Brigade",alias:"大队管辖区",id:1},
						{name:"Locus",alias:"中队管辖区",id:2}],
			
			//添加到map中的layer数目
			idCount:0				
	}
	
	
	//初始化
	initLayerControl();
	initListener();
	
			
	//初始化地图及控制菜单
	function initLayerControl(){		
		for(var i = 0; i < conf.layerInfos.length; i++){
			//添加图层控制菜单
			
			var inputId = "layersRenderInput"+conf.layerInfos[i].id;		
			var html = "<p><span class='checkbox'><input id="+inputId+" type='checkbox'/></span>"+conf.layerInfos[i].alias+"</p>";
			$(".layersRenderContainer").append(html);
			
			//获取配置文件中的图层url信息，并生成layer			
			var requestUrl = "map/home/getMapLayersRest/"+conf.layerInfos[i].name;
			$.get(requestUrl,function(data){
				var layerUrl = data.substr(0,data.indexOf(','));
				var layer = new esri.layers.FeatureLayer(layerUrl,{
					mode:esri.layers.FeatureLayer.MODE_ONDEMAND,
					outFields:["*"]
				});	
				map.addLayer(layer);
			});	
		}
	}
	
	
	//初始化监听事件
	function initListener(){		
		var handler = dojo.connect(map,"onLayerAdd",function(layer){							
				conf.idCount+=1;
				
				//添加对应的图层控制
				for(var j in conf.layerInfos){
					if(layer.name === conf.layerInfos[j].name.toLowerCase()){
						
						//创建一个id的副本，因为onScaleVisibility暂时未触发，若直接用conf.layerInfos[j].id会导致触发的时候j一直为2
						var id = conf.layerInfos[j].id;
						
						//checkBox默认是否选中
						$("#layersRenderInput"+id).attr("checked",layer.visibleAtMapScale);

						//向layer注册 onScaleVisibilityChange()事件
						dojo.connect(layer,"onScaleVisibilityChange",function(){
							$("#layersRenderInput"+id).attr("checked",layer.visibleAtMapScale);
						});
						
						//checkBox的click事件，确定图层是否可见
						$("#layersRenderInput"+id).click(function(){
							if($(this).attr("checked")){
								layer.show();
							}else{
								layer.hide();
							}
						});
											
					}	
				}
				
				//取消事件
				if(conf.idCount >= conf.layerInfos.length){
					dojo.disconnect(handler);
				}
		});
	}

})(myMap)


