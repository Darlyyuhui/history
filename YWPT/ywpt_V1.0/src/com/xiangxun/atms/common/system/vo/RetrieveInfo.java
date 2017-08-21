package com.xiangxun.atms.common.system.vo;

import java.io.Serializable;
import java.util.Date;

//@Document(collection="SYS_RETRIEVE_INFO")
public class RetrieveInfo  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4710101757301806216L;

//	@Id
    private String id;

//  @Indexed
    private Date deltime;

//   @Indexed
    private String operator;

//   @Indexed
    private String clearflag;

//    @Indexed
    private String pojoclass;

//    @Indexed
    private String operatorip;

 //   @Indexed
    private String content;
    
    
    private String deltimeStr;
    private String startTime;
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getDeltime() {
        return deltime;
    }

    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getClearflag() {
        return clearflag;
    }

    public void setClearflag(String clearflag) {
        this.clearflag = clearflag == null ? null : clearflag.trim();
    }

    public String getPojoclass() {
        return pojoclass;
    }

    public void setPojoclass(String pojoclass) {
        this.pojoclass = pojoclass == null ? null : pojoclass.trim();
    }

    public String getOperatorip() {
        return operatorip;
    }

    public void setOperatorip(String operatorip) {
        this.operatorip = operatorip == null ? null : operatorip.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getDeltimeStr() {
		return deltimeStr;
	}

	public void setDeltimeStr(String deltimeStr) {
		this.deltimeStr = deltimeStr;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
    
}