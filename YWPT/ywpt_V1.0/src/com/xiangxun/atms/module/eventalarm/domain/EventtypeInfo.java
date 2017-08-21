package com.xiangxun.atms.module.eventalarm.domain;

import java.io.Serializable;

//@Document(collection="ALARM_EVENTTYPE_INFO")
public class EventtypeInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7400880161919207100L;

//	@Indexed
    private String id;

 //   @Indexed
    private String name;
    
//    @Indexed
    private String code;

//    @Indexed
    private String relationLevel;

 //   @Indexed
    private String note;
    
    //类别
//    @Indexed
    private String type;

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

    public String getRelationLevel() {
        return relationLevel;
    }

    public void setRelationLevel(String relationLevel) {
        this.relationLevel = relationLevel == null ? null : relationLevel.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}