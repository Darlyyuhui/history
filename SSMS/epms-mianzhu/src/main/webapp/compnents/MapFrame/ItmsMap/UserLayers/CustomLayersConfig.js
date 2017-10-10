MapFactory.Define("ItmsMap/UserLayers/CustomLayersConfig*",function(){
	return {
		
		"baseLyr" : {
            "label" : "水系示意图",
            "class" : "BaseLyr",
            "isOpen" : false,
            "isChildren":false,
            "isShowCol":false,
            "isShowAC":true,
            "layers" : {
                "riverLyr": {
                    isOpen: false
                }
            }
        },
        "region" : {
            "label" : "行政区域",
            "class" : "Regions",
            "isOpen" : true,
            "isChildren":true,
            "isShowCol":true,
            "isShowAC":true,
            "layers" : {
                "xzqyLyrPoint": {
                    isOpen: true
                }
            }
        },
        "nchp" : {
            "label" : "农产品基地",
            "class" : "AgrBase",
            "isChildren":true,
            "isOpen" : true,
            "isShowCol":true,
            "isShowAC":true,
            "layers" : {
                "lcpjdLyrPoint": {
                    isOpen: true
                }
            }
        },
        
        "cyd" : {
            "label" : "普查采样",
            "class" : "SamplingPoint",
            "isOpen" : true,
            "isChildren":true,
            "isShowCol":true,
            "isShowAC":true,
            "layers" : {
                "pccydLayer": {
                    isOpen: true
                }
            }
        },
        
        "dy" : {
            "label" : "样地监控",
            "class" : "SampleMonitor",
            "isOpen" : true,
            "isChildren":true,
            "isShowCol":true,
            "isShowAC":true,
            "layers" : {
                "ydjkLayer": {
                    isOpen: true
                }
            }
        },
        "fx" : {
            "label" : "专题分析",
            "class" : "ZTAnalysis",
            "isOpen" : false,
            "isChildren":true,
            "isShowCol":true,
            "isShowAC":false,
            "layers" : {
                "analysis": {
                    isOpen: false
                }
            }
        },
        "cp" : {
            "label" : "污染源企业",
            "class" : "Company",
            "isOpen" : true,
            "isChildren":false,
            "isShowCol":false,
            "isShowAC":true,
            "layers" : {
                "wrcpLyr": {
                    isOpen: false
                }
            }
        }
        
	};
});