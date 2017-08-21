MapFactory.Define("MapFactory/BufferAPI*",function(){
	return {

		/**
		 * 设置服务地址
		 * @param url String 服务地址字符串
		 */
		setUrl : "设置服务地址",

		/**
		 * 设置需要缓冲的几何体
		 * @param geometryArr MapFactory/Geometry[] 传入需要处理的几何体数组
		 */
		setGeometry : "设置需要缓冲的几何体",

		/**
		 * 设置需要缓冲的几何体
		 * @param meterArr Number[] 传入缓冲的距离数组
		 */
		setDistance : "设置缓冲的距离，单位:米",

		/**
		 * 执行缓冲区分析
		 * @param successFunc Function 成功回调函数
		 * @param failureFunc Function 失败回调函数
		 */
		execute : "执行缓冲区分析"
	};
});