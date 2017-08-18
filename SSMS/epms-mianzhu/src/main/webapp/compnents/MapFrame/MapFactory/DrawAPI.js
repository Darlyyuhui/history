MapFactory.Define("MapFactory/DrawAPI*",function(){
	return {

		/**
		 * 设置要绘画的几何类型
		 * @param geometryType String 要绘画的几何类型
		 */
		setGeoType : "设置要绘画的几何类型",

		/**
		 * 设置填充符号
		 * @param symbol Object 填充符号配置，详见MapFactory/SymbolConfig
		 */
		setFillSymbol : "设置填充符号",

		/**
		 * 设置线符号
		 * @param symbol Object 设置线符号，详见MapFactory/SymbolConfig
		 */
		setLineSymbol : "设置线符号",

		/**
		 * 设置标记符号
		 * @param symbol Object 设置标记符号，详见MapFactory/SymbolConfig
		 */
		setMarkerSymbol : "设置标记符号",

		/**
		 * 设置绘画结尾事件
		 * @param func Function 绘画完成回调事件
		 * @return geometry Geometry 返回绘画的几何体
		 */
		setDrawEndEvent : "设置绘画结尾事件",

		/**
		 * 开启捕捉
		 * @param snapConf Object 捕捉配置
		 *  + ids String[] 要捕捉的图层ID
		 *  + urls String[] 要捕捉的图层Url
		 */
		openSnapping : "开启捕捉",

		/**
		 * 关闭捕捉
		 */
		closeSnapping : "关闭捕捉",

		/**
		 * 激活工具
		 */
		activate : "激活工具",

		/**
		 * 停止工具
		 */
		deactivate : "停止工具"
	};
});