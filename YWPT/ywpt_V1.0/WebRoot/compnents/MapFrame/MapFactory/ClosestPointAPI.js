MapFactory.Define("MapFactory/ClosestPointAPI*",function(){
	return {
		/**
		 * 配置参数
		 * @param conf Object 配置信息
		 * 	 + urls String[] 查找最近点的图层url字符串数组
		 *   + ids String[] 查找最近点的图层id字符串数组
		 *   + geometry MapFactory/Geometry[] 查找最近点的几何体数组
		 */

		/**
		 * 获取最近点
		 * @param Geometry {type:"point", points: "x,y"} 地图坐标对象
		 * @return Geometry {type:"point", points: "x,y"} 地图坐标对象
		 */
		getClosestPoint : "获取最近点",

		/**
		 * 销毁最近点查找对象
		 */
		destroy : "销毁最近点查找对象"
	};
});