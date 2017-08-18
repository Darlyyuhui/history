MapFactory.Define("MapFactory/RouteAPI*",function(){
	return {

		/**
		 * 设置路径分析服务地址
		 * @param url String 传入服务地址
		 */
		setUrl : "设置路径分析服务地址",

		/**
		 * 设置起始点
		 * @param point Geometry 传入起始点
		 */
		setStart : "设置起始点",

		/**
		 * 设置停靠点
		 * @param points Geometry[] 传入停靠点数组
		 */
		setStops : "设置停靠点",

		/**
		 * 设置结束点
		 * @param point Geometry 传入结束点
		 */
		setEnd : "设置结束点",

		/**
		 * 设置障碍点
		 * @param points Geometry[] 传入障碍点
		 */
		setBarriers : "设置障碍点",

		/**
		 * 执行路径分析
		 * @param successFunc Function 成功之后的回调函数
		 * @param failureFunc Function 失败之后的回调函数
		 */
		solve : "执行路径分析"
	}
});