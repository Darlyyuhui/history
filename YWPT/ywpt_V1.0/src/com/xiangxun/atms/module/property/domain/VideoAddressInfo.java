package com.xiangxun.atms.module.property.domain;

public class VideoAddressInfo {
	
    private String id;

    private String ip;

    private String port;

    private String tracode;

    private String username;

    private String password;

    private String isrealitime;

    private String name;

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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getTracode() {
        return tracode;
    }

    public void setTracode(String tracode) {
        this.tracode = tracode == null ? null : tracode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIsrealitime() {
        return isrealitime;
    }

    public void setIsrealitime(String isrealitime) {
        this.isrealitime = isrealitime == null ? null : isrealitime.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}