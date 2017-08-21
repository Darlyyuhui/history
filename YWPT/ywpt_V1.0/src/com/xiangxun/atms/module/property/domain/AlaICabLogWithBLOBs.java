package com.xiangxun.atms.module.property.domain;

import java.io.Serializable;

public class AlaICabLogWithBLOBs extends AlaICabLog implements Serializable {
    private byte[] photo1;

    private byte[] photo2;

    private static final long serialVersionUID = 1L;

    public byte[] getPhoto1() {
        return photo1;
    }

    public void setPhoto1(byte[] photo1) {
        this.photo1 = photo1;
    }

    public byte[] getPhoto2() {
        return photo2;
    }

    public void setPhoto2(byte[] photo2) {
        this.photo2 = photo2;
    }
}