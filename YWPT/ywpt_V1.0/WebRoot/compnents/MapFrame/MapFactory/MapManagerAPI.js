/**
 * 地图管理器
 * @author ZLT
 * @since 2014-3-31
 */
MapFactory.Define("MapFactory/MapManagerAPI*",function(){
	return {

		/**
		 * 用来居中某一点，并缩放至某一级别
		 * @param x Float 横坐标
		 * @param y Float 纵坐标
		 * @param level Int 缩放等级                
		 * @param callback Function 回调函数
		 **/
		centerAndZoom : "函数，用来居中某一点，并缩放至某一级别",

		/**
		 * 居中某一点
		 * @param x Float 横坐标
		 * @param y Float 纵坐标
		 * @param callback Function 回调函数
		 */
		centerAt : "居中某一点",

		/**
		 * 带偏移的居中
		 * @param x Float 横坐标
		 * @param y Float 纵坐标
		 * @param offsetX Float 横坐标偏移量，单位：像素
		 * @param offsetY Float 纵坐标偏移量，单位：像素
		 * @param callback Function 回调函数
		 */
		centerAtWithOffset : "带偏移的居中",

		/**
		 * 将地图向左移动
		 * @param callback Function 回调函数
		 */
		panLeft : "将地图向左移动",

		/**
		 * 将地图向右移动
		 * @param callback Function 回调函数
		 */
		panRight : "将地图向右移动",

		/**
		 * 将地图向上移动
		 * @param callback Function 回调函数
		 */
		panUp : "将地图向上移动",

		/**
		 * 将地图向下移动
		 * @param callback Function 回调函数
		 */
		panDown : "将地图向下移动",

		/**
		 * 设置地图显示级别
		 * @param level Number 地图级别
		 * @param callback Function 回调函数
		 */
		setLevel : "设置地图显示级别",

		/**
		 * 获取地图当前的显示级别
		 * @return level Number 返回当前地图级别
		 */
		getLevel : "获取地图当前的显示级别",

		/**
		 * 获取地图分级数
		 * @return num Number 返回分级数
		 */
		getNumsOfLevel : "获取地图分级数",

		/**
		 *  设置地图范围
		 *  @param extent Object
		 *  + minX 最小经度
		 *  + minY 最小纬度
		 *  + maxX 最大经度
		 *  + maxY 最大纬度
		 */
		setExtent : "设置地图范围",

		/**
		 * 获取当前地图范围
		 * @param extent Object
		 *  + minX 最小经度
		 *  + minY 最小纬度
		 *  + maxX 最大经度
		 *  + maxY 最大纬度
		 */
		getCurrentExtent : "获取当前地图范围",

		/**
		 * 是否在当前的范围内
		 * @param x String 传入要判断点的横坐标
		 * @param y String 传入要判断点的纵坐标
		 */
		isInCurrentExtent : "是否在当前的范围内",

		/**
		 * 将屏幕坐标转换为地图坐标
		 * @param x Float 横坐标
		 * @param y Float 纵坐标
		 */
		toMap : "将屏幕坐标转换为地图坐标",

		/**
		 * 将地图坐标转换为屏幕坐标
		 * @param x Float 横坐标
		 * @param y Float 纵坐标
		 */
		toScreen : "将地图坐标转换为屏幕坐标",

		/**
		 * 获取所有图层ID
		 * @return layerids String[] 图层ID数组
		 */
		getAllLayersID : "获取地图所有图层ID",

		/**
		 * 将图层排至指定层
		 * @param layerId String 图层ID
		 * @param layerIndex Number 图层层数
		 */
		reorderLayer : "将图层排至指定层",

		/**
		 * 设置图层添加事件
		 * @param func Function 图层添加后的回调函数
		 */
		setLayerAddEvent : "图层添加后的回调函数",

		/**
		 * 图层移除事件
		 * @param func Function 图层移除后的回调函数
		 */
		setLayerRemoveEvent : "图层移除后的回调函数",

		/**
		 * 鼠标拖拽事件
		 * @param obj Object 传入参数对象
		 * 	+ dragstart Function 拖拽开始回调函数
		 *  + dragmove Function 拖拽中回调函数
		 *  + dragend Function 拖拽完成回调函数
		 * @return point 返回拖拽点
		 */
		setMouseDragEvent : "鼠标拖拽事件",

		/**
		 * 移除鼠标拖拽事件
		 */
		removeMouseDragEvent : "移除鼠标拖拽事件",

		/**
		 * 地图缩放结束事件
		 * @param obj Object 传入参数对象
		 * 	+ zoomstart Function 缩放开始回调函数
		 *  + zooming Function 缩放中回调函数
		 *  + zoomend Function 缩放完成回调函数
		 * @return extent 返回当前地图范围
		 */
		setZoomEvent : "地图缩放结束事件",

		/**
		 * 设置鼠标悬停事件
		 * @param func Function 鼠标悬停回调函数，返回地图点mapPoint和screenPoint
		 */
		setMouseMoveEvent : "设置鼠标悬停事件",

		/**
		 * 移除鼠标悬停事件
		 */
		removeMouseMoveEvent : "移除鼠标悬停事件",

		/**
		 * 设置鼠标按下事件
		 * @param func Function 鼠标按下回调函数
		 */
		setMouseDownEvent : "设置鼠标按下事件",

		/**
		 * 移除鼠标按下事件
		 */
		removeMouseDownEvent : "移除鼠标按下事件",

		/**
		 * 设置鼠标抬起事件
		 * @param func Function 鼠标抬起回调函数
		 */
		setMouseUpEvent : "设置鼠标抬起事件",

		/**
		 * 移除鼠标抬起事件
		 */
		removeMouseUpEvent : "移除鼠标抬起事件",

		/**
		 * 设置鼠标点击事件
		 * @param func Function 鼠标点击回调函数，返回地图点mapPoint和screenPoint
		 */
		setMouseClickEvent : "设置鼠标点击事件",

		/**
		 * 移除鼠标点击事件
		 */
		removeMouseClickEvent : "移除鼠标点击事件",

		/**
		 * 设置鼠标双击事件
		 * @param func Function 鼠标双击事件回调函数，返回地图点mapPoint和screenPoint
		 */
		setMouseDoubleClickEvent : "设置鼠标双击事件",

		/**
		 * 移除鼠标双击事件
		 */
		removeMouseDoubleClickEvent : "移除鼠标双击事件",

		/**
		 * 设置地图范围改变事件
		 * @param func Function 地图范围改变时的回调函数
		 */
		setExtentChangeEvent : "设置地图范围改变事件",

		/**
		 * 移除地图范围改变监听事件
		 */
		removeExtentChangeEvent : "移除地图范围改变监听事件",

		/**
		 * 设置鼠标滑轮事件
		 * @param func Function 鼠标滑轮滑动时触发的回调函数
		 */
		setMouseWheelEvent : "设置鼠标滑轮事件",

		/**
		 * 获取坐标系代码
		 * @return wkid String 地理坐标系代码
		 */
		getSpatialReferenceCode : "获取坐标系代码",

		/**
		 * 获取投影坐标系代码
		 * @return wkid String 投影坐标系代码
		 */
		getProjectSpatialReferenceCode : "获取投影坐标系代码",

		/**
		 * 获取初始化范围
		 * @return extent 返回初始化地图范围
		 */
		getInitExtent : "获取初始化范围",

		/**
		 * 更新地图大小
		 */
		resize : "更新地图大小",

		/**
		 * 获取地图配置
		 * @return obj Object 返回配置对象
		 */
		getMapConfig : "获取地图配置",

		/**
		 * 禁止拖动地图
		 */
		disablePan : "禁止拖动地图",

		/**
		 * 允许拖动地图
		 */
		enablePan : "允许拖动地图",

		/**
		 * 判断图层是否存在
		 * @param String layerid 传入要判断的layer id
		 * @return Boolean isExist 返回是否存在
		 */
		isLayerExist : "判断图层是否存在",

		/**
		 * 放大一个级别
		 */
		zoomIn : "放大一个级别",

		/**
		 * 缩小一个级别
		 */
		zoomOut : "缩小一个级别",

		/**
		 * 获取当前地图大小
		 * @return Object size
		 *  + width int 宽
		 *  + height int 高
		 */
		getMapSize : "获取当前地图大小"
	}
});