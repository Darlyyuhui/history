package com.xiangxun.atms.module.eventalarm.domain;

import java.io.Serializable;
import java.math.BigDecimal;

//@Document(collection="ALARM_WORKORDER_RULE")
public class WorkorderRule implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5286287314534770038L;

//	@Indexed
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String describe;

//    @Indexed
    private String code;

//    @Indexed
    private BigDecimal score;

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}