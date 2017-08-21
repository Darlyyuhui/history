MapFactory.Define("MapFactory/MeasureMentAPI*",function(){
	return {
		
		/**
		 * 简单的获取几何要素的长度
		 * @param line Geometry 线的几何要素
		 */
		getLength : "简单的获取几何要素的长度",
		
		/**
		 * 设置几何服务链接
		 * @param url String 几何服务链接字符串
		 */
		setGeometryServiceUrl : "设置几何服务链接",

		/**
		 * 显示测量工具
		 */
		show : "显示测量工具",

		/**
		 * 隐藏测量工具
		 */
		hide : "隐藏测量工具"
	};
});