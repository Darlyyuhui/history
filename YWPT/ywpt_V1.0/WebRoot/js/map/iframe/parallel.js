/** 
 * 平行线算法
 * */

var parallel = (function() {
	var //缓存坐标转换方法
		toXY,
		toGeo,
		
		//生成的平行线graphic
		graphic;
		
	
	var api = {
		createParallel: createParallel
	}
	
	/*
	 * 创建平行线
	 * @param line 需要创建平行线的中心线
	 * @param distance 需要创建的平行线离原始线的距离
	 * @param side 取值"both", "left", "right"，默认为"both"；
	 * */
	function createParallel(line, distance, side){
			
		var paths,
			newPaths,
			
			//每条平行线的paths
			leftPaths = [],
			rightPaths = [],
	
			//当前点与平行线上对应的之间的经纬度差
			d={},
			//平行线上与当前点对应的点坐标
			lx, ly, rx, ry,
			
			//side正确值的集合
			sideObj = "both left right",
			
			//平行线及对应的graphic
			polyline;
				
		//对线的头部和尾部进行加点处理
		paths = initPolyline(line);
		
		//distance不存在，则设置默认距离[-10, 10]
		if (!distance) {
			distance = 10;
		}
		
		if (!side || typeof side != "string" || sideObj.indexOf(side) === -1) {
			side = "both";
		}
		
		toXY = esri.geometry.lngLatToXY;
		toGeo = esri.geometry.xyToLngLat;
		
		polyline =  new esri.geometry.Polyline(new esri.SpatialReference({wkid:4326}));
		
		for (var j = 0, pathsLen = paths.length; j < pathsLen; j = j + 1) {
			newPaths = paths[j];
			for(var i = 1, newPathLen = newPaths.length - 1; i < newPathLen; i = i + 1){
				d = getParallelPoint(newPaths[i - 1], newPaths[i], newPaths[i + 1], distance);
				tempPoint = toXY(newPaths[i][0], newPaths[i][1]);

				lx = tempPoint[0] + d.lng;
				ly = tempPoint[1] + d.lat;
				rx = tempPoint[0] - d.lng;
				ry = tempPoint[1] - d.lat;

				leftPaths.push(toGeo(lx, ly));
				rightPaths.push(toGeo(rx, ry));
			}
			
			switch(side) {
				case "both":
					polyline.addPath(leftPaths);
					polyline.addPath(rightPaths);
					break;
				case "left":
					polyline.addPath(leftPaths);
					break;
				case "right":
					polyline.addPath(rightPaths);
					break;
				default:
					break;
			}
			
			leftPaths = rightPaths = [];
		}		
		
		graphic = new esri.Graphic(polyline, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255,255,255]), 3), null, null);
		//默认图层不显示
		graphic.hide();
		methodAccess(graphic);
		//恢复原始线数据
		paths[0].shift();
		paths[paths.length - 1].pop();
		
		return graphic;
	}
	
	//添加辅助点，辅助点与开始的两点或尾部两点在一条直线上，这样方便计算添加点的坐标
	//同时也便于计算平行线上对应点的坐标
	function initPolyline(line){		
		var paths = line.paths,
			pathLength = paths.length,
			tempLength,
			newFirPoint,
			newLastPoint,
			firstPoint,
			secondPoint,
			lastSecPoint,
			lastPoint;

		firstPoint = paths[0][0];
		secondPoint = paths[0][1];
		tempLength = paths[pathLength - 1].length;
		lastPoint = paths[pathLength - 1][tempLength - 1];
		lastSecPoint = paths[pathLength - 1][tempLength - 2];
		
		newFirPoint = [firstPoint[0] * 2 - secondPoint[0], firstPoint[1] * 2 - secondPoint[1]];
		newLastPoint = [lastPoint[0] * 2 - lastSecPoint[0], lastPoint[1] *2 - lastSecPoint[1]];
		paths[0].unshift(newFirPoint);
		paths[pathLength - 1].push(newLastPoint);
		
		return [].concat(line.paths);
	}
	
	//获取平行线上与当前点相对应的点的坐标,距离默认以米为单位,怎么设置多条平行线？
	function getParallelPoint(firPoint, secPoint, thiPoint, distance){
		firPoint = toXY(firPoint[0], firPoint[1]);
		secPoint = toXY(secPoint[0], secPoint[1]);
		thiPoint = toXY(thiPoint[0], thiPoint[1]);
		//第三点到到第二点的距离
		var y32 = thiPoint[1] - secPoint[1],
			x32 = thiPoint[0] - secPoint[0],
			
			//第二点到第一点的距离
			y21 = secPoint[1] - firPoint[1],
			x21 = secPoint[0] - firPoint[0],
			
			//当前线段与x轴正方向的夹角
			angle32,angle21,
			
			//两条线段夹角的一半
			half,
			
			//两条线段的角平分线与x轴正方向的夹角
			r,
			//平行线上与对应当前点的坐标经纬度差
			lat,lng;
		
		//如果x32为零，则说明此线段垂直于x轴
		if (x32 == 0){
			angle32 = Math.PI/2;
			if (y32 < 0) angle = -angle;
		}else{
			angle32 = Math.atan(y32/x32);
		}
		//如果x21为零，则说明此线段垂直于x轴
		if (x21 == 0){
			angle21 = Math.PI/2;
			if (y21 < 0) angle21 = -angle21;
		}else{
			angle21 = Math.atan(y21/x21);
		} 
		//如果第三点的x值小于第二点的x值,角度落在二三象限，加上PI进行转换
		if( thiPoint[0] < secPoint[0]){
			angle32 += Math.PI;
		}
		//如果第二点的x值小于第一点的x值,角度落在二三象限，加上PI进行转换
		if( secPoint[0] < firPoint[0]){
			angle21 += Math.PI;
		}
		
		half = (angle21 - angle32 - Math.PI)/2;
		r = angle32 + half;
		
		lenLat = distance / Math.sin(half) * Math.sin(r);
		lenLng = distance / Math.sin(half) * Math.cos(r);
		return {lat:lenLat, lng:lenLng};
	}
	
	function methodAccess(graphic) {
		var layer = graphic.getLayer();
		
		//设置样式
		graphic.setStyle = function(style) {
			style = esri.symbol.SimpleLineSymbol["STYLE_" + style.toUpperCase()];
			graphic.symbol.setStyle(style);			
		}
		
		//释放
		graphic.destroy = function() {
			layer ? layer.remove(graphic) : "";
		}
		//刷新
		graphic.refresh = function() {
			layer ? layer.redraw() : "";
		}
	}	
	return api;
})()