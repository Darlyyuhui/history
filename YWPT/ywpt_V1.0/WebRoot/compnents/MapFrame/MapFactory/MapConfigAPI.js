MapFactory.Define("MapFactory/MapConfigAPI*",function(){
	return {
		// 服务总地址
		serviceUrl : "",
		// 初始范围
		initExtent : {
			minX:"",minY:"",maxX:"",maxY:""
		},
		// 图层信息
		layers : {},
		// 切片分别率
		resolutions : [],
		// 坐标系代码
		spatialReference : "",
		// 坐标系对应的投影坐标系
		projectSpatialReference : "",
		// 需要引入的css文件数组
		referenceCSS : []
	}
});