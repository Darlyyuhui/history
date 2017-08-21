package com.xiangxun.atms.common.road.vo;

import java.io.Serializable;

//@Document(collection="SYS_ROAD_INFO")
public class RoadInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7893208001838029470L;

//	@Id
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String note;

//    @Indexed
    private String groupId;

 //   @Indexed
    private String pid;

//    @Indexed
    private Integer levels;

 //   @Indexed
    private String status;

//    @Indexed
    private String uploadcode;

//    @Indexed
    private String coderoadtype;
    
    
    //扩展FORM属性
    
    private String codeRoadType;
    private String codeRoadDh;
    private String codeRoadZh;
    private String codeRoadMi;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

   

    public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUploadcode() {
        return uploadcode;
    }

    public void setUploadcode(String uploadcode) {
        this.uploadcode = uploadcode == null ? null : uploadcode.trim();
    }

    public String getCoderoadtype() {
        return coderoadtype;
    }

    public void setCoderoadtype(String coderoadtype) {
        this.coderoadtype = coderoadtype == null ? null : coderoadtype.trim();
    }

	public String getCodeRoadType() {
		return codeRoadType;
	}

	public void setCodeRoadType(String codeRoadType) {
		this.codeRoadType = codeRoadType;
	}

	public String getCodeRoadDh() {
		return codeRoadDh;
	}

	public void setCodeRoadDh(String codeRoadDh) {
		this.codeRoadDh = codeRoadDh;
	}

	public String getCodeRoadZh() {
		return codeRoadZh;
	}

	public void setCodeRoadZh(String codeRoadZh) {
		this.codeRoadZh = codeRoadZh;
	}

	public String getCodeRoadMi() {
		return codeRoadMi;
	}

	public void setCodeRoadMi(String codeRoadMi) {
		this.codeRoadMi = codeRoadMi;
	}
    
}