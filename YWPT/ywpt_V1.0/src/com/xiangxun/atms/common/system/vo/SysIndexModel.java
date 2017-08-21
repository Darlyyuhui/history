package com.xiangxun.atms.common.system.vo;

import java.math.BigDecimal;

//@Document(collection="SYS_INDEX_MODEL")
public class SysIndexModel {
//    @Id
    private String id;

//    @Indexed
    private String code;

//    @Indexed
    private String htmlsrc;

 //   @Indexed
    private String name;

//    @Indexed
    private BigDecimal rowcount;

 //   @Indexed
    private String layout;

  //  @Indexed
    private String isshow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getHtmlsrc() {
        return htmlsrc;
    }

    public void setHtmlsrc(String htmlsrc) {
        this.htmlsrc = htmlsrc == null ? null : htmlsrc.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getRowcount() {
        return rowcount;
    }

    public void setRowcount(BigDecimal rowcount) {
        this.rowcount = rowcount;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout == null ? null : layout.trim();
    }

    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow == null ? null : isshow.trim();
    }
}