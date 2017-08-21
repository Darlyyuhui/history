package com.xiangxun.atms.module.sergrade.domain;

import java.io.Serializable;
import java.math.BigDecimal;

//@Document(collection="SERGRADE_GRADE_INFO")
public class GradeInfo implements Serializable {
//    @Id
    private String id;

//    @Indexed
    private String code;

//    @Indexed
    private String name;

//    @Indexed
    private String remark;

//    @Indexed
    private BigDecimal minpoint;

//    @Indexed
    private BigDecimal maxpoint;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getMinpoint() {
        return minpoint;
    }

    public void setMinpoint(BigDecimal minpoint) {
        this.minpoint = minpoint;
    }

    public BigDecimal getMaxpoint() {
        return maxpoint;
    }

    public void setMaxpoint(BigDecimal maxpoint) {
        this.maxpoint = maxpoint;
    }
}