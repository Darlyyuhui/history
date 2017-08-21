package com.xiangxun.atms.module.repository.domain;

import java.io.Serializable;
import java.math.BigDecimal;

//@Document(collection="REPOSITORY_CATALOG_INFO")
public class CatalogInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6751490176850007245L;

//	@Indexed
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String pid;

//    @Indexed
    private BigDecimal levels;

//    @Indexed
    private BigDecimal disabled;

//    @Indexed
    private String note;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public BigDecimal getLevels() {
        return levels;
    }

    public void setLevels(BigDecimal levels) {
        this.levels = levels;
    }

    public BigDecimal getDisabled() {
        return disabled;
    }

    public void setDisabled(BigDecimal disabled) {
        this.disabled = disabled;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}