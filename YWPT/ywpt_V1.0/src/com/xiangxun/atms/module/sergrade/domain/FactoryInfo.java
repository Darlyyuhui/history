package com.xiangxun.atms.module.sergrade.domain;

import java.io.Serializable;
import java.math.BigDecimal;

//@Document(collection="SERGRADE_FACTORY_INFO")
public class FactoryInfo implements Serializable {
//    @Id
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String linkman;

//    @Indexed
    private String telphone;

//    @Indexed
    private BigDecimal point;

//    @Indexed
    private String gradeCode;

//    @Indexed
    private String remark;
    
    //服务商级别
    private String level;

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

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode == null ? null : gradeCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
    
    
}