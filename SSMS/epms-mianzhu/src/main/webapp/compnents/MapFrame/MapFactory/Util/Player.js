MapFactory.Define("MapFactory/Util/Player*",[
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*",
	"MapFactory/GraphicManager",
	"MapFactory/Util/Util*"
], function(mapFun, layerFun, geoFun, geoType, graphicFun, Util){
	return function(list) {
		var map = mapFun();
		var _layerId = "tempPlayerLayer";
		var _layer = layerFun(_layerId);
		
		var api = {
			play: play,
			pause: pause,
			stop: stop,
			setspeed: setspeed, 
			destroy: destroy
		};
		
		var i=0;
		var movelength=0.0002;//地图上的移动距离,设置速度的时候更改距离的大小
		var timerobj = null;
		
		function destroy() {
			if(timerobj)clearInterval(timerobj);
			setTimeout(function(){
				_layer.clear();
			}, 100);
		}
		function setspeed(value) {
			if(value == "U")movelength *= 2;
			if(value == "D")movelength /= 2;
			return api;
		}
		function play() {
			if(i == list.length-1) stop();
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
				currentPoint = list[0];
				
				_layer.clear();
				var point = new geoFun({type: geoType.POINT, points: currentPoint[0]+","+currentPoint[1]});
				var gm = graphicFun({geo:point, symbol:{url:"images/map/car/c_e.png", width: 32, height:32}});
				gm.addToLayer(_layerId);
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
		var currentPoint = list[0];
		_layer.clear();
		var point = new geoFun({type: geoType.POINT, points: currentPoint[0]+","+currentPoint[1]});
		var gm = graphicFun({geo:point, symbol:{url:"images/map/car/c_e.png", width: 32, height:32}});
		setTimeout(function(){
				gm.addToLayer(_layerId);
			}, 100);
		
		function runcar() {
			if(2 > list.length) {
				clearInterval(timerobj);
				return;
			}
			var cx = parseFloat(currentPoint[0]);
			var cy = parseFloat(currentPoint[1]);
			if(isNaN(cx) || isNaN(cy)) {
				i += 1;
				if((i+1) < list.length) {
					currentPoint = list[i];
				}
				else {
					clearInterval(timerobj);
				}
				return;
			}
			var nx = parseFloat(list[i+1][0]);
			var ny = parseFloat(list[i+1][1]);
			//if(typeof console != 'undefined' && console.log)console.log("当前坐标："+cx+"\t"+cy);
			while(cx == nx && cy == ny) {
				i += 1;
				if(i == list.length-1) {
					// 如果最后2点重复会出现这种情况
					clearInterval(timerobj);
					return;
				}
				nx = parseFloat(list[i+1][0]);
				ny = parseFloat(list[i+1][1]);
			}
			// 两点间的角度
			var cnAngle = Util.getAngleByXY(nx-cx, ny-cy);
			var my = Math.abs(Math.sin(Util.getXAngleByXY(nx-cx, ny-cy))*movelength);
			var mx = Math.abs(Math.cos(Util.getXAngleByXY(nx-cx, ny-cy))*movelength);
			var carSymbol, dirction="e";
			
			if(cnAngle < 10) {
				dirction = "e";
			}
			else if(cnAngle < 55) {
				dirction = "ne";
			}
			else if(cnAngle < 100) {
				dirction = "n";
			}
			else if(cnAngle < 145) {
				dirction = "nw";
			}
			else if(cnAngle < 190) {
				dirction = "w";
			}
			else if(cnAngle < 235) {
				dirction = "sw";
			}
			else if(cnAngle < 280) {
				dirction = "s";
			}
			else if(cnAngle < 315) {
				dirction = "se";
			}
			else {
				dirction = "e";
			}
			carSymbol = {url:"images/map/car/c_"+dirction+".png", width: 32, height:32};
			// 判断是否移动到或超出了下一点
			if(cnAngle >= 270) {
				if(cy < ny || ny > cy-my) {
					currentPoint = [nx, ny];
					i += 1;
				}
				else {
					currentPoint = [cx+mx, cy-my];
				}
			}
			else if(cnAngle >= 180) {
				if(cx < nx || nx > cx-mx) {
					currentPoint = [nx, ny];
					i += 1;
				}
				else {
					currentPoint = [cx-mx, cy-my];
				}
			}
			else if(cnAngle >= 90) {
				if(cy > ny || ny < cy+my) {
					currentPoint = [nx, ny];
					i += 1;
				}
				else {
					currentPoint = [cx-mx, cy+my];
				}
			}
			else {
				if(cx > nx || nx < cx+mx) {
					currentPoint = [nx, ny];
					i += 1;
				}
				else {
					currentPoint = [cx+mx, cy+my];
				}
			}
			
			var point = new geoFun({type: geoType.POINT, points: currentPoint[0]+","+currentPoint[1]});
			var gm = graphicFun({geo:point, symbol:carSymbol});
			
			if(!map.isInCurrentExtent(currentPoint[0], currentPoint[1])) {
				pause();
				map.centerAt(currentPoint[0], currentPoint[1]);
				setTimeout(function(){
					play();
				}, 500);
			}
			
			if(i < list.length) {
				_layer.clear();
				gm.addToLayer(_layerId);
				if(i == list.length-1) {
					clearInterval(timerobj);
				}
			}
			else {
				clearInterval(timerobj);
			}
		}
		
		return api;
	}
});