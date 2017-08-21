/**
 * 渲染类
 * */

itmap.openlayer.renderer = function(){
	return function() {
		var api = {
				addRouteResultArrow:addRouteResultArrow,//添加方向箭头
				addRouteResult:addRouteResult//添加路径分析结果线
			};
		var util = itmap.openlayer.util;
		// 标注箭头
		function addRouteResultArrow(array, graphicLayer, callback) {
			/**
			 * 判断两点的方向，并判断角度属于8个方向的那一个方向
			 * 记录同一方向的线，在方向改变的时候，添加上一个方向箭头
			// 夹角和8个方向的夹角相减的绝对值小于22.5即认为是一个方向的线，标注为同一个方向箭头
			 * */
			var sumlength=0;// 同方向线的总长度
			var templist = [];
			var result = {};
			var predir = "";
			for(var i=0; i<array.length-1; i++) {
				var first = array[i];
				var second = array[i+1];
				var xl = second[0] - first[0];
				var yl = second[1] - first[1];
				// 如果为重复点，则继续下一次循环
				if(xl == 0 && yl == 0)continue;
				var linelength = Math.sqrt(xl*xl+yl*yl);
				var direction = util.getDirectionByXY(xl, yl);
				if(!predir)predir = direction;
				if(predir != direction) {
					// 方向变了，添加上一方向的线到地图上
					var obj = getResultPoints(templist, predir, sumlength);
					if(obj) {
						if(!util.checkParam(result[obj.direction]))result[obj.direction]=[];
						result[obj.direction]=result[obj.direction].concat(obj.points);
					}
					predir = direction;
					templist = [];
					sumlength = 0;
				}
				sumlength += linelength;
				templist.push([first, second, linelength]);
				// 最后一点的时候，添加templist到地图上
				if((array.length-2) == i) {
					var obj = getResultPoints(templist, predir, sumlength);
					if(obj) {
						if(!util.checkParam(result[obj.direction]))result[obj.direction]=[];
						result[obj.direction]=result[obj.direction].concat(obj.points);
					}
				}
			}
			//判断该图层对象是否为空或者未定义---------胡红勋修改开始-----------------
			if(util.checkParam(graphicLayer)) {
				var gs=[];
				for(var k in result) {
					var points=[];					
					for(var m in result[k]) {
						var p = result[k][m];
						var point=new OpenLayers.Geometry.Point(parseFloat(p[0]),parseFloat(p[1]));	
						points.push(point);
						}
					
					var mp=new OpenLayers.Geometry.MultiPoint(points);				
					var symbol=new OpenLayers.Symbolizer.Point({
						externalGraphic:"images/map/arrow/"+k.toLowerCase()+".png",
						graphicWidth:16,
						graphicHeight:16					
					});
					var g=new OpenLayers.Feature.Vector(mp,null,symbol);
					gs.push(g);
				}
				graphicLayer.addFeatures(gs);
				if(callback)callback(gs);
			}
			//胡红勋修改----------------------------------------------------
		}
		
		function getResultPoints(list, direction, sumlength) {
			if(!list || list.length<1)return null;// 如果list为空，或者长度小于1则，不存在方向
			/**
			 * 根据此方向的总长度，计算出添加箭头的x坐标，在根据x坐标计算出对应的坐标
			 * 如果方向为南北方向则根据y轴距离求x坐标
			 * 如果方向为东西方向则根据x轴距离求y坐标
			 * */
			var maxlength=0.01;// 添加箭头的最大地图距离，大于这个距离将添加多个箭头
			var minlength=0.0015;// 添加箭头的最小地图距离，小于这个距离则不添加箭头
			if(sumlength < minlength)return null;// 线太短了，就不加箭头
			
			// 计算上一方向上需要添加方向箭头的个数
			var dnum = Math.round(sumlength/maxlength);
			if(dnum == 0)dnum = 1;// 如果线的长度小于maxlength则添加一个箭头
			var ylist = [];
			var xlist = [];
			if(direction == "N" || direction == "S") {
				// 在y轴上，根据y轴距离差，确定添加箭头的y轴坐标点集合
				var ylength = list[list.length-1][1][1] - list[0][0][1];
				var itemlength = ylength/dnum;
				ylist.push(list[0][0][1]+itemlength/2);
				for(var i=1; i<dnum; i++) {
					ylist.push(ylist[i-1]+itemlength);
				}
			}
			else {
				// 其他情况使用x轴的距离差，确定添加箭头的x坐标点集合
				// 获取x轴的长度
				var xlength = list[list.length-1][1][0] - list[0][0][0];
				var itemlength = xlength/dnum;
				xlist.push(list[0][0][0]+itemlength/2);
				for(var i=1; i<dnum; i++) {
					xlist.push(xlist[i-1]+itemlength);
				}
			}
			
			var points = [];
			var tempsum = 0;
			var di = 1;
			var index = 0;
			// 判断对应的坐标点对应那一条线，并计算出具体坐标，添加到地图上
			for(var j=0; j<list.length; j++) {
				// 先判断方向，在判断x坐标是否在此线上，如果在则求出对应的y坐标
				var yl = list[j][1][1] - list[j][0][1];
				var xl = list[j][1][0] - list[j][0][0];
				// 计算xy的坐标
				var x,y;
				var point;
				if(direction == "N" || direction == "S") {
					point = util.getPointByY(list[j], ylist[index]);
				}
				else {
					point = util.getPointByX(list[j], xlist[index]);
				}
				if(point) {
					points.push(point);
					index++;
					j--;
				}
				if(index != 0 && (index == xlist.length || index == ylist.length))break;
			}
			return {points:points, direction:direction};
		}
		
		// 添加路径分析结果到地图上，按照经过道路的次数的不同，用颜色和线宽进行渲染
		// list为分析结果线的点的集合[[x1,y1],[x2,y2]]    graphicLayer添加到地图上的graphiclayer
		function addRouteResult(array, graphicLayer) {
			// 将list点集合转换为线[[x1,y1],[x2,y2]]集合
			var list = [];
			for(var i=0; i<array.length-1; i++) {
				if(array[i][0] == array[i+1][0] && array[i][1] == array[i+1][1])continue;
				list.push([array[i],array[i+1]]);
			}
			
			var result = [];
			var templist = [];
			var flag = false;
			var maxNum = 1;
			var first;
			var second;
			var direction;
			for(var i=0; i<list.length; i++) {
				first = list[i][0];
				second = list[i][1];
				// 剔除相邻的相同点
				if(first[0] == second[0] && first[1] == second[1])continue;
				direction = util.getAngleByXY(second[0] - first[0], second[1] - first[1]);
				flag = true;
				var leftline;// 被截断的左侧的线
				var rightline;// 被截断的右侧的线
				for(var j=0; j<result.length; j++) {
					// 先判断是否为同一方向
					if(!result[j])continue;// 如果result中对应的元素为空，继续下一次循环
					if(Math.abs(direction - result[j][2]) != 0 && 
							Math.abs(direction - result[j][2]) != 180)continue;
					
					var temp1 = result[j][0][0];
					var temp2 = result[j][0][1];
					// 判断是否为同一线
					if((first[0] == temp1[0] && first[1] == temp1[1] && 
							second[0] == temp2[0] && second[1] == temp2[1]) || 
							(second[0] == temp1[0] && second[1] == temp1[1] && 
									first[0] == temp2[0] && first[1] == temp2[1])) {
						result[j][1] += 1;// 更新重复次数
						maxNum = Math.max(result[j][1], maxNum);
						flag = false;
						break;
					}
					else {
						if(util.isBlongLine(result[j][0], first)) {
							flag = false;
							// 点first在线上
							if(util.isBlongLine(result[j][0], second)) {
								// 点first,second在线上，截断result对应的线，并更新重复部分的个数
								if((result[j][2] == 0 && first[0] > second[0]) || (result[j][2] == 180 && first[0] < second[0])) {
									// 第一点在第二点右侧
									if(!util.isSimilarPoint(second, result[j][0][0]))result.push([[second, result[j][0][0]], result[j][1], direction]);
									if(!util.isSimilarPoint(result[j][0][1], first))result.push([[result[j][0][1], first], result[j][1], direction]);
								}
								else if((result[j][2] == 0 && first[0] < second[0]) || (result[j][2] == 180 && first[0] > second[0])){
									if(!util.isSimilarPoint(result[j][0][0], first))result.push([[result[j][0][0], first], result[j][1], direction]);
									if(!util.isSimilarPoint(second, result[j][0][1]))result.push([[second, result[j][0][1]], result[j][1], direction]);
								}
								else if(result[j][2] < 180) {
									if(Math.max(first[1], second[1]) == first[1]) {
										// 第一点在第二点之上
										if(!util.isSimilarPoint(second, result[j][0][0]))result.push([[second, result[j][0][0]], result[j][1], direction]);
										if(!util.isSimilarPoint(result[j][0][1], first))result.push([[result[j][0][1], first], result[j][1], direction]);
									}
									else {
										if(!util.isSimilarPoint(result[j][0][0], first))result.push([[result[j][0][0], first], result[j][1], direction]);
										if(!util.isSimilarPoint(second, result[j][0][1]))result.push([[second, result[j][0][1]], result[j][1], direction]);
									}
								}
								else {
									if(Math.max(first[1], second[1]) == first[1]) {
										// 第一点在第二点之上
										if(!util.isSimilarPoint(result[j][0][0], first))result.push([[result[j][0][0], first], result[j][1], direction]);
										if(!util.isSimilarPoint(second, result[j][0][1]))result.push([[second, result[j][0][1]], result[j][1], direction]);
									}
									else {
										if(!util.isSimilarPoint(second, result[j][0][0]))result.push([[second, result[j][0][0]], result[j][1], direction]);
										if(!util.isSimilarPoint(result[j][0][1], first))result.push([[result[j][0][1], first], result[j][1], direction]);
									}
								}
								result.push([[first, second], result[j][1]+1, direction]);
								maxNum = Math.max(result[j][1]+1, maxNum);
								result[j] = null;
								break;
							}
							else {
								if((result[j][2] == 0 && first[0] > second[0]) || (result[j][2] == 180 && first[0] < second[0])) {
									// 第一点在第二点右侧
									if(!util.isSimilarPoint(second, result[j][0][0]))result.push([[second, result[j][0][0]], result[j][1], direction]);
									if(!util.isSimilarPoint(result[j][0][1], first))result.push([[result[j][0][1], first], result[j][1], direction]);
								}
								else if((result[j][2] == 0 && first[0] < second[0]) || (result[j][2] == 180 && first[0] > second[0])){
									if(!util.isSimilarPoint(result[j][0][0], first))result.push([[result[j][0][0], first], result[j][1], direction]);
									if(!util.isSimilarPoint(second, result[j][0][1]))result.push([[second, result[j][0][1]], result[j][1], direction]);
								}
								else if(result[j][2] < 180) {
									if(Math.max(first[1], second[1]) == first[1]) {
										// 添加判断，是否为端点相交
										if(util.isSimilarPoint(first, result[j][0][0])) {
											// 跳过，并设置flag=true
											flag = true;
											continue;
										}
										// 循环完后在存，如果截断的线也存在则只需要更新重复个数
										result.push([[first, result[j][0][0]], result[j][1]+1, direction]);
										if(!util.isSimilarPoint(result[j][0][1], first))result.push([[result[j][0][1], first], result[j][1], direction]);
										list.push([second, result[j][0][0]]);
									}
									else {
										if(util.isSimilarPoint(first, result[j][0][1])) {
											flag = true;
											continue;
										}
										result.push([[first, result[j][0][1]], result[j][1]+1, direction]);
										if(!util.isSimilarPoint(result[j][0][0], first))result.push([[result[j][0][0], first], result[j][1], direction]);
										// 将second和result[j][0][1]添加到list中
										list.push([second, result[j][0][1]]);
									}
									maxNum = Math.max(result[j][1]+1, maxNum);
									result[j] = null;
								}
								else {
									if(Math.max(first[1], second[1]) == first[1]) {
										if(util.isSimilarPoint(first, result[j][0][1])) {
											flag = true;
											continue;
										}
										result.push([[first, result[j][0][1]], result[j][1]+1, direction]);
										if(!util.isSimilarPoint(result[j][0][0], first))result.push([[result[j][0][0], first], result[j][1], direction]);
										list.push([second, result[j][0][1]]);
									}
									else {
										if(util.isSimilarPoint(first, result[j][0][0])) {
											flag = true;
											continue;
										}
										// 循环完后在存，如果截断的线也存在则只需要更新重复个数
										result.push([[first, result[j][0][0]], result[j][1]+1, direction]);
										if(!util.isSimilarPoint(result[j][0][1], first))result.push([[result[j][0][1], first], result[j][1], direction]);
										list.push([second, result[j][0][0]]);
									}
									maxNum = Math.max(result[j][1]+1, maxNum);
									result[j] = null;
								}
							}
							break;
						}
						else if(util.isBlongLine(result[j][0], second)) {
							flag = false;
							// 点second在线上
							if((result[j][2] == 0 && first[0] > second[0]) || (result[j][2] == 180 && first[0] < second[0])) {
								// 第一点在第二点右侧
								if(!util.isSimilarPoint(second, result[j][0][0]))result.push([[second, result[j][0][0]], result[j][1], direction]);
								if(!util.isSimilarPoint(result[j][0][1], first))result.push([[result[j][0][1], first], result[j][1], direction]);
							}
							else if((result[j][2] == 0 && first[0] < second[0]) || (result[j][2] == 180 && first[0] > second[0])){
								if(!util.isSimilarPoint(result[j][0][0], first))result.push([[result[j][0][0], first], result[j][1], direction]);
								if(!util.isSimilarPoint(second, result[j][0][1]))result.push([[second, result[j][0][1]], result[j][1], direction]);
							}
							else if(result[j][2] < 180) {
								if(Math.max(first[1], second[1]) == first[1]) {
									if(util.isSimilarPoint(second, result[j][0][1])) {
										flag = true;
										continue;
									}
									// 循环完后在存，如果截断的线也存在则只需要更新重复个数
									result.push([[result[j][0][1], second], result[j][1]+1, direction]);
									if(!util.isSimilarPoint(second, result[j][0][0]))result.push([[second, result[j][0][0]], result[j][1], direction]);
									list.push([first, result[j][0][1]]);
								}
								else {
									if(util.isSimilarPoint(second, result[j][0][0])) {
										flag = true;
										continue;
									}
									result.push([[result[j][0][0], second], result[j][1]+1, direction]);
									if(!util.isSimilarPoint(second, result[j][0][1]))result.push([[second, result[j][0][1]], result[j][1], direction]);
									list.push([first, result[j][0][0]]);
								}
								maxNum = Math.max(result[j][1]+1, maxNum);
								result[j] = null;
							}
							else {
								if(Math.max(first[1], second[1]) == first[1]) {
									if(util.isSimilarPoint(second, result[j][0][0])) {
										flag = true;
										continue;
									}
									// 循环完后在存，如果截断的线也存在则只需要更新重复个数
									result.push([[result[j][0][0], second], result[j][1]+1, direction]);
									if(!util.isSimilarPoint(second, result[j][0][1]))result.push([[second, result[j][0][1]], result[j][1], direction]);
									list.push([first, result[j][0][0]]);
								}
								else {
									if(util.isSimilarPoint(second, result[j][0][1])) {
										flag = true;
										continue;
									}
									result.push([[result[j][0][1], second], result[j][1]+1, direction]);
									if(!util.isSimilarPoint(second, result[j][0][0]))result.push([[second, result[j][0][0]], result[j][1], direction]);
									list.push([first, result[j][0][1]]);
								}
								maxNum = Math.max(result[j][1]+1, maxNum);
								result[j] = null;
							}
							break;
						}
						else {
							// 线在first,second两点之间
							// 这里添加2条线到list中，并跳出，不做任何处理
							if(util.isBlongLine([first,second], result[j][0][0]) && 
									util.isBlongLine([first,second], result[j][0][1])) {
								flag = false;
								result[j][1] += 1;// 更新重复数量
								maxNum = Math.max(result[j][1], maxNum);
								// 添加截断的两条线到list并跳出
								if((result[j][2] == 0 && first[0] > second[0]) || (result[j][2] == 180 && first[0] < second[0])) {
									// 第一点在第二点右侧
									if(!util.isSimilarPoint(result[j][0][0], second))list.push([result[j][0][0], second]);
									if(!util.isSimilarPoint(result[j][0][1], first))list.push([result[j][0][1], first]);
								}
								else if((result[j][2] == 0 && first[0] < second[0]) || (result[j][2] == 180 && first[0] > second[0])){
									if(!util.isSimilarPoint(result[j][0][1], second))list.push([result[j][0][1], second]);
									if(!util.isSimilarPoint(result[j][0][0], first))list.push([result[j][0][0], first]);
								}
								else if(result[j][2] < 180) {
									if(Math.max(first[1], second[1]) == first[1]) {
										if(!util.isSimilarPoint(result[j][0][0], second))list.push([result[j][0][0], second]);
										if(!util.isSimilarPoint(result[j][0][1], first))list.push([result[j][0][1], first]);
									}
									else {
										if(!util.isSimilarPoint(result[j][0][1], second))list.push([result[j][0][1], second]);
										if(!util.isSimilarPoint(result[j][0][0], first))list.push([result[j][0][0], first]);
									}
								}
								else {
									if(Math.max(first[1], second[1]) == first[1]) {
										if(!util.isSimilarPoint(result[j][0][1], second))list.push([result[j][0][1], second]);
										if(!util.isSimilarPoint(result[j][0][0], first))list.push([result[j][0][0], first]);
									}
									else {
										if(!util.isSimilarPoint(result[j][0][0], second))list.push([result[j][0][0], second]);
										if(!util.isSimilarPoint(result[j][0][1], first))list.push([result[j][0][1], first]);
									}
								}
								break;
							}
							else {
								flag = true;
							}
						}
					}
				}
				if(flag)result.push([[first, second],1, direction]);
			}
			
			
			//胡红勋修改开始------------------------------
			if(util.checkParam(graphicLayer)) {
				var level = Math.ceil(maxNum/3);// 每一级别中最多有几个子等级
				for(var i=0; i<result.length; i++) {
					if(!result[i])continue;
					var paths=[];
					for(var j in result[i][0]){				
						var path=new OpenLayers.Geometry.Point(parseFloat(result[i][0][j][0]),parseFloat(result[i][0][j][1]));
						    paths.push(path);						
					}
					var line=new OpenLayers.Geometry.LineString(paths);
					var g = new OpenLayers.Feature.Vector(line);
					var symbol;
					var linecolor;
					var linewidth;
					if(result[i][1] <= maxNum/3) {
						linecolor ="8e8eea";
						if(result[i][1] <= level/3) {
							linewidth = 2;
						}
						else if(result[i][1] <= level/3*2) {
							linewidth = 4;
						}
						else {
							linewidth = 6;
						}
					}
					else if(result[i][1] <= maxNum/3*2) {

						linecolor ="6666aa";
						if(result[i][1] <= (level/3+maxNum/3)) {
							linewidth = 2;
						}
						else if(result[i][1] <= (level/3*2+maxNum/3)) {
							linewidth = 4;
						}
						else {
							linewidth= 6;
						}
					}
					else{
						linecolor ="383860";
						if(result[i][1] <= (level/3+maxNum/3*2)) {
							linewidth= 2;
						}
						else if(result[i][1] <= (level/3*2+maxNum/3*2)) {
							linewidth = 4;
						}
						else {
							linewidth = 6;
						}
					}
					symbol =OpenLayers.Symbolizer.Line({strokeWidth:linewidth,strokeColor:linecolor });
					g.style=symbol;
					graphicLayer.addFeatures([g]);
				}
			}
			return result;
		}
		
		return api;
	}
}();