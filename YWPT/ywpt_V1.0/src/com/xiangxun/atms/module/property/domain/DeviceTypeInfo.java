package com.xiangxun.atms.module.property.domain;


public class DeviceTypeInfo {
    private String id;

    private String pid;

    private String name;

    private String note;

    private Integer position;

    private Integer levels;
    
    private String code;
    
    
    public DeviceTypeInfo() {
		super();
	}
    
    public DeviceTypeInfo(String id,String pid,String name,String note,int position,int levels,String code) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.note = note;
		this.position = position;
		this.levels = levels;
		this.code = code;
		
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }


    public void setPosition(Short position) {
        this.position = Integer.parseInt(position.toString());
    }


    public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public void setLevels(Short levels) {
        this.levels = Integer.parseInt(levels.toString());
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}