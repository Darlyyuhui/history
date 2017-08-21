MapFactory.Define("ItmsMap/SymbolConfig*",function(){

	var symbols =  {			
	peoSymbol: {
		url: "images/peocross.png",
		width: 20,
		height: 15,
		name: "人开口"
	},	
	carcrossSymbol: {
		url: "images/carcross.png",
		width: 15,
		height: 20,
		name: "车开口"
	},
	beginSymbol: {
		url: "images/start.png",
		width: 25,
		height: 25,
		name: " 开始点"
	},
	endSymbol: {
		url: "images/end.png",
		width: 25,
		height: 25,
		name: "结束点"
	},
	stopSymbol: {
		url: "images/stops.png",
		width: 20,
		height: 20,
		name: "停靠点"
	},
	carDetectorSymbol: {
		url: "images/cardetector.png",
		width: 25,
		height: 30,
		name: "车辆检测器"
	},
	blindSymbol: {
		url: "images/blind.png",
		width: 25,
		height: 30,
		name: "盲人过街装置"
	},
		
		/**
		 * 资产状态符号
		 */
		zcPowerErrorStyle: {
			url: "images/map/cross/cross_black.png",
			width: 24,
			height: 24,
			name: "供电异常"
		},
		zcNetErrorStyle: {
			url: "images/map/cross/cross_black.png",
			width: 24,
			height: 24,
			name: "网络异常"
		},
		zcDataErrorStyle: {
			url: "images/map/cross/cross_error.png",
			width: 24,
			height: 24,
			name: "数据异常"
		},
		zcNormalStyle: {
			url: "images/map/cross/cross_normal.png",
			width: 24,
			height: 24,
			name: "数据正常"
		},
		
		/**
		 * 位置标示符号
		 */
		positionSignSymbol: {
			url: "images/map/position1.png",
			width: 30,
			height: 45,
			name: "位置"
		},

		/**
		 * 位置标示符号
		 */
		orderSignSymbol: {
			url: "images/map/position3.png",
			width: 20,
			height: 27,
			xOffset: 0,
			yOffset: 15,
			name: "顺序标注"
		},
		
		
		/**
		 * 正常运行卡口图标
		 */
		normalCrossSymbol: {
			url: "images/map/cross/cross_normal.png", 
			width: 24, 
			height: 24,
			name: "正常"
		},

		/**
		 * 故障运行卡口图标
		 */
		unnormalCrossSymbol: {
			url: "images/map/cross/cross_error.png", 
			width: 24, 
			height: 24,
			name: "故障"
		},
		
		/**
		 * 卡口未接入图标
		 */
		blackCrossSymbol: {
			url: "images/map/cross/cross_black.png", 
			width: 24, 
			height: 24,
			name: "未接入"
		},

		/**
		 * 卡口报警图标
		 */
		alarmCrossSymbol: {
			url: "images/map/cross/cross_alarm.gif", 
			width: 32, 
			height: 32,
			name: "报警"
		},

		/**
		 * 直属停车场图标
		 */
		dirSymbol: {
			url: "images/map/dirparking.png",
			width: 24, 
			height: 24,
			name: "直属"
		},

		/**
		 * 带状态的停车场图标status:normal  black分别代表：正常，停用
		 */
		parkzsNormal: {
			url: "images/map/park/park_zhishu_normal.png",
			width: 24,
			height: 24,
			name: "正常"
		},
		parkzsBlack: {
			url: "images/map/park/park_zhishu_black.png",
			width: 24,
			height: 24,
			name: "停用"
		},

		/**
		 * 带状态的停车场图标status:free  mid  full  black分别代表：空闲，一般，饱满，停用
		 */
		parkFree: {
			url: "images/map/park/park_free.png",
			width: 24,
			height: 24,
			name: "空闲"
		},
		parkMid: {
			url: "images/map/park/park_mid.png",
			width: 24,
			height: 24,
			name: "一般"
		},
		parkFull: {
			url: "images/map/park/park_full.png",
			width: 24,
			height: 24,
			name: "饱满"
		},
		parkBlack: {
			url: "images/map/park/park_black.png",
			width: 24,
			height: 24,
			name: "停用"
		},

		/**
		 * 带类型的停车场诱导屏图标type: 1  2  3  control分别代表：一级，二级，三级，控制器
		 */
		leaddevice1: {
			url: "images/map/park/park_panel_1.png",
			width: 24,
			height: 24,
			name: "一级"
		},
		leaddevice2: {
			url: "images/map/park/park_panel_2.png",
			width: 24,
			height: 24,
			name: "二级"
		},
		leaddevice3: {
			url: "images/map/park/park_panel_3.png",
			width: 24,
			height: 24,
			name: "三级"
		},
		leaddeviceControl: {
			url: "images/map/park/park_panel_control.png",
			width: 24,
			height: 24,
			name: "控制器"
		},

		/**
		 * 普通停车场
		 */
		genSymbol: {
			url: "images/map/genparking.png", 
			width: 24, 
			height: 24,
			name: "普通"
		},

		/**
		 * 诱导屏图标
		 */
		leadSymbol: {
			url: "images/map/screen.png", 
			width: 24, 
			height: 24,
			name: "诱导屏"
		},

		/**
		 * 位置标示符号带偏移
		 */
		positionSymbolOffset: {
			
		},

		/**
		 * 加油站图标
		 */
		oilStation: {
			url: "images/map/oil.png",
			width: 30,
			height: 30,
			name: "加油站"
		},
		/**
		 * 卡口图标
		 */
		cross: {
			url: "images/map/device.png",
			width: 30,
			height: 30,
			name: "卡口"
		},

		/**
		 * 勤务网格巡查岗、重点岗、点岗、示范岗、未排班岗
		 */
		omGrid: {
			url: "images/map/om/grid.png",
			width: 30,
			height: 30,
			name: "网格巡查岗"
		},
		omImportant: {
			url: "images/map/om/import.png",
			width: 30,
			height: 30,
			name: "重点岗"
		},
		omPoint: {
			url: "images/map/om/police-dian.png",
			width: 30,
			height: 30,
			name: "点岗"
		},
		omShiFan: {
			url: "images/map/om/police-shifan.png",
			width: 30,
			height: 30,
			name: "示范岗"
		},
		omWeiPaiBan: {
			url: "images/map/om/weipaiban.png",
			width: 30,
			height: 30,
			name: "岗位未排班"
		},

		/**
		 * 勤务警员在线、离线
		 */
		omPoliceNormal: {
			url: "images/map/om/police-normal.png",
			width: 30,
			height: 30,
			name: "警员在线"
		},
		omPoliceOutline: {
			url: "images/map/om/police-outline.png",
			width: 30,
			height: 30,
			name: "警员离线"
		},

		/**
		 * 定位符号、高亮定位符号
		 */
		positionStyle: {
			url: "images/map/position3.png",
			width: 10,
			height: 15,
			xOffset: 0,
			yOffset: 10,
			name: "定位"
		},
		positionHighLightStyle: {
			url: "images/map/position4.png",
			width: 30,
			height:40,
			xOffset: 0,
			yOffset: 24,
			name: "高亮定位"
		},

		/**
		 * 视频符号--球机、枪机
		 */
		videoQiujiSymbol: {
			url: "images/map/video/qiuji_normal.png",
			width: 24,
			height: 24,
			name: "球机"
		},
		videoQiangjiSymbol: {
			url: "images/map/video/qiangji_normal.png",
			width: 24,
			height: 24,
			name: "枪机"
		},

		/**
		 * 违法符号，正常、故障、停用
		 */
		vioSymbol: {
			url: "images/map/vio/vio_normal.png",
			width: 24,
			height: 24,
			name: "正常"
		},
		vioErrorSymbol: {
			url: "images/map/vio/vio_error.png",
			width: 24,
			height: 24,
			name: "故障"
		},
		vioBlackSymbol: {
			url: "images/map/vio/vio_black.png",
			width: 24,
			height: 24,
			name: "未使用"
		},

		/**
		 * 通行证符号 饱满 一般 空闲 无数据
		 */
		passFullSymbol: {
			outLineWidth : 5,
			outLineColor : "#f00",
			name: "饱满"
		},
		passGenSymbol: {
			outLineWidth : 5,
			outLineColor : "#0f0",
			name: "一般"
		},
		passFreeSymbol: {
			outLineWidth : 5,
			outLineColor : "#00f",
			name: "空闲"
		},
		passNodataSymbol: {
			outLineWidth : 5,
			outLineColor : "#000000",
			name: "无数据"
		},

		/**
		 * gps符号
		 */
		gpsCarOnline: {
			url: "images/map/om/policecar-normal.png",
			width: 32,
			height: 32,
			name: "警车正常"
		},
		gpsCarOutline: {
			url: "images/map/om/policecar-outline.png",
			width: 32,
			height: 32,
			name: "警车离线"
		},
		gpsCarwarnline: {
			url: "images/map/om/policecar-warn.png",
			width: 32,
			height: 32,
			name: "警车越界"
		},
		gpsMotoOnline: {
			url: "images/map/om/moto-normal.png",
			width: 32,
			height: 32,
			name: "摩托正常"
		},
		gpsMotoOutline: {
			url: "images/map/om/moto-outline.png",
			width: 32,
			height: 32,
			name: "摩托离线"
		},
		gpsMotowarnline: {
			url: "images/map/om/moto-warn.png",
			width: 32,
			height: 32,
			name: "摩托越界"
		},
		gpsPoliceOnline: {
			url: "images/map/om/police-normal.png",
			width: 32,
			height: 32,
			name: "警员正常"
		},
		gpsPoliceOutline: {
			url: "images/map/om/police-outline.png",
			width: 32,
			height: 32,
			name: "警员离线"
		},
		gpsPolicewarnline: {
			url: "images/map/om/police-warn.png",
			width: 32,
			height: 32,
			name: "警员越界"
		},
		gpsTruckOnline: {
			url: "images/map/om/truck-normal.png",
			width: 32,
			height: 32,
			name: "货车正常"
		},
		gpsTruckOutline: {
			url: "images/map/om/truck-outline.png",
			width: 32,
			height: 32,
			name: "货车离线"
		},
		gpsTruckwarnline: {
			url: "images/map/om/truck-warn.png",
			width: 32,
			height: 32,
			name: "货车越界"
		},
		/**
		* 流量管理
		*/
		flowDeviceNormal: {
			url: "images/map/flow/flow_device_normal.png",
			width: 24,
			height: 24,
			name: "监测器"
		},
		flowDeviceError: {
			url: "images/map/flow/flow_device_error.png",
			width: 24,
			height: 24,
			name: "监测器故障"
		},
		flowDeviceBlack: {
			url: "images/map/flow/flow_device_black.png",
			width: 24,
			height: 24,
			name: "监测器未接入"
		},
		flowPanel: {
			url: "images/map/flow/flow_panel.png",
			width: 24,
			height: 24,
			name: "流量诱导屏"
		},
		flowFullSymbol: {
			outLineWidth : 2,
			outLineColor : "#B70503",
			backgroundColor : "#B70503",
			backgroundOpacity : 1,
			name: "阻塞"
		},
		flowGenSymbol: {
			outLineWidth : 2,
			outLineColor : "#EB9B28",
			backgroundColor : "#EB9B28",
			backgroundOpacity : 1,
			name: "拥堵"
		},
		flowFreeSymbol: {
			outLineWidth : 2,
			outLineColor : "#17BD05",
			backgroundColor : "#17BD05",
			backgroundOpacity : 1,
			name: "畅通"
		},
		flowNodataSymbol: {
			outLineWidth : 2,
			outLineColor : "#000000",
			backgroundColor : "#000000",
			backgroundOpacity : 1,
			name: "无数据"
		},

		/**
		* 道路挖占
		*/
		occupy: {
			url: "images/map/occupy/occupy.png",
			width: 32,
			height: 32,
			name: "挖占图标"
		},

		/**
		 * 街景图标
		 */
		streetview: {
			url: "images/threeDimension/camera.png",
			width: 24,
			height: 24,
			name: "街景"
 		},

		/**
		 * 挖占图例
		 */
 		subway_zsg: {
			url: "images/map/occupy/subway_zsg.png",
			width: 32,
			height: 32,
			name: "地铁-施工中"
		},
		subway_kdq: {
			url: "images/map/occupy/subway_kdq.png",
			width: 32,
			height: 32,
			name: "地铁-快到期"
		},
		subway_yyq: {
			url: "images/map/occupy/subway_yyq.png",
			width: 32,
			height: 32,
			name: "地铁-已延期"
		},
		subway_ydq: {
			url: "images/map/occupy/subway_ydq.png",
			width: 32,
			height: 32,
			name: "地铁-已到期"
		},
		subway_wkg: {
			url: "images/map/occupy/subway_wkg.png",
			width: 32,
			height: 32,
			name: "地铁-未开工"
		},
		electricity_ydq: {
			url: "images/map/occupy/electricity_ydq.png",
			width: 32,
			height: 32,
			name: "电力-已到期"
		},
		electricity_yyq: {
			url: "images/map/occupy/electricity_yyq.png",
			width: 32,
			height: 32,
			name: "电力-已延期"
		},
		electricity_kdq: {
			url: "images/map/occupy/electricity_kdq.png",
			width: 32,
			height: 32,
			name: "电力-快到期"
		},
		electricity_zsg: {
			url: "images/map/occupy/electricity_zsg.png",
			width: 32,
			height: 32,
			name: "电力-施工中"
		},
		electricity_wkg: {
			url: "images/map/occupy/electricity_wkg.png",
			width: 32,
			height: 32,
			name: "电力-未开工"
		},
		gas_wkg: {
			url: "images/map/occupy/gas_wkg.png",
			width: 32,
			height: 32,
			name: "天然气-未开工"
		},
		gas_zsg: {
			url: "images/map/occupy/gas_zsg.png",
			width: 32,
			height: 32,
			name: "天然气-施工中"
		},
		gas_kdq: {
			url: "images/map/occupy/gas_kdq.png",
			width: 32,
			height: 32,
			name: "天然气-快到期"
		},
		gas_yyq: {
			url: "images/map/occupy/gas_yyq.png",
			width: 32,
			height: 32,
			name: "天然气-已延期"
		},
		gas_ydq: {
			url: "images/map/occupy/gas_ydq.png",
			width: 32,
			height: 32,
			name: "天然气-已到期"
		},
		heat_ydq: {
			url: "images/map/occupy/heat_ydq.png",
			width: 32,
			height: 32,
			name: "热力-已到期"
		},
		heat_yyq: {
			url: "images/map/occupy/heat_yyq.png",
			width: 32,
			height: 32,
			name: "热力-已延期"
		},
		heat_kdq: {
			url: "images/map/occupy/heat_kdq.png",
			width: 32,
			height: 32,
			name: "热力-快到期"
		},
		heat_zsg: {
			url: "images/map/occupy/heat_zsg.png",
			width: 32,
			height: 32,
			name: "热力-施工中"
		},
		heat_wkg: {
			url: "images/map/occupy/heat_wkg.png",
			width: 32,
			height: 32,
			name: "热力-未开工"
		},
		pipe_wkg: {
			url: "images/map/occupy/pipe_wkg.png",
			width: 32,
			height: 32,
			name: "管道-未开工"
		},
		pipe_zsg: {
			url: "images/map/occupy/pipe_zsg.png",
			width: 32,
			height: 32,
			name: "管道-施工中"
		},
		pipe_kdq: {
			url: "images/map/occupy/pipe_kdq.png",
			width: 32,
			height: 32,
			name: "管道-快到期"
		},
		pipe_yyq: {
			url: "images/map/occupy/pipe_yyq.png",
			width: 32,
			height: 32,
			name: "管道-已延期"
		},
		pipe_ydq: {
			url: "images/map/occupy/pipe_ydq.png",
			width: 32,
			height: 32,
			name: "管道-已到期"
		},
		shizheng_ydq: {
			url: "images/map/occupy/shizheng_ydq.png",
			width: 32,
			height: 32,
			name: "市政-已到期"
		},
		shizheng_yyq: {
			url: "images/map/occupy/shizheng_yyq.png",
			width: 32,
			height: 32,
			name: "市政--已延期"
		},
		shizheng_kdq: {
			url: "images/map/occupy/shizheng_kdq.png",
			width: 32,
			height: 32,
			name: "市政-快到期"
		},
		shizheng_zsg: {
			url: "images/map/occupy/shizheng_zsg.png",
			width: 32,
			height: 32,
			name: "市政-施工中"
		},
		shizheng_wkg: {
			url: "images/map/occupy/shizheng_wkg.png",
			width: 32,
			height: 32,
			name: "市政-未开工"
		},
		signal:{
			url: "images/map/singal.png",
			width: 32,
			height: 32,
			name: "信号灯"			
		}
	};

	return symbols;
});