MapFactory.Define("MapFactory/LayerManagerAPI*",function(){
	return {
		/**
		 * 构造函数
		 * @param id String 图层id
		 * @param conf Object 配置信息
		 * 	 + minScale int 最小可见比例尺
		 *   + maxScale int 最大可见比例尺
		 *   + isVisible Boolean 是否可见
		 */

		/**
		 * 获取图层ID
		 * @return layerID String 返回图层ID
		 */
		getId : "获取图层ID",

		/**
		 * 获取图层Dom的ID
		 * @return id String 返回图层Dom的Id
		 */
		getDomId : "获取图层Dom的ID",

		/**
		 * 显示图层
		 */
		show : "显示图层",

		/**
		 * 隐藏图层
		 */
		hide : "隐藏图层",

		/**
		 * 清除图层上的元素
		 */
		clear : "清除图层上的元素",

		/**
		 * 是否可见
		 * @return Boolean
		 */
		isVisible : "是否可见",

		/**
		 * 将图层从地图移除
		 */
		removeFromMap : "将图层从地图移除",

		/**
		 * 点击事件
		 * @param func Function 点击事件回调函数
		 */
		addOnClickEvent : "",

		/**
		 * 添加鼠标按下事件
		 * @param func Function 鼠标按下回调函数
		 */
		addMouseDownEvent : "",

		/**
		 * 添加鼠标悬停事件
		 * @param func Function 鼠标悬停回调函数
		 */
		addMouseOverEvent : "添加鼠标悬停事件",

		/**
		 * 添加鼠标移出事件
		 * @param func Function 鼠标移出事件回调函数
		 */
		addMouseOutEvent : "添加鼠标移出事件",

		/**
		 * 添加图层可见性改变事件
		 * @param func Function 可见性改变回调函数
		 */
		addVisibleChangeEvent : "",

		/**
		 * 移除图层事件
		 * @param evtId String 事件ID
		 */
		removeEvent : "",

		/**
		 * 获取图层中所有feature
		 * @return Array 要素数组
		 */
		getFeatures : "获取图层中所有feature",

		/**
		 * 图层过滤器
		 * @param condition Object 过滤条件
		 * {
		 * 	  field : value
		 * }
		 * @param isremove Boolean 是保存过滤还是移除过滤
		 */
		filterByAttr : ""
	}
});