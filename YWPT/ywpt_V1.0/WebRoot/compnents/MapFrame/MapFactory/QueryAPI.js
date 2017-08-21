MapFactory.Define("MapFactory/QueryAPI*",function(){
	return {

		/**
		 * 设置查询链接
		 * @param url String 查询链接
		 */
		setUrl : "设置查询链接",

		/**
		 * 设置用来查询的几何对象
		 * @param geometry MapFactory.Geometry 输入几何对象
		 */
		setGeometry : "设置用来查询的几何对象",

		/**
		 * 设置几何查询精度
		 * @param precision Float 精度
		 */
		setGeometryPrecision : "设置几何查询精度",

		/**
		 * 几何查询空间关系设置
		 * @param relationShip String 空间关系，intersect|overlap|contains|closedpoint
		 */
		setSpatialRelationShip : "设置几何查询，空间关系",

		/**
		 * 设置查询条件
		 * @param obj Object 要查询的键值对
		 *     + field String 字段名称
		 *     + fieldcon String 字段条件
		 */
		setCondition : "设置查询条件",

		/**
		 * 执行查询
		 * @param successFunc Function 成功回调函数
		 * @param failureFunc Function 失败回调函数
		 */
		execute : "执行查询"
	}
});