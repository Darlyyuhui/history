MapFactory.Define("MapFactory/Util/Graph*",[
    "MapFactory/MapManager",
    "MapFactory/Geometry*",
    "MapFactory/GeometryType*"
],function(MapManager,Geometry,GeometryType){
	return function(){

		var api = {
			draw : draw,
			onDrawEnd : onDrawEnd,
			onDrawComplete : onDrawComplete,
			TREND_ARROW : "TREND_ARROW",
			TREND_ARROW_WITH_CURVE : "TREND_ARROW_WITH_CURVE"
		};

		/**
		 * 图形类型
		 */
		var graphType = {
			"TREND_ARROW" : _trendArrow,
			"TREND_ARROW_WITH_CURVE" : _trendArrow2
		};

		var mouseDownHandler = null, // 鼠标按下事件句柄
			mouseDragHandler = null, // 鼠标拖拽事件句柄
			mouseUpHandler = null, // 鼠标松开事件句柄
			startPoint,endPoint, // 起始点和结束点
			onDrawEndFunc = null, // 拖拽绘制完成回调函数
			onDrawCompleteFunc = null, // 绘制结束回调函数
			currentGraphType; // 目前所要绘制的图形类型

		var _mapManager = MapManager();

		/**
		 * 图形绘画
		 * @param type 传入要绘的类型
		 */
		function draw(type){
			currentGraphType = type;
			_disableMapAction();
			_mapManager.setMouseDownEvent(_mouseDownEvt);
			return api;
		}

		/**
		 * 拖拽过程中绘制完成触发函数
		 * @param func 传入回调函数
		 */
		function onDrawEnd(func){
			onDrawEndFunc = func;
			return api;
		}

		/**
		 * 绘制完成触发函数
		 * @param func 传入回调函数 
		 */
		function onDrawComplete(func){
			onDrawCompleteFunc = func;
			return api;
		}

		/**
		 * 屏蔽地图行为
		 */
		function _disableMapAction(){
			_mapManager.disablePan();
		}

		/**
		 * 恢复被屏蔽的地图行为
		 */
		function _enableMapAction(){
			_mapManager.enablePan();
		}

		/**
		 * 鼠标按下事件触发函数
		 * @param event 事件对象
		 */
		function _mouseDownEvt(event){
			var geometry = null;
			startPoint = event.mapPoint;

			_mapManager.setMouseDragEvent({
				dragmove : _mouseDragEvt,
				dragend : _mouseDragEndEvt
			});

			_mapManager.setMouseUpEvent(_mouseUpEvt);

			/**
			 * 鼠标拖拽事件触发函数
			 * @param event 事件对象
			 */
			function _mouseDragEvt(event){
				endPoint = event.mapPoint;
				if(currentGraphType && graphType[currentGraphType]){
					geometry = graphType[currentGraphType]();
				}

				if(onDrawEndFunc){
					onDrawEndFunc(geometry);
				}
			}

			function _mouseDragEndEvt(event){
				endPoint = event.mapPoint;
				/*if(onDrawEndFunc){
					onDrawEndFunc(geometry);
				}*/
			}

			/**
			 * 鼠标松开事件触发函数
			 * @param event 事件对象
			 */
			function _mouseUpEvt(event){
				endPoint = event.mapPoint;
				_enableMapAction();

				_mapManager.removeMouseDownEvent();
				_mapManager.removeMouseMoveEvent();
				_mapManager.removeMouseUpEvent();
				_mapManager.removeMouseDragEvent();

				if(onDrawCompleteFunc){
					onDrawCompleteFunc(geometry);
				}
			}
		}

		/**
		 * 计算由两点构成直线的斜率
		 * @param startP 起始点
		 * @param endP 结束点
		 */
		function _caculateGradient(startP,endP){
			var _sPoints = startP.points.split(","),
				_ePoints = endP.points.split(",");
			return (parseFloat(_ePoints[1])-parseFloat(_sPoints[1]))/(parseFloat(_ePoints[0])-parseFloat(_sPoints[0]));
		}

		/**
		 * 根据三个点构建贝塞尔曲线
		 * @param startP 起始点
		 * @param middleP 中间控制点
		 * @param endP 终点
		 */
		function _beizerCurve(startP,middleP,endP){
			var x,y,t,tempT,path=[];
			for(tempT=0;tempT<=10;tempT++){
				t = tempT/10;
				x = Math.pow(1 - t,2) * parseFloat(startP.x) + 2 * t *(1 - t) * parseFloat(middleP.x) + Math.pow(t,2) * parseFloat(endP.x);
				y = Math.pow(1 - t,2) * parseFloat(startP.y) + 2 * t *(1 - t) * parseFloat(middleP.y) + Math.pow(t,2) * parseFloat(endP.y);
				path.push([x,y]);
			}
			return path;
		}

		/**
		 * 趋势箭头
		 * @return geometry 返回面状geometry
		 */
		function _trendArrow(){
			var geometry,
				// 图形各边与拖拽出的直线的夹角
				angle1 = Math.PI / 2,
				angle2 = Math.PI / 3,
				angle3 = Math.PI / 4,
				angle4 = Math.PI / 5,
				angle5 = Math.PI / 6,
				// 鼠标绘制与水平夹角
				axisAngle,
				// 各个顶点对象
				point1 = {},point2 = {},point3 = {},point4 = {},point5 = {},point6 = {},
				point_1,point_2,point_3,point_4,point_5,point_6,
				// 各顶点横纵坐标偏移量
				offsetX1,offsetY1, 
				offsetX2,offsetY2,
				offsetX3,offsetY3,
				offsetX4,offsetY4,
				offsetX5,offsetY5,
				offsetX6,offsetY6,
				// 鼠标拖出的直线长度
				lineLength,
				// 图形各边边长
				edge1,edge2,edge3,edge4,edge5,edge6,
				startX,startY,endX,endY;
			var _sPoints = startPoint.points.split(","),
				_ePoints = endPoint.points.split(",");

			startX = parseFloat(_sPoints[0]);
			startY = parseFloat(_sPoints[1]);
			endX = parseFloat(_ePoints[0]);
			endY = parseFloat(_ePoints[1]);

			// 计算鼠标拉出的距离
			lineLength = Math.sqrt(Math.pow(endY - startY,2) + Math.pow(endX - startX,2));
			axisAngle = Math.atan(_caculateGradient(startPoint,endPoint));

			// 计算箭头的各个边长
			edge1 = lineLength / 4;
			edge2 = lineLength / 4;
			edge3 = lineLength / 8;
			edge4 = lineLength / 5;
			edge5 = lineLength / 5;
			edge6 = lineLength / 8;

			if(axisAngle > 0){
				offsetX1 = edge1 * Math.cos(angle2 + axisAngle);
				offsetY1 = edge1 * Math.sin(angle2 + axisAngle);

				offsetX2 = edge2 * Math.cos(angle2 - axisAngle);
				offsetY2 = edge2 * Math.sin(angle2 - axisAngle);

				offsetX3 = edge3 * Math.cos(angle3 + axisAngle);
				offsetY3 = edge3 * Math.sin(angle4 + axisAngle);

				offsetX4 = edge4 * Math.cos(angle2 + axisAngle);
				offsetY4 = edge4 * Math.sin(angle2 + axisAngle);

				offsetX5 = edge5 * Math.cos(angle2 - axisAngle);
				offsetY5 = edge5 * Math.sin(angle2 - axisAngle);

				offsetX6 = edge6 * Math.cos(angle3 - axisAngle);
				offsetY6 = edge6 * Math.sin(angle4 - axisAngle);
			}else{
				offsetX1 = edge1 * Math.cos(angle2 - axisAngle);
				offsetY1 = edge1 * Math.sin(angle2 - axisAngle);

				offsetX2 = edge2 * Math.cos(angle2 + axisAngle);
				offsetY2 = edge2 * Math.sin(angle2 + axisAngle);

				offsetX3 = edge3 * Math.cos(angle3 - axisAngle);
				offsetY3 = edge3 * Math.sin(angle4 - axisAngle);

				offsetX4 = edge4 * Math.cos(angle2 - axisAngle);
				offsetY4 = edge4 * Math.sin(angle2 - axisAngle);

				offsetX5 = edge5 * Math.cos(angle2 + axisAngle);
				offsetY5 = edge5 * Math.sin(angle2 + axisAngle);

				offsetX6 = edge6 * Math.cos(angle3 + axisAngle);
				offsetY6 = edge6 * Math.sin(angle4 + axisAngle);
			}

			if(endX > startX){
				point1.x = startX - offsetX1;
				point2.x = startX - offsetX2;
				point3.x = endX - offsetX3;
				point4.x = endX - offsetX4;
				point5.x = endX - offsetX5;
				point6.x = endX - offsetX6;
			}else{
				point1.x = startX + offsetX1;
				point2.x = startX + offsetX2;
				point3.x = endX + offsetX3;
				point4.x = endX + offsetX4;
				point5.x = endX + offsetX5;
				point6.x = endX + offsetX6;
			}

			if(endY > startY){
				point1.y = startY - offsetY1;
				point2.y = startY + offsetY2;
				point3.y = endY - offsetY3;
				point4.y = endY - offsetY4;
				point5.y = endY + offsetY5;
				point6.y = endY + offsetY6;
			}else{
				point1.y = startY + offsetY1;
				point2.y = startY - offsetY2;
				point3.y = endY + offsetY3;
				point4.y = endY + offsetY4;
				point5.y = endY - offsetY5;
				point6.y = endY - offsetY6;
			}

			geometry = new Geometry({
				type : GeometryType.POLYGON,
				points : startX+","+startY+","+
						 point1.x+","+point1.y+","+
						 point3.x+","+point3.y+","+
						 point4.x+","+point4.y+","+
						 endX+","+endY+","+
						 point5.x+","+point5.y+","+
						 point6.x+","+point6.y+","+
						 point2.x+","+point2.y+","+
						 startX+","+startY
			});

			return geometry;
		}

		/**
		 * 带弧线的趋势箭头
		 */
		function _trendArrow2(){
			var geometry,
				angle1 = Math.PI / 2,
				angle2 = Math.PI / 3,
				angle3 = Math.PI / 4,
				angle4 = Math.PI / 5,
				angle5 = Math.PI / 6,
				axisAngle,
				point1 = {},point2 = {},point3 = {},point4 = {},point5 = {},point6 = {},
				offsetX1,offsetY1,
				offsetX2,offsetY2,
				offsetX3,offsetY3,
				offsetX4,offsetY4,
				offsetX5,offsetY5,
				offsetX6,offsetY6,
				lineLength,
				edge1,edge2,edge3,edge4,edge5,edge6,
				middlePoint = {},
				mPoint1 = {},mPoint2 = {},
				gradient1,gradient2,offsetB,
				point_1,point_2,point_3,point_4,point_5,point_6,
				startX,startY,endX,endY;

			var _sPoints = startPoint.points.split(","),
				_ePoints = endPoint.points.split(",");

			startX = parseFloat(_sPoints[0]);
			startY = parseFloat(_sPoints[1]);
			endX = parseFloat(_ePoints[0]);
			endY = parseFloat(_ePoints[1]);

			// 计算鼠标拉出的距离
			lineLength = Math.sqrt(Math.pow(endY - startY,2) + Math.pow(endX - startX,2));
			gradient1 = _caculateGradient(startPoint,endPoint);
			axisAngle = Math.atan(gradient1);

			edge1 = lineLength / 4;
			edge2 = lineLength / 4;
			edge3 = lineLength / 8;
			edge4 = lineLength / 5;
			edge5 = lineLength / 5;
			edge6 = lineLength / 8;

			if(axisAngle > 0){
				offsetX1 = edge1 * Math.cos(angle3 + axisAngle);
				offsetY1 = edge1 * Math.sin(angle3 + axisAngle);

				offsetX2 = edge2 * Math.cos(angle3 - axisAngle);
				offsetY2 = edge2 * Math.sin(angle3 - axisAngle);

				offsetX3 = edge3 * Math.cos(angle4 + axisAngle);
				offsetY3 = edge3 * Math.sin(angle5 + axisAngle);

				offsetX4 = edge4 * Math.cos(angle3 + axisAngle);
				offsetY4 = edge4 * Math.sin(angle3 + axisAngle);

				offsetX5 = edge5 * Math.cos(angle3 - axisAngle);
				offsetY5 = edge5 * Math.sin(angle3 - axisAngle);

				offsetX6 = edge6 * Math.cos(angle4 - axisAngle);
				offsetY6 = edge6 * Math.sin(angle5 - axisAngle);
			}else{
				offsetX1 = edge1 * Math.cos(angle3 - axisAngle);
				offsetY1 = edge1 * Math.sin(angle3 - axisAngle);

				offsetX2 = edge2 * Math.cos(angle3 + axisAngle);
				offsetY2 = edge2 * Math.sin(angle3 + axisAngle);

				offsetX3 = edge3 * Math.cos(angle4 - axisAngle);
				offsetY3 = edge3 * Math.sin(angle5 - axisAngle);

				offsetX4 = edge4 * Math.cos(angle3 - axisAngle);
				offsetY4 = edge4 * Math.sin(angle3 - axisAngle);

				offsetX5 = edge5 * Math.cos(angle3 + axisAngle);
				offsetY5 = edge5 * Math.sin(angle3 + axisAngle);

				offsetX6 = edge6 * Math.cos(angle4 + axisAngle);
				offsetY6 = edge6 * Math.sin(angle5 + axisAngle);
			}

			if(endX > startX){
				point1.x = startX - offsetX1;
				point2.x = startX - offsetX2;
				point3.x = endX - offsetX3;
				point4.x = endX - offsetX4;
				point5.x = endX - offsetX5;
				point6.x = endX - offsetX6;
			}else{
				point1.x = startX + offsetX1;
				point2.x = startX + offsetX2;
				point3.x = endX + offsetX3;
				point4.x = endX + offsetX4;
				point5.x = endX + offsetX5;
				point6.x = endX + offsetX6;
			}

			if(endY > startY){
				point1.y = startY - offsetY1;
				point2.y = startY + offsetY2;
				point3.y = endY - offsetY3;
				point4.y = endY - offsetY4;
				point5.y = endY + offsetY5;
				point6.y = endY + offsetY6;
			}else{
				point1.y = startY + offsetY1;
				point2.y = startY - offsetY2;
				point3.y = endY + offsetY3;
				point4.y = endY + offsetY4;
				point5.y = endY - offsetY5;
				point6.y = endY - offsetY6;
			}

			var middleSE = {};
			var offsetLength = lineLength/50;
			var point_1_3 = {},point_6_2 = {};
			var jiajiao;
			var line_path_1,line_path_2;
			var path;

			middleSE.x = (startX + endX)/2;
			middleSE.y = (startY + endY)/2;

			if(gradient1>0){
				jiajiao = Math.PI/2 - axisAngle;
				point_1_3.x = middleSE.x - offsetLength * Math.cos(jiajiao);
				point_1_3.y = middleSE.y + offsetLength * Math.sin(jiajiao);
				point_6_2.x = middleSE.x + offsetLength * Math.cos(jiajiao);
				point_6_2.y = middleSE.y - offsetLength * Math.cos(jiajiao);
			}else{
				jiajiao = Math.PI - axisAngle;
				point_1_3.x = middleSE.x + offsetLength * Math.cos(jiajiao);
				point_1_3.y = middleSE.y - offsetLength * Math.sin(jiajiao);
				point_6_2.x = middleSE.x - offsetLength * Math.cos(jiajiao);
				point_6_2.y = middleSE.y + offsetLength * Math.cos(jiajiao);
			}

			var point_1_3_geo = new Geometry({
				type : GeometryType.POINT,
				points : point_1_3.x+","+point_1_3.y
			});

			var point_6_2_geo = new Geometry({
				type : GeometryType.POINT,
				points : point_6_2.x+","+point_6_2.y
			});

			line_path_1 = _beizerCurve(point1,point_1_3,point3);
			line_path_2 = _beizerCurve(point6,point_6_2,point2);

			path = [
				[startX,startY],
				[point1.x,point1.y]
			];
			path = path.concat(line_path_1);
			path = path.concat([
				[point3.x,point3.y],
				[point4.x,point4.y],
				[endX,endY],
				[point5.x,point5.y],
				[point6.x,point6.y]
			]);
			path = path.concat(line_path_2);
			path = path.concat([
				[point2.x,point2.y],
				[startX,startY]
			]);

			geometry = new Geometry({
				type : GeometryType.POLYGON,
				points : path.join()
			});

			return geometry;
		}

		return api;
	}
});