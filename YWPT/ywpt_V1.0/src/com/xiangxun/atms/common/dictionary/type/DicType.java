package com.xiangxun.atms.common.dictionary.type;


/**
 * 数据字典类型枚举类
 * 
 * @author zhouhaij
 * 
 */
public enum DicType {
	//车辆品牌
	CAR_BRAND("carbrand"),
	//车牌类型
	CAR_CLASS("carclass"),
	//车辆颜色
	CAR_COLOR("carcolor"),
	//号牌颜色
	PLATE_COLOR("platecolor"),
	//地市号牌对照表
	PLATE_AREA("platearea"),
	//号牌类型
	PLATE_TYPE("platetype"),
	//车辆状态
	CAR_STATE("carstate"),
	//车辆类型
	CAR_TYPE("cartype"),
	//设备类型
	DEVICE_TYPE("devicetypes"),
	//车辆营运类型
	CAR_RUN_TYPE("runtype"),
	//网络营运商
	NET_COMPANY("netcompany"),
	//网络接入方式
	NET_IN_TYPE("netintype"),
	//方向表
	DIRECT("direction"),
	//道路状态表
	FLOWSTATUS("flowstatus"),
	//车道类型
	LANE_TYPE("lanetype"),
	//监控类型表
	MONITOR_TYPE("monittype"),
	//许可证类型
	PASSORT_TYPE("passorttype"),
	//各省简称对照表
	PROVICE_AREA("provicearea"),
	//道路类型
	ROAD_TYPE("roadtype"),
	//区域行政编码
	PLACEAREA_CODE("placeareacode"),
	//信号机功能分类
	SIGNALFUNCTION_CODE("signalfunction"),
	//信号机按安装环境
	SIGNALENV_CODE("signalenv"),
	//信号灯透光面尺寸
	SIGNALLIGHTAREA_CODE("signallightarea"),
	//信号灯外壳材料
	SIGNALESHELL_CODE("signalshell"),
	//信号灯光源类型
	SIGNALLIGHTSOURCE_CODE("signallightsource"),
	//信号灯功能分类
	SIGNALLAMPFUNCTION_CODE("signallampfunction"),
	//流量检测器分类
	FLOWTYPE_CODE("flowtype"),
	//路口类型
	SIGNALCROSSROADS_TYPE("signalcrossroadstype"),
	//信号倒计时器 显示方式分类
	SIGNALTIEMWORK_SHOWCLASSCODE("signaltimeworkshowclass"),
	//信号倒计时器 工作方式分类
	SIGNALTIEMWORK_WORKCLASSCODE("signaltimeworkworkclass"),
	//信号倒计时器 供电方式分类
	SIGNALTIEMWORK_POWERSOURCECLASSCODE("signaltimeworkpowerclass"),
	//信号倒计时器 数码显示位数
	SIGNALTIEMWORK_SHOWEXTENTCODE("signaltimeworkshowextent"),
	//信号控制模式
	SIGNAL_CONTROLMODEL("signalcontrolmodel"),
	//流量诱导屏级别编号
	FLOW_GUIDE_LEVEL_CODE("flowguidelevelcode"),
	//实时路况
	FLOW_STATUS_CODE("flowstatuscode"),
	//未知类型
	NO_MATCHING_TYPE("404"),
	//控制卡类型
	GUIDESCREEN_CONTROLCARD_TYPE("guidescreenControlcardType"),
	//屏幕类型
	GUIDESCREEN_SCREEN_TYPE("guidescreenScreenType"),
	//LED屏通信模式
	CONNMODEL_TYPE("connModelType"),
	//知识类型
	KNOWLEDGE_TYPE("knowledgeType"),
	//告警方式
	ALARMTYPE("alarmtype"),
	//资产状态
	ASSETSTATUS("assetstatus"),
	//故障类型
	BREAKDOWN_TYPE("breakdownType"),
	//服务级别
	GRADE_TYPE("gradetype"),
	//事件告警级别
	EVENTLEVEL_GRADE_TYPE("eventLevelGradeType");
	
	private final String value;

    private DicType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
    
    public static DicType valuesOf(String type){
    	  for (DicType dicType : values()) {
              if (dicType.value.equals(type)) {
                  return dicType;
              }
          }
//         System.err.println("No matching constant for [" + type + "]");
    	 return DicType.NO_MATCHING_TYPE;
    }
    
    /**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	public String toString() {
        return this.value;
    }
    
    

}
