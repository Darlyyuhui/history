MapFactory.Define("ItmsMap/UserLayers/CustomLayersConfig*",function(){
	return {
		"qinwu" : {
			"label" : "勤务系统",
			"class" : "QinWu",
			"layers" : {
				"omUnorderedLayer" :{
					isOpen :false
				},
				"omStationLayer":{
					
				 },
				"omPoliceLayer":{
					
				},
				"omStationExtentsLayer":{
					
				}
			}
		},
		"gps" :{
			 "label" : "GPS系统",
			 "class" : "GPS",
			 "layers" : {
				"gps" :{
					isOpen :false
				  }			
			}
		},
		"kakou" : {
			"label" : "卡口系统",
			"class" : "Kakou",
			"isOpen" : false,
			"layers" : {
				"cross" : {
					isOpen : false
				},
				"crossAlarm":{
					isOpen : false
				}
			}
		},
		"weifa" : {
			"label" : "违法系统",
			"class" : "Weifa",
			"layers" : {
				"vioLayer" : {
					
				}
			}
		},
		"shipin" : {
			"label" : "视频管理",
			"class" : "Shipin",
			"layers" : {
				"videoLayer" : {
					
				}
			}
		},
		"liuliang" : {
			"label" : "流量分析",
			"class" : "Liuliang",
			"layers" : {
				"flow" : {
					
				},
				"flowDevice" : {
					
				},
				"flowPanel":{
					
				}
			}
		},
		"wazhan" :{
			"label" : "道路挖占",
			"class" : "Occupy",
			"layers" : {
				"unStart" :{
					isOpen : false
				},
				"constructing" :{
					isOpen : false
				},	
				"maturring" :{
					isOpen : false
				},	
				"defferring" :{
					isOpen : false
				},	
				"done" :{
					isOpen : false
				},	
				"matured" :{
					isOpen : false
				}
			}
		},
		"tingche" : {
			"label" : "停车诱导",
			"class" : "Tingche",
			"layers" : {
				"leadDevice" : {
					
				},
				"genParking" : {
					
				},
				"dirParking":{
					
				}
			}
		},
		"tongxingzheng" : {
			"label" : "通行证管理",
			"class" : "Pass",
			"layers" : {
				"passLayer" : {
		        	isOpen : false
				}				
			}
		},
		"basemap" : {
			"label" : "基础图层",
			"class" : "BaseMap",
			"isOpen" : true,
			"layers" : {
				"baseMap" : {
					isOpen : true
				}
			}
		}
	};
});