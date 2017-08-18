MapFactory.Define("MapFactory/EditAPI*",function(){
	return {

		/**
		 * 设置编辑方法
		 * @param mode String 编辑方法：rotate|scale|move|editvertices，在openlayers下，节点编辑和其他编辑不能共用
		 */
		setEditMode : "设置编辑方法",

		/**
		 * 设置编辑的图层对象ID
		 * @param layerID String 传入要编辑的图层ID
		 */
		setLayerID : "设置编辑的图层对象ID",

		/**
		 * 激活工具
		 */
		activate : "激活工具",

		/**
		 * 关闭工具
		 */
		deactivate : "关闭工具"
	};
});