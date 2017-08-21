package com.xiangxun.atms.module.eventalarm.domain;

import java.io.Serializable;
import java.util.Date;
//@Document(collection="STATUS_SERVER_PROCESS")
public class ServerStatusProcess implements Serializable {
//    @Id
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String pid;

//    @Indexed
    private String talkname;

//    @Indexed
    private String memory;

//    @Indexed
    private String userstr;

//    @Indexed
    private String ip;

//    @Indexed
    private Date inserttime;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getTalkname() {
        return talkname;
    }

    public void setTalkname(String talkname) {
        this.talkname = talkname == null ? null : talkname.trim();
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory == null ? null : memory.trim();
    }

    public String getUserstr() {
        return userstr;
    }

    public void setUserstr(String userstr) {
        this.userstr = userstr == null ? null : userstr.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }
}