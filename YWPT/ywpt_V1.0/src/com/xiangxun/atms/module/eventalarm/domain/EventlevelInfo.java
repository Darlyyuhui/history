package com.xiangxun.atms.module.eventalarm.domain;

import java.io.Serializable;
import java.math.BigDecimal;

//@Document(collection="ALARM_EVENTLEVEL_INFO")
public class EventlevelInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6161545913659450716L;

//	@Indexed
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String relationType;

//    @Indexed
    private String note;
    
//    @Indexed
    private String color;
    
//  @Indexed
    private BigDecimal healthcoefficient;
    
//@Indexed
    private BigDecimal grade;
    
    private String typeName;
    
    private String typein;

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

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType == null ? null : relationType.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypein() {
		return typein;
	}

	public void setTypein(String typein) {
		this.typein = typein;
	}

	public BigDecimal getHealthcoefficient() {
        return healthcoefficient;
    }

    public void setHealthcoefficient(BigDecimal healthcoefficient) {
        this.healthcoefficient = healthcoefficient;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
	
	
}