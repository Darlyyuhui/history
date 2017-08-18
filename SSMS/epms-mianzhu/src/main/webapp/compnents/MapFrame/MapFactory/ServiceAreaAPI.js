MapFactory.Define("MapFactory/ServiceAreaAPI*",function(){
	return {

		/**
		 * 设置服务链接
		 * @param url String 服务链接
		 */
		setUrl : "设置服务链接",

		/**
		 * 设置服务点几何体
		 * @param geoArr MapFactory/Geometry[] 服务点几何体数组
		 */
		setFacilities : "设置服务点几何体",

		/**
		 * 设置障碍几何体
		 * @param geoArr MapFactory/Geometry[] 障碍几何体数组，点/线/面
		 */
		setBarriers : "设置障碍几何体",

		/**
		 * 设置障碍几何体
		 * @param times Number[] 时间分隔数组
		 */
		setBreaks : "设置时间间隔",

		/**
		 * 执行服务区分析
		 * @param successFunc Function 执行成功函数
		 * @param failureFunc Function 执行失败函数
		 */
		execute : "执行服务区分析"
	}
});