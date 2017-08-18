MapFactory.Define("ItmsMap/UserLayers/MPointConfig*",function(){
	return {
        dxs : "undergroundWaterNum", //地下水监测点
        dbs : "surfaceWaterNum", //地表灌溉水监测点
        dn  : "dirtNum", //底泥监测点
        dqcj: "airNum",  //大气沉降采样点
        bjtr: "backNum",  //背景土壤监测点
        nttr: "landNum",  //农田土壤监测点
        nzw : "farmNum",  //农作物监测点
        fl  : "manureNum" //肥料采集点
        };
});