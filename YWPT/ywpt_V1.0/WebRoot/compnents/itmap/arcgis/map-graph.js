/**
 * ArcGIS图形扩展
 * @author ZLT
 * @since 2014-1-15
 **/
itmap.arcgis.graph = (function(){
	return function(){

		/**
		 * 返回API
		 */
		var api = {
			draw : draw,
			onDrawEnd : onDrawEnd,
			onDrawComplete : onDrawComplete
		};

		/**
		 * 图形类型
		 */
		var graphType = {
			"TREND_ARROW" : _trendArrow,
			"TREND_ARROW_WITH_CURVE" : _trendArrow2
		};

		var mapC = itmap.arcgis.map, // 初始化地图资源
			mouseDownHandler = null, // 鼠标按下事件句柄
			mouseDragHandler = null, // 鼠标拖拽事件句柄
			mouseUpHandler = null, // 鼠标松开事件句柄
			startPoint,endPoint, // 起始点和结束点
			onDrawEndFunc = null, // 拖拽绘制完成回调函数
			onDrawCompleteFunc = null, // 绘制结束回调函数
			currentGraphType; // 目前所要绘制的图形类型

		/**
		 * 图形绘画
		 * @param type 传入要绘的类型
		 */
		function draw(type){
			currentGraphType = type;
			_disableMapAction();
			mouseDownHandler = dojo.connect(mapC,"onMouseDown",_mouseDownEvt);
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
			mapC.disablePan();
			mapC.disableScrollWheelZoom();
			mapC.disableShiftDoubleClickZoom();
		}

		/**
		 * 恢复被屏蔽的地图行为
		 */
		function _enableMapAction(){
			mapC.enablePan();
			mapC.enableScrollWheelZoom();
			mapC.enableShiftDoubleClickZoom();
		}

		/**
		 * 鼠标按下事件触发函数
		 * @param event 事件对象
		 */
		function _mouseDownEvt(event){
			var geometry = null;
			startPoint = event.mapPoint;
			mouseMoveHandler = dojo.connect(mapC,"onMouseDrag",_mouseDragEvt);
			dojo.connect(mapC,"onMouseDragEnd",_mouseDragEndEvt);
			mouseUpHandler = dojo.connect(mapC,"onMouseUp",_mouseUpEvt);

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
				dojo.disconnect(mouseDownHandler);
				dojo.disconnect(mouseMoveHandler);
				dojo.disconnect(mouseUpHandler);
				_enableMapAction();

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
			return (endP.y-startP.y)/(endP.x-startP.x);
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
				x = Math.pow(1 - t,2) * startP.x + 2 * t *(1 - t) * middleP.x + Math.pow(t,2) * endP.x;
				y = Math.pow(1 - t,2) * startP.y + 2 * t *(1 - t) * middleP.y + Math.pow(t,2) * endP.y;
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
				edge1,edge2,edge3,edge4,edge5,edge6;
			
			// 计算鼠标拉出的距离
			lineLength = Math.sqrt(Math.pow(endPoint.y - startPoint.y,2) + Math.pow(endPoint.x - startPoint.x,2));
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
			
			if(endPoint.x > startPoint.x){
				point1.x = startPoint.x - offsetX1;
				point2.x = startPoint.x - offsetX2;
				point3.x = endPoint.x - offsetX3;
				point4.x = endPoint.x - offsetX4;
				point5.x = endPoint.x - offsetX5;
				point6.x = endPoint.x - offsetX6;
			}else{
				point1.x = startPoint.x + offsetX1;
				point2.x = startPoint.x + offsetX2;
				point3.x = endPoint.x + offsetX3;
				point4.x = endPoint.x + offsetX4;
				point5.x = endPoint.x + offsetX5;
				point6.x = endPoint.x + offsetX6;
			}

			if(endPoint.y > startPoint.y){
				point1.y = startPoint.y - offsetY1;
				point2.y = startPoint.y + offsetY2;
				point3.y = endPoint.y - offsetY3;
				point4.y = endPoint.y - offsetY4;
				point5.y = endPoint.y + offsetY5;
				point6.y = endPoint.y + offsetY6;
			}else{
				point1.y = startPoint.y + offsetY1;
				point2.y = startPoint.y - offsetY2;
				point3.y = endPoint.y + offsetY3;
				point4.y = endPoint.y + offsetY4;
				point5.y = endPoint.y - offsetY5;
				point6.y = endPoint.y - offsetY6;
			}
			
			point_1 = new esri.geometry.Point(point1.x,point1.y,mapC.spatialReference);
			point_2 = new esri.geometry.Point(point2.x,point2.y,mapC.spatialReference);
			point_3 = new esri.geometry.Point(point3.x,point3.y,mapC.spatialReference);
			point_4 = new esri.geometry.Point(point4.x,point4.y,mapC.spatialReference);
			point_5 = new esri.geometry.Point(point5.x,point5.y,mapC.spatialReference);
			point_6 = new esri.geometry.Point(point6.x,point6.y,mapC.spatialReference);
			
			geometry = new esri.geometry.Polygon(new esri.SpatialReference({wkid:4326}));
			geometry.addRing([
				[startPoint.x,startPoint.y],
				[point_1.x,point_1.y],
				[point_3.x,point_3.y],
				[point_4.x,point_4.y],
				[endPoint.x,endPoint.y],
				[point_5.x,point_5.y],
				[point_6.x,point_6.y],
				[point_2.x,point_2.y],
				[startPoint.x,startPoint.y]
			]);
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
				point_1,point_2,point_3,point_4,point_5,point_6;

			// 计算鼠标拉出的距离
			lineLength = Math.sqrt(Math.pow(endPoint.y - startPoint.y,2) + Math.pow(endPoint.x - startPoint.x,2));
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

			if(endPoint.x > startPoint.x){
				point1.x = startPoint.x - offsetX1;
				point2.x = startPoint.x - offsetX2;
				point3.x = endPoint.x - offsetX3;
				point4.x = endPoint.x - offsetX4;
				point5.x = endPoint.x - offsetX5;
				point6.x = endPoint.x - offsetX6;
			}else{
				point1.x = startPoint.x + offsetX1;
				point2.x = startPoint.x + offsetX2;
				point3.x = endPoint.x + offsetX3;
				point4.x = endPoint.x + offsetX4;
				point5.x = endPoint.x + offsetX5;
				point6.x = endPoint.x + offsetX6;
			}

			if(endPoint.y > startPoint.y){
				point1.y = startPoint.y - offsetY1;
				point2.y = startPoint.y + offsetY2;
				point3.y = endPoint.y - offsetY3;
				point4.y = endPoint.y - offsetY4;
				point5.y = endPoint.y + offsetY5;
				point6.y = endPoint.y + offsetY6;
			}else{
				point1.y = startPoint.y + offsetY1;
				point2.y = startPoint.y - offsetY2;
				point3.y = endPoint.y + offsetY3;
				point4.y = endPoint.y + offsetY4;
				point5.y = endPoint.y - offsetY5;
				point6.y = endPoint.y - offsetY6;
			}

			point_1 = new esri.geometry.Point(point1.x,point1.y,mapC.spatialReference);
			point_2 = new esri.geometry.Point(point2.x,point2.y,mapC.spatialReference);
			point_3 = new esri.geometry.Point(point3.x,point3.y,mapC.spatialReference);
			point_4 = new esri.geometry.Point(point4.x,point4.y,mapC.spatialReference);
			point_5 = new esri.geometry.Point(point5.x,point5.y,mapC.spatialReference);
			point_6 = new esri.geometry.Point(point6.x,point6.y,mapC.spatialReference);

			var middleSE = {};
			var offsetLength = lineLength/50;
			var point_1_3 = {},point_6_2 = {};
			var jiajiao;
			var line_path_1,line_path_2;
			var path;
			
			middleSE.x = (startPoint.x + endPoint.x)/2;
			middleSE.y = (startPoint.y + endPoint.y)/2;
			
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
			
			line_path_1 = _beizerCurve(point1,point_1_3,point3);
			line_path_2 = _beizerCurve(point6,point_6_2,point2);
			
			path = [
				[startPoint.x,startPoint.y],
				[point_1.x,point_1.y]
			];
			path = path.concat(line_path_1);
			path = path.concat([
				[point_3.x,point_3.y],
				[point_4.x,point_4.y],
				[endPoint.x,endPoint.y],
				[point_5.x,point_5.y],
				[point_6.x,point_6.y]
			]);
			path = path.concat(line_path_2);
			path = path.concat([
				[point_2.x,point_2.y],
				[startPoint.x,startPoint.y]
			]);
			geometry = new esri.geometry.Polygon(new esri.SpatialReference({wkid:4326}));
			geometry.addRing(path);
			
			return geometry;
		}

		return api;
	}
})();

itmap.arcgis.graph.type = {
	TREND_ARROW : "TREND_ARROW",
	TREND_ARROW_WITH_CURVE : "TREND_ARROW_WITH_CURVE"
}