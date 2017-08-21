package com.xiangxun.atms.module.perambulate.domain;

public class PerambulateInfoWithBLOBs extends PerambulateInfo {
    private byte[] picture1;

    private byte[] picture2;

    private byte[] picture3;

    public byte[] getPicture1() {
        return picture1;
    }

    public void setPicture1(byte[] picture1) {
        this.picture1 = picture1;
    }

    public byte[] getPicture2() {
        return picture2;
    }

    public void setPicture2(byte[] picture2) {
        this.picture2 = picture2;
    }

    public byte[] getPicture3() {
        return picture3;
    }

    public void setPicture3(byte[] picture3) {
        this.picture3 = picture3;
    }
}