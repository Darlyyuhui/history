MapFactory.Define("MapFactory/ExportAPI*",function(){
	return {

		/**
		 * 设置访问链接
		 * @param url String 设置访问链接
		 */
		setUrl : "设置访问链接",

		/**
		 * 设置参数
		 * @param params Object 参数键值对
		 */
		setParams : "设置参数",

		/**
		 * 导出地图
		 * @param successFunc Function 成功回调函数
		 * @param failureFunc Function 失败回调函数
		 */
		exportMap : "导出地图"
	};
});