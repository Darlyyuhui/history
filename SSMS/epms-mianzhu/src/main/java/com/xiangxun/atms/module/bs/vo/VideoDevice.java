package com.xiangxun.atms.module.bs.vo;

import java.math.BigDecimal;

public class VideoDevice {
    private String id;

    private String name;

    private String inCode;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String ip;

    private String port;

    private String user;

    private String pwd;

    private Integer status;

    /**
     * @return the value of ID
     */
    public String getId() {
        return id;
    }

    /**
    
     *
     * @param id the value for ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return the value of NAME
     */
    public String getName() {
        return name;
    }

    /**
    
     *
     * @param name the value for NAME
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return the value of IN_CODE
     */
    public String getInCode() {
        return inCode;
    }

    /**
    
     *
     * @param inCode the value for IN_CODE
     */
    public void setInCode(String inCode) {
        this.inCode = inCode == null ? null : inCode.trim();
    }

    public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
     * @return the value of IP
     */
    public String getIp() {
        return ip;
    }

    /**
    
     *
     * @param ip the value for IP
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * @return the value of PORT
     */
    public String getPort() {
        return port;
    }

    /**
    
     *
     * @param port the value for PORT
     */
    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    /**
     * @return the value of USER
     */
    public String getUser() {
        return user;
    }

    /**
    
     *
     * @param user the value for USER
     */
    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    /**
     * @return the value of PWD
     */
    public String getPwd() {
        return pwd;
    }

    /**
    
     *
     * @param pwd the value for PWD
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * @return the value of STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
    
     *
     * @param status the value for STATUS
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
    
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", inCode=").append(inCode);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", ip=").append(ip);
        sb.append(", port=").append(port);
        sb.append(", user=").append(user);
        sb.append(", pwd=").append(pwd);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}