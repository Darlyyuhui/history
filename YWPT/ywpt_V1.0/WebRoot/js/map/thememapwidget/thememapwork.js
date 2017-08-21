/**
 * @author ZLT
 * @Date 2013-7-16
 */
//(function() {
	/**
	 * step 1 : 点击按钮，询问用户是否需要框选区域,区域选定之后，点击确认进入下一步
	 * step 2 : 确认要输出的范围
	 * step 3 : 添加图名等参数
	 * step 4 : 选择输出模板
	 * step 5 : 提交
	 */
	//dojo.ready(function() {
var themeMapWork = function(){
		// init
		var eventButton = $("#drawButton");
		var tmw = ThemeMapWidget({
			basemap : itmap.arcgis.map,
			extentEndCall : extentEndCallFunc
		});
		/**
		 * 地图输出参数设置
		 */
		var params = {
			title : '',
			extent : '',
			templateIndex : '0',
			mapPoint : '',
			mapPolyline : '',
			mapPolygon : ''
		};
		
		// step 1
		if(eventButton[0]!=null){
			eventButton.click(function(){
				//map.graphics.clear();
				itmap.util.dialog({
						maskClick : true,
						title : '第一步:框选区域',
						content : "<div style='width:250px;text-align:center;height:80px;line-height:80px;'>您是否需要框选区域范围</div>",
						confirmButton : "需要",
						confirmButtonCall : step1_confirmButton_event,
						cancelButton : "跳过",
						cancelButtonCall : step1_cancelButton_event
				}).show();
					
				// 点击需要按钮触发事件
				function step1_confirmButton_event(e) {
					itmap.util.dialog().hide();
					tmw.init().beginSeletionBox();
				}
		
				// 点击不需要按钮触发事件
				function step1_cancelButton_event(e) {
					itmap.util.dialog().hide();
					tmw.init();
					extentEndCallFunc();
				}
			});
		}
		
		
		//if(eventButton[0] != null){
		//}

		// step2

		// 框选完成之后触发函数
		function extentEndCallFunc(e) {
			itmap.util.dialog({
				maskClick : true,
				title : '第二步：确定范围',
				content : "<div style='width:250px;text-align:center;height:80px;line-height:80px;'>确定是这个范围？</div>",
				confirmButton : "下一步",
				confirmButtonCall : step2_confirmButton_event,
				cancelButton : "重选",
				cancelButtonCall : step2_cancelButton_event
			}).show();
		}

		// 点击确定后的触发函数
		function step2_confirmButton_event() {
			var setParamsContent = "<table style='width:280px;height:80px;'><tr><td>图名：</td><td><input type='text' name='mapTitle' value='西安翔迅样图' id='themeMapTitle'></td></tr></table>";
			itmap.util.dialog({
				title : '第三步：地图参数设置',
				maskClick : true,
				content : setParamsContent,
				confirmButton : "下一步",
				confirmButtonCall : step3_confirmButton_event,
				cancelButton : "取消",
				cancelButtonCall : ''
			}).show();
			params.extent = myMap.extent;
		}

		// 点击重选的触发函数
		function step2_cancelButton_event() {
			itmap.util.dialog().hide();
			itmap.arcgis.graphics.clear();
			tmw.beginSeletionBox();
		}

		// 输入参数后，选择模板
		function step3_confirmButton_event() {
			params.title = document.getElementById("themeMapTitle").value;
			var setParamsContent = "<div style='width:500px;'><table id='mapLayoutTemplate' class='mapLayoutTemplate'><tr><td><img src='images/map/template_1.jpg'></td><td><img src='images/map/template_2.jpg'></td><td><img src='images/map/template_3.jpg'></td></tr></table></div>";
			itmap.util.dialog({
				title : '第四步：地图模板选择',
				maskClick : true,
				content : setParamsContent,
				confirmButton : "提交",
				confirmButtonCall : step4_confirmButton_event,
				cancelButton : "取消",
				cancelButtonCall : ''
			}).show();

			// 为模板图片绑定点击函数
			var templateImages = document.getElementById("mapLayoutTemplate").getElementsByTagName('img');
			if(params.templateIndex) {
				templateImages[params.templateIndex].parentNode.className = "selectedNode";
			}
			for(var i = 0; i < templateImages.length; i++) {
				templateImages[i].onclick = function(e) {
					for(var j = 0; j < templateImages.length; j++) {
						if(this == templateImages[j]) {
							params.templateIndex = j;
							this.parentNode.className = "selectedNode";
						} else {
							templateImages[j].parentNode.className = "";
						}
					}
				}
			}

		}

		// 设置完地图参数确定后触发函数
		function step4_confirmButton_event() {
			var pointSet = new esri.tasks.FeatureSet();
			var polyLineSet = new esri.tasks.FeatureSet();
			var polygonSet = new esri.tasks.FeatureSet();
			var pointFeatures = [];
			var polyLineFeatures = [];
			var polygonFeatures = [];
			var graphics = myMap.graphics.graphics;
			
			for(var i = 0;i<graphics.length;i++){
				if(graphics[i].geometry.type == "point"){
					pointFeatures.push(graphics[i]);
				}else if(graphics[i].geometry.type == "polyline"){
					polyLineFeatures.push(graphics[i]);
				}else if(graphics[i].geometry.type == "polygon"){
					polygonFeatures.push(graphics[i]);
				}
			}
			
			var graphicLayers = itmap.arcgis.mapGraphicManager().listGraphicLayer();
			for(var i=0,len=graphicLayers.length;i<len;i++){
				var layer = graphicLayers[i].layer;
				if(!layer){
					continue;
				}
				var graphics = layer.graphics;
				if(!graphics || !graphics.length){
					continue;
				}
				for(var j = 0;j<graphics.length;j++){
					if(graphics[j].geometry.type == "point"){
						pointFeatures.push(graphics[j]);
					}else if(graphics[j].geometry.type == "polyline"){
						polyLineFeatures.push(graphics[j]);
					}else if(graphics[j].geometry.type == "polygon"){
						polygonFeatures.push(graphics[j]);
					}
				}
			}
			
			pointSet.features = pointFeatures;
			pointSet.spatialReference = myMap.spatialReference;
			pointSet.geometryType = "esriGeometryPoint";
			
			polyLineSet.features = polyLineFeatures;
			polyLineSet.spatialReference = myMap.spatialReference;
			pointSet.geometryType = "esriGeometryPolyline";
			
			polygonSet.features = polygonFeatures;
			polygonSet.spatialReference = myMap.spatialReference;
			pointSet.geometryType = "esriGeometryPolygon";
			
			params.mapPoint = pointSet;
			params.mapPolyline = polyLineSet;
			params.mapPolygon = polygonSet;

			tmw.setMapParameters(params).exportMap();
			var dialog = new itmap.util.dialog({
				title : '完成：正在提交',
				maskClick : true,
				content : "<div style='width:500px;height:150px;line-height:150px;text-align:center;'><img src='images/loading.gif'/>处理中...</div>",
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				}
			});
			dialog.show();
			
			var timeoutFunc = null;
			var time = 0;
			
			// 任务执行进度
			function onStatus(){
				timeoutFunc = setTimeout(onStatus,500);
				time++;
				if(time>60){
					clearTimeout(timeoutFunc);
					dialog.setDialogTitle('完成');
					dialog.setDialogContent("超时");
				}
				//console.log('tmw.getJobStatus : '+tmw.getJobStatus()+" const : "+tmw.STATUS_SUBMIT);
				if(tmw.getJobStatus() == tmw.STATUS_SUBMIT){
					dialog.setDialogTitle('正在提交');
				}else if(tmw.getJobStatus() == tmw.STATUS_EXECUTING){
					dialog.setDialogTitle('正在执行...');
				}else if(tmw.getJobStatus() == tmw.STATUS_SUCCEEDED){
					//var succeedTitle = '<span style="float:left;">完成</span><span style="float:right;"><a href="'+tmw.getResultUrl()+'">下载图片</a></span>';
					//dialog.setDialogTitle(succeedTitle);
					if(tmw.getResultUrl() != ""){
						dialog.setDialogTitle('完成');
						clearTimeout(timeoutFunc);
						dialog.setDialogContent("<img width=600 height=400 src='"+tmw.getResultUrl()+"' style='width:600px;height:400px;'/>");
					}
				}else if(tmw.getJobStatus() == tmw.STATUS_FAILED){
					dialog.setDialogTitle('完成');
					clearTimeout(timeoutFunc);
					dialog.setDialogContent("失败");
				}
			}
			onStatus();
			
		}
}
	//})
//})();
