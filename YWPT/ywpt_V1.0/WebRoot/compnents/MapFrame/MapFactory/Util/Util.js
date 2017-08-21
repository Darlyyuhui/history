MapFactory.Define("MapFactory/Util/Util*",["MapFactory/GeometryUtil"],function(geoUtil){
	var api = {
			checkParam: checkParam,//判断传入的参数是否为null或者为undefined，否则返回true
			isSimilarPoint: isSimilarPoint,//是否为同一点
			getPointByX: getPointByX,//根据y计算线上对应的点
			getPointByY: getPointByY,//根据y计算线上对应的点
			isBlongLine: isBlongLine,//计算点是否在线上
			getDirectionByXY: getDirectionByXY,//根据x，y的差值计算方向，返回值为8个方向的英文首字母
			getAngleByXY: getAngleByXY,// 根据x，y的差值计算方向0-360
			getXAngleByXY: getXAngleByXY,// 根据x，y的差值计算弧度值
			cut: cut,// 线的截断操作
			union: union,// geometry的合并操作
			getLayerObj: getLayerObj// 根据指定的graphicLayer或者featureLayer返回包含点线面的对象
		};
	
	var _geoUtil;
	var errorNum = 0.00000000000001;// 设置一个容差值，因为在计算的时候取了PI的近似值
	var exitNum = 0.0000001;// 二分法查找时跳出递归的差值
	
	function checkParam(obj) {
		if(typeof obj == "undefined" || null == obj) {
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
		// 由于经度和维度的最大值不同，使用经度查询时候，经度偏小，经常查不到对应的值(合理的应该是经度和维度使用不同的容差值)
		var dir = getDirectionByXY(line[1][0]-line[0][0], line[1][1]-line[0][1]);
		//var dirAngle = getAngleByXY(line[1][0]-line[0][0], line[1][1]-line[0][1]);
		//if(355 < dirAngle || 5 > dirAngle || (175 < dirAngle && 185 > dirAngle)) {
		if(dir == "W" || dir == "E") {
			var p = getPointByX(line, point[0]);
			if(p && Math.abs(p[1]-point[1]) < exitNum)return true;
		}
		else {
			var p = getPointByY(line, point[1]);
			if(p && Math.abs(p[0]-point[0]) < exitNum)return true;
		}
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
	
	function cut(line, point) {
		var lines = [];
		if(line.type == "multipolyline") {
			var linePoints = line.points.split("|");
			var prePoints = "";
			var lastPoints = "";
			var preGeo = null;
			var lastGeo = null;
			var result, flag=false;
			for(var i=0,len=linePoints.length; i<len; i++) {
				if(flag) {
					// 找到线上的点了
					if(result) {
						prePoints += result.preLine.points + "|";
						lastPoints += result.lastLine.points + "|";
						result = null;
						i--;
					}
					else {
						lastPoints += linePoints[i] + "|";
					}
				}
				else {
					result = cutSingleLine({type:"polyline", points:linePoints[i]}, point);
					if(result.lastLine) {
						flag = true;
					}
					if(!flag) {
						prePoints += linePoints[i] + "|";
					}
				}
			}
			if(prePoints)preGeo = {"type":"multipolyline", "points":prePoints.substr(0, prePoints.length-1)};
			if(lastPoints)lastGeo = {"type":"multipolyline", "points":lastPoints.substr(0, lastPoints.length-1)};
			return {preLine: preGeo, lastLine: lastGeo};
		}
		else {
			cutSingleLine(line, point);
		}
	}
	function cutSingleLine(line, point) {
		line = converLine(line);
		point = converPoint(point);
		var prePaths = [];
		var lastPaths = [];
		var flag = false;
		for(var i=0,len=line.length; i<len; i++) {
			if(!flag) {
				flag = isBlongLine(line[i], point);
				if(flag) {
					// 在当前线上，先获取线上对应的点
					var linepoint = getPointByX(line[i], point[0]);
					if(linepoint) {
						prePaths.push([line[i][0], linepoint]);
						lastPaths.push([linepoint, line[i][1]]);
					}
				}
				else {
					prePaths.push(line[i]);
				}
			}
			else {
				lastPaths.push(line[i]);
			}
		}
		return {preLine: converToLineGeo(prePaths), lastLine: converToLineGeo(lastPaths)};
	}
	
	function union(url,geometries,callback,errback) {
		if(!_geoUtil)_geoUtil = geoUtil();
		_geoUtil.union(url,geometries,callback,errback);
	}
	
	function converToLineGeo(linePaths) {
		var len = linePaths.length;
		if(!len)return null;
		var points = linePaths[0][0].join(",");
		for(var i=0; i<len; i++) {
			points += ","+linePaths[i][1].join(",");
		}
		return {"type": "polyline", "points": points};
	}
	function converLine(line) {
		var result = [];
		var linePoints = line.points.split(",");
		for(var i=0,len=linePoints.length-2; i<len; i+=2) {
			var linePath = [];
			linePath.push([parseFloat(linePoints[i]), parseFloat(linePoints[i+1])]);
			linePath.push([parseFloat(linePoints[i+2]), parseFloat(linePoints[i+3])]);
			result.push(linePath);
		}
		return result;
	}
	
	function converPoint(point) {
		var result = [];
		var points = point.points.split(",");
		return [parseFloat(points[0]), parseFloat(points[1])];
	}
	
	function getLayerObj(layer) {
	/*
		var graphics = layer.graphics;
		var points=[], lines=[], polygons=[];
		for(var i in graphics) {
			var g = graphics[i];
			switch(g.geometry.type) {
				case "point":
					points.push(g);
				break;
				case "multipoint":
					for(var i in g.geometry.points) {
						var temp = new esri.Graphic(g.geometry.points[i], g.symbol);
						points.push(temp);
					}
					break;
				case "polyline":
					lines.push(g);
					break;
				case "polygon":
				case "extent":
					polygons.push(g);
					break;
			}
		}
		return {"points":points, "lines":lines, "polygons":polygons};
		*/
	}
	
	return api;
});