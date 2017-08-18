MapFactory.Define("MapFactory/GraphicManagerAPI*",function(){
	return {
		/**
		 * 构造函数
		 * @param id String GraphicID，可选
		 * @param conf Object 配置信息
		 *   + geo String 几何字符串
		 *   + symbol Object 符号配置对象
		 *   + attributes Object Graphic属性
		 */

		/**
		 * 获取几何坐标字符串
		 * @return geoStr String 几何字符串
		 */
		getGeometryString : "获取几何坐标字符串",

		/**
		 * 获取几何类型
		 * @return type String 几何类型
		 */
		getGeometryType : "获取几何类型",

		/**
		 * 获取属性
		 * @return attributes Object 属性键值对
		 */
		getAttributes : "获取属性",

		/**
		 * 将Graphic添加到指定图层
		 * @param id String 添加到指定图层
		 */
		addToLayer : "将Graphic添加到指定图层",

		/**
		 * 将Graphic移除
		 */
		remove : "将Graphic移除",

		/**
		 * 获取geometry中心点
		 * @return point Geometry
		 */
		getCenter : "获取geometry中心点",

		/**
		 * 获取geometry范围
		 * @return extent Object
		 * + minX 最小经度
		 * + minY 最小纬度
		 * + maxX 最大经度
		 * + maxY 最大纬度
		 */
		getExtent : "获取geometry范围",

		/**
		 * 获取Graphic
		 * @return graphic Object 框架定义graphic对象
		 */
		getGraphic : "获取Graphic",

		/**
		 * 获取Graphic所属的图层ID
		 * @return layerid String 图层ID
		 */
		getLayerID : "",

		/**
		 * 显示Graphic
		 */
		show : "",

		/**
		 * 隐藏Graphic
		 */
		hide : "",

		/**
		 * 高亮显示Graphic
		 */
		highlight : "高亮显示",

		/**
		 * 清除所有的高亮显示
		 */
		clearAllHighlight : "清除所有的高亮显示"
	}
});