/**
 * 符号配置
 * symbolid : {
 *    url,width,height,size,xOffset,yOffset,angle,
 *    text,textFontFamily,textColor,textStyle,textWeight,
 *    symbolStyle,
 *    outLineColor,outLineWidth,outlineStyle,outlineOpacity
 *    backgroundColor,backgroundOpacity,backgroundStyle,
 * }
 */
MapFactory.Define("MapFactory/SymbolConfig*",function(){
	return{
		defaultPoint : {
			outLineColor : "#FF0000",
			outLineStyle : "solid",
			outLineWidth : 1,
			size : 10,
			backgroundColor : "#00FF00",
			backgroundOpacity : 0.75,
			symbolStyle : "circle"
		},
		defaultPolyline : {
			outLineWidth : 3,
			outLineColor : "#6666aa",
			outLineStyle : "Solid"
		},
		defaultPolygon : {
			outLineWidth : 1,
			outLineColor : "#6666aa",
			outLineStyle : "dashed",
			backgroundColor : "#9999aa",
			backgroundOpacity : 0
		},
		highLight : {
			outLineColor : "#00FFFF",
			outLineStyle : "solid",
			backgroundOpacity : 0
		}
	}
});