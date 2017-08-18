MapFactory.Define("ItmsMap/UserLayers/CustomLayersConfig*",function(){
	return {
        "region" : {
            "label" : "行政区域",
            "class" : "Regions",
            "isOpen" : true,
            "layers" : {
                "xzqyLyrPoint": {
                    isOpen: true
                }
            }
        },
        "nchp" : {
            "label" : "农产品基地",
            "class" : "AgrBase",
            "isOpen" : true,
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
            "layers" : {
                "analysis": {
                    isOpen: false
                }
            }
        }
        
	};
});