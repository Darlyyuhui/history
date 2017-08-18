MapFactory.Define("MapFactory/EditServicesAPI*",function(){
	return {

		/**
		 * 设置编辑方法
		 * @param type String 编辑方法：UPDATE/ADD/DELETE
		 */
		setEditType : "设置编辑方法",

		/**
		 * 设置服务链接
		 * @param url String 服务链接
		 */
		setUrl : "设置服务链接",

		/**
		 * 设置要修改的Grpahics数组
		 * @param graphics Graphic[] Graphic数组
		 */
		setGraphics : "设置要修改的Graphics数组",

		/**
		 * 提交数据
		 * @param successFunc Function 处理成功回调函数
		 * @param failureFunc Function 处理失败回调函数
		 */
		submit : "提交数据"
	};
});