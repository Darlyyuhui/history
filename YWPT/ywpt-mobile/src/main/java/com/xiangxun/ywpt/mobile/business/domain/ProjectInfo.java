package com.xiangxun.ywpt.mobile.business.domain;

import java.io.Serializable;

//@Document(collection="PROPERTY_PROJECT_INFO")
public class ProjectInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7542220119559568044L;

//	@Indexed
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String ip;

//    @Indexed
    private String port;

//    @Indexed
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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