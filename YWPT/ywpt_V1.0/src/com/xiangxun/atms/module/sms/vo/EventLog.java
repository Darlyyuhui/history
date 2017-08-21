package com.xiangxun.atms.module.sms.vo;

import java.math.BigDecimal;
import java.util.Date;

//@Document(collection="SMS_EVENTLOG")
public class EventLog {
//    @Indexed
    private BigDecimal logindex;

//    @Indexed
    private Short commport;

 //   @Indexed
    private String description;

 //   @Indexed
    private Date happentime;

    public BigDecimal getLogindex() {
        return logindex;
    }

    public void setLogindex(BigDecimal logindex) {
        this.logindex = logindex;
    }

    public Short getCommport() {
        return commport;
    }

    public void setCommport(Short commport) {
        this.commport = commport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getHappentime() {
        return happentime;
    }

    public void setHappentime(Date happentime) {
        this.happentime = happentime;
    }
}