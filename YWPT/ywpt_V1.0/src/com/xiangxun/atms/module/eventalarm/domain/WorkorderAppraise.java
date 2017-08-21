package com.xiangxun.atms.module.eventalarm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//@Document(collection="ALARM_WORKORDER_APPRAISE")
public class WorkorderAppraise implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1158791321332507148L;

//	@Indexed
    private String id;

//    @Indexed
    private String workorderid;

//    @Indexed
    private String contactname;

//    @Indexed
    private String telephone;

//    @Indexed
    private String violaterule;

//    @Indexed
    private BigDecimal deductscore;

//    @Indexed
    private BigDecimal finalscore;

//    @Indexed
    private String appraiser;

//    @Indexed
    private Date appraisetime;
    
    //附加属性
    private String ruleids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWorkorderid() {
        return workorderid;
    }

    public void setWorkorderid(String workorderid) {
        this.workorderid = workorderid == null ? null : workorderid.trim();
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname == null ? null : contactname.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getViolaterule() {
        return violaterule;
    }

    public void setViolaterule(String violaterule) {
        this.violaterule = violaterule == null ? null : violaterule.trim();
    }

    public BigDecimal getDeductscore() {
        return deductscore;
    }

    public void setDeductscore(BigDecimal deductscore) {
        this.deductscore = deductscore;
    }

    public BigDecimal getFinalscore() {
        return finalscore;
    }

    public void setFinalscore(BigDecimal finalscore) {
        this.finalscore = finalscore;
    }

    public String getAppraiser() {
        return appraiser;
    }

    public void setAppraiser(String appraiser) {
        this.appraiser = appraiser == null ? null : appraiser.trim();
    }

    public Date getAppraisetime() {
        return appraisetime;
    }

    public void setAppraisetime(Date appraisetime) {
        this.appraisetime = appraisetime;
    }

	public String getRuleids() {
		return ruleids;
	}

	public void setRuleids(String ruleids) {
		this.ruleids = ruleids;
	}
}