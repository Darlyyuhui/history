/**
 * 符号转换应用
 * 该接口仅限于框架内部使用，禁止框架外使用，否则会暴露底层接口
 */
MapFactory.Define("MapFactory/SymbolUtilAPI*",function(){
	return {
		/**
		 * 将框架符号定义格式转换为其他框架格式
		 * @param gType String 几何类型
		 * @param symbolObj Object 框架符号格式
		 * @return symbol Symbol 其他框架符号格式
		 */
		convertFromMapFactory : "将框架符号定义格式转换为其他框架格式",

		/**
		 * 将其他框架符号定义格式转换为MapFactory定义格式
		 * @param gType String 几何类型
		 * @param symbolObject Object 其他框架符号格式
		 * @return symbol Object 本框架符号格式
		 */
		convertFromObject : "将其他框架符号定义格式转换为MapFactory定义格式"
	}
});