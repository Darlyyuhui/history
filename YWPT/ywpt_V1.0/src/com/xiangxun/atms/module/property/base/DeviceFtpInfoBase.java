package com.xiangxun.atms.module.property.base;

import java.io.Serializable;

//@Document(collection="PROPERTY_FTP_INFO")
public class DeviceFtpInfoBase implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8355239498887243631L;

//	@Id
    private String id;

//    @Indexed
    private String ftpip;

//    @Indexed
    private String ftpuser;

//    @Indexed
    private String ftppassword;

//    @Indexed
    private String ftpport;

//    @Indexed
    private String name;

//    @Indexed
    private String httpport;

//    @Indexed
    private String dirname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFtpip() {
        return ftpip;
    }

    public void setFtpip(String ftpip) {
        this.ftpip = ftpip == null ? null : ftpip.trim();
    }

    public String getFtpuser() {
        return ftpuser;
    }

    public void setFtpuser(String ftpuser) {
        this.ftpuser = ftpuser == null ? null : ftpuser.trim();
    }

    public String getFtppassword() {
        return ftppassword;
    }

    public void setFtppassword(String ftppassword) {
        this.ftppassword = ftppassword == null ? null : ftppassword.trim();
    }

    public String getFtpport() {
        return ftpport;
    }

    public void setFtpport(String ftpport) {
        this.ftpport = ftpport == null ? null : ftpport.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHttpport() {
        return httpport;
    }

    public void setHttpport(String httpport) {
        this.httpport = httpport == null ? null : httpport.trim();
    }

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname == null ? null : dirname.trim();
    }
}