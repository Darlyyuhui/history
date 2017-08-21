package com.xiangxun.atms.module.property.domain;

import java.util.Date;
//@Document(collection="SYS_MODIFY_RECORD")
public class ModifyRecord {
//    @Indexed
    private String id;

//    @Indexed
    private String modifyId;

//    @Indexed
    private String moduleName;

//    @Indexed
    private String modifyType;

//    @Indexed
    private String modifyOperator;

//    @Indexed
    private Date modifyDatetime;

//    @Indexed
    private String modifyReason;

//    @Indexed
    private String version;

//    @Indexed
    private String operator;

//    @Indexed
    private Date operatorTime;
    
    private String modifyDatetimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId == null ? null : modifyId.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModifyType() {
        return modifyType;
    }

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType == null ? null : modifyType.trim();
    }

    public String getModifyOperator() {
        return modifyOperator;
    }

    public void setModifyOperator(String modifyOperator) {
        this.modifyOperator = modifyOperator == null ? null : modifyOperator.trim();
    }

    public Date getModifyDatetime() {
        return modifyDatetime;
    }

    public void setModifyDatetime(Date modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public void setModifyReason(String modifyReason) {
        this.modifyReason = modifyReason == null ? null : modifyReason.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

	public String getModifyDatetimeStr() {
		return modifyDatetimeStr;
	}

	public void setModifyDatetimeStr(String modifyDatetimeStr) {
		this.modifyDatetimeStr = modifyDatetimeStr;
	}
}