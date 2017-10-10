MapFactory.Define("ItmsMap/SymbolConfig*", function() {
	var symbols = {
			
			//自己添加的符号
		wrySymbol : {
			url : path + "/images/renderIcon/wry.png",
			width : 34,
			height : 38,
			xOffset : 0,
			yOffset : 0,
			name : "污染源"
		},
			//自己添加的符号
	    phEqureFace : {
			outLineWidth : 0,
			outlineOpacity:0,
			outLineColor : "#000000",
			outlineStyle:"solid",
			backgroundColor : "#669966",
			backgroundOpacity : 1
		},
		//自己添加的符号
		regionPolluteLevel1 : {
			outLineWidth : 1,
			outlineOpacity:0,
			outLineColor : "#000000",
			outlineStyle:"dot",
			backgroundColor : "#669966",
			backgroundOpacity : 0,
			name : "行政区域区块符号1"
		},
		regionPolluteLevel2 : {
			outLineWidth : 1,
			outlineOpacity:0,
			outLineColor : "#000000",
			outlineStyle:"dot",
			backgroundColor : "#FFD700",
			backgroundOpacity : 0,
			name : "行政区域区块符号2"
		},
		regionPolluteLevel3 : {
			outLineWidth : 1,
			outlineOpacity:0,
			outLineColor : "#000000",
			outlineStyle:"dot",
			backgroundColor : "#FF7F00",
			backgroundOpacity : 0,
			name : "行政区域区块符号3"
		},
		regionPolluteLevel4 : {
			outLineWidth : 1,
			outlineOpacity:0,
			outLineColor : "#000000",
			outlineStyle:"dot",
			backgroundColor : "#EE0000",
			backgroundOpacity : 0,
			name : "行政区域区块符号4"
		},
		regionPolognSymbol : {
			outLineWidth : 0,
			outlineOpacity:0,
			outLineColor : "#000000",
			outlineStyle:"dot",
			backgroundColor : "#133B69",
			backgroundOpacity : 0.5,
			name : "高亮符号"
		},
		ydjkSymbol : {
			url : path + "/images/renderIcon/ydjk.png",
			width : 34,
			height : 39,
			xOffset : 0,
			yOffset : 0,
			name : "样地监控"
		},
		nchpPointSymbolByG : {
			url : path + "/images/renderIcon/ncpjd1.png",
			width : 27,
			height : 38,
			xOffset : 0,
			yOffset : 0,
			name : "农产品基地根据镉渲染"
		},
		nchpPointSymbol : {
			url : path + "/images/renderIcon/ncpjd.png",
			width : 34,
			height : 39,
			xOffset : 0,
			yOffset : 0,
			name : "农产品基地"
		},
		//采样点
		cydSymbol : {
			url : path + "/images/renderIcon/",
			width : 27,
			height : 38,
			xOffset : 0,
			yOffset : 0,
			name : "采样点"
		},
		regionPointSymbol : {
			outLineWidth : 1,
			outLineColor : "#76EE00",
			backgroundColor : "#76EE00",
			backgroundOpacity : 0.7,
			width:14,
            height:14,
			name : "行政区域点符号"
		},
		//各个行政区采样点统计
		regionPointCountSymbol : {
			text : "",
			textFontFamily : "微软雅黑",
			textStyle : "normal",
			textWeight : "normal",
			textColor :"#000000",
			xOffset:0,
			yOffset:0
		},
		//聚合点样式
		 jh1:{
            "count":2,//子节点小于等于2的聚合点
            "style":{
                fontColor:"#404040",
                graphic:true,
                externalGraphic:path+"/compnents/MapFrame/MapFactory/ThirdParty/SuperMapLib/theme/images/cluster3.png",
                graphicWidth:37,
                graphicHeight:38,
                labelXOffset:-4,
                labelYOffset:5
            }
        },
        jh2:{
            "count":5,//子节点小于等于5大于2的聚合点
            "style":{
                fontColor:"#404040",
                graphic:true,
                externalGraphic:path+"/compnents/MapFrame/MapFactory/ThirdParty/SuperMapLib/theme/images/cluster2.png",
                graphicWidth:41,
                graphicHeight:46,
                labelXOffset:-3,
                labelYOffset:6
            }
        },
        jh3:{
            "count":"moreThanMax",// 子节点大于5的聚合点
            "style":{
                fontColor:"#404040",
                graphic:true,
                externalGraphic:path+"/compnents/MapFrame/MapFactory/ThirdParty/SuperMapLib/theme/images/cluster1.png",
                graphicWidth:48,
                graphicHeight:53,
                labelXOffset:-5,
                labelYOffset:8
            }
        },
        landSymbol : {
			outLineWidth : 2,
			outlineOpacity:1,
			outLineColor : "#9cd000",
			outlineStyle:"solid",
			backgroundColor : "#aeff00",
			backgroundOpacity : 0.5,
			name : "地块符号"
		},
		apblandSymbol : {
			outLineWidth : 2,
			outlineOpacity:1,
			outLineColor : "#9cd000",
			outlineStyle:"solid",
			backgroundColor : "#00FF00",
			backgroundOpacity : 0.5,
			name : "基地符号"
		},
		landInfoSymbol : {
			url : path + "/images/renderIcon/landinfo.png",
			width : 183,
			height : 88,
			xOffset : 88,
			yOffset : 43,
			name : "地块信息背景样式"
		},
		//各个污染区块详细信息
		landInfoTextSymbol: {
			text : "",
			textFontFamily : "微软雅黑",
			textStyle : "normal",
			textWeight : "bold",
			textColor :"#FFFFFF",
			xOffset:96,
			yOffset:65
		},
		//-------------------------------------------
        //-------------------------------------------
        //-------------------------------------------
		waterSymbol : {
			outLineWidth : 2,
			outLineColor : "#428bca",
			outlineOpacity : 1,
			backgroundColor : "#9999aa",
			backgroundOpacity : 0,
			name : "水资源保护区"
		},

		ndSymbol : {
			outLineWidth : 2,
			outLineColor : "#d47829",
			backgroundColor : "#d47829",
			backgroundOpacity : 0.4,
			name : "矿区"
		},
		//视频设备
		videoOnlineSymbol : {
			url : path + "/images/video_online.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "正常"
		},
		videoOfflineSymbol : {
			url : path + "/images/video_offline.gif",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "异常"
		},
		videoUnconnectedSymbol : {
			url : path + "/images/video_unconnected.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "未接入"
		},
		//油烟设备
		lampOnlineSymbol : {
			url : path + "/images/lamp_online.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "正常"
		},
		lampOfflineSymbol : {
			url : path + "/images/lamp_offline.gif",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "异常"
		},
		lampUnconnectedSymbol : {
			url : path + "/images/lamp_unconnected.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "未接入"
		},
		//声尘设备
		ndOnlineSymbol : {
			url : path + "/images/nd_online.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "正常"
		},
		ndOfflineSymbol : {
			url : path + "/images/nd_offline.gif",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "异常"
		},
		ndUnconnectedSymbol : {
			url : path + "/images/nd_unconnected.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "未接入"
		},
		//企业
		enterStartSymbol : {
			url : path + "/images/enter_start.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "在产"
		},
		enterStopSymbol : {
			url : path + "/images/enter_stop.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "停产"
		},
		enterPauseSymbol : {
			url : path + "/images/enter_pause.gif",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "整顿"
		},
		//空气质量设备
		airOnlineSymbol : {
			url : path + "/images/air_online.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "正常"
		},
		airOfflineSymbol : {
			url : path + "/images/air_offline.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "异常"
		},
		airUnconnectedSymbol : {
			url : path + "/images/air_unconnected.png",
			width : 26,
			height : 39,
			xOffset : 0,
			yOffset : 18,
			name : "未接入"
		},
		//工况设备
		gkOnlineSymbol : {
			url : path + "/images/gk_online.png",
			width : 24,
			height : 23,
			name : "正常"
		},
		gkOfflineSymbol : {
			url : path + "/images/gk_offline.png",
			width : 24,
			height : 23,
			name : "异常"
		},
		gkUnconnectedSymbol : {
			url : path + "/images/gk_unconnected.png",
			width : 24,
			height : 23,
			name : "未接入"
		},
		/**
		 * 位置标示符号
		 */
		positionSignSymbol : {
			url : "images/map/position1.png",
			width : 30,
			height : 45,
			name : "位置"
		},
		/**
		 * 位置标示符号
		 */
		orderSignSymbol : {
			url : "images/map/position3.png",
			width : 20,
			height : 27,
			xOffset : 0,
			yOffset : 15,
			name : "顺序标注"
		},
		/**
		 * 定位符号、高亮定位符号
		 */
		positionStyle : {
			url : "images/map/position3.png",
			width : 10,
			height : 15,
			xOffset : 0,
			yOffset : 10,
			name : "定位"
		},
		positionHighLightStyle : {
			url : "images/map/position4.png",
			width : 30,
			height : 40,
			xOffset : 0,
			yOffset : 24,
			name : "高亮定位"
		},
		getlenged : function() {
			//			var legend=[{name:"视频",urls:[this.videoOnlineSymbol,this.videoOfflineSymbol,this.videoUnconnectedSymbol]},
			//						{name:"油烟",urls:[this.lampOnlineSymbol,this.lampOfflineSymbol,this.lampUnconnectedSymbol]},
			//						{name:"声尘",urls:[this.ndOnlineSymbol,this.ndOfflineSymbol,this.ndUnconnectedSymbol]},
			//						{name:"企业",urls:[this.enterStartSymbol,this.enterPauseSymbol,this.enterStopSymbol]}
			//						];
			var legend = [ {
				name : "视频",
				urls : [ this.videoOnlineSymbol ]
			}, {
				name : "油烟",
				urls : [ this.lampOnlineSymbol ]
			}, {
				name : "声尘",
				urls : [ this.ndOnlineSymbol ]
			}, {
				name : "企业",
				urls : [ this.enterStartSymbol ]
			} ];
			return legend;
		}
	};
	return symbols;
});