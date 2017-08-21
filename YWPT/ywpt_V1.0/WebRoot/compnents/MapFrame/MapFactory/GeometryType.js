/**
 * 几何类型
 * @author ZLT
 * @since 2014-3-27
 **/
MapFactory.Define("MapFactory/GeometryType*",function(){
	return {
		POINT : "point", // 点
		POLYLINE : "polyline", // 线
		POLYGON : "polygon", // 面
		MULTIPOINT : "multipoint", // 多点
		MULTIPOLYLINE : "multipolyline", // 多线
		MULTIPOLYGON : "multipolygon" // 多面
	};
});