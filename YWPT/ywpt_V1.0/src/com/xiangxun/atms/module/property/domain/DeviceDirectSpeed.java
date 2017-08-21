package com.xiangxun.atms.module.property.domain;

public class DeviceDirectSpeed {
    private String id;

    private String deviceCode;

    private String directionCode;

    private String landtotal;

    private String carlimitspeed;

    private String carlimitspeedcatch;

    private String bigcarlimitspeed;

    private String bigcarlimitspeedcatch;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getDirectionCode() {
        return directionCode;
    }

    public void setDirectionCode(String directionCode) {
        this.directionCode = directionCode == null ? null : directionCode.trim();
    }

    public String getLandtotal() {
        return landtotal;
    }

    public void setLandtotal(String landtotal) {
        this.landtotal = landtotal == null ? null : landtotal.trim();
    }

	public String getCarlimitspeed() {
		return carlimitspeed;
	}

	public void setCarlimitspeed(String carlimitspeed) {
		this.carlimitspeed = carlimitspeed;
	}

	public String getCarlimitspeedcatch() {
		return carlimitspeedcatch;
	}

	public void setCarlimitspeedcatch(String carlimitspeedcatch) {
		this.carlimitspeedcatch = carlimitspeedcatch;
	}

	public String getBigcarlimitspeed() {
		return bigcarlimitspeed;
	}

	public void setBigcarlimitspeed(String bigcarlimitspeed) {
		this.bigcarlimitspeed = bigcarlimitspeed;
	}

	public String getBigcarlimitspeedcatch() {
		return bigcarlimitspeedcatch;
	}

	public void setBigcarlimitspeedcatch(String bigcarlimitspeedcatch) {
		this.bigcarlimitspeedcatch = bigcarlimitspeedcatch;
	}

}