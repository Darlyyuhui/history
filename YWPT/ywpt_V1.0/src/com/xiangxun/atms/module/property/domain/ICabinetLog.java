package com.xiangxun.atms.module.property.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//@Document(collection="OPTION_ICABINET_LOG")
public class ICabinetLog implements Serializable {
   // @Id
    private String id;

   // @Indexed
    private String ip;

  //  @Indexed
    private BigDecimal status;

  //  @Indexed
    private Object message;

   // @Indexed
    private String execNo;

  //  @Indexed
    private BigDecimal progress;

  //  @Indexed
    private Date opDate;

  //  @Indexed
    private Date lastTime;
    
    private CabInfo cabinfo;

    public CabInfo getCabinfo() {
		return cabinfo;
	}

	public void setCabinfo(CabInfo cabinfo) {
		this.cabinfo = cabinfo;
	}

	private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getExecNo() {
        return execNo;
    }

    public void setExecNo(String execNo) {
        this.execNo = execNo == null ? null : execNo.trim();
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}