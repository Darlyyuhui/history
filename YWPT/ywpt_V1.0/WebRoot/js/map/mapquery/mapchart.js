/**
 *@author LHF
 * @
 * 
 */
var MapChart=(function(){		
	return function(){
		
		//静态变量,flash的存储路径
		var graphPath = path+"/compnents/fusioncharts/chart/",
		
		strXML = "",	
		
		//Flash图表的颜色
		color = ["AFD8F8", "51DCFF","5F96FC", "8BBA00", "FF8E46",
				"008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6",
				"9D080D", "A186BE", "346DB2", "C13A36", "A02F2B", "7C9D35",
				"6A4A8D", "2B8CAC", "D2721E", "7792C1", "BF7B7A", "A6BC7C",
				"9183A7" ],
				
		//图表类型
		graphs = {
					Pie2D:"Pie2D.swf",
					Pie3D:"Pie3D.swf"
				 },
		
		//创建图表的参数
		attributes = [],
		
		//存储不重复的属性字段名
		keyArray = [],
		
		//事件管理器
//		eventsManager = {},
		//图表位置
		x,y,rightX,topY;
		
				
		var api={
			createChart:createChart,
			setParams:setParams,
			setLoc:setLoc
		}
		
		function setParams(inAttr,category){
			var tempAttr;
			if(!inAttr || Object.prototype.toString.call(inAttr) !== "[object Array]" || !category || category === "")return;
			
			//传入featureset
			for(var i=0; i<inAttr.length; i++){
				tempAttr = inAttr[i][category]||inAttr[i]["attributes"][category]|| "lost";
				attributes.push(tempAttr);
			}

//			console.log("setParams");
			return api;
			
		}
		
		function statistic(){
			var results = {};
			
			for(var i=0; i<attributes.length; i++){
				if(results.hasOwnProperty(attributes[i]))
				{
					results[attributes[i]]++;
				}else{
					keyArray.push(attributes[i]);
					results[attributes[i]] = 1;
				}
			}
//			console.log(results);
//			console.log(keyArray);
			return results;
			
		}
		
		//将统计好的数据转换成XML
		function ConvertToXML(res){			
			var xml="<graph formatNumber='1'  numberSuffix='个'  rotateYAxisName='0' "+
					"baseFont='宋体'  baseFontSize='12'  outCnvBaseFont='宋体'  outCnvBaseFontSize='8'  decimalPrecision='2' " +
					" chartLeftMargin='0'  shownames='1'  showValues='1'  enableSmartLabels='1'  enableRotation='1'  animation='1' " +
					" formatNumberScale='0' showPercentageValues='0'  canvasBorderThickness='0' startingAngle='45' chartBottomMargin='0' bgColor='#ffffff'>";			
			for(var i=0; i<keyArray.length; i++){				
				xml += "<set name='" + keyArray[i] + "' value='"+res[keyArray[i]]+"' color='"+ color[i%24] + "' /> ";
			}			
			xml+="</graph>";			
			return xml;
		}
	
		
		//创建饼状图。需要两个参数featureSet和category
		function createChart(){
			var chartSwfPath = graphPath + graphs["Pie2D"] + "?ChartNoDataText='无数据显示'",
		    chart = new FusionCharts(chartSwfPath, "Chart2Id", "100%", "90%"),
			xml = ConvertToXML(statistic());
			chart.setDataXML(xml);	
			chart.render("graphLoader");
			$("#graphLoader").css("display","block");
			return api;
		}
		
		
		//设置饼状图的位置,以right和top值为准		
		function setLoc(rightLoc,topLoc){
			$("#graphLoader").css({right:rightLoc,top:topLoc});
			return api;
		}
		
		(function(){
			//添加装载饼状图的div
			var htmlContent = "<div id='graphLoader' style='width:100%;height:210px;" +
					"display:none;border:0px'></div>";
			$("#mapOther").css("display", "block");
			$("#mapOther").append(htmlContent);
//			//初始化监听，使饼状图可以拖动
//			$("#graphLoader").bind("mousedown",function(e){
//				x = e.clientX;
//				y = e.clientY;
//				topY = parseInt($(this).css("top"));
//				rightX = parseInt($(this).css("right"));	
//				$(this).bind("mousemove",moveHandler);
//				return false;
//			});	
//			
//			$("#graphLoader").bind("mouseup",function(e){
//				$(this).unbind("mousemove",moveHandler);
//				return false;
//			});
		})();
		
		function moveHandler(e){
			var rightLocation = rightX + x - e.clientX,
			topLocation = topY + e.clientY - y;
			$(this).css({top:topLocation,right:rightLocation});
			return false;
		}
				
		return api;
	}
})()