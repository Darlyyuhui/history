package com.xiangxun.atms.module.question.domain;

import java.io.Serializable;
import java.util.Date;

//@Document(collection="QUESTION_BREAKDOWN_INFO")
public class BreakdownInfo implements Serializable {
//    @Id
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String type;

//    @Indexed
    private String deviceCode;

//    @Indexed
    private String factoryCode;

//    @Indexed
    private Date breakdownTime;

//    @Indexed
    private String remark;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode == null ? null : factoryCode.trim();
    }

    public Date getBreakdownTime() {
        return breakdownTime;
    }

    public void setBreakdownTime(Date breakdownTime) {
        this.breakdownTime = breakdownTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}