/**
 * 工具类 
 */

itmap.openlayer.util = function(){
	var api = {
			orderRouteResult:orderRouteResult,//组织网络分析返回的数据
			checkParam:checkParam,//判断传入的参数是否为null或者为undefined，否则返回true
			isSimilarPoint:isSimilarPoint,//是否为同一点
			getPointByX:getPointByX,//根据y计算线上对应的点
			getPointByY:getPointByY,//根据y计算线上对应的点
			isBlongLine:isBlongLine,//计算点是否在线上
			getDirectionByXY:getDirectionByXY,//根据x，y的差值计算方向，返回值为8个方向的英文首字母
			getAngleByXY:getAngleByXY,// 根据x，y的差值计算方向0-360
			getXAngleByXY:getXAngleByXY,// 根据x，y的差值计算弧度值
			getLayerObj:getLayerObj,// 根据指定的graphicLayer或者featureLayer返回包含点线面的对象
			showLoadingInfoWindow:showLoadingInfoWindow//繁忙信息框
		};
	
	
	var errorNum = 0.00000000000001;// 设置一个容差值，因为在计算的时候取了PI的近似值
	var exitNum = 0.0000001;// 二分法查找时跳出递归的差值
	
	function checkParam(obj) {
		if(null == obj || typeof obj == "undefined") {
			return false;
		}
		return true;
	}
	
	function isSimilarPoint(p1, p2) {
		if(p1[0] == p2[0] && p1[1] == p2[1])return true;
		else return false;
	}
	function getPointByX(line, x) {
		if(Math.max(line[0][0],line[1][0]) < x || Math.min(line[0][0],line[1][0]) > x)return null;
		var midx = (line[0][0]+line[1][0])/2;
		var midy = (line[0][1]+line[1][1])/2;
		if(Math.abs(midx-x) < exitNum)return [midx,midy];
		else if((midx > x && x > line[0][0]) || (midx < x && x < line[0][0])) {
			return getPointByX([line[0], [midx, midy]], x);
		}
		else {
			return getPointByX([line[1], [midx, midy]], x);
		}
	}
	function getPointByY(line, y) {
		if(Math.max(line[0][1],line[1][1]) < y || Math.min(line[0][1],line[1][1]) > y)return null;
		var midx = (line[0][0]+line[1][0])/2;
		var midy = (line[0][1]+line[1][1])/2;
		if(Math.abs(midy-y) < exitNum)return [midx,midy];
		else if((midy > y && y > line[0][1]) || (midy < y && y < line[0][1])) {
			return getPointByY([line[0], [midx, midy]], y);
		}
		else {
			return getPointByY([line[1], [midx, midy]], y);
		}
	}
	function isBlongLine(line, point) {
		var p = getPointByX(line, point[0]);
		if(p && Math.abs(p[1]-point[1]) < exitNum)return true;
		return false;
	}
	function getDirectionByXY(x, y) {
		var result = "E";
		if(x == 0) {
			if(y > 0) {
				result = "N";
			}
			else if(y < 0){
				result = "S";
			}
			return result;
		}
		var angle = 180*Math.abs(Math.atan(y/x))/Math.PI;
		if(x > 0 && y > 0) {
			if(angle <= 22)result = "E";
			else if(angle >= 68)result = "N";
			else result = "NE";
		}
		else if(x < 0 && y >= 0) {
			if(angle <= 22)result = "W";
			else if(angle >= 68)result = "N";
			else result = "NW";
		}
		else if(x < 0 && y <= 0) {
			if(angle <= 22)result = "W";
			else if(angle >= 68)result = "S";
			else result = "SW";
		}
		else if(x > 0 && y < 0) {
			if(angle <= 22)result = "E";
			else if(angle >= 68)result = "S";
			else result = "SE";
		}
		return result;
	}
	/**
	 * 根据两点间xy坐标差获得角度值
	 * */
	function getAngleByXY(x, y) {
		var result = 0;
		if(x == 0) {
			if(y > 0) {
				return 90;
			}
			else if(y < 0){
				return 270;
			}
			else {
				return 0;
			}
		}
		// 计算角度，无方向，与x轴的夹角
		var angle = 180*Math.abs(Math.atan(y/x))/Math.PI;
		if(x > 0 && y >= 0) {
			result = angle;
		}
		else if(x < 0 && y > 0) {
			result = 180 - angle;
		}
		else if(x < 0 && y <= 0) {
			result = angle + 180;
		}
		else if(x > 0 && y < 0) {
			result = 360 - angle;
		}
		return result;
	}
	/**
	 * 根据两点间xy坐标差获得弧度值
	 * */
	function getXAngleByXY(x, y) {
		return Math.abs(Math.atan(y/x));
	}
	
	function getLayerObj(layer) {
//		var graphics = layer.graphics;
//		var points=[], lines=[], polygons=[];
//		for(var i in graphics) {
//			var g = graphics[i];
//			switch(g.geometry.type) {
//				case "point":
//					points.push(g);
//				break;
//				case "multipoint":
//					for(var i in g.geometry.points) {
//						var temp = new esri.Graphic(g.geometry.points[i], g.symbol);
//						points.push(temp);
//					}
//					break;
//				case "polyline":
//					lines.push(g);
//					break;
//				case "polygon":
//				case "extent":
//					polygons.push(g);
//					break;
//			}
//		}
//		return {"points":points, "lines":lines, "polygons":polygons};
	}
	function showLoadingInfoWindow(g, content) {
//		map.infoWindow.setTitle("数据正在加载");
//		if(content)map.infoWindow.setContent(content);
//		else map.infoWindow.setContent("<img src='images/loading.gif'>");
//		map.infoWindow.resize(300,300);
//		map.infoWindow.show(g.geometry);
	}
	
	// 传入routeLine.geometry.components网络分析结果几何体中的线的数组，返回一个排好序的点的数组
	function orderRouteResult(lines) {
		var routePints = [];
		var errorIndex = 0;
		var firstPoint;// 上一条线的第一个点
		var lastPoint;// 上一条线的最后一个点
		var vertices = []; //存放点数组的数组
		for(var i=0,len=lines.length; i<len; i++) {
			var points = lines[i].components;// 得到点的数组
			var p1 = points[0];
			var p2 = points[points.length-1];
			if(!firstPoint && !lastPoint) {
				firstPoint = p1;
				lastPoint = p2;
				vertices.push(points);
				continue;
			}
			
			if(p1.x == lastPoint.x && p1.y == lastPoint.y) {
				// 顺序相同，直接添加
				firstPoint = p1;
				lastPoint = p2;
				vertices.push(points);
			}
			else if(p2.x == lastPoint.x && p2.y == lastPoint.y) {
				// 下一条线方向反了，进行反转
				firstPoint = p2;
				lastPoint = p1;
				vertices.push(points.reverse());
			}
			else {
				// 第一条线的方向反了，只存在第二次循环中
				if(p1.x == firstPoint.x && p1.y == firstPoint.y) {
					vertices[errorIndex] = vertices[errorIndex].reverse();// 反转第一条线
					// 顺序相同，直接添加
					firstPoint = p1;
					lastPoint = p2;
					vertices.push(points);
				}
				else if(p2.x == firstPoint.x && p2.y == firstPoint.y) {
					vertices[errorIndex] = vertices[errorIndex].reverse();// 反转第一条线
					// 下一条线方向反了，进行反转
					firstPoint = p2;
					lastPoint = p1;
					vertices.push(points.reverse());
				}
				else {
					// 路网分析时候出错了，直接连接了2点构造的线
					firstPoint = p1;
					lastPoint = p2;
					errorIndex = i;
					vertices.push(points);
				}
			}
		}
		
		// 添加线上的点到集合中，返回点的集合
		for(var i=0,len=vertices.length; i<len; i++) {
			routePints = routePints.concat(vertices[i]);
		}
		return routePints;
	}
	
	return api;
}();