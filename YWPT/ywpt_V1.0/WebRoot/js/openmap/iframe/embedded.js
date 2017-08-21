/**
 内嵌地图父页面的js，用于初始化加载地图
*/
var callback = null;
var ifrObjWindow = null;
var initIframeMap = function(ifrObj, mapLoadCallBack) {
	callback = mapLoadCallBack;
	ifrObjWindow = ifrObj.contentWindow;
	
	//iframe加载完成后执行操作
	bind(ifrObj, "load", dealProgram);
}

/**
 * 定义跨浏览器事件处理程序
 * */
function bind(element, type, handler) {
	if(element.addEventListener) {
		element.addEventListener(type, handler, false);
	} else if (element.attachEvent) {
		element.attachEvent("on" + type, handler);
	} else {
		element["on" + type] = handler;
	}
}

function dealProgram() {
	if (ifrObjWindow && ifrObjWindow.isLoaded) {
		if(callback)callback(ifrObjWindow.EmbeddedMap);
	} else {
		setTimeout(dealProgram, 50);
	}
}
//根据x，y的差值计算方向，返回值为8个方向的英文首字母
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
		if(angle <= 12)result = "E";
		else if(angle >= 78)result = "N";
		else result = "NE";
	}
	else if(x < 0 && y >= 0) {
		if(angle <= 12)result = "W";
		else if(angle >= 78)result = "N";
		else result = "NW";
	}
	else if(x < 0 && y <= 0) {
		if(angle <= 12)result = "W";
		else if(angle >= 78)result = "S";
		else result = "SW";
	}
	else if(x > 0 && y < 0) {
		if(angle <= 12)result = "E";
		else if(angle >= 78)result = "S";
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