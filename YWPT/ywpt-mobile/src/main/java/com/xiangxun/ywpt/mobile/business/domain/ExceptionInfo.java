package com.xiangxun.ywpt.mobile.business.domain;

import java.io.Serializable;
import java.util.Date;

//@Document(collection="ALARM_EXCEPTION_INFO")
public class ExceptionInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1549603607985036830L;

//	@Indexed
    private String id;

//    @Indexed
    private String operator;

//    @Indexed
    private Date reporttime;

//    @Indexed
    private String content;
    
    //附加属性
    private String reporttimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getReporttimeStr() {
		return reporttimeStr;
	}

	public void setReporttimeStr(String reporttimeStr) {
		this.reporttimeStr = reporttimeStr;
	}
}