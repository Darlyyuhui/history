package com.xiangxun.atms.module.gplot.domain;

import java.math.BigDecimal;
import java.util.Date;

//@Document(collection="STATUS_APP")
public class StatusApp {
    
    private String id;

    private String ip;

    private String type;

    private BigDecimal connectStatus;

    private String resourceInfo;

    private String appName;

    private BigDecimal appStatus;

    private String path;

    private Date inserttime;

    private String insertpc;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(BigDecimal connectStatus) {
        this.connectStatus = connectStatus;
    }

    public String getResourceInfo() {
        return resourceInfo;
    }

    public void setResourceInfo(String resourceInfo) {
        this.resourceInfo = resourceInfo == null ? null : resourceInfo.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public BigDecimal getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(BigDecimal appStatus) {
        this.appStatus = appStatus;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getInsertpc() {
        return insertpc;
    }

    public void setInsertpc(String insertpc) {
        this.insertpc = insertpc == null ? null : insertpc.trim();
    }
}