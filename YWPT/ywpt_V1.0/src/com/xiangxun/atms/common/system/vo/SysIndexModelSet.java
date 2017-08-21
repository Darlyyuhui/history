package com.xiangxun.atms.common.system.vo;

import java.util.Date;

//@Document(collection="SYS_INDEX_MODEL_SET")
public class SysIndexModelSet {
//    @Id
    private String id;

 //   @Indexed
    private String left;

 //   @Indexed
    private String center;

//    @Indexed
    private String right;

//    @Indexed
    private Date inserttime;

//    @Indexed
    private String userid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left == null ? null : left.trim();
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center == null ? null : center.trim();
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right == null ? null : right.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}