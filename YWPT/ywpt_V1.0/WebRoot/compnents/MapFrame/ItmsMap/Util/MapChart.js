/**
 * 传入数据格式
 * var data = [
 *		{name:"大队",total:"10"},
 *		{name:"中队",total:"37"},
 *		{name:"网格",total:"67"},
 *		{name:"示范岗",total:"4"},
 *		{name:"点岗",total:"31"},
 *		{name:"时段岗",total:"34"},
 *		{name:"重点保障路段",total:"61"},
 *		{name:"网格机动岗",total:"57"}
 * ];
 */
MapFactory.Define("ItmsMap/Util/MapChart*",function(){
	return function(){

		var api = {
			init : init
		}

		//静态变量,flash的存储路径
		var graphPath = path+"/compnents/fusioncharts/chart/";

		//Flash图表的颜色
		var color = ["AFD8F8", "51DCFF","5F96FC", "8BBA00", "FF8E46","008E8E",
					"D64646", "8E468E", "588526", "B3AA00", "008ED6","9D080D",
					"A186BE", "346DB2", "C13A36", "A02F2B", "7C9D35","6A4A8D",
					"2B8CAC", "D2721E", "7792C1", "BF7B7A", "A6BC7C","9183A7"];

		//图表类型
		var graphs = {Pie2D:"Pie2D.swf",Pie3D:"Pie3D.swf"};

		var container = "";

		// 初始化
		function init(conf){
			if(!conf){
				return;
			}
			if(conf.src){
				container = $("#"+conf.src);
			}
			if(conf.data){
				createChart(conf.data);
			}
		}

		// 将数据转换为需要的xml
		function ConvertToXML(res){
			var xml = "<graph formatNumber='1'  numberSuffix='个'  rotateYAxisName='0' "+
					"baseFont='宋体'  baseFontSize='12'  outCnvBaseFont='宋体'  outCnvBaseFontSize='8'  decimalPrecision='2' " +
					" chartLeftMargin='0' chartRightMargin='0' showLabels='1' labelDisplay='2' showValues='0'  enableSmartLabels='1'  enableRotation='1'  animation='1' " +
					" formatNumberScale='0' showPercentageValues='0'  canvasBorderThickness='0' startingAngle='45' chartBottomMargin='0' bgColor='#ffffff'>";			
			for(var i=0; i<res.length; i++){
				xml += "<set name='" + res[i].name + "' value='" + res[i].total + "' color='" + color[i%24] + "'></set>";
			}
			xml += "</graph>";
			return xml;
		}

		//创建饼状图
		function createChart(data){
			var chartSwfPath = graphPath + graphs["Pie2D"] + "?ChartNoDataText='无数据显示'";
			var chart = new FusionCharts(chartSwfPath,"Chart2Id","100%","100%");
			var xml = ConvertToXML(data);
			chart.setDataXML(xml);
			container.empty();
			$("<div id='graphLoader' style='width:100%;height:210px;'></div>").appendTo(container);
			chart.render("graphLoader");
		}

		return api;
	}
});