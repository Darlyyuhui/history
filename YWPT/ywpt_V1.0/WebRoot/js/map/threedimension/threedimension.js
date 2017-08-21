/**
 * 在地图上的指定位置生成弹出框，显示此位置的街景 
 *  
 */

var threeDimension = (function(){
	var graphic,
	
	//图片标注的位置集合
	locs=[108.916,34.243],
	
	//图片标注的位置
	labelPoint,
	
	//图片标注的相关变量
	picUrl,picWidth,picHeight,picSymbol,
	
	//标注图层
	graphicsLayer,
	
	//弹出框
	infoTemplate,
	
	//地图
	map = myMap,
	
	//配置信息
	conf = {
		picUrl:"images/threeDimension/camera.png",
		picWidth:27,
		picHeight:37			
	};
			
	//开放的方法
	var api = {
		open:open,
		close:close
	};

	(function(){
		//html字符串
		var htmStr;
		
		//生成图片标注
		picSymbol = new esri.symbol.PictureMarkerSymbol(conf.picUrl, conf.picWidth, conf.picHeight);
		graphicsLayer = new esri.layers.GraphicsLayer({id:"三维图层"});
		map.addLayer(graphicsLayer);
		graphicsLayer.hide();
		
		//添加指示位置图标
		labelPoint = new esri.geometry.Point(locs[0],locs[1]);
		infoTemplate = new esri.InfoTemplate();				
		htmlStr="<div><iframe src='map/home/mapTools/threedimension/pano' width='900' height='500''></iframe></div>";
		infoTemplate.setContent(htmlStr);
		infoTemplate.setTitle("西工大路街景");
		graphic = new esri.Graphic(labelPoint,picSymbol,null,infoTemplate);
		graphicsLayer.add(graphic);
		
		//添加事件
		dojo.connect(graphicsLayer, "onClick", function(e){
			map.infoWindow.hide();
			
			var p1 = map.toMap({x:0, y:0});
			var p2 = map.toMap({x:120, y:70});
			var cmp = e.mapPoint;
			var mh = map.extent.getHeight()/2+p2.y-p1.y;
			var mw = map.extent.getWidth()/2-p2.x+p1.x;
			var newP = new esri.geometry.Point(cmp.x+mw, cmp.y-mh, map.spatialReference);
			
			var mechandler = dojo.connect(map, "onExtentChange", function(extent,delta,levelChange,lod){
				map.infoWindow.resize(935, 520);
				map.infoWindow.show(cmp);
//				dojo.disconnect(cmp);
			});
			map.centerAt(newP);
//			console.log(map.infoWindow);
			
			var hideHandler = dojo.connect(map.infoWindow, "onHide", function(e){
				//隐藏指示位置的graphic
				graphic.hide();
				dojo.disconnect(mechandler);
				dojo.disconnect(hideHandler);
			})
		});
						
	})();
			
	//显示街景
	function open(){
		graphic.show();
		graphicsLayer.show();
		map.centerAt(labelPoint);						
	}
	
	//关闭街景
	function close(){
		graphic.hide();
		graphicsLayer.hide();
		map.infoWindow.hide();
	}
	
	//添加点位置
	function addLoc(locPoint){			
		locs.push(locPoint);						
	}
	
	//设置图片标注的路径等属性
	function setPic(picUrl,picWidth,picHeight)
	{
	}
	
	return api;	
})();

