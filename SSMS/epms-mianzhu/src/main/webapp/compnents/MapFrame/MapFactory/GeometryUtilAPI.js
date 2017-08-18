/**
 * 几何类型转换接口
 * 该接口仅限于框架内部使用，禁止框架外使用，否则会暴露底层接口
 */
MapFactory.Define("MapFactory/GeometryUtilAPI*",function(){
	return {

		/**
		 * 从其他框架转到本框架
		 * @param obj Object 其他框架geometry格式
		 * @return geo Geometry 返回本框架geometry格式定义
		 */
		convertFromObject : "从其他框架转到本框架",

		/**
		 * 从本框架转为其他框架格式
		 * @param geo Geometry 本框架geometry格式定义
		 * @return obj Object 返回其他框架geometry格式
		 */
		convertFromMapFactory : "从本框架转为其他框架格式",

		union : "几何体的合并操作"
	}
});