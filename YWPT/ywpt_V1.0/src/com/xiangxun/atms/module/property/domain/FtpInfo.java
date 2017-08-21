package com.xiangxun.atms.module.property.domain;

import java.io.Serializable;

//@Document(collection="PROPERTY_FTP_INFO")
public class FtpInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3484429912267286936L;

//	@Indexed
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String ftptype;

//    @Indexed
    private String ip;

//    @Indexed
    private String port;

//    @Indexed
    private String username;

//    @Indexed
    private String password;

//    @Indexed
    private String dirname;

//    @Indexed
    private String factoryId;
    
    //附加属性
    private String factoryName;
    //是否分配
    private boolean hasAllot;

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

    public String getFtptype() {
        return ftptype;
    }

    public void setFtptype(String ftptype) {
        this.ftptype = ftptype == null ? null : ftptype.trim();
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

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname == null ? null : dirname.trim();
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public boolean isHasAllot() {
		return hasAllot;
	}

	public void setHasAllot(boolean hasAllot) {
		this.hasAllot = hasAllot;
	}
}