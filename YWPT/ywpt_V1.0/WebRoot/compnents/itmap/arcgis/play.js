/**
 * 轨迹回放播放类
 */

itmap.arcgis.play = function(list, car){
	var api = {
			play:play,
			pause:pause,
			stop:stop,
			setspeed:setspeed
		};
	var util = itmap.arcgis.util;
	var symbol = itmap.arcgis.symbol;
	
	var i=0;
	var movelength=0.0008;//地图上的移动距离,设置速度的时候更改距离的大小
	var timerobj = null;
	
	function setspeed(value) {
		if(value == "U")movelength *= 2;
		if(value == "D")movelength /= 2;
		return api;
	}
	function play() {
		timerobj = setInterval(runcar, 50);
		return api;
	}
	function pause() {
		// 取消定时器
		if(timerobj)clearInterval(timerobj);
		return api;
	}
	function stop() {
		// 取消定时器
		if(timerobj) {
			clearInterval(timerobj);
			var firstp = list[0];
			car.setGeometry(new esri.geometry.Point(firstp[0],firstp[1],map.spatialReference));
			currentPoint = null;
			i = 0;
		}
		return api;
	}
	/**
	 * 思路：每次移动固定距离，
	 * 首先，计算出当前点和下一点的方向
	 * 判断，每次移动坐标时候判断，是否超过下一点，如果超过，则设置为下一点，
	 * 如果当前点等于下一点，则设置下一点为下下一点，当前点为下一点
	 * 循环以上
	 * 
	 * */
	var currentPoint;
	function runcar() {
		var graphic = car;
		
		if(!currentPoint)currentPoint = new esri.geometry.Point(list[i][0],list[i][1],map.spatialReference);
		var cx = parseFloat(currentPoint.x);
		var cy = parseFloat(currentPoint.y);
		var nx = parseFloat(list[i+1][0]);
		var ny = parseFloat(list[i+1][1]);
		if(isNaN(cx) || isNaN(cy)) {
			i += 1;
			currentPoint = new esri.geometry.Point(list[i+1][0],list[i+1][1],map.spatialReference);
			return;
		}
		//if(typeof console != 'undefined' && console.log)console.log("当前坐标："+cx+"\t"+cy);
		while(cx == nx && cy == ny) {
			i += 1;
			nx = parseFloat(list[i+1][0]);
			ny = parseFloat(list[i+1][1]);
		}
		// 两点间的角度
		var cnAngle = util.getAngleByXY(nx-cx, ny-cy);
		var my = Math.abs(Math.sin(util.getXAngleByXY(nx-cx, ny-cy))*movelength);
		var mx = Math.abs(Math.cos(util.getXAngleByXY(nx-cx, ny-cy))*movelength);
		if(cnAngle < 10) {
			graphic.setSymbol(symbol.runcarSymbol("e"));
		}
		else if(cnAngle < 55) {
			graphic.setSymbol(symbol.runcarSymbol("ne"));
		}
		else if(cnAngle < 100) {
			graphic.setSymbol(symbol.runcarSymbol("n"));
		}
		else if(cnAngle < 145) {
			graphic.setSymbol(symbol.runcarSymbol("nw"));
		}
		else if(cnAngle < 190) {
			graphic.setSymbol(symbol.runcarSymbol("w"));
		}
		else if(cnAngle < 235) {
			graphic.setSymbol(symbol.runcarSymbol("sw"));
		}
		else if(cnAngle < 280) {
			graphic.setSymbol(symbol.runcarSymbol("s"));
		}
		else if(cnAngle < 315) {
			graphic.setSymbol(symbol.runcarSymbol("se"));
		}
		else {
			graphic.setSymbol(symbol.runcarSymbol("e"));
		}
		// 判断是否移动到或超出了下一点
		if(cnAngle >= 270) {
			if(cy < ny || ny > cy-my) {
				currentPoint = new esri.geometry.Point(nx, ny, map.spatialReference);
				i += 1;
			}
			else {
				currentPoint = new esri.geometry.Point(cx+mx, cy-my, map.spatialReference);
			}
		}
		else if(cnAngle >= 180) {
			if(cx < nx || nx > cx-mx) {
				currentPoint = new esri.geometry.Point(nx, ny, map.spatialReference);
				i += 1;
			}
			else {
				currentPoint = new esri.geometry.Point(cx-mx, cy-my, map.spatialReference);
			}
		}
		else if(cnAngle >= 90) {
			if(cy > ny || ny < cy+my) {
				currentPoint = new esri.geometry.Point(nx, ny, map.spatialReference);
				i += 1;
			}
			else {
				currentPoint = new esri.geometry.Point(cx-mx, cy+my, map.spatialReference);
			}
		}
		else {
			if(cx > nx || nx < cx+mx) {
				currentPoint = new esri.geometry.Point(nx, ny, map.spatialReference);
				i += 1;
			}
			else {
				currentPoint = new esri.geometry.Point(cx+mx, cy+my, map.spatialReference);
			}
		}
		
		if(!map.extent.contains(currentPoint)) {
			var mechandler = dojo.connect(map, "onExtentChange", function(extent,delta,levelChange,lod){
				dojo.disconnect(mechandler);
				play();
			});
			pause();
			map.centerAt(currentPoint);
		}
		
		if(i < list.length) {
			graphic.setGeometry(currentPoint);
			if(i == list.length-1) {
				clearInterval(timerobj);
			}
		}
		else {
			clearInterval(timerobj);
		}
	}
	
	return api;
};