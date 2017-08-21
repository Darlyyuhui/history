package com.xiangxun.atms.module.eventalarm.status;

/**
 * 工单状态枚举类型
 * @author kouyunhao
 * 工单状态：0-已派发；1-已接收；2-已退回；3-已转派；4-已上报；5-遗留中；6-已关闭；7-已评估;8-已完成。
 */
public enum WorkorderStatus {
	//未知类型
	NO_MATCHING_TYPE("404"),
	//已派发
	ASSIGNED("0"),
	//已接收
	RECEIVED("1"),
	//已退回
	REFUSED("2"),
	//已转派
	REASSIGNED("3"),
	//已上报
	REPORTED("4"),
	//遗留中
	LEAVING("5"),
	//已关闭
	CLOSED("6"),
	//已评估
	APPRAISED("7"),
	//已完成
	COMPLETE("8"),
	;
	
	private final String value;

    private WorkorderStatus(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
    
    public static WorkorderStatus valuesOf(String type){
    	  for (WorkorderStatus WorkorderStatus : values()) {
              if (WorkorderStatus.value.equals(type)) {
                  return WorkorderStatus;
              }
          }
         System.err.println("No matching constant for [" + type + "]");
    	 return WorkorderStatus.NO_MATCHING_TYPE;
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