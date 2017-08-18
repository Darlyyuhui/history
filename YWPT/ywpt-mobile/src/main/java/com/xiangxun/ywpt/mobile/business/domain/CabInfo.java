package com.xiangxun.ywpt.mobile.business.domain;

import java.io.Serializable;
import java.util.Date;


//@Document(collection="PROPERTY_ICABINET_INFO")
public class CabInfo implements Serializable {
  //  @Id
    private String id;

  //  @Indexed
    private String orgId;

//    @Indexed
    private String code;

 //   @Indexed
    private String ip;

 //   @Indexed
    private String name;

//    @Indexed
    private String companyId;

 //   @Indexed
    private String roadId;

  //  @Indexed
    private String isshow;

  //  @Indexed
    private Date buildtime;

  //  @Indexed
    private String buildnetworkId;

   // @Indexed
    private String photo1;

  //  @Indexed
    private String photo2;

   // @Indexed
    private String photo3;

   // @Indexed
    private String sn;

  //  @Indexed
    private Date lastdatatime;

 //   @Indexed
    private String toporgcode;

  //  @Indexed
    private String devicetypecode;

 //   @Indexed
    private String trademark;

 //   @Indexed
    private String pattern;

  //  @Indexed
    private Date efficaciousStime;

  //  @Indexed
    private Date fficaciousEtime;

 //   @Indexed
    private String inspectorg;

 //   @Indexed
    private Date inspecttime;

 //   @Indexed
    private String inspectnumber;

 //   @Indexed
    private String mapx;

  //  @Indexed
    private String mapy;

  //  @Indexed
    private String issend;

 //   @Indexed
    private Date inserttime;

  //  @Indexed
    private String note;

  //  @Indexed
    private String status;

  //  @Indexed
    private String factoryId;

//    @Indexed
    private String port;

  //  @Indexed
    private String alarmhostip;
    
    

 
	//  @Indexed
    private String alarmhostport;

    private static final long serialVersionUID = 1L;
    
    private boolean HasAllot;

    public boolean isHasAllot() {
		return HasAllot;
	}

	public void setHasAllot(boolean hasAllot) {
		HasAllot = hasAllot;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId == null ? null : roadId.trim();
    }

    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow == null ? null : isshow.trim();
    }

    public Date getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Date buildtime) {
        this.buildtime = buildtime;
    }

    public String getBuildnetworkId() {
        return buildnetworkId;
    }

    public void setBuildnetworkId(String buildnetworkId) {
        this.buildnetworkId = buildnetworkId == null ? null : buildnetworkId.trim();
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1 == null ? null : photo1.trim();
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2 == null ? null : photo2.trim();
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3 == null ? null : photo3.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Date getLastdatatime() {
        return lastdatatime;
    }

    public void setLastdatatime(Date lastdatatime) {
        this.lastdatatime = lastdatatime;
    }

    public String getToporgcode() {
        return toporgcode;
    }

    public void setToporgcode(String toporgcode) {
        this.toporgcode = toporgcode == null ? null : toporgcode.trim();
    }

    public String getDevicetypecode() {
        return devicetypecode;
    }

    public void setDevicetypecode(String devicetypecode) {
        this.devicetypecode = devicetypecode == null ? null : devicetypecode.trim();
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark == null ? null : trademark.trim();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern == null ? null : pattern.trim();
    }

    public Date getEfficaciousStime() {
        return efficaciousStime;
    }

    public void setEfficaciousStime(Date efficaciousStime) {
        this.efficaciousStime = efficaciousStime;
    }

    public Date getFficaciousEtime() {
        return fficaciousEtime;
    }

    public void setFficaciousEtime(Date fficaciousEtime) {
        this.fficaciousEtime = fficaciousEtime;
    }

    public String getInspectorg() {
        return inspectorg;
    }

    public void setInspectorg(String inspectorg) {
        this.inspectorg = inspectorg == null ? null : inspectorg.trim();
    }

    public Date getInspecttime() {
        return inspecttime;
    }

    public void setInspecttime(Date inspecttime) {
        this.inspecttime = inspecttime;
    }

    public String getInspectnumber() {
        return inspectnumber;
    }

    public void setInspectnumber(String inspectnumber) {
        this.inspectnumber = inspectnumber == null ? null : inspectnumber.trim();
    }

    public String getMapx() {
        return mapx;
    }

    public void setMapx(String mapx) {
        this.mapx = mapx == null ? null : mapx.trim();
    }

    public String getMapy() {
        return mapy;
    }

    public void setMapy(String mapy) {
        this.mapy = mapy == null ? null : mapy.trim();
    }

    public String getIssend() {
        return issend;
    }

    public void setIssend(String issend) {
        this.issend = issend == null ? null : issend.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getAlarmhostip() {
        return alarmhostip;
    }

    public void setAlarmhostip(String alarmhostip) {
        this.alarmhostip = alarmhostip == null ? null : alarmhostip.trim();
    }

    public String getAlarmhostport() {
        return alarmhostport;
    }

    public void setAlarmhostport(String alarmhostport) {
        this.alarmhostport = alarmhostport == null ? null : alarmhostport.trim();
    }
  

}